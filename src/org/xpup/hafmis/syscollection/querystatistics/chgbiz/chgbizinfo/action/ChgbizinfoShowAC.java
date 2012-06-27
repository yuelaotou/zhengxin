package org.xpup.hafmis.syscollection.querystatistics.chgbiz.chgbizinfo.action;

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
import org.xpup.hafmis.syscollection.querystatistics.chgbiz.chgbizinfo.bsinterface.IChgbizinfoBS;
import org.xpup.hafmis.syscollection.querystatistics.chgbiz.chgbizinfo.form.ChgbizinfoAF;
import org.xpup.hafmis.syscollection.querystatistics.chgbiz.chgorgrate.bsinterface.IChgorgrateBS;
import org.xpup.hafmis.syscollection.querystatistics.chgbiz.chgorgrate.form.ChgorgrateAF;

/**
 * @author 于庆丰 2007-07-19
 */
public class ChgbizinfoShowAC extends Action {

  public static final String PAGINATION_KEY = "org.xpup.hafmis.syscollection.querystatistics.chgbiz.chgbizinfo.action.ChgbizinfoShowAC";

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
      IChgbizinfoBS chgbizinfoBS = (IChgbizinfoBS) BSUtils.getBusinessService(
          "chgbizinfoBS", this, mapping.getModuleConfig());
      ChgbizinfoAF chgbizinfoAF = new ChgbizinfoAF();
      chgbizinfoAF = chgbizinfoBS.findChgorgrateByCriterions(pagination,
          securityInfo);
      // 业务状态下拉框
//      Map map = BusiTools.listBusiProperty(BusiConst.CHGTYPESTATUS);
//      chgorgrateAF.setMap(map);
      request.setAttribute("chgbizinfoAF", chgbizinfoAF);
      request.setAttribute("bizDate", (String) pagination.getQueryCriterions().get("chgMonth"));
      // 用于打印的list
      request.getSession().setAttribute("thechgbizinfoList",
          chgbizinfoAF.getAlllist());
      request.setAttribute("list1", chgbizinfoAF.getList1());
      request.setAttribute("list2", chgbizinfoAF.getList2());
      request.setAttribute("list3", chgbizinfoAF.getList3());
      request.setAttribute("data_1", chgbizinfoAF.getData_1());
      request.setAttribute("data_2", chgbizinfoAF.getData_2());
      request.setAttribute("data_3", chgbizinfoAF.getData_3());
      request.setAttribute("data_4", chgbizinfoAF.getData_4());
      request.setAttribute("data_5", chgbizinfoAF.getData_5());
      request.setAttribute("data_6", chgbizinfoAF.getData_6());
      request.setAttribute("data_7", chgbizinfoAF.getData_7());
      request.setAttribute("data_8", chgbizinfoAF.getData_8());
      request.setAttribute("data_9", chgbizinfoAF.getOrgId());
      request.setAttribute("data_10", chgbizinfoAF.getOrgName());
      request.setAttribute("data_11", chgbizinfoAF.getPerson());
      request.setAttribute("data_12", chgbizinfoAF.getTel());
      request.setAttribute("data_13", chgbizinfoAF.getData_9());
      request.setAttribute("data_14", chgbizinfoAF.getData_10());
      chgbizinfoAF.reset(mapping, request);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return mapping.findForward("to_ChgbizinfoList.jsp");
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
