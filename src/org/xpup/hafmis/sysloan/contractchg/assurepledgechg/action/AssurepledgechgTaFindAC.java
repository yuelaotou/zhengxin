package org.xpup.hafmis.sysloan.contractchg.assurepledgechg.action;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.xpup.common.util.Pagination;
import org.xpup.hafmis.sysloan.contractchg.assurepledgechg.form.AssurepledgechgTaAF;



public class AssurepledgechgTaFindAC extends Action{
  private HashMap criterions = null;
  public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
    // TODO Auto-generated method stub
    
    AssurepledgechgTaAF assurepledgechgTaAF = (AssurepledgechgTaAF)form;
    criterions = makeCriterionsMap(assurepledgechgTaAF);
    Pagination pagination = new Pagination(0, 10, 1, "t1.contract_id", "ASC",
        criterions);
    String paginationKey = getPaginationKey();
    request.getSession().setAttribute(paginationKey, pagination);
    modifyPagination(pagination);
    assurepledgechgTaAF.reset(mapping, request);
    return mapping.findForward("to_assurepledgechgTaShowAC");
  }
  protected String getPaginationKey() {

    return AssurepledgechgTaShowAC.PAGINATION_KEY;
  }

  protected HashMap makeCriterionsMap(AssurepledgechgTaAF form) {
    HashMap m = new HashMap();
    String contractId = form.getContractId();
    if (contractId != null && !"".equals(contractId)) {
      m.put("contractId", contractId.trim());
    }

    String debitter = form.getDebitter();
    if (debitter != null && !"".equals(debitter)) {
      m.put("debitter", debitter.trim());
    }

    String empId = form.getEmpId();
    if (empId != null && !"".equals(empId)) {
      m.put("empId", empId.trim());
    }

    String cardNum = form.getCardNum();
    if (cardNum != null && !"".equals(cardNum)) {
      m.put("cardNum", cardNum.trim());
    }

    String houseType = form.getHouseType();
    if(houseType != null && !"".equals(houseType)){
      m.put("houseType", houseType.trim());
    }

    return m;
  }
  protected void modifyPagination(Pagination pagination) {
  }
}
