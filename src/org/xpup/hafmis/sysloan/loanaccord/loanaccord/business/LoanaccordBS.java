package org.xpup.hafmis.sysloan.loanaccord.loanaccord.business;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.xpup.common.exception.BusinessException;
import org.xpup.common.util.Pagination;
import org.xpup.hafmis.common.util.BusiConst;
import org.xpup.hafmis.common.util.BusiTools;
import org.xpup.hafmis.common.util.bizlog.BusiLogConst;
import org.xpup.hafmis.orgstrct.dao.CollBankDAO;
import org.xpup.hafmis.orgstrct.domain.CollBank;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.syscollection.common.dao.CollParaDAO;
import org.xpup.hafmis.sysloan.common.dao.BorrowerAccDAO;
import org.xpup.hafmis.sysloan.common.dao.BorrowerLoanInfoDAO;
import org.xpup.hafmis.sysloan.common.dao.LoanBankDAO;
import org.xpup.hafmis.sysloan.common.dao.LoanBankParaDAO;
import org.xpup.hafmis.sysloan.common.dao.LoanFlowHeadDAO;
import org.xpup.hafmis.sysloan.common.dao.LoanFlowTailDAO;
import org.xpup.hafmis.sysloan.common.dao.PlBizActiveLogDAO;
import org.xpup.hafmis.sysloan.common.dao.PlDocNumCancelDAO;
import org.xpup.hafmis.sysloan.common.dao.PlDocNumMaintainDAO;
import org.xpup.hafmis.sysloan.common.dao.PlOperateLogDAO;
import org.xpup.hafmis.sysloan.common.domain.entity.BorrowerAcc;
import org.xpup.hafmis.sysloan.common.domain.entity.BorrowerLoanInfo;
import org.xpup.hafmis.sysloan.common.domain.entity.LoanFlowHead;
import org.xpup.hafmis.sysloan.common.domain.entity.LoanFlowTail;
import org.xpup.hafmis.sysloan.common.domain.entity.PlBizActiveLog;
import org.xpup.hafmis.sysloan.common.domain.entity.PlOperateLog;
import org.xpup.hafmis.sysloan.loanaccord.loanaccord.bsinterface.ILoanaccordBS;
import org.xpup.hafmis.sysloan.loanaccord.loanaccord.dto.LoanaccordDTO;
import org.xpup.hafmis.sysloan.loanaccord.loanaccord.form.LoanaccordNewAF;
import org.xpup.hafmis.sysloan.loanaccord.loanaccord.form.LoanaccordShowAF;

public class LoanaccordBS implements ILoanaccordBS {
  private BorrowerAccDAO borrowerAccDAO = null;

  private BorrowerLoanInfoDAO borrowerLoanInfoDAO = null;

  private CollBankDAO collBankDAO = null;

  private LoanBankParaDAO loanBankParaDAO = null;

  private LoanFlowHeadDAO loanFlowHeadDAO = null;

  private LoanFlowTailDAO loanFlowTailDAO = null;

  private CollParaDAO collParaDAO = null;

  private PlOperateLogDAO plOperateLogDAO = null;

  private PlBizActiveLogDAO plBizActiveLogDAO = null;

  private PlDocNumMaintainDAO plDocNumMaintainDAO = null;

  private PlDocNumCancelDAO plDocNumCancelDAO = null;

  private LoanBankDAO loanBankDAO = null; // 查找是否年节pl002

  public void setBorrowerAccDAO(BorrowerAccDAO borrowerAccDAO) {
    this.borrowerAccDAO = borrowerAccDAO;
  }

  public void setBorrowerLoanInfoDAO(BorrowerLoanInfoDAO borrowerLoanInfoDAO) {
    this.borrowerLoanInfoDAO = borrowerLoanInfoDAO;
  }

  public void setCollBankDAO(CollBankDAO collBankDAO) {
    this.collBankDAO = collBankDAO;
  }

  public void setLoanBankParaDAO(LoanBankParaDAO loanBankParaDAO) {
    this.loanBankParaDAO = loanBankParaDAO;
  }

  public void setLoanFlowHeadDAO(LoanFlowHeadDAO loanFlowHeadDAO) {
    this.loanFlowHeadDAO = loanFlowHeadDAO;
  }

  public void setLoanFlowTailDAO(LoanFlowTailDAO loanFlowTailDAO) {
    this.loanFlowTailDAO = loanFlowTailDAO;
  }

  public void setPlOperateLogDAO(PlOperateLogDAO plOperateLogDAO) {
    this.plOperateLogDAO = plOperateLogDAO;
  }

  public void setPlBizActiveLogDAO(PlBizActiveLogDAO plBizActiveLogDAO) {
    this.plBizActiveLogDAO = plBizActiveLogDAO;
  }

  public void setPlDocNumMaintainDAO(PlDocNumMaintainDAO plDocNumMaintainDAO) {
    this.plDocNumMaintainDAO = plDocNumMaintainDAO;
  }

  public void setPlDocNumCancelDAO(PlDocNumCancelDAO plDocNumCancelDAO) {
    this.plDocNumCancelDAO = plDocNumCancelDAO;
  }

  public void setLoanBankDAO(LoanBankDAO loanBankDAO) {
    this.loanBankDAO = loanBankDAO;
  }

  public String getLoanBankId(String contractId) throws BusinessException {

    return null;
  }

  // 查找合同相关信息显示。
  public LoanaccordNewAF queryLoanaccordInfo(String contractId,
      SecurityInfo securityInfo) throws BusinessException {
    LoanaccordNewAF loanaccordNewAF = new LoanaccordNewAF();
    try {
      // 合同的状态
      String contractSt = "9";
      String bizDate = securityInfo.getUserInfo().getPlbizDate();// 业务日期
      List list = borrowerLoanInfoDAO.queryBorrowerLoanInfo_sy(contractSt,
          contractId, securityInfo);
      if (!list.isEmpty()) {
        String is = loanBankDAO.queryIsBoKuan(contractId);
        if ("0".equals(is)) {
          throw new BusinessException("该笔合同未拨款，不能发放！");
        }
        LoanaccordDTO loanaccordDTO = (LoanaccordDTO) list.get(0);
        // //获取最新得利率，先是判断银行是否采用最新的利率。如果没有采用证明利率启用得时候没有更新得pl115得信息。所以要判断是否重新获取利率。
        // //查看银行是否采用新的利率
        // List islist=loanBankParaDAO.queryLoanBankPara_sy(new
        // Integer(loanaccordDTO.getLoanBankId()));
        // if(islist.isEmpty()){
        // List
        // islistNewMonthRate=borrowerAccDAO.findUseMontRate_sy(loanaccordDTO.getLoanBankId(),loanaccordDTO.getLoanRateType());
        // Object infonewmonthrate[] = (Object[]) islistNewMonthRate.get(0);
        // if ((infonewmonthrate[1]+"").equals("1")){
        // loanaccordDTO.setLoanMonthRate(new
        // BigDecimal(infonewmonthrate[0]+""));
        // }
        // }
        // 显示的月利率
        loanaccordDTO.setTemploanMonthRate(loanaccordDTO.getLoanMonthRate()
            .multiply(new BigDecimal(100.00)).toString()
            + "%");
        // 合同编号
        loanaccordDTO.setContractId(contractId);
        // 证件类型对应的名称
        loanaccordDTO.setCardKindName(BusiTools.getBusiValue(Integer
            .parseInt(loanaccordDTO.getCardKind()), BusiConst.DOCUMENTSSTATE));
        // 通过bankId查找银行的名称
        String bankId = loanaccordDTO.getLoanBankId();
        String bankDate = "";// 银行日期
        bankDate = loanBankDAO.queryPL002BizDate_jj(bankId);
        if (!bizDate.equals(bankDate)) {
          throw new BusinessException("登录日期与银行业务日期不一致，不能做业务！");
        }
        CollBank collBank = collBankDAO.getCollBankByCollBankid_(bankId);
        loanaccordDTO.setLoanBankName(collBank.getCollBankName());
        // 对应银行对应的办事处
        loanaccordDTO.setOffice(collBank.getOffice());
        // 还款方式
        loanaccordDTO.setLoanModeName(BusiTools.getBusiValue(Integer
            .parseInt(loanaccordDTO.getLoanMode()), BusiConst.PLRECOVERTYPE));
        if (Integer.parseInt(loanaccordDTO.getLoanMode()) > 3) {
          loanaccordDTO.setIsEntire("1");
        } else {
          loanaccordDTO.setIsEntire(null);
        }
        // 发放日期
        loanaccordDTO.setLoanStartDate(securityInfo.getUserInfo()
            .getPlbizDate());
        // 计算发放日期
        String tempLoanStartDate = securityInfo.getUserInfo().getPlbizDate();
        // 获取借款期限
        String tempLoanTimeLimit = loanaccordDTO.getLoanTimeLimit();
        int temp_addYear = (Integer.parseInt(tempLoanStartDate.substring(4, 6)) + Integer
            .parseInt(tempLoanTimeLimit)) / 12;
        int temp_moth = (Integer.parseInt(tempLoanStartDate.substring(4, 6)) + Integer
            .parseInt(tempLoanTimeLimit)) % 12;
        // 判断是不是最后一个月的天数，如果是看看你那一个月
        int month_day = BusiTools.daysOfMonth((Integer
            .parseInt(tempLoanStartDate.substring(0, 4)) + temp_addYear),
            temp_moth);
        // 还款的最后天数
        String overDay = "";
        if (month_day < Integer.parseInt(tempLoanStartDate.substring(6, 8))) {
          overDay = month_day + "";
        } else {
          overDay = tempLoanStartDate.substring(6, 8);
        }
        // 到期日期
        String overTime = "";
        if (temp_moth < 10) {
          overTime = (Integer.parseInt(tempLoanStartDate.substring(0, 4)) + temp_addYear)
              + "" + "0" + temp_moth + "" + overDay;
        } else {
          overTime = (Integer.parseInt(tempLoanStartDate.substring(0, 4)) + temp_addYear)
              + "" + temp_moth + "" + overDay;
        }
        // 判断是统一定日，还是按户定日
        List loanBankParaInfo = loanBankParaDAO.queryLoanBankPara_sy(bankId,
            "2", "A");
        Object Info[] = (Object[]) loanBankParaInfo.get(0);
        String loanRepayDay = "";
        if ((Info[0] + "").equals("1")) {
          loanRepayDay = tempLoanStartDate.substring(6, 8);
          loanaccordDTO.setLoanRepayDay(loanRepayDay);
          loanaccordDTO.setLoanRepayDayInfo("1");
        } else {
          loanRepayDay = Info[1] + "";
          loanaccordDTO.setLoanRepayDay(loanRepayDay);
          loanaccordDTO.setLoanRepayDayInfo("2");
        }
        // 计算首月还款金额
        String firstLoanMoney = "";
        int temploanRepayDay = new Integer(loanaccordDTO.getLoanRepayDay())
            .intValue();
        // 计算出发放是月总共多少天:这个月的天数-方法日期+下个还款日
        int tempmonth_day = BusiTools.daysOfMonth((Integer
            .parseInt(tempLoanStartDate.substring(0, 4))), (Integer
            .parseInt(tempLoanStartDate.substring(4, 6))));
        if (temploanRepayDay > 28) {
          // 判断第一个月得还款日是不是有这一天。
          int tempnextday = 0;
          if (Integer.parseInt(tempLoanStartDate.substring(4, 6)) == 12) {
            tempnextday = BusiTools.daysOfMonth((Integer
                .parseInt(tempLoanStartDate.substring(0, 4))), Integer
                .parseInt("01"));
          } else {
            tempnextday = BusiTools.daysOfMonth((Integer
                .parseInt(tempLoanStartDate.substring(0, 4))), (Integer
                .parseInt(tempLoanStartDate.substring(4, 6))) + 1);
          }
          if (temploanRepayDay > tempnextday) {
            temploanRepayDay = tempnextday;
          }
        }
        int fristday = tempmonth_day
            - Integer.parseInt(tempLoanStartDate.substring(6, 8))
            + temploanRepayDay;
        if (loanaccordDTO.getLoanMode().equals("2")) {
          // 等额本息
          // 按户定日
          if (loanaccordDTO.getLoanRepayDayInfo().equals("1")) {
            // 月还本息
            firstLoanMoney = loanaccordDTO.getCorpusInterest().toString();
          }
          // 统一定日
          else {
            // 应还利息+应还本金
            firstLoanMoney = ((loanaccordDTO.getLoanMoney()
                .multiply(new BigDecimal(fristday + "")))
                .multiply(loanaccordDTO.getLoanMonthRate()).divide(
                new BigDecimal("30"), 2, BigDecimal.ROUND_HALF_UP)).add(
                loanaccordDTO.getCorpusInterest().subtract(
                    loanaccordDTO.getLoanMoney().multiply(
                        loanaccordDTO.getLoanMonthRate()))).divide(
                new BigDecimal(1.00), 2, BigDecimal.ROUND_HALF_UP).toString();
          }
        } else {
          // 等额本金

          // 按户定日
          if (loanaccordDTO.getLoanRepayDayInfo().equals("1")) {
            // 等于剩余本金*新利率+月还款金额
            firstLoanMoney = (loanaccordDTO.getLoanMoney()
                .multiply(loanaccordDTO.getLoanMonthRate())).add(
                loanaccordDTO.getLoanMoney().divide(
                    new BigDecimal(tempLoanTimeLimit), 2,
                    BigDecimal.ROUND_HALF_UP)).divide(new BigDecimal(1.00), 2,
                BigDecimal.ROUND_HALF_UP).toString();
          }
          // 统一定日
          else {

            //
            firstLoanMoney = ((loanaccordDTO.getLoanMoney()
                .multiply(loanaccordDTO.getLoanMonthRate()))
                .multiply(new BigDecimal(fristday + "")).divide(new BigDecimal(
                "30"), 2, BigDecimal.ROUND_HALF_UP)).add(
                loanaccordDTO.getLoanMoney().divide(
                    new BigDecimal(tempLoanTimeLimit), 2,
                    BigDecimal.ROUND_HALF_UP)).divide(new BigDecimal(1.00), 2,
                BigDecimal.ROUND_HALF_UP).toString();
          }
        }
        loanaccordDTO.setFirstLoanMoney(new BigDecimal(firstLoanMoney));
        loanaccordDTO.setOverTime(overTime);
        loanaccordNewAF.setLoanaccordDTO(loanaccordDTO);
      } else {
        throw new BusinessException("此合同不存在请核实后再发放");
      }
    } catch (BusinessException bx) {
      throw bx;
    } catch (Exception e) {
      e.printStackTrace();
    }
    return loanaccordNewAF;
  }

  // 确认合同，发放
  public String updateBorrowerAccInfo(LoanaccordDTO loanaccordDTO,
      SecurityInfo securityInfo) throws BusinessException {
    String flowHeadId = "";
    try {
      // 新扣款账号

      // String newLoanKouAcc = loanaccordDTO.getLoanKouAcc();
      // Integer count = borrowerAccDAO
      // .findBorrowerAccByLoanKouAcc_sy(newLoanKouAcc);
      // if (count.intValue() > 0) {
      // throw new BusinessException("贷款账号已使用，确认核实账号！");
      // }
      // 更新对应的合同号
      String contractId = loanaccordDTO.getContractId();
      BorrowerAcc borrowerAcc = borrowerAccDAO.queryById(contractId);
      if (borrowerAcc.getContractSt().equals("10")) {
        throw new BusinessException("此合同已经发放请核实！");
      }
      if(borrowerAcc.getMarkA()!=null && !borrowerAcc.getMarkA().equals("1")){
        throw new BusinessException("此合同财务未审核！");
      }
      String tempyear = new Integer(securityInfo.getUserInfo().getPlbizDate()
          .substring(0, 4)).intValue()
          - 1 + ""; // 查看结转年度结转时间
      String yearClear = "";
      yearClear = loanBankDAO.queryYearClearByBankId_sy(loanaccordDTO
          .getLoanBankId());
      if (!yearClear.equals(tempyear)) {
        throw new BusinessException("上一年没有年结不能发放！");
      }
      // 更新pl111记录
      // 还款帐号
      borrowerAcc.setLoanKouAcc(loanaccordDTO.getLoanKouAcc());
      // 贷款金额
      borrowerAcc.setLoanMoney(loanaccordDTO.getLoanMoney());
      // 贷款期限
      borrowerAcc.setLoanTimeLimit(loanaccordDTO.getLoanTimeLimit());
      // 贷款类型
      borrowerAcc.setLoanRateType(loanaccordDTO.getLoanRateType());
      // 还款方式
      borrowerAcc.setLoanMode(loanaccordDTO.getLoanMode());
      // 发放日期
      borrowerAcc.setLoanStartDate(loanaccordDTO.getLoanStartDate());
      // 还款日
      borrowerAcc.setLoanRepayDay(new BigDecimal(loanaccordDTO
          .getLoanRepayDay()));
      // 剩余金额
      borrowerAcc.setOverplusLoanMoney(loanaccordDTO.getLoanMoney());
      // 剩余期限
      borrowerAcc.setOverplusLimite(loanaccordDTO.getLoanTimeLimit());
      // 更新新的状态
      borrowerAcc.setContractSt(BusiConst.PLCONTRACTSTATUS_ISSUING + "");
      // 更新pl115的首月还款金额
      BorrowerLoanInfo borrowerLoanInfo = new BorrowerLoanInfo();
      borrowerLoanInfo = borrowerLoanInfoDAO.queryById(contractId);
      borrowerLoanInfo.setFirstLoanMoney(loanaccordDTO.getFirstLoanMoney());
      // borrowerLoanInfo.setLoanMonthRate(loanaccordDTO.getLoanMonthRate());
      // 插入贷款流水账头表
      LoanFlowHead loanFlowHead = new LoanFlowHead();
      // 会计日期
      loanFlowHead.setBizDate(securityInfo.getUserInfo().getPlbizDate());
      // 业务类型.发放
      loanFlowHead.setBizType(BusiConst.PLBUSINESSTYPE_ISSUED + "");
      // 应还正常本金
      loanFlowHead.setShouldCorpus(new BigDecimal(0.00));
      // 应还利息
      loanFlowHead.setShouldInterest(new BigDecimal(0.00));
      // 应还逾期本金
      loanFlowHead.setShouldOverdueCorpus(new BigDecimal(0.00));
      // 应还逾期利息
      loanFlowHead.setShouldOverdueInterest(new BigDecimal(0.00));
      // 应还罚息
      loanFlowHead.setShouldPunishInterest(new BigDecimal(0.00));
      // 实还本金
      loanFlowHead.setRealCorpus(new BigDecimal(0.00));
      // 实还利息
      loanFlowHead.setRealInterest(new BigDecimal(0.00));
      // 实还逾期本金
      loanFlowHead.setRealOverdueCorpus(new BigDecimal(0.00));
      // 实还逾期利息
      loanFlowHead.setRealOverdueInterest(new BigDecimal(0.00));
      // 实还罚息
      loanFlowHead.setRealPunishInterest(new BigDecimal(0.00));
      // 其他利息
      loanFlowHead.setOtherInterest(new BigDecimal(0.00));
      // 发生金额
      loanFlowHead.setOccurMoney(loanaccordDTO.getLoanMoney());
      // 取凭证号
      String officeId = "";
      // String loanDocNumDocument=collParaDAO.getLoanDocNumDesignPara();
      // if(loanDocNumDocument.equals("1")){
      officeId = loanaccordDTO.getOffice();
      // }else{
      // officeId=loanaccordDTO.getLoanBankId();
      // }
      String bizYearmonth = securityInfo.getUserInfo().getPlbizDate()
          .substring(0, 6);
      String loanbankid = borrowerAccDAO.getLoanBankId(contractId);
      String docNum = plDocNumMaintainDAO.getDocNumdocNum(loanbankid,
          bizYearmonth.substring(0, 4));
      Map office = securityInfo.getOfficeInnerCodeMap();
      String officecode = office.get(officeId).toString();
      if (officecode.length() == 1) {
        officecode = "0" + officecode;
      }
      docNum = bizYearmonth.substring(0, 4) + officecode + "0" + loanbankid
          + docNum;
      loanFlowHead.setDocNum(docNum);
      // 放款银行
      loanFlowHead.setLoanBankId(new BigDecimal(loanaccordDTO.getLoanBankId()));
      // 业务状态
      loanFlowHead.setBizSt(String.valueOf(BusiConst.BUSINESSSTATE_SURE));
      // 制单人
      loanFlowHead.setMakePerson(securityInfo.getUserInfo().getUsername());
      // 财务是否取数
      loanFlowHead.setIsFinance(new Integer("1"));
      flowHeadId = loanFlowHeadDAO.insert(loanFlowHead) + "";
      // 更新自己插入票据编号
      // 系统自动生成结算号：业务日期+流水号
      String noteNum = "";
      String bizDate = securityInfo.getUserInfo().getBizDate();
      noteNum = bizDate + loanFlowHeadDAO.queryNoteNum();
      LoanFlowHead loanFlowHeadUpdate = loanFlowHeadDAO.queryById(new Integer(
          flowHeadId));
      loanFlowHeadUpdate.setNoteNum(noteNum);
      // 插入贷款流水账尾表
      LoanFlowTail loanFlowTail = new LoanFlowTail();
      // 发生金额
      loanFlowTail.setOccurMoney(loanaccordDTO.getLoanMoney());
      // 头表流水号
      loanFlowTail.setFlowHeadId(new BigDecimal(flowHeadId));
      // 贷款账号
      loanFlowTail.setLoanKouAcc(loanaccordDTO.getLoanKouAcc());
      // 插入合同编号
      loanFlowTail.setContractId(contractId);
      loanFlowTailDAO.insert(loanFlowTail);
      // 插入操作日志
      String opModel = BusiLogConst.PL_OP_LOANISSUED_DO + "";
      String opButton = BusiLogConst.BIZLOG_ACTION_CONFIRM + "";
      this.addPlOperateLog_sy(contractId, opModel, opButton, flowHeadId,
          securityInfo);
      // 业务活动日志
      PlBizActiveLog plBizActiveLog = new PlBizActiveLog();
      plBizActiveLog.setBizid(new BigDecimal(flowHeadId));
      plBizActiveLog.setAction(String.valueOf(BusiConst.BUSINESSSTATE_SURE));
      plBizActiveLog.setOpTime(new Date());
      plBizActiveLog.setOperator(securityInfo.getUserInfo().getUsername());
      plBizActiveLog.setType(String.valueOf(BusiConst.PLBUSINESSTYPE_ISSUED));
      plBizActiveLogDAO.insert(plBizActiveLog);
    } catch (BusinessException bx) {
      throw bx;
    } catch (Exception e) {
      e.printStackTrace();
    }

    return flowHeadId;
  }

  // 插入pl021操作日志
  public void addPlOperateLog_sy(String contractId, String opModel,
      String opButton, String opBizId, SecurityInfo securityInfo) {
    PlOperateLog plOperateLog = new PlOperateLog();
    plOperateLog.setOpSys(new BigDecimal(BusiLogConst.OP_SYSTEM_TYPE_LOAN));
    plOperateLog.setOpModel(opModel);
    plOperateLog.setOpButton(opButton);
    plOperateLog.setOpBizId(new BigDecimal(opBizId));
    plOperateLog.setOpIp(securityInfo.getUserInfo().getUserIp());
    plOperateLog.setOpTime(new Date());
    plOperateLog.setContractId(contractId);
    plOperateLog.setOperator(securityInfo.getUserInfo().getUsername());
    plOperateLogDAO.insert(plOperateLog);
  }

  // 查找流水表中业务初始状态为4,5,6的记录
  public LoanaccordShowAF queryLoanaccordList(Pagination pagination,
      SecurityInfo securityInfo) throws BusinessException {
    LoanaccordShowAF loanaccordShowAF = new LoanaccordShowAF();
    try {
      String orderBy = (String) pagination.getOrderBy();
      String order = (String) pagination.getOrderother();
      int start = pagination.getFirstElementOnPage() - 1;
      int pageSize = pagination.getPageSize();
      int page = pagination.getPage();
      List returnList = new ArrayList();
      String loanBankId = (String) pagination.getQueryCriterions().get(
          "loanBankId");
      String contractId = (String) pagination.getQueryCriterions().get(
          "contractId");
      String borrowerName = (String) pagination.getQueryCriterions().get(
          "borrowerName");
      String cardNum = (String) pagination.getQueryCriterions().get("cardNum");
      String bizSt = (String) pagination.getQueryCriterions().get("bizSt");
      String loanStartDate = (String) pagination.getQueryCriterions().get(
          "loanStartDate");
      String loanEndDate = (String) pagination.getQueryCriterions().get(
          "loanEndDate");
      
      List loanaccordList = borrowerLoanInfoDAO.queryBorrowerLoanList(bizSt,
          loanBankId, contractId, "1", borrowerName, cardNum, orderBy, order,
          start, pageSize, securityInfo, page, loanStartDate, loanEndDate);
      if (!loanaccordList.isEmpty()) {
        for (int i = 0; i < loanaccordList.size(); i++) {
          LoanaccordDTO loanaccordDTO = new LoanaccordDTO();
          loanaccordDTO = (LoanaccordDTO) loanaccordList.get(i);
          // 显示用的月利率
          loanaccordDTO.setTemploanMonthRate(loanaccordDTO.getLoanMonthRate()
              .multiply(new BigDecimal(100.00)).toString()
              + "%");
          // 证件类型对应的名称
          loanaccordDTO
              .setCardKindName(BusiTools.getBusiValue(Integer
                  .parseInt(loanaccordDTO.getCardKind()),
                  BusiConst.DOCUMENTSSTATE));
          // 通过bankId查找银行的名称
          String bankId = loanaccordDTO.getLoanBankId();
          CollBank collBank = collBankDAO.getCollBankByCollBankid_(bankId);
          loanaccordDTO.setLoanBankName(collBank.getCollBankName());

          // 还款方式
          loanaccordDTO.setLoanModeName(BusiTools.getBusiValue(Integer
              .parseInt(loanaccordDTO.getLoanMode()), BusiConst.PLRECOVERTYPE));
          //
          loanaccordDTO.setBizStName(BusiTools.getBusiValue(Integer
              .parseInt(loanaccordDTO.getBizSt()), BusiConst.PLBUSINESSSTATE));
          // 获取借款期限
          String tempLoanTimeLimit = loanaccordDTO.getLoanTimeLimit();
          String tempLoanStartDate = loanaccordDTO.getLoanStartDate();
          int temp_addYear = (Integer.parseInt(tempLoanStartDate
              .substring(4, 6)) + Integer.parseInt(tempLoanTimeLimit)) / 12;
          int temp_moth = (Integer.parseInt(tempLoanStartDate.substring(4, 6)) + Integer
              .parseInt(tempLoanTimeLimit)) % 12;
          // 判断是不是最后一个月的天数，如果是看看你那一个月
          int month_day = BusiTools.daysOfMonth((Integer
              .parseInt(tempLoanStartDate.substring(0, 4)) + temp_addYear),
              temp_moth);
          // 还款的最后天数
          String overDay = "";
          if (month_day < Integer.parseInt(tempLoanStartDate.substring(6, 8))) {
            overDay = month_day + "";
          } else {
            overDay = tempLoanStartDate.substring(6, 8);
          }
          // 到期日期
          String overTime = "";
          if (temp_moth < 10) {
            if (temp_moth == 0) {
              overTime = (Integer.parseInt(tempLoanStartDate.substring(0, 4))
                  + temp_addYear - 1)
                  + "" + "12" + "" + overDay;
            } else {
              overTime = (Integer.parseInt(tempLoanStartDate.substring(0, 4)) + temp_addYear)
                  + "" + "0" + temp_moth + "" + overDay;
            }
          } else {
            overTime = (Integer.parseInt(tempLoanStartDate.substring(0, 4)) + temp_addYear)
                + "" + temp_moth + "" + overDay;
          }
          loanaccordDTO.setOverTime(overTime);
          // 判断是统一定日，还是按户定日
          List loanBankParaInfo = loanBankParaDAO.queryLoanBankPara_sy(bankId,
              "2", "A");
          Object Info[] = (Object[]) loanBankParaInfo.get(0);
          String loanRepayDay = "";
          if ((Info[0] + "").equals("1")) {
            loanRepayDay = tempLoanStartDate.substring(6, 8);
            loanaccordDTO.setLoanRepayDay(loanRepayDay);
            loanaccordDTO.setLoanRepayDayInfo("1");
          } else {
            loanRepayDay = Info[1] + "";
            loanaccordDTO.setLoanRepayDay(loanRepayDay);
            loanaccordDTO.setLoanRepayDayInfo("2");
          }
          returnList.add(loanaccordDTO);
        }
      }
      List loanaccordAllList = borrowerLoanInfoDAO.countBorrowerLoanList(bizSt,
          loanBankId, contractId, "1", borrowerName, cardNum, securityInfo,
          loanStartDate, loanEndDate);
      // 合计贷款金额取数据
      BigDecimal sumloanMoney = new BigDecimal(0.00);
      LoanaccordDTO loanaccordDTO = null;
      if (!loanaccordAllList.isEmpty()) {
        for (int i = 0; i < loanaccordAllList.size(); i++) {
          loanaccordDTO = (LoanaccordDTO) loanaccordAllList.get(i);
          // 显示用的月利率
          loanaccordDTO.setTemploanMonthRate(loanaccordDTO.getLoanMonthRate()
              .multiply(new BigDecimal(100.00)).toString()
              + "%");
          // 证件类型对应的名称
          loanaccordDTO
              .setCardKindName(BusiTools.getBusiValue(Integer
                  .parseInt(loanaccordDTO.getCardKind()),
                  BusiConst.DOCUMENTSSTATE));
          // 通过bankId查找银行的名称
          String bankId = loanaccordDTO.getLoanBankId();
          CollBank collBank = collBankDAO.getCollBankByCollBankid_(bankId);
          loanaccordDTO.setLoanBankName(collBank.getCollBankName());

          // 还款方式
          loanaccordDTO.setLoanModeName(BusiTools.getBusiValue(Integer
              .parseInt(loanaccordDTO.getLoanMode()), BusiConst.PLRECOVERTYPE));
          //
          loanaccordDTO.setBizStName(BusiTools.getBusiValue(Integer
              .parseInt(loanaccordDTO.getBizSt()), BusiConst.PLBUSINESSSTATE));
          // 获取借款期限
          String tempLoanTimeLimit = loanaccordDTO.getLoanTimeLimit();
          String tempLoanStartDate = loanaccordDTO.getLoanStartDate();
          int temp_addYear = (Integer.parseInt(tempLoanStartDate
              .substring(4, 6)) + Integer.parseInt(tempLoanTimeLimit)) / 12;
          int temp_moth = (Integer.parseInt(tempLoanStartDate.substring(4, 6)) + Integer
              .parseInt(tempLoanTimeLimit)) % 12;
          // 判断是不是最后一个月的天数，如果是看看你那一个月
          int month_day = BusiTools.daysOfMonth((Integer
              .parseInt(tempLoanStartDate.substring(0, 4)) + temp_addYear),
              temp_moth);
          // 还款的最后天数
          String overDay = "";
          if (month_day < Integer.parseInt(tempLoanStartDate.substring(6, 8))) {
            overDay = month_day + "";
          } else {
            overDay = tempLoanStartDate.substring(6, 8);
          }
          // 到期日期
          String overTime = "";
          if (temp_moth < 10) {
            overTime = (Integer.parseInt(tempLoanStartDate.substring(0, 4)) + temp_addYear)
                + "" + "0" + temp_moth + "" + overDay;
          } else {
            overTime = (Integer.parseInt(tempLoanStartDate.substring(0, 4)) + temp_addYear)
                + "" + temp_moth + "" + overDay;
          }
          loanaccordDTO.setOperson(securityInfo.getRealName());
          loanaccordDTO.setBizDate(securityInfo.getUserInfo().getPlbizDate());
          loanaccordDTO.setOverTime(overTime);
          BigDecimal totalInterest = new BigDecimal(0.00);
          totalInterest = loanaccordDTO.getCorpusInterest().multiply(
              new BigDecimal(loanaccordDTO.getLoanTimeLimit())).subtract(
              loanaccordDTO.getLoanMoney());
          loanaccordDTO.setInterestTotal(totalInterest);
          // 判断是统一定日，还是按户定日
          List loanBankParaInfo = loanBankParaDAO.queryLoanBankPara_sy(bankId,
              "2", "A");
          Object Info[] = (Object[]) loanBankParaInfo.get(0);
          String loanRepayDay = "";
          if ((Info[0] + "").equals("1")) {
            loanRepayDay = tempLoanStartDate.substring(6, 8);
            loanaccordDTO.setLoanRepayDay(loanRepayDay);
            loanaccordDTO.setLoanRepayDayInfo("1");
          } else {
            loanRepayDay = Info[1] + "";
            loanaccordDTO.setLoanRepayDay(loanRepayDay);
            loanaccordDTO.setLoanRepayDayInfo("2");
            securityInfo.getUserInfo().getPlbizDate();
          }
          sumloanMoney = sumloanMoney.add(loanaccordDTO.getLoanMoney());
        }
      }
      loanaccordShowAF.setSumloanMoney(sumloanMoney);
      pagination.setNrOfElements(loanaccordAllList.size());
      loanaccordShowAF.setList(returnList);
      loanaccordShowAF.setPrintList(loanaccordAllList);
    } catch (BusinessException bx) {
      throw bx;
    } catch (Exception e) {
      e.printStackTrace();
    }
    return loanaccordShowAF;
  }

  // 发放维护删除
  public void removeLoanaccordInfo(String flowHeadId, SecurityInfo securityInfo)
      throws BusinessException {
    try {
      // 查找要删除流水头尾表
      List temp_FlowHeadInfo = new ArrayList();
      temp_FlowHeadInfo = loanFlowTailDAO.queryLoanFlowHeadInfo(flowHeadId);
      if (!temp_FlowHeadInfo.isEmpty()) {
        Object[] info = (Object[]) temp_FlowHeadInfo.get(0);
        String contractId = info[0] + "";
        // 撤销的凭证号
        String cancelcredenceid = info[1] + "";
        // 凭证号生成日期
        String yearMonth = info[2] + "";
        // 办事处
        String officeCode = info[3] + "";
        // 更新pl111记录
        BorrowerAcc borrowerAcc = borrowerAccDAO.queryById(contractId);
        // 还款帐号
        // borrowerAcc.setLoanKouAcc("");
        // 贷款金额
        borrowerAcc.setLoanMoney(new BigDecimal(0.00));
        // 贷款期限
        borrowerAcc.setLoanTimeLimit("");
        // 贷款类型
        borrowerAcc.setLoanRateType("");
        // 还款方式
        borrowerAcc.setLoanMode("");
        // 发放日期
        borrowerAcc.setLoanStartDate("");
        // 还款日
        borrowerAcc.setLoanRepayDay(new BigDecimal(0.00));
        // 剩余金额
        borrowerAcc.setOverplusLoanMoney(new BigDecimal(0.00));
        // 剩余期限
        borrowerAcc.setOverplusLimite("0");
        // 更新新的状态
        borrowerAcc
            .setContractSt(BusiConst.PLCONTRACTSTATUS_ISSUEDNOTICES + "");
        // 更新pl115的首月还款金额
        BorrowerLoanInfo borrowerLoanInfo = new BorrowerLoanInfo();
        borrowerLoanInfo = borrowerLoanInfoDAO.queryById(contractId);
        BigDecimal firstLoanMoney = null;
        borrowerLoanInfo.setFirstLoanMoney(firstLoanMoney);
        // 撤销凭证号
        String officeId = "";
        String loanDocNumDocument = collParaDAO.getLoanDocNumDesignPara();
        if (loanDocNumDocument.equals("1")) {
          officeId = officeCode;
        } else {
          officeId = info[4] + "";
        }
        // plDocNumCancelDAO.insertPlDocNumCancel(cancelcredenceid, officeId,
        // yearMonth.substring(0, 6));
        plDocNumCancelDAO.insertPlDocNumCancel(cancelcredenceid
            .substring(8, 12), cancelcredenceid.substring(7, 8),
            cancelcredenceid.substring(0, 4));
        // 删除流水尾表
        loanFlowTailDAO.deleteLoanFlowTailAll(flowHeadId);
        // 删除流水头表
        loanFlowHeadDAO.deleteLoanFlowHead(flowHeadId);
        // 插入业务日志
        String opModel = BusiLogConst.PL_OP_LOANISSUED_MAINTAIN + "";
        String opButton = BusiLogConst.BIZLOG_ACTION_DELETE + "";
        this.addPlOperateLog_sy(contractId, opModel, opButton, flowHeadId,
            securityInfo);
        // 删除活动日志
        plBizActiveLogDAO.deletePlBizActiveLogByFlowHeadId_FYF(flowHeadId, "1");
      } else {
        throw new BusinessException("删除失败,记录不存在");
      }
    } catch (BusinessException bx) {
      throw bx;
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public LoanaccordDTO printLoanaccordInfo(String flowHeadId,
      SecurityInfo securityInfo) throws BusinessException {
    LoanaccordDTO loanaccordDTO = new LoanaccordDTO();
    try {
      List temp_FlowHeadInfo = new ArrayList();
      temp_FlowHeadInfo = loanFlowTailDAO.queryLoanFlowHeadInfo(flowHeadId);
      Object[] info = (Object[]) temp_FlowHeadInfo.get(0);
      // 合同编号
      String contractId = info[0] + "";
      List loanaccordAllList = borrowerLoanInfoDAO.countBorrowerLoanList_sy(
          null, null, null, null, null, null, securityInfo,flowHeadId);
      if (!loanaccordAllList.isEmpty()) {
        for (int i = 0; i < loanaccordAllList.size(); i++) {

          loanaccordDTO = (LoanaccordDTO) loanaccordAllList.get(0);
          // 显示用的月利率
          loanaccordDTO.setTemploanMonthRate(loanaccordDTO.getLoanMonthRate()
              .multiply(new BigDecimal(100.00)).toString()
              + "%");
          // 证件类型对应的名称
          loanaccordDTO
              .setCardKindName(BusiTools.getBusiValue(Integer
                  .parseInt(loanaccordDTO.getCardKind()),
                  BusiConst.DOCUMENTSSTATE));
          // 通过bankId查找银行的名称
          String bankId = loanaccordDTO.getLoanBankId();
          CollBank collBank = collBankDAO.getCollBankByCollBankid_(bankId);
          loanaccordDTO.setLoanBankName(collBank.getCollBankName());

          // 还款方式
          loanaccordDTO.setLoanModeName(BusiTools.getBusiValue(Integer
              .parseInt(loanaccordDTO.getLoanMode()), BusiConst.PLRECOVERTYPE));
          //
          loanaccordDTO.setBizStName(BusiTools.getBusiValue(Integer
              .parseInt(loanaccordDTO.getBizSt()), BusiConst.PLBUSINESSSTATE));
          // 获取借款期限
          String tempLoanTimeLimit = loanaccordDTO.getLoanTimeLimit();
          String tempLoanStartDate = loanaccordDTO.getLoanStartDate();
          int temp_addYear = (Integer.parseInt(tempLoanStartDate
              .substring(4, 6)) + Integer.parseInt(tempLoanTimeLimit)) / 12;
          int temp_moth = (Integer.parseInt(tempLoanStartDate.substring(4, 6)) + Integer
              .parseInt(tempLoanTimeLimit)) % 12;
          // 判断是不是最后一个月的天数，如果是看看你那一个月
          int month_day = BusiTools.daysOfMonth((Integer
              .parseInt(tempLoanStartDate.substring(0, 4)) + temp_addYear),
              temp_moth);
          // 还款的最后天数
          String overDay = "";
          if (month_day < Integer.parseInt(tempLoanStartDate.substring(6, 8))) {
            overDay = month_day + "";
          } else {
            overDay = tempLoanStartDate.substring(6, 8);
          }
          // 到期日期
          String overTime = "";
          if (temp_moth < 10) {
            overTime = (Integer.parseInt(tempLoanStartDate.substring(0, 4)) + temp_addYear)
                + "" + "0" + temp_moth + "" + overDay;
          } else {
            overTime = (Integer.parseInt(tempLoanStartDate.substring(0, 4)) + temp_addYear)
                + "" + temp_moth + "" + overDay;
          }
          loanaccordDTO.setFlowHeadId(new Integer(flowHeadId));
          loanaccordDTO.setOperson(securityInfo.getRealName());
          loanaccordDTO.setBizDate(securityInfo.getUserInfo().getPlbizDate());
          loanaccordDTO.setDocNum(info[1] + "");
          loanaccordDTO.setOverTime(overTime);
          BigDecimal totalInterest = new BigDecimal(0.00);
          totalInterest = loanaccordDTO.getCorpusInterest().multiply(
              new BigDecimal(loanaccordDTO.getLoanTimeLimit())).subtract(
              loanaccordDTO.getLoanMoney());
          loanaccordDTO.setInterestTotal(totalInterest);
          // 判断是统一定日，还是按户定日
          List loanBankParaInfo = loanBankParaDAO.queryLoanBankPara_sy(bankId,
              "2", "A");
          Object Info[] = (Object[]) loanBankParaInfo.get(0);
          String loanRepayDay = "";
          if ((Info[0] + "").equals("1")) {
            loanRepayDay = tempLoanStartDate.substring(6, 8);
            loanaccordDTO.setLoanRepayDay(loanRepayDay);
            loanaccordDTO.setLoanRepayDayInfo("1");
          } else {
            loanRepayDay = Info[1] + "";
            loanaccordDTO.setLoanRepayDay(loanRepayDay);
            loanaccordDTO.setLoanRepayDayInfo("2");
            securityInfo.getUserInfo().getPlbizDate();
          }
        }
      }
    } catch (Exception e) {
      e.printStackTrace();
      throw new BusinessException("打印失败");
    }
    return loanaccordDTO;
  }

  public CollParaDAO getCollParaDAO() {
    return collParaDAO;
  }

  public void setCollParaDAO(CollParaDAO collParaDAO) {
    this.collParaDAO = collParaDAO;
  }

}
