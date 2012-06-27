package org.xpup.hafmis.sysloan.common.biz.loankouaccpop.business;

import java.util.ArrayList;
import java.util.List;

import org.xpup.common.exception.BusinessException;
import org.xpup.common.util.Pagination;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.sysloan.common.biz.loankouaccpop.bsinterface.ILoanKouAccpopBS;
import org.xpup.hafmis.sysloan.common.dao.BorrowerDAO;

public class LoanKouAccpopBS implements ILoanKouAccpopBS{
private BorrowerDAO borrowerDAO = null;
  
  public void setBorrowerDAO(BorrowerDAO borrowerDAO) {
    this.borrowerDAO = borrowerDAO;
  }



  public List findLoanKouAccpopList(Pagination pagination, SecurityInfo securityInfo) throws Exception, BusinessException {
    // TODO Auto-generated method stub
    List list= new ArrayList();
    String orderBy = (String) pagination.getOrderBy();
    String order = (String) pagination.getOrderother();
    int start = pagination.getFirstElementOnPage() - 1;  
    int pageSize = pagination.getPageSize();
    int count=0;
    try{
    String loankouacc = (String)pagination.getQueryCriterions().get("loankouacc");
    String contractId = (String)pagination.getQueryCriterions().get("contractId");
    String borrowerName =(String)pagination.getQueryCriterions().get("borrowerName");
    String cardNum = (String)pagination.getQueryCriterions().get("cardNum");
    String empId = (String)pagination.getQueryCriterions().get("empId");
    List empSt = (List)pagination.getQueryCriterions().get("status");
    list = borrowerDAO.queryLoanKouAccpopListByCondition(loankouacc, contractId, 
        borrowerName, cardNum, empId, empSt, start, pageSize, orderBy, order, securityInfo);
    count = borrowerDAO.queryLoanKouAccListCountByCondition(loankouacc, contractId, borrowerName, cardNum, empId, empSt,securityInfo);
    pagination.setNrOfElements(count);
    }catch(Exception e){
      e.printStackTrace();
    }
    return list;
  }
}
