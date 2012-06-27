package org.xpup.hafmis.sysloan.querystatistics.querystatistics.overdueinfoquery.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.xpup.common.exception.BusinessException;
import org.xpup.common.util.BSUtils;
import org.xpup.common.util.Pagination;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.sysloan.loancallback.loancallback.form.LoancallbackTaImportAF;
import org.xpup.hafmis.sysloan.querystatistics.querystatistics.overdueinfoquery.bsinterface.IOverDueinfoQueryBS;

public class OverDueinfoQueryTaToImportAC extends Action {
  /*
   * Generated Methods
   */

  /**
   * Method execute
   * jj 2007-10-29
   * @param mapping
   * @param form
   * @param request
   * @param response
   * @return ActionForward
   */
  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response) {
    return mapping.findForward("overDueinfoQueryTaToImportAC");
  }
}