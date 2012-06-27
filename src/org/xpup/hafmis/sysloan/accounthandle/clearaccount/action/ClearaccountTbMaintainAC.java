package org.xpup.hafmis.sysloan.accounthandle.clearaccount.action;

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
import org.xpup.hafmis.sysloan.accounthandle.clearaccount.bsinterface.IClearaccountBS;
import org.xpup.hafmis.sysloan.accounthandle.clearaccount.form.ClearAccountBalanceInfoAF;
import org.xpup.hafmis.sysloan.dataready.loanpara.bsinterface.ILoanDocNumDesignBS;

public class ClearaccountTbMaintainAC extends LookupDispatchAction {
  protected Map getKeyMethodMap() {
    Map map = new HashMap();
    map.put("button.print", "print");
    return map;
  }

  public ActionForward print(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response) {
    ClearAccountBalanceInfoAF clearAccountBalanceInfoAF = (ClearAccountBalanceInfoAF)form;
    SecurityInfo securityInfo = (SecurityInfo) request.getSession()
    .getAttribute("SecurityInfo");
    Pagination pagination = (Pagination)request.getSession().getAttribute(ClearaccountTbShowAC.PAGINATION_KEY);
    String specialType = (String) pagination.getQueryCriterions().get(
    "specialType");
    try {
      
      String loanBankId = (String)pagination.getQueryCriterions().get("loanBankId");
      String startDate = (String)pagination.getQueryCriterions().get("startDate");
      String endDate = (String)pagination.getQueryCriterions().get("endDate");
     
      IClearaccountBS clearaccountBS = (IClearaccountBS) BSUtils
      .getBusinessService("clearaccountBS", this, mapping.getModuleConfig());
      String loanBankName = clearaccountBS.getLoanBankName(loanBankId);
      clearAccountBalanceInfoAF.setLoanBankId(loanBankName);
      String bizDate = securityInfo.getUserInfo().getPlbizDate();

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
      request.setAttribute("startDate", startDate);
      request.setAttribute("endDate", endDate);
      clearAccountBalanceInfoAF.setBizDate(bizDate.substring(0,4)+"-"+bizDate.substring(4,6)+"-"+bizDate.substring(6,8));
      request.setAttribute("clearAccountBalanceInfoAF", clearAccountBalanceInfoAF);
    } catch (Exception e) {
      e.printStackTrace();
    }
    if (specialType != null) {
      return mapping.findForward("to_print_month");
    }else{
      return mapping.findForward("clearaccountbalance_cell");
    }
    
  }

}
