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
import org.xpup.hafmis.demo.bsinterface.IDemoBS;
import org.xpup.hafmis.signjoint.bsinterface.ISignjointBS;
import org.xpup.hafmis.signjoint.form.EmpAllAF;
import org.xpup.hafmis.signjoint.form.HistoryQueryAF;
import org.xpup.hafmis.signjoint.form.SignAF;
import org.xpup.hafmis.signjoint.util.SignTools;


public class ShowSignHistoryAC extends Action{
  
  public static final String PAGINATION_KEY = "org.xpup.hafmis.signjoint.action.ShowSignHistoryAC";
  
  public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        ActionMessages messages =null;
    try{
      Pagination pagination = getPagination(PAGINATION_KEY, request); 
      PaginationUtils.updatePagination(pagination, request);  
      saveToken(request);
      HistoryQueryAF af=(HistoryQueryAF)form;
      String affirmdatastart=af.getAffirmdatastart().trim();
      String affirmdataend= af.getAffirmdataend().trim();
      String transactdatastart=af.getTransactdatastart().trim();
      String transactdataend=af.getTransactdataend().trim();
      
      
      //校验日期 start
      if((affirmdatastart!=null)&&(!affirmdatastart.equalsIgnoreCase(""))){
        if(!SignTools.isRightDate(affirmdatastart)){
          request.setAttribute("historyQueryAF", af);
          messages=new ActionMessages();
          messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("办理日期格式不正确！",
              false));
          saveErrors(request, messages);
          return mapping.findForward("to_list");
        }
      }
      if((affirmdataend!=null)&&(!affirmdataend.equalsIgnoreCase(""))){
        if(!SignTools.isRightDate(affirmdataend)){
          request.setAttribute("historyQueryAF", af);
          messages=new ActionMessages();
          messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("办理日期格式不正确！",
              false));
          saveErrors(request, messages);
          return mapping.findForward("to_list");
        }
      }
      if((transactdatastart!=null)&&(!transactdatastart.equalsIgnoreCase(""))){
        if(!SignTools.isRightDate(transactdatastart)){
          request.setAttribute("historyQueryAF", af);
          messages=new ActionMessages();
          messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("银行确认日期格式不正确！",
              false));
          saveErrors(request, messages);
          return mapping.findForward("to_list");
        }
      }
      if((transactdataend!=null)&&(!transactdataend.equalsIgnoreCase(""))){
        if(!SignTools.isRightDate(transactdataend)){
          request.setAttribute("historyQueryAF", af);
          messages=new ActionMessages();
          messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("银行确认日期格式不正确！",
              false));
          saveErrors(request, messages);
          return mapping.findForward("to_list");
        }
      }
      //校验日期 end
      
      
      
      pagination.getQueryCriterions().put("orgid",af.getOrgid().trim());
      pagination.getQueryCriterions().put("empid",af.getEmpid().trim());
      pagination.getQueryCriterions().put("transactdatastart",transactdatastart);
      pagination.getQueryCriterions().put("transactdataend",transactdataend);
      pagination.getQueryCriterions().put("affirmdatastart",affirmdatastart);
      pagination.getQueryCriterions().put("affirmdataend",affirmdataend);
      pagination.getQueryCriterions().put("issccueed",af.getIsucceed().trim());
      ISignjointBS bs=(ISignjointBS)BSUtils.getBusinessService("SignjointBS",this, mapping.getModuleConfig());
      List list=bs.queryHistoryList(pagination);
      af.setList(list);
      request.setAttribute("historyQueryAF", af);
      request.getSession().setAttribute(PAGINATION_KEY, pagination);

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
      pagination = new Pagination(0, 10, 1, "orgid", "ASC",new HashMap(0));
      request.getSession().setAttribute(paginationKey, pagination);
    }   
    return pagination;
  }
}

