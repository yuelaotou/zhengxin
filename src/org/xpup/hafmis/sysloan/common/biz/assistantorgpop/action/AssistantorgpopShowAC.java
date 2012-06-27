package org.xpup.hafmis.sysloan.common.biz.assistantorgpop.action;

import java.util.ArrayList;
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
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.sysloan.common.biz.assistantorgpop.bsinterface.IAssistantorgpopBS;
import org.xpup.hafmis.sysloan.common.biz.contractpop.bsinterface.IContractpopBS;

/**
 * 
 * @author yuqf
 *
 */
public class AssistantorgpopShowAC extends Action{
  public static final String PAGINATION_KEY = "org.xpup.hafmis.sysloan.common.biz.assistantorgpop.action.AssistantorgpopShowAC";
  public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
    // TODO Auto-generated method stub
    ActionMessages messages =null;
    List list = new ArrayList();
    try{
      SecurityInfo securityInfo =(SecurityInfo)request.getSession().getAttribute("SecurityInfo");
     
      String indexs=request.getParameter("indexs");
      if(indexs != null){
        request.getSession().setAttribute("indexs", indexs);
      }
     
      IAssistantorgpopBS assistantorgpopBS = (IAssistantorgpopBS) BSUtils
      .getBusinessService("assistantorgpopBS", this, mapping.getModuleConfig());
      Pagination pagination = getPagination(PAGINATION_KEY, request); 
      PaginationUtils.updatePagination(pagination, request);
      list = assistantorgpopBS.findAssistantpopList(pagination, securityInfo);
      request.getSession().setAttribute("assistantpopList", list);

    }catch(BusinessException bex){
      messages=new ActionMessages();
      messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("没有您要查询的信息！",
          false));
      saveErrors(request, messages);
  }catch(Exception e){
    e.printStackTrace();
  }
    return mapping.findForward("to_assistantorgpop_list");
  }

  private Pagination getPagination(String paginationKey,
      HttpServletRequest request) {
    Pagination pagination = (Pagination) request.getSession().getAttribute(
        paginationKey);
    if (pagination == null) {
      pagination = new Pagination(0, 10, 1, "t.assistant_org_id", "ASC",
          new HashMap(0));
      request.getSession().setAttribute(paginationKey, pagination);
    }   

    return pagination;
  }
}
