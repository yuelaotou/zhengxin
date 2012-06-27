package org.xpup.hafmis.syscollection.accounthandle.clearaccount.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.xpup.common.exception.BusinessException;
import org.xpup.common.util.BSUtils;
import org.xpup.common.util.Pagination;
import org.xpup.common.util.PaginationUtils;
import org.xpup.hafmis.common.util.BusiConst;
import org.xpup.hafmis.common.util.BusiTools;
import org.xpup.hafmis.orgstrct.dto.OfficeDto;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.syscollection.accounthandle.clearaccount.bsinterface.IclearAccountBS;
import org.xpup.hafmis.syscollection.accounthandle.clearaccount.form.ClearAccountBalanceShowAF;
import org.xpup.hafmis.syscollection.accounthandle.clearaccount.form.ClearAccountShowAF;
import org.xpup.hafmis.syscollection.paymng.monthpay.action.MonthpayTbShowAC;
import org.xpup.hafmis.syscollection.paymng.monthpay.form.MonthpayTbFindAF;
import org.xpup.security.common.domain.User;
import org.xpup.security.common.domain.Userslogincollbank;

/**
 * @author 李鹏 2007-7-10
 */
public class ClearAccountTaBalanceFindAC extends Action {

  public static final String PAGINATION_KEY = "org.xpup.hafmis.syscollection.accounthandle.clearaccount.action.ClearAccountTaBalanceShowAC";

  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    SecurityInfo securityInfo = (SecurityInfo) request.getSession()
        .getAttribute("SecurityInfo");
    ClearAccountBalanceShowAF f = (ClearAccountBalanceShowAF) form;
    ClearAccountBalanceShowAF clearAccountBalanceShowAF = new ClearAccountBalanceShowAF();
    List bankList = securityInfo.getCollBankList();
    List bankList1 = new ArrayList();
    Userslogincollbank bankdto = null;
    Iterator itr1 = bankList.iterator();
    while (itr1.hasNext()) {
      bankdto = (Userslogincollbank) itr1.next();
      bankList1.add(new org.apache.struts.util.LabelValueBean(bankdto
          .getCollBankName().toString(), bankdto.getCollBankId().toString()));
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

    request
        .setAttribute("clearAccountBalanceShowAF", clearAccountBalanceShowAF);
    HashMap criterions = makeCriterionsMap(f);
    Pagination pagination = new Pagination(0, 10, 1,
        "orgHAFAccountFlow.bizStatus", "DESC", criterions);
    String paginationKey = ClearAccountTaBalanceShowAC.PAGINATION_KEY;
    request.getSession().setAttribute(paginationKey, pagination);
    f.reset(mapping, request);
    return mapping.findForward(getForword());
  }

  protected String getForword() {
    return "to_clearAccountbalance_list";
  }

  protected HashMap makeCriterionsMap(ClearAccountBalanceShowAF form) {
    HashMap m = new HashMap();
    String orgId = form.getOrgId();
    if (!(orgId == null || "".equals(orgId))) {
      try {
        int iid = Integer.parseInt(orgId);
        m.put("orgId", new Integer(iid));
      } catch (NumberFormatException e) {
        m.put("orgId", new Integer(-10000));
      }
    }
    String orgName = form.getOrgName();
    if (!(orgName == null || orgName.length() == 0))
      m.put("orgName", form.getOrgName());
    // TODO 需要继续添加查询条件！！！
    String bank1 = form.getBank1();
    if (!(bank1 == null || bank1.length() == 0)) {
      m.put("bank1", form.getBank1());
    }
    String operator = form.getOperator_temp();
    if (!(operator == null || operator.length() == 0)) {
      m.put("operator", form.getOperator_temp());
    }
    String startday = form.getStartday();
    if (!(startday == null || startday.length() == 0)) {
      if (startday.length() == 6) {
        String a = startday;
        startday = startday + "01";
        m.put("startday", startday);
        m.put("untilday", a + "31");
        m.put("specialType", "1");
      } else {
        m.put("startday", form.getStartday());
      }

    }
    String untilday = form.getUntilday();
    if (!(untilday == null || untilday.length() == 0)) {
      m.put("untilday", form.getUntilday());
    }
    String bis_type1 = form.getBis_type1();
    if (!(bis_type1 == null || bis_type1.length() == 0)) {
      m.put("bis_type1", form.getBis_type1());
    }
    return m;
  }

}
