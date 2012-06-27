package org.xpup.hafmis.syscollection.paymng.personaddpay.action;

import java.math.BigDecimal;
import java.util.ArrayList;
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
import org.xpup.hafmis.syscollection.common.domain.entity.EmpAddPay;
import org.xpup.hafmis.syscollection.common.domain.entity.Org;
import org.xpup.hafmis.syscollection.paymng.monthpay.action.MonthpayTbShowAC;
import org.xpup.hafmis.syscollection.paymng.monthpay.bsinterface.IMonthpayBS;
import org.xpup.hafmis.syscollection.paymng.monthpay.form.MonthpayJYAF;
import org.xpup.hafmis.syscollection.paymng.orgaddpay.bsinterface.IOrgaddpayBS;
import org.xpup.hafmis.syscollection.paymng.orgaddpay.dto.OrgaddpayHeadPrintDto;
import org.xpup.hafmis.syscollection.paymng.personaddpay.bsinterface.IPersonAddPayBS;
import org.xpup.hafmis.syscollection.paymng.personaddpay.form.PersonAddPayAF;

public class EmpAddPayTbMaintainAC extends LookupDispatchAction{
  public static final String PAGINATION_KEY = "org.xpup.hafmis.syscollection.paymng.personaddpay.action.PersonAddPayTaShowAC";
  protected Map getKeyMethodMap() {
    Map map = new HashMap();
    map.put("button.del.addpay", "delAddpay");
    map.put("button.update", "update");
    map.put("button.delete", "delete");
    map.put("button.print", "print");
    map.put("button.over.addpay", "overAddPay");
    map.put("button.referring.data", "referring");
    map.put("button.pproval.data", "pproval");
    map.put("button.printList", "printList");
    map.put("button.printDoc", "printDoc");
    map.put("button.reupdate", "reupdate");
    return map;
  }

  public ActionForward delAddpay(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
    ActionMessages messages =null;
    try{
      IdAF f = (IdAF)form;     
      SecurityInfo securityInfo = (SecurityInfo) request.getSession().getAttribute("SecurityInfo");
      IPersonAddPayBS personAddPayBS = (IPersonAddPayBS) BSUtils
      .getBusinessService("personAddPayBS", this, mapping.getModuleConfig());
      personAddPayBS.repealAddpay(f.getId(), securityInfo);
    
    }catch(BusinessException b){
      messages=new ActionMessages();
      messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(b.getMessage(),
          false));
      saveErrors(request, messages);
    }
    return mapping.findForward("to_personAddPayMaintain_show");
  }
  public ActionForward update(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
    try{
    IdAF f=(IdAF)form; 
    IPersonAddPayBS personAddPayBS = (IPersonAddPayBS) BSUtils
    .getBusinessService("personAddPayBS", this, mapping.getModuleConfig());
    EmpAddPay empAddPay = personAddPayBS.findEmpAddPayInfo(f.getId());
    Pagination pagination=new Pagination();
    pagination.getQueryCriterions().put("orgId", empAddPay.getOrg().getId().toString());
    pagination.getQueryCriterions().put("unitName", empAddPay.getOrg().getOrgInfo().getName());
    pagination.getQueryCriterions().put("paymentHeadId",f.getId());
    request.getSession().setAttribute(PersonAddPayTaShowAC.PAGINATION_KEY, pagination);
    }catch(Exception e){
      e.printStackTrace();
    }
    
    return mapping.findForward("personAddPayTaShowAC");
  }
  public ActionForward delete(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
    ActionMessages messages =null;
    try{
      
      IdAF f = (IdAF)form;  
      SecurityInfo securityInfo = (SecurityInfo) request.getSession().getAttribute("SecurityInfo");
      IPersonAddPayBS personAddPayBS = (IPersonAddPayBS) BSUtils
      .getBusinessService("personAddPayBS", this, mapping.getModuleConfig());

      String id=f.getId().toString();
     
      List list=new ArrayList();
      list=personAddPayBS.querytest1(id);
      if(list.size()==0){
        messages=new ActionMessages();
        messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("删除失败,发生并发操作，数据已经删除！",
            false));
        saveErrors(request, messages);
      }else{
      personAddPayBS.deleteAddpay(f.getId(), securityInfo);
      }
    
     
    }catch(BusinessException b){
      messages=new ActionMessages();
      messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(b.getMessage(),
          false));
      saveErrors(request, messages);
    }
    return mapping.findForward("to_personAddPayMaintain_show");
  }
  public ActionForward print(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
   
    IdAF f = (IdAF)form;   
    Pagination pagination=(Pagination)request.getSession().getAttribute("pagination");
    pagination.getQueryCriterions().put("paymentHeadId", f.getId());
    IPersonAddPayBS personAddPayBS = (IPersonAddPayBS) BSUtils
    .getBusinessService("personAddPayBS", this, mapping.getModuleConfig()); 
    
    IMonthpayBS monthpayBS = (IMonthpayBS) BSUtils.getBusinessService(
        "monthpayBS", this, mapping.getModuleConfig());
    
    SecurityInfo securityInfo = (SecurityInfo) request.getSession().getAttribute("SecurityInfo");
    EmpAddPay empAddPay=personAddPayBS.findEmpAddPayInfo(f.getId());
    pagination.getQueryCriterions().put("orgId",empAddPay.getOrg().getId().toString());
    pagination.getQueryCriterions().put("unitName",empAddPay.getOrg().getOrgInfo().getName());
    pagination.getQueryCriterions().put("docNumber",empAddPay.getNoteNum());
    List list2=personAddPayBS.findPersonAddPayPrintList(pagination); 
    PersonAddPayAF personAddPayAF=new PersonAddPayAF();
    Org org=personAddPayBS.findOrgInfo(empAddPay.getOrg().getId().toString(), "2", securityInfo);
    String office="";
    String collbankname="";
    String bankid="";
    String bankname="";
    String str[]=new String[2];
    String receivables_orgname="";
    String receivables_bank_acc="";
    String receivables_bank_name="";
    String userName="";
//  List officlist=securityInfo.getAllCenterList();
//  String collBankid=org.getOrgInfo().getCollectionBankId();
//  if(officlist != null){
//    OfficeDto dto=(OfficeDto)officlist.get(0);
//    office=dto.getOfficeName();
//  }
    List banklist = new ArrayList();
    banklist = monthpayBS.queryCollBankInfo(org.getOrgInfo()
        .getOfficecode(), org.getOrgInfo().getCollectionBankId());
    if (banklist.size() > 0) {
      for (int i = 0; i < banklist.size(); i++) {
        Object obj[] = null;
        obj = (Object[]) banklist.get(0);
        if(obj[0] != null){
          receivables_orgname = obj[0].toString();
        }
        if(obj[2] != null){
          receivables_bank_acc = obj[2].toString();
        }
        if(obj[1] != null){
          receivables_bank_name = obj[1].toString();
        }
      }
    }
  str= personAddPayBS.queryOfficeBankNames(empAddPay.getOrg().getId().toString(), "2", f.getId().toString(), "B", securityInfo);
  if(str[0]!=null){
    office=str[0];
  }
  if(str[1]!=null){
    collbankname=str[1];
  }
   if(org.getOrgInfo().getPayBank()!=null){
     bankid=org.getOrgInfo().getPayBank().getAccountNum();
     bankname=org.getOrgInfo().getPayBank().getName();
   }
   personAddPayAF.setOffice(office);
   personAddPayAF.setCollbankname(collbankname);
   personAddPayAF.setBankid(bankid);
   personAddPayAF.setBankname(bankname);
    personAddPayAF.setPersonAddPayList(list2);
    
    personAddPayAF.setReceivables_bank_acc(empAddPay.getReceivables_bank_acc());
    personAddPayAF.setReceivables_bank_name(empAddPay.getReceivables_bank_name());
    personAddPayAF.setReceivables_orgname(receivables_orgname);
    personAddPayAF.setPayment_bank_acc(empAddPay.getPayment_bank_acc());
    personAddPayAF.setPayment_bank_name(empAddPay.getPayment_bank_name());
    personAddPayAF.setPayment_orgname(org.getOrgInfo().getName());
    personAddPayAF.setPayWay(empAddPay.getPay_way());
    personAddPayAF.setPayKind(empAddPay.getPay_kind());
    personAddPayAF.setNoteNum(empAddPay.getNoteNum());
    String name = monthpayBS.queryMakerPara();
    personAddPayAF.setBizDate(securityInfo.getUserInfo().getBizDate());
    if (name.equals("1")) {
      userName = securityInfo.getUserName();
    } else {
      userName = securityInfo.getRealName();
    }
    
    
    request.setAttribute("empaddpURL", "javascript:history.back();");
    request.getSession().setAttribute("userName", userName);
    request.getSession().setAttribute("f", personAddPayAF);
//    EmpaddpayHeadPrintDTO dto = personAddPayBS.findEmpaddpayPring(f.getId().toString());
//    request.setAttribute("empaddpay",dto);

    return mapping.findForward("to_print.jsp");
  }
    
  
  public ActionForward overAddPay(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
    ActionMessages messages =null;
    try{
      IdAF f = (IdAF)form;
      SecurityInfo securityInfo = (SecurityInfo) request.getSession().getAttribute("SecurityInfo");
      Pagination pagination=(Pagination)request.getSession().getAttribute("pagination");
      pagination.getQueryCriterions().put("paymentHeadId", f.getId());
      IPersonAddPayBS personAddPayBS = (IPersonAddPayBS) BSUtils
      .getBusinessService("personAddPayBS", this, mapping.getModuleConfig());
      IMonthpayBS monthpayBS = (IMonthpayBS) BSUtils.getBusinessService(
          "monthpayBS", this, mapping.getModuleConfig());
      //刘聃修改：添加判断是否为登记状态
      boolean flag=personAddPayBS.overAddpaya(f.getId(), securityInfo);
      if(flag){
      personAddPayBS.overAddpay(f.getId(), securityInfo);

      EmpAddPay empAddPay=personAddPayBS.findEmpAddPayInfo(f.getId());
      String orgId=empAddPay.getOrg().getId().toString();
      String unitName=empAddPay.getOrg().getOrgInfo().getName();
      String noteNum=empAddPay.getNoteNum();
      String paymentHeadId=f.getId().toString();
      BigDecimal payMoney = personAddPayBS.getOverPay(empAddPay.getNoteNum());
      String receivables_orgname="";
      String receivables_bank_acc="";
      String receivables_bank_name="";
      String userName="";
      
      List banklist = new ArrayList();
      banklist = monthpayBS.queryCollBankInfo(empAddPay.getOrg().getOrgInfo()
          .getOfficecode(), empAddPay.getOrg().getOrgInfo().getCollectionBankId());
      if (banklist.size() > 0) {
        for (int i = 0; i < banklist.size(); i++) {
          Object obj[] = null;
          obj = (Object[]) banklist.get(0);
          receivables_orgname = obj[0].toString();
          receivables_bank_acc = obj[2].toString();
          receivables_bank_name = obj[1].toString();
        }
      }
      List list2=personAddPayBS.findPersonAddPayPrint(paymentHeadId,orgId,unitName,noteNum); 
      PersonAddPayAF personAddPayAF=new PersonAddPayAF();
      Org org=personAddPayBS.findOrgInfo(orgId, "2", securityInfo);
      String office="";
      String collbankname="";
      String bankid="";
      String bankname="";
      List officlist=securityInfo.getAllCenterList();
      String collBankid=org.getOrgInfo().getCollectionBankId();
      if(officlist != null){
        OfficeDto dto1=(OfficeDto)officlist.get(0);
        office=dto1.getOfficeName();
      }
      if(collBankid != null){
        collbankname=personAddPayBS.findCollBank(collBankid);
     }
     if(org.getOrgInfo().getPayBank()!=null){
       bankid=org.getOrgInfo().getPayBank().getAccountNum();
       bankname=org.getOrgInfo().getPayBank().getName();
     }
     personAddPayAF.setOffice(office);
     personAddPayAF.setCollbankname(collbankname);
     personAddPayAF.setBankid(bankid);
     personAddPayAF.setBankname(bankname);
     
     personAddPayAF.setReceivables_bank_acc(empAddPay.getReceivables_bank_acc());
     personAddPayAF.setReceivables_bank_name(empAddPay.getReceivables_bank_name());
     personAddPayAF.setReceivables_orgname(receivables_orgname);
     personAddPayAF.setPayment_bank_acc(empAddPay.getPayment_bank_acc());
     personAddPayAF.setPayment_bank_name(empAddPay.getPayment_bank_name());
     personAddPayAF.setPayment_orgname(org.getOrgInfo().getName());
     personAddPayAF.setPayWay(empAddPay.getPay_way());
     personAddPayAF.setPayKind(empAddPay.getPay_kind());
     personAddPayAF.setPaySum(empAddPay.getPayMoney().add(payMoney));
     String name = monthpayBS.queryMakerPara();
     personAddPayAF.setBizDate(securityInfo.getUserInfo().getBizDate());
     if (name.equals("1")) {
       userName = securityInfo.getUserName();
     } else {
       userName = securityInfo.getRealName();
     }
      request.setAttribute("monthpayURL", "personAddPayTbShowAC.do");
      personAddPayAF.setPersonAddPayList(list2);
      request.getSession().setAttribute("f", personAddPayAF);
      String report=request.getParameter("printReport");
      request.getSession().setAttribute("userName", userName);
      if(report.equals("print")){
        return mapping.findForward("emp_tdpz_cell");
      }
      }
      else{
        messages=new ActionMessages();
        messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("该补缴已经登记！",  false));     
        saveErrors(request, messages);
      }
    }catch(BusinessException b){
      messages=new ActionMessages();
      messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(b.getMessage(),
          false));
      saveErrors(request, messages);
    }
    return mapping.findForward("to_personAddPayMaintain_show");
  }
  
  /**
   * Description 个人补缴_修改记录
   * 
   * @author wangy 2008-02-26
   * @param 单位版_缴存维护_提交数据
   * @param mapping
   * @param form
   * @param request
   * @param response
   * @throws Exception
   */
  public ActionForward referring(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
    ActionMessages messages =null;
    try{
      
      IdAF f = (IdAF)form;  
      SecurityInfo securityInfo = (SecurityInfo) request.getSession().getAttribute("SecurityInfo");
      IPersonAddPayBS personAddPayBS = (IPersonAddPayBS) BSUtils
      .getBusinessService("personAddPayBS", this, mapping.getModuleConfig());
      personAddPayBS.insertEmpAddPayReferringData(f.getId(), securityInfo);
      messages=new ActionMessages();
      messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("提交数据成功！",
          false));
      saveErrors(request, messages);
    }catch(BusinessException bex){
      messages=new ActionMessages();
      messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(bex.getMessage(),
          false));
      saveErrors(request, messages);
    }
    return mapping.findForward("to_personAddPayMaintain_show");
  }
  
  /**
   * Description 个人补缴_修改记录
   * 
   * @author wangy 2008-02-26
   * @param 单位版_缴存维护_撤销提交数据
   * @param mapping
   * @param form
   * @param request
   * @param response
   * @throws Exception
   */
  public ActionForward pproval(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
    ActionMessages messages =null;
    try{
      
      IdAF f = (IdAF)form;  
      SecurityInfo securityInfo = (SecurityInfo) request.getSession().getAttribute("SecurityInfo");
      IPersonAddPayBS personAddPayBS = (IPersonAddPayBS) BSUtils
      .getBusinessService("personAddPayBS", this, mapping.getModuleConfig());
      personAddPayBS.deleteAddpayPproval(f.getId(), securityInfo);
      messages=new ActionMessages();
      messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("撤消提交数据成功！",
          false));
      saveErrors(request, messages);
    }catch(BusinessException bex){
      messages=new ActionMessages();
      messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(bex.getMessage(),
          false));
      saveErrors(request, messages);
    }
    return mapping.findForward("to_personAddPayMaintain_show");
  }  
  public ActionForward printList(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
    ActionMessages messages =null;
    try{
      
      SecurityInfo securityInfo=(SecurityInfo)request.getSession().getAttribute("SecurityInfo");
      Pagination pagination = (Pagination) request.getSession().getAttribute(PersonAddPayTbShowAC.PAGINATION_KEY); 
      IMonthpayBS monthpayBS = (IMonthpayBS) BSUtils
      .getBusinessService("monthpayBS", this, mapping.getModuleConfig());
      IPersonAddPayBS personAddPayBS = (IPersonAddPayBS) BSUtils
      .getBusinessService("personAddPayBS", this, mapping.getModuleConfig());
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
      List paymentList = personAddPayBS.findEmpaddpayList_jj(pagination, securityInfo);
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
    return mapping.findForward("personaddpay_lb_cell");
  }  
  public ActionForward printDoc(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
    IdAF f = (IdAF)form;   
    Pagination pagination=(Pagination)request.getSession().getAttribute("pagination");
    pagination.getQueryCriterions().put("paymentHeadId", f.getId());
    IPersonAddPayBS personAddPayBS = (IPersonAddPayBS) BSUtils
    .getBusinessService("personAddPayBS", this, mapping.getModuleConfig()); 
    
    IMonthpayBS monthpayBS = (IMonthpayBS) BSUtils.getBusinessService(
        "monthpayBS", this, mapping.getModuleConfig());
    
    SecurityInfo securityInfo = (SecurityInfo) request.getSession().getAttribute("SecurityInfo");
    EmpAddPay empAddPay=personAddPayBS.findEmpAddPayInfo(f.getId());
    pagination.getQueryCriterions().put("orgId",empAddPay.getOrg().getId().toString());
    pagination.getQueryCriterions().put("unitName",empAddPay.getOrg().getOrgInfo().getName());
    pagination.getQueryCriterions().put("docNumber",empAddPay.getNoteNum());
    List list2=personAddPayBS.findPersonAddPayPrintList(pagination); 
    PersonAddPayAF personAddPayAF=new PersonAddPayAF();
    Org org=personAddPayBS.findOrgInfo(empAddPay.getOrg().getId().toString(), "2", securityInfo);
    BigDecimal payMoney = personAddPayBS.getOverPay(empAddPay.getNoteNum());
    String office="";
    String collbankname="";
    String bankid="";
    String bankname="";
    String str[]=new String[2];
    String receivables_orgname="";
    String receivables_bank_acc="";
    String receivables_bank_name="";
    String userName="";
//  List officlist=securityInfo.getAllCenterList();
//  String collBankid=org.getOrgInfo().getCollectionBankId();
//  if(officlist != null){
//    OfficeDto dto=(OfficeDto)officlist.get(0);
//    office=dto.getOfficeName();
//  }
    List banklist = new ArrayList();
    banklist = monthpayBS.queryCollBankInfo(org.getOrgInfo()
        .getOfficecode(), org.getOrgInfo().getCollectionBankId());
    if (banklist.size() > 0) {
      for (int i = 0; i < banklist.size(); i++) {
        Object obj[] = null;
        obj = (Object[]) banklist.get(0);
        if(obj[0] != null){
          receivables_orgname = obj[0].toString();
        }
        if(obj[2] != null){
          receivables_bank_acc = obj[2].toString();
        }
        if(obj[1] != null){
          receivables_bank_name = obj[1].toString();
        }
      }
    }
  str= personAddPayBS.queryOfficeBankNames(empAddPay.getOrg().getId().toString(), "2", f.getId().toString(), "B", securityInfo);
  if(str[0]!=null){
    office=str[0];
  }
  if(str[1]!=null){
    collbankname=str[1];
  }
   if(org.getOrgInfo().getPayBank()!=null){
     bankid=org.getOrgInfo().getPayBank().getAccountNum();
     bankname=org.getOrgInfo().getPayBank().getName();
   }
   personAddPayAF.setOffice(office);
   personAddPayAF.setCollbankname(collbankname);
   personAddPayAF.setBankid(bankid);
   personAddPayAF.setBankname(bankname);
    personAddPayAF.setPersonAddPayList(list2);
    
    personAddPayAF.setReceivables_bank_acc(empAddPay.getReceivables_bank_acc());
    personAddPayAF.setReceivables_bank_name(empAddPay.getReceivables_bank_name());
    personAddPayAF.setReceivables_orgname(receivables_orgname);
    personAddPayAF.setPayment_bank_acc(empAddPay.getPayment_bank_acc());
    personAddPayAF.setPayment_bank_name(empAddPay.getPayment_bank_name());
    if(org.getOrgInfo().getReserveaB()!=null && !org.getOrgInfo().getReserveaB().equals("")){
      personAddPayAF.setPayment_orgname(org.getOrgInfo().getReserveaB());
    }else{
      personAddPayAF.setPayment_orgname(org.getOrgInfo().getName());
    }
    personAddPayAF.setPayWay(empAddPay.getPay_way());
    personAddPayAF.setPayKind(empAddPay.getPay_kind());
    personAddPayAF.setNoteNum(empAddPay.getDocNum());
    String name = monthpayBS.queryMakerPara();
    personAddPayAF.setBizDate(securityInfo.getUserInfo().getBizDate());
    if (name.equals("1")) {
      userName = securityInfo.getUserName();
    } else {
      userName = securityInfo.getRealName();
    }
    personAddPayAF.setPaySum(empAddPay.getPayMoney().add(payMoney));
    request.setAttribute("monthpayURL", "javascript:history.back();");
    request.getSession().setAttribute("userName", userName);
    request.getSession().setAttribute("f", personAddPayAF);
    request.setAttribute("seq_aa300", monthpayBS.getSeq_aa300());
    return mapping.findForward("emp_tdpz_cell");
  }
  public ActionForward reupdate(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
    ActionMessages messages =null;
    try{
    IdAF f = (IdAF)form;
    SecurityInfo securityInfo=(SecurityInfo)request.getSession().getAttribute("SecurityInfo");
    IPersonAddPayBS personAddPayBS = (IPersonAddPayBS) BSUtils
    .getBusinessService("personAddPayBS", this, mapping.getModuleConfig()); 
    MonthpayJYAF monthpayJYAF = new MonthpayJYAF();
    monthpayJYAF=personAddPayBS.findPayInfo(f.getId().toString());
    request.setAttribute("monthpayJYAF", monthpayJYAF);
    }
    catch(Exception e){
      e.printStackTrace();
      messages=new ActionMessages();
      messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("修改失败!",false));
      saveErrors(request, messages);
    }
    return mapping.findForward("modify_monthPay");
  }
}
