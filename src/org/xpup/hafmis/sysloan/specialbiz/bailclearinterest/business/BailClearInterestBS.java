package org.xpup.hafmis.sysloan.specialbiz.bailclearinterest.business;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.xpup.common.exception.BusinessException;
import org.xpup.common.util.Pagination;
import org.xpup.hafmis.common.util.BusiConst;
import org.xpup.hafmis.common.util.bizlog.BusiLogConst;
import org.xpup.hafmis.orgstrct.dao.CollBankDAO;
import org.xpup.hafmis.orgstrct.domain.CollBank;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.sysloan.common.dao.BorrowerAccDAO;
import org.xpup.hafmis.sysloan.common.dao.LoanFlowHeadDAO;
import org.xpup.hafmis.sysloan.common.dao.LoanFlowTailDAO;
import org.xpup.hafmis.sysloan.common.dao.PlBizActiveLogDAO;
import org.xpup.hafmis.sysloan.common.dao.PlDocNumMaintainDAO;
import org.xpup.hafmis.sysloan.common.dao.PlOperateLogDAO;
import org.xpup.hafmis.sysloan.common.domain.entity.BorrowerAcc;
import org.xpup.hafmis.sysloan.common.domain.entity.LoanFlowHead;
import org.xpup.hafmis.sysloan.common.domain.entity.LoanFlowTail;
import org.xpup.hafmis.sysloan.common.domain.entity.PlBizActiveLog;
import org.xpup.hafmis.sysloan.common.domain.entity.PlOperateLog;
import org.xpup.hafmis.sysloan.specialbiz.bailclearinterest.bsinterface.IBailClearInterestBS;
import org.xpup.hafmis.sysloan.specialbiz.bailclearinterest.dto.BailClearInterestTaDTO;
import org.xpup.hafmis.sysloan.specialbiz.bailclearinterest.dto.BailClearInterestTbDTO;
import org.xpup.hafmis.sysloan.specialbiz.bailclearinterest.form.BailClearInterestTaAF;
import org.xpup.hafmis.sysloan.specialbiz.bailclearinterest.form.BailClearInterestTbAF;
import org.xpup.security.common.domain.Userslogincollbank;

/**
 * @author 王野 2007-10-05
 */
public class BailClearInterestBS implements IBailClearInterestBS {

  private BorrowerAccDAO borrowerAccDAO = null;// 借款人账户表 PL111

  private LoanFlowHeadDAO loanFlowHeadDAO = null;// 流水头表 PL202

  private LoanFlowTailDAO loanFlowTailDAO = null;// 流水尾表 PL203

  private PlBizActiveLogDAO plBizActiveLogDAO = null;// 业务活动日志 PL020

  private PlOperateLogDAO plOperateLogDAO = null;// 操作日志 PL021

  private CollBankDAO collBankDAO = null;// 转换银行名称

  private PlDocNumMaintainDAO plDocNumMaintainDAO = null;

  public void setBorrowerAccDAO(BorrowerAccDAO borrowerAccDAO) {
    this.borrowerAccDAO = borrowerAccDAO;
  }

  public void setLoanFlowHeadDAO(LoanFlowHeadDAO loanFlowHeadDAO) {
    this.loanFlowHeadDAO = loanFlowHeadDAO;
  }

  public void setLoanFlowTailDAO(LoanFlowTailDAO loanFlowTailDAO) {
    this.loanFlowTailDAO = loanFlowTailDAO;
  }

  public void setPlBizActiveLogDAO(PlBizActiveLogDAO plBizActiveLogDAO) {
    this.plBizActiveLogDAO = plBizActiveLogDAO;
  }

  public void setPlOperateLogDAO(PlOperateLogDAO plOperateLogDAO) {
    this.plOperateLogDAO = plOperateLogDAO;
  }

  public void setCollBankDAO(CollBankDAO collBankDAO) {
    this.collBankDAO = collBankDAO;
  }

  public void setPlDocNumMaintainDAO(PlDocNumMaintainDAO plDocNumMaintainDAO) {
    this.plDocNumMaintainDAO = plDocNumMaintainDAO;
  }

  /**
   * 办理保证金结息查询显示列表
   * 
   * @author 王野 2007-10-05
   */
  public BailClearInterestTaAF findBailClearInterestTaListByCriterions(
      Pagination pagination, SecurityInfo securityInfo) throws Exception,
      BusinessException {
    String orderBy = (String) pagination.getOrderBy();
    String order = (String) pagination.getOrderother();
    int start = pagination.getFirstElementOnPage() - 1;
    int pageSize = pagination.getPageSize();
    int page = pagination.getPage();
    int count = 0;
    String loanBankName = (String) pagination.getQueryCriterions().get(
        "loanBankName");
    BigDecimal bailBalanceTotle = new BigDecimal(0.00);// 结息前保证金-总额
    BailClearInterestTaAF bailClearInterestTaAF = new BailClearInterestTaAF();
    // 会计年
    String bizYear = securityInfo.getUserInfo().getPlbizDate().substring(0, 4);
    // 修改记录：只有在6月份才能进行保证金结息 2007-11-10
    // 修改记录：只有在6月30日才能进行保证金结息 2007-11-20
    String bizMonthDay = securityInfo.getUserInfo().getPlbizDate().substring(4, 8);
    if (!bizMonthDay.equals("0630")) {
      throw new BusinessException("只可以在6月30日进行保证金结息！");
    }
    String bizDate = bizYear + "0630";
    // 判断该银行下会计日期为"会计年的6月30日"的是否存在结息 （PL202.Type=15）
    boolean isClearinterestTa = loanFlowHeadDAO.isExistsClearinterestTa(
        bizDate, loanBankName);
    if (isClearinterestTa) {// 若没有结息，取出银行下所有的有保证金的借款人（PL111.CUR_INTEGRAL！=null）
      List bailclearinterestTaList = new ArrayList();
      List templist = new ArrayList();
      bailclearinterestTaList = loanFlowHeadDAO
          .queryBailClearInterestTaListByCriterions(loanBankName, start,
              orderBy, order, pageSize, page);
      if (bailclearinterestTaList.size() > 0) {
        List countList = new ArrayList();
        countList = loanFlowHeadDAO
            .queryBailClearInterestTaCountByCriterions(loanBankName);
        count = countList.size();
        bailBalanceTotle = loanFlowHeadDAO
            .queryBailClearInterestTaSumMoneyByCriterions(loanBankName);
        Iterator iterate = bailclearinterestTaList.iterator();
        Object[] obj = null;
        while (iterate.hasNext()) {
          BailClearInterestTaDTO bailClearInterestTaDTO = new BailClearInterestTaDTO();
          obj = (Object[]) iterate.next();
          String loanBankId = null;
          if (obj[0] != null && !obj[0].equals(""))
            loanBankId = obj[0].toString();
          if (obj[1] != null && !obj[1].equals(""))
            bailClearInterestTaDTO.setParamExplainInterestD(obj[1].toString());
          if (obj[2] != null && !obj[2].equals(""))
            bailClearInterestTaDTO.setParamExplainInterestL(obj[2].toString());
          if (obj[3] != null && !obj[3].equals(""))
            bailClearInterestTaDTO.setLoanKouAcc(obj[3].toString());
          if (obj[4] != null && !obj[4].equals(""))
            bailClearInterestTaDTO.setContractId(obj[4].toString());
          if (obj[5] != null && !obj[5].equals(""))
            bailClearInterestTaDTO.setBorrowerName(obj[5].toString());
          if (obj[6] != null && !obj[6].equals(""))
            bailClearInterestTaDTO.setCardNum(obj[6].toString());
          if (obj[7] != null && !obj[7].equals(""))
            bailClearInterestTaDTO.setBailBalance(obj[7].toString());
          // 转换银行名称
          try {
            CollBank dto = collBankDAO.getCollBankByCollBankid(loanBankId
                .toString());
            bailClearInterestTaDTO.setLoanBankName(dto.getCollBankName());
          } catch (Exception e) {
            e.printStackTrace();
          }
          templist.add(bailClearInterestTaDTO);
        }
      }
      pagination.setNrOfElements(count);
      if (bailBalanceTotle != null && !bailBalanceTotle.equals("")) {
        bailClearInterestTaAF.setBailBalanceTotle(bailBalanceTotle);
      }
      bailClearInterestTaAF.setList(templist);
    }
    // else 该会计年biz_Type=15已结息！
    return bailClearInterestTaAF;
  }

  /**
   * 办理保证金结息
   * 
   * @author 王野 2007-10-06 全部结息
   */
  public void bailClearInterestTa(Pagination pagination,
      SecurityInfo securityInfo) throws Exception, BusinessException {
    String operateName = securityInfo.getUserInfo().getUsername();// 操作员
    String userIp = securityInfo.getUserInfo().getUserIp();// 操作员IP

    String loanBankName = (String) pagination.getQueryCriterions().get(
        "loanBankName");
    BigDecimal loanBankId = new BigDecimal(loanBankName);// PL111.LOAN_BANK_ID

    // 会计年
    String bizYear = securityInfo.getUserInfo().getPlbizDate().substring(0, 4);// 个贷会计日期的
    String bizDate = bizYear + "0630";
    // 判断列表中是否存在未记账的记录（该操作员权限下是否存在PL202.TYPE=14 and PL202.BIZ_ST!=6的记录）
    boolean isNoBizSt6 = loanFlowHeadDAO
        .isExistsClearinterestTaNoBizSt6(loanBankName);
    if (isNoBizSt6) {
      // 判断该结息年度下是否存在结息的记录（该操作员权限下是否存在PL202.TYPE=15 and
      // PL202.BIZ_ST=6的记录，BIZ_DATE的年在会计年的6月30日）
      boolean isBizSt6 = loanFlowHeadDAO.isExistsClearinterestTaBisSt6(bizDate,
          loanBankName);
      if (isBizSt6) {
        BigDecimal sumOccurMoney = new BigDecimal(0.00);// PL202发生金额
        BigDecimal occurMoney = new BigDecimal(0.00);// PL203发生金额
        String paramExplainInterestD = null;// 定期利率(往年利率)
        String paramExplainInterestL = null;// 活期利率(本年利率)
        int count = 0;// 实还人数
        // 取凭证号
        CollBank collBank = collBankDAO.getCollBankByCollBankid_(loanBankName);
        String bizYearmonth = securityInfo.getUserInfo().getPlbizDate()
            .substring(0, 6);
        String docNum = plDocNumMaintainDAO.getDocNumdocNum(collBank
            .getOffice(), bizYearmonth);
        List sumMoneyList = new ArrayList();
        List moneylist = new ArrayList();
        sumMoneyList = loanFlowHeadDAO
            .queryBailClearInterestTaCountByCriterions(loanBankName);
        count = sumMoneyList.size();
        Iterator iterate = sumMoneyList.iterator();
        Object[] obj = null;
        while (iterate.hasNext()) {
          BailClearInterestTaDTO bailClearInterestTaDTO = new BailClearInterestTaDTO();
          obj = (Object[]) iterate.next();
          if (obj[0] != null && !obj[0].equals(""))
            bailClearInterestTaDTO.setLoanBankName(obj[0].toString());
          if (obj[1] != null && !obj[1].equals("")) {
            bailClearInterestTaDTO.setParamExplainInterestD(obj[1].toString());
            paramExplainInterestD = obj[1].toString();
          }
          if (obj[2] != null && !obj[2].equals("")) {
            bailClearInterestTaDTO.setParamExplainInterestL(obj[2].toString());
            paramExplainInterestL = obj[2].toString();
          }
          if (obj[3] != null && !obj[3].equals(""))
            bailClearInterestTaDTO.setLoanKouAcc(obj[3].toString());
          if (obj[4] != null && !obj[4].equals(""))
            bailClearInterestTaDTO.setContractId(obj[4].toString());
          if (obj[5] != null && !obj[5].equals(""))
            bailClearInterestTaDTO.setBorrowerName(obj[5].toString());
          if (obj[6] != null && !obj[6].equals(""))
            bailClearInterestTaDTO.setCardNum(obj[6].toString());
          if (obj[7] != null && !obj[7].equals(""))
            bailClearInterestTaDTO.setBailBalance(obj[7].toString());
          BigDecimal preIntegral = new BigDecimal(0.00);// 往年积数
          if (obj[8] != null && !obj[8].equals("")) {
            bailClearInterestTaDTO.setPreIntegral(obj[8].toString());
            preIntegral = new BigDecimal(obj[8].toString());// 往年积数
          }
          BigDecimal curIntegral = new BigDecimal(0.00);// 本年积数
          if (obj[9] != null && !obj[9].equals("")) {
            bailClearInterestTaDTO.setCurIntegral(obj[9].toString());
            curIntegral = new BigDecimal(obj[9].toString());// 本年积数
          }
          sumOccurMoney = sumOccurMoney.add((preIntegral
              .multiply(new BigDecimal(paramExplainInterestD)).add(curIntegral
              .multiply(new BigDecimal(paramExplainInterestL)))).divide(
              new BigDecimal(365), 2, BigDecimal.ROUND_HALF_DOWN));
          moneylist.add(bailClearInterestTaDTO);
        }// while
        // 插入贷款流水账头表PL202
        LoanFlowHead loanFlowHead = new LoanFlowHead();
        loanFlowHead.setBizDate(bizDate);
        loanFlowHead.setBizType(new Integer(
            BusiConst.PLBUSINESSTYPE_CLEARINTEREST).toString());// 业务类型
        // 15（保证金结息）
        loanFlowHead.setRealCount(new BigDecimal(count));// 实还人数
        loanFlowHead.setOccurMoney(sumOccurMoney);// 发生金额
        loanFlowHead.setDocNum(docNum);
        loanFlowHead.setBizSt(new Integer(BusiConst.BUSINESSSTATE_CLEARACCOUNT)
            .toString());// 业务状态
        // 6(入账)
        loanFlowHead.setLoanBankId(loanBankId);
        loanFlowHead.setMakePerson(operateName);
        loanFlowHead.setIsFinance(new Integer(1));// PL202中的isFinance插1
        String flowHeadId = loanFlowHeadDAO.insert(loanFlowHead).toString();// 插入PL202
        // 并返回flow_head_id
        // 向流水头表插入票据号
        loanFlowHead.setNoteNum(flowHeadId);
        if (moneylist != null) {
          for (Iterator iter = moneylist.iterator(); iter.hasNext();) {
            BailClearInterestTaDTO element = (BailClearInterestTaDTO) iter
                .next();
            String contractId = element.getContractId();// 页面上的每个合同编号
            String loanKouAcc = element.getLoanKouAcc();// 页面上的每个贷款账号
            BigDecimal bailBalance = new BigDecimal(element.getBailBalance());// 页面上的每个结息前保证金
            BigDecimal preIntegral = new BigDecimal(element.getPreIntegral());// 该合同编号下的往年积数
            BigDecimal curIntegral = new BigDecimal(element.getCurIntegral());// 该合同编号下的本年积数
            occurMoney = preIntegral.multiply(
                new BigDecimal(paramExplainInterestD)).add(
                curIntegral.multiply(new BigDecimal(paramExplainInterestL)))
                .divide(new BigDecimal(365), 2, BigDecimal.ROUND_HALF_DOWN);
            // 依次插入贷款流水账尾表PL203
            LoanFlowTail loanFlowTail = new LoanFlowTail();
            loanFlowTail.setFlowHeadId(new BigDecimal(flowHeadId));
            loanFlowTail.setLoanKouAcc(loanKouAcc);
            loanFlowTail.setContractId(contractId);
            loanFlowTail.setOccurMoney(occurMoney);// 该合同编号下的（PL111.CUR_INTEGRAL*页面显示的本年利率+PL111.PRE_INTEGRAL*页面显示的往年利率）/365
            loanFlowTailDAO.insert(loanFlowTail);
            // 依次更新PL111
            preIntegral = bailBalance.add(occurMoney).multiply(
                new BigDecimal(365));// PRE_INTEGRAL=（PL111.BAIL_BALANCE+PL203.OCCUE_MONEY）*365
            curIntegral = new BigDecimal(0);
            bailBalance = bailBalance.add(occurMoney);// BAIL_BALANCE=PL111.BAIL_BALANCE+PL203.OCCUR_MONEY
            BorrowerAcc borrowerAcc = borrowerAccDAO.queryById(contractId);
            borrowerAcc.setPreIntegral(preIntegral);
            borrowerAcc.setCurIntegral(curIntegral);
            borrowerAcc.setBailBalance(bailBalance);
          }
        }
        // 插入业务活动日志PL020
        PlBizActiveLog plBizActiveLog = new PlBizActiveLog();
        plBizActiveLog.setBizid(new BigDecimal(flowHeadId));
        plBizActiveLog.setAction(new Integer(
            BusiConst.BUSINESSSTATE_CLEARACCOUNT).toString());// action 6
        plBizActiveLog.setOpTime(new Date());
        plBizActiveLog.setOperator(operateName);
        plBizActiveLog.setType(new Integer(
            BusiConst.PLBUSINESSTYPE_CLEARINTEREST).toString());// type 15
        plBizActiveLogDAO.insert(plBizActiveLog);
        // 插入操作日志 pl021
        PlOperateLog plOperateLog = new PlOperateLog();
        plOperateLog.setOpSys(new BigDecimal(BusiLogConst.OP_SYSTEM_TYPE_LOAN));// 个贷 2
        plOperateLog.setOpModel(new Integer(
            BusiLogConst.PL_OP_SPECIALBUSS_BAILCLEARINTEREST_DO).toString());// 保证金结息75
        plOperateLog.setOpButton(new Integer(
            BusiLogConst.BIZLOG_ACTION_INTEREST).toString());// 结息 12
        plOperateLog.setOpBizId(new BigDecimal(flowHeadId));// PL202.FLOW_HEAD_ID
        plOperateLog.setOpIp(userIp);
        // 这里的合同编号没插
        plOperateLog.setOpTime(new Date());
        plOperateLog.setOperator(operateName);
        plOperateLogDAO.insert(plOperateLog);
      } else {
        throw new BusinessException("不可以重复提交");
      }
    } else {
      throw new BusinessException("存在未记账的保证金业务，不可以结息");
    }
  }

  /**
   * 保证金结息维护
   * 
   * @author 王野 2007-10-08 查询列表信息
   */
  public BailClearInterestTbAF queryBailClearInterestTbListByCriterions(
      Pagination pagination, SecurityInfo securityInfo) throws Exception,
      BusinessException {
    String orderBy = (String) pagination.getOrderBy();
    String order = (String) pagination.getOrderother();
    int start = pagination.getFirstElementOnPage() - 1;
    int pageSize = pagination.getPageSize();
    int page = pagination.getPage();
    int count = 0;
    // 获取权限中的银行
    List loanBankIDList = new ArrayList();
    List bankList = securityInfo.getDkUserBankList();
    Userslogincollbank userslogincollbank = null;
    Iterator bank = bankList.iterator();
    while (bank.hasNext()) {
      userslogincollbank = (Userslogincollbank) bank.next();
      loanBankIDList.add(userslogincollbank.getCollBankId().toString());
    }
    String loanKouAcc = (String) pagination.getQueryCriterions().get(
        "loanKouAcc");
    String borrowerName = (String) pagination.getQueryCriterions().get(
        "borrowerName");
    String bizYear = (String) pagination.getQueryCriterions().get("bizYear");// 结息年度
    String loanBankName = (String) pagination.getQueryCriterions().get(
        "loanBankName");
    String findType = (String) pagination.getQueryCriterions().get("findType");
    BigDecimal firstBalanceTotle = new BigDecimal(0.00);// 结息前保证金-总额
    BigDecimal occurMoneyTotle = new BigDecimal(0.00);// 结息利息-总额
    BigDecimal lastBalanceTotle = new BigDecimal(0.00);// 结息后保证金-总额
    BailClearInterestTbAF bailClearInterestTbAF = new BailClearInterestTbAF();
    // 会计年
    String bizYearDefault = securityInfo.getUserInfo().getPlbizDate()
        .substring(0, 4);
    // 列表中默认显示当年的结息记录
    String bizDate = null;
    if (findType != null && !findType.equals("")) {
      if (bizYear != null && !bizYear.equals("")) {
        bizDate = bizYear + "0630";
      }
    } else {
      if (bizYear != null && !bizYear.equals("")) {
        bizDate = bizYear + "0630";
      } else {
        bizDate = bizYearDefault + "0630";
      }
    }
    List bailclearinterestTbList = new ArrayList();
    List templist = new ArrayList();
    List printList = new ArrayList();
    bailclearinterestTbList = loanFlowHeadDAO
        .queryBailClearInterestTbListByCriterions(loanKouAcc, borrowerName,
            bizDate, loanBankName, loanBankIDList, start, orderBy, order,
            pageSize, page);
    if (bailclearinterestTbList.size() > 0) {
      List countList = new ArrayList();
      countList = loanFlowHeadDAO.queryBailClearInterestTbCountByCriterions(
          loanKouAcc, borrowerName, bizDate, loanBankName, loanBankIDList);
      count = countList.size();
      Iterator iterate = bailclearinterestTbList.iterator();
      Object[] obj = null;
      while (iterate.hasNext()) {
        BailClearInterestTbDTO bailClearInterestTbDTO = new BailClearInterestTbDTO();
        obj = (Object[]) iterate.next();
        if (obj[0] != null && !obj[0].equals("")) {
          String bizDateTable = obj[0].toString();
          bailClearInterestTbDTO.setBizYear(bizDateTable.substring(0, 4));
        }
        String loanBankId = null;
        if (obj[1] != null && !obj[1].equals(""))
          loanBankId = obj[1].toString();
        if (obj[2] != null && !obj[2].equals(""))
          bailClearInterestTbDTO.setLoanKouAcc(obj[2].toString());
        if (obj[3] != null && !obj[3].equals(""))
          bailClearInterestTbDTO.setBorrowerName(obj[3].toString());
        String bailBalance = null;
        if (obj[4] != null && !obj[4].equals("")) {
          bailBalance = obj[4].toString();
        }
        String occurMoney = null;
        if (obj[5] != null && !obj[5].equals("")) {
          occurMoney = obj[5].toString();
          bailClearInterestTbDTO.setOccurMoney(occurMoney);
        }
        BigDecimal afterYearOccurMoney = new BigDecimal(0.00);// 结息年度之后的结息利息（包括结息年度）
        if (obj[6] != null && !obj[6].equals(""))
          afterYearOccurMoney = afterYearOccurMoney.add(new BigDecimal(obj[6]
              .toString()));
        BigDecimal firstBalance = new BigDecimal(0.00);// 结息前保证金
        firstBalance = new BigDecimal(bailBalance).add(afterYearOccurMoney
            .negate());
        bailClearInterestTbDTO.setBailBalance(firstBalance.toString());
        BigDecimal lastBalance = new BigDecimal(0.00);// 结息后保证金
        lastBalance = firstBalance.add(new BigDecimal(occurMoney));// 结息前保证金+结息利息
        bailClearInterestTbDTO.setLastBalance(lastBalance.toString());
        // 转换银行名称
        try {
          CollBank dto = collBankDAO.getCollBankByCollBankid(loanBankId
              .toString());
          bailClearInterestTbDTO.setLoanBankName(dto.getCollBankName());
        } catch (Exception e) {
          e.printStackTrace();
        }
        templist.add(bailClearInterestTbDTO);
      }
      // 打印全部列表
      Iterator iter = countList.iterator();
      Object[] object = null;
      while (iter.hasNext()) {
        BailClearInterestTbDTO bailClearInterestTbDTO = new BailClearInterestTbDTO();
        object = (Object[]) iter.next();
        if (object[0] != null && !object[0].equals("")) {
          String bizDateTable = object[0].toString();
          bailClearInterestTbDTO.setBizYear(bizDateTable.substring(0, 4));
        }
        String loanBankId = null;
        if (object[1] != null && !object[1].equals(""))
          loanBankId = object[1].toString();
        if (object[2] != null && !object[2].equals(""))
          bailClearInterestTbDTO.setLoanKouAcc(object[2].toString());
        if (object[3] != null && !object[3].equals(""))
          bailClearInterestTbDTO.setBorrowerName(object[3].toString());
        String bailBalance = null;
        if (object[4] != null && !object[4].equals("")) {
          bailBalance = object[4].toString();
        }
        String occurMoney = null;
        if (object[5] != null && !object[5].equals("")) {
          occurMoney = object[5].toString();
          bailClearInterestTbDTO.setOccurMoney(occurMoney);
        }
        BigDecimal afterYearOccurMoney = new BigDecimal(0.00);// 结息年度之后的结息利息（包括结息年度）
        if (object[6] != null && !object[6].equals(""))
          afterYearOccurMoney = afterYearOccurMoney.add(new BigDecimal(
              object[6].toString()));
        BigDecimal firstBalance = new BigDecimal(0.00);// 结息前保证金
        firstBalance = new BigDecimal(bailBalance).add(afterYearOccurMoney
            .negate());
        bailClearInterestTbDTO.setBailBalance(firstBalance.toString());
        BigDecimal lastBalance = new BigDecimal(0.00);// 结息后保证金
        lastBalance = firstBalance.add(new BigDecimal(occurMoney));// 结息前保证金+结息利息
        bailClearInterestTbDTO.setLastBalance(lastBalance.toString());
        // 合计
        firstBalanceTotle = firstBalanceTotle.add(firstBalance);// 结息前保证金-总额
        occurMoneyTotle = occurMoneyTotle.add(new BigDecimal(occurMoney));// 结息利息-总额
        lastBalanceTotle = lastBalanceTotle.add(lastBalance);// 结息后保证金-总额
        // 转换银行名称
        try {
          CollBank dto = collBankDAO.getCollBankByCollBankid(loanBankId
              .toString());
          bailClearInterestTbDTO.setLoanBankName(dto.getCollBankName());
        } catch (Exception e) {
          e.printStackTrace();
        }
        printList.add(bailClearInterestTbDTO);
      }
    }
    pagination.setNrOfElements(count);
    if (firstBalanceTotle != null && !firstBalanceTotle.equals("")) {
      bailClearInterestTbAF.setBailBalanceTotle(firstBalanceTotle);
    }
    if (occurMoneyTotle != null && !occurMoneyTotle.equals("")) {
      bailClearInterestTbAF.setOccurMoneyTotle(occurMoneyTotle);
    }
    if (lastBalanceTotle != null && !lastBalanceTotle.equals("")) {
      bailClearInterestTbAF.setLastBalanceTotle(lastBalanceTotle);
    }
    bailClearInterestTbAF.setList(templist);
    bailClearInterestTbAF.setPrintList(printList);
    return bailClearInterestTbAF;
  }
}
