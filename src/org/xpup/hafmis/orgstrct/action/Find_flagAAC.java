package org.xpup.hafmis.orgstrct.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.xpup.common.util.BSUtils;
import org.xpup.common.util.Pagination;
import org.xpup.hafmis.orgstrct.bizsrvc.IToSecurityMngBS;

public class Find_flagAAC extends Action {

  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    response.setContentType("text/html;charset=UTF-8");
    response.setHeader("Cache-Control", "no-cache");
    try {
      String code = request.getParameter("code");
      int flag=0;
      IToSecurityMngBS toSecurityMngBS = (IToSecurityMngBS) BSUtils
      .getBusinessService("toSecurityMngBS", this, mapping.getModuleConfig());
      flag=toSecurityMngBS.findBank(code);
      response.getWriter().print(flag);
    } catch (Exception e) {
      e.printStackTrace();
    }

    return null;
  }

}