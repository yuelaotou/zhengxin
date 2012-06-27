package org.xpup.hafmis.sysfinance.accounthandle.monthclear.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.xpup.common.util.BSUtils;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.sysfinance.accounthandle.dayclear.bsinterface.IDayclearBS;
import org.xpup.hafmis.sysfinance.accounthandle.monthclear.form.MonthclearAF;

public class MonthclearShowAC extends Action{

  public ActionForward execute(ActionMapping arg0, ActionForm arg1, HttpServletRequest arg2, HttpServletResponse arg3) throws Exception {
    MonthclearAF lform=(MonthclearAF)arg1;
    SecurityInfo securityInfo = (SecurityInfo)arg2.getSession()
    .getAttribute("SecurityInfo");
    IDayclearBS dayclearBS = (IDayclearBS) BSUtils.getBusinessService("dayclearBS_f", this, arg0.getModuleConfig()); 
    lform.setDate(dayclearBS.getBizDate(securityInfo.getUserInfo().getUsername(), securityInfo.getBookId()));
    return arg0.findForward("monthclear");
  }

}
