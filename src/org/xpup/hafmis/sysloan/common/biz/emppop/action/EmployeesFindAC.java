package org.xpup.hafmis.sysloan.common.biz.emppop.action;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import org.xpup.common.util.Pagination;
import org.xpup.hafmis.sysloan.common.biz.emppop.form.EmployeesAF;

import org.xpup.hafmis.sysloan.common.biz.emppop.action.EmployeesShowAC;



public class EmployeesFindAC extends Action {




    public ActionForward execute(ActionMapping mapping, ActionForm form,
        HttpServletRequest request, HttpServletResponse response)
        throws Exception {
try{
      EmployeesAF f = (EmployeesAF) form;
      HashMap criterions = makeCriterionsMap(f);
      Pagination pagination = new Pagination(0, f.getPageSize(), 1,
          "emp.id", "ASC", criterions);
      String paginationKey = getPaginationKey();
      request.getSession().setAttribute(paginationKey, pagination);
      f.reset(mapping, request);
}catch(Exception ex){
  ex.printStackTrace();
}
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
      if (!(name == null || "".equals(name)))
        m.put("name", name);
      String oldId = form.getOldId().trim();
      if(!(oldId == null || oldId.length() ==0)){
        m.put("oldId", oldId);
      }
      String sfzh = form.getSfzh().trim();
      if(!(sfzh == null || sfzh.length() ==0)){
        m.put("sfzh", sfzh);
      }
      String dwbh = form.getDwbh().trim();
      if(!(dwbh == null || dwbh.length() ==0)){
        m.put("dwbh", dwbh);
      }
      String dwmc = form.getDwmc().trim();
      if(!(dwmc == null || dwmc.length() ==0)){
        m.put("dwmc", dwmc);
      }

      return m;
    }
  }

