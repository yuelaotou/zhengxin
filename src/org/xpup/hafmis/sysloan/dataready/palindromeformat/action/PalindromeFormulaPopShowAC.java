package org.xpup.hafmis.sysloan.dataready.palindromeformat.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import org.xpup.hafmis.sysloan.dataready.palindromeformat.bsinterface.IPalindromeformatBS;
import org.xpup.hafmis.sysloan.dataready.palindromeformat.form.PalindromeFormulaPopAF;
/**
 * 
 * @author yuqf
 *2007-12-20
 */
public class PalindromeFormulaPopShowAC extends Action{
  public static final String PAGINATION_KEY ="org.xpup.hafmis.sysloan.dataready.palindromeformat.action.PalindromeFormulaPopShowAC";
  public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
    // TODO Auto-generated method stub
    ActionMessages messages = null;
    String property = request.getParameter("property");
    String bankId = request.getParameter("bankId");
    IPalindromeformatBS palindromeformatBS=(IPalindromeformatBS)BSUtils
    .getBusinessService("palindromeformatBS", this, mapping.getModuleConfig());
    PalindromeFormulaPopAF palindromeFormulaPopAF = new PalindromeFormulaPopAF();
    List list = new ArrayList();
    try{
      list = palindromeformatBS.queryRowNum(bankId);
    } catch (BusinessException bex) {
      messages = new ActionMessages();
      messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(bex
          .getMessage(), false));
      saveErrors(request, messages);
    }
    palindromeFormulaPopAF.setProperty(property);
    request.getSession().setAttribute("list", list);
    request.setAttribute("palindromeFormulaPopAF", palindromeFormulaPopAF);
    return mapping.findForward("to_formulaPop");
  }

}
