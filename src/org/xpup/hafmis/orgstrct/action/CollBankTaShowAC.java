package org.xpup.hafmis.orgstrct.action;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.xpup.common.util.BSUtils;
import org.xpup.common.util.Pagination;
import org.xpup.common.util.PaginationUtils;
import org.xpup.hafmis.orgstrct.bizsrvc.IToSecurityMngBS;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.orgstrct.form.CollBankTaAF;

public class CollBankTaShowAC extends Action {
  public static final String PAGINATION_KEY = "org.xpup.hafmis.orgstrct.action.CollBankTaShowAC";
  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
      try {

      Pagination pagination = getPagination(PAGINATION_KEY, request); 
      SecurityInfo securityInfo=(SecurityInfo)request.getSession().getAttribute("SecurityInfo");
      PaginationUtils.updatePagination(pagination, request);  
      IToSecurityMngBS toSecurityMngBS = (IToSecurityMngBS) BSUtils
          .getBusinessService("toSecurityMngBS", this, mapping.getModuleConfig());
      CollBankTaAF collBankTaAF = new CollBankTaAF();
      if(securityInfo != null)
       collBankTaAF = toSecurityMngBS.findBankListByUser(pagination, securityInfo);

      request.setAttribute("collBankTaAF", collBankTaAF);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return mapping.findForward("show_collbank");
  }  
  private Pagination getPagination(String paginationKey,
      HttpServletRequest request) {
    Pagination pagination = (Pagination) request.getSession().getAttribute(
        paginationKey);
    if (pagination == null) {
      pagination = new Pagination();
      request.getSession().setAttribute(paginationKey, pagination);
    }   
 
    return pagination;
  }

}
