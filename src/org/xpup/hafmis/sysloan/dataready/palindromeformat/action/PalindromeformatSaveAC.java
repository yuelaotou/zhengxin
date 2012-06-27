package org.xpup.hafmis.sysloan.dataready.palindromeformat.action;

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
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.sysloan.dataready.palindromeformat.bsinterface.IPalindromeformatBS;
import org.xpup.hafmis.sysloan.dataready.palindromeformat.form.PalindromeformatAF;
/**
 * 
 * @author yuqf
 *2007-12-20
 */
public class PalindromeformatSaveAC extends Action{
  ActionMessages messages = null;
  public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
    // TODO Auto-generated method stub
    try{
    String bankId = (String)request.getSession().getAttribute("bankId");
    PalindromeformatAF palindromeformatAF = (PalindromeformatAF)form;
    SecurityInfo securityInfo = (SecurityInfo) request.getSession()
    .getAttribute("SecurityInfo");
    IPalindromeformatBS palindromeformatBS=(IPalindromeformatBS)BSUtils
    .getBusinessService("palindromeformatBS", this, mapping.getModuleConfig());
    palindromeformatBS.insertSet(securityInfo,palindromeformatAF, bankId);
    messages = new ActionMessages();
    messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("²Ù×÷³É¹¦£¡", false));
    saveErrors(request, messages);
    }catch (BusinessException bex) {
      messages = new ActionMessages();
      messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(bex
          .getMessage(), false));
      saveErrors(request, messages);
    }
    return mapping.findForward("to_palindromeformatShowAC");
  }

}
