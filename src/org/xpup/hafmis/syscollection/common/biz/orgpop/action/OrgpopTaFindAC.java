package org.xpup.hafmis.syscollection.common.biz.orgpop.action;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.xpup.common.util.Pagination;
import org.xpup.hafmis.syscollection.common.biz.orgpop.form.OrgpopFindAF;

/**
 * 根据单位编号、单位名称查询单位信息
 * 
 * @author 李娟 2007-6-27
 */
public class OrgpopTaFindAC extends Action {
  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {

    try {
      OrgpopFindAF f = (OrgpopFindAF) form;
      String qx = request.getParameter("qx");
      HashMap criterions = makeCriterionsMap(f);
      Pagination pagination = new Pagination(0, f.getPageSize(), 1, "orgs.id",
          "DESC", criterions);
      String paginationKey = OrgpopTaShowAC.PAGINATION_KEY;
      if (qx != null) {
        request.getSession().setAttribute("qxs", qx);
        pagination.getQueryCriterions().put("qx", qx);
      }
      request.getSession().setAttribute(paginationKey, pagination);

      f.reset(mapping, request);
    } catch (Exception e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }

    return mapping.findForward(getForword());
  }

  protected String getForword() {
    return "show_orgpop";
  }

  protected HashMap makeCriterionsMap(OrgpopFindAF form) {
    HashMap m = new HashMap();
    String id = form.getId().trim();
    if (!(id == null || "".equals(id))) {
      try {
        int iid = Integer.parseInt(id);
        m.put("id", new Integer(iid));
      } catch (NumberFormatException e) {
        m.put("id", new Integer(-10000));
      }
    }
    String name = form.getName().trim();
    if (!(name == null || name.length() == 0))
      m.put("name", name);

    String oldId = form.getOldId().trim();

    if (!(oldId == null || oldId.length() == 0))
      try {
        int ooldId = Integer.parseInt(oldId);
        m.put("oldId", "0" + ooldId + "");
      } catch (NumberFormatException exx) {
        exx.printStackTrace();
        m.put("oldId", new Integer(-10000));
      }

    // TODO 需要继续添加查询条件！！！
    return m;
  }

}
