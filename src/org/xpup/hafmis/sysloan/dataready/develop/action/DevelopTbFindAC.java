package org.xpup.hafmis.sysloan.dataready.develop.action;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.xpup.common.util.Pagination;
import org.xpup.hafmis.sysloan.dataready.develop.dto.DevelopTbFindDTO;
import org.xpup.hafmis.sysloan.dataready.develop.form.DevelopFindAF;

/**
 * 开发商维护条件查询的Action
 * 
 * @author 付云峰
 */
public class DevelopTbFindAC extends Action {

  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    // TODO Auto-generated method stub
    try {
      DevelopFindAF developFindAF = (DevelopFindAF) form;
      DevelopTbFindDTO developTbFindDTO = developFindAF.getDevelopTbFindDTO();

      Pagination pagination = new Pagination(0, 10, 1, "p5.developer_code", "ASC",
          new HashMap(0));
      pagination.getQueryCriterions().put("developTbFindDTO", developTbFindDTO);
      String paginationKey = getPaginationKey();

      request.getSession().setAttribute(paginationKey, pagination);

    } catch (Exception e) {
      e.printStackTrace();
    }

    return mapping.findForward("developTbShowAC");
  }

  protected String getPaginationKey() {
    return DevelopTbShowAC.PAGINATION_KEY;
  }

}
