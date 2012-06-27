package org.xpup.hafmis.sysloan.loanapply.beforeloanapply.action;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.xpup.common.exception.BusinessException;
import org.xpup.common.util.BSUtils;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.sysloan.common.arithmetic.corpusinterest.CorpusinterestBS;
import org.xpup.hafmis.sysloan.loanapply.beforeloanapply.bsinterface.IBeforeLoanApplyBS;

public class CaculateLoanRateForwardURLAC extends Action {

  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response) {

    IBeforeLoanApplyBS beforeLoanApplyBS = (IBeforeLoanApplyBS) BSUtils
        .getBusinessService("beforeLoanApplyBS", this, mapping
            .getModuleConfig());
    SecurityInfo securityInfo = (SecurityInfo) request.getSession()
        .getAttribute("SecurityInfo");
    String office = securityInfo.getUserInfo().getOfficeId().toString();
    try {
      BigDecimal minMonthRate = beforeLoanApplyBS.findMonthRate(office, 1);
      BigDecimal maxMonthRate = beforeLoanApplyBS.findMonthRate(office, 6);
      // BigDecimal minYearRate=minMonthRate.multiply(new BigDecimal(12));
      // BigDecimal maxYearRate=maxMonthRate.multiply(new BigDecimal(12));
      List list = new ArrayList();
      String corpusInterest_list[] = null;

      list.add(minMonthRate);
      list.add(maxMonthRate);

      for (int i = 1; i <= 30; i++) {
        corpusInterest_list = new String[30];
        if (i <= 5) {
          for (int j = 1; j <= 30; j++) {
            BigDecimal corpusInterest = CorpusinterestBS.getCorpusInterest(
                new BigDecimal(j * 10000 + ""), minMonthRate, i * 12 + "");
            corpusInterest_list[j - 1] = corpusInterest + "";
          }
        } else {
          for (int k = 1; k <= 30; k++) {
            BigDecimal corpusInterest = CorpusinterestBS.getCorpusInterest(
                new BigDecimal(k * 10000 + ""), maxMonthRate, i * 12 + "");
            corpusInterest_list[k - 1] = corpusInterest + "";
          }
        }
        list.add(corpusInterest_list);
      }
      request.setAttribute("list", list);

    } catch (BusinessException e) {
      e.printStackTrace();
    } catch (Exception e) {
      e.printStackTrace();
    }
    return mapping.findForward("caculatecorpus");
  }
}
