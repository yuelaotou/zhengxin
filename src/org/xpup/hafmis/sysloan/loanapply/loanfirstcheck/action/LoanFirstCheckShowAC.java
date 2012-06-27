package org.xpup.hafmis.sysloan.loanapply.loanfirstcheck.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.xpup.common.exception.BusinessException;
import org.xpup.common.util.BSUtils;
import org.xpup.common.util.Pagination;
import org.xpup.common.util.PaginationUtils;
import org.xpup.hafmis.common.util.BusiConst;
import org.xpup.hafmis.common.util.BusiTools;
import org.xpup.hafmis.orgstrct.dto.OfficeDto;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.sysloan.loanapply.loanfirstcheck.bsinterface.ILoanFirstCheckBS;
import org.xpup.hafmis.sysloan.loanapply.loanfirstcheck.form.LoanFirstCheckShowAF;

/**
 * @author 王野 2007-09-22
 */
public class LoanFirstCheckShowAC extends Action {

  public static final String PAGINATION_KEY = "org.xpup.hafmis.sysloan.loanapply.loanfirstcheck.action.LoanFirstCheckShowAC";

  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    try {
      SecurityInfo securityInfo = (SecurityInfo) request.getSession()
          .getAttribute("SecurityInfo");
      Pagination pagination = getPagination(PAGINATION_KEY, request);
      PaginationUtils.updatePagination(pagination, request);
      if (securityInfo != null && !securityInfo.equals("")) {
        ILoanFirstCheckBS loanFirstCheckBS = (ILoanFirstCheckBS) BSUtils
            .getBusinessService("loanFirstCheckBS", this, mapping
                .getModuleConfig());
        LoanFirstCheckShowAF loanFirstCheckShowAF = new LoanFirstCheckShowAF();
        loanFirstCheckShowAF = loanFirstCheckBS.queryLoanInfoListByCriterions(
            pagination, securityInfo);
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
        request.getSession(true).setAttribute("officeList1", officeList1);
        // 购房类型下拉框
        Map houseTypeMap = BusiTools.listBusiProperty(BusiConst.PLHOUSETYPE);
        loanFirstCheckShowAF.setHouseTypeMap(houseTypeMap);
        // 合同状态下拉框
        Map contractStMap = BusiTools
            .listBusiProperty(BusiConst.PLCONTRACTSTATUS);
        loanFirstCheckShowAF.setContractStMap(contractStMap);

        request.setAttribute("loanFirstCheckShowAF", loanFirstCheckShowAF);
      }
    } catch (BusinessException e) {
      e.printStackTrace();
    }
    return mapping.findForward("to_loanfirstcheck_show");
  }

  private Pagination getPagination(String paginationKey,
      HttpServletRequest request) {
    Pagination pagination = (Pagination) request.getSession().getAttribute(
        paginationKey);
    if (pagination == null) {
      pagination = new Pagination(0, 10, 1, "p110.contract_id", "DESC",
          new HashMap(0));
      request.getSession().setAttribute(paginationKey, pagination);
    }
    return pagination;
  }
}
