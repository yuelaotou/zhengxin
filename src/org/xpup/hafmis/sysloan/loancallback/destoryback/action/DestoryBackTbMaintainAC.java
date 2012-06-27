package org.xpup.hafmis.sysloan.loancallback.destoryback.action;

import java.util.HashMap;
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
import org.xpup.hafmis.syscollection.systemmaintain.loanpara.bsinterface.ILoanDocNumDesignBS;
import org.xpup.hafmis.sysloan.loancallback.destoryback.bsinterface.IDestoryBackBS;


public class DestoryBackTbMaintainAC extends LookupDispatchAction{
  protected Map getKeyMethodMap() {
    Map map = new HashMap();
    map.put("button.delete", "delete");
    map.put("button.print", "print");
    return map;
  }
  public ActionForward delete(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response) {
    ActionMessages messages = null;
    try {
     SecurityInfo securityInfo = (SecurityInfo) request.getSession()
        .getAttribute("SecurityInfo");
     IDestoryBackBS destoryBackBS = (IDestoryBackBS) BSUtils.getBusinessService(
        "destoryBackBS", this, mapping.getModuleConfig());
     IdAF idAF = (IdAF) form;
     String flowHeadId = idAF.getId().toString();
     destoryBackBS.deleteDestoryBackInfo(flowHeadId,securityInfo);
     messages = new ActionMessages();
     messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("É¾³ý³É¹¦£¡",
         false));
     saveErrors(request, messages);

   } catch (BusinessException bex) {
     messages = new ActionMessages();
     messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("É¾³ýÊ§°Ü£¡",
         false));
     saveErrors(request, messages);
   } catch (Exception e) {
     e.printStackTrace();
   }
    return mapping.findForward("destoryBackTbShowAC");
  }
  public ActionForward print(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    try {
      Pagination pagination = (Pagination) request.getSession().getAttribute(
          DestoryBackTbShowAC.PAGINATION_KEY);
      SecurityInfo securityInfo = (SecurityInfo) request.getSession()
      .getAttribute("SecurityInfo");
      IDestoryBackBS destoryBackBS = (IDestoryBackBS) BSUtils.getBusinessService(
          "destoryBackBS", this, mapping.getModuleConfig());
      List printList = destoryBackBS.findDestoryBackTbPrint(pagination,securityInfo);
      //String opertname = securityInfo.getRealName();

      ILoanDocNumDesignBS loanDocNumDesignBS = (ILoanDocNumDesignBS) BSUtils
         .getBusinessService("sysloanloanDocNumDesignBS", this, mapping.getModuleConfig());
         String userName="";
         try {
           String name = loanDocNumDesignBS.getNamePara();

           if (name.equals("1")) {
             userName = securityInfo.getUserName();
           } else {
             userName = securityInfo.getRealName();
           }

         } catch (Exception e) {
           // TODO Auto-generated catch block
           e.printStackTrace();
         }
      String time = securityInfo.getUserInfo().getPlbizDate();
      request.setAttribute("opertname",userName);
      request.setAttribute("time",time);
      request.setAttribute("URL", "destoryBackTbShowAC.do");
      request.setAttribute("printList", printList);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return mapping.findForward("to_destoryback_printtb");
  }
}

