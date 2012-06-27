package org.xpup.hafmis.sysloan.querystatistics.querystatistics.overdueinfoquery.action;

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
import org.xpup.common.util.Pagination;
import org.xpup.hafmis.common.form.IdAF;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.sysloan.dataready.loanpara.bsinterface.ILoanDocNumDesignBS;
import org.xpup.hafmis.sysloan.querystatistics.querystatistics.overdueinfoquery.action.OverDueinfoQueryShowListAC;
import org.xpup.hafmis.sysloan.querystatistics.querystatistics.overdueinfoquery.bsinterface.IOverDueinfoQueryBS;
import org.xpup.hafmis.sysloan.querystatistics.querystatistics.overdueinfoquery.dto.OverDueinfoQueryShowListDTO;

public class OverDueinfoQueryMaintainAC extends LookupDispatchAction{
  protected Map getKeyMethodMap() {
    Map map = new HashMap();
    map.put("button.printone", "printone");
    map.put("button.print", "print");
    return map;
  }
  public ActionForward print(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    try {
      Pagination pagination = (Pagination) request.getSession().getAttribute(
          OverDueinfoQueryShowListAC.PAGINATION_KEY);
      SecurityInfo securityInfo = (SecurityInfo) request.getSession()
      .getAttribute("SecurityInfo");
      IOverDueinfoQueryBS overDueinfoQueryBS = (IOverDueinfoQueryBS) BSUtils.getBusinessService(
          "overDueinfoQueryBS", this, mapping.getModuleConfig());
      List printList = overDueinfoQueryBS.findoverDueinfoQueryPrint(pagination,securityInfo);
      String opertname = securityInfo.getRealName();
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
      request.setAttribute("URL", "overDueinfoQueryShowListAC.do");
      request.setAttribute("printList", printList);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return mapping.findForward("to_overdueinfoquery_print");
  }
  public ActionForward printone(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    try {
      IdAF idAF = (IdAF) form;
      String id= idAF.getId().toString();
      SecurityInfo securityInfo = (SecurityInfo) request.getSession()
      .getAttribute("SecurityInfo");
      IOverDueinfoQueryBS overDueinfoQueryBS = (IOverDueinfoQueryBS) BSUtils.getBusinessService(
          "overDueinfoQueryBS", this, mapping.getModuleConfig());
      OverDueinfoQueryShowListDTO overDueinfoQueryShowListDTO = overDueinfoQueryBS.findoverDueinfoQueryPrintone(id,securityInfo);
      String opertname = securityInfo.getRealName();
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
      request.setAttribute("URL", "overDueinfoQueryShowListAC.do");
      request.setAttribute("overDueinfoQueryPrintOneDTO",overDueinfoQueryShowListDTO);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return mapping.findForward("to_overdueinfoquery_printone");
  }
}

