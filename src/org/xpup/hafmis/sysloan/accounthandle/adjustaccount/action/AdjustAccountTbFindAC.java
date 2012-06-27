package org.xpup.hafmis.sysloan.accounthandle.adjustaccount.action;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.xpup.common.util.Pagination;
import org.xpup.hafmis.sysloan.accounthandle.adjustaccount.dto.AdjustAccountTbFindDTO;
import org.xpup.hafmis.sysloan.accounthandle.adjustaccount.form.AdjustAccountFindAF;

/**
 * 错帐调整维护Find Action
 * 
 * @author 付云峰
 */
public class AdjustAccountTbFindAC extends Action {

  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    try {
      
      AdjustAccountFindAF adjustAccountFindAF = (AdjustAccountFindAF) form;
      AdjustAccountTbFindDTO adjustAccountTbFindDTO = adjustAccountFindAF.getAdjustAccountTbFindDTO();

      Pagination pagination = new Pagination(0, 10, 1, "res.flowheadid", "DESC",
          new HashMap(0));
      pagination.getQueryCriterions().put("adjustAccountTbFindDTO", adjustAccountTbFindDTO);
      // 用此状态来区分是否为默认登陆，如果不是默认登陆则type为'1'
      pagination.getQueryCriterions().put("type", "1");
      String paginationKey = getPaginationKey();

      request.getSession().setAttribute(paginationKey, pagination);

    } catch (Exception e) {
      e.printStackTrace();
    }
    return mapping.findForward("adjustAccountTbShowdAC");
  }
  protected String getPaginationKey() {
    return AdjustAccountTbShowdAC.PAGINATION_KEY;
  }
}
