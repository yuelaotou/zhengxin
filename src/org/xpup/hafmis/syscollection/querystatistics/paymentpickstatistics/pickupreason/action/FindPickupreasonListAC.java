package org.xpup.hafmis.syscollection.querystatistics.paymentpickstatistics.pickupreason.action;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.xpup.common.util.Pagination;
import org.xpup.hafmis.syscollection.querystatistics.paymentpickstatistics.pickupreason.form.PickupreasonAF;

public class FindPickupreasonListAC extends Action {

  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    PickupreasonAF pickupreasonAF = (PickupreasonAF) form;      
    //为首页保存查询条件.
    HashMap criterions = makeCriterionsMap(pickupreasonAF);
    String officeid=request.getParameter("officeid");
    String bankid=request.getParameter("bankid");
    String orgid=request.getParameter("orgid");
    String date=request.getParameter("date");
    if (!(officeid == null || "".equals(officeid))) {
      criterions.put("officeid", officeid);
    }
    if (!(bankid == null || bankid.length() == 0))
      criterions.put("bankid", bankid);
    
    if (!(orgid == null || orgid.length() == 0))
      criterions.put("orgid", orgid);
    if (!(date == null || date.length() == 0))
      criterions.put("date", date);
    Pagination pagination = new Pagination(0, 10, 1,
        "pickreason", "ASC", criterions); 
    String paginationKey = getPaginationKey();
    request.getSession().setAttribute(paginationKey, pagination);
    modifyPagination(pagination);
    pickupreasonAF.reset(mapping, request);
    return mapping.findForward(getForword());
  }

  protected String getForword() {
    return "to_pickupreasonshow_list";
  }

  protected String getPaginationKey() {
    return "org.xpup.hafmis.syscollection.querystatistics.paymentpickstatistics.pickupreason.action.ShowPickupreasonListAC";
  }

  protected HashMap makeCriterionsMap(PickupreasonAF form) {
    HashMap m = new HashMap();
    return m;
  }

  protected void modifyPagination(Pagination pagination) {
  }
}



