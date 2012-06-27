package org.xpup.hafmis.syscollection.querystatistics.accountinfo.empaccountinfo.action;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.xpup.common.util.Pagination;
import org.xpup.hafmis.syscollection.querystatistics.accountinfo.empaccountinfo.form.EmpAccountAF;

public class FindEmpAccountListAC extends Action {

  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    EmpAccountAF empAccountAF = (EmpAccountAF) form;      
    //为首页保存查询条件.
    HashMap criterions = makeCriterionsMap(empAccountAF);
    String startDate=request.getParameter("startDate");
    String lastDate=request.getParameter("lastDate");
    String orgIdaa101=request.getParameter("orgIdaa101");
    String nameba001=request.getParameter("nameba001");
    String empIdaa102=request.getParameter("empIdaa102");
    String nameba002=request.getParameter("nameba002");
    if (!(startDate == null || "".equals(startDate))) {
      criterions.put("startDate", startDate);
    }
    if (!(lastDate == null || lastDate.length() == 0))
      criterions.put("lastDate", lastDate);
    
    if (!(orgIdaa101 == null || orgIdaa101.length() == 0))
      criterions.put("orgIdaa101", orgIdaa101);

    
    
    if (!(nameba001 == null || nameba001.length() == 0))
      criterions.put("nameba001", nameba001);
    
    if (!(empIdaa102 == null || empIdaa102.length() == 0))
      criterions.put("empIdaa102", empIdaa102);
    if (!(nameba002 == null || nameba002.length() == 0))
      criterions.put("nameba002", nameba002);
    Pagination pagination = new Pagination(0, 10, 1,
        "id", "DESC", criterions); 
    String paginationKey = getPaginationKey();
    request.getSession().setAttribute(paginationKey, pagination);
    modifyPagination(pagination);
    
    empAccountAF.reset(mapping, request);

    return mapping.findForward(getForword());
  }

  protected String getForword() {
    return "to_empAccountshow_list";
  }

  protected String getPaginationKey() {
    return "org.xpup.hafmis.syscollection.querystatistics.accountinfo.empaccountinfo.action.ShowEmpAccountListAC";
  }

  protected HashMap makeCriterionsMap(EmpAccountAF form) {
    HashMap m = new HashMap();
    return m;
  }

  protected void modifyPagination(Pagination pagination) {
  }
}



