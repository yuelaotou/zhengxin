/**
 * Copy Right Information   : Goldsoft 
 * Project                  : LoanBusiFlowQueryMaintainAC
 * @Version                 : 1.0
 * @author                  : wangy
 * 生成日期                   :2007-10-16
 **/
package org.xpup.hafmis.sysloan.querystatistics.loanbusiquery.loanbusiflowquery.action;

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

public class LoanBusiFlowQueryMaintainAC extends LookupDispatchAction {

  protected Map getKeyMethodMap() {
    Map map = new HashMap();
    map.put("button.print", "print");
    return map;
  }

  public ActionForward print(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response) {
    try {
      List printList = (List) request.getSession().getAttribute("printLoanBusiFlowQueryList");
      request.setAttribute("URL", "loanBusiFlowQueryShowAC.do");
      request.setAttribute("printLoanBusiFlowQueryList", printList);
      SecurityInfo securityInfo = (SecurityInfo) request.getSession()
      .getAttribute("SecurityInfo");
     // String userName = securityInfo.getRealName();
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
    } catch (Exception e) {
      e.printStackTrace();
    }
    return mapping.findForward("to_loanbusiflowquery_print");
  }
}