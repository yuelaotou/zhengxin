package org.xpup.hafmis.syscollection.paymng.orgaddpay.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.xpup.common.util.BSUtils;
import org.xpup.hafmis.syscollection.paymng.orgaddpay.bsinterface.IOrgaddpayBS;

public class OrgaddpayTbPickupdataWindowFindAAC extends Action {

  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    response.setContentType("text/html;charset=UTF-8");
    response.setHeader("Cache-Control", "no-cache");
    try {
      String orgId=request.getParameter("orgId");
      IOrgaddpayBS orgaddpayBS = (IOrgaddpayBS) BSUtils
      .getBusinessService("orgaddpayBS", this, mapping.getModuleConfig());
      String id=orgaddpayBS.queryInfoByOrgId(orgId);
      String text = null;
      text = "displays('" + id + "')";
      response.getWriter().write(text);
      response.getWriter().close();
    } catch (Exception e) {
      e.printStackTrace();
    }

    return null;
  }
}
