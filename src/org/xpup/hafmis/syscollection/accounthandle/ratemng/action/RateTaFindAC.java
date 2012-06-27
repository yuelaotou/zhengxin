package org.xpup.hafmis.syscollection.accounthandle.ratemng.action;

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

    HashMap criterions = makeCriterionsMap(f);
    HttpSession session = request.getSession();
    session.setAttribute("firstsearch", "2"); 
    Pagination pagination = new Pagination(0, 10, 1,
        "hafInterestRate.bizDate", "DESC", criterions); 
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
    return "org.xpup.hafmis.syscollection.tranmng.tranin.action.ShowRatemngListAC";
  }


  protected HashMap makeCriterionsMap(RateShowAF form) {
    HashMap m = new HashMap();
    String officecode = form.getOfficecode();
    if (!(officecode == null || "".equals(officecode))) {
      m.put("officecode", officecode);
    }

    String usetime = form.getUsetime();
    if (usetime != null && !"".equals(usetime)) {
      m.put("usetime", usetime.trim());
    }

    String ratetype = form.getRatetype();
    if (ratetype != null && !"".equals(ratetype)) {
      m.put("ratetype", ratetype.trim());
    }
    return m;
  }

  protected void modifyPagination(Pagination pagination) {
  }
}



