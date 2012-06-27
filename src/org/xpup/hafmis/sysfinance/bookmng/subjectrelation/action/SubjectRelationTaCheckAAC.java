package org.xpup.hafmis.sysfinance.bookmng.subjectrelation.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.xpup.common.util.BSUtils;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.sysfinance.bookmng.subjectrelation.bsinterface.ISubjectrelationBS;

public class SubjectRelationTaCheckAAC extends Action {
  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    response.setContentType("text/html;charset=UTF-8");
    response.setHeader("Cache-Control", "no-cache");
    String text=null;
    String message="";
    try {

      String subject = request.getParameter("firstSubjectCode");
      String flag = "0";

      SecurityInfo securityInfo = (SecurityInfo) request.getSession().getAttribute("SecurityInfo");
      ISubjectrelationBS subjectrelationBS = (ISubjectrelationBS) BSUtils
      .getBusinessService("subjectrelationBS", this, mapping
          .getModuleConfig());
      message=subjectrelationBS.checkSubjectcode(subject,securityInfo);
      
      text="display('"+ message+ "','"+flag+"')";
      response.getWriter().write(text); 
      response.getWriter().close();
      
    }catch(Exception e){
      e.printStackTrace();
    }

    return null;
  }
}
