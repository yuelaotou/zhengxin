package org.xpup.hafmis.syscollection.paymng.personaddpay.action;


import java.io.Serializable;
import java.util.List;

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
import org.xpup.hafmis.syscollection.paymng.orgaddpay.bsinterface.IOrgaddpayBS;
import org.xpup.hafmis.syscollection.paymng.personaddpay.bsinterface.IPersonAddPayBS;
import org.xpup.hafmis.syscollection.paymng.personaddpay.form.PersonAddPayAF;

public class PersonAddPayTaExportAC extends Action{
  
  public static final String PAGINATION_KEY = "org.xpup.hafmis.syscollection.paymng.personaddpay.action.PersonAddPayTaShowAC";
  
  public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

    ActionMessages messages =null;
    try{
      SecurityInfo securityInfo = (SecurityInfo) request.getSession().getAttribute("SecurityInfo");
      HttpSession session=request.getSession(false);
      Pagination pagination = (Pagination)session.getAttribute(PAGINATION_KEY);
      IPersonAddPayBS personAddPayBS = (IPersonAddPayBS) BSUtils
      .getBusinessService("personAddPayBS", this, mapping.getModuleConfig());

      
      String empIds=request.getParameter("empIds");
      String empNames=request.getParameter("empNames");
      String statuss=request.getParameter("statuss");

      String payKind = request.getParameter("payKind");
      String payWay = request.getParameter("payWay");
      String tatol=request.getParameter("tatol");
      int finalTatol=(Integer.parseInt(tatol)-1);
      String order[]=new String [finalTatol] ;
      
      if((Integer.parseInt(empIds))!=0){
        order[(Integer.parseInt(empIds)-1)]="emp.empId";
      }
      if((Integer.parseInt(empNames))!=0){
        order[(Integer.parseInt(empNames)-1)]="emp.empInfo.name";
      } 
      if((Integer.parseInt(statuss))!=0){
        order[(Integer.parseInt(statuss)-1)]="emp.payStatus";
      }   
     
      pagination.getQueryCriterions().put("orderArray", order);
      for (int i = 0; i < order.length; i++) {
      }
      String orgId="";
      String noteNum="";
     //  String settleMode="";
      orgId=request.getParameter("orgId");
      noteNum=request.getParameter("noteNum");
      // settleMode=request.getParameter("settMode");
      pagination.getQueryCriterions().put("orgId", orgId);
      pagination.getQueryCriterions().put("docNumber", noteNum);

      pagination.getQueryCriterions().put("payKind",payKind);
      pagination.getQueryCriterions().put("payWay", payWay);
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
    return mapping.findForward("personaddpayTbShow");
  
  
  
  }


}
