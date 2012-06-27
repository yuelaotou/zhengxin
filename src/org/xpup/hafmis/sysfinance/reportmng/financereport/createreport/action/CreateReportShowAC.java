package org.xpup.hafmis.sysfinance.reportmng.financereport.createreport.action;

import java.util.HashMap;
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
import org.xpup.hafmis.sysfinance.common.domain.entity.ReportMng;
import org.xpup.hafmis.sysfinance.reportmng.financereport.createreport.bsinterface.ICreateReportBS;
import org.xpup.hafmis.sysfinance.reportmng.financereport.createreport.form.CreateReportAF;

/**
 * 
 * @author ЭѕСт
 * 2007-10-16
 */
public class CreateReportShowAC extends Action {
  public static final String PAGINATION_KEY =
    "org.xpup.hafmis.sysfinance.reportmng.financereport.createreport.action.CreateReportShowAC";

  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response) {
    CreateReportAF createReportAF = new CreateReportAF();
    try {
      Pagination pagination = getPagination(PAGINATION_KEY, request);
      PaginationUtils.updatePagination(pagination, request);
      SecurityInfo securityInfo = (SecurityInfo) request.getSession().getAttribute("SecurityInfo");
      ICreateReportBS createReportBS = (ICreateReportBS) BSUtils.getBusinessService("createReportBS", this, mapping.getModuleConfig());
      List list=createReportBS.findcreateReportMngList(pagination,securityInfo);
      createReportAF.setList(list);
      
      String modifyid=(String)pagination.getQueryCriterions().get("modifyid");
      if(modifyid!=null && !modifyid.equals("")){
        ReportMng reportMng=createReportBS.queryCreateReportMngById(pagination,securityInfo);
        createReportAF.setTableid(reportMng.getId().toString());
        createReportAF.setTablenamedef(reportMng.getTailtableNameChinese());
        createReportAF.setColspandef(reportMng.getColspan());
        createReportAF.setRowspandef(reportMng.getRowspan());
        createReportAF.setActionflag("1");
        pagination.getQueryCriterions().put("modifyid","");
      }
      String savemethod=(String)request.getSession().getAttribute("savemethod");
      if(savemethod!=null){
        createReportAF.setSavemethod(savemethod);
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    request.setAttribute("createReportAF", createReportAF);
    return mapping.findForward("to_createreport_show");
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