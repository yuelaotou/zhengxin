package org.xpup.hafmis.sysloan.querystatistics.querystatistics.overduequery.business;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.xpup.common.exception.BusinessException;
import org.xpup.common.util.Pagination;
import org.xpup.hafmis.common.util.BusiTools;
import org.xpup.hafmis.orgstrct.dao.CollBankDAO;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.sysloan.common.dao.OverdueInfoDAO;
import org.xpup.hafmis.sysloan.querystatistics.querystatistics.overduequery.bsinterface.IOverdueQueryBS;
import org.xpup.hafmis.sysloan.querystatistics.querystatistics.overduequery.dto.OverdueInfoDTO;
import org.xpup.hafmis.sysloan.querystatistics.querystatistics.overduequery.form.OverdueQueryTaAF;

public class OverdueQueryBS implements IOverdueQueryBS {
  private CollBankDAO collBankDAO = null;

  private OverdueInfoDAO overdueInfoDAO = null;

  public void setCollBankDAO(CollBankDAO collBankDAO) {
    this.collBankDAO = collBankDAO;
  }

  public void setOverdueInfoDAO(OverdueInfoDAO overdueInfoDAO) {
    this.overdueInfoDAO = overdueInfoDAO;
  }

  public void updatePunishInterest(SecurityInfo securityInfo)
      throws BusinessException, Exception {
    overdueInfoDAO.execUpdatePunishInst(securityInfo.getUserInfo()
        .getPlbizDate());
  }


  public OverdueQueryTaAF queryOverdueList(Pagination pagination,
      SecurityInfo securityInfo) throws BusinessException, Exception {
    OverdueQueryTaAF af = new OverdueQueryTaAF();
    List list = null;
    try {
      String orderBy = (String) pagination.getOrderBy();
      String order = (String) pagination.getOrderother();
      int start = pagination.getFirstElementOnPage() - 1;
      int pageSize = pagination.getPageSize();
      int count = 0;
      String office = (String) pagination.getQueryCriterions().get("office");
      String bankId = (String) pagination.getQueryCriterions().get("bankId");
      if (bankId != null && !"".equals(bankId)) {
        String bankName = collBankDAO.getCollBankByCollBankid_(bankId)
            .getCollBankName();
        pagination.getQueryCriterions().put("bankName", bankName);
      }
      String contractId = (String) pagination.getQueryCriterions().get(
          "contractId");
      String borrowerName = (String) pagination.getQueryCriterions().get(
          "borrowerName");
      String cardNum = (String) pagination.getQueryCriterions().get("cardNum");
      String loanKouAcc = (String) pagination.getQueryCriterions().get(
          "loanKouAcc");
      String agreement = (String) pagination.getQueryCriterions().get(
          "agreement");
      String overdueMonthSt = (String) pagination.getQueryCriterions().get(
          "overdueMonthSt");
      String overdueMonthEnd = (String) pagination.getQueryCriterions().get(
          "overdueMonthEnd");
      String today = BusiTools.dateToString(new Date(), "yyyyMMdd");

      // 查询pl201_1的日期是否有记录,有则说明今天已经查询过即更新过pl201的备选C(罚息),没有则执行更新
      int num = overdueInfoDAO.queryPl201_1(today);
      if (num == 0) {
        overdueInfoDAO.updatePl201_1(today);
        updatePunishInterest(securityInfo); // 更新pl201的备选C(罚息)
      }
      list = overdueInfoDAO.queryOverdueList(office, bankId, contractId,
          borrowerName, cardNum, loanKouAcc, overdueMonthSt, overdueMonthEnd,
          agreement, start, orderBy, order, pageSize, securityInfo);
      count = overdueInfoDAO.queryOverdueCount(office, bankId, contractId,
          borrowerName, cardNum, loanKouAcc, overdueMonthSt, overdueMonthEnd,
          agreement, securityInfo);
      // 逾期月数大于1的人数
      int overdueCount = 0;
      if (overdueMonthSt != null && Integer.parseInt(overdueMonthSt) > 1) {
        overdueCount = count;
      } else {
        overdueCount = overdueInfoDAO.queryOverdueCount(office, bankId,
            contractId, borrowerName, cardNum, loanKouAcc, "2",
            overdueMonthEnd, agreement, securityInfo);
      }
      int hkzPsnCount = overdueInfoDAO.queryHKZCount(office, bankId,
          contractId, borrowerName, cardNum, loanKouAcc, securityInfo);
      String overdueRate = "";
      if (hkzPsnCount != 0)
        overdueRate = (new BigDecimal(overdueCount + "").divide(new BigDecimal(
            hkzPsnCount + ""), 4, BigDecimal.ROUND_HALF_UP)).multiply(
            new BigDecimal(100)).setScale(2, BigDecimal.ROUND_HALF_DOWN)
            + "%";
      af.setOverdueRate(overdueRate);
      Object[] info = overdueInfoDAO.queryTotalInfoByCriterions(office, bankId,
          contractId, borrowerName, cardNum, loanKouAcc, overdueMonthSt,
          overdueMonthEnd, agreement, securityInfo);
      BigDecimal overdueCorpus = null;
      if (overdueMonthSt != null && Integer.parseInt(overdueMonthSt) > 1) {
        overdueCorpus = new BigDecimal(info[0] == null ? "0.00" : info[0]
            .toString());
      } else {
        Object[] obj = overdueInfoDAO.queryTotalInfoByCriterions(office,
            bankId, contractId, borrowerName, cardNum, loanKouAcc, "2",
            overdueMonthEnd, agreement, securityInfo);
        overdueCorpus = new BigDecimal(obj[0] == null ? "0.00" : obj[0]
            .toString());
      }
      BigDecimal balance = overdueInfoDAO.queryHKZBalance(office, bankId,
          contractId, borrowerName, cardNum, loanKouAcc, securityInfo);
      String overdueMoneyRate = "";
      if (balance.intValue() != 0)
        overdueMoneyRate = (new BigDecimal(overdueCorpus + "").divide(
            new BigDecimal(balance + ""), 4, BigDecimal.ROUND_HALF_UP))
            .multiply(new BigDecimal(100)).setScale(2,
                BigDecimal.ROUND_HALF_DOWN)
            + "%";
      af.setOverdueMoneyRate(overdueMoneyRate);
      if (info != null) {
        af.setShouldCorpusSum(new BigDecimal(info[0] == null ? "0.00" : info[0]
            .toString()));
        af.setShouldInterestSum(new BigDecimal(info[1] == null ? "0.00"
            : info[1].toString()));
        af.setShouldPayMoneySum(new BigDecimal(info[2] == null ? "0.00"
            : info[2].toString()));
      }
      af.setList(list);
      pagination.setNrOfElements(count);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return af;
  }

  public OverdueQueryTaAF queryOverdueAllList(Pagination pagination,
      SecurityInfo securityInfo) throws BusinessException, Exception {
    OverdueQueryTaAF af = new OverdueQueryTaAF();
    List list = null;
    try {
      String orderBy = (String) pagination.getOrderBy();
      String order = (String) pagination.getOrderother();
      String office = (String) pagination.getQueryCriterions().get("office");
      String bankId = (String) pagination.getQueryCriterions().get("bankId");
      String contractId = (String) pagination.getQueryCriterions().get(
          "contractId");
      String borrowerName = (String) pagination.getQueryCriterions().get(
          "borrowerName");
      String cardNum = (String) pagination.getQueryCriterions().get("cardNum");
      String loanKouAcc = (String) pagination.getQueryCriterions().get(
          "loanKouAcc");
      String agreement = (String) pagination.getQueryCriterions().get(
          "agreement");
      String overdueMonthSt = (String) pagination.getQueryCriterions().get(
          "overdueMonthSt");
      String overdueMonthEnd = (String) pagination.getQueryCriterions().get(
          "overdueMonthEnd");
      list = overdueInfoDAO.queryOverdueList(office, bankId, contractId,
          borrowerName, cardNum, loanKouAcc, overdueMonthSt, overdueMonthEnd,
          agreement, 0, orderBy, order, 0, securityInfo);
      for (int i = 0; i < list.size(); i++) {
        OverdueInfoDTO dto = (OverdueInfoDTO) list.get(i);
        dto.setLoanBank(collBankDAO.getCollBankByCollBankid_(dto.getLoanBank())
            .getCollBankName());
      }
      af.setList(list);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return af;
  }

  public List queryOverdueInfByContractid(Pagination pagination,
      SecurityInfo securityInfo) throws BusinessException, Exception {
    List list = null;
    try {
      String orderBy = (String) pagination.getOrderBy();
      String order = (String) pagination.getOrderother();
      int start = pagination.getFirstElementOnPage() - 1;
      int pageSize = pagination.getPageSize();

      String contractId = (String) pagination.getQueryCriterions().get(
          "contractId");
      list = overdueInfoDAO.queryOverdueInfoList(contractId, start, pageSize,
          orderBy, order, securityInfo);
      int count = overdueInfoDAO
          .queryOverdueInfoCount(contractId, securityInfo);
      pagination.setNrOfElements(count);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return list;
  }
  
  public OverdueQueryTaAF queryExpList(Pagination pagination,
      SecurityInfo securityInfo) throws BusinessException, Exception {
    OverdueQueryTaAF af = new OverdueQueryTaAF();
    List list = null;
    try {
      String orderBy = (String) pagination.getOrderBy();
      String order = (String) pagination.getOrderother();
      String office = (String) pagination.getQueryCriterions().get("office");
      String bankId = (String) pagination.getQueryCriterions().get("bankId");
      String contractId = (String) pagination.getQueryCriterions().get(
          "contractId");
      String borrowerName = (String) pagination.getQueryCriterions().get(
          "borrowerName");
      String cardNum = (String) pagination.getQueryCriterions().get("cardNum");
      String loanKouAcc = (String) pagination.getQueryCriterions().get(
          "loanKouAcc");
      String agreement = (String) pagination.getQueryCriterions().get(
          "agreement");
      String overdueMonthSt = (String) pagination.getQueryCriterions().get(
          "overdueMonthSt");
      String overdueMonthEnd = (String) pagination.getQueryCriterions().get(
          "overdueMonthEnd");
      
      list = overdueInfoDAO.queryOverdueList(office, bankId, contractId,
          borrowerName, cardNum, loanKouAcc, overdueMonthSt, overdueMonthEnd,
          agreement, 0, orderBy, order, 0, securityInfo);
      af.setList(list);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return af;
  }
}
