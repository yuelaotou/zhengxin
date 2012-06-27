/*
 * Generated by MyEclipse Struts
 * Template path: templates/java/JavaClass.vtl
 */
package org.xpup.hafmis.sysloan.loanapply.loanapply.action;

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
import org.xpup.hafmis.sysloan.loanapply.loanapply.form.LoanapplyTbNewAF;

/**
 * MyEclipse Struts Creation date: 09-26-2007 XDoclet definition:
 * 
 * @struts.action validate="true"
 */
public class LoanapplyTbShowAC extends Action {

  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response) {

    LoanapplyTbNewAF loanapplytbnewAF = new LoanapplyTbNewAF();
    request.getSession().setAttribute(LoanapplyTeShowAC.PAGINATION_KEY, null);
    ILoanapplyBS loanapplyBS = (ILoanapplyBS) BSUtils.getBusinessService(
        "loanapplyBS", this, mapping.getModuleConfig());

    SecurityInfo securityInfo = (SecurityInfo) request.getSession()
        .getAttribute("SecurityInfo");
    try {
      String contractIdHL = (String) request.getSession().getAttribute(
          "contractIdHL");// 判断是否是从维护的超链接过来的
      String contractid = (String) request.getSession().getAttribute(
          "contractid");
      if (contractIdHL != null) {
        contractid = contractIdHL;
      }
      String maxauxiliaryid = (String) request.getAttribute("maxauxiliaryid");
      String remend = (String) request.getSession().getAttribute("remend");// 判断是否是维护过来的
      String temp_addtype = (String) request.getSession().getAttribute(
          "temp_addtype");// 判断是否走过添加按钮
      request.getSession().setAttribute("temp_addtype", null);
      if (contractid != null) {
        loanapplytbnewAF = loanapplyBS.findAssistanBorrowerInfo(contractid
            .trim(), securityInfo, maxauxiliaryid);
        request.setAttribute("loanType_wsh", loanapplytbnewAF.getLoanType());
        loanapplytbnewAF.setSexhidden(loanapplytbnewAF.getSex());
        loanapplytbnewAF.setCardKindhidden(loanapplytbnewAF.getCardKind());
        if (loanapplytbnewAF.getList().size() == 0) {
          loanapplytbnewAF.setCoodm_type("4");// 没有辅助借款人信息,下面按钮禁用
        }
        String maxid = loanapplytbnewAF.getMaxauxiliaryid();

        if (maxid != null && temp_addtype == null) {
          request.getSession().setAttribute("maxauxiliaryid", maxid);
        }
        if (temp_addtype != null) {
          LoanapplyTbNewAF laAF = new LoanapplyTbNewAF();
          laAF.setTemp_tes("5");
          laAF.setList(loanapplytbnewAF.getList());
          laAF.setContractId(loanapplytbnewAF.getContractId());
          laAF.setBorrowerName(loanapplytbnewAF.getBorrowerName());
          if (loanapplytbnewAF.getList().size() == 0) {
            laAF.setCoodm_type("4");// 没有辅助借款人信息,下面按钮禁用
          }
          loanapplytbnewAF = laAF;
        }
        if (remend != null) {
          loanapplytbnewAF.setTypenum("1");
        } else {
          loanapplytbnewAF.setTypenum("2");
        }
      } else {
        loanapplytbnewAF.setTypenum("2");
        loanapplytbnewAF.setCoodm_type("5");// 没有合同编号和辅助借款人，所有按钮禁用
      }
      loanapplytbnewAF.setRelationMap(BusiTools.listBusiProperty(BusiConst.RELATION));
      loanapplytbnewAF.setSexMap(BusiTools.listBusiProperty(BusiConst.SEX));
      loanapplytbnewAF.setCardkingMap(BusiTools
          .listBusiProperty(BusiConst.DOCUMENTSSTATE));
      loanapplytbnewAF.setCount(loanapplytbnewAF.getList().size() + "");
      request.setAttribute("loanapplytbnewAF", loanapplytbnewAF);

    } catch (Exception e) {
      e.printStackTrace();
    }

    return mapping.findForward("assistantborrower_new");
  }
}