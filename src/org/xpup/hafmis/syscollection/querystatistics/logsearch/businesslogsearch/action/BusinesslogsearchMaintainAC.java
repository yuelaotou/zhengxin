package org.xpup.hafmis.syscollection.querystatistics.logsearch.businesslogsearch.action;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;


public class BusinesslogsearchMaintainAC extends Action {

 
  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
     
     return mapping.findForward("to_businesslogprint.jsp");
  
}
  
}

