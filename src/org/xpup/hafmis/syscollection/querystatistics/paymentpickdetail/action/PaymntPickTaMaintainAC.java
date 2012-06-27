package org.xpup.hafmis.syscollection.querystatistics.paymentpickdetail.action;

import java.util.HashMap;
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
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.syscollection.querystatistics.paymentpickstatistics.pickmonthreport.bsinterface.IPickMonthReportBS;
import org.xpup.hafmis.syscollection.querystatistics.paymentpickstatistics.pickmonthreport.dto.PickMonRepInfoDTO;

public class PaymntPickTaMaintainAC extends LookupDispatchAction {
  
  protected Map getKeyMethodMap() {
    HashMap map = new HashMap();
    map.put("button.print", "print");
    return map;
  }

  public ActionForward print(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    SecurityInfo securityInfo = (SecurityInfo) request.getSession().getAttribute("SecurityInfo");
    String bizDate = securityInfo.getUserInfo().getBizDate();
    String operator = securityInfo.getUserInfo().getUsername();
    request.setAttribute("bizDate", bizDate);
    request.setAttribute("operator", operator);
    return mapping.findForward("to_paymntpickta_cell");
  }
}



