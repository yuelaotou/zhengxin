package org.xpup.hafmis.sysloan.loancallback.loancallback.action; 

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.xpup.common.util.Pagination;
import org.xpup.hafmis.sysloan.loancallback.loancallback.form.LoancallbackTbAF;


public class LoancallbackTbFindAC extends Action {
  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {

    LoancallbackTbAF f = (LoancallbackTbAF) form;
    HashMap criterions = makeCriterionsMap(f);
    Pagination pagination = new Pagination(0, f.getPageSize(), 1,
        "headid", "DESC", criterions);
    String paginationKey = LoancallbackTbShowAC.PAGINATION_KEY;
    request.getSession().setAttribute(paginationKey, pagination);
    f.reset(mapping, request);
    return mapping.findForward(getForword());
  }

  protected String getForword() {
    return "loancallbackTbShowAC";
  }


  protected HashMap makeCriterionsMap(LoancallbackTbAF form) {
    HashMap m = new HashMap();
    
    String contractId = form.getContractId();
    if (!(contractId== null || "".equals(contractId))) {
        m.put("contractId",form.getContractId().trim());
    }
    String name = form.getBorrowerName();
    if (!(name == null || name.length() == 0))
      m.put("name", form.getBorrowerName().trim());
    
    String status = form.getStatus();
    if(!(status == null || status.length() == 0)){
      m.put("status", form.getStatus().trim());
    }
    String cardNum = form.getCardNum();
    if(!(cardNum == null || cardNum.length() == 0)){
      m.put("cardNum", form.getCardNum().trim());
    }
    String loanBankId = form.getLoanBankId();
    if(!(loanBankId == null || loanBankId.length() == 0)){
      m.put("loanBankId", form.getLoanBankId().trim());
    }
    String loanKouAcc = form.getLoanKouAcc();
    if (!(loanKouAcc == null || loanKouAcc.length() == 0))
      m.put("loanKouAcc", form.getLoanKouAcc().trim());
    
    String docNum = form.getDocNum();
    if(!(docNum == null || docNum.length() == 0)){
      m.put("docNum", form.getDocNum().trim());
    }
    String type = form.getType();
    if(!(type == null || type.length() == 0)){
      m.put("type", form.getType().trim());
    }
    String dateStart = form.getDateStart();
    if(!(dateStart == null || dateStart.length()==0)){
      m.put("dateStart",form.getDateStart().trim());
    }
    String dateEnd = form.getDateEnd();
    if(!(dateEnd == null || dateEnd.length()==0)){
      m.put("dateEnd",form.getDateEnd().trim());
    }
    String yesOrNo = form.getYesOrNo();//是否为公积金还贷  yqf 20080625 +
    if(!(yesOrNo == null || yesOrNo.length()==0)){
      m.put("yesOrNo",yesOrNo.trim());
    }
    return m;
  }

}
