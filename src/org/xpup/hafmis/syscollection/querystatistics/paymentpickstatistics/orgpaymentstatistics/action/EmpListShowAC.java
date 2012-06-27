package org.xpup.hafmis.syscollection.querystatistics.paymentpickstatistics.orgpaymentstatistics.action;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import org.xpup.common.util.BSUtils;
import org.xpup.common.util.Pagination;
import org.xpup.common.util.PaginationUtils;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.syscollection.common.biz.emppop.bsinterface.IEmpPopBS;
import org.xpup.hafmis.syscollection.querystatistics.paymentpickstatistics.orgpaymentstatistics.bsinterface.IOrgpaymentstatisticsBS;


public class EmpListShowAC extends Action {

    public static final String PAGINATION_KEY = "org.xpup.hafmis.syscollection.querystatistics.paymentpickstatistics.orgpaymentstatistics.action.EmpListShowAC";

    public ActionForward execute(ActionMapping mapping, ActionForm form,
        HttpServletRequest request, HttpServletResponse response)
        throws Exception {
      try{

        String indexs=request.getParameter("indexs");
        if(indexs != null)
        request.getSession().setAttribute("indexs", indexs);
        SecurityInfo securityInfo=(SecurityInfo)request.getSession().getAttribute("SecurityInfo");
        String paginationKey = getPaginationKey();
        Pagination pagination = getPagination(paginationKey, request);
        PaginationUtils.updatePagination(pagination, request);     
        IOrgpaymentstatisticsBS orgpaymentstatisticsBS = (IOrgpaymentstatisticsBS) 
        BSUtils.getBusinessService("orgpaymentstatisticsBS", this, mapping.getModuleConfig());
        List emplist = orgpaymentstatisticsBS.findEmpList(pagination, securityInfo);
        request.setAttribute("employees", emplist);
      }catch(Exception e){
        e.printStackTrace();
      }
      return mapping.findForward(getForword());
    }

    protected String getForword() {
      return "employees";
    }

    private Pagination getPagination(String paginationKey,
        HttpServletRequest request) {
      Pagination pagination = (Pagination) request.getSession().getAttribute(
          paginationKey);
      if (pagination == null) {
        pagination = new Pagination(0, 10, 1, "emp.id", "ASC",
            new HashMap(0));

        request.getSession().setAttribute(paginationKey, pagination);
      }
      return pagination;
    }

    protected String getPaginationKey() {
      return PAGINATION_KEY;
    }

  }


