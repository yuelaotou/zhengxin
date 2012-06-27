package org.xpup.hafmis.sysloan.querystatistics.datareadyquery.empolderquery.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * 开发商查询
 * 
 * @author 付云峰
 */
public class EmpolderQueryForwardAC extends Action {

  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    request.getSession().removeAttribute(EmpolderQueryShowAC.PAGINATION_KEY);
    return mapping.findForward("empolderQueryShowAC");
  }

}
