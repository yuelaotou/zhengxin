package org.xpup.hafmis.sysloan.querystatistics.loanbusiquery.assureborrowerquery.action;

import java.util.HashMap;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.xpup.common.util.BSUtils;
import org.xpup.common.util.Pagination;
import org.xpup.common.util.PaginationUtils;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.sysloan.querystatistics.loanbusiquery.assureborrowerquery.bsinterface.IAssureborrowerqueryBS;
import org.xpup.hafmis.sysloan.querystatistics.loanbusiquery.assureborrowerquery.form.AssureborrowerqueryAF;

/** 
 * MyEclipse Struts
 * Creation date: 09-20-2007
 * 
 * XDoclet definition:
 * @struts.action validate="true"
 */
public class AssureborrowerqueryShowAC extends Action {
  
  public static final String PAGINATION_KEY = "org.xpup.hafmis.sysloan.querystatistics.loanbusiquery.assureborrowerquery.action.AssureborrowerqueryShowAC";
	/** 
	 * Method execute
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 */
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
    ActionMessages messages = null;
    AssureborrowerqueryAF assureborrowerqueryAF=new AssureborrowerqueryAF();
    SecurityInfo securityInfo = (SecurityInfo) request.getSession()
    .getAttribute("SecurityInfo");
    try{
      Pagination pagination = getPagination(PAGINATION_KEY, request);
      PaginationUtils.updatePagination(pagination, request);
      
      IAssureborrowerqueryBS assureborrowerqueryBS = (IAssureborrowerqueryBS) BSUtils
      .getBusinessService("assureborrowerqueryBS", this, mapping.getModuleConfig());
      String type=(String)request.getAttribute("type");
      if(type==null||!"1".equals(type)){
        assureborrowerqueryAF.setList(assureborrowerqueryBS.findAssureborrowerqueryList(pagination,securityInfo)); 
      }
    }
    catch(Exception e)
    {e.printStackTrace();
      messages = new ActionMessages();
      messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("œ‘ æ¡–±Ì ß∞‹", false));
      saveErrors(request, messages);
    }
   request.setAttribute("assureborrowerqueryAF", assureborrowerqueryAF);
		return mapping.findForward("assureborrower_show");
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