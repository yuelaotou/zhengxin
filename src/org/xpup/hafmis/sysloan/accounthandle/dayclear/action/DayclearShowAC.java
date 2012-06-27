package org.xpup.hafmis.sysloan.accounthandle.dayclear.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.xpup.common.util.BSUtils;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.sysloan.accounthandle.dayclear.bsinterface.IDayclearBS;
import org.xpup.hafmis.sysloan.accounthandle.dayclear.form.DayclearAF;

public class DayclearShowAC extends Action{

  public ActionForward execute(ActionMapping arg0, ActionForm arg1, HttpServletRequest request, HttpServletResponse arg3) throws Exception {
    DayclearAF lform=(DayclearAF)arg1;
    
    SecurityInfo securityInfo = (SecurityInfo)request.getSession()
    .getAttribute("SecurityInfo");
    IDayclearBS dayclearBS = (IDayclearBS) BSUtils.getBusinessService("dayclearBSl", this, arg0.getModuleConfig());
    //lform.setDate(dayclearBS.getBizDate(securityInfo.getUserInfo().getUsername(), securityInfo.getUserInfo().getUserIp()));
    List list = dayclearBS.getBankInfoList(securityInfo.getUserInfo().getUsername());
    request.setAttribute("List", list);
    return arg0.findForward("dayclear");
  }

}
