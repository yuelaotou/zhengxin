package org.xpup.hafmis.syscollection.accounthandle.clearaccount.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.xpup.security.common.domain.User;

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
import org.xpup.hafmis.syscollection.accounthandle.clearaccount.bsinterface.IclearAccountBS;
import org.xpup.hafmis.syscollection.accounthandle.clearaccount.dto.ClearAccountBalanceDTO;
import org.xpup.hafmis.syscollection.accounthandle.clearaccount.form.ClearAccountBalanceForm;
import org.xpup.hafmis.syscollection.accounthandle.clearaccount.form.ClearAccountBalanceShowAF;
import org.xpup.security.common.domain.Userslogincollbank;

/**
 * @author ¿Ó≈Ù 2007-7-10
 */
public class ClearAccountTaBalanceShowAC extends Action {

  public static final String PAGINATION_KEY = "org.xpup.hafmis.syscollection.accounthandle.clearaccount.action.ClearAccountTaBalanceShowAC";

  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    try {
      SecurityInfo securityInfo = (SecurityInfo) request.getSession()
          .getAttribute("SecurityInfo");
      ClearAccountBalanceForm clearAccountBalanceDTO = new ClearAccountBalanceForm();
      String nosearch = (String)request.getAttribute("nosearch");
      Pagination pagination = getPagination(PAGINATION_KEY, securityInfo,
          request);
      saveToken(request);
      PaginationUtils.updatePagination(pagination, request);
      IclearAccountBS clearaccountBS = (IclearAccountBS) BSUtils
          .getBusinessService("clearaccountBS", this, mapping.getModuleConfig());
      ClearAccountBalanceShowAF clearAccountBalanceShowAF = new ClearAccountBalanceShowAF();
      if (securityInfo == null) {
        // request.setAttribute("clearAccountShowAF", clearAccountShowAF);
      } else {
        List bankList = securityInfo.getCollBankList();
        List bankList1 = new ArrayList();
        Userslogincollbank bankdto = null;
        Iterator itr1 = bankList.iterator();
        while (itr1.hasNext()) {
          bankdto = (Userslogincollbank) itr1.next();
          bankList1
              .add(new org.apache.struts.util.LabelValueBean(bankdto
                  .getCollBankName().toString(), bankdto.getCollBankId()
                  .toString()));
        }
        request.getSession(true).setAttribute("bankList1", bankList1);

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

        clearAccountBalanceShowAF.setBis_type(BusiTools
            .listBusiProperty(BusiConst.CLEARACCOUNTBUSINESSTYPE));
        if(nosearch==null || nosearch.equals("")){
          clearAccountBalanceDTO = clearaccountBS.findClearAccountBalance(
              pagination, securityInfo);
        }
      }
      request.setAttribute("clearAccountBalanceShowAF",
          clearAccountBalanceShowAF);
      request.setAttribute("clearAccountBalanceForm", clearAccountBalanceDTO);
    } catch (Exception ex) {
      ex.printStackTrace();
    }
    return mapping.findForward("to_clearAccountbalance_list");
  }

  private Pagination getPagination(String paginationKey,
      SecurityInfo securityInfo, HttpServletRequest request) {
    Pagination pagination = (Pagination) request.getSession().getAttribute(
        paginationKey);
    if (pagination == null) {
      HashMap m = new HashMap();
      pagination = new Pagination(0, 5, 1, "orgHAFAccountFlow.bizStatus",
          "ASC", m);
      request.getSession().setAttribute(paginationKey, pagination);
    }
    return pagination;
  }
}
