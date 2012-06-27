package org.xpup.hafmis.syscollection.chgbiz.chgperson.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.xpup.common.exception.BusinessException;
import org.xpup.common.util.BSUtils;
import org.xpup.common.util.Pagination;
import org.xpup.hafmis.common.util.BusiTools;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.syscollection.chgbiz.chgperson.bsinterface.IChgpersonDoBS;
import org.xpup.hafmis.syscollection.common.domain.entity.Org;
/**
 * 
 * @author Õı¡·
 *2007-6-28
 */
public class ChgpersonFindEmpList_AjaxAAC extends Action{

  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
        response.setContentType("text/html;charset=UTF-8");
        response.setHeader("Cache-Control", "no-cache");
        try {
          request.getSession().setAttribute("sun_autochg_list",null);
          String id=(String)request.getParameter("orgID");
          
          IChgpersonDoBS chgpersonDoBS = (IChgpersonDoBS) BSUtils
          .getBusinessService("chgpersonDoBS", this, mapping.getModuleConfig()); 
          
          SecurityInfo securityInfo = (SecurityInfo) request.getSession()
          .getAttribute("SecurityInfo");
          
          String name="";
          Org org = null;
          if(id!=null&&!id.equals("")){
            org = chgpersonDoBS.queryOrgById(new Integer(id),securityInfo);
          }
          
          if(org!=null){
            id=BusiTools.convertTenNumber(org.getId().toString());
            name=org.getOrgInfo().getName();
            String paginationKey = getPaginationKey();
            Pagination pagination= (Pagination) request.getSession().getAttribute(paginationKey);
            pagination.getQueryCriterions().put("id", id);
          }
          chgpersonDoBS.deleteFnTempTable("0");
          String text=null;
          text="displays('"+id+"','"+name+"')";
          response.getWriter().write(text); 
          response.getWriter().close();
          
        }catch(BusinessException be){
          String text="reportErrors('"+be.getLocalizedMessage()+"')";
          response.getWriter().write(text);
          response.getWriter().close();
          
          
        }
        
        return null; 
}
  protected String getPaginationKey() {
    return ShowChgpersonDoListAC.PAGINATION_KEY;
 }
}