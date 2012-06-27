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
import org.xpup.hafmis.common.util.BusiConst;
import org.xpup.hafmis.common.util.BusiTools;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.syscollection.chgbiz.chgorgrate.bsinterface.IChgorgrateBS;
import org.xpup.hafmis.syscollection.common.domain.entity.Org;

public class OrgChgAAC extends Action {

  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    response.setContentType("text/html;charset=UTF-8");
    response.setHeader("Cache-Control", "no-cache");
    String text;
    try {

      String orgID = request.getParameter("orgID");

      IChgorgrateBS chgorgrateBS = (IChgorgrateBS) BSUtils.getBusinessService(
          "chgorgrateBS", this, mapping.getModuleConfig());
      SecurityInfo securityInfo = (SecurityInfo) request.getSession()
          .getAttribute("SecurityInfo");
      Org org = chgorgrateBS.queryOrgByorgIDYg(orgID, securityInfo);
      if (org == null) {
        throw new BusinessException("无此单位信息!");
      }
      String orgid = BusiTools.convertTenNumber(orgID);
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
        orgtransactortelephone = org.getOrgInfo().getTransactor()
            .getTelephone() == null ? "" : org.getOrgInfo().getTransactor()
            .getTelephone();
      }
      int orgcount = chgorgrateBS.queryPersonCountByOrgID(orgID);
      BigDecimal preRate = org.getOrgRate();
      BigDecimal prePay = org.getSumPay()==null?new BigDecimal(0):org.getSumPay();
      text = "displays('" + orgid + "','" + orgname + "','" + orgaddress
          + "','" + orgdep + "','" + orgcharacter + "','" + orgtransactorname
          + "','" + orgtransactortelephone + "','" + orgcount + "','" + preRate
          + "','" + prePay + "')";
      response.getWriter().write(text);
      response.getWriter().close();

    } catch (BusinessException be) {
      be.printStackTrace();
      text = "backErrors('" + be.getLocalizedMessage() + "')";
      response.getWriter().write(text);
      response.getWriter().close();

    } catch (Exception e) {
      e.printStackTrace();
    }

    return null;
  }
}
