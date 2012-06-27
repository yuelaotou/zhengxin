package org.xpup.hafmis.sysfinance.systemmaintain.queryoperationlog.action;

import java.util.HashMap;
import java.util.Map;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.LookupDispatchAction;
/**
 * Copy Right Information : 查询业务日志的MainTainAction Goldsoft Project :
 * QueryOperationLogMainTainAC
 * 
 * @Version : v1.0
 * @author : 付云峰
 * @Date : 2007.10.19
 */
public class QueryOperationLogMainTainAC extends LookupDispatchAction {

  protected Map getKeyMethodMap() {
    Map map = new HashMap();
    map.put("button.print", "printOperationLog");
    return map;
  }
  /**
   * 打印
   * @param mapping
   * @param form
   * @param request
   * @param response
   * @return
   * @throws Exception
   */
  public ActionForward printOperationLog(ActionMapping mapping,
      ActionForm form, HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    try {
    } catch (Exception e) {
      e.printStackTrace();
    }
    return mapping.findForward("to_queryoperationlog_print");
  }
}
