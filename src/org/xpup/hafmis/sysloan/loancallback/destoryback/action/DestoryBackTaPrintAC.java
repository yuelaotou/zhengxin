package org.xpup.hafmis.sysloan.loancallback.destoryback.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.xpup.common.util.BSUtils;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.sysloan.dataready.loanpara.bsinterface.ILoanDocNumDesignBS;
import org.xpup.hafmis.sysloan.loancallback.destoryback.dto.DestoryBackTaDTO;
public class DestoryBackTaPrintAC extends Action {

  /**
   * 办理核销收回-打印凭证
   */
  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response) {
   
    try { 
      DestoryBackTaDTO destoryBackTaDTO = new DestoryBackTaDTO();
      destoryBackTaDTO = (DestoryBackTaDTO) request.getSession()
          .getAttribute("destoryBackTaPrintDTO");
      request.getSession().setAttribute("destoryBackTaPrintDTO",null);
      SecurityInfo securityInfo = (SecurityInfo) request.getSession()
      .getAttribute("SecurityInfo");
     // String opertname = securityInfo.getRealName();
      ILoanDocNumDesignBS loanDocNumDesignBS = (ILoanDocNumDesignBS) BSUtils
      .getBusinessService("sysloanloanDocNumDesignBS", this, mapping.getModuleConfig());
      String opertname="";
      try {
        String name = loanDocNumDesignBS.getNamePara();

        if (name.equals("1")) {
          opertname = securityInfo.getUserName();
        } else {
          opertname = securityInfo.getRealName();
        }

      } catch (Exception e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }
      String time = securityInfo.getUserInfo().getPlbizDate();
      request.setAttribute("opertname", opertname);
      request.setAttribute("time", time);
      request.setAttribute("URL", "destoryBackTaShowAC.do");
      request.setAttribute("destoryBackTaPrintDTO", destoryBackTaDTO);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return mapping.findForward("to_destoryback_printta");
  }
}
