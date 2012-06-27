package org.xpup.hafmis.sysfinance.accounthandle.dayclear.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.xpup.common.util.BSUtils;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.sysfinance.accounthandle.dayclear.bsinterface.IDayclearBS;
import org.xpup.hafmis.sysfinance.accounthandle.dayclear.form.DayclearAF;

public class DayclearShowAC extends Action{

  public ActionForward execute(ActionMapping arg0, ActionForm arg1, HttpServletRequest arg2, HttpServletResponse arg3) throws Exception {
    DayclearAF lform=(DayclearAF)arg1;
    SecurityInfo securityInfo = (SecurityInfo)arg2.getSession()
    .getAttribute("SecurityInfo");
    IDayclearBS dayclearBS = (IDayclearBS) BSUtils.getBusinessService("dayclearBS_f", this, arg0.getModuleConfig()); 
    lform.setDate(dayclearBS.getBizDate(securityInfo.getUserInfo().getUsername(), securityInfo.getUserInfo().getUserIp()));
    return arg0.findForward("dayclear");
  }

}
