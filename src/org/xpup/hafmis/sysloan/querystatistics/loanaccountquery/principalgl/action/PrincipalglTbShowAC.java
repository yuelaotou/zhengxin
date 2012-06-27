package org.xpup.hafmis.sysloan.querystatistics.loanaccountquery.principalgl.action;

import java.util.HashMap;

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
import org.xpup.hafmis.sysloan.querystatistics.loanaccountquery.principalgl.bsinterface.IPrincipalglBS;
import org.xpup.hafmis.sysloan.querystatistics.loanaccountquery.principalgl.form.PrincipalglTaAF;
import org.xpup.hafmis.sysloan.querystatistics.loanaccountquery.principalgl.action.PrincipalglTaShowAC;
/**
 * 
 * @author yuqf
 *
 */
public class PrincipalglTbShowAC extends Action{
  public static final String PAGINATION_KEY = "org.xpup.hafmis.sysloan.querystatistics.loanaccountquery.principalgl.action.PrincipalglTbShowAC";
  public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
    // TODO Auto-generated method stub
    PrincipalglTaAF principalglTaAF1 = (PrincipalglTaAF)form;
    String id = request.getParameter("id");
    String date = request.getParameter("date");
    Pagination pagination1 = (Pagination)request.getSession().getAttribute(PrincipalglTaShowAC.PAGINATION_KEY);
    HashMap map = (HashMap) pagination1.getQueryCriterions();
    String radioValue = (String)map.get("radioValue");
    String floorname = (String)map.get("floorNum");
    String startDate = (String) map.get("startDate");//查询开始时间
    String endDate = (String) map.get("endDate");//查询结束时间
    if(date!=null){
      request.getSession().setAttribute("date", date);
    }else{
      date = (String)request.getSession().getAttribute("date");
    }
    SecurityInfo securityInfo = (SecurityInfo) request.getSession()
    .getAttribute("SecurityInfo");
    Pagination pagination2 = this.getPagination(PAGINATION_KEY, request);
    pagination2.getQueryCriterions().put("startDate", startDate);
    pagination2.getQueryCriterions().put("endDate", endDate);
    if(id != null && !"".equals(id)){
      pagination2.getQueryCriterions().put("id", id);
    }
    String tempId= (String)pagination2.getQueryCriterions().get("id");
    PaginationUtils.updatePagination(pagination2, request);
    
    IPrincipalglBS principalglBS = (IPrincipalglBS) BSUtils.getBusinessService(
        "principalglBS", this, mapping.getModuleConfig());
    PrincipalglTaAF principalglTaAF = new PrincipalglTaAF();
    principalglTaAF = principalglBS.queryListByMonth(tempId, date, pagination2, securityInfo,radioValue, floorname);
    request.getSession().setAttribute("principalglTblist", principalglTaAF.getPrintlist());
    request.setAttribute("theprincipalglTaAF", principalglTaAF);
    request.getSession().setAttribute(PAGINATION_KEY, pagination2);
    return mapping.findForward("to_principalglTb_show");
  }
  private Pagination getPagination(String paginationKey,
      HttpServletRequest request) {
    Pagination pagination = (Pagination) request.getSession().getAttribute(
        paginationKey);
    if (pagination == null) {
      HashMap m = new HashMap();
      pagination = new Pagination(0, 12, 1, null, "ASC", m);
      request.getSession().setAttribute(paginationKey, pagination);
    }
    return pagination;
  }
}
