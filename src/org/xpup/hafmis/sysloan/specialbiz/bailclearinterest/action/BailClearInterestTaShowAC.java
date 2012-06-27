package org.xpup.hafmis.sysloan.specialbiz.bailclearinterest.action;

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
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.sysloan.specialbiz.bailclearinterest.bsinterface.IBailClearInterestBS;
import org.xpup.hafmis.sysloan.specialbiz.bailclearinterest.form.BailClearInterestTaAF;
import org.xpup.security.common.domain.Userslogincollbank;

/**
 * @author 王野 2007-10-05
 */
public class BailClearInterestTaShowAC extends Action {

  public static final String PAGINATION_KEY = "org.xpup.hafmis.sysloan.specialbiz.bailclearinterest.action.BailClearInterestTaShowAC";

  /**
   * 办理保证金结息
   */
  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response) {
    ActionMessages messages = null;
    try {
      SecurityInfo securityInfo = (SecurityInfo) request.getSession()
          .getAttribute("SecurityInfo");
      Pagination pagination = getPagination(PAGINATION_KEY, request);
      PaginationUtils.updatePagination(pagination, request);
      if (securityInfo != null && !securityInfo.equals("")) {
        IBailClearInterestBS bailClearInterestBS = (IBailClearInterestBS) BSUtils
            .getBusinessService("bailClearInterestBS", this, mapping
                .getModuleConfig());
        BailClearInterestTaAF bailClearInterestTaAF = new BailClearInterestTaAF();
        if (pagination.getQueryCriterions().size() != 0) {
          bailClearInterestTaAF = bailClearInterestBS
              .findBailClearInterestTaListByCriterions(pagination, securityInfo);
        }
        // 收款银行下拉框--开始
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
        // 收款银行下拉框--结束
        request.setAttribute("bailClearInterestTaAF", bailClearInterestTaAF);
      }
    } catch (BusinessException bex) {
      messages = new ActionMessages();
      messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(bex
          .getMessage(), false));
      saveErrors(request, messages);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return mapping.findForward("to_bailclearinterest_showta");
  }

  private Pagination getPagination(String paginationKey,
      HttpServletRequest request) {
    Pagination pagination = (Pagination) request.getSession().getAttribute(
        paginationKey);
    if (pagination == null) {
      pagination = new Pagination(0, 10, 1, "p111.loan_bank_id", "DESC",
          new HashMap(0));
      request.getSession().setAttribute(paginationKey, pagination);
    }
    return pagination;
  }
}
