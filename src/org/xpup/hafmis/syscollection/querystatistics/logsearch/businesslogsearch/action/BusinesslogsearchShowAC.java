package org.xpup.hafmis.syscollection.querystatistics.logsearch.businesslogsearch.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

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
import org.xpup.hafmis.common.util.BusiConst;
import org.xpup.hafmis.common.util.BusiTools;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.syscollection.querystatistics.logsearch.businesslogsearch.bsinterface.IBusinesslogsearchBS;
import org.xpup.hafmis.syscollection.querystatistics.logsearch.businesslogsearch.form.BusinesslogsearchAF;
import org.xpup.security.common.domain.User;

public class BusinesslogsearchShowAC extends Action {

  public static final String PAGINATION_KEY="org.xpup.hafmis.syscollection.querystatistics.logsearch.businesslogsearch.action.BusinesslogsearchShowAC";
  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    ActionMessages messages =null;
    SecurityInfo securityInfo=(SecurityInfo)request.getSession().getAttribute("SecurityInfo");
    try{
      Pagination pagination = getPagination(PAGINATION_KEY, request); 
      PaginationUtils.updatePagination(pagination, request);
      BusinesslogsearchAF businesslogsearchAF=(BusinesslogsearchAF)form;
      BusinesslogsearchAF f=new BusinesslogsearchAF();
      IBusinesslogsearchBS businesslogsearchBS = (IBusinesslogsearchBS) BSUtils
      .getBusinessService("businesslogsearchBS", this, mapping.getModuleConfig());  
      Map bizType = BusiTools.listBusiProperty(BusiConst.BUSINESSLOGSEARCH);
      businesslogsearchAF.setBizType(bizType);
      Map status = BusiTools.listBusiProperty(BusiConst.BUSINESSSTATE);
      businesslogsearchAF.setStatus(status);
      List list=new ArrayList();
      list = businesslogsearchBS.findLogFlowList(pagination, securityInfo);
      List allList=businesslogsearchBS.findAllLogFlowList(pagination, securityInfo);
      f.setList(allList);
      List userList=securityInfo.getUserList();
      Iterator it=userList.iterator();
      List userLists=new ArrayList();
      while(it.hasNext()){
        User user=(User)it.next();
        userLists.add(new org.apache.struts.util.LabelValueBean(user.getUsername(), user.getUsername()));
      }
      businesslogsearchAF.setList(list);
      businesslogsearchAF.setUserList(userList);
      request.setAttribute("userLists", userLists);
      request.getSession().setAttribute("businesslogsearchAF", businesslogsearchAF);
      request.getSession().setAttribute("f", f);
      }catch(BusinessException bex){
        messages=new ActionMessages();
        messages.add(ActionMessages.GLOBAL_MESSAGE,new ActionMessage(bex.getMessage(),  false));    
        saveErrors(request, messages);
    }catch(Exception e){
      e.printStackTrace();
    }
    return mapping.findForward(getForword());
  }

  protected String getForword() {
    return "to_businesslogsearch_show.jsp";
  }


  
  
  private Pagination getPagination(String paginationKey,
      HttpServletRequest request) {
    Pagination pagination = (Pagination) request.getSession().getAttribute(
        paginationKey);
  
    if (pagination == null) {
      pagination = new Pagination(0, 10, 1, "a319.bizid", "DESC", new HashMap(0));
      request.getSession().setAttribute(paginationKey, pagination);
    }

    return pagination;
  }
}

