package org.xpup.hafmis.sysloan.loanapply.loanreturnedbyfund.action;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;

import org.xpup.common.exception.BusinessException;
import org.xpup.common.util.BSUtils;
import org.xpup.hafmis.common.form.IdAF;
import org.xpup.hafmis.common.util.BusiConst;
import org.xpup.hafmis.common.util.BusiTools;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.syscollection.common.domain.entity.Emp;
import org.xpup.hafmis.sysloan.common.domain.entity.AssistantBorrower;
import org.xpup.hafmis.sysloan.common.domain.entity.Borrower;
import org.xpup.hafmis.sysloan.common.domain.entity.BorrowerAcc;
import org.xpup.hafmis.sysloan.common.domain.entity.BorrowerLoanInfo;
import org.xpup.hafmis.sysloan.loanapply.issuenotice.bsinterface.IIssuenoticeBS;
import org.xpup.hafmis.sysloan.loanapply.issuenotice.form.IssuenoticeTaAF;
import org.xpup.hafmis.sysloan.loanapply.loanreturnedbyfund.bsinterface.ILoanreturnedbyfundBS;
import org.xpup.hafmis.sysloan.loanapply.loanreturnedbyfund.form.LoanreturnedbyfundTaAF;

public class LoanreturnedbyfundTbRevertAC extends Action {
  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception, BusinessException {
    String contractId = (String) request.getParameter("contractId");
    String xieYiNum = (String) request.getParameter("xieYiNum");
    String year = (String) request.getParameter("year");
    String realName = "";
    LoanreturnedbyfundTaAF loanreturnedbyfundTaAF = new LoanreturnedbyfundTaAF();
    List list = new ArrayList();
    List list_fuzhuren = new ArrayList();
    Borrower borrower = new Borrower();
    AssistantBorrower assistantBorrower = new AssistantBorrower();
    
    ILoanreturnedbyfundBS loanreturnedbyfundBS = (ILoanreturnedbyfundBS) BSUtils
        .getBusinessService("loanreturnedbyfundBS", this, mapping
            .getModuleConfig());
    SecurityInfo securityInfo = (SecurityInfo) request.getSession()
        .getAttribute("SecurityInfo");
    try {

      if (contractId != null) {
        borrower = loanreturnedbyfundBS.showLoanapplyInfoBycontractid(
            contractId, securityInfo);
        String value = "";
        value = loanreturnedbyfundBS.LoanreturnedbyfundCheck(borrower
            .getOffice(), "5");
        assistantBorrower = loanreturnedbyfundBS
            .findAssistanBorrower(contractId);
        if (list_fuzhuren.size() > 0) {
          assistantBorrower = (AssistantBorrower) list_fuzhuren.get(0);
        }

        if ("0".equals(value)) {
          list = loanreturnedbyfundBS.findAssistanBorrowerInfo(contractId);
          loanreturnedbyfundTaAF.setList(list);
        }
        borrower.setCardKind(BusiTools.getBusiValue(Integer.parseInt(borrower
            .getCardKind()), BusiConst.DOCUMENTSSTATE));
        borrower.setReserveaC(loanreturnedbyfundBS.findOfficeName(borrower
            .getOffice()));
        loanreturnedbyfundTaAF.setSexc(borrower.getSex());
        loanreturnedbyfundTaAF.setCardkingc(borrower.getCardKind());
        loanreturnedbyfundTaAF.setBorrower(borrower);

        loanreturnedbyfundTaAF.setAssistantBorrower(assistantBorrower);

        // 新增页面显示字段
        // 若有辅助借款人
        BigDecimal moneyYue = new BigDecimal("0.00");// 借款人的最大提取金额
        BigDecimal moneyYue_1 = new BigDecimal("0.00");// 辅助借款人的最大提取金额
        Emp emp = new Emp();
        BorrowerAcc borrowerAcc = loanreturnedbyfundBS.findBorrowerAcc(borrower
            .getContractId());
        BorrowerLoanInfo borrowerLoanInfo = loanreturnedbyfundBS
            .findBorrowerLoanInfo(borrower.getContractId());
        if (borrowerAcc != null) {
          // 月还本息
          if (borrowerLoanInfo != null) {
            loanreturnedbyfundTaAF.setYueHuanBenXi(borrowerLoanInfo
                .getCorpusInterest().toString());
          }
          if (borrower.getEmpId() != null && !"".equals(borrower.getEmpId())) {
            emp = loanreturnedbyfundBS.findEmpInfo(borrower.getEmpId()
                .toString(), borrower.getOrgId().toString());
            if (emp.getPayOldYear() != null) {
              moneyYue = (emp.getCurBalance().add(emp.getPreBalance()))
                  .subtract(emp.getPayOldYear().multiply(new BigDecimal("12")));
              if (moneyYue.compareTo(new BigDecimal("0.00")) > 0) {
                loanreturnedbyfundTaAF.setBorrowerMaxMoney(moneyYue.toString());
              } else {
                loanreturnedbyfundTaAF.setBorrowerMaxMoney("0.00");
              }

              loanreturnedbyfundTaAF.setYueJiaoCun(emp.getEmpPay().add(
                  emp.getOrgPay()).toString());
              loanreturnedbyfundTaAF.setYuE(emp.getCurBalance().add(
                  emp.getPreBalance()).toString());
            } else {
              loanreturnedbyfundTaAF.setBorrowerMaxMoney("");
            }
          }
          if (assistantBorrower != null) {
            emp = new Emp();
            if (assistantBorrower.getEmpId() != null
                && !"".equals(assistantBorrower.getEmpId())) {
              emp = loanreturnedbyfundBS.findEmpInfo(assistantBorrower
                  .getEmpId().toString(), assistantBorrower.getOrgId()
                  .toString());
              if (emp.getPayOldYear() != null) {
                moneyYue_1 = (emp.getCurBalance().add(emp.getPreBalance()))
                    .subtract(emp.getPayOldYear()
                        .multiply(new BigDecimal("12")));
                if (moneyYue_1.compareTo(new BigDecimal("0.00")) > 0) {
                  loanreturnedbyfundTaAF.setAssiMaxMoney(moneyYue_1.toString());
                } else {
                  loanreturnedbyfundTaAF.setAssiMaxMoney("0.00");
                }

                if (loanreturnedbyfundTaAF.getYueJiaoCun() != null
                    && !""
                        .equals(loanreturnedbyfundTaAF.getYueJiaoCun().trim())) {
                  loanreturnedbyfundTaAF.setYueJiaoCun((new BigDecimal(
                      loanreturnedbyfundTaAF.getYueJiaoCun()).add(emp
                      .getEmpPay().add(emp.getOrgPay())).toString()));
                } else {
                  loanreturnedbyfundTaAF.setYueJiaoCun(emp.getEmpPay().add(
                      emp.getOrgPay()).toString());
                }
                if (loanreturnedbyfundTaAF.getYuE() != null
                    && !"".equals(loanreturnedbyfundTaAF.getYuE())) {
                  loanreturnedbyfundTaAF.setYuE((new BigDecimal(
                      loanreturnedbyfundTaAF.getYuE()).add(emp.getCurBalance()
                      .add(emp.getPreBalance()))).toString());
                }
              } else {
                loanreturnedbyfundTaAF.setAssiMaxMoney("");
              }

            }
          }
          loanreturnedbyfundTaAF.setMaxPickMoney((moneyYue_1.add(moneyYue)));
          loanreturnedbyfundTaAF.setMaxMonth(String.valueOf(moneyYue_1.add(
              moneyYue).intValue()
              / borrowerLoanInfo.getCorpusInterest().intValue()));
          if ((moneyYue_1.add(moneyYue)).compareTo(new BigDecimal("0.00")) < 0) {
            loanreturnedbyfundTaAF.setMaxPickMoney(new BigDecimal(0.00));
            loanreturnedbyfundTaAF.setMaxMonth("0");

          }
          loanreturnedbyfundTaAF.setLoanMoney(borrowerAcc.getLoanMoney()
              .toString());
          loanreturnedbyfundTaAF.setLoanTime(borrowerAcc.getLoanTimeLimit());
          loanreturnedbyfundTaAF.setLoanYuE(borrowerAcc.getOverplusLoanMoney()
              .toString());
          loanreturnedbyfundTaAF
              .setShengYuTime(borrowerAcc.getOverplusLimite());
        }
        // 若无辅助借款人
        // 新增页面显示字段
        if (assistantBorrower == null) {
          assistantBorrower = new AssistantBorrower();
          loanreturnedbyfundTaAF.setAssistantBorrower(assistantBorrower);
        }
        request.getSession().setAttribute("office", borrower.getOffice());
      }
      loanreturnedbyfundBS.updateLoanreturnedbyfund_1(contractId, securityInfo
          .getUserInfo().getPlbizDate());
      loanreturnedbyfundTaAF.setBizTime(year);
      loanreturnedbyfundTaAF.setXieYinum(xieYiNum);
      request.getSession().setAttribute("username", realName);
      request.setAttribute("loanreturnedbyfundTaAF", loanreturnedbyfundTaAF);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return mapping.findForward("loanreturnedbyfundTaTbRevertPrint");
  }
}
