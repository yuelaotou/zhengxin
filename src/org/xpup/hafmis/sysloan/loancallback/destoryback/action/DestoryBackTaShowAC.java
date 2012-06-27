package org.xpup.hafmis.sysloan.loancallback.destoryback.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.xpup.hafmis.sysloan.loancallback.destoryback.form.DestoryBackTaAF;


/**
 * @author 吴迪 2007-10-16
 */
public class DestoryBackTaShowAC extends Action {

  /**
   * 办理核销收回
   */
  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response) {
    saveToken(request);
    DestoryBackTaAF destoryBackTaAF = new DestoryBackTaAF();
    destoryBackTaAF.setBackUnit("中心");
    destoryBackTaAF.setBackMoney(null);
    destoryBackTaAF.setOverplusLoanMoney(null);
    destoryBackTaAF.setNoBackMoney(null);   
    request.setAttribute("destoryBackTaAF",destoryBackTaAF); 
    return mapping.findForward("to_destoryback_new");
  }
}
