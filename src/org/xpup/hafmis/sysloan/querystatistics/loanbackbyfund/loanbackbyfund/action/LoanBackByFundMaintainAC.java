package org.xpup.hafmis.sysloan.querystatistics.loanbackbyfund.loanbackbyfund.action;

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
 * 
 * @author yangg
 * 20090303
 */
public class LoanBackByFundMaintainAC extends LookupDispatchAction {

  protected Map getKeyMethodMap() {
    Map map = new HashMap();
    map.put("button.print", "print");
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
      Pagination pagination = getPagination(LoanBackByFundShowAC.PAGINATION_KEY, request);
      if(pagination.getQueryCriterions().get("loanBank")!=null){
        LoanBusiFlowQueryClearDTO loanBusiFlowQueryClearDTO=loanBackBS.queryLoanBusiFlowQueryClearByFlowHeadId(pagination.getQueryCriterions().get("loanBank").toString(), securityInfo);
        request.setAttribute("loanBankName", loanBusiFlowQueryClearDTO.getLoanBankName());
      }else{
        request.setAttribute("loanBankName", "");
      }
      String plbizDate = securityInfo.getUserInfo().getPlbizDate();
      request.setAttribute("userName", userName);
      request.setAttribute("plbizDate", plbizDate);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return mapping.findForward("to_loanBack_print");
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