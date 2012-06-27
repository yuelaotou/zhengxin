package org.xpup.hafmis.syscollection.paymng.monthpay.action;

import java.math.BigDecimal;
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
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.xpup.common.exception.BusinessException;
import org.xpup.common.util.BSUtils;
import org.xpup.common.util.Pagination;
import org.xpup.common.util.PaginationUtils;
import org.xpup.hafmis.common.util.BusiConst;
import org.xpup.hafmis.common.util.BusiTools;
import org.xpup.hafmis.orgstrct.dto.OfficeDto;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.syscollection.paymng.monthpay.bsinterface.IMonthpayBS;
import org.xpup.hafmis.syscollection.paymng.monthpay.dto.PaymentBankNameDTO;
import org.xpup.hafmis.syscollection.paymng.monthpay.form.MonthpayJYAF;
import org.xpup.hafmis.syscollection.paymng.monthpay.form.MonthpayTbFindAF;
import org.xpup.hafmis.syscollection.querystatistics.paymentpickstatistics.searchLackInfo.dto.SearchLackInfoContentDTO;

public class MonthpayTaShowAC extends Action{
public static final String PAGINATION_KEY = "org.xpup.hafmis.syscollection.paymng.monthpay.action.MonthpayTaShowAC";
  
  public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        ActionMessages messages =null;
    try{
      String orgPaymentMonth="";
      String empPaymentMonth="";
      // List paymentBankNameList=new ArrayList();
      Pagination pagination = getPagination(PAGINATION_KEY, request);
      PaginationUtils.updatePagination(pagination, request);  
      SecurityInfo securityInfo=(SecurityInfo)request.getSession().getAttribute("SecurityInfo");
      saveToken(request);
      IMonthpayBS monthpayBS = (IMonthpayBS) BSUtils
      .getBusinessService("monthpayBS", this, mapping.getModuleConfig());
      MonthpayJYAF f = new MonthpayJYAF();
      String orgid=(String)pagination.getQueryCriterions().get("id");
      String name=(String)pagination.getQueryCriterions().get("name");
      if(orgid != null){
      f = monthpayBS.findMonthpayInfo(new Integer(orgid),securityInfo);
      
      List list=monthpayBS.findSearchLackInfoAllByCriterions(orgid,securityInfo);
      orgPaymentMonth=monthpayBS.getOrgPaymentMonth(orgid, "org");
   
      // ²éÑ¯¹ÒÕËÓà¶î
      BigDecimal overPay = monthpayBS.queryOverPay(orgid);
      f.setOrgPayMonth(orgPaymentMonth);
      empPaymentMonth=monthpayBS.getOrgPaymentMonth(orgid, "");
      f.setEmpPayMonth(empPaymentMonth);
      f.setOverPay(overPay);
      if(list.size()>0){
        SearchLackInfoContentDTO searchLackInfoContentDTO=(SearchLackInfoContentDTO)list.get(0);
        if (searchLackInfoContentDTO!=null){
          f.setLackMonths(searchLackInfoContentDTO.getLackMonths());
        }   
      
      }
      
      f.setPayStatus(1);
//      List bankList=monthpayBS.queryPaymentBankNameList(new Integer(orgid));
//      PaymentBankNameDTO  paymentBankNameDTO=null;
//      Iterator itr1 = bankList.iterator();
//      while(itr1.hasNext())
//      {
//        paymentBankNameDTO = (PaymentBankNameDTO) itr1.next();
//        paymentBankNameList.add(new org.apache.struts.util.LabelValueBean(paymentBankNameDTO.getPaymentBankNameValues(), 
//            paymentBankNameDTO.getPaymentBankNameKey()));
//      }
      
      }
      
     f.setPay_kind((BusiTools
            .listBusiProperty(BusiConst.PAY_KIND_INFO)));
     f.setPay_way((BusiTools
            .listBusiProperty(BusiConst.PAY_WAY_INFO)));
      
//      f.setOrgid(orgid);
//      f.setName(name);
//      List paymentBankAccList=new ArrayList();
//      
//      
//      request.setAttribute("paymentBankAccList", paymentBankAccList);
//      request.setAttribute("paymentBankNameList", paymentBankNameList);
      request.setAttribute("monthpayJYAF", f);

    }catch(BusinessException bex){
      messages=new ActionMessages();
      messages.add(ActionMessages.GLOBAL_MESSAGE,new ActionMessage(bex.getMessage(),
          false));
      saveErrors(request, messages);
    }catch(Exception ex){
      ex.printStackTrace();
    }
    return mapping.findForward("show_monthpay");
  }
  
  private Pagination getPagination(String paginationKey,
      HttpServletRequest request) {
    Pagination pagination = (Pagination) request.getSession().getAttribute(
        paginationKey);
    if (pagination == null) {
      pagination = new Pagination();
      request.getSession().setAttribute(paginationKey, pagination);
    }   
 
    return pagination;
  }
}