package org.xpup.hafmis.sysloan.common.biz.loankouaccpop.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.StringTokenizer;

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
import org.xpup.hafmis.sysloan.common.biz.loankouaccpop.bsinterface.ILoanKouAccpopBS;
/**
 * @author 郭婧平
 */
public class LoanKouAccpopShowAC extends Action{
  public static final String PAGINATION_KEY = "org.xpup.hafmis.sysloan.common.biz.loankouaccpop.action.LoanKouAccpopShowAC";
  public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
    ActionMessages messages =null;
    List list = new ArrayList();
    try{
      SecurityInfo securityInfo =(SecurityInfo)request.getSession().getAttribute("SecurityInfo");
      String statuss=request.getParameter("status");
      String indexs=request.getParameter("indexs");
      if(indexs != null){
        request.getSession().setAttribute("indexs", indexs);
      }
      if(statuss==null){
        statuss = request.getSession().getAttribute("status").toString();
      }

      List status = new ArrayList();
      StringTokenizer str = new StringTokenizer(statuss,",");
      while (str.hasMoreTokens()) {
        String temp_str = str.nextToken();
        status.add(temp_str);
      }

      ILoanKouAccpopBS loanKouAccpopBS = (ILoanKouAccpopBS) BSUtils
      .getBusinessService("loanKouAccpopBS", this, mapping.getModuleConfig());
      Pagination pagination = getPagination(PAGINATION_KEY, request); 
      PaginationUtils.updatePagination(pagination, request);
      request.getSession().setAttribute("status",statuss);
      pagination.getQueryCriterions().put("status", status);  
      list = loanKouAccpopBS.findLoanKouAccpopList(pagination, securityInfo);
      request.getSession().setAttribute("loankouaccpopList", list);

    }catch(BusinessException bex){
      messages=new ActionMessages();
      messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("没有您要查询的信息！",
          false));
      saveErrors(request, messages);
  }catch(Exception e){
    e.printStackTrace();
  }
    return mapping.findForward("to_loankouaccpop_list");
  }
  private Pagination getPagination(String paginationKey,
      HttpServletRequest request) {
    Pagination pagination = (Pagination) request.getSession().getAttribute(
        paginationKey);
    if (pagination == null) {
      pagination = new Pagination(0, 10, 1, "p110.contract_id", "ASC",
          new HashMap(0));
      request.getSession().setAttribute(paginationKey, pagination);
    }   

    return pagination;
  }
}
