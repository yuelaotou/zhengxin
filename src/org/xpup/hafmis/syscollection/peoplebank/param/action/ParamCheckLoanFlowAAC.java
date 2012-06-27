package org.xpup.hafmis.syscollection.peoplebank.param.action;



import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.xpup.common.util.BSUtils;
import org.xpup.hafmis.sysloan.dataready.param.bsinterface.IParamBS;

public class ParamCheckLoanFlowAAC extends Action{
  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response) throws Exception{
    response.setContentType("text/html;charset=UTF-8");
    response.setHeader("Cache-Control", "no-cache");
    try {
      String loanVipCheck=request.getParameter("loanVipCheck");
      String endorseLoan=request.getParameter("endorseLoan");
      IParamBS paramBS = (IParamBS) BSUtils
      .getBusinessService("paramBS", this, mapping.getModuleConfig());
      String paramValue="";
      paramValue=paramBS.checkLoanFlow();
      String temp_pramValue=loanVipCheck+endorseLoan;
      if(!paramValue.equals("")){
        if(!paramValue.equals(temp_pramValue)){
          String text="show();";
          response.getWriter().write(text);
          response.getWriter().close();
        }
      }
    } catch(Exception e){
      e.printStackTrace();
    }
    return null;
  }
}
