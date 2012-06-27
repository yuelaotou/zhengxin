package org.xpup.hafmis.sysloan.loancallback.loancallback.action; 

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.xpup.common.util.BSUtils;
import org.xpup.common.util.Pagination;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.sysloan.dataready.loanpara.bsinterface.ILoanDocNumDesignBS;
import org.xpup.hafmis.sysloan.loancallback.loancallback.bsinterface.ILoancallbackBS;
import org.xpup.hafmis.sysloan.loancallback.loancallback.form.LoancallbackTaAF;

public class LoancallbackTbPrintWindowAC extends Action {
  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    LoancallbackTaAF af = (LoancallbackTaAF)form; 
    SecurityInfo securityInfo=(SecurityInfo)request.getSession().getAttribute("SecurityInfo");
    LoancallbackTaAF loancallbackTaAF = new LoancallbackTaAF();
    Pagination pagination= (Pagination) request.getSession().getAttribute(LoancallbackTbShowWindowAC.PAGINATION_KEY);
    ILoancallbackBS loancallbackBS = (ILoancallbackBS) BSUtils
    .getBusinessService("loancallbackBS", this, mapping.getModuleConfig());
      String headId = af.getHeadId();
      String bizType = af.getBizType();

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
      request.setAttribute("makePerson", userName);
      request.setAttribute("bizDate", securityInfo.getUserInfo().getPlbizDate());
      if(bizType.equals("5")){
        List list = loancallbackBS.findCallbackBatchMXPrint(pagination) ;//(List)request.getSession().getAttribute("BatchList");
        request.setAttribute("batchList", list);
        String flowId = (String) pagination.getQueryCriterions().get("headId");
        String bankName=loancallbackBS.selectPL202_BankId_wsh(flowId);
        request.setAttribute("bankName", bankName);
        return mapping.findForward("loancallbackList_cell");
      }else{
        loancallbackTaAF = loancallbackBS.findPrintCallbackInfo(headId);
        request.setAttribute("LoancallbackTaAF", loancallbackTaAF);
        return mapping.findForward("loancallback_cell");
      }
  }

}