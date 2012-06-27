package org.xpup.hafmis.sysloan.querystatistics.queryoperationlog.action;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.xpup.common.util.Pagination;
import org.xpup.hafmis.sysloan.querystatistics.queryoperationlog.form.QueryOperationLogAF;

public class QueryOperatorLogTaFind extends Action{

  private HashMap criterions = null;
  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    // TODO Auto-generated method stub
    QueryOperationLogAF queryOperationLogAF = (QueryOperationLogAF)form;
    criterions = makeCriterionsMap(queryOperationLogAF);
    Pagination pagination = new Pagination(0, 10, 1, "plBizActiveLog.bizid", "ASC",
        criterions);
    String paginationKey = getPaginationKey();
    request.getSession().setAttribute(paginationKey, pagination);
    pagination.getQueryCriterions().put("flag", "1");
//    request.setAttribute("flag", "1");
    queryOperationLogAF.reset(mapping, request);
    return mapping.findForward("queryOperationLogTaShow");
  }
  
  protected String getPaginationKey() {
    return QueryOperationLogTaShow.PAGINATION_KEY;
  }
  protected HashMap makeCriterionsMap(QueryOperationLogAF form) {
    HashMap m = new HashMap();
    String bizStatus = form.getBizStatusValue();
    if (bizStatus != null && !"".equals(bizStatus)) {
      m.put("bizStatus", bizStatus.trim());
    }

    String bizType = form.getBizTypeValue();
    if (bizType != null && !"".equals(bizType)) {
      m.put("bizType", bizType.trim());
    }

    String operator = form.getOperatorValue();
    if (operator != null && !"".equals(operator)) {
      m.put("operator", operator.trim());
    }

    String beginTime = form.getBeginTime();
    if (beginTime != null && !"".equals(beginTime)) {
      m.put("beginTime", beginTime.trim());
    }

    String endTime = form.getEndTime();
    if (endTime != null && !"".equals(endTime)) {
      m.put("endTime", endTime.trim());
    }
    m.put("key", "value");
    return m;
  }
  
}
