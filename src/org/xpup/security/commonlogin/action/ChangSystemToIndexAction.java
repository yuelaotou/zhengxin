package org.xpup.security.commonlogin.action;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.xpup.security.commonlogin.form.LoginActionForm;
public class ChangSystemToIndexAction extends Action{
  
  public ActionForward execute(ActionMapping arg0, ActionForm arg1, HttpServletRequest arg2, HttpServletResponse arg3) throws Exception {
    LoginActionForm lform=(LoginActionForm)arg1;
    String systemId=lform.getSystemId();
    HttpSession session=arg2.getSession(false);
    session.setAttribute("systemId", systemId);
    return arg0.findForward("toChangeBizDateAction");
  }
}
