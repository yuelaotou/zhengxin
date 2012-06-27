package org.xpup.hafmis.syscollection.querystatistics.paymentpickstatistics.pickupreason.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.xpup.hafmis.syscollection.querystatistics.paymentpickstatistics.pickupreason.form.PickupreasonAF;

public class ShowPickupreasonListURLAC extends Action{
  public static final String PAGINATION_KEY = "org.xpup.hafmis.syscollection.querystatistics.paymentpickstatistics.pickupreason.action.ShowPickupreasonListAC";

  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
  throws Exception {
    HttpSession session = request.getSession();
    session.setAttribute(PAGINATION_KEY, null);
    PickupreasonAF pickupreasonAF=new PickupreasonAF();
    pickupreasonAF.reset(mapping, request);
    session.setAttribute("pickupreasonAF", pickupreasonAF);
    return mapping.findForward("to_showPickupreason_list"); 
  }

}
