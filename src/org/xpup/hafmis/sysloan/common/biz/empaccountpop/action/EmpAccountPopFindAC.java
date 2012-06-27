/**
 * Copy Right Information   : Goldsoft 
 * Project                  : EmpAccountPopFindAC
 * @Version                 : 1.0
 * @author                  : wangy
 * 生成日期                   :2007-11-02
 * 修改日期                   :2007-11-13增加查询条件：职工编号、单位编号
 **/
package org.xpup.hafmis.sysloan.common.biz.empaccountpop.action;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import org.xpup.common.util.Pagination;
import org.xpup.hafmis.sysloan.common.biz.empaccountpop.form.EmpAccountPopAF;

public class EmpAccountPopFindAC extends Action {

  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response) {
    try {
      EmpAccountPopAF f = (EmpAccountPopAF) form;
      HashMap criterions = makeCriterionsMap(f);
      Pagination pagination = new Pagination(0, f.getPageSize(), 1, "a101.sett_date",
          "DESC", criterions);
      request.getSession().setAttribute(EmpAccountPopShowAC.PAGINATION_KEY,
          pagination);
      f.reset(mapping, request);
    } catch (Exception ex) {
      ex.printStackTrace();
    }
    request.setAttribute("yg", "yg");
    return mapping.findForward("showEmpAccountPopListAC");
  }

  protected HashMap makeCriterionsMap(EmpAccountPopAF form) {
    HashMap m = new HashMap();
//    String empId = form.getEmpId().trim();
//    if (!(empId == null || "".equals(empId)))
//      m.put("empId", empId);
//    String orgId = form.getOrgId().trim();
//    if (!(orgId == null || "".equals(orgId)))
//      m.put("orgId", orgId);
    String bizType = form.getBizType().trim();
    if (!(bizType == null || "".equals(bizType)))
      m.put("bizType", bizType);
    String settDateA = form.getSettDateA().trim();
    if (!(settDateA == null || "".equals(settDateA)))
      m.put("settDateA", settDateA);
    String settDateB = form.getSettDateB().trim();
    if (!(settDateB == null || "".equals(settDateB)))
      m.put("settDateB", settDateB);
    return m;
  }
}
