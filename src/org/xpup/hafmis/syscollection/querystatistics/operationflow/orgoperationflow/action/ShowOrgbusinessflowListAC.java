package org.xpup.hafmis.syscollection.querystatistics.operationflow.orgoperationflow.action;

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

  public static final String PAGINATION_KEY = "org.xpup.hafmis.syscollection.querystatistics.businessflow.orgbusinessflow.action.ShowOrgbusinessflowListAC";

  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {

    try {
      OrgbusinessflowAF orgbusinessflowAF = new OrgbusinessflowAF();
      /**
       * 分页
       */
      // 结算单查询
      HttpSession session = request.getSession(false);
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

      List returnList = new ArrayList();
      OrgbusinessflowHeadDTO orgbusinessflowHeadDTO = new OrgbusinessflowHeadDTO();
      Pagination pagination = (Pagination) session.getAttribute(PAGINATION_KEY);
      if (pagination == null) {
        pagination = getPagination(PAGINATION_KEY, request);
      } else {
        PaginationUtils.updatePagination(pagination, request);
        int start = pagination.getFirstElementOnPage() - 1;
        int pageSize = pagination.getPageSize();
        int page = pagination.getPage();
        
        // 查询全部信息,打印导出用到
        List allList = new ArrayList();
        allList = orgbusinessflowBS.findOrgFlowAllListByCriterions(pagination,
            securityInfo);
        if (allList.size() != 0) {
          session.setAttribute("cellList", allList);
          if(allList.size()<page*pageSize){
            returnList = allList.subList(start, allList.size());
          }else{
            returnList = allList.subList(start, page*pageSize);
          }
          orgbusinessflowAF.setList(returnList);
        }
        // 显示合计信息
        if (returnList.size() != 0) {
          orgbusinessflowHeadDTO = orgbusinessflowBS
              .findOrgFlowListHead(allList);
        }
      }
      String date1 = securityInfo.getUserInfo().getBizDate();
      orgbusinessflowAF.setSetMonthStart(date1);
      request.setAttribute("orgbusinessflowAF", orgbusinessflowAF);
      request.setAttribute("orgbusinessflowHeadDTO",
          orgbusinessflowHeadDTO);

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
      pagination = new Pagination(0, 10, 1, "settDate,id", "ASC", new HashMap(0));
      request.getSession().setAttribute(paginationKey, pagination);
    }

    return pagination;
  }
}
