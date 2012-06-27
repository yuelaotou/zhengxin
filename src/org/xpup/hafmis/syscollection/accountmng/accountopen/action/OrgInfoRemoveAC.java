package org.xpup.hafmis.syscollection.accountmng.accountopen.action;

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
import org.xpup.hafmis.syscollection.accountmng.accountopen.bsinterface.IOrgOpenAccountBS;

public class OrgInfoRemoveAC extends Action {
  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    try{
      SecurityInfo securityInfo = (SecurityInfo) request.getSession()
      .getAttribute("SecurityInfo");
    String id = request.getParameter("id");
    IOrgOpenAccountBS orgOpenAccountBS = (IOrgOpenAccountBS) BSUtils
    .getBusinessService("orgOpenAccountBS", this, mapping.getModuleConfig());
    orgOpenAccountBS.removeOrgEmp(id,securityInfo);
    }catch(Exception e){
      ActionMessages messages = new ActionMessages();
      messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("É¾³ýÊ§°Ü£¡",
          false));
      saveErrors(request, messages);
    }
    return mapping.findForward(getForword());
  } 

  protected String getForword() {
    return "organization_open_show";
  }
  }


