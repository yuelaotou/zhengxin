package org.xpup.hafmis.syscollection.paymng.orgoverpay.action;

import java.math.BigDecimal;

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
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.syscollection.paymng.orgoverpay.bsinterface.IOrgoverpayBS;
import org.xpup.hafmis.syscollection.paymng.orgoverpay.form.OrgoverpayAF;


public class OrgoverpayConfirmAC extends Action{
public static final String PAGINATION_KEY = "org.xpup.hafmis.syscollection.paymng.orgoverpay.action.OrgoverpayTaShowAC";  
public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception { 
  ActionMessages messages=null;
  String type = "1";
  try{
    SecurityInfo securityInfo = (SecurityInfo) request.getSession().getAttribute("SecurityInfo");
    OrgoverpayAF f=(OrgoverpayAF)form;
    IOrgoverpayBS orgoverpayBS = (IOrgoverpayBS) BSUtils
    .getBusinessService("orgoverpayBS", this, mapping.getModuleConfig());   
     String sign=(String)request.getSession().getAttribute("updateoverpay");
     HttpSession session=request.getSession(false);
     Pagination pagination = (Pagination)session.getAttribute(PAGINATION_KEY);
     String orgId=(String)pagination.getQueryCriterions().get("orgId"); 
  
     if((sign==null)||(sign=="")){
       
       //看是否存在缴存类型为D的并且缴存状态不等于5的数据
       boolean temp_boolean = orgoverpayBS.isOrgoverpay(orgId, securityInfo);
       if(!temp_boolean){
         type = "2";
         //throw new BusinessException("存在未入账的挂账,单位挂账失败！");
         
        
           
//           String orgOverPayAccount="";
//           orgOverPayAccount=orgoverpayBS.queryOrgOverPayAccount(orgId, securityInfo);
           if((new BigDecimal(f.getBanlance()).add(new BigDecimal(f.getAmount())).compareTo(new BigDecimal(0)))<0){
             throw new BusinessException("冲挂账金额大于缴存金额，不能冲挂账！");
           }
           
        
         
       }
       if(f.getType()!=null&&f.getType().equals("1")){
         boolean temp_boolean_1 = orgoverpayBS.isOrgoverpay_1(f.getNoteNum(), f.getAmount(),securityInfo);
         if(!temp_boolean_1){
           throw new BusinessException("请输入正确的缴存结算号！");
         }
//         String orgOverPayAccount="";
//         orgOverPayAccount=orgoverpayBS.queryOrgOverPayAccount(orgId, securityInfo);
         if((new BigDecimal(f.getBanlance()).add(new BigDecimal(f.getAmount())).compareTo(new BigDecimal(0)))<0){
           throw new BusinessException("冲挂账金额大于缴存金额，不能冲挂账！");
         }
         
       }
       if(f.getType()!=null&&f.getType().equals("2")){
         
//         String orgOverPayAccount="";
//         orgOverPayAccount=orgoverpayBS.queryOrgOverPayAccount(orgId, securityInfo);
         if((new BigDecimal(f.getBanlance()).add(new BigDecimal(f.getAmount())).compareTo(new BigDecimal(0)))<0){
           throw new BusinessException("挂账金额为负！");
         }
         
       }
       
       String noteNum=f.getNoteNum();
       String amount=f.getAmount(); 
       String reason=f.getReason();
       orgoverpayBS.insertPaymentHead(orgId,amount,noteNum,reason,f.getType(),securityInfo);
       f.reset(mapping, request);
     }else{   
      String id=request.getParameter("orgoverpayid");        
      String noteNum=f.getNoteNum();
      String payMoney=f.getAmount();
      String reason=f.getReason();
      f.setSign("update");
      f.reset(mapping, request);
      orgoverpayBS.updateOrgoverpayById(id, noteNum, payMoney, reason,securityInfo);
      request.getSession().setAttribute("updateoverpay", "");
    }
    
  }catch(Exception bex){
    if(true){
      messages=new ActionMessages();
      messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(bex.getMessage(),
          false));
      saveErrors(request, messages);
    }else{
      messages=new ActionMessages();
      messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("存在未入账的挂账,单位挂账失败！",
          false));
      saveErrors(request, messages);
    }
  }
  return mapping.findForward("to_orgoverpay.jsp");
} 
}
