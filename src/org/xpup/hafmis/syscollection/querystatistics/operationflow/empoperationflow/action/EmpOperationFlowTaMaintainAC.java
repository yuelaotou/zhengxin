package org.xpup.hafmis.syscollection.querystatistics.operationflow.empoperationflow.action;

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
import org.xpup.common.util.BSUtils;
import org.xpup.common.util.Pagination;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.syscollection.querystatistics.operationflow.empoperationflow.bsinterface.IEmpOperationFlowBS;


public class EmpOperationFlowTaMaintainAC extends LookupDispatchAction{
  protected Map getKeyMethodMap() {
    Map map = new HashMap();
    map.put("button.exports", "exports");
    map.put("button.print", "print");
    return map;
  }

  public ActionForward exports(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
    ActionMessages messages =null;
    try{
      
      Pagination pagination = (Pagination)request.getSession().getAttribute(EmpOperationFlowTaShowAC.PAGINATION_KEY);
      List expList=(List)pagination.getQueryCriterions().get("pageList"); 
      if(expList.size()>0)
      {
          messages=new ActionMessages();
          messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("导出成功！",
            false));
          saveErrors(request, messages);
          request.getSession().setAttribute("explist",expList);
          response.sendRedirect(request.getContextPath()+"/ExportServlet?ISCANSHU=false&EXP_NAME=empOperationFlow_exp");
          return null;
      }else
      {
        messages=new ActionMessages();
        messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("没有数据！",
            false));
        saveErrors(request, messages);
      }
            
    }catch(Exception bex){
      messages=new ActionMessages();
      messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("导出失败！"+bex.getMessage(),
          false));
      saveErrors(request, messages);
    }
    return mapping.findForward("show_empOperationFlow");
  }
  public ActionForward print(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
    try{
      
      Pagination pagination = (Pagination)request.getSession().getAttribute(EmpOperationFlowTaShowAC.PAGINATION_KEY);
      IEmpOperationFlowBS empOperationFlowBS = (IEmpOperationFlowBS) BSUtils
      .getBusinessService("empOperationFlowBS", this, mapping.getModuleConfig());
      SecurityInfo securityInfo=(SecurityInfo)request.getSession().getAttribute("SecurityInfo");
      String bizDate = securityInfo.getUserInfo().getBizDate();
      String operator = securityInfo.getUserInfo().getUsername();
      request.setAttribute("bizDate", bizDate);
      request.setAttribute("operator", operator);
      List printList = empOperationFlowBS.findEmpHAFAccountFlowPrintList(pagination, securityInfo);
 //     List list=(List)pagination.getQueryCriterions().get("pageList"); 
      request.setAttribute("cellList",printList);
    }catch(Exception e){
      e.printStackTrace();
    }
    
    return mapping.findForward("show_print");
  }

}
