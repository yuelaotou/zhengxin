package org.xpup.hafmis.syscollection.querystatistics.collfncomparison.action;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.xpup.common.util.Pagination;
import org.xpup.hafmis.syscollection.querystatistics.collfncomparison.form.CollFnComparisonEmpInfoAF;
/**
 * 职工信息查询的FindAction
 * Copy Right Information :
 * Goldsoft Project : 
 *
 * @Version : v1.0
 * @author : 付云峰
 * @Date : 2008-5-9 下午02:29:38
 */
public class CollFnComparisonEmpInfoFindAC extends Action {

  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    try {
      CollFnComparisonEmpInfoAF collFnComparisonEmpInfoAF = (CollFnComparisonEmpInfoAF)form;
      Pagination pagination = new Pagination(0, 10, 1, "a.id", "DESC", new HashMap(0));
      String paginationKey = getPaginationKey();
      pagination.getQueryCriterions().put("empId", collFnComparisonEmpInfoAF.getEmpId());
      pagination.getQueryCriterions().put("empName", collFnComparisonEmpInfoAF.getEmpName());
      pagination.getQueryCriterions().put("cardNum", collFnComparisonEmpInfoAF.getCardNum());
      
      //保留查询条件
      request.setAttribute("find_empid", collFnComparisonEmpInfoAF.getEmpId());
      request.setAttribute("find_empname", collFnComparisonEmpInfoAF.getEmpName());
      request.setAttribute("find_cardnum", collFnComparisonEmpInfoAF.getCardNum());
      
      request.getSession().setAttribute(paginationKey, pagination);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return mapping.findForward("to_collFnComparisonEmpInfoShowAC");
  }
  protected String getPaginationKey() {
    return CollFnComparisonEmpInfoShowAC.PAGINATION_KEY;
  }
}
