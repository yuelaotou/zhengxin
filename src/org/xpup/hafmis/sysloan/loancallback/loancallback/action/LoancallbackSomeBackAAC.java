package org.xpup.hafmis.sysloan.loancallback.loancallback.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.xpup.common.util.BSUtils;
import org.xpup.common.util.Pagination;
import org.xpup.hafmis.common.util.BusiConst;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.sysloan.loancallback.loancallback.bsinterface.ILoancallbackBS;
public class LoancallbackSomeBackAAC extends Action{
  
  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
        response.setContentType("text/html;charset=UTF-8");
        response.setHeader("Cache-Control", "no-cache");
        try {
          String contractId=(String)request.getParameter("id");
          String salary=(String)request.getParameter("salary");
          String text=null;
          ILoancallbackBS loancallbackBS = (ILoancallbackBS) BSUtils
          .getBusinessService("loancallbackBS", this, mapping.getModuleConfig());
          String time=loancallbackBS.someBackTime(contractId, salary);
          text = "displaySomeBack('"+time+"');";
          response.getWriter().write(text); 
          response.getWriter().close();
        } catch (Exception e) {
          e.printStackTrace();
        }
        
        return null; 
}

}