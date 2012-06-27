package org.xpup.hafmis.syscollection.querystatistics.baseinfosearch.empbaseinfo.action;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.xpup.common.util.Pagination;
import org.xpup.hafmis.syscollection.querystatistics.baseinfosearch.empbaseinfo.dto.EmployeeInfoSearchDTO;
import org.xpup.hafmis.syscollection.querystatistics.baseinfosearch.empbaseinfo.form.EmployeeInfoSearchAF;
import org.xpup.hafmis.sysloan.querystatistics.loanaccountquery.principaltotalacc.action.PrincipalTaShowAC;

public class EmployeeInfoSearchAC extends Action {
  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response) {
    try {
      EmployeeInfoSearchAF af = (EmployeeInfoSearchAF) form;
      EmployeeInfoSearchDTO dto = af.getDto();
      Pagination pagination = new Pagination(0, 10, 1, "a2.id", "ASC",
          new HashMap());
      String paginationKey = getPaginationKey();
      pagination.getQueryCriterions().put("search", dto);
      request.getSession().setAttribute(paginationKey, pagination);
    } catch (Exception s) {
      s.printStackTrace();
    }
    return new ActionForward("/employeeInfoListShowAC.do");
  }
  protected String getPaginationKey() {
    return EmployeeInfoListShowAC.PAGINATION_KEY;
  }
}