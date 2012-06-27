package org.xpup.hafmis.sysloan.systemmaintain.hafoperateLog.action;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.xpup.common.util.Pagination;
import org.xpup.hafmis.syscollection.querystatistics.operationflow.orgoperationflow.form.OrgbusinessflowAF;
import org.xpup.hafmis.sysloan.systemmaintain.hafoperateLog.form.HafoperateLogAF;

public class HafoperatorLogFindAC extends Action {

  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {

    HafoperateLogAF f= (HafoperateLogAF)form;

    HashMap criterions = makeCriterionsMap(f);
    
    Pagination pagination = new Pagination(0, 10, 1,
        "plOperateLog.id", "DESC", criterions); 
    String paginationKey = getPaginationKey();
    request.getSession().setAttribute(paginationKey, pagination);
    modifyPagination(pagination);
    
    f.reset(mapping, request);

    return mapping.findForward(getForword());
  }

  protected String getForword() {
    return "goto_showAC";
  }

  protected String getPaginationKey() {
    return "org.xpup.hafmis.sysloan.systemmaintain.hafoperateLog.action.HafoperatorLogShowAC";
  }

  protected HashMap makeCriterionsMap(HafoperateLogAF form) {
    HashMap m = new HashMap();

    String opmodle = form.getOpmodle();
    if (!(opmodle == null || "".equals(opmodle))) {
      m.put("opmodle", opmodle);
    }

    String oper = form.getOper();
    if (!(oper == null || "".equals(oper))) {
      m.put("oper", oper);
    }
    
    String starttime = form.getStarttime();
    if (!(starttime == null || "".equals(starttime))) {
      m.put("starttime", starttime);
    }
    
    String endtime = form.getEndtime();
    if (!(endtime == null || endtime.length() == 0)){
      m.put("endtime", endtime);
    }

    String opaction = form.getOpaction();
    if (!(opaction == null || "".equals(opaction))) {
      m.put("opaction", opaction);
    }
    
    return m;
  }

  protected void modifyPagination(Pagination pagination) {
  }
}
