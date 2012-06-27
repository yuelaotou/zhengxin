package org.xpup.hafmis.syscollection.accounthandle.bizcheck.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.xpup.common.util.BSUtils;
import org.xpup.common.util.Pagination;
import org.xpup.hafmis.syscollection.accounthandle.bizcheck.bsinterface.IBizcheckBS;
import org.xpup.hafmis.syscollection.accounthandle.clearaccount.bsinterface.IclearAccountBS;

public class BizcheckTaRemotePrintAAC extends Action{

  public static final String PAGINATION_KEY = "org.xpup.hafmis.syscollection.accounthandle.bizcheck.action.BizcheckTaWindowAC";

  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
        response.setContentType("text/html;charset=UTF-8");
        response.setHeader("Cache-Control", "no-cache");
        try {
          Pagination pagination = (Pagination) request.getSession().getAttribute(
              PAGINATION_KEY);
          String headid =(String) pagination.getQueryCriterions().get("headid");
          IBizcheckBS bizcheckBS = (IBizcheckBS) BSUtils
          .getBusinessService("bizcheckBS", this, mapping.getModuleConfig());
          
          String bizID = bizcheckBS.queryBizIDByFlowID(headid);
          String type =(String) pagination.getQueryCriterions().get("type");
          String url="/syscollection/accounthandle/bizcheck/bizcheckTaWindowAC.do";
          
          String text = "display('"+bizID+"','"+url+"','"+type+"');";
          
          response.getWriter().write(text); 
          response.getWriter().close();
        } catch (Exception e) {
          e.printStackTrace();
        }
        
        return null; 
}

}