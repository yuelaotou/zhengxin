package org.xpup.hafmis.sysloan.dataready.bankpalindrome.action;

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
import org.xpup.hafmis.sysloan.dataready.bankpalindrome.bsinterface.IBankpalindromeBS;
/**
 * 
 * @author yuqf
 *2007-12-20
 */
public class BankpalindromeSaveAC extends Action{

  public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
    // TODO Auto-generated method stub
    ActionMessages messages = null;
    String bankId = request.getParameter("bankId");
    String rowNum = request.getParameter("rowNum");
    String path = request.getContextPath();
    SecurityInfo securityInfo = (SecurityInfo) request.getSession()
    .getAttribute("SecurityInfo");
    try{
    IBankpalindromeBS bankpalindromeBS=(IBankpalindromeBS)BSUtils
    .getBusinessService("bankpalindromeBS", this, mapping.getModuleConfig());
    bankpalindromeBS.insertRowNum(securityInfo,bankId, rowNum);
    } catch (BusinessException bex) {
      messages = new ActionMessages();
      messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(bex
          .getMessage(), false));
      saveErrors(request, messages);
    }
    return mapping.findForward("to_bankpalindromeShowAC");
  }
  
}
