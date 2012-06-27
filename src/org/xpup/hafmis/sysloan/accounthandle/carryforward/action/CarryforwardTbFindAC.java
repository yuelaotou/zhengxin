package org.xpup.hafmis.sysloan.accounthandle.carryforward.action;

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
import org.xpup.hafmis.sysloan.accounthandle.carryforward.bsinterface.ICarryforwardBS;
import org.xpup.hafmis.sysloan.accounthandle.carryforward.form.CarryforwardShowAF;

/**
 *页面原为以中心为主维护的查询页面，后来去掉此功能，现在用作以银行为主的年结。
 *shiy 
 * 2007.11.14
 */
public class CarryforwardTbFindAC extends Action {
  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    ActionMessages messages = null;
    try{
      messages=new ActionMessages();
      CarryforwardShowAF carryforwardShowAF=(CarryforwardShowAF)form;
      String loanBankId=carryforwardShowAF.getLoanBankIdf();
      SecurityInfo securityInfo=(SecurityInfo)request.getSession().getAttribute("SecurityInfo");
      ICarryforwardBS carryforwardBS = (ICarryforwardBS) BSUtils.getBusinessService("carryforwardBS", this,
          mapping.getModuleConfig());
    if(loanBankId!=null&&!loanBankId.equals("")){
      String info=carryforwardBS.useBankCarryforward(loanBankId, securityInfo);
      if(info.equals("pass")){
        messages=new ActionMessages();
        messages.add(ActionMessages.GLOBAL_MESSAGE,new ActionMessage("年终结转成功！",
        false));
        saveErrors(request, messages);
      }
    } else{
      messages=new ActionMessages();
      messages.add(ActionMessages.GLOBAL_MESSAGE,new ActionMessage("年终结转失败！",
      false));
      saveErrors(request, messages);
    }
    }catch(BusinessException bex){
      messages=new ActionMessages();
      messages.add(ActionMessages.GLOBAL_MESSAGE,new ActionMessage(bex.getMessage(),
      false));
      saveErrors(request, messages);
    }catch(Exception e){
       e.printStackTrace();
     }
    return mapping.findForward("carryforwardTbShowAC");
  }
}
