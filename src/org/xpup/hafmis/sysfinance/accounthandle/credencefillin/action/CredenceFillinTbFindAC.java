package org.xpup.hafmis.sysfinance.accounthandle.credencefillin.action;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.xpup.common.util.Pagination;
import org.xpup.hafmis.sysfinance.accounthandle.credencefillin.dto.CredenceFillinTbFindDTO;
import org.xpup.hafmis.sysfinance.accounthandle.credencefillin.form.CredenceFillinTbAF;

/**
 * Copy Right Information : 自动转帐的FindAction 
 * Goldsoft Project : CredenceFillinTbFindAC
 * 
 * @Version : v1.0
 * @author : 付云峰
 * @Date : 2007.10.17
 */
public class CredenceFillinTbFindAC extends Action {

  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    try {
      CredenceFillinTbAF credenceFillinTbAF = (CredenceFillinTbAF) form;
      CredenceFillinTbFindDTO credenceFillinTbFindDTO = credenceFillinTbAF.getCredenceFillinTbFindDTO();

      Pagination pagination = new Pagination(0, 10, 1, "res.notenum", "asc",
          new HashMap(0));
      pagination.getQueryCriterions().put("credenceFillinTbFindDTO", credenceFillinTbFindDTO);
      String paginationKey = getPaginationKey();

      request.getSession().setAttribute(paginationKey, pagination);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return mapping.findForward("credenceFillinTbShowAC");
  }
  protected String getPaginationKey() {
    return CredenceFillinTbShowAC.PAGINATION_KEY;
  }
}
