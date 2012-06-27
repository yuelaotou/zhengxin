package org.xpup.hafmis.syscollection.querystatistics.paymentpickstatistics.searchLackInfo.action;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.xpup.common.util.BSUtils;
import org.xpup.common.util.Pagination;
import org.xpup.hafmis.common.util.BusiTools;
import org.xpup.hafmis.syscollection.querystatistics.paymentpickstatistics.searchLackInfo.bsinterface.ISearchLackInfoBS;

public class CreateSearchLackInfoAC extends Action {
  
  
  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response){

    ISearchLackInfoBS searchLackInfoBS = (ISearchLackInfoBS) BSUtils
        .getBusinessService("searchLackInfoBS", this, mapping.getModuleConfig());
    searchLackInfoBS.createSearchLackInfo();
    Pagination pagination = (Pagination) request.getSession().getAttribute(
        ShowSearchLackInfoListAC.PAGINATION_KEY);
    String yearMonth = "";
    yearMonth = BusiTools.dateToString(new Date(), "yyyyMM");
    pagination.getQueryCriterions().put("yearMonth", yearMonth);
    return mapping.findForward("to_searchLackInfo_list");
  }
}
