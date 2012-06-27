package org.xpup.hafmis.sysloan.common.biz.assistantorgpop.business;

import java.util.ArrayList;
import java.util.List;

import org.xpup.common.exception.BusinessException;
import org.xpup.common.util.Pagination;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.sysloan.common.biz.assistantorgpop.bsinterface.IAssistantorgpopBS;
import org.xpup.hafmis.sysloan.common.dao.AssistantOrgDAO;
/**
 * 
 * @author yuqf
 *
 */
public class AssistantorgpopBS implements IAssistantorgpopBS {

  private AssistantOrgDAO assistantOrgDAO = null;//pl007
  
  public void setAssistantOrgDAO(AssistantOrgDAO assistantOrgDAO) {
    this.assistantOrgDAO = assistantOrgDAO;
  }

  public List findAssistantpopList(Pagination pagination, SecurityInfo securityInfo) throws Exception, BusinessException {
    // TODO Auto-generated method stub
    List list= new ArrayList();
    String orderBy = (String) pagination.getOrderBy();
    String order = (String) pagination.getOrderother();
    int start = pagination.getFirstElementOnPage() - 1;  
    int pageSize = pagination.getPageSize();
    int count=0;
    try{
      String id = (String)pagination.getQueryCriterions().get("id");
      String name =(String)pagination.getQueryCriterions().get("name");
      list = assistantOrgDAO.queryAssistantOrgList(id, name, start, pageSize, orderBy, order, securityInfo);
      count = assistantOrgDAO.queryAssistantOrgList_(id, name, start, pageSize, orderBy, order, securityInfo);
    }catch(Exception e){
      e.printStackTrace();
    }
    pagination.setNrOfElements(count);
    return list;
  }

}
