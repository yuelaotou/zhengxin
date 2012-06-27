package org.xpup.hafmis.sysloan.credit.gjj.action;

import java.io.BufferedOutputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.sql.ResultSet;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.LookupDispatchAction;
import org.xpup.common.util.BSUtils;
import org.xpup.common.util.Pagination;
import org.xpup.common.util.PaginationUtils;
import org.xpup.hafmis.common.form.IdAF;
import org.xpup.hafmis.common.util.BusiTools;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.sysloan.credit.report.bsinterface.ICreditBS;

public class CreditGjjMaintainAC extends LookupDispatchAction {

	@Override
	protected Map getKeyMethodMap() {
		Map map = new HashMap();
		map.put("button.credit.zhengchangshuju", "zhengchangshuju");
		map.put("button.credit.shanchushuju", "shanchushuju");
		map.put("button.credit.jiandujiancha", "jiandujiancha");
		map.put("button.credit.shanchuhetong", "shanchuhetong");
		map.put("button.credit.huifuhetong", "huifuhetong");
		return map;
	}

	/**
	 * 正常数据提取
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward zhengchangshuju(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		try {

			Pagination pagination = getPagination(
					CreditGjjShowAC.PAGINATION_KEY, request);
			PaginationUtils.updatePagination(pagination, request);
			ICreditBS creditBS = (ICreditBS) BSUtils.getBusinessService(
					"creditBS", this, mapping.getModuleConfig());

			ResultSet rs = creditBS.exportNormal(pagination);
			String shujutiquriqi = (String) pagination.getQueryCriterions()
					.get("shujutiquriqi");
			String filename = "E3330121080001" + shujutiquriqi + "001" + "1"
					+ "000";

			response.setContentType("text/plain");
			response.addHeader("Content-Disposition", "attachment;filename="
					+ URLEncoder.encode(filename, "UTF-8") + ".txt");
			OutputStream toClient = new BufferedOutputStream(
					response.getOutputStream());
			while (rs.next()) {
				toClient.write(rs.getString("data").concat("\r\n")
						.getBytes("UTF-8"));
			}
			toClient.flush();
			toClient.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 删除数据提取
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward shanchushuju(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		IdAF idaf = (IdAF) form;
		String[] rowArray = idaf.getRowArray();
		try {

			Pagination pagination = getPagination(
					CreditGjjShowAC.PAGINATION_KEY, request);
			pagination.getQueryCriterions().put("rowArray", rowArray);
			PaginationUtils.updatePagination(pagination, request);
			ICreditBS creditBS = (ICreditBS) BSUtils.getBusinessService(
					"creditBS", this, mapping.getModuleConfig());
			ResultSet rs = creditBS.exportDelete(pagination);
			String date = BusiTools.dateToString(new Date(), "yyyyMMdd");

			String filename = "E3330121080001" + date + "0" + "8" + "001";

			response.setContentType("text/plain");
			response.addHeader("Content-Disposition", "attachment;filename="
					+ URLEncoder.encode(filename, "UTF-8") + ".txt");
			OutputStream toClient = new BufferedOutputStream(
					response.getOutputStream());
			while (rs.next()) {
				toClient.write(rs.getString("data").concat("\r\n")
						.getBytes("UTF-8"));
			}
			toClient.flush();
			toClient.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 监督监察数据提取
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward jiandujiancha(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		try {
			Pagination pagination = getPagination(
					CreditGjjShowAC.PAGINATION_KEY, request);
			PaginationUtils.updatePagination(pagination, request);
			if (pagination.getQueryCriterions().get("batchNum") != null) {
				request.setAttribute("batchNum", pagination
						.getQueryCriterions().get("batchNum").toString());
			}
			SecurityInfo securityInfo = (SecurityInfo) request.getSession()
					.getAttribute("SecurityInfo");
			String userName = securityInfo.getRealName();
			request.setAttribute("userName", userName);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mapping.findForward("credit");
	}

	/**
	 * 删除合同操作，就是报送时不提取这样的合同
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward shanchuhetong(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		IdAF idaf = (IdAF) form;
		String[] rowArray = idaf.getRowArray();
		try {
			Pagination pagination = getPagination(
					CreditGjjShowAC.PAGINATION_KEY, request);
			pagination.getQueryCriterions().put("rowArray", rowArray);
			pagination.getQueryCriterions().put("status", "1");
			PaginationUtils.updatePagination(pagination, request);
			ICreditBS creditBS = (ICreditBS) BSUtils.getBusinessService(
					"creditBS", this, mapping.getModuleConfig());
			creditBS.dealWithContract(pagination);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mapping.findForward("creditShowAC");
	}

	/**
	 * 恢复合同操作
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward huifuhetong(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		IdAF idaf = (IdAF) form;
		String[] rowArray = idaf.getRowArray();
		try {
			Pagination pagination = getPagination(
					CreditGjjShowAC.PAGINATION_KEY, request);
			pagination.getQueryCriterions().put("rowArray", rowArray);
			pagination.getQueryCriterions().put("status", "0");
			PaginationUtils.updatePagination(pagination, request);
			ICreditBS creditBS = (ICreditBS) BSUtils.getBusinessService(
					"creditBS", this, mapping.getModuleConfig());
			creditBS.dealWithContract(pagination);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mapping.findForward("creditShowAC");
	}

	private Pagination getPagination(String paginationKey,
			HttpServletRequest request) {
		Pagination pagination = (Pagination) request.getSession().getAttribute(
				paginationKey);
		if (pagination == null) {
			HashMap m = new HashMap();
			pagination = new Pagination(0, 10, 1, "credit.id", "ASC", m);
			request.getSession().setAttribute(paginationKey, pagination);
		}
		return pagination;
	}
}