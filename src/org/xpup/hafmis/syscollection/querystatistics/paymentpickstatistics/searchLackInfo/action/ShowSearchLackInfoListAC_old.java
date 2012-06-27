package org.xpup.hafmis.syscollection.querystatistics.paymentpickstatistics.searchLackInfo.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
import org.xpup.hafmis.common.util.BusiConst;
import org.xpup.hafmis.common.util.BusiTools;
import org.xpup.hafmis.orgstrct.dto.OfficeDto;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.syscollection.querystatistics.paymentpickstatistics.searchLackInfo.bsinterface.ISearchLackInfoBS;
import org.xpup.hafmis.syscollection.querystatistics.paymentpickstatistics.searchLackInfo.dto.SearchLackInfoContentDTO;
import org.xpup.hafmis.syscollection.querystatistics.paymentpickstatistics.searchLackInfo.dto.SearchLackInfoHeadDTO;
import org.xpup.hafmis.syscollection.querystatistics.paymentpickstatistics.searchLackInfo.form.SearchLackInfoListAF;
import org.xpup.security.common.domain.Userslogincollbank;

public class ShowSearchLackInfoListAC_old extends Action {

  public static final String PAGINATION_KEY = "org.xpup.hafmis.syscollection.querystatistics.paymentpickstatistics.searchLackInfo.action.ShowSearchLackInfoListAC_old";

  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response) {

    ActionMessages messages = null;
    SearchLackInfoListAF af = (SearchLackInfoListAF) form;
    try {
      /**
       * ·ÖÒ³
       */
      Pagination pagination = getPagination(PAGINATION_KEY, request);
      PaginationUtils.updatePagination(pagination, request);

      SecurityInfo securityInfo = (SecurityInfo) request.getSession()
          .getAttribute("SecurityInfo");

      ISearchLackInfoBS searchLackInfoBS = (ISearchLackInfoBS) BSUtils
          .getBusinessService("searchLackInfoBS", this, mapping
              .getModuleConfig());

      List list = null;

      if (!pagination.getQueryCriterions().isEmpty()) {
        list = searchLackInfoBS.findSearchLackInfoListByCriterions_oldsys(
            pagination, securityInfo);

        af.setList(list);
      } else {
        af.setList(null);
      }
      request.setAttribute("searchLackInfoListAF", af);

    } catch (BusinessException bex) {
      messages = new ActionMessages();
      messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(bex
          .getLocalizedMessage().toString(), false));
      saveErrors(request, messages);
    } catch (Exception ex) {
      ex.printStackTrace();
    }
    return mapping.findForward("to_searchLackInfo_list");
  }

  private Pagination getPagination(String paginationKey,
      HttpServletRequest request) {
    Pagination pagination = (Pagination) request.getSession().getAttribute(
        paginationKey);
    if (pagination == null) {
      pagination = new Pagination(0, 10, 1, "aa305.org_id", "DESC",
          new HashMap(0));
      request.getSession().setAttribute(paginationKey, pagination);
    }

    return pagination;
  }

}
