package org.xpup.hafmis.syscollection.querystatistics.collbyfund.business;

import java.math.BigDecimal;
import java.util.List;

import org.xpup.common.exception.BusinessException;
import org.xpup.common.util.Pagination;
import org.xpup.hafmis.orgstrct.dao.CollBankDAO;
import org.xpup.hafmis.orgstrct.domain.CollBank;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.syscollection.common.dao.PickHeadDAO;
import org.xpup.hafmis.syscollection.querystatistics.collbyfund.bsinterface.ICollByFundBS;
import org.xpup.hafmis.syscollection.querystatistics.collbyfund.dto.CollByFundBankDTO;
import org.xpup.hafmis.syscollection.querystatistics.collbyfund.form.CollByFundAF;

public class CollByFundBS implements ICollByFundBS {

  private PickHeadDAO pickHeadDAO = null;

  private CollBankDAO collBankDAO = null;

  public void setPickHeadDAO(PickHeadDAO pickHeadDAO) {
    this.pickHeadDAO = pickHeadDAO;
  }

  public void setCollBankDAO(CollBankDAO collBankDAO) {
    this.collBankDAO = collBankDAO;
  }

  public CollByFundAF queryCollByFundByCriterions(Pagination pagination,
      SecurityInfo securityInfo) throws Exception, BusinessException {
    CollByFundAF collByFundAF = new CollByFundAF();
    String orderBy = (String) pagination.getOrderBy();
    String order = (String) pagination.getOrderother();
    int start = pagination.getFirstElementOnPage() - 1;
    int pageSize = pagination.getPageSize();
    String officeCode = (String) pagination.getQueryCriterions().get(
        "officeCode");
    String collBankId = (String) pagination.getQueryCriterions().get(
        "collBankId");
    String contractId = (String) pagination.getQueryCriterions().get(
        "contractId");
    String orgId = (String) pagination.getQueryCriterions().get("orgId");
    String orgName = (String) pagination.getQueryCriterions().get("orgName");
    String empId = (String) pagination.getQueryCriterions().get("empId");
    String empName = (String) pagination.getQueryCriterions().get("empName");
    String cardNum = (String) pagination.getQueryCriterions().get("cardNum");
    String begDate = (String) pagination.getQueryCriterions().get("begDate");
    String endDate = (String) pagination.getQueryCriterions().get("endDate");
    String batchNum = (String) pagination.getQueryCriterions().get("batchNum");
    List list = pickHeadDAO.queryCollByFundByCriterions(officeCode, collBankId,
        contractId, orgId, orgName, empId, empName, cardNum, begDate, endDate,
        batchNum, start, pageSize, orderBy, order, securityInfo);
    List printList = pickHeadDAO.queryCollByFundAllByCriterions(officeCode,
        collBankId, contractId, orgId, orgName, empId, empName, cardNum,
        begDate, endDate, batchNum);
    List bybankList = pickHeadDAO.queryCollByFundCollBankByCriterions(
        officeCode, collBankId, contractId, orgId, orgName, empId, empName,
        cardNum, begDate, endDate, batchNum, start, pageSize, orderBy, order,
        securityInfo);
    BigDecimal money = pickHeadDAO.queryCollByFundSumByCriterions(officeCode,
        collBankId, contractId, orgId, orgName, empId, empName, cardNum,
        begDate, endDate, batchNum);
    String date = pickHeadDAO.queryCollByFundDateByCriterions(officeCode,
        collBankId, contractId, orgId, orgName, empId, empName, cardNum,
        begDate, endDate, batchNum);
    for (int i = 0; i < bybankList.size(); i++) {
      CollByFundBankDTO dto = (CollByFundBankDTO) bybankList.get(i);
      CollBank collBank = collBankDAO.getCollBankByCollBankid_(dto
          .getCollBankId());
      dto.setCollBankId(collBank.getCollBankName());
    }
    collByFundAF.setMoney(money);
    collByFundAF.setDate(date);
    collByFundAF.setList(list);
    collByFundAF.setPrintList(printList);
    collByFundAF.setBybankList(bybankList);
    pagination.setNrOfElements(printList.size());
    return collByFundAF;
  }
}
