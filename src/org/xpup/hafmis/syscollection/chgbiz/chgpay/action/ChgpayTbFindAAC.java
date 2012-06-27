package org.xpup.hafmis.syscollection.chgbiz.chgpay.action;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.xpup.common.util.Pagination;



//单位的查询
public class ChgpayTbFindAAC extends Action {
  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    response.setContentType("text/html;charset=UTF-8");
    response.setHeader("Cache-Control", "no-cache");
 
    try {
//    获得单位编号
      String unitcode=request.getParameter("org.id");
      String name=request.getParameter("org.orgInfo.name");
  
      String chgMonth=request.getParameter("chgMonth");
      String typetb=request.getParameter("typetb");
      
      String text=null;
      String paginationKey = getPaginationKey();
      request.getSession().removeAttribute(paginationKey);
      Pagination pagination = getPagination(paginationKey, request);     
      pagination.getQueryCriterions().put("org.id", unitcode);  
      pagination.getQueryCriterions().put("org.orgInfo.name", name);  
      pagination.getQueryCriterions().put("chgMonth", chgMonth); 
      pagination.getQueryCriterions().put("typetb", typetb);
      text="showlist()";
      response.getWriter().write(text);
      response.getWriter().close();
    } catch (Exception e) {
      e.printStackTrace();
    }
    return null;
  }

  protected String getPaginationKey() {
    return ChgpayTbSouwAC.PAGINATION_KEY;
  }
  private Pagination getPagination(String paginationKey,
      HttpServletRequest request) {
    Pagination pagination = (Pagination) request.getSession().getAttribute(
        paginationKey);
    if (pagination == null) {
      pagination = new Pagination(0, 10, 1, "chgPaymentPayment.id", "DESC", new HashMap(0));
      request.getSession().setAttribute(paginationKey, pagination);
    }

    return pagination;
  }
}
