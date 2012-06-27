package org.xpup.hafmis.sysloan.querystatistics.datareadyquery.assistantorg.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.xpup.common.util.BSUtils;
import org.xpup.hafmis.sysloan.querystatistics.datareadyquery.assistantorg.bsinterface.IAssistantOrgBS;
import org.xpup.hafmis.sysloan.querystatistics.datareadyquery.assistantorg.dto.AssistantorgQueryTbDTO;
import org.xpup.hafmis.sysloan.querystatistics.datareadyquery.assistantorg.form.AssistantorgQueryTbAF;

public class AssistantOrgInfoShowAC extends Action {

  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    // TODO Auto-generated method stub   
    AssistantorgQueryTbDTO assistantorgQueryTbDTO = new AssistantorgQueryTbDTO();
    AssistantorgQueryTbAF assistantorgQueryTbAF = new AssistantorgQueryTbAF();
    try {
      String id = "";
      id = (String) request.getParameter("id").trim();
      IAssistantOrgBS assistantOrgBS = (IAssistantOrgBS) BSUtils
          .getBusinessService("assistantOrgBS", this, mapping.getModuleConfig());
      assistantorgQueryTbDTO = assistantOrgBS.findAssistantOrgInfo(id);
      assistantorgQueryTbAF.setAssistantorgQueryTbDTO(assistantorgQueryTbDTO);
      request.setAttribute("assistantorgQueryTbAF", assistantorgQueryTbAF);
    } catch (Exception e) {
      // TODO: handle exception
      e.printStackTrace();
    }
    return mapping.findForward("assistantorgInfo_show");
  }
}
