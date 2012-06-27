package org.xpup.hafmis.sysfinance.accmng.listacc.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.xpup.common.util.BSUtils;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.sysfinance.accmng.listacc.bsinterface.IListacctBS;

public class CheckSubjectcodeAAC extends Action {

  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    response.setContentType("text/html;charset=UTF-8");
    response.setHeader("Cache-Control", "no-cache");
    String text = null;
    String message = "";
    try {
      String subject = request.getParameter("subject");
      SecurityInfo securityInfo = (SecurityInfo) request.getSession()
          .getAttribute("SecurityInfo");
      IListacctBS listaccBS = (IListacctBS) BSUtils.getBusinessService(
          "listaccBS", this, mapping.getModuleConfig());
      message = listaccBS.checkSubjectcode(subject, securityInfo);
      text = "display('" + message + "')";
      response.getWriter().write(text);
      response.getWriter().close();

    } catch (Exception e) {
      e.printStackTrace();
    }
    return null;
  }
}