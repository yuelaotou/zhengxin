package org.xpup.hafmis.sysloan.accounthandle.overpay.action;

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
import org.xpup.hafmis.sysloan.accounthandle.bizcheck.bsinterface.IBizCheckBS;
import org.xpup.hafmis.sysloan.accounthandle.bizcheck.dto.OverPayDTO;
import org.xpup.hafmis.sysloan.accounthandle.overpay.bsinterface.IOverPayBS;
import org.xpup.hafmis.sysloan.dataready.loanpara.bsinterface.ILoanDocNumDesignBS;


public class OverPayTbMaintainAC extends LookupDispatchAction{

  protected Map getKeyMethodMap() {
    Map map = new HashMap();
    map.put("button.delete", "delete");
    map.put("button.print", "print");
    return map;
  }
  public ActionForward delete(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    ActionMessages messages = new ActionMessages();
    try{
      IdAF idAF = (IdAF)form;
      String contractId=(String)request.getParameter("contractid");
      SecurityInfo securityInfo = (SecurityInfo) request.getSession()
      .getAttribute("SecurityInfo");
      IOverPayBS overPayBS = (IOverPayBS) BSUtils
      .getBusinessService("overPayBS", this, mapping.getModuleConfig());
      overPayBS.deleteOverPayTbList(idAF.getId().toString(),contractId,securityInfo);
      }catch (BusinessException bex) {
        messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(bex.getLocalizedMessage(),
            false));
        saveErrors(request, messages);
      }catch(Exception e){
        e.printStackTrace();
      }
      return mapping.findForward("overpaytb_show");
  }

  public ActionForward print(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    ActionMessages messages = null;
    try {
      IdAF idAF = (IdAF)form;
      String flowHeadId = idAF.getId().toString();
      if (flowHeadId != null && !flowHeadId.equals("null")
          && !flowHeadId.equals("")) {
        SecurityInfo securityInfo = (SecurityInfo) request.getSession()
            .getAttribute("SecurityInfo");
        IBizCheckBS bizCheckBS = (IBizCheckBS) BSUtils.getBusinessService(
            "bizCheckBS", this, mapping.getModuleConfig());
        OverPayDTO overPayDTO = bizCheckBS.queryOverPayById(
            flowHeadId, securityInfo);
        overPayDTO.setFlowHeadId(flowHeadId);
        //String opertname = securityInfo.getUserName();

        ILoanDocNumDesignBS loanDocNumDesignBS = (ILoanDocNumDesignBS) BSUtils
           .getBusinessService("sysloanloanDocNumDesignBS", this, mapping.getModuleConfig());
           String opertname="";
           try {
             String name = loanDocNumDesignBS.getNamePara();

             if (name.equals("1")) {
               opertname = securityInfo.getUserName();
             } else {
               opertname = securityInfo.getRealName();
             }

           } catch (Exception e) {
             // TODO Auto-generated catch block
             e.printStackTrace();
           }
        String time = securityInfo.getUserInfo().getPlbizDate();
        request.setAttribute("overPayWindowPrinDTO",overPayDTO);
        request.setAttribute("opertname",opertname);
        request.setAttribute("time",time);
        request.setAttribute("url", "overPayTbShowAC.do");
      }
    }catch (BusinessException bex) {
      messages = new ActionMessages();
      messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(bex
          .getMessage(), false));
      saveErrors(request, messages);
      return mapping.findForward("overpaytb_show");
    } catch (Exception e) {
      // TODO: handle exception
      e.printStackTrace();
    }
    return mapping.findForward("to_overpay_windowprin");
  }
}
