package org.xpup.hafmis.sysfinance.bookmng.subjectrelation.action;

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
import org.xpup.hafmis.sysfinance.bookmng.subjectrelation.bsinterface.ISubjectrelationBS;
import org.xpup.hafmis.sysfinance.bookmng.subjectrelation.form.SubjectrelationTaPop3AF;
import org.xpup.security.common.domain.Userslogincollbank;

public class SubjectRelationTaPop3AC extends Action {
  public static final String PAGINATION_KEY = "org.xpup.hafmis.sysfinance.bookmng.subjectrelation.action.SubjectRelationTaPop3AC";

  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    // TODO Auto-generated method stub
    List loanbankList1 = null;
    List loanbankList2 = null;
    List officeList1 = null;
    List officeList2 = null;
    try {
      Pagination pagination = getPagination(SubjectRelationTaPop3AC.PAGINATION_KEY,
          request);
      PaginationUtils.updatePagination(pagination, request);
      SecurityInfo securityInfo = (SecurityInfo) request.getSession()
          .getAttribute("SecurityInfo");
    
      try {
        // 取出用户权限办事处,显示在下拉菜单中
        List officeList = securityInfo.getOfficeList();
        officeList1 = new ArrayList();
        officeList2 = new ArrayList();
        OfficeDto officedto = null;
        Iterator itr1 = officeList.iterator();
        while (itr1.hasNext()) {
          officedto = (OfficeDto) itr1.next();
          officeList1.add(new org.apache.struts.util.LabelValueBean(officedto
              .getOfficeName(), officedto.getOfficeCode()));
          officeList2.add(officedto.getOfficeCode());
        }
      } catch (Exception e) {
        e.printStackTrace();
      }
      try {
        // 取出用户权限放款银行,显示在下拉菜单中
        List loanbankList = securityInfo.getCollBankList();       
        loanbankList1 = new ArrayList();
        loanbankList2 = new ArrayList();
        Userslogincollbank bank = null;
        Iterator itr1 = loanbankList.iterator();
        while (itr1.hasNext()) {
          bank = (Userslogincollbank) itr1.next();
          loanbankList1.add(new org.apache.struts.util.LabelValueBean(bank
              .getCollBankName(), bank.getCollBankId().toString()));
          loanbankList2.add(bank.getCollBankId());
        }
      } catch (Exception e) {
        e.printStackTrace();
      }
      List list = new ArrayList();
      List countList = new ArrayList();
      ISubjectrelationBS subjectrelationBS = (ISubjectrelationBS) BSUtils
          .getBusinessService("subjectrelationBS", this, mapping
              .getModuleConfig());
     
      list = subjectrelationBS.querySubejectRelationTaPop3List(pagination,securityInfo, officeList2,
          loanbankList2);
      countList = subjectrelationBS.querySubejectRelationTaPop3CountList(pagination,securityInfo);
      pagination.setNrOfElements(countList.size());
      SubjectrelationTaPop3AF subjectrelaionTaPop3AF = new SubjectrelationTaPop3AF();
      subjectrelaionTaPop3AF.setList(list);
      subjectrelaionTaPop3AF.setLoanBankId(null);
      subjectrelaionTaPop3AF.setOffice(null);
      request.setAttribute("subjectrelaionTaPop3AF", subjectrelaionTaPop3AF);
      request.setAttribute("count", "1");
      String count1=(String)request.getAttribute("count1");
      if("1".equals(count1)){
        request.setAttribute("count", "2");
      }
    } catch (Exception e) {
      // TODO: handle exception
      e.printStackTrace();
    }
    request.setAttribute("loanbankList1", loanbankList1);
    request.setAttribute("officeList1", officeList1);
    return mapping.findForward("to_pop_subjectrelationta");
  }

  private Pagination getPagination(String paginationKey,
      HttpServletRequest request) {
    Pagination pagination = (Pagination) request.getSession().getAttribute(
        paginationKey);
    if (pagination == null) {
      HashMap m = new HashMap();
      pagination = new Pagination(0, 10, 1, "", "orgId", m);
      request.getSession().setAttribute(paginationKey, pagination);
    }
    return pagination;
  }
}
