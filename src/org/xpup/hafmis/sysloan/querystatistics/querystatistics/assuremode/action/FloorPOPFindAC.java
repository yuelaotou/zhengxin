package org.xpup.hafmis.sysloan.querystatistics.querystatistics.assuremode.action;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.xpup.common.util.Pagination;
import org.xpup.hafmis.sysloan.querystatistics.querystatistics.assuremode.form.FloorPOPNewAF;

/**
 * @author Õı“∞ 2007-10-13
 */
public class FloorPOPFindAC extends Action {

  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response) {
    try {
      FloorPOPNewAF floorPOPNewAF = (FloorPOPNewAF) form;
      HashMap criterions = makeCriterionsMap(floorPOPNewAF);
      Pagination pagination = new Pagination(0, 10, 1, "p006.floor_id", "DESC",
          criterions);
      String paginationKey = FloorPOPShowAC.PAGINATION_KEY;
      request.getSession().setAttribute(paginationKey, pagination);
      floorPOPNewAF.reset(mapping, request);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return mapping.findForward("floorPOPShowAC");
  }

  protected HashMap makeCriterionsMap(FloorPOPNewAF develepernewAF) {
    HashMap map = new HashMap();
    String floorName = develepernewAF.getFloorName().trim();
    if (!(floorName == null || "".equals(floorName))) {
      map.put("floorName", floorName);
    }
    return map;
  }
}