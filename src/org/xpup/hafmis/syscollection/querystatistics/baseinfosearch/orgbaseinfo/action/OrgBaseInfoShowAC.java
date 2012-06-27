package org.xpup.hafmis.syscollection.querystatistics.baseinfosearch.orgbaseinfo.action;

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
import org.xpup.hafmis.syscollection.common.domain.entity.Org;
import org.xpup.hafmis.syscollection.querystatistics.baseinfosearch.orgbaseinfo.bsinterface.IOrgBaseInfoBS;
import org.xpup.hafmis.syscollection.querystatistics.baseinfosearch.orgbaseinfo.form.OrgInfoSearchAF;
import org.xpup.hafmis.syscommon.domain.entity.Transactor;
import org.xpup.security.common.domain.Userslogincollbank;

public class OrgBaseInfoShowAC extends Action{
  public static final String PAGINATION_KEY ="org.xpup.hafmis.syscollection.querystatistics.baseinfosearch.orgbaseinfo.action.OrgBaseInfoShowAC";
  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response) {
    try{
      OrgInfoSearchAF orgInfoSearchAF = new OrgInfoSearchAF();
      Pagination pagination = (Pagination)request.getSession().getAttribute(PAGINATION_KEY);
      orgInfoSearchAF.setCharacterMap(BusiTools.listBusiProperty(BusiConst.NATUREOFUNITS));
      orgInfoSearchAF.setDeptInChargeMap(BusiTools.listBusiProperty(BusiConst.AUTHORITIES));
      orgInfoSearchAF.setOpenStatusMap(BusiTools.listBusiProperty(BusiConst.ORGSTATE));
      orgInfoSearchAF.setPayModeMap(BusiTools.listBusiProperty(BusiConst.ORGPAYWAY));
      orgInfoSearchAF.setRegionMap(BusiTools.listBusiProperty(BusiConst.INAREA));
      // 从数据库读取办事处信息
      SecurityInfo securityInfo = (SecurityInfo) request.getSession()
      .getAttribute("SecurityInfo");
      List officeList = securityInfo.getOfficeList();
      List officeList1 = new ArrayList();
      OfficeDto officedto = null;
      Iterator itr1 = officeList.iterator();
      while (itr1.hasNext()) {
        officedto = (OfficeDto) itr1.next();
        officeList1.add(new org.apache.struts.util.LabelValueBean(officedto.getOfficeName(), officedto.getOfficeCode()));
      }
      request.getSession(true).setAttribute("officeList1", officeList1);
      //结束
      
      // 取出用户下归集行
      List collBankList = securityInfo.getCollBankList();
      List collBankList1 = new ArrayList();
      Userslogincollbank userslogincollbank = null;
      Iterator itr2 = collBankList.iterator();
      while (itr2.hasNext()) {
        userslogincollbank = (Userslogincollbank) itr2.next();
        collBankList1.add(new org.apache.struts.util.LabelValueBean(userslogincollbank.getCollBankName().toString(), userslogincollbank.getCollBankId().toString()));
      }
      request.getSession(true).setAttribute("collBankList1", collBankList1);
      String personc="0";
      //结束
      if(pagination == null){
        pagination = getPagination(PAGINATION_KEY, request);
      }else{
        IOrgBaseInfoBS orgBaseInfoBS = (IOrgBaseInfoBS)BSUtils.getBusinessService("orgBaseInfoBS",this,mapping.getModuleConfig());
        PaginationUtils.updatePagination(pagination, request);
        SecurityInfo sInfo = (SecurityInfo)request.getSession().getAttribute("SecurityInfo");
        List list=orgBaseInfoBS.findOrgList(pagination, sInfo);
        orgInfoSearchAF.setList(list);
        personc=(String)pagination.getQueryCriterions().get("personcount");
        orgInfoSearchAF.setPersonc(Integer.parseInt(personc));
      }
      request.getSession().setAttribute("infoList", orgInfoSearchAF);
      orgInfoSearchAF.clear();
    }catch(Exception s){
      s.printStackTrace();
    }
    return mapping.findForward("to_orgbaseinfo_list");
  }
  private Pagination getPagination(String paginationKey,HttpServletRequest request) {
    Pagination pagination = (Pagination) request.getSession().getAttribute(paginationKey);
    if (pagination == null) {
      pagination = new Pagination(0, 10, 1, "a001.id","DESC",new HashMap(0));
      request.getSession().setAttribute(paginationKey, pagination);
    }   
    return pagination;
  }
}
