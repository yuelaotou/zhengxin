package org.xpup.hafmis.syscollection.tranmng.tranin.action;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;

import org.xpup.common.util.BSUtils;
import org.xpup.common.util.Pagination;
import org.xpup.common.util.PaginationUtils;
import org.xpup.hafmis.syscollection.common.biz.emppop.bsinterface.IEmpPopBS;
import org.xpup.hafmis.syscollection.tranmng.tranin.bsinterface.ITraninBS;

public class EmployeeShowAC extends Action {

  public static final String PAGINATION_KEY = "org.xpup.hafmis.syscollection.tranmng.tranin.action.EmployeeShowAC";

  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    ActionMessages messages = null;
    try {
      String orgid = null;
      String[] status = null;
      String cardnum = null;
      HttpSession session = request.getSession(false);
      if (request.getParameter("orgid") != null) {
        String orgiid = (String) request.getParameter("orgid");
        String statuss = request.getParameter("st");
        String cardnumm = request.getParameter("cardnum");
        if (statuss == null) {
          statuss = request.getSession().getAttribute("status").toString();
        }
        if (cardnum == null && cardnumm.equals("")) {
          cardnumm = request.getSession().getAttribute("cardnumm").toString();
        }
        status = new String[statuss.length()];
        for (int i = 0; i < statuss.length(); i++) {
          status[i] = statuss.substring(i, i + 1);
        }
        request.getSession().setAttribute("cardnumm", cardnumm);
        request.getSession().setAttribute("status", statuss);
        session.setAttribute("SL_org_id", orgiid);
      }
      orgid = (String) session.getAttribute("SL_org_id");
      String cardnummm = (String) session.getAttribute("cardnumm");
      String paginationKey = getPaginationKey();
      Pagination pagination = getPagination(paginationKey, request);
      pagination.getQueryCriterions().put("id", orgid);
      pagination.getQueryCriterions().put("status", status);
      pagination.getQueryCriterions().put("cardnum", cardnummm);
      modifyPagination(pagination);
      PaginationUtils.updatePagination(pagination, request);
      request.getSession().setAttribute(paginationKey, pagination);
      ITraninBS traninBS = (ITraninBS) BSUtils.getBusinessService("traninBS",
          this, mapping.getModuleConfig());
      List employees=traninBS.findEmployee(pagination);
      request.setAttribute("employees", employees);
      messages = new ActionMessages();
      messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("Ìí¼Ó³É¹¦£¡",
          false));
      saveErrors(request, messages);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return mapping.findForward("");
  }

  protected void modifyPagination(Pagination pagination) {
  }

  private Pagination getPagination(String paginationKey,
      HttpServletRequest request) {
    Pagination pagination = (Pagination) request.getSession().getAttribute(
        paginationKey);
    if (pagination == null) {
      pagination = new Pagination(0, 10, 1, "epa.Employee.id", "ASC",
          new HashMap(0));
      request.getSession().setAttribute(paginationKey, pagination);
    }
    return pagination;
  }

  protected String getPaginationKey() {
    return PAGINATION_KEY;
  }

}
