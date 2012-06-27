package org.xpup.hafmis.sysfinance.treasurermng.depositcheckacc.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.xpup.common.util.Pagination;
import org.xpup.hafmis.sysfinance.treasurermng.depositcheckacc.form.DepositCheckAccAF;

public class DepositCheckAccFindAC extends Action {
  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response) throws Exception {
    try{
      DepositCheckAccAF depositCheckAccAF = (DepositCheckAccAF) form;
      Pagination pagination = new Pagination();
      pagination.getQueryCriterions().put("settDateSt", depositCheckAccAF.getSettDateSt().trim());
      pagination.getQueryCriterions().put("settDateEnd", depositCheckAccAF.getSettDateEnd().trim());
      pagination.getQueryCriterions().put("subjectCode", depositCheckAccAF.getSubjectCode().trim());
      request.getSession().setAttribute("pagination_gjp", pagination);
  }catch(Exception e){
    e.printStackTrace();
  }
  return mapping.findForward("depositcheckacc_show");
  }
}
