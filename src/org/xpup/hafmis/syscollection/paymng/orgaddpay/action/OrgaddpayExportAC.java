package org.xpup.hafmis.syscollection.paymng.orgaddpay.action;

import java.io.Serializable;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
import org.xpup.hafmis.syscollection.paymng.orgaddpay.form.OrgaddpayTaAF;

public class OrgaddpayExportAC extends Action{
  public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
    ActionMessages messages =null;
    try{
      IOrgaddpayBS orgaddpayBS = (IOrgaddpayBS) BSUtils
      .getBusinessService("orgaddpayBS", this, mapping.getModuleConfig());
      Serializable orgid = request.getParameter("orgId");
      String payMonth = request.getParameter("payMonth");
      String noteNum = request.getParameter("noteNum");
      String payStartMonth = request.getParameter("startPayMonth");
      String payKind = request.getParameter("payKind");
      String payWay = request.getParameter("payWay");
      SecurityInfo securityInfo=(SecurityInfo)request.getSession().getAttribute("SecurityInfo");
      //查询所有list
      
      String empIds=request.getParameter("empIds");
      String empNames=request.getParameter("empNames");
      String orgPays=request.getParameter("orgPays");
      String statuss=request.getParameter("statuss");
      String tatol=request.getParameter("tatol");
      int finalTatol=(Integer.parseInt(tatol)-1);
      String order[]=new String [finalTatol] ;
      
      if((Integer.parseInt(empIds))!=0){
        order[(Integer.parseInt(empIds)-1)]="emp.empId";
      }
      if((Integer.parseInt(empNames))!=0){
        order[(Integer.parseInt(empNames)-1)]="emp.empInfo.name";
      } 
      if((Integer.parseInt(orgPays))!=0){
        order[(Integer.parseInt(orgPays)-1)]="emp.orgPay";
      }   
      if((Integer.parseInt(statuss))!=0){
        order[(Integer.parseInt(statuss)-1)]="emp.payStatus";
      }   
    
      
      Pagination pagination = (Pagination) request.getSession().getAttribute(OrgaddpayTaShowAC.PAGINATION_KEY);
      OrgaddpayTaAF orgaddpayTaAF = (OrgaddpayTaAF)pagination.getQueryCriterions().get("orgaddpayTaAF");
      orgaddpayTaAF.setPayWay(payWay);
      orgaddpayTaAF.setPayKind(payKind);
      pagination.getQueryCriterions().put("orgaddpayTaAF", orgaddpayTaAF);
      List expList=orgaddpayBS.findPaylistBatch(orgid, payMonth, payStartMonth,noteNum, securityInfo,order);
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

}