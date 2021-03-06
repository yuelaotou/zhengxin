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
import org.xpup.hafmis.syscollection.paymng.agent.form.AgentImpAF;

/**
 * Copy Right Information : 显示代扣导入Action Goldsoft Project : AgentImpShowAC
 * 
 * @Version : v1.0
 * @author : 付云峰
 * @Date : 2007.12.17
 */
public class AgentImpShowAC extends Action {

  public static final String PAGINATION_KEY = "org.xpup.hafmis.syscollection.paymng.agent.action.AgentImpShowAC";

  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    AgentImpAF agentImpAF = new AgentImpAF();
    Object[] obj = null;
    try {
      SecurityInfo securityInfo = (SecurityInfo) request.getSession()
          .getAttribute("SecurityInfo");

      IAgentBS agentBS = (IAgentBS) BSUtils.getBusinessService("agentBS", this,
          mapping.getModuleConfig());

      String paginationKey = getPaginationKey();
      Pagination pagination = getPagination(paginationKey, request);
      PaginationUtils.updatePagination(pagination, request);
      // 得到查询列表需要的批号
      String agentDetailId = (String) request.getSession().getAttribute("agentDetailId");
      String noteNum = (String) request.getSession().getAttribute("noteNum");
      if (agentDetailId!=null) {
        obj = agentBS.findAgentInfo(pagination, securityInfo, agentDetailId);
        agentImpAF.setList((List)obj[0]);
        agentImpAF.setSumAgentOrgPay((BigDecimal)obj[1]);
        agentImpAF.setSumAgentEmpPay((BigDecimal)obj[2]);
        agentImpAF.setSumAgentEmpSalary((BigDecimal)obj[3]);
        agentImpAF.setOrgCount((Integer)obj[4]);
      }
      agentImpAF.setNoteNum(noteNum);
      agentImpAF.setId(agentDetailId);
      request.setAttribute("agentImpAF", agentImpAF);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return mapping.findForward("to_agentimp_show");
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
