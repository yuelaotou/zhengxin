package org.xpup.hafmis.sysfinance.reportmng.financereport.createreport.action;

import java.util.HashMap;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.actions.DispatchAction;
import org.xpup.common.exception.BusinessException;
import org.xpup.common.util.BSUtils;
import org.xpup.common.util.Pagination;
import org.xpup.common.util.PaginationUtils;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.sysfinance.reportmng.financereport.createreport.bsinterface.ICreateReportBS;
/**
 * 
 * @author ЭѕСт
 * 2007-10-17
 */
public class CreateReportMaintainAC extends DispatchAction {
  public static final String PAGINATION_KEY = "org.xpup.hafmis.sysfinance.reportmng.financereport.createreport.action.CreateReportShowAC";

  public ActionForward modify(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response) {
    try{
      
      String id=request.getParameter("reportid");

      Pagination pagination = getPagination(PAGINATION_KEY, request);
      PaginationUtils.updatePagination(pagination, request);
      pagination.getQueryCriterions().put("modifyid", id);

    }catch(Exception e){
      e.printStackTrace();
    }
    return mapping.findForward("to_createtable_modify");
  }

  public ActionForward delete(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response) {
    ActionMessages message=null;
    try{

      String id=request.getParameter("reportid");

      SecurityInfo securityInfo = (SecurityInfo) request.getSession().getAttribute("SecurityInfo");
      ICreateReportBS createReportBS = (ICreateReportBS) BSUtils.getBusinessService("createReportBS", this, mapping.getModuleConfig());
      createReportBS.deleteReport(id,securityInfo);
      request.getSession().setAttribute("savemethod", "1");

    } catch (BusinessException bex) {
      bex.printStackTrace();
      message= new ActionMessages();
      message.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(bex.getLocalizedMessage().toString(), false));
      saveErrors(request,message);
    }catch(Exception e){
      e.printStackTrace();
    }
    return mapping.findForward("to_createtable_delete");
  }
  
  public ActionForward print(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response) {
    try{

      Pagination pagination = getPagination(PAGINATION_KEY, request);
      PaginationUtils.updatePagination(pagination, request);
      SecurityInfo securityInfo = (SecurityInfo) request.getSession().getAttribute("SecurityInfo");
      ICreateReportBS createReportBS = (ICreateReportBS) BSUtils.getBusinessService("createReportBS", this, mapping.getModuleConfig());
      List list=createReportBS.findcreateReportMngAllList(pagination,securityInfo);
      HttpSession session = request.getSession();
      session.setAttribute("cellList", list);
    }catch(Exception e){
      e.printStackTrace();
    }
    
    return mapping.findForward("to_createtable_report");
  }


  private Pagination getPagination(String paginationKey,
      HttpServletRequest request) {
    Pagination pagination = (Pagination) request.getSession().getAttribute(
        paginationKey);
    if (pagination == null) {
      pagination = new Pagination(0, 10, 1, "reportMng.id",
          "DESC", new HashMap(0));
      request.getSession().setAttribute(PAGINATION_KEY, pagination);
    }

    return pagination;
  }

}
