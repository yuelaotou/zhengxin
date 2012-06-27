package org.xpup.hafmis.sysloan.dataready.rate.action;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.xpup.common.util.Pagination;
import org.xpup.hafmis.sysloan.dataready.rate.form.RateShowAF;

public class RateTaFindAC extends Action {

  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {

    RateShowAF f = (RateShowAF) form;
    System.out.println(f.getRatetype()+"==================>");
    HashMap criterions = makeCriterionsMap(f);
    
    String officecode=request.getParameter("officecode");
    String usetime=request.getParameter("usetime");
    String ratetype=request.getParameter("ratetype");
    String adjustBasis=request.getParameter("adjustBasis");

    HttpSession session = request.getSession();
    session.setAttribute("firstsearch", "2"); 
    
    if (!(officecode == null || "".equals(officecode))) {
      criterions.put("officecode", officecode);
    }
    
    if (!(usetime == null || usetime.length() == 0))
      criterions.put("usetime", usetime);
    
    if (!(ratetype == null || ratetype.length() == 0))
      criterions.put("ratetype", ratetype);
    
    if (!(adjustBasis == null || adjustBasis.length() == 0))
      criterions.put("adjustBasis", adjustBasis);
  
    Pagination pagination = new Pagination(0, 10, 1,
        "loanRate.loanRateId", "DESC", criterions); 
    String paginationKey = getPaginationKey();
    request.getSession().setAttribute(paginationKey, pagination);
    modifyPagination(pagination);
    
    f.reset(mapping, request);

    return mapping.findForward(getForword());
  }

  protected String getForword() {
    return "rateTaShowAC";
  }

  protected String getPaginationKey() {
    return "org.xpup.hafmis.sysloan.dataready.rate.action.RateTaShowAC";
  }

  protected HashMap makeCriterionsMap(RateShowAF form) {
    HashMap m = new HashMap();
    return m;
  }

  protected void modifyPagination(Pagination pagination) {
  }
}



