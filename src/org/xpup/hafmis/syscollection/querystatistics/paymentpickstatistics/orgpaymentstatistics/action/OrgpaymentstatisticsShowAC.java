package org.xpup.hafmis.syscollection.querystatistics.paymentpickstatistics.orgpaymentstatistics.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
import org.xpup.hafmis.orgstrct.dto.OfficeDto;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.syscollection.querystatistics.paymentpickstatistics.orgpaymentstatistics.bsinterface.IOrgpaymentstatisticsBS;
import org.xpup.hafmis.syscollection.querystatistics.paymentpickstatistics.orgpaymentstatistics.dto.OrgpaymentstatisticsDTO;
import org.xpup.hafmis.syscollection.querystatistics.paymentpickstatistics.orgpaymentstatistics.form.OrgpaymentAF;

public class OrgpaymentstatisticsShowAC extends Action {
  /**
   * @author wzq 2007-07-31
   */
  public static final String PAGINATION_KEY = "org.xpup.hafmis.syscollection.querystatistics.paymentpickstatistics.orgpaymentstatistics.action.OrgpaymentstatisticsShowAC";
  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response) throws Exception {
     
      OrgpaymentAF orgpaymentAF = new OrgpaymentAF();
      OrgpaymentstatisticsDTO dto=new OrgpaymentstatisticsDTO();
      SecurityInfo securityInfo=(SecurityInfo)request.getSession().getAttribute("SecurityInfo");
      String year=request.getParameter("year");
      try {
        Pagination pagination = getPagination(PAGINATION_KEY, request);
        PaginationUtils.updatePagination(pagination, request);

        IOrgpaymentstatisticsBS orgpaymentstatisticsBS = (IOrgpaymentstatisticsBS) 
        BSUtils.getBusinessService("orgpaymentstatisticsBS", this, mapping.getModuleConfig());
        pagination.getQueryCriterions().put("payMonth",year);

        orgpaymentAF = orgpaymentstatisticsBS.queryOrgpayInfoList(pagination, securityInfo);  //∑≈»Î request ¿Ô
        
      } catch(BusinessException bex){
        ActionMessages messages = new ActionMessages();
        messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(bex.getMessage(), false));
        saveErrors(request, messages);
      }
      List yearlist = orgpaymentAF.getYearlist();
      List yearlist1 = new ArrayList();
      Object obj = null;
      Iterator itr = yearlist.iterator();     
      while (itr.hasNext()) {
        obj = (Object) itr.next();   
        yearlist1.add(new org.apache.struts.util.LabelValueBean(obj.toString(),obj.toString()));
      }
      if(!orgpaymentAF.getTotallist().isEmpty()){
        dto=(OrgpaymentstatisticsDTO)orgpaymentAF.getTotallist().get(0);
      }
      request.setAttribute("yearlist", yearlist1);
      request.setAttribute("orgpaymentAF",orgpaymentAF); 
      request.setAttribute("totaldto",dto); 
      request.getSession().setAttribute("orgpaymentprintlist", orgpaymentAF.getList());
    return mapping.findForward("to_showTajsp");
  }

  private Pagination getPagination(String paginationKey, HttpServletRequest request) {
    Pagination pagination = (Pagination) request.getSession().getAttribute(paginationKey);
    if (pagination == null) {
      pagination = new Pagination(0, 10, 1, "null", "ASC", new HashMap(0));
      request.getSession().setAttribute(paginationKey, pagination);
    }
    return pagination;
  }

}
