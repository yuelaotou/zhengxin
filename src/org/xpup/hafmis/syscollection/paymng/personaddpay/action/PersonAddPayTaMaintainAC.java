package org.xpup.hafmis.syscollection.paymng.personaddpay.action;

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
import org.xpup.hafmis.common.util.BusiConst;
import org.xpup.hafmis.common.util.BusiTools;
import org.xpup.hafmis.orgstrct.dto.OfficeDto;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.syscollection.common.domain.entity.AddPayTail;
import org.xpup.hafmis.syscollection.common.domain.entity.Emp;
import org.xpup.hafmis.syscollection.common.domain.entity.EmpAddPay;
import org.xpup.hafmis.syscollection.common.domain.entity.Org;
import org.xpup.hafmis.syscollection.paymng.monthpay.bsinterface.IMonthpayBS;
import org.xpup.hafmis.syscollection.paymng.orgaddpay.action.OrgaddpayTaShowAC;
import org.xpup.hafmis.syscollection.paymng.personaddpay.bsinterface.IPersonAddPayBS;
import org.xpup.hafmis.syscollection.paymng.personaddpay.form.PersonAddPayAF;
import org.xpup.hafmis.syscollection.paymng.personaddpay.form.PersonAddPayImportAF;
import org.xpup.hafmis.syscommon.domain.entity.EmpInfo;

public class PersonAddPayTaMaintainAC extends LookupDispatchAction{
  public static final String PAGINATION_KEY = "org.xpup.hafmis.syscollection.paymng.personaddpay.action.PersonAddPayTaShowAC";
  
  protected Map getKeyMethodMap() {
    Map map = new HashMap();
    map.put("button.add", "add");
    map.put("button.update", "update");
    map.put("button.delete", "delete");  
    map.put("button.deleteall", "deleteAll"); 
    map.put("button.exports", "personAddPayExports"); 
    map.put("button.imports", "personAddPayimports"); 
    map.put("button.over.addpay", "overAddPay");
    map.put("button.referring.data", "referring");
    map.put("button.pproval.data", "pproval");
    map.put("button.pickup.data", "pickup");
    return map;
  }

  public ActionForward add(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
    saveToken(request);
    PersonAddPayAF personAddPayAF=new PersonAddPayAF();
    personAddPayAF.setType("add");
    String noteNum=request.getParameter("noteNumber");
    String orgId=request.getParameter("unitNumber");
    String payWay=request.getParameter("p_Way");
    String payKind=request.getParameter("p_Kind");
    String payment_orgname=request.getParameter("payment_orgnames");
    personAddPayAF.setPayWay(payWay);
    personAddPayAF.setPayKind(payKind);
    personAddPayAF.setOrgId(orgId);
    personAddPayAF.setPayment_orgname(payment_orgname);
    Map addPayTypeMap = new HashMap();
    addPayTypeMap = BusiTools.listBusiProperty(BusiConst.ADDPAYTYPE);
    personAddPayAF.setAddPayTypeMap(addPayTypeMap);
    request.getSession().setAttribute("noteNum", noteNum);
    request.setAttribute("personAddPayAF", personAddPayAF);
    return mapping.findForward("to_personAddPay_add.jsp");
  }

  
  public ActionForward update(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
   
    IdAF idaf=(IdAF)form;
   
    PersonAddPayAF personAddPayAF=new PersonAddPayAF();
    IPersonAddPayBS personAddPayBS = (IPersonAddPayBS) BSUtils
    .getBusinessService("personAddPayBS", this, mapping.getModuleConfig());
    String paginationKey = this.getPaginationKey();
    Pagination pagination= (Pagination) request.getSession().getAttribute(paginationKey);   
    String paymentId=(String)pagination.getQueryCriterions().get("paymentHeadId");
    String orgid=(String)pagination.getQueryCriterions().get("orgId");
    Integer orgId=new Integer(orgid);
    Integer paymentHeadId=new Integer(paymentId);
    List list=personAddPayBS.findEmpById(paymentHeadId,new Integer(idaf.getId().toString()));
   
    AddPayTail addPayTail=(AddPayTail)list.get(0);
    Integer empId=new Integer(addPayTail.getEmpId().toString());
  
    Emp emps=personAddPayBS.findEmpInfo(empId.toString(), orgId.toString()); 
    EmpInfo empInfo=personAddPayBS.findEmpInfoById(new Integer(emps.getEmpInfo().getId().toString()));
  
    Map sexMap=BusiTools.listBusiProperty(BusiConst.SEX);
    personAddPayAF.setSexMap(sexMap);
    personAddPayAF.setSex(BusiTools.getBusiValue(Integer.parseInt(empInfo.getSex().toString()), BusiConst.SEX));
    personAddPayAF.setType("update"); 
    personAddPayAF.setAddPayTail(addPayTail);
    personAddPayAF.getAddPayTail().getEmp().setEmpId(empId);
    personAddPayAF.setEmpId(BusiTools.convertSixNumber(empId.toString()));
   
    personAddPayAF.getAddPayTail().getEmp().getEmpInfo().setId(empInfo.getId());  
    personAddPayAF.getAddPayTail().getEmp().getEmpInfo().setName(empInfo.getName());

    personAddPayAF.getAddPayTail().getEmp().getEmpInfo().setTEMP_cardNum(BusiTools.getBusiValue(Integer.parseInt(empInfo.getCardKind().toString()), BusiConst.DOCUMENTSSTATE));
    personAddPayAF.getAddPayTail().getEmp().getEmpInfo().setCardNum(empInfo.getCardNum());
    personAddPayAF.getAddPayTail().getEmp().getEmpInfo().setBirthday(empInfo.getBirthday());
    personAddPayAF.getAddPayTail().setSex(BusiTools.getBusiValue((empInfo.getSex().intValue()), BusiConst.SEX));
    personAddPayAF.getAddPayTail().getEmp().getEmpInfo().setBirthday(empInfo.getBirthday());
    List empList=personAddPayBS.findEmpById_lg(empId, orgId);   
    BigDecimal orgPay=new BigDecimal(0.00);
    BigDecimal empPay=new BigDecimal(0.00);
    BigDecimal paySum=new BigDecimal(0.00);
    if(empList.size()>0){
    Emp emp=(Emp)empList.get(0);
    personAddPayAF.getAddPayTail().getEmp().setSalaryBase(emp.getSalaryBase());
    for(int i=0;i<list.size();i++){
      Emp e=(Emp)empList.get(i);
      orgPay=orgPay.add(e.getOrgPay());
      empPay=empPay.add(e.getEmpPay());
      paySum=orgPay.add(empPay);
      
    }
    }
    personAddPayAF.getAddPayTail().getEmp().setOrgPay(orgPay);
    personAddPayAF.getAddPayTail().getEmp().setEmpPay(empPay);
    personAddPayAF.getAddPayTail().getEmp().setPaySum(paySum); 
    personAddPayAF.setAddPayType(addPayTail.getReserveaC());
    Map addPayTypeMap = new HashMap();
    addPayTypeMap = BusiTools.listBusiProperty(BusiConst.ADDPAYTYPE);
    personAddPayAF.setAddPayTypeMap(addPayTypeMap);
    request.getSession().setAttribute("personAddPayAF", personAddPayAF); 
    return mapping.findForward("to_personAddPay_add.jsp");
  }

  
  public ActionForward delete(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
    ActionMessages messages =null;
    try{
      IdAF idaf=(IdAF)form;
    
      SecurityInfo securityInfo = (SecurityInfo) request.getSession().getAttribute("SecurityInfo");
      IPersonAddPayBS personAddPayBS = (IPersonAddPayBS) BSUtils
      .getBusinessService("personAddPayBS", this, mapping.getModuleConfig()); 
      //bit修改，防止数据并发操作被重复删除==========================================================================
      String id=idaf.getId().toString();
      List list=new ArrayList();
      list=personAddPayBS.querytest(id);
     //System.out.print(new Integer(list.size()).toString()+"=======================");
      if(list.size()==0){
        messages=new ActionMessages();
        messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("删除失败,发生并发操作，数据已经删除！",
            false));
        saveErrors(request, messages);
      }else{
     
      //=======================================================================================================
      
      String paginationKey = this.getPaginationKey();
      Pagination pagination= (Pagination) request.getSession().getAttribute(paginationKey);    
      String paymentId=(String)pagination.getQueryCriterions().get("paymentHeadId");
      personAddPayBS.deleteEmpaddpayList(idaf.getId(), paymentId,securityInfo );
      messages=new ActionMessages();
      messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("删除成功！",
          false));
      saveErrors(request, messages);
      }
    }catch(BusinessException bex){
      messages=new ActionMessages();
      messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("删除失败！",
          false));
      saveErrors(request, messages);
    }
   
    return mapping.findForward("personAddPayTaShowAC");
  }

  public ActionForward deleteAll(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
    
    ActionMessages messages =null;
    try{
      messages=new ActionMessages();
      if(!isTokenValid(request)){
        messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("请不要灌水！", false));    
        saveErrors(request, messages);
      }
      else
      {
        SecurityInfo securityInfo = (SecurityInfo) request.getSession().getAttribute("SecurityInfo");
        IPersonAddPayBS personAddPayBS = (IPersonAddPayBS) BSUtils
        .getBusinessService("personAddPayBS", this, mapping.getModuleConfig());
        PersonAddPayAF personAddPayAF=(PersonAddPayAF)request.getSession().getAttribute("personAddPayAF");
        String paginationKey = getPaginationKey();
        Pagination pagination= (Pagination) request.getSession().getAttribute(paginationKey);
        String paymentHeadid=(String)pagination.getQueryCriterions().get("paymentHeadId"); 
        Integer paymentHeadId=new Integer(paymentHeadid);
        List list=new ArrayList();
        list=personAddPayBS.querydeleteall(pagination);
        if(list.size()==0){
          messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("删除失败，并发操作，数据已经删除！", false));           
          saveErrors(request, messages);
        }else{
        personAddPayBS.deleteAddpayDo(paymentHeadId,securityInfo);
        
        messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("删除成功！", false));           
        saveErrors(request, messages);
        }
      }
      
    }catch(BusinessException bex){
      bex.printStackTrace();
      messages=new ActionMessages();
      messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("删除失败！", false));     
      saveErrors(request, messages);
    } 
    return mapping.findForward("personAddPayTaShowAC");
    }
  
  protected String getPaginationKey() {
    return PersonAddPayTaShowAC.PAGINATION_KEY;
 }
  
  public ActionForward personAddPayExports(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
    ActionMessages messages =null;
    try{
      SecurityInfo securityInfo = (SecurityInfo) request.getSession().getAttribute("SecurityInfo");
      HttpSession session=request.getSession(false);
      Pagination pagination = (Pagination)session.getAttribute(PAGINATION_KEY);
      IPersonAddPayBS personAddPayBS = (IPersonAddPayBS) BSUtils
      .getBusinessService("personAddPayBS", this, mapping.getModuleConfig());
      String orgName="";
      String noteNum="";
      orgName=request.getParameter("unitNumber");
      noteNum=request.getParameter("noteNumber");
      pagination.getQueryCriterions().put("unitName", orgName);
      pagination.getQueryCriterions().put("docNumber", noteNum);
      List expList=personAddPayBS.findPaylist(pagination,securityInfo);
      if(expList.size()>0)
      {
          messages=new ActionMessages();
          messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("导出成功！",false));    
          saveErrors(request, messages);
          request.getSession().setAttribute("explist",expList);
          response.sendRedirect(request.getContextPath()+"/ExportServlet?ISCANSHU=false&EXP_NAME=empaddpay_exp");         
      }else
      {
        messages=new ActionMessages();
        messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("没有数据！",  false));     
        saveErrors(request, messages);
      }
        return null;
    }catch(BusinessException bex){
      messages=new ActionMessages();
      messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("导出失败！",  false));     
      saveErrors(request, messages);
    }
    return mapping.findForward("personAddPayTaShowAC");
  }
  public ActionForward personAddPayimports(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
 
    HttpSession session=request.getSession(false);
    Pagination pagination = (Pagination)session.getAttribute(PAGINATION_KEY);
    String orgId=(String)pagination.getQueryCriterions().get("orgId");
    String orgName=(String)pagination.getQueryCriterions().get("unitName");
    String noteNum=(String)pagination.getQueryCriterions().get("docNumber");
    String payment_orgname=(String)request.getParameter("payment_orgnames");
    String payWay = request.getParameter("p_Way");
    String payKind = request.getParameter("p_Kind");
    PersonAddPayImportAF f = new PersonAddPayImportAF();
    f.setOrgId(orgId);
    f.setOrgName(orgName);
    f.setNoteNum(noteNum);
    f.setPayKind(payKind);
    f.setPayWay(payWay);
    f.setPayment_orgname(payment_orgname);
    request.setAttribute("personAddPayImportAF", f); 
    return mapping.findForward("to_personaddpay_imports");
  }
 
  
  public ActionForward overAddPay(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
      ActionMessages messages = null;    
    try{
    IdAF f = (IdAF)form;
    SecurityInfo securityInfo = (SecurityInfo) request.getSession().getAttribute("SecurityInfo");
    String printReport=(String)request.getParameter("printReport"); 
    IPersonAddPayBS personAddPayBS = (IPersonAddPayBS) BSUtils
    .getBusinessService("personAddPayBS", this, mapping.getModuleConfig());
    HttpSession session=request.getSession(false);
    Pagination pagination = (Pagination)session.getAttribute(PAGINATION_KEY);
    String id=(String)pagination.getQueryCriterions().get("paymentHeadId");
    String orgid=(String)pagination.getQueryCriterions().get("orgId");
    String docNumber=request.getParameter("noteNumber"); 

    String payWay = request.getParameter("p_Way");
    String payKind = request.getParameter("p_Kind");
    String p_bank_name = request.getParameter("p_bank_name");
    String p_bank_acc = request.getParameter("p_bank_acc");
    pagination.getQueryCriterions().put("p_bank_acc", p_bank_acc);
    pagination.getQueryCriterions().put("p_bank_name", p_bank_name);
    IMonthpayBS monthpayBS = (IMonthpayBS) BSUtils
    .getBusinessService("monthpayBS", this, mapping.getModuleConfig());
   
//  刘聃修改：添加判断是否为登记状态
    boolean flag=personAddPayBS.overAddpaya(id, securityInfo);
    if(flag){
    personAddPayBS.overAddpay(f.getId(), id, securityInfo,docNumber,pagination);
      if(printReport.equals("isPrint")){
        EmpAddPay empAddPay=personAddPayBS.findEmpAddPayInfo(id);
        BigDecimal payMoney = personAddPayBS.getOverPay(empAddPay.getNoteNum());
        PersonAddPayAF personAddPayAF=(PersonAddPayAF) request.getSession().getAttribute("f");
        Org org=personAddPayBS.findOrgInfo(orgid.toString(), "2", securityInfo);
        String office="";
        String collbankname="";
        String bankid="";
        String bankname="";
        String userName="";
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

       personAddPayAF.setPaySum(empAddPay.getPayMoney().add(payMoney));
       personAddPayAF.setBizDate(securityInfo.getUserInfo().getBizDate());
       
       String name = monthpayBS.queryMakerPara();

       if (name.equals("1")) {
         userName = securityInfo.getUserName();
       } else {
         userName = securityInfo.getRealName();
       }
        request.setAttribute("monthpayURL", "personAddPayTaShowAC.do");
        request.getSession().setAttribute("f", personAddPayAF);
        request.getSession().setAttribute("userName", userName);
        request.setAttribute("seq_aa300", monthpayBS.getSeq_aa300());
        return mapping.findForward("emp_tdpz_cell");
      }
    }
    else{
      messages=new ActionMessages();
      messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("该补缴已经登记！",  false));     
      saveErrors(request, messages);
    }
    }catch(Exception e){
      e.printStackTrace();
    }    
      return mapping.findForward("personAddPayTaShowAC");
  }
  
  /**
   * Description 个人补缴_修改记录
   * 
   * @author wangy 2008-02-26
   * @param 单位版_办理缴存_提交数据
   * @param mapping
   * @param form
   * @param request
   * @param response
   * @throws Exception
   */
  public ActionForward referring(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
    ActionMessages messages =null;
    try{
      SecurityInfo securityInfo = (SecurityInfo) request.getSession().getAttribute("SecurityInfo");
      IPersonAddPayBS personAddPayBS = (IPersonAddPayBS) BSUtils
      .getBusinessService("personAddPayBS", this, mapping.getModuleConfig());
      String paginationKey = this.getPaginationKey();
      Pagination pagination= (Pagination) request.getSession().getAttribute(paginationKey); 
      String paymentId=(String)pagination.getQueryCriterions().get("paymentHeadId");
      String orgid=(String)pagination.getQueryCriterions().get("orgId");
      personAddPayBS.insertEmpAddPayReferringData(orgid, paymentId, securityInfo);
      messages=new ActionMessages();
      messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("提交数据成功！",
          false));
      saveErrors(request, messages);
    }catch(BusinessException bex){
      messages=new ActionMessages();
      messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(bex.getMessage(),
          false));
      saveErrors(request, messages);
    }catch(Exception e){
      e.printStackTrace();
    }
    return mapping.findForward("personAddPayTaShowAC");
  }
  
  /**
   * Description 个人补缴_修改记录
   * 
   * @author wangy 2008-02-26
   * @param 单位版_办理缴存_撤销提交数据
   * @param mapping
   * @param form
   * @param request
   * @param response
   * @throws Exception
   */
  public ActionForward pproval(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
    ActionMessages messages =null;
    try{
      SecurityInfo securityInfo = (SecurityInfo) request.getSession().getAttribute("SecurityInfo");
      IPersonAddPayBS personAddPayBS = (IPersonAddPayBS) BSUtils
      .getBusinessService("personAddPayBS", this, mapping.getModuleConfig());
      String paginationKey = this.getPaginationKey();
      Pagination pagination= (Pagination) request.getSession().getAttribute(paginationKey); 
      String paymentId=(String)pagination.getQueryCriterions().get("paymentHeadId");
      String orgid=(String)pagination.getQueryCriterions().get("orgId");
      personAddPayBS.deleteAddpayPproval(orgid, paymentId, securityInfo);
      messages=new ActionMessages();
      messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("撤消提交数据成功！",
          false));
      saveErrors(request, messages);
    }catch(BusinessException bex){
      messages=new ActionMessages();
      messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(bex.getMessage(),
          false));
      saveErrors(request, messages);
    }catch(Exception e){
      e.printStackTrace();
    }
    return mapping.findForward("personAddPayTaShowAC");
  }
  
  /**
   * Description 个人补缴_修改记录
   * 
   * @author wangy 2008-02-26
   * @param 单位版_办理缴存_提取数据
   * @param mapping
   * @param form
   * @param request
   * @param response
   * @throws Exception
   */
  public ActionForward pickup(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
    ActionMessages messages =null;
    try{
      SecurityInfo securityInfo = (SecurityInfo) request.getSession().getAttribute("SecurityInfo");
      IPersonAddPayBS personAddPayBS = (IPersonAddPayBS) BSUtils
      .getBusinessService("personAddPayBS", this, mapping.getModuleConfig());
      String paginationKey = this.getPaginationKey();
      Pagination pagination= (Pagination) request.getSession().getAttribute(paginationKey); 
      String orgid=(String)pagination.getQueryCriterions().get("orgId");
      personAddPayBS.importsAddpayPickup(orgid, securityInfo);
//      messages=new ActionMessages();
//      messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("提取数据成功！",
//          false));
//      saveErrors(request, messages);
    }catch(BusinessException bex){
      messages=new ActionMessages();
      messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(bex.getMessage(),
          false));
      saveErrors(request, messages);
    }catch(Exception e){
      e.printStackTrace();
    }
    return mapping.findForward("personAddPayTaShowAC");
  }
 }

