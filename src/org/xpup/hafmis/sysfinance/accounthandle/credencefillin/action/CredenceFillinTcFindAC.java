package org.xpup.hafmis.sysfinance.accounthandle.credencefillin.action;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.xpup.common.util.Pagination;
import org.xpup.hafmis.sysfinance.accounthandle.credencefillin.dto.CredenceFillinTcFindDTO;
import org.xpup.hafmis.sysfinance.accounthandle.credencefillin.form.CredenceFillinTcAF;

/**
 * Copy Right Information : 损益结转列表的FindAction Goldsoft Project :
 * CredenceFillinTcFindAC
 * 
 * @Version : v1.0
 * @author : 付云峰
 * @Date : 2007.10.24
 */
public class CredenceFillinTcFindAC extends Action {

  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {

    try {
      CredenceFillinTcAF credenceFillinTcAF = (CredenceFillinTcAF) form;
      CredenceFillinTcFindDTO credenceFillinTcFindDTO = credenceFillinTcAF
          .getCredenceFillinTcFindDTO();
      Pagination pagination = new Pagination(0, 20, 1, "f201.subject_code", "ASC",
          new HashMap(0));
      pagination.getQueryCriterions().put("credenceFillinTcFindDTO",
          credenceFillinTcFindDTO);
      String paginationKey = getPaginationKey();

      request.getSession().setAttribute(paginationKey, pagination);
    } catch (Exception e) {
      e.printStackTrace();
    }

    return mapping.findForward("credenceFillinTcShowAC");
  }

  protected String getPaginationKey() {
    return CredenceFillinTcShowAC.PAGINATION_KEY;
  }
}
