package org.xpup.hafmis.syscollection.querystatistics.collfncomparison.action;

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
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.syscollection.querystatistics.collfncomparison.bsinterface.ICollFnComparisonBS;
import org.xpup.hafmis.syscollection.querystatistics.collfncomparison.form.CollFnComparisonEmpAccountAF;
/**
 * 职工账查询的FindAction
 * Copy Right Information :
 * Goldsoft Project : 
 *
 * @Version : v1.0
 * @author : 付云峰
 * @Date : 2008-5-10 上午09:15:47
 */
public class CollFnComparisonEmpAccountFindAC extends Action {

  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    try {
      CollFnComparisonEmpAccountAF collFnComparisonEmpAccountAF = (CollFnComparisonEmpAccountAF) form;

      Pagination pagination = new Pagination(0, 10, 1,
          "a1.org_id,a1.emp_id,a1.sett_date,a1.id", "ASC", new HashMap(
              0));
      String paginationKey = getPaginationKey();
      pagination.getQueryCriterions().put("orgId",
          collFnComparisonEmpAccountAF.getOrgId());
      pagination.getQueryCriterions().put("empId",
          collFnComparisonEmpAccountAF.getEmpId());
      pagination.getQueryCriterions().put("orgName",
          collFnComparisonEmpAccountAF.getOrgName());
      pagination.getQueryCriterions().put("empName",
          collFnComparisonEmpAccountAF.getEmpName());
      pagination.getQueryCriterions().put("cardNum",
          collFnComparisonEmpAccountAF.getCardNum());
      pagination.getQueryCriterions().put("timeSt",
          collFnComparisonEmpAccountAF.getTimeSt());
      pagination.getQueryCriterions().put("timeEnd",
          collFnComparisonEmpAccountAF.getTimeEnd());
      
      ICollFnComparisonBS collFnComparisonBS = (ICollFnComparisonBS) BSUtils
      .getBusinessService("collFnComparisonBS", this, mapping
          .getModuleConfig());
      SecurityInfo securityInfo = (SecurityInfo) request.getSession()
      .getAttribute("SecurityInfo");
      // 当点击查询按钮时将从新得到余额的列表
      List list = collFnComparisonBS.queryqcye(pagination, securityInfo);
      pagination.getQueryCriterions().put("qcyelist", list);

      request.setAttribute("find_orgId", collFnComparisonEmpAccountAF
          .getOrgId());
      request.setAttribute("find_empId", collFnComparisonEmpAccountAF
          .getEmpId());
      request.setAttribute("find_orgName", collFnComparisonEmpAccountAF
          .getOrgName());
      request.setAttribute("find_empName", collFnComparisonEmpAccountAF
          .getEmpName());
      request.setAttribute("find_cardNum", collFnComparisonEmpAccountAF
          .getCardNum());
      request.setAttribute("find_timeSt", collFnComparisonEmpAccountAF
          .getTimeSt());
      request.setAttribute("find_timeEnd", collFnComparisonEmpAccountAF
          .getTimeEnd());

      request.getSession().setAttribute(paginationKey, pagination);
      request.getSession().setAttribute("EmpAccount_empId", collFnComparisonEmpAccountAF.getEmpId());
      request.getSession().setAttribute("EmpAccount_empName", collFnComparisonEmpAccountAF.getEmpName());
      request.getSession().setAttribute("CollFnComparison_orgId",collFnComparisonEmpAccountAF.getOrgId());
      request.getSession().setAttribute("CollFnComparison_orgName",collFnComparisonEmpAccountAF.getOrgName());
      request.setAttribute("qcyelist", list);

    } catch (Exception e) {
      e.printStackTrace();
    }
    return mapping.findForward("to_collFnComparisonEmpAccountShowAC");
  }
  protected String getPaginationKey() {
    return CollFnComparisonEmpAccountShowAC.PAGINATION_KEY;
  }
}
