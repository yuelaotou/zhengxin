package org.xpup.hafmis.sysfinance.accounthandle.credencefillin.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Copy Right Information : Æ¾Ö¤Â¼ÈëForwardAction Goldsoft Project :
 * CredenceFillinTcForwardAC
 * 
 * @Version : v1.0
 * @author : Áõ±ù
 * @Date : 2007.11.15
 */
public class CredenceFillinTaForwardAC extends Action {

  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    try {
      request.getSession().setAttribute("single", null);
      resetToken(request);
      request.getSession().removeAttribute(
          CredenceFillinTcShowAC.PAGINATION_KEY);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return mapping.findForward("credenceFillinTaShowAC");
  }

}
