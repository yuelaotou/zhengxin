package org.xpup.hafmis.sysloan.loanapply.giveacc.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.xpup.common.util.BSUtils;
import org.xpup.hafmis.common.util.BusiConst;
import org.xpup.hafmis.common.util.BusiTools;
import org.xpup.hafmis.sysloan.loanapply.giveacc.bsinterface.IGiveaccBS;
import org.xpup.hafmis.sysloan.loanapply.giveacc.dto.GiveaccModifyDTO;
import org.xpup.hafmis.sysloan.loanapply.giveacc.form.GiveaccModifyAF;

public class GiveaccTaShowAC extends Action {
  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    // TODO Auto-generated method stub
    try {
      GiveaccModifyAF giveaccModifyAF = new GiveaccModifyAF();
      GiveaccModifyDTO giveaccModifyDTO = new GiveaccModifyDTO();
      giveaccModifyAF.setType("1");
      if (!"1".equals((String) request.getAttribute("type"))) {
        giveaccModifyAF.setType("2");
        IGiveaccBS giveaccBS = (IGiveaccBS) BSUtils.getBusinessService(
            "giveaccBS", this, mapping.getModuleConfig());
        if (!""
            .equals((String) request.getSession().getAttribute("contractId"))) {
          String houseType = (String) request.getSession().getAttribute(
              "houseType");
          giveaccModifyDTO = giveaccBS.findGiveaccInfo((String) request
              .getSession().getAttribute("contractId"), houseType);
          String cardKind = "";
          if (!"".equals(giveaccModifyDTO.getCardKind())) {
            cardKind = BusiTools.getBusiValue(Integer.parseInt(giveaccModifyDTO
                .getCardKind().toString()), BusiConst.DOCUMENTSSTATE);
          }
          if (cardKind != null && !"".equals(cardKind.trim())) {
            giveaccModifyAF.setCardKind(cardKind);
          }
          String borrowerName = giveaccModifyDTO.getBorrowerName();
          if (borrowerName != null && !"".equals(borrowerName.trim())) {
            giveaccModifyAF.setBorrowerName(borrowerName);
          }
          String cardNum = giveaccModifyDTO.getCardNum();
          if (cardNum != null && !"".equals(cardNum.trim())) {
            giveaccModifyAF.setCardNum(cardNum);
          }
          String oldSellerPayBank = giveaccModifyDTO.getOldSellerPayBank();
          if (oldSellerPayBank != null && !"".equals(oldSellerPayBank.trim())) {
            giveaccModifyAF.setOldSellerPayBank(oldSellerPayBank);
          }
          String oldPayBankAcc = giveaccModifyDTO.getOldPayBankAcc();
          if (oldPayBankAcc != null && !"".equals(oldPayBankAcc.trim())) {
            giveaccModifyAF.setOldPayBankAcc(oldPayBankAcc);
          }
          String sellerName = giveaccModifyDTO.getSellerName();
          if (sellerName != null && !"".equals(sellerName.trim())) {
            giveaccModifyAF.setSellerName(sellerName);
          }
          String remark = giveaccModifyDTO.getRemark();
          if (remark != null && !"".equals(remark.trim())) {
            giveaccModifyAF.setRemark(remark);
          }
          giveaccModifyAF.setContractId((String) request
              .getSession().getAttribute("contractId"));
        }
      }
      request.setAttribute("giveaccModifyAF", giveaccModifyAF);
    } catch (Exception e) {
      // TODO: handle exception
      e.printStackTrace();
    }
    return mapping.findForward("to_show_giveacc");
  }
}
