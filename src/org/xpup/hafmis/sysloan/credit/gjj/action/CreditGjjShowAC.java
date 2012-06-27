package org.xpup.hafmis.sysloan.credit.gjj.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.xpup.common.exception.BusinessException;
import org.xpup.common.util.BSUtils;
import org.xpup.common.util.Pagination;
import org.xpup.common.util.PaginationUtils;
import org.xpup.hafmis.orgstrct.dto.OfficeDto;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.sysloan.credit.report.bsinterface.ICreditBS;
import org.xpup.hafmis.sysloan.credit.report.form.CreditAF;
import org.xpup.security.common.domain.Userslogincollbank;

public class CreditGjjShowAC extends Action {
	public static final String PAGINATION_KEY = "org.xpup.hafmis.sysloan.credit.report.action.CreditShowAC";

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		ActionMessages messages = null;
		try {
			SecurityInfo securityInfo = (SecurityInfo) request.getSession()
					.getAttribute("SecurityInfo");

			// 办事处下拉
			List officeList = securityInfo.getOfficeList();
			List officeList1 = new ArrayList();
			OfficeDto officedto = null;
			Iterator itr1 = officeList.iterator();
			while (itr1.hasNext()) {
				officedto = (OfficeDto) itr1.next();
				officeList1.add(new org.apache.struts.util.LabelValueBean(
						officedto.getOfficeName(), officedto.getOfficeCode()));
			}
			// 放款银行下拉框
			List loanBankNameList = new ArrayList();
			List bangkList = securityInfo.getDkUserBankList();
			Userslogincollbank userslogincollbank = null;
			Iterator bank = bangkList.iterator();
			while (bank.hasNext()) {
				userslogincollbank = (Userslogincollbank) bank.next();
				loanBankNameList.add(new org.apache.struts.util.LabelValueBean(
						userslogincollbank.getCollBankName(),
						userslogincollbank.getCollBankId().toString()));
			}
			request.getSession().setAttribute("loanBankNameList",
					loanBankNameList);
			request.getSession().setAttribute("officeList", officeList1);

			Pagination pagination = getPagination(PAGINATION_KEY, request);
			PaginationUtils.updatePagination(pagination, request);
			ICreditBS creditBS = (ICreditBS) BSUtils.getBusinessService(
					"creditBS", this, mapping.getModuleConfig());

			CreditAF creditAF = new CreditAF();

			String officeCode = (String) pagination.getQueryCriterions().get(
					"officeCode");
			String loanBankName = (String) pagination.getQueryCriterions().get(
					"loanBankName");
			String shujutiquriqi = (String) pagination.getQueryCriterions()
					.get("shujutiquriqi");
			creditAF.setLoanBankName(loanBankName);
			creditAF.setOfficeCode(officeCode);
			creditAF.setShujutiquriqi(shujutiquriqi);

			List list = creditBS.getCredit(pagination);
			creditAF.setList(list);

			request.setAttribute("creditGjjAF", creditAF);
		} catch (BusinessException bex) {
			messages = new ActionMessages();
			messages.add(ActionMessages.GLOBAL_MESSAGE,
					new ActionMessage(bex.getMessage(), false));
			saveErrors(request, messages);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mapping.findForward("creditgjj");
	}

	private Pagination getPagination(String paginationKey,
			HttpServletRequest request) {
		Pagination pagination = (Pagination) request.getSession().getAttribute(
				paginationKey);
		if (pagination == null) {
			HashMap m = new HashMap();
			pagination = new Pagination(0, 1000, 1, "credit.id", "ASC", m);
			request.getSession().setAttribute(paginationKey, pagination);
		}
		return pagination;
	}

}
