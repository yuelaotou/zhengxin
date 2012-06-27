package org.xpup.hafmis.syscollection.querystatistics.collfncomparison.action;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.xpup.common.util.Pagination;
import org.xpup.hafmis.syscollection.querystatistics.baseinfosearch.orgcollinfo.dto.OrgCollInfoFindDTO;
import org.xpup.hafmis.syscollection.querystatistics.collfncomparison.form.CollFnComparisonAF;

public class CollFnComparisonFindAC extends Action {

  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    try {
      CollFnComparisonAF collFnComparisonAF = (CollFnComparisonAF) form;
      OrgCollInfoFindDTO orgCollInfoFindDTO = collFnComparisonAF.getDto();
      Pagination pagination = new Pagination(0, 10, 1, "a1.id", "DESC",
          new HashMap(0));
      pagination.getQueryCriterions().put("searchDTO", orgCollInfoFindDTO);
      String paginationKey = getPaginationKey();
      request.getSession().setAttribute(paginationKey, pagination);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return mapping.findForward("to_collFnComparisonShowAC");
  }

  protected String getPaginationKey() {
    return CollFnComparisonShowAC.PAGINATION_KEY;
  }
  
}
