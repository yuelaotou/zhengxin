package org.xpup.hafmis.sysfinance.accounthandle.credencefillin.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.math.BigDecimal;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.xpup.common.util.Pagination;
import org.xpup.common.util.PaginationUtils;
import org.xpup.hafmis.sysfinance.accounthandle.credencefillin.form.CredenceFillinIncomeExpenseAF;

/**
 * 显示收入，支出列表页面 CredenceFillinIncomeExpenseShowAC
 * 
 * @Version : v1.0
 * @author : 杨光
 * @Date : 2008.12.16
 */
public class CredenceFillinIncomeExpenseShowAC extends Action {

  public static final String PAGINATION_KEY = "org.xpup.hafmis.sysfinance.accounthandle.credencefillin.action.CredenceFillinIncomeExpenseShowAC";

  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    CredenceFillinIncomeExpenseAF credenceFillinIncomeExpenseAF = new CredenceFillinIncomeExpenseAF();
    String incomeOrExpense = (String)request.getAttribute("incomeOrExpense");
    BigDecimal sum_debit = (BigDecimal)request.getSession().getAttribute("sum_debit");
    BigDecimal sum_credit = (BigDecimal)request.getSession().getAttribute("sum_credit");
    credenceFillinIncomeExpenseAF.setIncomeOrExpense(incomeOrExpense);
    credenceFillinIncomeExpenseAF.setSum_debit(sum_debit);
    credenceFillinIncomeExpenseAF.setSum_credit(sum_credit);
    String main = (String) request.getAttribute("main");
    if (main != null && main.equals("yes")) {//第一次进来的
      List showList = (List) request.getAttribute("list");
      String paginationKey = getPaginationKey();
      Pagination pagination = (Pagination) request.getSession().getAttribute(
          paginationKey);
      if (pagination == null) {
        pagination = getPagination(paginationKey, request);
      } else {
        PaginationUtils.updatePagination(pagination, request);
      }
      if (showList.size() > pagination.getPageSize()) {
        credenceFillinIncomeExpenseAF.setList(showList.subList(0, pagination.getPageSize()));
      } else {
        credenceFillinIncomeExpenseAF.setList(showList);
      }
      request.setAttribute("credenceFillinIncomeExpenseAF",
          credenceFillinIncomeExpenseAF);
      pagination.setPage(0);
      pagination.setNrOfElements(showList.size());
      request.getSession().setAttribute("incomeExpenseList", showList);
    } else {// 分页过来的
      List incomeExpenseList = (List) request.getSession().getAttribute(
          "incomeExpenseList");
      String paginationKey = getPaginationKey();
      Pagination pagination = (Pagination) request.getSession().getAttribute(
          paginationKey);
      if (pagination == null) {
        pagination = getPagination(paginationKey, request);
      } else {
        PaginationUtils.updatePagination(pagination, request);
      }
      int page = pagination.getPage()-1;
      int pageSize = pagination.getPageSize();
      List showList = new ArrayList();
      if (page * pageSize + pageSize < incomeExpenseList.size()) {
        for (int i = page * pageSize; i < page * pageSize + pageSize; i++) {
          showList.add(incomeExpenseList.get(i));
        }
      } else {
        for (int i = page * pageSize; i < incomeExpenseList.size(); i++) {
          showList.add(incomeExpenseList.get(i));
        }
      }
      credenceFillinIncomeExpenseAF.setList(showList);
      request.setAttribute("credenceFillinIncomeExpenseAF",
          credenceFillinIncomeExpenseAF);
      pagination.setNrOfElements(incomeExpenseList.size());
    }
    return mapping.findForward("credencefillinshowincomeexpense");
  }

  private Pagination getPagination(String paginationKey,
      HttpServletRequest request) {
    Pagination pagination = (Pagination) request.getSession().getAttribute(
        paginationKey);
    if (pagination == null) {
      pagination = new Pagination(0, 10, 1, "res.fn120type", "DESC",
          new HashMap(0));
      request.getSession().setAttribute(paginationKey, pagination);
    }
    return pagination;
  }

  protected String getPaginationKey() {
    return PAGINATION_KEY;
  }
}
