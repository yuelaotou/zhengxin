package org.xpup.hafmis.sysfinance.systemmaintain.queryoperationlog.action;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.xpup.common.util.Pagination;
import org.xpup.hafmis.sysfinance.systemmaintain.queryoperationlog.dto.QueryOperationLogFindDTO;
import org.xpup.hafmis.sysfinance.systemmaintain.queryoperationlog.form.QueryOperationLogAF;

/**
 * Copy Right Information : 查询业务日志的FindAction Goldsoft Project :
 * QueryOperationLogFindAC
 * 
 * @Version : v1.0
 * @author : 付云峰
 * @Date : 2008.1.29
 */
public class QueryOperationLogFindAC extends Action {

  
  
  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    try {
      QueryOperationLogAF queryOperationLogAF = (QueryOperationLogAF) form;
      QueryOperationLogFindDTO queryOperationLogFindDTO = queryOperationLogAF.getQueryOperationLogFindDTO();
      Pagination pagination = new Pagination(0, 10, 1, "f310.bizactivity_log_id", "DESC",
          new HashMap(0));
      pagination.getQueryCriterions().put("queryOperationLogFindDTO", queryOperationLogFindDTO);
      String paginationKey = getPaginationKey();

      request.getSession().setAttribute(paginationKey, pagination);
      
    } catch (Exception e) {
      e.printStackTrace();
    }
    return mapping.findForward("queryOperationLogShowAC");
  }
  protected String getPaginationKey() {
    return QueryOperationLogShowAC.PAGINATION_KEY;
  }
}
