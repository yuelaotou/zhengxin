package org.xpup.hafmis.sysloan.accounthandle.clearaccount.action;

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
import org.xpup.common.util.BSUtils;
import org.xpup.common.util.Pagination;
import org.xpup.common.util.PaginationUtils;
import org.xpup.hafmis.common.util.BusiConst;
import org.xpup.hafmis.common.util.BusiTools;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.sysloan.accounthandle.clearaccount.bsinterface.IClearaccountBS;
import org.xpup.hafmis.sysloan.accounthandle.clearaccount.form.ClearAccountBalanceAF;
import org.xpup.hafmis.sysloan.accounthandle.clearaccount.form.ClearAccountBalanceInfoAF;
import org.xpup.security.common.domain.User;
import org.xpup.security.common.domain.Userslogincollbank;

/**
 * @author jj 2007-11-01
 */
public class ClearaccountTbShowAC extends Action {

  public static final String PAGINATION_KEY = "org.xpup.hafmis.sysloan.accounthandle.clearaccount.action.ClearaccountTbShowAC";

  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    try {
      ClearAccountBalanceAF clearAccountBalanceAF = new ClearAccountBalanceAF();
      ClearAccountBalanceInfoAF clearAccountBalanceInfoAF = new ClearAccountBalanceInfoAF();
      SecurityInfo securityInfo = (SecurityInfo) request.getSession()
      .getAttribute("SecurityInfo");
      Pagination pagination = getPagination(PAGINATION_KEY,securityInfo, request);
      saveToken(request);
      PaginationUtils.updatePagination(pagination, request);
      IClearaccountBS clearaccountBS = (IClearaccountBS) BSUtils
      .getBusinessService("clearaccountBS", this, mapping.getModuleConfig());
      Map typeMap=BusiTools.listBusiProperty(BusiConst.PLBUSINESSTYPE);
      typeMap.remove(new Integer(BusiConst.PLBUSINESSTYPE_CARRYOVERDUE));
      typeMap.remove(new Integer(BusiConst.PLBUSINESSTYPE_CARRYPAY));
      clearAccountBalanceAF.setTypeMap(typeMap);
      List temp_banklist = securityInfo.getDkUserBankList();
      List banklist = new ArrayList();
      if(!temp_banklist.isEmpty()){
        Iterator itr = temp_banklist.iterator();    
        while (itr.hasNext()) {
          Userslogincollbank userslogincollbank=(Userslogincollbank)itr.next();
          banklist.add(new org.apache.struts.util.LabelValueBean(
              userslogincollbank.getCollBankName().toString(), userslogincollbank.getCollBankId().toString()));
        }
      }
      List operList = securityInfo.getUserList();
      List operList1 = new ArrayList();
      User user = null;
      Iterator itr2 = operList.iterator();
      while (itr2.hasNext()) {
        user = (User) itr2.next();
        operList1.add(new org.apache.struts.util.LabelValueBean(user
            .getUsername(), user.getUsername()));
      }
      request.getSession(true).setAttribute("operList1", operList1);
      clearAccountBalanceInfoAF = clearaccountBS.findClearAccountBalanceInfo(pagination, securityInfo);
      String loanBankId = (String)pagination.getQueryCriterions().get("loanBankId");
      clearAccountBalanceAF.setLoanBankId(loanBankId);
      clearAccountBalanceAF.setMydate(clearaccountBS.getMydate());
      request.setAttribute("clearAccountBalanceAF", clearAccountBalanceAF);   
      request.setAttribute("clearAccountBalanceInfoAF", clearAccountBalanceInfoAF);  
      request.getSession(true).setAttribute("banklist", banklist);   
    } catch (Exception ex) {
      ex.printStackTrace();
    }
    return mapping.findForward("clearaccountbalance");
  }

  private Pagination getPagination(String paginationKey,SecurityInfo securityInfo,
      HttpServletRequest request) {
    Pagination pagination = (Pagination) request.getSession().getAttribute(
        paginationKey);
    if (pagination == null) {
      HashMap m = new HashMap();
      m.put("startDate",securityInfo.getUserInfo().getPlbizDate());
      m.put("endDate",securityInfo.getUserInfo().getPlbizDate());
      pagination = new Pagination(0, 5, 1, "",
          "", m);
      request.getSession().setAttribute(paginationKey, pagination);
    }
    return pagination;
  }
}
