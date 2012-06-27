package org.xpup.security.commonlogin.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.xpup.common.util.BSUtils;
import org.xpup.hafmis.common.util.bizlog.BusiLogConst;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.security.common.domain.MenuItem;
import org.xpup.security.common.domain.UsersloginBook;
import org.xpup.security.commonlogin.form.LoginActionForm;
import org.xpup.security.wsf.bizsrvc.IUserControlBS;

public class ToIndexAction extends Action {

  public ActionForward execute(ActionMapping arg0, ActionForm arg1,
      HttpServletRequest arg2, HttpServletResponse arg3) throws Exception {
    LoginActionForm lform = (LoginActionForm) arg1;
    String userName = lform.getUserName();
    HttpSession session = arg2.getSession(false);
    IUserControlBS securityControlBS = (IUserControlBS) BSUtils
        .getBusinessService("securityControlBS", this, arg0.getModuleConfig());
    SecurityInfo securityInfo = securityControlBS.getSecurityInfo(userName,
        arg2.getRemoteAddr());
    String bookId = (String) session.getAttribute("bookId");
    lform.setBookName(securityControlBS.getUserBookName(lform.getBookId()));
    String opSystemType = (String) session.getAttribute("opSystemType");
    securityInfo.setBookId(bookId);
    securityInfo.setOpSystemType(opSystemType);
    String bizDate = "";
    if (opSystemType.equals(Integer
        .toString((BusiLogConst.OP_SYSTEM_TYPE_FINANCE)))) {
      if (bookId != null || !"".equals(bookId)) {
        bizDate = securityControlBS.getFnBizDate(userName, bookId);
        lform.setBizdate(bizDate);
      }
    }
    securityInfo.getUserInfo().setFbizDate(bizDate);
    session.setAttribute("SecurityInfo", securityInfo);
    session.setAttribute("loginActionForm", lform);
    return arg0.findForward("index");
  }

}
