package org.xpup.hafmis.syscollection.accountmng.accountopen.action;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.xpup.common.util.Pagination;
import org.xpup.hafmis.syscollection.accountmng.accountopen.form.OrgkhCriteronsAF;

public class OrgOpenFindListAC extends Action {

  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {

    OrgkhCriteronsAF f = (OrgkhCriteronsAF) form;
    HashMap criterions = makeCriterionsMap(f);
    Pagination pagination = new Pagination(0, 10, 1, "orgs.id", "DESC",criterions);
    String paginationKey = getPaginationKey();
    request.getSession().setAttribute(paginationKey, pagination);
    modifyPagination(pagination);
    
    return mapping.findForward(getForword());
  }

  protected String getForword() {
    return "show_organizations";
  }

  protected String getPaginationKey() {
    return "org.xpup.hafmis.syscollection.accountmng.accountopen.action.OrgOpenShowListAC";
  }

  protected HashMap makeCriterionsMap(OrgkhCriteronsAF form) {
    HashMap m = new HashMap();
    String id = form.getOrgid();
    if (!(id == null || "".equals(id.trim()))) {
      try {
        int iid = Integer.parseInt(id.trim());
        m.put("id", new Integer(iid));
      } catch (NumberFormatException e) {
        m.put("id", new Integer(-10000));
      }
    }

    String name = form.getName();
    if (!(name == null || name.trim().length() == 0))
      m.put("name", form.getName().trim());

    String status = form.getStatus();
    if (status != null) {
      try {

        m.put("status", new Integer(status.toString()));
      } catch (NumberFormatException e) {
        // 付云峰修改：条件查询时无法得到status为空的值，所以去掉此异常处理。
        // m.put("status", new Integer(-10000));
      }
    }

    String payMode = form.getPayMode();
    if (payMode != null) {
      try {

        m.put("payMode", new Integer(payMode.toString()));
      } catch (NumberFormatException e) {
        // 付云峰修改：条件查询时无法得到payMode为空的值，所以去掉此异常处理。
        // m.put("payMode", new Integer(-10000));
      }
    }
    
    String startdate = form.getStartdate();
    String enddate = form.getEnddate();
    
    if (startdate != null && enddate != null) {
      try {

        m.put("startdate", startdate.toString().trim());
        m.put("enddate", enddate.toString().trim());
        
      } catch (NumberFormatException e) {
        // 付云峰修改：条件查询时无法得到payMode为空的值，所以去掉此异常处理。
        // m.put("payMode", new Integer(-10000));
      }
    }
    
    // 付云峰修改：由此条件来判断是否查出所有状态的单位
    String temp_Type = "b";
    m.put("temp_Type", temp_Type);
    return m;
  }

  protected void modifyPagination(Pagination pagination) {
  }
}
