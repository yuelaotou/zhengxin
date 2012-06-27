package org.xpup.security.commonlogin.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.acegisecurity.AuthenticationException;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.xpup.common.exception.BusinessException;
import org.xpup.common.util.BSUtils;
import org.xpup.common.util.Pagination;
import org.xpup.hafmis.demo.bsinterface.IDemoBS;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.security.common.domain.User;
import org.xpup.security.commonlogin.form.LoginActionForm;
import org.xpup.security.wsf.acegiex.AuthenticationProcessingFilterEx;
import org.xpup.security.wsf.bizsrvc.IUserControlBS;

public class LoginAction extends Action{
  
  public ActionForward execute(ActionMapping arg0, ActionForm arg1, HttpServletRequest arg2, HttpServletResponse arg3) throws Exception {
    String text=null;
    try{
      IUserControlBS securityControlBS = (IUserControlBS) BSUtils
      .getBusinessService("securityControlBS", this, arg0.getModuleConfig());
      //String validateCode=arg2.getParameter("validateCode");
      String userName=arg2.getParameter("userName");  
      String userPassword=arg2.getParameter("userPassword");
      HttpSession session = arg2.getSession(false);
      User user=securityControlBS.findUserbyUsername(userName);
      if(userName==null||userName.equals("")){
        text="toFail(1)";
        arg3.getWriter().write(text); 
        arg3.getWriter().close();
      }else if(user==null){
        text="toFail(2)";
        arg3.getWriter().write(text); 
        arg3.getWriter().close();
      }/*else if(userPassword==null||userPassword.equals("")){
        text="toFail(3)";
        arg3.getWriter().write(text); 
        arg3.getWriter().close();
      }*/else if(securityControlBS.checkLogin(userName, userPassword)){
        text="toFail(4)";
        arg3.getWriter().write(text); 
        arg3.getWriter().close();
      }/*else if(validateCode==null||validateCode.equals("")){
        text="toFail(5)";
        arg3.getWriter().write(text); 
        arg3.getWriter().close();
      }else if(checkValidatecode(validateCode, session)){
        text="toFail(6)";
        arg3.getWriter().write(text); 
        arg3.getWriter().close();
      }*/else{
      text="toSuccess()";
      arg3.getWriter().write(text); 
      arg3.getWriter().close();
      }

    }catch(Exception be){
      be.printStackTrace();
      text="toFail()";
      arg3.getWriter().write(text); 
      arg3.getWriter().close();
    }
    return null;
  }

  /**
   * 校验验证码
   * 
   * @param request
   * @param session
   * @return
   * @throws BusinessException 
   */
  protected boolean checkValidatecode(String writeValidatecode,
      HttpSession session) throws Exception {
    boolean flag=true;
      String validateCodeInSession = (String) session
          .getAttribute(AuthenticationProcessingFilterEx.ACEGI_SECURITY_FORM_VALIDATECODE_KEY_IN_SESSION);
      if ((writeValidatecode!= null)&&(!writeValidatecode.equals(""))&&((writeValidatecode.trim()).equals(validateCodeInSession))) {
       flag=false;
      }
      return flag;
    
  }
}