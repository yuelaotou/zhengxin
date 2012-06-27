package org.xpup.hafmis.sysloan.accounthandle.bizcheck.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.sysloan.accounthandle.bizcheck.dto.BailDTO;

public class BailWindowPrinAC extends  Action {
  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    try {
      SecurityInfo securityInfo = (SecurityInfo) request.getSession()
      .getAttribute("SecurityInfo");  
      BailDTO bailDTO =(BailDTO)request.getSession().getAttribute("bailDTO");     
      request.getSession().setAttribute("bailDTO",null);
      String opertname = securityInfo.getRealName();
      String time = securityInfo.getUserInfo().getPlbizDate();
      request.setAttribute("bailWindowPrinDTO",bailDTO);
      request.setAttribute("opertname",opertname);
      request.setAttribute("time",time);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return mapping.findForward("to_bail_windowprin");
  }

}
