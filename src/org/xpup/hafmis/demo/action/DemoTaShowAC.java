package org.xpup.hafmis.demo.action;

import java.util.HashMap;
import java.util.List;

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
import org.xpup.common.util.PaginationUtils;
import org.xpup.hafmis.demo.bsinterface.IDemoBS;
import org.xpup.hafmis.demo.form.DemoAF;


/**
 * 
 * @author 刘洋
 *2007-5-31
 */
public class DemoTaShowAC extends Action{
  
  public static final String PAGINATION_KEY = "org.xpup.hafmis.demo.action.DemoTaShowAC";
  
  public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        ActionMessages messages =null;
    try{
   //   System.out.println("用户名"+request.getRemoteAddr()+request.getRemoteUser());
      /**
       * 分页
       */
      Pagination pagination = getPagination(PAGINATION_KEY, request); 
      PaginationUtils.updatePagination(pagination, request);  
      saveToken(request);
 //     System.out.println("mapping.getModuleConfig()"+mapping.getModuleConfig()+"  "+this);
      
      IDemoBS demoBS = (IDemoBS) BSUtils
      .getBusinessService("demoBS", this, mapping.getModuleConfig());
      
      List list=demoBS.findDemoListByCriterions(pagination);
      
      DemoAF demoAF=new DemoAF();
      
      demoAF.setList(list);
      
      demoAF.setId((String)pagination.getQueryCriterions().get("demo.id"));
      demoAF.setName((String)pagination.getQueryCriterions().get("demo.name"));
      
      request.setAttribute("demoAF", demoAF);
      pagination.getQueryCriterions().put("pageList", list);
    }catch(Exception ex){
      ex.printStackTrace();
    }
    return mapping.findForward("to_demo_list");
  }
  
  private Pagination getPagination(String paginationKey,
      HttpServletRequest request) {
    Pagination pagination = (Pagination) request.getSession().getAttribute(
        paginationKey);
    if (pagination == null) {
      pagination = new Pagination(0, 2, 1, "demo.id", "ASC",
          new HashMap(0));
      request.getSession().setAttribute(paginationKey, pagination);
    }   
 
    return pagination;
  }
}
