package org.xpup.hafmis.sysloan.querystatistics.checkqueryplfn.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.xpup.hafmis.sysloan.querystatistics.checkqueryplfn.form.CollFnComparisonOrgAccountShowAF;

public class CheckQueryPlFnWindow2AC extends Action {
  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response) {
    // TODO Auto-generated method stub
 
    
   
    CollFnComparisonOrgAccountShowAF collFnComparisonOrgAccountShowAF=new CollFnComparisonOrgAccountShowAF();
    request.setAttribute("collFnComparisonOrgAccountShowAF", collFnComparisonOrgAccountShowAF);
    return mapping.findForward("checkQueryPlFnWindow2_show");
  }
}
