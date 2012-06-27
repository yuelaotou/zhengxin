package org.xpup.hafmis.sysloan.querystatistics.datareadyquery.assistantorg.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.xpup.common.util.BSUtils;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.sysloan.querystatistics.datareadyquery.assistantorg.bsinterface.IAssistantOrgBS;
import org.xpup.hafmis.sysloan.querystatistics.datareadyquery.assistantorg.form.AssistantorgQueryTbAF;

public class AssistantOrgInfoPrintAC extends Action {
  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    SecurityInfo securityInfo = (SecurityInfo) request.getSession()
    .getAttribute("SecurityInfo");
    String bizdate=(String)securityInfo.getUserInfo().getPlbizDate();
    request.setAttribute("bizDate", bizdate);
    IAssistantOrgBS assistantOrgBS = (IAssistantOrgBS) BSUtils
    .getBusinessService("assistantOrgBS", this, mapping.getModuleConfig());
    AssistantorgQueryTbAF assistantorgQueryTbAF = (AssistantorgQueryTbAF) form;
    String realName=assistantOrgBS.getUserRealName(securityInfo.getUserInfo().getUsername());
    request.setAttribute("assistantorgQueryTbAF", assistantorgQueryTbAF);
    request.setAttribute("username", realName);
    return mapping.findForward("assistantorgInfo_print");
  }
}
