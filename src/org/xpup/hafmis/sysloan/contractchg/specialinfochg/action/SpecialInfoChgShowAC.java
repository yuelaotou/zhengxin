package org.xpup.hafmis.sysloan.contractchg.specialinfochg.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.xpup.hafmis.sysloan.contractchg.specialinfochg.form.SpecialInfoChgAF;

public class SpecialInfoChgShowAC extends Action{
  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response) {
    try{
      SpecialInfoChgAF specialInfoChgAF=new SpecialInfoChgAF();
      request.setAttribute("specialInfoChgAF", specialInfoChgAF);
    }catch(Exception e){
      e.printStackTrace();
    }
    return mapping.findForward("to_specialinfochg_show");
  }
}
