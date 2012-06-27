package org.xpup.hafmis.syscollection.accounthandle.clearaccount.action;

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
import org.xpup.common.util.PaginationUtils;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.syscollection.accounthandle.clearaccount.bsinterface.IclearAccountBS;
import org.xpup.hafmis.syscollection.accounthandle.clearaccount.form.ClearAccountDetailAF;
import org.xpup.hafmis.syscollection.systemmaintain.loanpara.bsinterface.ILoanDocNumDesignBS;

/**
 * @author 李鹏 2007-7-10
 */
public class ClearAccountTaWindowAC extends Action {

  public static final String PAGINATION_KEY = "org.xpup.hafmis.syscollection.accounthandle.clearaccount.action.ClearAccountTaWindowAC";

  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    ActionMessages messages = null;
    try {
      // 头表ID
      String headid = request.getParameter("headid");
      Pagination pagination = getPagination(PAGINATION_KEY, request);

      if (headid != null){
        pagination.getQueryCriterions().put("headid", headid);
      }
      saveToken(request);
      SecurityInfo securityInfo = (SecurityInfo) request.getSession()
          .getAttribute("SecurityInfo");
      PaginationUtils.updatePagination(pagination, request);
      IclearAccountBS clearaccountBS = (IclearAccountBS) BSUtils
          .getBusinessService("clearaccountBS", this, mapping.getModuleConfig());
      ILoanDocNumDesignBS loanDocNumDesignBS = (ILoanDocNumDesignBS) BSUtils
      .getBusinessService("loanDocNumDesignBS", this, mapping.getModuleConfig());
      ClearAccountDetailAF clearAccountDetailAF = clearaccountBS
          .findOrgHAFAccountFlowByID(pagination, securityInfo,loanDocNumDesignBS);

      request.setAttribute("otherlist", clearAccountDetailAF.getOtherList());
      request.setAttribute("ootherlist", clearAccountDetailAF.getList());
      request.setAttribute("clearAccountDetailAF", clearAccountDetailAF);

      pagination.getQueryCriterions().put("type", clearAccountDetailAF.getBiz_type());
      
      if ("汇缴".equals(clearAccountDetailAF.getBiz_type())) {
        return mapping.findForward("to_clearaccount_mx_A.jsp");
      }
      if ("单位补缴".equals(clearAccountDetailAF.getBiz_type())) {
        return mapping.findForward("to_clearaccount_mx_M.jsp");
      }
      if ("转出".equals(clearAccountDetailAF.getBiz_type())) {
        return mapping.findForward("to_clearaccount_mx_E.jsp");
      }
      if ("转入".equals(clearAccountDetailAF.getBiz_type())) {
        return mapping.findForward("to_clearaccount_mx_F.jsp");
      }
      if ("提取".equals(clearAccountDetailAF.getBiz_type())) {
        return mapping.findForward("to_clearaccount_mx_D.jsp");
      }
      if("挂账".equals(clearAccountDetailAF.getBiz_type())){
       return mapping.findForward("to_orgover_windowlist.jsp");
      }
      return mapping.findForward("show_window");
      
    } catch (BusinessException bex) {
      messages = new ActionMessages();
      messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
          "没有您要查询的信息！", false));
      saveErrors(request, messages);
    } catch (Exception ex) {
      ex.printStackTrace();
    }
    return mapping.findForward("show_window");
  }

  private Pagination getPagination(String paginationKey,
      HttpServletRequest request) {
    Pagination pagination = (Pagination) request.getSession().getAttribute(
        paginationKey);
    if (pagination == null) {
      pagination = new Pagination(0, 10, 1, "empHAFAccountFlow.id", "ASC",
          new HashMap(0));
      request.getSession().setAttribute(paginationKey, pagination);
    }
    return pagination;
  }
}
