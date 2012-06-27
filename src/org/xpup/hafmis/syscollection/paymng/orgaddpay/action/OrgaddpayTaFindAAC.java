package org.xpup.hafmis.syscollection.paymng.orgaddpay.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.xpup.common.util.BSUtils;
import org.xpup.common.util.Pagination;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.syscollection.common.domain.entity.Org;
import org.xpup.hafmis.syscollection.paymng.orgaddpay.bsinterface.IOrgaddpayBS;

public class OrgaddpayTaFindAAC extends Action{

  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
        response.setContentType("text/html;charset=UTF-8");
        response.setHeader("Cache-Control", "no-cache");
        SecurityInfo securityInfo=(SecurityInfo)request.getSession().getAttribute("SecurityInfo");
        try {
          String id=(String)request.getParameter("id");
          IOrgaddpayBS orgaddpayBS = (IOrgaddpayBS) BSUtils
          .getBusinessService("orgaddpayBS", this, mapping.getModuleConfig());
          Org org=null;
          String name="";
          if(id!=null&&!id.equals("")){
            org=orgaddpayBS.findOrgInfo(id,"2",securityInfo);
          }
          if(org!=null){
            name=org.getOrgInfo().getName();
          }
          String text=null;
          String paginationKey = getPaginationKey();
          Pagination pagination= (Pagination) request.getSession().getAttribute(paginationKey);
          pagination.getQueryCriterions().put("id", id);
          pagination.getQueryCriterions().put("name", name);
          text="displays('"+id+"','"+name+"')";
          response.getWriter().write(text); 
          response.getWriter().close();
        } catch (Exception e) {
          e.printStackTrace();
        }
        
        return null; 
}

  protected String getPaginationKey() {
    return OrgaddpayTaShowAC.PAGINATION_KEY;
 }
}