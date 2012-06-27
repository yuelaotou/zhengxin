package org.xpup.hafmis.sysloan.loancallback.bankexports.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.sysloan.loancallback.bankexports.bsinterface.IBankExportsBS;
import org.xpup.hafmis.sysloan.loancallback.bankexports.form.BankExportsTaAF;

public class BankExportsTaCreatDataAC extends Action {

  public static final String PAGINATION_KEY = "org.xpup.hafmis.sysloan.loancallback.bankexports.action.BankexportsTaShowAC";

  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    ActionMessages messages = null;
    SecurityInfo securityInfo = (SecurityInfo) request.getSession()
        .getAttribute("SecurityInfo");
    String office = securityInfo.getUserInfo().getOfficeId().toString();
    String bizDate = securityInfo.getUserInfo().getBizDate().toString();
    String type_yg = "";
    try {
      BankExportsTaAF af = (BankExportsTaAF) form;
      String type_gjp = request.getParameter("type_gjp");
      type_yg = type_gjp;
      Pagination pagination = getPagination(BankExportsTaShowAC.PAGINATION_KEY,
          request);
      pagination.getQueryCriterions().put("headId", null);
      pagination.getQueryCriterions().put("contractId", null);
      pagination.getQueryCriterions().put("loanKouAcc", null);
      pagination.getQueryCriterions().put("borrowerName", null);
      pagination.getQueryCriterions().put("cardNum", null);
      pagination.getQueryCriterions().put("loanBankId", af.getLoanBankId());
      pagination.getQueryCriterions().put("yearMonth", af.getMonthYear());
      pagination.getQueryCriterions().put("day", af.getDay());
      pagination.getQueryCriterions().put("type_gjp", type_gjp);
      PaginationUtils.updatePagination(pagination, request);
      IBankExportsBS bankExportsBS = (IBankExportsBS) BSUtils
          .getBusinessService("bankExportsBS", this, mapping.getModuleConfig());

      String batchNum = bankExportsBS.createDataBankCallbackList(pagination,
          securityInfo, type_gjp);
      pagination.getQueryCriterions().put("batchNum", batchNum);
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
    return "bankExportsTaShowAC";
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
