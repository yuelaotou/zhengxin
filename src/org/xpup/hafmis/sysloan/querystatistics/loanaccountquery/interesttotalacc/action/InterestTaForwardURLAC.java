package org.xpup.hafmis.sysloan.querystatistics.loanaccountquery.interesttotalacc.action;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.xpup.common.util.BSUtils;
import org.xpup.common.util.Pagination;
import org.xpup.common.util.PaginationUtils;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.sysloan.querystatistics.loanaccountquery.principaltotalacc.bsinterface.IPrincipalBS;
import org.xpup.hafmis.sysloan.querystatistics.loanaccountquery.principaltotalacc.form.PrincipalTaAF;

public class InterestTaForwardURLAC extends Action {

  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    request.getSession().setAttribute(InterestTaShowAC.PAGINATION_KEY, null);
    return mapping.findForward("interestTaShowAC");
  }
}
