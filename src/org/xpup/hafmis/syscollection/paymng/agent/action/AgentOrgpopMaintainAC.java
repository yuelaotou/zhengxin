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

public class AgentOrgpopMaintainAC extends LookupDispatchAction {

  protected Map getKeyMethodMap() {
    Map map = new HashMap();
    map.put("button.delete", "delete");
    map.put("button.deleteall", "deleteAll");
    map.put("button.back", "back");
    return map;
  }

  /**
   * 删除方法
   * 
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
      IdAF idAF = (IdAF) form;
      String temp_str = idAF.getId().toString();
      
      String[] str = temp_str.split(",");
      String orgAgentId = str[0];
//      String agentDetailId = str[1];
      IAgentBS agentBS = (IAgentBS) BSUtils.getBusinessService("agentBS", this,
          mapping.getModuleConfig());
      agentBS.deleteAgentInfo(orgAgentId, orgAgentId);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return mapping.findForward("to_agentOrgpopShowAC");

  }

  /**
   * 全部删除方法
   * 
   * @param mapping
   * @param form
   * @param request
   * @param response
   * @return
   * @throws Exception
   */
  public ActionForward deleteAll(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    try {
      // 得到清册id
      String agentHeadId = (String) request.getSession().getAttribute("org_agentHeadId");
      IAgentBS agentBS = (IAgentBS) BSUtils.getBusinessService("agentBS", this,
          mapping.getModuleConfig());
      agentBS.deleteAllAgentInfo(agentHeadId, "");
    } catch (Exception e) {
      e.printStackTrace();
    }
    return mapping.findForward("to_agentOrgpopShowAC");
  }

  /**
   * 返回的方法
   * 
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
    return mapping.findForward("to_agentChgShowAC");
  }

}
