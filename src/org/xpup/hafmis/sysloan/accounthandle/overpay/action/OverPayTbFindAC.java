package org.xpup.hafmis.sysloan.accounthandle.overpay.action;

import java.util.HashMap;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.xpup.common.util.Pagination;
import org.xpup.hafmis.sysloan.accounthandle.overpay.dto.OverPayTbFindDTO;
import org.xpup.hafmis.sysloan.accounthandle.overpay.form.OverPayTbAF;


public class OverPayTbFindAC extends Action{
  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response) throws Exception {
    try{
      OverPayTbAF overPayTbAF = (OverPayTbAF) form;
      OverPayTbFindDTO overPayTbFindDTO = overPayTbAF.getOverPayTbFindDTO();
      String type="1";//用来判断是不是第一次进入页面
      Pagination pagination = new Pagination(0, 10, 1, "pl202.flow_head_id", "DESC",
          new HashMap(0));
      pagination.getQueryCriterions().put("overPayTbFindDTO", overPayTbFindDTO);
      pagination.getQueryCriterions().put("type", type);
      String paginationKey = getPaginationKey();

      request.getSession().setAttribute(paginationKey, pagination);
  }catch(Exception e){
    e.printStackTrace();
  }
    return mapping.findForward("overpaytb_show");
  }
  protected String getPaginationKey() {
    return OverPayTbShowAC.PAGINATION_KEY;
  }
}
