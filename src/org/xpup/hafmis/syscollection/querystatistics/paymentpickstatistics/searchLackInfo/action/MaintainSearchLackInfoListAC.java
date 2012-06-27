package org.xpup.hafmis.syscollection.querystatistics.paymentpickstatistics.searchLackInfo.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.LookupDispatchAction;
import org.xpup.common.exception.BusinessException;
import org.xpup.common.util.BSUtils;
import org.xpup.common.util.Pagination;
import org.xpup.common.util.PaginationUtils;
import org.xpup.hafmis.common.form.IdAF;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.syscollection.querystatistics.paymentpickstatistics.searchLackInfo.bsinterface.ISearchLackInfoBS;
import org.xpup.hafmis.syscollection.querystatistics.paymentpickstatistics.searchLackInfo.dto.SearchLackInfoContentDTO;

public class MaintainSearchLackInfoListAC extends LookupDispatchAction {

  public static final String PAGINATION_KEY = "org.xpup.hafmis.syscollection.querystatistics.paymentpickstatistics.searchLackInfo.action.ShowSearchLackInfoListAC";

  protected Map getKeyMethodMap() {
    Map map = new HashMap();
    map.put("button.print.tzd", "printone");
    map.put("button.printList", "print");
    return map;
  }

  public ActionForward printone(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    try {
      HttpSession session = request.getSession();
      IdAF idaf = (IdAF) form;
      String orgid = idaf.getId().toString();

      ISearchLackInfoBS searchLackInfoBS = (ISearchLackInfoBS) BSUtils
          .getBusinessService("searchLackInfoBS", this, mapping
              .getModuleConfig());

      SecurityInfo securityInfo = (SecurityInfo) request.getSession()
          .getAttribute("SecurityInfo");
      Pagination pagination = getPagination(PAGINATION_KEY, request);
      SearchLackInfoContentDTO searchLackInfoContentDTO = searchLackInfoBS
          .findSearchLackInfoOneByCriterions(orgid,pagination,securityInfo);

      session
          .setAttribute("searchLackInfoContentDTO", searchLackInfoContentDTO);
      session.setAttribute("bizDate", securityInfo.getUserInfo().getBizDate());
    } catch (BusinessException bex) {
      bex.printStackTrace();
    }
    return mapping.findForward("to_searchLackInfo_one_report");
  }

  public ActionForward print(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {

    Pagination pagination = getPagination(PAGINATION_KEY, request);
    PaginationUtils.updatePagination(pagination, request);
    
    List listcount = (List)pagination.getQueryCriterions().get("listcount");
    HttpSession session = request.getSession();
    session
    .setAttribute("listcount", listcount);
    
    return mapping.findForward("to_searchLackInfo_report");
  }

  private Pagination getPagination(String paginationKey,
      HttpServletRequest request) {
    Pagination pagination = (Pagination) request.getSession().getAttribute(
        paginationKey);
    if (pagination == null) {
      pagination = new Pagination(0, 10, 1, "org2_.id", "DESC",
          new HashMap(0));
      request.getSession().setAttribute(paginationKey, pagination);
    }

    return pagination;
  }

}
