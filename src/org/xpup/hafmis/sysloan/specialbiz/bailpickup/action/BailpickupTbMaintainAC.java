package org.xpup.hafmis.sysloan.specialbiz.bailpickup.action;

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
import org.xpup.common.util.Pagination;
import org.xpup.hafmis.common.form.IdAF;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.syscollection.systemmaintain.loanpara.bsinterface.ILoanDocNumDesignBS;
import org.xpup.hafmis.sysloan.specialbiz.bailenrol.bsinterface.IBailenRolBS;
import org.xpup.hafmis.sysloan.specialbiz.bailpickup.bsinterface.IBailpickupBS;
import org.xpup.hafmis.sysloan.specialbiz.bailpickup.form.BailpickupTaAF;

/**
 * @author yuqf 2007-10-16
 */
public class BailpickupTbMaintainAC extends LookupDispatchAction {

  protected Map getKeyMethodMap() {
    // TODO Auto-generated method stub
    Map map = new HashMap();
    map.put("button.delete", "delete");
    map.put("button.print", "print");
    return map;
  }

  public ActionForward delete(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response) {
    ActionMessages messages = null;
    SecurityInfo securityInfo = (SecurityInfo) request.getSession()
        .getAttribute("SecurityInfo");
    IBailpickupBS bailpickupBS = (IBailpickupBS) BSUtils.getBusinessService(
        "bailpickupBS", this, mapping.getModuleConfig());
    Pagination pagination = (Pagination) request.getSession().getAttribute(
        BailpickupTbShowAC.PAGINATION_KEY);
    IdAF idAF = (IdAF) form;
    String flowHeadId = idAF.getId().toString();
    try {
      bailpickupBS.tbDelete(flowHeadId, pagination, securityInfo);
    } catch (BusinessException bex) {
      messages = new ActionMessages();
      messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(bex
          .getMessage(), false));
      saveErrors(request, messages);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return mapping.findForward("to_bailpickupTbShowAC");
  }

  public ActionForward print(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response) {
    SecurityInfo securityInfo = (SecurityInfo) request.getSession()
        .getAttribute("SecurityInfo");
    IBailpickupBS bailpickupBS = (IBailpickupBS) BSUtils.getBusinessService(
        "bailpickupBS", this, mapping.getModuleConfig());
    Pagination pagination = (Pagination) request.getSession().getAttribute(
        BailpickupTbShowAC.PAGINATION_KEY);
    BailpickupTaAF bailpickupTaAF = new BailpickupTaAF();
    IdAF idAF = (IdAF) form;
    String flowHeadId = idAF.getId().toString();
    try {
      bailpickupTaAF = bailpickupBS.tbPrintQuery(flowHeadId, pagination, securityInfo);
      //String userName = securityInfo.getRealName();

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
      String plbizDate = securityInfo.getUserInfo().getPlbizDate();
      request.setAttribute("userName", userName);
      
      request.setAttribute("plbizDate", plbizDate);
      request.setAttribute("theprintbailpickupTaAF", bailpickupTaAF);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return mapping.findForward("to_bailpickupTa_cell");
  }
}
