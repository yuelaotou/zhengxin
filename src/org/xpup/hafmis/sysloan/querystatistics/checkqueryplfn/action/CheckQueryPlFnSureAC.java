package org.xpup.hafmis.sysloan.querystatistics.checkqueryplfn.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.xpup.hafmis.common.form.IdAF;

public class CheckQueryPlFnSureAC extends Action {
  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response) {
    try{
      IdAF idAF = (IdAF) form;
      String contractId=request.getParameter("contractid").toString();
      request.setAttribute("contractid",request.getParameter("contractid").toString());
    }catch(Exception e){
      e.printStackTrace();
    }
    return mapping.findForward("checkQueryPlFntb_find");
  }
}
