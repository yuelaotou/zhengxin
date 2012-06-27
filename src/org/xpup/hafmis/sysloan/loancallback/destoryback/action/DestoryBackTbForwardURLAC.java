package org.xpup.hafmis.sysloan.loancallback.destoryback.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * @author Œ‚µœ 2007-10-18
 */
public class DestoryBackTbForwardURLAC  extends Action {
  public static final String PAGINATION_KEY = "org.xpup.hafmis.sysloan.loancallback.destoryback.action.DestoryBackTbShowAC";

  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response) {
    try {
      request.getSession().setAttribute(DestoryBackTbShowAC.PAGINATION_KEY, null);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return mapping.findForward("destoryBackTbShowAC");
  }
}
