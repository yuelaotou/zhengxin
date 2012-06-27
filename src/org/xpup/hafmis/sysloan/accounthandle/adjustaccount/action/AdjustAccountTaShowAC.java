package org.xpup.hafmis.sysloan.accounthandle.adjustaccount.action;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.xpup.hafmis.common.util.BusiConst;
import org.xpup.hafmis.common.util.BusiTools;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.sysloan.accounthandle.adjustaccount.form.AdjustAccountAF;

/**
 * 显示办理错帐调整页的Action
 * 
 * @author 付云峰
 */
public class AdjustAccountTaShowAC extends Action {

  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    saveToken(request);
    AdjustAccountAF adjustAccountAF = new AdjustAccountAF();
    adjustAccountAF.getAdjustAccountTaInfoDTO().setAutoOverPay("0");
    int plLoanReturnType = 0;
    try {
      SecurityInfo securityInfo = (SecurityInfo) request.getSession()
          .getAttribute("SecurityInfo");
      // 贷款还款类型1.中心为主2.银行为主
      int temp_plLoanReturnType = securityInfo.getPlLoanReturnType();
      // 根据权限中的还款类型判断以哪为主
      if (temp_plLoanReturnType == BusiConst.PLLOANRETURNTYPE_CENTER) {
        plLoanReturnType = 1;// 中心为主
      } else if (temp_plLoanReturnType == BusiConst.PLLOANRETURNTYPE_BANK) {
        plLoanReturnType = 2;// 银行为主
      }
      Map autoOverPayMap = BusiTools.listBusiProperty(BusiConst.YesNo);
      adjustAccountAF.setAutoOverPayMap(autoOverPayMap);
    } catch (Exception e) {
      e.printStackTrace();
    }
    request.setAttribute("adjustAccountAF", adjustAccountAF);
    request.getSession().setAttribute("plLoanReturnType", new Integer(plLoanReturnType));
    return mapping.findForward("to_adjustaccountta_show");
  }

}
