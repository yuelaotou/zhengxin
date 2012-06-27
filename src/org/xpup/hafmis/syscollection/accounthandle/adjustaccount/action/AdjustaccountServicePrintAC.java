package org.xpup.hafmis.syscollection.accounthandle.adjustaccount.action;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
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
import org.xpup.hafmis.syscollection.accounthandle.adjustaccount.dto.AdjustaccountReportDTO;
import org.xpup.hafmis.syscollection.systemmaintain.loanpara.bsinterface.ILoanDocNumDesignBS;

/**
 * @author ÀîÅô 2007-6-28
 */
public class AdjustaccountServicePrintAC extends Action {

  public static final String PAGINATION_KEY = "org.xpup.hafmis.syscollection.accounthandle.adjustaccount.action.AdjustaccountServiceTaShowAC";

  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {

    Pagination pagination = getPagination(PAGINATION_KEY, request);
    List printlist = (List) pagination.getQueryCriterions().get("adjustprint");
    String url = request.getParameter("URL");
    request.setAttribute("URL", url);
    request.setAttribute("printlist", printlist);
    AdjustaccountReportDTO adjustaccountReportDTO = new AdjustaccountReportDTO();
    if (printlist != null && printlist.size() > 0) {
      adjustaccountReportDTO = (AdjustaccountReportDTO) printlist.get(0);
    }

    String orgId = adjustaccountReportDTO.getOrgid().toString();

    ILoanDocNumDesignBS loanDocNumDesignBS = (ILoanDocNumDesignBS) BSUtils
        .getBusinessService("loanDocNumDesignBS", this, mapping
            .getModuleConfig());
    String userName = "";
    SecurityInfo securityInfo = (SecurityInfo) request.getSession()
        .getAttribute("SecurityInfo");
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
    String collectionBankId = "";
    String collectionBankName = "";

    if (orgId != null && !orgId.equals("")) {
      collectionBankName = loanDocNumDesignBS.queryCollectionBankNameById(
          orgId, securityInfo);
    }
    String bizDate = securityInfo.getUserInfo().getBizDate();
    request.setAttribute("userName", userName);
    request.setAttribute("bizDate", bizDate);
    request.setAttribute("collectionBankName", collectionBankName);

    if (!printlist.isEmpty()) {
      if (printlist.size() > 1)
        return mapping.findForward("adjustaccount_list_cell");
    }
    // wuht

    return mapping.findForward("adjustaccount_single_cell");
  }

  private Pagination getPagination(String paginationKey,
      HttpServletRequest request) {
    Pagination pagination = (Pagination) request.getSession().getAttribute(
        paginationKey);
    if (pagination == null) {
      pagination = new Pagination(0, 10, 1,
          "empHAFAccountFlow.orgHAFAccountFlow.docNum", "ASC", new HashMap(0));
      request.getSession().setAttribute(paginationKey, pagination);
    }
    return pagination;
  }
}
