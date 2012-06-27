package org.xpup.hafmis.sysloan.loanapply.loanvipcheck.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.xpup.common.util.BSUtils;
import org.xpup.hafmis.common.util.BusiConst;
import org.xpup.hafmis.common.util.BusiTools;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.sysloan.loanapply.loanapply.bsinterface.ILoanapplyBS;
import org.xpup.hafmis.sysloan.loanapply.loanapply.form.LoanapplyNewAF;
import org.xpup.hafmis.sysloan.loanapply.loanvipcheck.bsinterface.ILoanVIPCheckBS;

/**
 * @author 王野 2007-09-28
 */
public class LoanVIPCheckTaShowAC extends Action {

  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response) {
    LoanapplyNewAF loanapplynewAF = new LoanapplyNewAF();
    ILoanapplyBS loanapplyBS = (ILoanapplyBS) BSUtils.getBusinessService(
        "loanapplyBS", this, mapping.getModuleConfig());
    ILoanVIPCheckBS loanVIPCheckBS = (ILoanVIPCheckBS) BSUtils
        .getBusinessService("loanVIPCheckBS", this, mapping.getModuleConfig());
    SecurityInfo securityInfo = (SecurityInfo) request.getSession()
        .getAttribute("SecurityInfo");
    String contractId = null;
    try {
      String contractid = (String) request.getSession().getAttribute(
          "contractIdWY");
      contractId = (String) request.getParameter("contractIdWY");
      if (contractId != null && !contractId.equals("")) {
        contractid = contractId;
        request.getSession().setAttribute("contractIdWY", contractId);
      }
      loanapplynewAF = loanapplyBS.showLoanapplyInfoBycontractid(contractid,
          securityInfo);
      //获得合同相关所有图片路径
      List photoURLList = loanapplyBS.queryPhotoURLByContractID(contractid);
      request.getSession().setAttribute("photoURLList",photoURLList);
      // 求办事处
      if (loanapplynewAF.getBorrower().getOffice() != null
          && !loanapplynewAF.getBorrower().getOffice().equals("")) {
        String office = loanVIPCheckBS.changeOffice(loanapplynewAF
            .getBorrower().getOffice().toString());
        loanapplynewAF.getBorrower().setOffice(office);
      }
      String sex = BusiTools.getBusiValue(Integer.parseInt(loanapplynewAF
          .getBorrower().getSex()), BusiConst.SEX);
      loanapplynewAF.getBorrower().setSex(sex);
      String cardKind = BusiTools.getBusiValue(Integer.parseInt(loanapplynewAF
          .getBorrower().getCardKind()), BusiConst.DOCUMENTSSTATE);
      loanapplynewAF.getBorrower().setCardKind(cardKind);
      request.setAttribute("loanapplynewAF", loanapplynewAF);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return mapping.findForward("to_loanvipcheckta_show");
  }
}