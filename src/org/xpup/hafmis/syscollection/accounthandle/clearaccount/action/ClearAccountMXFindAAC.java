package org.xpup.hafmis.syscollection.accounthandle.clearaccount.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.xpup.common.util.BSUtils;
import org.xpup.common.util.Pagination;
import org.xpup.hafmis.syscollection.accounthandle.clearaccount.bsinterface.IclearAccountBS;

public class ClearAccountMXFindAAC extends Action{

  public static final String PAGINATION_KEY = "org.xpup.hafmis.syscollection.accounthandle.clearaccount.action.ClearAccountTaWindowAC";

  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
        response.setContentType("text/html;charset=UTF-8");
        response.setHeader("Cache-Control", "no-cache");
        try {
          Pagination pagination = (Pagination) request.getSession().getAttribute(
              PAGINATION_KEY);
          String headid =(String) pagination.getQueryCriterions().get("headid");

          IclearAccountBS clearaccountBS = (IclearAccountBS) BSUtils
              .getBusinessService("clearaccountBS", this, mapping.getModuleConfig());
          String bizID = clearaccountBS.queryBizIDByFlowID(headid);
          String type =(String) pagination.getQueryCriterions().get("type");
          String url="/syscollection/accounthandle/clearaccount/clearAccountTaWindowAC.do";
          
          String text = "display('"+bizID+"','"+url+"','"+type+"');";
          
          response.getWriter().write(text); 
          response.getWriter().close();
        } catch (Exception e) {
          e.printStackTrace();
        }
        
        return null; 
}

}