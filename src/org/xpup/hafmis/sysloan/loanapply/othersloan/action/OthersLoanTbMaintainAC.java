package org.xpup.hafmis.sysloan.loanapply.othersloan.action;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.LookupDispatchAction;
import org.xpup.common.util.BSUtils;
import org.xpup.common.util.Pagination;
import org.xpup.hafmis.common.form.IdAF;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.sysloan.loanapply.othersloan.bsinterface.IOthersLoanBS;
import org.xpup.hafmis.sysloan.loanapply.othersloan.form.OthersLoanTbShowAF;

public class OthersLoanTbMaintainAC extends LookupDispatchAction {

  public static final String PAGINATION_KEY = "org.xpup.hafmis.sysloan.loanapply.othersloan.action.OthersLoanTbShowAC";

  protected Map getKeyMethodMap() {
    Map map = new HashMap();

    map.put("button.delete", "delete");
    map.put("button.update", "update");
    map.put("button.print", "print");
    return map;
  }

  public ActionForward delete(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response) {
    try {
      IOthersLoanBS othersLoanBS = (IOthersLoanBS) BSUtils.getBusinessService(
          "othersLoanBS", this, mapping.getModuleConfig());
      IdAF idAF = (IdAF) form;
      String id = (String) idAF.getId();
      othersLoanBS.deleteOthersLoan(id);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return mapping.findForward("othersLoanTbShowAC");
  }

  public ActionForward update(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response) {
    try {
      IdAF idAF = (IdAF) form;
      String id = (String) idAF.getId();
      request.setAttribute("id", id);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return mapping.findForward("othersLoanShowAC");
  }

  public ActionForward print(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response) {
    try {
      SecurityInfo securityInfo = (SecurityInfo) request.getSession()
          .getAttribute("SecurityInfo");
      Pagination pagination = getPagination(PAGINATION_KEY, request);
      IOthersLoanBS othersLoanBS = (IOthersLoanBS) BSUtils.getBusinessService(
          "othersLoanBS", this, mapping.getModuleConfig());
      OthersLoanTbShowAF othersLoanTbShowAF = new OthersLoanTbShowAF();
      othersLoanTbShowAF = othersLoanBS.queryOthersLoanListByCriterions(
          pagination, securityInfo);
      request.setAttribute("othersLoanprintAllList_wsh", othersLoanTbShowAF
          .getAllList());
      request.setAttribute("time", securityInfo.getUserInfo().getPlbizDate());
      request.setAttribute("opertname", securityInfo.getUserInfo().getRealName());
    } catch (Exception e) {
      e.printStackTrace();
    }
    return mapping.findForward("othersLoanTbPrint");
  }

  private Pagination getPagination(String paginationKey,
      HttpServletRequest request) {
    Pagination pagination = (Pagination) request.getSession().getAttribute(
        paginationKey);
    if (pagination == null) {
      pagination = new Pagination(0, 10, 1, "a.d", "DESC", new HashMap(0));
      request.getSession().setAttribute(paginationKey, pagination);
    }
    return pagination;
  }
}
