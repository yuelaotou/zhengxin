package org.xpup.hafmis.sysloan.querystatistics.checkqueryplfn.action;

import java.util.ArrayList;
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
import org.xpup.hafmis.sysloan.querystatistics.checkqueryplfn.bsinterface.ICheckQueryPlFnBS;
import org.xpup.hafmis.sysloan.querystatistics.loanaccountquery.particulargl.bsinterface.IParticularglBS;
import org.xpup.hafmis.sysloan.querystatistics.loanaccountquery.particulargl.form.ParticularglTaAF;

public class CollFnComparisonOrgAccountPrintAC extends Action {
  public static final String PAGINATION_KEY = "org.xpup.hafmis.sysloan.querystatistics.checkqueryplfn.action.CheckQueryPlFnTBShowAC";
  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    try{
      String contractId=request.getParameter("contractId");
      String contractIdEnd=request.getParameter("contractIdEnd");
      String timest=request.getParameter("timeSt");
      String timeend=request.getParameter("timeEnd");
      Pagination pagination = getPagination(PAGINATION_KEY, request);
      SecurityInfo securityInfo = (SecurityInfo) request.getSession().getAttribute(
      "SecurityInfo");
      pagination.getQueryCriterions().put("borrowercontractid", contractId);
      pagination.getQueryCriterions().put("bizdateB", timest);
      pagination.getQueryCriterions().put("bizdateE", timeend);
      request.getSession().setAttribute("bizdateB", timest);
      request.getSession().setAttribute("bizdateE", timeend);
      PaginationUtils.updatePagination(pagination, request);
      ICheckQueryPlFnBS checkQueryPlFnBS = (ICheckQueryPlFnBS) BSUtils.getBusinessService("checkQueryPlFnBS", this, mapping.getModuleConfig());
      List contracIds=new ArrayList();
      contracIds=checkQueryPlFnBS.showContrctList_print("", "", timest, timeend, contractId,contractIdEnd, securityInfo);
      pagination.getQueryCriterions().put("contracIds", contracIds);
      IParticularglBS particularglBS = (IParticularglBS) BSUtils.getBusinessService("particularglBS", this, mapping.getModuleConfig());
      ParticularglTaAF particularglTaAF=new ParticularglTaAF();
      particularglTaAF=particularglBS.showparticularglListTe(pagination,pagination,securityInfo);
     
      List printList=particularglBS.changePrintList(particularglTaAF.getAlllist());
      request.getSession().setAttribute("countList_wsh", printList);
    }catch(Exception e){
      e.printStackTrace();
    }
    return mapping.findForward("checkQueryPlFnprint");
  }
  private Pagination getPagination(String paginationKey,HttpServletRequest request) {
    Pagination pagination = (Pagination) request.getSession().getAttribute(
        paginationKey);
    if (pagination == null) {
      pagination = new Pagination(0, 10, 1, " pl202.biz_date ", "DESC", new HashMap(0));
      request.getSession().setAttribute(paginationKey, pagination);
    }
    return pagination;
  }
}
