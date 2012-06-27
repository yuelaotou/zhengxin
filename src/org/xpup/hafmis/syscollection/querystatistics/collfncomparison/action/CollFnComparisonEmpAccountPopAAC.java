package org.xpup.hafmis.syscollection.querystatistics.collfncomparison.action;

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
import org.xpup.hafmis.syscollection.querystatistics.collfncomparison.bsinterface.ICollFnComparisonBS;
import org.xpup.hafmis.syscollection.querystatistics.collfncomparison.dto.CollFnComparisonEmpAccountPopDTO;
/**
 * 用此action来判断是否需要弹出页
 * Copy Right Information :
 * Goldsoft Project : 
 *
 * @Version : v1.0
 * @author : 付云峰
 * @Date : 2008-5-12 下午02:57:50
 */
public class CollFnComparisonEmpAccountPopAAC extends Action {

  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    response.setContentType("text/html;charset=UTF-8");
    response.setHeader("Cache-Control", "no-cache");
    String text = "";
    try {
      String orgid=request.getParameter("orgid");
      String orgname=request.getParameter("orgname");
      String empid = request.getParameter("empid");
      String empname = request.getParameter("empname");
      
      String resorgid = "";
      String resorgname = "";
      String resempname = "";
      String resempid = "";
      String cardnum = "";
      
      Pagination pagination = getPagination(CollFnComparisonEmpAccountPopShowAC.PAGINATION_KEY, request);
      PaginationUtils.updatePagination(pagination, request);
      
      pagination.getQueryCriterions().put("orgid", orgid);
      pagination.getQueryCriterions().put("orgname", orgname);
      pagination.getQueryCriterions().put("empid", empid);
      pagination.getQueryCriterions().put("empname", empname);

      ICollFnComparisonBS collFnComparisonBS = (ICollFnComparisonBS) BSUtils
          .getBusinessService("collFnComparisonBS", this, mapping
              .getModuleConfig());
      SecurityInfo securityInfo = (SecurityInfo) request.getSession()
          .getAttribute("SecurityInfo");
      List list = collFnComparisonBS.findEmpPopList(pagination,
          securityInfo);
      if (list.size()==1) {
        CollFnComparisonEmpAccountPopDTO CollFnComparisonEmpAccountPopDTO = (CollFnComparisonEmpAccountPopDTO) list.get(0);
        resorgid = CollFnComparisonEmpAccountPopDTO.getOrgId().toString();
        resorgname = CollFnComparisonEmpAccountPopDTO.getOrgName();
        resempname = CollFnComparisonEmpAccountPopDTO.getEmpName();
        resempid = CollFnComparisonEmpAccountPopDTO.getEmpId();
        cardnum = CollFnComparisonEmpAccountPopDTO.getCardNum();
      }
      
      int count = list.size();
      request.getSession().removeAttribute(CollFnComparisonEmpAccountShowAC.PAGINATION_KEY);
      text = "displays('" + resorgid + "','" + resorgname + "','" + resempid + "','" + resempname + "','" + cardnum + "','" + count + "')";
      response.getWriter().write(text);
      response.getWriter().close();
    } catch (Exception e) {
      e.printStackTrace();
    }
    return null;
  }
  private Pagination getPagination(String paginationKey,
      HttpServletRequest request) {
    Pagination pagination = (Pagination) request.getSession().getAttribute(
        paginationKey);
    if (pagination == null) {
      pagination = new Pagination(0, 10, 1, "a001.id", "ASC", new HashMap(0));
      request.getSession().setAttribute(paginationKey, pagination);
    }
    return pagination;
  }
}
