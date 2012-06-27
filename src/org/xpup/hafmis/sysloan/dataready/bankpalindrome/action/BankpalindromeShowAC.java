package org.xpup.hafmis.sysloan.dataready.bankpalindrome.action;

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
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.sysloan.dataready.bankpalindrome.bsinterface.IBankpalindromeBS;
import org.xpup.hafmis.sysloan.dataready.bankpalindrome.form.BankpalindromeAF;
import org.xpup.security.common.domain.Userslogincollbank;
/**
 * 
 * @author yuqf
 *2007-12-20
 */
public class BankpalindromeShowAC extends Action{
  public static final String PAGINATION_KEY ="org.xpup.hafmis.sysloan.dataready.bankpalindrome.action.BankpalindromeShowAC";
  public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
    // TODO Auto-generated method stub
    ActionMessages messages = null;
    BankpalindromeAF bankpalindromeAF = new BankpalindromeAF();
    SecurityInfo securityInfo = (SecurityInfo) request.getSession()
    .getAttribute("SecurityInfo");
    Pagination pagination = getPagination(PAGINATION_KEY, request);
    List loanBankNameList = new ArrayList();//ÒøÐÐ 
    List bankList = securityInfo.getDkUserBankList();
    Userslogincollbank userslogincollbank = null;
    Iterator bank = bankList.iterator();
    while (bank.hasNext()) {
      userslogincollbank = (Userslogincollbank) bank.next();
//      Integer bankId = userslogincollbank.getCollBankId();
      loanBankNameList.add(new org.apache.struts.util.LabelValueBean(
          userslogincollbank.getCollBankName(), userslogincollbank
              .getCollBankId().toString()));
    }
    try{
    IBankpalindromeBS bankpalindromeBS=(IBankpalindromeBS)BSUtils
    .getBusinessService("bankpalindromeBS", this, mapping.getModuleConfig());
    request.getSession().setAttribute("loanBankNameList", loanBankNameList);
    bankpalindromeAF = bankpalindromeBS.queryRowNumByBank(securityInfo, pagination);
    } catch (BusinessException bex) {
      messages = new ActionMessages();
      messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(bex
          .getMessage(), false));
      saveErrors(request, messages);
    }
    request.setAttribute("bankpalindromeAF", bankpalindromeAF);
    return mapping.findForward("to_bankpalindrome_show");
  }
  private Pagination getPagination(String paginationKey,
      HttpServletRequest request) {
    Pagination pagination = (Pagination) request.getSession().getAttribute(
        paginationKey);
    if (pagination == null) {
      pagination = new Pagination(0, 10, 1, "null", "ASC", new HashMap(0));
      request.getSession().setAttribute(paginationKey, pagination);
    }

    return pagination;
  }
}
