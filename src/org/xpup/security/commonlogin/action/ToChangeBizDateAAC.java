package org.xpup.security.commonlogin.action;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.xpup.common.util.BSUtils;
import org.xpup.hafmis.orgstrct.domain.HafEmployee;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.security.common.domain.MenuItem;
import org.xpup.security.common.domain.UsersloginBook;
import org.xpup.security.wsf.bizsrvc.IMenuControlBS;
import org.xpup.security.wsf.bizsrvc.IUserControlBS;
import org.xpup.hafmis.common.util.BusiTools;
import org.xpup.hafmis.common.util.bizlog.BusiLogConst;

/**
 * @author liuy
 */
public class ToChangeBizDateAAC extends Action {
  public ActionForward execute(ActionMapping arg0, ActionForm arg1,
      HttpServletRequest arg2, HttpServletResponse arg3) throws Exception {
    try {
      arg3.setContentType("text/html;charset=UTF-8");
      arg3.setHeader("Cache-Control", "no-cache");
      HttpSession session = arg2.getSession(false);
      String bizdate = "";
      String userName = arg2.getParameter("userName");
      String systemId = arg2.getParameter("systemId");

      /*IUserControlBS securityControlBS = (IUserControlBS) BSUtils
          .getBusinessService("securityControlBS", this, arg0.getModuleConfig());*/
      // SecurityInfo securityInfo = securityControlBS.getSecurityInfo(userName,
      // arg2.getRemoteAddr());
      // HafEmployee hafEmployee = securityControlBS.getHafEmployee(userName,
      // arg2.getRemoteAddr());
      IMenuControlBS menuControlBS = (IMenuControlBS) BSUtils
          .getBusinessService("securityControlBS", session.getServletContext());
      String opSystemType = ((MenuItem) menuControlBS.queryMenuItem(userName,
          systemId)).getOpSystemType();
      String isChooseBook = "";
      String date = BusiTools.dateToString(new Date(), "yyyyMMdd");
      if (opSystemType.equals(Integer
          .toString((BusiLogConst.OP_SYSTEM_TYPE_MANAGER)))) {
        bizdate = "";
      } else if (opSystemType.equals(Integer
          .toString((BusiLogConst.OP_SYSTEM_TYPE_COLLECTION)))) {
        bizdate = date;// hafEmployee.getBizDate();
      } else if (opSystemType.equals(Integer
          .toString((BusiLogConst.OP_SYSTEM_TYPE_LOAN)))) {
        bizdate = date;// hafEmployee.getPlbizDate();
      } else if (opSystemType.equals(Integer
          .toString((BusiLogConst.OP_SYSTEM_TYPE_FINANCE)))) {
        /* List userBookList = securityControlBS.getUserBookList(userName);
        if (userBookList != null && userBookList.size() > 0) {
          String bookId = ((UsersloginBook) userBookList.get(0)).getBookId();
          bizdate = securityControlBS.getFnBizDate(userName, bookId);
        } */
        bizdate = date;// 反正也不显示只是让文本里面有值
        isChooseBook = "1";
      }

      String text = "displays('" + bizdate + "','" + opSystemType + "','"
          + isChooseBook + "')";
      arg3.getWriter().write(text);
      arg3.getWriter().close();
      session.setAttribute("systemId", systemId);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return null;
  }

}
