package org.xpup.hafmis.sysfinance.common.biz.credencepop.action;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.actions.LookupDispatchAction;
import org.xpup.common.exception.BusinessException;
import org.xpup.common.util.BSUtils;
import org.xpup.hafmis.common.form.IdAF;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.sysfinance.accounthandle.credencecheck.bsinterface.ICredencecheckBS;

/**
 * Copy Right Information : Æ¾Ö¤µ¯³ö¿ò´òÓ¡µÄAction Goldsoft Project : CredencePopShowAC
 * 
 * @Version : v1.0
 * @author : ¸¶ÔÆ·å
 * @Date : 2007.11.3
 */
public class CredencePopPrintAC extends LookupDispatchAction {
  protected Map getKeyMethodMap() {
    Map map = new HashMap();
    map.put("button.checkc", "check");
    map.put("button.print", "print");
    return map;
  }

  public ActionForward check(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    ActionMessages messages = new ActionMessages();
    try {
      String credenceId = request.getParameter("credenceId");
      String oldCredenceNum = request.getParameter("credencePopInfoDTO.oldCredenceNum");
      SecurityInfo securityInfo = (SecurityInfo) request.getSession()
          .getAttribute("SecurityInfo");
      ICredencecheckBS credencecheckBS = (ICredencecheckBS) BSUtils
          .getBusinessService("credencecheckBS", this, mapping
              .getModuleConfig());
      credencecheckBS.credencecheckCheck_yg(credenceId, securityInfo,oldCredenceNum);
      request.setAttribute("isFinished", "1");
    } catch (BusinessException bex) {
      messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(bex
          .getLocalizedMessage(), false));
      saveErrors(request, messages);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return mapping.findForward("to_credencepop_show");
  }
  public ActionForward print(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
  throws Exception {
    try {
      SecurityInfo securityInfo = (SecurityInfo) request.getSession()
        .getAttribute("SecurityInfo");
      String bizDate = securityInfo.getUserInfo().getFbizDate();
      request.setAttribute("bizDate", bizDate);
      String bookid = securityInfo.getBookId();
      if(bookid.equals("2")){
        return mapping.findForward("credencepop_print_jj");
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    return mapping.findForward("to_credencepop_print");
  }

}
