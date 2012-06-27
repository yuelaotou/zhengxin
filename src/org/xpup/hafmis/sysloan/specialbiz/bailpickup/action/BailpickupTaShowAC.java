package org.xpup.hafmis.sysloan.specialbiz.bailpickup.action;

import java.util.HashMap;

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
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.sysloan.specialbiz.bailpickup.bsinterface.IBailpickupBS;
import org.xpup.hafmis.sysloan.specialbiz.bailpickup.dto.BailpickupTaDTO;

/**
 * @author yuqf 2007-10-13 
 */
public class BailpickupTaShowAC extends Action {
  public static final String PAGINATION_KEY = "org.xpup.hafmis.sysloan.specialbiz.bailpickup.action.BailpickupTaShowAC";

  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    // TODO Auto-generated method stub]
    Pagination pagination = null;
    ActionMessages messages = null;
    SecurityInfo securityInfo = (SecurityInfo) request.getSession()
        .getAttribute("SecurityInfo");
    pagination = getPagination(PAGINATION_KEY, request);

    IBailpickupBS bailpickupBS = (IBailpickupBS) BSUtils.getBusinessService("bailpickupBS", this, mapping.getModuleConfig());
    BailpickupTaDTO bailpickupTaDTO = null;
    try {
     
        String id = request.getParameter("loanKouAcc");
        if (id != null && !"".equals(id)) {
          bailpickupTaDTO = new BailpickupTaDTO();
          bailpickupTaDTO = bailpickupBS
              .ajaxQuery(id, pagination, securityInfo);
          bailpickupTaDTO.setIsDisabled("1");
        } else {
          bailpickupTaDTO = new BailpickupTaDTO();
          bailpickupTaDTO.setIsDisabled("0");// 确定按钮禁用
        }
    } catch (BusinessException bex) {
      messages = new ActionMessages();
      messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(bex
          .getMessage(), false));
      saveErrors(request, messages);
    }
    request.setAttribute("theBailpickupTaDTO", bailpickupTaDTO);
    return mapping.findForward("to_bailpickupTa");
  }

  private Pagination getPagination(String paginationKey,
      HttpServletRequest request) {
    Pagination pagination = (Pagination) request.getSession().getAttribute(
        paginationKey);
    if (pagination == null) {
      pagination = new Pagination(0, 10, 1, null, "ASC",
          new HashMap(0));
      request.getSession().setAttribute(paginationKey, pagination);
    }

    return pagination;
  }
}
