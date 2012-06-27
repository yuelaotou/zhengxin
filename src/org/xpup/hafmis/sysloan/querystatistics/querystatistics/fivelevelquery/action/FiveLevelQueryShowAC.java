/**
 * Copy Right Information   : Goldsoft 
 * Project                  : FiveLevelQueryShowAC
 * @Version                 : 1.0
 * @author                  : wangy
 * 生成日期                   :2007-10-19
 **/
package org.xpup.hafmis.sysloan.querystatistics.querystatistics.fivelevelquery.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.xpup.common.util.BSUtils;
import org.xpup.common.util.Pagination;
import org.xpup.common.util.PaginationUtils;
import org.xpup.hafmis.orgstrct.dto.OfficeDto;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.sysloan.common.domain.entity.AssistantOrg;
import org.xpup.hafmis.sysloan.querystatistics.querystatistics.fivelevelquery.bsinterface.IFiveLevelQueryBS;
import org.xpup.hafmis.sysloan.querystatistics.querystatistics.fivelevelquery.form.FiveLevelQueryAF;
import org.xpup.security.common.domain.Userslogincollbank;

public class FiveLevelQueryShowAC extends Action {

  public static final String PAGINATION_KEY = "org.xpup.hafmis.sysloan.querystatistics.querystatistics.fivelevelquery.action.FiveLevelQueryShowAC";

  /**
   * 五级分类统计
   */
  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response) {
    try {
      SecurityInfo securityInfo = (SecurityInfo) request.getSession()
          .getAttribute("SecurityInfo");
      Pagination pagination = getPagination(
          FiveLevelQueryShowAC.PAGINATION_KEY, request);
      PaginationUtils.updatePagination(pagination, request);
      if (securityInfo != null && !securityInfo.equals("")) {
        IFiveLevelQueryBS fiveLevelQueryBS = (IFiveLevelQueryBS) BSUtils
            .getBusinessService("fiveLevelQueryBS", this, mapping
                .getModuleConfig());
        FiveLevelQueryAF fiveLevelQueryAF = new FiveLevelQueryAF();
        fiveLevelQueryAF = fiveLevelQueryBS
            .queryFiveLevelQueryListByCriterions(pagination, securityInfo);
        // 办事处下拉框
        List temp_officeList = securityInfo.getOfficeList();
        List officeList = new ArrayList();
        for (int i = 0; i < temp_officeList.size(); i++) {
          OfficeDto officedto = null;
          officedto = (OfficeDto) temp_officeList.get(i);
          officeList.add(new org.apache.struts.util.LabelValueBean(officedto
              .getOfficeName(), officedto.getOfficeCode()));
        }
        request.setAttribute("officeList", officeList);
        // 放款银行下拉框
        List loanBankNameList = new ArrayList();
        List bankList = securityInfo.getDkUserBankList();
        Userslogincollbank userslogincollbank = null;
        Iterator bank = bankList.iterator();
        while (bank.hasNext()) {
          userslogincollbank = (Userslogincollbank) bank.next();
          loanBankNameList.add(new org.apache.struts.util.LabelValueBean(
              userslogincollbank.getCollBankName(), userslogincollbank
                  .getCollBankId().toString()));
        }
        request.getSession(true).setAttribute("loanBankNameList",
            loanBankNameList);
        // 担保公司下拉框
        List assistantOrgNameList = new ArrayList();
        List assistantOrgList = fiveLevelQueryBS.getAssistantOrgNameList();
        AssistantOrg assistantOrg = null;
        Iterator assistantOrgIter = assistantOrgList.iterator();
        while (assistantOrgIter.hasNext()) {
          assistantOrg = (AssistantOrg) assistantOrgIter.next();
          assistantOrgNameList.add(new org.apache.struts.util.LabelValueBean(
              assistantOrg.getAssistantOrgName(), assistantOrg
                  .getAssistantOrgId().toString()));
        }
        request.getSession(true).setAttribute("assistantOrgNameList",
            assistantOrgNameList);
        // 单选按钮默认选办事处
        fiveLevelQueryAF.setMode((String) pagination.getQueryCriterions().get(
            "mode"));
        // 设置打印list
        List printList = fiveLevelQueryAF.getList();
        if (printList != null) {
          request.getSession().setAttribute("printList", printList);
        }
        request.setAttribute("fiveLevelQueryAF", fiveLevelQueryAF);
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    return mapping.findForward("to_fivelevelquery_show");
  }

  private Pagination getPagination(String paginationKey,
      HttpServletRequest request) {
    Pagination pagination = (Pagination) request.getSession().getAttribute(
        paginationKey);
    if (pagination == null) {
      HashMap m = new HashMap();
      m.put("type", "0");
      m.put("mode", "1");
      pagination = new Pagination(0, 10, 1, "", "DESC", m);
      request.getSession().setAttribute(paginationKey, pagination);
    }
    return pagination;
  }
}
