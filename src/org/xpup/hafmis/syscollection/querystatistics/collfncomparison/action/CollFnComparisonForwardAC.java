package org.xpup.hafmis.syscollection.querystatistics.collfncomparison.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
/**
 * 归集财务对账查询ForwardAction
 * Copy Right Information :
 * Goldsoft Project : CollFnComparisonForwardAC
 *
 * @Version : v1.0
 * @author : 付云峰
 * @Date : 2008-4-10 下午08:57:04
 */
public class CollFnComparisonForwardAC extends Action {

  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    try {
      request.getSession().removeAttribute(CollFnComparisonShowAC.PAGINATION_KEY);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return mapping.findForward("to_collFnComparisonShowAC");
  }

}
