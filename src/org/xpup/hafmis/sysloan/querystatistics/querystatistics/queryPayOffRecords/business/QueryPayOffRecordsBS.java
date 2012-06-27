package org.xpup.hafmis.sysloan.querystatistics.querystatistics.queryPayOffRecords.business;

import java.math.BigDecimal;
import java.util.Iterator;
import org.xpup.common.exception.BusinessException;
import org.xpup.common.util.Pagination;
import org.xpup.hafmis.common.util.BusiConst;
import org.xpup.hafmis.common.util.BusiTools;
import org.xpup.hafmis.orgstrct.dao.CollBankDAO;
import org.xpup.hafmis.orgstrct.domain.CollBank;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.syscollection.common.dao.EmpDAO;
import org.xpup.hafmis.sysloan.common.dao.BorrowerAccDAO;
import org.xpup.hafmis.sysloan.common.dao.BorrowerDAO;
import org.xpup.hafmis.sysloan.loanapply.loancheck.dto.LoanCheckDTO;
import org.xpup.hafmis.sysloan.loanapply.loancheck.form.LoanCheckShowAF;
import org.xpup.hafmis.sysloan.querystatistics.querystatistics.queryPayOffRecords.bsinterface.IQueryPayOffRecordsBS;
import org.xpup.hafmis.sysloan.querystatistics.querystatistics.queryPayOffRecords.dto.QueryPayOffRecordsTaListDTO;
import org.xpup.hafmis.sysloan.querystatistics.querystatistics.queryPayOffRecords.form.QueryPayOffRecordsTaShowAF;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class QueryPayOffRecordsBS implements IQueryPayOffRecordsBS {

  private CollBankDAO collBankDAO = null;

  private BorrowerDAO borrowerDAO = null;

  private EmpDAO empDAO = null;

  private BorrowerAccDAO borrowerAccDAO = null;

  public void setBorrowerAccDAO(BorrowerAccDAO borrowerAccDAO) {
    this.borrowerAccDAO = borrowerAccDAO;
  }

  public void setBorrowerDAO(BorrowerDAO borrowerDAO) {
    this.borrowerDAO = borrowerDAO;
  }

  public void setCollBankDAO(CollBankDAO collBankDAO) {
    this.collBankDAO = collBankDAO;
  }

  public void setEmpDAO(EmpDAO empDAO) {
    this.empDAO = empDAO;
  }

  public QueryPayOffRecordsTaShowAF queryBorrowerListByCriterions(
      Pagination pagination, SecurityInfo securityInfo) throws Exception,
      BusinessException {
    // TODO Auto-generated method stub
    QueryPayOffRecordsTaShowAF queryPayOffRecordsTaShowAF = new QueryPayOffRecordsTaShowAF();
    String orderBy = (String) pagination.getOrderBy();
    String order = (String) pagination.getOrderother();
    int start = pagination.getFirstElementOnPage() - 1;
    int pageSize = pagination.getPageSize();
    int page = pagination.getPage();
    int count = 0;
    String contractId = (String) pagination.getQueryCriterions().get(
        "contractId");
    String borrowerName = (String) pagination.getQueryCriterions().get(
        "borrowerName");
    String cardNum = (String) pagination.getQueryCriterions().get("cardNum");
    String loanBankName = (String) pagination.getQueryCriterions().get(
        "loanBankName");
    String loanStartDate = (String) pagination.getQueryCriterions().get(
        "loanStartDate");
    String loanEndDate = (String) pagination.getQueryCriterions().get(
        "loanEndDate");
    String loanPayOffDate = (String) pagination.getQueryCriterions().get(
        "loanPayOffDate");
    String loanPayOffEndDate = (String) pagination.getQueryCriterions().get(
        "loanPayOffEndDate");
    String office = (String) pagination.getQueryCriterions().get("office");
    String houseType = (String) pagination.getQueryCriterions().get("houseType_off");
    List list = null;
    List listAll = null;
    // 合计：人数，贷款金额，利息总额，还款总额
    String peopleNumSum = "0";
    BigDecimal loanMoneySum = new BigDecimal(0.00);
    BigDecimal interestSum = new BigDecimal(0.00);
    BigDecimal corpusSum = new BigDecimal(0.00);

    try {
      list = borrowerDAO.queryBorrowerListByCriterions(securityInfo, office,
          loanBankName, borrowerName, contractId, cardNum, loanStartDate, loanEndDate,
          loanPayOffDate,loanPayOffEndDate, start, orderBy, order, pageSize, page,houseType);
      listAll = borrowerDAO.queryBorrowerListByCriterionsAll(securityInfo,
          office, loanBankName, borrowerName, contractId, cardNum,
          loanStartDate,loanEndDate, loanPayOffDate, loanPayOffEndDate,start, orderBy, order, pageSize, page,houseType);

      if (listAll != null && listAll.size() > 0) {
        for (int i = 0; i < listAll.size(); i++) {
          QueryPayOffRecordsTaListDTO queryPayOffRecordsTaListDTO = (QueryPayOffRecordsTaListDTO) listAll
              .get(i);
          loanMoneySum = loanMoneySum.add(new BigDecimal(                                                                                                                              
              queryPayOffRecordsTaListDTO.getLoanMoney()));
          interestSum = interestSum.add(new BigDecimal(
              queryPayOffRecordsTaListDTO.getInterest()));
          corpusSum = corpusSum.add(new BigDecimal(queryPayOffRecordsTaListDTO
              .getCorpus()));
        }
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    if (listAll != null && listAll.size() > 0) {
      count = listAll.size();
      peopleNumSum = listAll.size() + "";
    }
    queryPayOffRecordsTaShowAF.setList(list);
    queryPayOffRecordsTaShowAF.setListAll(listAll);

    queryPayOffRecordsTaShowAF.setPeopleNumSum(peopleNumSum);
    queryPayOffRecordsTaShowAF.setLoanMoneySum(loanMoneySum.toString());
    queryPayOffRecordsTaShowAF.setInterestSum(interestSum.toString());
    queryPayOffRecordsTaShowAF.setCorpusSum(corpusSum.toString());

    pagination.setNrOfElements(count);
    return queryPayOffRecordsTaShowAF;
  }

}
