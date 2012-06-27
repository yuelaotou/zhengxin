package org.xpup.hafmis.sysloan.loancallback.baddebtdestroy.action; 

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.xpup.common.util.BSUtils;
import org.xpup.hafmis.sysloan.loancallback.baddebtdestroy.bsinterface.IBadDebtDestroyBS;
import org.xpup.hafmis.sysloan.loancallback.baddebtdestroy.dto.BadDebtDestroyTaAFDTO;
import org.xpup.hafmis.sysloan.loancallback.baddebtdestroy.form.BadDebtDestroyTaAF;
 
public class BadDebtDestroyTbPrintWindowAC extends Action {
  
  public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
    
    BadDebtDestroyTaAF af = (BadDebtDestroyTaAF)form; 
    String headId = af.getHeadId();
    IBadDebtDestroyBS badDebtDestroyBS = (IBadDebtDestroyBS) BSUtils
    .getBusinessService("badDebtDestroyBS", this, mapping.getModuleConfig());
    BadDebtDestroyTaAFDTO badDebtDestroyTaAFDTO=new BadDebtDestroyTaAFDTO();
    badDebtDestroyTaAFDTO = badDebtDestroyBS.findPrintCallbackInfo(headId);
    request.setAttribute("badDebtDestroyTaAFDTO", badDebtDestroyTaAFDTO);
    return mapping.findForward("baddebtdestroy_cell");
  }
}
 