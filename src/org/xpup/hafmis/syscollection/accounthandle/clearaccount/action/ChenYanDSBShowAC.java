package org.xpup.hafmis.syscollection.accounthandle.clearaccount.action;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.xpup.common.util.BSUtils;
import org.xpup.common.util.Pagination;
import org.xpup.common.util.PaginationUtils;
import org.xpup.hafmis.common.util.BusiTools;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.syscollection.accounthandle.clearaccount.bsinterface.IclearAccountBS;
import org.xpup.hafmis.syscollection.accounthandle.clearaccount.dto.ChenYanDSBDTO;
import org.xpup.hafmis.syscollection.accounthandle.clearaccount.form.ChenYanDSBAF;
import org.xpup.hafmis.syscollection.accounthandle.clearaccount.form.ClearAccountBalanceForm;
import org.xpup.security.common.domain.Userslogincollbank;

public class ChenYanDSBShowAC extends Action {

  public static final String PAGINATION_KEY = "org.xpup.hafmis.syscollection.accounthandle.clearaccount.action.ChenYanDSBShowAC";

  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    try {
      ChenYanDSBAF chenYanDSBAF = new ChenYanDSBAF();
      String where = (String) request.getAttribute("where");
      SecurityInfo securityInfo = (SecurityInfo) request.getSession()
          .getAttribute("SecurityInfo");
      Pagination pagination = getPagination(PAGINATION_KEY, securityInfo,
          request);
      PaginationUtils.updatePagination(pagination, request);
      IclearAccountBS clearaccountBS = (IclearAccountBS) BSUtils
          .getBusinessService("clearaccountBS", this, mapping.getModuleConfig());
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

      List list = new ArrayList();
      ChenYanDSBDTO dto = null;
      if (where != null && where.equals("forward")) {
      } else {
        for (int i = 1; i < 31; i++) {
          String yearmonth = (String) pagination.getQueryCriterions().get(
              "yearmonth");
          pagination.getQueryCriterions().put("startday",
              yearmonth + BusiTools.convertTwoNumber(i + ""));
          pagination.getQueryCriterions().put("untilday",
              yearmonth + BusiTools.convertTwoNumber(i + ""));
          ClearAccountBalanceForm clearAccountBalanceDTO = clearaccountBS
              .findClearAccountBalance(pagination, securityInfo);
          if (clearAccountBalanceDTO != null) {
            dto = new ChenYanDSBDTO();
            dto.setBizdate(yearmonth + BusiTools.convertTwoNumber(i + ""));
            dto.setMonthpay(clearAccountBalanceDTO
                .getXiaoji1_credit_paybalance());
            dto.setPickup(clearAccountBalanceDTO.getDebit_paybalance_xiaoji());
            dto.setTranin(clearAccountBalanceDTO.getOrg_tranin_paybalance());
            dto.setTranout(clearAccountBalanceDTO.getOrg_tranout_balance());
            dto.setChgup(clearAccountBalanceDTO
                .getAdjustaccout_credit_paybalance());
            dto.setChgdown(clearAccountBalanceDTO
                .getAdjustaccout_debit_paybalance());
            dto.setPickloan(clearAccountBalanceDTO.getPick_payload_balance());
            dto.setBalance(clearAccountBalanceDTO.getCur_rest_paybalance());
            if (dto.getMonthpay().compareTo(new BigDecimal(0.00)) == 0
                && dto.getPickup().compareTo(new BigDecimal(0.00)) == 0
                && dto.getTranin().compareTo(new BigDecimal(0.00)) == 0
                && dto.getTranout().compareTo(new BigDecimal(0.00)) == 0
                && dto.getChgup().compareTo(new BigDecimal(0.00)) == 0
                && dto.getChgdown().compareTo(new BigDecimal(0.00)) == 0
                && dto.getPickloan().compareTo(new BigDecimal(0.00)) == 0) {

            } else {
              list.add(dto);
            }
          }
        }
      }
      chenYanDSBAF.setList(list);
      request.getSession().setAttribute("chenyandsb", list);
      request.setAttribute("chenYanDSBAF", chenYanDSBAF);
    } catch (Exception ex) {
      ex.printStackTrace();
    }
    return mapping.findForward("chenyandsb");
  }

  private Pagination getPagination(String paginationKey,
      SecurityInfo securityInfo, HttpServletRequest request) {
    Pagination pagination = (Pagination) request.getSession().getAttribute(
        paginationKey);
    if (pagination == null) {
      HashMap m = new HashMap();
      pagination = new Pagination(0, 10, 1, "", "ASC", m);
      request.getSession().setAttribute(paginationKey, pagination);
    }
    return pagination;
  }
}
