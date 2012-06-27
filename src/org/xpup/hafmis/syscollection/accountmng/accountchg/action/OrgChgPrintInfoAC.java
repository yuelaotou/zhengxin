package org.xpup.hafmis.syscollection.accountmng.accountchg.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.xpup.common.util.BSUtils;
import org.xpup.hafmis.syscollection.accountmng.accountchg.bsinterface.IOrgChgBS;
import org.xpup.hafmis.syscollection.accountmng.accountchg.dto.OrgChgDTO;

public class OrgChgPrintInfoAC extends Action {

  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    String printid = request.getParameter("printid");
    IOrgChgBS orgChgBS = (IOrgChgBS) BSUtils.getBusinessService("orgChgBS",
        this, mapping.getModuleConfig());
    OrgChgDTO orgChgDTO = orgChgBS.getOrgChgById(new Integer(printid));
    request.setAttribute("orgChgDTO", orgChgDTO);
    return mapping.findForward("printorgchginfo");
  }
}
