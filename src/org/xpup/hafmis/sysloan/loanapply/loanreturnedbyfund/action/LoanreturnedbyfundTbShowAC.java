package org.xpup.hafmis.sysloan.loanapply.loanreturnedbyfund.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
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
import org.xpup.common.util.Pagination;
import org.xpup.common.util.PaginationUtils;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.sysloan.loanapply.loanreturnedbyfund.bsinterface.ILoanreturnedbyfundBS;
import org.xpup.hafmis.sysloan.loanapply.loanreturnedbyfund.form.LoanreturnedbyfundTbAF;
import org.xpup.security.common.domain.Userslogincollbank;

public class LoanreturnedbyfundTbShowAC extends Action {
  public static final String PAGINATION_KEY = "org.xpup.hafmis.sysloan.loanapply.loanreturnedbyfund.action.LoanreturnedbyfundTbShowAC";

  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception, BusinessException {
    // TODO Auto-generated method stub
    ActionMessages messages = null;
    try {
      SecurityInfo securityInfo = (SecurityInfo) request.getSession()
          .getAttribute("SecurityInfo");
      LoanreturnedbyfundTbAF loanreturnedbyfundTbAF = new LoanreturnedbyfundTbAF();
      Pagination pagination = getPagination(
          LoanreturnedbyfundTbShowAC.PAGINATION_KEY, request);
      PaginationUtils.updatePagination(pagination, request);
      ILoanreturnedbyfundBS loanreturnedbyfundBS = (ILoanreturnedbyfundBS) BSUtils
          .getBusinessService("loanreturnedbyfundBS", this, mapping
              .getModuleConfig());
      // 插入下拉列表银行
      List temp_banklist = securityInfo.getDkUserBankList();
      List banklist = new ArrayList();
      if (!temp_banklist.isEmpty()) {
        Iterator itr = temp_banklist.iterator();
        while (itr.hasNext()) {
          Userslogincollbank userslogincollbank = (Userslogincollbank) itr
              .next();
          banklist.add(new org.apache.struts.util.LabelValueBean(
              userslogincollbank.getCollBankName().toString(),
              userslogincollbank.getCollBankId().toString()));
        }
      }
      request.getSession(true).setAttribute("banklist", banklist);
      List list = loanreturnedbyfundBS.LoanreturnedbyfundTbFind(pagination,
          securityInfo);
      String str[] = new String[2];
      str = loanreturnedbyfundBS.findCount(pagination, securityInfo);
      if (str[0] != null) {
        loanreturnedbyfundTbAF.setCount(str[0]);
      }
      if (str[1] != null) {
        loanreturnedbyfundTbAF.setPersonCount(str[1]);
      }
      loanreturnedbyfundTbAF.setList(list);
      List countList = loanreturnedbyfundBS.LoanreturnedbyfundTbCountListFind(
          pagination, securityInfo);
      pagination.setNrOfElements(countList.size());
      request.getSession().setAttribute("cellList", countList);
      request.setAttribute("loanreturnedbyfundTbAF", loanreturnedbyfundTbAF);
      if (request.getAttribute("error") != null) {
        messages = new ActionMessages();
        String a = (String) request.getAttribute("error");
        messages
            .add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(a, false));
        saveErrors(request, messages);
        throw new BusinessException(a);
      }
    } catch (BusinessException e) {
      messages = new ActionMessages();
      String a = (String) request.getAttribute("error");
      messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(a, false));
      saveErrors(request, messages);

      return mapping.findForward("to_showloanreturnedbyfundTb");
    } catch (Exception e) {
      e.printStackTrace();
    }
    return mapping.findForward("to_showloanreturnedbyfundTb");
  }

  private Pagination getPagination(String paginationKey,
      HttpServletRequest request) {
    Pagination pagination = (Pagination) request.getSession().getAttribute(
        paginationKey);
    if (pagination == null) {
      HashMap m = new HashMap();
      pagination = new Pagination(0, 10, 1, "a.reservea_a  DESC,a.reservea_c", "DESC", m);
      request.getSession().setAttribute(paginationKey, pagination);
    }
    return pagination;
  }
}
