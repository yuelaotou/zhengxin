package org.xpup.hafmis.sysloan.querystatistics.checkqueryplfn.action;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.xpup.common.util.Pagination;
import org.xpup.hafmis.sysloan.querystatistics.checkqueryplfn.form.CheckQueryPlFnAF;

public class CheckQueryPlFnFindAC extends Action {
  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response) {
    try{
      CheckQueryPlFnAF checkQueryPlFnAF = (CheckQueryPlFnAF) form;
      HashMap criterions = makeCriterionsMap(checkQueryPlFnAF);
      Pagination pagination = new Pagination(0, 10, 1, " p11.contract_id ", "DESC",criterions);
      String paginationKey = getPaginationKey();
      request.getSession().setAttribute(paginationKey, pagination);
      modifyPagination(pagination);
  }catch(Exception e){
    e.printStackTrace();
  }
    return mapping.findForward("checkQueryPlFn_show");
  }
  protected String getPaginationKey() {
    return CheckQueryPlFnShowAC.PAGINATION_KEY;
  }
  protected HashMap makeCriterionsMap(CheckQueryPlFnAF form) {
    HashMap m = new HashMap();
    String contractid = form.getCheckQueryPlFnFindDTO().getContractid();
    if (!(contractid == null || "".equals(contractid.trim()))) {
        m.put("contractid", contractid.trim());
    }

    String borrowername = form.getCheckQueryPlFnFindDTO().getBorrowername();
    if (!(borrowername == null || borrowername.trim().length() == 0)){
      m.put("borrowername", borrowername.trim());
    }
    
    String loankouacc = form.getCheckQueryPlFnFindDTO().getLoankouacc();
    if (!(loankouacc == null || loankouacc.trim().length() == 0)){
      m.put("loankouacc", loankouacc.trim());
    }

    String cardnum = form.getCheckQueryPlFnFindDTO().getCardnum();
    if (!(cardnum == null || cardnum.trim().length() == 0)){
      m.put("cardnum", cardnum.trim());
    }

    String loanstartdateSt = form.getCheckQueryPlFnFindDTO().getLoanstartdateSt();
    if (!(loanstartdateSt == null || loanstartdateSt.trim().length() == 0)){
      m.put("loanstartdateSt", loanstartdateSt.trim());
    }

    String loanstartdateEnd = form.getCheckQueryPlFnFindDTO().getLoanstartdateEnd();
    if (!(loanstartdateEnd == null || loanstartdateEnd.trim().length() == 0)){
      m.put("loanstartdateEnd", loanstartdateEnd.trim());
    }
    return m;
  }

  protected void modifyPagination(Pagination pagination) {
  }
}
