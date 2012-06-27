package org.xpup.hafmis.sysloan.loanapply.receiveacc.action;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.xpup.common.util.BSUtils;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.sysloan.loanapply.receiveacc.bsinterface.IReceiveaccBS;
import org.xpup.hafmis.sysloan.loanapply.receiveacc.dto.ReceiveaccModifyDTO;
import org.xpup.hafmis.sysloan.loanapply.receiveacc.form.ReceiveaccModifyAF;
import org.xpup.security.common.domain.Userslogincollbank;

public class ReceiveaccTaShowAC extends Action {
  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    // TODO Auto-generated method stub
    try {
      ReceiveaccModifyAF receiveaccModifyAF = new ReceiveaccModifyAF();
      receiveaccModifyAF.setType("1");
      if (!"1".equals((String) request.getAttribute("type"))) {
        receiveaccModifyAF.setType("2");
        IReceiveaccBS receiveaccBS = (IReceiveaccBS) BSUtils
            .getBusinessService("receiveaccBS", this, mapping.getModuleConfig());
        if (!""
            .equals((String) request.getSession().getAttribute("contractId"))) {
          ReceiveaccModifyDTO receiveaccModifyDTO = new ReceiveaccModifyDTO();
          receiveaccModifyDTO = receiveaccBS
              .findReceiveaccInfo((String) request.getSession().getAttribute(
                  "contractId"));
          String cardKind = receiveaccModifyDTO.getCardKind().toString();

          if (cardKind != null && !"".equals(cardKind.trim())) {
            receiveaccModifyAF.setCardKind(cardKind.trim());
          }
          String borrowerName = receiveaccModifyDTO.getBorrowerName();
          if (borrowerName != null && !"".equals(borrowerName.trim())) {
            receiveaccModifyAF.setBorrowerName(borrowerName.trim());
          }
          String cardNum = receiveaccModifyDTO.getCardNum();
          if (cardNum != null && !"".equals(cardNum.trim())) {
            receiveaccModifyAF.setCardNum(cardNum.trim());
          }
          String loanankId = receiveaccModifyDTO.getLoanankId();
          if (loanankId != null && !"".equals(loanankId.trim())) {

            receiveaccModifyAF.setLoanankId(receiveaccModifyDTO.getLoanankId().trim());
          }
          String oldLoanKouAcc = receiveaccModifyDTO.getOldLoanKouAcc();
          if (oldLoanKouAcc != null && !"".equals(oldLoanKouAcc.trim())) {
            receiveaccModifyAF.setOldLoanKouAcc(oldLoanKouAcc.trim());
          }
          String orgName = receiveaccModifyDTO.getOrgName();
          if (orgName != null && !"".equals(orgName.trim())) {
            receiveaccModifyAF.setOrgName(orgName.trim());
          }
          receiveaccModifyAF.setContractId(((String) request.getSession()
              .getAttribute("contractId")).trim());
       
        }
       
      }
      // 放款银行下拉框
      SecurityInfo securityInfo = (SecurityInfo) request.getSession()
      .getAttribute("SecurityInfo");
      List loanBankNameList = new ArrayList();
      List bangkList = securityInfo.getDkUserBankList();
      Userslogincollbank userslogincollbank = null;
      Iterator bank = bangkList.iterator();
      while (bank.hasNext()) {
        userslogincollbank = (Userslogincollbank) bank.next();
        loanBankNameList.add(new org.apache.struts.util.LabelValueBean(
            userslogincollbank.getCollBankName(), userslogincollbank
                .getCollBankId().toString()));
      }
      request.getSession(true).setAttribute("loanBankNameList",
          loanBankNameList);
      request.setAttribute("receiveaccModifyAF", receiveaccModifyAF);
    } catch (Exception e) {
      // TODO: handle exception
      e.printStackTrace();
    }
    return mapping.findForward("to_show_receiveacc");
  }

}
