package org.xpup.hafmis.syscollection.paymng.orgaddpay.action;

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
import org.xpup.hafmis.syscollection.common.domain.entity.Org;
import org.xpup.hafmis.syscollection.common.domain.entity.OrgAddPay;
import org.xpup.hafmis.syscollection.common.domain.entity.OrgHAFAccountFlow;
import org.xpup.hafmis.syscollection.paymng.monthpay.bsinterface.IMonthpayBS;
import org.xpup.hafmis.syscollection.paymng.monthpay.form.MonthpayJYAF;
import org.xpup.hafmis.syscollection.paymng.orgaddpay.bsinterface.IOrgaddpayBS;
import org.xpup.hafmis.syscollection.paymng.orgaddpay.dto.OrgaddpayHeadPrintDto;
import org.xpup.hafmis.syscollection.paymng.orgaddpay.dto.OrgaddpayMaintainDto;
import org.xpup.hafmis.syscollection.paymng.personaddpay.action.PersonAddPayTbShowAC;
import org.xpup.hafmis.syscollection.paymng.personaddpay.bsinterface.IPersonAddPayBS;


public class OrgaddpayTbMaintainAC extends LookupDispatchAction{
  protected Map getKeyMethodMap() {
    Map map = new HashMap();
    map.put("button.del.addpay", "delAddpay");
    map.put("button.update", "update");
    map.put("button.delete", "delete");
    map.put("button.print", "print");
    map.put("button.over.addpay","overAddpay");
    map.put("button.referring.data", "overAddpayCommitData");
    map.put("button.pproval.data", "overAddpayCancelData");
    map.put("button.printList", "printList");
    map.put("button.printDoc", "printDoc");
    map.put("button.reupdate", "reupdate");
    return map;
  }
  public ActionForward overAddpay(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
    ActionMessages messages =null;
    try{
      IdAF f = (IdAF)form;
      SecurityInfo securityInfo=(SecurityInfo)request.getSession().getAttribute("SecurityInfo");
      IOrgaddpayBS orgaddpayBS = (IOrgaddpayBS) BSUtils
      .getBusinessService("orgaddpayBS", this, mapping.getModuleConfig());
      String head_ID=f.getId().toString();
      String noteNum = request.getParameter("newNoteNum");
      String report = request.getParameter("report");  
      //刘聃修改：添加判断是否为登记状态
      String id=orgaddpayBS.findIDld(head_ID);

      Pagination pagination = (Pagination) request.getSession().getAttribute(
          OrgaddpayTaShowAC.PAGINATION_KEY);
      boolean flag=orgaddpayBS.overAddpaya(id, securityInfo);
     
      if(!flag){
      orgaddpayBS.overAddpay("", id,securityInfo,noteNum,pagination);
      
      }
      else{
        messages=new ActionMessages();
        messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("该业务状态已经为登记状态！",
            false));
        saveErrors(request, messages);
       // return mapping.findForward("show_orgaddpay");
      }       
      IMonthpayBS monthpayBS = (IMonthpayBS) BSUtils
      .getBusinessService("monthpayBS", this, mapping.getModuleConfig());
      if(report.equals("print")){
        OrgaddpayHeadPrintDto dto = orgaddpayBS.findOrgaddpayPring(f.getId().toString());
        MonthpayJYAF monthpayJYAF = new MonthpayJYAF();
        Org org=orgaddpayBS.findOrgInfo(dto.getOrgId(), "2", securityInfo);
        List banklist = new ArrayList();
        banklist = monthpayBS.queryCollBankInfo(org.getOrgInfo()
            .getOfficecode(), org.getOrgInfo().getCollectionBankId());
        String receivables_orgname = ""; // 收款单位名称
        String receivables_bank_acc = ""; // 收款单位归集银行账号
        String receivables_bank_name = ""; // 收款单位归集银行名称
        if (banklist.size() > 0) {
          for (int i = 0; i < banklist.size(); i++) {
            Object obj[] = null;
            obj = (Object[]) banklist.get(0);
            if(obj[0] != null){
              receivables_orgname=obj[0].toString();
            }
            if(obj[2] != null){
              receivables_bank_acc=obj[2].toString();
            }
            if(obj[1] != null){
              receivables_bank_name=obj[1].toString();
            }
          }
        }  
        monthpayJYAF.setOrgid(org.getId().toString());
        monthpayJYAF.setPrintDate(securityInfo.getUserInfo().getBizDate());
        monthpayJYAF.setNoteNum(dto.getDocNum());
        monthpayJYAF.setReceivables_orgname(receivables_orgname);
        monthpayJYAF.setReceivables_bank_acc(receivables_bank_acc);
        monthpayJYAF.setReceivables_bank_name(receivables_bank_name);
        monthpayJYAF.setPayment_orgname(org.getOrgInfo().getName());
        monthpayJYAF.setPayment_bank_acc(org.getOrgInfo().getPayBank().getAccountNum());
        monthpayJYAF.setPayment_bank_name(org.getOrgInfo().getPayBank().getName());
        monthpayJYAF.setSumPay(new BigDecimal(dto.getPay()));
        monthpayJYAF.setPayKind(dto.getPayKind());
        monthpayJYAF.setPayWay(dto.getPayWay());
        monthpayJYAF.setPersonCounts(new Integer(dto.getPersonCounts()));
        request.setAttribute("monthpayJYAF", monthpayJYAF);
        request.setAttribute("monthpayURL", "orgaddpayTbShowAC.do");
        return mapping.findForward("org_tdpz_cell");
      }
    }catch(Exception e){
      e.printStackTrace();
      messages=new ActionMessages();
      messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("完成失败！",
          false));
      saveErrors(request, messages);
    }    
    return mapping.findForward("show_orgaddpay");
  }
  public ActionForward delAddpay(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
    ActionMessages messages =null;
    try{
      IdAF f = (IdAF)form;
      SecurityInfo securityInfo=(SecurityInfo)request.getSession().getAttribute("SecurityInfo");
      IOrgaddpayBS orgaddpayBS = (IOrgaddpayBS) BSUtils
      .getBusinessService("orgaddpayBS", this, mapping.getModuleConfig());
      orgaddpayBS.repealAddpay(f.getId(), securityInfo);
    
    }catch(BusinessException b){
      messages=new ActionMessages();
      messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(b.getMessage(),
          false));
      saveErrors(request, messages);
    }
    return mapping.findForward("show_orgaddpay");
  }
  public ActionForward update(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
    try{
    IdAF f=(IdAF)form;
    IOrgaddpayBS orgaddpayBS = (IOrgaddpayBS) BSUtils
    .getBusinessService("orgaddpayBS", this, mapping.getModuleConfig());
    OrgAddPay orgAddPay = orgaddpayBS.findOrgaddpayInfo(f.getId());
    Pagination pagination=new Pagination();
    pagination.getQueryCriterions().put("id", orgAddPay.getOrg().getId().toString());
    pagination.getQueryCriterions().put("name", orgAddPay.getOrg().getOrgInfo().getName());
    request.setAttribute("type","0");
    request.getSession().setAttribute(OrgaddpayTaShowAC.PAGINATION_KEY, pagination);
    }catch(Exception e){
      e.printStackTrace();
    }
    
    return mapping.findForward("show_orgaddpayta");
  }
  public ActionForward delete(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
    ActionMessages messages =null;
    try{
      
      IdAF f = (IdAF)form;
      SecurityInfo securityInfo=(SecurityInfo)request.getSession().getAttribute("SecurityInfo");
      IOrgaddpayBS orgaddpayBS = (IOrgaddpayBS) BSUtils
      .getBusinessService("orgaddpayBS", this, mapping.getModuleConfig());
     //刘聃修改：添加判断是否是否已经删除
     boolean flag=orgaddpayBS.findld1(f.getId(), securityInfo);
     if(flag){
      orgaddpayBS.deleteAddpay(f.getId(), securityInfo);
      }
    else{
      messages=new ActionMessages();
      messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("该业务已经被删除", false));
      saveErrors(request, messages);
       }
   }catch(BusinessException b){
      messages=new ActionMessages();
      messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(b.getMessage(),
          false));
      saveErrors(request, messages);
    }
    return mapping.findForward("show_orgaddpay");
  }
  public ActionForward print(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

    IdAF f=(IdAF)form;
    SecurityInfo securityInfo=(SecurityInfo)request.getSession().getAttribute("SecurityInfo");
    IOrgaddpayBS orgaddpayBS = (IOrgaddpayBS) BSUtils
    .getBusinessService("orgaddpayBS", this, mapping.getModuleConfig());
    OrgaddpayHeadPrintDto dto = orgaddpayBS.findOrgaddpayPring(f.getId().toString());
    Org org=orgaddpayBS.findOrgInfo(dto.getOrgId(), "2", securityInfo);
    String office="";
    String collbankname="";
    String bankid="";
    String bankname="";
    String str[]=new String[2];
//    List officlist=securityInfo.getAllCenterList();
//    String collBankid=org.getOrgInfo().getCollectionBankId();
//    if(officlist != null){
//      OfficeDto dto1=(OfficeDto)officlist.get(0);
//      office=dto1.getOfficeName();
//    }
    str= orgaddpayBS.queryOfficeBankNames(dto.getOrgId(), "2", f.getId().toString(), "M", securityInfo);
    if(str[0]!=null){
      office=str[0];
    }
    if(str[1]!=null){
      collbankname=str[1];
    }
    
////查询办事处银行开始   
//      OrgHAFAccountFlow orgHAFAccountFlow = orgaddpayBS.queryOrgHAFAccountFlow(f
//          .getId().toString(), "M");
//      if (orgHAFAccountFlow != null) {
//        if (orgHAFAccountFlow.getOfficeCode() != null) {
//          String officeCode = orgaddpayBS.queryOfficeName(orgHAFAccountFlow
//              .getOfficeCode());
//          office = officeCode;
//        }
//        if (orgHAFAccountFlow.getMoneyBank() != null) {
//          collbankname = orgaddpayBS.findCollBank(orgHAFAccountFlow
//              .getMoneyBank());
//        }
//      } else {
//        if (org.getOrgInfo().getOfficecode() != null) {
//          String officeCode = orgaddpayBS.queryOfficeName(org.getOrgInfo()
//              .getOfficecode());
//          office = officeCode;
//        }
//        if (collBankid != null) {
//          collbankname = orgaddpayBS.findCollBank(collBankid);
//        }
//      }
////查询办事处银行结束
      
      
      
    if(org.getOrgInfo().getPayBank()!=null){
      bankid=org.getOrgInfo().getPayBank().getAccountNum();
      bankname=org.getOrgInfo().getPayBank().getName();
    }
    dto.setBankid(bankid);
    dto.setBankname(bankname);
    dto.setOffice(office);
    dto.setCollbankname(collbankname);
    request.setAttribute("orgaddpay",dto);
    return mapping.findForward("show_print");
  }
  public ActionForward overAddpayCommitData(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
    ActionMessages messages =null;
    try{
      IdAF f = (IdAF)form;
      String type=request.getParameter("types");
      request.setAttribute("type", type);
      SecurityInfo securityInfo=(SecurityInfo)request.getSession().getAttribute("SecurityInfo");
      IOrgaddpayBS orgaddpayBS = (IOrgaddpayBS) BSUtils
      .getBusinessService("orgaddpayBS", this, mapping.getModuleConfig());
      
      OrgAddPay orgAddPay = orgaddpayBS.findOrgaddpayInfo(f.getId());
      String orgid = orgAddPay.getOrg().getId().toString();
      orgaddpayBS.commit_Maintain(orgid,f.getId().toString(),securityInfo);
      
      ActionMessages messages_1 =null;
      messages_1=new ActionMessages();
      messages_1.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("提交成功",
          false));
      saveErrors(request, messages_1);
    }catch(BusinessException e){
      messages=new ActionMessages();
      messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(e.getMessage(),
          false));
      saveErrors(request, messages);
    }    
    return mapping.findForward("show_orgaddpay");
  }
  public ActionForward overAddpayCancelData(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
    ActionMessages messages =null;
    try{
      IdAF f = (IdAF)form;
      String type=request.getParameter("types");
      request.setAttribute("type", type);
      SecurityInfo securityInfo=(SecurityInfo)request.getSession().getAttribute("SecurityInfo");
      IOrgaddpayBS orgaddpayBS = (IOrgaddpayBS) BSUtils
      .getBusinessService("orgaddpayBS", this, mapping.getModuleConfig());
      
      OrgAddPay orgAddPay = orgaddpayBS.findOrgaddpayInfo(f.getId());
      String orgid = orgAddPay.getOrg().getId().toString();
      orgaddpayBS.cancel_Maintain(orgid,f.getId().toString(),securityInfo);
      
      ActionMessages messages_1 =null;
      messages_1=new ActionMessages();
      messages_1.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("撤销提交成功",
          false));
      saveErrors(request, messages_1);
    }catch(BusinessException e){
      messages=new ActionMessages();
      messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(e.getMessage(),
          false));
      saveErrors(request, messages);
    }    
    return mapping.findForward("show_orgaddpay");
  }
  public ActionForward printList(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
    ActionMessages messages =null;
    try{
      
      SecurityInfo securityInfo=(SecurityInfo)request.getSession().getAttribute("SecurityInfo");
      Pagination pagination = (Pagination) request.getSession().getAttribute(OrgaddpayTbShowAC.PAGINATION_KEY); 
      IMonthpayBS monthpayBS = (IMonthpayBS) BSUtils
      .getBusinessService("monthpayBS", this, mapping.getModuleConfig());
      IOrgaddpayBS orgaddpayBS = (IOrgaddpayBS) BSUtils
      .getBusinessService("orgaddpayBS", this, mapping.getModuleConfig());
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
      List orgaddpayList = orgaddpayBS.findOrgaddpayListPrint(pagination,securityInfo);
      request.setAttribute("bizDate",bizDate );
      request.setAttribute("userName",userName );
      request.setAttribute("collBankName",collBankName);
      request.setAttribute("cellList", orgaddpayList);
    }catch(BusinessException b){
      messages=new ActionMessages();
      messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(b.getMessage(),
          false));
      saveErrors(request, messages);
    }
    return mapping.findForward("orgaddpay_lb_cell");
  }
  public ActionForward printDoc(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
    ActionMessages messages =null;
    try{

      IdAF f = (IdAF)form;
      SecurityInfo securityInfo=(SecurityInfo)request.getSession().getAttribute("SecurityInfo");
      IMonthpayBS monthpayBS = (IMonthpayBS) BSUtils
      .getBusinessService("monthpayBS", this, mapping.getModuleConfig());
      IOrgaddpayBS orgaddpayBS = (IOrgaddpayBS) BSUtils
      .getBusinessService("orgaddpayBS", this, mapping.getModuleConfig());
      OrgaddpayHeadPrintDto dto = orgaddpayBS.findOrgaddpayPring(f.getId().toString());
      MonthpayJYAF monthpayJYAF = new MonthpayJYAF();
      Org org=orgaddpayBS.findOrgInfo(dto.getOrgId(), "2", securityInfo);
      List banklist = new ArrayList();
      banklist = monthpayBS.queryCollBankInfo(org.getOrgInfo()
          .getOfficecode(), org.getOrgInfo().getCollectionBankId());
      String receivables_orgname = ""; // 收款单位名称
      String receivables_bank_acc = ""; // 收款单位归集银行账号
      String receivables_bank_name = ""; // 收款单位归集银行名称
      if (banklist.size() > 0) {
        for (int i = 0; i < banklist.size(); i++) {
          Object obj[] = null;
          obj = (Object[]) banklist.get(0);
          if(obj[0] != null){
            receivables_orgname=obj[0].toString();
          }
          if(obj[2] != null){
            receivables_bank_acc=obj[2].toString();
          }
          if(obj[1] != null){
            receivables_bank_name=obj[1].toString();
          }
        }
      }  
      monthpayJYAF.setOrgid(org.getId().toString());
      monthpayJYAF.setPrintDate(securityInfo.getUserInfo().getBizDate());
      monthpayJYAF.setNoteNum(dto.getDocNum());
      monthpayJYAF.setReceivables_orgname(receivables_orgname);
      monthpayJYAF.setReceivables_bank_acc(receivables_bank_acc);
      monthpayJYAF.setReceivables_bank_name(receivables_bank_name);
      if(org.getOrgInfo().getReserveaB()!=null && !org.getOrgInfo().getReserveaB().equals("")){
        monthpayJYAF.setPayment_orgname(org.getOrgInfo().getReserveaB());
      }else{
        monthpayJYAF.setPayment_orgname(org.getOrgInfo().getName());
      }
//      monthpayJYAF.setPayment_bank_acc(org.getOrgInfo().getPayBank().getAccountNum());
//      monthpayJYAF.setPayment_bank_name(org.getOrgInfo().getPayBank().getName());
      monthpayJYAF.setPayment_bank_acc(dto.getPayment_bank_acc());
      monthpayJYAF.setPayment_bank_name(dto.getPayment_bank_name());
      monthpayJYAF.setSumPay(new BigDecimal(dto.getPay()));
      monthpayJYAF.setPayKind(dto.getPayKind());
      monthpayJYAF.setPayWay(dto.getPayWay());
      monthpayJYAF.setPersonCounts(new Integer(dto.getPersonCounts()));
      monthpayJYAF.setPayMonth(dto.getStartPayMonth()+"-"+dto.getPayMonth());
      request.setAttribute("monthpayJYAF", monthpayJYAF);
      request.setAttribute("monthpayURL", "orgaddpayTbShowAC.do");
      request.setAttribute("seq_aa300", monthpayBS.getSeq_aa300());
    }catch(BusinessException b){
      messages=new ActionMessages();
      messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(b.getMessage(),
          false));
      saveErrors(request, messages);
    }
    return mapping.findForward("org_tdpz_cell");
  }
  public ActionForward reupdate(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
    ActionMessages messages =null;
    try{
    IdAF f = (IdAF)form;
    SecurityInfo securityInfo=(SecurityInfo)request.getSession().getAttribute("SecurityInfo");
    IOrgaddpayBS orgaddpayBS = (IOrgaddpayBS) BSUtils
    .getBusinessService("orgaddpayBS", this, mapping.getModuleConfig());
    MonthpayJYAF monthpayJYAF = new MonthpayJYAF();
    monthpayJYAF=orgaddpayBS.findPayInfo(f.getId().toString());
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
