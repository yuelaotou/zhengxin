package org.xpup.hafmis.sysloan.loancallback.consultation.business;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.xpup.common.exception.BusinessException;
import org.xpup.common.util.Pagination;
import org.xpup.hafmis.common.util.BusiConst;
import org.xpup.hafmis.common.util.BusiTools;
import org.xpup.hafmis.orgstrct.dao.CollBankDAO;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.sysloan.common.arithmetic.corpusinterest.CorpusinterestBS;
import org.xpup.hafmis.sysloan.common.arithmetic.punishinterest.PunishInterestBS;
import org.xpup.hafmis.sysloan.common.dao.BorrowerAccDAO;
import org.xpup.hafmis.sysloan.common.dao.LoanBankDAO;
import org.xpup.hafmis.sysloan.common.dao.LoanBankParaDAO;
import org.xpup.hafmis.sysloan.common.dao.LoanContractParaDAO;
import org.xpup.hafmis.sysloan.common.dao.LoanFlowHeadDAO;
import org.xpup.hafmis.sysloan.common.dao.LoanFlowTailDAO;
import org.xpup.hafmis.sysloan.common.dao.PlBizActiveLogDAO;
import org.xpup.hafmis.sysloan.common.dao.PlDocNumCancelDAO;
import org.xpup.hafmis.sysloan.common.dao.PlDocNumMaintainDAO;
import org.xpup.hafmis.sysloan.common.dao.PlOperateLogDAO;
import org.xpup.hafmis.sysloan.common.dao.RestoreLoanDAO;
import org.xpup.hafmis.sysloan.loancallback.consultation.bsinterface.IConsultationBS;
import org.xpup.hafmis.sysloan.loancallback.loancallback.dto.BorrowerInfoDTO;
import org.xpup.hafmis.sysloan.loancallback.loancallback.dto.ShouldBackListDTO;
import org.xpup.hafmis.sysloan.loancallback.loancallback.form.LoancallbackTaAF;

public class ConsultationBS implements IConsultationBS{
  private BorrowerAccDAO borrowerAccDAO = null;

  private LoanFlowHeadDAO loanFlowHeadDAO = null;

  private LoanFlowTailDAO loanFlowTailDAO = null;

  private RestoreLoanDAO restoreLoanDAO = null;

  private LoanBankParaDAO loanBankParaDAO = null;
  
  private LoanContractParaDAO loanContractParaDAO = null;
  
  private PlOperateLogDAO plOperateLogDAO = null;
  
  private PlBizActiveLogDAO plBizActiveLogDAO = null;
  
  private PlDocNumMaintainDAO plDocNumMaintainDAO = null;
  
  private PlDocNumCancelDAO plDocNumCancelDAO = null;
  
  private CollBankDAO collBankDAO = null;
  
  private LoanBankDAO loanBankDAO = null;

  public void setLoanBankDAO(LoanBankDAO loanBankDAO) {
    this.loanBankDAO = loanBankDAO;
  }

  public void setCollBankDAO(CollBankDAO collBankDAO) {
    this.collBankDAO = collBankDAO;
  }

  public void setPlDocNumMaintainDAO(PlDocNumMaintainDAO plDocNumMaintainDAO) {
    this.plDocNumMaintainDAO = plDocNumMaintainDAO;
  }

  public void setBorrowerAccDAO(BorrowerAccDAO borrowerAccDAO) {
    this.borrowerAccDAO = borrowerAccDAO;
  }

  public void setLoanFlowHeadDAO(LoanFlowHeadDAO loanFlowHeadDAO) {
    this.loanFlowHeadDAO = loanFlowHeadDAO;
  }

  public void setLoanFlowTailDAO(LoanFlowTailDAO loanFlowTailDAO) {
    this.loanFlowTailDAO = loanFlowTailDAO;
  }

  public void setRestoreLoanDAO(RestoreLoanDAO restoreLoanDAO) {
    this.restoreLoanDAO = restoreLoanDAO;
  }

  public void setLoanBankParaDAO(LoanBankParaDAO loanBankParaDAO) {
    this.loanBankParaDAO = loanBankParaDAO;
  }

  public void setLoanContractParaDAO(LoanContractParaDAO loanContractParaDAO) {
    this.loanContractParaDAO = loanContractParaDAO;
  }

  public void setPlOperateLogDAO(PlOperateLogDAO plOperateLogDAO) {
    this.plOperateLogDAO = plOperateLogDAO;
  }

  public void setPlBizActiveLogDAO(PlBizActiveLogDAO plBizActiveLogDAO) {
    this.plBizActiveLogDAO = plBizActiveLogDAO;
  }

  public void setPlDocNumCancelDAO(PlDocNumCancelDAO plDocNumCancelDAO) {
    this.plDocNumCancelDAO = plDocNumCancelDAO;
  }

  public String findBorrowerAcc(String loanKouAcc, String contractSt,
      SecurityInfo securityInfo) {
    String contractId = "";
    contractId = borrowerAccDAO.queryBorrowerAccByLoanKouAcc_LJ(loanKouAcc,
        contractSt, securityInfo);
    return contractId;
  }


  // 办理页面，根据贷款账号查询应还款信息
  public LoancallbackTaAF findShouldLoancallbackInfo(Pagination pagination,
      SecurityInfo securityInfo) throws Exception {
    LoancallbackTaAF af = new LoancallbackTaAF();
    BorrowerInfoDTO borrowerInfoDTO = new BorrowerInfoDTO();
    String bizDate = securityInfo.getUserInfo().getBizDate();// 会计日期
    List shouldBackList = null;// 应还信息
    List bizStList = null;// 用于查询是否存在未记账的状态
    BigDecimal ovaerLoanRepay = new BigDecimal(0.00);// 挂账余额
    String pldebit = "";// 扣款方式
    Integer loanBankId = null;// 放款银行
    List borrowerList = new ArrayList();// 账户信息
    String contractSt = BusiConst.PLCONTRACTSTATUS_RECOVING+"";// 合同状态 11.还款中
    String contractId = "";// 合同编号
    String loanRepayDay = "";// 还款日 在取应还款信息时用到
    String paramType = "A";// 参数类型
    // 从PL003中查询扣款方式(全额扣款、足额扣款)
    pldebit = loanBankParaDAO.queryParamValue_LJ(loanBankId, paramType, "1");
    // 贷款账号
    String loanKouAcc = (String) pagination.getQueryCriterions().get(
        "loanKouAcc");
    // 业务类型
    String bizType = (String) pagination.getQueryCriterions().get("bizType");
    String aheadMoney = (String)pagination.getQueryCriterions().get("aheadMoney");//提前还款金额
    String yearMonth = bizDate.substring(0, 6);// 取出会计日期中的年月
    String day = bizDate.substring(6, bizDate.length());// 取出会计日期中的日
    String paramValue = "";
    String isAmend = "1";//是否可以更改实收金额(0.不可以；1.可以)
    af.setBizType("2");
    if (loanKouAcc != null && !loanKouAcc.equals("")) {
      // 判断贷款账号在PL111中是否存在并且状态为还款中。
      contractId = findBorrowerAcc(loanKouAcc, contractSt, securityInfo);
      if (contractId == null) {
        throw new BusinessException("此贷款账号不存在！");
      }
      pagination.getQueryCriterions().put("contractId", contractId);
      // 该贷款账号在贷款流水账头表PL202表中是否存在BIZ_ST!=6(未记账)（关联贷款流水账尾表PL203）
    //  bizStList = loanFlowHeadDAO.queryBizStListByLoanKouAcc_LJ(contractId);
      bizStList = loanFlowHeadDAO.queryBizStListByLoanKouAcc_LJ(contractId,null);
      if (!bizStList.isEmpty()) {
        throw new BusinessException("存在未记账的业务！");
      } 
      // 从PL110及PL111取信息
      borrowerList = borrowerAccDAO
          .queryBorrowerAccInfoByLoanKouAcc_LJ(contractId);
      if (!borrowerList.isEmpty()) {
        borrowerInfoDTO = (BorrowerInfoDTO) borrowerList.get(0);
      }
      borrowerInfoDTO.setCardKind(BusiTools.getBusiValue(Integer.parseInt(borrowerInfoDTO.getCardKind()), BusiConst.DOCUMENTSSTATE));
      borrowerInfoDTO.setLoanMode(BusiTools.getBusiValue(Integer.parseInt(borrowerInfoDTO.getLoanMode()), BusiConst.PLRECOVERTYPE));
      loanRepayDay = borrowerInfoDTO.getLoanRepayDay();
      ovaerLoanRepay = borrowerInfoDTO.getOvaerLoanRepay();
      loanBankId = borrowerInfoDTO.getLoanBankId();
      // 从PL201中查应还信息
      if (Integer.parseInt(day) < Integer.parseInt(loanRepayDay)) {
        // 会计日小于还款日
        shouldBackList = restoreLoanDAO.queryRestoreLoanListByContractId_LJA(
            contractId, yearMonth);
      } else {
        // 会计日大于等于还款日
        shouldBackList = restoreLoanDAO.queryRestoreLoanListByContractId_LJB(
            contractId, yearMonth);
      }
      // 单笔回收2
      if (bizType==null) {
        bizType = BusiConst.PLBUSINESSTYPE_SINGLERECOVER + "";
      }
      LoancallbackTaAF af1 = this.findCallbackList(shouldBackList, borrowerInfoDTO, bizDate);
      // 单笔回收2
      if (bizType.equals(BusiConst.PLBUSINESSTYPE_SINGLERECOVER + "")) {
        // 挂账余额=借款人账户表PL111中挂账余额，
        // A.当挂账余额大于等于本次总还款金额：本次实收金额=0，挂账发生额=本次总还款金额
        // B.当挂账余额小于本次总还款金额：本次实收金额=本次总还款金额-挂账余额 （如果PL003中类型为：还款参数，序号为1中的参数值=2：
        // 全额扣款，本次实收金额可以修改，但要大于等于0小于等于默认显示的实收金额），挂账发生额=挂账余额
        af.setSumCorpus(af1.getSumCorpus());
        af.setSumInterest(af1.getSumInterest());
        af.setSumMoney(af.getSumCorpus().add(af.getSumInterest()));
        if (ovaerLoanRepay.doubleValue() >= af.getSumMoney().doubleValue()) {
          af.setRealMoney(new BigDecimal(0.00));
          af.setOverOccurMoney(af.getSumMoney());
        } else {
          af.setRealMoney(af.getSumMoney().subtract(ovaerLoanRepay));
          af.setOverOccurMoney(ovaerLoanRepay);
        }
      } else if (bizType.equals(BusiConst.PLBUSINESSTYPE_PARTRECOVER + "")) {
        // 部分提前还款3
        af = this.partAheadInfo(bizDate, borrowerInfoDTO,af1,aheadMoney);
      } else if (bizType.equals(BusiConst.PLBUSINESSTYPE_ALLCLEAR + "")) {
        // 一次性还清4
        af = fullAheadInfo(borrowerInfoDTO, bizDate, af1);
      }
      if(!shouldBackList.isEmpty()){
          ShouldBackListDTO dto = (ShouldBackListDTO) shouldBackList.get(shouldBackList.size()-1);
          af.setMonthYear(dto.getLoanKouYearmonth());
        }
      //判断该贷款账号的所属银行在银行贷款参数PL003表中参数类型PARAM_TYPE=A:参数维护：还款参数and 参数序号PARAM_NUM=1的参数值PARAM_VALUE是否=1:足额扣款
      paramValue = loanBankParaDAO.queryParamValue_LJ(loanBankId, paramType, "1");
      if(paramValue.equals(BusiConst.PLDEBITTYPE_SUFF+"")){
        isAmend = "0";
      }else if(!bizType.equals(BusiConst.PLBUSINESSTYPE_SINGLERECOVER + "")){
        isAmend = "0";
      }
      af.setIsAmend(isAmend);
      af.setOvaerLoanRepay(ovaerLoanRepay);
      af.setPldebit(pldebit);
      af.setBorrowerInfoDTO(borrowerInfoDTO);
      af.setShouldBackList(af1.getShouldBackList());
      af.setBizType(bizType);
      af.setLoanBalance(borrowerInfoDTO.getOverplusLoanMoney());
    }
    return af;
  }
  /**
   * 判断是否允许部份提前
   * @param bizDate
   * @param borrowerInfoDTO
   * @param af1
   * @param aheadMoney
   * @return
   * @throws Exception
   */
  //部份提前
  public LoancallbackTaAF partAheadInfo(String bizDate,BorrowerInfoDTO borrowerInfoDTO,LoancallbackTaAF af1,String aheadMoney)throws Exception{
    LoancallbackTaAF af = new LoancallbackTaAF();
    String paramType = "B";//参数类型
    String paramValue = "";//参数值
    String paramExplain = "";//参数说明
    String loanStartDate = borrowerInfoDTO.getLoanStartDate();//发放日期
    Integer loanBankId = borrowerInfoDTO.getLoanBankId();//放款银行
    String contractId = borrowerInfoDTO.getContractId();//合同编号
    BigDecimal aheadCorpus = new BigDecimal(0.00);//提前还款本金
    BigDecimal overplusLoanMoney = new BigDecimal(0.00);//剩余本金
    overplusLoanMoney= borrowerInfoDTO.getOverplusLoanMoney();
    if(aheadMoney != null){
      aheadCorpus = new BigDecimal(aheadMoney);
    }else{
      aheadCorpus = overplusLoanMoney.subtract(af1.getSumCorpus());
    }
    paramValue = loanContractParaDAO.queryParamValue_LJ(loanBankId, paramType, "2",contractId);
    //判断该贷款号在合同贷款参数PL004表中参数序号=2的参数值是否=1(不允许提前还款)
    if(paramValue.equals(BusiConst.PLCOMMONSTATUS_2_NOTALLOW+"")){
      //throw new BusinessException("不允许提前还款！");
      //取出该参数对应的参数说明PARAM_EXPLAIN，判断（会计日期-PL111表中发放日期LOAN_START_DATE）的月数是否大于等于该值
      paramExplain = loanContractParaDAO.queryParamExplain_LJ(loanBankId, paramType, "2",contractId);
      int temp_monthCounts=BusiTools.monthInterval(loanStartDate, bizDate);
      if(temp_monthCounts>=Integer.parseInt(paramExplain)){
        //取出该贷款号在合同贷款参数PL004表中参数序号=5的参数说明，判断该贷款账在贷款流水账头表PL202中(会计年内1-12)提前还款的次数是否小于该值
        paramExplain = loanContractParaDAO.queryParamExplain_LJ(loanBankId, paramType, "5",contractId);
        //会计年内提前还款次数
        int aheadCounts=loanFlowTailDAO.queryCallbackAheadCounts_LJ(contractId, bizDate).intValue();
        if(aheadCounts<Integer.parseInt(paramExplain)){
          //取出该贷款号在合同贷款参数PL004表中参数序号=6的参数说明，判断该贷款账在贷款流水账头表PL202中是提前还款的次数是否小于该值
          paramExplain = loanContractParaDAO.queryParamExplain_LJ(loanBankId, paramType, "6",contractId);
          //贷款期限内提前次数
          int lineCounts=loanFlowTailDAO.queryCallbackAheadCounts_LJ(contractId, null).intValue();;
          if(lineCounts<Integer.parseInt(paramExplain)){
            //允许
            af = findPartAheadInfo(borrowerInfoDTO, af1, bizDate, aheadCorpus);
          }else{
            throw new BusinessException("不允许提前还款！");
          }
        }else{
          throw new BusinessException("不允许提前还款！");
        }
      }else{
        throw new BusinessException("不允许提前还款！");
      }
    }else{//允许再判断
//    取出该贷款号在合同贷款参数PL004表中参数序号=5的参数说明，判断该贷款账在贷款流水账头表PL202中(会计年内1-12)提前还款的次数是否小于该值
      paramExplain = loanContractParaDAO.queryParamExplain_LJ(loanBankId, paramType, "5",contractId);
      //会计年内提前还款次数
      int aheadCounts=loanFlowTailDAO.queryCallbackAheadCounts_LJ(contractId, bizDate).intValue();
      if(aheadCounts<Integer.parseInt(paramExplain)){
        //取出该贷款号在合同贷款参数PL004表中参数序号=6的参数说明，判断该贷款账在贷款流水账头表PL202中是提前还款的次数是否小于该值
        paramExplain = loanContractParaDAO.queryParamExplain_LJ(loanBankId, paramType, "6",contractId);
        //贷款期限内提前次数
        int lineCounts=loanFlowTailDAO.queryCallbackAheadCounts_LJ(contractId, null).intValue();;
        if(lineCounts<Integer.parseInt(paramExplain)){
          //允许
          af = findPartAheadInfo(borrowerInfoDTO, af1, bizDate, aheadCorpus);
        }else{
          throw new BusinessException("不允许提前还款！");
        }
      }else{
        throw new BusinessException("不允许提前还款！");
      }
    }
    if(aheadMoney == null){
      af.setDeadLine("0");
    }
    return af;
  }
  /**
   * 部份提前还款信息
   * @param borrowerInfoDTO
   * @param af1
   * @param bizDate
   * @param aheadMoney
   * @return
   * @throws Exception
   */
  public LoancallbackTaAF findPartAheadInfo(BorrowerInfoDTO borrowerInfoDTO,LoancallbackTaAF af1,String bizDate,BigDecimal aheadMoney)throws Exception{
    LoancallbackTaAF af = new LoancallbackTaAF();
    ShouldBackListDTO shouldBackListDTO = new ShouldBackListDTO();
    String paramExplain = "";//参数说明
    String paramType = "B";//参数类型
    String paramValue = "";//参数值
    String loanRepayDay = borrowerInfoDTO.getLoanRepayDay();// 还款日
    String days = "";//占用天数 
    Integer loanBankId = borrowerInfoDTO.getLoanBankId();//放款银行
    BigDecimal overplusLoanMoney = new BigDecimal(0.00);//剩余本金
    overplusLoanMoney= borrowerInfoDTO.getOverplusLoanMoney();
    BigDecimal aheadCorpus = new BigDecimal(0.00);//提前还款本金
    BigDecimal aheadInterest = new BigDecimal(0.00);//提前还款利息
    BigDecimal loanRate = new BigDecimal(0.00);//月利率
    BigDecimal loanPoundageMoney = new BigDecimal(0.00);//手续费金额
    BigDecimal corpusInterest = new BigDecimal(0.00);//提前还款后月还本息
    String deadLine = "";//提前还款后剩余期限
    BigDecimal sumCorpus = new BigDecimal(0.00);// 总还款本金
    BigDecimal sumInterest = new BigDecimal(0.00);// 本次总还款利息
    BigDecimal sumMoney = new BigDecimal(0.00);//总还金额
    BigDecimal ovaerLoanRepay = new BigDecimal(0.00);// 挂账余额
    BigDecimal realMoney = new BigDecimal(0.00);//实收金额
    List tempList = new ArrayList();
    String contractId = borrowerInfoDTO.getContractId();//合同编号
    //提前还款最低还款金额为
    paramExplain = loanContractParaDAO.queryParamExplain_LJ(loanBankId, paramType, "4",contractId);
    BigDecimal minMoney = new BigDecimal(paramExplain);
    String yearMonth = bizDate.substring(0, 6);// 取出会计日期中的年月
    String year = yearMonth.substring(0, 4);
    String month = yearMonth.substring(4, 6);
    String day = bizDate.substring(6, bizDate.length());// 取出会计日期中的日
    String temp_bizDate = year + "-"
    + month + "-" + day;// 用来判断列表中的还款类型所转换的会计日期
    String temp_date = "";
    String callbackMonth=yearMonth;//还至年月
    String isAmendLine = "0";//是否可以修改剩余期限（0.不可以；1.可以）
    //占用天数
    if(Integer.parseInt(day)<Integer.parseInt(loanRepayDay)){
      if(Integer.parseInt(month)==1){
        month = "12";
        year=Integer.parseInt(year)-1+"";
      }else{
        month = Integer.parseInt(month)-1+"";
      }
      temp_date = year + "-" + month + "-" + loanRepayDay;
      days = BusiTools.minusDate(temp_bizDate, temp_date)+"";
    }else{
      temp_date = year + "-"+ month + "-" + loanRepayDay;
      days = BusiTools.minusDate(temp_bizDate, temp_date)+"";      
    }
    aheadCorpus = overplusLoanMoney.subtract(af1.getSumCorpus());
    //手续费金额分三种情况（判断该贷款号在合同贷款参数PL004表中参数序号=7的参数值）
    paramValue = loanContractParaDAO.queryParamValue_LJ(loanBankId, paramType, "7",contractId);
    //参数值为1:手续费=提前还款本金*参数说明/100
    if(paramValue.equals(BusiConst.PLPREPAYMENTFEES_PREPAYMENT+"")){
      paramExplain = loanContractParaDAO.queryParamExplain_LJ(loanBankId, paramType, "7",contractId);
      loanPoundageMoney = aheadCorpus.multiply(new BigDecimal(paramExplain)).divide(new BigDecimal(100),2,BigDecimal.ROUND_HALF_UP);
    }else if(paramValue.equals(BusiConst.PLPREPAYMENTFEES_PAYMENT+"")){
      paramExplain = loanContractParaDAO.queryParamExplain_LJ(loanBankId, paramType, "7",contractId);
      loanPoundageMoney = new BigDecimal(paramExplain);
    }else{
      loanPoundageMoney = new BigDecimal(0.00);
    }
    yearMonth = BusiTools.addMonth(yearMonth, 1);
    tempList = restoreLoanDAO.queryRestoreLoanInfoByContractId_LJ(contractId, yearMonth);
    if(!tempList.isEmpty()){
       shouldBackListDTO = (ShouldBackListDTO)tempList.get(0);
    }else{
      tempList = restoreLoanDAO.queryRestoreLoanInfoByContractId_LJ(contractId, callbackMonth);
      if(!tempList.isEmpty()){
        shouldBackListDTO = (ShouldBackListDTO)tempList.get(0);
     }
    }
    loanRate = shouldBackListDTO.getLoanRate();//还款表每月利率    
    aheadInterest = aheadCorpus.multiply(new BigDecimal(days)).multiply(loanRate).divide(new BigDecimal(30),2,BigDecimal.ROUND_HALF_UP);
    paramValue = loanContractParaDAO.queryParamValue_LJ(loanBankId, paramType, "1",contractId);
    if(paramValue.equals(BusiConst.PLRECOVERPARASCHG_SAMEPAY+"")){//参数值为1
      double dOverplusLoanMoney=overplusLoanMoney.subtract(af1.getSumCorpus().add(af1.getSumInterest())).subtract(aheadMoney).doubleValue();
      double dMonthIngerest=shouldBackListDTO.getShouldCorpus().add(shouldBackListDTO.getShouldInterest()).doubleValue();
      double dLine=Math.abs(Math.log((dMonthIngerest-dOverplusLoanMoney*loanRate.doubleValue())/dMonthIngerest)/Math.log(1+loanRate.doubleValue()));
      deadLine = String.valueOf(dLine);
      if(deadLine.indexOf('.',0)!=-1){
        int i=deadLine.lastIndexOf(".");
        deadLine = deadLine.substring(0,i);
      }
    }else if(paramValue.equals(BusiConst.PLRECOVERPARASCHG_SAMEMONTHS+"")){//参数值为2
      //提前还款后剩余期限=贷款期限-（会计年月和借款人账户表PL111中发放年月之间的月数差）
      deadLine = Integer.parseInt(borrowerInfoDTO.getLoanTimeLimit())-(BusiTools.monthInterval(bizDate, borrowerInfoDTO.getLoanStartDate()))+"";      
    }else if(paramValue.equals(BusiConst.PLRECOVERPARASCHG_CHGMONTHS+"")){//参数值为3
      //默认情况下提前还款后剩余期限=贷款期限-（会计年月和借款人账户表PL111中发放年月之间的月数差），可以修改该贷款期限。
      deadLine = Integer.parseInt(borrowerInfoDTO.getLoanTimeLimit())-(BusiTools.monthInterval(bizDate, borrowerInfoDTO.getLoanStartDate()))+""; 
      af.setLine(paramValue);
      isAmendLine = "1";
    }
    //提前还款后月还本息的值=（剩余本金-应还本金合-提前还款金额）*（1+月利率）新剩余期限*月利率/(1+月利率)新剩余期限-1
    overplusLoanMoney=overplusLoanMoney.subtract(af1.getSumCorpus()).subtract(aheadMoney);
    corpusInterest = CorpusinterestBS.getCorpusInterest(overplusLoanMoney, loanRate, deadLine);
    //本次还款总本金=应还本金合计+提前还款金额
    sumCorpus = af1.getSumCorpus().add(aheadMoney);
    //本次还款总利息=提前还款利息+应还利息合计+罚息合计
    sumInterest = aheadInterest.add(af1.getSumInterest());
    //本次还款总金额=本次总还款本金+本次总还款利息、
    sumMoney = sumCorpus.add(sumInterest);
    //挂账余额=借款人账户表PL111中挂账余额
    ovaerLoanRepay = borrowerInfoDTO.getOvaerLoanRepay();
    //本次实收金额:A当挂账余额大于等于本次总还款金额：本次实收金额=0 否则B本次实收金额=本次总还款金额-挂账余额
    if(ovaerLoanRepay.equals(sumMoney)){
      realMoney = new BigDecimal(0.00);
      af.setOverOccurMoney(af.getSumMoney());
    }else{
      realMoney = sumMoney.subtract(ovaerLoanRepay);
      af.setOverOccurMoney(ovaerLoanRepay);
    }
    af.setAheadCorpus(aheadCorpus);//提前还款本金
    af.setMinMoney(minMoney);//提前还款最低还款金额 为了判断提前还款的本金的修改范围在大于此值并小于默认的提前还款本金
    af.setAheadInterest(aheadInterest);
    af.setDays(days);
    af.setLoanPoundageMoney(loanPoundageMoney);
    af.setDeadLine(deadLine);
    af.setCorpusInterest(corpusInterest);
    af.setSumCorpus(sumCorpus);
    af.setSumInterest(sumInterest);
    af.setSumMoney(sumMoney);
    af.setOvaerLoanRepay(ovaerLoanRepay);
    af.setRealMoney(realMoney);
    af.setIsAmendLine(isAmendLine);
    return af;
  }
  

  /**
   * 判断是否允许清还
   * @param borrowerInfoDTO
   * @param bizDate
   * @param af1
   * @return
   * @throws Exception
   */
  //一次性清还
  public LoancallbackTaAF fullAheadInfo(BorrowerInfoDTO borrowerInfoDTO,String bizDate,LoancallbackTaAF af1) throws Exception{
    LoancallbackTaAF af = new LoancallbackTaAF();
    String paramType = "B";//参数类型
    String paramValue = "";//参数值
    String paramExplain = "";//参数说明
    String loanStartDate = borrowerInfoDTO.getLoanStartDate();//发放日期
    Integer loanBankId = borrowerInfoDTO.getLoanBankId();//放款银行
    String contractId = borrowerInfoDTO.getContractId();//合同编号
    //判断该贷款号在合同贷款参数PL004表中参数序号=3的参数值是否=1（不允许一次性清还）
    paramValue = loanContractParaDAO.queryParamValue_LJ(loanBankId, paramType, "3",contractId);
    if(paramValue.equals(BusiConst.PLCOMMONSTATUS_2_NOTALLOW+"")){
      //取出该参数对应的参数说明PARAM_EXPLAIN，判断（会计日期-PL111表中发放日期LOAN_START_DATE）的月数是否大于等于该值
      paramExplain = loanContractParaDAO.queryParamExplain_LJ(loanBankId, paramType, "3",contractId);
      int temp_monthCounts=BusiTools.monthInterval(loanStartDate, bizDate);
      if(temp_monthCounts>=Integer.parseInt(paramExplain)){
        //取出该贷款号在合同贷款参数PL004表中参数序号=5的参数说明，判断该贷款账在贷款流水账头表PL202中(会计年内1-12)提前还款的次数是否小于该值
        paramExplain = loanContractParaDAO.queryParamExplain_LJ(loanBankId, paramType, "5",contractId);
        //会计年内提前还款次数
        int aheadCounts=loanFlowTailDAO.queryCallbackAheadCounts_LJ(contractId, bizDate).intValue();
        if(aheadCounts<Integer.parseInt(paramExplain)){
          //取出该贷款号在合同贷款参数PL004表中参数序号=6的参数说明，判断该贷款账在贷款流水账头表PL202中是提前还款的次数是否小于该值
          paramExplain = loanContractParaDAO.queryParamExplain_LJ(loanBankId, paramType, "6",contractId);
//        贷款期限内提前次数
          int lineCounts=loanFlowTailDAO.queryCallbackAheadCounts_LJ(contractId, null).intValue();;
          if(lineCounts<Integer.parseInt(paramExplain)){
            af = findFullAheadInfo(borrowerInfoDTO, bizDate, af1);
          }else{
            throw new BusinessException("不允许一次性结清！");
          }
        }else{
          throw new BusinessException("不允许一次性结清！");
        }
      }else{
        throw new BusinessException("不允许一次性结清！");
      }
    }else{//允许再判断
//    取出该贷款号在合同贷款参数PL004表中参数序号=5的参数说明，判断该贷款账在贷款流水账头表PL202中(会计年内1-12)提前还款的次数是否小于该值
      paramExplain = loanContractParaDAO.queryParamExplain_LJ(loanBankId, paramType, "5",contractId);
      //会计年内提前还款次数
      int aheadCounts=loanFlowTailDAO.queryCallbackAheadCounts_LJ(contractId, bizDate).intValue();
      if(aheadCounts<Integer.parseInt(paramExplain)){
        //取出该贷款号在合同贷款参数PL004表中参数序号=6的参数说明，判断该贷款账在贷款流水账头表PL202中是提前还款的次数是否小于该值
        paramExplain = loanContractParaDAO.queryParamExplain_LJ(loanBankId, paramType, "6",contractId);
//      贷款期限内提前次数
        int lineCounts=loanFlowTailDAO.queryCallbackAheadCounts_LJ(contractId, null).intValue();;
        if(lineCounts<Integer.parseInt(paramExplain)){
          af = findFullAheadInfo(borrowerInfoDTO, bizDate, af1);
        }else{
          throw new BusinessException("不允许一次性结清！");
        }
      }else{
        throw new BusinessException("不允许一次性结清！");
      }
    }
    return af;
  }

  /**
   * 一次性清还应还信息
   * @param borrowerInfoDTO
   * @param bizDate
   * @param af1
   * @return
   * @throws Exception
   */
  public LoancallbackTaAF findFullAheadInfo(BorrowerInfoDTO borrowerInfoDTO,String bizDate,LoancallbackTaAF af1) throws Exception{
    LoancallbackTaAF af = new LoancallbackTaAF();
    ShouldBackListDTO shouldBackListDTO = new ShouldBackListDTO();
    String paramExplain = "";//参数说明
    String paramType = "B";//参数类型
    String paramValue = "";//参数值
    Integer loanBankId = borrowerInfoDTO.getLoanBankId();//放款银行
    BigDecimal aheadCorpus = new BigDecimal(0.00);//提前还款本金
    BigDecimal overplusLoanMoney = borrowerInfoDTO.getOverplusLoanMoney();//剩余本金
    String loanRepayDay = borrowerInfoDTO.getLoanRepayDay();// 还款日
    BigDecimal aheadInterest = new BigDecimal(0.00);//提前还款利息
    BigDecimal loanPoundageMoney = new BigDecimal(0.00);//手续费金额
    BigDecimal sumCorpus = new BigDecimal(0.00);// 总还款本金
    BigDecimal sumInterest = new BigDecimal(0.00);// 本次总还款利息
    BigDecimal sumMoney = new BigDecimal(0.00);//总还金额
    BigDecimal ovaerLoanRepay = new BigDecimal(0.00);// 挂账余额
    BigDecimal realMoney = new BigDecimal(0.00);//实收金额
    String days = "";//占用天数 
    String contractId = borrowerInfoDTO.getContractId();//合同编号
    BigDecimal monthInterest = new BigDecimal(0.00);//月利率
    List tempList = null;
    //提前还款本金默认情况下=剩余本金-应还本金合计（不可以修改）
    aheadCorpus = overplusLoanMoney.subtract(af1.getSumCorpus());
    String yearMonth = bizDate.substring(0, 6);// 取出会计日期中的年月
    String year = yearMonth.substring(0, 4);
    String month = yearMonth.substring(4, 6);
    String day = bizDate.substring(6, bizDate.length());// 取出会计日期中的日
    String temp_bizDate = year + "-"
    + month + "-" + day;// 用来判断列表中的还款类型所转换的会计日期
    String temp_date = "";
    String callbackMonth=yearMonth;//还至年月
    //  占用天数
    if(Integer.parseInt(day)<Integer.parseInt(loanRepayDay)){
      if(Integer.parseInt(month)==1){
        month = "12";
        year=Integer.parseInt(year)-1+"";
      }else{
        month = Integer.parseInt(month)-1+"";
      }
      temp_date = year + "-" + month + "-" + loanRepayDay;
      days = BusiTools.minusDate(temp_bizDate, temp_date)+"";
    }else{
      temp_date = year + "-"+ month + "-" + loanRepayDay;
      days = BusiTools.minusDate(temp_bizDate, temp_date)+"";      
    }
    yearMonth = BusiTools.addMonth(yearMonth, 1);
    tempList = restoreLoanDAO.queryRestoreLoanInfoByContractId_LJ(contractId, yearMonth);
    if(!tempList.isEmpty()){
       shouldBackListDTO = (ShouldBackListDTO)tempList.get(0);
    }else{
      tempList = restoreLoanDAO.queryRestoreLoanInfoByContractId_LJ(contractId, callbackMonth);
      if(!tempList.isEmpty()){
        shouldBackListDTO = (ShouldBackListDTO)tempList.get(0);
     }
    }
    monthInterest = shouldBackListDTO.getLoanRate();//还款表每月利率
    //提前还款利息=提前还款本金*占用天数*月利率/30
    aheadInterest = aheadCorpus.multiply(new BigDecimal(days)).multiply(monthInterest).divide(new BigDecimal(30),2,BigDecimal.ROUND_HALF_UP);
    //手续费金额分三种情况（判断该贷款号在合同贷款参数PL004表中参数序号=6的参数值）
    paramValue = loanContractParaDAO.queryParamValue_LJ(loanBankId, paramType, "7",contractId);
    //  参数值为1:手续费=提前还款本金*参数说明/100
    if(paramValue.equals(BusiConst.PLPREPAYMENTFEES_PREPAYMENT+"")){
      paramExplain = loanContractParaDAO.queryParamExplain_LJ(loanBankId, paramType, "7",contractId);
      loanPoundageMoney = aheadCorpus.multiply(new BigDecimal(paramExplain)).divide(new BigDecimal(100),2,BigDecimal.ROUND_HALF_UP);
    }else if(paramValue.equals(BusiConst.PLPREPAYMENTFEES_PAYMENT+"")){//参数值为2:手续费=参数说明
      paramExplain = loanContractParaDAO.queryParamExplain_LJ(loanBankId, paramType, "7",contractId);
      loanPoundageMoney = new BigDecimal(paramExplain);
    }else{//参数值为3:手续费=0
      loanPoundageMoney = new BigDecimal(0.00);
    }
    //本次还款总本金=应还本金合计+提前还款金额
    sumCorpus = af1.getSumCorpus().add(aheadCorpus);
    //本次还款总利息=提前还款利息+应还利息合计+罚息合计
    sumInterest = af1.getSumInterest().add(aheadInterest);
    //本次还款总金额=本次总还款本金+本次总还款利息、
    sumMoney = sumCorpus.add(sumInterest);
    //挂账余额=借款人账户表PL111中挂账余额
    ovaerLoanRepay = borrowerInfoDTO.getOvaerLoanRepay();
    //本次实收金额:A当挂账余额大于等于本次总还款金额：本次实收金额=0挂账发生额=本次总还款金额
    if(ovaerLoanRepay.doubleValue()>=sumMoney.doubleValue()){
      realMoney = new BigDecimal(0.00);
      af.setOverOccurMoney(af.getSumMoney());
    }else{//否则B本次实收金额=本次总还款金额-挂账余额
      realMoney = sumMoney.subtract(ovaerLoanRepay);
      af.setOverOccurMoney(ovaerLoanRepay);
    }
    af.setAheadCorpus(aheadCorpus);
    af.setDays(days);
    af.setAheadInterest(aheadInterest);
    af.setLoanPoundageMoney(loanPoundageMoney);
    af.setSumCorpus(sumCorpus);
    af.setSumInterest(sumInterest);
    af.setSumMoney(sumMoney);
    af.setOvaerLoanRepay(ovaerLoanRepay);
    af.setRealMoney(realMoney);
    return af;
  }
  /**
   * 根据页面输入的剩余期限计算月还本息
   * @param pagination
   * @param securityInfo
   * @return
   */
  public BigDecimal getCorpusInterest(Pagination pagination, SecurityInfo securityInfo)throws Exception{
    BigDecimal corpusInterest = new BigDecimal(0.00);
    String bizDate = securityInfo.getUserInfo().getBizDate();
    String yearMonth = bizDate.substring(0, 6);// 取出会计日期中的年月
    String callbackMonth=yearMonth;//还至年月
    yearMonth = BusiTools.addMonth(yearMonth, 1);
    List tempList = null;
    ShouldBackListDTO shouldBackListDTO = null;
    List borrowerList = null;
    BorrowerInfoDTO borrowerInfoDTO = null;
    String contractId = (String) pagination.getQueryCriterions().get("contractId");
    String deadLine = (String) pagination.getQueryCriterions().get("deadLine");
    String cIMoney = (String) pagination.getQueryCriterions().get("cIMoney");
    String aheadMoney = (String) pagination.getQueryCriterions().get("aheadMoney");
    tempList = restoreLoanDAO.queryRestoreLoanInfoByContractId_LJ(contractId, yearMonth);
    if(!tempList.isEmpty()){
       shouldBackListDTO = (ShouldBackListDTO)tempList.get(0);
    }else{
      tempList = restoreLoanDAO.queryRestoreLoanInfoByContractId_LJ(contractId, callbackMonth);
      if(!tempList.isEmpty()){
        shouldBackListDTO = (ShouldBackListDTO)tempList.get(0);
     }
    }
    //  从PL110及PL111取信息
    borrowerList = borrowerAccDAO
        .queryBorrowerAccInfoByLoanKouAcc_LJ(contractId);
    if (!borrowerList.isEmpty()) {
      borrowerInfoDTO = (BorrowerInfoDTO) borrowerList.get(0);
    }
    BigDecimal overplusLoanMoney = new BigDecimal(0.00);//剩余本金
    overplusLoanMoney= borrowerInfoDTO.getOverplusLoanMoney();
    BigDecimal loanRate = shouldBackListDTO.getLoanRate();//还款表每月利率
    overplusLoanMoney = overplusLoanMoney.subtract(new BigDecimal(cIMoney)).subtract(new BigDecimal(aheadMoney));
    //提前还款后月还本息的值=（剩余本金-应还本金合-提前还款金额）*（1+月利率）新剩余期限*月利率/(1+月利率)新剩余期限-1
    corpusInterest = CorpusinterestBS.getCorpusInterest(overplusLoanMoney, loanRate, deadLine);
    return corpusInterest;
  }
  
  /**
   * 应还信息列表
   * @param shouldBackList
   * @param borrowerInfoDTO
   * @param bizDate
   * @return
   * @throws Exception
   */
  public LoancallbackTaAF findCallbackList(List shouldBackList,BorrowerInfoDTO borrowerInfoDTO,String bizDate)throws Exception{
    LoancallbackTaAF af = new LoancallbackTaAF();
    List temp_list = new ArrayList();
    BigDecimal sumCorpus = new BigDecimal(0.00);// 总还款本金
    BigDecimal sumInterest = new BigDecimal(0.00);// 本次总还款利息
    Integer loanBankId = borrowerInfoDTO.getLoanBankId();// 放款银行
    String isRate = "";// 是否记息
    String accountDate = "";// 记账日期
    String loanRepayDay = "";// 还款日 在取应还款信息时用到
    String paramType = "A";// 参数类型
    String interestMode = "";// 计算罚息方式
    String paramExplain = "";//参数说明
    String allowdays = "";// 宽限天数
    BigDecimal temp_interest = new BigDecimal(0.00);// 临时罚息
    String yearMonth = bizDate.substring(0, 6);// 取出会计日期中的年月
    String day = bizDate.substring(6, bizDate.length());// 取出会计日期中的日
    String temp_bizDate = yearMonth.substring(0, 4) + "-"
        + yearMonth.substring(4, 6) + "-" + day;// 用来判断列表中的还款类型所转换的会计日期
    loanRepayDay = borrowerInfoDTO.getLoanRepayDay();
    // 从PL003中查询宽限天数内是否计息
    isRate = loanBankParaDAO.queryParamValue_LJ(loanBankId, paramType, "5");
    // 从PL003中取计算罚息方式（按按罚息日利率、按合同日利率、按额每日XX元计算）
    interestMode = loanBankParaDAO.queryParamValue_LJ(loanBankId, paramType,
        "4");
    // 从PL003中取对应的参数说明
    paramExplain = loanBankParaDAO.queryParamExplain_LJ(loanBankId,
        paramType, "4");
    // PL003中查询宽限天数
    allowdays = loanBankParaDAO.queryParamExplain_LJ(loanBankId, paramType,
        "5");
    if (!shouldBackList.isEmpty()) {
      for (int i = 0; i < shouldBackList.size(); i++) {
        ShouldBackListDTO dto1 = (ShouldBackListDTO) shouldBackList.get(i);
        ShouldBackListDTO dto2 = new ShouldBackListDTO();
        dto2.setLoanKouYearmonth(dto1.getLoanKouYearmonth());// 显示还款年月
        String temp_date = dto1.getLoanKouYearmonth().substring(0, 4) + "-"
            + dto1.getLoanKouYearmonth().substring(4, 6) + "-" + loanRepayDay;
        // 逾期天数
        int days = BusiTools.minusDate(temp_bizDate, temp_date);
        if (days > 0) {
          dto2.setLoanKouType("逾期");// 显示还款类型
        } else {
          dto2.setLoanKouType("正常");
        }
        dto2.setShouldCorpus(dto1.getShouldCorpus().subtract(
            dto1.getRealCorpus()));// 显示应还本金
        dto2.setShouldInterest(dto1.getShouldInterest().subtract(
            dto1.getRealInterest()));// 显示应还利息
        dto2.setDays(days + "");// 显示逾期天数
        // 罚息卡住了。
        // 足条计算每月产生的罚息
        // 判断还款表(应还本金-本金+应还利息-利息)是否=0
        if (dto1.getShouldCorpus().subtract(dto1.getRealCorpus()).add(
            dto1.getShouldInterest().subtract(dto1.getRealInterest()))
            .doubleValue() == 0) {
          dto2.setPunishInterest(dto1.getPunishInterest());
        } else {
          // 不等于0判断是否在宽限天数内计息
          // 条件银行贷款参数PL003中类型为：A:还款参数，并且参数序号PARAM_NUM=5的参数值PARAM_VALUE是否=0(宽限天数内计息)
          if (isRate.equals(BusiConst.YES + "")) {// 计息
            // 逐条判断PL201中的记账日期是否小于等于还款日
            accountDate = dto1.getBizDate();
            String temp_day = accountDate.substring(6, 8);// 记账日期的日
            if (Integer.parseInt(temp_day) <= Integer.parseInt(loanRepayDay)) {// 小于等于还款日
              if (interestMode
                  .equals(BusiConst.PLPUNISHINTERESTTYPE_PUNISHDAYRATE + "")) {
                temp_interest = PunishInterestBS.getTempInterestA(bizDate,
                    dto1.getLoanKouYearmonth(), loanRepayDay, dto1
                        .getShouldCorpus(), dto1.getRealCorpus(), dto1
                        .getShouldInterest(), dto1.getRealInterest(),
                    paramExplain);
              } else if (interestMode
                  .equals(BusiConst.PLPUNISHINTERESTTYPE_CONTRACTDAYRATE + "")) {
                temp_interest = PunishInterestBS.getTempInterestB(bizDate,
                    dto1.getLoanKouYearmonth(), loanRepayDay, dto1
                        .getShouldCorpus(), dto1.getRealCorpus(), dto1
                        .getShouldInterest(), dto1.getRealInterest(),
                    paramExplain, dto1.getLoanRate());
              } else if (interestMode
                  .equals(BusiConst.PLPUNISHINTERESTTYPE_PAYMENTDAYRATE + "")) {
                temp_interest = PunishInterestBS.getTempInterestC(bizDate,
                    dto1.getLoanKouYearmonth(), loanRepayDay, paramExplain);
              }
            } else {// 大于还款日
              if (interestMode
                  .equals(BusiConst.PLPUNISHINTERESTTYPE_PUNISHDAYRATE + "")) {
                temp_interest = PunishInterestBS.getTempInterestA(bizDate,
                    dto1.getBizDate(), loanRepayDay, dto1.getShouldCorpus(),
                    dto1.getRealCorpus(), dto1.getShouldInterest(), dto1
                        .getRealInterest(), paramExplain);
              } else if (interestMode
                  .equals(BusiConst.PLPUNISHINTERESTTYPE_CONTRACTDAYRATE + "")) {
                temp_interest = PunishInterestBS.getTempInterestB(bizDate,
                    dto1.getBizDate(), loanRepayDay, dto1.getShouldCorpus(),
                    dto1.getRealCorpus(), dto1.getShouldInterest(), dto1
                        .getRealInterest(), paramExplain, dto1.getLoanRate());
              } else if (interestMode
                  .equals(BusiConst.PLPUNISHINTERESTTYPE_PAYMENTDAYRATE + "")) {
                temp_interest = PunishInterestBS.getTempInterestC(bizDate,
                    dto1.getBizDate(), loanRepayDay, paramExplain);
              }
              temp_interest = temp_interest.add(dto1.getPunishInterest())
                  .divide(new BigDecimal(1), 2, BigDecimal.ROUND_HALF_UP);// 加还款表中未还罚息
            }
            // 先查询该贷款账号的所属银行在银行贷款参数PL003中类型为：A:还款参数，并且参数序号PARAM_NUM=5的参数说明PARAM_EXPLAIN的值（宽限天数）；判断（会计日期分别减去每个月的还款日）是否<=该值（宽限天数）
            // 判断宽限天数
            if (days <= Integer.parseInt(allowdays)) {// 逾期天数小于等于宽限天数
              dto2.setPunishInterest(new BigDecimal(0.00));
              dto2.setTempInterest(temp_interest);
            } else {
              dto2.setPunishInterest(temp_interest);
              dto2.setTempInterest(temp_interest);
            }
          } else {// 不计息
            // 先查询该贷款账号的所属银行在银行贷款参数PL003中类型为：A:还款参数，并且参数序号PARAM_NUM=5的参数说明PARAM_EXPLAIN的值（宽限天数）；判断（会计日期分别减去每个月的还款日）是否<=该值（宽限天数）
            // 判断宽限天数
            if (days <= Integer.parseInt(allowdays)) {// 逾期天数小于等于宽限天数
              dto2.setPunishInterest(new BigDecimal(0.00));
              dto2.setTempInterest(new BigDecimal(0.00));
            } else {// 已经逾期
              // 逐条判断PL201中的记账日期是否小于等于还款日+宽限天数
              String temp_day = dto1.getBizDate().substring(6, 8);// 记账日期的日
              // 还款年月日+宽限天数后生成的年月日
              String temp_loanRepayDay = "";
              temp_loanRepayDay = BusiTools.addDay(dto1.getLoanKouYearmonth()
                  + loanRepayDay, Integer.parseInt(allowdays));
              temp_loanRepayDay = temp_loanRepayDay.substring(6, 8);
              if (Integer.parseInt(temp_day) <= Integer
                  .parseInt(temp_loanRepayDay)) {// 小于等于还款日+宽限天数
                if (interestMode
                    .equals(BusiConst.PLPUNISHINTERESTTYPE_PUNISHDAYRATE + "")) {
                  temp_interest = PunishInterestBS.getTempInterestD(bizDate,
                      dto1.getLoanKouYearmonth(), loanRepayDay, dto1
                          .getShouldCorpus(), dto1.getRealCorpus(), dto1
                          .getShouldInterest(), dto1.getRealInterest(),
                      paramExplain, allowdays);
                } else if (interestMode
                    .equals(BusiConst.PLPUNISHINTERESTTYPE_CONTRACTDAYRATE
                        + "")) {
                  temp_interest = PunishInterestBS.getTempInterestE(bizDate,
                      dto1.getLoanKouYearmonth(), loanRepayDay, dto1
                          .getShouldCorpus(), dto1.getRealCorpus(), dto1
                          .getShouldInterest(), dto1.getRealInterest(),
                      paramExplain, dto1.getLoanRate(), allowdays);
                } else if (interestMode
                    .equals(BusiConst.PLPUNISHINTERESTTYPE_PAYMENTDAYRATE
                        + "")) {
                  temp_interest = PunishInterestBS.getTempInterestF(bizDate,
                      dto1.getLoanKouYearmonth(), loanRepayDay, paramExplain,
                      allowdays);
                }
              } else {// 大于还款日+宽限天数
                if (interestMode
                    .equals(BusiConst.PLPUNISHINTERESTTYPE_PUNISHDAYRATE + "")) {
                  temp_interest = PunishInterestBS.getTempInterestA(bizDate,
                      dto1.getBizDate(), loanRepayDay,
                      dto1.getShouldCorpus(), dto1.getRealCorpus(), dto1
                          .getShouldInterest(), dto1.getRealInterest(),
                      paramExplain);
                } else if (interestMode
                    .equals(BusiConst.PLPUNISHINTERESTTYPE_CONTRACTDAYRATE
                        + "")) {
                  temp_interest = PunishInterestBS.getTempInterestB(bizDate,
                      dto1.getBizDate(), loanRepayDay,
                      dto1.getShouldCorpus(), dto1.getRealCorpus(), dto1
                          .getShouldInterest(), dto1.getRealInterest(),
                      paramExplain, dto1.getLoanRate());
                } else if (interestMode
                    .equals(BusiConst.PLPUNISHINTERESTTYPE_PAYMENTDAYRATE
                        + "")) {
                  temp_interest = PunishInterestBS.getTempInterestC(bizDate,
                      dto1.getBizDate(), loanRepayDay, paramExplain);
                }
                // 加还款表中未还罚息
                temp_interest = temp_interest.add(dto1.getPunishInterest())
                    .divide(new BigDecimal(1), 2, BigDecimal.ROUND_HALF_UP);
              }
              dto2.setPunishInterest(temp_interest);
              dto2.setTempInterest(temp_interest);
            }
          }
        }
        dto2.setCiMoney(dto2.getShouldCorpus().add(dto2.getShouldInterest()));// 显示应还本息合计
        dto2.setLoanRate(dto1.getLoanRate());// 显示每月利率
        sumCorpus = sumCorpus.add(dto2.getShouldCorpus());
        sumInterest = sumInterest.add(dto2.getShouldInterest().add(
            dto2.getPunishInterest()));
        temp_list.add(dto2);
      }
    }
    af.setSumCorpus(sumCorpus);
    af.setSumInterest(sumInterest);
    af.setShouldBackList(temp_list);
    return af;
  }
}