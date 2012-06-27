package org.xpup.hafmis.syscollection.paymng.monthpay.action;

import java.util.HashMap;
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
import org.xpup.hafmis.common.form.IdAF;
import org.xpup.hafmis.orgstrct.dto.OfficeDto;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.syscollection.common.domain.entity.Org;
import org.xpup.hafmis.syscollection.paymng.monthpay.bsinterface.IMonthpayBS;
import org.xpup.hafmis.syscollection.paymng.monthpay.form.MonthpayJYAF;



public class MonthpayTbMaintainAC extends LookupDispatchAction{
  protected Map getKeyMethodMap() {
    Map map = new HashMap();
    map.put("button.delete", "delete");
    map.put("button.printDoc", "print");
    map.put("button.printList", "printList");
    map.put("button.reupdate", "update");
    return map;
  }

  public ActionForward delete(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
    ActionMessages messages =null;
    try{
    IdAF f = (IdAF)form;
    SecurityInfo securityInfo=(SecurityInfo)request.getSession().getAttribute("SecurityInfo");
    IMonthpayBS monthpayBS = (IMonthpayBS) BSUtils
    .getBusinessService("monthpayBS", this, mapping.getModuleConfig());
    monthpayBS.deletePaymentInfo(f.getId(), securityInfo);
    }catch(BusinessException b){
      messages=new ActionMessages();
      messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("É¾³ýÊ§°Ü!"+b.getMessage(),
          false));
      saveErrors(request, messages);
    }
    catch(Exception e){
      e.printStackTrace();
      messages=new ActionMessages();
      messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("É¾³ýÊ§°Ü!",false));
      saveErrors(request, messages);
    }
    return mapping.findForward("show_paymentList");
  }
  public ActionForward print(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
    ActionMessages messages =null;
    try{
      MonthpayJYAF f = new MonthpayJYAF();
      IdAF idaf = (IdAF)form;
      SecurityInfo securityInfo=(SecurityInfo)request.getSession().getAttribute("SecurityInfo");
      IMonthpayBS monthpayBS = (IMonthpayBS) BSUtils
      .getBusinessService("monthpayBS", this, mapping.getModuleConfig());
      f=monthpayBS.findPringInfo(idaf.getId().toString());
      Org org=monthpayBS.findOrgInfo(f.getOrgid(), "2", securityInfo);
      String office="";
      String collbankname="";
      String bankid="";
      String bankname="";
      String str[]=new String[2];
//      String str1[]=new String[2];
//    List officlist=securityInfo.getAllCenterList();
//    String collBankid=org.getOrgInfo().getCollectionBankId();
//    if(officlist != null){
//      OfficeDto dto=(OfficeDto)officlist.get(0);
//      office=dto.getOfficeName();
//    }
    str= monthpayBS.queryOfficeBankNames(f.getOrgid(), "2", idaf.getId().toString(), "A", securityInfo);
    if(str[0]!=null){
      office=str[0];
    }
    if(str[1]!=null){
      collbankname=str[1];
    }
//    if(collBankid != null){
//       collbankname=monthpayBS.findCollBank(collBankid);
//    }
//      str1=monthpayBS.queryCheckkNames(idaf.getId().toString(), "A");
//      if(!"".equals(str1[0])){
//        f.setCheckPerson(str1[0]);
//      }
//      if(!"".equals(str1[1])){
//        f.setClearPerson(str1[1]);
//      }
      List list = f.getList();
      f.setOffice(office);
      if(org.getOrgInfo().getPayBank()!=null){
        bankid=org.getOrgInfo().getPayBank().getAccountNum();
        bankname=org.getOrgInfo().getPayBank().getName();
      }
      f.setBankid(bankid);
      f.setBankname(bankname);
      f.setCollbankname(collbankname);
      f.setPrintDate(securityInfo.getUserInfo().getBizDate());
      
      String url="monthpayTbForwardUrlAC.do";
      request.setAttribute("monthpayURL", url);
      request.setAttribute("monthpayJYAF",f);
      request.setAttribute("cellList", list);
      request.setAttribute("seq_aa300", monthpayBS.getSeq_aa300());
    }catch(BusinessException b){
      messages=new ActionMessages();
      messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(b.getMessage(),
          false));
      saveErrors(request, messages);
    }
    return mapping.findForward("show_print");
  }
  public ActionForward printList(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
    ActionMessages messages =null;
    try{
      
      SecurityInfo securityInfo=(SecurityInfo)request.getSession().getAttribute("SecurityInfo");
      Pagination pagination = (Pagination) request.getSession().getAttribute(MonthpayTbShowAC.PAGINATION_KEY); 
      IMonthpayBS monthpayBS = (IMonthpayBS) BSUtils
      .getBusinessService("monthpayBS", this, mapping.getModuleConfig());
      String bizDate=securityInfo.getUserInfo().getBizDate();
      String userName="";
      String name = monthpayBS.queryMakerPara();
      if (name.equals("1")) {
        userName = securityInfo.getUserName();
      } else {
        userName = securityInfo.getRealName();
      } 
      String collBankId = (String) pagination.getQueryCriterions()
      .get("collBankId"); 
      String collBankName = "";
      if(collBankId!=null){
        collBankName = monthpayBS.findCollBank(collBankId);        
      }
      List paymentList = monthpayBS.findPaymentListPrint(pagination,securityInfo);
      request.setAttribute("bizDate",bizDate );
      request.setAttribute("userName",userName );
      request.setAttribute("collBankName",collBankName);
      request.setAttribute("cellList", paymentList);
    }catch(BusinessException b){
      messages=new ActionMessages();
      messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(b.getMessage(),
          false));
      saveErrors(request, messages);
    }
    return mapping.findForward("monthpay_lb_cell");
  }
  public ActionForward update(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
    ActionMessages messages =null;
    try{
    IdAF f = (IdAF)form;
    SecurityInfo securityInfo=(SecurityInfo)request.getSession().getAttribute("SecurityInfo");
    IMonthpayBS monthpayBS = (IMonthpayBS) BSUtils
    .getBusinessService("monthpayBS", this, mapping.getModuleConfig());
    MonthpayJYAF monthpayJYAF = new MonthpayJYAF();
    monthpayJYAF=monthpayBS.findPayInfo(f.getId().toString());
    request.setAttribute("monthpayJYAF", monthpayJYAF);
    }
    catch(Exception e){
      e.printStackTrace();
      messages=new ActionMessages();
      messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("ÐÞ¸ÄÊ§°Ü!",false));
      saveErrors(request, messages);
    }
    return mapping.findForward("modify_monthPay");
  }
}
