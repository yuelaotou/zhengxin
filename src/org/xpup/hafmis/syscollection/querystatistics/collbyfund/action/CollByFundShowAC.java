package org.xpup.hafmis.syscollection.querystatistics.collbyfund.action;

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
import org.xpup.hafmis.syscollection.querystatistics.collbyfund.bsinterface.ICollByFundBS;
import org.xpup.hafmis.syscollection.querystatistics.collbyfund.form.CollByFundAF;
import org.xpup.security.common.domain.Userslogincollbank;

public class CollByFundShowAC extends Action {
  public static final String PAGINATION_KEY = "org.xpup.hafmis.syscollection.querystatistics.collbyfund.action.CollByFundShowAC";

  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response) {
    try {
      SecurityInfo securityInfo = (SecurityInfo) request.getSession()
          .getAttribute("SecurityInfo");
      Pagination pagination = getPagination(PAGINATION_KEY, request);
      PaginationUtils.updatePagination(pagination, request);
      String forward = (String) request.getAttribute("forward");
      List officeList = null;
      ICollByFundBS collByFundBS = (ICollByFundBS) BSUtils.getBusinessService(
          "collByFundBS", this, mapping.getModuleConfig());
      CollByFundAF collByFundAF = new CollByFundAF();
      if (forward == null) {
        collByFundAF = collByFundBS.queryCollByFundByCriterions(pagination,
            securityInfo);
      }
      String collBankId = (String) pagination.getQueryCriterions().get(
          "collBankId");
      collByFundAF.setCollBankId(collBankId);

      // 取出用户权限办事处,显示在下拉菜单中
      List temp_officeList = securityInfo.getAllOfficeList();
      officeList = new ArrayList();
      OfficeDto officedto = null;
      Iterator it = temp_officeList.iterator();
      while (it.hasNext()) {
        officedto = (OfficeDto) it.next();
        officeList.add(new org.apache.struts.util.LabelValueBean(officedto
            .getOfficeName(), officedto.getOfficeCode()));
      }

      // 归集银行下拉框
      List collBankNameList = new ArrayList();
      List bangkList = securityInfo.getCollBankList();
      Userslogincollbank userslogincollbank = null;
      Iterator bank = bangkList.iterator();
      while (bank.hasNext()) {
        userslogincollbank = (Userslogincollbank) bank.next();
        collBankNameList.add(new org.apache.struts.util.LabelValueBean(
            userslogincollbank.getCollBankName(), userslogincollbank
                .getCollBankId().toString()));
      }

      request.setAttribute("officeList", officeList);
      request.setAttribute("collBankNameList", collBankNameList);
      request.setAttribute("collByFundAF", collByFundAF);
      request.getSession().setAttribute("collByFundprintDate",
          collByFundAF.getDate());
      request.getSession().setAttribute("collByFundprintList",
          collByFundAF.getPrintList());
      request.getSession().setAttribute("collByFundbankList",
          collByFundAF.getBybankList());
    } catch (Exception ex) {
      ex.printStackTrace();
    }
    return mapping.findForward("collbyfund");
  }

  private Pagination getPagination(String paginationKey,
      HttpServletRequest request) {
    Pagination pagination = (Pagination) request.getSession().getAttribute(
        paginationKey);
    if (pagination == null) {
      HashMap m = new HashMap();
      pagination = new Pagination(0, 10, 1, "a001.id", "ASC", m);
      request.getSession().setAttribute(paginationKey, pagination);
    }
    return pagination;
  }

}
