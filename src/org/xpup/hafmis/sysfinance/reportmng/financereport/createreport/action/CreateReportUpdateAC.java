package org.xpup.hafmis.sysfinance.reportmng.financereport.createreport.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.xpup.common.exception.BusinessException;
import org.xpup.common.util.BSUtils;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.sysfinance.reportmng.financereport.createreport.bsinterface.ICreateReportBS;
import org.xpup.hafmis.sysfinance.reportmng.financereport.createreport.dto.ReportMngDTO;
import org.xpup.hafmis.sysfinance.reportmng.financereport.createreport.form.CreateReportAF;

public class CreateReportUpdateAC extends Action {       
  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    ActionMessages message=null;
    CreateReportAF createReportAF = (CreateReportAF)form;
    try {
      SecurityInfo securityInfo = (SecurityInfo) request.getSession().getAttribute("SecurityInfo");
      ICreateReportBS createReportBS = (ICreateReportBS) BSUtils.getBusinessService("createReportBS", this, mapping.getModuleConfig());
      ReportMngDTO reportMngDTO=new ReportMngDTO();
      reportMngDTO.setId(createReportAF.getTableid());
      reportMngDTO.setTailtableNameChinese(createReportAF.getTablenamedef());
      reportMngDTO.setColspan(createReportAF.getColspandef());
      reportMngDTO.setRowspan(createReportAF.getRowspandef());
      createReportBS.insertCreateReportMng(reportMngDTO,securityInfo);
      request.getSession().setAttribute("savemethod", "1");

    } catch (BusinessException bex) {
      bex.printStackTrace();
      message= new ActionMessages();
      message.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(bex.getLocalizedMessage().toString(), false));
      saveErrors(request,message);
    } catch (Exception ex) {
      ex.printStackTrace();
    }
    return mapping.findForward("createReportShowAC");
  }
}
