package org.xpup.hafmis.sysloan.loancallback.relievecontract.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.xpup.common.exception.BusinessException;
import org.xpup.common.util.BSUtils;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.sysloan.loancallback.relievecontract.bsinterface.IRelieveContractBS;

public class RelieveContractTaSaveAC extends Action{
  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response) throws Exception{
    ActionMessages messages = new ActionMessages();
    try{
      SecurityInfo securityInfo = (SecurityInfo) request.getSession()
      .getAttribute("SecurityInfo");
      IRelieveContractBS relieveContractBS = (IRelieveContractBS) BSUtils
      .getBusinessService("relieveContractBS", this, mapping.getModuleConfig());
      String contractId=(String)request.getParameter("relieveContractTaDTO.contractId");
      relieveContractBS.saveRelieveContractTa(contractId,securityInfo);
    }catch (BusinessException be) {
      messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(be.getLocalizedMessage(),
          false));
      saveErrors(request, messages);
    }catch(Exception e){
      e.printStackTrace();
    }
    return mapping.findForward("relievecontractta_show");
  }
}
