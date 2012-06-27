package org.xpup.hafmis.syscollection.paymng.agent.action;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.LookupDispatchAction;
import org.xpup.common.util.BSUtils;
import org.xpup.hafmis.common.form.IdAF;
import org.xpup.hafmis.syscollection.paymng.agent.bsinterface.IAgentBS;

public class AgentEmppopMaintainAC extends LookupDispatchAction {

  protected Map getKeyMethodMap() {
    Map map = new HashMap();
    map.put("button.delete", "delete");
    map.put("button.back", "back");
    return map;
  }
  /**
   * 删除方法
   * @param mapping
   * @param form
   * @param request
   * @param response
   * @return
   * @throws Exception
   */
  public ActionForward delete(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    try {
      IdAF idAF = (IdAF)form;
      String empAgentId = idAF.getId().toString();
      IAgentBS agentBS = (IAgentBS) BSUtils.getBusinessService("agentBS", this,
          mapping.getModuleConfig());
      agentBS.deleteEmpAgentInfo(empAgentId);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return mapping.findForward("to_agentEmppopShowAC");
  }
  /**
   * 返回方法
   * @param mapping
   * @param form
   * @param request
   * @param response
   * @return
   * @throws Exception
   */
  public ActionForward back(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    String returnType = null;
    returnType = (String) request.getSession().getAttribute("org_returnType");
    if (returnType!=null) {
      return mapping.findForward("to_agentImpShowAC");
    }else{
      return mapping.findForward("to_agentOrgpopShowAC");
    }
  }
}
