package org.xpup.hafmis.syscollection.paymng.personaddpay.action;


import java.math.BigDecimal;
import java.util.Date;
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
import org.xpup.hafmis.common.util.BusiTools;
import org.xpup.hafmis.common.util.bizlog.BusiLogConst;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.syscollection.common.domain.entity.AddPayTail;
import org.xpup.hafmis.syscollection.common.domain.entity.EmpAddPay;
import org.xpup.hafmis.syscollection.common.domain.entity.PaymentHead;
import org.xpup.hafmis.syscollection.paymng.personaddpay.bsinterface.IPersonAddPayBS;
import org.xpup.hafmis.syscollection.paymng.personaddpay.form.PersonAddPayAF;
import org.xpup.hafmis.syscommon.domain.entity.HafOperateLog;

public class PersonAddPayTaAddAC extends LookupDispatchAction{
 public static final String PAGINATION_KEY = "org.xpup.hafmis.syscollection.paymng.personaddpay.action.PersonAddPayTaShowAC";
  
  protected Map getKeyMethodMap() {
    // TODO Auto-generated method stub
    Map map=new HashMap();
    map.put("button.sure", "save");
    map.put("button.back", "cancle");
    map.put("button.update", "update");  
    
    return map;
  }
 
  public ActionForward save(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
    ActionMessages messages =null;
    try{
      messages=new ActionMessages();
      if(!isTokenValid(request))
      {
        messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("请不要重复提交！",false));         
        saveErrors(request, messages);
      }else{
        
        SecurityInfo securityInfo = (SecurityInfo) request.getSession().getAttribute("SecurityInfo");
        PersonAddPayAF personAddPayAF=(PersonAddPayAF)form;
        String noteNum=(String)request.getSession().getAttribute("noteNum");
        resetToken(request);
        Pagination pagination= (Pagination) request.getSession().getAttribute(PAGINATION_KEY);
        String orgId=(String)pagination.getQueryCriterions().get("orgId");
        String empId=personAddPayAF.getEmpId();
        String nextInsert=(String)request.getParameter("insertnext");
        IPersonAddPayBS personAddPayBS = (IPersonAddPayBS) BSUtils
        .getBusinessService("personAddPayBS", this, mapping.getModuleConfig());
        pagination.getQueryCriterions().put("empId", empId);
        AddPayTail addPayTail=personAddPayAF.getAddPayTail();  
        String addPayType = personAddPayAF.getAddPayType();//挂账类型
        String beginMonth=addPayTail.getBeginMonth();
        String endMonth=addPayTail.getEndMonth();
        String orgAddPaySum=addPayTail.getOrgAddPaySum().toString();
        String empAddPaySum=addPayTail.getEmpAddPaySum().toString();
        String reason=addPayTail.getAddReason();
        pagination.getQueryCriterions().put("beginMonth", beginMonth);
        pagination.getQueryCriterions().put("endMonth", endMonth);
        pagination.getQueryCriterions().put("orgAddPaySum", orgAddPaySum);
        pagination.getQueryCriterions().put("empAddPaySum", empAddPaySum);
        pagination.getQueryCriterions().put("reason", reason);
        pagination.getQueryCriterions().put("nextInsert", nextInsert);
        pagination.getQueryCriterions().put("noteNum", noteNum);
        pagination.getQueryCriterions().put("addPayType", addPayType);
        pagination.getQueryCriterions().put("payment_orgname", personAddPayAF.getPayment_orgname());
        
        pagination.getQueryCriterions().put("personAddPayAF", personAddPayAF);
        boolean isRepeat=personAddPayBS.queryAddPayTailEmp(pagination,securityInfo);
        if(isRepeat==false){
        messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("添加失败，该职工正在办理补缴！", false));  
        saveErrors(request, messages);
        personAddPayAF.setType("add");
        return mapping.findForward("to_personAddPay_add.jsp");
       
        }else{
        EmpAddPay empAddPay=personAddPayBS.findPersonAddPay(pagination,securityInfo);
        String bizId=empAddPay.getId().toString();
        pagination.getQueryCriterions().put("bizId", bizId);
        personAddPayAF.setType("add");
        if(nextInsert.equals("next")){
          personAddPayAF.reset();
          return mapping.findForward("to_personAddPay_add.jsp");
        }
        messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("添加成功！", false));          
        saveErrors(request, messages);
      }
      }
    }catch(BusinessException bex){
      bex.printStackTrace();
      messages=new ActionMessages();
      messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("添加失败！",   false));     
      saveErrors(request, messages);
      return mapping.findForward("to_personAddPay_add.jsp");
    } 
    return mapping.findForward("personAddPayTaShowAC");
  }
  
  public ActionForward update(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
    ActionMessages messages =null;
    try{
      
      PersonAddPayAF  f=(PersonAddPayAF)request.getSession().getAttribute("personAddPayAF");
      PersonAddPayAF personAddPayAF=(PersonAddPayAF)form;
      AddPayTail addPayTail=personAddPayAF.getAddPayTail();
      SecurityInfo securityInfo = (SecurityInfo) request.getSession().getAttribute("SecurityInfo");
      IPersonAddPayBS personAddPayBS = (IPersonAddPayBS) BSUtils
     .getBusinessService("personAddPayBS", this, mapping.getModuleConfig());
      Pagination pagination= (Pagination) request.getSession().getAttribute(PAGINATION_KEY);
      String empId= f.getEmpId();    
      String paymentId=(String)pagination.getQueryCriterions().get("paymentHeadId");
      String orgId=(String)pagination.getQueryCriterions().get("orgId");
      Integer paymentHeadId=new Integer(paymentId);
      personAddPayBS.deleteAddPayTail(new Integer (empId), paymentHeadId);
      String beginMonth=personAddPayAF.getAddPayTail().getBeginMonth();
      String endMonth=personAddPayAF.getAddPayTail().getEndMonth();
      int m=BusiTools.monthInterval(beginMonth, endMonth)+1;
      BigDecimal month=new BigDecimal(0.00);
      if(m!=0){
        month=new BigDecimal(new Integer(m).toString());
      }
    
      BigDecimal sum=new BigDecimal(0.00);
      BigDecimal orgPaySum=new BigDecimal(0.00);
      orgPaySum=addPayTail.getOrgAddPaySum();
      BigDecimal empPaySum=new BigDecimal(0.00);
      empPaySum=addPayTail.getEmpAddPaySum();
      BigDecimal orgmonthpay=new BigDecimal(0.00);
      BigDecimal empmonthpay=new BigDecimal(0.00);
      //sum=personAddPayAF.getAddPayTail().getOrgAddPaySum().add(personAddPayAF.getAddPayTail().getEmpAddPaySum());
      EmpAddPay empAddPay=personAddPayBS.queryHead(paymentHeadId); 
      int p=BusiTools.monthInterval(beginMonth, endMonth);
      if(p==0)
      {
        AddPayTail addPay=new AddPayTail();
        addPay.setOrgAddMoney(addPayTail.getOrgAddPaySum());
        addPay.setEmpAddMoney(addPayTail.getEmpAddPaySum());
        addPay.setPaymentHead(empAddPay);
        addPay.setEmpId(new Integer(empId));
        addPay.setAddMonht(beginMonth);
        addPay.setAddReason(addPayTail.getAddReason());
        addPay.setReserveaC(personAddPayAF.getAddPayType());//yqf
        personAddPayBS.addPersonAddPayTail(addPay);
      }
      else{    
        BigDecimal orgtemp=new BigDecimal(0.00);
        orgtemp=new BigDecimal(orgPaySum.doubleValue()%month.intValue());
        BigDecimal emptemp=new BigDecimal(0.00);
        emptemp=new BigDecimal(empPaySum.doubleValue()%month.intValue());
        orgmonthpay=(orgPaySum.subtract(orgtemp)).divide(month,2,BigDecimal.ROUND_HALF_UP);
        empmonthpay=(empPaySum.subtract(emptemp)).divide(month,2,BigDecimal.ROUND_HALF_UP);
         for(int i=0;i<m;i++){
        
         AddPayTail addPay=new AddPayTail();
//         orgmonthpay=orgPaySum.divide(month,2,BigDecimal.ROUND_HALF_UP);
//         empmonthpay=empPaySum.divide(month,2,BigDecimal.ROUND_HALF_UP);
//         addPay.setOrgAddMoney(orgmonthpay);
//         addPay.setEmpAddMoney(empmonthpay);
         addPay.setPaymentHead(empAddPay);
         addPay.setEmpId(new Integer(empId));
         if(i!=m-1){
           addPay.setOrgAddMoney(orgmonthpay);
           addPay.setEmpAddMoney(empmonthpay);
           
         }else{
           addPay.setOrgAddMoney(orgmonthpay.add(orgtemp));
           addPay.setEmpAddMoney(empmonthpay.add(emptemp));
         }
         addPay.setAddMonht(beginMonth);
         addPay.setAddReason(addPayTail.getAddReason());
         addPay.setReserveaC(personAddPayAF.getAddPayType());//yqf
         personAddPayBS.addPersonAddPayTail(addPay);
         beginMonth=BusiTools.addMonth(beginMonth, 1);
        
       }
      }
      sum = personAddPayBS.getAddPayMoney(paymentHeadId);
      empAddPay.setPayMoney(sum);
      personAddPayBS.updateHead(empAddPay);
      HafOperateLog  hafOperateLog =new HafOperateLog();
      hafOperateLog.setOpSys(new Integer(BusiLogConst.OP_SYSTEM_TYPE_COLLECTION));
      hafOperateLog.setOpModel(new Integer(BusiLogConst.OP_MODE_PAYMENTMANAGE_ADDPERSONPAY_DO).toString());
      hafOperateLog.setOpButton(Integer.toString(BusiLogConst.BIZLOG_ACTION_MODIFY));
      hafOperateLog.setOpBizId(paymentHeadId);
      hafOperateLog.setOpIp(securityInfo.getUserIp());
      hafOperateLog.setOperator(securityInfo.getUserName());
      hafOperateLog.setOpTime(new Date());
      hafOperateLog.setOrgId(new Integer(orgId));
      personAddPayBS.insertHafOperateLog(hafOperateLog);
      messages=new ActionMessages();
      messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("修改成功！", false));
       
      saveErrors(request, messages);
     }catch(Exception bex){
      bex.printStackTrace();
      messages=new ActionMessages();
      messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("修改失败！", false));      
      saveErrors(request, messages);
      return mapping.findForward("to_personAddPay_add.jsp");  
    }
    return mapping.findForward("personAddPayTaShowAC");
  }
  public ActionForward cancle(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
  
    PersonAddPayAF personAddPayAF=(PersonAddPayAF)form;
    SecurityInfo securityInfo = (SecurityInfo) request.getSession().getAttribute("SecurityInfo");
    String sign=personAddPayAF.getType(); 
    Pagination pagination= (Pagination) request.getSession().getAttribute(PAGINATION_KEY);
    String bizId=(String)pagination.getQueryCriterions().get("bizId");  
    String orgId=(String)pagination.getQueryCriterions().get("orgId");
    IPersonAddPayBS personAddPayBS = (IPersonAddPayBS) BSUtils
    .getBusinessService("personAddPayBS", this, mapping.getModuleConfig());
    if(sign.endsWith("add")){    
      HafOperateLog  hafOperateLog =new HafOperateLog();
      hafOperateLog.setOpSys(new Integer(BusiLogConst.OP_SYSTEM_TYPE_COLLECTION));
      hafOperateLog.setOpModel(new Integer(BusiLogConst.OP_MODE_PAYMENTMANAGE_ADDPERSONPAY_DO).toString());
      hafOperateLog.setOpButton(Integer.toString(BusiLogConst.BIZLOG_ACTION_MODIFY));
      if(bizId!=null&&!bizId.endsWith(""))
      {
      hafOperateLog.setOpBizId(new Integer(bizId));
      }
      hafOperateLog.setOpIp(securityInfo.getUserIp());
      hafOperateLog.setOperator(securityInfo.getUserName());
      hafOperateLog.setOpTime(new Date());
      if(orgId!=null&&!orgId.equals(""))
      {
      hafOperateLog.setOrgId(new Integer(orgId));
      }
      personAddPayBS.insertHafOperateLog(hafOperateLog);
    }    
    return mapping.findForward("personAddPayTaShowAC");
  }
}
