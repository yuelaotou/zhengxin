package org.xpup.hafmis.syscollection.chgbiz.chgorgrate.action;

import java.math.BigDecimal;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.xpup.common.exception.BusinessException;
import org.xpup.common.util.BSUtils;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.syscollection.chgbiz.chgorgrate.bsinterface.IChgorgrateBS;
import org.xpup.hafmis.syscollection.chgbiz.chgorgrate.form.OrgChgAF;
import org.xpup.hafmis.syscollection.common.domain.entity.Org;
import org.xpup.hafmis.syscollection.common.domain.entity.OrgChg;

public class OrgChgSaveAC extends Action {

  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    OrgChgAF orgChgAF = (OrgChgAF)form;
    OrgChg orgChg = orgChgAF.getOrgChg();
    String update = (String)request.getParameter("update");
    if(update==null){
      update="insert";
    }
    IChgorgrateBS chgorgrateBS = (IChgorgrateBS) BSUtils.getBusinessService(
        "chgorgrateBS", this, mapping.getModuleConfig());
    SecurityInfo securityInfo = (SecurityInfo) request.getSession()
      .getAttribute("SecurityInfo");
    try {
      Org org = chgorgrateBS.queryOrgByorgID(orgChgAF.getOrgid());
      orgChg.setOrg(org);
      orgChg.setStatus("0");
      orgChg.setPrint("0");
      orgChg.setMake(securityInfo.getUserInfo().getRealName());
      if(update.equals("update")){
        chgorgrateBS.updateOrgChg(orgChg);
      }else{
        chgorgrateBS.saveOrgChg(orgChg);
      }
    } catch (BusinessException e) {
      e.printStackTrace();
    } catch (Exception ex) {
      ex.printStackTrace();
    }
    request.setAttribute("orgChgAF", orgChgAF);
    return mapping.findForward("orgChgShowAC");
  }
}
