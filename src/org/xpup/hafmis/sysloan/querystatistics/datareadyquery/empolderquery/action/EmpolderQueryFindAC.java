package org.xpup.hafmis.sysloan.querystatistics.datareadyquery.empolderquery.action;

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
 * 开发商查询的find action
 * 
 * @author 付云峰
 */
public class EmpolderQueryFindAC extends Action {

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
      pagination.getQueryCriterions().put("buyhouseorgid", developFindAF.getBuyhouseorgid());
      pagination.getQueryCriterions().put("floorName", developFindAF.getFloorName());
      pagination.getQueryCriterions().put("floorNum", developFindAF.getFloorNum());
      String paginationKey = getPaginationKey();

      request.getSession().setAttribute(paginationKey, pagination);

    } catch (Exception e) {
      e.printStackTrace();
    }
    return mapping.findForward("empolderQueryShowAC");
  }

  protected String getPaginationKey() {
    return EmpolderQueryShowAC.PAGINATION_KEY;
  }
}
