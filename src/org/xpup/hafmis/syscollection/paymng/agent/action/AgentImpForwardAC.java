package org.xpup.hafmis.syscollection.paymng.agent.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
/**
 * Copy Right Information : ´ú¿Ûµ¼ÈëForwardAction Goldsoft Project : AgentImpForwardAC
 * 
 * @Version : v1.0
 * @author : ¸¶ÔÆ·å
 * @Date : 2007.12.26
 */
public class AgentImpForwardAC extends Action {

  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    try {
      request.getSession().removeAttribute(AgentImpShowAC.PAGINATION_KEY);
      request.getSession().removeAttribute("agentDetailId");
    } catch (Exception e) {
      e.printStackTrace();
    }
    return mapping.findForward("to_agentImpShowAC");
  }

}
