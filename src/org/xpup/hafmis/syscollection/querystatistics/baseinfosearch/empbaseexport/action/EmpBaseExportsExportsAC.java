package org.xpup.hafmis.syscollection.querystatistics.baseinfosearch.empbaseexport.action;

import java.util.ArrayList;
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
import org.xpup.hafmis.syscollection.common.domain.entity.Org;
import org.xpup.hafmis.syscollection.querystatistics.baseinfosearch.empbaseexport.bsinterface.IEmpBaseExportsBS;
import org.xpup.hafmis.syscollection.querystatistics.baseinfosearch.empbaseexport.form.EmpBaseExportsAF;

public class EmpBaseExportsExportsAC extends Action {

  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    ActionMessages messages = null;
    try {
      EmpBaseExportsAF empBaseExportsAF = (EmpBaseExportsAF) form;
      String orgid = empBaseExportsAF.getOrgid().trim();
      IEmpBaseExportsBS empBaseExportsBS = (IEmpBaseExportsBS) BSUtils
          .getBusinessService("empBaseExportsBS", this, mapping
              .getModuleConfig());
      Org org = empBaseExportsBS.getOrg(orgid);
      if (org == null) {
        messages = new ActionMessages();
        messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
            "无此单位信息！！！", false));
        saveErrors(request, messages);
        return mapping.findForward("empbaseexport");
      }
      List list = new ArrayList();
      list.add(org.getId().toString());
      list.add(org.getOrgInfo().getName().toString());
      List expList = empBaseExportsBS.getEmployeeInfoAllList(orgid, list);
      if (expList != null && expList.size() > 0) {
        request.getSession().setAttribute("explist", expList);
        response.sendRedirect(request.getContextPath()
            + "/ExportServlet?ISCANSHU=false&EXP_NAME=empbaseexport");
      }
      return null;
    } catch (Exception e) {
      e.printStackTrace();
      messages = new ActionMessages();
      messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("导出失败！",
          false));
      saveErrors(request, messages);
    }
    return mapping.findForward("empbaseexport");
  }

}
