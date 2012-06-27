package org.xpup.security.commonlogin.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.xpup.common.util.BSUtils;
import org.xpup.hafmis.orgstrct.domain.enums.DutyEnum;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.syscollection.accounthandle.dayclear.bsinterface.IDayclearBS;
import org.xpup.security.commonlogin.form.LoginActionForm;
import org.xpup.security.wsf.bizsrvc.IUserControlBS;

public class ChangeBizDateAction extends Action {

	@Override
	public ActionForward execute(ActionMapping arg0, ActionForm arg1, HttpServletRequest arg2, HttpServletResponse arg3)
			throws Exception {
		IUserControlBS securityControlBS = (IUserControlBS) BSUtils.getBusinessService("securityControlBS", this,
				arg0.getModuleConfig());
		LoginActionForm lform = (LoginActionForm) arg1;
		String userName = lform.getUserName();
		SecurityInfo securityInfo = securityControlBS.getSecurityInfo(userName, arg2.getRemoteAddr());
		securityControlBS.updateBizDate(lform.getUserName(), lform.getOpSystemType(), lform.getBizdate());
		HttpSession session = arg2.getSession(false);
		IDayclearBS dayclearBS = (IDayclearBS) BSUtils.getBusinessService("dayclearBS", this, arg0.getModuleConfig());
		List list = new ArrayList();
		DutyEnum duty = securityInfo.getUserInfo().getDuty();// 操作员职务
		if (!"".equals(duty) && duty != null) {
			if (duty.equals(DutyEnum.职员)) {
				list = dayclearBS.getBankInfoList(securityInfo.getUserInfo().getUsername(), lform.getOpSystemType());
			}
		}
		arg2.getSession().setAttribute("bankDateList", list);
		session.setAttribute("bookId", lform.getBookId());
		session.setAttribute("opSystemType", lform.getOpSystemType());
		return arg0.findForward("toIndexAction");
	}

}
