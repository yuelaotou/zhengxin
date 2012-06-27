package org.xpup.hafmis.syscollection.accounthandle.clearaccount.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.xpup.common.util.Pagination;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;

public class ClearAccountBalanceMXFindAAC extends Action{
  public static final String PAGINATION_KEY = "org.xpup.hafmis.syscollection.accounthandle.clearaccount.action.ClearAccountTaBalanceShowAC";
                                              
  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
        response.setContentType("text/html;charset=UTF-8");
        response.setHeader("Cache-Control", "no-cache");
        SecurityInfo securityInfo=(SecurityInfo)request.getSession().getAttribute("SecurityInfo");
        try {
          Pagination pagination = (Pagination) request.getSession().getAttribute(
              PAGINATION_KEY);
          String orgcode="";
          if(pagination.getQueryCriterions().get("orgId")!=null){
            orgcode = new Integer(pagination.getQueryCriterions().get("orgId").toString()).toString();
          }
          String orgname = (String) pagination.getQueryCriterions().get("orgName");
          String bstype = (String) pagination.getQueryCriterions().get("bis_type1");
          String setmonthstart = (String) pagination.getQueryCriterions().get("startday");
          String setmonthend = (String) pagination.getQueryCriterions().get("untilday");
          // 结算单查询页面选择的银行
          String bankId = (String) pagination.getQueryCriterions().get("bank1");
          if(setmonthstart==null&&setmonthend==null){
            setmonthstart=securityInfo.getUserInfo().getBizDate();
          }
          Pagination pagination1 = new Pagination();
          pagination1.getQueryCriterions().put("orgid",orgcode);
          pagination1.getQueryCriterions().put("orgname",orgname);
          pagination1.getQueryCriterions().put("bstype",bstype);
          pagination1.getQueryCriterions().put("setmonthstart",setmonthstart);
          pagination1.getQueryCriterions().put("setmonthend",setmonthend);
          pagination1.getQueryCriterions().put("bankId",bankId);
          request.getSession(false).setAttribute("sessionLJ", pagination1);
          String text = "display();";
          response.getWriter().write(text); 
          response.getWriter().close();
        } catch (Exception e) {
          e.printStackTrace();
        }
        
        return null; 
}

}