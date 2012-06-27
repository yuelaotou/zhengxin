package org.xpup.hafmis.sysloan.querystatistics.loanaccountquery.interesttotalacc.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.LookupDispatchAction;
import org.xpup.common.util.BSUtils;
import org.xpup.common.util.Pagination;
import org.xpup.common.util.PaginationUtils;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.sysloan.querystatistics.loanaccountquery.interesttotalacc.bsinterface.IInterestBS;
import org.xpup.hafmis.sysloan.querystatistics.loanaccountquery.interesttotalacc.form.InterestTaAF;
import org.xpup.security.common.domain.Userslogincollbank;

public class InterestTaMaintainAC extends LookupDispatchAction {


  protected Map getKeyMethodMap() {
    HashMap map = new HashMap();
    map.put("button.print", "print");
    return map;
  }

  public ActionForward print(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    SecurityInfo securityInfo = (SecurityInfo) request.getSession()
        .getAttribute("SecurityInfo");
    try {
      
    } catch (Exception e) {
      e.printStackTrace();
    }
    return mapping.findForward("to_interestta_cell");
  }
}
