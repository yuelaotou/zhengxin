package org.xpup.hafmis.sysloan.specialbiz.bailclearinterest.action;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.actions.LookupDispatchAction;
import org.xpup.common.exception.BusinessException;
import org.xpup.common.util.BSUtils;
import org.xpup.common.util.Pagination;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.sysloan.specialbiz.bailclearinterest.bsinterface.IBailClearInterestBS;

/**
 * @author 王野 2007-10-05
 */
public class BailClearInterestTaMaintainAC extends LookupDispatchAction {

  protected Map getKeyMethodMap() {
    Map map = new HashMap();
    map.put("button.in.clearaccountall", "clearaccountall");
    return map;
  }

  /**
   * 全部结息
   * 
   * @author 王野 2007-10-06
   * @param mapping
   * @param form
   * @param request
   * @param response
   * @return
   */
  public ActionForward clearaccountall(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response) {
    ActionMessages messages = null;
    try {
      Pagination pagination = (Pagination) request.getSession().getAttribute(
          BailClearInterestTaShowAC.PAGINATION_KEY);
      SecurityInfo securityInfo = (SecurityInfo) request.getSession()
          .getAttribute("SecurityInfo");
      IBailClearInterestBS bailClearInterestBS = (IBailClearInterestBS) BSUtils
          .getBusinessService("bailClearInterestBS", this, mapping
              .getModuleConfig());
      bailClearInterestBS.bailClearInterestTa(pagination, securityInfo);
      request.setAttribute("URL", "bailClearInterestTbShowAC.do");
      request.getSession().setAttribute(BailClearInterestTaShowAC.PAGINATION_KEY, null);
    } catch (BusinessException bex) {
      messages = new ActionMessages();
      messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(bex
          .getMessage(), false));
      saveErrors(request.getSession(), messages);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return mapping.findForward("bailClearInterestTaShowAC");
  }

}
