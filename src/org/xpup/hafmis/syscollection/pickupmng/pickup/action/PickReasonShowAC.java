package org.xpup.hafmis.syscollection.pickupmng.pickup.action;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.xpup.common.util.BSUtils;
import org.xpup.hafmis.common.util.BusiConst;
import org.xpup.hafmis.common.util.BusiTools;
import org.xpup.hafmis.syscollection.pickupmng.pickup.bsinterface.IPickupBS;
import org.xpup.hafmis.syscollection.pickupmng.pickup.dto.PickReasonDTO;
import org.xpup.hafmis.syscollection.pickupmng.pickup.form.PickReasonAF;

public class PickReasonShowAC extends Action {

  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response) {
    try {
      IPickupBS pickBS = (IPickupBS) BSUtils.getBusinessService("pickupBS",
          this, mapping.getModuleConfig());
      Map some = BusiTools.listBusiProperty(BusiConst.SOMEPICK);// 部分提取
      Map destroy = BusiTools.listBusiProperty(BusiConst.DISTROYPICK);// 销户提取
      List reason = new ArrayList();
      PickReasonDTO dto = null;
      PickReasonAF af = new PickReasonAF();
      Iterator re = some.entrySet().iterator();
      while (re.hasNext()) {
        dto = new PickReasonDTO();
        Map.Entry entry = (Map.Entry) re.next();
        dto.setValue(entry.getValue().toString());
        dto.setKey(entry.getKey().toString());
        reason.add(dto);
      }
      af.setSomelist(reason);
      reason = new ArrayList();
      Iterator dis = destroy.entrySet().iterator();
      while (dis.hasNext()) {
        dto = new PickReasonDTO();
        Map.Entry entry = (Map.Entry) dis.next();
        dto.setValue(entry.getValue().toString());
        dto.setKey(entry.getKey().toString());
        reason.add(dto);
      }
      af.setDestroylist(reason);
      String srea = pickBS.getAA306_1();
      request.setAttribute("srea", srea);
      request.setAttribute("pickReasonAF", af);
    } catch (Exception s) {
      s.printStackTrace();
    }
    return mapping.findForward("pickreason");
  }
}
