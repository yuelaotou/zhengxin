package org.xpup.hafmis.syscollection.querystatistics.baseinfosearch.empbaseinfo.action;

import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.xpup.common.util.BSUtils;
import org.xpup.common.util.Pagination;
import org.xpup.common.util.PaginationUtils;
import org.xpup.hafmis.common.util.BusiConst;
import org.xpup.hafmis.common.util.BusiTools;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.syscollection.querystatistics.baseinfosearch.empbaseinfo.bsinterface.IEmployeeInfoListBS;
import org.xpup.hafmis.syscollection.querystatistics.baseinfosearch.empbaseinfo.form.EmployeeInfoSearchAF;

public class EmployeeInfoListShowAC extends Action {
  public static final String PAGINATION_KEY = "org.xpup.hafmis.syscollection.querystatistics.baseinfosearch.empbaseinfo.action.EmployeeInfoListShowAC";

  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response) {
    try {
      EmployeeInfoSearchAF af = new EmployeeInfoSearchAF();
      Pagination pag = (Pagination) request.getSession().getAttribute(
          PAGINATION_KEY);
      if (pag == null) {
        pag = getPagination(PAGINATION_KEY, request);
      } else {
        IEmployeeInfoListBS pbs = (IEmployeeInfoListBS) BSUtils
            .getBusinessService("employeeInfoListBS", this, mapping
                .getModuleConfig());
        PaginationUtils.updatePagination(pag, request);
        SecurityInfo sInfo = (SecurityInfo) request.getSession().getAttribute(
            "SecurityInfo");
        af = pbs.getEmployeeInfoList(pag, sInfo);
      }
      af.setSex(BusiTools.listBusiProperty(BusiConst.SEX));
      Map m = BusiTools.listBusiProperty(BusiConst.OLDPAYMENTSTATE);
      m.remove(new Integer("6"));
      af.setPayState(m);
      request.setAttribute("infoList", af);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return new ActionForward("/employee_info_list.jsp");
  }

  private Pagination getPagination(String paginationKey,
      HttpServletRequest request) {
    Pagination pagination = (Pagination) request.getSession().getAttribute(
        paginationKey);
    if (pagination == null) {
      pagination = new Pagination(0, 10, 1, "a2.id", "DESC", new HashMap(0));
      request.getSession().setAttribute(paginationKey, pagination);
    }
    return pagination;
  }
}