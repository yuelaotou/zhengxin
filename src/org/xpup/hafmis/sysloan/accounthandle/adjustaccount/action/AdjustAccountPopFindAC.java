package org.xpup.hafmis.sysloan.accounthandle.adjustaccount.action;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.xpup.common.util.Pagination;
import org.xpup.hafmis.sysloan.accounthandle.adjustaccount.dto.AdjustAccountPopFindDTO;
import org.xpup.hafmis.sysloan.accounthandle.adjustaccount.form.AdjustAccountPopFindAF;
/**
 * ´íÕÊµ÷Õûµ¯³ö¿òµÄFind Action
 * @author ¸¶ÔÆ·å
 *
 */
public class AdjustAccountPopFindAC extends Action {
  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {

    AdjustAccountPopFindAF adjustAccountPopFindAF = (AdjustAccountPopFindAF) form;
    AdjustAccountPopFindDTO adjustAccountPopFindDTO = adjustAccountPopFindAF.getAdjustAccountPopFindDTO();
    
    Pagination pagination = new Pagination(0, 10, 1, "res.loankouacc", "DESC",
        new HashMap(0));
    pagination.getQueryCriterions().put("adjustAccountPopFindDTO", adjustAccountPopFindDTO);
    String paginationKey = getPaginationKey();

    request.getSession().setAttribute(paginationKey, pagination);
    return mapping.findForward("adjustAccountPopShowAC");
    
  }
  
  protected String getPaginationKey() {
    return AdjustAccountPopShowAC.PAGINATION_KEY;
  }
  
}
