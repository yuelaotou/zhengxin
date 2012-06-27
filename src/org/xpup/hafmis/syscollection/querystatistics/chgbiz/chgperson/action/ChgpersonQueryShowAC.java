package org.xpup.hafmis.syscollection.querystatistics.chgbiz.chgperson.action;

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
import org.xpup.hafmis.syscollection.querystatistics.chgbiz.chgperson.bsinterface.IChgpersonOrgBS;
import org.xpup.hafmis.syscollection.querystatistics.chgbiz.chgperson.from.ChgpersonQueryAF;
import org.xpup.security.common.domain.Userslogincollbank;

/**
 * @author 杨光 20090507
 */
public class ChgpersonQueryShowAC extends Action {

  public static final String PAGINATION_KEY = "org.xpup.hafmis.syscollection.querystatistics.chgbiz.chgperson.action.ChgpersonQueryShowAC";

  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    ChgpersonQueryAF chgpersonQueryAF = new ChgpersonQueryAF();
    try {
      String forward = (String)request.getAttribute("forward");
      Pagination pagination = getPagination(PAGINATION_KEY, request);
      PaginationUtils.updatePagination(pagination, request);
      IChgpersonOrgBS chgpersonOrgBS = (IChgpersonOrgBS) BSUtils
          .getBusinessService("chgpersonOrgBS", this, mapping.getModuleConfig());
      SecurityInfo securityInfo = (SecurityInfo) request.getSession()
          .getAttribute("SecurityInfo");
      List list = new ArrayList();
      if(forward==null){
        list = chgpersonOrgBS.getChgpersonQueryList(pagination);
      }
      chgpersonQueryAF.setList(list);
      
      //办事处信息显示在下拉列表中
      List officeList = securityInfo.getOfficeList();
      List officeList1 = new ArrayList();
      OfficeDto officedto = null;
      Iterator itr1 = officeList.iterator();
      while (itr1.hasNext()) {
        officedto = (OfficeDto) itr1.next();
        officeList1.add(new org.apache.struts.util.LabelValueBean(officedto.getOfficeName(), officedto.getOfficeCode()));
      }
      //取出归集银行下拉列表
      List collBankList = securityInfo.getCollBankList();
      List collBankList1 = new ArrayList();
      Userslogincollbank userslogincollbank = null;
      Iterator itr2 = collBankList.iterator();
      while (itr2.hasNext()) {
        userslogincollbank = (Userslogincollbank) itr2.next();
        collBankList1.add(new org.apache.struts.util.LabelValueBean(userslogincollbank.getCollBankName().toString(), userslogincollbank.getCollBankId().toString()));
      }
      request.setAttribute("officeList", officeList1);
      request.setAttribute("bankList",collBankList1);
      request.setAttribute("chgpersonQueryAF",chgpersonQueryAF);
    } 
    catch (Exception ex) {
      ex.printStackTrace();
    }
    return mapping.findForward("chgpersonquery");
  }

  private Pagination getPagination(String paginationKey,
      HttpServletRequest request) {
    Pagination pagination = (Pagination) request.getSession().getAttribute(
        paginationKey);
    if (pagination == null) {
      pagination = new Pagination(0, 10, 1, "", "ASC",
          new HashMap(0));
      request.getSession().setAttribute(paginationKey, pagination);
    }
    return pagination;
  }
}
