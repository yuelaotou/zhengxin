package org.xpup.hafmis.sysloan.accounthandle.bizcheck.action;

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
import org.xpup.hafmis.common.form.IdAF;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.sysloan.accounthandle.bizcheck.bsinterface.IBizCheckBS;

/**
 * @author 吴迪 2007-10-6
 */
public class BizcheckMaintainAC extends LookupDispatchAction {

  protected Map getKeyMethodMap() {
    Map map = new HashMap();
    map.put("button.check.through", "checkthrough");
    map.put("button.checkall", "checkall");
    map.put("button.del.check", "delcheck");
    map.put("button.del.checkall", "delcheckall");
    return map;
  }

  // 账务处理—业务复核—复核通过
  public ActionForward checkthrough(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    ActionMessages messages = null;

    try {
      SecurityInfo securityInfo = (SecurityInfo) request.getSession()
          .getAttribute("SecurityInfo");

      IdAF idaf = (IdAF) form;
      String[] rowArray = idaf.getRowArray();
      IBizCheckBS bizCheckBS = (IBizCheckBS) BSUtils.getBusinessService(
          "bizCheckBS", this, mapping.getModuleConfig());
      bizCheckBS.updateBizSTcheckthrough(rowArray, securityInfo);
    } catch (BusinessException bex) {
      messages = new ActionMessages();
      messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(bex
          .getMessage(), false));
      saveErrors(request, messages);
    }
    return mapping.findForward("bizCheckForwardURLAC");
  }

  // 账务处理—业务复核—撤消复核
  public ActionForward delcheck(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    ActionMessages messages = null;
    try {

      SecurityInfo securityInfo = (SecurityInfo) request.getSession()
          .getAttribute("SecurityInfo");
      IdAF idaf = (IdAF) form;
      String[] rowArray = idaf.getRowArray();
      IBizCheckBS bizCheckBS = (IBizCheckBS) BSUtils.getBusinessService(
          "bizCheckBS", this, mapping.getModuleConfig());
      bizCheckBS.updateBizSTdelcheck(rowArray, securityInfo);
    } catch (BusinessException bex) {
      messages = new ActionMessages();
      messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(bex
          .getMessage(), false));
      saveErrors(request, messages);
    }
    return mapping.findForward("bizCheckForwardURLAC");
  }

  // 账务处理—业务复核—批量复核
  public ActionForward checkall(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    ActionMessages messages = null;
    try {

      SecurityInfo securityInfo = (SecurityInfo) request.getSession()
          .getAttribute("SecurityInfo");
      String paginationKey = getPaginationKey();
      Pagination pagination = (Pagination) request.getSession().getAttribute(
          paginationKey);
      IBizCheckBS bizCheckBS = (IBizCheckBS) BSUtils.getBusinessService(
          "bizCheckBS", this, mapping.getModuleConfig());
      bizCheckBS.updateBizSTcheckall(securityInfo, pagination);
    } catch (BusinessException bex) {
      messages = new ActionMessages();
      messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(bex
          .getMessage(), false));
      saveErrors(request, messages);
    }
    return mapping.findForward("bizCheckForwardURLAC");
  }

  // 账务处理—业务复核—撤消批量复核
  public ActionForward delcheckall(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    ActionMessages messages = null;
    try {

      SecurityInfo securityInfo = (SecurityInfo) request.getSession()
          .getAttribute("SecurityInfo");
      String paginationKey = getPaginationKey();
      Pagination pagination = (Pagination) request.getSession().getAttribute(
          paginationKey);
      IBizCheckBS bizCheckBS = (IBizCheckBS) BSUtils.getBusinessService(
          "bizCheckBS", this, mapping.getModuleConfig());
      bizCheckBS.updateBizSTdelcheckall(securityInfo, pagination);
    } catch (BusinessException bex) {
      messages = new ActionMessages();
      messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(bex
          .getMessage(), false));
      saveErrors(request, messages);
    }
    return mapping.findForward("bizCheckForwardURLAC");
  }

  protected String getPaginationKey() {
    return BizCheckShowListAC.PAGINATION_KEY;
  }

}