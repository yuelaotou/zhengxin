package org.xpup.hafmis.syscollection.paymng.agent.action;

import java.math.BigDecimal;
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

public class AgentOrgpopShowAC extends Action {

  public static final String PAGINATION_KEY = "org.xpup.hafmis.syscollection.paymng.agent.action.AgentOrgpopShowAC";

  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    AgentChgAF agentChgAF = new AgentChgAF();
    try {
      String agentHeadId = request.getParameter("agentHeadId");
      String agentStatus = request.getParameter("agentStatus");
      request.getSession().setAttribute("org_agentStatus", agentStatus);
      if (request.getParameter("agentHeadId")!=null) {
        // 将头表id放入session以免在进入Find后无法得到
        request.getSession().setAttribute("org_agentHeadId", agentHeadId);
      }
      SecurityInfo securityInfo = (SecurityInfo) request.getSession()
          .getAttribute("SecurityInfo");

      IAgentBS agentBS = (IAgentBS) BSUtils.getBusinessService("agentBS", this,
          mapping.getModuleConfig());
      
      String paginationKey = getPaginationKey();
      Pagination pagination = getPagination(paginationKey, request);
      PaginationUtils.updatePagination(pagination, request);
      agentHeadId = (String) request.getSession().getAttribute("org_agentHeadId");
      Object[] obj = agentBS.findAgentOrgInfoList(pagination, agentHeadId);
      List list = (List) obj[0];
      
      agentChgAF.setList(list);
      agentChgAF.setSumAgentOrgPay((BigDecimal)obj[1]);
      agentChgAF.setSumAgentEmpPay((BigDecimal)obj[2]);
      agentChgAF.setSumAgentEmpSalary((BigDecimal)obj[3]);
      agentChgAF.setOrgCount((Integer)obj[4]);
      agentChgAF.setAgentHeadId(agentHeadId);
      if (agentStatus!=null&&!agentStatus.equals("1")) {
        agentStatus = (String) request.getSession().getAttribute("org_agentStatus");
        agentChgAF.setAgentStatus("1");
      }
      // 用来全部删除
      List orgCountList = (List)obj[5];
      request.setAttribute("orgCountList", orgCountList);
    } catch (Exception e) {
      e.printStackTrace();
    }
    request.setAttribute("agentChgAF", agentChgAF);
    return mapping.findForward("to_agentorgpop_show");
  }

  private Pagination getPagination(String paginationKey,
      HttpServletRequest request) {
    Pagination pagination = (Pagination) request.getSession().getAttribute(
        paginationKey);
    if (pagination == null) {
      pagination = new Pagination(0, 10, 1, "e002.org_agent_id", "DESC",
          new HashMap(0));
      request.getSession().setAttribute(paginationKey, pagination);
    }
    return pagination;
  }

  protected String getPaginationKey() {
    return PAGINATION_KEY;
  }
}
