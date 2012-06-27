package org.xpup.hafmis.syscollection.common.biz.emppop.business;

import java.io.Serializable;
import java.util.List;


import org.xpup.common.exception.BusinessException;
import org.xpup.common.util.Pagination;
import org.xpup.hafmis.common.util.BusiConst;
import org.xpup.hafmis.common.util.BusiTools;
import org.xpup.hafmis.syscollection.common.biz.emppop.bsinterface.IEmpPopBS;
import org.xpup.hafmis.syscollection.common.dao.EmpDAO;
import org.xpup.hafmis.syscollection.common.domain.entity.Emp;

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
    String empid = (String)pagination.getQueryCriterions().get("empid");
    String name = (String)pagination.getQueryCriterions().get("name");
    String oldId = (String)pagination.getQueryCriterions().get("oldId");
    String orderBy = (String) pagination.getOrderBy();
    String order = (String) pagination.getOrderother(); 
    int start = pagination.getFirstElementOnPage() - 1;
    int pageSize = pagination.getPageSize();
    emps = empDAO.queryByCriterions(id,name,empid,oldId,orderBy,order,start,pageSize,status,cardnum,empname);
    // 将职工状态转换为中文
    for (int i = 0; i < emps.size(); i++) {
      Emp emp = (Emp) emps.get(i);
      emp.setPayStatusStr(BusiTools.getBusiValue(emp.getPayStatus().intValue(), BusiConst.OLDPAYMENTSTATE));
    }
    
    int count = empDAO.queryCountByCriterions(id, name,empid,oldId,status,cardnum,empname);
    pagination.setNrOfElements(count);
    return emps;
  }
}
