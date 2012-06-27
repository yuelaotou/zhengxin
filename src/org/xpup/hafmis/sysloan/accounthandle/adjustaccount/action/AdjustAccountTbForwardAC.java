package org.xpup.hafmis.sysloan.accounthandle.adjustaccount.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * 跳转到维护页的Action
 * 
 * @author 付云峰
 */
public class AdjustAccountTbForwardAC extends Action {

  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {

    request.getSession().removeAttribute(AdjustAccountTbShowdAC.PAGINATION_KEY);
    return mapping.findForward("adjustAccountTbShowdAC");
  }

}
