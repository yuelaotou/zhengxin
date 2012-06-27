package org.xpup.hafmis.sysloan.credit.report.action;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.actions.LookupDispatchAction;
import org.xpup.common.exception.BusinessException;
import org.xpup.common.util.BSUtils;
import org.xpup.common.util.Pagination;
import org.xpup.hafmis.sysloan.credit.report.bsinterface.ICreditBS;
import org.xpup.hafmis.sysloan.credit.report.form.CreditAF;

public class CreditFindAC extends LookupDispatchAction {

	@Override
	protected Map getKeyMethodMap() {
		Map map = new HashMap();
		map.put("button.search", "search");
		map.put("button.credit.shengchengshuju", "create");
		map.put("button.credit.shanchubaowenshuju", "delete");
		return map;
	}

	public ActionForward search(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		try {
			CreditAF creditAF = (CreditAF) form;
			HashMap criterions = makeCriterionsMap(creditAF);
			Pagination pagination = new Pagination(0, 1000, 1, "credit.id", "ASC", criterions);
			request.getSession().setAttribute(CreditShowAC.PAGINATION_KEY, pagination);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mapping.findForward("creditShowAC");
	}

	public ActionForward create(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		ActionMessages messages = null;
		try {
			CreditAF creditAF = (CreditAF) form;
			HashMap criterions = makeCriterionsMap(creditAF);
			Pagination pagination = new Pagination(0, 1000, 1, "credit.id", "ASC", criterions);
			request.getSession().setAttribute(CreditShowAC.PAGINATION_KEY, pagination);
			String shujutiquriqi = creditAF.getShujutiquriqi();
			ICreditBS creditBS = (ICreditBS) BSUtils.getBusinessService("creditBS", this, mapping.getModuleConfig());

			creditBS.createCredit(shujutiquriqi);
			request.getSession().setAttribute(CreditShowAC.PAGINATION_KEY, null);
			messages = new ActionMessages();
			messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("生成数据成功，可以选择查询", false));
			// x
			saveErrors(request, messages);
		} catch (BusinessException bex) {
			messages = new ActionMessages();
			messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(bex.getMessage(), false));
			saveErrors(request, messages);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mapping.findForward("creditShowAC");
	}

	public ActionForward delete(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		ActionMessages messages = null;
		try {
			CreditAF creditAF = (CreditAF) form;
			HashMap criterions = makeCriterionsMap(creditAF);
			Pagination pagination = new Pagination(0, 1000, 1, "credit.id", "ASC", criterions);
			request.getSession().setAttribute(CreditShowAC.PAGINATION_KEY, pagination);
			String shujutiquriqi = creditAF.getShujutiquriqi();
			ICreditBS creditBS = (ICreditBS) BSUtils.getBusinessService("creditBS", this, mapping.getModuleConfig());

			creditBS.deleteCredit(shujutiquriqi);
			request.getSession().setAttribute(CreditShowAC.PAGINATION_KEY, null);
			messages = new ActionMessages();
			messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("删除数据成功，可以选择重新生成", false));
			saveErrors(request, messages);
		} catch (BusinessException bex) {
			messages = new ActionMessages();
			messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(bex.getMessage(), false));
			saveErrors(request, messages);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mapping.findForward("creditShowAC");
	}

	protected HashMap makeCriterionsMap(CreditAF form) {
		HashMap m = new HashMap();

		/** 数据提取月份 */
		String shujutiquriqi = form.getShujutiquriqi();
		if (shujutiquriqi != null && !"".equals(shujutiquriqi.trim())) {
			m.put("shujutiquriqi", shujutiquriqi.trim());
		}

		/** 报文生成日期 */
		String baowenshengchengriqi = form.getBaowenshengchengriqi();
		if (baowenshengchengriqi != null && !"".equals(baowenshengchengriqi.trim())) {
			m.put("baowenshengchengriqi", baowenshengchengriqi.trim());
		}

		/** 办事处 */
		String officeCode = form.getOfficeCode();
		if (officeCode != null && !"".equals(officeCode.trim())) {
			m.put("officeCode", officeCode.trim());
		}

		/** 放款银行 */
		String loanBankName = form.getLoanBankName();
		if (loanBankName != null && !"".equals(loanBankName.trim())) {
			m.put("loanBankName", loanBankName.trim());
		}

		/** 合同账号 */
		String yewuhao = form.getYewuhao();
		if (yewuhao != null && !"".equals(yewuhao.trim())) {
			m.put("yewuhao", yewuhao.trim());
		}

		/** 数据状态 */
		String jiluzhuangtai = form.getJiluzhuangtai();
		if (jiluzhuangtai != null && !"".equals(jiluzhuangtai.trim())) {
			m.put("jiluzhuangtai", jiluzhuangtai.trim());
		}
		return m;
	}

}