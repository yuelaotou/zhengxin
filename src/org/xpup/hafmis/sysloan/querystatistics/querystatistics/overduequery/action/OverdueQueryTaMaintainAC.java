package org.xpup.hafmis.sysloan.querystatistics.querystatistics.overduequery.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.actions.LookupDispatchAction;
import org.xpup.common.util.BSUtils;
import org.xpup.common.util.Pagination;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.sysfinance.accmng.listacc.bsinterface.IListacctBS;
import org.xpup.hafmis.sysfinance.accmng.listacc.form.ListaccAF;
import org.xpup.hafmis.sysloan.querystatistics.querystatistics.overduequery.bsinterface.IOverdueQueryBS;
import org.xpup.hafmis.sysloan.querystatistics.querystatistics.overduequery.form.OverdueQueryTaAF;

public class OverdueQueryTaMaintainAC extends LookupDispatchAction {

  protected Map getKeyMethodMap() {
    HashMap map = new HashMap();
    map.put("button.print", "print");
    map.put("button.export", "export");
    return map;
  }

  public ActionForward print(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    OverdueQueryTaAF af = null;
    try {
      SecurityInfo securityInfo = (SecurityInfo) request.getSession()
          .getAttribute("SecurityInfo");
      IOverdueQueryBS overdueQueryBS = (IOverdueQueryBS) BSUtils
          .getBusinessService("overdueQueryBS", this, mapping.getModuleConfig());
      Pagination pagination = (Pagination) request.getSession().getAttribute(
          OverdueQueryTaShowAC.PAGINATION_KEY);
      af = overdueQueryBS.queryOverdueAllList(pagination, securityInfo);
      String bankName = (String) pagination.getQueryCriterions()
          .get("bankName");
      request.setAttribute("bankName", bankName == null ? "全部" : bankName);
      String bizDate = securityInfo.getUserInfo().getPlbizDate();
      request.setAttribute("printList", af.getList());
      request.setAttribute("bizDate", bizDate);
    } catch (Exception e) {
      e.printStackTrace();
    }
    request.setAttribute("overdueQueryTaAF", af);
    return mapping.findForward("to_overduequeryta_cell");
  }

  public ActionForward export(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    ActionMessages messages = null;
    OverdueQueryTaAF af = null;
    try {
      SecurityInfo securityInfo = (SecurityInfo) request.getSession()
          .getAttribute("SecurityInfo");
      IOverdueQueryBS overdueQueryBS = (IOverdueQueryBS) BSUtils
          .getBusinessService("overdueQueryBS", this, mapping.getModuleConfig());
      Pagination pagination = (Pagination) request.getSession().getAttribute(
          OverdueQueryTaShowAC.PAGINATION_KEY);
      af = overdueQueryBS.queryOverdueAllList(pagination, securityInfo);
      List expList = af.getList();
      if (expList != null && expList.size() > 0) {
        request.getSession().setAttribute("explist", expList);
        response.sendRedirect(request.getContextPath()
            + "/ExportServlet?ISCANSHU=false&EXP_NAME=overdue_exp");
      }
      return null;
    } catch (Exception e) {
      e.printStackTrace();
      messages = new ActionMessages();
      messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("导出失败！",
          false));
      saveErrors(request, messages);
    }
    return mapping.findForward("to_overduequeryta_show");
  }
}
