package org.xpup.hafmis.syscollection.chgbiz.chgpay.action;

import java.util.HashMap;
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
import org.xpup.hafmis.syscollection.chgbiz.chgpay.bsinterface.IChgpayBS;
import org.xpup.hafmis.syscollection.chgbiz.chgpay.form.ChgpayListAF;
import org.xpup.hafmis.syscollection.chgbiz.chgslarybase.bsinterface.IChgslarybaseBS;
import org.xpup.hafmis.syscollection.chgbiz.chgslarybase.form.ChgslarybaseListAF;
import org.xpup.hafmis.syscollection.common.domain.entity.ChgPaymentTail;

/**
 * ÎâºéÌÎ 2007.6.27 @
 */
public class ChgpayTbSouwAC extends Action {
  public static final String PAGINATION_KEY = "org.xpup.hafmis.syscollection.chgbiz.chgpay.action.ChgpayTbSouwAC";

  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    ActionMessages messages = null;
    ChgpayListAF chgpayListAF = null;
    List list = null;
    Pagination pagination = getPagination(PAGINATION_KEY, request);
    PaginationUtils.updatePagination(pagination, request);
    SecurityInfo securityInfo = (SecurityInfo) request.getSession()
        .getAttribute("SecurityInfo");
    pagination.getQueryCriterions().put("SecurityInfo", securityInfo);
    try {
      IChgpayBS chgpayBS = (IChgpayBS) BSUtils.getBusinessService("chgpayBS",
          this, mapping.getModuleConfig());
      chgpayListAF = chgpayBS.findOrgChgpayList(pagination);
      int typetem = securityInfo.getIsOrgEdition();
      request.setAttribute("typetem", typetem + "");
      if (chgpayListAF == null) {
        chgpayListAF = new ChgpayListAF();
      }
      request.setAttribute("chgpayListAF", chgpayListAF);
      list = chgpayListAF.getList();
      if (list.size() > 0 && list != null) {

        chgpayListAF.setListCount("1");
      }
      pagination.getQueryCriterions().put("pageList", list);
      int type = securityInfo.getIsOrgEdition();
      request.setAttribute("type", type + "");
    } catch (BusinessException bex) {
      messages = new ActionMessages();
      messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(bex
          .getMessage(), false));
      saveErrors(request, messages);
    } catch (Exception ex) {
      ex.printStackTrace();
    }
    return mapping.findForward("to_chgpay_tblist.jsp");
  }

  private Pagination getPagination(String paginationKey,
      HttpServletRequest request) {
    Pagination pagination = (Pagination) request.getSession().getAttribute(
        paginationKey);
    if (pagination == null) {
      HashMap m = new HashMap();
      pagination = new Pagination(0, 10, 1, "chgPaymentPayment.chgStatus ASC,chgPaymentPayment.id DESC", "", m);
      request.getSession().setAttribute(paginationKey, pagination);
    }

    return pagination;
  }
}
