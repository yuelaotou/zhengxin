package org.xpup.hafmis.sysloan.accounthandle.bizcheck.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.sysloan.accounthandle.bizcheck.dto.OverPayDTO;

public class OverPayWindowPrinAC extends  Action {
  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    try {
      SecurityInfo securityInfo = (SecurityInfo) request.getSession()
      .getAttribute("SecurityInfo");  
      OverPayDTO overPayDTO =(OverPayDTO)request.getSession().getAttribute("overPayDTO");     
      request.getSession().setAttribute("overPayDTO",null);
      String opertname = securityInfo.getRealName();
      String time = securityInfo.getUserInfo().getPlbizDate();
      request.setAttribute("overPayWindowPrinDTO",overPayDTO);
      request.setAttribute("opertname",opertname);
      request.setAttribute("time",time);
      request.setAttribute("url", "");
    } catch (Exception e) {
      e.printStackTrace();
    }
    return mapping.findForward("to_overpay_windowprin");
  }

}