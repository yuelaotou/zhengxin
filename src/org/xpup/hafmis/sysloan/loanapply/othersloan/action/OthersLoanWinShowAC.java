package org.xpup.hafmis.sysloan.loanapply.othersloan.action;

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
import org.xpup.common.util.BSUtils;
import org.xpup.common.util.Pagination;
import org.xpup.common.util.PaginationUtils;
import org.xpup.hafmis.common.util.BusiConst;
import org.xpup.hafmis.common.util.BusiTools;
import org.xpup.hafmis.orgstrct.dto.OfficeDto;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.sysloan.loanapply.othersloan.bsinterface.IOthersLoanBS;
import org.xpup.hafmis.sysloan.loanapply.othersloan.form.OthersLoanShowAF;
public class OthersLoanWinShowAC extends Action {

  public static final String PAGINATION_KEY = "org.xpup.hafmis.sysloan.loanapply.othersloan.action.OthersLoaanShowAC";

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
        OthersLoanShowAF othersLoanShowAF = new OthersLoanShowAF();
        // 休要修改的异地贷款的主键id
        String id = (String) request.getParameter("id");
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
        if (id != null && !"".equals(id)) {
          pagination.getQueryCriterions().put("id", id);
          othersLoanShowAF = othersLoanBS.queryBorrowerListByCriterions(
              pagination, securityInfo);
        }
        // 购房类型下拉框
        Map houseTypeMap = BusiTools.listBusiProperty(BusiConst.PLHOUSETYPE);
        othersLoanShowAF.setHouseTypeMap(houseTypeMap);
        othersLoanShowAF.setSexMap(BusiTools.listBusiProperty(BusiConst.SEX));
        othersLoanShowAF.setCardkingMap(BusiTools
            .listBusiProperty(BusiConst.DOCUMENTSSTATE));
        othersLoanShowAF.setRelationMap(BusiTools.listBusiProperty(BusiConst.RELATION));
        request.setAttribute("othersLoanAF", othersLoanShowAF);
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    return mapping.findForward("to_othersLoan_show");
  }

  private Pagination getPagination(String paginationKey,
      HttpServletRequest request) {
    Pagination pagination = (Pagination) request.getSession().getAttribute(
        paginationKey);
    if (pagination == null) {
      pagination = new Pagination(0, 10, 1, "t.id", "DESC", new HashMap(0));
      request.getSession().setAttribute(paginationKey, pagination);
    }
    return pagination;
  }
}
