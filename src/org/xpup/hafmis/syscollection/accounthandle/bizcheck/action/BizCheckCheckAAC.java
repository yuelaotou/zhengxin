package org.xpup.hafmis.syscollection.accounthandle.bizcheck.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.xpup.common.util.BSUtils;
import org.xpup.hafmis.syscollection.accounthandle.bizcheck.bsinterface.IBizcheckBS;

public class BizCheckCheckAAC extends Action{
  public static final String PAGINATION_KEY = "org.xpup.hafmis.syscollection.accounthandle.clearaccount.action.ClearAccountTaBalanceShowAC";
  public static final String PAGINATION_KEY1="org.xpup.hafmis.syscollection.querystatistics.businessflow.orgbusinessflow.action.ShowOrgbusinessflowListAC";
                                              
  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
        response.setContentType("text/html;charset=UTF-8");
        response.setHeader("Cache-Control", "no-cache");
        try {
          
          String orgId=(String)request.getParameter("orgId");
          System.out.println(".."+orgId);
          if(orgId.length()==10){
            orgId=orgId.substring(1,orgId.length());
          }
          String isModify="";
          IBizcheckBS bizcheckBS = (IBizcheckBS) BSUtils
          .getBusinessService("bizcheckBS", this, mapping.getModuleConfig());
          isModify=bizcheckBS.queruIsBankModify(orgId);
          request.getSession().setAttribute("isModify", isModify);
          request.getSession().setAttribute("orgId", orgId);
          String text = null;
          text = "displays('" + isModify + "')";
          response.getWriter().write(text); 
          response.getWriter().close();
        } catch (Exception e) {
          e.printStackTrace();
        }
        
        return null; 
}

}