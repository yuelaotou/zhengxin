package org.xpup.hafmis.syscollection.querystatistics.collfncomparison.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.xpup.hafmis.syscollection.querystatistics.collfncomparison.form.CollFnComparisonOrgAccountShowAF;

public class CollFnComparisonOrgAccountFindPrintConditionAC extends Action {
  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    try{
      CollFnComparisonOrgAccountShowAF collFnComparisonOrgAccountShowAF = new CollFnComparisonOrgAccountShowAF();
      request.setAttribute("collFnComparisonOrgAccountShowAF", collFnComparisonOrgAccountShowAF);
    }catch(Exception e){
      e.printStackTrace();
    }
    return mapping.findForward("to_findprintconditon_show");
  }
}
