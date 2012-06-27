package org.xpup.hafmis.syscollection.chgbiz.chgperson.action;

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
import org.xpup.hafmis.syscollection.chgbiz.chgperson.bsinterface.IChgpersonDoBS;

public class DeluseChgpersonAC extends Action{
  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    saveToken(request);
    ActionMessages messages = null;
    try{
       String id = request.getParameter("chgpersonHeadID");
       
       IChgpersonDoBS chgpersonDoBS = (IChgpersonDoBS) BSUtils
          .getBusinessService("chgpersonDoBS", this, mapping.getModuleConfig());

       SecurityInfo securityInfo = (SecurityInfo) request.getSession()
           .getAttribute("SecurityInfo");
          
       chgpersonDoBS.deluserChgPersonMessage(id,securityInfo);

    }catch(Exception e){
      messages = new ActionMessages();
      messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("≥∑œ˙∆Ù”√ ß∞‹£°£°", false));
      saveErrors(request, messages);
    }
    return mapping.findForward("to_chgperson_deluse");
  }
}
