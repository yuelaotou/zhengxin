package org.xpup.hafmis.syscollection.common.biz.loankoupop.action;

import java.util.HashMap;
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
import org.xpup.hafmis.syscollection.common.biz.loankoupop.bsinterface.ILoanKouPopBS;
import org.xpup.hafmis.syscollection.common.biz.loankoupop.form.LoanKouPopAF;

public class LoanKouPopShowAC extends Action {
  public static final String PAGINATION_KEY = "org.xpup.hafmis.syscollection.common.biz.loankoupop.action.LoanKouPopShowAC";
  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    LoanKouPopAF loanKouPopAF = new LoanKouPopAF();
    try {
      ILoanKouPopBS loanKouPopBS = (ILoanKouPopBS) BSUtils
          .getBusinessService("loanKouPopBS", this, mapping
              .getModuleConfig());

      String paginationKey = getPaginationKey();
      Pagination pagination = getPagination(paginationKey, request);
      PaginationUtils.updatePagination(pagination, request);
      
      // 得到相关的查询条件
      String type="";
      if(request.getAttribute("type")!=null){
        type=(String)request.getAttribute("type");
      }
      String loanBankId="";
      String batchNum="";
      if(type.equals("1")){
        loanBankId = (String) request.getParameter("loanBankId");
      //  System.out.println("loanBankId=="+loanBankId);
        
        batchNum = (String) request.getParameter("batchNum");
       
        loanBankId =loanKouPopBS.querybankname(batchNum);
        request.getSession().setAttribute("loanBankId", loanBankId);
        request.getSession().setAttribute("batchNum", batchNum);
      //  System.out.println("batchNum=="+batchNum);
      }else{
        batchNum=(String)request.getSession().getAttribute("batchNum");
      //  System.out.println("batchNum==="+batchNum);
      }    
      
      List list = loanKouPopBS.findCollLoanbackTaList(batchNum, pagination);
      if(list!=null&&list.size()!=0){
        loanKouPopAF.setList(list);
      }
      loanKouPopAF.setLoanBankID((String)request.getSession().getAttribute("loanBankId"));
      loanKouPopAF.setBatchNum(batchNum);
      request.getSession().setAttribute(paginationKey, pagination);
    } catch (Exception e) {
      e.printStackTrace();
    }
    request.setAttribute("loanKouPopAF", loanKouPopAF);
    return mapping.findForward("to_loankoupop_show");
  }
  private Pagination getPagination(String paginationKey,
      HttpServletRequest request) {
    Pagination pagination = (Pagination) request.getSession().getAttribute(
        paginationKey);
    if (pagination == null) {
      pagination = new Pagination(0, 10, 1, "aa411.loan_kou_acc", "DESC", new HashMap(0));
      request.getSession().setAttribute(paginationKey, pagination);
    }
    return pagination;
  }

  protected String getPaginationKey() {
    return LoanKouPopShowAC.PAGINATION_KEY;
  }
}
