package org.xpup.hafmis.syscollection.querystatistics.logsearch.businesslogsearch.action;
import java.util.HashMap;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.xpup.common.util.Pagination;
import org.xpup.hafmis.syscollection.querystatistics.logsearch.businesslogsearch.form.BusinesslogsearchAF;



public class BusinesslogsearchFindAC extends Action {


    public ActionForward execute(ActionMapping mapping, ActionForm form,
        HttpServletRequest request, HttpServletResponse response)
        throws Exception {

      BusinesslogsearchAF f= (BusinesslogsearchAF)form;

      HashMap criterions = makeCriterionsMap(f);
      Pagination pagination = new Pagination(0, 10, 1,
          "a319.id", "DESC", criterions); 
      String paginationKey = getPaginationKey();
      request.getSession().setAttribute(paginationKey, pagination);
      modifyPagination(pagination);
      
      f.reset(mapping, request);

      return mapping.findForward(getForword());
    }

    protected String getForword() {
      return "to_businesslogsearchShowAC";
    }

    protected String getPaginationKey() {
     
      return "org.xpup.hafmis.syscollection.querystatistics.logsearch.businesslogsearch.action.BusinesslogsearchShowAC";
    }

    protected HashMap makeCriterionsMap(BusinesslogsearchAF form) {
      HashMap m = new HashMap();

      String bizType = form.getBusinessType();
      if (!(bizType == null || "".equals(bizType))) {
        m.put("bizType", bizType);
      }
   
      String beginMonth = form.getBeginMonth();
      if (!(beginMonth == null || "".equals(beginMonth))) {
        m.put("beginMonth", beginMonth);
      }
      
      String endMonth = form.getEndMonth();
      if (!(endMonth == null || "".equals(endMonth))) {
        m.put("endMonth", endMonth);
      }     
      String operator = form.getOperator();
      if (!(operator == null || "".equals(operator))) {
        m.put("operator", operator);
      }
      String payStatus = form.getPayStatus();
      if (!(payStatus == null || "".equals(payStatus))) {
        m.put("payStatus", payStatus);
      }
      return m;
    }

    protected void modifyPagination(Pagination pagination) {
    }
  }


