package org.xpup.hafmis.syscollection.accounthandle.queryorgversionint.action;


import java.util.ArrayList;
import java.util.HashMap;
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
import org.xpup.hafmis.syscollection.accounthandle.queryorgversionint.bsinterface.IQueryorgversionintBS;
import org.xpup.hafmis.syscollection.accounthandle.queryorgversionint.form.QueryorgversionintAF;




public class QueryorgversionintShowAC extends Action {
  public static final String PAGINATION_KEY = "org.xpup.hafmis.syscollection.accounthandle.queryorgversionint.action.QueryorgversionintShowAC";

  public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
    // TODO Auto-generated method stub
    SecurityInfo securityInfo = (SecurityInfo) request.getSession()
    .getAttribute("SecurityInfo");
    Pagination pagination = getPagination(PAGINATION_KEY,
        request);
    PaginationUtils.updatePagination(pagination, request);
    IQueryorgversionintBS queryorgversionintBS = (IQueryorgversionintBS) BSUtils.getBusinessService(
    "queryorgversionintBS", this, mapping.getModuleConfig());
    QueryorgversionintAF queryorgversionintAF = new QueryorgversionintAF();
    List list = new ArrayList();
    list = queryorgversionintBS.queryList(pagination, securityInfo);
    PaginationUtils.updatePagination(pagination, request);
    Map map = BusiTools.listBusiProperty(BusiConst.CLEARINTERESTTYPE);
    queryorgversionintAF.setMap(map);
    queryorgversionintAF.setList(list);
    request.setAttribute("queryorgversionintAF", queryorgversionintAF);
    return mapping.findForward("to_queryorgversionint_show");
  }
  
  private Pagination getPagination(String paginationKey,
      HttpServletRequest request) {
    Pagination pagination = (Pagination) request.getSession().getAttribute(
        paginationKey);
    if (pagination == null) {
      HashMap m = new HashMap();
      pagination = new Pagination(0, 10, 1, null, "ASC", m);
      request.getSession().setAttribute(paginationKey, pagination);
    }
    return pagination;
  }
}
