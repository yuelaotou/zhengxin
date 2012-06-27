package org.xpup.hafmis.sysloan.querystatistics.loanbackbyfund.loanback.action;

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
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.sysloan.dataready.loanpara.bsinterface.ILoanDocNumDesignBS;
import org.xpup.hafmis.sysloan.querystatistics.loanbackbyfund.loanback.bsinterface.ILoanBackBS;
import org.xpup.hafmis.sysloan.querystatistics.loanbusiquery.loanbusiflowquery.dto.LoanBusiFlowQueryClearDTO;

/**
 * @author ั๎นโ 20090512
 */
public class LoanBackMaintainAC extends LookupDispatchAction {

  protected Map getKeyMethodMap() {
    Map map = new HashMap();
    map.put("button.print", "print");
    map.put("button.print.bank", "bank");
    return map;
  }

  public ActionForward print(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response) {
    try {
      List printList = (List) request.getSession().getAttribute("printLoanBusiFlowQueryList");
      request.setAttribute("URL", "loanBackShowAC.do");
      request.setAttribute("printlist", printList);
      SecurityInfo securityInfo = (SecurityInfo) request.getSession()
      .getAttribute("SecurityInfo");
     // String userName = securityInfo.getRealName();
      ILoanBackBS loanBackBS = (ILoanBackBS) BSUtils
      .getBusinessService("loanBackBS", this, mapping
          .getModuleConfig());
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
      Pagination pagination = getPagination(LoanBackShowAC.PAGINATION_KEY, request);
      if(pagination.getQueryCriterions().get("loanBank")!=null){
        LoanBusiFlowQueryClearDTO loanBusiFlowQueryClearDTO=loanBackBS.queryLoanBusiFlowQueryClearByFlowHeadId(pagination.getQueryCriterions().get("loanBank").toString(), securityInfo);
        request.setAttribute("loanBankName", loanBusiFlowQueryClearDTO.getLoanBankName());
      }else{
        request.setAttribute("loanBankName", "");
      }
      String plbizDate = securityInfo.getUserInfo().getPlbizDate();
      request.setAttribute("userName", userName);
      request.setAttribute("bizDate", plbizDate);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return mapping.findForward("to_loanBack_print");
  }
  public ActionForward bank(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response) {
    try {
      request.setAttribute("URL", "loanBackShowAC.do");
      SecurityInfo securityInfo = (SecurityInfo) request.getSession()
      .getAttribute("SecurityInfo");
      ILoanDocNumDesignBS loanDocNumDesignBS = (ILoanDocNumDesignBS) BSUtils
      .getBusinessService("sysloanloanDocNumDesignBS", this, mapping.getModuleConfig());
      Pagination pagination = (Pagination) request.getSession().getAttribute(
          LoanBackShowAC.PAGINATION_KEY);
      String batchNum = (String)pagination.getQueryCriterions().get("noteNum");
      if(batchNum!=null){
        request.setAttribute("batchNum", batchNum);
      }
      String userName="";
      try {
        String name = loanDocNumDesignBS.getNamePara();
        if (name.equals("1")) {
          userName = securityInfo.getUserName();
        } else {
          userName = securityInfo.getRealName();
        }
      } catch (Exception e) {
        e.printStackTrace();
      }
      String plbizDate = securityInfo.getUserInfo().getPlbizDate();
      request.setAttribute("userName", userName);
      request.setAttribute("plbizDate", plbizDate);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return mapping.findForward("loanbackbank_cell");
  }
  
  
  private Pagination getPagination(String paginationKey,
      HttpServletRequest request) {
    Pagination pagination = (Pagination) request.getSession().getAttribute(
        paginationKey);
    if (pagination == null) {
      HashMap m = new HashMap();
      m.put("type", "0");
      pagination = new Pagination(0, 10, 1, "p203.contract_id", "DESC", m);
      request.getSession().setAttribute(paginationKey, pagination);
    }
    return pagination;
  }
}