package org.xpup.hafmis.sysfinance.reportmng.financereport.createreport.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.xpup.common.util.BSUtils;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.sysfinance.reportmng.financereport.createreport.bsinterface.ICreateReportBS;

public class CreateReportCheckDeleteAAC extends Action {

  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    response.setContentType("text/html;charset=UTF-8");
    response.setHeader("Cache-Control", "no-cache");
    String text=null;
    try {

      String reportid = request.getParameter("reportid");
      
      SecurityInfo securityInfo = (SecurityInfo) request.getSession().getAttribute("SecurityInfo");
      ICreateReportBS createReportBS = (ICreateReportBS) BSUtils.getBusinessService("createReportBS", this, mapping.getModuleConfig());
      String flag=createReportBS.checkdeleteReportTable(reportid,securityInfo);
      
      text="display_Delete('"+ flag+"')";
      response.getWriter().write(text); 
      response.getWriter().close();
      
    }catch(Exception e){
      e.printStackTrace();
    }

    return null;
  }
}