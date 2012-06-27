package org.xpup.security.commonlogin.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.xpup.common.util.BSUtils;
import org.xpup.security.wsf.bizsrvc.IUserControlBS;

public class ToChangeBookAAC extends Action {

  public ActionForward execute(ActionMapping arg0, ActionForm arg1,
      HttpServletRequest arg2, HttpServletResponse arg3) throws Exception {
    try {
      arg3.setContentType("text/html;charset=UTF-8");
      arg3.setHeader("Cache-Control", "no-cache");
      String bookId = arg2.getParameter("bookId");
      String userName = arg2.getParameter("userName");
      IUserControlBS securityControlBS = (IUserControlBS) BSUtils
          .getBusinessService("securityControlBS", this, arg0.getModuleConfig());
      String bookName = securityControlBS.getUserBookName(bookId);
      String bizDate = securityControlBS.getFnBizDate(userName, bookId);
      String text = "displays1('" + bookName + "','" + bizDate + "')";
      arg3.getWriter().write(text);
      arg3.getWriter().close();
    } catch (Exception e) {
      e.printStackTrace();
    }
    return null;
  }

}
