package org.xpup.hafmis.sysloan.loanapply.beforeloanapply.action;

import java.io.IOException;
import java.math.BigDecimal;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.xpup.common.exception.BusinessException;
import org.xpup.common.util.BSUtils;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.syscollection.pickupmng.pickup.dto.Convert;
import org.xpup.hafmis.sysloan.common.arithmetic.corpusinterest.CorpusinterestBS;
import org.xpup.hafmis.sysloan.dataready.loanconditionsset.bsinterface.ILoanConditionsSetBS;
import org.xpup.hafmis.sysloan.dataready.loanconditionsset.dto.LoanConditionsSetDTO;
import org.xpup.hafmis.sysloan.loanapply.beforeloanapply.bsinterface.IBeforeLoanApplyBS;

public class BeforeLoanApplyAAAAC extends Action {

  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response) {
    response.setContentType("text/html;charset=UTF-8");
    response.setHeader("Cache-Control", "no-cache");
    try {
      String uMaxLoanYear = request.getParameter("uMaxLoanYear");// 贷款年限
      String uMaxLoanMoney = request.getParameter("uMaxLoanMoney");// 您的公积金可用额度

      String spouseBalanceUse = request.getParameter("spouseBalanceUse");
      String empBalanceUse = request.getParameter("empBalanceUse");
      IBeforeLoanApplyBS beforeLoanApplyBS = (IBeforeLoanApplyBS) BSUtils
          .getBusinessService("beforeLoanApplyBS", this, mapping
              .getModuleConfig());
      SecurityInfo securityInfo = (SecurityInfo) request.getSession()
          .getAttribute("SecurityInfo");
      String office = securityInfo.getUserInfo().getOfficeId().toString();
      String monthRate = beforeLoanApplyBS.findMonthRate(office,
          Integer.parseInt(uMaxLoanYear)).toString();
      BigDecimal corpusInterest = CorpusinterestBS.getCorpusInterest(
          new BigDecimal(uMaxLoanMoney + ""), new BigDecimal(monthRate),
          Integer.parseInt(uMaxLoanYear) * 12 + "");

      BigDecimal empYue = new BigDecimal(empBalanceUse).divide(corpusInterest,
          0, BigDecimal.ROUND_DOWN);
      String spouseYue = "";
      if (spouseBalanceUse != null && !spouseBalanceUse.equals("")) {
        spouseYue = new BigDecimal(spouseBalanceUse).divide(corpusInterest, 0,
            BigDecimal.ROUND_DOWN).toString();
      }

      String text = "";
      text = "sun_display1('" + empYue + "','" + spouseYue + "','"
          + uMaxLoanYear + "','" + Float.parseFloat(String.valueOf(new BigDecimal(monthRate).multiply(new BigDecimal(1000))))+"‰"+ "','" + uMaxLoanMoney + "','"
          + corpusInterest + "','" + Float.parseFloat(String.valueOf(new BigDecimal(monthRate).multiply(new BigDecimal(100))))*12+"%"+ "')";
      response.getWriter().write(text);
      response.getWriter().close();

    } catch (IOException e) {
      e.printStackTrace();
    } catch (BusinessException e) {
      e.printStackTrace();
    } catch (Exception e) {
      e.printStackTrace();
    }
    return null;
  }

}