package org.xpup.hafmis.syscollection.accountmng.accountopen.action;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.xpup.common.exception.BusinessException;
import org.xpup.common.util.BSUtils;
import org.xpup.common.util.Pagination;
import org.xpup.common.util.PaginationUtils;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.syscollection.accountmng.accountopen.bsinterface.IOrgOpenAccountBS;
import org.xpup.hafmis.syscollection.accountmng.accountopen.form.EmpkhAF;
import org.xpup.hafmis.syscollection.common.domain.entity.Emp;
import org.xpup.hafmis.syscollection.common.domain.entity.Org;

public class EmpOpenShowAC extends Action {

  public static final String PAGINATION_KEY = "org.xpup.hafmis.syscollection.accountmng.accountopen.action.EmpOpenShowAC";

  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {

    String paginationKey = getPaginationKey();
    Pagination pagination = getPagination(paginationKey, request);
    modifyPagination(pagination);
    PaginationUtils.updatePagination(pagination, request);
    ActionMessages messages = new ActionMessages();
    try {
      IOrgOpenAccountBS orgOpenAccountBS = (IOrgOpenAccountBS) BSUtils
          .getBusinessService("orgOpenAccountBS", this, mapping
              .getModuleConfig());
      // 取得权限
      SecurityInfo securityInfo = (SecurityInfo) request.getSession()
          .getAttribute("SecurityInfo");
      // 多传入个参数
      EmpkhAF af = orgOpenAccountBS.findEmployee(pagination, securityInfo);

      request.setAttribute("employees", af.getList());
      String ispickupType = (String) pagination.getQueryCriterions().get(
          "istype");
      af.setIsType(ispickupType);
      request.setAttribute("empkhAF", af);
      af.reset();
    } catch (Exception e) {
      e.printStackTrace();
    }
    return mapping.findForward(getForword());
  }

  protected String getForword() {
    return "employees";
  }

  protected void modifyPagination(Pagination pagination) {
  }

  private Pagination getPagination(String paginationKey,
      HttpServletRequest request) {

    Pagination pagination = (Pagination) request.getSession().getAttribute(
        paginationKey);

    if (pagination == null) {
      HashMap m = new HashMap();
      m.put("orgId", new Integer(String.valueOf(request.getSession()
          .getAttribute("org.id"))));
      pagination = new Pagination(0, 10, 1, "emp.empId", "DESC", m);
      request.getSession().setAttribute(paginationKey, pagination);

    }
    return pagination;
  }

  protected String getPaginationKey() {
    return PAGINATION_KEY;
  }
}
