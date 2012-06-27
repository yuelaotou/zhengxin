package org.xpup.hafmis.sysloan.loanapply.endorsecontract.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.xpup.common.util.BSUtils;
import org.xpup.hafmis.sysloan.loanapply.endorsecontract.bsinterface.IEndorsecontractBS;

public class EndorsecontractTeFindAAC extends Action {
  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    response.setHeader("Cache-Control", "no-cache");
    response.setContentType("text/html;charset=UTF-8");

    IEndorsecontractBS endorsecontractBS = (IEndorsecontractBS) BSUtils
        .getBusinessService("endorsecontractBS", this, mapping
            .getModuleConfig());
    String text = "";
    String contractid = request.getParameter("contractid");
    String contractId = endorsecontractBS.queryPL121Contract(contractid);
    if (contractId != null && !contractId.equals("")) {
      text = "display_yga();";
    } else {
      text = "display_yg();";
    }
    response.getWriter().write(text);
    response.getWriter().close();
    return null;
  }
}
