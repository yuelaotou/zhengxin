package org.xpup.hafmis.sysloan.loancallback.loancallback.action;

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
import org.xpup.hafmis.common.util.BusiConst;
import org.xpup.hafmis.sysloan.loancallback.loancallback.bsinterface.ILoancallbackBS;
import org.xpup.hafmis.sysloan.loancallback.loancallback.dto.ShouldBackListDTO;
import org.xpup.hafmis.sysloan.loancallback.loancallback.form.LoancallbackTaAF;

public class LoancallbackTbShowWindowAC extends Action {
  public static final String PAGINATION_KEY = "org.xpup.hafmis.sysloan.loancallback.loancallback.action.LoancallbackTbShowWindowAC";

  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    ActionMessages messages = null;
    try {
      LoancallbackTaAF loancallbackTaAF = new LoancallbackTaAF();
      Pagination pagination = getPagination(
          LoancallbackTbShowWindowAC.PAGINATION_KEY, request);
      PaginationUtils.updatePagination(pagination, request);
      ILoancallbackBS loancallbackBS = (ILoancallbackBS) BSUtils
          .getBusinessService("loancallbackBS", this, mapping.getModuleConfig());
      String contractId = (String) request.getAttribute("contractId");
      String type = (String) request.getAttribute("type");
      String headId = (String) request.getAttribute("headId");
      if (contractId != null) {
        pagination.getQueryCriterions().put("contractId", contractId);
      }
      if (type != null) {
        pagination.getQueryCriterions().put("type", type);
      }
      if (headId != null) {
        pagination.getQueryCriterions().put("headId", headId);
      }
      type = (String) pagination.getQueryCriterions().get("type");
      request.setAttribute("headId", pagination.getQueryCriterions().get(
          "headId"));
      if (type.equals(BusiConst.PLBUSINESSTYPE_SOMERECOVER + "")) {// 批量回收转到银行导入列表页面
        List list = loancallbackBS.findCallbackBatchMX(pagination);
        ShouldBackListDTO dto = loancallbackBS
            .findCallbackBatchMXTotal(pagination);
        request.setAttribute("batchList", list);
        request.setAttribute("totaldto", dto);
        // request.getSession().setAttribute("BatchList", list);
        return mapping.findForward("loancallback_lb");
      } else {// 单笔回收转到办理页面
        loancallbackTaAF = loancallbackBS.findCallbackInfoMX(pagination);
        request.setAttribute("loancallbackTaAF", loancallbackTaAF);
        return mapping.findForward("loancallback_jy");
      }
    } catch (BusinessException bex) {
      messages = new ActionMessages();
      messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(bex
          .getMessage(), false));
      saveErrors(request, messages);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return mapping.findForward("loancallbackTbShow");
  }

  private Pagination getPagination(String paginationKey,
      HttpServletRequest request) {
    Pagination pagination = (Pagination) request.getSession().getAttribute(
        paginationKey);
    if (pagination == null) {
      pagination = new Pagination();
      request.getSession().setAttribute(paginationKey, pagination);
    }

    return pagination;
  }
}