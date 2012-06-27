package org.xpup.hafmis.signjoint.action;

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
import org.xpup.hafmis.signjoint.bsinterface.ISignjointBS;
import org.xpup.hafmis.signjoint.form.LogQueryAF;
import org.xpup.hafmis.signjoint.form.SignAF;
import org.xpup.hafmis.signjoint.util.SignTools;


public class ShowLogListAC extends Action{
  
  public static final String PAGINATION_KEY = "org.xpup.hafmis.signjoint.action.ShowLogListAC";
  
  public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        ActionMessages messages =null;
    try{
      Pagination pagination = getPagination(PAGINATION_KEY, request); 
      PaginationUtils.updatePagination(pagination, request);  
      saveToken(request);
      LogQueryAF af=(LogQueryAF)form;
      //验证 start
      String type=af.getFiletype().trim();
      String start=af.getStarttime().trim();
      String end=af.getEndtime().trim();
      
      if((start!=null)&&(!start.equalsIgnoreCase(""))){
        if(!SignTools.isRightDate(start)){
          messages=new ActionMessages();
          messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("办理日期格式不正确！",
              false));
          saveErrors(request, messages);
          return mapping.findForward("to_list");
        }
      }
      if((end!=null)&&(!end.equalsIgnoreCase(""))){
        if(!SignTools.isRightDate(end)){
          messages=new ActionMessages();
          messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("办理日期格式不正确！",
              false));
          saveErrors(request, messages);
          return mapping.findForward("to_list");
        }
      }
      
      
      //验证 end 
      pagination.getQueryCriterions().put("filetype",type);
      pagination.getQueryCriterions().put("starttime",start);
      pagination.getQueryCriterions().put("endtime",end);
      ISignjointBS bs=(ISignjointBS)BSUtils.getBusinessService("SignjointBS",this, mapping.getModuleConfig());
      List list=bs.queryLog(pagination);
      af.setList(list);
      request.setAttribute("logAF", af);
      request.getSession().setAttribute(PAGINATION_KEY, pagination);
      pagination.getQueryCriterions().put("pageList",list);
    }catch(Exception ex){
      ex.printStackTrace();
      messages=new ActionMessages();
      messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("查询失败！",
          false));
      saveErrors(request, messages);
    }
    return mapping.findForward("to_list");
  }
  
  private Pagination getPagination(String paginationKey,
      HttpServletRequest request) {
    Pagination pagination = (Pagination) request.getSession().getAttribute(
        paginationKey);
    if(pagination == null){
      pagination = new Pagination(0, 10, 1, "file_name", "ASC",new HashMap(0));
      request.getSession().setAttribute(paginationKey, pagination);
    }   
    return pagination;
  }
}
