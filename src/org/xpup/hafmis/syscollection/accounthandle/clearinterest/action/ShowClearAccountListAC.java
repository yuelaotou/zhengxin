package org.xpup.hafmis.syscollection.accounthandle.clearinterest.action;

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
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.xpup.common.exception.BusinessException;
import org.xpup.common.util.BSUtils;
import org.xpup.common.util.Pagination;
import org.xpup.common.util.PaginationUtils;
import org.xpup.hafmis.orgstrct.dto.OfficeDto;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.syscollection.accounthandle.clearinterest.bsinterface.IClearaccountBS;
import org.xpup.security.common.domain.User;
import org.xpup.security.common.domain.Userslogincollbank;

public class ShowClearAccountListAC extends Action {

  public static final String PAGINATION_KEY = "org.xpup.hafmis.syscollection.accounthandle.clearinterest.action.ShowClearAccountListAC";

  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {

    ActionMessages messages = null;

    try {

      /**
       * ·ÖÒ³
       */
      Pagination pagination = getPagination(PAGINATION_KEY, request);
      PaginationUtils.updatePagination(pagination, request);
      IClearaccountBS clearaccountBS = (IClearaccountBS) BSUtils
          .getBusinessService("clearaccountBS", this, mapping.getModuleConfig());

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

      List bankList = securityInfo.getCollBankList();
      List bankList1 = new ArrayList();
      Userslogincollbank bankdto = null;   
      Iterator itr2 = bankList.iterator();    
      while (itr2.hasNext()) {
        bankdto = (Userslogincollbank) itr2.next();   
        bankList1.add(new org.apache.struts.util.LabelValueBean(bankdto.getCollBankName().toString(), bankdto.getCollBankId().toString()));
      }
      request.getSession(true).setAttribute("bankList1", bankList1);
      
      List operList = securityInfo.getUserList();
      List operList1 = new ArrayList();
      User user = null;
      Iterator itr3 = operList.iterator();
      while (itr3.hasNext()) {
        user = (User) itr3.next();
        operList1.add(new org.apache.struts.util.LabelValueBean(user.getUsername(), user.getUsername()));
      }
      request.getSession(true).setAttribute("operList1", operList1);
      
      String bizDate = securityInfo.getUserInfo().getBizDate();
      String bizYear=bizDate.substring(0, 4);
      String bizMonth=bizDate.substring(4, 8);
      String accountantyear = null;
      if(bizMonth.compareTo("0701")>0&&bizMonth.compareTo("1231")<=0){
        accountantyear=""+(Integer.parseInt(bizYear)+1);
      }else{
        accountantyear=bizYear;
      }
      pagination.getQueryCriterions().put("accountantyear", accountantyear);

      List list = clearaccountBS.findClearaccountListByCriterions(pagination,securityInfo);

      List list_all = clearaccountBS.findClearaccountAllListByCriterions(pagination,securityInfo);
      HttpSession session = request.getSession();
      session.setAttribute("clearaccountalllist", list_all);
      
      request.setAttribute("clearaccountlist", list);
      request.setAttribute("bizDate", bizDate);
      request.setAttribute("clearbizDate", bizDate.substring(4, 8));

    } catch (BusinessException bex) {
      messages = new ActionMessages();
      messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(bex
          .getLocalizedMessage().toString(), false));
      saveErrors(request, messages);
    } catch (Exception ex) {
      ex.printStackTrace();
    }
    return mapping.findForward("to_clearaccount_list");
  }

  private Pagination getPagination(String paginationKey,
      HttpServletRequest request) {
    Pagination pagination = (Pagination) request.getSession().getAttribute(
        paginationKey);
    if (pagination == null) {
      pagination = new Pagination(0, 10, 1, "org.id", "ASC", new HashMap(0));
      request.getSession().setAttribute(paginationKey, pagination);
    }

    return pagination;
  }
}
