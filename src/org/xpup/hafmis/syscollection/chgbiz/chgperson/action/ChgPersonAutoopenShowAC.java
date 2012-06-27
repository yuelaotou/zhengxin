package org.xpup.hafmis.syscollection.chgbiz.chgperson.action;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.xpup.common.util.BSUtils;
import org.xpup.common.util.Pagination;
import org.xpup.common.util.PaginationUtils;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.syscollection.chgbiz.chgperson.bsinterface.IChgpersonDoBS;
import org.xpup.hafmis.syscollection.chgbiz.chgperson.form.ChgPersonAutoopenAF;

/**
 * @author 吴洪涛 2008-6-17
 */
public class ChgPersonAutoopenShowAC extends Action {
  public static final String PAGINATION_KEY = "org.xpup.hafmis.syscollection.chgbiz.chgperson.action.ChgPersonAutoopenShowAC";
  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
  try {
    ChgPersonAutoopenAF chgPersonAutoopenAF = new ChgPersonAutoopenAF();
      // 得到请求参数，通过此参数判断是否需要清空session
      String orgId = request.getParameter("orgId");
      String chgMonth = request.getParameter("chgMonth");
      SecurityInfo securityInfo = (SecurityInfo) request.getSession()
          .getAttribute("SecurityInfo");
      String type = request.getParameter("type");
      if (type!=null) {
        request.getSession().removeAttribute(getPaginationKey());
      }
      String paginationKey = getPaginationKey();
      Pagination pagination = getPagination(paginationKey, request);
      PaginationUtils.updatePagination(pagination, request);
      if(type!=null){
        pagination.getQueryCriterions().put("orgId", orgId);
        pagination.getQueryCriterions().put("chgMonth", chgMonth);
      }
      System.out.println(pagination.getQueryCriterions().get("orgId"));
      System.out.println(pagination.getQueryCriterions().get("chgMonth"));
      IChgpersonDoBS chgpersonDoBS = (IChgpersonDoBS) BSUtils
      .getBusinessService("chgpersonDoBS", this, mapping.getModuleConfig());
      chgPersonAutoopenAF = chgpersonDoBS.findChgPersonAutoopenAF(pagination,securityInfo);
      request.setAttribute("chgPersonAutoopenAF", chgPersonAutoopenAF);
      pagination.getQueryCriterions().put("listAll",  chgPersonAutoopenAF.getListAll());
    } catch (Exception e) {
      e.printStackTrace();
    }
    return mapping.findForward("to_chgPersonAutoopenpop");
  }
  
  private Pagination getPagination(String paginationKey,
      HttpServletRequest request) {
    Pagination pagination = (Pagination) request.getSession().getAttribute(
        paginationKey);
    if (pagination == null) {
      pagination = new Pagination(0, 10, 1, "tranInTail.id", "DESC", new HashMap(0));
      request.getSession().setAttribute(paginationKey, pagination);
    }
    return pagination;
  }
  
  protected String getPaginationKey() {
    return PAGINATION_KEY;
  }
}
