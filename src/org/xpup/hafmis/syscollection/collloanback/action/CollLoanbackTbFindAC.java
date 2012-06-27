package org.xpup.hafmis.syscollection.collloanback.action;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.xpup.common.util.Pagination;
import org.xpup.hafmis.syscollection.collloanback.form.CollLoanbackTaAF;

public class CollLoanbackTbFindAC extends Action {
  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    try {
      CollLoanbackTaAF collLoanbackTaAF = (CollLoanbackTaAF) form;
      String bank = collLoanbackTaAF.getCollectionBankId();
      String office = collLoanbackTaAF.getOfficeCode();
      String batchNum = collLoanbackTaAF.getBatchNum();
      String startdate = collLoanbackTaAF.getStartdate();
      String enddate = collLoanbackTaAF.getEnddate();
      
      Pagination pagination = new Pagination(0, 10, 1, "aa410.loanbankid",
          "DESC", new HashMap(0));
      pagination.getQueryCriterions().put("bank", bank);
      pagination.getQueryCriterions().put("office", office);
      pagination.getQueryCriterions().put("batchNum", batchNum);
      pagination.getQueryCriterions().put("startdate", startdate);
      pagination.getQueryCriterions().put("enddate", enddate);
      
      request.getSession().setAttribute(CollLoanbackTbShowAC.PAGINATION_KEY, pagination);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return mapping.findForward("collloanbacktb_show");
  }
}
