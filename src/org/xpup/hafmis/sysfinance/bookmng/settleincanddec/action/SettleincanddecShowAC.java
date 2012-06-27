package org.xpup.hafmis.sysfinance.bookmng.settleincanddec.action;

import java.util.HashMap;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.xpup.common.util.BSUtils;
import org.xpup.common.util.Pagination;
import org.xpup.common.util.PaginationUtils;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.sysfinance.bookmng.settleincanddec.bsinterface.ISettleincanddecBS;
import org.xpup.hafmis.sysfinance.bookmng.settleincanddec.form.SettleincanddecAF;

public class SettleincanddecShowAC extends Action {
  public static final String PAGINATION_KEY = "org.xpup.hafmis.sysfinance.bookmng.credencemodle.action.CredencemodleShowAC";

  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    // TODO Auto-generated method stub
    try {
      SettleincanddecAF settleincanddecAF = new SettleincanddecAF();
      Pagination pagination = getPagination(
          SettleincanddecShowAC.PAGINATION_KEY, request);
      PaginationUtils.updatePagination(pagination, request);
      SecurityInfo securityInfo = (SecurityInfo) request.getSession()
          .getAttribute("SecurityInfo");
      List list = null;
      ISettleincanddecBS settleincanddecBS = (ISettleincanddecBS) BSUtils
          .getBusinessService("settleincanddecBS", this, mapping
              .getModuleConfig());
      list = settleincanddecBS.querySettleIncAndDecList(securityInfo,
          pagination);
      settleincanddecAF.setList(list);
      request.setAttribute("settleincanddecAF", settleincanddecAF);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return mapping.findForward("to_show_settleincanddec");
  }

  private Pagination getPagination(String paginationKey,
      HttpServletRequest request) {
    Pagination pagination = (Pagination) request.getSession().getAttribute(
        paginationKey);
    if (pagination == null) {
      HashMap m = new HashMap();
      pagination = new Pagination(0, 10, 1, "a.by_subject_code  ", "ASC", m);
      request.getSession().setAttribute(paginationKey, pagination);
    }
    return pagination;
  }
}
