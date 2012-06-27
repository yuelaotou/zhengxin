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

public class PartPickupConditionShowAC extends Action {
  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    try{
      PartPickupConditionAF partPickupConditionAF=new PartPickupConditionAF();
      IPartPickupConditionBS partPickupConditionBS = (IPartPickupConditionBS) BSUtils
      .getBusinessService("partPickupConditionBS", this, mapping.getModuleConfig());
      PartPickupConditionDTO partPickupConditionDTO=partPickupConditionBS.findPartPickupConditionInfo();
      if(partPickupConditionDTO!=null){
        partPickupConditionAF.setPartPickupConditionDTO(partPickupConditionDTO);
      }
      request.setAttribute("partPickupConditionAF", partPickupConditionAF);
    }catch(Exception e){
      e.printStackTrace();
    }
    return mapping.findForward("to_partpickupcondition_show");
  }
}
