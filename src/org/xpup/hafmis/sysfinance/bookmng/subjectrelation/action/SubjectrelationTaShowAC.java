package org.xpup.hafmis.sysfinance.bookmng.subjectrelation.action;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.xpup.common.util.BSUtils;
import org.xpup.common.util.Pagination;
import org.xpup.common.util.PaginationUtils;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.sysfinance.bookmng.subjectrelation.bsinterface.ISubjectrelationBS;
import org.xpup.hafmis.sysfinance.bookmng.subjectrelation.form.SubjectrelationTaAF;

public class SubjectrelationTaShowAC extends Action {
  public static final String PAGINATION_KEY = "org.xpup.hafmis.sysfinance.bookmng.subjectrelation.action.SubjectrelationTaShowAC";

  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    // TODO Auto-generated method stub
    Pagination pagination = getPagination(
        SubjectrelationTaShowAC.PAGINATION_KEY, request);
    PaginationUtils.updatePagination(pagination, request);
    SecurityInfo securityInfo = (SecurityInfo) request.getSession()
    .getAttribute("SecurityInfo");
    SubjectrelationTaAF subjectrelationTaAF = new SubjectrelationTaAF();
    String subjectCode = "";
    List list = null;
    String type = (String) request.getAttribute("type");
    if (!"1".equals(type)) {
      subjectCode = (String) request.getSession().getAttribute("subjectCode");
      if (subjectCode != null && !"".equals(subjectCode.trim())) {
        subjectCode = (String) request.getSession().getAttribute("subjectCode");
        subjectrelationTaAF.setSubjectCode(subjectCode);
      }
      String subjectName = "";
      subjectName = (String) request.getSession().getAttribute("subjectName");
      if (subjectName != null && !"".equals(subjectName.trim())) {
        subjectName = (String) request.getSession().getAttribute("subjectName");
        subjectrelationTaAF.setSubjectName(subjectName);
      }
      String balanceDirection = "";
      balanceDirection = (String) request.getSession().getAttribute(
          "balanceDirection");
      if (balanceDirection != null && !"".equals(balanceDirection.trim())) {
        balanceDirection = (String) request.getSession().getAttribute(
            "balanceDirection");
        subjectrelationTaAF.setBalanceDirection(balanceDirection);
      }
      BigDecimal balance = new BigDecimal(0.00);
      balance = (BigDecimal) request.getSession().getAttribute("balance");
      if (balance != null && !"".equals(balance)) {
        balance = (BigDecimal) request.getSession().getAttribute("balance");
        subjectrelationTaAF.setBalance(balance);
      }
      String firstSubjectCode = "";
      firstSubjectCode = (String) request.getSession().getAttribute(
          "firstSubjectCode");
      if (firstSubjectCode != null && !"".equals(firstSubjectCode.trim())) {
        firstSubjectCode = (String) request.getSession().getAttribute(
            "firstSubjectCode");
        subjectrelationTaAF.setFirstSubjectCode(firstSubjectCode);
      }
      String calculRelaType = "";
      calculRelaType = (String) request.getSession().getAttribute(
          "calculRelaType");
      if (calculRelaType != null && !"".equals(calculRelaType.trim())) {
        calculRelaType = (String) request.getSession().getAttribute(
            "calculRelaType");
        subjectrelationTaAF.setCalculRelaType(calculRelaType);
      } else {
        subjectrelationTaAF.setCalculRelaType("");
      }
      ISubjectrelationBS subjectrelationBS = (ISubjectrelationBS) BSUtils
          .getBusinessService("subjectrelationBS", this, mapping
              .getModuleConfig());
      list = subjectrelationBS.querySubejectRelationTaList(subjectCode,
          calculRelaType, pagination,securityInfo.getBookId());
      subjectrelationTaAF.setList(list);
    }
    request.setAttribute("subjectrelationTaAF", subjectrelationTaAF);
    return mapping.findForward("to_show_subjectrelationta");
  }

  private Pagination getPagination(String paginationKey,
      HttpServletRequest request) {
    Pagination pagination = (Pagination) request.getSession().getAttribute(
        paginationKey);
    if (pagination == null) {
      HashMap m = new HashMap();
      pagination = new Pagination(0, 10, 1, "", "", m);
      request.getSession().setAttribute(paginationKey, pagination);
    }
    return pagination;
  }
}
