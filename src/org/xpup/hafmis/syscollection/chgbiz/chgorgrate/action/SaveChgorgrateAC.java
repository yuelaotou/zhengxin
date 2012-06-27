package org.xpup.hafmis.syscollection.chgbiz.chgorgrate.action;

import java.math.BigDecimal;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.xpup.hafmis.common.util.BusiConst;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.syscollection.chgbiz.chgorgrate.form.ChgOrgRateDoAF;

public class SaveChgorgrateAC extends Action {

  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {

    HttpSession session = request.getSession();
    BigDecimal newSumPay = (BigDecimal)session.getAttribute("newSumPay");

    ChgOrgRateDoAF chgOrgRateDoAF = (ChgOrgRateDoAF)session.getAttribute("chgOrgRateDoAF_temp");
    
    chgOrgRateDoAF.getChgOrgRate().setSumPay(newSumPay);
    
//  吴洪涛修改 2008-3-18 单位_汇缴比例调整
    SecurityInfo securityInfo = (SecurityInfo) request.getSession()
    .getAttribute("SecurityInfo");
    int isOrgEdition = securityInfo.getIsOrgEdition();
    if (isOrgEdition == BusiConst.ORG_OR_CENTER_INFO_ORG)// 为单位版{
    {
      chgOrgRateDoAF.getChgOrgRate().setOrgEdition("yes");
    } 
    
    session.setAttribute("chgOrgRateDoAF", chgOrgRateDoAF);
    
    return mapping.findForward("to_chgorgrateDo_sure");
  }

}
