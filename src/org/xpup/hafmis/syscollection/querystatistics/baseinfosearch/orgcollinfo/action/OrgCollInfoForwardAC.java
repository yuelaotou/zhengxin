package org.xpup.hafmis.syscollection.querystatistics.baseinfosearch.orgcollinfo.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class OrgCollInfoForwardAC extends Action {

  public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
    // TODO Auto-generated method stub
    request.getSession().removeAttribute(OrgCollInfoShowAC.PAGINATION_KEY);
    return mapping.findForward("orgcollinfo_show_AC");
  }

}
