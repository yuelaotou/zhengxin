package org.xpup.hafmis.syscollection.paymng.orgoverpay.action;


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
import org.xpup.hafmis.common.form.IdAF;
import org.xpup.hafmis.orgstrct.dto.OfficeDto;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.syscollection.common.domain.entity.Org;
import org.xpup.hafmis.syscollection.common.domain.entity.OrgExcessPayment;
import org.xpup.hafmis.syscollection.paymng.orgoverpay.bsinterface.IOrgoverpayBS;
import org.xpup.hafmis.syscollection.paymng.orgoverpay.form.OrgoverpayAF;


public class OrgoverpayMaintainAC extends LookupDispatchAction{
  public static final String PAGINATION_KEY = "org.xpup.hafmis.syscollection.paymng.orgoverpay.action.OrgoverpayMaintainAC"; 
  protected Map getKeyMethodMap() {
    Map map = new HashMap();
   
    map.put("button.update", "update");
    map.put("button.delete", "delete");  
    map.put("button.print", "printReport");  
   
    return map;
  }



  
  public ActionForward update(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
    IdAF idaf=(IdAF)form;
    SecurityInfo securityInfo = (SecurityInfo) request.getSession().getAttribute("SecurityInfo");
    IOrgoverpayBS orgoverpayBS = (IOrgoverpayBS) BSUtils
    .getBusinessService("orgoverpayBS", this, mapping.getModuleConfig());   
//    String paginationKey = this.getPaginationKey();
//    Pagination pagination= (Pagination) request.getSession().getAttribute(paginationKey);
    OrgExcessPayment orgExcessPayment=orgoverpayBS.updateOrgoverpay(idaf.getId().toString(),securityInfo);
    // 付云峰修改：用type来判断是否为修改进入jsp
    request.setAttribute("type", "1");
    request.setAttribute("orgExcessPayment", orgExcessPayment);
    request.getSession().setAttribute("updateoverpay", "update");
    return mapping.findForward("orgoverpayTaShowAC");
  }

  
  public ActionForward delete(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
    ActionMessages messages =null;
    try{
      IdAF idaf=(IdAF)form;
      String id=idaf.getId().toString();
      SecurityInfo securityInfo = (SecurityInfo) request.getSession().getAttribute("SecurityInfo");
      IOrgoverpayBS orgoverpayBS = (IOrgoverpayBS) BSUtils
      .getBusinessService("orgoverpayBS", this, mapping.getModuleConfig());   
//      String paginationKey = this.getPaginationKey();
//      Pagination pagination= (Pagination) request.getSession().getAttribute(paginationKey);
      
      List list=orgoverpayBS.querytest(id);
      
      if(list.size()==0){
        messages=new ActionMessages();
        messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("删除失败,并发操作，数据已被删除！",
            false));
        saveErrors(request, messages);
      }else{
      orgoverpayBS.deleteOrgoverpay(id,securityInfo);
      }
      saveErrors(request, messages);
 
      
    }catch(BusinessException bex){
      messages=new ActionMessages();
      messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("删除失败！",
          false));
      saveErrors(request, messages);
    }
   
    return mapping.findForward("orgoverpayTbShowAC");
  }

  public ActionForward printReport(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
    IdAF idaf=(IdAF)form;
    IOrgoverpayBS orgoverpayBS = (IOrgoverpayBS) BSUtils
    .getBusinessService("orgoverpayBS", this, mapping.getModuleConfig());   
    OrgoverpayAF  orgoverpayAF=orgoverpayBS.printReport(idaf.getId().toString());
    SecurityInfo securityInfo=(SecurityInfo)request.getSession().getAttribute("SecurityInfo");
    String bizDate=securityInfo.getUserInfo().getBizDate();
    orgoverpayAF.setBizDate(bizDate);
    // 付云峰修改：解决挂帐原因不添时显示null问题
    if (orgoverpayAF.getReason()==null) {
      orgoverpayAF.setReason("");
    }
    Org org=orgoverpayBS.findOrgInfo(orgoverpayAF.getOrgId(), "2", securityInfo);
    String office="";
    String collbankname="";
    String bankid="";
    String bankname="";
    String str[]=new String[2];
//  List officlist=securityInfo.getAllCenterList();
//  String collBankid=org.getOrgInfo().getCollectionBankId();
//  if(officlist != null){
//    OfficeDto dto=(OfficeDto)officlist.get(0);
//    office=dto.getOfficeName();
//  }
  str= orgoverpayBS.queryOfficeBankNames(orgoverpayAF.getOrgId(), "2", idaf.getId().toString(), "C", securityInfo);
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
    orgoverpayAF.setBankid(bankid);
    orgoverpayAF.setBankname(bankname);
    orgoverpayAF.setOffice(office);
    orgoverpayAF.setCollbankname(collbankname);
    request.setAttribute("ForwardUrl","orgoverpayTbForwardUrlAC.do" );
    request.setAttribute("orgoverpayAF",orgoverpayAF);
    return mapping.findForward("to_print.jsp");
  }
  
  protected String getPaginationKey() {
    return OrgoverpayMaintainAC.PAGINATION_KEY;
 }
  protected String getPagination() {
    return OrgoverpayTbShowAC.PAGINATION_KEY;
 } 
  
  

  
 

 }

