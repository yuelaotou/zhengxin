package org.xpup.hafmis.syscollection.paymng.agent.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.xpup.common.util.BSUtils;
import org.xpup.common.util.Pagination;
import org.xpup.common.util.PaginationUtils;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.syscollection.paymng.agent.bsinterface.IAgentBS;
import org.xpup.hafmis.syscollection.paymng.agent.form.AgentChgAF;

public class AgentEmppopShowAC extends Action {

  public static final String PAGINATION_KEY = "org.xpup.hafmis.syscollection.paymng.agent.action.AgentEmppopShowAC";

  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    AgentChgAF agentChgAF = new AgentChgAF();
    String returnType = null;
    try {
      String orgAgentId = request.getParameter("orgAgentId");
      String orgId = request.getParameter("orgId");
      String payMode = "";
      returnType = request.getParameter("returnType");
      if (request.getParameter("payMode")!=null) {
         payMode = request.getParameter("payMode");
         request.getSession().setAttribute("imp_payMode", payMode);
      }else{
        payMode = (String) request.getSession().getAttribute("imp_payMode");
      }
      if (returnType!=null) {
        request.getSession().setAttribute("org_returnType", returnType);
      }
      if (request.getParameter("orgAgentId") != null) {
        request.getSession().setAttribute("emp_orgAgentId", orgAgentId);
        request.getSession().setAttribute("emp_orgId", orgId);
      }
      SecurityInfo securityInfo = (SecurityInfo) request.getSession()
          .getAttribute("SecurityInfo");

      IAgentBS agentBS = (IAgentBS) BSUtils.getBusinessService("agentBS", this,
          mapping.getModuleConfig());

      String paginationKey = getPaginationKey();
      Pagination pagination = getPagination(paginationKey, request);
      PaginationUtils.updatePagination(pagination, request);
      orgAgentId = (String) request.getSession().getAttribute("emp_orgAgentId");
      //ÐÞ¸Ä
      String agentHeadId = (String) request.getSession().getAttribute("org_agentHeadId");
      //
      orgId = (String) request.getSession().getAttribute("emp_orgId");
//      Object[] obj = agentBS
//          .findAgentEmpInfoList(pagination, orgAgentId, orgId, payMode);
      Object[] obj = agentBS
      .findAgentEmpInfoList(pagination, agentHeadId, orgId, payMode);
      List emplist=new ArrayList();
      emplist = (List) obj[0];
      if(emplist.size()==0){
        Object[] objs = agentBS.findAgentEmpInfoList(pagination, orgAgentId, orgId, payMode);
        emplist = (List) objs[0];
      }
      agentChgAF.setEmplist(emplist);
      if (request.getSession().getAttribute("org_agentStatus") != null
          && !((String) request.getSession().getAttribute("org_agentStatus"))
              .equals("1")) {
        agentChgAF.setAgentStatus("1");
      }
      returnType = (String) request.getSession().getAttribute("org_returnType");
      if (returnType != null) {
        agentChgAF.setAgentStatus("1");
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    request.setAttribute("agentChgAF", agentChgAF);
    return mapping.findForward("to_agentemppop_show");
  }

  private Pagination getPagination(String paginationKey,
      HttpServletRequest request) {
    Pagination pagination = (Pagination) request.getSession().getAttribute(
        paginationKey);
    if (pagination == null) {
      pagination = new Pagination(0, 10, 1, "e003.emp_agent_id", "DESC",
          new HashMap(0));
      request.getSession().setAttribute(paginationKey, pagination);
    }
    return pagination;
  }

  protected String getPaginationKey() {
    return PAGINATION_KEY;
  }
}
