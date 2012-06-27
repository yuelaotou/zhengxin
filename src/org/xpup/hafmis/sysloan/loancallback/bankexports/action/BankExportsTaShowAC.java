package org.xpup.hafmis.sysloan.loancallback.bankexports.action;

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
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.xpup.common.exception.BusinessException;
import org.xpup.common.util.BSUtils;
import org.xpup.common.util.Pagination;
import org.xpup.common.util.PaginationUtils;
import org.xpup.hafmis.common.util.BusiTools;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.sysloan.loancallback.bankexports.bsinterface.IBankExportsBS;
import org.xpup.hafmis.sysloan.loancallback.bankexports.dto.BatchShouldBackListDTO;
import org.xpup.hafmis.sysloan.loancallback.bankexports.form.BankExportsTaAF;
import org.xpup.security.common.domain.Userslogincollbank;

public class BankExportsTaShowAC extends Action {

  public static final String PAGINATION_KEY = "org.xpup.hafmis.sysloan.loancallback.bankexports.action.BankexportsTaShowAC";

  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    ActionMessages messages = null;
    SecurityInfo securityInfo = (SecurityInfo) request.getSession()
        .getAttribute("SecurityInfo");
    try {
      Pagination pagination = getPagination(BankExportsTaShowAC.PAGINATION_KEY,
          request);
      String yearMonth = (String) pagination.getQueryCriterions().get(
          "yearMonth");
      String day = (String) pagination.getQueryCriterions().get("day");
      String loanBankId = (String) pagination.getQueryCriterions().get(
          "loanBankId");
      String batchNum = (String) pagination.getQueryCriterions()
          .get("batchNum");
      BankExportsTaAF af = new BankExportsTaAF();
      PaginationUtils.updatePagination(pagination, request);
      IBankExportsBS bankExportsBS = (IBankExportsBS) BSUtils
          .getBusinessService("bankExportsBS", this, mapping.getModuleConfig());
      List callbacklist = bankExportsBS.findBankCallbackList(pagination,
          securityInfo);
      yearMonth = (String) pagination.getQueryCriterions().get("yearMonth");
      day = (String) pagination.getQueryCriterions().get("day");
      BatchShouldBackListDTO batchShouldBackListDTO = new BatchShouldBackListDTO();
      if (!callbacklist.isEmpty()) {
        batchShouldBackListDTO = bankExportsBS.findTotalBankCallback(
            pagination, securityInfo);
      }
      List yearMonthlist = new ArrayList();
      List temp_list = bankExportsBS.getYearMonthList(securityInfo);
      List temp_banklist = securityInfo.getDkUserBankList();
      List banklist = new ArrayList();
      if (!temp_banklist.isEmpty()) {
        Iterator itr = temp_banklist.iterator();
        while (itr.hasNext()) {
          Userslogincollbank userslogincollbank = (Userslogincollbank) itr
              .next();
          banklist.add(new org.apache.struts.util.LabelValueBean(
              userslogincollbank.getCollBankName().toString(),
              userslogincollbank.getCollBankId().toString()));
        }
      }
      if (!temp_list.isEmpty()) {
        Iterator it = temp_list.iterator();
        while (it.hasNext()) {
          BatchShouldBackListDTO dto = (BatchShouldBackListDTO) it.next();
          yearMonthlist.add(new org.apache.struts.util.LabelValueBean(dto
              .getLoanKouYearmonth(), dto.getLoanKouYearmonth()));
        }
      }
      if (yearMonth == null) {
        yearMonth = "";
      }
      if (yearMonth == null || yearMonth.equals("")) {
        yearMonth = securityInfo.getUserInfo().getPlbizDate().substring(0, 6);
      }
      if (day == null || day.equals("")) {
        day = securityInfo.getUserInfo().getPlbizDate().substring(6, 8);
      }
      List dayList = BusiTools.getDayList(yearMonth);
      List temp_daylist = new ArrayList();
      if (!dayList.isEmpty()) {
        Iterator it = dayList.iterator();
        while (it.hasNext()) {
          Object obj = (Object) it.next();
          temp_daylist.add(new org.apache.struts.util.LabelValueBean(obj
              .toString(), obj.toString()));
        }
      }
      if (Integer.parseInt(day) < 10 && day.length() >= 2) {
        day = day.substring(1, 2);
      }
      af.setTemp_day(day);
      af.setMonthYear(yearMonth);
      af.setLoanBankId(loanBankId);
      if (batchNum != null && !batchNum.equals("sun_no_batch_num")) {
        af.setBatchNum(batchNum);
      } else {
        af.setBatchNum("");
      }
      String deletema = (String) request.getAttribute("deletema");
      request.setAttribute("deletema", deletema);
      request.setAttribute("yearMonth", yearMonth);
      request.setAttribute("callbacklist", callbacklist);
      request.setAttribute("yearMonthlist", yearMonthlist);
      request.setAttribute("DTO", batchShouldBackListDTO);
      request.setAttribute("bankExportsTaAF", af);
      request.getSession(true).setAttribute("daylist", temp_daylist);
      request.getSession(true).setAttribute("banklist", banklist);
      pagination.getQueryCriterions().put("pageList", callbacklist);
    } catch (BusinessException bex) {
      messages = new ActionMessages();
      messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(bex
          .getMessage(), false));
      saveErrors(request, messages);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return mapping.findForward(getForword());
  }

  protected String getForword() {
    return "bankexports";
  }

  private Pagination getPagination(String paginationKey,
      HttpServletRequest request) {
    Pagination pagination = (Pagination) request.getSession().getAttribute(
        paginationKey);
    if (pagination == null) {
      HashMap m = new HashMap();
      pagination = new Pagination(0, 10, 1, "loanFlowTail.loanKouAcc", "DESC",
          m);
      request.getSession().setAttribute(paginationKey, pagination);
    }

    return pagination;
  }

}
