package org.xpup.hafmis.sysloan.accounthandle.overpay.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.xpup.hafmis.sysloan.accounthandle.overpay.form.OverPayTaAF;

public class OverPayTaShowAC extends Action{
  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response) {
    OverPayTaAF overPayTaAF=null;
    if(request.getAttribute("overPayTaAF")==null){
      overPayTaAF=new OverPayTaAF();
    }else{
      overPayTaAF=(OverPayTaAF)request.getAttribute("overPayTaAF");
    }
    request.setAttribute("overPayTaAF", overPayTaAF);
    return mapping.findForward("to_overpayta_show");
  }
}
