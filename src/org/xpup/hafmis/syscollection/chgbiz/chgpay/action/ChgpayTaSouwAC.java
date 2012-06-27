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

/**
 * ÎâºéÌÎ 2007.6.27 @
 */
public class ChgpayTaSouwAC extends Action {
  public static final String PAGINATION_KEY = "org.xpup.hafmis.syscollection.chgbiz.chgpay.action.ChgpayTaSouwAC";

  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    ActionMessages messages = null;
    ChgpayListAF chgpayListAF = new ChgpayListAF();
    List list = null;
    Pagination pagination = getPagination(PAGINATION_KEY, request);
    PaginationUtils.updatePagination(pagination, request);
    String type = (String) pagination.getQueryCriterions().get("type");
    String orgid = (String) pagination.getQueryCriterions().get("org.id");
    SecurityInfo securityInfo=(SecurityInfo)request.getSession().getAttribute("SecurityInfo");
    pagination.getQueryCriterions().put("SecurityInfo", securityInfo);
    try {
 
      IChgpayBS chgpayBS = (IChgpayBS) BSUtils.getBusinessService("chgpayBS",
          this, mapping.getModuleConfig());
      chgpayListAF = chgpayBS.findChgpayList(pagination);

      if (chgpayListAF == null) {
        chgpayListAF = new ChgpayListAF();
      }
      // ÊÇ·ñÏÔÊ¾°´Å¥²éÑ¯
      if (type == null) {
        type = "1";
        chgpayListAF.setType(type);
      }

      list = chgpayListAF.getList();
      if (list != null && list.size() > 0) {

        chgpayListAF.setListCount("1");
      }
      pagination.getQueryCriterions().put("pageList", list);
      chgpayListAF.setType(type);
      request.setAttribute("type", chgpayListAF.getType());
      request.setAttribute("statetype", chgpayListAF.getStatetype());
      request.setAttribute("chgpayListAF", chgpayListAF);
      int typetem=securityInfo.getIsOrgEdition();
      request.setAttribute("typetem", typetem+"");

      // chgpayListAF.setType111("1");
      //chgpayListAF.reset(mapping, request)
    } catch (BusinessException bex) {

      chgpayListAF=new ChgpayListAF();
  
      request.setAttribute("chgpayListAF", chgpayListAF);
      messages = new ActionMessages();
      messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(bex
          .getMessage(), false));
      int typetem=securityInfo.getIsOrgEdition();
      request.setAttribute("typetem", typetem+"");
      saveErrors(request, messages);
    } catch (Exception ex) {
      int typetem=securityInfo.getIsOrgEdition();
      request.setAttribute("typetem", typetem+"");
      saveErrors(request, messages);
      ex.printStackTrace();
    }
    return mapping.findForward("to_chgpay_list.jsp");
  }

  private Pagination getPagination(String paginationKey,
      HttpServletRequest request) {
    Pagination pagination = (Pagination) request.getSession().getAttribute(
        paginationKey);
    if (pagination == null) {
      pagination = new Pagination(0, 10, 1, "chgPaymentTail.id", "ASC",
          new HashMap(0));
      request.getSession().setAttribute(paginationKey, pagination);
    }

    return pagination;
  }
}
