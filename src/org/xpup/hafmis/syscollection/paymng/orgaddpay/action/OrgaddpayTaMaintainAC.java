package org.xpup.hafmis.syscollection.paymng.orgaddpay.action;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
import org.xpup.hafmis.demo.bsinterface.IDemoBS;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.syscollection.common.domain.entity.Org;
import org.xpup.hafmis.syscollection.paymng.monthpay.bsinterface.IMonthpayBS;
import org.xpup.hafmis.syscollection.paymng.monthpay.form.MonthpayJYAF;
import org.xpup.hafmis.syscollection.paymng.orgaddpay.bsinterface.IOrgaddpayBS;
import org.xpup.hafmis.syscollection.paymng.orgaddpay.dto.OrgaddpayHeadPrintDto;
import org.xpup.hafmis.syscollection.paymng.orgaddpay.form.OrgaddpayImportTaAF;
import org.xpup.hafmis.syscollection.paymng.orgaddpay.form.OrgaddpayTaAF;


public class OrgaddpayTaMaintainAC extends LookupDispatchAction{
  public static final String PAGINATION_KEY = "org.xpup.hafmis.syscollection.paymng.orgaddpay.action.OrgaddpayTaShowAC";
  protected Map getKeyMethodMap() {
    Map map = new HashMap();
    map.put("button.pickup.data", "pickupdata");
    map.put("button.exports", "exports");
    map.put("button.imports", "imports");
    map.put("button.delete", "delete");
    map.put("button.deleteall", "deleteall");
    map.put("button.over.addpay", "overAddpay");
    map.put("button.referring.data", "overAddpayCommitData");
    map.put("button.pproval.data", "overAddpayCancelData");
    return map;
  }

  public ActionForward exports(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
    ActionMessages messages =null;
    try{
      IOrgaddpayBS orgaddpayBS = (IOrgaddpayBS) BSUtils
      .getBusinessService("orgaddpayBS", this, mapping.getModuleConfig());
      Serializable orgid = request.getParameter("orgId");
      String payMonth = request.getParameter("month");
      String startPayMonth = request.getParameter("startMonth");
      String noteNum = request.getParameter("newNoteNum");
      SecurityInfo securityInfo=(SecurityInfo)request.getSession().getAttribute("SecurityInfo");
      //查询所有list
      List expList=orgaddpayBS.findPaylistBatch(orgid, payMonth,startPayMonth, noteNum, securityInfo);
      if(expList.size()>0)
      {
          messages=new ActionMessages();
          messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("导出成功！",
            false));
          saveErrors(request, messages);
          request.getSession().setAttribute("explist",expList);
          response.sendRedirect(request.getContextPath()+"/ExportServlet?ISCANSHU=false&EXP_NAME=orgaddpay_exp");
          return null;
      }else
      {
        messages=new ActionMessages();
        messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("没有数据！",
            false));
        saveErrors(request, messages);
      }
            
    }catch(BusinessException bex){
      messages=new ActionMessages();
      messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("导出失败！"+bex.getMessage(),
          false));
      saveErrors(request, messages);
    }
    return mapping.findForward("show_orgaddpay");
  }
  public ActionForward imports(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

    String orgid = request.getParameter("orgId");
    String payMonth = request.getParameter("month");
    String startPayMonth = request.getParameter("startMonth");
    String noteNum = request.getParameter("newNoteNum");
    String payWay = request.getParameter("payWays");
    String payKind = request.getParameter("payKinds");
    String payment_orgname = request.getParameter("payment_orgnames");
    OrgaddpayImportTaAF f = new OrgaddpayImportTaAF();
    f.setNoteNum(noteNum);
    f.setOrgid(orgid);
    f.setPayMonth(payMonth);
    f.setPayWay(payWay);
    f.setPayKind(payKind);
    f.setStartPayMonth(startPayMonth);
    f.setPayment_orgname(payment_orgname);
    request.setAttribute("orgaddpayImportTaAF", f);
    return mapping.findForward("orgaddpay_imports");
  }
  public ActionForward delete(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
    ActionMessages messages =null;
    try{
      IdAF f = (IdAF)form;
      String type=request.getParameter("types");
      request.setAttribute("type", type);
      SecurityInfo securityInfo=(SecurityInfo)request.getSession().getAttribute("SecurityInfo");
      IOrgaddpayBS orgaddpayBS = (IOrgaddpayBS) BSUtils
      .getBusinessService("orgaddpayBS", this, mapping.getModuleConfig());
      String headId = request.getParameter("monthPaymentHead.id");
      //刘聃修改：添加判断是否是否已经删除
      boolean flag=orgaddpayBS.findld(f.getId(),headId, securityInfo);
      if(flag){
      orgaddpayBS.deleteOrgaddpayListing(f.getId(), headId, securityInfo);
       }
      else{
      messages=new ActionMessages();
      messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("该业务已经被删除", false));
      saveErrors(request, messages);
      }
    }catch(BusinessException e){
      messages=new ActionMessages();
      messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(e.getMessage(),
          false));
      saveErrors(request, messages);
    }    
    return mapping.findForward("show_orgaddpay");
  }
  public ActionForward deleteall(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
    ActionMessages messages =null;
    try{
      IdAF f = (IdAF)form;
      String type=request.getParameter("types");
      request.setAttribute("type", type);
      SecurityInfo securityInfo=(SecurityInfo)request.getSession().getAttribute("SecurityInfo");
      IOrgaddpayBS orgaddpayBS = (IOrgaddpayBS) BSUtils
      .getBusinessService("orgaddpayBS", this, mapping.getModuleConfig());
      HttpSession session=request.getSession(false);
      Pagination pagination = (Pagination)session.getAttribute(PAGINATION_KEY);
      List list=(List)pagination.getQueryCriterions().get("pageList");  
      String headId = request.getParameter("monthPaymentHead.id");
      //刘聃修改：添加判断是否是否已经删除
      OrgaddpayTaAF orgaddpayTaAF = new OrgaddpayTaAF();
      orgaddpayTaAF=orgaddpayBS.findOrgaddpay(pagination,securityInfo);
      List list1=orgaddpayTaAF.getList();
      if(list1.size()!=0){
      orgaddpayBS.deleteAllOrgaddpayListing(list, headId, securityInfo);
       }
      else{
        messages=new ActionMessages();
        messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("该业务已经被删除", false));
        saveErrors(request, messages);
       }
     }catch(BusinessException e){
      messages=new ActionMessages();
      messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(e.getMessage(),
          false));
      saveErrors(request, messages);
    }    
    return mapping.findForward("show_orgaddpay");
  }
  public ActionForward overAddpay(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
    ActionMessages messages =null;
    try{
      IdAF f = (IdAF)form;
      String type=request.getParameter("types");
      request.setAttribute("type", type);
      SecurityInfo securityInfo=(SecurityInfo)request.getSession().getAttribute("SecurityInfo");
      IOrgaddpayBS orgaddpayBS = (IOrgaddpayBS) BSUtils
      .getBusinessService("orgaddpayBS", this, mapping.getModuleConfig());
      String headId = request.getParameter("monthPaymentHead.id");
      String payHeadId = request.getParameter("monthPaymentHead_paymentHead_id");
      String noteNum = request.getParameter("newNoteNum");
      String report = request.getParameter("report");  
      String payWay = request.getParameter("payWays");
      String payKind = request.getParameter("payKinds");
      String payBankname = request.getParameter("payment_bank_names");
      String payBanknameAcc = request.getParameter("payment_bank_accs");
      Pagination pagination = (Pagination) request.getSession().getAttribute(
          OrgaddpayTaShowAC.PAGINATION_KEY);
      pagination.getQueryCriterions().put("payWay", payWay);
      pagination.getQueryCriterions().put("payKind", payKind);
      pagination.getQueryCriterions().put("payment_bank_name", payBankname);
      pagination.getQueryCriterions().put("payment_bank_acc", payBanknameAcc);
      boolean flag=orgaddpayBS.overAddpaya(headId, securityInfo);
      if(!flag){
          orgaddpayBS.overAddpay(f.getId(), headId,securityInfo,noteNum,pagination);
       }
       else{
          messages=new ActionMessages();
          messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("该业务状态已经为登记状态", false));
          saveErrors(request, messages);
        }

      IMonthpayBS monthpayBS = (IMonthpayBS) BSUtils
      .getBusinessService("monthpayBS", this, mapping.getModuleConfig());
      if(report.equals("print")){
        OrgaddpayHeadPrintDto dto = orgaddpayBS.findOrgaddpayPring(payHeadId);
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
        if(payBankname!=null&&!"".equals(payBankname)){
          monthpayJYAF.setPayment_bank_name(payBankname);
        }else{
          monthpayJYAF.setPayment_bank_name(org.getOrgInfo().getPayBank().getName());
        }
        if(payBanknameAcc!=null&&!"".equals(payBanknameAcc)){
          monthpayJYAF.setPayment_bank_acc(payBanknameAcc);
        }else{
          monthpayJYAF.setPayment_bank_acc(org.getOrgInfo().getPayBank().getAccountNum());
        }
        monthpayJYAF.setSumPay(new BigDecimal(dto.getPay()));
        monthpayJYAF.setPayKind(dto.getPayKind());
        monthpayJYAF.setPayWay(dto.getPayWay());
        monthpayJYAF.setPersonCounts(new Integer(dto.getPersonCounts()));
        request.setAttribute("monthpayJYAF", monthpayJYAF);
        request.setAttribute("monthpayURL", "orgaddpayTaForwardUrlAC.do");
        request.setAttribute("seq_aa300", monthpayBS.getSeq_aa300());
        return mapping.findForward("org_tdpz_cell");
      }
   }catch(Exception e){
      messages=new ActionMessages();
      messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("完成失败！",
          false));
      saveErrors(request, messages);
    }    
    return mapping.findForward("show_orgaddpay");
  }
  public ActionForward overAddpayCommitData(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
    ActionMessages messages =null;
    ActionMessages messages_1 =null;
    try{
      IdAF f = (IdAF)form;
      String type=request.getParameter("types");
      request.setAttribute("type", type);
      SecurityInfo securityInfo=(SecurityInfo)request.getSession().getAttribute("SecurityInfo");
      IOrgaddpayBS orgaddpayBS = (IOrgaddpayBS) BSUtils
      .getBusinessService("orgaddpayBS", this, mapping.getModuleConfig());
      
      String headId = request.getParameter("monthPaymentHead.id");
      orgaddpayBS.commit_Do(headId,securityInfo);
      
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
    ActionMessages messages_1 =null;
    try{
      IdAF f = (IdAF)form;
      String type=request.getParameter("types");
      request.setAttribute("type", type);
      SecurityInfo securityInfo=(SecurityInfo)request.getSession().getAttribute("SecurityInfo");
      IOrgaddpayBS orgaddpayBS = (IOrgaddpayBS) BSUtils
      .getBusinessService("orgaddpayBS", this, mapping.getModuleConfig());
      
      String headId = request.getParameter("monthPaymentHead.id");
      orgaddpayBS.cancel_Do(headId,securityInfo);
      
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
  public ActionForward pickupdata(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
    ActionMessages messages =null;
    try{
      String orgid = request.getParameter("orgId");
      String payMonth = request.getParameter("month");
      String startPayMonth = request.getParameter("startMonth");
      String noteNum = request.getParameter("newNoteNum");
            
      SecurityInfo securityInfo=(SecurityInfo)request.getSession().getAttribute("SecurityInfo");
      Pagination pagination = (Pagination) request.getSession().getAttribute(OrgaddpayTaShowAC.PAGINATION_KEY);
      IOrgaddpayBS orgaddpayBS = (IOrgaddpayBS) BSUtils
      .getBusinessService("orgaddpayBS", this, mapping.getModuleConfig());
      orgaddpayBS.pickupdata(orgid,payMonth,startPayMonth,noteNum,securityInfo,pagination);
    
  }catch(BusinessException exc){
    messages=new ActionMessages();
    messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(exc.getMessage(),
        false));
    saveErrors(request, messages);
  }
  return mapping.findForward("show_orgaddpay");
  }

}
