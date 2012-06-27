package org.xpup.hafmis.sysloan.querystatistics.datareadyquery.bankquery.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.LookupDispatchAction;
import org.xpup.common.util.BSUtils;
import org.xpup.common.util.Pagination;
import org.xpup.hafmis.common.form.IdAF;
import org.xpup.hafmis.orgstrct.dto.OfficeDto;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.sysloan.dataready.loanpara.bsinterface.ILoanDocNumDesignBS;
import org.xpup.hafmis.sysloan.querystatistics.datareadyquery.bankquery.bsinterface.IBankQueryBS;
import org.xpup.hafmis.sysloan.querystatistics.datareadyquery.bankquery.dto.BankQueryDTO;
import org.xpup.security.common.domain.Userslogincollbank;

public class BankQueryMaintainAC extends LookupDispatchAction {
  public static final String PAGINATION_KEY = "org.xpup.hafmis.sysloan.querystatistics.datareadyquery.bankquery.action.BankQueryShowAC";

  protected Map getKeyMethodMap() {
    Map map = new HashMap();
    map.put("button.print", "print");
    map.put("button.printone", "printone");
    return map;
  }

  public ActionForward printone(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    try {
      IdAF idAF = (IdAF) form;
      String id = idAF.getId().toString();
      SecurityInfo securityInfo = (SecurityInfo) request.getSession()
      .getAttribute("SecurityInfo");
  String bizdate=(String)securityInfo.getUserInfo().getPlbizDate();
  request.setAttribute("bizDate", bizdate);
      IBankQueryBS bankQueryBS = (IBankQueryBS) BSUtils.getBusinessService(
          "bankQueryBS", this, mapping.getModuleConfig());
      //String realName=bankQueryBS.getUserRealName(securityInfo.getUserInfo().getUsername());

      ILoanDocNumDesignBS loanDocNumDesignBS = (ILoanDocNumDesignBS) BSUtils
         .getBusinessService("sysloanloanDocNumDesignBS", this, mapping.getModuleConfig());
         String realName="";
         try {
           String name = loanDocNumDesignBS.getNamePara();

           if (name.equals("1")) {
             realName = securityInfo.getUserName();
           } else {
             realName = securityInfo.getRealName();
           }

         } catch (Exception e) {
           // TODO Auto-generated catch block
           e.printStackTrace();
         }
      BankQueryDTO bankQueryDTO = bankQueryBS.findBankQueryInfo(id);      
      request.setAttribute("bankQueryDTO", bankQueryDTO);
      request.setAttribute("username", realName);
    } catch (Exception e) {
      // TODO: handle exception
      e.printStackTrace();
    }
    return mapping.findForward("bankquery_print");
  }

  public ActionForward print(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    try {
      SecurityInfo securityInfo = (SecurityInfo) request.getSession()
          .getAttribute("SecurityInfo");
      List loanbankList1 = null;
      List loanbankList2 = null;
      List officeList1 = null;
      List officeList2 = null;
      Pagination pagination = getPagination(BankQueryShowAC.PAGINATION_KEY,
          request);
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
        List loanbankList = securityInfo.getDkUserBankList();
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
      IBankQueryBS bankQueryBS = (IBankQueryBS) BSUtils.getBusinessService(
          "bankQueryBS", this, mapping.getModuleConfig());
      List cellList = bankQueryBS.queryBankQueryListCount_wsh(pagination, officeList2,
          loanbankList2);     
      request.setAttribute("cellList", cellList);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return mapping.findForward("bankquerylist_print");
  }

  private Pagination getPagination(String paginationKey,
      HttpServletRequest request) {
    Pagination pagination = (Pagination) request.getSession().getAttribute(
        paginationKey);
    if (pagination == null) {
      HashMap m = new HashMap();
      pagination = new Pagination(0, 10, 1, "office,loanbankid,id ", "DESC", m);
      request.getSession().setAttribute(paginationKey, pagination);
    }
    return pagination;
  }
}
