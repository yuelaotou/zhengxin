package org.xpup.hafmis.sysloan.querystatistics.querystatistics.assuremode.action;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.xpup.common.util.BSUtils;
import org.xpup.common.util.Pagination;
import org.xpup.common.util.PaginationUtils;
import org.xpup.hafmis.sysloan.querystatistics.querystatistics.assuremode.bsinterface.IAssureModeBS;
import org.xpup.hafmis.sysloan.querystatistics.querystatistics.assuremode.form.FloorPOPNewAF;

/**
 * @author 王野 2007-10-13
 */
public class FloorPOPShowAC extends Action {
  public static final String PAGINATION_KEY = "org.xpup.hafmis.sysloan.querystatistics.querystatistics.assuremode.action.FloorPOPShowAC";

  /**
   * 带出楼盘名称
   */
  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response) {

    FloorPOPNewAF floorPOPNewAF = new FloorPOPNewAF();
    try {
      String indexs = request.getParameter("indexs");
      if (indexs != null) {
        request.getSession().removeAttribute(FloorPOPShowAC.PAGINATION_KEY);
        request.getSession().setAttribute("indexs", indexs);
      }
      Pagination pagination = getPagination(PAGINATION_KEY, request);
      PaginationUtils.updatePagination(pagination, request);
      IAssureModeBS assureModeBS = (IAssureModeBS) BSUtils.getBusinessService(
          "assureModeBS", this, mapping.getModuleConfig());
      floorPOPNewAF = assureModeBS.findFloorInfoList(pagination);
      request.setAttribute("floorPOPNewAF", floorPOPNewAF);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return mapping.findForward("floorpop_new");
  }

  private Pagination getPagination(String paginationKey,
      HttpServletRequest request) {
    Pagination pagination = (Pagination) request.getSession().getAttribute(
        paginationKey);
    if (pagination == null) {
      pagination = new Pagination(0, 10, 1, "p006.floor_id", "DESC",
          new HashMap(0));
      request.getSession().setAttribute(paginationKey, pagination);
    }
    return pagination;
  }
}