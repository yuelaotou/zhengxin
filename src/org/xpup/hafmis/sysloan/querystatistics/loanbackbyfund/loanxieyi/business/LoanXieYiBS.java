package org.xpup.hafmis.sysloan.querystatistics.loanbackbyfund.loanxieyi.business;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.xpup.common.exception.BusinessException;
import org.xpup.common.util.Pagination;
import org.xpup.hafmis.common.util.BusiConst;
import org.xpup.hafmis.common.util.BusiTools;
import org.xpup.hafmis.orgstrct.dao.CollBankDAO;
import org.xpup.hafmis.orgstrct.domain.CollBank;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.sysloan.common.dao.LoanFlowHeadDAO;
import org.xpup.hafmis.sysloan.querystatistics.loanbackbyfund.loanxieyi.bsinterface.ILoanXieYiBS;
import org.xpup.hafmis.sysloan.querystatistics.loanbackbyfund.loanxieyi.dto.LoanXieYiDTO;
import org.xpup.hafmis.sysloan.querystatistics.loanbackbyfund.loanxieyi.form.LoanXieYiAF;
import org.xpup.hafmis.sysloan.querystatistics.loanbusiquery.loanbusiflowquery.dto.LoanBusiFlowQueryClearDTO;
import org.xpup.hafmis.sysloan.specialbiz.bailclearinterest.dto.BailClearInterestTbDTO;
import org.xpup.hafmis.sysloan.specialbiz.bailclearinterest.form.BailClearInterestTbAF;

/**
 * @author 王野 2007-10-15
 */
public class LoanXieYiBS implements ILoanXieYiBS {

  private LoanFlowHeadDAO loanFlowHeadDAO = null;// 流水头表 PL202

  private CollBankDAO collBankDAO = null;// 转换银行名称

  public void setLoanFlowHeadDAO(LoanFlowHeadDAO loanFlowHeadDAO) {
    this.loanFlowHeadDAO = loanFlowHeadDAO;
  }

  public void setCollBankDAO(CollBankDAO collBankDAO) {
    this.collBankDAO = collBankDAO;
  }

  /**
   * Description 贷款业务流水统计查询列表
   * 
   * @author wangy 2007-10-16
   * @param 根据条件查询列表
   * @param pagination
   * @param securityInfo
   * @param 由LoanBusiFlowQueryShowAC调用
   * @return LoanBusiFlowQueryAF
   * @throws Exception, BusinessException
   */
  public LoanXieYiAF queryLoanBusiFlowQueryListByCriterions(
      Pagination pagination, SecurityInfo securityInfo) throws Exception,
      BusinessException {
    LoanXieYiAF loanXieYiAF = new LoanXieYiAF();
    String orderBy = (String) pagination.getOrderBy();
    String order = (String) pagination.getOrderother();
    int start = pagination.getFirstElementOnPage() - 1;
    int pageSize = pagination.getPageSize();
    int page = pagination.getPage();
    int count = 0;
    String office = (String) pagination.getQueryCriterions().get("office");
    String loanBank = (String) pagination.getQueryCriterions().get("loanBank");
    String contractId = (String) pagination.getQueryCriterions().get(
        "contractId");
    String borrowerName = (String) pagination.getQueryCriterions().get(
        "borrowerName");
    String beginBizDate = (String) pagination.getQueryCriterions().get(
        "beginBizDate");
    String endBizDate = (String) pagination.getQueryCriterions().get(
        "endBizDate");
    String beginDelDate = (String) pagination.getQueryCriterions().get(
        "beginDelDate");
    String endDelDate = (String) pagination.getQueryCriterions().get(
        "endDelDate");
    String isDelete = (String) pagination.getQueryCriterions().get("isDelete");
    List loanBusiFlowQueryList = new ArrayList();
    List printList = new ArrayList();// 打印列表
    List templist = new ArrayList();
    List temptotlelist = new ArrayList();
    // 修改记录：付云峰财务系统需要链接贷款业务流水，需要通过票据号（结算号）进行查询，在参数列表的最后加上了一个notNum 2007-11-10
    templist = loanFlowHeadDAO.queryLoanXieYiListByCriterions_wsh(start,
        orderBy, order, pageSize, page, securityInfo, office, loanBank,
        contractId, borrowerName, beginBizDate, endBizDate, beginDelDate,
        endDelDate, isDelete);
    temptotlelist = loanFlowHeadDAO.queryLoanXieYiAllListByCriterions_wsh(
        securityInfo, office, loanBank, contractId, borrowerName, beginBizDate,
        endBizDate, beginDelDate, endDelDate, isDelete);
    String personCount = loanFlowHeadDAO
        .queryLoanXieYiAllListByCriterions_count_wsh(securityInfo, office,
            loanBank, contractId, borrowerName, beginBizDate, endBizDate,
            beginDelDate, endDelDate, isDelete);
    count = temptotlelist.size();
    // 贷款业务流水查询列表
    Iterator iterate = templist.iterator();
    Object[] obj = null;
    while (iterate.hasNext()) {
      LoanXieYiDTO loanXieYiDTO = new LoanXieYiDTO();
      obj = (Object[]) iterate.next();
      // 凭证编号PL202.DOC_NUM
      if (obj[0] != null && !obj[0].equals(""))
        loanXieYiDTO.setContractId(obj[0].toString());
      // 贷款账号PL203.LOAN_KOU_ACC
      if (obj[1] != null && !obj[1].equals(""))
        loanXieYiDTO.setXieYiNum(obj[1].toString());
      // 合同编号PL203.CONTRACT_ID
      if (obj[2] != null && !obj[2].equals(""))
        loanXieYiDTO.setBorrowerName(obj[2].toString());
      if (obj[3] != null && !obj[3].equals(""))
        loanXieYiDTO.setBorrowerCardNum(obj[3].toString());
      // 业务类型L202.BIZ_TYPE
      if (obj[4] != null && !obj[4].equals("")) {
        loanXieYiDTO.setBorrowerEmpId(BusiTools.convertSixNumber(obj[4]
            .toString()));
      }
      // 发放金额L202.OCCUR_MONEY(当业务类型=1、11或业务类型=12，WRONG_BIZ_TYPE=1时)
      if (obj[5] != null && !obj[5].equals("")) {

        loanXieYiDTO.setFuzhuName(obj[5].toString());

      }
      if (obj[6] != null && !obj[6].equals("")) {

        loanXieYiDTO.setFuzhuCardNum(obj[6].toString());

      }
      if (obj[7] != null && !obj[7].equals("")) {

        loanXieYiDTO.setFuzhuEmpId(BusiTools
            .convertSixNumber(obj[7].toString()));

      }
      if (obj[8] != null && !obj[8].equals("")) {

        loanXieYiDTO.setBizDate(obj[8].toString());

      }
      if (obj[9] != null && !obj[9].equals("")) {

        loanXieYiDTO.setStopDate(obj[9].toString());

      }

      loanBusiFlowQueryList.add(loanXieYiDTO);
    }
    loanXieYiAF.setList(loanBusiFlowQueryList);
    // 合计信息

    Iterator iteratetotle = temptotlelist.iterator();
    Object[] obj1 = null;
    while (iteratetotle.hasNext()) {
      obj1 = (Object[]) iteratetotle.next();
      LoanXieYiDTO loanXieYiDTO = new LoanXieYiDTO();

      // 凭证编号PL202.DOC_NUM
      if (obj1[0] != null && !obj1[0].equals(""))
        loanXieYiDTO.setContractId(obj1[0].toString());
      // 贷款账号PL203.LOAN_KOU_ACC
      if (obj1[1] != null && !obj1[1].equals(""))
        loanXieYiDTO.setXieYiNum(obj1[1].toString());
      // 合同编号PL203.CONTRACT_ID
      if (obj1[2] != null && !obj1[2].equals(""))
        loanXieYiDTO.setBorrowerName(obj1[2].toString());
      if (obj1[3] != null && !obj1[3].equals(""))
        loanXieYiDTO.setBorrowerCardNum(obj1[3].toString());
      // 业务类型L202.BIZ_TYPE
      if (obj1[4] != null && !obj1[4].equals("")) {
        loanXieYiDTO.setBorrowerEmpId(BusiTools.convertSixNumber(String
            .valueOf(obj1[4])));
      }
      // 发放金额L202.OCCUR_MONEY(当业务类型=1、11或业务类型=12，WRONG_BIZ_TYPE=1时)
      if (obj1[5] != null && !obj1[5].equals("")) {

        loanXieYiDTO.setFuzhuName(obj1[5].toString());

      }
      if (obj1[6] != null && !obj1[6].equals("")) {

        loanXieYiDTO.setFuzhuCardNum(obj1[6].toString());

      }
      if (obj1[7] != null && !obj1[7].equals("")) {

        loanXieYiDTO.setFuzhuEmpId(BusiTools.convertSixNumber(String
            .valueOf(obj1[7])));

      }
      if (obj1[8] != null && !obj1[8].equals("")) {

        loanXieYiDTO.setBizDate(obj1[8].toString());

      }
      if (obj1[9] != null && !obj1[9].equals("")) {

        loanXieYiDTO.setStopDate(obj1[9].toString());

      }
      printList.add(loanXieYiDTO);
    }
    loanXieYiAF.setPrintList(printList);
    loanXieYiAF.setCount(personCount);
    pagination.setNrOfElements(count);
    return loanXieYiAF;
  }

  /**
   * Description 贷款业务流水统计查询列表
   * 
   * @author wangy 2007-10-18
   * @param 根据PL202.FLOW_HEAD_ID查询结转余额信息
   * @param flowHeadId
   * @param securityInfo
   * @param 由LoanBusiFlowQueryClearShowWindowAC调用
   * @return LoanBusiFlowQueryClearDTO
   * @throws Exception, BusinessException
   */
  public LoanBusiFlowQueryClearDTO queryLoanBusiFlowQueryClearByFlowHeadId(
      String flowHeadId, SecurityInfo securityInfo) throws Exception,
      BusinessException {
    LoanBusiFlowQueryClearDTO loanBusiFlowQueryClearDTO = null;
    try {
      List list = loanFlowHeadDAO.queryLoanBusiFlowQueryClearByFlowHeadId(
          flowHeadId, securityInfo);
      if (!list.isEmpty()) {
        loanBusiFlowQueryClearDTO = (LoanBusiFlowQueryClearDTO) list.get(0);
        loanBusiFlowQueryClearDTO.setLoanMonthRate(new BigDecimal(
            loanBusiFlowQueryClearDTO.getLoanMonthRate())
            .multiply(new BigDecimal(100))
            + "%");// 月利率
        // 证件类型对应的名称
        loanBusiFlowQueryClearDTO.setCardKindName(BusiTools.getBusiValue(
            Integer.parseInt(loanBusiFlowQueryClearDTO.getCardKind()),
            BusiConst.DOCUMENTSSTATE));
        // 通过bankId查找银行的名称
        String bankId = loanBusiFlowQueryClearDTO.getLoanBankId();
        CollBank collBank = collBankDAO.getCollBankByCollBankid_(bankId);
        loanBusiFlowQueryClearDTO.setLoanBankName(collBank.getCollBankName());
        // 剩余本金=贷款金额-实还本金
        BigDecimal surplusCorpus = new BigDecimal(0.00); // 剩余本金
        surplusCorpus = loanBusiFlowQueryClearDTO.getLoanMoney().add(
            loanBusiFlowQueryClearDTO.getRealCorpus().negate());
        loanBusiFlowQueryClearDTO.setSurplusCorpus(surplusCorpus);
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    return loanBusiFlowQueryClearDTO;
  }

  /**
   * Description 贷款业务流水统计查询列表
   * 
   * @author wangy 2007-10-19
   * @param 根据PL202.FLOW_HEAD_ID查询结息信息
   * @param flowHeadId
   * @param securityInfo
   * @param 由LoanBusiFlowQueryClearShowWindowAC调用
   * @return LoanBusiFlowQueryClearDTO
   * @throws Exception, BusinessException
   */
  public BailClearInterestTbAF queryLoanBusiFlowQueryBailClearListByFlowHeadId(
      Pagination pagination, SecurityInfo securityInfo) throws Exception,
      BusinessException {
    String orderBy = (String) pagination.getOrderBy();
    String order = (String) pagination.getOrderother();
    int start = pagination.getFirstElementOnPage() - 1;
    int pageSize = pagination.getPageSize();
    int page = pagination.getPage();
    int count = 0;
    String flowHeadId = (String) pagination.getQueryCriterions().get(
        "flowHeadId");
    BigDecimal firstBalanceTotle = new BigDecimal(0.00);// 结息前保证金-总额
    BigDecimal occurMoneyTotle = new BigDecimal(0.00);// 结息利息-总额
    BigDecimal lastBalanceTotle = new BigDecimal(0.00);// 结息后保证金-总额
    BailClearInterestTbAF bailClearInterestTbAF = new BailClearInterestTbAF();
    List bailclearinterestTbList = new ArrayList();
    List templist = new ArrayList();
    List printList = new ArrayList();
    bailclearinterestTbList = loanFlowHeadDAO
        .queryBailClearInterestTbListByCriterions(flowHeadId, start, orderBy,
            order, pageSize, page);
    if (bailclearinterestTbList.size() > 0) {
      List countList = new ArrayList();
      countList = loanFlowHeadDAO
          .queryBailClearInterestTbCountByCriterions(flowHeadId);
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
          bailClearInterestTbAF.setLoanBankName(dto.getCollBankName());
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
