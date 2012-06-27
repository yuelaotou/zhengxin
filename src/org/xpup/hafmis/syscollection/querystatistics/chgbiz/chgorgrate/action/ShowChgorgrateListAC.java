package org.xpup.hafmis.syscollection.querystatistics.chgbiz.chgorgrate.action;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.xpup.common.util.BSUtils;
import org.xpup.common.util.Pagination;
import org.xpup.common.util.PaginationUtils;
import org.xpup.hafmis.common.util.BusiConst;
import org.xpup.hafmis.common.util.BusiTools;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.syscollection.common.domain.entity.ChgOrgRate;
import org.xpup.hafmis.syscollection.paymng.paysure.form.PaymentAF;
import org.xpup.hafmis.syscollection.querystatistics.chgbiz.chgorgrate.bsinterface.IChgorgrateBS;
import org.xpup.hafmis.syscollection.querystatistics.chgbiz.chgorgrate.form.ChgorgrateAF;

/**
 * @author 于庆丰 2007-07-19
 */
public class ShowChgorgrateListAC extends Action {

  public static final String PAGINATION_KEY = "org.xpup.hafmis.syscollection.querystatistics.chgbiz.chgorgrate.action.ShowChgorgrateListAC";

  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    // TODO Auto-generated method stub
    try {
      /**
       * 分页
       */
      // 获取权限
      SecurityInfo securityInfo = (SecurityInfo) request.getSession()
          .getAttribute("SecurityInfo");
      Pagination pagination = getPagination(PAGINATION_KEY, request);
      PaginationUtils.updatePagination(pagination, request);
      IChgorgrateBS chgorgrateBS = (IChgorgrateBS) BSUtils.getBusinessService(
          "chgorgrateBS", this, mapping.getModuleConfig());
      ChgorgrateAF chgorgrateAF = new ChgorgrateAF();
      chgorgrateAF = chgorgrateBS.findChgorgrateByCriterions(pagination,
          securityInfo);
      // 业务状态下拉框
      Map map = BusiTools.listBusiProperty(BusiConst.CHGTYPESTATUS);
      chgorgrateAF.setMap(map);
      request.setAttribute("thechgorgrateAF", chgorgrateAF);
      // 用于打印的list
      request.getSession().setAttribute("thechgorgrateList",
          chgorgrateAF.getAlllist());
      chgorgrateAF.reset(mapping, request);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return mapping.findForward("to_ChgorgrateList.jsp");
  }

  private Pagination getPagination(String paginationKey,
      HttpServletRequest request) {
    Pagination pagination = (Pagination) request.getSession().getAttribute(
        paginationKey);
    if (pagination == null) {
    
      pagination = new Pagination(0, 10, 1, "chgOrgRate.id", "ASC",
          new HashMap(0));
      request.getSession().setAttribute(paginationKey, pagination);
    }
    return pagination;
  }
}
