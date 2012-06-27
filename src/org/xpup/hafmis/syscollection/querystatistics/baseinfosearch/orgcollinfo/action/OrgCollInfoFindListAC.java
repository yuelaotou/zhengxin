package org.xpup.hafmis.syscollection.querystatistics.baseinfosearch.orgcollinfo.action;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.xpup.common.util.Pagination;
import org.xpup.hafmis.syscollection.querystatistics.baseinfosearch.orgcollinfo.dto.OrgCollInfoFindDTO;
import org.xpup.hafmis.syscollection.querystatistics.baseinfosearch.orgcollinfo.form.OrgCollInfoFindAF;

public class OrgCollInfoFindListAC extends Action {

  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {

    try {
      OrgCollInfoFindAF orgCollInfoFindAF = (OrgCollInfoFindAF) form;
      OrgCollInfoFindDTO orgCollInfoFindDTO = orgCollInfoFindAF.getDto();
      Pagination pagination = new Pagination(0, 10, 1, "a1.id", "DESC",
          new HashMap(0));
      pagination.getQueryCriterions().put("searchDTO", orgCollInfoFindDTO);
      String paginationKey = getPaginationKey();
      request.getSession().setAttribute(paginationKey, pagination);
    } catch (Exception e) {
      System.err.println("条件查询有错误！");
      e.printStackTrace();
    }
    return mapping.findForward("orgcollinfo_show_AC");
  }

  protected String getPaginationKey() {
    return OrgCollInfoShowAC.PAGINATION_KEY;
  }

}
