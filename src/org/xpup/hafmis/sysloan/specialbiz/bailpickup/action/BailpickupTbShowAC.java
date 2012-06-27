package org.xpup.hafmis.sysloan.specialbiz.bailpickup.action;

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
import org.xpup.hafmis.common.util.BusiConst;
import org.xpup.hafmis.common.util.BusiTools;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.sysloan.specialbiz.bailpickup.bsinterface.IBailpickupBS;
import org.xpup.hafmis.sysloan.specialbiz.bailpickup.dto.BailpickupTaDTO;
import org.xpup.hafmis.sysloan.specialbiz.bailpickup.dto.BailpickupTbDTO;
import org.xpup.hafmis.sysloan.specialbiz.bailpickup.form.BailpickupTbAF;
/**
 * 
 * @author yuqf
 *2007-10-16
 */
public class BailpickupTbShowAC extends Action{
  public static final String PAGINATION_KEY = "org.xpup.hafmis.sysloan.specialbiz.bailpickup.action.BailpickupTbShowAC";
  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    // TODO Auto-generated method stub]
    Pagination pagination = null;
    ActionMessages messages = null;
    BailpickupTbDTO bailpickupTbDTO = new BailpickupTbDTO();
    SecurityInfo securityInfo = (SecurityInfo) request.getSession()
        .getAttribute("SecurityInfo");
    pagination = getPagination(PAGINATION_KEY, request);
    PaginationUtils.updatePagination(pagination, request);
    IBailpickupBS bailpickupBS = (IBailpickupBS) BSUtils.getBusinessService("bailpickupBS", this, mapping.getModuleConfig());
    try{
      bailpickupTbDTO = bailpickupBS.tbQuery(pagination, securityInfo);
    }catch (BusinessException bex) {
      messages = new ActionMessages();
      messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(bex
          .getMessage(), false));
      saveErrors(request, messages);
    }
   
    request.setAttribute("theBailpickupTbDTO", bailpickupTbDTO);
    return mapping.findForward("to_bailpickupTb");
  }

  private Pagination getPagination(String paginationKey,
      HttpServletRequest request) {
    Pagination pagination = (Pagination) request.getSession().getAttribute(
        paginationKey);
    if (pagination == null) {
      pagination = new Pagination(0, 10, 1, "t3.flow_head_id ", "DESC",
          new HashMap(0));
      request.getSession().setAttribute(paginationKey, pagination);
    }

    return pagination;
  }
}
