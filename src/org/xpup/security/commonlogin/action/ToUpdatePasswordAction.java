/*
 * Generated by MyEclipse Struts
 * Template path: templates/java/JavaClass.vtl
 */
package org.xpup.security.commonlogin.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.xpup.common.exception.BusinessException;
import org.xpup.common.util.BSUtils;
import org.xpup.security.common.domain.User;
import org.xpup.security.commonlogin.form.LoginActionForm;
import org.xpup.security.wsf.bizsrvc.IUserControlBS;

/**
 * MyEclipse Struts Creation date: 08-15-2007 XDoclet definition:
 * 
 * @struts.action validate="true"
 * @struts.action-forward name="sussceupdatetoshowjsp"
 *                        path="/toshowupdatepassword.jsp"
 * @struts.action-forward name="badupdatepassword"
 *                        path="/toshowupdatepassword.jsp"
 */
public class ToUpdatePasswordAction extends Action {
  /*
   * Generated Methods
   */

  /**
   * Method execute
   * 
   * @param mapping
   * @param form
   * @param request
   * @param response
   * @return ActionForward
   */
  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response) {
    // ActionMessages messages = null;
    LoginActionForm loginform = (LoginActionForm) form;

    ActionMessages messages = new ActionMessages();

    String toshow = "sussceupdatetoshowjsp";

    String username = loginform.getUserName();
    String pwd = loginform.getUserPassword();
    String newpwd = loginform.getNewPassword();
    String snewpwd = loginform.getSnewPassword();

    IUserControlBS securityControlBS = (IUserControlBS) BSUtils
        .getBusinessService("securityControlBS", this, mapping
            .getModuleConfig());

    boolean falg = securityControlBS.checkLogin(username, pwd);

    if (falg) {
      // 原始密码输入有误
      // messages = new ActionMessages();
      messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
          "原始密码输入有误！", false));
      saveErrors(request, messages);

    } else {
      // 原始密码输入正确
      if (newpwd.equals(snewpwd)) {
        // 两次新密码输入一样
        User user = new User();
        try {
          String password1 = securityControlBS.encodedPassword(newpwd, user);
          user = securityControlBS.outUser(username, password1);
          loginform.setUserPassword("");
          loginform.setNewPassword("");
          loginform.setSnewPassword("");
          messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
              "密码修改成功！", false));
          saveErrors(request, messages);
        } catch (BusinessException e) {
          // TODO Auto-generated catch block

          e.printStackTrace();
        }

      } else {
        // 两次新密码输入不一样
//       messages = new ActionMessages();
        messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
            "两次新密码输入不一样！", false));
        saveErrors(request, messages);
      }
    }

    return mapping.findForward(toshow);
  }
}