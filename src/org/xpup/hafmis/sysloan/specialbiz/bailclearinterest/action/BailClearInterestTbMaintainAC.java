package org.xpup.hafmis.sysloan.specialbiz.bailclearinterest.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.LookupDispatchAction;
import org.xpup.common.util.BSUtils;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.sysloan.dataready.loanpara.bsinterface.ILoanDocNumDesignBS;

/**
 * @author Õı“∞ 2007-10-05
 */
public class BailClearInterestTbMaintainAC extends LookupDispatchAction {

  protected Map getKeyMethodMap() {
    Map map = new HashMap();
    map.put("button.print", "print");
    return map;
  }

  public ActionForward print(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response) {
    try {
      SecurityInfo securityInfo = (SecurityInfo) request.getSession()
          .getAttribute("SecurityInfo");
      List printList = (List) request.getSession().getAttribute("printList");
      request.setAttribute("URL", "bailClearInterestTbShowAC.do");
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
      request.setAttribute("printList", printList);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return mapping.findForward("to_bailclearinterest_printtb");
  }
}
