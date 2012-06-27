package org.xpup.hafmis.sysloan.querystatistics.loanbusiquery.assureborrowerquery.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.LookupDispatchAction;
import org.xpup.common.util.BSUtils;
import org.xpup.common.util.Pagination;
import org.xpup.common.util.PaginationUtils;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.sysloan.querystatistics.loanbusiquery.assureborrowerquery.bsinterface.IAssureborrowerqueryBS;

/** 
 * MyEclipse Struts
 * Creation date: 09-22-2007
 * 
 * XDoclet definition:
 * @struts.action validate="true"
 */
public class AssureborrowerqueryMaintainAC extends LookupDispatchAction {
  public static final String PAGINATION_KEY = "org.xpup.hafmis.sysloan.querystatistics.loanbusiquery.assureborrowerquery.action.AssureborrowerqueryShowAC";
  protected Map getKeyMethodMap() {
    Map map = new HashMap();
    map.put("button.print", "print");
   
    return map;
  }
  public ActionForward print(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response){
  
    List printList=new ArrayList();
    try{
    SecurityInfo securityInfo = (SecurityInfo) request.getSession()
        .getAttribute("SecurityInfo");   
    Pagination pagination = getPagination(PAGINATION_KEY, request);
    PaginationUtils.updatePagination(pagination, request);
    
    IAssureborrowerqueryBS assureborrowerqueryBS = (IAssureborrowerqueryBS) BSUtils
    .getBusinessService("assureborrowerqueryBS", this, mapping.getModuleConfig());
    printList=assureborrowerqueryBS.findAssureborrowerqueryPrintList(pagination,securityInfo);
  
    }catch(Exception e)
    {
      e.printStackTrace();
    }
   
    request.getSession().setAttribute("printList", printList);
    return mapping.findForward("queryCongeallog_cell");
  }
  private Pagination getPagination(String paginationKey,
      HttpServletRequest request) {
    Pagination pagination = (Pagination) request.getSession().getAttribute(
        paginationKey);
    if (pagination == null) {   
      pagination = new Pagination(0, 10, 1, "t.id", "DESC",
          new HashMap(0));
      request.getSession().setAttribute(PAGINATION_KEY, pagination);
    }
    return pagination;
  }
}
