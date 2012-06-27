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
import org.apache.struts.action.ActionMessages;
import org.xpup.common.util.BSUtils;
import org.xpup.common.util.Pagination;
import org.xpup.common.util.PaginationUtils;
import org.xpup.hafmis.common.util.BusiConst;
import org.xpup.hafmis.common.util.BusiTools;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.sysloan.accounthandle.clearaccount.bsinterface.IClearaccountBS;
import org.xpup.hafmis.sysloan.accounthandle.clearaccount.form.ClearaccountAF;
import org.xpup.security.common.domain.User;
import org.xpup.security.common.domain.Userslogincollbank;

public class ClearaccountTbMXShowAC extends Action {
  public static final String PAGINATION_KEY = "org.xpup.hafmis.sysloan.accounthandle.clearaccount.action.ClearaccountTbMXShowAC";

  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    ActionMessages messages = null;
    try {
      saveToken(request);
      SecurityInfo securityInfo = (SecurityInfo) request.getSession()
          .getAttribute("SecurityInfo");
      Pagination pagination = getPagination(PAGINATION_KEY, request);
      PaginationUtils.updatePagination(pagination, request);
      saveToken(request);
      IClearaccountBS clearaccountBS = (IClearaccountBS) BSUtils
          .getBusinessService("clearaccountBS", this, mapping.getModuleConfig());
      ClearaccountAF clearaccountAF = clearaccountBS.findClearaccountMXList(pagination, securityInfo);
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
      // 放款银行下拉框
      List loanBankNameList = new ArrayList();
      List bangkList = securityInfo.getDkUserBankList();
      Userslogincollbank userslogincollbank = null;
      Iterator bank = bangkList.iterator();
      while (bank.hasNext()) {
        userslogincollbank = (Userslogincollbank) bank.next();
        loanBankNameList.add(new org.apache.struts.util.LabelValueBean(
            userslogincollbank.getCollBankName(), userslogincollbank
                .getCollBankId().toString()));
      }
      request.getSession(true).setAttribute("loanBankNameList",
          loanBankNameList);
      request.setAttribute("clearaccountAF", clearaccountAF);
      // 业务类型下拉框
      Map bizTypeMap = BusiTools.listBusiProperty(BusiConst.PLBUSINESSTYPE);
      bizTypeMap.remove(new Integer(BusiConst.PLBUSINESSTYPE_CARRYOVERDUE));
      bizTypeMap.remove(new Integer(BusiConst.PLBUSINESSTYPE_CARRYPAY));
      clearaccountAF.setBizTypeMap(bizTypeMap);
    } catch (Exception ex) {
      ex.printStackTrace();
    }
    return mapping.findForward("clearaccountbalanceWindow");
  }

  private Pagination getPagination(String paginationKey,
      HttpServletRequest request) {
    Pagination pagination = (Pagination) request.getSession().getAttribute(
        paginationKey);
    if (pagination == null) {
      Map m = new HashMap(0);
      pagination = new Pagination(0, 10, 1, "docnum", "DESC", m);
      request.getSession().setAttribute(paginationKey, pagination);
    }
    return pagination;
  }
}
