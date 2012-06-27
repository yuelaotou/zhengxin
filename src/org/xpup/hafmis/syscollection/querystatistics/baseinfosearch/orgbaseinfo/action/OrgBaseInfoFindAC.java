package org.xpup.hafmis.syscollection.querystatistics.baseinfosearch.orgbaseinfo.action;

import java.util.HashMap;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.xpup.common.util.Pagination;
import org.xpup.hafmis.syscollection.querystatistics.baseinfosearch.orgbaseinfo.form.OrgInfoSearchAF;

public class OrgBaseInfoFindAC extends Action{
  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response) {
    try{
      OrgInfoSearchAF orgInfoSearchAF = (OrgInfoSearchAF) form;
      request.getSession().removeAttribute(OrgBaseInfoShowAC.PAGINATION_KEY);
      //在点查询的时候必须保持这个Pagination是新new的对象...不然在输入查询条件的时候 页码会保持上一个查询条件的页码
      Pagination pagination = getPagination(OrgBaseInfoShowAC.PAGINATION_KEY, request);
      pagination.getQueryCriterions().put("search", orgInfoSearchAF);
    }catch(Exception s){
      s.printStackTrace();
    }
    return new ActionForward("/orgBaseInfoShowAC.do");
  }
  private Pagination getPagination(String paginationKey,HttpServletRequest request) {
    Pagination pagination = (Pagination) request.getSession().getAttribute(paginationKey);
    if (pagination == null) {
      pagination = new Pagination(0, 10, 1, "a001.id","DESC",new HashMap(0));
      request.getSession().setAttribute(paginationKey, pagination);
    }   
    return pagination;
  }
}
