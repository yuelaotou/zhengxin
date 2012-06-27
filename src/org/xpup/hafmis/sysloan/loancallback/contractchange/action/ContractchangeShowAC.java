package org.xpup.hafmis.sysloan.loancallback.contractchange.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.xpup.common.util.BSUtils;
import org.xpup.common.util.Pagination;
import org.xpup.common.util.PaginationUtils;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.sysloan.loanapply.loandelete.bsinterface.ILoandeleteBS;
import org.xpup.hafmis.sysloan.loanapply.loandelete.form.LoandeleteAF;
import org.xpup.hafmis.sysloan.loancallback.contractchange.bsinterface.IContractchangeBS;
import org.xpup.hafmis.sysloan.loancallback.contractchange.form.ContractchangeAF;
import org.xpup.security.common.domain.Userslogincollbank;

public class ContractchangeShowAC extends Action {

  /**
   * 提前还款变更合同打印 查询PL101_1
   */
  public static final String PAGINATION_KEY = "org.xpup.hafmis.sysloan.loancallback.contractchange.action.ContractchangeShowAC";

  public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
    // TODO Auto-generated method stub
    String type=(String)request.getSession().getAttribute("type");
    try{
      List list = new ArrayList();
      ContractchangeAF contractchangeAF=new ContractchangeAF();
      Pagination pagination = getPagination(PAGINATION_KEY, request); 
      PaginationUtils.updatePagination(pagination, request);
      SecurityInfo securityInfo = (SecurityInfo) request.getSession().getAttribute(
      "SecurityInfo");
      pagination.getQueryCriterions().put("type", type);
      IContractchangeBS contractchangeBS = (IContractchangeBS) BSUtils
      .getBusinessService("contractchangeBS", this, mapping.getModuleConfig());
      list = contractchangeBS.queryContractchangeList(pagination, securityInfo);
      contractchangeAF.setList(list);
      request.setAttribute("contractchangeAF", contractchangeAF);
    }catch(Exception e){
      e.printStackTrace();
    }
    if("1".equals(type)){
      return mapping.findForward("to_contractchange_show");
    }
    if("2".equals(type)){
      return mapping.findForward("to_contractchange_show_1");
    }
    if("3".equals(type)){
      return mapping.findForward("to_contractchange_show_2");
    }
    return null;
  }
  
  private Pagination getPagination(String paginationKey,
      HttpServletRequest request) {
    Pagination pagination = (Pagination) request.getSession().getAttribute(
        paginationKey);
    if (pagination == null) {
      HashMap m = new HashMap();
      pagination = new Pagination(0, 10, 1, " t1.bizdate ", "DESC", m);
      request.getSession().setAttribute(paginationKey, pagination);
    }

    return pagination;
  }
}
