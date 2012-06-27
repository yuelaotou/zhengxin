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
import org.xpup.hafmis.syscollection.chgbiz.chgperson.bsinterface.IChgpersonDoBS;
import org.xpup.hafmis.syscollection.chgbiz.chgperson.form.AutoChangePopAF;

/**
 * Copy Right Information : 显示自动变更弹出框的Action Goldsoft Project :
 * AutoChangePopShowAC
 * 
 * @Version : v1.0
 * @author : 付云峰
 * @Date : 2008.3.18
 */
public class AutoChangePopShowAC extends Action {
  
  public static final String PAGINATION_KEY = "org.xpup.hafmis.syscollection.chgbiz.chgperson.action.AutoChangePopShowAC";

  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    try {
      
      AutoChangePopAF autoChangePopAF = new AutoChangePopAF();
      // 得到请求参数，通过此参数判断是否需要清空session
      String type = request.getParameter("type");
      String orgId = request.getParameter("orgID");
      if (type!=null) {
        request.getSession().removeAttribute(getPaginationKey());
      }
      String paginationKey = getPaginationKey();
      Pagination pagination = getPagination(paginationKey, request);
      PaginationUtils.updatePagination(pagination, request);
      if (type!=null) {
        pagination.getQueryCriterions().put("orgId",orgId);
      }
      IChgpersonDoBS chgpersonDoBS = (IChgpersonDoBS) BSUtils
      .getBusinessService("chgpersonDoBS", this, mapping.getModuleConfig());
      List list = chgpersonDoBS.findAutoChangePopList(pagination);
      List list_yg_chgperson = chgpersonDoBS.findAutoChangePopListALL(pagination);
      autoChangePopAF.setList(list);
      request.setAttribute("autoChangePopAF", autoChangePopAF);
      request.getSession().setAttribute("list_yg_chgperson", list_yg_chgperson);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return mapping.findForward("to_autochangepop_show");
  }
  
  private Pagination getPagination(String paginationKey,
      HttpServletRequest request) {
    Pagination pagination = (Pagination) request.getSession().getAttribute(
        paginationKey);
    if (pagination == null) {
      pagination = new Pagination(0, 10, 1, "t.temp_column1", "DESC", new HashMap(0));
      request.getSession().setAttribute(paginationKey, pagination);
    }
    return pagination;
  }
  
  protected String getPaginationKey() {
    return PAGINATION_KEY;
  }
}
