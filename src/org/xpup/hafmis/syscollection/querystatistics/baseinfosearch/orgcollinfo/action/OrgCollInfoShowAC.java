package org.xpup.hafmis.syscollection.querystatistics.baseinfosearch.orgcollinfo.action;

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

import org.xpup.hafmis.syscollection.querystatistics.baseinfosearch.orgcollinfo.bsinterface.IOrgCollInfoBS;
import org.xpup.hafmis.syscollection.querystatistics.baseinfosearch.orgcollinfo.dto.OrgCollInfoFindDTO;
import org.xpup.hafmis.syscollection.querystatistics.baseinfosearch.orgcollinfo.dto.OrgCollinfoSumDTO;
import org.xpup.hafmis.syscollection.querystatistics.baseinfosearch.orgcollinfo.form.OrgCollInfoFindAF;

import org.xpup.security.common.domain.Userslogincollbank;

public class OrgCollInfoShowAC extends Action {

  public static final String PAGINATION_KEY = "org.xpup.hafmis.syscollection.querystatistics.baseinfosearch.orgcollinfo.action.OrgCollInfoShowAC";

  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    // TODO Auto-generated method stub
    OrgCollInfoFindAF orgCollInfoFindAF = new OrgCollInfoFindAF();
    SecurityInfo securityInfo = null;
    try {
      securityInfo = (SecurityInfo) request.getSession().getAttribute(
          "SecurityInfo");

      Map natureofunitsMap = BusiTools
          .listBusiProperty(BusiConst.NATUREOFUNITS);
      orgCollInfoFindAF.setNatureofunitsMap(natureofunitsMap);
      Map authoritiesMap = BusiTools.listBusiProperty(BusiConst.AUTHORITIES);
      orgCollInfoFindAF.setAuthoritiesMap(authoritiesMap);
      Map openstatusMap = BusiTools.listBusiProperty(BusiConst.ORGSTATE);
      orgCollInfoFindAF.setOpenstatusMap(openstatusMap);
      Map paymodeMap = BusiTools.listBusiProperty(BusiConst.ORGPAYWAY);
      orgCollInfoFindAF.setPaymodeMap(paymodeMap);
      Map regionMap = BusiTools.listBusiProperty(BusiConst.INAREA);
      orgCollInfoFindAF.setRegionMap(regionMap);
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
        pagination.getQueryCriterions().put("securityInfo", securityInfo);
      }else{
        pagination.getQueryCriterions().put("securityInfo", securityInfo);
        PaginationUtils.updatePagination(pagination, request);
        IOrgCollInfoBS orgCollInfoBS = (IOrgCollInfoBS) BSUtils
            .getBusinessService("orgCollInfoBS", this, mapping.getModuleConfig());
        Object[] orgCollInfoList = orgCollInfoBS.findOrgCollInfo(pagination);
        
        OrgCollInfoFindDTO dto = (OrgCollInfoFindDTO)pagination.getQueryCriterions().get("searchDTO");
        if(dto.getOfficecode()!=null && !dto.getOfficecode().equals("")){
          request.getSession().setAttribute("officeOrBank","office");
        }
        if(dto.getCollectionBankId()!=null && !dto.getCollectionBankId().equals("")){
          request.getSession().setAttribute("officeOrBank","bank");
        }
        if((dto.getCollectionBankId()==null || dto.getCollectionBankId().equals("")) && (dto.getOfficecode()==null || dto.getOfficecode().equals(""))){
          request.getSession().setAttribute("officeOrBank","no");
        }
        
        orgCollInfoFindAF.setList((List)orgCollInfoList[0]);
        orgCollInfoFindAF.setOrgCollinfoSumDTO((OrgCollinfoSumDTO)orgCollInfoList[1]);
        //print
        request.getSession().setAttribute("orgInfo_print", (List)orgCollInfoList[2]);
      }

    } catch (Exception e) {
      e.printStackTrace();
    }
    
    request.setAttribute("officeList1", officeList1);
    request.setAttribute("collBankList1", collBankList1);
    request.setAttribute("orgCollInfoFindAF", orgCollInfoFindAF);
    return mapping.findForward("orgcollinfo_show");
  }
  
  private Pagination getPagination(String paginationKey,
      HttpServletRequest request) {
    Pagination pagination = (Pagination) request.getSession().getAttribute(
        paginationKey);
    if (pagination == null) {
      pagination = new Pagination(0, 10, 1, "a1.id", "DESC", new HashMap(0));
      request.getSession().setAttribute(paginationKey, pagination);
    }
    return pagination;
  }
  
  protected String getPaginationKey() {
    return PAGINATION_KEY;
  }

}
