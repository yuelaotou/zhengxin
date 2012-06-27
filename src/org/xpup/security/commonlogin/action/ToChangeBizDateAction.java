package org.xpup.security.commonlogin.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.xpup.common.util.BSUtils;
import org.xpup.hafmis.common.util.BusiTools;
import org.xpup.hafmis.common.util.bizlog.BusiLogConst;
import org.xpup.hafmis.orgstrct.domain.enums.DutyEnum;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.syscollection.accounthandle.dayclear.bsinterface.IDayclearBS;
import org.xpup.security.common.domain.MenuItem;
import org.xpup.security.common.domain.UsersloginBook;
import org.xpup.security.commonlogin.form.LoginActionForm;
import org.xpup.security.wsf.bizsrvc.IMenuControlBS;
import org.xpup.security.wsf.bizsrvc.IUserControlBS;

/**
 * @author liuy
 */
public class ToChangeBizDateAction extends Action {

	@Override
	public ActionForward execute(ActionMapping arg0, ActionForm arg1, HttpServletRequest arg2, HttpServletResponse arg3)
			throws Exception {
		HttpSession session = arg2.getSession(false);
		String bizdate = "";
		LoginActionForm lform = (LoginActionForm) arg1;
		String userName = lform.getUserName();
		String systemId = ""; // lform.getSystemId();
		IUserControlBS securityControlBS = (IUserControlBS) BSUtils.getBusinessService("securityControlBS", this,
				arg0.getModuleConfig());
		SecurityInfo securityInfo = securityControlBS.getSecurityInfo(userName, arg2.getRemoteAddr());
		String opSystemType = "";
		IMenuControlBS menuControlBS = (IMenuControlBS) BSUtils.getBusinessService("securityControlBS",
				session.getServletContext());
		IDayclearBS dayclearBS = (IDayclearBS) BSUtils.getBusinessService("dayclearBS", this, arg0.getModuleConfig());
		List list = new ArrayList();
		String date = BusiTools.dateToString(new Date(), "yyyyMMdd");
		DutyEnum duty = securityInfo.getUserInfo().getDuty();// 操作员职务
		if (!"".equals(duty) && duty != null) {
			if (duty.equals(DutyEnum.职员)) {
				list = dayclearBS.getBankInfoList(securityInfo.getUserInfo().getUsername());
			}
		}
		arg2.getSession().setAttribute("bankDateList", list);
		arg2.getSession().setAttribute("Duty", "1");
		if (session.getAttribute("systemId") == null) {
			List systems = menuControlBS.findAllRootMenu(userName);

			if (systems.size() > 0) {
				opSystemType = ((MenuItem) systems.get(0)).getOpSystemType();
			}
		} else {
			systemId = (String) session.getAttribute("systemId");
			opSystemType = menuControlBS.queryMenuItem(userName, systemId).getOpSystemType();
		}
		securityInfo.getUserInfo().setBizDate(date);
		securityInfo.getUserInfo().setPlbizDate(date);

		List userBookList = securityInfo.getUserBookList();
		if (userBookList != null && userBookList.size() > 0) {
			String bookId = ((UsersloginBook) userBookList.get(0)).getBookId();
			;
			lform.setBookName(securityControlBS.getUserBookName(bookId));
			securityInfo.getUserInfo().setFbizDate(securityControlBS.getFnBizDate(userName, bookId));
		} else {
			lform.setBookName("");
		}
		if (opSystemType.equals(Integer.toString((BusiLogConst.OP_SYSTEM_TYPE_MANAGER)))) {
			bizdate = "";
			lform.setIsChooseBook("");
		} else if (opSystemType.equals(Integer.toString((BusiLogConst.OP_SYSTEM_TYPE_COLLECTION)))) {
			bizdate = securityInfo.getUserInfo().getBizDate();
			lform.setIsChooseBook("");
		} else if (opSystemType.equals(Integer.toString((BusiLogConst.OP_SYSTEM_TYPE_LOAN)))) {
			bizdate = securityInfo.getUserInfo().getPlbizDate();
			lform.setIsChooseBook("");
		} else if (opSystemType.equals(Integer.toString((BusiLogConst.OP_SYSTEM_TYPE_FINANCE)))) {
			bizdate = securityInfo.getUserInfo().getFbizDate();
			lform.setIsChooseBook("1");
		}
		List systemsList = menuControlBS.findAllRootMenu(userName);
		lform.setSystemId(systemId);
		lform.setUserName(userName);
		lform.setOpSystemType(opSystemType);
		lform.setBizdate(bizdate);
		lform.setSystemsList(systemsList);
		lform.setUserBookList(userBookList);
		return arg0.findForward("changebizdate");
	}

}
