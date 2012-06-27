package org.xpup.hafmis.sysfinance.accounthandle.credencefillin.action;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.xpup.common.util.Pagination;
import org.xpup.hafmis.sysfinance.accounthandle.credencecheck.dto.CredencecheckFindDTO;
import org.xpup.hafmis.sysfinance.accounthandle.credencefillin.form.CredenceFillinTdAF;

/**
 * Copy Right Information : 显示凭证录入维护列表的FindAction Goldsoft Project :
 * CredenceFillinTdFindAC
 * 
 * @Version : v1.0
 * @author : 付云峰
 * @Date : 2007.10.31
 */
public class CredenceFillinTdFindAC extends Action {

  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    try {
      CredenceFillinTdAF credenceFillinTdAF = (CredenceFillinTdAF) form;
      request.getSession().setAttribute("type", "1");
      CredencecheckFindDTO credencecheckFindDTO = credenceFillinTdAF
          .getCredencecheckFindDTO();
      Pagination pagination = new Pagination(0, 10, 1,
          "fn201.credence_date,to_number(fn201.credence_num)", "", new HashMap(0));
      credencecheckFindDTO.setFlag("1");
      pagination.getQueryCriterions().put("credencecheckFindDTO",
          credencecheckFindDTO);
      String paginationKey = getPaginationKey();
      request.getSession().setAttribute(paginationKey, pagination);
    } catch (Exception e) {
      e.printStackTrace();
    }
    // 打印要用到的状态
    request.getSession().setAttribute("print_type", "2");
    return mapping.findForward("credenceFillinTdShowdAC");
  }

  protected String getPaginationKey() {
    return CredenceFillinTdShowdAC.PAGINATION_KEY;
  }

}
