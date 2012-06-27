package org.xpup.hafmis.sysloan.loancallback.baddebtdestroy.business;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

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
import org.xpup.hafmis.sysloan.common.arithmetic.punishinterest.PunishInterestBS;
import org.xpup.hafmis.sysloan.common.dao.AssistantOrgDAO;
import org.xpup.hafmis.sysloan.common.dao.BorrowerAccDAO;
import org.xpup.hafmis.sysloan.common.dao.LoanBankDAO;
import org.xpup.hafmis.sysloan.common.dao.LoanBankParaDAO;
import org.xpup.hafmis.sysloan.common.dao.LoanFlowHeadDAO;
import org.xpup.hafmis.sysloan.common.dao.LoanFlowTailDAO;
import org.xpup.hafmis.sysloan.common.dao.PlBizActiveLogDAO;
import org.xpup.hafmis.sysloan.common.dao.PlDocNumCancelDAO;
import org.xpup.hafmis.sysloan.common.dao.PlDocNumMaintainDAO;
import org.xpup.hafmis.sysloan.common.dao.PlOperateLogDAO;
import org.xpup.hafmis.sysloan.common.dao.RestoreLoanDAO;
import org.xpup.hafmis.sysloan.common.domain.entity.LoanFlowHead;
import org.xpup.hafmis.sysloan.common.domain.entity.LoanFlowTail;
import org.xpup.hafmis.sysloan.common.domain.entity.PlBizActiveLog;
import org.xpup.hafmis.sysloan.common.domain.entity.PlOperateLog;
import org.xpup.hafmis.sysloan.loanaccord.printplan.dto.PrintplanListDTO;
import org.xpup.hafmis.sysloan.loancallback.baddebtdestroy.bsinterface.IBadDebtDestroyBS;
import org.xpup.hafmis.sysloan.loancallback.baddebtdestroy.dto.BadDebtDestroyTaAFDTO;
import org.xpup.hafmis.sysloan.loancallback.baddebtdestroy.dto.BadDebtDestroyTbDTO;
import org.xpup.hafmis.sysloan.loancallback.loancallback.dto.BorrowerInfoDTO;
import org.xpup.hafmis.sysloan.loancallback.loancallback.dto.LoancallbackTaImportDTO;
import org.xpup.hafmis.sysloan.loancallback.loancallback.dto.ShouldBackListDTO;

public class BadDebtDestroyBS implements IBadDebtDestroyBS {

  private BorrowerAccDAO borrowerAccDAO = null;

  private LoanFlowHeadDAO loanFlowHeadDAO = null;

  private LoanFlowTailDAO loanFlowTailDAO = null;

  private RestoreLoanDAO restoreLoanDAO = null;

  private LoanBankParaDAO loanBankParaDAO = null;
  
  private PlOperateLogDAO plOperateLogDAO = null;
  private CollParaDAO collParaDAO = null;
  private PlBizActiveLogDAO plBizActiveLogDAO = null;
  
  private PlDocNumMaintainDAO plDocNumMaintainDAO = null;
  
  private PlDocNumCancelDAO plDocNumCancelDAO = null;
  
  private CollBankDAO collBankDAO = null;
  
  private LoanBankDAO loanBankDAO = null;
  
  private AssistantOrgDAO assistantOrgDAO = null;
  
  private SecurityDAO securityDAO = null;

  
  public void setSecurityDAO(SecurityDAO securityDAO) {
    this.securityDAO = securityDAO;
  }

  public void setAssistantOrgDAO(AssistantOrgDAO assistantOrgDAO) {
    this.assistantOrgDAO = assistantOrgDAO;
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
  /**
   * 判断还款日如果大于本月最后一天返回本月最后一天
   * @param yearMonth
   * @param loanRepayDay
   * @return
   */
  public String getEndDay(String yearMonth,String loanRepayDay){
    String day = loanRepayDay;
    String year = yearMonth.substring(0,4);
    String month = yearMonth.substring(4,6);
    int days = BusiTools.daysOfMonth(Integer.parseInt(year), Integer.parseInt(month));
    if(Integer.parseInt(loanRepayDay)>days){
      day = String.valueOf(days);
    }     
    if(day.length()<2&&Integer.parseInt(day)<10){
      day = "0"+day;
    }
    return day;
  }
  /**
   * 查询还至年月列表
   * 会计年月到会计年12月
   * @param securityInfo
   * @return
   */
  public List getYearMonthList(String loanRepayDay,String contractId,SecurityInfo securityInfo)throws Exception{
    List list = new ArrayList();
    String bizDate = securityInfo.getUserInfo().getPlbizDate();
    String day = bizDate.substring(6,8);
    List temp_list = restoreLoanDAO.countRestoreLoanListByContractId_sy(contractId);  
    String loanDay="";
    try{
      if(!temp_list.isEmpty()){
        for(int i=0;i<temp_list.size();i++){
          PrintplanListDTO dto = (PrintplanListDTO)temp_list.get(i);
          ShouldBackListDTO shouldBackListDTO = new ShouldBackListDTO();
//        if(Integer.parseInt(day)<Integer.parseInt(loanRepayDay)){
//        //会计日小于还款日，取未还的最小月的下个月--最大月的下一个月
//        if(dto.getLoanKouYearmonth().substring(4,6).equals("12")){
//          shouldBackListDTO.setLoanKouYearmonth(String.valueOf(Integer.parseInt(dto.getLoanKouYearmonth().substring(0,4))+1)+"01");
//        }else{
//          shouldBackListDTO.setLoanKouYearmonth(String.valueOf(Integer.parseInt(dto.getLoanKouYearmonth())+1));
//        }
//      }else{
        // 会计日大于等于还款日，取未还的最小月--最大月
          loanDay = this.getEndDay(dto.getLoanKouYearmonth(), loanRepayDay);
          shouldBackListDTO.setLoanKouYearmonth(dto.getLoanKouYearmonth()+loanDay);
//      }
          list.add(shouldBackListDTO);
        }
      }
    }catch(Exception e){
      e.printStackTrace();
    }
    return list;
  }
  
  /**
   * 以中心为主
   * 办理页面，根据贷款账号查询应还款信息
   */
  public BadDebtDestroyTaAFDTO findShouldLoancallbackInfo(Pagination pagination,
      SecurityInfo securityInfo) throws Exception {
    String callbackMonth = (String)pagination.getQueryCriterions().get("callbackMonth");
    BadDebtDestroyTaAFDTO af = new BadDebtDestroyTaAFDTO();
    BorrowerInfoDTO borrowerInfoDTO = new BorrowerInfoDTO();
    String bizDate = securityInfo.getUserInfo().getPlbizDate();// 会计日期
    List shouldBackList = new ArrayList();// 应还信息
    List bizStList = null;// 用于查询是否存在未记账的状态
    List borrowerList = new ArrayList();// 账户信息
    String contractSt = BusiConst.PLCONTRACTSTATUS_RECOVING+"";// 合同状态 11.还款中
    String contractId = "";// 合同编号
    String loanRepayDay = "";// 还款日 在取应还款信息时用到
    int count = 0;
    // 贷款账号
    String loanKouAcc = (String) pagination.getQueryCriterions().get(
        "loanKouAcc");
    String yearMonth = bizDate.substring(0, 6);// 取出会计日期中的年月
    String day = bizDate.substring(6, bizDate.length());// 取出会计日期中的日
    if(callbackMonth!=null&&!callbackMonth.equals("")){
      String temp_yearMonth = callbackMonth.substring(0,6);
      if(!temp_yearMonth.equals(yearMonth)){
        yearMonth=callbackMonth.substring(0,6);
        //day=callbackMonth.substring(6,8);
      }
    }
    
    if (loanKouAcc != null && !loanKouAcc.equals("")) {
      // 判断贷款账号在PL111中是否存在并且状态为还款中。
      contractId = findBorrowerAcc(loanKouAcc, contractSt, securityInfo);
      if (contractId == null) {
        throw new BusinessException("此贷款账号不存在或不可以办理回收业务！");
      }
      pagination.getQueryCriterions().put("contractId", contractId);
      // 该贷款账号在贷款流水账头表PL202表中是否存在BIZ_ST!=6(未记账)（关联贷款流水账尾表PL203）
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
      //证件类型
      borrowerInfoDTO.setCardKind(BusiTools.getBusiValue(Integer.parseInt(borrowerInfoDTO.getCardKind()), BusiConst.DOCUMENTSSTATE));
      //还款方式
      borrowerInfoDTO.setLoanMode(BusiTools.getBusiValue(Integer.parseInt(borrowerInfoDTO.getLoanMode()), BusiConst.PLRECOVERTYPE));
      loanRepayDay = borrowerInfoDTO.getLoanRepayDay();
      Integer loanBankId=borrowerInfoDTO.getLoanBankId();
      int iClearYear=Integer.parseInt(bizDate.substring(0,4))-1;
      String clearYear=this.getClearYear(String.valueOf(loanBankId));
      //会计年份-1如果不等于PL002中的年结年份则不允许回收
      if(iClearYear>Integer.parseInt(clearYear)){
        throw new BusinessException(iClearYear+"年尚未年结，不允许进行呆核业务！");
      }
      String loanRepayDay1 = this.getEndDay(yearMonth, loanRepayDay);
      // 从PL201中查应还信息
      if(callbackMonth==null){
        if (Integer.parseInt(day) < Integer.parseInt(loanRepayDay1)) {
          // 会计日小于还款日
          shouldBackList = restoreLoanDAO.queryRestoreLoanListByContractId_LJA(
              contractId, yearMonth);
        } 
        else {
          // 会计日大于等于还款日
          shouldBackList = restoreLoanDAO.queryRestoreLoanListByContractId_LJB(
              contractId, yearMonth);
        }
      }else {
        // 会计日大于等于还款日
        shouldBackList = restoreLoanDAO.queryRestoreLoanListByContractId_LJB(
            contractId, yearMonth);
      }
      if(!shouldBackList.isEmpty()){
        ShouldBackListDTO dto = (ShouldBackListDTO) shouldBackList.get(shouldBackList.size()-1);
        yearMonth = dto.getLoanKouYearmonth();
      }
      loanRepayDay1 = this.getEndDay(yearMonth, loanRepayDay);
      BadDebtDestroyTaAFDTO af1 = this.findCallbackList(shouldBackList, borrowerInfoDTO, bizDate);
      List yearMonthList = this.getYearMonthList(loanRepayDay, contractId, securityInfo);
      af.setBorrowerInfoDTO(borrowerInfoDTO);
      af.setShouldBackList(af1.getShouldBackList());
      af.setMonthYearList(yearMonthList);
      af.setMonthYear(yearMonth+loanRepayDay1);
      af.setSumCorpus(af1.getSumCorpus());
      af.setSumInterest(af1.getSumInterest());
      af.setSumMoney(af1.getSumMoney());
      af.setRealMoney(af1.getRealMoney());
    }
    count = shouldBackList.size();
    if(count<=0){
      af.setMonthYear("");
    }
    pagination.setNrOfElements(count);
    return af;
  }
  
  
  /**
   * 应还信息列表
   * @param shouldBackList
   * @param borrowerInfoDTO
   * @param bizDate
   * @return
   * @throws Exception
   */
  public BadDebtDestroyTaAFDTO findCallbackList(List shouldBackList,BorrowerInfoDTO borrowerInfoDTO,String bizDate)throws Exception{
    BadDebtDestroyTaAFDTO af = new BadDebtDestroyTaAFDTO();
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
    if(borrowerInfoDTO.getLoanMode().equals("一年期")||borrowerInfoDTO.getLoanMode().equals("两年期")){
      //整年期还贷
      //从PL003中查询宽限天数内是否计息
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
    }else{
      //从PL003中查询宽限天数内是否计息
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
        String loanRepayDay1 = this.getEndDay(dto1.getLoanKouYearmonth(), loanRepayDay);
        dto2.setLoanKouYearmonth(dto1.getLoanKouYearmonth());// 显示还款年月
        String temp_date = dto1.getLoanKouYearmonth().substring(0, 4) + "-"
            + dto1.getLoanKouYearmonth().substring(4, 6) + "-" + loanRepayDay1;
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
        if(days<0){
          dto2.setDays("0");
        }
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
       //     String temp_day = accountDate.substring(6, 8);// 记账日期的日
      //      if (Integer.parseInt(temp_day) <= Integer.parseInt(loanRepayDay)) {// 小于等于还款日
            if (accountDate==null || accountDate.equals("")) {//判断是否有记账日期：没有：减还款年月；有：减记账日期
              temp_interest = PunishInterestBS.getTempInterestByYearMonth(interestMode,bizDate,
                  dto1.getLoanKouYearmonth(), loanRepayDay1, dto1
                      .getShouldCorpus(), dto1.getRealCorpus(), dto1
                      .getShouldInterest(), dto1.getRealInterest(),
                  paramExplain ,dto1.getLoanRate());
          }else if(Integer.parseInt(accountDate) <= Integer.parseInt(dto1.getLoanKouYearmonth()+loanRepayDay1)){// 小于等于还款日
              temp_interest = PunishInterestBS.getTempInterestByYearMonth(interestMode,bizDate,
                  dto1.getLoanKouYearmonth(), loanRepayDay1, dto1
                  .getShouldCorpus(), dto1.getRealCorpus(), dto1
                  .getShouldInterest(), dto1.getRealInterest(),
              paramExplain ,dto1.getLoanRate());
          } else {// 大于还款日
              temp_interest = PunishInterestBS.getTempInterestByClearDate(interestMode,bizDate,
                  dto1.getBizDate(), dto1.getShouldCorpus(),
                  dto1.getRealCorpus(), dto1.getShouldInterest(), dto1
                      .getRealInterest(), paramExplain,dto1.getLoanRate());
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
            } else if(days<0){
              dto2.setPunishInterest(dto1.getPunishInterest());
            } else {// 已经逾期
              // 逐条判断PL201中的记账日期是否小于等于还款日+宽限天数
             
              // 还款年月日+宽限天数后生成的年月日
              String temp_loanRepayDay = "";
              temp_loanRepayDay = BusiTools.addDay(dto1.getLoanKouYearmonth()
                  + loanRepayDay1, Integer.parseInt(allowdays));
              //temp_loanRepayDay = temp_loanRepayDay.substring(6, 8);
//              if (Integer.parseInt(temp_day) <= Integer.parseInt(temp_loanRepayDay)) {// 小于等于还款日+宽限天数
              if (dto1.getBizDate()==null || dto1.getBizDate().equals("")) {//判断是否有记账日期
                  temp_interest = PunishInterestBS.getTempInterestByAllowdays(interestMode,bizDate,
                      dto1.getLoanKouYearmonth(), loanRepayDay1, dto1
                          .getShouldCorpus(), dto1.getRealCorpus(), dto1
                          .getShouldInterest(), dto1.getRealInterest(),
                      paramExplain, allowdays,dto1.getLoanRate());
              } else if(Integer.parseInt(dto1.getBizDate())<=Integer.parseInt(temp_loanRepayDay)){//小于等于还款日+宽限天数
                temp_interest = PunishInterestBS.getTempInterestByAllowdays(interestMode,bizDate,
                    dto1.getLoanKouYearmonth(), loanRepayDay1, dto1
                        .getShouldCorpus(), dto1.getRealCorpus(), dto1
                        .getShouldInterest(), dto1.getRealInterest(),
                    paramExplain, allowdays,dto1.getLoanRate());
              }else {// 大于还款日+宽限天数
                  temp_interest = PunishInterestBS.getTempInterestByClearDate(interestMode,bizDate,
                      dto1.getBizDate(),
                      dto1.getShouldCorpus(), dto1.getRealCorpus(), dto1
                          .getShouldInterest(), dto1.getRealInterest(),
                      paramExplain,dto1.getLoanRate());               
                // 加还款表中未还罚息
                temp_interest = temp_interest.add(dto1.getPunishInterest())
                    .divide(new BigDecimal(1), 2, BigDecimal.ROUND_HALF_UP);
              
              }
              dto2.setPunishInterest(temp_interest);
              dto2.setTempInterest(temp_interest);
            }
          }
        }
        dto2.setCiMoney(dto2.getShouldCorpus().add(dto2.getShouldInterest()).add(dto2.getPunishInterest()));// 显示应还本息合计
        dto2.setLoanRate(dto1.getLoanRate());// 显示每月利率
        dto2.setShow_loanRate(dto1.getShow_loanRate());
        sumCorpus = sumCorpus.add(dto2.getShouldCorpus());
        sumInterest = sumInterest.add(dto2.getShouldInterest().add(
            dto2.getPunishInterest()));
        temp_list.add(dto2);
      }
    }
    //总还款本金
    af.setSumCorpus(sumCorpus);
    //总还款利息
    af.setSumInterest(sumInterest);
    //总还款金额
    af.setSumMoney(af.getSumCorpus().add(af.getSumInterest()));
    //实还总额
    af.setRealMoney(af.getSumMoney());
    //应还信息列表
    af.setShouldBackList(temp_list);
    return af;
  }

/**
 * 以银行为主导入插入流水表
 * @param importList
 * @param securityInfo
 * @throws Exception
 */
  public Integer adCallbackBatch(List importList,SecurityInfo securityInfo)throws Exception{
    String loanKouAcc = "";//贷款账号
    String contractId = "";//合同编号
    String loanBankId = "";//放款银行
    String loanBankIdim="";//导入文件放款银行
    String bizDate = securityInfo.getUserInfo().getPlbizDate();//会计日期
    String bizDateim = "";//导入文件会计日期
    String bizTypeim = "";//导入文件类型
    BigDecimal shouldCorpus = new BigDecimal(0.00);//应还正常本金
    BigDecimal shouldInterest = new BigDecimal(0.00);//应还正常利息
    BigDecimal shouldOverdueCorpus = new BigDecimal(0.00);//应还逾期本金
    BigDecimal shouldOverdueInterest = new BigDecimal(0.00);//应还逾期利息
    BigDecimal shouldPunishInterest = new BigDecimal(0.00);//应还罚息
    BigDecimal realCorpus = new BigDecimal(0.00);//实还本金
    BigDecimal realInterest = new BigDecimal(0.00);//实还利息
    BigDecimal realOverdueCorpus = new BigDecimal(0.00);//实还逾期本金
    BigDecimal realOverdueInterest = new BigDecimal(0.00);//实还逾期利息
    BigDecimal realPunishInterest = new BigDecimal(0.00);//实还罚息
    BigDecimal temp_realCorpus = new BigDecimal(0.00);//实还本金
    BigDecimal temp_realInterest = new BigDecimal(0.00);//实还利息
    BigDecimal temp_realOverdueCorpus = new BigDecimal(0.00);//实还逾期本金
    BigDecimal temp_realOverdueInterest = new BigDecimal(0.00);//实还逾期利息
    BigDecimal temp_realPunishInterest = new BigDecimal(0.00);//实还罚息
    String contractSt = BusiConst.PLCONTRACTSTATUS_RECOVING+"";// 合同状态 11.还款中
    String loanRepayDay = "";// 还款日 
    String temp_bizDate = bizDate.substring(0,4)+"-"+bizDate.substring(4,6)+"-"+bizDate.substring(6,8);
    List bizStList = null;
    List borrowerList = null;
    BorrowerInfoDTO borrowerInfoDTO = null;
    List bankList = null;
    if(importList.isEmpty()){
      throw new BusinessException("导入文件为空！");
    }else if(importList.size()>1){
      LoancallbackTaImportDTO dto1 = (LoancallbackTaImportDTO)importList.get(0);
      loanBankIdim = dto1.getLoanBankId();
      bizDateim = dto1.getBizDate();
      bizTypeim = dto1.getBizType();
      bankList = securityDAO.getDkUserBankList_LJ(securityInfo.getUserName(), loanBankIdim);
      //判断有无操作此银行的权限
      if(bankList.isEmpty()){
        throw new BusinessException("不具备该银行的权限！");
      }
      int iClearYear=Integer.parseInt(bizDate.substring(0,4))-1;
      String clearYear=this.getClearYear(String.valueOf(loanBankIdim));
      //会计年份-1如果不等于PL002中的年结年份则不允许回收
      if(iClearYear>Integer.parseInt(clearYear)){
        throw new BusinessException(iClearYear+"年尚未年结，不允许进行呆核业务！");
      }
      if(!temp_bizDate.equals(bizDateim)){
        throw new BusinessException("会计日期不一致！");
      }
      if(bizTypeim==null){
        throw new BusinessException("业务类型不能为空！");
      }else if(!bizTypeim.equals(String.valueOf(BusiConst.PLBUSINESSTYPE_BADDEBTSOFFCENTRE)) 
          && !bizTypeim.equals(String.valueOf(BusiConst.PLBUSINESSTYPE_BADDEBTSOFFOTHER))){
        throw new BusinessException("该业务类型不能在此导入！");
      }
      LoancallbackTaImportDTO dto2 = (LoancallbackTaImportDTO)importList.get(1);
      loanKouAcc = dto2.getLoanKouAcc();
      
      //判断贷款账号在PL111中是否存在并且状态为还款中。
      contractId = findBorrowerAcc(loanKouAcc, contractSt, securityInfo);
      if (contractId == null) {
        throw new BusinessException("此贷款账号不存在！");
      }
      // 该贷款账号在贷款流水账头表PL202表中是否存在BIZ_ST!=6(未记账)（关联贷款流水账尾表PL203）
      bizStList = loanFlowHeadDAO.queryBizStListByLoanKouAcc_LJ(contractId,bizTypeim);
      if (!bizStList.isEmpty()) {
        throw new BusinessException("存在未记账的业务！");
      } 
      // 从PL110及PL111取信息
      borrowerList = borrowerAccDAO.queryBorrowerAccInfoByLoanKouAcc_LJ(contractId);
      if (!borrowerList.isEmpty()) {
        borrowerInfoDTO = (BorrowerInfoDTO) borrowerList.get(0);
      }
      loanRepayDay = borrowerInfoDTO.getLoanRepayDay();
      loanBankId = borrowerInfoDTO.getLoanBankId().toString();
      if(!loanBankId.equals(loanBankIdim)){
        throw new BusinessException("放款银行不一致！");
      }
      for(int i=1;i<importList.size();i++){
        LoancallbackTaImportDTO dto = (LoancallbackTaImportDTO)importList.get(i);
        
        if(Double.parseDouble(dto.getRealCorpus())<0){
          throw new BusinessException("金额不能小于0！");
        }
        if(Double.parseDouble(dto.getRealOverdueCorpus())<0){
          throw new BusinessException("金额不能小于0！");
        }
        if(Double.parseDouble(dto.getRealInterest())<0){
          throw new BusinessException("金额不能小于0！");
        }
        if(Double.parseDouble(dto.getRealOverdueInterest())<0){
          throw new BusinessException("金额不能小于0！");
        }
        if(Double.parseDouble(dto.getRealPunishInterest())<0){
          throw new BusinessException("金额不能小于0！");
        }
        if(Double.parseDouble(dto.getNobackCorpus())<0){
          throw new BusinessException("金额不能小于0！");
        }
        if(Double.parseDouble(dto.getNobackOverdueCorpus())<0){
          throw new BusinessException("金额不能小于0！");
        }
        if(Double.parseDouble(dto.getNobackInterest())<0){
          throw new BusinessException("金额不能小于0！");
        }
        if(Double.parseDouble(dto.getNobackOverdueInterest())<0){
          throw new BusinessException("金额不能小于0！");
        }
        if(Double.parseDouble(dto.getNobackPunishInterest())<0){
          throw new BusinessException("金额不能小于0！");
        }
        shouldCorpus = shouldCorpus.add(new BigDecimal(dto.getRealCorpus()).add(new BigDecimal(dto.getNobackCorpus())));
        shouldInterest = shouldInterest.add(new BigDecimal(dto.getRealInterest()).add(new BigDecimal(dto.getNobackInterest())));
        shouldOverdueCorpus = shouldOverdueCorpus.
        add(new BigDecimal(dto.getRealOverdueCorpus()).add(new BigDecimal(dto.getNobackOverdueCorpus())));
        shouldOverdueInterest = shouldOverdueInterest.
        add(new BigDecimal(dto.getRealOverdueInterest()).add(new BigDecimal(dto.getNobackOverdueInterest())));
        shouldPunishInterest = shouldPunishInterest.
        add(new BigDecimal(dto.getRealPunishInterest()).add(new BigDecimal(dto.getNobackPunishInterest())));
        realCorpus = realCorpus.add(new BigDecimal(dto.getRealCorpus()));
        realInterest = realInterest.add(new BigDecimal(dto.getRealInterest()));
        realOverdueCorpus = realOverdueCorpus.add(new BigDecimal(dto.getRealOverdueCorpus()));
        realOverdueInterest = realOverdueInterest.add(new BigDecimal(dto.getRealOverdueInterest()));
        realPunishInterest = realPunishInterest.add(new BigDecimal(dto.getRealPunishInterest()));
      }
    }
    //插入流水头表
    LoanFlowHead loanFlowHead = new LoanFlowHead();
    loanFlowHead.setBizDate(bizDate);
    String officeId="";
    String loanDocNumDocument=collParaDAO.getLoanDocNumDesignPara();
    if(loanDocNumDocument.equals("1")){
      officeId=borrowerInfoDTO.getOfficeCode();
    }else{
      officeId=borrowerInfoDTO.getLoanBankId().toString();
    }
    loanFlowHead.setDocNum(plDocNumMaintainDAO.getDocNumdocNum(officeId, bizDate.substring(0,6)));
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
    loanFlowHead.setOccurMoney(realCorpus.add(realInterest).add(realOverdueCorpus).add(realOverdueInterest).add(realPunishInterest));
    loanFlowHead.setOtherInterest(new BigDecimal(0.00));
    loanFlowHead.setLoanBankId(new BigDecimal(loanBankId));
    loanFlowHead.setBizSt(BusiConst.PLBUSINESSSTATE_IMP+"");
    loanFlowHead.setMakePerson(securityInfo.getUserName());  
    loanFlowHead.setIsFinance(new Integer(1));
    loanFlowHeadDAO.insert(loanFlowHead);
    //更新票据号
    LoanFlowHead loanFlowHead1 = loanFlowHeadDAO.queryById(loanFlowHead.getFlowHeadId());
    loanFlowHead1.setNoteNum(loanFlowHead1.getFlowHeadId().toString());

    //插入流水尾表
    if(!importList.isEmpty()){
      for(int i=1;i<importList.size();i++){
        LoancallbackTaImportDTO dto = (LoancallbackTaImportDTO) importList.get(i);
        temp_realCorpus = new BigDecimal(dto.getRealCorpus());
        temp_realInterest = new BigDecimal(dto.getRealInterest());
        temp_realOverdueCorpus = new BigDecimal(dto.getRealOverdueCorpus());
        temp_realOverdueInterest = new BigDecimal(dto.getRealOverdueInterest());
        temp_realPunishInterest = new BigDecimal(dto.getRealPunishInterest());
        LoanFlowTail loanFlowTail = new LoanFlowTail();
//      如果逾期数据和正常数据都大于0则向流水表中插入两条数据，一条还款类型为1正常，一条为2逾期
        if(temp_realCorpus.add(temp_realInterest).doubleValue()>0 && 
            temp_realOverdueCorpus.add(temp_realOverdueInterest).add(temp_realPunishInterest).doubleValue()>0){
          loanFlowTail.setContractId(contractId);
          loanFlowTail.setFlowHeadId(new BigDecimal(loanFlowHead1.getFlowHeadId().toString()));
          loanFlowTail.setLoanKouAcc(loanKouAcc);
          loanFlowTail.setYearMonth(dto.getYearMonth());
          loanFlowTail.setShouldCorpus(new BigDecimal(dto.getRealCorpus()).add(new BigDecimal(dto.getNobackCorpus())));
          loanFlowTail.setShouldInterest(new BigDecimal(dto.getRealInterest()).add(new BigDecimal(dto.getNobackInterest())));
       //   loanFlowTail.setShouldPunishInterest(new BigDecimal(dto.getRealPunishInterest()).add(new BigDecimal(dto.getNobackPunishInterest())));
          loanFlowTail.setRealCorpus(new BigDecimal(dto.getRealCorpus()));
          loanFlowTail.setRealInterest(new BigDecimal(dto.getRealInterest()));
          //loanFlowTail.setRealPunishInterest(new BigDecimal(dto.getRealPunishInterest()));
          loanFlowTail.setOtherInterest(new BigDecimal(0.00)); 
          loanFlowTail.setOccurMoney(loanFlowTail.getRealCorpus().add(loanFlowTail.getRealInterest()).add(loanFlowTail.getRealPunishInterest()));        
          loanFlowTail.setLoanType("1");
          loanFlowTailDAO.insert(loanFlowTail);
          
          LoanFlowTail loanFlowTail1 = new LoanFlowTail();
          loanFlowTail1.setContractId(contractId);
          loanFlowTail1.setFlowHeadId(new BigDecimal(loanFlowHead1.getFlowHeadId().toString()));
          loanFlowTail1.setLoanKouAcc(loanKouAcc);
          loanFlowTail1.setYearMonth(dto.getYearMonth());
          loanFlowTail1.setShouldCorpus(new BigDecimal(dto.getRealOverdueCorpus()).add(new BigDecimal(dto.getNobackOverdueCorpus())));
          loanFlowTail1.setShouldInterest(new BigDecimal(dto.getRealOverdueInterest()).add(new BigDecimal(dto.getNobackOverdueInterest())));
          loanFlowTail1.setShouldPunishInterest(new BigDecimal(dto.getRealPunishInterest()).add(new BigDecimal(dto.getNobackPunishInterest())));
          loanFlowTail1.setRealCorpus(new BigDecimal(dto.getRealOverdueCorpus()));
          loanFlowTail1.setRealInterest(new BigDecimal(dto.getRealOverdueInterest()));
          loanFlowTail1.setRealPunishInterest(new BigDecimal(dto.getRealPunishInterest()));
          loanFlowTail1.setOtherInterest(new BigDecimal(0.00)); 
          loanFlowTail1.setOccurMoney(loanFlowTail1.getRealCorpus().add(loanFlowTail1.getRealInterest()).add(loanFlowTail1.getRealPunishInterest()));        
          loanFlowTail1.setLoanType("2");
          loanFlowTailDAO.insert(loanFlowTail1);
          //否则正常数据大于0逾期数据小于0，插入一条还款类型为1正常
        }else if(temp_realCorpus.add(temp_realInterest).doubleValue()>0){
          loanFlowTail.setContractId(contractId);
          loanFlowTail.setFlowHeadId(new BigDecimal(loanFlowHead1.getFlowHeadId().toString()));
          loanFlowTail.setLoanKouAcc(loanKouAcc);
          loanFlowTail.setYearMonth(dto.getYearMonth());
          loanFlowTail.setShouldCorpus(new BigDecimal(dto.getRealCorpus()).add(new BigDecimal(dto.getNobackCorpus())));
          loanFlowTail.setShouldInterest(new BigDecimal(dto.getRealInterest()).add(new BigDecimal(dto.getNobackInterest())));
          //loanFlowTail.setShouldPunishInterest(new BigDecimal(dto.getRealPunishInterest()).add(new BigDecimal(dto.getNobackPunishInterest())));
          loanFlowTail.setRealCorpus(new BigDecimal(dto.getRealCorpus()));
          loanFlowTail.setRealInterest(new BigDecimal(dto.getRealInterest()));
          //loanFlowTail.setRealPunishInterest(new BigDecimal(dto.getRealPunishInterest()));
          loanFlowTail.setOtherInterest(new BigDecimal(0.00)); 
          loanFlowTail.setOccurMoney(loanFlowTail.getRealCorpus().add(loanFlowTail.getRealInterest()).add(loanFlowTail.getRealPunishInterest()));        
          loanFlowTail.setLoanType("1");
          loanFlowTailDAO.insert(loanFlowTail);
//        否则逾期数据大于0正常数据等于0，插入一条还款类型为2逾期
        }else if(temp_realOverdueCorpus.add(temp_realOverdueInterest).add(temp_realPunishInterest).doubleValue()>0){
          loanFlowTail.setContractId(contractId);
          loanFlowTail.setFlowHeadId(new BigDecimal(loanFlowHead1.getFlowHeadId().toString()));
          loanFlowTail.setLoanKouAcc(loanKouAcc);
          loanFlowTail.setYearMonth(dto.getYearMonth());
          loanFlowTail.setShouldCorpus(new BigDecimal(dto.getRealOverdueCorpus()).add(new BigDecimal(dto.getNobackOverdueCorpus())));
          loanFlowTail.setShouldInterest(new BigDecimal(dto.getRealOverdueInterest()).add(new BigDecimal(dto.getNobackOverdueInterest())));
          loanFlowTail.setShouldPunishInterest(new BigDecimal(dto.getRealPunishInterest()).add(new BigDecimal(dto.getNobackPunishInterest())));
          loanFlowTail.setRealCorpus(new BigDecimal(dto.getRealOverdueCorpus()));
          loanFlowTail.setRealInterest(new BigDecimal(dto.getRealOverdueInterest()));
          loanFlowTail.setRealPunishInterest(new BigDecimal(dto.getRealPunishInterest()));
          loanFlowTail.setOtherInterest(new BigDecimal(0.00)); 
          loanFlowTail.setOccurMoney(loanFlowTail.getRealCorpus().add(loanFlowTail.getRealInterest()).add(loanFlowTail.getRealPunishInterest()));        
          loanFlowTail.setLoanType("2");
          loanFlowTailDAO.insert(loanFlowTail);
        }
        // 逾期天数
//        String loanRepayDay1 = this.getEndDay(dto.getYearMonth(), loanRepayDay);
//        int days = this.getDays(bizDate, dto.getYearMonth(), loanRepayDay1);
//        if(days<=0){
//          loanFlowTail.setLoanType("1");
//        }else{
//          loanFlowTail.setLoanType("2");
//        }
      }
    }
    //插入业务日志
    PlBizActiveLog plBizActiveLog = new PlBizActiveLog();
    plBizActiveLog.setAction(BusiLogConst.BIZBLOG_ACTION_IMP+"");
    plBizActiveLog.setBizid(new BigDecimal(loanFlowHead.getFlowHeadId().toString()));
    plBizActiveLog.setOperator(securityInfo.getUserName());
    plBizActiveLog.setOpTime(new Date());
    plBizActiveLog.setType(loanFlowHead.getBizType());
    plBizActiveLogDAO.insert(plBizActiveLog);
    //插入操作日志
    PlOperateLog plOperateLog = new PlOperateLog();
    plOperateLog.setOpSys(new BigDecimal(BusiLogConst.OP_SYSTEM_TYPE_LOAN));
    plOperateLog.setContractId(contractId);
    plOperateLog.setOpBizId(new BigDecimal(loanFlowHead.getFlowHeadId().toString()));
    plOperateLog.setOpButton(BusiLogConst.BIZBLOG_ACTION_IMP+"");
    plOperateLog.setOperator(securityInfo.getUserName());
    plOperateLog.setOpIp(securityInfo.getUserIp());
    plOperateLog.setOpModel(BusiLogConst.PL_OP_LOANRECOVER_BADDEBTSOFF_DO+"");
    plOperateLog.setOpTime(new Date());
    plOperateLogDAO.insert(plOperateLog);
    return loanFlowHead.getFlowHeadId();
  }

  /**
   * 办理呆账核销银行导入后查询
   * @param pagination
   * @return
   */
  public BadDebtDestroyTaAFDTO findCallbacklistByLoanBank(Pagination pagination) throws Exception{
    BadDebtDestroyTaAFDTO af = new BadDebtDestroyTaAFDTO();
    BorrowerInfoDTO borrowerInfoDTO = null;
    List borrowerList = null;
    String headId = (String)pagination.getQueryCriterions().get("headId");
    List list = new ArrayList();
    int count=0;
    if(headId != null){
      LoanFlowHead loanFlowHead = loanFlowHeadDAO.queryById(new Integer(headId));
      String bizDate = loanFlowHead.getBizDate();
      String orgType = loanFlowHead.getBizType();
      String orgId = loanFlowHead.getHedaiOrg();
      String orgName = "";
      if(orgId!=null&&!orgId.equals("")){
        orgName = assistantOrgDAO.queryAssistantOrgNameByAssistantOrgId(orgId);
      }
      List tailList = loanFlowTailDAO.queryRealLoanFlowTailByHeadId_LJ(headId);
      String contractId = "";//合同编号
      String loanRepayDay = "";//还款日
      if(!tailList.isEmpty()){
        ShouldBackListDTO shouldBackListDTO = (ShouldBackListDTO)tailList.get(0);
        contractId = shouldBackListDTO.getContractId();
        borrowerList = borrowerAccDAO
        .queryBorrowerAccInfoByLoanKouAcc_LJ(contractId);
        if (!borrowerList.isEmpty()) {
          borrowerInfoDTO = (BorrowerInfoDTO) borrowerList.get(0);
        }
        //证件类型
        borrowerInfoDTO.setCardKind(BusiTools.getBusiValue(Integer.parseInt(borrowerInfoDTO.getCardKind()), BusiConst.DOCUMENTSSTATE));
        //还款方式
        borrowerInfoDTO.setLoanMode(BusiTools.getBusiValue(Integer.parseInt(borrowerInfoDTO.getLoanMode()), BusiConst.PLRECOVERTYPE));
        loanRepayDay = borrowerInfoDTO.getLoanRepayDay();
        for(int i=0;i<tailList.size();i++){
          ShouldBackListDTO dto = (ShouldBackListDTO)tailList.get(i);
          ShouldBackListDTO dto2 = new ShouldBackListDTO();
          dto2.setLoanKouYearmonth(dto.getLoanKouYearmonth());
          String loanRepayDay1 = this.getEndDay(dto.getLoanKouYearmonth(), loanRepayDay);
          // 逾期天数
          int days = this.getDays(bizDate, dto.getLoanKouYearmonth(), loanRepayDay1);
          if(days<0){
            days=0;
          }
          String type = dto.getLoanKouType();
          if(type.equals("1")){
            dto2.setLoanKouType("正常");
          }else if(type.equals("2")){
            dto2.setLoanKouType("逾期");
          }
          dto2.setShouldCorpus(dto.getShouldCorpus());
          dto2.setShouldInterest(dto.getShouldInterest());
          dto2.setPunishInterest(dto.getPunishInterest());
          dto2.setCiMoney(dto.getShouldCorpus().add(dto.getShouldInterest()).add(dto.getPunishInterest()));
          dto2.setLoanRate(dto.getLoanRate());
          dto2.setDays(days+"");
          list.add(dto2);
        }
      }
      if(!list.isEmpty()){
        ShouldBackListDTO dto = (ShouldBackListDTO) list.get(list.size()-1);
        af.setMonthYear(dto.getLoanKouYearmonth());
        count = list.size();
      }
      af.setMonthYearList(list);
      af.setBorrowerInfoDTO(borrowerInfoDTO);
      af.setShouldBackList(list);
      af.setSumCorpus(loanFlowHead.getRealCorpus().add(loanFlowHead.getRealOverdueCorpus()));
      af.setSumInterest(loanFlowHead.getRealInterest().add(loanFlowHead.getRealOverdueInterest()).add(loanFlowHead.getRealPunishInterest()));
      af.setSumMoney(af.getSumCorpus().add(af.getSumInterest()));
      af.setRealMoney(af.getSumMoney());
      af.setOrgName(orgName);
      if(orgType.equals(String.valueOf(BusiConst.PLBUSINESSTYPE_BADDEBTSOFFCENTRE))){
        af.setOrgType("1");        
      }else if(orgType.equals(String.valueOf(BusiConst.PLBUSINESSTYPE_BADDEBTSOFFOTHER))){
        if(orgName!=null && !orgName.equals("")){
          af.setOrgType("2");
        }else{
          af.setOrgType("3");
        }
      }
    }
    
    pagination.setNrOfElements(count);
    return af;
  }

  /**
   * 呆账核销确定(以中心为主)
   * @param af
   * @param securityInfo
   * @throws Exception
   * @return
   */
  public String addCallbackInfo(BadDebtDestroyTaAFDTO af,SecurityInfo securityInfo) throws Exception{
    //以中心为主
    String contractId = af.getBorrowerInfoDTO().getContractId();//合同编号
    List bizStList = new ArrayList();
    String bizDate = securityInfo.getUserInfo().getPlbizDate();
    String operator = securityInfo.getUserName();
    Integer headId=null;
    String orgType=af.getOrgType();//核销单位类型
    //判断贷款账号在表PL202中是否存在:BIZ_ST！=6（关联PL203）
    //该贷款账号在贷款流水账头表PL202表中是否存在BIZ_ST!=6(未记账)（关联贷款流水账尾表PL203）
    bizStList = loanFlowHeadDAO.queryBizStListByLoanKouAcc_LJ(contractId,null);
    if (bizStList.size()>0) {
      throw new BusinessException("存在未记账的业务！");
    }
    headId=this.addLoanFlowHeadFull(bizDate, operator, af);
    //插入操作日志
    PlOperateLog plOperateLog = new PlOperateLog();
    plOperateLog.setOpSys(new BigDecimal(BusiLogConst.OP_SYSTEM_TYPE_LOAN));
    plOperateLog.setContractId(contractId);
    plOperateLog.setOpBizId(new BigDecimal(headId.toString()));
    plOperateLog.setOpButton(BusiLogConst.BIZLOG_ACTION_ADD+"");
    plOperateLog.setOperator(securityInfo.getUserName());
    plOperateLog.setOpIp(securityInfo.getUserIp());
    plOperateLog.setOpModel(BusiLogConst.PL_OP_LOANRECOVER_BADDEBTSOFF_DO+"");
    plOperateLog.setOpTime(new Date());
    plOperateLogDAO.insert(plOperateLog);
    //插入业务日志
    PlBizActiveLog plBizActiveLog = new PlBizActiveLog();
    plBizActiveLog.setAction(String.valueOf(BusiConst.PLBUSINESSSTATE_SIGN));
    plBizActiveLog.setBizid(new BigDecimal(headId.toString()));
    plBizActiveLog.setOperator(securityInfo.getUserName());
    plBizActiveLog.setOpTime(new Date());
    if(orgType.equals("1")){
      //中心
      plBizActiveLog.setType(String.valueOf(BusiConst.PLBUSINESSTYPE_BADDEBTSOFFCENTRE));
    }else{
      //担保公司或其他
      plBizActiveLog.setType(String.valueOf(BusiConst.PLBUSINESSTYPE_BADDEBTSOFFOTHER));
    }
    plBizActiveLogDAO.insert(plBizActiveLog);
    return headId.toString();
  }
  //足额扣款
  public Integer addLoanFlowHeadFull(String bizDate,String operator,BadDebtDestroyTaAFDTO af)throws Exception{
    BigDecimal shouldCorpus = new BigDecimal(0.00);
    BigDecimal shouldInterest = new BigDecimal(0.00);
    BigDecimal shouldOverdueCorpus = new BigDecimal(0.00);
    BigDecimal shouldOverdueInterest = new BigDecimal(0.00);
    BigDecimal punishInterest = new BigDecimal(0.00);
    LoanFlowHead loanFlowHead = new LoanFlowHead();
    try{
      String orgType=af.getOrgType();//核销单位类型
      String bizYearmonth = bizDate.substring(0,6);
      List list = af.getShouldBackList();
      //插入流水头表
      String officeId="";
      String loanDocNumDocument=collParaDAO.getLoanDocNumDesignPara();
      if(loanDocNumDocument.equals("1")){
        officeId=af.getBorrowerInfoDTO().getOfficeCode();
      }else{
        officeId=af.getBorrowerInfoDTO().getLoanBankId().toString();
      }
      loanFlowHead.setDocNum(plDocNumMaintainDAO.getDocNumdocNum(officeId, bizYearmonth));
      loanFlowHead.setBizDate(bizDate);
      //业务类型BIZ_TYPE：A.如果核销单位选择中心,BIZ_TYPE=6.呆账核销（中心）B.如果核销单位选择担保公司或其它，BIZ_TYPE=7.呆账核销（其他）、
      if(orgType.equals("1")){
        //中心
        loanFlowHead.setBizType(String.valueOf(BusiConst.PLBUSINESSTYPE_BADDEBTSOFFCENTRE));
      }else{
        //担保公司或其他
        loanFlowHead.setBizType(String.valueOf(BusiConst.PLBUSINESSTYPE_BADDEBTSOFFOTHER));
      }
      //核呆单位HEDAI_ORG：核销单位：如果为担保公司核销时，插入担保公司代码协作单位信息表PL007中协作单位编号ASSISTANT_ORG_ID，其它情况下核单位为空
      if(orgType.equals("2")){
        loanFlowHead.setHedaiOrg(af.getLoanassistantorgId());
      }
      loanFlowHead.setShouldCount(new BigDecimal(1));
      loanFlowHead.setRealCount(new BigDecimal(1));
      loanFlowHead.setOtherInterest(new BigDecimal(0.00));
      loanFlowHead.setLoanBankId(new BigDecimal(af.getBorrowerInfoDTO().getLoanBankId().toString()));
      loanFlowHead.setBizSt(String.valueOf(BusiConst.PLBUSINESSSTATE_SIGN));
      loanFlowHead.setMakePerson(operator);
      loanFlowHead.setIsFinance(new Integer(1));
      loanFlowHeadDAO.insert(loanFlowHead);   
      //插入流水尾表
      if(!list.isEmpty()){
        for(int i=0;i<list.size();i++){
          ShouldBackListDTO dto = (ShouldBackListDTO) list.get(i);
          LoanFlowTail loanFlowTail = new LoanFlowTail();
          loanFlowTail.setContractId(af.getBorrowerInfoDTO().getContractId());
          loanFlowTail.setFlowHeadId(new BigDecimal(loanFlowHead.getFlowHeadId().toString()));
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
          punishInterest = punishInterest.add(dto.getPunishInterest());
          if(Integer.parseInt(dto.getDays())<=0){
            loanFlowTail.setLoanType("1");
            shouldCorpus = shouldCorpus.add(dto.getShouldCorpus());
            shouldInterest = shouldInterest.add(dto.getShouldInterest());
          }else{
            loanFlowTail.setLoanType("2");
            shouldOverdueCorpus = shouldOverdueCorpus.add(dto.getShouldCorpus());
            shouldOverdueInterest = shouldOverdueInterest.add(dto.getShouldInterest());
          }
          loanFlowTail.setTempPunishInterest(dto.getTempInterest().subtract(dto.getPunishInterest()));
          //注：插入发生额
          loanFlowTail.setOccurMoney(loanFlowTail.getRealCorpus().add(loanFlowTail.getRealInterest()).add(loanFlowTail.getRealPunishInterest()));
          loanFlowTailDAO.insert(loanFlowTail);
        }
      }
      //更新头表
      LoanFlowHead loanFlowHead1 = loanFlowHeadDAO.queryById(loanFlowHead.getFlowHeadId());
      loanFlowHead1.setNoteNum(loanFlowHead1.getFlowHeadId().toString());
      loanFlowHead1.setShouldCorpus(shouldCorpus);
      loanFlowHead1.setShouldInterest(shouldInterest);
      loanFlowHead1.setShouldOverdueCorpus(shouldOverdueCorpus);
      loanFlowHead1.setShouldOverdueInterest(shouldOverdueInterest);
      loanFlowHead1.setShouldPunishInterest(punishInterest);
      loanFlowHead1.setRealCorpus(loanFlowHead1.getShouldCorpus());
      loanFlowHead1.setRealInterest(loanFlowHead1.getShouldInterest());
      loanFlowHead1.setRealOverdueCorpus(loanFlowHead1.getShouldOverdueCorpus());
      loanFlowHead1.setRealOverdueInterest(loanFlowHead1.getShouldOverdueInterest());
      loanFlowHead1.setRealPunishInterest(loanFlowHead1.getShouldPunishInterest());
//      loanFlowHead1.setOccurMoney(loanFlowHead1.getRealCorpus().add(loanFlowHead1.getRealInterest()).add(loanFlowHead1.getRealOverdueCorpus()).
//          add(loanFlowHead1.getRealOverdueInterest()).add(loanFlowHead1.getRealPunishInterest()));
      loanFlowHead1.setOccurMoney(loanFlowHead1.getOccurMoney().add(loanFlowHead1.getRealCorpus()).add(loanFlowHead1.getRealInterest()).add(loanFlowHead1.getRealOverdueCorpus()).
          add(loanFlowHead1.getRealOverdueInterest()).add(loanFlowHead1.getRealPunishInterest()));
    }catch(Exception e){
      e.printStackTrace();
    }
    return loanFlowHead.getFlowHeadId();
  }
  
  
  /**
   * 办理呆账核销以银行为主导入后确定改变流水表业务状态（导入--登记）
   * @param headId
   * @param securityInfo
   */
  public String addCallbackInfoByLoanBankId(String headId,String contractId,SecurityInfo securityInfo) throws Exception{
    LoanFlowHead loanFlowHead=loanFlowHeadDAO.queryById(new Integer(headId));
    if(!loanFlowHead.getBizSt().equals(String.valueOf(BusiConst.PLBUSINESSSTATE_IMP))){
      throw new BusinessException("不能进行登记操作！");
    }
    loanFlowHead.setBizSt(BusiConst.PLBUSINESSSTATE_SIGN+"");
    //插入业务日志
    PlBizActiveLog plBizActiveLog = new PlBizActiveLog();
    plBizActiveLog.setAction(BusiConst.PLBUSINESSSTATE_SIGN+"");
    plBizActiveLog.setBizid(new BigDecimal(loanFlowHead.getFlowHeadId().toString()));
    plBizActiveLog.setOperator(securityInfo.getUserName());
    plBizActiveLog.setOpTime(new Date());
    plBizActiveLog.setType(loanFlowHead.getBizType());
    plBizActiveLogDAO.insert(plBizActiveLog);
    
    //插入操作日志
    PlOperateLog plOperateLog = new PlOperateLog();
    plOperateLog.setOpSys(new BigDecimal(BusiLogConst.OP_SYSTEM_TYPE_LOAN));
    plOperateLog.setContractId(contractId);
    plOperateLog.setOpButton(BusiLogConst.BIZLOG_ACTION_ADD+"");
    plOperateLog.setOperator(securityInfo.getUserName());
    plOperateLog.setOpIp(securityInfo.getUserIp());
    plOperateLog.setOpModel(BusiLogConst.PL_OP_LOANRECOVER_BADDEBTSOFF_DO+"");
    plOperateLog.setOpTime(new Date());
    plOperateLogDAO.insert(plOperateLog);
    return loanFlowHead.getFlowHeadId().toString();
  }
  /**
   * 办理呆账核销删除
   * @param headId
   * @param securityInfo
   * @throws Exception
   */
  public void deleteCallbackInfoByBank(String headId,SecurityInfo securityInfo)throws Exception{
      LoanFlowHead loanFlowHead = loanFlowHeadDAO.queryById(new Integer(headId));
      String office = "";//办事处代码
      //判断该笔业务在PL202中的业务状态是否=2
      if(!loanFlowHead.getBizSt().equals(String.valueOf(BusiConst.PLBUSINESSSTATE_IMP))){
        throw new BusinessException("该笔业务为"+loanFlowHead.getBizSt()+"状态，不能删除！");
      }else{
        loanFlowTailDAO.deleteLoanFlowTailAll(headId.toString());
        //删除业务务日志
        plBizActiveLogDAO.deletePlBizActiveLog_LJ(headId.toString(),loanFlowHead.getBizSt());
        //撤销凭证号
        office = loanBankDAO.queryOfficeCodeByBankId_LJ(loanFlowHead.getLoanBankId().toString());
        plDocNumCancelDAO.insertPlDocNumCancel(loanFlowHead.getDocNum(), office, loanFlowHead.getBizDate().substring(0, 6));
        //删除头表
        loanFlowHeadDAO.delete(loanFlowHead);
      }
      //插入操作日志
      PlOperateLog plOperateLog = new PlOperateLog();
      plOperateLog.setOpSys(new BigDecimal(BusiLogConst.OP_SYSTEM_TYPE_LOAN));
      plOperateLog.setContractId(headId);
      plOperateLog.setOpBizId(new BigDecimal(headId));
      plOperateLog.setOpButton(BusiLogConst.BIZLOG_ACTION_DELETE+"");
      plOperateLog.setOperator(securityInfo.getUserName());
      plOperateLog.setOpIp(securityInfo.getUserIp());
      plOperateLog.setOpModel(BusiLogConst.PL_OP_LOANRECOVER_BADDEBTSOFF_DO+"");
      plOperateLog.setOpTime(new Date());
      plOperateLogDAO.insert(plOperateLog);

  }
  
  /**
   * 呆账核销维护列表显示
   * @param pagination
   * @param securityInfo
   * @return
   */
  public List findCallbackList(Pagination pagination,SecurityInfo securityInfo)throws Exception{
    List list = new ArrayList();;
    try{
    List tem_list = null;
    String contractId = (String)pagination.getQueryCriterions().get("contractId");
    String loanKouAcc = (String)pagination.getQueryCriterions().get("loanKouAcc");
    String name = (String)pagination.getQueryCriterions().get("name");
    String cardNum = (String)pagination.getQueryCriterions().get("cardNum");
    String loanBankId = (String)pagination.getQueryCriterions().get("loanBankId");
    String docNum = (String)pagination.getQueryCriterions().get("docNum");
    String status = (String)pagination.getQueryCriterions().get("status");
    String orderBy=(String) pagination.getOrderBy();;
    String order = (String) pagination.getOrderother(); 
    int start = pagination.getFirstElementOnPage() - 1;
    int pageSize = pagination.getPageSize(); 
    int page=pagination.getPage();
    tem_list = loanFlowHeadDAO.queryBadDestroyList_LJ(loanKouAcc, contractId, name, cardNum, docNum, loanBankId, status, orderBy, order, start, pageSize, securityInfo, page);
    if(!tem_list.isEmpty()){
      for(int i=0;i<tem_list.size();i++){
        BadDebtDestroyTbDTO dto = (BadDebtDestroyTbDTO)tem_list.get(i);
        BadDebtDestroyTbDTO dto2 = new BadDebtDestroyTbDTO();
            //转换业务状态
            dto2.setBizSt(BusiTools.getBusiValue(Integer.parseInt(dto.getBizSt()), BusiConst.PLBUSINESSSTATE));
            dto2.setBorrowerName(dto.getBorrowerName());
            dto2.setCardNum(dto.getCardNum());
            dto2.setContractId(dto.getContractId());
            dto2.setDocNum(dto.getDocNum());
            dto2.setId(dto.getId());
            dto2.setLoanKouAcc(dto.getLoanKouAcc());
            dto2.setRealCorpus(dto.getRealCorpus());
            dto2.setRealInterest(dto.getRealInterest());
            dto2.setRealMoney(dto2.getRealCorpus().add(dto2.getRealInterest()));
            list.add(dto2);
      }
    }
    int count = loanFlowHeadDAO.queryBadDestroyCount_LJ(loanKouAcc, contractId, name, cardNum, docNum,loanBankId, status, securityInfo);
    pagination.setNrOfElements(count);
    }catch(Exception e){
      e.printStackTrace();
    }
    return list;
  }
  
  /**
   * 维护列表合计
   * @param pagination
   * @param securityInfo
   * @return
   * @throws Exception
   */
  public BadDebtDestroyTbDTO findCallbackListTotal(Pagination pagination,SecurityInfo securityInfo)throws Exception{
    List list = new ArrayList();
    BadDebtDestroyTbDTO dto = new BadDebtDestroyTbDTO();
    BigDecimal realCorpus = new BigDecimal(0.00);
    BigDecimal realInterest = new BigDecimal(0.00);
    BigDecimal realMoney = new BigDecimal(0.00);
    try{
      String contractId = (String)pagination.getQueryCriterions().get("contractId");
      String loanKouAcc = (String)pagination.getQueryCriterions().get("loanKouAcc");
      String name = (String)pagination.getQueryCriterions().get("name");
      String cardNum = (String)pagination.getQueryCriterions().get("cardNum");
      String loanBankId = (String)pagination.getQueryCriterions().get("loanBankId");
      String docNum = (String)pagination.getQueryCriterions().get("docNum");
      String status = (String)pagination.getQueryCriterions().get("status");
      list = loanFlowHeadDAO.queryBadDestroyTotal_LJ(loanKouAcc, contractId, name, cardNum, docNum,loanBankId, status, securityInfo);
      if(!list.isEmpty()){
        for(int i=0;i<list.size();i++){
          BadDebtDestroyTbDTO dto1 = (BadDebtDestroyTbDTO)list.get(i);
          realCorpus = realCorpus.add(dto1.getRealCorpus());
          realInterest = realInterest.add(dto1.getRealInterest());
          realMoney = realMoney.add(dto1.getRealCorpus().add(dto1.getRealInterest()));
        }
      }
      dto.setRealCorpus(realCorpus);
      dto.setRealInterest(realInterest);
      dto.setRealMoney(realMoney);
    }catch(Exception e){
      e.printStackTrace();
    }
    return dto;
  }
  
  /**
   * 回收维护删除列表信息
   * @param rowArray
   * @param securityInfo
   * @throws Exception
   */
  public void deleteCallbackInfos(String headId,SecurityInfo securityInfo) throws Exception{
        LoanFlowHead loanFlowHead = loanFlowHeadDAO.queryById(new Integer(headId));
        String office = "";//办事处代码
        // 判断该笔业务在PL202中的业务状态BIZ_ST是否=2、3、4
        if(!(loanFlowHead.getBizSt().equals("2") || loanFlowHead.getBizSt().equals("3") || loanFlowHead.getBizSt().equals("4"))){
          String bizSt=BusiTools.getBusiValue(Integer.parseInt(loanFlowHead.getBizSt()), BusiConst.PLBUSINESSSTATE);
          throw new BusinessException(bizSt+"状态，不能删除！");
        }else{
          loanFlowTailDAO.deleteLoanFlowTailAll(headId);
          // 删除业务务日志
         plBizActiveLogDAO.deletePlBizActiveLog_LJ(headId, loanFlowHead.getBizSt());
         // 撤销凭证号
         office = loanBankDAO.queryOfficeCodeByBankId_LJ(loanFlowHead.getLoanBankId().toString());
         plDocNumCancelDAO.insertPlDocNumCancel(loanFlowHead.getDocNum(), office, loanFlowHead.getBizDate().substring(0, 6));
         //删除头表
         loanFlowHeadDAO.delete(loanFlowHead);
       }
      
      // 插入操作日志
      PlOperateLog plOperateLog = new PlOperateLog();
      plOperateLog.setOpSys(new BigDecimal(BusiLogConst.OP_SYSTEM_TYPE_LOAN));
      plOperateLog.setContractId(loanFlowHead.getFlowHeadId().toString());
      plOperateLog.setOpBizId(new BigDecimal(headId));
      plOperateLog.setOpButton(BusiLogConst.BIZLOG_ACTION_DELETE+"");
      plOperateLog.setOperator(securityInfo.getUserName());
      plOperateLog.setOpIp(securityInfo.getUserIp());
      plOperateLog.setOpModel(BusiLogConst.PL_OP_LOANRECOVER_MAINTAIN+"");
      plOperateLog.setOpTime(new Date());
      plOperateLogDAO.insert(plOperateLog);

  }
  
  /**
   * 回收维护回收
   * @param rowArray
   * @param securityInfo
   * @throws Exception
   */
  public void callbackCallbackInfo(String headId,SecurityInfo securityInfo) throws Exception{

        LoanFlowHead loanFlowHead = loanFlowHeadDAO.queryById(new Integer(headId));
        //判断该笔业务在贷款流水账头表PL202头表中的状态BIZ_ST是否=2.导入3.登记
        if(!loanFlowHead.getBizSt().equals(String.valueOf(BusiConst.PLBUSINESSSTATE_SIGN))&&
            !loanFlowHead.getBizSt().equals(String.valueOf(BusiConst.PLBUSINESSSTATE_IMP))){
          String bizSt = BusiTools.getBusiValue(Integer.parseInt(loanFlowHead.getBizSt()), BusiConst.PLBUSINESSSTATE);
          throw new BusinessException(bizSt+"状态，不能回收！");
        }else{
          loanFlowHead.setBizSt(BusiConst.BUSINESSSTATE_SURE+"");
          //插入业务日志
          PlBizActiveLog plBizActiveLog = new PlBizActiveLog();
          plBizActiveLog.setAction(BusiConst.BUSINESSSTATE_SURE+"");
          plBizActiveLog.setBizid(new BigDecimal(loanFlowHead.getFlowHeadId().toString()));
          plBizActiveLog.setOperator(securityInfo.getUserName());
          plBizActiveLog.setOpTime(new Date());
          plBizActiveLog.setType(loanFlowHead.getBizType());
          plBizActiveLogDAO.insert(plBizActiveLog);
        }
      //插入操作日志
      PlOperateLog plOperateLog = new PlOperateLog();
      plOperateLog.setOpSys(new BigDecimal(BusiLogConst.OP_SYSTEM_TYPE_LOAN));
      plOperateLog.setContractId(headId);
      plOperateLog.setOpBizId(new BigDecimal(headId));
      plOperateLog.setOpButton(BusiLogConst.BIZLOG_ACTION_CONFIRM+"");
      plOperateLog.setOperator(securityInfo.getUserName());
      plOperateLog.setOpIp(securityInfo.getUserIp());
      plOperateLog.setOpModel(BusiLogConst.PL_OP_LOANRECOVER_MAINTAIN+"");
      plOperateLog.setOpTime(new Date());
      plOperateLogDAO.insert(plOperateLog);

  }
  
  /**
   * 回收维护弹出窗口查询(单笔)
   * @param pagination
   * @return
   * @throws Exception
   */
  public BadDebtDestroyTaAFDTO findCallbackInfoMX(Pagination pagination) throws Exception{
    BadDebtDestroyTaAFDTO af = new BadDebtDestroyTaAFDTO();
    BorrowerInfoDTO borrowerInfoDTO = null;
    List borrowerList = null;
    List callbacklist = null;
    List taillist = new ArrayList();
    LoanFlowHead loanFlowHead = null;
    String contractId = (String)pagination.getQueryCriterions().get("contractId");
    String headId = (String)pagination.getQueryCriterions().get("headId");
    String bizDate = "";
    String dateStart = "";
    String dateEnd = "";
//  从PL110及PL111取信息
    borrowerList = borrowerAccDAO
        .queryBorrowerAccInfoByLoanKouAcc_LJ(contractId);
    if (!borrowerList.isEmpty()) {
      borrowerInfoDTO = (BorrowerInfoDTO) borrowerList.get(0);
    }
    borrowerInfoDTO.setCardKind(BusiTools.getBusiValue(Integer.parseInt(borrowerInfoDTO.getCardKind()), BusiConst.DOCUMENTSSTATE));
    borrowerInfoDTO.setLoanMode(BusiTools.getBusiValue(Integer.parseInt(borrowerInfoDTO.getLoanMode()), BusiConst.PLRECOVERTYPE));
    String loanRepayDay = borrowerInfoDTO.getLoanRepayDay();// 还款日
    callbacklist = loanFlowTailDAO.queryLoanFlowTailByHeadId_LJ(headId);
    loanFlowHead = loanFlowHeadDAO.queryById(new Integer(headId));
    bizDate = loanFlowHead.getBizDate();
    String yearMonth = bizDate.substring(0, 6);// 取出会计日期中的年月
    String year = yearMonth.substring(0, 4);
    String month = yearMonth.substring(4, 6);
    String day = bizDate.substring(6, bizDate.length());// 取出会计日期中的日
    String temp_bizDate = year + "-"
    + month + "-" + day;// 用来判断列表中的还款类型所转换的会计日期
    if(!callbacklist.isEmpty()){
      for(int i=0;i<callbacklist.size();i++){
        ShouldBackListDTO dto = (ShouldBackListDTO)callbacklist.get(i);
        ShouldBackListDTO dto2 = new ShouldBackListDTO();
        dto2.setLoanKouYearmonth(dto.getLoanKouYearmonth());
        String loanRepayDay1=this.getEndDay(dto.getLoanKouYearmonth(), loanRepayDay);
        String temp_date = dto.getLoanKouYearmonth().substring(0, 4) + "-"
            + dto.getLoanKouYearmonth().substring(4, 6) + "-" + loanRepayDay1;
        // 逾期天数
        int days = BusiTools.minusDate(temp_bizDate, temp_date);
        String type = dto.getLoanKouType();
        if(type.equals("1")){
          dto2.setLoanKouType("正常");
        }else if(type.equals("2")){
          dto2.setLoanKouType("逾期");
        }
        if(i==0){
          dateStart = dto.getLoanKouYearmonth();
        }
        if(i==(callbacklist.size()-1)){
          dateEnd = dto.getLoanKouYearmonth();
        }
        if(days<=0){
          days=0;
        }
        dto2.setShouldCorpus(dto.getShouldCorpus());
        dto2.setShouldInterest(dto.getShouldInterest());
        dto2.setPunishInterest(dto.getPunishInterest());
        dto2.setCiMoney(dto.getShouldCorpus().add(dto.getShouldInterest()).add(dto.getPunishInterest()));
        dto2.setLoanRate(dto.getLoanRate());
        dto2.setShow_loanRate(dto.getLoanRate().multiply(new BigDecimal(100))+"%");
        dto2.setRealCorpus(dto.getRealCorpus());
        dto2.setRealInterest(dto.getRealInterest());
        dto2.setRealPunishInterest(dto.getRealPunishInterest());
        dto2.setRealCiMoney(dto.getRealCorpus().add(dto.getRealInterest()).add(dto.getRealPunishInterest()));
        if(dto.getLoanRate().doubleValue()==0){
          dto2.setShow_loanRate("");
        }
        dto2.setDays(days+"");
        taillist.add(dto2);
      }
    }
    String orgId = loanFlowHead.getHedaiOrg();
    String orgName = "";
    if(orgId!=null&&!orgId.equals("")){
      orgName = assistantOrgDAO.queryAssistantOrgNameByAssistantOrgId(orgId);
    }
    af.setOrgName(orgName);
    af.setBorrowerInfoDTO(borrowerInfoDTO);
    af.setShouldBackList(taillist);
    af.setBizType(BusiTools.getBusiValue(Integer.parseInt(loanFlowHead.getBizType()), BusiConst.PLBUSINESSTYPE));
    if(loanFlowHead.getBizType().equals("6")){
      af.setOrgType("中心");
    }
    if(loanFlowHead.getHedaiOrg()!=null){
      af.setOrgType("担保公司");
      af.setOrgName(orgName);
    }
    else{
      af.setOrgType("其他");
    }
    af.setSumCorpus(loanFlowHead.getRealCorpus().add(loanFlowHead.getRealOverdueCorpus()));
    af.setSumInterest(loanFlowHead.getRealInterest().add(loanFlowHead.getRealOverdueInterest()).add(loanFlowHead.getRealPunishInterest()));
    af.setSumMoney(af.getSumCorpus().add(af.getSumInterest()));
    af.setRealMoney(af.getSumMoney());
    af.setMonthYear(dateEnd);
    af.setHeadId(loanFlowHead.getFlowHeadId().toString());
    return af;
  }

  /**
   * 呆账核销维护打印信息
   * @param headId
   * @return
   */
  public BadDebtDestroyTaAFDTO findPrintCallbackInfo(String headId){
    BadDebtDestroyTaAFDTO af = new BadDebtDestroyTaAFDTO();
    LoanFlowHead loanFlowHead = loanFlowHeadDAO.queryById(new Integer(headId));
    List taillist = loanFlowTailDAO.queryPrintLoanFlowTailByHeadId_LJ(headId);
    String contractId="";
    BorrowerInfoDTO borrowerInfoDTO = null;
    String yearMonth="";
    List borrowerList = null;
    if(!taillist.isEmpty()){
      ShouldBackListDTO dto = (ShouldBackListDTO)taillist.get(0);
      ShouldBackListDTO dto1 = (ShouldBackListDTO)taillist.get(taillist.size()-1);
      contractId =dto.getContractId();
      yearMonth = dto1.getLoanKouYearmonth();
      //从PL110及PL111取信息
      borrowerList = borrowerAccDAO
          .queryBorrowerAccInfoByLoanKouAcc_LJ(contractId);
      if (!borrowerList.isEmpty()) {
        borrowerInfoDTO = (BorrowerInfoDTO) borrowerList.get(0);
      }
    }
    String loanBankId = loanFlowHead.getLoanBankId().toString();
    CollBank collBank = collBankDAO.getCollBankByCollBankid_(loanBankId);
    List acclist = loanBankDAO.queryLoanBankAccByBankId_LJ(loanBankId);
    Iterator it = acclist.iterator();
    Object obj [] = null;
    while(it.hasNext()){
      obj=(Object [])it.next();
      if(obj[0] != null){
        af.setLoanAcc(obj[0].toString());
      }
      if(obj[1] != null){
        af.setInterestAcc(obj[1].toString());        
      }
    }
    af.setSumCorpus(loanFlowHead.getRealCorpus().add(loanFlowHead.getRealOverdueCorpus()));
    af.setSumInterest(loanFlowHead.getRealInterest().add(loanFlowHead.getRealOverdueInterest())
        .add(loanFlowHead.getRealPunishInterest()));
    af.setRealInterest(loanFlowHead.getRealInterest());
    af.setOverdueInterest(loanFlowHead.getRealOverdueInterest());
    af.setPunishInterest(loanFlowHead.getRealPunishInterest());
    af.setMakeOP(securityDAO.queryByUserid(loanFlowHead.getMakePerson()));
    af.setClearOP(securityDAO.queryByUserid(loanFlowHead.getCheckPerson()));
    af.setBankName(collBank.getCollBankName());
    af.setShouldBackList(taillist);
    String bizType = loanFlowHead.getBizType();
    if(bizType.equals(String.valueOf(BusiConst.PLBUSINESSTYPE_BADDEBTSOFFCENTRE))){
      af.setBizType("呆账核销（中心），");
    }else if(bizType.equals(String.valueOf(BusiConst.PLBUSINESSTYPE_BADDEBTSOFFOTHER))){
      if(loanFlowHead.getHedaiOrg() != null){
        af.setBizType("呆账核销，核销单位："+loanFlowHead.getHedaiOrg()+"，");
      }else{
        af.setBizType("呆账核销（其他），");
      }
    }
    af.setMonthYear(yearMonth.substring(0,4)+"年"+yearMonth.substring(4,6)+"月");
    af.setBorrowerInfoDTO(borrowerInfoDTO);
    String bizDate = loanFlowHead.getBizDate();
    af.setBizDate(bizDate);
    af.setRealMoney(af.getSumCorpus().add(af.getSumInterest()));
    af.setDocNum(loanFlowHead.getDocNum());
    af.setNoteNum(loanFlowHead.getNoteNum());
    return af;
  }
  /**
   * 计算逾期天数
   * @param bizDate 业务日期
   * @param yearMonth 还款年月
   * @param loanRepayDay 还款日
   * @return
   */
  public int getDays(String bizDate,String yearMonth,String loanRepayDay){
    int days=0;
    String temp_date = yearMonth.substring(0, 4) + "-"
    + yearMonth.substring(4, 6) + "-" + loanRepayDay;
    String temp_bizDate = bizDate.substring(0, 4) + "-"
    + bizDate.substring(4,6)+"-"+bizDate.substring(6,8);
    // 逾期天数
    days = BusiTools.minusDate(temp_bizDate, temp_date);
    return days;
  }
  /**
   * 取得年结年份
   * @param loanBankId
   * @return
   */
  public String getClearYear(String loanBankId){
    String year = "";//年结年份
    year = loanBankDAO.queryYearClearByBankId_sy(loanBankId);
    return year;
  }

  public CollParaDAO getCollParaDAO() {
    return collParaDAO;
  }

  public void setCollParaDAO(CollParaDAO collParaDAO) {
    this.collParaDAO = collParaDAO;
  }
 
}