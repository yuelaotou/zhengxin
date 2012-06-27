package org.xpup.hafmis.syscollection.querystatistics.accountinfo.empaccountinfo.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.xpup.hafmis.syscollection.querystatistics.accountinfo.empaccountinfo.form.EmpAccountAF;

public class ShowEmpAccountListURLAC extends Action{
  public static final String PAGINATION_KEY = "org.xpup.hafmis.syscollection.querystatistics.accountinfo.empaccountinfo.action.ShowEmpAccountListAC";
  public static final String PAGINATION_KEY1 = "org.xpup.hafmis.syscollection.querystatistics.accountinfo.empaccountinfo.action.ShowEmpAccountMonthListAC";
  public static final String PAGINATION_KEY2 = "org.xpup.hafmis.syscollection.querystatistics.accountinfo.empaccountinfo.action.ShowEmpAccountDayListAC";
  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
  throws Exception {
    HttpSession session = request.getSession();
    session.setAttribute(PAGINATION_KEY, null);
    session.setAttribute(PAGINATION_KEY1, null);
    session.setAttribute(PAGINATION_KEY2, null);
    EmpAccountAF empAccountAF=new EmpAccountAF();
    empAccountAF.reset(mapping, request);
    session.setAttribute("empAccountAF", empAccountAF);
    return mapping.findForward("to_showEmpAccount_list"); 
  }

}
