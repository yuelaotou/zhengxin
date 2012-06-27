package org.xpup.hafmis.syscollection.querystatistics.logsearch.businesslogsearch.action;

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
import org.xpup.hafmis.syscollection.accounthandle.bizcheck.bsinterface.IBizcheckBS;
import org.xpup.hafmis.syscollection.accounthandle.bizcheck.form.BizcheckDetailAF;
import org.xpup.hafmis.syscollection.querystatistics.logsearch.businesslogsearch.bsinterface.IBusinesslogsearchBS;
 

/**
 * @author 李鹏 2007-7-10
 */
public class LogSearchTaWindowAC extends Action {

  public static final String PAGINATION_KEY = "org.xpup.hafmis.syscollection.accounthandle.bizcheck.action.BizcheckTaWindowAC";
  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    ActionMessages messages = null;
    BizcheckDetailAF bizcheckDetailAF = null;
    try {
      // 头表ID
      String headid = request.getParameter("headid");
      String bizType = request.getParameter("bizType");
      Pagination pagination = getPagination(PAGINATION_KEY, request);
      if (headid != null){
        pagination.getQueryCriterions().put("headid", headid);
      }
      if (bizType != null&&!bizType.equals("")){
        pagination.getQueryCriterions().put("bizType", bizType);
      }
      saveToken(request);
      SecurityInfo securityInfo = (SecurityInfo) request.getSession()
          .getAttribute("SecurityInfo");
      PaginationUtils.updatePagination(pagination, request);
      IBusinesslogsearchBS businesslogsearchBS = (IBusinesslogsearchBS) BSUtils
      .getBusinessService("businesslogsearchBS", this, mapping.getModuleConfig()); 
      bizcheckDetailAF=businesslogsearchBS.findOrgHAFAccountFlowByID(pagination,securityInfo);
      pagination.getQueryCriterions().put("type", bizcheckDetailAF.getBiz_type());
      request.getSession().setAttribute("bizcheckDetailAF", bizcheckDetailAF);
      request.getSession().setAttribute("otherlist", bizcheckDetailAF.getOtherList());
      request.getSession().setAttribute("ootherlist", bizcheckDetailAF.getList());

      pagination.getQueryCriterions().put("type", bizcheckDetailAF.getBiz_type());
      
      if ("汇缴".equals(bizcheckDetailAF.getBiz_type())) {
        return mapping.findForward("to_logsearch_mx_A.jsp");
      }
      if ("单位补缴".equals(bizcheckDetailAF.getBiz_type())) {
        return mapping.findForward("to_logsearch_mx_M.jsp");
      }
      if ("转出".equals(bizcheckDetailAF.getBiz_type())) {
        return mapping.findForward("to_logsearch_mx_E.jsp");
      }
      if ("转入".equals(bizcheckDetailAF.getBiz_type())) {
        return mapping.findForward("to_logsearch_mx_F.jsp");
      }
      if ("提取".equals(bizcheckDetailAF.getBiz_type())) {
        return mapping.findForward("to_logsearch_mx_D.jsp");
      }
      if("挂账".equals(bizcheckDetailAF.getBiz_type())){
       return mapping.findForward("to_orgover_windowlist.jsp");
      }
      return mapping.findForward("to_bizcheck_windowlist.jsp");
      
    } catch (BusinessException bex) {
      messages = new ActionMessages();
      messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
          "没有您要查询的信息！", false));
      saveErrors(request, messages);
    } catch (Exception ex) {
      ex.printStackTrace();
    }
    return mapping.findForward("to_bizcheck_windowlist.jsp");
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
