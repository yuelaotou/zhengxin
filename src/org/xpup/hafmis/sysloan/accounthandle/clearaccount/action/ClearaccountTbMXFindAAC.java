package org.xpup.hafmis.sysloan.accounthandle.clearaccount.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.xpup.common.util.Pagination;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;

public class ClearaccountTbMXFindAAC extends Action{
                                               
  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
        response.setContentType("text/html;charset=UTF-8");
        response.setHeader("Cache-Control", "no-cache");
        SecurityInfo securityInfo=(SecurityInfo)request.getSession().getAttribute("SecurityInfo");
        try {
          Pagination pagination = (Pagination) request.getSession().getAttribute(
              ClearaccountTbShowAC.PAGINATION_KEY);
          String bizType = (String) pagination.getQueryCriterions().get("bizType");
          String loanBankId = (String) pagination.getQueryCriterions().get("loanBankId");
          String startDate = (String) pagination.getQueryCriterions().get("startDate");
          String endDate = (String) pagination.getQueryCriterions().get("endDate");
          String makePerson = (String) pagination.getQueryCriterions().get("makePerson");
          if(startDate==null&&endDate==null){
            startDate=securityInfo.getUserInfo().getBizDate();
            endDate=securityInfo.getUserInfo().getBizDate();
          }
          Pagination pagination1 = new Pagination();
          pagination1.getQueryCriterions().put("bizType",bizType);
          pagination1.getQueryCriterions().put("loanBankName",loanBankId);
          pagination1.getQueryCriterions().put("beginBizDate",startDate);
          pagination1.getQueryCriterions().put("endBizDate",endDate);
          pagination1.getQueryCriterions().put("makePerson",makePerson);
          request.getSession(false).setAttribute(ClearaccountTbMXShowAC.PAGINATION_KEY, pagination1);
          String text = "display();";
          response.getWriter().write(text); 
          response.getWriter().close();
        } catch (Exception e) {
          e.printStackTrace();
        }
        
        return null; 
}

}