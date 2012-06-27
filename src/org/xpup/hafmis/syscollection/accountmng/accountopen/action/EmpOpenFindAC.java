package org.xpup.hafmis.syscollection.accountmng.accountopen.action;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.xpup.common.util.Pagination;
import org.xpup.hafmis.syscollection.accountmng.accountopen.form.EmpkhAF;

public class EmpOpenFindAC extends Action {

  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {

    EmpkhAF f = (EmpkhAF) form;
    String orgId = String.valueOf(request.getSession().getAttribute("org.id"));
    String orgInfoname = String.valueOf(request.getSession().getAttribute("org.name"));
    HashMap criterions = makeCriterionsMap(f,orgId,orgInfoname);
  

    Pagination pagination = new Pagination(0, 10, 1, "emp.empId", "DESC",criterions);
    String paginationKey = getPaginationKey();
    request.getSession().setAttribute(paginationKey, pagination);
    modifyPagination(pagination);
    f.reset(mapping, request);

    return mapping.findForward(getForword());
  }

  protected String getForword() {
    return "employee_kh_show";
  }

  protected String getPaginationKey() {
    return "org.xpup.hafmis.syscollection.accountmng.accountopen.action.EmpOpenShowAC";
  }

  protected HashMap makeCriterionsMap(EmpkhAF form, String orgId, String orgInfoname) {
    
    HashMap m = new HashMap();
    String id = form.getEmpid();
    
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

    String cardNumber = form.getCardNumber();
    if (!(cardNumber == null || cardNumber.trim().length() == 0))
      m.put("cardNumber", form.getCardNumber());

    if (!(orgId == null || "".equals(orgId.trim()))) {
      try {
        int iid = Integer.parseInt(orgId.trim());
        m.put("orgId", new Integer(iid));
      } catch (NumberFormatException e) {
        m.put("orgId", new Integer(-10000));
      }
    }
    
    if (!(orgInfoname == null || "".equals(orgInfoname.trim()))) {
      String nname = orgInfoname.trim();
      m.put("orgInfoname", nname);
    }
    
    return m;
  }

  protected void modifyPagination(Pagination pagination) {
  }
}
