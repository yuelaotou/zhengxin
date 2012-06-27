
package org.xpup.hafmis.syscollection.querystatistics.paymentpickstatistics.orgpaymentstatistics.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessages;
import org.xpup.common.util.BSUtils;
import org.xpup.common.util.Pagination;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.syscollection.common.domain.entity.Org;
import org.xpup.hafmis.syscollection.querystatistics.paymentpickstatistics.orgpaymentstatistics.bsinterface.IOrgpaymentstatisticsBS;

public class OrgpaymentstatisticsFindAAC extends Action{
  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
        response.setContentType("text/html;charset=UTF-8");
        response.setHeader("Cache-Control", "no-cache");
        ActionMessages messages =null;
        SecurityInfo securityInfo=(SecurityInfo)request.getSession().getAttribute("SecurityInfo");
        try {
          String id=(String)request.getParameter("id");
          IOrgpaymentstatisticsBS orgpaymentstatisticsBS = (IOrgpaymentstatisticsBS) 
          BSUtils.getBusinessService("orgpaymentstatisticsBS", this, mapping.getModuleConfig());
          Org org = orgpaymentstatisticsBS.findOrgInfo(id,"2",securityInfo);
          String name = "";
          if(org!=null){
            name = org.getOrgInfo().getName();
          }
          String text=null;
          String paginationKey = getPaginationKey();
          Pagination pagination= (Pagination) request.getSession().getAttribute(paginationKey);
          pagination.getQueryCriterions().put("orgId", id);
          pagination.getQueryCriterions().put("orgName",name);
          text = "display('"+id+"','"+name+"');";
          response.getWriter().write(text); 
          response.getWriter().close();
        } catch (Exception e) {
          e.printStackTrace();
        }
        
        return null; 
}

  protected String getPaginationKey() {
    return OrgpaymentstatisticsShowAC.PAGINATION_KEY;
 }
}