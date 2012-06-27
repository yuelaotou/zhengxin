package org.xpup.hafmis.signjoint.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.xpup.common.util.BSUtils;
import org.xpup.common.util.Pagination;
import org.xpup.hafmis.signjoint.bsinterface.ISignjointBS;

public class CompanyFindAAC extends Action{

  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
        response.setContentType("text/html;charset=UTF-8");
        response.setHeader("Cache-Control", "no-cache");
        
        try {
          String id=(String)request.getParameter("id");
          System.out.println(" ajax :" + id);
          ISignjointBS bs=(ISignjointBS)BSUtils.getBusinessService("SignjointBS",this, mapping.getModuleConfig());
          String name="";
          if(id!=null&&!id.equals("")){
            name=bs.getOrgnameByOrgID(id.trim());
          }
          String text=null;
          String paginationKey = getPaginationKey();
          Pagination pagination= (Pagination) request.getSession().getAttribute(paginationKey);
          pagination.getQueryCriterions().put("orgid", id);
          pagination.getQueryCriterions().put("orgname",name );
          
          text="displays('"+id+"','"+name+"')";
          response.getWriter().write(text); 
          response.getWriter().close();
         } catch (Exception e) {
          e.printStackTrace();
        }
        return null;
  }

  protected String getPaginationKey() {
    return CompanyShowAC.PAGINATION_KEY;
 }
}