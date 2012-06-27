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

public class AgentEmppopFindAC  extends Action {

  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    try {
      AgentChgAF af = (AgentChgAF) form;
      Pagination pagination = new Pagination(0, 10, 1, "e003.emp_agent_id",
          "DESC", new HashMap(0));
      String empid=af.getAgentEmppopId();
      String empname=af.getAgentEmppopname();
      String koucode=af.getAgentEmppopkouCode();  
      String cardid=af.getAgentEmppopCardID();  

      if (!(empid == null || "".equals(empid))) {
        pagination.getQueryCriterions().put("emppopid",empid);
      }
      if (!(empname == null || "".equals(empname))) {
        pagination.getQueryCriterions().put("emppopname",empname);
      }
      if (!(koucode == null || "".equals(koucode))) {
        pagination.getQueryCriterions().put("emppopkoucode",koucode);
      }
      if (!(cardid == null || "".equals(cardid))) {
        pagination.getQueryCriterions().put("emppopcardid",cardid);
      }
      
      String paginationKey = getPaginationKey();
      request.getSession().setAttribute(paginationKey, pagination);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return mapping.findForward("to_agentEmppopShowAC");
  }

  protected String getPaginationKey() {
    return AgentEmppopShowAC.PAGINATION_KEY;
  }

}