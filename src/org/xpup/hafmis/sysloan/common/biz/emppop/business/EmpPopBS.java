package org.xpup.hafmis.sysloan.common.biz.emppop.business;

import java.io.Serializable;
import java.util.List;


import org.xpup.common.exception.BusinessException;
import org.xpup.common.util.Pagination;
import org.xpup.hafmis.sysloan.common.biz.emppop.bsinterface.IEmpPopBS;
import org.xpup.hafmis.syscollection.common.dao.EmpDAO;


public class EmpPopBS implements IEmpPopBS{
  private EmpDAO empDAO = null;  

  public void setEmpDAO(EmpDAO empDAO) {
    this.empDAO = empDAO;
  }

  public List findEmployee(Pagination pagination)throws Exception,BusinessException {
    List emps = null;
    Serializable id = (Serializable) pagination.getQueryCriterions().get("id");
    String []status=(String[])pagination.getQueryCriterions().get("status");
    String cardnum = (String)pagination.getQueryCriterions().get("cardnum");
    String empname = (String)pagination.getQueryCriterions().get("empname");
    String oldId = (String)pagination.getQueryCriterions().get("oldId");
//    System.out.println("woshishui"+pagination.getQueryCriterions().get("status"));
    String empid = (String)pagination.getQueryCriterions().get("empid");
    String name = (String)pagination.getQueryCriterions().get("name");
    String sfzh=(String)pagination.getQueryCriterions().get("sfzh");
    String dwbh=(String)pagination.getQueryCriterions().get("dwbh");
    String dwmc=(String)pagination.getQueryCriterions().get("dwmc");
    String orderBy = (String) pagination.getOrderBy();
    String order = (String) pagination.getOrderother(); 
    int start = pagination.getFirstElementOnPage() - 1;
    int pageSize = pagination.getPageSize();
    emps = empDAO.queryByCriterionsZL(id,name,empid,oldId,orderBy,order,start,pageSize,status,cardnum,empname,sfzh,dwbh,dwmc);
    int count = empDAO.queryCountByCriterionsZL(id, name,empid,oldId,status,cardnum,empname,sfzh,dwbh,dwmc);
    pagination.setNrOfElements(count);
    return emps;
  }
}
