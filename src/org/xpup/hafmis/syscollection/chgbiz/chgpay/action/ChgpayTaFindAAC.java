package org.xpup.hafmis.syscollection.chgbiz.chgpay.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.xpup.common.util.BSUtils;
import org.xpup.common.util.Pagination;
import org.xpup.hafmis.syscollection.chgbiz.chgslarybase.bsinterface.IChgslarybaseBS;


//单位的查询
public class ChgpayTaFindAAC extends Action {
  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    response.setContentType("text/html;charset=UTF-8");
    response.setHeader("Cache-Control", "no-cache");
 
    try {
//    获得单位编号
      String unitcode=request.getParameter("org.id");
      String text=null;
      String paginationKey = getPaginationKey();
      Pagination pagination= (Pagination) request.getSession().getAttribute(paginationKey);     
      pagination.getQueryCriterions().put("org.id", unitcode);  
      text="showlist()";
      response.getWriter().write(text);
      response.getWriter().close();
    } catch (Exception e) {
      e.printStackTrace();
    }
    return null;
  }

  protected String getForword() {
    return "chgpayTaSouwAC.do";
  }
  protected String getPaginationKey() {
    return ChgpayTaSouwAC.PAGINATION_KEY;
  }
}
