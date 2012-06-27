package org.xpup.hafmis.syscollection.accounthandle.clearaccount.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
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
import org.xpup.hafmis.syscollection.querystatistics.operationflow.orgoperationflow.bsinterface.IOrgbusinessflowBS;
import org.xpup.hafmis.syscollection.querystatistics.operationflow.orgoperationflow.dto.OrgbusinessflowHeadDTO;
import org.xpup.hafmis.syscollection.querystatistics.operationflow.orgoperationflow.form.OrgbusinessflowAF;
import org.xpup.security.common.domain.Userslogincollbank;

public class ShowOrgbusinessflowListAC extends Action {

  public static final String PAGINATION_KEY = "org.xpup.hafmis.syscollection.accounthandle.clearaccount.action.ShowOrgbusinessflowListAC";

  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {

    try {
      OrgbusinessflowAF orgbusinessflowAF = (OrgbusinessflowAF) form;
      // 结算单查询
      HttpSession session = request.getSession(false);
      Pagination pagination1 = (Pagination) session.getAttribute("sessionLJ");
      if (pagination1 != null) {
        session.setAttribute(PAGINATION_KEY, pagination1);
      }
      Pagination pagination = getPagination(PAGINATION_KEY, request);
      PaginationUtils.updatePagination(pagination, request);
      IOrgbusinessflowBS orgbusinessflowBS = (IOrgbusinessflowBS) BSUtils
          .getBusinessService("orgbusinessflowBS", this, mapping
              .getModuleConfig());

      SecurityInfo securityInfo = (SecurityInfo) request.getSession()
          .getAttribute("SecurityInfo");

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

      List bankList = securityInfo.getCollBankList();
      List bankList1 = new ArrayList();
      Userslogincollbank bankdto = null;
      Iterator itr2 = bankList.iterator();
      while (itr2.hasNext()) {
        bankdto = (Userslogincollbank) itr2.next();
        bankList1.add(new org.apache.struts.util.LabelValueBean(bankdto
            .getCollBankName().toString(), bankdto.getCollBankId().toString()));
      }
      request.getSession(true).setAttribute("bankList1", bankList1);

      orgbusinessflowAF.setBsstatueMap(BusiTools
          .listBusiProperty(BusiConst.CLEARACCOUNTSTATUS));
      orgbusinessflowAF.setBstypeMap(BusiTools
          .listBusiProperty(BusiConst.CLEARACCOUNTBUSINESSTYPE));
      orgbusinessflowAF.setSetDirectionMap(BusiTools
          .listBusiProperty(BusiConst.OCCURREDDIRECTION));

      OrgbusinessflowHeadDTO orgbusinessflowHeadDTO = new OrgbusinessflowHeadDTO();

      List returnList = orgbusinessflowBS.findOrgFlowListByCriterions(pagination,
          securityInfo);

      orgbusinessflowAF.setList(returnList);

      // 查询全部信息,打印导出用到
      List allList = new ArrayList();
      allList = orgbusinessflowBS.findOrgFlowAllListByCriterions(pagination,
          securityInfo);
      if (allList.size() != 0) {
        session.setAttribute("cellList", allList);
      }
      // 显示合计信息
      if (returnList.size() != 0) {
        orgbusinessflowHeadDTO = orgbusinessflowBS.findOrgFlowListHead(allList);
      }
      request.setAttribute("orgbusinessflowAF", orgbusinessflowAF);
      request.setAttribute("orgbusinessflowHeadDTO", orgbusinessflowHeadDTO);

    } catch (Exception ex) {
      ex.printStackTrace();
    }
    return mapping.findForward("to_orgbusinessflow_list");
  }

  private Pagination getPagination(String paginationKey,
      HttpServletRequest request) {
    Pagination pagination = (Pagination) request.getSession().getAttribute(
        paginationKey);
    if (pagination == null) {
      pagination = new Pagination(0, 10, 1, "id", "DESC", new HashMap(0));
      request.getSession().setAttribute(paginationKey, pagination);
    }

    return pagination;
  }
}
