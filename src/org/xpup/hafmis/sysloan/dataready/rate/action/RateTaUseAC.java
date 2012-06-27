package org.xpup.hafmis.sysloan.dataready.rate.action;

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
import org.xpup.hafmis.sysloan.dataready.rate.bsinterface.IRateBS;
import org.xpup.hafmis.sysloan.dataready.rate.form.RateUseAF;

public class RateTaUseAC extends Action {
  public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
    ActionMessages messages = null;
    RateUseAF rateUseAF=(RateUseAF)form;
      try{
        SecurityInfo securityInfo=(SecurityInfo)request.getSession().getAttribute("SecurityInfo");
      IRateBS rateBS = (IRateBS) BSUtils.getBusinessService("rateBS", this,
          mapping.getModuleConfig());
      String rateId=request.getParameter("rateId");
      if(rateId.equals("all")){
        rateBS.useRateAll_sy(rateUseAF.getAppMode(), securityInfo);
      }else if(rateId!=null&&!rateId.equals("")){
      rateBS.useRateInfo_sy(rateUseAF, securityInfo);
      }
      messages=new ActionMessages();
      messages.add(ActionMessages.GLOBAL_MESSAGE,new ActionMessage("启用成功",
      false));
      saveErrors(request.getSession(), messages);
      request.getSession().setAttribute("ratemassage", "启用成功");
      }catch(BusinessException bex){
        messages=new ActionMessages();
        messages.add(ActionMessages.GLOBAL_MESSAGE,new ActionMessage(bex.getMessage(),
        false));
        saveErrors(request.getSession(), messages);
        request.getSession().setAttribute("ratemassage", bex.getMessage()+"");
      }
      catch(Exception e){
        e.printStackTrace();
      }
    return mapping.findForward("to_rate_appuse_new");
  }
}
