package org.xpup.hafmis.syscollection.chgbiz.chgorgrate.action;

import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
import org.xpup.hafmis.syscollection.chgbiz.chgorgrate.form.ChgOrgRateDoAF;
import org.xpup.hafmis.syscollection.chgbiz.chgorgrate.form.ChgOrgRateMaintainListAF;
import org.xpup.hafmis.syscollection.common.domain.entity.ChgOrgRate;

public class MaintainChgorgrateMaintainListAC extends LookupDispatchAction {

  public static final String PAGINATION_KEY = "org.xpup.hafmis.syscollection.chgbiz.chgorgrate.action.ShowChgorgrateMaintainListAC";

  protected Map getKeyMethodMap() {
    Map map = new HashMap();
    map.put("button.update", "modify");
    map.put("button.delete", "delete");
    map.put("button.use", "use");
    map.put("button.deluse", "deluse");
    map.put("button.print", "print");
    return map;
  }

  public ActionForward modify(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {

    IdAF idAF = (IdAF) form;
    String id = (String) idAF.getId();

    IChgorgrateBS chgorgrateBS = (IChgorgrateBS) BSUtils.getBusinessService(
        "chgorgrateBS", this, mapping.getModuleConfig());
    // 根据头表ID 查询AA201表信息
    ChgOrgRate chgOrgRate = chgorgrateBS.queryChgorgrateMessage(id);

    ChgOrgRateDoAF chgOrgRateDoAF = new ChgOrgRateDoAF();
    chgOrgRate.getOrg().setTemp_payMode(
        BusiTools.getBusiValue(chgOrgRate.getOrg().getPayMode().intValue(),
            BusiConst.ORGPAYWAY));
    chgOrgRateDoAF.setChgOrgRate(chgOrgRate);
    chgOrgRateDoAF.setType("2");
    request.setAttribute("chgOrgRateDoFromMaintainAF", chgOrgRateDoAF);

    return mapping.findForward("to_chgorgrate_modify");
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

      SecurityInfo securityInfo = (SecurityInfo) request.getSession()
          .getAttribute("SecurityInfo");

      HttpSession session = request.getSession();
      session.setAttribute("deleteflag", "2");

      chgorgrateBS.deleteChgOrgRate(new Integer(id), securityInfo);

      ChgOrgRateMaintainListAF chgOrgRateMaintainListAF = new ChgOrgRateMaintainListAF();
      request.setAttribute("chgOrgRateDoFromMaintainAF",
          chgOrgRateMaintainListAF);

      messages = new ActionMessages();
      messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("删除成功！",
          false));
      saveErrors(request, messages);
    } catch (BusinessException bex) {
      messages = new ActionMessages();
      messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("删除失败！",
          false));
      saveErrors(request, messages);
    }

    return mapping.findForward("to_chgorgrate_delete");
  }

  public ActionForward use(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {

    ActionMessages messages = null;

    try {
      IdAF idAF = (IdAF) form;
      String id = (String) idAF.getId();

      IChgorgrateBS chgorgrateBS = (IChgorgrateBS) BSUtils.getBusinessService(
          "chgorgrateBS", this, mapping.getModuleConfig());

      SecurityInfo securityInfo = (SecurityInfo) request.getSession()
          .getAttribute("SecurityInfo");

      HttpSession session = request.getSession();
      session.setAttribute("deleteflag", "2");

      // 根据头表ID 查询单位ID
      ChgOrgRate chgOrgRate = chgorgrateBS.queryChgorgrateMessage(id);

      chgorgrateBS.useChgOrgRate(chgOrgRate, securityInfo);

    } catch (BusinessException bex) {
      messages = new ActionMessages();
      messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(bex
          .getLocalizedMessage().toString(), false));
      saveErrors(request, messages);
    }

    return mapping.findForward("to_chgperson_use");
  }

  public ActionForward deluse(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {

    ActionMessages messages = null;
    try {
      IdAF idAF = (IdAF) form;
      String id = (String) idAF.getId();

      IChgorgrateBS chgorgrateBS = (IChgorgrateBS) BSUtils.getBusinessService(
          "chgorgrateBS", this, mapping.getModuleConfig());

      SecurityInfo securityInfo = (SecurityInfo) request.getSession()
          .getAttribute("SecurityInfo");

      HttpSession session = request.getSession();
      session.setAttribute("deleteflag", "2");

      chgorgrateBS.checkDelUseMessage(id, securityInfo);

    } catch (BusinessException bex) {
      messages = new ActionMessages();
      messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(bex
          .getLocalizedMessage().toString(), false));
      saveErrors(request, messages);
    }
    return mapping.findForward("to_chgperson_deluse");

  }

  /**
   * 打印汇缴比例调整审批表
   * 
   * @param mapping
   * @param form
   * @param request
   * @param response
   * @return
   * @throws Exception
   */
  public ActionForward print(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {

    try {
      IdAF idAF = (IdAF) form;
      String id = (String) idAF.getId();

      IChgorgrateBS chgorgrateBS = (IChgorgrateBS) BSUtils.getBusinessService(
          "chgorgrateBS", this, mapping.getModuleConfig());
      // 根据头表ID 查询AA201表信息
      ChgOrgRate chgOrgRate = chgorgrateBS.queryChgorgrateMessage(id);
      chgOrgRate.setCharacter(BusiTools.getBusiValue(Integer
          .parseInt(chgOrgRate.getOrg().getOrgInfo().getCharacter()),
          BusiConst.NATUREOFUNITS));
      request.setAttribute("chgOrgRate", chgOrgRate);
    } catch (BusinessException bex) {
      bex.printStackTrace();

    }
    return mapping.findForward("to_chgapproval_cell");
  }

}
