package org.xpup.hafmis.sysloan.loanapply.beforeloanapply.action;

import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.xpup.common.exception.BusinessException;
import org.xpup.common.util.BSUtils;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.sysloan.loanapply.beforeloanapply.bsinterface.IBeforeLoanApplyBS;
import org.xpup.hafmis.sysloan.loanapply.beforeloanapply.form.BeforeLoanApplyShowAF;

public class BeforeLoanApplyAAC extends Action {

  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response) {
    response.setContentType("text/html;charset=UTF-8");
    response.setHeader("Cache-Control", "no-cache");
    try {
      String empid = (String) request.getParameter("empid");
      String orgid = (String) request.getParameter("orgid");
      String type = (String) request.getParameter("type");
      IBeforeLoanApplyBS beforeLoanApplyBS = (IBeforeLoanApplyBS) BSUtils
          .getBusinessService("beforeLoanApplyBS", this, mapping
              .getModuleConfig());
      SecurityInfo securityInfo = (SecurityInfo) request.getSession()
      .getAttribute("SecurityInfo");
      BeforeLoanApplyShowAF beforeLoanApplyShowAF = beforeLoanApplyBS
          .queryEmpInfo(empid, orgid,securityInfo);
      if(beforeLoanApplyShowAF.getEmpBalanceUse().substring(0,1).equals("-")){
        beforeLoanApplyShowAF.setEmpBalanceUse("0");
      }
      String text = "";
      text = "display('" + beforeLoanApplyShowAF.getEmpId() + "','"
          + beforeLoanApplyShowAF.getEmpname() + "','"
          + beforeLoanApplyShowAF.getEmpSex() + "','"
          + beforeLoanApplyShowAF.getEmpCardkind() + "','"
          + beforeLoanApplyShowAF.getEmpCardnum() + "','"
          + beforeLoanApplyShowAF.getEmpBirthDay() + "','"
          + beforeLoanApplyShowAF.getEmpAge() + "','"
          + beforeLoanApplyShowAF.getEmpSalaryBase() + "','"
          + beforeLoanApplyShowAF.getEmpMonthPay() + "','"
          + beforeLoanApplyShowAF.getEmpBalance() + "','"
          + beforeLoanApplyShowAF.getEmpStatus() + "','"
          + beforeLoanApplyShowAF.getEmpContinus() + "','"
          + beforeLoanApplyShowAF.getEmpBalanceUse() + "','"
          + beforeLoanApplyShowAF.getMatter() + "','"
          + beforeLoanApplyShowAF.getOrgId() + "','"
          + beforeLoanApplyShowAF.getOrgname() + "','"
          + type + "')";

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