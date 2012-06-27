package org.xpup.hafmis.sysfinance.accounthandle.credencefillin.action;

import java.math.BigDecimal;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.xpup.common.util.BSUtils;
import org.xpup.common.util.Pagination;
import org.xpup.hafmis.sysfinance.accounthandle.credencefillin.bsinterface.ICredenceFillinBS;
import org.xpup.hafmis.sysfinance.accounthandle.credencefillin.dto.CredenceFillinIncomeExpenseDTO;
import org.xpup.hafmis.sysfinance.accounthandle.credencefillin.dto.CredenceFillinTbFindDTO;

/**
 * 显示收入，支出列表页面 CredenceFillinIncomeExpenseShowAC
 * 
 * @Version : v1.0
 * @author : 杨光
 * @Date : 2008.12.16
 */
public class CredenceFillinShowIncomeExpenseAC extends Action {

  public static final String PAGINATION_KEY = "org.xpup.hafmis.sysfinance.accounthandle.credencefillin.action.CredenceFillinIncomeExpenseShowAC";

  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    try {
      Pagination pagination = (Pagination) request.getSession().getAttribute(
          CredenceFillinTbShowAC.PAGINATION_KEY);
      CredenceFillinTbFindDTO credenceFillinTbFindDTO = (CredenceFillinTbFindDTO) pagination
          .getQueryCriterions().get("credenceFillinTbFindDTO");
      ICredenceFillinBS credenceFillinBS = (ICredenceFillinBS) BSUtils
          .getBusinessService("credenceFillinBS", this, mapping
              .getModuleConfig());
      String incomeOrExpense = request.getParameter("incomeOrExpense");
      List list = null;
      if (incomeOrExpense.equals("income")) {
        list = credenceFillinBS.getIncomeList(credenceFillinTbFindDTO);
      } else if (incomeOrExpense.equals("expense")) {
        list = credenceFillinBS.getExpenseList(credenceFillinTbFindDTO);
      }
      BigDecimal debit = new BigDecimal(0.00);
      BigDecimal credit = new BigDecimal(0.00);
      for (int i = 0; i < list.size(); i++) {
        CredenceFillinIncomeExpenseDTO dto = (CredenceFillinIncomeExpenseDTO) list
            .get(i);
        debit = debit.add(new BigDecimal(dto.getDebit()));
        credit = credit.add(new BigDecimal(dto.getCredit()));
      }
      request.getSession().setAttribute("sum_debit", debit);
      request.getSession().setAttribute("sum_credit", credit);
      request.setAttribute("main", "yes");
      request.setAttribute("incomeOrExpense", incomeOrExpense);
      request.setAttribute("list", list);
    } catch (Exception e) {
      e.printStackTrace();
      ActionMessages messages = new ActionMessages();
      messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("查询收入失败！",
          false));
      saveErrors(request, messages);
    }
    return mapping.findForward("credenceFillinIncomeExpenseShowAC");
  }
}
