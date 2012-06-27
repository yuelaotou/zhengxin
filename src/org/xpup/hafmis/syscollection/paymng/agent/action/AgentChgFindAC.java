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

/**
 * Copy Right Information : ´ú¿Û±ä¸üFindAction Goldsoft Project : AgentChgFindAC
 * 
 * @Version : v1.0
 * @author : ¸¶ÔÆ·å
 * @Date : 2007.12.19
 */
public class AgentChgFindAC extends Action {

  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    try {
      AgentChgAF agentChgAF = (AgentChgAF) form;
      
      Pagination pagination = new Pagination(0, 10, 1, "e001.id",
          "DESC", new HashMap(0));
      pagination.getQueryCriterions().put("agentHeadId",
          agentChgAF.getAgentHeadId());
      pagination.getQueryCriterions().put("agentYearMonth",
          agentChgAF.getAgentYearMonth());
      String paginationKey = getPaginationKey();
      request.getSession().setAttribute(paginationKey, pagination);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return mapping.findForward("to_agentChgShowAC");
  }

  protected String getPaginationKey() {
    return AgentChgShowAC.PAGINATION_KEY;
  }

}
