package org.xpup.hafmis.syscollection.querystatistics.paymentpickstatistics.orginandoutschedule.action;

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
import org.xpup.hafmis.common.util.BusiConst;
import org.xpup.hafmis.common.util.BusiTools;
import org.xpup.hafmis.orgstrct.dto.OfficeDto;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.syscollection.querystatistics.paymentpickstatistics.orginandoutschedule.bsinterface.IOrgInAndOutScheduleBS;
import org.xpup.hafmis.syscollection.querystatistics.paymentpickstatistics.orginandoutschedule.form.DisplayAF;
import org.xpup.security.common.domain.Userslogincollbank;

public class ShowListAC extends Action{
  public static final String PAGINATION_KEY ="org.xpup.hafmis.syscollection.querystatistics.paymentpickstatistics.orginandoutschedule.action.ShowListAC";
  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response) {
    DisplayAF displayAF = new DisplayAF();
    SecurityInfo securityInfo = null;
    try {
      securityInfo = (SecurityInfo) request.getSession().getAttribute(
          "SecurityInfo");
      displayAF.setCharaterMap(BusiTools
          .listBusiProperty(BusiConst.NATUREOFUNITS));
      displayAF.setDeptInChargeMap(BusiTools
          .listBusiProperty(BusiConst.AUTHORITIES));
      displayAF.setRegionMap(BusiTools
          .listBusiProperty(BusiConst.INAREA));
    } catch (Exception e) {
      e.printStackTrace();
    }
    
    List officeList1 = null;
    try {
      // 取出用户权限办事处,显示在下拉菜单中
      List officeList = securityInfo.getOfficeList();
      officeList1 = new ArrayList();
      OfficeDto officedto = null;
      Iterator itr1 = officeList.iterator();
      while (itr1.hasNext()) {
        officedto = (OfficeDto) itr1.next();
        officeList1.add(new org.apache.struts.util.LabelValueBean(officedto
            .getOfficeName(), officedto.getOfficeCode()));
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    
    List collBankList1 = null;
    try {
      // 取出用户下归集行
      List collBankList = securityInfo.getCollBankList();
      collBankList1 = new ArrayList();
      Userslogincollbank userslogincollbank = null;
      Iterator itr2 = collBankList.iterator();
      while (itr2.hasNext()) {
        userslogincollbank = (Userslogincollbank) itr2.next();
        collBankList1.add(new org.apache.struts.util.LabelValueBean(
            userslogincollbank.getCollBankName().toString(), userslogincollbank
                .getCollBankId().toString()));
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    
    // 查询操作，查询的结果Object[]携带着表单将要显示的内容：合计，列表内容
    try {
      String paginationKey = getPaginationKey();
      Pagination pagination = (Pagination)request.getSession().getAttribute(paginationKey);
      if (pagination==null) {
        pagination = getPagination(paginationKey, request);
      }else{
        pagination.getQueryCriterions().put("securityInfo", securityInfo);
        IOrgInAndOutScheduleBS orgInAndOutScheduleBS = (IOrgInAndOutScheduleBS) BSUtils
            .getBusinessService("orgInAndOutScheduleBS", this, mapping.getModuleConfig());
        PaginationUtils.updatePagination(pagination, request);
        Object[] InfoList = orgInAndOutScheduleBS.find(pagination);
        if(InfoList!=null){
          displayAF=(DisplayAF)InfoList[1];
          displayAF.setList((List)InfoList[0]);
          displayAF.setCharaterMap(BusiTools
              .listBusiProperty(BusiConst.NATUREOFUNITS));
          displayAF.setDeptInChargeMap(BusiTools
              .listBusiProperty(BusiConst.AUTHORITIES));
          displayAF.setRegionMap(BusiTools
              .listBusiProperty(BusiConst.INAREA));
        }
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    request.setAttribute("officeList1", officeList1);
    request.setAttribute("collBankList1", collBankList1);
    request.setAttribute("displayAF", displayAF);
    return mapping.findForward("orginandoutschedule");
  }
  private Pagination getPagination(String paginationKey,
      HttpServletRequest request) {
    Pagination pagination = (Pagination) request.getSession().getAttribute(
        paginationKey);
    if (pagination == null) {
      pagination = new Pagination(0, 10, 1, "a.org_id", "DESC", new HashMap(0));
      request.getSession().setAttribute(paginationKey, pagination);
    }
    return pagination;
  }
  
  protected String getPaginationKey() {
    return PAGINATION_KEY;
  }
}
