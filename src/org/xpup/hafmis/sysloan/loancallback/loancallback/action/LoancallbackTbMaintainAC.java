package org.xpup.hafmis.sysloan.loancallback.loancallback.action;

import java.util.Date;
import java.util.HashMap;
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
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.sysloan.dataready.loanpara.bsinterface.ILoanDocNumDesignBS;
import org.xpup.hafmis.sysloan.loancallback.loancallback.bsinterface.ILoancallbackBS;
import org.xpup.hafmis.sysloan.loancallback.loancallback.form.LoancallbackTaAF;

public class LoancallbackTbMaintainAC extends LookupDispatchAction {
  protected Map getKeyMethodMap() {
    Map map = new HashMap();
    map.put("button.delete", "delete");
    map.put("button.callback", "callback");
    map.put("button.print", "print");
    return map;
  }

  public ActionForward delete(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    ActionMessages messages = null;
    SecurityInfo securityInfo = (SecurityInfo) request.getSession()
        .getAttribute("SecurityInfo");
    try {
      messages = new ActionMessages();
      IdAF idaf = (IdAF) form;
      String str[] = idaf.getId().toString().split(",");
      ILoancallbackBS loancallbackBS = (ILoancallbackBS) BSUtils
          .getBusinessService("loancallbackBS", this, mapping.getModuleConfig());
      loancallbackBS.deleteCallbackInfos(str[0], securityInfo);

    } catch (BusinessException b) {
      messages = new ActionMessages();
      messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(b
          .getMessage(), false));
      saveErrors(request, messages);
    }
    return mapping.findForward("loancallbackTbShowAC");
  }

  public ActionForward callback(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    ActionMessages messages = null;
    SecurityInfo securityInfo = (SecurityInfo) request.getSession()
        .getAttribute("SecurityInfo");
    try {
      messages = new ActionMessages();
      IdAF idaf = (IdAF) form;
      String str[] = idaf.getId().toString().split(",");
      System.out.println(str.length+"=============>");
      ILoancallbackBS loancallbackBS = (ILoancallbackBS) BSUtils
          .getBusinessService("loancallbackBS", this, mapping.getModuleConfig());
      loancallbackBS.callbackCallbackInfo(str[0], securityInfo,
          str.length == 1 ? null : str[1]);

    } catch (Exception e) {
      e.printStackTrace();
      messages = new ActionMessages();
      messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(e
          .getMessage(), false));
      saveErrors(request, messages);
    }
    // request.getSession().removeAttribute(MonthpayTaShowAC.PAGINATION_KEY);
    return mapping.findForward("loancallbackTbShowAC");
  }

  public ActionForward print(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    ActionMessages messages = null;
    LoancallbackTaAF loancallbackTaAF = new LoancallbackTaAF();
    SecurityInfo securityInfo = (SecurityInfo) request.getSession()
        .getAttribute("SecurityInfo");
    try {
      IdAF idaf = (IdAF) form;
      String str[] = idaf.getId().toString().split(",");
      ILoancallbackBS loancallbackBS = (ILoancallbackBS) BSUtils
          .getBusinessService("loancallbackBS", this, mapping.getModuleConfig());
      loancallbackTaAF = loancallbackBS.findPrintCallbackInfo(str[0]);
      Date date = new Date();
      String sdate = "";
      sdate = date.toLocaleString();
      loancallbackTaAF.setDate(sdate.substring(0, 9));
      ILoanDocNumDesignBS loanDocNumDesignBS = (ILoanDocNumDesignBS) BSUtils
          .getBusinessService("sysloanloanDocNumDesignBS", this, mapping
              .getModuleConfig());
      String userName = "";
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
      request.setAttribute("makePerson", userName);
      request
          .setAttribute("bizDate", securityInfo.getUserInfo().getPlbizDate());
      request.setAttribute("LoancallbackTaAF", loancallbackTaAF);
    } catch (Exception b) {
      b.printStackTrace();
      messages = new ActionMessages();
      messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(b
          .getMessage(), false));
      saveErrors(request, messages);
    }
    return mapping.findForward("loancallback_cell");
  }

}