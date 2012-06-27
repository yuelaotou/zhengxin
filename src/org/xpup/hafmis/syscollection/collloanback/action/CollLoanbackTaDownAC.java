package org.xpup.hafmis.syscollection.collloanback.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.xpup.common.util.BSUtils;
import org.xpup.common.util.Pagination;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.syscollection.collloanback.bsinterface.ICollLoanbackBS;

public class CollLoanbackTaDownAC extends Action {
  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    ActionMessages messages = null;
    try{
      SecurityInfo securityInfo = (SecurityInfo) request.getSession()
      .getAttribute("SecurityInfo");
      ICollLoanbackBS collLoanbackBS = (ICollLoanbackBS) BSUtils
      .getBusinessService("collLoanbackBS", this, mapping.getModuleConfig());
      Pagination pagination = (Pagination)request.getSession().getAttribute(CollLoanbackTaShowAC.PAGINATION_KEY); 
      String batchNum=(String)request.getSession().getAttribute("batchNum");
      collLoanbackBS.kouMoney(batchNum,pagination,securityInfo);
    }catch(Exception e){
      messages = new ActionMessages();
      messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("¿Û¿îÊ§°Ü", false));
      saveErrors(request, messages);
    }
    return mapping.findForward("collloanbackta_show");
  }
}
