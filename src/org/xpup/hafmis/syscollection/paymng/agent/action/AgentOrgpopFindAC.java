package org.xpup.hafmis.syscollection.paymng.agent.action;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.xpup.common.util.Pagination;
import org.xpup.hafmis.syscollection.paymng.agent.form.AgentChgAF;

public class AgentOrgpopFindAC extends Action {

  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    try {
      AgentChgAF agentChgAF = (AgentChgAF) form;

      Pagination pagination = new Pagination(0, 10, 1, "e002.org_agent_id", "DESC",
          new HashMap(0));
      pagination.getQueryCriterions().put("orgId",
          agentChgAF.getAgentOrgpopId());
      pagination.getQueryCriterions().put("orgAgentNum",
          agentChgAF.getAgentOrgpopkouCode());
      String paginationKey = getPaginationKey();
      request.getSession().setAttribute(paginationKey, pagination);

    } catch (Exception e) {
      e.printStackTrace();
    }

    return mapping.findForward("to_agentOrgpopShowAC");
  }

  protected String getPaginationKey() {
    return AgentOrgpopShowAC.PAGINATION_KEY;
  }
}
