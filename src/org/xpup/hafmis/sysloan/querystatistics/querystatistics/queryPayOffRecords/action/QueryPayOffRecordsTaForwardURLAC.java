package org.xpup.hafmis.sysloan.querystatistics.querystatistics.queryPayOffRecords.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
public class QueryPayOffRecordsTaForwardURLAC extends Action {
  public static final String PAGINATION_KEY = "org.xpup.hafmis.sysloan.querystatistics.querystatistics.queryPayOffRecords.action.QueryPayOffRecordsTaShowAC";

  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response) {
    try {
      request.getSession().setAttribute(QueryPayOffRecordsTaShowAC.PAGINATION_KEY, null);
    } catch (Exception e) {
      e.printStackTrace();
    }
    System.out.println("-----------QueryPayOffRecordsTaForwardURLAC--------------------");
    return mapping.findForward("queryPayOffRecordsTaShowAC");
  }
}
