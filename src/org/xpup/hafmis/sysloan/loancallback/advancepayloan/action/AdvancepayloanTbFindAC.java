package org.xpup.hafmis.sysloan.loancallback.advancepayloan.action;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.xpup.common.util.Pagination;
import org.xpup.hafmis.sysloan.loancallback.advancepayloan.form.AdvancepayloanTbAF;

public class AdvancepayloanTbFindAC extends Action{
  private HashMap criterions = null;
  public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
    // TODO Auto-generated method stub
    AdvancepayloanTbAF af = (AdvancepayloanTbAF)form;
    criterions = makeCriterionsMap(af);
    Pagination pagination = new Pagination(0, 10, 1, "t1.contract_id", "ASC",
        criterions);
    String paginationKey = getPaginationKey();
    request.getSession().setAttribute(paginationKey, pagination);
    modifyPagination(pagination);
    return mapping.findForward("to_advancepayloanTbShowAC");
  }
  private void modifyPagination(Pagination pagination) {
    // TODO Auto-generated method stub
    
  }
  private String getPaginationKey() {
    // TODO Auto-generated method stub
    return AdvancepayloanTbShowAC.PAGINATION_KEY;
  }
  private HashMap makeCriterionsMap(AdvancepayloanTbAF af) {
    // TODO Auto-generated method stub
    HashMap m = new HashMap();
    String contractId = af.getContractId();//合同编号
    if(contractId!=null && !"".equals(contractId)){
      m.put("contractId", contractId.trim());
    }
    String borrowerName = af.getBorrowerName();//借款人姓名
    if(borrowerName!=null && !"".equals(borrowerName)){
      m.put("borrowerName", borrowerName.trim());
    }
    String cardNum = af.getCardNum();//证件号码
    if(cardNum!=null && !"".equals(cardNum)){
      m.put("cardNum", cardNum.trim());
    }
    String status = af.getStatus();//状态
    if(status!=null && !"".equals(status)){
      m.put("status", status.trim());
    }
    m.put("key", "value");
    return m;
  }

}
