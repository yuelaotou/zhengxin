package org.xpup.hafmis.syscollection.chgbiz.chgperson.action;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.xpup.common.util.Pagination;
import org.xpup.hafmis.syscollection.chgbiz.chgperson.form.ChgpersonMaintainListAF;

public class FindChgpersonMaintainListAC extends Action {

  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {

    ChgpersonMaintainListAF f = (ChgpersonMaintainListAF) form;

    HashMap criterions = makeCriterionsMap(f);
    
    String orgID=request.getParameter("id");
    String orgName=request.getParameter("name");
    String chgDate=request.getParameter("date"); 
    String endchgDate=request.getParameter("endDate");  

    HttpSession session = request.getSession();
    session.setAttribute("firstsearch", "2"); 
    
    if (!(orgID == null || "".equals(orgID))) {
      criterions.put("orgID", orgID);
    }
    
    if (!(orgName == null || orgName.length() == 0))
      criterions.put("orgName", orgName);
    
    if (!(chgDate == null || chgDate.length() == 0))
      criterions.put("chgDate", chgDate);
    if (!(endchgDate == null || endchgDate.length() == 0))
      criterions.put("endchgDate", endchgDate);

  
    Pagination pagination = new Pagination(0, 10, 1,
        "chgPersonHead.chgStatus ASC,chgPersonHead.id DESC", "", criterions); 
    String paginationKey = getPaginationKey();
    request.getSession().setAttribute(paginationKey, pagination);
    modifyPagination(pagination);
    
    f.reset(mapping, request);

    return mapping.findForward(getForword());
  }

  protected String getForword() {
    return "to_chgpersonMaintain_list";
  }

  protected String getPaginationKey() {
    return "org.xpup.hafmis.syscollection.chgbiz.chgperson.action.ShowChgpersonMaintainListAC";
  }

  protected HashMap makeCriterionsMap(ChgpersonMaintainListAF form) {
    HashMap m = new HashMap();
    return m;
  }

  protected void modifyPagination(Pagination pagination) {
  }
}



