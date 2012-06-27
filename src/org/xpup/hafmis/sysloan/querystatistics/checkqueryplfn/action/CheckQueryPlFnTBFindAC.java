package org.xpup.hafmis.sysloan.querystatistics.checkqueryplfn.action;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.xpup.common.util.Pagination;
import org.xpup.hafmis.sysloan.querystatistics.checkqueryplfn.form.CheckQueryPlFnTBAF;

public class CheckQueryPlFnTBFindAC extends Action {
  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response) {
    try{
      CheckQueryPlFnTBAF checkQueryPlFnTBAF = (CheckQueryPlFnTBAF) form;
      String contractid=(String)request.getAttribute("contractid");
      contractid=(String)request.getParameter("contractid");
      String startTime=(String)request.getParameter("startTime");
      String endTime=(String)request.getParameter("endTime");
      checkQueryPlFnTBAF.setStartTime(startTime);
      checkQueryPlFnTBAF.setEndTime(endTime);
      HashMap criterions = makeCriterionsMap(checkQueryPlFnTBAF,contractid);
      Pagination pagination = new Pagination(0, 10, 1, " pl202.biz_date ", "DESC",criterions);
      String paginationKey = getPaginationKey();
      request.getSession().setAttribute(paginationKey, pagination);
      modifyPagination(pagination);
  }catch(Exception e){
    e.printStackTrace();
  }
    return mapping.findForward("checkQueryPlFntb_show");
  }
  protected String getPaginationKey() {
    return CheckQueryPlFnTBShowAC.PAGINATION_KEY;
  }
  protected HashMap makeCriterionsMap(CheckQueryPlFnTBAF form,String contractid) {
    HashMap m = new HashMap();
    if (!(contractid == null || "".equals(contractid.trim()))) {
        m.put("borrowercontractid", contractid.trim());
    }

    String empId = form.getEmpId();
    if (!(empId == null || empId.trim().length() == 0)){
      m.put("empId", empId.trim());
    }
    
    String empName = form.getEmpName();
    if (!(empName == null || empName.trim().length() == 0)){
      m.put("empName", empName.trim());
    }

    String startTime = form.getStartTime();
    if (!(startTime == null || startTime.trim().length() == 0)){
      m.put("bizdateB", startTime.trim());
    }

    String endTime = form.getEndTime();
    if (!(endTime == null || endTime.trim().length() == 0)){
      m.put("bizdateE", endTime.trim());
    }

    String contracId = form.getContractId();
    if (!(contracId == null || contracId.trim().length() == 0)){
      m.put("borrowercontractid", contracId.trim());
    }
    return m;
  }
  protected void modifyPagination(Pagination pagination) {
  }
}
