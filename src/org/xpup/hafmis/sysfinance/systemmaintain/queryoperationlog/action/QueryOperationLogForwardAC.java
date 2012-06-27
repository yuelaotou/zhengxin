package org.xpup.hafmis.sysfinance.systemmaintain.queryoperationlog.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Copy Right Information : 查询业务日志的ForwardAction Goldsoft Project :
 * QueryOperationLogForwardAC
 * 
 * @Version : v1.0
 * @author : 付云峰
 * @Date : 2008.1.29
 */
public class QueryOperationLogForwardAC extends Action {

  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    // TODO Auto-generated method stub
    try {
      request.getSession().removeAttribute(QueryOperationLogShowAC.PAGINATION_KEY);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return mapping.findForward("queryOperationLogShowAC");
  }

}
