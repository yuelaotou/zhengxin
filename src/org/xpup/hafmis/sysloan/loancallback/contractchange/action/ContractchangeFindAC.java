package org.xpup.hafmis.sysloan.loancallback.contractchange.action;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.xpup.common.util.Pagination;
import org.xpup.hafmis.sysloan.loancallback.contractchange.form.ContractchangeAF;

public class ContractchangeFindAC extends Action{
  private HashMap criterions = null;
  public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
    // TODO Auto-generated method stub
    ContractchangeAF af = (ContractchangeAF)form;
    criterions = makeCriterionsMap(af);
    Pagination pagination = new Pagination(0, 10, 1, "t1.bizdate", "ASC",
        criterions);
    String paginationKey = getPaginationKey();
    request.getSession().setAttribute(paginationKey, pagination);
    modifyPagination(pagination);
    return mapping.findForward("to_contractchangeShowAC");
  }
  private String getPaginationKey() {
    // TODO Auto-generated method stub
    return ContractchangeShowAC.PAGINATION_KEY;
  }
  private HashMap makeCriterionsMap(ContractchangeAF af) {
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
    String startDate = af.getStartDate();//开始时间
    if(startDate!=null && !"".equals(startDate)){
      m.put("startDate", startDate.trim());
    }
    String endDate = af.getEndDate();//结束时间
    if(endDate!=null && !"".equals(endDate)){
      m.put("endDate", endDate.trim());
    }
    m.put("key", "value");
    return m;
  }
  private void modifyPagination(Pagination pagination) {
    // TODO Auto-generated method stub
    
  }
}
