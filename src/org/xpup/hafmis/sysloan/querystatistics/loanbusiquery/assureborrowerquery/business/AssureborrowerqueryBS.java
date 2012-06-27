package org.xpup.hafmis.sysloan.querystatistics.loanbusiquery.assureborrowerquery.business;



import java.util.List;
import org.xpup.common.util.Pagination;
import org.xpup.hafmis.common.util.BusiConst;
import org.xpup.hafmis.common.util.BusiTools;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.sysloan.common.dao.BorrowerDAO;
import org.xpup.hafmis.sysloan.querystatistics.loanbusiquery.assureborrowerquery.bsinterface.IAssureborrowerqueryBS;
import org.xpup.hafmis.sysloan.querystatistics.loanbusiquery.assureborrowerquery.dto.AssureborrowerqueryDTO;

public class AssureborrowerqueryBS implements IAssureborrowerqueryBS{

  
  
  
  private BorrowerDAO borrowerDAO=null;

  public List findAssureborrowerqueryList(Pagination pagination,SecurityInfo securityInfo)
  {
    List list=null;
    try{
      String orderBy = (String) pagination.getOrderBy();
      int start = pagination.getFirstElementOnPage() - 1;
      int pageSize = pagination.getPageSize();
      int page = pagination.getPage();
      String orderother=pagination.getOrderother();
        String  empid= (String) pagination.getQueryCriterions().get("empId");//职工编号
      String  empName= (String) pagination.getQueryCriterions().get("empName");//职工姓名  
      
      String  contractId= (String) pagination.getQueryCriterions().get("contractId");//职工编号
      String  loanKouAcc= (String) pagination.getQueryCriterions().get("loanKouAcc");//职工姓名
      
      String  orgId= (String) pagination.getQueryCriterions().get("orgId");//职工编号
      String  orgName= (String) pagination.getQueryCriterions().get("orgName");//职工姓名
      
      list=borrowerDAO.findAssureborrowerqueryList_wsh(empid, empName, contractId, loanKouAcc, orgId, orgName, securityInfo, orderBy, orderother, start, pageSize, page);
      if(list.size()!=0||list!=null)
      {
        for(int i=0;i<list.size();i++)
        {
          AssureborrowerqueryDTO assureborrowerqueryDTO = new AssureborrowerqueryDTO();
          assureborrowerqueryDTO=(AssureborrowerqueryDTO)list.get(i);
          String type=assureborrowerqueryDTO.getType();
          
          assureborrowerqueryDTO.setType(BusiTools.getBusiValue(Integer.parseInt(type),
              BusiConst.CONGEALINFOTYPE));
      
        }
      }
    List countList =borrowerDAO.findAssureborrowerqueryCountList_wsh(empid, empName, contractId, loanKouAcc, orgId, orgName, securityInfo, orderBy, orderother, start, pageSize, page);
    int count = countList.size();
    pagination.setNrOfElements(count);
    }
    catch(Exception e)
    {
      e.printStackTrace();
    }
    return list;
  }
  
  
  
  
  public List findAssureborrowerqueryPrintList(Pagination pagination,SecurityInfo securityInfo)
  {
    List list=null;
    try{
      String orderBy = (String) pagination.getOrderBy();
      int start = pagination.getFirstElementOnPage() - 1;
      int pageSize = pagination.getPageSize();
      int page = pagination.getPage();
      String orderother=pagination.getOrderother();
        String  empid= (String) pagination.getQueryCriterions().get("empId");//职工编号
      String  empName= (String) pagination.getQueryCriterions().get("empName");//职工姓名  
      
      String  contractId= (String) pagination.getQueryCriterions().get("contractId");//职工编号
      String  loanKouAcc= (String) pagination.getQueryCriterions().get("loanKouAcc");//职工姓名
      
      String  orgId= (String) pagination.getQueryCriterions().get("orgId");//职工编号
      String  orgName= (String) pagination.getQueryCriterions().get("orgName");//职工姓名
      
      list=borrowerDAO.findAssureborrowerqueryPrintList_wsh(empid, empName, contractId, loanKouAcc, orgId, orgName, securityInfo, orderBy, orderother, start, pageSize, page);
      if(list.size()!=0||list!=null)
      {
        for(int i=0;i<list.size();i++)
        {
          AssureborrowerqueryDTO assureborrowerqueryDTO = new AssureborrowerqueryDTO();
          assureborrowerqueryDTO=(AssureborrowerqueryDTO)list.get(i);
          String type=assureborrowerqueryDTO.getType();
          
          assureborrowerqueryDTO.setType(BusiTools.getBusiValue(Integer.parseInt(type),
              BusiConst.CONGEALINFOTYPE));
      
        }
      }
   
    }
    catch(Exception e)
    {
      e.printStackTrace();
    }
    return list;
  }




  public void setBorrowerDAO(BorrowerDAO borrowerDAO) {
    this.borrowerDAO = borrowerDAO;
  }
  
}
