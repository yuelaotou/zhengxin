package org.xpup.hafmis.sysloan.querystatistics.loanaccountquery.interestgl.action;

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
import org.xpup.hafmis.sysloan.querystatistics.loanaccountquery.interestgl.bsinterface.IInterestglBS;
import org.xpup.hafmis.sysloan.querystatistics.loanaccountquery.interestgl.form.InterestglTaAF;
import org.xpup.hafmis.sysloan.querystatistics.loanaccountquery.principalgl.action.PrincipalglTaShowAC;
import org.xpup.security.common.domain.Userslogincollbank;
/**
 * 
 * @author yuqf
 *2007-10-29
 */
public class InterestglTaShowAC extends Action{
  public static final String PAGINATION_KEY = "org.xpup.hafmis.sysloan.querystatistics.loanaccountquery.interestgl.action.InterestglTaShowAC";
  public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
    // TODO Auto-generated method stub
    SecurityInfo securityInfo = (SecurityInfo) request.getSession()
    .getAttribute("SecurityInfo");
    Pagination pagination = getPagination(InterestglTaShowAC.PAGINATION_KEY,
        request);
    IInterestglBS interestglBS = (IInterestglBS) BSUtils.getBusinessService(
        "interestglBS", this, mapping.getModuleConfig());
    PaginationUtils.updatePagination(pagination, request);
    InterestglTaAF interestglTaAF = new InterestglTaAF();
//  办事处下拉框
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
    List assistantOrgList = interestglBS.getAssistantOrgNameList();
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
    
//  单选按钮默认选办事处
    interestglTaAF = interestglBS.queryListByCriterions(pagination, securityInfo);
    interestglTaAF.setMydate(interestglBS.getMydate());
    interestglTaAF.setRadioValue((String) pagination.getQueryCriterions().get("radioValue"));
    request.getSession().setAttribute("theinterestglTaAFlist", interestglTaAF.getPrintlist());
    request.setAttribute("theinterestglTaAF", interestglTaAF);
    return mapping.findForward("to_interestglTa_show");
  }
  private Pagination getPagination(String paginationKey,
      HttpServletRequest request) {
    Pagination pagination = (Pagination) request.getSession().getAttribute(
        paginationKey);
    if (pagination == null) {
      HashMap m = new HashMap();
      m.put("radioValue", "1");
      pagination = new Pagination(0, 12, 1, "t1.flow_head_id", "ASC", m);
      request.getSession().setAttribute(paginationKey, pagination);
    }
    return pagination;
  }
}
