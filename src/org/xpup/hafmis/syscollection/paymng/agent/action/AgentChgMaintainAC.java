package org.xpup.hafmis.syscollection.paymng.agent.action;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.actions.LookupDispatchAction;
import org.xpup.common.exception.BusinessException;
import org.xpup.common.util.BSUtils;
import org.xpup.hafmis.common.form.IdAF;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.syscollection.paymng.agent.bsinterface.IAgentBS;

public class AgentChgMaintainAC extends LookupDispatchAction {

  protected Map getKeyMethodMap() {
    Map map = new HashMap();
    map.put("button.createchange", "createChange");
    map.put("button.backchange", "backChange");
    map.put("button.delete", "delete");
    return map;
  }

  /**
   * 删除的方法
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
      String agentHeadId = idAF.getId().toString();
      IAgentBS agentBS = (IAgentBS) BSUtils.getBusinessService("agentBS", this,
          mapping.getModuleConfig());
      agentBS.deleteAllAgentInfo(agentHeadId, "");
    } catch (Exception e) {
      e.printStackTrace();
    }
    return mapping.findForward("to_agentChgShowAC");
  }

  /**
   * 生成变更的方法
   * 
   * @param mapping
   * @param form
   * @param request
   * @param response
   * @return
   * @throws Exception
   */
  public ActionForward createChange(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    try {
      IdAF idAF = (IdAF) form;
      String agentHeadId = idAF.getId().toString();
      SecurityInfo securityInfo = (SecurityInfo) request.getSession()
          .getAttribute("SecurityInfo");

      IAgentBS agentBS = (IAgentBS) BSUtils.getBusinessService("agentBS", this,
          mapping.getModuleConfig());
      agentBS.createAgentChange(agentHeadId, securityInfo);
    } catch (BusinessException bex) {
      bex.printStackTrace();
      ActionMessages messages = new ActionMessages();
      messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(bex
          .getLocalizedMessage(), false));
      saveErrors(request, messages);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return mapping.findForward("to_agentChgShowAC");
  }

  /**
   * 撤销变更的方法
   * 
   * @param mapping
   * @param form
   * @param request
   * @param response
   * @return
   * @throws Exception
   */
  public ActionForward backChange(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    try {
      IdAF idAF = (IdAF) form;
      String agentHeadId = idAF.getId().toString();
      SecurityInfo securityInfo = (SecurityInfo) request.getSession()
          .getAttribute("SecurityInfo");
      IAgentBS agentBS = (IAgentBS) BSUtils.getBusinessService("agentBS", this,
          mapping.getModuleConfig());
      agentBS.backAgentChagneInfo(agentHeadId,securityInfo);
    } catch (BusinessException bex) {
      bex.printStackTrace();
      ActionMessages messages = new ActionMessages();
      messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(bex
          .getLocalizedMessage(), false));
      saveErrors(request, messages);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return mapping.findForward("to_agentChgShowAC");
  }
}
