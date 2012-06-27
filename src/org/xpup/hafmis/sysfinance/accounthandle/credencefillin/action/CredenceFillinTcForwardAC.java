package org.xpup.hafmis.sysfinance.accounthandle.credencefillin.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Copy Right Information : ËðÒæ½á×ªForwardAction Goldsoft Project :
 * CredenceFillinTcForwardAC
 * 
 * @Version : v1.0
 * @author : ¸¶ÔÆ·å
 * @Date : 2007.10.24
 */
public class CredenceFillinTcForwardAC extends Action {

  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
     try {
      request.getSession().removeAttribute(CredenceFillinTcShowAC.PAGINATION_KEY);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return mapping.findForward("credenceFillinTcShowAC");
  }

}
