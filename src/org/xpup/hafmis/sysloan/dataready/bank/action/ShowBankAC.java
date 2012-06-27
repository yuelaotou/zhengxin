package org.xpup.hafmis.sysloan.dataready.bank.action;

import java.util.HashMap;
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
import org.xpup.common.util.PaginationUtils;
import org.xpup.hafmis.sysloan.dataready.bank.bsinterface.IBankBS;
import org.xpup.hafmis.sysloan.dataready.bank.form.BankListAF;

/** 
 * MyEclipse Struts
 * Creation date: 09-20-2007
 * 
 * XDoclet definition:
 * @struts.action validate="true"
 */
public class ShowBankAC extends Action {
  
  public static final String PAGINATION_KEY = "org.xpup.hafmis.sysloan.dataready.bank.action.ShowBankAC";
	/** 
	 * Method execute
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 */
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
    ActionMessages messages = null;
    BankListAF bankListAF=new BankListAF();
    try{
      Pagination pagination = getPagination(PAGINATION_KEY, request);
      PaginationUtils.updatePagination(pagination, request);
      
      IBankBS bankBS = (IBankBS) BSUtils
      .getBusinessService("bankBS", this, mapping.getModuleConfig());
      bankListAF.setList(bankBS.findBankList(pagination)); 
    }
    catch(Exception e)
    {e.printStackTrace();
      messages = new ActionMessages();
      messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("œ‘ æ¡–±Ì ß∞‹", false));
      saveErrors(request, messages);
    }
   request.setAttribute("listform", bankListAF);
		return mapping.findForward("to_bank_show");
	}  
  private Pagination getPagination(String paginationKey,
      HttpServletRequest request) {
    Pagination pagination = (Pagination) request.getSession().getAttribute(
        paginationKey);
    if (pagination == null) {   
      pagination = new Pagination(0, 10, 1, "loanBank.id,loanBank.office,loanBank.loanBankId ", "DESC",
          new HashMap(0));
      request.getSession().setAttribute(PAGINATION_KEY, pagination);
    }
    return pagination;
  }
}