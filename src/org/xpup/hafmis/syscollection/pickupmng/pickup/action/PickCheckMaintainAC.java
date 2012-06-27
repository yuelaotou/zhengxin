package org.xpup.hafmis.syscollection.pickupmng.pickup.action;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.LookupDispatchAction;
import org.xpup.common.util.BSUtils;
import org.xpup.hafmis.common.form.IdAF;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.syscollection.pickupmng.pickup.bsinterface.IPickupBS;

public class PickCheckMaintainAC extends LookupDispatchAction {

  public ActionForward pass(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response) {

    try {
      SecurityInfo securityInfo = (SecurityInfo) request.getSession()
          .getAttribute("SecurityInfo");
      IdAF af = (IdAF) form;
      String id = af.getId().toString();
      IPickupBS pbs = (IPickupBS) BSUtils.getBusinessService("pickupBS", this,
          mapping.getModuleConfig());
      pbs.setPickCheckStatus(securityInfo.getUserInfo().getBizDate(), "0", id);
    } catch (Exception s) {
      s.printStackTrace();
    }
    return mapping.findForward("pickCheckShowAC");
  }

  public ActionForward delete(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {

    try {
      IdAF af = (IdAF) form;
      String id = af.getId().toString();
      IPickupBS pbs = (IPickupBS) BSUtils.getBusinessService("pickupBS", this,
          mapping.getModuleConfig());
      pbs.setPickCheckStatus("", "1", id);
    } catch (Exception s) {
      s.printStackTrace();
    }
    return mapping.findForward("pickCheckShowAC");
  }

  protected Map getKeyMethodMap() {
    Map map = new HashMap();
    map.put("button.approval.pass", "pass");
    map.put("button.approval.delete", "delete");
    return map;
  }
}
