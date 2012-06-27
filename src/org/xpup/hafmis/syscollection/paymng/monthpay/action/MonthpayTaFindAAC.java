package org.xpup.hafmis.syscollection.paymng.monthpay.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
import org.xpup.hafmis.syscollection.common.domain.entity.Org;
import org.xpup.hafmis.syscollection.paymng.monthpay.bsinterface.IMonthpayBS;

public class MonthpayTaFindAAC extends Action{
  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
        response.setContentType("text/html;charset=UTF-8");
        response.setHeader("Cache-Control", "no-cache");
        ActionMessages messages =null;
        SecurityInfo securityInfo=(SecurityInfo)request.getSession().getAttribute("SecurityInfo");
        try {
          String id=(String)request.getParameter("id");
          IMonthpayBS monthpayBS = (IMonthpayBS) BSUtils
          .getBusinessService("monthpayBS", this, mapping.getModuleConfig());
          Org org = monthpayBS.findOrgInfo(id,"2",securityInfo);
          String name = "";
          if(org!=null){
            name = org.getOrgInfo().getName();
          }
          String text=null;
          String paginationKey = getPaginationKey();
          Pagination pagination= (Pagination) request.getSession().getAttribute(paginationKey);
          pagination.getQueryCriterions().put("id", id);
          pagination.getQueryCriterions().put("name",name);
          text = "display('"+id+"','"+name+"');";
          response.getWriter().write(text); 
          response.getWriter().close();
        } catch (Exception e) {
          e.printStackTrace();
        }
        
        return null; 
}

  protected String getPaginationKey() {
    return MonthpayTaShowAC.PAGINATION_KEY;
 }
}