package org.xpup.hafmis.sysloan.loanapply.othersloan.action;

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
import org.xpup.hafmis.orgstrct.dto.OfficeDto;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.sysloan.loanapply.othersloan.bsinterface.IOthersLoanBS;
import org.xpup.hafmis.sysloan.loanapply.othersloan.form.OthersLoanTbShowAF;

public class OthersLoanTbShowAC extends Action {

  public static final String PAGINATION_KEY = "org.xpup.hafmis.sysloan.loanapply.othersloan.action.OthersLoaanTbShowAC";

  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response) {
    try {
      SecurityInfo securityInfo = (SecurityInfo) request.getSession()
          .getAttribute("SecurityInfo");
      Pagination pagination = getPagination(PAGINATION_KEY, request);
      if (securityInfo != null && !securityInfo.equals("")) {
        PaginationUtils.updatePagination(pagination, request);
        saveToken(request);
        IOthersLoanBS othersLoanBS = (IOthersLoanBS) BSUtils
            .getBusinessService("othersLoanBS", this, mapping.getModuleConfig());
        OthersLoanTbShowAF othersLoanTbShowAF = new OthersLoanTbShowAF();
        // 办事处下拉
        List officeList = securityInfo.getOfficeList();
        List officeList1 = new ArrayList();
        OfficeDto officedto = null;
        Iterator itr1 = officeList.iterator();
        while (itr1.hasNext()) {
          officedto = (OfficeDto) itr1.next();
          officeList1.add(new org.apache.struts.util.LabelValueBean(officedto
              .getOfficeName(), officedto.getOfficeCode()));
        }
        request.getSession(true).setAttribute("officeList", officeList1);
        if (request.getAttribute("type") == null) {
          othersLoanTbShowAF = othersLoanBS.queryOthersLoanListByCriterions(pagination, securityInfo);
        }
        request.setAttribute("othersLoanTbShowAF", othersLoanTbShowAF);
        if(request.getSession().getAttribute("forward")!=null){
          request.setAttribute("forward", "2");
        }else{
          request.setAttribute("forward", "1");
        }
       
      }
    } catch (Exception e) {
      e.printStackTrace();
    }                           
    return mapping.findForward("to_othersLoanTb_show");
  }

  private Pagination getPagination(String paginationKey,
      HttpServletRequest request) {
    Pagination pagination = (Pagination) request.getSession().getAttribute(
        paginationKey);
    if (pagination == null) {
      pagination = new Pagination(0, 10, 1, "a.d", "DESC", new HashMap(0));
      request.getSession().setAttribute(paginationKey, pagination);
    }
    return pagination;
  }
}
