package org.xpup.hafmis.sysfinance.accmng.listacc.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.xpup.common.util.BSUtils;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.sysfinance.accmng.listacc.bsinterface.IListacctBS;
import org.xpup.hafmis.sysfinance.accmng.listacc.form.ListaccAF;

public class CheckupAccExportAC extends Action {
  public static final String PAGINATION_KEY = "org.xpup.hafmis.sysfinance.accmng.listacc.action.CheckupAccExportAC";
  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response) {
    ActionMessages messages = null;
    try {
      ListaccAF af = (ListaccAF) form;
      SecurityInfo securityInfo = (SecurityInfo) request.getSession().getAttribute("SecurityInfo");
      IListacctBS listaccBS = (IListacctBS) BSUtils.getBusinessService(
          "listaccBS", this, mapping.getModuleConfig());
      List expList = listaccBS.queryExpDetailAccList(af, securityInfo);
      if (expList != null && expList.size() > 0) {
        request.getSession().setAttribute("explist", expList);
        response.sendRedirect(request.getContextPath()
            + "/ExportServlet?ISCANSHU=false&EXP_NAME=checkupaccexport_exp");
      }
      return null;
    } catch (Exception e) {
      e.printStackTrace();
      messages = new ActionMessages();
      messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("µ¼³öÊ§°Ü£¡",
          false));
      saveErrors(request, messages);
    }
    return mapping.findForward("to_checkupaccexport");
  }
}
