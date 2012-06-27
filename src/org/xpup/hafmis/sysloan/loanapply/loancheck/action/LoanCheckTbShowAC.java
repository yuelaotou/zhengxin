package org.xpup.hafmis.sysloan.loanapply.loancheck.action;

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

public class LoanCheckTbShowAC extends Action {

  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response) {

    LoanapplyTbNewAF loanapplytbnewAF = new LoanapplyTbNewAF();
    ILoanapplyBS loanapplyBS = (ILoanapplyBS) BSUtils.getBusinessService(
        "loanapplyBS", this, mapping.getModuleConfig());
    SecurityInfo securityInfo = (SecurityInfo) request.getSession()
        .getAttribute("SecurityInfo");
    try {
      String contractid = (String) request.getSession().getAttribute(
          "contractIdWY");
      String maxauxiliaryid = (String) request.getAttribute("maxauxiliaryid");
      if (contractid != null) {
        loanapplytbnewAF = loanapplyBS.findAssistanBorrowerInfo(contractid,
            securityInfo, maxauxiliaryid);
      }
      // 枚举转换性别
      String sex = loanapplytbnewAF.getSex();
      if (sex != null && !"".equals(sex)) {
        sex = BusiTools.getBusiValue(Integer.parseInt(sex), BusiConst.SEX);
        loanapplytbnewAF.setSex(sex);
      }
      // 枚举转换证件类型
      String cardKind = loanapplytbnewAF.getCardKind();
      if (cardKind != null && !"".equals(cardKind)) {
        cardKind = BusiTools.getBusiValue(Integer.parseInt(cardKind),
            BusiConst.DOCUMENTSSTATE);
        loanapplytbnewAF.setCardKind(cardKind);
      }
      loanapplytbnewAF.setCount(loanapplytbnewAF.getList().size()+"");
      loanapplytbnewAF.setRelationMap(BusiTools.listBusiProperty(BusiConst.RELATION));
      request.setAttribute("loanapplytbnewAF", loanapplytbnewAF);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return mapping.findForward("to_loanchecktb_show");
  }
}
