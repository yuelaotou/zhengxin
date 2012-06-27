package org.xpup.hafmis.sysloan.loancallback.loanerlogout.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
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
import org.xpup.common.exception.BusinessException;
import org.xpup.common.util.BSUtils;
import org.xpup.common.util.Pagination;
import org.xpup.hafmis.common.form.IdAF;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.sysloan.loancallback.loanerlogout.bsinterface.ILoanerlogoutBS;
import org.xpup.security.common.domain.Userslogincollbank;

public class LoanerlogoutTbMaintainAC extends LookupDispatchAction {
  public static final String PAGINATION_KEY = "org.xpup.hafmis.sysloan.loancallback.loanerlogout.action.LoanerlogoutTbShowAC";
  protected Map getKeyMethodMap() {
    Map map = new HashMap();
    map.put("button.trun.logout", "trunlogout");
    map.put("button.print", "print");
    return map;
  }

  public ActionForward trunlogout(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    try{
      IdAF idAF = (IdAF)form;
      SecurityInfo securityInfo = (SecurityInfo) request.getSession()
      .getAttribute("SecurityInfo");
      ILoanerlogoutBS loanerlogoutBS = (ILoanerlogoutBS) BSUtils
      .getBusinessService("loanerlogoutBS", this, mapping.getModuleConfig());
      loanerlogoutBS.trunLogoutLoanerlogout(idAF.getId().toString(),securityInfo);
      }catch (BusinessException e) {
      e.printStackTrace();
      ActionMessages messages = null;
      messages = new ActionMessages();
      messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("该贷款户已正常！",
          false));
      saveErrors(request, messages);
      
      return mapping.findForward("loanerlogouttb_show");
    } catch(Exception e){
        e.printStackTrace();
      }
      return mapping.findForward("loanerlogouttb_show");
  }

  public ActionForward print(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    SecurityInfo securityInfo = (SecurityInfo) request.getSession().getAttribute(
    "SecurityInfo");
    List loanbankList1 = null;
    List loanbankList2 = null;
    try {
      // 取出用户权限放款银行,显示在下拉菜单中
      List loanbankList = securityInfo.getDkUserBankList();
      Pagination pagination = getPagination(LoanerlogoutTbShowAC.PAGINATION_KEY, request); 
      loanbankList1 = new ArrayList();
      loanbankList2 = new ArrayList();
      Userslogincollbank bank = null;
      Iterator itr1 = loanbankList.iterator();
      while (itr1.hasNext()) {
        bank = (Userslogincollbank) itr1.next();
        loanbankList1.add(new org.apache.struts.util.LabelValueBean(bank
            .getCollBankName(), bank.getCollBankId().toString()));
        loanbankList2.add(bank.getCollBankId());
      }
      ILoanerlogoutBS loanerlogoutBS = (ILoanerlogoutBS) BSUtils
      .getBusinessService("loanerlogoutBS", this, mapping.getModuleConfig());
      List cellList=loanerlogoutBS.findLoanerlogoutTbPrintList(pagination,loanbankList2);
      request.setAttribute("cellList", cellList);
    } catch (Exception e) {
      e.printStackTrace();
    }
    
    return mapping.findForward("to_loanerlogouttb_print");
  }
  private Pagination getPagination(String paginationKey,
      HttpServletRequest request) {
    Pagination pagination = (Pagination) request.getSession().getAttribute(
        paginationKey);
    if (pagination == null) {
      HashMap m = new HashMap();
      pagination = new Pagination(0, 10, 1, "POKEBANKMODIFYID", "DESC",
           m);
      request.getSession().setAttribute(paginationKey, pagination);
    }   

    return pagination;
  }
}
