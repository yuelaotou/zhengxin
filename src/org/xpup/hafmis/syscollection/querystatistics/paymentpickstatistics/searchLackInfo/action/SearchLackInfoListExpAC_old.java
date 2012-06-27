package org.xpup.hafmis.syscollection.querystatistics.paymentpickstatistics.searchLackInfo.action;

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
import org.xpup.hafmis.syscollection.querystatistics.paymentpickstatistics.searchLackInfo.bsinterface.ISearchLackInfoBS;

public class SearchLackInfoListExpAC_old extends Action {

  public static final String PAGINATION_KEY = "org.xpup.hafmis.syscollection.querystatistics.paymentpickstatistics.searchLackInfo.action.ShowSearchLackInfoListAC";

  /**
   * 导出的方法
   * 
   * @param mapping
   * @param form
   * @param request
   * @param response
   * @return
   * @throws Exception
   */
  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    ActionMessages messages = null;
    try {
      String id = request.getParameter("id");
      String orgid = request.getParameter("orgid");
      String orgName = request.getParameter("orgName");
      String addPayMonth = request.getParameter("addPayMonth");
      addPayMonth = addPayMonth.substring(0,4)+addPayMonth.substring(5,7);
      ISearchLackInfoBS searchLackInfoBS = (ISearchLackInfoBS) BSUtils
          .getBusinessService("searchLackInfoBS", this, mapping
              .getModuleConfig());
      List expList = searchLackInfoBS.findOrgAddPayExpList_old(id, orgid,
          orgName, addPayMonth);
      if (expList.size() > 0) {
        messages = new ActionMessages();
        messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("导出成功！",
            false));
        saveErrors(request, messages);
        request.getSession().setAttribute("explist", expList);
        response.sendRedirect(request.getContextPath()
            + "/ExportServlet?ISCANSHU=false&EXP_NAME=orgaddpay_exp");
        return null;
      } else {
        messages = new ActionMessages();
        messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("没有数据！",
            false));
        saveErrors(request, messages);
      }

    } catch (BusinessException bex) {
      messages = new ActionMessages();
      messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("导出失败！"
          + bex.getMessage(), false));
      saveErrors(request, messages);
    }
    return mapping.findForward("to_searchLackInfo_list");
  }
}
