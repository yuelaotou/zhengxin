package org.xpup.hafmis.sysloan.loancallback.destoryback.action;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.sysloan.loancallback.destoryback.dto.DestoryBackTaDTO;

public class DestoryBackTbWindowPrinAC extends Action {
  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    try {
      SecurityInfo securityInfo = (SecurityInfo) request.getSession()
      .getAttribute("SecurityInfo");  
      DestoryBackTaDTO destoryBackTaDTO =(DestoryBackTaDTO)request.getSession().getAttribute("destoryBackWindowDTO");     
      request.getSession().setAttribute("destoryBackWindowDTO",null);
      String opertname = securityInfo.getRealName();
      String time = securityInfo.getUserInfo().getPlbizDate();
      request.setAttribute("destoryBackWindowPrinDTO",destoryBackTaDTO);
      request.setAttribute("opertname",opertname);
      request.setAttribute("time",time);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return mapping.findForward("to_destorybacktb_windowprin");
  }

}
