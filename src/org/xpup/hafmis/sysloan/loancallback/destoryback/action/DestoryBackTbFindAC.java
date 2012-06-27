package org.xpup.hafmis.sysloan.loancallback.destoryback.action;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.xpup.common.util.Pagination;
import org.xpup.hafmis.sysloan.loancallback.destoryback.form.DestoryBackTbAF;

public class DestoryBackTbFindAC extends Action {

  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    DestoryBackTbAF destoryBackTbAF = (DestoryBackTbAF) form;
    HashMap criterions = makeCriterionsMap(destoryBackTbAF);
    Pagination pagination = new Pagination(0, 10, 1,  "docnum", "DESC",
        criterions);
   
   
    String paginationKey = getPaginationKey();
    request.getSession().setAttribute(paginationKey, pagination);
    return mapping.findForward("destoryBackTbShowAC");
  }

  protected HashMap makeCriterionsMap(DestoryBackTbAF form) {
    HashMap m = new HashMap();
  
    String docNum=form.getDocNum().trim();// 凭证编号
    if (docNum != null && !"".equals(docNum)) {
      m.put("docNum", docNum);
    }
    
    String contractId=form.getContractId().trim();// 合同编号
    if (contractId != null && !"".equals(contractId)) {
      m.put("contractId", contractId);
    }
    
    String loanKouAcc=form.getLoanKouAcc().trim();// 贷款账号
    if (loanKouAcc!= null && !"".equals(loanKouAcc)) {
      m.put("loanKouAcc", loanKouAcc);
    }
    
    String borrowerName = form.getBorrowerName().trim();//借款人姓名
    if (borrowerName != null && borrowerName.length() > 0) {
      m.put("borrowerName", borrowerName);
    }
    
    String bizSt=form.getBizSt().trim();// 业务状态
    if (bizSt!= null && bizSt.length() > 0) {
      m.put("bizSt",bizSt);
    }
    
    String loanBankName = form.getLoanBankName().trim();// 放款银行
    if (loanBankName != null && loanBankName.length() > 0) {
      m.put("loanBankName", loanBankName);
    }
    
    String cardNum = form.getCardNum().trim();//证件号码
    if (cardNum != null && cardNum.length() > 0) {
      m.put("cardNum", cardNum);
    }

    return m;
  }

  protected String getPaginationKey() {
    return DestoryBackTbShowAC.PAGINATION_KEY;
  }
}