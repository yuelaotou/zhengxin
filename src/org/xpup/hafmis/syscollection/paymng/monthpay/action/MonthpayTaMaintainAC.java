package org.xpup.hafmis.syscollection.paymng.monthpay.action;

import java.math.BigDecimal;
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
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.actions.LookupDispatchAction;
import org.xpup.common.exception.BusinessException;
import org.xpup.common.util.BSUtils;
import org.xpup.common.util.Pagination;
import org.xpup.hafmis.common.util.BusiConst;
import org.xpup.hafmis.common.util.BusiTools;
import org.xpup.hafmis.orgstrct.dto.OfficeDto;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.syscollection.common.domain.entity.Org;
import org.xpup.hafmis.syscollection.paymng.monthpay.bsinterface.IMonthpayBS;
import org.xpup.hafmis.syscollection.paymng.monthpay.dto.PaymentBankNameDTO;
import org.xpup.hafmis.syscollection.paymng.monthpay.form.MonthpayJYAF;



public class MonthpayTaMaintainAC extends LookupDispatchAction{
  protected Map getKeyMethodMap() {
    Map map = new HashMap();
    map.put("button.check", "check");
    map.put("button.sure", "sure");
    return map;
  }

  public ActionForward check(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
    ActionMessages messages =null;
    MonthpayJYAF f = (MonthpayJYAF)form;
   // List paymentBankNameList=new ArrayList();
    SecurityInfo securityInfo=(SecurityInfo)request.getSession().getAttribute("SecurityInfo");
    try{
    messages=new ActionMessages();
    IMonthpayBS monthpayBS = (IMonthpayBS) BSUtils
    .getBusinessService("monthpayBS", this, mapping.getModuleConfig());
    MonthpayJYAF mf = new MonthpayJYAF();
    mf = monthpayBS.findMonthpayInfoCheck(f,securityInfo);
//    List bankList=monthpayBS.queryPaymentBankNameList(new Integer(mf.getOrgid()));
//    PaymentBankNameDTO  paymentBankNameDTO=null;
//    Iterator itr1 = bankList.iterator();
//    while(itr1.hasNext())
//    {
//      paymentBankNameDTO = (PaymentBankNameDTO) itr1.next();
//      paymentBankNameList.add(new org.apache.struts.util.LabelValueBean(paymentBankNameDTO.getPaymentBankNameValues(), 
//          paymentBankNameDTO.getPaymentBankNameKey()));
//    }
    
    mf.setNoteNum(f.getNoteNum());
    mf.setPay_kind((BusiTools
        .listBusiProperty(BusiConst.PAY_KIND_INFO)));
    mf.setPay_way((BusiTools
        .listBusiProperty(BusiConst.PAY_WAY_INFO)));
//    List paymentBankAccList=new ArrayList();
    
    
//    request.setAttribute("paymentBankAccList", paymentBankAccList);
//    request.setAttribute("paymentBankNameList", paymentBankNameList);
    request.setAttribute("monthpayJYAF", mf);
    }catch(BusinessException b){
      messages=new ActionMessages();
      messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(b.getMessage(),
          false));
      saveErrors(request, messages);
      request.getSession().removeAttribute(MonthpayTaShowAC.PAGINATION_KEY);
      return mapping.findForward("show_monthpay");
    }
    f.reset(mapping,request);
   // request.getSession().removeAttribute(MonthpayTaShowAC.PAGINATION_KEY);
    return mapping.findForward("show_monthpay_jsp");
  }
  public ActionForward sure(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
    ActionMessages messages =null;
    MonthpayJYAF f = (MonthpayJYAF)form;
    String report = request.getParameter("report");      
    SecurityInfo securityInfo=(SecurityInfo)request.getSession().getAttribute("SecurityInfo");
    IMonthpayBS monthpayBS = (IMonthpayBS) BSUtils
    .getBusinessService("monthpayBS", this, mapping.getModuleConfig());
    List list = null;
    try{
      list = monthpayBS.addPaymentInfo(f,securityInfo);
    }catch(BusinessException b){
      messages=new ActionMessages();
      messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(b.getMessage(),
          false));
      saveErrors(request, messages);
      request.getSession().removeAttribute(MonthpayTaShowAC.PAGINATION_KEY);
    }
    f.reset(mapping,request);
    
    if(report.equals("print")){
      String orgid=f.getOrgid();
      Org org=monthpayBS.findOrgInfo(orgid, "2", securityInfo);
      String office="";
      String collbankname="";
      String bankid="";
      String bankname="";
      List officlist=securityInfo.getAllCenterList();
      String collBankid=org.getOrgInfo().getCollectionBankId();
      if(officlist != null){
        OfficeDto dto=(OfficeDto)officlist.get(0);
        office=dto.getOfficeName();
      }
      if(collBankid != null){
         collbankname=monthpayBS.findCollBank(collBankid);
      }
      String personCounts = request.getParameter("personCounts");
      String personCountsAdd =request.getParameter("personCountsAdd");
      String personCountsLess = request.getParameter("personCountsLess");
      String ultimoPersonCounts = request.getParameter("ultimoPersonCounts");
      String payMoney = request.getParameter("payMoney");
      String payMoneyAdd = request.getParameter("payMoneyAdd");
      String payMoneyLess = request.getParameter("payMoneyLess");
      String ultimoPayMoney = request.getParameter("ultimoPayMoney");
      String sumPay = request.getParameter("sumPay");
      int monthCounts=BusiTools.monthInterval(f.getInceptMonth(), f.getPayMonth())+1;
      f.setSumPay(new BigDecimal(sumPay));
      f.setPersonCounts(new Integer(personCounts));
      f.setPersonCountsAdd(new Integer(personCountsAdd));
      f.setPersonCountsLess(new Integer(personCountsLess));
      f.setUltimoPersonCounts(new Integer(ultimoPersonCounts));
      f.setPayMoneyAdd(new BigDecimal(payMoneyAdd));
      f.setPayMoneyLess(new BigDecimal(payMoneyLess));
      f.setPayMoney(new BigDecimal(payMoney));
      f.setUltimoPayMoney(new BigDecimal(ultimoPayMoney));
      f.setOffice(office);
      f.setPayMonthCount(String.valueOf(monthCounts));
      if(org.getOrgInfo().getPayBank()!=null){
        bankid=org.getOrgInfo().getPayBank().getAccountNum();
        bankname=org.getOrgInfo().getPayBank().getName();
      }
      f.setBankid(bankid);
      f.setBankname(bankname);
      f.setCollbankname(collbankname);
      f.setPrintDate(securityInfo.getUserInfo().getBizDate());
      request.setAttribute("monthpayJYAF",f);
      request.setAttribute("cellList", list);
      request.getSession().setAttribute(MonthpayTaShowAC.PAGINATION_KEY, null);
      return mapping.findForward("show_print");
    }
    return mapping.findForward("show_monthpay");
  }
}
