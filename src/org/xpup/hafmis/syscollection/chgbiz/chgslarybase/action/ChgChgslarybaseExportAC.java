package org.xpup.hafmis.syscollection.chgbiz.chgslarybase.action;

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
import org.xpup.hafmis.syscollection.chgbiz.chgpay.bsinterface.IChgpayBS;
import org.xpup.hafmis.syscollection.chgbiz.chgperson.bsinterface.IChgpersonDoBS;
import org.xpup.hafmis.syscollection.chgbiz.chgslarybase.bsinterface.IChgslarybaseBS;

/**
 * @author 李鹏 2007-12-15
 */
public class ChgChgslarybaseExportAC extends Action {
  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    ActionMessages messages =null;
    try{
      HttpSession session=request.getSession(false);
      Pagination pagination=new Pagination();
      SecurityInfo securityInfo=(SecurityInfo)request.getSession().getAttribute("SecurityInfo");
      String ip=securityInfo.getUserInfo().getUserIp();
      String name=securityInfo.getUserInfo().getUsername();
      IChgslarybaseBS chgslarybaseBS = (IChgslarybaseBS) BSUtils.getBusinessService("chgslarybaseBS", this,
          mapping.getModuleConfig());
      
      String empIds=request.getParameter("empIds");
      String empNames=request.getParameter("empNames");
      String salaryBases=request.getParameter("salaryBases");
    
      String tatol=request.getParameter("tatol");
      int finalTatol=(Integer.parseInt(tatol)-1);
      String order[]=new String [finalTatol] ;
      
      if((Integer.parseInt(empIds))!=0){
        order[(Integer.parseInt(empIds)-1)]="emp.empId";
      }
      if((Integer.parseInt(empNames))!=0){
        order[(Integer.parseInt(empNames)-1)]="emp.empInfo.name";
      } 
      if((Integer.parseInt(salaryBases))!=0){
        order[(Integer.parseInt(salaryBases)-1)]="emp.salaryBase";
      }   
     
      pagination.getQueryCriterions().put("orderArray", order);
//      for (int i = 0; i < order.length; i++) {
//        System.out.println("test排序===>"+order[i]);
//      }
      
      String orgId=request.getParameter("orgId");
      String orgName=request.getParameter("orgName");
      pagination.getQueryCriterions().put("org.id", orgId);
      pagination.getQueryCriterions().put("orgName", orgName);
      pagination.setOrderother("ASC");
      String chgMonth=request.getParameter("chgMonth");
      pagination.getQueryCriterions().put("chgMonth", chgMonth);
 
      pagination.getQueryCriterions().put("ip", ip);
      pagination.getQueryCriterions().put("name", name);
      
      List expList=chgslarybaseBS.queryEmpOrgList(pagination);
      if(expList.size()>0)
      {
          messages=new ActionMessages();
          messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("导出成功！",
            false));
          saveErrors(request, messages);
          request.getSession().setAttribute("explist",expList);
          response.sendRedirect(request.getContextPath()+"/ExportServlet?ISCANSHU=false&EXP_NAME=chgslarybase_exp");
      }else
      {
        messages=new ActionMessages();
        messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("没有数据！",
            false));
        saveErrors(request, messages);
      }
            return null;
    }catch(BusinessException bex){
      messages=new ActionMessages();
      messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(bex
          .getMessage(), false));
      saveErrors(request, messages);
    }
    return mapping.findForward("chgslarybaseTaShowAC");
  }
  
}
