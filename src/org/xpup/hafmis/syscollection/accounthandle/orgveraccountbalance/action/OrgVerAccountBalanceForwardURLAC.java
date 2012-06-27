/**
 * Copy Right Information   : Goldsoft 
 * Project                  : OrgVerAccountBalanceForwardURLAC
 * @Version                 : 1.0
 * @author                  : wangy
 * 生成日期                   :2007-12-19
 **/
package org.xpup.hafmis.syscollection.accounthandle.orgveraccountbalance.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class OrgVerAccountBalanceForwardURLAC extends Action {

  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {

    request.getSession().setAttribute(
        OrgVerAccountBalanceShowAC.PAGINATION_KEY, null);
    return mapping.findForward("orgVerAccountBalanceShowAC");
  }

}
