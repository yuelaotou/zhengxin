package org.xpup.hafmis.sysloan.loancallback.bankimports.action;


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
import org.xpup.hafmis.sysloan.loancallback.bankexports.dto.BatchShouldBackListDTO;
import org.xpup.hafmis.sysloan.loancallback.bankimports.bsinterface.IBankImportsBS;
import org.xpup.hafmis.sysloan.loancallback.bankimports.form.BankImportsTaAF;
import org.xpup.security.common.domain.Userslogincollbank;


public class BankImportsTaShowAC extends Action {

  public static final String PAGINATION_KEY = "org.xpup.hafmis.sysloan.loancallback.bankimports.action.BankImportsTaShowAC";
  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    ActionMessages messages =null;
    SecurityInfo securityInfo=(SecurityInfo)request.getSession().getAttribute("SecurityInfo");
    try{
      BankImportsTaAF bankImportsTaAF = new BankImportsTaAF();
      Pagination pagination = getPagination(BankImportsTaShowAC.PAGINATION_KEY, request); 
      String loanBankId=(String)pagination.getQueryCriterions().get("loanBankId");
      String loanBankName = "";
      PaginationUtils.updatePagination(pagination, request);
      IBankImportsBS bankImportsBS = (IBankImportsBS) BSUtils
      .getBusinessService("bankImportsBS", this, mapping.getModuleConfig());
      List callbacklist = bankImportsBS.findBankCallbackList(pagination, securityInfo);
      BatchShouldBackListDTO batchShouldBackListDTO = bankImportsBS.findTotalBankCallback(pagination,securityInfo);
      String count = (String)pagination.getQueryCriterions().get("allCount");
      List temp_banklist = securityInfo.getDkUserBankList();
      List banklist = new ArrayList();
      if(!temp_banklist.isEmpty()){
        Iterator itr = temp_banklist.iterator();    
        while (itr.hasNext()) {
          Userslogincollbank userslogincollbank=(Userslogincollbank)itr.next();
          banklist.add(new org.apache.struts.util.LabelValueBean(
              userslogincollbank.getCollBankName().toString(), userslogincollbank.getCollBankId().toString()));
        }
      }
      loanBankName=bankImportsBS.getLoanBankName(loanBankId);
      bankImportsTaAF.setLoanBankId(loanBankId);
      bankImportsTaAF.setLoanBankName(loanBankName);
      request.setAttribute("bankImportsTaAF", bankImportsTaAF);
      request.setAttribute("allCount", count);
      request.setAttribute("callbacklist", callbacklist);
      request.setAttribute("DTO", batchShouldBackListDTO);
      request.getSession(true).setAttribute("banklist", banklist);
      pagination.getQueryCriterions().put("pageList", callbacklist);
      }catch(BusinessException bex){
        messages=new ActionMessages();
        messages.add(ActionMessages.GLOBAL_MESSAGE,new ActionMessage(bex.getMessage(),
            false));
        saveErrors(request, messages);
    }catch(Exception e){
      e.printStackTrace();
    }
    return mapping.findForward(getForword());
  }

  protected String getForword() {
    return "bankimports";
  }

  private Pagination getPagination(String paginationKey,
      HttpServletRequest request) {
    Pagination pagination = (Pagination) request.getSession().getAttribute(
        paginationKey);
    if (pagination == null) {
      HashMap m = new HashMap();
      pagination = new Pagination(0, 10, 1, "loanFlowTail.loanKouAcc", "DESC",
          m);
      request.getSession().setAttribute(paginationKey, pagination);
    }   

    return pagination;
  }
  
}


