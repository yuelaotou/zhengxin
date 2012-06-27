package org.xpup.hafmis.sysloan.accounthandle.carryforward.action;

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
import org.xpup.hafmis.common.form.IdAF;
import org.xpup.hafmis.common.util.BusiConst;
import org.xpup.hafmis.common.util.BusiTools;
import org.xpup.hafmis.orgstrct.dto.OfficeDto;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.sysloan.accounthandle.carryforward.bsinterface.ICarryforwardBS;
import org.xpup.hafmis.sysloan.accounthandle.carryforward.form.CarryforwardShowAF;
import org.xpup.hafmis.sysloan.dataready.rate.bsinterface.IRateBS;
import org.xpup.hafmis.sysloan.dataready.rate.form.RateNewAF;



public class CarryforwardTaMaintainAC extends LookupDispatchAction{
  protected Map getKeyMethodMap() {
    Map map = new HashMap();
    map.put("button.carry.forward", "carryForwardInfo");
    return map;
  }
  public ActionForward carryForwardInfo(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response) {
    ActionMessages messages = null;
    try{
      messages=new ActionMessages();
      CarryforwardShowAF carryforwardShowAF=(CarryforwardShowAF)form;
      String loanBankId=carryforwardShowAF.getLoanBankId();
      SecurityInfo securityInfo=(SecurityInfo)request.getSession().getAttribute("SecurityInfo");
      ICarryforwardBS carryforwardBS = (ICarryforwardBS) BSUtils.getBusinessService("carryforwardBS", this,
          mapping.getModuleConfig());
    if(loanBankId!=null&&!loanBankId.equals("")){
      String info=carryforwardBS.useCarryforward(loanBankId, securityInfo);
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
    return mapping.findForward("carryforwardTaShowAC");
  }
}
