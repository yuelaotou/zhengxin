package org.xpup.hafmis.sysfinance.systemmaintain.queryoperationlog.action;

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
import org.xpup.common.util.BSUtils;
import org.xpup.common.util.Pagination;
import org.xpup.common.util.PaginationUtils;
import org.xpup.hafmis.common.util.BusiConst;
import org.xpup.hafmis.common.util.BusiTools;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.sysfinance.systemmaintain.queryoperationlog.bsinterface.IQueryOperationLogBS;
import org.xpup.hafmis.sysfinance.systemmaintain.queryoperationlog.form.QueryOperationLogAF;
import org.xpup.security.common.domain.User;

/**
 * Copy Right Information : 查询业务日志的ShowAction Goldsoft Project :
 * QueryOperationLogShowAC
 * 
 * @Version : v1.0
 * @author : 付云峰
 * @Date : 2008.1.29
 */
public class QueryOperationLogShowAC extends Action {

  public static final String PAGINATION_KEY = "org.xpup.hafmis.sysfinance.systemmaintain.queryoperationlog.action.QueryOperationLogShowAC";
  
  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    
    QueryOperationLogAF queryOperationLogAF = new QueryOperationLogAF();
    try {
      SecurityInfo securityInfo = (SecurityInfo) request.getSession()
      .getAttribute("SecurityInfo");
      IQueryOperationLogBS queryOperationLogBS = (IQueryOperationLogBS) BSUtils
      .getBusinessService("queryOperationLogBS", this, mapping
          .getModuleConfig());
      
      String paginationKey = getPaginationKey();
      Pagination pagination = getPagination(paginationKey, request);
      PaginationUtils.updatePagination(pagination, request);
      // 得到操作员
      List operList = securityInfo.getUserList();
      List operList1 = new ArrayList();
      User user = null;
      Iterator itr2 = operList.iterator();
      while (itr2.hasNext()) {
        user = (User) itr2.next();
        operList1.add(new org.apache.struts.util.LabelValueBean(user
            .getUsername(), user.getUsername()));
      }
      Object[] obj = queryOperationLogBS.findOperationLogList(pagination,securityInfo,operList);
      
      Map credenceTypeMap = BusiTools.listBusiProperty(BusiConst.CREDENCE_NUM_TYPE);
      queryOperationLogAF.setCredenceTypeMap(credenceTypeMap);
      Map actionMap = BusiTools.listBusiProperty(BusiConst.CREDSTATE);
      queryOperationLogAF.setActionMap(actionMap);
      queryOperationLogAF.setList((List)obj[0]);
      // 用来进行打印的list
      request.getSession().setAttribute("printList", (List)obj[1]);
      request.getSession(true).setAttribute("operList1", operList1);
      request.setAttribute("queryOperationLogAF", queryOperationLogAF);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return mapping.findForward("to_queryoperationlog_show");
  }
  private Pagination getPagination(String paginationKey,
      HttpServletRequest request) {
    Pagination pagination = (Pagination) request.getSession().getAttribute(
        paginationKey);
    if (pagination == null) {
      pagination = new Pagination(0, 10, 1, "f310.bizactivity_log_id", "DESC", new HashMap(0));
      request.getSession().setAttribute(paginationKey, pagination);
    }
    return pagination;
  }

  protected String getPaginationKey() {
    return PAGINATION_KEY;
  }
}
