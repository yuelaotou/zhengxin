package org.xpup.hafmis.sysloan.accounthandle.bizcheck.action;

import java.math.BigDecimal;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.sysloan.accounthandle.bizcheck.dto.AdjustAccountDTO;


public class AdjustAccountWindowPrinAC  extends  Action {
  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    try {
      SecurityInfo securityInfo = (SecurityInfo) request.getSession()
      .getAttribute("SecurityInfo");  
      AdjustAccountDTO adjustAccountDTO =(AdjustAccountDTO)request.getSession().getAttribute("adjustAccountDTO");     
      request.getSession().setAttribute("adjustAccountDTO",null);
      String opertname = securityInfo.getRealName();
      String time = securityInfo.getUserInfo().getPlbizDate();
      if(adjustAccountDTO.getOccurMoney()==null||"".equals(adjustAccountDTO.getOccurMoney()))
        adjustAccountDTO.setOccurMoney(new BigDecimal(0.00));
      request.setAttribute("adjustAccountWindowPrinDTO",adjustAccountDTO);
      request.setAttribute("opertname",opertname);
      request.setAttribute("time",time);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return mapping.findForward("to_adjustAccount_windowprin");
  }

}

