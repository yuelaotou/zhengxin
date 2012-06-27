package org.xpup.hafmis.sysloan.querystatistics.queryoperationlog.action;

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
import org.xpup.hafmis.sysloan.querystatistics.loanaccountquery.interestgl.action.InterestglTaShowAC;
import org.xpup.hafmis.sysloan.querystatistics.queryoperationlog.bsinterface.IQueryOperationgLogBS;
import org.xpup.hafmis.sysloan.querystatistics.queryoperationlog.form.QueryOperationLogAF;
import org.xpup.security.common.domain.User;

public class QueryOperationLogTaShow extends Action  {

  public static final String PAGINATION_KEY = "org.xpup.hafmis.sysloan.querystatistics.queryoperationlog.action.QueryOperationLogTaShow";
  
  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    
    SecurityInfo securityInfo = (SecurityInfo) request.getSession()
    .getAttribute("SecurityInfo");
    Pagination pagination = getPagination(QueryOperationLogTaShow.PAGINATION_KEY,
        request);
    IQueryOperationgLogBS queryOperationgLogBS = (IQueryOperationgLogBS) BSUtils.getBusinessService(
        "queryoperationgLogBS", this, mapping.getModuleConfig());
    PaginationUtils.updatePagination(pagination, request);
    String flag=(String)pagination.getQueryCriterions().get("flag");
    QueryOperationLogAF queryOperationLogAF=new QueryOperationLogAF();
    Map businessStatus = BusiTools.listBusiProperty(BusiConst.PLBUSINESSSTATE);/*个贷-业务状态*/
    Map businessType = BusiTools.listBusiProperty(BusiConst.PLBUSINESSTYPE);/*个贷-业务类型*/

    List operList = securityInfo.getUserList();
    List operList1 = new ArrayList();
    List userList = new ArrayList();
    User user = null;
    Iterator itr2 = operList.iterator();
    while (itr2.hasNext()) {
      user = (User) itr2.next();
      operList1.add(new org.apache.struts.util.LabelValueBean(user
          .getUsername(), user.getUsername()));
      userList.add(user.getUsername());
    }
    pagination.getQueryCriterions().put("userList", userList);
    if("1".equals(flag)){
        Map map=queryOperationgLogBS.queryListByCriterions(pagination, securityInfo);
        
        request.getSession(true).setAttribute("operatorList", operList1);
        queryOperationLogAF.setList((List)map.get("list"));
        queryOperationLogAF.setPrintList((List)map.get("printlist"));
        queryOperationLogAF.setBizType(businessType);
        queryOperationLogAF.setBizStatus(businessStatus);
        queryOperationLogAF.setOperatorList(operList1);
    }else{
      request.getSession(true).setAttribute("operatorList", operList1);
      queryOperationLogAF.setList(null);
      queryOperationLogAF.setPrintList(null);
      queryOperationLogAF.setBizType(businessType);
      queryOperationLogAF.setBizStatus(businessStatus);
      queryOperationLogAF.setOperatorList(operList1);
    }
    request.setAttribute("queryOperationLogAF", queryOperationLogAF);
    return mapping.findForward("to_jsp");
  }
  private Pagination getPagination(String paginationKey,
      HttpServletRequest request) {
    Pagination pagination = (Pagination) request.getSession().getAttribute(
        paginationKey);
    if (pagination == null) {
      HashMap m = new HashMap();
//      m.put("radioValue", "1");
      pagination = new Pagination(0, 10, 1, "plBizActiveLog.bizid", "ASC", m);
      request.getSession().setAttribute(paginationKey, pagination);
    }
    return pagination;
  }
}
