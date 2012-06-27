/*
 * Generated by MyEclipse Struts
 * Template path: templates/java/JavaClass.vtl
 */
package org.xpup.hafmis.syscollection.paymng.agent.action;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.xpup.common.util.BSUtils;
import org.xpup.common.util.Pagination;
import org.xpup.common.util.PaginationUtils;
import org.xpup.hafmis.syscollection.paymng.agent.bsinterface.IAgentBS;
import org.xpup.hafmis.syscollection.paymng.agent.form.AgentInfoQueryTcAF;

/** 
 * MyEclipse Struts
 * Creation date: 12-20-2007
 * 
 * XDoclet definition:
 * @struts.action path="/agentInfoQueryTcShowAC" name="idAF" scope="request" validate="true"
 */
public class AgentInfoQueryTcShowAC extends Action {
	/*
	 * Generated Methods
	 */

  public static final String PAGINATION_KEY = "org.xpup.hafmis.syscollection.paymng.agent.action.AgentInfoQueryTcShowAC";
	/** 
	 * Method execute
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 */
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		//IdAF idAF = (IdAF) form;// TODO Auto-generated method stub
    try {
      //把 orgId 传过来放在SESSION 里
      if(request.getAttribute("orgId_num") != null){
        HttpSession session = request.getSession();
        session.setAttribute("orgId_num_session", (String)request.getAttribute("orgId_num"));
      }
      
      IAgentBS agentBS = (IAgentBS) BSUtils.getBusinessService("agentBS", this,
          mapping.getModuleConfig());
      Pagination pagination = getPagination(PAGINATION_KEY, request,(String)request.getAttribute("orgId_num"));
      PaginationUtils.updatePagination(pagination, request);
      //TC  集合
      List list = agentBS.queryAgentInfoTcList(pagination);
      //TC  数量
      int count = agentBS.queryAgentInfoTcListCount(pagination);
      
      pagination.setNrOfElements(count);
      AgentInfoQueryTcAF agentInfoQueryTcAF = new AgentInfoQueryTcAF();
      agentInfoQueryTcAF.setList(list);
      request.setAttribute("agentInfoQueryTcAF", agentInfoQueryTcAF);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return mapping.findForward("to_agentInfoQueryTc_show");
	}
  
  private Pagination getPagination(String paginationKey,
      HttpServletRequest request,String orgId) {
    Pagination pagination = (Pagination) request.getSession().getAttribute(
        paginationKey);
    if (pagination == null) {
      pagination = new Pagination(0, 10, 1, "a303.emp_id", "ASC", new HashMap(0));
      pagination.getQueryCriterions().put("orgId",orgId);
      pagination.getQueryCriterions().put("payId",request.getSession().getAttribute("payId_num_session").toString().trim());
      request.getSession().setAttribute(paginationKey, pagination);
    }
    return pagination;
  }
}