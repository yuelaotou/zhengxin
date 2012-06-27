package org.xpup.hafmis.syscollection.pickupmng.partpickupcondition.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.xpup.common.util.BSUtils;
import org.xpup.hafmis.syscollection.pickupmng.partpickupcondition.bsinterface.IPartPickupConditionBS;
import org.xpup.hafmis.syscollection.pickupmng.partpickupcondition.dto.PartPickupConditionDTO;
import org.xpup.hafmis.syscollection.pickupmng.partpickupcondition.form.PartPickupConditionAF;

public class PartPickupConditionSaveAC extends Action {
  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response) {
    try{
      PartPickupConditionAF partPickupConditionAF=(PartPickupConditionAF)form;
      PartPickupConditionDTO partPickupConditionDTO=partPickupConditionAF.getPartPickupConditionDTO();
      IPartPickupConditionBS partPickupConditionBS = (IPartPickupConditionBS) BSUtils
      .getBusinessService("partPickupConditionBS", this, mapping.getModuleConfig());
      partPickupConditionBS.savePartPickupConditionInfo(partPickupConditionDTO);
    }catch(Exception e){
      e.printStackTrace();
    }
    return mapping.findForward("partpickupcondition_show");
  }
}
