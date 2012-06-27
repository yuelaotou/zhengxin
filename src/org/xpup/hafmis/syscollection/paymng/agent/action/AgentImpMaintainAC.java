package org.xpup.hafmis.syscollection.paymng.agent.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.actions.LookupDispatchAction;
import org.xpup.common.util.BSUtils;
import org.xpup.hafmis.common.form.IdAF;
import org.xpup.hafmis.syscollection.paymng.agent.bsinterface.IAgentBS;
/**
 * Copy Right Information : 代扣导入MaintainAction Goldsoft Project : AgentImpMaintainAC
 * 
 * @Version : v1.0
 * @author : 付云峰
 * @Date : 2007.12.19
 */
public class AgentImpMaintainAC extends LookupDispatchAction {

  protected Map getKeyMethodMap() {
    Map map = new HashMap();
//    map.put("button.delete", "delete");
//    map.put("button.deleteall", "deleteAll");
//    map.put("button.export", "export");
   
    map.put("button.delete", "deleteAll");
    map.put("button.export", "export");
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
      String temp_str = idAF.getId().toString();
      String[] str = temp_str.split(",");
      String agentDetailId = str[0];
//      String orgAgentId = str[2];
      IAgentBS agentBS = (IAgentBS) BSUtils.getBusinessService("agentBS", this,
          mapping.getModuleConfig());
//      agentBS.deleteAgentInfo(agentDetailId, orgAgentId);
      agentBS.deleteAgentInfo(agentDetailId, agentDetailId);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return mapping.findForward("to_agentImpShowAC");
  }
  /**
   * 全部删除方法
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
      IdAF idAF = (IdAF)form;
      String temp_str = idAF.getId().toString();
      String[] str = temp_str.split(",");
      String agentDetailId = str[0];
      String orgAgentNum = str[1];
      IAgentBS agentBS = (IAgentBS) BSUtils.getBusinessService("agentBS", this,
          mapping.getModuleConfig());
      agentBS.deleteAllAgentInfo(agentDetailId, orgAgentNum);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return mapping.findForward("to_agentImpShowAC");
  }
  /**
   * 代扣导出模版
   * @param mapping
   * @param form
   * @param request
   * @param response
   * @return
   * @throws Exception
   */
  public ActionForward export(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    ActionMessages messages = null;
    try {
      List expList = new ArrayList();
        messages = new ActionMessages();
        messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("导出成功！",
            false));
        saveErrors(request, messages);
        request.getSession().setAttribute("explist", expList);
        response.sendRedirect(request.getContextPath()
            + "/ExportServlet?ISCANSHU=false&EXP_NAME=agent_exp");
    } catch (Exception e) {
      e.printStackTrace();
    }
    return null;
  }
}
