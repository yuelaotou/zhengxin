package org.xpup.hafmis.syscollection.chgbiz.chgorgrate.action;

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
import org.xpup.common.exception.BusinessException;
import org.xpup.common.util.BSUtils;
import org.xpup.hafmis.common.form.IdAF;
import org.xpup.hafmis.common.util.BusiConst;
import org.xpup.hafmis.common.util.BusiTools;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.syscollection.chgbiz.chgorgrate.bsinterface.IChgorgrateBS;
import org.xpup.hafmis.syscollection.chgbiz.chgorgrate.form.OrgChgAF;
import org.xpup.hafmis.syscollection.common.domain.entity.Org;
import org.xpup.hafmis.syscollection.common.domain.entity.OrgChg;

public class OrgChgMainTainTbAC extends LookupDispatchAction {

  protected Map getKeyMethodMap() {
    Map map = new HashMap();
    map.put("button.update", "update");
    map.put("button.delete", "delete");
    map.put("button.submit", "submit");
    map.put("button.delsubmit", "delsubmit");
    map.put("button.print", "print");
    return map;
  }

  public ActionForward update(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    IdAF idAF = (IdAF) form;
    String id = (String) idAF.getId();
    IChgorgrateBS chgorgrateBS = (IChgorgrateBS) BSUtils.getBusinessService(
        "chgorgrateBS", this, mapping.getModuleConfig());
    OrgChg orgChg = chgorgrateBS.queryOrgChgById(id);
    OrgChgAF orgChgAF = new OrgChgAF();
    orgChgAF.setOrgChg(orgChg);
    Org org = orgChg.getOrg();
    String orgname = org.getOrgInfo().getName();
    String orgaddress = org.getOrgInfo().getAddress() == null ? "" : org
        .getOrgInfo().getAddress();
    String orgdep = "";
    String orgcharacter = BusiTools.getBusiValue(Integer.parseInt(org
        .getOrgInfo().getCharacter()), BusiConst.NATUREOFUNITS);
    String orgtransactorname = "";
    String orgtransactortelephone = "";
    if (org.getOrgInfo().getTransactor() != null) {
      orgtransactorname = org.getOrgInfo().getTransactor().getName() == null ? ""
          : org.getOrgInfo().getTransactor().getName();
      orgtransactortelephone = org.getOrgInfo().getTransactor().getTelephone() == null ? ""
          : org.getOrgInfo().getTransactor().getTelephone();
    }
    int orgcount = chgorgrateBS.queryPersonCountByOrgID(org.getId().toString());
    orgChgAF.setOrgid(BusiTools.convertTenNumber(org.getId().toString()));
    orgChgAF.setOrgname(orgname);
    orgChgAF.setOrgaddress(orgaddress);
    orgChgAF.setOrgdep(orgdep);
    orgChgAF.setOrgcharacter(orgcharacter);
    orgChgAF.setOrgtransactorname(orgtransactorname);
    orgChgAF.setOrgtransactortelephone(orgtransactortelephone);
    orgChgAF.setOrgcount(orgcount + "");
    request.setAttribute("orgChgAF", orgChgAF);
    request.setAttribute("update", "update");
    return mapping.findForward("orgchgta");
  }

  public ActionForward delete(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    ActionMessages messages = null;
    try {
      IdAF idAF = (IdAF) form;
      String id = idAF.getId().toString();
      IChgorgrateBS chgorgrateBS = (IChgorgrateBS) BSUtils.getBusinessService(
          "chgorgrateBS", this, mapping.getModuleConfig());
      OrgChg orgChg = chgorgrateBS.queryOrgChgById(id);
      chgorgrateBS.deleteOrgChg(orgChg);
    } catch (BusinessException bex) {
      messages = new ActionMessages();
      messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("É¾³ýÊ§°Ü£¡",
          false));
      saveErrors(request, messages);
    }
    return mapping.findForward("orgChgShowTbAC");
  }

  public ActionForward submit(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    ActionMessages messages = null;
    try {
      IdAF idAF = (IdAF) form;
      String id = (String) idAF.getId();

      IChgorgrateBS chgorgrateBS = (IChgorgrateBS) BSUtils.getBusinessService(
          "chgorgrateBS", this, mapping.getModuleConfig());
      chgorgrateBS.submitOrgChgById(id);
    } catch (BusinessException bex) {
      messages = new ActionMessages();
      messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(bex
          .getLocalizedMessage().toString(), false));
      saveErrors(request, messages);
    }
    return mapping.findForward("orgChgShowTbAC");
  }

  public ActionForward delsubmit(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    ActionMessages messages = null;
    try {
      IdAF idAF = (IdAF) form;
      String id = (String) idAF.getId();
      IChgorgrateBS chgorgrateBS = (IChgorgrateBS) BSUtils.getBusinessService(
          "chgorgrateBS", this, mapping.getModuleConfig());
      chgorgrateBS.delSubmitOrgChgById(id);
    } catch (BusinessException bex) {
      messages = new ActionMessages();
      messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(bex
          .getLocalizedMessage().toString(), false));
      saveErrors(request, messages);
    }
    return mapping.findForward("orgChgShowTbAC");
  }

  public ActionForward print(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    try {
      IdAF idAF = (IdAF) form;
      String id = (String) idAF.getId();
      IChgorgrateBS chgorgrateBS = (IChgorgrateBS) BSUtils.getBusinessService(
          "chgorgrateBS", this, mapping.getModuleConfig());
      SecurityInfo securityInfo = (SecurityInfo) request.getSession()
      .getAttribute("SecurityInfo");
      String bizDate = securityInfo.getUserInfo().getBizDate();
      OrgChg orgChg = chgorgrateBS.queryOrgChgById(id);
      request.setAttribute("orgChg", orgChg);
      request.setAttribute("bizDate", bizDate);
      request.setAttribute("url", "orgChgShowTbAC.do");
    } catch (BusinessException bex) {
      bex.printStackTrace();
    }
    return mapping.findForward("orgchgprint");
  }

}
