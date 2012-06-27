package org.xpup.hafmis.syscollection.querystatistics.paymentpickstatistics.orgpaymentstatistics.action;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import org.xpup.common.util.Pagination;
import org.xpup.hafmis.syscollection.querystatistics.paymentpickstatistics.orgpaymentstatistics.form.EmployeesAF;

public class EmpListFindAC extends Action {

    public ActionForward execute(ActionMapping mapping, ActionForm form,
        HttpServletRequest request, HttpServletResponse response)
        throws Exception {
      EmployeesAF f = (EmployeesAF) form;
      HashMap criterions = makeCriterionsMap(f);
      Pagination pagination = new Pagination(0, f.getPageSize(), 1,
          "emp.id", "ASC", criterions);
      String paginationKey = getPaginationKey();
      request.getSession().setAttribute(paginationKey, pagination);
      f.reset(mapping, request);
      return mapping.findForward(getForword());
    }

    protected String getForword() {
      return "show_employees";
    }

    protected String getPaginationKey() {
      return EmpListShowAC.PAGINATION_KEY;
    }

    protected HashMap makeCriterionsMap(EmployeesAF form) {
      HashMap m = new HashMap();
      String id = form.getId();
      if (!(id == null || "".equals(id))) {
        try {
          m.put("empid",id);
        } catch (NumberFormatException e) {
          m.put("empid", new Integer(-10000));
        }
      }
      String name = form.getName();
      if (!(name == null || name.length() == 0))
        m.put("name", form.getName());
      
      return m;
    }

  }

