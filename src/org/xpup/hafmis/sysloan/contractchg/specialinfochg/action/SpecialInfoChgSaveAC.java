package org.xpup.hafmis.sysloan.contractchg.specialinfochg.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.xpup.common.util.BSUtils;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.sysloan.contractchg.specialinfochg.bsinterface.ISpecialInfoChgBS;
import org.xpup.hafmis.sysloan.contractchg.specialinfochg.dto.SpecialInfoChgDTO;
import org.xpup.hafmis.sysloan.contractchg.specialinfochg.form.SpecialInfoChgAF;


public class SpecialInfoChgSaveAC  extends Action{
  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response) {
    try{
      SpecialInfoChgAF specialInfoChgAF=(SpecialInfoChgAF)form;
      SpecialInfoChgDTO specialInfoChgDTO=specialInfoChgAF.getSpecialInfoChgDTO();
      SecurityInfo securityInfo = (SecurityInfo) request.getSession()
      .getAttribute("SecurityInfo");
      ISpecialInfoChgBS specialInfoChgBS = (ISpecialInfoChgBS) BSUtils
      .getBusinessService("specialInfoChgBS", this, mapping.getModuleConfig()); 
      specialInfoChgBS.saveSpecialInfoChgInfo(specialInfoChgDTO, securityInfo);
    }catch(Exception e){
      e.printStackTrace();
    }
    return mapping.findForward("specialinfochg_show");
  }
}
