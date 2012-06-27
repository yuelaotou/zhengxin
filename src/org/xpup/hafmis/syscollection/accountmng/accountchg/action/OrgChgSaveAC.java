package org.xpup.hafmis.syscollection.accountmng.accountchg.action;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.actions.LookupDispatchAction;
import org.xpup.common.util.BSUtils;
import org.xpup.hafmis.common.util.BusiConst;
import org.xpup.hafmis.common.util.BusiTools;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.syscollection.accountmng.accountchg.bsinterface.IOrgChgBS;
import org.xpup.hafmis.syscollection.accountmng.accountchg.form.OrgChgAF;
import org.xpup.hafmis.syscollection.accountmng.accountopen.bsinterface.IOrgOpenAccountBS;
import org.xpup.hafmis.syscollection.accountmng.accountopen.form.OrgkhAF;
import org.xpup.hafmis.syscollection.common.domain.entity.Org;

public class OrgChgSaveAC extends LookupDispatchAction{
  protected Map getKeyMethodMap() {
    Map map = new HashMap();
    map.put("button.sure", "save");
    return map;
  }
  public ActionForward save(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    String openstatus = "";
    OrgChgAF af = (OrgChgAF) form;
    SecurityInfo securityInfo = (SecurityInfo) request.getSession()
    .getAttribute("SecurityInfo");
    try {
      IOrgChgBS orgChgBS = (IOrgChgBS) BSUtils
          .getBusinessService("orgChgBS", this, mapping.getModuleConfig());
      Org org = orgChgBS.findOrgChgById(new Integer(af.getOrg().getId().toString()));
      // 原单位开户状态
      int i = new Integer(org.getOrgInfo().getOpenstatus()).intValue();
      switch (i) {
        case 2:
          openstatus = "A";
        break;
        case 3:
          openstatus = "B";
        break;
        case 4:
          openstatus = "C";
        break;
      }
      // 判断状态是否相同
      if (openstatus.equals(af.getOrgChgLog().getChgType())) {
        String openstatusStr = (BusiTools.getBusiValue(Integer.parseInt(org.getOrgInfo().getOpenstatus()), BusiConst.ORGSTATE));
        ActionMessages messages = new ActionMessages();
        messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
            "该单位已经是"+openstatusStr+"状态", false));
        saveErrors(request, messages);
        Map orgchgtypeMap=BusiTools.listBusiProperty(BusiConst.ORGCHGTYPE);
        af.setOrgchgtypeMap(orgchgtypeMap);
        request.setAttribute("flag","1");
        return mapping.findForward("to_org_cha_new");
      }
      orgChgBS.saveOrgChg(af.getOrg().getId().toString(),af.getOrgChgLog().getChgType(),securityInfo);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return mapping.findForward("show_orgchg");
  }

}
