package org.xpup.hafmis.sysloan.querystatistics.checkqueryplfn.action;

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
import org.xpup.hafmis.sysloan.querystatistics.checkqueryplfn.form.CheckQueryPlFnAF;

public class CheckQueryPlFnShowAC extends Action {
  public static final String PAGINATION_KEY = "org.xpup.hafmis.sysloan.querystatistics.checkqueryplfn.action.CheckQueryPlFnShowAC";
  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response) {
    try{
      //用带条件的AF
      //CheckQueryPlFnAF checkQueryPlFnAF=new CheckQueryPlFnAF();
      CheckQueryPlFnAF checkQueryPlFnAF=(CheckQueryPlFnAF) form;
      Pagination pagination = getPagination(PAGINATION_KEY, request);
      PaginationUtils.updatePagination(pagination, request);
      ICheckQueryPlFnBS checkQueryPlFnBS = (ICheckQueryPlFnBS) BSUtils.getBusinessService("checkQueryPlFnBS", this, mapping.getModuleConfig());
      SecurityInfo securityInfo = (SecurityInfo) request.getSession().getAttribute("SecurityInfo");
      List list=checkQueryPlFnBS.showCheckQueryPlFnList(pagination, securityInfo);
      if(list.size()>0){
        checkQueryPlFnAF.setList(list);
      }
      request.setAttribute("checkQueryPlFnAF", checkQueryPlFnAF);
      request.getSession().setAttribute(CheckQueryPlFnShowAC.PAGINATION_KEY, pagination);
    }catch(Exception e){
      e.printStackTrace();
    }
    return mapping.findForward("to_checkQueryPlFn_show");
  }
  private Pagination getPagination(String paginationKey,HttpServletRequest request) {
    Pagination pagination = (Pagination) request.getSession().getAttribute(
        paginationKey);
    if (pagination == null) {
      pagination = new Pagination(0, 10, 1, " p11.contract_id ", "DESC", new HashMap(0));
      request.getSession().setAttribute(paginationKey, pagination);
    }
    return pagination;
  }
}
