package org.xpup.hafmis.sysloan.querystatistics.loanbusiquery.loancontractquery.action;

/*
 * Generated by MyEclipse Struts
 * Template path: templates/java/JavaClass.vtl
 */

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.xpup.common.util.BSUtils;
import org.xpup.common.util.Pagination;
import org.xpup.common.util.PaginationUtils;
import org.xpup.hafmis.common.util.BusiConst;
import org.xpup.hafmis.common.util.BusiTools;
import org.xpup.hafmis.orgstrct.dto.OfficeDto;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.sysloan.common.domain.entity.AssistantOrg;
import org.xpup.hafmis.sysloan.querystatistics.loanbusiquery.loancontractquery.bsinterface.ILoancontractqueryBS;
import org.xpup.hafmis.sysloan.querystatistics.loanbusiquery.loancontractquery.form.LoanContractQueryAF;
import org.xpup.security.common.domain.Userslogincollbank;

public class LoancontractqueryShowAC extends Action {

  public static final String PAGINATION_KEY = "org.xpup.hafmis.sysloan.querystatistics.loanbusiquery.loancontractquery.action.LoancontractqueryShowAC";

  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response) {
    Pagination pagination = getPagination(PAGINATION_KEY, request);
    PaginationUtils.updatePagination(pagination, request);
    String url = (String) request.getSession().getAttribute("url");
    pagination.getQueryCriterions().put("url", url);
    LoanContractQueryAF loancontractqueryAF = new LoanContractQueryAF();
    ILoancontractqueryBS loancontractqueryBS = (ILoancontractqueryBS) BSUtils
        .getBusinessService("loancontractqueryBS", this, mapping
            .getModuleConfig());
    SecurityInfo securityInfo = (SecurityInfo) request.getSession()
        .getAttribute("SecurityInfo");
    try {
      loancontractqueryAF = loancontractqueryBS.showLoancontractqueryList(
          pagination, securityInfo);
      String loanbank = (String) pagination.getQueryCriterions()
          .get("loanbank");
      loancontractqueryAF.setLoanbank(loanbank);
      // 求办事处
      List temp_officeList = securityInfo.getOfficeList();
      List li = new ArrayList();
      for (int i = 0; i < temp_officeList.size(); i++) {
        OfficeDto officedto = null;
        officedto = (OfficeDto) temp_officeList.get(i);
        li.add(new org.apache.struts.util.LabelValueBean(officedto
            .getOfficeName(), officedto.getOfficeCode()));
      }

      // 求放款银行
      List temp_DkbankList = securityInfo.getDkUserBankList();
      List libank = new ArrayList();
      for (int i = 0; i < temp_DkbankList.size(); i++) {
        Userslogincollbank u = null;
        u = (Userslogincollbank) temp_DkbankList.get(i);
        libank.add(new org.apache.struts.util.LabelValueBean(u
            .getCollBankName(), u.getCollBankId().toString()));
      }
      // 求担保公司
      List temp_assistantorgList = loancontractqueryAF.getAssistantorgList();
      List assistantorgList = new ArrayList();
      for (int i = 0; i < temp_assistantorgList.size(); i++) {
        AssistantOrg assistantOrg = null;
        assistantOrg = (AssistantOrg) temp_assistantorgList.get(i);
        assistantorgList.add(new org.apache.struts.util.LabelValueBean(
            assistantOrg.getAssistantOrgName(), assistantOrg
                .getAssistantOrgId().toString()));
      }

      loancontractqueryAF.setHousetypemap(BusiTools
          .listBusiProperty(BusiConst.PLHOUSETYPE));
      // 去掉申请
      Map temp_Map = BusiTools.listBusiProperty(BusiConst.PLCONTRACTSTATUS);
      temp_Map.remove(new Integer(BusiConst.PLCONTRACTSTATUS_APPL));
      loancontractqueryAF.setContact_stmap(temp_Map);
      // 去掉其它
      Map temp_Map_map = BusiTools.listBusiProperty(BusiConst.PLRECOVERTYPE);
      Map yesNoMap = BusiTools.listBusiProperty(BusiConst.YesNo);
      loancontractqueryAF.setYesNoMap(yesNoMap);
      temp_Map_map.remove(new Integer(BusiConst.PLRECOVERTYPE_OTHER));
      loancontractqueryAF.setPaymoodmap(temp_Map_map);
      request.setAttribute("loancontractqueryAF", loancontractqueryAF);
      request.setAttribute("officelist", li);
      request.setAttribute("dkbanklist", libank);
      request.setAttribute("assistantorglist", assistantorgList);
      request.getSession().setAttribute("allList",
          loancontractqueryAF.getListall());
    } catch (Exception e) {
      e.printStackTrace();
    }
    return mapping.findForward("loancontractquery");
  }

  private Pagination getPagination(String paginationKey,
      HttpServletRequest request) {
    Pagination pagination = (Pagination) request.getSession().getAttribute(
        paginationKey);
    if (pagination == null) {
      pagination = new Pagination(0, 10, 1, "p10.contract_id", "DESC",
          new HashMap(0));
      request.getSession().setAttribute(paginationKey, pagination);
    }
    return pagination;
  }

}