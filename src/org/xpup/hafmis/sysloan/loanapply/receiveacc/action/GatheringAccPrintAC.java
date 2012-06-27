package org.xpup.hafmis.sysloan.loanapply.receiveacc.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.xpup.common.util.BSUtils;
import org.xpup.hafmis.common.form.IdAF;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.sysloan.loanapply.receiveacc.bsinterface.IReceiveaccBS;
import org.xpup.hafmis.sysloan.loanapply.receiveacc.dto.GatheringAccInfoDTO;

public class GatheringAccPrintAC extends Action {

  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    // TODO Auto-generated method stub
    try {
      String realName = "";
      IdAF idAF = (IdAF) form;
      SecurityInfo securityInfo = (SecurityInfo) request.getSession()
          .getAttribute("SecurityInfo");
      String bizdate = (String) securityInfo.getUserInfo().getPlbizDate();
      request.setAttribute("bizDate", bizdate);

      GatheringAccInfoDTO gatheringAccInfoDTO = new GatheringAccInfoDTO();
      String receiveBankModifyId = (String) idAF.getId();
      IReceiveaccBS receiveaccBS = (IReceiveaccBS) BSUtils.getBusinessService(
          "receiveaccBS", this, mapping.getModuleConfig());
      gatheringAccInfoDTO = receiveaccBS.findGatheringAccInfo(
          receiveBankModifyId, securityInfo.getUserInfo().getUsername());

      request.setAttribute("empaddpURL", "javascript:history.back();");
      request.setAttribute("gatheringAccInfoDTO", gatheringAccInfoDTO);
    } catch (Exception e) {
      // TODO: handle exception
      e.printStackTrace();
    }
    return mapping.findForward("to_gatheringAccInfoDTO_print");
  }

}
