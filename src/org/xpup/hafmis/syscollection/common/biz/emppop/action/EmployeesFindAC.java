package org.xpup.hafmis.syscollection.common.biz.emppop.action;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import org.xpup.common.util.Pagination;
import org.xpup.hafmis.syscollection.common.biz.emppop.form.EmployeesAF;

import org.xpup.hafmis.syscollection.common.biz.emppop.action.EmployeesShowAC;

public class EmployeesFindAC extends Action {




    public ActionForward execute(ActionMapping mapping, ActionForm form,
        HttpServletRequest request, HttpServletResponse response)
        throws Exception {
      EmployeesAF f = (EmployeesAF) form;
      HashMap criterions = makeCriterionsMap(f);
      Pagination pagination = new Pagination(0, f.getPageSize(), 1,
          "emp.id", "ASC", criterions);
      String paginationKey = getPaginationKey();
      request.getSession().setAttribute(paginationKey, pagination);
      modifyPagination(pagination);
      f.reset(mapping, request);
      return mapping.findForward(getForword());
    }

    protected String getForword() {
      return "show_employees";
    }

    protected String getPaginationKey() {
      return EmployeesShowAC.PAGINATION_KEY;
    }

    protected HashMap makeCriterionsMap(EmployeesAF form) {
      HashMap m = new HashMap();
      String id = form.getId().trim();
      if (!(id == null || "".equals(id))) {
        try {
          m.put("empid",id);
        } catch (NumberFormatException e) {
          m.put("empid", new Integer(-10000));
        }
      }
      String name = form.getName().trim();
      if (!(name == null || name.length() == 0))
        m.put("name", name);
      String oldId = form.getOldId().trim();
      if(!(oldId == null || oldId.length() ==0)){
        int ooldId = Integer.parseInt(oldId);
        m.put("oldId", ooldId+"");
      }
      String cardNum = form.getCardNumber().trim();
      if(!(cardNum == null || cardNum.length() == 0)){
        m.put("cardnum", cardNum);
      }

      return m;
    }

    protected void modifyPagination(Pagination pagination) {
    }
  }

