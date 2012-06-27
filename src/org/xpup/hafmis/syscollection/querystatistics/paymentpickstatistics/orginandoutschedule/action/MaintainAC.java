package org.xpup.hafmis.syscollection.querystatistics.paymentpickstatistics.orginandoutschedule.action;


import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.xpup.common.util.BSUtils;
import org.xpup.common.util.Pagination;
import org.xpup.common.util.PaginationUtils;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.syscollection.querystatistics.paymentpickstatistics.orginandoutschedule.bsinterface.IOrgInAndOutScheduleBS;
public class MaintainAC extends Action{
  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    Pagination pagination = (Pagination)request.getSession().getAttribute(getPaginationKey());
    SecurityInfo securityInfo = (SecurityInfo) request.getSession().getAttribute(
    "SecurityInfo");
    pagination.getQueryCriterions().put("securityInfo", securityInfo);
    IOrgInAndOutScheduleBS orgInAndOutScheduleBS = (IOrgInAndOutScheduleBS) BSUtils
    .getBusinessService("orgInAndOutScheduleBS", this, mapping.getModuleConfig());
    PaginationUtils.updatePagination(pagination, request);
    List printlist=orgInAndOutScheduleBS.findPrintInfo(pagination);
    request.setAttribute("printlist", printlist);
    return mapping.findForward("to_print");
  }
  protected String getPaginationKey() {
    return ShowListAC.PAGINATION_KEY;
  }
}
