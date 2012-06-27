package org.xpup.hafmis.syscollection.querystatistics.paymentpickstatistics.collectionstatistics.action;

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
import org.xpup.common.exception.BusinessException;

public class MaintainCollectionstatisticsListAC extends LookupDispatchAction{

  protected Map getKeyMethodMap() {
    Map map = new HashMap();
    map.put("button.print", "print");
    map.put("button.export", "export");
    return map;
  }
  
  public ActionForward print(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    
    return mapping.findForward("to_printjsp");
  }
  
  public ActionForward export(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception{
    List list = new ArrayList();
    ActionMessages messages =null;
    try{
    list = (List)request.getSession().getAttribute("printList");
    if(list.size()>0){
      messages=new ActionMessages();
      messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("导出成功！",
        false));
      saveErrors(request, messages);
      request.getSession().setAttribute("explist",list);
      response.sendRedirect(request.getContextPath()+"/ExportServlet?ISCANSHU=false&EXP_NAME=collectionstatistics_exp");
      return null;
    }else{
      messages=new ActionMessages();
      messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("没有数据！",
          false));
      saveErrors(request, messages);
     }   
    }catch(Exception bex){
      bex.printStackTrace();
    }
    return mapping.findForward("to_showAC");
  }
}
