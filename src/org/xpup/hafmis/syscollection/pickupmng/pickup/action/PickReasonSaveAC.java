package org.xpup.hafmis.syscollection.pickupmng.pickup.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.xpup.common.util.BSUtils;
import org.xpup.hafmis.syscollection.pickupmng.pickup.bsinterface.IPickupBS;
import org.xpup.hafmis.syscollection.pickupmng.pickup.form.PickReasonAF;

public class PickReasonSaveAC extends Action {
  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response) {
    try {
      IPickupBS pickBS = (IPickupBS) BSUtils.getBusinessService("pickupBS",
          this, mapping.getModuleConfig());
      PickReasonAF af = (PickReasonAF) form;
      String[] somerowArray = af.getSomerowArray();
      String[] destroyrowArray = af.getDestroyrowArray();
      String reason = "";
      for (int i = 0; i < somerowArray.length; i++) {
        reason += somerowArray[i] + ",";
      }
      for (int i = 0; i < destroyrowArray.length; i++) {
        reason += destroyrowArray[i] + ",";
      }
      if (reason != null && !reason.equals("")) {
        reason = reason.substring(0, reason.lastIndexOf(","));
        pickBS.updateAA306_1(reason);
      }else{
        pickBS.deleteAA306_1();
      }
    } catch (Exception s) {
      s.printStackTrace();
    }
    return mapping.findForward("pickReasonShowAC");
  }
}