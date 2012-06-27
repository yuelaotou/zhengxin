package org.xpup.hafmis.sysloan.loancallback.loancallback.business;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.xpup.common.exception.BusinessException;
import org.xpup.common.util.Pagination;
import org.xpup.hafmis.common.util.BusiConst;
import org.xpup.hafmis.common.util.BusiTools;
import org.xpup.hafmis.common.util.bizlog.BusiLogConst;
import org.xpup.hafmis.orgstrct.dao.CollBankDAO;
import org.xpup.hafmis.orgstrct.dao.SecurityDAO;
import org.xpup.hafmis.orgstrct.domain.CollBank;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.syscollection.common.dao.CollParaDAO;
import org.xpup.hafmis.syscollection.common.domain.entity.Emp;
import org.xpup.hafmis.sysloan.accounthandle.adjustaccount.action.T;
import org.xpup.hafmis.sysloan.common.arithmetic.corpusinterest.CorpusinterestBS;
import org.xpup.hafmis.sysloan.common.arithmetic.punishinterest.PunishInterestBS;
import org.xpup.hafmis.sysloan.common.dao.BorrowerAccDAO;
import org.xpup.hafmis.sysloan.common.dao.BorrowerDAO;
import org.xpup.hafmis.sysloan.common.dao.FundloanInfoDAO;
import org.xpup.hafmis.sysloan.common.dao.HousesDAO;
import org.xpup.hafmis.sysloan.common.dao.LoanBankDAO;
import org.xpup.hafmis.sysloan.common.dao.LoanBankParaDAO;
import org.xpup.hafmis.sysloan.common.dao.LoanContractParaDAO;
import org.xpup.hafmis.sysloan.common.dao.LoanFlowHeadDAO;
import org.xpup.hafmis.sysloan.common.dao.LoanFlowTailDAO;
import org.xpup.hafmis.sysloan.common.dao.LoanRateDAO;
import org.xpup.hafmis.sysloan.common.dao.PlBizActiveLogDAO;
import org.xpup.hafmis.sysloan.common.dao.PlDocNumCancelDAO;
import org.xpup.hafmis.sysloan.common.dao.PlDocNumMaintainDAO;
import org.xpup.hafmis.sysloan.common.dao.PlOperateLogDAO;
import org.xpup.hafmis.sysloan.common.dao.RestoreLoanDAO;
import org.xpup.hafmis.sysloan.common.domain.entity.Borrower;
import org.xpup.hafmis.sysloan.common.domain.entity.BorrowerAcc;
import org.xpup.hafmis.sysloan.common.domain.entity.Houses;
import org.xpup.hafmis.sysloan.common.domain.entity.LoanFlowHead;
import org.xpup.hafmis.sysloan.common.domain.entity.LoanFlowTail;
import org.xpup.hafmis.sysloan.common.domain.entity.PlBizActiveLog;
import org.xpup.hafmis.sysloan.common.domain.entity.PlOperateLog;
import org.xpup.hafmis.sysloan.common.domain.entity.RestoreLoan;
import org.xpup.hafmis.sysloan.dataready.rate.dto.RateDTO;
import org.xpup.hafmis.sysloan.loanaccord.printplan.dto.PrintplanListDTO;
import org.xpup.hafmis.sysloan.loancallback.loancallback.bsinterface.ILoancallbackBS;
import org.xpup.hafmis.sysloan.loancallback.loancallback.dto.BorrowerInfoDTO;
import org.xpup.hafmis.sysloan.loancallback.loancallback.dto.LoancallbackTaImportDTO;
import org.xpup.hafmis.sysloan.loancallback.loancallback.dto.LoancallbackTbDTO;
import org.xpup.hafmis.sysloan.loancallback.loancallback.dto.ShouldBackListDTO;
import org.xpup.hafmis.sysloan.loancallback.loancallback.form.LoancallbackTaAF;

public class LoancallbackBS implements ILoancallbackBS {

  private BorrowerAccDAO borrowerAccDAO = null;

  private CollParaDAO collParaDAO = null;

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

  private SecurityDAO securityDAO = null;

  private LoanRateDAO loanRateDAO = null;

  private FundloanInfoDAO fundloanInfoDAO = null;

  private BorrowerDAO borrowerDAO = null;

  private HousesDAO housesDAO = null;

  public void setLoanRateDAO(LoanRateDAO loanRateDAO) {
    this.loanRateDAO = loanRateDAO;
  }

  public void setSecurityDAO(SecurityDAO securityDAO) {
    this.securityDAO = securityDAO;
  }

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

  public boolean findContractSt(String contract_id, String contractSt) {
    String st = "";
    if(borrowerAccDAO.queryById(contract_id)!=null){
      st = borrowerAccDAO.queryById(contract_id).getContractSt();
    }
    if (st.equals(contractSt)) {
      return true;
    }
    return false;
  }

  /**
   * 判断还款日如果大于本月最后一天返回本月最后一天
   * 
   * @param yearMonth
   * @param loanRepayDay
   * @return
   */
  public String getEndDay(String yearMonth, String loanRepayDay) {
    String day = loanRepayDay;
    String year = yearMonth.substring(0, 4);
    String month = yearMonth.substring(4, 6);
    int days = BusiTools.daysOfMonth(Integer.parseInt(year), Integer
        .parseInt(month));
    if (Integer.parseInt(loanRepayDay) > days) {
      day = String.valueOf(days);
    }
    if (day.length() < 2 && Integer.parseInt(day) < 10) {
      day = "0" + day;
    }
    return day;
  }

  /**
   * 查询还至年月列表 会计年月到会计年12月
   * 
   * @param securityInfo
   * @return
   */
  public List getYearMonthList(String loanRepayDay, String contractId,
      SecurityInfo securityInfo) throws Exception {
    List list = new ArrayList();
    String bizDate = securityInfo.getUserInfo().getPlbizDate();
    String yearMonth = bizDate.substring(0, 6);
    List temp_list = restoreLoanDAO
        .countRestoreLoanListByContractId_sy(contractId);
    String loanDay = "";
    try {
      if (!temp_list.isEmpty()) {
        for (int i = 0; i < temp_list.size(); i++) {
          PrintplanListDTO dto = (PrintplanListDTO) temp_list.get(i);
          ShouldBackListDTO shouldBackListDTO = new ShouldBackListDTO();
          // if(Integer.parseInt(day)<Integer.parseInt(loanRepayDay)){
          // //会计日小于还款日，取未还的最小月的下个月--最大月的下一个月
          // if(dto.getLoanKouYearmonth().substring(4,6).equals("12")){
          // shouldBackListDTO.setLoanKouYearmonth(String.valueOf(Integer.parseInt(dto.getLoanKouYearmonth().substring(0,4))+1)+"01");
          // }else{
          // shouldBackListDTO.setLoanKouYearmonth(String.valueOf(Integer.parseInt(dto.getLoanKouYearmonth())+1));
          // }
          // }else{
          // 会计日大于等于还款日，取未还的最小月--最大月
          loanDay = this.getEndDay(dto.getLoanKouYearmonth(), loanRepayDay);
          if (i == 0) {
            if (Integer.parseInt(yearMonth) < Integer.parseInt(dto
                .getLoanKouYearmonth())) {
              shouldBackListDTO.setLoanKouYearmonth("");
            }
          }
          shouldBackListDTO.setLoanKouYearmonth(dto.getLoanKouYearmonth()
              + loanDay);
          // }
          list.add(shouldBackListDTO);
        }
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    return list;
  }

  public LoancallbackTaAF getLoancallbackTaAF(Pagination pagination,
      SecurityInfo securityInfo) throws Exception {
    LoancallbackTaAF af = new LoancallbackTaAF();
    String contractId = (String) pagination.getQueryCriterions().get(
        "contractId");
    BorrowerInfoDTO borrowerInfoDTO = new BorrowerInfoDTO();
    List borrowerList = new ArrayList();// 账户信息
    String bizDate = securityInfo.getUserInfo().getPlbizDate();
    List shouldBackList = new ArrayList();
    String yearMonth = bizDate.substring(0, 6);
    String day = bizDate.substring(6, bizDate.length());// 取出会计日期中的日
    // 从PL110及PL111取信息
    borrowerList = borrowerAccDAO
        .queryBorrowerAccInfoByLoanKouAcc_LJ(contractId);
    if (!borrowerList.isEmpty()) {
      borrowerInfoDTO = (BorrowerInfoDTO) borrowerList.get(0);
    }
    String loanRepayDay = borrowerInfoDTO.getLoanRepayDay();
    String loanRepayDay1 = this.getEndDay(yearMonth, loanRepayDay);
    // 如果没有选择还至日则需判断还款日和会计日的大小
    if (Integer.parseInt(day) < Integer.parseInt(loanRepayDay1)) {
      // 会计日小于还款日
      shouldBackList = restoreLoanDAO.queryRestoreLoanListByContractId_LJA(
          contractId, yearMonth);
    } else {
      // 会计日大于等于还款日
      shouldBackList = restoreLoanDAO.queryRestoreLoanListByContractId_LJB(
          contractId, yearMonth);
    }
    af = this.findCallbackList(shouldBackList, borrowerInfoDTO, bizDate);
    return af;
  }

  /**
   * 办理页面，根据贷款账号查询应还款信息
   */
  public LoancallbackTaAF findShouldLoancallbackInfo(Pagination pagination,
      SecurityInfo securityInfo) throws Exception {
    String identifier = (String) pagination.getQueryCriterions().get(
        "identifier");// 用来判断是否是来自回收咨询的调用,"consultation"
    String callbackMonth = (String) pagination.getQueryCriterions().get(
        "callbackMonth");
    LoancallbackTaAF af = new LoancallbackTaAF();
    BorrowerInfoDTO borrowerInfoDTO = new BorrowerInfoDTO();
    String bizDate = securityInfo.getUserInfo().getPlbizDate();// 会计日期
    List shouldBackList = null;// 应还信息
    List bizStList = null;// 用于查询是否存在未记账的状态
    BigDecimal ovaerLoanRepay = new BigDecimal(0.00);// 挂账余额
    String pldebit = "";// 扣款方式
    Integer loanBankId = null;// 放款银行
    List borrowerList = new ArrayList();// 账户信息
    String contractSt = BusiConst.PLCONTRACTSTATUS_RECOVING + "";// 合同状态 11.还款中
    String contractId = "";// 合同编号
    String loanRepayDay = "";// 还款日 在取应还款信息时用到
    String paramType = "A";// 参数类型
    int count = 0;// 应还款列表记录数
    // 合同账号
    contractId = (String) pagination.getQueryCriterions().get("contractId");
    // 业务类型
    String bizType = (String) pagination.getQueryCriterions().get("bizType");
    String aheadMoney = (String) pagination.getQueryCriterions().get(
        "aheadMoney");// 提前还款金额
    String yearMonth = bizDate.substring(0, 6);// 取出会计日期中的年月
    String temp_bizDate = "";
    String day = bizDate.substring(6, bizDate.length());// 取出会计日期中的日
    if (callbackMonth != null && !callbackMonth.equals("")) {
      String temp_yearMonth = callbackMonth.substring(0, 6);
      // if(!temp_yearMonth.equals(yearMonth)){
      yearMonth = callbackMonth.substring(0, 6);
      // day=callbackMonth.substring(6,8);
      // }
    }
    String paramValue = "";
    String isAmend = "1";// 是否可以更改实收金额(0.不可以；1.可以)
    af.setBizType("2");
    String bankDate = "";// 银行日期
    if (contractId != null && !contractId.equals("")) {
      // 判断贷款账号在PL111中是否存在并且状态为还款中。
      if (!findContractSt(contractId, contractSt)) {
        throw new BusinessException("此合同状态不对或不存在或不可以办理回收业务！");
      }
      pagination.getQueryCriterions().put("contractId", contractId);
      // 该贷款账号在贷款流水账头表PL202表中是否存在BIZ_ST!=6(未记账)（关联贷款流水账尾表PL203）
      if (identifier == null) {
        bizStList = loanFlowHeadDAO.queryBizStListByLoanKouAcc_LJ(contractId,
            null);
        if (!bizStList.isEmpty()) {
          throw new BusinessException("存在未记账的业务！");
        }
      }
      
      // 从PL110及PL111取信息
      borrowerList = borrowerAccDAO
          .queryBorrowerAccInfoByLoanKouAcc_LJ(contractId);
      if (!borrowerList.isEmpty()) {
        borrowerInfoDTO = (BorrowerInfoDTO) borrowerList.get(0);
      }
      pagination.getQueryCriterions().put("loanKouAcc",
          borrowerInfoDTO.getLoanKouAcc());
      borrowerInfoDTO.setCardKind(BusiTools.getBusiValue(Integer
          .parseInt(borrowerInfoDTO.getCardKind()), BusiConst.DOCUMENTSSTATE));
      borrowerInfoDTO.setLoanMode(BusiTools.getBusiValue(Integer
          .parseInt(borrowerInfoDTO.getLoanMode()), BusiConst.PLRECOVERTYPE));
      loanRepayDay = borrowerInfoDTO.getLoanRepayDay();
      ovaerLoanRepay = borrowerInfoDTO.getOvaerLoanRepay();
      loanBankId = borrowerInfoDTO.getLoanBankId();
      int iClearYear = Integer.parseInt(bizDate.substring(0, 4)) - 1;
      String clearYear = this.getClearYear(String.valueOf(loanBankId));
      // 会计年份-1如果不等于PL002中的年结年份则不允许回收
      Integer.parseInt(clearYear);
      if (identifier == null) {
        bankDate = loanBankDAO.queryPL002BizDate_jj(loanBankId.toString());
        if (!bizDate.equals(bankDate)) {
          throw new BusinessException("登录日期与银行业务日期不一致，不能做业务！");
        }
        if (iClearYear > Integer.parseInt(clearYear)) {
          throw new BusinessException(iClearYear + "年尚未年结，不允许进行回收业务！");
        }
      }
      String loanRepayDay1 = this.getEndDay(yearMonth, loanRepayDay);
      List yearMonthList = this.getYearMonthList(loanRepayDay, contractId,
          securityInfo);
      // 从PL003中查询扣款方式(全额扣款、足额扣款)
      pldebit = loanBankParaDAO.queryParamValue_LJ(loanBankId, paramType, "1");
      // 从PL201中查应还信息
      // if(identifier!=null){
      // //回收咨询查询所有应还信息
      // shouldBackList= restoreLoanDAO.queryRestoreLoanListByContractId_LJC(
      // contractId, yearMonth.substring(0,4));
      // }else
      if (callbackMonth == null) {
        // 如果没有选择还至日则需判断还款日和会计日的大小
        if (Integer.parseInt(day) < Integer.parseInt(loanRepayDay1)) {
          // 会计日小于还款日
          shouldBackList = restoreLoanDAO.queryRestoreLoanListByContractId_LJA(
              contractId, yearMonth);
        } else {
          // 会计日大于等于还款日
          shouldBackList = restoreLoanDAO.queryRestoreLoanListByContractId_LJB(
              contractId, yearMonth);
        }
      } else {
        // 会计日大于等于还款日
        shouldBackList = restoreLoanDAO.queryRestoreLoanListByContractId_LJB(
            contractId, yearMonth);
      }
      // 单笔回收2
      if (bizType == null) {
        bizType = BusiConst.PLBUSINESSTYPE_SINGLERECOVER + "";
      }
      // 得到算出罚息和逾期天数的list
      LoancallbackTaAF af1 = this.findCallbackList(shouldBackList,
          borrowerInfoDTO, bizDate);

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
        try{
          String fuzhuEmpName = borrowerDAO.countPeopleNum_EmpName(contractId);
          String fuzhuEmpCardNum = borrowerDAO.countPeopleNum_EmpCardNum(contractId);
          String fuzhuEmpsalary = borrowerDAO.countPeopleNum_EmpSalary(contractId);
          int fuzhusalaryBase=0;
          int borrowerSalaryBase=0;
          if(fuzhuEmpName!=null&&!"".equals(fuzhuEmpName)&&fuzhuEmpCardNum!=null&&!"".equals(fuzhuEmpCardNum)){
            fuzhusalaryBase=borrowerDAO.queryEmpSalary(fuzhuEmpName, fuzhuEmpCardNum);
          }
          if(fuzhusalaryBase<=0&&!"".equals(fuzhuEmpsalary)){
//            if(!"".equals(fuzhuEmpId)&&!"".equals(fuzhuOrgId)){
//              emp_fuzhu=borrowerDAO.queryByCriterions(fuzhuEmpId.toString(),
//                  fuzhuOrgId.toString());
//            }
            fuzhusalaryBase=Integer.parseInt(fuzhuEmpsalary);
          }
          Borrower borrower_wsh = new Borrower();
          borrower_wsh = borrowerDAO.queryById(contractId);
          if(borrower_wsh.getBorrowerName()!=null&&!"".equals(borrower_wsh.getBorrowerName())&&borrower_wsh.getCardNum()!=null&&!"".equals(borrower_wsh.getCardNum())){
            borrowerSalaryBase=borrowerDAO.queryEmpSalary(borrower_wsh.getBorrowerName(), borrower_wsh.getCardNum());
          }
          if(borrowerSalaryBase<=0){
            borrowerSalaryBase=borrower_wsh.getMonthSalary().intValue();
          }
          if(fuzhusalaryBase>0){
            af.setSumSalary(new BigDecimal(borrowerSalaryBase).add((new BigDecimal(fuzhusalaryBase))));
          }else{
            af.setSumSalary(new BigDecimal(borrowerSalaryBase));
          }
        }catch (Exception e) {
          e.printStackTrace();
        }
      } else if (bizType.equals(BusiConst.PLBUSINESSTYPE_PARTRECOVER + "")) {
        // 部分提前还款3
        temp_bizDate = securityInfo.getUserInfo().getPlbizDate()
            .substring(0, 6);
        // loanRepayDay = borrowerInfoDTO.getLoanRepayDay();
        String temp_loanRepayDay = this.getEndDay(temp_bizDate, loanRepayDay);
        if (Integer.parseInt(temp_bizDate) > Integer.parseInt(yearMonth)) {
          yearMonth = temp_bizDate;// 提前还款要判断应还的月份是否都已还清
          if (Integer.parseInt(day) < Integer.parseInt(temp_loanRepayDay)) {
            // 会计日小于还款日
            shouldBackList = restoreLoanDAO
                .queryRestoreLoanListByContractId_LJA(contractId, yearMonth);
          } else {
            // 会计日大于等于还款日
            shouldBackList = restoreLoanDAO
                .queryRestoreLoanListByContractId_LJB(contractId, yearMonth);
          }
        }
        af1 = this.findCallbackList(shouldBackList, borrowerInfoDTO, bizDate);
        BigDecimal overplusCorpus = new BigDecimal(0.00);// 剩余本金
        overplusCorpus = borrowerInfoDTO.getOverplusLoanMoney().subtract(
            af1.getSumCorpus());
        if (!shouldBackList.isEmpty()) {
          ShouldBackListDTO dto = (ShouldBackListDTO) shouldBackList
              .get(shouldBackList.size() - 1);
          yearMonth = dto.getLoanKouYearmonth();// 用来设置还至年月下拉框的显示值
        }
        af1.setMonthYearList(yearMonthList);
        af = this.partAheadInfo(bizDate, borrowerInfoDTO, af1, aheadMoney,
            callbackMonth);
        af.setOverplusCorpus(overplusCorpus);
        try{
          String fuzhuEmpName = borrowerDAO.countPeopleNum_EmpName(contractId);
          String fuzhuEmpCardNum = borrowerDAO.countPeopleNum_EmpCardNum(contractId);
          String fuzhuEmpsalary = borrowerDAO.countPeopleNum_EmpSalary(contractId);
          int fuzhusalaryBase=0;
          int borrowerSalaryBase=0;
          if(fuzhuEmpName!=null&&!"".equals(fuzhuEmpName)&&fuzhuEmpCardNum!=null&&!"".equals(fuzhuEmpCardNum)){
            fuzhusalaryBase=borrowerDAO.queryEmpSalary(fuzhuEmpName, fuzhuEmpCardNum);
          }
          if(fuzhusalaryBase<=0&&!"".equals(fuzhuEmpsalary)){
//            if(!"".equals(fuzhuEmpId)&&!"".equals(fuzhuOrgId)){
//              emp_fuzhu=borrowerDAO.queryByCriterions(fuzhuEmpId.toString(),
//                  fuzhuOrgId.toString());
//            }
            fuzhusalaryBase=Integer.parseInt(fuzhuEmpsalary);
          }
          Borrower borrower_wsh = new Borrower();
          borrower_wsh = borrowerDAO.queryById(contractId);
          if(borrower_wsh.getBorrowerName()!=null&&!"".equals(borrower_wsh.getBorrowerName())&&borrower_wsh.getCardNum()!=null&&!"".equals(borrower_wsh.getCardNum())){
            borrowerSalaryBase=borrowerDAO.queryEmpSalary(borrower_wsh.getBorrowerName(), borrower_wsh.getCardNum());
          }
          if(borrowerSalaryBase<=0){
            borrowerSalaryBase=borrower_wsh.getMonthSalary().intValue();
          }
          if(fuzhusalaryBase>0){
            af.setSumSalary(new BigDecimal(borrowerSalaryBase).add((new BigDecimal(fuzhusalaryBase))));
          }else{
            af.setSumSalary(new BigDecimal(borrowerSalaryBase));
          }
        }catch (Exception e) {
          e.printStackTrace();
        }
      } else if (bizType.equals(BusiConst.PLBUSINESSTYPE_ALLCLEAR + "")) {
        // 一次性还清4
        temp_bizDate = securityInfo.getUserInfo().getPlbizDate()
            .substring(0, 6);
        // loanRepayDay = borrowerInfoDTO.getLoanRepayDay();
        String temp_loanRepayDay = this.getEndDay(temp_bizDate, loanRepayDay);
        if (Integer.parseInt(temp_bizDate) > Integer.parseInt(yearMonth)) {
          yearMonth = temp_bizDate;
          if (Integer.parseInt(day) < Integer.parseInt(temp_loanRepayDay)) {
            // 会计日小于还款日
            shouldBackList = restoreLoanDAO
                .queryRestoreLoanListByContractId_LJA(contractId, yearMonth);
          } else {
            // 会计日大于等于还款日
            shouldBackList = restoreLoanDAO
                .queryRestoreLoanListByContractId_LJB(contractId, yearMonth);
          }
        }
        if (!shouldBackList.isEmpty()) {
          ShouldBackListDTO dto = (ShouldBackListDTO) shouldBackList
              .get(shouldBackList.size() - 1);
          yearMonth = dto.getLoanKouYearmonth();// 用来设置还至年月下拉框的显示值
        }
        af1 = this.findCallbackList(shouldBackList, borrowerInfoDTO, bizDate);
        af1.setMonthYearList(yearMonthList);
        af = fullAheadInfo(borrowerInfoDTO, bizDate, af1, callbackMonth);
        try{
          String fuzhuEmpName = borrowerDAO.countPeopleNum_EmpName(contractId);
          String fuzhuEmpCardNum = borrowerDAO.countPeopleNum_EmpCardNum(contractId);
          String fuzhuEmpsalary = borrowerDAO.countPeopleNum_EmpSalary(contractId);
          int fuzhusalaryBase=0;
          int borrowerSalaryBase=0;
          if(fuzhuEmpName!=null&&!"".equals(fuzhuEmpName)&&fuzhuEmpCardNum!=null&&!"".equals(fuzhuEmpCardNum)){
            fuzhusalaryBase=borrowerDAO.queryEmpSalary(fuzhuEmpName, fuzhuEmpCardNum);
          }
          if(fuzhusalaryBase<=0&&!"".equals(fuzhuEmpsalary)){
//            if(!"".equals(fuzhuEmpId)&&!"".equals(fuzhuOrgId)){
//              emp_fuzhu=borrowerDAO.queryByCriterions(fuzhuEmpId.toString(),
//                  fuzhuOrgId.toString());
//            }
            fuzhusalaryBase=Integer.parseInt(fuzhuEmpsalary);
          }
          Borrower borrower_wsh = new Borrower();
          borrower_wsh = borrowerDAO.queryById(contractId);
          if(borrower_wsh.getBorrowerName()!=null&&!"".equals(borrower_wsh.getBorrowerName())&&borrower_wsh.getCardNum()!=null&&!"".equals(borrower_wsh.getCardNum())){
            borrowerSalaryBase=borrowerDAO.queryEmpSalary(borrower_wsh.getBorrowerName(), borrower_wsh.getCardNum());
          }
          if(borrowerSalaryBase<=0){
            borrowerSalaryBase=borrower_wsh.getMonthSalary().intValue();
          }
          if(fuzhusalaryBase>0){
            af.setSumSalary(new BigDecimal(borrowerSalaryBase).add((new BigDecimal(fuzhusalaryBase))));
          }else{
            af.setSumSalary(new BigDecimal(borrowerSalaryBase));
          }
        }catch (Exception e) {
          e.printStackTrace();
        }
      }
      if (!shouldBackList.isEmpty()) {
        ShouldBackListDTO dto = (ShouldBackListDTO) shouldBackList
            .get(shouldBackList.size() - 1);
        yearMonth = dto.getLoanKouYearmonth();
      }
      // 判断该贷款账号的所属银行在银行贷款参数PL003表中参数类型PARAM_TYPE=A:参数维护：还款参数and
      // 参数序号PARAM_NUM=1的参数值PARAM_VALUE是否=1:足额扣款
      paramValue = loanBankParaDAO.queryParamValue_LJ(loanBankId, paramType,
          "1");
      if (paramValue.equals(BusiConst.PLDEBITTYPE_SUFF + "")) {
        isAmend = "0";
      } else if (!bizType.equals(BusiConst.PLBUSINESSTYPE_SINGLERECOVER + "")) {
        isAmend = "0";
      }
      loanRepayDay1 = this.getEndDay(yearMonth, loanRepayDay);
      count = af1.getShouldBackList().size();
      pagination.setNrOfElements(count);
      af.setIsAmend(isAmend);
      af.setOvaerLoanRepay(ovaerLoanRepay);
      af.setPldebit(pldebit);
      af.setBorrowerInfoDTO(borrowerInfoDTO);
      af.setShouldBackList(af1.getShouldBackList());
      af.setBizType(bizType);
      af.setLoanBalance(borrowerInfoDTO.getOverplusLoanMoney());// 贷款余额
      af.setMonthYearList(yearMonthList);
      af.setMonthYear(yearMonth + loanRepayDay1);
      pagination.getQueryCriterions().put("callbackMonth", af.getMonthYear());
      if (count <= 0) {
        af.setMonthYear("");
        pagination.getQueryCriterions().put("callbackMonth", null);
      }

      pagination.setNrOfElements(count);
    }
    // 吴洪涛修改//2007-3-11

    try {
      if (af != null) {
        if (contractId != "") {
          if (af.getBorrowerInfoDTO().getLoanMode() == "等额本金") {

            if (af.getBizType().equals(
                BusiConst.PLBUSINESSTYPE_SINGLERECOVER + "")) {
              // 单笔回收
              String monthYear = af.getMonthYear();
              if (monthYear != "") {
                String contractID = af.getBorrowerInfoDTO().getContractId();
                String shouldCorpus = "0";
                List listRestoreLoan = loanBankDAO
                    .queryRestoreLoanbyCriterions_wuht(contractID, monthYear
                        .substring(0, 6));
                if (listRestoreLoan != null && listRestoreLoan.size() > 0) {
                  RestoreLoan restoreLoan = (RestoreLoan) listRestoreLoan
                      .get(0);
                  shouldCorpus = restoreLoan.getShouldCorpus().toString();
                }
                String deadLine = af.getDeadLine();
                List listBorrowerAcc = loanBankDAO
                    .queryBorrowerAccByCcontractId_wuht(contractID);
                if (listBorrowerAcc != null && listBorrowerAcc.size() > 0) {
                  BorrowerAcc borrowerAcc = (BorrowerAcc) listBorrowerAcc
                      .get(0);
                  deadLine = borrowerAcc.getOverplusLimite().toString();
                  if (af.getShouldBackList() != null
                      && af.getShouldBackList().size() > 0) {
                    deadLine = new BigDecimal(deadLine).subtract(
                        new BigDecimal(af.getShouldBackList().size() + ""))
                        .toString();
                  }
                }
                String sumCorpus = af.getSumCorpus().toString();
                LoancallbackTaAF loancallbackTaAF = this
                    .queryTdShow_loancallbackByCriterionsPlrecovertype_corpus_wuht2(
                        shouldCorpus, monthYear, contractID, deadLine,
                        sumCorpus, securityInfo);
                af.setOverplusInterestAll(loancallbackTaAF
                    .getOverplusInterestAll());// 剩余利息
                List listLoanFlowTail = loanBankDAO
                    .queryLoanFlowTail_wuht(contractID);
                BigDecimal realInterest = new BigDecimal(0.0);
                BigDecimal interestAll = new BigDecimal(0.0);
                BigDecimal sumInterest = af.getSumInterest();
                if (listLoanFlowTail != null && listLoanFlowTail.size() > 0) {
                  for (int i = 0; i < listLoanFlowTail.size(); i++) {
                    LoanFlowTail loanFlowTail = (LoanFlowTail) listLoanFlowTail
                        .get(i);
                    realInterest = realInterest.add(loanFlowTail
                        .getRealInterest());
                  }
                }
                interestAll = interestAll.add(realInterest).add(sumInterest)
                    .add(loancallbackTaAF.getOverplusInterestAll());// 总利息
                af.setInterestAll(interestAll);// 总利息
              }
            } else {
              // 部分提前还款一次性还清

              String corpusInterest = af.getCorpusInterest().toString();
              String monthYear = af.getMonthYear();
              String contractID = af.getBorrowerInfoDTO().getContractId();
              String deadLine = af.getDeadLine();
              String sumCorpus = af.getSumCorpus().toString();

              List listBorrowerAcc = loanBankDAO
                  .queryBorrowerAccByCcontractId_wuht(contractID);
              if (listBorrowerAcc != null && listBorrowerAcc.size() > 0) {
                BorrowerAcc borrowerAcc = (BorrowerAcc) listBorrowerAcc.get(0);
                deadLine = borrowerAcc.getOverplusLimite().toString();
                if (af.getShouldBackList() != null
                    && af.getShouldBackList().size() > 0) {
                  deadLine = new BigDecimal(deadLine).subtract(
                      new BigDecimal(af.getShouldBackList().size() + ""))
                      .toString();
                }
              }
              LoancallbackTaAF loancallbackTaAF = this
                  .queryTdShow_loancallbackByCriterionsPlrecovertype_corpus_wuht(
                      corpusInterest, monthYear, contractID, deadLine,
                      sumCorpus, securityInfo);
              af.setOverplusInterestAll(loancallbackTaAF
                  .getOverplusInterestAll());// 剩余利息
              List listLoanFlowTail = loanBankDAO
                  .queryLoanFlowTail_wuht(contractID);
              BigDecimal realInterest = new BigDecimal(0.0);
              BigDecimal interestAll = new BigDecimal(0.0);
              BigDecimal sumInterest = af.getSumInterest();
              if (listLoanFlowTail != null && listLoanFlowTail.size() > 0) {
                for (int i = 0; i < listLoanFlowTail.size(); i++) {
                  LoanFlowTail loanFlowTail = (LoanFlowTail) listLoanFlowTail
                      .get(i);
                  realInterest = realInterest.add(loanFlowTail
                      .getRealInterest());
                }
              }
              interestAll = interestAll.add(realInterest).add(sumInterest).add(
                  loancallbackTaAF.getOverplusInterestAll());// 总利息
              af.setInterestAll(interestAll);// 总利息
            }

          } else {

            if (af.getBizType().equals(
                BusiConst.PLBUSINESSTYPE_SINGLERECOVER + "")) {
              // 单笔回收
              String monthYear = af.getMonthYear();
              if (monthYear != "") {
                String corpusInterest = af.getCorpusInterest().toString();
                String contractID = af.getBorrowerInfoDTO().getContractId();
                List listRestoreLoan = loanBankDAO
                    .queryRestoreLoanbyCriterions_wuht(contractID, monthYear
                        .substring(0, 6));
                if (listRestoreLoan != null && listRestoreLoan.size() > 0) {
                  RestoreLoan restoreLoan = (RestoreLoan) listRestoreLoan
                      .get(0);
                  corpusInterest = restoreLoan.getShouldCorpus().add(
                      restoreLoan.getShouldInterest()).toString();
                }
                String deadLine = af.getDeadLine();
                List listBorrowerAcc = loanBankDAO
                    .queryBorrowerAccByCcontractId_wuht(contractID);
                if (listBorrowerAcc != null && listBorrowerAcc.size() > 0) {
                  BorrowerAcc borrowerAcc = (BorrowerAcc) listBorrowerAcc
                      .get(0);
                  deadLine = borrowerAcc.getOverplusLimite().toString();
                  if (af.getShouldBackList() != null
                      && af.getShouldBackList().size() > 0) {
                    deadLine = new BigDecimal(deadLine).subtract(
                        new BigDecimal(af.getShouldBackList().size() + ""))
                        .toString();
                  }
                }
                String sumCorpus = af.getSumCorpus().toString();
                LoancallbackTaAF loancallbackTaAF = this
                    .queryTdShow_loancallbackByCriterions_wuht2(corpusInterest,
                        monthYear, contractID, deadLine, sumCorpus,
                        securityInfo);
                af.setOverplusInterestAll(loancallbackTaAF
                    .getOverplusInterestAll());// 剩余利息
                List listLoanFlowTail = loanBankDAO
                    .queryLoanFlowTail_wuht(contractID);
                BigDecimal realInterest = new BigDecimal(0.0);
                BigDecimal interestAll = new BigDecimal(0.0);
                BigDecimal sumInterest = af.getSumInterest();
                if (listLoanFlowTail != null && listLoanFlowTail.size() > 0) {
                  for (int i = 0; i < listLoanFlowTail.size(); i++) {
                    LoanFlowTail loanFlowTail = (LoanFlowTail) listLoanFlowTail
                        .get(i);
                    realInterest = realInterest.add(loanFlowTail
                        .getRealInterest());
                  }
                }
                interestAll = interestAll.add(realInterest).add(sumInterest)
                    .add(loancallbackTaAF.getOverplusInterestAll());// 总利息
                af.setInterestAll(interestAll);// 总利息
              }
            } else {
              // 部分提前还款一次性还清

              String corpusInterest = af.getCorpusInterest().toString();
              String monthYear = af.getMonthYear();
              String contractID = af.getBorrowerInfoDTO().getContractId();
              String deadLine = af.getDeadLine();
              String sumCorpus = af.getSumCorpus().toString();

              LoancallbackTaAF loancallbackTaAF = this
                  .queryTdShow_loancallbackByCriterions_wuht(corpusInterest,
                      monthYear, contractID, deadLine, sumCorpus, securityInfo);
              af.setOverplusInterestAll(loancallbackTaAF
                  .getOverplusInterestAll());// 剩余利息
              List listLoanFlowTail = loanBankDAO
                  .queryLoanFlowTail_wuht(contractID);
              BigDecimal realInterest = new BigDecimal(0.0);
              BigDecimal interestAll = new BigDecimal(0.0);
              BigDecimal sumInterest = af.getSumInterest();
              if (listLoanFlowTail != null && listLoanFlowTail.size() > 0) {
                for (int i = 0; i < listLoanFlowTail.size(); i++) {
                  LoanFlowTail loanFlowTail = (LoanFlowTail) listLoanFlowTail
                      .get(i);
                  realInterest = realInterest.add(loanFlowTail
                      .getRealInterest());
                }
              }
              interestAll = interestAll.add(realInterest).add(sumInterest).add(
                  loancallbackTaAF.getOverplusInterestAll());// 总利息
              af.setInterestAll(interestAll);// 总利息
            }
          }
        }
      }
      
      
    } catch (Exception e) {
      e.printStackTrace();
    }
    // 吴洪涛修改//2007-3-11

    return af;
  }

  /**
   * 判断是否允许部份提前
   * 
   * @param bizDate
   * @param borrowerInfoDTO
   * @param af1
   * @param aheadMoney
   * @return
   * @throws Exception
   */
  // 部份提前
  public LoancallbackTaAF partAheadInfo(String bizDate,
      BorrowerInfoDTO borrowerInfoDTO, LoancallbackTaAF af1, String aheadMoney,
      String yearMonth) throws Exception {
    LoancallbackTaAF af = new LoancallbackTaAF();
    String paramType = "B";// 参数类型
    String paramValue = "";// 参数值
    String paramExplain = "";// 参数说明
    String loanStartDate = borrowerInfoDTO.getLoanStartDate();// 发放日期
    Integer loanBankId = borrowerInfoDTO.getLoanBankId();// 放款银行
    String contractId = borrowerInfoDTO.getContractId();// 合同编号
    BigDecimal aheadCorpus = new BigDecimal(0.00);// 提前还款本金
    BigDecimal overplusLoanMoney = new BigDecimal(0.00);// 剩余本金
    String loanRepayDay = borrowerInfoDTO.getLoanRepayDay();// 还款日
    String temp_loanRepayDay = "";
    String day = bizDate.substring(6, 8);
    List shouldBackList = null;
    List shouldBackList1 = af1.getShouldBackList();
    String loanRepayDayBiz = this.getEndDay(bizDate.substring(0, 6),
        loanRepayDay);// 会计日期的还款日
    overplusLoanMoney = borrowerInfoDTO.getOverplusLoanMoney();
    String chgType = af1.getAheadType();
    if (aheadMoney != null) {
      aheadCorpus = new BigDecimal(aheadMoney);
    } else {
      if (!chgType.equals("3") && !"".equals(chgType)) {
        aheadCorpus = new BigDecimal(0.00);
      } else {
        aheadCorpus = overplusLoanMoney.subtract(af1.getSumCorpus());
      }
    }
    String endDate = "";// 还至年日
    if (!shouldBackList1.isEmpty()) {
      ShouldBackListDTO dto = (ShouldBackListDTO) shouldBackList1
          .get(shouldBackList1.size() - 1);
      endDate = dto.getLoanKouYearmonth().substring(0, 6);// 用来设置还至年月下拉框的显示值
    }
    if (endDate == "") {
      List monthYearList = af1.getMonthYearList();
      if (!monthYearList.isEmpty()) {
        ShouldBackListDTO dto = (ShouldBackListDTO) monthYearList.get(0);
        endDate = dto.getLoanKouYearmonth().substring(0, 6);
        if (Integer.parseInt(loanRepayDayBiz) <= Integer.parseInt(day)) {
          endDate = BusiTools.addMonth(endDate, -1);
        }
      } else {
        // 取下一年1月
        String year = bizDate.substring(0, 4);
        int iYear = Integer.parseInt(year) + 1;
        String temp_yearMonth = String.valueOf(iYear) + "01";
        temp_yearMonth = temp_yearMonth.substring(0, 6);
        if (Integer.parseInt(loanRepayDayBiz) <= Integer.parseInt(day)) {
          endDate = BusiTools.addMonth(temp_yearMonth, -1);
        } else {
          endDate = temp_yearMonth;
        }
      }
    } else if (Integer.parseInt(loanRepayDayBiz) > Integer.parseInt(day)) {
      endDate = BusiTools.addMonth(endDate, 1);
    }
    // 超前还款未还清不允许提前
    temp_loanRepayDay = this.getEndDay(bizDate.substring(0, 6), loanRepayDay);
    if (Integer.parseInt(day) < Integer.parseInt(temp_loanRepayDay)) {
      // 会计日小于还款日(大于等于)
      shouldBackList = restoreLoanDAO.queryRestoreLoanListByContractId_LJD(
          contractId, endDate);
    } else {
      // 会计日大于等于还款日
      shouldBackList = restoreLoanDAO.queryRestoreLoanListByContractId_LJE(
          contractId, endDate);
    }
    if (!shouldBackList.isEmpty()) {
      throw new BusinessException("超前还款未还清，不允许提前还款！");
    } else {
      paramValue = loanContractParaDAO.queryParamValue_wsh(loanBankId,
          paramType, "2", contractId);
      // 判断该贷款号在合同贷款参数PL004表中参数序号=2的参数值是否=1(不允许提前还款)
      if (paramValue.equals(BusiConst.PLCOMMONSTATUS_2_NOTALLOW + "")) {
        // throw new BusinessException("不允许提前还款！");
        // 取出该参数对应的参数说明PARAM_EXPLAIN，判断（会计日期-PL111表中发放日期LOAN_START_DATE）的月数是否大于等于该值
        paramExplain = loanContractParaDAO.queryParamExplain_wsh(loanBankId,
            paramType, "2", contractId);
        int temp_monthCounts = BusiTools.getDisMonths(bizDate.substring(0, 4)
            + "-" + bizDate.substring(4, 6) + "-" + bizDate.substring(6, 8),
            loanStartDate.substring(0, 4) + "-" + loanStartDate.substring(4, 6)
                + "-" + loanStartDate.substring(6, 8));
        if (temp_monthCounts >= Integer.parseInt(paramExplain)) {
          // 取出该贷款号在合同贷款参数PL004表中参数序号=5的参数说明，判断该贷款账在贷款流水账头表PL202中(会计年内1-12)提前还款的次数是否小于该值
          paramExplain = loanContractParaDAO.queryParamExplain_wsh(loanBankId,
              paramType, "5", contractId);
          // 会计年内提前还款次数
          int aheadCounts = loanFlowTailDAO.queryCallbackAheadCounts_LJ(
              contractId, bizDate.substring(0, 4)).intValue();
          if (aheadCounts < Integer.parseInt(paramExplain)) {
            // 取出该贷款号在合同贷款参数PL004表中参数序号=6的参数说明，判断该贷款账在贷款流水账头表PL202中是提前还款的次数是否小于该值
            paramExplain = loanContractParaDAO.queryParamExplain_wsh(loanBankId,
                paramType, "6", contractId);
            // 贷款期限内提前次数
            int lineCounts = loanFlowTailDAO.queryCallbackAheadCounts_LJ(
                contractId, null).intValue();
            ;
            if (lineCounts < Integer.parseInt(paramExplain)) {
              // 允许
              af = findPartAheadInfo(borrowerInfoDTO, af1, bizDate,
                  aheadCorpus, yearMonth);
            } else {
              throw new BusinessException("不允许提前还款！");
            }
          } else {
            throw new BusinessException("不允许提前还款！");
          }
        } else {
          throw new BusinessException("不允许提前还款！");
        }
      } else {// 允许再判断
        // 取出该贷款号在合同贷款参数PL004表中参数序号=5的参数说明，判断该贷款账在贷款流水账头表PL202中(会计年内1-12)提前还款的次数是否小于该值
        paramExplain = loanContractParaDAO.queryParamExplain_wsh(loanBankId,
            paramType, "5", contractId);
        // 会计年内提前还款次数
        int aheadCounts = loanFlowTailDAO.queryCallbackAheadCounts_LJ(
            contractId, bizDate.substring(0, 4)).intValue();
        if (aheadCounts < Integer.parseInt(paramExplain)) {
          // 取出该贷款号在合同贷款参数PL004表中参数序号=6的参数说明，判断该贷款账在贷款流水账头表PL202中是提前还款的次数是否小于该值
          paramExplain = loanContractParaDAO.queryParamExplain_wsh(loanBankId,
              paramType, "6", contractId);
          // 贷款期限内提前次数
          int lineCounts = loanFlowTailDAO.queryCallbackAheadCounts_LJ(
              contractId, null).intValue();
          ;
          if (lineCounts < Integer.parseInt(paramExplain)) {
            // 允许
            af = findPartAheadInfo(borrowerInfoDTO, af1, bizDate, aheadCorpus,
                yearMonth);
          } else {
            throw new BusinessException("不允许提前还款！");
          }
        } else {
          throw new BusinessException("不允许提前还款！");
        }
      }
    }
    // if(aheadMoney == null){
    // af.setDeadLine("0");
    // }
    af.setAheadType(af1.getAheadType());
    return af;
  }

  /**
   * 判断是否允许部份提前 贷款人实际年龄加贷款期限不超过 贷款最高年限 借款人月收入还款比例
   * 
   * @param bizDate
   * @param borrowerInfoDTO
   * @param af1
   * @param aheadMoney
   * @return
   * @throws Exception
   */
  // 部份提前
  public String partAheadInfo_wsh(LoancallbackTaAF loancallbackTaAF,
      SecurityInfo securityInfo) throws Exception {
    String paramType = "B";// 参数类型
    String paramExplain = "";// 参数说明
    String matter = "";
    String changeType="";
    changeType=loancallbackTaAF.getAheadTypeS();
    if("2".equals(changeType)||"4".equals(changeType)){
      int age = 0;
      String contractId = loancallbackTaAF.getBorrowerInfoDTO().getContractId();// 合同编号
      BorrowerAcc borrowerAcc_wsh = new BorrowerAcc();
      borrowerAcc_wsh = borrowerAccDAO.queryById(contractId);
      Integer loanBankId = new Integer(borrowerAcc_wsh.getLoanBankId().toString());// 放款银行
      Borrower borrower_wsh = new Borrower();
      borrower_wsh = borrowerDAO.queryById(contractId);
      Houses houses = housesDAO.queryById(contractId);
      if ("1".equals(borrower_wsh.getSex())) {
        paramExplain = loanContractParaDAO.queryParamExplain_wsh_1(loanBankId,
            paramType, "8", contractId, "1");
        String opdate = borrower_wsh.getOpTime().toString().substring(0, 4);
        String nowdate = securityInfo.getUserInfo().getPlbizDate()
            .substring(0, 4);
        if (borrower_wsh.getBirthday() != null && !"".equals(borrower_wsh.getBirthday())) {
//          age = borrower_wsh.getAge().intValue() + Integer.parseInt(nowdate)
//              - Integer.parseInt(opdate);
          age = Integer.parseInt(nowdate)
          - Integer.parseInt(borrower_wsh.getBirthday().substring(0,4));
        }
        if ((age + Integer.parseInt(loancallbackTaAF.getDeadLine()) / 12) > Integer
            .parseInt(paramExplain)) {
          matter = "贷款人实际年龄加贷款期限不超过" + paramExplain + "岁";
        }
      } else {
        paramExplain = loanContractParaDAO.queryParamExplain_wsh_1(loanBankId,
            paramType, "8", contractId, "2");
        String opdate = borrower_wsh.getOpTime().toString().substring(0, 4);
        String nowdate = securityInfo.getUserInfo().getPlbizDate()
            .substring(0, 4);
        if (borrower_wsh.getBirthday() != null && !"".equals(borrower_wsh.getBirthday())) {
//        age = borrower_wsh.getAge().intValue() + Integer.parseInt(nowdate)
//            - Integer.parseInt(opdate);
        age = Integer.parseInt(nowdate)
        - Integer.parseInt(borrower_wsh.getBirthday().substring(0,4));
      }
        if ((age + Integer.parseInt(loancallbackTaAF.getDeadLine()) / 12) > Integer
            .parseInt(paramExplain)) {
          matter = "贷款人实际年龄加贷款期限不超过" + paramExplain + "岁";
        }
      }
      if ("01".equals(houses.getHouseType())) {
        paramExplain = loanContractParaDAO.queryParamExplain_wsh_1(loanBankId,
            paramType, "9", contractId, "1");
        String opdate = borrowerAcc_wsh.getLoanStartDate().substring(0,4);
        String nowdate = securityInfo.getUserInfo().getPlbizDate().substring(0,4);
        age = Integer.parseInt(nowdate) - Integer.parseInt(opdate);
        if ((age + Integer.parseInt(loancallbackTaAF.getDeadLine()) / 12) > Integer
            .parseInt(paramExplain)) {
          matter += "商品房贷款最高年限不超过" + paramExplain + "年";
        }
      } else {
        paramExplain = loanContractParaDAO.queryParamExplain_wsh_1(loanBankId,
            paramType, "9", contractId, "2");
        String opdate = borrowerAcc_wsh.getLoanStartDate().substring(0,4);
        String nowdate = securityInfo.getUserInfo().getPlbizDate().substring(0,4);
        age = Integer.parseInt(nowdate) - Integer.parseInt(opdate);
        if ((age + Integer.parseInt(loancallbackTaAF.getDeadLine()) / 12) > Integer
            .parseInt(paramExplain)) {
          matter += "二手房贷款最高年限不超过" + paramExplain + "年";
        }
      }
//      paramExplain = loanContractParaDAO.queryParamExplain_wsh(loanBankId,
//          paramType, "10", contractId, null);
//      String fuzhuEmpId = borrowerDAO.countPeopleNum_EmpId(contractId);
//      String fuzhuOrgId = borrowerDAO.countPeopleNum_Id(contractId);
//      Emp emp_fuzhu = borrowerDAO.queryByCriterions(fuzhuEmpId.toString(),
//          fuzhuOrgId.toString());
//      if(emp_fuzhu!=null){
//        if ((loancallbackTaAF.getCorpusInterest().add(emp_fuzhu.getSalaryBase())).compareTo(
//            borrower_wsh.getMonthSalary().multiply(
//                new BigDecimal(paramExplain).divide(new BigDecimal(100), 2))) > 0) {
//          matter += "借款人月收入还款比例不超过" + paramExplain + "%";
//        }
//      }else{
//        if (loancallbackTaAF.getCorpusInterest().compareTo(
//            borrower_wsh.getMonthSalary().multiply(
//                new BigDecimal(paramExplain).divide(new BigDecimal(100), 2))) > 0) {
//          matter += "借款人月收入还款比例不超过" + paramExplain + "%";
//        }
//      }
    }
    if("3".equals(changeType)||"5".equals(changeType)){
      int age = 0;
      String contractId = loancallbackTaAF.getBorrowerInfoDTO().getContractId();// 合同编号
      BorrowerAcc borrowerAcc_wsh = new BorrowerAcc();
      borrowerAcc_wsh = borrowerAccDAO.queryById(contractId);
      Integer loanBankId = new Integer(borrowerAcc_wsh.getLoanBankId().toString());// 放款银行
      Borrower borrower_wsh = new Borrower();
      borrower_wsh = borrowerDAO.queryById(contractId);
//      Houses houses = housesDAO.queryById(contractId);
//      if ("1".equals(borrower_wsh.getSex())) {
//        paramExplain = loanContractParaDAO.queryParamExplain_wsh(loanBankId,
//            paramType, "8", contractId, "1");
//        String opdate = borrower_wsh.getOpTime().toString().substring(0, 4);
//        String nowdate = securityInfo.getUserInfo().getPlbizDate()
//            .substring(0, 4);
//        if (borrower_wsh.getAge() != null && !"".equals(borrower_wsh.getAge())) {
//          age = borrower_wsh.getAge().intValue() + Integer.parseInt(nowdate)
//              - Integer.parseInt(opdate);
//        }
//        if ((age + Integer.parseInt(loancallbackTaAF.getDeadLine()) / 12) > Integer
//            .parseInt("65")) {
//          matter = "贷款人实际年龄加贷款期限不超过" + paramExplain + "岁";
//        }
//      } else {
//        paramExplain = loanContractParaDAO.queryParamExplain_wsh(loanBankId,
//            paramType, "8", contractId, "2");
//        String opdate = borrower_wsh.getOpTime().toString().substring(0, 4);
//        String nowdate = securityInfo.getUserInfo().getPlbizDate()
//            .substring(0, 4);
//        if (borrower_wsh.getAge() != null && !"".equals(borrower_wsh.getAge())) {
//          age = borrower_wsh.getAge().intValue() + Integer.parseInt(nowdate)
//              - Integer.parseInt(opdate);
//        }
//        if ((age + Integer.parseInt(loancallbackTaAF.getDeadLine()) / 12) > Integer
//            .parseInt(paramExplain)) {
//          matter = "贷款人实际年龄加贷款期限不超过" + paramExplain + "岁";
//        }
//      }
//      if ("1".equals(houses.getHouseType())) {
//        paramExplain = loanContractParaDAO.queryParamExplain_wsh(loanBankId,
//            paramType, "9", contractId, "1");
//        String opdate = borrowerAcc_wsh.getLoanStartDate().substring(4);
//        String nowdate = securityInfo.getUserInfo().getPlbizDate().substring(4);
//        age = Integer.parseInt(nowdate) - Integer.parseInt(opdate);
//        if ((age + Integer.parseInt(loancallbackTaAF.getDeadLine()) / 12) > Integer
//            .parseInt("20")) {
//          matter += "商品房贷款最高年限不超过" + paramExplain + "年";
//        }
//      } else {
//        paramExplain = loanContractParaDAO.queryParamExplain_wsh(loanBankId,
//            paramType, "9", contractId, "2");
//        String opdate = borrowerAcc_wsh.getLoanStartDate().substring(4);
//        String nowdate = securityInfo.getUserInfo().getPlbizDate().substring(4);
//        age = Integer.parseInt(nowdate) - Integer.parseInt(opdate);
//        if ((age + Integer.parseInt(loancallbackTaAF.getDeadLine()) / 12) > Integer
//            .parseInt("10")) {
//          matter += "二手房贷款最高年限不超过" + paramExplain + "年";
//        }
//      }
      paramExplain = loanContractParaDAO.queryParamExplain_wsh_1(loanBankId,
          paramType, "10", contractId, null);
      String fuzhuEmpId = borrowerDAO.countPeopleNum_EmpId(contractId);
      String fuzhuOrgId = borrowerDAO.countPeopleNum_Id(contractId);
      String fuzhuEmpName = borrowerDAO.countPeopleNum_EmpName(contractId);
      String fuzhuEmpCardNum = borrowerDAO.countPeopleNum_EmpCardNum(contractId);
      String fuzhuEmpsalary = borrowerDAO.countPeopleNum_EmpSalary(contractId);
      Emp emp_fuzhu =null;
      int fuzhusalaryBase=0;
      int borrowerSalaryBase=0;
      if(fuzhuEmpName!=null&&!"".equals(fuzhuEmpName)&&fuzhuEmpCardNum!=null&&!"".equals(fuzhuEmpCardNum)){
        fuzhusalaryBase=borrowerDAO.queryEmpSalary(fuzhuEmpName, fuzhuEmpCardNum);
      }
      if(fuzhusalaryBase<=0&&!"".equals(fuzhuEmpsalary)){
//        if(!"".equals(fuzhuEmpId)&&!"".equals(fuzhuOrgId)){
//          emp_fuzhu=borrowerDAO.queryByCriterions(fuzhuEmpId.toString(),
//              fuzhuOrgId.toString());
//        }
        fuzhusalaryBase=Integer.parseInt(fuzhuEmpsalary);
      }
      if(borrower_wsh.getBorrowerName()!=null&&!"".equals(borrower_wsh.getBorrowerName())&&borrower_wsh.getCardNum()!=null&&!"".equals(borrower_wsh.getCardNum())){
        borrowerSalaryBase=borrowerDAO.queryEmpSalary(borrower_wsh.getBorrowerName(), borrower_wsh.getCardNum());
      }
      if(borrowerSalaryBase<=0){
        borrowerSalaryBase=borrower_wsh.getMonthSalary().intValue();
      }
      if(fuzhusalaryBase>0){
        if ((loancallbackTaAF.getCorpusInterest()).compareTo((
            (new BigDecimal(borrowerSalaryBase)).add((new BigDecimal(fuzhusalaryBase)))).multiply(
                new BigDecimal(paramExplain)).divide(new BigDecimal(100), 2)) > 0) {
          matter += "借款人月收入还款比例不超过" + paramExplain + "%";
        }
      }else{
        if (loancallbackTaAF.getCorpusInterest().compareTo(
            (new BigDecimal(borrowerSalaryBase)).multiply(
                new BigDecimal(paramExplain)).divide(new BigDecimal(100), 2)) > 0) {
          matter += "借款人月收入还款比例不超过" + paramExplain + "%";
        }
      }
      System.out.println(borrower_wsh.getMonthSalary().multiply(
          new BigDecimal(paramExplain)).divide(new BigDecimal(100), 2));
    }
   
    
    return matter;
  }

  /**
   * 部份提前还款信息
   * 
   * @param borrowerInfoDTO
   * @param af1
   * @param bizDate
   * @param aheadMoney
   * @return
   * @throws Exception
   */
  public LoancallbackTaAF findPartAheadInfo(BorrowerInfoDTO borrowerInfoDTO,
      LoancallbackTaAF af1, String bizDate, BigDecimal aheadMoney,
      String yearMonths) throws Exception {
    LoancallbackTaAF af = new LoancallbackTaAF();
    ShouldBackListDTO shouldBackListDTO = new ShouldBackListDTO();
    String paramExplain = "";// 参数说明
    String paramType = "B";// 参数类型
    String paramValue = "";// 参数值
    String loanRepayDay = borrowerInfoDTO.getLoanRepayDay();// 还款日
    String days = "";// 占用天数
    Integer loanBankId = borrowerInfoDTO.getLoanBankId();// 放款银行
    BigDecimal overplusLoanMoney = new BigDecimal(0.00);// 剩余本金
    overplusLoanMoney = borrowerInfoDTO.getOverplusLoanMoney();
    BigDecimal temp_overplusLoanMoney = overplusLoanMoney;
    BigDecimal aheadCorpus = new BigDecimal(0.00);// 提前还款本金
    BigDecimal aheadInterest = new BigDecimal(0.00);// 提前还款利息
    BigDecimal loanRate = new BigDecimal(0.00);// 月利率
    BigDecimal loanPoundageMoney = new BigDecimal(0.00);// 手续费金额
    BigDecimal corpusInterest = new BigDecimal(0.00);// 提前还款后月还本息
    String deadLine = "";// 提前还款后剩余期限
    BigDecimal sumCorpus = new BigDecimal(0.00);// 总还款本金
    BigDecimal sumInterest = new BigDecimal(0.00);// 本次总还款利息
    BigDecimal sumMoney = new BigDecimal(0.00);// 总还金额
    BigDecimal ovaerLoanRepay = new BigDecimal(0.00);// 挂账余额
    BigDecimal realMoney = new BigDecimal(0.00);// 实收金额
    List tempList = new ArrayList();
    String contractId = borrowerInfoDTO.getContractId();// 合同编号
    // 提前还款最低还款金额为
    paramExplain = loanContractParaDAO.queryParamExplain_wsh(loanBankId,
        paramType, "4", contractId);
    BigDecimal minMoney = new BigDecimal(paramExplain);
    String yearMonth = bizDate.substring(0, 6);// 取出会计日期中的年月
    String day = bizDate.substring(6, bizDate.length());// 取出会计日期中的日
    String temp_bizDate = yearMonth.substring(0, 4) + "-"
        + yearMonth.substring(4, 6) + "-" + day;// 用来判断列表中的还款类型所转换的会计日期
    String callbackMonth = yearMonths;// 还至年月就是下拉框显示的年月
    String isAmendLine = "0";// 是否可以修改剩余期限（0.不可以；1.可以）
    String loanRepayDay1 = "";
    String loanRepayDayBiz = this.getEndDay(yearMonth, loanRepayDay);// 会计日期的还款日
    List shouldBackList = af1.getShouldBackList();
    String endDate = "";// 还至年日
    int temp_monthCounts = 0;// 还过月数 用判断占用天数的方法取日期来减发放日
    if (!shouldBackList.isEmpty()) {
      ShouldBackListDTO dto = (ShouldBackListDTO) shouldBackList
          .get(shouldBackList.size() - 1);
      endDate = dto.getLoanKouYearmonth().substring(0, 6);// 用来设置还至年月下拉框的显示值
    }
    String loanStartDate = borrowerInfoDTO.getLoanStartDate();// 发放日
    if (yearMonths != null && !yearMonths.equals("")) {
      // 如果还至日不为空走一个判断
      loanRepayDay1 = this.getEndDay(yearMonths, loanRepayDay);
      // 占用天数
      // 用还款日和会计日做比较，1、还款日大于会计日 占用天数=(还至年月+1+会计日)-还至日。
      // 2、还款日小于等于会计日 占用天数=还至年月+会计日-还至日
      if (Integer.parseInt(loanRepayDayBiz) > Integer.parseInt(day)) {
        String temp_yearMonths = BusiTools.addMonth(yearMonths, 1);
        days = BusiTools.minusDate(temp_yearMonths.substring(0, 4) + "-"
            + temp_yearMonths.substring(4, 6) + "-" + day, yearMonths
            .substring(0, 4)
            + "-" + yearMonths.substring(4, 6) + "-" + loanRepayDay1)
            + "";
        temp_monthCounts = BusiTools.getDisMonths(temp_yearMonths.substring(0,
            4)
            + "-" + temp_yearMonths.substring(4, 6) + "-" + day, loanStartDate
            .substring(0, 4)
            + "-"
            + loanStartDate.substring(4, 6)
            + "-"
            + loanStartDate.substring(6, 8));
      } else if (Integer.parseInt(loanRepayDayBiz) <= Integer.parseInt(day)) {
        days = BusiTools.minusDate(yearMonths.substring(0, 4) + "-"
            + yearMonths.substring(4, 6) + "-" + day, yearMonths
            .substring(0, 4)
            + "-" + yearMonths.substring(4, 6) + "-" + loanRepayDay1)
            + "";
        temp_monthCounts = BusiTools.getDisMonths(yearMonths.substring(0, 4)
            + "-" + yearMonths.substring(4, 6) + "-" + day, loanStartDate
            .substring(0, 4)
            + "-"
            + loanStartDate.substring(4, 6)
            + "-"
            + loanStartDate.substring(6, 8));
      }
    } else {
      // 还至日为空
      List yearList = af1.getMonthYearList();
      if (!yearList.isEmpty()) {
        ShouldBackListDTO dto = (ShouldBackListDTO) yearList.get(0);
        yearMonths = dto.getLoanKouYearmonth();
        if (Integer.parseInt(loanRepayDayBiz) <= Integer.parseInt(day)) {
          String temp_yearMonths = BusiTools.addMonth(yearMonths, -1);
          loanRepayDay1 = this.getEndDay(temp_yearMonths, loanRepayDay);
          days = BusiTools.minusDate(temp_yearMonths.substring(0, 4) + "-"
              + temp_yearMonths.substring(4, 6) + "-" + day, temp_yearMonths
              .substring(0, 4)
              + "-" + temp_yearMonths.substring(4, 6) + "-" + loanRepayDay1)
              + "";
          temp_monthCounts = BusiTools.getDisMonths(temp_yearMonths.substring(
              0, 4)
              + "-" + temp_yearMonths.substring(4, 6) + "-" + day,
              loanStartDate.substring(0, 4) + "-"
                  + loanStartDate.substring(4, 6) + "-"
                  + loanStartDate.substring(6, 8));
        } else {
          // loanRepayDay1 = this.getEndDay(yearMonths, loanRepayDay); ww改的
          String temp_month = BusiTools.addMonth(yearMonths, -1);
          loanRepayDay1 = this.getEndDay(temp_month, loanRepayDay);// ww改的
          days = BusiTools.minusDate(yearMonths.substring(0, 4) + "-"
              + yearMonths.substring(4, 6) + "-" + day, temp_month.substring(0,
              4)
              + "-" + temp_month.substring(4, 6) + "-" + loanRepayDay1)
              + "";
          temp_monthCounts = BusiTools.getDisMonths(yearMonths.substring(0, 4)
              + "-" + yearMonths.substring(4, 6) + "-" + day, loanStartDate
              .substring(0, 4)
              + "-"
              + loanStartDate.substring(4, 6)
              + "-"
              + loanStartDate.substring(6, 8));
        }
      } else {
        // 取下一年1月
        String year = bizDate.substring(0, 4);
        int iYear = Integer.parseInt(year) + 1;
        String temp_yearMonth = String.valueOf(iYear) + "01";
        if (Integer.parseInt(loanRepayDayBiz) <= Integer.parseInt(day)) {
          String temp_yearMonths = BusiTools.addMonth(temp_yearMonth, -1);
          loanRepayDay1 = this.getEndDay(temp_yearMonths, loanRepayDay);
          days = BusiTools.minusDate(temp_yearMonths.substring(0, 4) + "-"
              + temp_yearMonths.substring(4, 6) + "-" + day, temp_yearMonths
              .substring(0, 4)
              + "-" + temp_yearMonths.substring(4, 6) + "-" + loanRepayDay1)
              + "";
          temp_monthCounts = BusiTools.getDisMonths(temp_yearMonths.substring(
              0, 4)
              + "-" + temp_yearMonths.substring(4, 6) + "-" + day,
              loanStartDate.substring(0, 4) + "-"
                  + loanStartDate.substring(4, 6) + "-"
                  + loanStartDate.substring(6, 8));
        } else if (Integer.parseInt(loanRepayDayBiz) > Integer.parseInt(day)) {
          loanRepayDay1 = this.getEndDay(bizDate, loanRepayDay);
          days = BusiTools.minusDate(temp_yearMonth.substring(0, 4) + "-"
              + temp_yearMonth.substring(4, 6) + "-" + day, year + "-" + "12"
              + "-" + loanRepayDay1)
              + "";
          temp_monthCounts = BusiTools.getDisMonths(temp_yearMonth.substring(0,
              4)
              + "-" + temp_yearMonth.substring(4, 6) + "-" + day, loanStartDate
              .substring(0, 4)
              + "-"
              + loanStartDate.substring(4, 6)
              + "-"
              + loanStartDate.substring(6, 8));
        }
      }
    }
    // if(Integer.parseInt(bizDate)<Integer.parseInt(endDate+loanRepayDay1)){
    // days="0";
    // }else{
    // if(Integer.parseInt(day)<Integer.parseInt(loanRepayDay1)){
    // //会计日期小于该合同的还款日，占用天数=会计日期-（会计年月-1的还款日）
    // String temp_yearMonths=BusiTools.addMonth(yearMonths, -1);
    // String temp_loanRepayDay=this.getEndDay(temp_yearMonths, loanRepayDay);
    // days =
    // BusiTools.minusDate(temp_bizDate,temp_yearMonths.substring(0,4)+"-"+temp_yearMonths.substring(4,6)+"-"+temp_loanRepayDay)+"";
    // }else{
    // //会计日期大于等于该合同的还款日占用天数=会计日期-（会计年月的还款日）
    // days =
    // BusiTools.minusDate(temp_bizDate,yearMonths.substring(0,4)+"-"+yearMonths.substring(4,6)+"-"+loanRepayDay1)+"";
    // }
    // }
    if (Integer.parseInt(days) <= 0) {
      days = "0";
    }

    // /**
    // * 查询PL112_1(条件:合同编号，status=1已启用)
    // */
    // String chgType = "3";//提前还款类型
    // String overplusLimite = "";//剩余期限
    // String aheadCheckId = "";//提前还款信息表ID
    // List list = loanBankParaDAO.queryPL112Info_jj(contractId, "0");
    // if(!list.isEmpty()){
    // Object obj[]=null;
    // Iterator it = list.iterator();
    // while(it.hasNext()){
    // obj = (Object[])it.next();
    // aheadCheckId = obj[0].toString();
    // overplusLimite = obj[1].toString();
    // chgType = obj[2].toString();
    // }
    // af.setDeadLine(overplusLimite);
    // // af.setAheadCorpus(new BigDecimal(0.00));
    // af.setAheadCheckId(aheadCheckId);
    // // aheadMoney=new BigDecimal(0.00);
    // }
    aheadCorpus = aheadMoney;// overplusLoanMoney.subtract(af1.getSumCorpus());
    // 手续费金额分三种情况（判断该贷款号在合同贷款参数PL004表中参数序号=7的参数值）
    paramValue = loanContractParaDAO.queryParamValue_wsh(loanBankId, paramType,
        "7", contractId);
    // 参数值为1:手续费=提前还款本金*参数说明/100
    if (paramValue.equals(BusiConst.PLPREPAYMENTFEES_PREPAYMENT + "")) {
      paramExplain = loanContractParaDAO.queryParamExplain_LJ(loanBankId,
          paramType, "7", contractId);
      loanPoundageMoney = aheadCorpus.multiply(new BigDecimal(paramExplain))
          .divide(new BigDecimal(100), 2, BigDecimal.ROUND_HALF_UP);
    } else if (paramValue.equals(BusiConst.PLPREPAYMENTFEES_PAYMENT + "")) {
      paramExplain = loanContractParaDAO.queryParamExplain_LJ(loanBankId,
          paramType, "7", contractId);
      loanPoundageMoney = new BigDecimal(paramExplain);
    } else {
      loanPoundageMoney = new BigDecimal(0.00);
    }
    if (yearMonths != null && !yearMonths.equals("")) {
      yearMonths = BusiTools.addMonth(yearMonths, 1);// 利率取还款表中还至年月的下一个月
    } else {
      yearMonths = bizDate.substring(0, 6);
    }

    if (endDate == "") {
      List monthYearList = af1.getMonthYearList();
      if (!monthYearList.isEmpty()) {
        ShouldBackListDTO dto = (ShouldBackListDTO) monthYearList.get(0);
        endDate = dto.getLoanKouYearmonth().substring(0, 6);
        if (Integer.parseInt(loanRepayDayBiz) <= Integer.parseInt(day)) {
          endDate = BusiTools.addMonth(endDate, -1);
        }
      } else {
        // 取下一年1月
        String year = bizDate.substring(0, 4);
        int iYear = Integer.parseInt(year) + 1;
        String temp_yearMonth = String.valueOf(iYear) + "01";
        temp_yearMonth = temp_yearMonth.substring(0, 6);
        if (Integer.parseInt(loanRepayDayBiz) <= Integer.parseInt(day)) {
          endDate = BusiTools.addMonth(temp_yearMonth, -1);
        } else {
          endDate = temp_yearMonth;
        }
      }
    } else if (Integer.parseInt(loanRepayDayBiz) > Integer.parseInt(day)) {
      endDate = BusiTools.addMonth(endDate, 1);
    }
    tempList = restoreLoanDAO.queryRestoreLoanInfoByContractId_LJ(contractId,
        endDate);
    if (!tempList.isEmpty()) {
      shouldBackListDTO = (ShouldBackListDTO) tempList.get(0);
    } else {
      endDate = BusiTools.addMonth(endDate, -1);
      tempList = restoreLoanDAO.queryRestoreLoanInfoByContractId_LJ(contractId,
          endDate.substring(0, 6));
      if (!tempList.isEmpty()) {
        shouldBackListDTO = (ShouldBackListDTO) tempList.get(0);
      }
    }
    String loanTimeLimit = borrowerInfoDTO.getLoanTimeLimit();// 原贷款期限
    // if(Integer.parseInt(loanTimeLimit)>60){
    // 是——利率取PL001中loan_rate_type=1的最新的利率
    // if(Integer.parseInt(loanTimeLimit)>60){
    // loanRate = loanRateDAO.findMontRate(borrowerInfoDTO.getOfficeCode(),
    // String.valueOf(BusiConst.PLLOANTYPE_FIVEUP));
    // }else{
    // //否——计算提前还款月还本息取利率到PL001中loan_rate_type=0的最新利率
    // loanRate = loanRateDAO.findMontRate(borrowerInfoDTO.getOfficeCode(),
    // String.valueOf(BusiConst.PLLOANTYPE_FIVE));
    // }
    loanRate = loanRateDAO.findMontRate_wsh(contractId, yearMonth);
    deadLine = loanRateDAO.findpl111_timelimit(contractId);
    // }
    // 提前还款利息=剩余本金（剩余本金-列表本金合计）*占用天数*月利率/30
    aheadInterest = temp_overplusLoanMoney.subtract(af1.getSumCorpus())
        .multiply(new BigDecimal(days)).multiply(loanRate).divide(
            new BigDecimal(30), 2, BigDecimal.ROUND_HALF_UP);
    paramValue = loanContractParaDAO.queryParamValue_wsh(loanBankId, paramType,
        "1", contractId);
    // if(!overplusLimite.equals("")){
    // paramValue = BusiConst.PLRECOVERPARASCHG_CHGMONTHS+"";
    // }
    String loanMode = borrowerInfoDTO.getLoanMode();
    // 从PL003中查询扣款方式(全额扣款、足额扣款)
    // String pldebit = loanBankParaDAO.queryParamValue_LJ(loanBankId,
    // paramType, "1");
    // if(list.isEmpty()){
    if (paramValue.equals(BusiConst.PLRECOVERPARASCHG_SAMEPAY + "")) {// 参数值为1
      if (loanMode.equals(String.valueOf(BusiConst.PLRECOVERTYPE_CORPUS))) {
        // 提前还款后剩余期限=贷款期限-（会计年月和借款人账户表PL111中发放年月之间的月数差）
//        deadLine = String.valueOf(Integer.parseInt(borrowerInfoDTO
//            .getLoanTimeLimit())
//            - temp_monthCounts);
        // Integer.parseInt(borrowerInfoDTO.getLoanTimeLimit())-(BusiTools.getDisMonths
        // (bizDate.substring(0,4)+"-"+bizDate.substring(4,6)+"-"+bizDate.substring(6,8),
        // borrowerInfoDTO.getLoanStartDate().substring(0,4)+"-"+borrowerInfoDTO.getLoanStartDate().substring(4,6)+"-"+
        // borrowerInfoDTO.getLoanStartDate().substring(6,8)))+"";
        // 提前还款后月还本息的值=（剩余本金-应还本金合-提前还款金额）*（1+月利率）新剩余期限*月利率/(1+月利率)新剩余期限-1
        overplusLoanMoney = overplusLoanMoney.subtract(af1.getSumCorpus())
            .subtract(aheadMoney);
        if (Integer.parseInt(deadLine) > 0) {
          corpusInterest = CorpusinterestBS.getCorpusInterest(
              overplusLoanMoney, loanRate, deadLine);
        }
      } else {
        double dOverplusLoanMoney = overplusLoanMoney.subtract(
            af1.getSumCorpus()).subtract(aheadMoney).doubleValue();
        double dMonthIngerest = shouldBackListDTO.getShouldCorpus().add(
            shouldBackListDTO.getShouldInterest()).doubleValue();
        double dLine = Math.abs(Math.log((dMonthIngerest - dOverplusLoanMoney
            * loanRate.doubleValue())
            / dMonthIngerest)
            / Math.log(1 + loanRate.doubleValue()));
        BigDecimal bLine = new BigDecimal(dLine);
        // 剩余期限四舍五入
        bLine = bLine.divide(new BigDecimal(1), 0, BigDecimal.ROUND_HALF_UP);
//        deadLine = bLine.toString();
        // 剩余期限取整
        // if(deadLine.indexOf('.',0)!=-1){
        // int i=deadLine.lastIndexOf(".");
        // deadLine = deadLine.substring(0,i);
        // }
        if (bLine.doubleValue() > 0) {
          corpusInterest = shouldBackListDTO.getShouldCorpus().add(
              shouldBackListDTO.getShouldInterest());
        }
      }
      af.setDeadLine(deadLine);
    } else if (paramValue.equals(BusiConst.PLRECOVERPARASCHG_SAMEMONTHS + "")) {// 参数值为2
      // 提前还款后剩余期限=贷款期限-（会计年月和借款人账户表PL111中发放年月之间的月数差）
//      deadLine = String.valueOf(Integer.parseInt(borrowerInfoDTO
//          .getLoanTimeLimit())
//          - temp_monthCounts);
      // Integer.parseInt(borrowerInfoDTO.getLoanTimeLimit())-(BusiTools.getDisMonths
      // (bizDate.substring(0,4)+"-"+bizDate.substring(4,6)+"-"+bizDate.substring(6,8),
      // borrowerInfoDTO.getLoanStartDate().substring(0,4)+"-"+borrowerInfoDTO.getLoanStartDate().substring(4,6)+"-"
      // +borrowerInfoDTO.getLoanStartDate().substring(6,8)))+"";
      // 提前还款后月还本息的值=（剩余本金-应还本金合-提前还款金额）*（1+月利率）新剩余期限*月利率/(1+月利率)新剩余期限-1
      overplusLoanMoney = overplusLoanMoney.subtract(af1.getSumCorpus())
          .subtract(aheadMoney);
      if (Integer.parseInt(deadLine) > 0) {
        corpusInterest = CorpusinterestBS.getCorpusInterest(overplusLoanMoney,
            loanRate, deadLine);
      }
      af.setDeadLine(deadLine);

    } else if (paramValue.equals(BusiConst.PLRECOVERPARASCHG_CHGMONTHS + "")) {// 参数值为3
      // 默认情况下提前还款后剩余期限=贷款期限-（会计年月和借款人账户表PL111中发放年月之间的月数差），可以修改该贷款期限。
//      deadLine = String.valueOf(Integer.parseInt(borrowerInfoDTO
//          .getLoanTimeLimit())
//          - temp_monthCounts);
      // deadLine = overplusLimite;
      // (BusiTools.getDisMonths
      // (bizDate.substring(0,4)+"-"+bizDate.substring(4,6)+"-"+bizDate.substring(6,8),
      // borrowerInfoDTO.getLoanStartDate().substring(0,4)+"-"+borrowerInfoDTO.getLoanStartDate().substring(4,6)+"-"
      // +borrowerInfoDTO.getLoanStartDate().substring(6,8)))+"";
      // af.setLine(paramValue);
      // isAmendLine = "1";
      // 取利率还要判断新贷款期限+已经还过的（已经还过的=PL203中最大还至年月-发放日）是否>60个月 and 原贷款期限<=60个月，
      // List
      // maxyearMonthlist=loanFlowTailDAO.queryYearMonth_LJ(borrowerInfoDTO.getLoanKouAcc());//查询最大还至年月
      // if(!maxyearMonthlist.isEmpty()){
      // ShouldBackListDTO dto=(ShouldBackListDTO)maxyearMonthlist.get(0);
      // maxYearMonth = dto.getLoanKouYearmonth();
      // }
      // if(maxYearMonth!=null && !maxYearMonth.equals("")){
      // temp_loanRepayDay=this.getEndDay(maxYearMonth,
      // borrowerInfoDTO.getLoanRepayDay());
      // temp_monthCounts=BusiTools.getDisMonths(maxYearMonth.substring(0,4)+"-"+maxYearMonth.substring(4,6)+"-"+temp_loanRepayDay,
      // loanStartDate.substring(0,4)+"-"+loanStartDate.substring(4,6)+"-"+loanStartDate.substring(6,8));
      // }
      if (Integer.parseInt(loanTimeLimit) <= 60) {
        // 是——利率取PL001中loan_rate_type=1的最新的利率
        if (Integer.parseInt(deadLine) + temp_monthCounts > 60) {
          loanRate = loanRateDAO.findMontRate(borrowerInfoDTO.getOfficeCode(),
              String.valueOf(BusiConst.PLLOANTYPE_FIVEUP));
        } else {
          // 否——计算提前还款月还本息取利率到PL001中loan_rate_type=0的最新利率
          loanRate = loanRateDAO.findMontRate(borrowerInfoDTO.getOfficeCode(),
              String.valueOf(BusiConst.PLLOANTYPE_FIVE));
        }
      } else {
        loanRate = loanRateDAO.findMontRate(borrowerInfoDTO.getOfficeCode(),
            String.valueOf(BusiConst.PLLOANTYPE_FIVEUP));
      }
      // 提前还款后月还本息的值=（剩余本金-应还本金合-提前还款金额）*（1+月利率）新剩余期限*月利率/(1+月利率)新剩余期限-1
      overplusLoanMoney = overplusLoanMoney.subtract(af1.getSumCorpus())
          .subtract(aheadMoney);
      if (Integer.parseInt(deadLine) > 0)
        corpusInterest = CorpusinterestBS.getCorpusInterest(overplusLoanMoney,
            loanRate, deadLine);
      af.setDeadLine(deadLine);
    }
    // }
    // 本次还款总本金=应还本金合计+提前还款金额
    sumCorpus = af1.getSumCorpus().add(aheadMoney);
    // 本次还款总利息=提前还款利息+应还利息合计+罚息合计
    sumInterest = aheadInterest.add(af1.getSumInterest());
    // 本次还款总金额=本次总还款本金+本次总还款利息、
    sumMoney = sumCorpus.add(sumInterest);
    // 挂账余额=借款人账户表PL111中挂账余额
    ovaerLoanRepay = borrowerInfoDTO.getOvaerLoanRepay();
    // 本次实收金额:A当挂账余额大于等于本次总还款金额：本次实收金额=0挂账发生额=本次总还款金额
    if (ovaerLoanRepay.doubleValue() >= sumMoney.doubleValue()) {
      realMoney = new BigDecimal(0.00);
      af.setOverOccurMoney(sumMoney);
    } else {// 否则B本次实收金额=本次总还款金额-挂账余额
      realMoney = sumMoney.subtract(ovaerLoanRepay);
      af.setOverOccurMoney(ovaerLoanRepay);
    }
    af.setAheadCorpus(aheadCorpus);// 提前还款本金
    af.setMinMoney(minMoney);// 提前还款最低还款金额 为了判断提前还款的本金的修改范围在大于此值并小于默认的提前还款本金
    af.setAheadInterest(aheadInterest);
    af.setDays(days);
    af.setLoanPoundageMoney(loanPoundageMoney);
    af.setCorpusInterest(corpusInterest);
    af.setSumCorpus(sumCorpus);
    af.setSumInterest(sumInterest);
    af.setSumMoney(sumMoney);
    af.setOvaerLoanRepay(ovaerLoanRepay);
    af.setRealMoney(realMoney);
    af.setIsAmendLine(isAmendLine);
    af.setDeadLine(deadLine);
    af.setDead(deadLine);
    if (af.getSumCorpus().doubleValue() == temp_overplusLoanMoney.doubleValue()) {
      af.setDeadLine("0");
    }
    if (af.getDeadLine().equals("0")) {
      af.setCorpusInterest(new BigDecimal(0.00));
    }
    // af.setAheadTypeS(BusiTools.getBusiValue(Integer.parseInt(chgType),
    // BusiConst.AHEADTYPE));
    // af.setAheadType(chgType);
    return af;
  }

  /**
   * 根据页面输入的剩余期限计算月还本息
   * 
   * @param pagination
   * @param securityInfo
   * @return
   */
  public BigDecimal getCorpusInterest(Pagination pagination,
      SecurityInfo securityInfo) throws Exception {
    BigDecimal corpusInterest = new BigDecimal(0.00);
    String bizDate = securityInfo.getUserInfo().getPlbizDate();
    String yearMonth = bizDate.substring(0, 6);// 取出会计日期中的年月
    String temp_yearMonth = yearMonth;// 还至年月
    List tempList = null;
    ShouldBackListDTO shouldBackListDTO = null;
    List borrowerList = null;
    BorrowerInfoDTO borrowerInfoDTO = null;
    String contractId = (String) pagination.getQueryCriterions().get(
        "contractId");
    String deadLine = (String) pagination.getQueryCriterions().get("deadLine");
    String cIMoney = (String) pagination.getQueryCriterions().get("cIMoney");
    String aheadMoney = (String) pagination.getQueryCriterions().get(
        "aheadMoney");
    String callbackMonth = (String) pagination.getQueryCriterions().get(
        "callbackMonth");
    String qiankuanbenjin = (String) pagination.getQueryCriterions().get(
    "qiankuanbenjin");
    if(qiankuanbenjin==null||qiankuanbenjin.equals("")){
      qiankuanbenjin="0";
    }
    String temp_month = "";
    List temp_list = (List) pagination.getQueryCriterions().get("YearList");
    if (callbackMonth != null && !callbackMonth.equals("")) {
      temp_yearMonth = callbackMonth.substring(0, 6);
      // if(!temp_yearMonth.equals(yearMonth)){
      yearMonth = callbackMonth.substring(0, 6);
      // day=callbackMonth.substring(6,8);
      // }
    }
    // else{
    // callbackMonth=temp_month;
    // }
    yearMonth = BusiTools.addMonth(yearMonth, 1);
    tempList = restoreLoanDAO.queryRestoreLoanInfoByContractId_LJ(contractId,
        yearMonth);
    if (!tempList.isEmpty()) {
      shouldBackListDTO = (ShouldBackListDTO) tempList.get(0);
    } else {
      tempList = restoreLoanDAO.queryRestoreLoanInfoByContractId_LJ(contractId,
          temp_yearMonth);
      if (!tempList.isEmpty()) {
        shouldBackListDTO = (ShouldBackListDTO) tempList.get(0);
      }
    }
    // 从PL110及PL111取信息
    borrowerList = borrowerAccDAO
        .queryBorrowerAccInfoByLoanKouAcc_LJ(contractId);
    if (!borrowerList.isEmpty()) {
      borrowerInfoDTO = (BorrowerInfoDTO) borrowerList.get(0);
    }
    BigDecimal overplusLoanMoney = new BigDecimal(0.00);// 剩余本金
    overplusLoanMoney = borrowerInfoDTO.getOverplusLoanMoney();
    BigDecimal loanRate = shouldBackListDTO.getLoanRate();// 还款表每月利率
    overplusLoanMoney = overplusLoanMoney.subtract(new BigDecimal(qiankuanbenjin)).subtract(new BigDecimal(cIMoney));
    // 取利率还要判断新贷款期限+已经还过的（已经还过的=PL203中最大还至年月-发放日）是否>60个月 and 原贷款期限<=60个月，
    String loanStartDate = borrowerInfoDTO.getLoanStartDate();// 发放日
    // List
    // maxyearMonthlist=loanFlowTailDAO.queryYearMonth_LJ(borrowerInfoDTO.getLoanKouAcc());
    String loanTimeLimit = borrowerInfoDTO.getLoanTimeLimit();// 原贷款期限
    // String maxYearMonth = "";//PL203中最大还至年月
    // int temp_monthCounts=0;
    // if(!maxyearMonthlist.isEmpty()){
    // ShouldBackListDTO dto=(ShouldBackListDTO)maxyearMonthlist.get(0);
    // maxYearMonth = dto.getLoanKouYearmonth();
    // }
    // if(maxYearMonth!=null && !maxYearMonth.equals("")){
    // loanRepayDay=this.getEndDay(maxYearMonth,
    // borrowerInfoDTO.getLoanRepayDay());
    // temp_monthCounts=BusiTools.getDisMonths(maxYearMonth.substring(0,4)+"-"+maxYearMonth.substring(4,6)+"-"+loanRepayDay,
    // loanStartDate.substring(0,4)+"-"+loanStartDate.substring(4,6)+"-"+loanStartDate.substring(6,8));
    // }
    // 用还款日和会计日做比较，1、还款日大于会计日 占用天数=(还至年月+1+会计日)-还至日。
    // 2、还款日小于等于会计日 占用天数=还至年月+会计日-还至日
    // 在这里取还至日是为了计算还过的期限。
    // 如果默认的还至日为空则取下拉框中第一条，判断还款日和会计日的大小，如果还款日大于
    String loanRepayDayBiz = this.getEndDay(bizDate.substring(0, 6),
        borrowerInfoDTO.getLoanRepayDay());
    String day = bizDate.substring(6, 8);
    int temp_monthCounts = 0;
    if (callbackMonth != null && !callbackMonth.equals("")) {
      String yearMonths = callbackMonth.substring(0, 6);
      if (Integer.parseInt(loanRepayDayBiz) > Integer.parseInt(day)) {
        String temp_yearMonths = BusiTools.addMonth(callbackMonth.substring(0,
            6), 1);
        temp_monthCounts = BusiTools.getDisMonths(temp_yearMonths.substring(0,
            4)
            + "-" + temp_yearMonths.substring(4, 6) + "-" + day, loanStartDate
            .substring(0, 4)
            + "-"
            + loanStartDate.substring(4, 6)
            + "-"
            + loanStartDate.substring(6, 8));
      } else if (Integer.parseInt(loanRepayDayBiz) <= Integer.parseInt(day)) {
        temp_monthCounts = BusiTools.getDisMonths(yearMonths.substring(0, 4)
            + "-" + yearMonths.substring(4, 6) + "-" + day, loanStartDate
            .substring(0, 4)
            + "-"
            + loanStartDate.substring(4, 6)
            + "-"
            + loanStartDate.substring(6, 8));
      }
    } else {
      // 还至日为空
      if (!temp_list.isEmpty()) {
        ShouldBackListDTO dto = (ShouldBackListDTO) temp_list.get(0);
        temp_month = dto.getLoanKouYearmonth();
        if (Integer.parseInt(loanRepayDayBiz) <= Integer.parseInt(day)) {
          String temp_yearMonths = BusiTools.addMonth(temp_month, -1);
          temp_monthCounts = BusiTools.getDisMonths(temp_yearMonths.substring(
              0, 4)
              + "-" + temp_yearMonths.substring(4, 6) + "-" + day,
              loanStartDate.substring(0, 4) + "-"
                  + loanStartDate.substring(4, 6) + "-"
                  + loanStartDate.substring(6, 8));
        } else {
          temp_monthCounts = BusiTools.getDisMonths(temp_month.substring(0, 4)
              + "-" + temp_month.substring(4, 6) + "-" + day, loanStartDate
              .substring(0, 4)
              + "-"
              + loanStartDate.substring(4, 6)
              + "-"
              + loanStartDate.substring(6, 8));
        }
      } else {
        // 取下一年1月
        String year = bizDate.substring(0, 4);
        int iYear = Integer.parseInt(year) + 1;
        String next_yearMonth = String.valueOf(iYear) + "01";
        if (Integer.parseInt(loanRepayDayBiz) <= Integer.parseInt(day)) {
          String temp_yearMonths = BusiTools.addMonth(next_yearMonth, -1);
          temp_monthCounts = BusiTools.getDisMonths(temp_yearMonths.substring(
              0, 4)
              + "-" + temp_yearMonths.substring(4, 6) + "-" + day,
              loanStartDate.substring(0, 4) + "-"
                  + loanStartDate.substring(4, 6) + "-"
                  + loanStartDate.substring(6, 8));
        } else if (Integer.parseInt(loanRepayDayBiz) > Integer.parseInt(day)) {
          temp_monthCounts = BusiTools.getDisMonths(next_yearMonth.substring(0,
              4)
              + "-" + next_yearMonth.substring(4, 6) + "-" + day, loanStartDate
              .substring(0, 4)
              + "-"
              + loanStartDate.substring(4, 6)
              + "-"
              + loanStartDate.substring(6, 8));

        }

      }
    }
    BorrowerAcc borrowerAcc=new BorrowerAcc();
    borrowerAcc=borrowerAccDAO.queryById(contractId);
    if (Integer.parseInt(loanTimeLimit)-Integer.parseInt(borrowerAcc.getOverplusLimite())+Integer.parseInt(deadLine) <= 60) {
      // 是——利率取PL001中loan_rate_type=1的最新的利率
//      if (Integer.parseInt(deadLine) + temp_monthCounts > 60) {
//        loanRate = loanRateDAO.findMontRate(borrowerInfoDTO.getOfficeCode(),
//            String.valueOf(BusiConst.PLLOANTYPE_FIVEUP));
//      } else {
        // 否——计算提前还款月还本息取利率到PL001中loan_rate_type=0的最新利率
        loanRate = loanRateDAO.findMontRate(borrowerInfoDTO.getOfficeCode(),
            String.valueOf(BusiConst.PLLOANTYPE_FIVE));
//      }
    } else {
      loanRate = loanRateDAO.findMontRate(borrowerInfoDTO.getOfficeCode(),
          String.valueOf(BusiConst.PLLOANTYPE_FIVEUP));
    }
    // 提前还款后月还本息的值=（剩余本金-应还本金合-提前还款金额）*（1+月利率）新剩余期限*月利率/(1+月利率)新剩余期限-1
    corpusInterest = CorpusinterestBS.getCorpusInterest(overplusLoanMoney,
        loanRate, deadLine);
    return corpusInterest;
  }

  /**
   * 判断是否允许清还
   * 
   * @param borrowerInfoDTO
   * @param bizDate
   * @param af1
   * @return
   * @throws Exception
   */
  // 一次性清还
  public LoancallbackTaAF fullAheadInfo(BorrowerInfoDTO borrowerInfoDTO,
      String bizDate, LoancallbackTaAF af1, String yearMonth) throws Exception {
    LoancallbackTaAF af = new LoancallbackTaAF();
    String paramType = "B";// 参数类型
    String paramValue = "";// 参数值
    String paramExplain = "";// 参数说明
    String loanStartDate = borrowerInfoDTO.getLoanStartDate();// 发放日期
    Integer loanBankId = borrowerInfoDTO.getLoanBankId();// 放款银行
    String contractId = borrowerInfoDTO.getContractId();// 合同编号
    String loanRepayDay = borrowerInfoDTO.getLoanRepayDay();// 还款日
    List shouldBackList = null;
    String day = bizDate.substring(6, 8);
    String endDate = "";// 还至年日
    String loanRepayDayBiz = this.getEndDay(bizDate.substring(0, 6),
        loanRepayDay);// 会计日期的还款日
    List shouldBackList1 = af1.getShouldBackList();
    if (!shouldBackList1.isEmpty()) {
      ShouldBackListDTO dto = (ShouldBackListDTO) shouldBackList1
          .get(shouldBackList1.size() - 1);
      endDate = dto.getLoanKouYearmonth().substring(0, 6);// 用来设置还至年月下拉框的显示值
    }
    if (endDate == "") {
      List monthYearList = af1.getMonthYearList();
      if (!monthYearList.isEmpty()) {
        ShouldBackListDTO dto = (ShouldBackListDTO) monthYearList.get(0);
        endDate = dto.getLoanKouYearmonth().substring(0, 6);
        if (Integer.parseInt(loanRepayDayBiz) <= Integer.parseInt(day)) {
          endDate = BusiTools.addMonth(endDate, -1);
        }
      } else {
        // 取下一年1月
        String year = bizDate.substring(0, 4);
        int iYear = Integer.parseInt(year) + 1;
        String temp_yearMonth = String.valueOf(iYear) + "01";
        temp_yearMonth = temp_yearMonth.substring(0, 6);
        if (Integer.parseInt(loanRepayDayBiz) <= Integer.parseInt(day)) {
          endDate = BusiTools.addMonth(temp_yearMonth, -1);
        } else {
          endDate = temp_yearMonth;
        }
      }
    } else if (Integer.parseInt(loanRepayDayBiz) > Integer.parseInt(day)) {
      endDate = BusiTools.addMonth(endDate, 1);
    }
    // 超前还款未还清不允许提前
    String temp_loanRepayDay = this.getEndDay(bizDate.substring(0, 6),
        loanRepayDay);
    if (Integer.parseInt(day) < Integer.parseInt(temp_loanRepayDay)) {
      // 会计日小于还款日(大于等于)
      shouldBackList = restoreLoanDAO.queryRestoreLoanListByContractId_LJD(
          contractId, endDate.substring(0, 6));
    } else {
      // 会计日大于等于还款日
      shouldBackList = restoreLoanDAO.queryRestoreLoanListByContractId_LJE(
          contractId, endDate.substring(0, 6));
    }
    if (!shouldBackList.isEmpty()) {
      throw new BusinessException("超前还款未还清，不允许提前还款！");
    } else {
      // 判断该贷款号在合同贷款参数PL004表中参数序号=3的参数值是否=1（不允许一次性清还）
      paramValue = loanContractParaDAO.queryParamValue_wsh(loanBankId,
          paramType, "3", contractId);
      if (paramValue.equals(BusiConst.PLCOMMONSTATUS_2_NOTALLOW + "")) {
        // 取出该参数对应的参数说明PARAM_EXPLAIN，判断（会计日期-PL111表中发放日期LOAN_START_DATE）的月数是否大于等于该值
        paramExplain = loanContractParaDAO.queryParamExplain_wsh(loanBankId,
            paramType, "3", contractId);

        // int temp_monthCounts=BusiTools.getDisMonths(loanStartDate, bizDate);
        int temp_monthCounts = BusiTools.getDisMonths(bizDate.substring(0, 4)
            + "-" + bizDate.substring(4, 6) + "-" + bizDate.substring(6, 8),
            loanStartDate.substring(0, 4) + "-" + loanStartDate.substring(4, 6)
                + "-" + loanStartDate.substring(6, 8));
        if (temp_monthCounts >= Integer.parseInt(paramExplain)) {
          // 取出该贷款号在合同贷款参数PL004表中参数序号=5的参数说明，判断该贷款账在贷款流水账头表PL202中(会计年内1-12)提前还款的次数是否小于该值
          paramExplain = loanContractParaDAO.queryParamExplain_wsh(loanBankId,
              paramType, "5", contractId);
          // 会计年内提前还款次数
          int aheadCounts = loanFlowTailDAO.queryCallbackAheadCounts_LJ(
              contractId, bizDate.substring(0, 4)).intValue();
          if (aheadCounts < Integer.parseInt(paramExplain)) {
            // 取出该贷款号在合同贷款参数PL004表中参数序号=6的参数说明，判断该贷款账在贷款流水账头表PL202中是提前还款的次数是否小于该值
            paramExplain = loanContractParaDAO.queryParamExplain_wsh(loanBankId,
                paramType, "6", contractId);
            // 贷款期限内提前次数
            int lineCounts = loanFlowTailDAO.queryCallbackAheadCounts_LJ(
                contractId, null).intValue();
            ;
            if (lineCounts < Integer.parseInt(paramExplain)) {
              af = findFullAheadInfo(borrowerInfoDTO, bizDate, af1, yearMonth);
            } else {
              throw new BusinessException("不允许一次性结清！");
            }
          } else {
            throw new BusinessException("不允许一次性结清！");
          }
        } else {
          throw new BusinessException("不允许一次性结清！");
        }
      } else {// 允许再判断
        // 取出该贷款号在合同贷款参数PL004表中参数序号=5的参数说明，判断该贷款账在贷款流水账头表PL202中(会计年内1-12)提前还款的次数是否小于该值
        paramExplain = loanContractParaDAO.queryParamExplain_wsh(loanBankId,
            paramType, "5", contractId);
        // 会计年内提前还款次数
        int aheadCounts = loanFlowTailDAO.queryCallbackAheadCounts_LJ(
            contractId, bizDate).intValue();
        if (aheadCounts < Integer.parseInt(paramExplain)) {
          // 取出该贷款号在合同贷款参数PL004表中参数序号=6的参数说明，判断该贷款账在贷款流水账头表PL202中是提前还款的次数是否小于该值
          paramExplain = loanContractParaDAO.queryParamExplain_wsh(loanBankId,
              paramType, "6", contractId);
          // 贷款期限内提前次数
          int lineCounts = loanFlowTailDAO.queryCallbackAheadCounts_LJ(
              contractId, null).intValue();
          ;
          if (lineCounts < Integer.parseInt(paramExplain)) {
            af = findFullAheadInfo(borrowerInfoDTO, bizDate, af1, yearMonth);
          } else {
            throw new BusinessException("不允许一次性结清！");
          }
        } else {
          throw new BusinessException("不允许一次性结清！");
        }
      }
    }
    return af;
  }

  /**
   * 一次性清还应还信息
   * 
   * @param borrowerInfoDTO
   * @param bizDate
   * @param af1
   * @return
   * @throws Exception
   */
  public LoancallbackTaAF findFullAheadInfo(BorrowerInfoDTO borrowerInfoDTO,
      String bizDate, LoancallbackTaAF af1, String yearMonths) throws Exception {
    LoancallbackTaAF af = new LoancallbackTaAF();
    ShouldBackListDTO shouldBackListDTO = new ShouldBackListDTO();
    String paramExplain = "";// 参数说明
    String paramType = "B";// 参数类型
    String paramValue = "";// 参数值
    Integer loanBankId = borrowerInfoDTO.getLoanBankId();// 放款银行
    BigDecimal aheadCorpus = new BigDecimal(0.00);// 提前还款本金
    BigDecimal overplusLoanMoney = borrowerInfoDTO.getOverplusLoanMoney();// 剩余本金
    String loanRepayDay = borrowerInfoDTO.getLoanRepayDay();// 还款日
    BigDecimal aheadInterest = new BigDecimal(0.00);// 提前还款利息
    BigDecimal loanPoundageMoney = new BigDecimal(0.00);// 手续费金额
    BigDecimal sumCorpus = new BigDecimal(0.00);// 总还款本金
    BigDecimal sumInterest = new BigDecimal(0.00);// 本次总还款利息
    BigDecimal sumMoney = new BigDecimal(0.00);// 总还金额
    BigDecimal ovaerLoanRepay = new BigDecimal(0.00);// 挂账余额
    BigDecimal realMoney = new BigDecimal(0.00);// 实收金额
    String days = "";// 占用天数
    String contractId = borrowerInfoDTO.getContractId();// 合同编号
    BigDecimal loanRate = new BigDecimal(0.00);// 月利率
    List tempList = null;
    // 提前还款本金默认情况下=剩余本金-应还本金合计（不可以修改）
    aheadCorpus = overplusLoanMoney.subtract(af1.getSumCorpus());
    String yearMonth = bizDate.substring(0, 6);// 取出会计日期中的年月
    String year = yearMonth.substring(0, 4);
    String month = yearMonth.substring(4, 6);
    String day = bizDate.substring(6, bizDate.length());// 取出会计日期中的日
    String callbackMonth = yearMonths;// 还至年月
    String loanRepayDay1 = "";
    String loanRepayDayBiz = this.getEndDay(yearMonth, loanRepayDay);// 会计日期的还款日
    List shouldBackList = af1.getShouldBackList();
    String endDate = "";// 还至年日
    if (!shouldBackList.isEmpty()) {
      ShouldBackListDTO dto = (ShouldBackListDTO) shouldBackList
          .get(shouldBackList.size() - 1);
      endDate = dto.getLoanKouYearmonth();// 用来设置还至年月下拉框的显示值
    }
    if (yearMonths != null && !yearMonths.equals("")) {
      // 占用天数
      // 用还款日和会计日做比较，1、还款日大于会计日 占用天数=(还至年月+1+会计日)-还至日。
      // 2、还款日小于等于会计日 占用天数=还至年月+会计日-还至日
      loanRepayDay1 = this.getEndDay(yearMonths, loanRepayDay);
      if (Integer.parseInt(loanRepayDayBiz) > Integer.parseInt(day)) {
        String temp_yearMonths = BusiTools.addMonth(yearMonths, 1);
        days = BusiTools.minusDate(temp_yearMonths.substring(0, 4) + "-"
            + temp_yearMonths.substring(4, 6) + "-" + day, yearMonths
            .substring(0, 4)
            + "-" + yearMonths.substring(4, 6) + "-" + loanRepayDay1)
            + "";
      } else if (Integer.parseInt(loanRepayDayBiz) <= Integer.parseInt(day)) {
        loanRepayDay1 = this.getEndDay(yearMonths, loanRepayDay);
        days = BusiTools.minusDate(yearMonths.substring(0, 4) + "-"
            + yearMonths.substring(4, 6) + "-" + day, yearMonths
            .substring(0, 4)
            + "-" + yearMonths.substring(4, 6) + "-" + loanRepayDay1)
            + "";
      }
    } else {
      // 还至日为空
      List yearList = af1.getMonthYearList();
      if (!yearList.isEmpty()) {
        ShouldBackListDTO dto = (ShouldBackListDTO) yearList.get(0);
        yearMonths = dto.getLoanKouYearmonth();
        if (Integer.parseInt(loanRepayDayBiz) <= Integer.parseInt(day)) {
          String temp_yearMonths = BusiTools.addMonth(yearMonths, -1);
          loanRepayDay1 = this.getEndDay(temp_yearMonths, loanRepayDay);
          days = BusiTools.minusDate(temp_yearMonths.substring(0, 4) + "-"
              + temp_yearMonths.substring(4, 6) + "-" + day, temp_yearMonths
              .substring(0, 4)
              + "-" + temp_yearMonths.substring(4, 6) + "-" + loanRepayDay1)
              + "";
        } else {
          loanRepayDay1 = this.getEndDay(yearMonths, loanRepayDay);
          String temp_month = BusiTools.addMonth(yearMonths, -1);
          days = BusiTools.minusDate(yearMonths.substring(0, 4) + "-"
              + yearMonths.substring(4, 6) + "-" + day, temp_month.substring(0,
              4)
              + "-" + temp_month.substring(4, 6) + "-" + loanRepayDay1)
              + "";
        }
      } else {
        // 取下一年1月
        String bizyear = bizDate.substring(0, 4);
        int iYear = Integer.parseInt(bizyear) + 1;
        String temp_yearMonth = iYear + "01";
        if (Integer.parseInt(loanRepayDayBiz) <= Integer.parseInt(day)) {
          String temp_yearMonths = BusiTools.addMonth(temp_yearMonth, -1);
          loanRepayDay1 = this.getEndDay(temp_yearMonths, loanRepayDay);
          days = BusiTools.minusDate(temp_yearMonths.substring(0, 4) + "-"
              + temp_yearMonths.substring(4, 6) + "-" + day, temp_yearMonths
              .substring(0, 4)
              + "-" + temp_yearMonths.substring(4, 6) + "-" + loanRepayDay1)
              + "";
        } else if (Integer.parseInt(loanRepayDayBiz) > Integer.parseInt(day)) {
          loanRepayDay1 = this.getEndDay(bizDate, loanRepayDay);
          days = BusiTools.minusDate(temp_yearMonth.substring(0, 4) + "-"
              + temp_yearMonth.substring(4, 6) + "-" + day, bizyear + "-"
              + "12" + "-" + loanRepayDay1)
              + "";
        }
      }
    }
    // if(Integer.parseInt(bizDate)<Integer.parseInt(endDate+loanRepayDay1)){
    // days="0";
    // }else{
    // if(Integer.parseInt(day)<Integer.parseInt(loanRepayDay1)){
    // String temp_yearMonths=BusiTools.addMonth(yearMonth, -1);
    // String temp_loanRepayDay=this.getEndDay(temp_yearMonths, loanRepayDay);
    // days =
    // BusiTools.minusDate(temp_bizDate,temp_yearMonths.substring(0,4)+"-"+temp_yearMonths.substring(4,6)+"-"+temp_loanRepayDay)+"";
    // }else{
    // temp_date = year + "-"+ month + "-" + loanRepayDay1;
    // days = BusiTools.minusDate(temp_bizDate, temp_date)+"";
    // }
    // }
    if (yearMonths != null && !yearMonths.equals("")) {
      yearMonths = BusiTools.addMonth(yearMonths, 1);
    } else {
      yearMonths = bizDate.substring(0, 6);
    }
    tempList = restoreLoanDAO.queryRestoreLoanInfoByContractId_LJ(contractId,
        yearMonths);
    if (!tempList.isEmpty()) {
      shouldBackListDTO = (ShouldBackListDTO) tempList.get(0);
    } else {
      tempList = restoreLoanDAO.queryRestoreLoanInfoByContractId_LJ(contractId,
          callbackMonth);
      if (!tempList.isEmpty()) {
        shouldBackListDTO = (ShouldBackListDTO) tempList.get(0);
      }
    }
    // monthInterest = shouldBackListDTO.getLoanRate();//还款表每月利率
    String loanTimeLimit = borrowerInfoDTO.getLoanTimeLimit();// 原贷款期限
    // 是——利率取PL001中loan_rate_type=1的最新的利率
    if (Integer.parseInt(loanTimeLimit) > 60) {
      loanRate = loanRateDAO.findMontRate(borrowerInfoDTO.getOfficeCode(),
          String.valueOf(BusiConst.PLLOANTYPE_FIVEUP));
    } else {
      // 否——计算提前还款月还本息取利率到PL001中loan_rate_type=0的最新利率
      loanRate = loanRateDAO.findMontRate(borrowerInfoDTO.getOfficeCode(),
          String.valueOf(BusiConst.PLLOANTYPE_FIVE));
    }
    // 提前还款利息=提前还款本金*占用天数*月利率/30
    aheadInterest = aheadCorpus.multiply(new BigDecimal(days)).multiply(
        loanRate).divide(new BigDecimal(30), 2, BigDecimal.ROUND_HALF_UP);
    // 手续费金额分三种情况（判断该贷款号在合同贷款参数PL004表中参数序号=6的参数值）
    paramValue = loanContractParaDAO.queryParamValue_wsh(loanBankId, paramType,
        "7", contractId);
    // 参数值为1:手续费=提前还款本金*参数说明/100
    if (paramValue.equals(BusiConst.PLPREPAYMENTFEES_PREPAYMENT + "")) {
      paramExplain = loanContractParaDAO.queryParamExplain_wsh(loanBankId,
          paramType, "7", contractId);
      loanPoundageMoney = aheadCorpus.multiply(new BigDecimal(paramExplain))
          .divide(new BigDecimal(100), 2, BigDecimal.ROUND_HALF_UP);
    } else if (paramValue.equals(BusiConst.PLPREPAYMENTFEES_PAYMENT + "")) {// 参数值为2:手续费=参数说明
      paramExplain = loanContractParaDAO.queryParamExplain_wsh(loanBankId,
          paramType, "7", contractId);
      loanPoundageMoney = new BigDecimal(paramExplain);
    } else {// 参数值为3:手续费=0
      loanPoundageMoney = new BigDecimal(0.00);
    }
    // 本次还款总本金=应还本金合计+提前还款金额
    sumCorpus = af1.getSumCorpus().add(aheadCorpus);
    // 本次还款总利息=提前还款利息+应还利息合计+罚息合计
    sumInterest = af1.getSumInterest().add(aheadInterest);
    // 本次还款总金额=本次总还款本金+本次总还款利息、
    sumMoney = sumCorpus.add(sumInterest);
    // 挂账余额=借款人账户表PL111中挂账余额
    ovaerLoanRepay = borrowerInfoDTO.getOvaerLoanRepay();
    // 本次实收金额:A当挂账余额大于等于本次总还款金额：本次实收金额=0挂账发生额=本次总还款金额
    if (ovaerLoanRepay.doubleValue() >= sumMoney.doubleValue()) {
      realMoney = new BigDecimal(0.00);
      af.setOverOccurMoney(sumMoney);
    } else {// 否则B本次实收金额=本次总还款金额-挂账余额
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
   * 应还信息列表
   * 
   * @param shouldBackList
   * @param borrowerInfoDTO
   * @param bizDate
   * @return
   * @throws Exception
   */
  public LoancallbackTaAF findCallbackList(List shouldBackList,
      BorrowerInfoDTO borrowerInfoDTO, String bizDate) throws Exception {
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
    String paramExplain = "";// 参数说明
    String allowdays = "";// 宽限天数
    BigDecimal temp_interest = new BigDecimal(0.00);// 临时罚息
    String yearMonth = bizDate.substring(0, 6);// 取出会计日期中的年月
    String day = bizDate.substring(6, bizDate.length());// 取出会计日期中的日
    String temp_bizDate = yearMonth.substring(0, 4) + "-"
        + yearMonth.substring(4, 6) + "-" + day;// 用来判断列表中的还款类型所转换的会计日期
    loanRepayDay = borrowerInfoDTO.getLoanRepayDay();

    if (borrowerInfoDTO.getLoanMode().equals("一年期")
        || borrowerInfoDTO.getLoanMode().equals("两年期")) {
      // 整年期还款
      // 从PL003中查询宽限天数内是否计息
      isRate = loanBankParaDAO.queryParamValue_LJ(loanBankId, paramType, "12");
      // 从PL003中取计算罚息方式（按按罚息日利率、按合同日利率、按额每日XX元计算）
      interestMode = loanBankParaDAO.queryParamValue_LJ(loanBankId, paramType,
          "11");
      // 从PL003中取对应的参数说明
      paramExplain = loanBankParaDAO.queryParamExplain_LJ(loanBankId,
          paramType, "11");
      // PL003中查询宽限天数
      allowdays = loanBankParaDAO.queryParamExplain_LJ(loanBankId, paramType,
          "12");
    } else {
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
    }
    if (!shouldBackList.isEmpty()) {
      for (int i = 0; i < shouldBackList.size(); i++) {
        ShouldBackListDTO dto1 = (ShouldBackListDTO) shouldBackList.get(i);
        ShouldBackListDTO dto2 = new ShouldBackListDTO();
        String loanRepayDay1 = this.getEndDay(dto1.getLoanKouYearmonth(),
            loanRepayDay);
        dto2.setLoanKouYearmonth(dto1.getLoanKouYearmonth());// 显示还款年月
        String temp_date = dto1.getLoanKouYearmonth().substring(0, 4) + "-"
            + dto1.getLoanKouYearmonth().substring(4, 6) + "-" + loanRepayDay1;
        // 逾期天数
        int days = BusiTools.minusDate(temp_bizDate, temp_date);
        
        if (days - Integer.parseInt(allowdays) > 0) {
          dto2.setLoanKouType("逾期");// 显示还款类型
        } else {
          dto2.setLoanKouType("正常");
        }
        dto2.setShouldCorpus(dto1.getShouldCorpus().subtract(
            dto1.getRealCorpus()));// 显示应还本金
        dto2.setShouldInterest(dto1.getShouldInterest().subtract(
            dto1.getRealInterest()));// 显示应还利息
        dto2.setDays(days + "");// 显示逾期天数
        
        if (days > Integer.parseInt(allowdays))
          dto2.setDays(days + "");
        else
          dto2.setDays("0");
        // 罚息卡住了。
        // 足条计算每月产生的罚息
        // 判断还款表(应还本金-本金+应还利息-利息)是否=0
        if (dto1.getShouldCorpus().subtract(dto1.getRealCorpus()).add(
            dto1.getShouldInterest().subtract(dto1.getRealInterest()))
            .doubleValue() == 0) {
          dto2.setPunishInterest(dto1.getPunishInterest());
        } else if (days <= 0) {
          dto2.setPunishInterest(dto1.getPunishInterest());
        } else {
          // 不等于0判断是否在宽限天数内计息
          // 条件银行贷款参数PL003中类型为：A:还款参数，并且参数序号PARAM_NUM=5的参数值PARAM_VALUE是否=0(宽限天数内计息)
          if (isRate.equals(BusiConst.YES + "")) {// 计息
            temp_interest = PunishInterestBS.getTempInterestByYearMonth(
                interestMode, bizDate, dto1.getLoanKouYearmonth(),
                loanRepayDay1, dto1.getShouldCorpus(), dto1.getRealCorpus(),
                dto1.getShouldInterest(), dto1.getRealInterest(),
                paramExplain, dto1.getLoanRate());
            // 逐条判断PL201中的记账日期是否小于等于还款日
//            accountDate = dto1.getBizDate();
            // String temp_day = accountDate.substring(6, 8);// 记账日期的日
            // if (Integer.parseInt(temp_day) <= Integer.parseInt(loanRepayDay))
            // {// 小于等于还款日
//            if (accountDate == null || accountDate.equals("")) {// 判断是否有记账日期：没有：减还款年月；有：减记账日期
//              temp_interest = PunishInterestBS.getTempInterestByYearMonth(
//                  interestMode, bizDate, dto1.getLoanKouYearmonth(),
//                  loanRepayDay1, dto1.getShouldCorpus(), dto1.getRealCorpus(),
//                  dto1.getShouldInterest(), dto1.getRealInterest(),
//                  paramExplain, dto1.getLoanRate());
//            } else if (Integer.parseInt(accountDate) <= Integer.parseInt(dto1
//                .getLoanKouYearmonth()
//                + loanRepayDay1)) {// 小于等于还款日
//              temp_interest = PunishInterestBS.getTempInterestByYearMonth(
//                  interestMode, bizDate, dto1.getLoanKouYearmonth(),
//                  loanRepayDay1, dto1.getShouldCorpus(), dto1.getRealCorpus(),
//                  dto1.getShouldInterest(), dto1.getRealInterest(),
//                  paramExplain, dto1.getLoanRate());
//            } else {// 大于还款日
//              temp_interest = PunishInterestBS.getTempInterestByClearDate(
//                  interestMode, bizDate, dto1.getBizDate(), dto1
//                      .getShouldCorpus(), dto1.getRealCorpus(), dto1
//                      .getShouldInterest(), dto1.getRealInterest(),
//                  paramExplain, dto1.getLoanRate());
//              temp_interest = temp_interest.add(dto1.getPunishInterest())
//                  .divide(new BigDecimal(1), 2, BigDecimal.ROUND_HALF_UP);// 加还款表中未还罚息
//            }
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
              // String temp_day = dto1.getBizDate().substring(6, 8);// 记账日期的日
              // 还款年月日+宽限天数后生成的年月日
              String temp_loanRepayDay = "";
              temp_loanRepayDay = BusiTools.addDay(dto1.getLoanKouYearmonth()
                  + loanRepayDay1, Integer.parseInt(allowdays));
              // temp_loanRepayDay = temp_loanRepayDay.substring(6, 8);
              // if (Integer.parseInt(temp_day) <=
              // Integer.parseInt(temp_loanRepayDay)) {// 小于等于还款日+宽限天数
              if (dto1.getBizDate() == null || dto1.getBizDate().equals("")) {// 判断是否有记账日期
                temp_interest = PunishInterestBS.getTempInterestByAllowdays(
                    interestMode, bizDate, dto1.getLoanKouYearmonth(),
                    loanRepayDay1, dto1.getShouldCorpus(),
                    dto1.getRealCorpus(), dto1.getShouldInterest(), dto1
                        .getRealInterest(), paramExplain, allowdays, dto1
                        .getLoanRate());
              } else if (Integer.parseInt(dto1.getBizDate()) <= Integer
                  .parseInt(temp_loanRepayDay)) {// 小于等于还款日+宽限天数
                temp_interest = PunishInterestBS.getTempInterestByAllowdays(
                    interestMode, bizDate, dto1.getLoanKouYearmonth(),
                    loanRepayDay1, dto1.getShouldCorpus(),
                    dto1.getRealCorpus(), dto1.getShouldInterest(), dto1
                        .getRealInterest(), paramExplain, allowdays, dto1
                        .getLoanRate());
              } else {// 大于还款日+宽限天数
                temp_interest = PunishInterestBS.getTempInterestByClearDate(
                    interestMode, bizDate, dto1.getBizDate(), dto1
                        .getShouldCorpus(), dto1.getRealCorpus(), dto1
                        .getShouldInterest(), dto1.getRealInterest(),
                    paramExplain, dto1.getLoanRate());
                // 加还款表中未还罚息
                temp_interest = temp_interest.add(dto1.getPunishInterest())
                    .divide(new BigDecimal(1), 2, BigDecimal.ROUND_HALF_UP);
              }
              dto2.setPunishInterest(temp_interest);
              dto2.setTempInterest(temp_interest);
            }
          }
        }
        dto2.setCiMoney(dto2.getShouldCorpus().add(dto2.getShouldInterest())
            .add(dto2.getPunishInterest()));// 显示应还本息合计
        dto2.setLoanRate(dto1.getLoanRate());// 显示每月利率
        dto2.setShow_loanRate(dto1.getShow_loanRate());
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

  /**
   * 选择还至年月进行查询
   */
  public LoancallbackTaAF findShouldLoancallbackInfo(Pagination pagination)
      throws Exception {
    LoancallbackTaAF af = new LoancallbackTaAF();
    String month = (String) pagination.getQueryCriterions()
        .get("callbackMonth");
    String ovaerLoanRepay = (String) pagination.getQueryCriterions().get(
        "ovaerLoanRepay");// 挂账余额
    // String pldebit =
    // (String)pagination.getQueryCriterions().get("pldebit");// 扣款方式
    String contractId = (String) pagination.getQueryCriterions().get(
        "contractId");
    List list = (List) pagination.getQueryCriterions().get("shouldBackList");
    List returnList = new ArrayList();
    BigDecimal sumCorpus = new BigDecimal(0.00);// 总还款本金
    BigDecimal sumInterest = new BigDecimal(0.00);// 本次总还款利息
    if (!list.isEmpty()) {
      for (int i = 0; i < list.size(); i++) {
        ShouldBackListDTO dto = (ShouldBackListDTO) list.get(i);
        if (Integer.parseInt(dto.getLoanKouYearmonth()) <= Integer
            .parseInt(month)) {
          sumCorpus = sumCorpus.add(dto.getShouldCorpus());
          sumInterest = sumInterest.add(dto.getShouldInterest().add(
              dto.getPunishInterest()));
          returnList.add(dto);
        }
      }
    }
    List borrowerList = new ArrayList();// 账户信息
    BorrowerInfoDTO borrowerInfoDTO = new BorrowerInfoDTO();
    // 从PL110及PL111取信息
    borrowerList = borrowerAccDAO
        .queryBorrowerAccInfoByLoanKouAcc_LJ(contractId);
    if (!borrowerList.isEmpty()) {
      borrowerInfoDTO = (BorrowerInfoDTO) borrowerList.get(0);
    }
    borrowerInfoDTO.setCardKind(BusiTools.getBusiValue(Integer
        .parseInt(borrowerInfoDTO.getCardKind()), BusiConst.DOCUMENTSSTATE));
    borrowerInfoDTO.setLoanMode(BusiTools.getBusiValue(Integer
        .parseInt(borrowerInfoDTO.getLoanMode()), BusiConst.PLRECOVERTYPE));
    af.setShouldBackList(returnList);
    af.setSumCorpus(sumCorpus);
    af.setSumInterest(sumInterest);
    af.setSumMoney(af.getSumCorpus().add(af.getSumInterest()));
    af.setOvaerLoanRepay(new BigDecimal(ovaerLoanRepay));
    // A.当挂账余额大于等于本次总还款金额：本次实收金额=0，挂账发生额=本次总还款金额
    if (Double.parseDouble(ovaerLoanRepay) >= af.getSumMoney().doubleValue()) {
      af.setRealMoney(new BigDecimal(0.00));
      af.setOverOccurMoney(af.getSumMoney());
    } else {
      // 当挂账余额小于本次总还款金额：本次实收金额=本次总还款金额-挂账余额 （如果PL003中类型为：还款参数，序号为1中的参数值=2：全额扣款，
      // 本次实收金额可以修改，但要大于等于0小于等于默认显示的实收金额），挂账发生额=挂账余额
      af
          .setRealMoney(af.getSumMoney().subtract(
              new BigDecimal(ovaerLoanRepay)));
      af.setOverOccurMoney(new BigDecimal(ovaerLoanRepay));
    }
    af.setBorrowerInfoDTO(borrowerInfoDTO);
    af.setMonthYear(month);
    af.setBizType(BusiConst.PLBUSINESSTYPE_SINGLERECOVER + "");
    return af;
  }

  /**
   * 以银行为主导入
   * 
   * @param importList
   * @param securityInfo
   * @throws Exception
   */
  public Integer adCallbackBatch(List importList, SecurityInfo securityInfo)
      throws Exception {
    String loanKouAcc = "";// 贷款账号
    String contractId = "";// 合同编号
    String loanBankId = "";// 放款银行
    String loanBankIdim = "";// 导入文件放款银行
    String bizDate = securityInfo.getUserInfo().getPlbizDate();// 会计日期
    String bizDateim = "";// 导入文件会计日期
    String bizTypeim = "";// 导入文件类型
    BigDecimal shouldCorpus = new BigDecimal(0.00);// 应还正常本金
    BigDecimal shouldInterest = new BigDecimal(0.00);// 应还正常利息
    BigDecimal shouldOverdueCorpus = new BigDecimal(0.00);// 应还逾期本金
    BigDecimal shouldOverdueInterest = new BigDecimal(0.00);// 应还逾期利息
    BigDecimal shouldPunishInterest = new BigDecimal(0.00);// 应还罚息
    BigDecimal realCorpus = new BigDecimal(0.00);// 实还本金
    BigDecimal realInterest = new BigDecimal(0.00);// 实还利息
    BigDecimal realOverdueCorpus = new BigDecimal(0.00);// 实还逾期本金
    BigDecimal realOverdueInterest = new BigDecimal(0.00);// 实还逾期利息
    BigDecimal realPunishInterest = new BigDecimal(0.00);// 实还罚息
    BigDecimal temp_realCorpus = new BigDecimal(0.00);// 实还本金
    BigDecimal temp_realInterest = new BigDecimal(0.00);// 实还利息
    BigDecimal temp_realOverdueCorpus = new BigDecimal(0.00);// 实还逾期本金
    BigDecimal temp_realOverdueInterest = new BigDecimal(0.00);// 实还逾期利息
    BigDecimal temp_realPunishInterest = new BigDecimal(0.00);// 实还罚息
    String contractSt = BusiConst.PLCONTRACTSTATUS_RECOVING + "";// 合同状态 11.还款中
    String line = "";
    String loanRepayDay = "";// 还款日
    String temp_bizDate = bizDate.substring(0, 4) + "-"
        + bizDate.substring(4, 6) + "-" + bizDate.substring(6, 8);
    List bizStList = null;
    List borrowerList = null;
    BorrowerInfoDTO borrowerInfoDTO = null;
    List bankList = null;// 权限下的银行
    if (importList.isEmpty()) {
      throw new BusinessException("导入文件为空！");
    } else if (importList.size() > 1) {
      LoancallbackTaImportDTO dto1 = (LoancallbackTaImportDTO) importList
          .get(0);
      loanBankIdim = dto1.getLoanBankId();
      bizDateim = dto1.getBizDate();
      bizTypeim = dto1.getBizType();

      int iClearYear = Integer.parseInt(bizDate.substring(0, 4)) - 1;
      String clearYear = this.getClearYear(String.valueOf(loanBankIdim));
      // 会计年份-1如果不等于PL002中的年结年份则不允许回收
      if (iClearYear > Integer.parseInt(clearYear)) {
        throw new BusinessException(iClearYear + "年尚未年结，不允许进行回收业务！");
      }
      if (!temp_bizDate.equals(bizDateim)) {
        throw new BusinessException("会计日期不一致！");
      }
      bankList = securityDAO.getDkUserBankList_LJ(securityInfo.getUserName(),
          loanBankIdim);
      // 判断有无操作此银行的权限
      if (bankList.isEmpty()) {
        throw new BusinessException("不具备该银行的权限！");
      }
      if (bizTypeim == null) {
        throw new BusinessException("业务类型不能为空！");
      } else if (!bizTypeim.equals(String
          .valueOf(BusiConst.PLBUSINESSTYPE_SINGLERECOVER))
          && !bizTypeim.equals(String
              .valueOf(BusiConst.PLBUSINESSTYPE_PARTRECOVER))
          && !bizTypeim.equals(String
              .valueOf(BusiConst.PLBUSINESSTYPE_ALLCLEAR))) {
        throw new BusinessException("该业务类型不能在此导入！");
      }
      LoancallbackTaImportDTO dto2 = (LoancallbackTaImportDTO) importList
          .get(1);
      loanKouAcc = dto2.getLoanKouAcc();

      // 判断贷款账号在PL111中是否存在并且状态为还款中。
      contractId = findBorrowerAcc(loanKouAcc, contractSt, securityInfo);
      if (contractId == null) {
        throw new BusinessException("此贷款账号不存在！");
      }
      // 该贷款账号在贷款流水账头表PL202表中是否存在BIZ_ST!=6(未记账)（关联贷款流水账尾表PL203）
      bizStList = loanFlowHeadDAO.queryBizStListByLoanKouAcc_LJ(contractId,
          bizTypeim);
      if (!bizStList.isEmpty()) {
        throw new BusinessException("存在未记账的业务！");
      }
      // 从PL110及PL111取信息
      borrowerList = borrowerAccDAO
          .queryBorrowerAccInfoByLoanKouAcc_LJ(contractId);
      if (!borrowerList.isEmpty()) {
        borrowerInfoDTO = (BorrowerInfoDTO) borrowerList.get(0);
      }
      loanRepayDay = borrowerInfoDTO.getLoanRepayDay();

      loanBankId = borrowerInfoDTO.getLoanBankId().toString();
      if (!loanBankId.equals(loanBankIdim)) {
        throw new BusinessException("放款银行不一致！");
      }
      for (int i = 1; i < importList.size(); i++) {
        LoancallbackTaImportDTO dto = (LoancallbackTaImportDTO) importList
            .get(i);

        if (Double.parseDouble(dto.getRealCorpus()) < 0) {
          throw new BusinessException("金额不能小于0！");
        }
        if (Double.parseDouble(dto.getRealOverdueCorpus()) < 0) {
          throw new BusinessException("金额不能小于0！");
        }
        if (Double.parseDouble(dto.getRealInterest()) < 0) {
          throw new BusinessException("金额不能小于0！");
        }
        if (Double.parseDouble(dto.getRealOverdueInterest()) < 0) {
          throw new BusinessException("金额不能小于0！");
        }
        if (Double.parseDouble(dto.getRealPunishInterest()) < 0) {
          throw new BusinessException("金额不能小于0！");
        }
        if (Double.parseDouble(dto.getNobackCorpus()) < 0) {
          throw new BusinessException("金额不能小于0！");
        }
        if (Double.parseDouble(dto.getNobackOverdueCorpus()) < 0) {
          throw new BusinessException("金额不能小于0！");
        }
        if (Double.parseDouble(dto.getNobackInterest()) < 0) {
          throw new BusinessException("金额不能小于0！");
        }
        if (Double.parseDouble(dto.getNobackOverdueInterest()) < 0) {
          throw new BusinessException("金额不能小于0！");
        }
        if (Double.parseDouble(dto.getNobackPunishInterest()) < 0) {
          throw new BusinessException("金额不能小于0！");
        }
        shouldCorpus = shouldCorpus.add(new BigDecimal(dto.getRealCorpus())
            .add(new BigDecimal(dto.getNobackCorpus())));
        shouldInterest = shouldInterest.add(new BigDecimal(dto
            .getRealInterest()).add(new BigDecimal(dto.getNobackInterest())));
        shouldOverdueCorpus = shouldOverdueCorpus.add(new BigDecimal(dto
            .getRealOverdueCorpus()).add(new BigDecimal(dto
            .getNobackOverdueCorpus())));
        shouldOverdueInterest = shouldOverdueInterest.add(new BigDecimal(dto
            .getRealOverdueInterest()).add(new BigDecimal(dto
            .getNobackOverdueInterest())));
        shouldPunishInterest = shouldPunishInterest.add(new BigDecimal(dto
            .getRealPunishInterest()).add(new BigDecimal(dto
            .getNobackPunishInterest())));
        realCorpus = realCorpus.add(new BigDecimal(dto.getRealCorpus()));
        realInterest = realInterest.add(new BigDecimal(dto.getRealInterest()));
        realOverdueCorpus = realOverdueCorpus.add(new BigDecimal(dto
            .getRealOverdueCorpus()));
        realOverdueInterest = realOverdueInterest.add(new BigDecimal(dto
            .getRealOverdueInterest()));
        realPunishInterest = realPunishInterest.add(new BigDecimal(dto
            .getRealPunishInterest()));
        if (i == importList.size() - 1) {
          line = dto.getDeadLine();
        }
      }
    }
    // 插入流水头表
    LoanFlowHead loanFlowHead = new LoanFlowHead();
    loanFlowHead.setBizDate(bizDate);
    String officeId = "";
    String loanDocNumDocument = collParaDAO.getLoanDocNumDesignPara();
    if (loanDocNumDocument.equals("1")) {
      officeId = borrowerInfoDTO.getOfficeCode();
    } else {
      officeId = borrowerInfoDTO.getLoanBankId().toString();
    }
    loanFlowHead.setDocNum(plDocNumMaintainDAO.getDocNumdocNum(officeId,
        bizDate.substring(0, 6)));
    loanFlowHead.setBizType(bizTypeim);
    loanFlowHead.setShouldCount(new BigDecimal(1));
    loanFlowHead.setShouldCorpus(shouldCorpus);
    loanFlowHead.setShouldInterest(shouldInterest);
    loanFlowHead.setShouldOverdueCorpus(shouldOverdueCorpus);
    loanFlowHead.setShouldOverdueInterest(shouldOverdueInterest);
    loanFlowHead.setShouldPunishInterest(shouldPunishInterest);
    loanFlowHead.setRealCount(new BigDecimal(1));
    loanFlowHead.setRealCorpus(realCorpus);
    loanFlowHead.setRealInterest(realInterest);
    loanFlowHead.setRealOverdueCorpus(realOverdueCorpus);
    loanFlowHead.setRealOverdueInterest(realOverdueInterest);
    loanFlowHead.setRealPunishInterest(realPunishInterest);
    loanFlowHead.setOccurMoney(new BigDecimal(0.00));
    loanFlowHead.setOtherInterest(new BigDecimal(0.00));
    loanFlowHead.setLoanBankId(new BigDecimal(loanBankId));
    loanFlowHead.setBizSt(BusiConst.PLBUSINESSSTATE_IMP + "");
    loanFlowHead.setMakePerson(securityInfo.getUserName());
    loanFlowHead.setIsFinance(new Integer(1));
    loanFlowHead.setDeadLine(line);
    loanFlowHeadDAO.insert(loanFlowHead);
    // 更新票据号
    LoanFlowHead loanFlowHead1 = loanFlowHeadDAO.queryById(loanFlowHead
        .getFlowHeadId());
    loanFlowHead1.setNoteNum(loanFlowHead1.getFlowHeadId().toString());

    // 插入流水尾表
    if (!importList.isEmpty()) {
      if (bizTypeim
          .equals(String.valueOf(BusiConst.PLBUSINESSTYPE_PARTRECOVER))
          || bizTypeim
              .equals(String.valueOf(BusiConst.PLBUSINESSTYPE_ALLCLEAR))) {
        if (importList.size() == 2) {
          for (int i = 1; i < importList.size(); i++) {
            LoancallbackTaImportDTO dto = (LoancallbackTaImportDTO) importList
                .get(i);
            LoanFlowTail loanFlowTail = new LoanFlowTail();
            loanFlowTail.setFlowHeadId(new BigDecimal(loanFlowHead
                .getFlowHeadId().toString()));
            loanFlowTail.setContractId(contractId);
            loanFlowTail.setLoanKouAcc(dto.getLoanKouAcc());
            loanFlowTail.setShouldCorpus(new BigDecimal(dto.getRealCorpus())
                .add(new BigDecimal(dto.getNobackCorpus())).add(
                    new BigDecimal(dto.getRealOverdueCorpus())).add(
                    new BigDecimal(dto.getNobackOverdueCorpus())));
            loanFlowTail.setRealCorpus(new BigDecimal(dto.getRealCorpus())
                .add(new BigDecimal(dto.getRealOverdueCorpus())));
            loanFlowTail
                .setShouldInterest(new BigDecimal(dto.getRealInterest()).add(
                    new BigDecimal(dto.getNobackInterest())).add(
                    new BigDecimal(dto.getRealOverdueInterest())).add(
                    new BigDecimal(dto.getNobackOverdueInterest())));
            loanFlowTail.setRealInterest(new BigDecimal(dto.getRealInterest())
                .add(new BigDecimal(dto.getRealOverdueInterest())));
            loanFlowTail.setShouldPunishInterest(new BigDecimal(dto
                .getRealPunishInterest()).add(new BigDecimal(dto
                .getNobackPunishInterest())));
            loanFlowTail.setRealPunishInterest(new BigDecimal(dto
                .getRealPunishInterest()));
            loanFlowTail.setYearMonth(dto.getYearMonth());
            loanFlowTail.setOtherInterest(new BigDecimal(0.00));
            loanFlowTail.setOccurMoney(new BigDecimal(0.00));
            loanFlowTail.setLoanType("3");
            loanFlowTailDAO.insert(loanFlowTail);
          }
        } else {
          for (int i = 1; i < importList.size(); i++) {
            LoancallbackTaImportDTO dto = (LoancallbackTaImportDTO) importList
                .get(i);
            temp_realCorpus = new BigDecimal(dto.getRealCorpus());
            temp_realInterest = new BigDecimal(dto.getRealInterest());
            temp_realOverdueCorpus = new BigDecimal(dto.getRealOverdueCorpus());
            temp_realOverdueInterest = new BigDecimal(dto
                .getRealOverdueInterest());
            temp_realPunishInterest = new BigDecimal(dto
                .getRealPunishInterest());
            LoanFlowTail loanFlowTail = new LoanFlowTail();

            // 如果逾期数据和正常数据都大于0则向流水表中插入两条数据，一条还款类型为1正常，一条为2逾期
            if (temp_realCorpus.add(temp_realInterest).doubleValue() > 0
                && temp_realOverdueCorpus.add(temp_realOverdueInterest).add(
                    temp_realPunishInterest).doubleValue() > 0) {

              loanFlowTail.setContractId(contractId);
              loanFlowTail.setFlowHeadId(new BigDecimal(loanFlowHead1
                  .getFlowHeadId().toString()));
              loanFlowTail.setLoanKouAcc(loanKouAcc);
              loanFlowTail.setYearMonth(dto.getYearMonth());
              loanFlowTail.setShouldCorpus(new BigDecimal(dto.getRealCorpus())
                  .add(new BigDecimal(dto.getNobackCorpus())));
              loanFlowTail.setShouldInterest(new BigDecimal(dto
                  .getRealInterest()).add(new BigDecimal(dto
                  .getNobackInterest())));
              // loanFlowTail.setShouldPunishInterest(new
              // BigDecimal(dto.getRealPunishInterest()).add(new
              // BigDecimal(dto.getNobackPunishInterest())));
              loanFlowTail.setRealCorpus(new BigDecimal(dto.getRealCorpus()));
              loanFlowTail
                  .setRealInterest(new BigDecimal(dto.getRealInterest()));
              // loanFlowTail.setRealPunishInterest(new
              // BigDecimal(dto.getRealPunishInterest()));
              loanFlowTail.setOtherInterest(new BigDecimal(0.00));
              loanFlowTail.setOccurMoney(new BigDecimal(0.00));
              loanFlowTail.setLoanType("1");
              loanFlowTailDAO.insert(loanFlowTail);

              LoanFlowTail loanFlowTail1 = new LoanFlowTail();
              loanFlowTail1.setContractId(contractId);
              loanFlowTail1.setFlowHeadId(new BigDecimal(loanFlowHead1
                  .getFlowHeadId().toString()));
              loanFlowTail1.setLoanKouAcc(loanKouAcc);
              loanFlowTail1.setYearMonth(dto.getYearMonth());
              loanFlowTail1.setShouldCorpus(new BigDecimal(dto
                  .getRealOverdueCorpus()).add(new BigDecimal(dto
                  .getNobackOverdueCorpus())));
              loanFlowTail1.setShouldInterest(new BigDecimal(dto
                  .getRealOverdueInterest()).add(new BigDecimal(dto
                  .getNobackOverdueInterest())));
              loanFlowTail1.setShouldPunishInterest(new BigDecimal(dto
                  .getRealPunishInterest()).add(new BigDecimal(dto
                  .getNobackPunishInterest())));
              loanFlowTail1.setRealCorpus(new BigDecimal(dto
                  .getRealOverdueCorpus()));
              loanFlowTail1.setRealInterest(new BigDecimal(dto
                  .getRealOverdueInterest()));
              loanFlowTail1.setRealPunishInterest(new BigDecimal(dto
                  .getRealPunishInterest()));
              loanFlowTail1.setOtherInterest(new BigDecimal(0.00));
              loanFlowTail1.setOccurMoney(new BigDecimal(0.00));
              loanFlowTail1.setLoanType("2");
              loanFlowTailDAO.insert(loanFlowTail1);
              // 否则正常数据大于0逾期数据等于0，插入一条还款类型为1正常
            } else if (temp_realCorpus.add(temp_realInterest).doubleValue() > 0) {
              loanFlowTail.setContractId(contractId);
              loanFlowTail.setFlowHeadId(new BigDecimal(loanFlowHead1
                  .getFlowHeadId().toString()));
              loanFlowTail.setLoanKouAcc(loanKouAcc);
              loanFlowTail.setYearMonth(dto.getYearMonth());
              loanFlowTail.setShouldCorpus(new BigDecimal(dto.getRealCorpus())
                  .add(new BigDecimal(dto.getNobackCorpus())));
              loanFlowTail.setShouldInterest(new BigDecimal(dto
                  .getRealInterest()).add(new BigDecimal(dto
                  .getNobackInterest())));
              // loanFlowTail.setShouldPunishInterest(new
              // BigDecimal(dto.getRealPunishInterest()).add(new
              // BigDecimal(dto.getNobackPunishInterest())));
              loanFlowTail.setRealCorpus(new BigDecimal(dto.getRealCorpus()));
              loanFlowTail
                  .setRealInterest(new BigDecimal(dto.getRealInterest()));
              // loanFlowTail.setRealPunishInterest(new
              // BigDecimal(dto.getRealPunishInterest()));
              loanFlowTail.setOtherInterest(new BigDecimal(0.00));
              loanFlowTail.setOccurMoney(new BigDecimal(0.00));
              loanFlowTail.setLoanType("1");
              loanFlowTailDAO.insert(loanFlowTail);
              // 否则逾期数据大于0正常数据等于0，插入一条还款类型为2逾期
            } else if (temp_realOverdueCorpus.add(temp_realOverdueInterest)
                .add(temp_realPunishInterest).doubleValue() > 0) {
              loanFlowTail.setContractId(contractId);
              loanFlowTail.setFlowHeadId(new BigDecimal(loanFlowHead1
                  .getFlowHeadId().toString()));
              loanFlowTail.setLoanKouAcc(loanKouAcc);
              loanFlowTail.setYearMonth(dto.getYearMonth());
              loanFlowTail.setShouldCorpus(new BigDecimal(dto
                  .getRealOverdueCorpus()).add(new BigDecimal(dto
                  .getNobackOverdueCorpus())));
              loanFlowTail.setShouldInterest(new BigDecimal(dto
                  .getRealOverdueInterest()).add(new BigDecimal(dto
                  .getNobackOverdueInterest())));
              loanFlowTail.setShouldPunishInterest(new BigDecimal(dto
                  .getRealPunishInterest()).add(new BigDecimal(dto
                  .getNobackPunishInterest())));
              loanFlowTail.setRealCorpus(new BigDecimal(dto
                  .getRealOverdueCorpus()));
              loanFlowTail.setRealInterest(new BigDecimal(dto
                  .getRealOverdueInterest()));
              loanFlowTail.setRealPunishInterest(new BigDecimal(dto
                  .getRealPunishInterest()));
              loanFlowTail.setOtherInterest(new BigDecimal(0.00));
              loanFlowTail.setOccurMoney(new BigDecimal(0.00));
              loanFlowTail.setLoanType("2");
              loanFlowTailDAO.insert(loanFlowTail);
            }
            // String loanRepayDay1 = this.getEndDay(dto.getYearMonth(),
            // loanRepayDay);
            // String temp_date = dto.getYearMonth().substring(0, 4) + "-"
            // + dto.getYearMonth().substring(4, 6) + "-" + loanRepayDay1;
            // 逾期天数
            // int days = BusiTools.minusDate(temp_bizDate, temp_date);
            // if(days<=0){
            // loanFlowTail.setLoanType("1");
            // }else{
            // loanFlowTail.setLoanType("2");
            // }
            if (i == importList.size() - 1) {
              loanFlowTail.setFlowHeadId(new BigDecimal(loanFlowHead
                  .getFlowHeadId().toString()));
              loanFlowTail.setContractId(contractId);
              loanFlowTail.setLoanKouAcc(dto.getLoanKouAcc());
              loanFlowTail.setShouldCorpus(new BigDecimal(dto.getRealCorpus())
                  .add(new BigDecimal(dto.getNobackCorpus())).add(
                      new BigDecimal(dto.getRealOverdueCorpus())).add(
                      new BigDecimal(dto.getNobackOverdueCorpus())));
              loanFlowTail.setRealCorpus(new BigDecimal(dto.getRealCorpus())
                  .add(new BigDecimal(dto.getRealOverdueCorpus())));
              loanFlowTail.setShouldInterest(new BigDecimal(dto
                  .getRealInterest()).add(
                  new BigDecimal(dto.getNobackInterest())).add(
                  new BigDecimal(dto.getRealOverdueInterest())).add(
                  new BigDecimal(dto.getNobackOverdueInterest())));
              loanFlowTail
                  .setRealInterest(new BigDecimal(dto.getRealInterest())
                      .add(new BigDecimal(dto.getRealOverdueInterest())));
              loanFlowTail.setShouldPunishInterest(new BigDecimal(dto
                  .getRealPunishInterest()).add(new BigDecimal(dto
                  .getNobackPunishInterest())));
              loanFlowTail.setRealPunishInterest(new BigDecimal(dto
                  .getRealPunishInterest()));
              loanFlowTail.setYearMonth(dto.getYearMonth());
              loanFlowTail.setOtherInterest(new BigDecimal(0.00));
              loanFlowTail.setOccurMoney(new BigDecimal(0.00));
              loanFlowTail.setLoanType("3");
              loanFlowTailDAO.insert(loanFlowTail);
            }
          }
        }
      } else {
        for (int i = 1; i < importList.size(); i++) {
          LoancallbackTaImportDTO dto = (LoancallbackTaImportDTO) importList
              .get(i);
          temp_realCorpus = new BigDecimal(dto.getRealCorpus());
          temp_realInterest = new BigDecimal(dto.getRealInterest());
          temp_realOverdueCorpus = new BigDecimal(dto.getRealOverdueCorpus());
          temp_realOverdueInterest = new BigDecimal(dto
              .getRealOverdueInterest());
          temp_realPunishInterest = new BigDecimal(dto.getRealPunishInterest());
          LoanFlowTail loanFlowTail = new LoanFlowTail();
          // 如果逾期数据和正常数据都大于0则向流水表中插入两条数据，一条还款类型为1正常，一条为2逾期
          if (temp_realCorpus.add(temp_realInterest).doubleValue() > 0
              && temp_realOverdueCorpus.add(temp_realOverdueInterest).add(
                  temp_realPunishInterest).doubleValue() > 0) {

            loanFlowTail.setContractId(contractId);
            loanFlowTail.setFlowHeadId(new BigDecimal(loanFlowHead1
                .getFlowHeadId().toString()));
            loanFlowTail.setLoanKouAcc(loanKouAcc);
            loanFlowTail.setYearMonth(dto.getYearMonth());
            loanFlowTail.setShouldCorpus(new BigDecimal(dto.getRealCorpus())
                .add(new BigDecimal(dto.getNobackCorpus())));
            loanFlowTail
                .setShouldInterest(new BigDecimal(dto.getRealInterest())
                    .add(new BigDecimal(dto.getNobackInterest())));
            // loanFlowTail.setShouldPunishInterest(new
            // BigDecimal(dto.getRealPunishInterest()).add(new
            // BigDecimal(dto.getNobackPunishInterest())));
            loanFlowTail.setRealCorpus(new BigDecimal(dto.getRealCorpus()));
            loanFlowTail.setRealInterest(new BigDecimal(dto.getRealInterest()));
            // loanFlowTail.setRealPunishInterest(new
            // BigDecimal(dto.getRealPunishInterest()));
            loanFlowTail.setOtherInterest(new BigDecimal(0.00));
            loanFlowTail.setOccurMoney(new BigDecimal(0.00));
            loanFlowTail.setLoanType("1");
            loanFlowTailDAO.insert(loanFlowTail);

            LoanFlowTail loanFlowTail1 = new LoanFlowTail();
            loanFlowTail1.setContractId(contractId);
            loanFlowTail1.setFlowHeadId(new BigDecimal(loanFlowHead1
                .getFlowHeadId().toString()));
            loanFlowTail1.setLoanKouAcc(loanKouAcc);
            loanFlowTail1.setYearMonth(dto.getYearMonth());
            loanFlowTail1.setShouldCorpus(new BigDecimal(dto
                .getRealOverdueCorpus()).add(new BigDecimal(dto
                .getNobackOverdueCorpus())));
            loanFlowTail1.setShouldInterest(new BigDecimal(dto
                .getRealOverdueInterest()).add(new BigDecimal(dto
                .getNobackOverdueInterest())));
            loanFlowTail1.setShouldPunishInterest(new BigDecimal(dto
                .getRealPunishInterest()).add(new BigDecimal(dto
                .getNobackPunishInterest())));
            loanFlowTail1.setRealCorpus(new BigDecimal(dto
                .getRealOverdueCorpus()));
            loanFlowTail1.setRealInterest(new BigDecimal(dto
                .getRealOverdueInterest()));
            loanFlowTail1.setRealPunishInterest(new BigDecimal(dto
                .getRealPunishInterest()));
            loanFlowTail1.setOtherInterest(new BigDecimal(0.00));
            loanFlowTail1.setOccurMoney(new BigDecimal(0.00));
            loanFlowTail1.setLoanType("2");
            loanFlowTailDAO.insert(loanFlowTail1);
            // 否则正常数据大于0逾期数据小于0，插入一条还款类型为1正常
          } else if (temp_realCorpus.add(temp_realInterest).doubleValue() > 0) {
            loanFlowTail.setContractId(contractId);
            loanFlowTail.setFlowHeadId(new BigDecimal(loanFlowHead1
                .getFlowHeadId().toString()));
            loanFlowTail.setLoanKouAcc(loanKouAcc);
            loanFlowTail.setYearMonth(dto.getYearMonth());
            loanFlowTail.setShouldCorpus(new BigDecimal(dto.getRealCorpus())
                .add(new BigDecimal(dto.getNobackCorpus())));
            loanFlowTail
                .setShouldInterest(new BigDecimal(dto.getRealInterest())
                    .add(new BigDecimal(dto.getNobackInterest())));
            // loanFlowTail.setShouldPunishInterest(new
            // BigDecimal(dto.getRealPunishInterest()).add(new
            // BigDecimal(dto.getNobackPunishInterest())));
            loanFlowTail.setRealCorpus(new BigDecimal(dto.getRealCorpus()));
            loanFlowTail.setRealInterest(new BigDecimal(dto.getRealInterest()));
            // loanFlowTail.setRealPunishInterest(new
            // BigDecimal(dto.getRealPunishInterest()));
            loanFlowTail.setOtherInterest(new BigDecimal(0.00));
            loanFlowTail.setOccurMoney(new BigDecimal(0.00));
            loanFlowTail.setLoanType("1");
            loanFlowTailDAO.insert(loanFlowTail);
          } else if (temp_realOverdueCorpus.add(temp_realOverdueInterest).add(
              temp_realPunishInterest).doubleValue() > 0) {
            loanFlowTail.setContractId(contractId);
            loanFlowTail.setFlowHeadId(new BigDecimal(loanFlowHead1
                .getFlowHeadId().toString()));
            loanFlowTail.setLoanKouAcc(loanKouAcc);
            loanFlowTail.setYearMonth(dto.getYearMonth());
            loanFlowTail.setShouldCorpus(new BigDecimal(dto
                .getRealOverdueCorpus()).add(new BigDecimal(dto
                .getNobackOverdueCorpus())));
            loanFlowTail.setShouldInterest(new BigDecimal(dto
                .getRealOverdueInterest()).add(new BigDecimal(dto
                .getNobackOverdueInterest())));
            loanFlowTail.setShouldPunishInterest(new BigDecimal(dto
                .getRealPunishInterest()).add(new BigDecimal(dto
                .getNobackPunishInterest())));
            loanFlowTail.setRealCorpus(new BigDecimal(dto
                .getRealOverdueCorpus()));
            loanFlowTail.setRealInterest(new BigDecimal(dto
                .getRealOverdueInterest()));
            loanFlowTail.setRealPunishInterest(new BigDecimal(dto
                .getRealPunishInterest()));
            loanFlowTail.setOtherInterest(new BigDecimal(0.00));
            loanFlowTail.setOccurMoney(new BigDecimal(0.00));
            loanFlowTail.setLoanType("2");
            loanFlowTailDAO.insert(loanFlowTail);
          }
          // String loanRepayDay1 = this.getEndDay(dto.getYearMonth(),
          // loanRepayDay);
          // String temp_date = dto.getYearMonth().substring(0, 4) + "-"
          // + dto.getYearMonth().substring(4, 6) + "-" + loanRepayDay1;
          // // 逾期天数
          // int days = BusiTools.minusDate(temp_bizDate, temp_date);
          // if(days<=0){
          // loanFlowTail.setLoanType("1");
          // }else{
          // loanFlowTail.setLoanType("2");
          // }
          // loanFlowTailDAO.insert(loanFlowTail);
        }
      }
    }
    // 插入业务日志
    PlBizActiveLog plBizActiveLog = new PlBizActiveLog();
    plBizActiveLog.setAction(BusiConst.PLBUSINESSSTATE_IMP + "");
    plBizActiveLog.setBizid(new BigDecimal(loanFlowHead.getFlowHeadId()
        .toString()));
    plBizActiveLog.setOperator(securityInfo.getUserName());
    plBizActiveLog.setOpTime(new Date());
    plBizActiveLog.setType(loanFlowHead.getBizType());
    plBizActiveLogDAO.insert(plBizActiveLog);
    // 插入操作日志
    PlOperateLog plOperateLog = new PlOperateLog();
    plOperateLog.setOpSys(new BigDecimal(BusiLogConst.OP_SYSTEM_TYPE_LOAN));
    plOperateLog.setContractId(contractId);
    plOperateLog.setOpBizId(new BigDecimal(loanFlowHead.getFlowHeadId()
        .toString()));
    plOperateLog.setOpButton(BusiLogConst.BIZBLOG_ACTION_IMP + "");
    plOperateLog.setOperator(securityInfo.getUserName());
    plOperateLog.setOpIp(securityInfo.getUserIp());
    plOperateLog.setOpModel(BusiLogConst.PL_OP_LOANRECOVER_DO + "");
    plOperateLog.setOpTime(new Date());
    plOperateLogDAO.insert(plOperateLog);
    return loanFlowHead.getFlowHeadId();
  }

  /**
   * 回收办理银行导入后查询
   * 
   * @param pagination
   * @return
   */
  public LoancallbackTaAF findCallbacklistByLoanBank(Pagination pagination)
      throws Exception {
    LoancallbackTaAF af = new LoancallbackTaAF();
    BorrowerInfoDTO borrowerInfoDTO = null;
    List borrowerList = null;
    String headId = (String) pagination.getQueryCriterions().get("headId");
    List list = new ArrayList();
    BigDecimal loanRate = null;
    String bizType = "";
    String line = "";
    if (headId != null) {
      LoanFlowHead loanFlowHead = loanFlowHeadDAO
          .queryById(new Integer(headId));
      line = loanFlowHead.getDeadLine();
      String bizDate = loanFlowHead.getBizDate();
      String yearMonth = bizDate.substring(0, 6);// 取出会计日期中的年月
      String year = yearMonth.substring(0, 4);
      String month = yearMonth.substring(4, 6);
      String day = bizDate.substring(6, bizDate.length());// 取出会计日期中的日
      String temp_bizDate = year + "-" + month + "-" + day;// 用来判断列表中的还款类型所转换的会计日期
      List tailList = loanFlowTailDAO.queryRealLoanFlowTailByHeadId_LJ(headId);
      String contractId = "";// 合同编号
      String loanRepayDay = "";// 还款日
      bizType = loanFlowHead.getBizType();
      if (!tailList.isEmpty()) {
        ShouldBackListDTO shouldBackListDTO = (ShouldBackListDTO) tailList
            .get(0);
        contractId = shouldBackListDTO.getContractId();
        borrowerList = borrowerAccDAO
            .queryBorrowerAccInfoByLoanKouAcc_LJ(contractId);
        if (!borrowerList.isEmpty()) {
          borrowerInfoDTO = (BorrowerInfoDTO) borrowerList.get(0);
        }
        // 证件类型
        borrowerInfoDTO
            .setCardKind(BusiTools.getBusiValue(Integer
                .parseInt(borrowerInfoDTO.getCardKind()),
                BusiConst.DOCUMENTSSTATE));
        // 还款方式
        borrowerInfoDTO.setLoanMode(BusiTools.getBusiValue(Integer
            .parseInt(borrowerInfoDTO.getLoanMode()), BusiConst.PLRECOVERTYPE));
        loanRepayDay = borrowerInfoDTO.getLoanRepayDay();
        if (bizType
            .equals(String.valueOf(BusiConst.PLBUSINESSTYPE_PARTRECOVER))
            || bizType
                .equals(String.valueOf(BusiConst.PLBUSINESSTYPE_ALLCLEAR))) {
          for (int i = 0; i < tailList.size() - 1; i++) {
            ShouldBackListDTO dto = (ShouldBackListDTO) tailList.get(i);
            ShouldBackListDTO dto2 = new ShouldBackListDTO();
            dto2.setLoanKouYearmonth(dto.getLoanKouYearmonth());
            String loanRepayDay1 = this.getEndDay(dto.getLoanKouYearmonth(),
                loanRepayDay);
            // 逾期天数
            int days = this.getDays(bizDate, dto.getLoanKouYearmonth(),
                loanRepayDay1);
            if (days < 0) {
              days = 0;
            }
            String type = dto.getLoanKouType();
            if (type.equals("1")) {
              dto2.setLoanKouType("正常");
            } else if (type.equals("2")) {
              dto2.setLoanKouType("逾期");
            }
            dto2.setShouldCorpus(dto.getShouldCorpus());
            dto2.setShouldInterest(dto.getShouldInterest());
            dto2.setPunishInterest(dto.getPunishInterest());
            dto2.setCiMoney(dto.getShouldCorpus().add(dto.getShouldInterest())
                .add(dto.getPunishInterest()));
            dto2.setLoanRate(dto.getLoanRate());
            // dto2.setShow_loanRate(dto.getLoanRate().multiply(new
            // BigDecimal(100))+"%");
            dto2.setShow_loanRate("");
            if (dto.getLoanRate().doubleValue() == 0) {
              dto2.setLoanRate(loanRate);
            }
            dto2.setDays(days + "");
            list.add(dto2);
          }
          ShouldBackListDTO dto = (ShouldBackListDTO) tailList.get(tailList
              .size() - 1);
          af.setAheadCorpus(dto.getShouldCorpus());
          af.setAheadInterest(dto.getShouldInterest().add(
              dto.getPunishInterest()));
          af.setDeadLine(line);
        } else {
          for (int i = 0; i < tailList.size(); i++) {
            ShouldBackListDTO dto = (ShouldBackListDTO) tailList.get(i);
            ShouldBackListDTO dto2 = new ShouldBackListDTO();
            dto2.setLoanKouYearmonth(dto.getLoanKouYearmonth());
            String loanRepayDay1 = this.getEndDay(dto.getLoanKouYearmonth(),
                loanRepayDay);
            // 逾期天数
            int days = this.getDays(bizDate, dto.getLoanKouYearmonth(),
                loanRepayDay1);
            if (days < 0) {
              days = 0;
            }
            String type = dto.getLoanKouType();
            if (type.equals("1")) {
              dto2.setLoanKouType("正常");
            } else if (type.equals("2")) {
              dto2.setLoanKouType("逾期");
            }
            dto2.setShouldCorpus(dto.getShouldCorpus());
            dto2.setShouldInterest(dto.getShouldInterest());
            dto2.setPunishInterest(dto.getPunishInterest());
            dto2.setCiMoney(dto.getShouldCorpus().add(dto.getShouldInterest())
                .add(dto.getPunishInterest()));
            dto2.setLoanRate(dto.getLoanRate());
            // dto2.setShow_loanRate(dto.getLoanRate().multiply(new
            // BigDecimal(100))+"%");
            dto2.setShow_loanRate("");
            if (dto.getLoanRate().doubleValue() == 0) {
              dto2.setLoanRate(loanRate);
            }
            dto2.setDays(days + "");
            list.add(dto2);
          }
        }
      }
      int count = 0;
      if (!list.isEmpty()) {
        ShouldBackListDTO dto = (ShouldBackListDTO) list.get(list.size() - 1);
        af.setMonthYear(dto.getLoanKouYearmonth());
        count = list.size();
      }

      af.setMonthYearList(list);
      af.setBorrowerInfoDTO(borrowerInfoDTO);
      af.setShouldBackList(list);
      af.setBizType(loanFlowHead.getBizType());
      af.setSumCorpus(loanFlowHead.getRealCorpus().add(
          loanFlowHead.getRealOverdueCorpus()));
      af.setSumInterest(loanFlowHead.getRealInterest().add(
          loanFlowHead.getRealOverdueInterest()).add(
          loanFlowHead.getRealPunishInterest()));
      af.setSumMoney(af.getSumCorpus().add(af.getSumInterest()));
      af.setOvaerLoanRepay(new BigDecimal(0.00));
      af.setRealMoney(af.getSumMoney());
      pagination.setNrOfElements(count);
    }

    return af;
  }

  /**
   * 办理回收确定
   * 
   * @param af
   * @param securityInfo
   * @throws Exception
   * @return
   */
  public String addCallbackInfo(LoancallbackTaAF af, SecurityInfo securityInfo,
      String matter) throws Exception {
    // 以中心为主
    String contractId = af.getBorrowerInfoDTO().getContractId();// 合同编号
    String paramType = "A";// 参数类型
    String paramValue = "";// 参数值
    Integer loanBankId = af.getBorrowerInfoDTO().getLoanBankId();// 放款银行
    BigDecimal ovaerLoanRepay = af.getOvaerLoanRepay();// 挂账余额
    BigDecimal sumMoney = af.getSumMoney();// 本次总还款金额
    BigDecimal realMoney = af.getRealMoney();// 实收金额
    String bizType = af.getBizType();// 类型
    List bizStList = new ArrayList();
    String bizDate = securityInfo.getUserInfo().getPlbizDate();
    String operator = securityInfo.getUserName();
    Integer headId = null;
    // 判断贷款账号在表PL202中是否存在:BIZ_ST！=6（关联PL203）
    // 该贷款账号在贷款流水账头表PL202表中是否存在BIZ_ST!=6(未记账)（关联贷款流水账尾表PL203）
    bizStList = loanFlowHeadDAO.queryBizStListByLoanKouAcc_LJ(contractId, null);
    if (bizStList.size() > 0) {
      throw new BusinessException("存在未记账的业务！");
    }
    // 判断该笔业务类型是否为部分提前还款或一次性清还
    if (!bizType.equals(BusiConst.PLBUSINESSTYPE_SINGLERECOVER + "")) {
      // 足额扣款
      headId = addLoanFlowHeadFull(bizDate, operator, af, matter, securityInfo,
          loanBankId.toString());
    } else {
      // 判断该贷款账号的所属银行在银行贷款参数PL003表中参数类型PARAM_TYPE=A:参数维护：还款参数and
      // 参数序号PARAM_NUM=1的参数值PARAM_VALUE是否=1:足额扣款
      paramValue = loanBankParaDAO.queryParamValue_LJ(loanBankId, paramType,
          "1");
      if (paramValue.equals(BusiConst.PLDEBITTYPE_SUFF + "")) {
        // 足额扣款
        headId = addLoanFlowHeadFull(bizDate, operator, af, matter,
            securityInfo, loanBankId.toString());
      } else {
        // 判断该贷款账号在借款人账户表PL111中挂账余额：OVAER_LOAN_REPAY是否大于（页面中）本次总还款金额
        if (ovaerLoanRepay.doubleValue() > sumMoney.doubleValue()) {
          // 足额扣款
          headId = addLoanFlowHeadFull(bizDate, operator, af, matter,
              securityInfo, loanBankId.toString());
        } else {
          // 判断页面中本次实收金额是否等于（本次总还款金额-挂账余额）
          if (realMoney.doubleValue() == sumMoney.subtract(ovaerLoanRepay)
              .doubleValue()) {
            // 足额扣款
            headId = addLoanFlowHeadFull(bizDate, operator, af, matter,
                securityInfo, loanBankId.toString());
          } else {
            // 全额扣款
            headId = addLoanFlowHeadALL(bizDate, operator, af);
          }
        }
      }
    }
    // 插入操作日志
    PlOperateLog plOperateLog = new PlOperateLog();
    plOperateLog.setOpSys(new BigDecimal(BusiLogConst.OP_SYSTEM_TYPE_LOAN));
    plOperateLog.setContractId(contractId);
    plOperateLog.setOpBizId(new BigDecimal(headId.toString()));
    plOperateLog.setOpButton(BusiLogConst.BIZLOG_ACTION_ADD + "");
    plOperateLog.setOperator(securityInfo.getUserName());
    plOperateLog.setOpIp(securityInfo.getUserIp());
    plOperateLog.setOpModel(BusiLogConst.PL_OP_LOANRECOVER_DO + "");
    plOperateLog.setOpTime(new Date());
    plOperateLogDAO.insert(plOperateLog);
    // 插入业务日志
    PlBizActiveLog plBizActiveLog = new PlBizActiveLog();
    plBizActiveLog.setAction(String.valueOf(BusiConst.PLBUSINESSSTATE_SIGN));
    plBizActiveLog.setBizid(new BigDecimal(headId.toString()));
    plBizActiveLog.setOperator(securityInfo.getUserName());
    plBizActiveLog.setOpTime(new Date());
    plBizActiveLog.setType(af.getBizType());
    plBizActiveLogDAO.insert(plBizActiveLog);
    return headId.toString();
  }

  // 足额扣款
  public Integer addLoanFlowHeadFull(String bizDate, String operator,
      LoancallbackTaAF af, String matter, SecurityInfo securityInfo,
      String loanBankId) throws Exception {
    LoanFlowHead loanFlowHead = new LoanFlowHead();
    BigDecimal shouldCorpus = new BigDecimal(0.00);
    BigDecimal shouldInterest = new BigDecimal(0.00);
    BigDecimal shouldOverdueCorpus = new BigDecimal(0.00);
    BigDecimal shouldOverdueInterest = new BigDecimal(0.00);
    BigDecimal punishInterest = new BigDecimal(0.00);
    String temp_bizDate = "";// 插入提前还款数据时所用到的日期
    String bizYearmonth = bizDate.substring(0, 6);
    String bizType = af.getBizType();
    List list = af.getShouldBackList();
    BigDecimal aheadCorpus = af.getAheadCorpus();
    BigDecimal aheadInterest = af.getAheadInterest();
    BigDecimal aheadInterest1 = af.getAheadInterest1();
    BigDecimal occurMoney = af.getSumMoney().subtract(af.getRealMoney())
        .divide(new BigDecimal(1), 2, BigDecimal.ROUND_HALF_UP);
    BigDecimal temp_occurMoney = new BigDecimal(0.00);
    BigDecimal temp_money = occurMoney;
    List monthYearList = af.getMonthYearList();// 下拉列表框list
    if (!list.isEmpty()) {
      for (int i = 0; i < list.size(); i++) {
        ShouldBackListDTO dto = (ShouldBackListDTO) list.get(i);
        punishInterest = punishInterest.add(dto.getPunishInterest());
        if (Integer.parseInt(dto.getDays()) <= 0) {
          shouldCorpus = shouldCorpus.add(dto.getShouldCorpus());
          shouldInterest = shouldInterest.add(dto.getShouldInterest());
        } else {
          shouldOverdueCorpus = shouldOverdueCorpus.add(dto.getShouldCorpus());
          shouldOverdueInterest = shouldOverdueInterest.add(dto
              .getShouldInterest());
        }
      }
    }
    if (bizType.equals(BusiConst.PLBUSINESSTYPE_SINGLERECOVER + "")) {
      loanFlowHead.setShouldCorpus(shouldCorpus);
      loanFlowHead.setShouldInterest(shouldInterest);
    } else {
      loanFlowHead.setShouldCorpus(shouldCorpus.add(aheadCorpus));
      loanFlowHead.setShouldInterest(shouldInterest.add(aheadInterest));
    }
    // //插入流水头表
    // String officeId="";
    // String loanDocNumDocument=collParaDAO.getLoanDocNumDesignPara();
    // if(loanDocNumDocument.equals("1")){
    // officeId=af.getBorrowerInfoDTO().getOfficeCode();
    // }else{
    // officeId=af.getBorrowerInfoDTO().getLoanBankId().toString();
    // }
    // loanFlowHead.setDocNum(plDocNumMaintainDAO.getDocNumdocNum(officeId,
    // bizYearmonth));
    String officeId = "";
    String loanDocNumDocument = collParaDAO.getLoanDocNumDesignPara();
    if (loanDocNumDocument.equals("1")) {
      officeId = af.getBorrowerInfoDTO().getOfficeCode();
    } else {
      officeId = af.getBorrowerInfoDTO().getLoanBankId().toString();
    }
    String docNum = plDocNumMaintainDAO.getDocNumdocNum(loanBankId + "",
        bizYearmonth.substring(0, 4));
    Map office = securityInfo.getOfficeInnerCodeMap();
    String officecode = office.get(officeId).toString();
    if (officecode.length() == 1) {
      officecode = "0" + officecode;
    }
    docNum = bizYearmonth.substring(0, 4) + officecode + "0" + loanBankId
        + docNum;
    loanFlowHead.setDocNum(docNum);
    loanFlowHead.setBizDate(bizDate);
    loanFlowHead.setBizType(bizType);
    loanFlowHead.setShouldCount(new BigDecimal(1));
    loanFlowHead.setShouldOverdueCorpus(shouldOverdueCorpus);
    loanFlowHead.setShouldOverdueInterest(shouldOverdueInterest);
    loanFlowHead.setShouldPunishInterest(punishInterest);
    loanFlowHead.setRealCount(new BigDecimal(1));
    loanFlowHead.setRealCorpus(loanFlowHead.getShouldCorpus());
    loanFlowHead.setRealInterest(loanFlowHead.getShouldInterest());
    loanFlowHead.setRealOverdueCorpus(loanFlowHead.getShouldOverdueCorpus());
    loanFlowHead
        .setRealOverdueInterest(loanFlowHead.getShouldOverdueInterest());
    loanFlowHead.setRealPunishInterest(loanFlowHead.getShouldPunishInterest());
    loanFlowHead.setOccurMoney(af.getSumMoney().subtract(af.getRealMoney())
        .divide(new BigDecimal(1), 2, BigDecimal.ROUND_HALF_UP).multiply(
            new BigDecimal(-1.00)));
    loanFlowHead.setOtherInterest(new BigDecimal(0.00));
    loanFlowHead.setLoanBankId(new BigDecimal(af.getBorrowerInfoDTO()
        .getLoanBankId().toString()));
    loanFlowHead.setBizSt(BusiConst.PLBUSINESSSTATE_SIGN + "");
    loanFlowHead.setMakePerson(operator);
    loanFlowHead.setLoanPoundageMoney(af.getLoanPoundageMoney());
    if("1".equals(af.getAheadTypeS())){
      loanFlowHead.setDeadLine(af.getDead());
    }else{
      loanFlowHead.setDeadLine(af.getDeadLine());
    }
    loanFlowHead.setIsFinance(new Integer(1));
    if (af.getAheadCheckId() != null && !"".equals(af.getAheadCheckId()))
      loanFlowHead.setAheadCheckId(new Integer(af.getAheadCheckId()));
    loanFlowHead.setReserveaB(aheadInterest1+"");
    loanFlowHeadDAO.insert(loanFlowHead);

    // 系统自动生成结算号：业务日期+流水号
    String noteNum = "";
    noteNum = bizDate + loanFlowHeadDAO.queryNoteNum();
    LoanFlowHead loanFlowHead1 = loanFlowHeadDAO.queryById(loanFlowHead
        .getFlowHeadId());
    loanFlowHead1.setNoteNum(noteNum);

    // 插入流水尾表
    if (!list.isEmpty()) {
      for (int i = 0; i < list.size(); i++) {
        ShouldBackListDTO dto = (ShouldBackListDTO) list.get(i);
        LoanFlowTail loanFlowTail = new LoanFlowTail();
        loanFlowTail.setContractId(af.getBorrowerInfoDTO().getContractId());
        loanFlowTail.setFlowHeadId(new BigDecimal(loanFlowHead.getFlowHeadId()
            .toString()));
        loanFlowTail.setLoanKouAcc(af.getBorrowerInfoDTO().getLoanKouAcc());
        loanFlowTail.setYearMonth(dto.getLoanKouYearmonth());
        loanFlowTail.setShouldCorpus(dto.getShouldCorpus());
        loanFlowTail.setShouldInterest(dto.getShouldInterest());
        loanFlowTail.setShouldPunishInterest(dto.getPunishInterest());
        loanFlowTail.setRealCorpus(dto.getShouldCorpus());
        loanFlowTail.setRealInterest(dto.getShouldInterest());
        loanFlowTail.setRealPunishInterest(dto.getPunishInterest());
        loanFlowTail.setTempPunishInterest(dto.getTempInterest());
        loanFlowTail.setOtherInterest(new BigDecimal(0.00));
        loanFlowTail.setLoanRate(dto.getLoanRate());
        if (Integer.parseInt(dto.getDays()) <= 0) {
          loanFlowTail.setLoanType("1");
        } else {
          loanFlowTail.setLoanType("2");
        }
        loanFlowTail.setTempPunishInterest(dto.getTempInterest().subtract(
            dto.getPunishInterest()));
        // 注：插入发生额
        temp_occurMoney = dto.getShouldCorpus().add(dto.getShouldInterest())
            .add(dto.getPunishInterest());
        if (temp_occurMoney.doubleValue() <= temp_money.doubleValue()) {
          loanFlowTail.setOccurMoney(temp_occurMoney.multiply(new BigDecimal(
              -1.00)));
          temp_money = temp_money.subtract(temp_occurMoney);
        } else {
          loanFlowTail
              .setOccurMoney(temp_money.multiply(new BigDecimal(-1.00)));
          temp_money = new BigDecimal(0.00);
        }
        if (i == list.size() - 1) {
          temp_bizDate = dto.getLoanKouYearmonth().substring(0, 6);
        }
        loanFlowTailDAO.insert(loanFlowTail);
      }
    }
    String loanRepayDayBiz = this.getEndDay(bizDate.substring(0, 6), af
        .getBorrowerInfoDTO().getLoanRepayDay());
    String day = bizDate.substring(6, 8);
    if (temp_bizDate == "") {
      if (!monthYearList.isEmpty()) {
        ShouldBackListDTO dto = (ShouldBackListDTO) monthYearList.get(0);
        temp_bizDate = dto.getLoanKouYearmonth().substring(0, 6);
        if (Integer.parseInt(loanRepayDayBiz) <= Integer.parseInt(day)) {
          temp_bizDate = BusiTools.addMonth(temp_bizDate, -1);
        }
      } else {
        // 取下一年1月
        String year = bizDate.substring(0, 4);
        int iYear = Integer.parseInt(year) + 1;
        String temp_yearMonth = String.valueOf(iYear) + "01";
        temp_yearMonth = temp_yearMonth.substring(0, 6);
        if (Integer.parseInt(loanRepayDayBiz) <= Integer.parseInt(day)) {
          temp_bizDate = BusiTools.addMonth(temp_yearMonth, -1);
        } else {
          temp_bizDate = temp_yearMonth;
        }
      }
    } else if (Integer.parseInt(loanRepayDayBiz) > Integer.parseInt(day)) {
      temp_bizDate = BusiTools.addMonth(temp_bizDate, 1);
    }
    if (!bizType.equals(BusiConst.PLBUSINESSTYPE_SINGLERECOVER + "")) {
      LoanFlowTail loanFlowTail = new LoanFlowTail();
      loanFlowTail.setFlowHeadId(new BigDecimal(loanFlowHead.getFlowHeadId()
          .toString()));
      loanFlowTail.setContractId(af.getBorrowerInfoDTO().getContractId());
      loanFlowTail.setLoanKouAcc(af.getBorrowerInfoDTO().getLoanKouAcc());
      loanFlowTail.setShouldCorpus(af.getAheadCorpus());
      loanFlowTail.setRealCorpus(af.getAheadCorpus());
      loanFlowTail.setShouldInterest(af.getAheadInterest());
      loanFlowTail.setRealInterest(af.getAheadInterest());
      loanFlowTail.setShouldPunishInterest(new BigDecimal(0.00));
      loanFlowTail.setRealPunishInterest(new BigDecimal(0.00));
      loanFlowTail.setYearMonth(temp_bizDate);
      loanFlowTail.setOtherInterest(new BigDecimal(0.00));
      loanFlowTail.setOccurMoney(temp_money.multiply(new BigDecimal(-1.00)));
      loanFlowTail.setLoanType("3");
      loanFlowTailDAO.insert(loanFlowTail);
      if (bizType.equals(BusiConst.PLBUSINESSTYPE_PARTRECOVER + "")) {
        String type = af.getAheadType();
        BigDecimal money = af.getAheadCorpus();
        /**
         * 1.提前部分还款2.延长还款期限3.缩短还款期限4.提前部分还款、延长还款期限5.提前部分还款、缩短还款期限
         */
        if (money.intValue() > 0) {
          if (type.equals(BusiConst.AHEADTYPE_3)) {
            af.setAheadType("1");
          } else if (type.equals(BusiConst.AHEADTYPE_1)) {
            af.setAheadType("4");
          } else {
            af.setAheadType("5");
          }
        } else {
          if (type.equals(BusiConst.AHEADTYPE_1)) {
            af.setAheadType("2");
          } else {
            af.setAheadType("3");
          }
        }
        // 插入PL101_1
        if("1".equals(af.getAheadTypeS())){
          loanFlowTailDAO.insertPL101_1_jj(af.getBorrowerInfoDTO()
              .getContractId(), af.getAheadTypeS(), bizDate, af.getAheadCorpus(),
              af.getDead(), af.getCorpusInterest(), af.getOverplusCorpus()
                  .subtract(af.getAheadCorpus()), matter, loanFlowHead
                  .getFlowHeadId().toString(),af.getDead());
        }else{
          loanFlowTailDAO.insertPL101_1_jj(af.getBorrowerInfoDTO()
              .getContractId(), af.getAheadTypeS(), bizDate, af.getAheadCorpus(),
              af.getDeadLine(), af.getCorpusInterest(), af.getOverplusCorpus()
                  .subtract(af.getAheadCorpus()), matter, loanFlowHead
                  .getFlowHeadId().toString(),af.getDead());
        }
        
        String chgid = loanFlowTailDAO.selectPL101_1_jj();
        loanFlowHead1.setChgId(new Integer(chgid));
        // 更新PL112_1
        if (af.getAheadCheckId() != null && !"".equals(af.getAheadCheckId())) {
          loanFlowTailDAO.updatePL112_1_jjByContractId(af.getBorrowerInfoDTO()
              .getContractId());
        }
      }
    }
    return loanFlowHead.getFlowHeadId();
  }

  // 全额扣款
  public Integer addLoanFlowHeadALL(String bizDate, String operator,
      LoancallbackTaAF af) throws Exception {
    LoanFlowHead loanFlowHead = new LoanFlowHead();
    BigDecimal shouldCorpus = new BigDecimal(0.00);
    BigDecimal shouldInterest = new BigDecimal(0.00);
    BigDecimal shouldOverdueCorpus = new BigDecimal(0.00);
    BigDecimal shouldOverdueInterest = new BigDecimal(0.00);
    BigDecimal punishInterest = new BigDecimal(0.00);
    String bizYearmonth = bizDate.substring(0, 6);
    List list = af.getShouldBackList();
    BigDecimal occurMoney = af.getOvaerLoanRepay();// 挂账余额
    BigDecimal temp_occurMoney = new BigDecimal(0.00);
    BigDecimal temp_money = occurMoney;
    BigDecimal realMoney = af.getRealMoney().add(af.getOvaerLoanRepay());// 实收+挂账余额
    String paramValue = "";// 参数值
    BigDecimal normalCorpus = new BigDecimal(0.00);// 正常本金
    BigDecimal normalIntersert = new BigDecimal(0.00);// 正常利息
    BigDecimal overdueCorpus = new BigDecimal(0.00);// 逾期本金
    BigDecimal overdueInterest = new BigDecimal(0.00);// 逾期利息
    BigDecimal punishInterests = new BigDecimal(0.00);// 罚息
    try {
      if (!list.isEmpty()) {
        for (int i = 0; i < list.size(); i++) {
          ShouldBackListDTO dto = (ShouldBackListDTO) list.get(i);
          punishInterest = punishInterest.add(dto.getPunishInterest());
          if (Integer.parseInt(dto.getDays()) <= 0) {
            shouldCorpus = shouldCorpus.add(dto.getShouldCorpus());
            shouldInterest = shouldInterest.add(dto.getShouldInterest());
          } else {
            shouldOverdueCorpus = shouldOverdueCorpus
                .add(dto.getShouldCorpus());
            shouldOverdueInterest = shouldOverdueInterest.add(dto
                .getShouldInterest());
          }
        }
      }
      // if(bizType.equals(BusiConst.PLBUSINESSTYPE_SINGLERECOVER + "")){
      // loanFlowHead.setShouldCorpus(shouldCorpus);
      // loanFlowHead.setShouldInterest(shouldInterest);
      // }
      Integer loanBankId = af.getBorrowerInfoDTO().getLoanBankId();
      paramValue = loanBankParaDAO.queryParamValue_LJ(loanBankId, "A", "3");
      // 插入流水头表
      String officeId = "";
      String loanDocNumDocument = collParaDAO.getLoanDocNumDesignPara();
      if (loanDocNumDocument.equals("1")) {
        officeId = af.getBorrowerInfoDTO().getOfficeCode();
      } else {
        officeId = af.getBorrowerInfoDTO().getLoanBankId().toString();
      }
      loanFlowHead.setDocNum(plDocNumMaintainDAO.getDocNumdocNum(officeId,
          bizYearmonth));
      loanFlowHead.setBizDate(bizDate);
      loanFlowHead.setBizType(BusiConst.PLBUSINESSTYPE_SINGLERECOVER + "");
      loanFlowHead.setShouldCount(new BigDecimal(1));
      loanFlowHead.setShouldCorpus(shouldCorpus);
      loanFlowHead.setShouldInterest(shouldInterest);
      loanFlowHead.setShouldOverdueCorpus(shouldOverdueCorpus);
      loanFlowHead.setShouldOverdueInterest(shouldOverdueInterest);
      loanFlowHead.setShouldPunishInterest(punishInterest);
      loanFlowHead.setRealCount(new BigDecimal(1));
      // loanFlowHead.setRealCorpus(loanFlowHead.getShouldCorpus());
      // loanFlowHead.setRealInterest(loanFlowHead.getShouldInterest());
      // loanFlowHead.setRealOverdueCorpus(loanFlowHead.getShouldOverdueCorpus());
      // loanFlowHead.setRealOverdueInterest(loanFlowHead.getShouldOverdueInterest());
      // loanFlowHead.setRealPunishInterest(loanFlowHead.getShouldPunishInterest());
      loanFlowHead.setOccurMoney(af.getOvaerLoanRepay().multiply(
          new BigDecimal(-1.00)));
      loanFlowHead.setOtherInterest(new BigDecimal(0.00));
      loanFlowHead.setLoanBankId(new BigDecimal(af.getBorrowerInfoDTO()
          .getLoanBankId().toString()));
      loanFlowHead.setBizSt(BusiConst.PLBUSINESSSTATE_SIGN + "");
      loanFlowHead.setMakePerson(operator);
      loanFlowHead.setIsFinance(new Integer(1));
      loanFlowHeadDAO.insert(loanFlowHead);

      // 插入流水尾表要竖着扣
      if (!list.isEmpty()) {
        List insertList = new ArrayList();
        boolean is_sub_A = false;
        boolean is_sub_B = false;
        boolean is_sub_C = false;
        boolean is_sub_D = false;
        boolean is_sub_E = false;
        for (int j = 0; j < paramValue.length(); j++) {
          int m = 0;
          if (BusiConst.PLALLMESS_CORPUS.equals(String.valueOf(paramValue
              .charAt(j)))) {// 正常本金
            for (int i = 0; i < list.size(); i++) {
              ShouldBackListDTO dto = (ShouldBackListDTO) list.get(i);
              ShouldBackListDTO dto1 = null;
              if (!is_sub_A && !is_sub_B && !is_sub_C && !is_sub_D && !is_sub_E) {
                dto1 = new ShouldBackListDTO();
              } else {
                dto1 = (ShouldBackListDTO) insertList.get(m);
                m = m + 1;
              }
              if (Integer.parseInt(dto.getDays()) <= 0) {
                if (dto.getShouldCorpus().doubleValue() <= realMoney
                    .doubleValue()) {
                  dto1.setRealCorpus(dto.getShouldCorpus());
                  // normalCorpus = normalCorpus.add(dto.getShouldCorpus());
                  realMoney = realMoney.subtract(dto.getShouldCorpus());
                } else {
                  dto1.setRealCorpus(realMoney);
                  realMoney = new BigDecimal(0.00);
                }
              }
              insertList.add(dto1);
            }
            is_sub_A = true;

          } else if (BusiConst.PLALLMESS_INTEREST.equals(String
              .valueOf(paramValue.charAt(j)))) {// 正常利息

            for (int i = 0; i < list.size(); i++) {
              ShouldBackListDTO dto1 = null;
              if (!is_sub_A && !is_sub_B && !is_sub_C && !is_sub_D && !is_sub_E) {
                dto1 = new ShouldBackListDTO();
              } else {
                dto1 = (ShouldBackListDTO) insertList.get(m);
                m = m + 1;
              }
              ShouldBackListDTO dto = (ShouldBackListDTO) list.get(i);
              if (Integer.parseInt(dto.getDays()) <= 0) {
                if (dto.getShouldInterest().doubleValue() <= realMoney
                    .doubleValue()) {
                  dto1.setRealInterest(dto.getShouldInterest());
                  // normalIntersert =
                  // normalIntersert.add(dto.getShouldInterest());
                  realMoney = realMoney.subtract(dto.getShouldInterest());
                } else {
                  dto1.setRealInterest(realMoney);
                  realMoney = new BigDecimal(0.00);
                }
              }
              insertList.add(dto1);
            }
            is_sub_B = true;
          } else if (BusiConst.PLALLMESS_OVERDUECORPUS.equals(String
              .valueOf(paramValue.charAt(j)))) {// 逾期本金
            for (int i = 0; i < list.size(); i++) {
              ShouldBackListDTO dto1 = null;
              if (!is_sub_A && !is_sub_B && !is_sub_C && !is_sub_D && !is_sub_E) {
                dto1 = new ShouldBackListDTO();
              } else {
                dto1 = (ShouldBackListDTO) insertList.get(m);
                m = m + 1;
              }
              ShouldBackListDTO dto = (ShouldBackListDTO) list.get(i);
              if (Integer.parseInt(dto.getDays()) > 0) {
                if (dto.getShouldCorpus().doubleValue() <= realMoney
                    .doubleValue()) {
                  dto1.setRealOverdueCorpus(dto.getShouldCorpus());
                  // overdueCorpus = overdueCorpus.add(dto.getShouldCorpus());
                  realMoney = realMoney.subtract(dto.getShouldCorpus());
                } else {
                  dto1.setRealOverdueCorpus(realMoney);
                  realMoney = new BigDecimal(0.00);
                }
              }

              insertList.add(dto1);
            }
            is_sub_C = true;
          } else if (BusiConst.PLALLMESS_OVERDUEINTEREST.equals(String
              .valueOf(paramValue.charAt(j)))) {// 逾期利息
            for (int i = 0; i < list.size(); i++) {
              ShouldBackListDTO dto1 = null;
              if (!is_sub_A && !is_sub_B && !is_sub_C && !is_sub_D && !is_sub_E) {
                dto1 = new ShouldBackListDTO();
              } else {
                dto1 = (ShouldBackListDTO) insertList.get(i);
                m = m + 1;
              }
              ShouldBackListDTO dto = (ShouldBackListDTO) list.get(i);
              if (Integer.parseInt(dto.getDays()) > 0) {
                if (dto.getShouldInterest().doubleValue() <= realMoney
                    .doubleValue()) {
                  dto1.setRealOverdueInterest(dto.getShouldInterest());
                  // overdueInterest =
                  // overdueInterest.add(dto.getShouldInterest());
                  realMoney = realMoney.subtract(dto.getShouldInterest());
                } else {
                  dto1.setRealOverdueInterest(realMoney);
                  realMoney = new BigDecimal(0.00);
                }
              }
              insertList.add(dto1);
            }
            is_sub_D = true;
          } else if (BusiConst.PLALLMESS_PUNISHINTEREST.equals(String
              .valueOf(paramValue.charAt(j)))) {// 罚息
            for (int i = 0; i < list.size(); i++) {
              ShouldBackListDTO dto1 = null;
              if (!is_sub_A && !is_sub_B && !is_sub_C && !is_sub_D && !is_sub_E) {
                dto1 = new ShouldBackListDTO();
              } else {
                dto1 = (ShouldBackListDTO) insertList.get(m);
                m = m + 1;
              }
              ShouldBackListDTO dto = (ShouldBackListDTO) list.get(i);
              if (dto.getPunishInterest().doubleValue() <= realMoney
                  .doubleValue()) {
                dto1.setPunishInterest(dto.getPunishInterest());
                // punishInterests =
                // punishInterests.add(dto.getPunishInterest());
                realMoney = realMoney.subtract(dto.getPunishInterest());
              } else {
                dto1.setPunishInterest(realMoney);
                realMoney = new BigDecimal(0.00);
              }
              insertList.add(dto1);
            }
            is_sub_E = true;
          }
        }
        for (int i = 0; i < list.size(); i++) {
          ShouldBackListDTO dto = (ShouldBackListDTO) list.get(i);
          ShouldBackListDTO dto1 = (ShouldBackListDTO) insertList.get(i);
          LoanFlowTail loanFlowTail = new LoanFlowTail();
          loanFlowTail.setContractId(af.getBorrowerInfoDTO().getContractId());
          loanFlowTail.setFlowHeadId(new BigDecimal(loanFlowHead
              .getFlowHeadId().toString()));
          loanFlowTail.setLoanKouAcc(af.getBorrowerInfoDTO().getLoanKouAcc());
          loanFlowTail.setYearMonth(dto.getLoanKouYearmonth());
          loanFlowTail.setShouldCorpus(dto.getShouldCorpus());
          loanFlowTail.setShouldInterest(dto.getShouldInterest());
          loanFlowTail.setShouldPunishInterest(dto.getPunishInterest());
          loanFlowTail.setRealCorpus(dto1.getRealCorpus().add(
              dto1.getRealOverdueCorpus()));
          loanFlowTail.setRealInterest(dto1.getRealInterest().add(
              dto1.getRealOverdueInterest()));
          loanFlowTail.setRealPunishInterest(dto1.getPunishInterest());
          loanFlowTail.setTempPunishInterest(dto.getTempInterest());
          loanFlowTail.setOtherInterest(new BigDecimal(0.00));
          loanFlowTail.setLoanRate(dto.getLoanRate());
          if (Integer.parseInt(dto.getDays()) <= 0) {
            loanFlowTail.setLoanType("1");
            normalCorpus = normalCorpus.add(loanFlowTail.getRealCorpus());
            normalIntersert = normalIntersert.add(loanFlowTail
                .getRealInterest());
          } else {
            loanFlowTail.setLoanType("2");
            overdueCorpus = overdueCorpus.add(loanFlowTail.getRealCorpus());
            overdueInterest = overdueInterest.add(loanFlowTail
                .getRealInterest());
          }
          punishInterests = punishInterests.add(loanFlowTail
              .getRealPunishInterest());
          loanFlowTail.setTempPunishInterest(dto.getTempInterest().subtract(
              dto1.getPunishInterest()));
          // 注：插入发生额
          temp_occurMoney = loanFlowTail.getRealCorpus().add(
              loanFlowTail.getRealInterest()).add(
              loanFlowTail.getRealPunishInterest());
          if (temp_occurMoney.doubleValue() <= temp_money.doubleValue()) {
            loanFlowTail.setOccurMoney(temp_occurMoney.multiply(new BigDecimal(
                -1.00)));
            temp_money = temp_money.subtract(temp_occurMoney);
          } else {
            loanFlowTail.setOccurMoney(temp_money
                .multiply(new BigDecimal(-1.00)));
            temp_money = new BigDecimal(0.00);
          }
          loanFlowTailDAO.insert(loanFlowTail);
        }
      }
      // 系统自动生成结算号：业务日期+流水号
      String noteNum = "";
      noteNum = bizDate + loanFlowHeadDAO.queryNoteNum();
      // 再通过尾表更新头表
      LoanFlowHead loanFlowHead1 = loanFlowHeadDAO.queryById(loanFlowHead
          .getFlowHeadId());
      loanFlowHead1.setRealCorpus(normalCorpus);
      loanFlowHead1.setRealInterest(normalIntersert);
      loanFlowHead1.setRealOverdueCorpus(overdueCorpus);
      loanFlowHead1.setRealOverdueInterest(overdueInterest);
      loanFlowHead1.setRealPunishInterest(punishInterests);
      loanFlowHead1.setNoteNum(noteNum);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return loanFlowHead.getFlowHeadId();
  }

  /**
   * 回收办理以银行为主导入后确定改变流水表业务状态（导入--登记）
   * 
   * @param headId
   * @param securityInfo
   */
  public String addCallbackInfoByLoanBankId(Pagination pagination,
      String contractId, SecurityInfo securityInfo) throws Exception {
    String headId = (String) pagination.getQueryCriterions().get("headId");
    LoanFlowHead loanFlowHead = loanFlowHeadDAO.queryById(new Integer(headId));
    if (!loanFlowHead.getBizSt().equals(
        String.valueOf(BusiConst.PLBUSINESSSTATE_IMP))) {
      throw new BusinessException("不能进行登记操作！");
    }
    loanFlowHead.setBizSt(BusiConst.PLBUSINESSSTATE_SIGN + "");
    // 插入业务日志
    PlBizActiveLog plBizActiveLog = new PlBizActiveLog();
    plBizActiveLog.setAction(BusiConst.PLBUSINESSSTATE_SIGN + "");
    plBizActiveLog.setBizid(new BigDecimal(loanFlowHead.getFlowHeadId()
        .toString()));
    plBizActiveLog.setOperator(securityInfo.getUserName());
    plBizActiveLog.setOpTime(new Date());
    plBizActiveLog.setType(loanFlowHead.getBizType());
    plBizActiveLogDAO.insert(plBizActiveLog);

    // 插入操作日志
    PlOperateLog plOperateLog = new PlOperateLog();
    plOperateLog.setOpSys(new BigDecimal(BusiLogConst.OP_SYSTEM_TYPE_LOAN));
    plOperateLog.setContractId(contractId);
    plOperateLog.setOpButton(BusiLogConst.BIZLOG_ACTION_ADD + "");
    plOperateLog.setOperator(securityInfo.getUserName());
    plOperateLog.setOpIp(securityInfo.getUserIp());
    plOperateLog.setOpModel(BusiLogConst.PL_OP_LOANRECOVER_DO + "");
    plOperateLog.setOpTime(new Date());
    plOperateLogDAO.insert(plOperateLog);
    return loanFlowHead.getFlowHeadId().toString();
  }

  /**
   * 办理回收删除
   * 
   * @param headId
   * @param securityInfo
   * @throws Exception
   */
  public void deleteCallbackInfoByBank(String headId, SecurityInfo securityInfo)
      throws Exception {
    LoanFlowHead loanFlowHead = loanFlowHeadDAO.queryById(new Integer(headId));
    String office = "";// 办事处代码
    // 判断该笔业务在PL202中的业务状态是否=2
    if (!loanFlowHead.getBizSt().equals(
        String.valueOf(BusiConst.PLBUSINESSSTATE_IMP))) {
      throw new BusinessException("该笔业务为" + loanFlowHead.getBizSt()
          + "状态，不能删除！");
    } else {
      loanFlowTailDAO.deleteLoanFlowTailAll(headId.toString());
      // 删除业务务日志
      plBizActiveLogDAO.deletePlBizActiveLog_LJ(headId.toString(), loanFlowHead
          .getBizSt());
      // 撤销凭证号
      office = loanBankDAO.queryOfficeCodeByBankId_LJ(loanFlowHead
          .getLoanBankId().toString());
      String officeId = "";
      String loanDocNumDocument = collParaDAO.getLoanDocNumDesignPara();
      if (loanDocNumDocument.equals("1")) {
        officeId = office;
      } else {
        officeId = loanFlowHead.getLoanBankId().toString();
      }

      // plDocNumCancelDAO.insertPlDocNumCancel(loanFlowHead.getDocNum(),
      // officeId, loanFlowHead.getBizDate().substring(0, 6));
      plDocNumCancelDAO.insertPlDocNumCancel(loanFlowHead.getDocNum()
          .substring(8, 12), loanFlowHead.getDocNum().substring(7, 8),
          loanFlowHead.getDocNum().substring(0, 4));
      // 删除头表
      loanFlowHeadDAO.delete(loanFlowHead);
    }
    // 插入操作日志
    PlOperateLog plOperateLog = new PlOperateLog();
    plOperateLog.setOpSys(new BigDecimal(BusiLogConst.OP_SYSTEM_TYPE_LOAN));
    plOperateLog.setContractId(headId);
    plOperateLog.setOpBizId(new BigDecimal(headId));
    plOperateLog.setOpButton(BusiLogConst.BIZLOG_ACTION_DELETE + "");
    plOperateLog.setOperator(securityInfo.getUserName());
    plOperateLog.setOpIp(securityInfo.getUserIp());
    plOperateLog.setOpModel(BusiLogConst.PL_OP_LOANRECOVER_MAINTAIN + "");
    plOperateLog.setOpTime(new Date());
    plOperateLogDAO.insert(plOperateLog);

  }

  /**
   * 回收维护列表显示
   * 
   * @param pagination
   * @param securityInfo
   * @return
   */
  public List findCallbackList(Pagination pagination, SecurityInfo securityInfo)
      throws Exception {
    List list = new ArrayList();
    ;
    try {
      List tem_list = null;
      String contractId = (String) pagination.getQueryCriterions().get(
          "contractId");
      String loanKouAcc = (String) pagination.getQueryCriterions().get(
          "loanKouAcc");
      String name = (String) pagination.getQueryCriterions().get("name");
      String cardNum = (String) pagination.getQueryCriterions().get("cardNum");
      String loanBankId = (String) pagination.getQueryCriterions().get(
          "loanBankId");
      String docNum = (String) pagination.getQueryCriterions().get("docNum");
      String status = (String) pagination.getQueryCriterions().get("status");
      String type = (String) pagination.getQueryCriterions().get("type");
      String dateStart = (String) pagination.getQueryCriterions().get(
          "dateStart");
      String dateEnd = (String) pagination.getQueryCriterions().get("dateEnd");
      String yesOrNo = (String) pagination.getQueryCriterions().get("yesOrNo");
      String orderBy = (String) pagination.getOrderBy();
      ;
      String order = (String) pagination.getOrderother();
      int start = pagination.getFirstElementOnPage() - 1;
      int pageSize = pagination.getPageSize();
      int page = pagination.getPage();
      tem_list = loanFlowHeadDAO.queryCallbackList_LJ(loanKouAcc, contractId,
          name, cardNum, docNum, type, loanBankId, status, orderBy, order,
          start, pageSize, securityInfo, page, dateStart, dateEnd, yesOrNo);
      if (!tem_list.isEmpty()) {
        for (int i = 0; i < tem_list.size(); i++) {
          LoancallbackTbDTO dto = (LoancallbackTbDTO) tem_list.get(i);
          LoancallbackTbDTO dto2 = new LoancallbackTbDTO();
          // 转换业务状态
          dto2.setBizSt(BusiTools.getBusiValue(
              Integer.parseInt(dto.getBizSt()), BusiConst.PLBUSINESSSTATE));
          // 转换业务类型
          dto2.setBizType(BusiTools.getBusiValue(Integer.parseInt(dto
              .getBizType()), BusiConst.PLBUSINESSTYPE));
          dto2.setType(dto.getBizType());
          dto2.setBorrowerName(dto.getBorrowerName());
          dto2.setCardNum(dto.getCardNum());
          dto2.setContractId(dto.getContractId());
          dto2.setDocNum(dto.getDocNum());
          dto2.setId(dto.getId());
          dto2.setLoanKouAcc(dto.getLoanKouAcc());
          dto2.setRealCorpus(dto.getRealCorpus());
          dto2.setRealInterest(dto.getRealInterest());
          dto2.setRealbackMoney(dto2.getRealCorpus()
              .add(dto2.getRealInterest()).add(dto.getRealPunishInterest()));
          dto2.setRealMoney(dto2.getRealbackMoney().add(dto.getOccurMoney()));
          if (dto2.getRealMoney().doubleValue() <= 0) {
            dto2.setRealMoney(new BigDecimal(0.00));
          }
          dto2.setRealPunishInterest(dto.getRealPunishInterest());
          dto2.setOccurMoney(dto.getOccurMoney());
          dto2.setBizDate(dto.getBizDate());
          String yesOrNo2 = dto.getYesOrNo();
          if (yesOrNo2 != null && !"".equals(yesOrNo2)) {
            dto2.setYesOrNo("是");
          } else {
            dto2.setYesOrNo("否");
          }
          if (dto.getMark() != null && !"是".equals(dto.getMark())) {
            dto2.setMark("否");
          } else {
            dto2.setMark("是");
          }
          list.add(dto2);

        }
      }
      int count = 0;
      count = loanFlowHeadDAO.queryCallbackListCounts_LJ(loanKouAcc,
          contractId, name, cardNum, docNum, type, loanBankId, status,
          securityInfo, dateStart, dateEnd, yesOrNo);
      pagination.setNrOfElements(count);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return list;
  }

  /**
   * 维护列表合计
   * 
   * @param pagination
   * @param securityInfo
   * @return
   * @throws Exception
   */
  public LoancallbackTbDTO findCallbackListTotal(Pagination pagination,
      SecurityInfo securityInfo) throws Exception {
    List list = new ArrayList();
    String contractId = (String) pagination.getQueryCriterions().get(
        "contractId");
    String loanKouAcc = (String) pagination.getQueryCriterions().get(
        "loanKouAcc");
    String name = (String) pagination.getQueryCriterions().get("name");
    String cardNum = (String) pagination.getQueryCriterions().get("cardNum");
    String loanBankId = (String) pagination.getQueryCriterions().get(
        "loanBankId");
    String docNum = (String) pagination.getQueryCriterions().get("docNum");
    String status = (String) pagination.getQueryCriterions().get("status");
    String type = (String) pagination.getQueryCriterions().get("type");
    String dateStart = (String) pagination.getQueryCriterions()
        .get("dateStart");
    String dateEnd = (String) pagination.getQueryCriterions().get("dateEnd");
    BigDecimal realCorpus = new BigDecimal(0.00);
    BigDecimal realInterest = new BigDecimal(0.00);
    BigDecimal realPunishInterest = new BigDecimal(0.00);
    BigDecimal realbackMoney = new BigDecimal(0.00);
    BigDecimal occurMoney = new BigDecimal(0.00);
    int count = 0;
    list = loanFlowHeadDAO.queryCallbackTotal_LJ(loanKouAcc, contractId, name,
        cardNum, docNum, type, loanBankId, status, securityInfo, dateStart,
        dateEnd);
    if (!list.isEmpty()) {
      for (int i = 0; i < list.size(); i++) {
        LoancallbackTbDTO dto = (LoancallbackTbDTO) list.get(i);
        realCorpus = realCorpus.add(dto.getRealCorpus());
        realInterest = realInterest.add(dto.getRealInterest());
        realPunishInterest = realPunishInterest
            .add(dto.getRealPunishInterest());
        realbackMoney = realbackMoney.add(dto.getRealCorpus().add(
            dto.getRealInterest()).add(dto.getRealPunishInterest()));
        occurMoney = occurMoney.add(dto.getOccurMoney());
        count = count + new Integer(dto.getRealCount()).intValue();
      }
    }
    LoancallbackTbDTO dto = new LoancallbackTbDTO();
    dto.setRealCorpus(realCorpus);
    dto.setRealInterest(realInterest);
    dto.setRealPunishInterest(realPunishInterest);
    dto.setRealbackMoney(realbackMoney);
    dto.setOccurMoney(occurMoney);
    dto.setRealMoney(realbackMoney.add(occurMoney));
    dto.setRealCount(String.valueOf(count));
    if (dto.getRealMoney().doubleValue() <= 0) {
      dto.setRealMoney(new BigDecimal(0.00));
    }
    return dto;
  }

  /**
   * 回收维护删除列表信息
   * 
   * @param rowArray
   * @param securityInfo
   * @throws Exception
   */
  public void deleteCallbackInfos(String headId, SecurityInfo securityInfo)
      throws Exception {
    LoanFlowHead loanFlowHead = loanFlowHeadDAO.queryById(new Integer(headId));
    String office = "";// 办事处代码
    // 判断该笔业务在PL202中的业务状态BIZ_ST是否=2、3、4
    if (!(loanFlowHead.getBizSt().equals("2")
        || loanFlowHead.getBizSt().equals("3") || loanFlowHead.getBizSt()
        .equals("4"))) {
      String bizSt = BusiTools.getBusiValue(Integer.parseInt(loanFlowHead
          .getBizSt()), BusiConst.PLBUSINESSSTATE);
      throw new BusinessException(bizSt + "状态，不能删除！");
    } else {
      loanFlowTailDAO.deleteLoanFlowTailAll(headId);
      // 删除业务务日志
      plBizActiveLogDAO
          .deletePlBizActiveLog_LJ(headId, loanFlowHead.getBizSt());
      // 撤销凭证号
      office = loanBankDAO.queryOfficeCodeByBankId_LJ(loanFlowHead
          .getLoanBankId().toString());
      String officeId = "";
      String loanDocNumDocument = collParaDAO.getLoanDocNumDesignPara();
      if (loanDocNumDocument.equals("1")) {
        officeId = office;
      } else {
        officeId = loanFlowHead.getLoanBankId().toString();
      }
      // plDocNumCancelDAO.insertPlDocNumCancel(loanFlowHead.getDocNum(),
      // officeId, loanFlowHead.getBizDate().substring(0, 6));
      plDocNumCancelDAO.insertPlDocNumCancel(loanFlowHead.getDocNum()
          .substring(8, 12), loanFlowHead.getDocNum().substring(7, 8),
          loanFlowHead.getDocNum().substring(0, 4));
      // 查询是否有提前还款预审ID
      String aheadCheckId = "";
      if (loanFlowHead.getAheadCheckId() != null) {
        aheadCheckId = loanFlowHead.getAheadCheckId().toString();
      }
      if (aheadCheckId != null && !"".equals(aheadCheckId)) {
        // 更新PL112_1状态为未启用
        loanFlowTailDAO.updatePL112_1_jjByAheadCheckId(aheadCheckId);
      }
      // 删除提前还款变更信息表PL101_1
      String chgId = "";
      if (loanFlowHead.getChgId() != null) {
        chgId = loanFlowHead.getChgId().toString();
      }
      if (chgId != null && !"".equals(chgId)) {
        loanFlowTailDAO.deletePL101_1_jj(chgId);
      }
      // 删除头表
      loanFlowHeadDAO.delete(loanFlowHead);
    }

    // 插入操作日志
    PlOperateLog plOperateLog = new PlOperateLog();
    plOperateLog.setOpSys(new BigDecimal(BusiLogConst.OP_SYSTEM_TYPE_LOAN));
    plOperateLog.setContractId(loanFlowHead.getFlowHeadId().toString());
    plOperateLog.setOpBizId(new BigDecimal(headId));
    plOperateLog.setOpButton(BusiLogConst.BIZLOG_ACTION_DELETE + "");
    plOperateLog.setOperator(securityInfo.getUserName());
    plOperateLog.setOpIp(securityInfo.getUserIp());
    plOperateLog.setOpModel(BusiLogConst.PL_OP_LOANRECOVER_MAINTAIN + "");
    plOperateLog.setOpTime(new Date());
    plOperateLogDAO.insert(plOperateLog);

  }

  /**
   * 回收维护回收
   * 
   * @param rowArray
   * @param securityInfo
   * @throws Exception
   */
  public void callbackCallbackInfo(String headId, SecurityInfo securityInfo,
      String contractid) throws Exception {
    try {
      LoanFlowHead loanFlowHead = loanFlowHeadDAO
          .queryById(new Integer(headId));
      if(contractid!=null){
        String count = borrowerAccDAO.queryIsPl101(contractid);
        if (count != null && !"".equals(count)) {
          if (Integer.parseInt(count) > 0) {
            throw new BusinessException("该笔合同存在未审批通过的合同变更！");
          }
  
        }
      }
      // 判断该笔业务在贷款流水账头表PL202头表中的状态BIZ_ST是否=3.登记(20071023修改导入状态可以回收)
      if (!loanFlowHead.getBizSt().equals(
          String.valueOf(BusiConst.PLBUSINESSSTATE_SIGN))
          && !loanFlowHead.getBizSt().equals(
              String.valueOf(BusiConst.PLBUSINESSSTATE_IMP))) {
        String bizSt = BusiTools.getBusiValue(Integer.parseInt(loanFlowHead
            .getBizSt()), BusiConst.PLBUSINESSSTATE);
        throw new BusinessException(bizSt + "状态，不能回收！");
      } else {
        loanFlowHead.setBizSt(BusiConst.BUSINESSSTATE_SURE + "");
        // 插入业务日志
        PlBizActiveLog plBizActiveLog = new PlBizActiveLog();
        plBizActiveLog.setAction(BusiConst.BUSINESSSTATE_SURE + "");
        plBizActiveLog.setBizid(new BigDecimal(loanFlowHead.getFlowHeadId()
            .toString()));
        plBizActiveLog.setOperator(securityInfo.getUserName());
        plBizActiveLog.setOpTime(new Date());
        plBizActiveLog.setType(loanFlowHead.getBizType());
        plBizActiveLogDAO.insert(plBizActiveLog);
      }
      // 插入操作日志
      PlOperateLog plOperateLog = new PlOperateLog();
      plOperateLog.setOpSys(new BigDecimal(BusiLogConst.OP_SYSTEM_TYPE_LOAN));
      plOperateLog.setContractId(headId);
      plOperateLog.setOpBizId(new BigDecimal(headId));
      plOperateLog.setOpButton(BusiLogConst.BIZLOG_ACTION_CONFIRM + "");
      plOperateLog.setOperator(securityInfo.getUserName());
      plOperateLog.setOpIp(securityInfo.getUserIp());
      plOperateLog.setOpModel(BusiLogConst.PL_OP_LOANRECOVER_MAINTAIN + "");
      plOperateLog.setOpTime(new Date());
      plOperateLogDAO.insert(plOperateLog);
    } catch (Exception e) {
      e.printStackTrace();
      throw e;
    }
  }

  /**
   * 回收维护弹出窗口查询(单笔)
   * 
   * @param pagination
   * @return
   * @throws Exception
   */
  public LoancallbackTaAF findCallbackInfoMX(Pagination pagination)
      throws Exception {
    LoancallbackTaAF af = new LoancallbackTaAF();
    try {
      BorrowerInfoDTO borrowerInfoDTO = null;
      List borrowerList = null;
      List callbacklist = null;
      List taillist = new ArrayList();
      LoanFlowHead loanFlowHead = null;
      BigDecimal loanRate = null;
      String contractId = (String) pagination.getQueryCriterions().get(
          "contractId");
      String headId = (String) pagination.getQueryCriterions().get("headId");
      String types = (String) pagination.getQueryCriterions().get("type");
      String bizDate = "";
      String dateStart = "";
      String dateEnd = "";
      BigDecimal corpusInterest = new BigDecimal(0.00);// 提前还款后月还本息
      // 从PL110及PL111取信息
      borrowerList = borrowerAccDAO
          .queryBorrowerAccInfoByLoanKouAcc_LJ(contractId);
      if (!borrowerList.isEmpty()) {
        borrowerInfoDTO = (BorrowerInfoDTO) borrowerList.get(0);
      }
      borrowerInfoDTO.setCardKind(BusiTools.getBusiValue(Integer
          .parseInt(borrowerInfoDTO.getCardKind()), BusiConst.DOCUMENTSSTATE));
      borrowerInfoDTO.setLoanMode(BusiTools.getBusiValue(Integer
          .parseInt(borrowerInfoDTO.getLoanMode()), BusiConst.PLRECOVERTYPE));
      String loanRepayDay = borrowerInfoDTO.getLoanRepayDay();// 还款日
      callbacklist = loanFlowTailDAO.queryLoanFlowTailByHeadId_LJ(headId);
      int count = callbacklist.size();
      if (!types.equals(BusiConst.PLBUSINESSTYPE_SINGLERECOVER + "")) {// 单笔回收
        count = callbacklist.size() - 1;
      }
      loanFlowHead = loanFlowHeadDAO.queryById(new Integer(headId));
      String olddeadline = null;
      String chgtype = null;
      if(types.equals("3")){
        olddeadline =loanFlowTailDAO.selectPL101_1_olddeadline_wsh(headId);
        chgtype=loanFlowTailDAO.selectPL101_1_type_wsh(headId);
      }
      
      if(olddeadline!=null&&!"".equals(olddeadline)){
        //原剩余期限
        af.setDead(olddeadline);
        //变更期限
        af.setChgMonth((new BigDecimal((Integer.parseInt(olddeadline)-Integer.parseInt(loanFlowHead.getDeadLine()))).abs()).toString());
      }
      if(chgtype!=null&&!"".equals(chgtype)){
        /**
         * 1.提前部分还款2.延长还款期限3.缩短还款期限4.提前部分还款、延长还款期限5.提前部分还款、缩短还款期限
         */
        String aheadTypeS="";
        if("1".equals(chgtype)){
          aheadTypeS="提前部分还款";
        }
        if("2".equals(chgtype)){
          aheadTypeS="延长还款期限";
        }
        if("3".equals(chgtype)){
          aheadTypeS=".缩短还款期限";
        }
        if("4".equals(chgtype)){
          aheadTypeS="提前部分还款、延长还款期限";
        }
        if("5".equals(chgtype)){
          aheadTypeS="提前部分还款、缩短还款期限";
        }
        af.setAheadTypeS(aheadTypeS);
      }
      String allowdays = "";
      bizDate = loanFlowHead.getBizDate();
      String yearMonth = bizDate.substring(0, 6);// 取出会计日期中的年月
      String year = yearMonth.substring(0, 4);
      String month = yearMonth.substring(4, 6);
      String day = bizDate.substring(6, bizDate.length());// 取出会计日期中的日
      String temp_bizDate = year + "-" + month + "-" + day;// 用来判断列表中的还款类型所转换的会计日期
      if (!callbacklist.isEmpty()) {
        for (int i = 0; i < count; i++) {
          ShouldBackListDTO dto = (ShouldBackListDTO) callbacklist.get(i);
          // 宽限天数
          if(i == 0)
            allowdays = loanBankParaDAO.queryParamExplain_LJ(Integer
                .valueOf(dto.getLoanBankId()), "A", "5");
          ShouldBackListDTO dto2 = new ShouldBackListDTO();
          dto2.setLoanKouYearmonth(dto.getLoanKouYearmonth());
          String loanRepayDay1 = this.getEndDay(dto.getLoanKouYearmonth(),
              loanRepayDay);
          String temp_date = dto.getLoanKouYearmonth().substring(0, 4) + "-"
              + dto.getLoanKouYearmonth().substring(4, 6) + "-" + loanRepayDay1;
          // 逾期天数
          int days = BusiTools.minusDate(temp_bizDate, temp_date);
          if (days <= 0) {
            days = 0;
          }
          String type = dto.getLoanKouType();
          if (type.equals("1")) {
            dto2.setLoanKouType("正常");
          } else if (type.equals("2")) {
            dto2.setLoanKouType("逾期");
          }
          if (i == 0) {
            dateStart = dto.getLoanKouYearmonth();
          }
          if (i == (count - 1)) {
            dateEnd = dto.getLoanKouYearmonth();
          }
          dto2.setShouldCorpus(dto.getShouldCorpus());
          dto2.setShouldInterest(dto.getShouldInterest());
          dto2.setPunishInterest(dto.getPunishInterest());
          dto2.setRealCorpus(dto.getRealCorpus());
          dto2.setRealInterest(dto.getRealInterest());
          dto2.setRealPunishInterest(dto.getRealPunishInterest());
          dto2.setCiMoney(dto.getShouldCorpus().add(dto.getShouldInterest())
              .add(dto.getPunishInterest()));
          dto2.setRealCiMoney(dto.getRealCorpus().add(dto.getRealInterest())
              .add(dto.getRealPunishInterest()));
          dto2.setLoanRate(dto.getLoanRate());
          if (dto.getLoanRate().doubleValue() != 0) {
            dto2.setShow_loanRate(dto.getLoanRate().multiply(
                new BigDecimal(100))
                + "%");
          } else {
            dto2.setShow_loanRate("");
          }
          if (dto2.getLoanRate().doubleValue() == 0) {
            dto2.setLoanRate(loanRate);
          }
          if (days > Integer.parseInt(allowdays))
            dto2.setDays(days + "");
          else 
            dto2.setDays("0");
          taillist.add(dto2);
        }
      }
      if (!types.equals(BusiConst.PLBUSINESSTYPE_SINGLERECOVER + "")) {// 单笔回收
        if (!callbacklist.isEmpty()) {
          ShouldBackListDTO dto = (ShouldBackListDTO) callbacklist
              .get(callbacklist.size() - 1);
          af.setAheadCorpus(dto.getShouldCorpus());
          af.setAheadInterest(dto.getShouldInterest());
        }
        String temp_date = "";
        String days = "";// 占用天数
        String loanRepayDay1 = this.getEndDay(bizDate, loanRepayDay);
        // 占用天数
        if (Integer.parseInt(day) < Integer.parseInt(loanRepayDay1)) {
          if (Integer.parseInt(month) == 1) {
            month = "12";
            year = Integer.parseInt(year) - 1 + "";
          } else {
            month = Integer.parseInt(month) - 1 + "";
          }
          temp_date = year + "-" + month + "-" + loanRepayDay1;
          days = BusiTools.minusDate(temp_bizDate, temp_date) + "";
        } else {
          temp_date = year + "-" + month + "-" + loanRepayDay1;
          days = BusiTools.minusDate(temp_bizDate, temp_date) + "";
        }
        af.setLoanPoundageMoney(loanFlowHead.getLoanPoundageMoney());
        af.setDeadLine(loanFlowHead.getDeadLine());
        af.setDays(days);
      } else {
        af.setAheadCorpus(new BigDecimal(0.00));
        af.setAheadInterest(new BigDecimal(0.00));
        af.setLoanPoundageMoney(new BigDecimal(0.00));
      }
      // //提前还款后月还本息的值=（剩余本金-应还本金合-提前还款金额）*（1+月利率）新剩余期限*月利率/(1+月利率)新剩余期限-1
      // corpusInterest = CorpusinterestBS.getCorpusInterest(overplusLoanMoney,
      // loanRate, loanFlowHead.getDeadLine());
      af.setBorrowerInfoDTO(borrowerInfoDTO);
      af.setShouldBackList(taillist);
      af.setBizType(BusiTools.getBusiValue(Integer.parseInt(loanFlowHead
          .getBizType()), BusiConst.PLBUSINESSTYPE));
      af.setSumCorpus(loanFlowHead.getRealCorpus().add(
          loanFlowHead.getRealOverdueCorpus()));
      af.setSumInterest(loanFlowHead.getRealInterest().add(
          loanFlowHead.getRealOverdueInterest()).add(
          loanFlowHead.getRealPunishInterest()));
      af.setSumMoney(af.getSumCorpus().add(af.getSumInterest()));
      af.setOverOccurMoney(loanFlowHead.getOccurMoney());
      af.setRealMoney(af.getSumMoney().add(loanFlowHead.getOccurMoney()));
      af.setMonthYear(dateEnd);
    if(headId!=null&&!"".equals(headId)&&"3".equals(types)){
      corpusInterest=new BigDecimal( loanFlowTailDAO.selectPL101_1_interest_wsh(headId));
    }
     
      af.setCorpusInterest(corpusInterest);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return af;
  }

  /**
   * 回收维护打印信息
   * 
   * @param headId
   * @return
   */
  public LoancallbackTaAF findPrintCallbackInfo(String headId) {
    LoancallbackTaAF af = new LoancallbackTaAF();
    LoanFlowHead loanFlowHead = loanFlowHeadDAO.queryById(new Integer(headId));
    LoancallbackTaAF yearMonethAF = loanFlowTailDAO.queryYearMonth(headId);
    String beginyemonth = yearMonethAF.getBegainMonthYear();
    String endyemonth = yearMonethAF.getMonthYear();
    List taillist = loanFlowTailDAO.queryPrintLoanFlowTailByHeadId_LJ(headId);
    
    BorrowerAcc borrowerAcc=new BorrowerAcc();
    String contractId = "";
    BorrowerInfoDTO borrowerInfoDTO = null;
    String yearMonth = "";
    List borrowerList = null;
    
    if (!taillist.isEmpty()) {
      ShouldBackListDTO dto = (ShouldBackListDTO) taillist.get(0);
      ShouldBackListDTO dto1 = (ShouldBackListDTO) taillist
          .get(taillist.size() - 1);
      contractId = dto.getContractId();
      borrowerAcc=borrowerAccDAO.queryById(contractId);
      af.setCardNum(dto.getCardNum());
      yearMonth = dto1.getLoanKouYearmonth();
      String temploanKouType = dto.getLoanKouType();
      String batchNum = dto.getBatchNum();
      // try {
      // if(temploanKouType!=null && !"".equals(temploanKouType)){
      // String loanKouType =
      // BusiTools.getBusiValue(Integer.parseInt(temploanKouType)
      // ,BusiConst.GIVEBACK);
      // // dto.setLoanKouType(loanKouType);
      // }
      // } catch (Exception e) {
      // // TODO Auto-generated catch block
      // e.printStackTrace();
      // }
      if (batchNum != null && !"".equals(batchNum)) {
        dto.setYesOrNo("是");
        af.setBatchNum("是");
      } else {
        dto.setYesOrNo("否");
        af.setBatchNum("否");
      }
      // 从PL110及PL111取信息
      borrowerList = borrowerAccDAO
          .queryBorrowerAccInfoByLoanKouAcc_LJ(contractId);
      if (!borrowerList.isEmpty()) {
        borrowerInfoDTO = (BorrowerInfoDTO) borrowerList.get(0);
      }
      BigDecimal yga = new BigDecimal(0.00);
      BigDecimal ygb = new BigDecimal(0.00);
      BigDecimal ygc = new BigDecimal(0.00);
      BigDecimal ygd = new BigDecimal(0.00);
      BigDecimal yge = new BigDecimal(0.00);
      for (int i = 0; i < taillist.size(); i++) {
        ShouldBackListDTO shouldBackListDTO = (ShouldBackListDTO) taillist
            .get(i);
        if(shouldBackListDTO.getLoanKouType().equals("1")){//正常
          yga=yga.add(shouldBackListDTO.getRealCorpus());
          ygb=ygb.add(shouldBackListDTO.getRealInterest());
        }else if(shouldBackListDTO.getLoanKouType().equals("2")){//逾期
          ygc=ygc.add(shouldBackListDTO.getRealCorpus());
          ygd=ygd.add(shouldBackListDTO.getRealInterest());
        }
        yge=yge.add(shouldBackListDTO.getRealPunishInterest());
        String loanBankId = shouldBackListDTO.getLoanBankId();
        CollBank collBank = collBankDAO.getCollBankByCollBankid_(loanBankId);
        String loanBankName = collBank.getCollBankName();
        if (batchNum != null && !"".equals(batchNum)) {
          shouldBackListDTO.setYesOrNo("是");
          af.setBatchNum("是");
        } else {
          shouldBackListDTO.setYesOrNo("否");
          af.setBatchNum("否");
        }
        shouldBackListDTO.setLoanBankName(loanBankName);
      }
      af.setYga(yga.toString());
      af.setYgb(ygb.toString());
      af.setYgc(ygc.toString());
      af.setYgd(ygd.toString());
      af.setYge(yge.toString());
    }
    
    String loanBankId = loanFlowHead.getLoanBankId().toString();
    CollBank collBank = collBankDAO.getCollBankByCollBankid_(loanBankId);
    List acclist = loanBankDAO.queryLoanBankAccByBankId_LJ(loanBankId);
    Iterator it = acclist.iterator();
    Object obj[] = null;
    while (it.hasNext()) {
      obj = (Object[]) it.next();
      if (obj[0] != null) {
        af.setLoanAcc(obj[0].toString());
      }
      if (obj[1] != null) {
        af.setInterestAcc(obj[1].toString());
      }
    }
    af.setRealCorpus(loanFlowHead.getRealCorpus());
    af.setRealOverduCorpus(loanFlowHead.getRealOverdueCorpus());
    af.setSumCorpus(loanFlowHead.getRealCorpus().add(
        loanFlowHead.getRealOverdueCorpus()));
    af.setSumInterest(loanFlowHead.getRealInterest().add(
        loanFlowHead.getRealOverdueInterest()).add(
        loanFlowHead.getRealPunishInterest()));
    af.setOverOccurMoney(loanFlowHead.getOccurMoney());
    af.setInterest(loanFlowHead.getRealInterest());
    af.setOverdueInterest(loanFlowHead.getRealOverdueInterest());
    af.setPunishInterest(loanFlowHead.getRealPunishInterest());
    af.setMakeOP(securityDAO.queryByUserid(loanFlowHead.getMakePerson()));
    af.setClearOP(securityDAO.queryByUserid(loanFlowHead.getCheckPerson()));
    af.setClearAccountOP(securityDAO.queryByUserid(loanFlowHead.getClearAccPerson()));
    af.setBankName(collBank.getCollBankName());
    af.setShouldBackList(taillist);
    String bizType = loanFlowHead.getBizType();
    if (bizType.equals(String.valueOf(BusiConst.PLBUSINESSTYPE_SINGLERECOVER))) {
      af.setBizType("正常还款，");
      af.setBorrowerInfoDTO(borrowerInfoDTO);
      af.setMonthYear(yearMonth.substring(0, 4) + "年"
          + yearMonth.substring(4, 6) + "月");
    } else if (bizType.equals(String
        .valueOf(BusiConst.PLBUSINESSTYPE_PARTRECOVER))) {
//      af.setBizType("部份提前还款，");
      af.setBorrowerInfoDTO(borrowerInfoDTO);
      String str[]=loanFlowTailDAO.queryPl101_1Info(headId);
      af.setData_1(str[5]);
      if("1".equals(str[0])){
        af.setData_2("不改变");
        af.setData_11(String.valueOf(Integer.parseInt(borrowerInfoDTO.getLoanStartDate().substring(0, 4))+Integer.parseInt(borrowerInfoDTO.getLoanTimeLimit())/12)+borrowerInfoDTO.getLoanStartDate().substring(4,6)+String.valueOf(str[1].subSequence(6, 8)));
      }else{
        af.setData_2("改变");
        
//          String data_11 = String.valueOf(Integer.parseInt(borrowerInfoDTO
//            .getLoanStartDate().substring(0, 4))
//            + (Integer.parseInt(str[6]) - Integer.parseInt(str[3]))
//            / 12)
//            + borrowerInfoDTO.getLoanStartDate().substring(4, 6)
//            + String.valueOf(str[1].subSequence(6, 8));
        //pl101-1的年月日+剩余的期限
        int year=(Integer.parseInt(str[3])% 12+Integer.parseInt(str[1].substring(4, 6)))/12;
        int month=(Integer.parseInt(str[3])% 12+Integer.parseInt(str[1].substring(4, 6)))%12;
        String data_11 = String.valueOf(Integer.parseInt(str[1].substring(0, 4))
            + Integer.parseInt(str[3])/ 12+year)
            + String.valueOf(month)
            + String.valueOf(str[1].subSequence(6, 8));
        af.setData_11(data_11);
      }
      if("1".equals(str[0])){
        af.setBizType("部份提前还款,");
      }
      if("2".equals(str[0])){
        af.setBizType("延长还款期限,");
      }
      if("3".equals(str[0])){
        af.setBizType("缩短还款期限,");
      }
      if("4".equals(str[0])){
        af.setBizType("部分提前还款、延长还款期限,");
      }
      if("5".equals(str[0])){
        af.setBizType("部分提前还款、缩短还款期,");
      }
      af.setData_4(str[3]);
      af.setData_5(str[4]);
      af.setData_6(str[6]);
      af.setData_7(borrowerAcc.getLoanRepayDay().toString());
      System.out.println(borrowerAcc.getReserveaA());
      af.setData_8(new BigDecimal("0" + (borrowerAcc.getReserveaA() == null ? ""
          : borrowerAcc.getReserveaA())).multiply(new BigDecimal(1000))
          .toString());
      BigDecimal loanRate=new BigDecimal("0.00");
      if (Integer.parseInt(str[3])-Integer.parseInt(str[6])+ Integer.parseInt(borrowerInfoDTO.getLoanTimeLimit())> 60) {
        loanRate = loanRateDAO.findMontRate(borrowerInfoDTO.getOfficeCode(),
            String.valueOf(BusiConst.PLLOANTYPE_FIVEUP));
      } else {
        // 否——计算提前还款月还本息取利率到PL001中loan_rate_type=0的最新利率
        loanRate = loanRateDAO.findMontRate(borrowerInfoDTO.getOfficeCode(),
            String.valueOf(BusiConst.PLLOANTYPE_FIVE));
      }
      af.setData_9(loanRate.multiply(new BigDecimal(1000)).toString());
      if(String.valueOf(str[1].subSequence(6, 7)).equals("0")){
        af.setData_10(String.valueOf(str[1].subSequence(7, 8)));
      }else{
        af.setData_10(String.valueOf(str[1].subSequence(6, 8)));
      }
      
      af.setMonthYear(yearMonth.substring(0, 4) + "年"
          + yearMonth.substring(4, 6) + "月");
      af.setTqhklx(loanFlowHead.getRealInterest());
      af.setTqhkbj(loanFlowHead.getRealCorpus());
    } else if (bizType
        .equals(String.valueOf(BusiConst.PLBUSINESSTYPE_ALLCLEAR))) {
      af.setBizType("一次性清还，");
      af.setBorrowerInfoDTO(borrowerInfoDTO);
      af.setMonthYear(yearMonth.substring(0, 4) + "年"
          + yearMonth.substring(4, 6) + "月");
      af.setTqhklx(loanFlowHead.getRealInterest());
      af.setTqhkbj(loanFlowHead.getRealCorpus());
    } else if (bizType.equals(String
        .valueOf(BusiConst.PLBUSINESSTYPE_SOMERECOVER))) {
      af.setBizType("批量回收，");
      af.setMonthYear(loanFlowHead.getBizDate().substring(0, 4) + "年"
          + loanFlowHead.getBizDate().substring(4, 6) + "月");
    }
    if (beginyemonth != null && !"".equals(beginyemonth)) {
      af.setBegainMonthYear(beginyemonth.substring(0, 4) + "年"
          + beginyemonth.substring(4, 6) + "月");
    }
    af.setMonths(taillist.size() + "");
    String bizDate = loanFlowHead.getBizDate();
    af.setBizDate(bizDate.substring(0, 4) + "-" + bizDate.substring(4, 6) + "-"
        + bizDate.substring(6, 8));
    af.setRealMoney(af.getSumCorpus().add(af.getSumInterest()).add(
        af.getOverOccurMoney()));
    af.setDocNum(loanFlowHead.getDocNum());
    af.setNoteNum(loanFlowHead.getNoteNum());
    return af;
  }

  /**
   * 计算逾期天数
   * 
   * @param bizDate 业务日期
   * @param yearMonth 还款年月
   * @param loanRepayDay 还款日
   * @return
   */
  public int getDays(String bizDate, String yearMonth, String loanRepayDay) {
    int days = 0;
    loanRepayDay = this.getEndDay(yearMonth, loanRepayDay);
    String temp_date = yearMonth.substring(0, 4) + "-"
        + yearMonth.substring(4, 6) + "-" + loanRepayDay;
    String temp_bizDate = bizDate.substring(0, 4) + "-"
        + bizDate.substring(4, 6) + "-" + bizDate.substring(6, 8);
    // 逾期天数
    days = BusiTools.minusDate(temp_bizDate, temp_date);
    return days;
  }

  /**
   * 回收维护明细列表（批量）
   * 
   * @param pagination
   * @return
   */
  public List findCallbackBatchMX(Pagination pagination) throws Exception {
    String headId = (String) pagination.getQueryCriterions().get("headId");
    String orderBy = (String) pagination.getOrderBy();
    String order = (String) pagination.getOrderother();
    int start = pagination.getFirstElementOnPage() - 1;
    int pageSize = pagination.getPageSize();
    List temp_list = null;
    List list = new ArrayList();
    try {
      LoanFlowHead loanFlowHead = loanFlowHeadDAO
          .queryById(new Integer(headId));
      String bizDate = loanFlowHead.getBizDate();
      temp_list = loanFlowTailDAO.queryFlowTailByHeadIdMX_LJ(headId, orderBy,
          order, start, pageSize);
      
      if (!temp_list.isEmpty()) {
        String allowdays = "";
        for (int i = 0; i < temp_list.size(); i++) {
          ShouldBackListDTO dto = (ShouldBackListDTO) temp_list.get(i);
          // 宽限天数
          if(i == 0)
            allowdays = loanBankParaDAO.queryParamExplain_LJ(Integer
                .valueOf(dto.getLoanBankId()), "A", "5");
          String loanRepayDay = this.getEndDay(dto.getLoanKouYearmonth(), dto
              .getLoanRepayDay());
          if (dto.getLoanKouType().equals("1")) {
            dto.setLoanKouType("正常");
          } else if (dto.getLoanKouType().equals("2")) {
            dto.setLoanKouType("逾期");
          }
          int days = this.getDays(bizDate, dto.getLoanKouYearmonth(),
              loanRepayDay);
          if (days <= 0) {
            days = 0;
          }
          if (dto.getMoney().doubleValue() <= 0) {
            dto.setMoney(new BigDecimal(0.00));
          }
          if(days > Integer.parseInt(allowdays)) 
            dto.setDays(days + "");
          else
            dto.setDays("0");
          list.add(dto);
        }
      }
      int count = loanFlowTailDAO.queryFlowTailCountsByHeadIdMX_LJ(headId);
      pagination.setNrOfElements(count);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return list;
  }

  /**
   * 回收维护明细列表打印（批量）
   * 
   * @param pagination
   * @return
   */
  public List findCallbackBatchMXPrint(Pagination pagination) throws Exception {
    String headId = (String) pagination.getQueryCriterions().get("headId");
    String orderBy = (String) pagination.getOrderBy();
    ;
    String order = (String) pagination.getOrderother();
    int start = pagination.getFirstElementOnPage() - 1;
    int pageSize = pagination.getPageSize();
    List temp_list = null;
    List list = new ArrayList();
    try {
      LoanFlowHead loanFlowHead = loanFlowHeadDAO
          .queryById(new Integer(headId));
      String bizDate = loanFlowHead.getBizDate();
      temp_list = loanFlowTailDAO.queryFlowTailByHeadIdMXPrint_LJ(headId,
          orderBy, order, start, pageSize);
      if (!temp_list.isEmpty()) {
        for (int i = 0; i < temp_list.size(); i++) {
          ShouldBackListDTO dto = (ShouldBackListDTO) temp_list.get(i);
          String loanRepayDay = this.getEndDay(dto.getLoanKouYearmonth(), dto
              .getLoanRepayDay());
          if (dto.getLoanKouType().equals("1")) {
            dto.setLoanKouType("正常");
          } else if (dto.getLoanKouType().equals("2")) {
            dto.setLoanKouType("逾期");
          }
          int days = this.getDays(bizDate, dto.getLoanKouYearmonth(),
              loanRepayDay);
          if (days <= 0) {
            days = 0;
          }
          if (dto.getMoney().doubleValue() <= 0) {
            dto.setMoney(new BigDecimal(0.00));
          }
          dto.setDays(String.valueOf(days));
          list.add(dto);
        }
      }
      int count = loanFlowTailDAO.queryFlowTailCountsByHeadIdMX_LJ(headId);
      pagination.setNrOfElements(count);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return list;
  }

  /**
   * 回收维护明细列表合计（批量）
   * 
   * @param pagination
   * @return
   */
  public ShouldBackListDTO findCallbackBatchMXTotal(Pagination pagination) {
    String headId = (String) pagination.getQueryCriterions().get("headId");
    ShouldBackListDTO dto = null;
    List list = null;
    list = loanFlowTailDAO.queryFlowTailTotalByHeadIdMX_LJ(headId);
    if (!list.isEmpty()) {
      dto = (ShouldBackListDTO) list.get(0);
      if (dto.getMoney().doubleValue() <= 0) {
        dto.setMoney(new BigDecimal(0.00));
      }
    }
    return dto;
  }

  /**
   * 取得年结年份
   * 
   * @param loanBankId
   * @return
   */
  public String getClearYear(String loanBankId) {
    String year = "";// 年结年份
    year = loanBankDAO.queryYearClearByBankId_sy(loanBankId);
    return year;
  }

  public LoancallbackTaAF findShouldLoancallbackInfo_wuht(String loanMode,
      String suminterest, String corpusInterest, String monthYear,
      String contractId, String deadLine, String sumCorpus,
      SecurityInfo securityInfo) throws Exception {
    LoancallbackTaAF loancallbackTaAF = new LoancallbackTaAF();
    try {
      // 吴洪涛修改//2007-3-11
      if (loanMode == "等额本金") {

        loancallbackTaAF = this
            .queryTdShow_loancallbackByCriterionsPlrecovertype_corpus_wuht(
                corpusInterest, monthYear, contractId, deadLine, sumCorpus,
                securityInfo);
        loancallbackTaAF.setOverplusInterestAll(loancallbackTaAF
            .getOverplusInterestAll());// 剩余利息
        List listLoanFlowTail = loanBankDAO.queryLoanFlowTail_wuht(contractId);
        BigDecimal realInterest = new BigDecimal(0.0);
        BigDecimal interestAll = new BigDecimal(0.0);
        BigDecimal sumInterest = new BigDecimal(suminterest);
        if (listLoanFlowTail != null && listLoanFlowTail.size() > 0) {
          for (int i = 0; i < listLoanFlowTail.size(); i++) {
            LoanFlowTail loanFlowTail = (LoanFlowTail) listLoanFlowTail.get(i);
            realInterest = realInterest.add(loanFlowTail.getRealInterest());
          }
        }
        interestAll = interestAll.add(realInterest).add(sumInterest).add(
            loancallbackTaAF.getOverplusInterestAll());// 总利息
        loancallbackTaAF.setInterestAll(interestAll);// 总利息

      } else {

        loancallbackTaAF = this.queryTdShow_loancallbackByCriterions_wuht(
            corpusInterest, monthYear, contractId, deadLine, sumCorpus,
            securityInfo);
        loancallbackTaAF.setOverplusInterestAll(loancallbackTaAF
            .getOverplusInterestAll());// 剩余利息
        List listLoanFlowTail = loanBankDAO.queryLoanFlowTail_wuht(contractId);
        BigDecimal realInterest = new BigDecimal(0.0);
        BigDecimal interestAll = new BigDecimal(0.0);
        BigDecimal sumInterest = new BigDecimal(suminterest);
        if (listLoanFlowTail != null && listLoanFlowTail.size() > 0) {
          for (int i = 0; i < listLoanFlowTail.size(); i++) {
            LoanFlowTail loanFlowTail = (LoanFlowTail) listLoanFlowTail.get(i);
            realInterest = realInterest.add(loanFlowTail.getRealInterest());
          }
        }
        interestAll = interestAll.add(realInterest).add(sumInterest).add(
            loancallbackTaAF.getOverplusInterestAll());// 总利息
        loancallbackTaAF.setInterestAll(interestAll);// 总利息
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    // 吴洪涛修改//2007-3-11

    return loancallbackTaAF;
  }

  // 吴洪涛修改//2007-3-11贷款中，在一些办理业务页面中，如提前还贷，应该能显示出借款人的总利息，剩余利息，而且有个试算，能够计算出部分提前还款后的，总利息，剩余利息
  public LoancallbackTaAF queryTdShow_loancallbackByCriterions_wuht(
      String corpusinterest, String monthYear, String contractId,
      String deadLine, String sumCorpus, SecurityInfo securityInfo)
      throws Exception, BusinessException {
    // TODO Auto-generated method stub
    // String srealinterest =
    // (String)request.getParameter("srealinterest");//剩余利息
    List list = new ArrayList();

    LoancallbackTaAF loancallbackTaAF = new LoancallbackTaAF();
    String timelimit = deadLine;// 提前还款后剩余期限
    String loanRate = "";// 月利率
    String corpusInterestloancallback = corpusinterest;// 提前还款后月还本息
    // String monthYear = monthYear;// 还至年月
    // String contractId = (String) request.getParameter("contractId");// 合同编号
    String year = "";// 贷款期限
    // String sumCorpus = (String) request.getParameter("sumCorpus");// 本次总还款本金
    String overplusLoanMoney = "";// 剩余本金

    List listborrowerAcc = loanBankDAO
        .queryBorrowerAccByCcontractId_wuht(contractId);

    if (listborrowerAcc != null && listborrowerAcc.size() > 0) {
      BorrowerAcc borrowerAcc = (BorrowerAcc) listborrowerAcc.get(0);
      overplusLoanMoney = borrowerAcc.getOverplusLoanMoney().subtract(
          new BigDecimal(sumCorpus)).toString();
      year = borrowerAcc.getLoanTimeLimit();
    }

    // 还至日为空
    try {
      /**
       * 还至日为空 从pl203中查出最大的年月，就是还款最后一个月，这样就需要加＋1
       * 若pl203中未查出值，就说明它是刚刚贷款的还未还过款。这样他的年月就应该从pl201中最小的
       */
      /** 还至日不为空 年月加＋1 * */
      if (monthYear == null || monthYear.equals("")) {
        monthYear = loanBankDAO.queryLoanFlowTaiMaxlYearMonth_wuht(contractId);
        if (monthYear == null || monthYear.equals("")) {
          monthYear = loanBankDAO
              .queryRestoreLoanMinlYearMonth_wuht(contractId);
        } else {
          monthYear = monthYear.substring(0, 6);
          if (monthYear.substring(4, 6).equals("12")
              || monthYear.substring(4, 6) == "12") {
            monthYear = Integer.parseInt(monthYear.substring(0, 4)) + 1 + "01";
          } else {
            monthYear = Integer.parseInt(monthYear) + 1 + "";
          }
        }
      } else {
        monthYear = monthYear.substring(0, 6);
        if (monthYear.substring(4, 6).equals("12")
            || monthYear.substring(4, 6) == "12") {
          monthYear = Integer.parseInt(monthYear.substring(0, 4)) + 1 + "01";
        } else {
          monthYear = Integer.parseInt(monthYear) + 1 + "";
        }
      }
      List listRestoreLoan = loanBankDAO.queryRestoreLoanbyCriterions_wuht(
          contractId, monthYear.substring(0, 6));

      if (listRestoreLoan != null && listRestoreLoan.size() > 0) {
        RestoreLoan borrowerAcc = (RestoreLoan) listRestoreLoan.get(0);
        loanRate = borrowerAcc.getLoanRate().toString();
        // 页面上显示

      }
      // bgQueryTdShowAF.setSrealinterest(srealinterest);

      BigDecimal corpusInterest = new BigDecimal(0.00);// 月还本息
      BigDecimal loanInterestMonth = new BigDecimal(0.00);// 月利息
      BigDecimal loanMoneyMonth = new BigDecimal(0.00);// 月本金

      if (listRestoreLoan != null && listRestoreLoan.size() > 0) {
        for (int i = Integer.parseInt(monthYear.substring(4, 6)); i <= 12; i++) {
          BigDecimal corpusInterest2 = new BigDecimal(
              corpusInterestloancallback);// 月还本息
          BorrowerInfoDTO borrowerInfoDTO2 = new BorrowerInfoDTO();
          BigDecimal temp = new BigDecimal(1.00);
          loanInterestMonth = new BigDecimal(overplusLoanMoney).multiply(
              new BigDecimal(loanRate)).divide(temp, 2,
              BigDecimal.ROUND_HALF_UP);

          // 月本金
          if (i == Integer.parseInt(timelimit) - 1) {
            loanMoneyMonth = new BigDecimal(overplusLoanMoney);
            corpusInterest2 = loanMoneyMonth.add(loanInterestMonth);
          } else {
            loanMoneyMonth = corpusInterest2.subtract(loanInterestMonth)
                .divide(temp, 2, BigDecimal.ROUND_HALF_UP);
          }
          // 剩余本金
          if (i == Integer.parseInt(timelimit) - 1) {

          } else {
            overplusLoanMoney = new BigDecimal(overplusLoanMoney).subtract(
                loanMoneyMonth).divide(temp, 2, BigDecimal.ROUND_HALF_UP)
                .toString();
          }
          // 年月
          if (Integer.parseInt(monthYear.substring(4, 6)) == 12) {
            monthYear = Integer.parseInt(monthYear.substring(0, 4)) + 1 + "01";
          } else {
            monthYear = Integer.parseInt(monthYear.substring(0, 6)) + 1 + "";
          }

          borrowerInfoDTO2.setLoanInterestMonth(loanInterestMonth.toString());
          list.add(borrowerInfoDTO2);
        }
      }
      // 在此合同编号在pl201中没有了。从下一年开始
      BigDecimal maxRate = new BigDecimal(0.00);// 最大利率
      String offince = null;// 办事处
      List listBorrower = loanBankDAO.queryBorrower_wuht(contractId);
      if (listBorrower != null && listBorrower.size() > 0) {
        Borrower borrower = (Borrower) listBorrower.get(0);
        offince = borrower.getOffice();
      }

      BorrowerAcc borrowerAccLoanRateType = (BorrowerAcc) listborrowerAcc
          .get(0);
      if (Integer.parseInt(borrowerAccLoanRateType.getLoanRateType()) == 1) {
        maxRate = loanBankDAO.queryLoanLoanRateMaxRate_wuht(offince, "1");
      } else {
        maxRate = loanBankDAO.queryLoanLoanRateMaxRate_wuht(offince, "0");
      }

      // 求出剩余期限
      if (list != null && list.size() > 0) {
        timelimit = Integer.parseInt(timelimit) - list.size() + "";
      }
      if (Integer.parseInt(timelimit) > 0) {
        corpusInterest = this.corpusInterest(new BigDecimal(overplusLoanMoney),
            maxRate, timelimit);

        for (int i = 0; i < Integer.parseInt(timelimit); i++) {
          BorrowerInfoDTO borrowerInfoDTO = new BorrowerInfoDTO();
          BigDecimal temp = new BigDecimal(1.00);
          // // 月利息
          loanInterestMonth = new BigDecimal(overplusLoanMoney).multiply(
              maxRate).divide(temp, 2, BigDecimal.ROUND_HALF_UP);
          // 月本金
          if (i == Integer.parseInt(timelimit) - 1) {
            loanMoneyMonth = new BigDecimal(overplusLoanMoney);
          } else {
            loanMoneyMonth = corpusInterest.subtract(loanInterestMonth).divide(
                temp, 2, BigDecimal.ROUND_HALF_UP);
          }
          // 剩余本金
          if (i == Integer.parseInt(timelimit) - 1) {

          } else {
            overplusLoanMoney = new BigDecimal(overplusLoanMoney).subtract(
                loanMoneyMonth).divide(temp, 2, BigDecimal.ROUND_HALF_UP)
                .toString();
          }
          // 年月
          if (Integer.parseInt(monthYear.substring(4, 6)) == 12) {
            monthYear = Integer.parseInt(monthYear.substring(0, 4)) + 1 + "01";
          } else {
            monthYear = Integer.parseInt(monthYear.substring(0, 6)) + 1 + "";
          }

          borrowerInfoDTO.setLoanInterestMonth(loanInterestMonth.toString());

          list.add(borrowerInfoDTO);
        }
      }

      BigDecimal loanInterestMonthSum = new BigDecimal(0);
      if (list != null && list.size() > 0) {
        for (int j = 0; j < list.size(); j++) {
          BorrowerInfoDTO borrowerInfoDTO = (BorrowerInfoDTO) list.get(j);
          loanInterestMonthSum = loanInterestMonthSum.add(new BigDecimal(
              borrowerInfoDTO.getLoanInterestMonth()));
        }
      }

      // bgQueryTdShowAF.setCount(Integer.parseInt(timelimit));
      loancallbackTaAF.setOverplusInterestAll(loanInterestMonthSum);

    } catch (Exception e) {
      e.printStackTrace();
    }

    return loancallbackTaAF;
  }

  // 吴洪涛修改//2007-3-11单笔回收求剩余利息的方法
  public LoancallbackTaAF queryTdShow_loancallbackByCriterions_wuht2(
      String corpusinterest, String monthYear, String contractId,
      String deadLine, String sumCorpus, SecurityInfo securityInfo)
      throws Exception, BusinessException {
    List list = new ArrayList();

    LoancallbackTaAF loancallbackTaAF = new LoancallbackTaAF();
    String timelimit = deadLine;// 提前还款后剩余期限
    String loanRate = "";// 月利率
    String corpusInterestloancallback = corpusinterest;// 提前还款后月还本息
    String year = "";// 贷款期限
    String overplusLoanMoney = "";// 剩余本金

    List listborrowerAcc = loanBankDAO
        .queryBorrowerAccByCcontractId_wuht(contractId);

    if (listborrowerAcc != null && listborrowerAcc.size() > 0) {
      BorrowerAcc borrowerAcc = (BorrowerAcc) listborrowerAcc.get(0);
      overplusLoanMoney = borrowerAcc.getOverplusLoanMoney().subtract(
          new BigDecimal(sumCorpus)).toString();
      year = borrowerAcc.getLoanTimeLimit();
    }
    // 还至日为空
    try {
      /**
       * 还至日为空 从pl203中查出最大的年月，就是还款最后一个月，这样就需要加＋1
       * 若pl203中未查出值，就说明它是刚刚贷款的还未还过款。这样他的年月就应该从pl201中最小的
       */
      /** 还至日不为空 年月加＋1 * */
      if (monthYear == null || monthYear.equals("")) {
        monthYear = loanBankDAO.queryLoanFlowTaiMaxlYearMonth_wuht(contractId);
        if (monthYear == null || monthYear.equals("")) {
          monthYear = loanBankDAO
              .queryRestoreLoanMinlYearMonth_wuht(contractId);
        } else {
          monthYear = monthYear.substring(0, 6);
          if (monthYear.substring(4, 6).equals("12")
              || monthYear.substring(4, 6) == "12") {
            monthYear = Integer.parseInt(monthYear.substring(0, 4)) + 1 + "01";
          } else {
            monthYear = Integer.parseInt(monthYear) + 1 + "";
          }
        }
      } else {
        monthYear = monthYear.substring(0, 6);
        if (monthYear.substring(4, 6).equals("12")
            || monthYear.substring(4, 6) == "12") {
          monthYear = Integer.parseInt(monthYear.substring(0, 4)) + 1 + "01";
        } else {
          monthYear = Integer.parseInt(monthYear) + 1 + "";
        }
      }
      List listRestoreLoan = loanBankDAO.queryRestoreLoanbyCriterions_wuht(
          contractId, monthYear.substring(0, 6));

      if (listRestoreLoan != null && listRestoreLoan.size() > 0) {
        RestoreLoan borrowerAcc = (RestoreLoan) listRestoreLoan.get(0);
        loanRate = borrowerAcc.getLoanRate().toString();
        // 页面上显示

      }
      // bgQueryTdShowAF.setSrealinterest(srealinterest);

      BigDecimal corpusInterest = new BigDecimal(0.00);// 月还本息
      BigDecimal loanInterestMonth = new BigDecimal(0.00);// 月利息
      BigDecimal loanMoneyMonth = new BigDecimal(0.00);// 月本金
      if (listRestoreLoan != null && listRestoreLoan.size() > 0) {
        for (int i = Integer.parseInt(monthYear.substring(4, 6)); i <= 12; i++) {
          // BigDecimal corpusInterest2 = new BigDecimal(
          // corpusInterestloancallback);// 月还本息
          BorrowerInfoDTO borrowerInfoDTO2 = new BorrowerInfoDTO();
          BigDecimal temp = new BigDecimal(1.00);
          // loanInterestMonth = new BigDecimal(overplusLoanMoney).multiply(
          // new BigDecimal(loanRate)).divide(temp, 2,
          // BigDecimal.ROUND_HALF_UP);
          // System.out.println(listRestoreLoan.size()+"------listRestoreLoan.size()");
          // // 月本金
          // if (i == Integer.parseInt(timelimit) - 1) {
          // loanMoneyMonth = new BigDecimal(overplusLoanMoney);
          // corpusInterest2 = loanMoneyMonth.add(loanInterestMonth);
          // } else {
          // loanMoneyMonth = corpusInterest2.subtract(loanInterestMonth)
          // .divide(temp, 2, BigDecimal.ROUND_HALF_UP);
          // }

          // // 年月
          List listRestoreLoan2 = loanBankDAO
              .queryRestoreLoanbyCriterions_wuht(contractId, monthYear
                  .substring(0, 6));
          RestoreLoan restoreLoan = (RestoreLoan) listRestoreLoan2.get(0);
          loanInterestMonth = restoreLoan.getShouldInterest();
          // 剩余本金
          if (i == Integer.parseInt(timelimit) - 1) {

          } else {
            overplusLoanMoney = new BigDecimal(overplusLoanMoney).subtract(
                restoreLoan.getShouldCorpus()).divide(temp, 2,
                BigDecimal.ROUND_HALF_UP).toString();
          }
          if (Integer.parseInt(monthYear.substring(4, 6)) == 12) {
            monthYear = Integer.parseInt(monthYear.substring(0, 4)) + 1 + "01";
          } else {
            monthYear = Integer.parseInt(monthYear.substring(0, 6)) + 1 + "";
          }
          //            
          borrowerInfoDTO2.setLoanInterestMonth(loanInterestMonth.toString());
          list.add(borrowerInfoDTO2);
        }
      }
      // 在此合同编号在pl201中没有了。从下一年开始
      BigDecimal maxRate = new BigDecimal(0.00);// 最大利率
      String offince = null;// 办事处
      List listBorrower = loanBankDAO.queryBorrower_wuht(contractId);
      if (listBorrower != null && listBorrower.size() > 0) {
        Borrower borrower = (Borrower) listBorrower.get(0);
        offince = borrower.getOffice();
      }

      BorrowerAcc borrowerAccLoanRateType = (BorrowerAcc) listborrowerAcc
          .get(0);
      if (Integer.parseInt(borrowerAccLoanRateType.getLoanRateType()) == 1) {
        maxRate = loanBankDAO.queryLoanLoanRateMaxRate_wuht(offince, "1");
      } else {
        maxRate = loanBankDAO.queryLoanLoanRateMaxRate_wuht(offince, "0");
      }

      // 求出剩余期限

      if (list != null && list.size() > 0) {
        timelimit = Integer.parseInt(timelimit) - list.size() + "";
      }
      if (Integer.parseInt(timelimit) > 0) {
        corpusInterest = this.corpusInterest(new BigDecimal(overplusLoanMoney),
            maxRate, timelimit);
        for (int i = 0; i < Integer.parseInt(timelimit); i++) {
          BorrowerInfoDTO borrowerInfoDTO = new BorrowerInfoDTO();
          BigDecimal temp = new BigDecimal(1.00);
          // // 月利息
          loanInterestMonth = new BigDecimal(overplusLoanMoney).multiply(
              maxRate).divide(temp, 2, BigDecimal.ROUND_HALF_UP);
          // 月本金
          if (i == Integer.parseInt(timelimit) - 1) {
            loanMoneyMonth = new BigDecimal(overplusLoanMoney);
          } else {
            loanMoneyMonth = corpusInterest.subtract(loanInterestMonth).divide(
                temp, 2, BigDecimal.ROUND_HALF_UP);
          }
          // 剩余本金
          if (i == Integer.parseInt(timelimit) - 1) {

          } else {
            overplusLoanMoney = new BigDecimal(overplusLoanMoney).subtract(
                loanMoneyMonth).divide(temp, 2, BigDecimal.ROUND_HALF_UP)
                .toString();
          }
          // 年月
          if (Integer.parseInt(monthYear.substring(4, 6)) == 12) {
            monthYear = Integer.parseInt(monthYear.substring(0, 4)) + 1 + "01";
          } else {
            monthYear = Integer.parseInt(monthYear.substring(0, 6)) + 1 + "";
          }

          borrowerInfoDTO.setLoanInterestMonth(loanInterestMonth.toString());

          list.add(borrowerInfoDTO);
        }
      }

      BigDecimal loanInterestMonthSum = new BigDecimal(0);
      if (list != null && list.size() > 0) {
        for (int j = 0; j < list.size(); j++) {
          BorrowerInfoDTO borrowerInfoDTO = (BorrowerInfoDTO) list.get(j);
          loanInterestMonthSum = loanInterestMonthSum.add(new BigDecimal(
              borrowerInfoDTO.getLoanInterestMonth()));

        }
      }
      loancallbackTaAF.setOverplusInterestAll(loanInterestMonthSum);

    } catch (Exception e) {
      e.printStackTrace();
    }

    return loancallbackTaAF;
  }

  // 吴洪涛修改//2007-3-11等额本金 单笔回收求剩余利息的方法
  public LoancallbackTaAF queryTdShow_loancallbackByCriterionsPlrecovertype_corpus_wuht2(
      String shouldCorpus, String monthYear, String contractId,
      String deadLine, String sumCorpus, SecurityInfo securityInfo)
      throws Exception, BusinessException {
    List list = new ArrayList();

    LoancallbackTaAF loancallbackTaAF = new LoancallbackTaAF();
    String timelimit = deadLine;// 提前还款后剩余期限
    String loanRate = "";// 月利率
    // String corpusInterestloancallback = corpusinterest;// 提前还款后月还本息
    String year = "";// 贷款期限
    String overplusLoanMoney = "";// 剩余本金

    List listborrowerAcc = loanBankDAO
        .queryBorrowerAccByCcontractId_wuht(contractId);

    if (listborrowerAcc != null && listborrowerAcc.size() > 0) {
      BorrowerAcc borrowerAcc = (BorrowerAcc) listborrowerAcc.get(0);
      overplusLoanMoney = borrowerAcc.getOverplusLoanMoney().subtract(
          new BigDecimal(sumCorpus)).toString();
      year = borrowerAcc.getLoanTimeLimit();
    }
    System.out.println(overplusLoanMoney + "---333---overplusLoanMoney");
    // 还至日为空
    try {
      /**
       * 还至日为空 从pl203中查出最大的年月，就是还款最后一个月，这样就需要加＋1
       * 若pl203中未查出值，就说明它是刚刚贷款的还未还过款。这样他的年月就应该从pl201中最小的
       */
      /** 还至日不为空 年月加＋1 * */
      if (monthYear == null || monthYear.equals("")) {
        monthYear = loanBankDAO.queryLoanFlowTaiMaxlYearMonth_wuht(contractId);
        if (monthYear == null || monthYear.equals("")) {
          monthYear = loanBankDAO
              .queryRestoreLoanMinlYearMonth_wuht(contractId);
        } else {
          monthYear = monthYear.substring(0, 6);
          if (monthYear.substring(4, 6).equals("12")
              || monthYear.substring(4, 6) == "12") {
            monthYear = Integer.parseInt(monthYear.substring(0, 4)) + 1 + "01";
          } else {
            monthYear = Integer.parseInt(monthYear) + 1 + "";
          }
        }
      } else {
        monthYear = monthYear.substring(0, 6);
        if (monthYear.substring(4, 6).equals("12")
            || monthYear.substring(4, 6) == "12") {
          monthYear = Integer.parseInt(monthYear.substring(0, 4)) + 1 + "01";
        } else {
          monthYear = Integer.parseInt(monthYear) + 1 + "";
        }
      }
      List listRestoreLoan = loanBankDAO.queryRestoreLoanbyCriterions_wuht(
          contractId, monthYear.substring(0, 6));

      if (listRestoreLoan != null && listRestoreLoan.size() > 0) {
        RestoreLoan borrowerAcc = (RestoreLoan) listRestoreLoan.get(0);
        loanRate = borrowerAcc.getLoanRate().toString();
        // 页面上显示

      }
      // bgQueryTdShowAF.setSrealinterest(srealinterest);

      BigDecimal loanInterestMonth = new BigDecimal(0.00);// 月利息
      BigDecimal loanMoneyMonth = new BigDecimal(0.00);// 月本金
      if (listRestoreLoan != null && listRestoreLoan.size() > 0) {
        for (int i = Integer.parseInt(monthYear.substring(4, 6)); i <= 12; i++) {
          // BigDecimal corpusInterest2 = new BigDecimal(
          // corpusInterestloancallback);// 月还本息
          BorrowerInfoDTO borrowerInfoDTO2 = new BorrowerInfoDTO();
          BigDecimal temp = new BigDecimal(1.00);
          // loanInterestMonth = new BigDecimal(overplusLoanMoney).multiply(
          // new BigDecimal(loanRate)).divide(temp, 2,
          // BigDecimal.ROUND_HALF_UP);
          // System.out.println(listRestoreLoan.size()+"------listRestoreLoan.size()");
          // // 月本金
          // if (i == Integer.parseInt(timelimit) - 1) {
          // loanMoneyMonth = new BigDecimal(overplusLoanMoney);
          // corpusInterest2 = loanMoneyMonth.add(loanInterestMonth);
          // } else {
          // loanMoneyMonth = corpusInterest2.subtract(loanInterestMonth)
          // .divide(temp, 2, BigDecimal.ROUND_HALF_UP);
          // }

          // // 年月
          List listRestoreLoan2 = loanBankDAO
              .queryRestoreLoanbyCriterions_wuht(contractId, monthYear
                  .substring(0, 6));
          RestoreLoan restoreLoan = (RestoreLoan) listRestoreLoan2.get(0);
          loanInterestMonth = restoreLoan.getShouldInterest();
          // 剩余本金
          if (i == Integer.parseInt(timelimit) - 1) {

          } else {
            overplusLoanMoney = new BigDecimal(overplusLoanMoney).subtract(
                restoreLoan.getShouldCorpus()).divide(temp, 2,
                BigDecimal.ROUND_HALF_UP).toString();
          }
          if (Integer.parseInt(monthYear.substring(4, 6)) == 12) {
            monthYear = Integer.parseInt(monthYear.substring(0, 4)) + 1 + "01";
          } else {
            monthYear = Integer.parseInt(monthYear.substring(0, 6)) + 1 + "";
          }
          //            
          borrowerInfoDTO2.setLoanInterestMonth(loanInterestMonth.toString());
          list.add(borrowerInfoDTO2);
        }
      }
      // 在此合同编号在pl201中没有了。从下一年开始
      BigDecimal maxRate = new BigDecimal(0.00);// 最大利率
      String offince = null;// 办事处
      List listBorrower = loanBankDAO.queryBorrower_wuht(contractId);
      if (listBorrower != null && listBorrower.size() > 0) {
        Borrower borrower = (Borrower) listBorrower.get(0);
        offince = borrower.getOffice();
      }

      BorrowerAcc borrowerAccLoanRateType = (BorrowerAcc) listborrowerAcc
          .get(0);
      if (Integer.parseInt(borrowerAccLoanRateType.getLoanRateType()) == 1) {
        maxRate = loanBankDAO.queryLoanLoanRateMaxRate_wuht(offince, "1");
      } else {
        maxRate = loanBankDAO.queryLoanLoanRateMaxRate_wuht(offince, "0");
      }

      // 求出剩余期限

      if (list != null && list.size() > 0) {
        timelimit = Integer.parseInt(timelimit) - list.size() + "";
      }
      // corpusInterest=this.corpusInterest(new BigDecimal(overplusLoanMoney),
      // maxRate,
      // timelimit);
      for (int i = 0; i < Integer.parseInt(timelimit); i++) {
        BorrowerInfoDTO borrowerInfoDTO = new BorrowerInfoDTO();
        BigDecimal temp = new BigDecimal(1.00);
        // // 月利息
        loanInterestMonth = new BigDecimal(overplusLoanMoney).multiply(maxRate)
            .divide(temp, 2, BigDecimal.ROUND_HALF_UP);
        // 月本金
        if (i == Integer.parseInt(timelimit) - 1) {
          loanMoneyMonth = new BigDecimal(overplusLoanMoney);
        } else {
          loanMoneyMonth = new BigDecimal(shouldCorpus);
        }
        // 剩余本金
        if (i == Integer.parseInt(timelimit) - 1) {

        } else {
          overplusLoanMoney = new BigDecimal(overplusLoanMoney).subtract(
              loanMoneyMonth).divide(temp, 2, BigDecimal.ROUND_HALF_UP)
              .toString();
        }
        // 年月
        if (Integer.parseInt(monthYear.substring(4, 6)) == 12) {
          monthYear = Integer.parseInt(monthYear.substring(0, 4)) + 1 + "01";
        } else {
          monthYear = Integer.parseInt(monthYear.substring(0, 6)) + 1 + "";
        }
        borrowerInfoDTO.setLoanInterestMonth(loanInterestMonth.toString());
        list.add(borrowerInfoDTO);
      }

      BigDecimal loanInterestMonthSum = new BigDecimal(0);
      if (list != null && list.size() > 0) {
        for (int j = 0; j < list.size(); j++) {
          BorrowerInfoDTO borrowerInfoDTO = (BorrowerInfoDTO) list.get(j);
          loanInterestMonthSum = loanInterestMonthSum.add(new BigDecimal(
              borrowerInfoDTO.getLoanInterestMonth()));
        }
      }
      loancallbackTaAF.setOverplusInterestAll(loanInterestMonthSum);

    } catch (Exception e) {
      e.printStackTrace();
    }

    return loancallbackTaAF;
  }

  // 吴洪涛修改//2007-3-11等额本金 非单笔回收求剩余利息的方法
  public LoancallbackTaAF queryTdShow_loancallbackByCriterionsPlrecovertype_corpus_wuht(
      String corpusinterest, String monthYear, String contractId,
      String deadLine, String sumCorpus, SecurityInfo securityInfo)
      throws Exception, BusinessException {
    // TODO Auto-generated method stub
    // String srealinterest =
    // (String)request.getParameter("srealinterest");//剩余利息
    List list = new ArrayList();

    LoancallbackTaAF loancallbackTaAF = new LoancallbackTaAF();
    String timelimit = deadLine;// 提前还款后剩余期限
    String loanRate = "";// 月利率
    // String corpusInterestloancallback = corpusinterest;// 提前还款后月还本息
    // String monthYear = monthYear;// 还至年月
    // String contractId = (String) request.getParameter("contractId");// 合同编号
    String year = "";// 贷款期限
    // String sumCorpus = (String) request.getParameter("sumCorpus");// 本次总还款本金
    String overplusLoanMoney = "";// 剩余本金

    List listborrowerAcc = loanBankDAO
        .queryBorrowerAccByCcontractId_wuht(contractId);

    if (listborrowerAcc != null && listborrowerAcc.size() > 0) {
      BorrowerAcc borrowerAcc = (BorrowerAcc) listborrowerAcc.get(0);
      overplusLoanMoney = borrowerAcc.getOverplusLoanMoney().subtract(
          new BigDecimal(sumCorpus)).toString();
      year = borrowerAcc.getLoanTimeLimit();
    }

    // 还至日为空
    try {
      /**
       * 还至日为空 从pl203中查出最大的年月，就是还款最后一个月，这样就需要加＋1
       * 若pl203中未查出值，就说明它是刚刚贷款的还未还过款。这样他的年月就应该从pl201中最小的
       */
      /** 还至日不为空 年月加＋1 * */
      if (monthYear == null || monthYear.equals("")) {
        monthYear = loanBankDAO.queryLoanFlowTaiMaxlYearMonth_wuht(contractId);
        if (monthYear == null || monthYear.equals("")) {
          monthYear = loanBankDAO
              .queryRestoreLoanMinlYearMonth_wuht(contractId);
        } else {
          monthYear = monthYear.substring(0, 6);
          if (monthYear.substring(4, 6).equals("12")
              || monthYear.substring(4, 6) == "12") {
            monthYear = Integer.parseInt(monthYear.substring(0, 4)) + 1 + "01";
          } else {
            monthYear = Integer.parseInt(monthYear) + 1 + "";
          }
        }
      } else {
        monthYear = monthYear.substring(0, 6);
        if (monthYear.substring(4, 6).equals("12")
            || monthYear.substring(4, 6) == "12") {
          monthYear = Integer.parseInt(monthYear.substring(0, 4)) + 1 + "01";
        } else {
          monthYear = Integer.parseInt(monthYear) + 1 + "";
        }
      }
      List listRestoreLoan = loanBankDAO.queryRestoreLoanbyCriterions_wuht(
          contractId, monthYear.substring(0, 6));

      if (listRestoreLoan != null && listRestoreLoan.size() > 0) {
        RestoreLoan borrowerAcc = (RestoreLoan) listRestoreLoan.get(0);
        loanRate = borrowerAcc.getLoanRate().toString();
        // 页面上显示

      }
      // bgQueryTdShowAF.setSrealinterest(srealinterest);

      BigDecimal loanInterestMonth = new BigDecimal(0.00);// 月利息
      BigDecimal loanMoneyMonth = new BigDecimal(0.00);// 月本金
      loanMoneyMonth = new BigDecimal(overplusLoanMoney).divide(new BigDecimal(
          timelimit), 2, BigDecimal.ROUND_HALF_UP);
      if (listRestoreLoan != null && listRestoreLoan.size() > 0) {
        for (int i = Integer.parseInt(monthYear.substring(4, 6)); i <= 12; i++) {
          // BigDecimal corpusInterest2 = new BigDecimal(
          // corpusInterestloancallback);// 月还本息
          BorrowerInfoDTO borrowerInfoDTO2 = new BorrowerInfoDTO();
          BigDecimal temp = new BigDecimal(1.00);
          loanInterestMonth = new BigDecimal(overplusLoanMoney).multiply(
              new BigDecimal(loanRate)).divide(temp, 2,
              BigDecimal.ROUND_HALF_UP);

          // 月本金
          if (i == Integer.parseInt(timelimit) - 1) {
            loanMoneyMonth = new BigDecimal(overplusLoanMoney);

          }
          // 剩余本金
          if (i == Integer.parseInt(timelimit) - 1) {

          } else {
            overplusLoanMoney = new BigDecimal(overplusLoanMoney).subtract(
                loanMoneyMonth).divide(temp, 2, BigDecimal.ROUND_HALF_UP)
                .toString();
          }
          // 年月
          if (Integer.parseInt(monthYear.substring(4, 6)) == 12) {
            monthYear = Integer.parseInt(monthYear.substring(0, 4)) + 1 + "01";
          } else {
            monthYear = Integer.parseInt(monthYear.substring(0, 6)) + 1 + "";
          }

          borrowerInfoDTO2.setLoanInterestMonth(loanInterestMonth.toString());
          list.add(borrowerInfoDTO2);
        }
      }
      // 在此合同编号在pl201中没有了。从下一年开始
      BigDecimal maxRate = new BigDecimal(0.00);// 最大利率
      String offince = null;// 办事处
      List listBorrower = loanBankDAO.queryBorrower_wuht(contractId);
      if (listBorrower != null && listBorrower.size() > 0) {
        Borrower borrower = (Borrower) listBorrower.get(0);
        offince = borrower.getOffice();
      }

      BorrowerAcc borrowerAccLoanRateType = (BorrowerAcc) listborrowerAcc
          .get(0);
      if (Integer.parseInt(borrowerAccLoanRateType.getLoanRateType()) == 1) {
        maxRate = loanBankDAO.queryLoanLoanRateMaxRate_wuht(offince, "1");
      } else {
        maxRate = loanBankDAO.queryLoanLoanRateMaxRate_wuht(offince, "0");
      }

      // 求出剩余期限
      if (list != null && list.size() > 0) {
        timelimit = Integer.parseInt(timelimit) - list.size() + "";
      }

      for (int i = 0; i < Integer.parseInt(timelimit); i++) {
        BorrowerInfoDTO borrowerInfoDTO = new BorrowerInfoDTO();
        BigDecimal temp = new BigDecimal(1.00);
        // // 月利息
        loanInterestMonth = new BigDecimal(overplusLoanMoney).multiply(maxRate)
            .divide(temp, 2, BigDecimal.ROUND_HALF_UP);

        // 月本金
        if (i == Integer.parseInt(timelimit) - 1) {
          loanMoneyMonth = new BigDecimal(overplusLoanMoney);

        }
        // 剩余本金
        if (i == Integer.parseInt(timelimit) - 1) {

        } else {
          overplusLoanMoney = new BigDecimal(overplusLoanMoney).subtract(
              loanMoneyMonth).divide(temp, 2, BigDecimal.ROUND_HALF_UP)
              .toString();
        }

        // 年月
        if (Integer.parseInt(monthYear.substring(4, 6)) == 12) {
          monthYear = Integer.parseInt(monthYear.substring(0, 4)) + 1 + "01";
        } else {
          monthYear = Integer.parseInt(monthYear.substring(0, 6)) + 1 + "";
        }

        borrowerInfoDTO.setLoanInterestMonth(loanInterestMonth.toString());

        list.add(borrowerInfoDTO);
      }

      BigDecimal loanInterestMonthSum = new BigDecimal(0);
      if (list != null && list.size() > 0) {
        for (int j = 0; j < list.size(); j++) {
          BorrowerInfoDTO borrowerInfoDTO = (BorrowerInfoDTO) list.get(j);
          loanInterestMonthSum = loanInterestMonthSum.add(new BigDecimal(
              borrowerInfoDTO.getLoanInterestMonth()));
        }
      }

      // bgQueryTdShowAF.setCount(Integer.parseInt(timelimit));
      loancallbackTaAF.setOverplusInterestAll(loanInterestMonthSum);

    } catch (Exception e) {
      e.printStackTrace();
    }

    return loancallbackTaAF;
  }

  public BigDecimal corpusInterest(BigDecimal overplusLoanMoney,
      BigDecimal loanRate, String timelimit) {
    BigDecimal corpusInterest = new BigDecimal(0.00);// 月还本息
    BigDecimal LoanMoneyMonth = new BigDecimal(0.00);// 月本金
    BigDecimal LoanInterestMonth = new BigDecimal(0.00);// 月利息
    BigDecimal AllLoanMonry = new BigDecimal(0.00);// 所有的未还金额（月还本息* 剩余期限）
    BigDecimal AllLoanInterest = new BigDecimal(0.00);// 所有未还的总利息（所有的未还金额-剩余本金）
    // BigDecimal AllLoanInterestMonry = new BigDecimal(0.00);//
    // 所有未还的总利息（所有的未还金额+所有未还的总利息）
    BigDecimal yi = new BigDecimal(1);
    //
    // BigDecimal overplusLoanMoney = new BigDecimal(70000);// 剩余本金
    // BigDecimal loanRate = new BigDecimal(0.003675);// 月利率
    // String timelimit = "120";// 剩余期限
    try {
      if (overplusLoanMoney.doubleValue() > 0) {
        BigDecimal temp_loanRate = new BigDecimal(1.00).add(loanRate);// （1+月利率）
        BigDecimal tempMoney = new BigDecimal(Math.pow(temp_loanRate
            .doubleValue(), Double.parseDouble(timelimit)));// （1+月利率）^剩余期限
        BigDecimal temp = tempMoney.subtract(new BigDecimal(1.00));// (1+月利率)^剩余期限-1
        corpusInterest = overplusLoanMoney.multiply(tempMoney).multiply(
            loanRate).divide(temp, 2, BigDecimal.ROUND_HALF_UP);
        LoanInterestMonth = overplusLoanMoney.multiply(loanRate).divide(yi, 2,
            BigDecimal.ROUND_HALF_UP);
        LoanMoneyMonth = corpusInterest.subtract(LoanInterestMonth).divide(yi,
            2, BigDecimal.ROUND_HALF_UP);
        AllLoanMonry = corpusInterest.multiply(new BigDecimal(timelimit));
        AllLoanInterest = AllLoanMonry.subtract(overplusLoanMoney);
        // AllLoanInterestMonry = AllLoanInterest.add(AllLoanMonry);
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    return corpusInterest;

  }

  public CollParaDAO getCollParaDAO() {
    return collParaDAO;
  }

  public void setCollParaDAO(CollParaDAO collParaDAO) {
    this.collParaDAO = collParaDAO;
  }

  public void setBorrowerDAO(BorrowerDAO borrowerDAO) {
    this.borrowerDAO = borrowerDAO;
  }

  public void setHousesDAO(HousesDAO housesDAO) {
    this.housesDAO = housesDAO;
  }

  public String selectPL202_BankId_wsh(String headId) {
    // TODO Auto-generated method stub
    String bankId=null;
    try {
      bankId=loanFlowTailDAO.selectPL202_BankId_wsh(headId);
    } catch (Exception e) {
      // TODO: handle exception
    }
    return bankId;
  }
//求最小剩余期限
  public String someBackTime(String contractId, String salary) throws Exception {
    // TODO Auto-generated method stub
    String time = null;
    try {
      BorrowerAcc borrowerAcc = new BorrowerAcc();
      borrowerAcc = borrowerAccDAO.queryById(contractId);
      BigDecimal corpusInterest = new BigDecimal(0.00);// 月还本息
      corpusInterest = new BigDecimal(salary).divide(new BigDecimal(2), 2,
          BigDecimal.ROUND_HALF_UP);
      List list_1 = loanRateDAO.queryRate_wsh(
          "4028810c120af23c01120b14ed840005", "0");
      List list_2 = loanRateDAO.queryRate_wsh(
          "4028810c120af23c01120b14ed840005", "1");
      RateDTO rateDTO = new RateDTO();
      rateDTO = (RateDTO) list_1.get(0);
      BigDecimal temp_varable_1 = new BigDecimal(0.00);// 
      BigDecimal temp_varable_2 = new BigDecimal(0.00);// 
      BigDecimal temp_loanRate = new BigDecimal(1.00).add(rateDTO
          .getLoanMonthRate());// （1+月利率）
      BigDecimal loanMoney = borrowerAcc.getOverplusLoanMoney();
      temp_varable_2 = corpusInterest.subtract(loanMoney.multiply(rateDTO
          .getLoanMonthRate()));
      temp_varable_1 = corpusInterest.divide(temp_varable_2, 2,
          BigDecimal.ROUND_HALF_UP);
      double a = T.log(Double.parseDouble(String.valueOf(temp_varable_1)),
          Double.parseDouble(String.valueOf(temp_loanRate)));
      rateDTO = (RateDTO) list_2.get(0);
      temp_loanRate = new BigDecimal(1.00).add(rateDTO.getLoanMonthRate());// （1+月利率）
      temp_varable_2 = corpusInterest.subtract(loanMoney.multiply(rateDTO
          .getLoanMonthRate()));
      temp_varable_1 = corpusInterest.divide(temp_varable_2, 2,
          BigDecimal.ROUND_HALF_UP);
      double b = T.log(Double.parseDouble(String.valueOf(temp_varable_1)),
          Double.parseDouble(String.valueOf(temp_loanRate)));
      String date_1 = restoreLoanDAO.find(contractId);// 还至年月
      String date_2 = String.valueOf(borrowerAcc.getLoanStartDate()).substring(
          0, 6);
      String months = restoreLoanDAO.findMonthBetween(date_1, date_2);
      System.out.println(Integer.parseInt(String.valueOf(a).substring(0,
          String.valueOf(a).indexOf(".")))
          + Integer.parseInt(months));
      if (Integer.parseInt(String.valueOf(a).substring(0,
          String.valueOf(a).indexOf(".")))
          + Integer.parseInt(months) < 60) {
        time = String.valueOf(Integer.parseInt(String.valueOf(a).substring(0,
            String.valueOf(a).indexOf("."))) / 12 + 1);
      } else {
        time = String.valueOf(Integer.parseInt(String.valueOf(b).substring(0,
            String.valueOf(b).indexOf("."))) / 12 + 1);
      }
    } catch (Exception e) {
      // TODO: handle exception
      e.printStackTrace();
    }
    return time;
  }

}