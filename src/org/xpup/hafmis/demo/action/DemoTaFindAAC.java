package org.xpup.hafmis.demo.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.xpup.common.util.BSUtils;
import org.xpup.common.util.Pagination;
import org.xpup.hafmis.demo.bsinterface.IDemoBS;
import org.xpup.hafmis.demo.domain.entity.Demo;


/**
 * 
 * @author ¡ı—Û
 *2007-5-31
 */
public class DemoTaFindAAC extends Action{

  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
        response.setContentType("text/html;charset=UTF-8");
        response.setHeader("Cache-Control", "no-cache");
        
        try {
          String id=(String)request.getParameter("id");
          IDemoBS demoBS = (IDemoBS) BSUtils
          .getBusinessService("demoBS", this, mapping.getModuleConfig());
          Demo demo=null;
          String name="";
          if(id!=null&&!id.equals("")){
            demo=demoBS.findDemoById(new Integer(id));
          }
          if(demo!=null){
            name=demo.getName();
          }
          String text=null;
          String paginationKey = getPaginationKey();
          Pagination pagination= (Pagination) request.getSession().getAttribute(paginationKey);
          pagination.getQueryCriterions().put("demo.id", id);
          pagination.getQueryCriterions().put("demo.name", name);
          
          text="displays('"+id+"','"+name+"')";
          
          response.getWriter().write(text); 
          response.getWriter().close();
        } catch (Exception e) {
          e.printStackTrace();
        }
        
        return null; 
}

  protected String getPaginationKey() {
    return DemoTaShowAC.PAGINATION_KEY;
 }
}