package org.xpup.hafmis.syscollection.querystatistics.collfncomparison.action;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.xpup.common.util.BSUtils;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.syscollection.querystatistics.collfncomparison.bsinterface.ICollFnComparisonBS;

/**
 * 根据单位编号、单位名称查询单位信息
 */
public class OrgpopTaFindAC extends Action {
  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    response.setContentType("text/html;charset=UTF-8");
    response.setHeader("Cache-Control", "no-cache");
    try {
      String orgid = (String) request.getParameter("orgid");
      String orgname = (String) request.getParameter("orgname");
      ICollFnComparisonBS collFnComparisonBS = (ICollFnComparisonBS) BSUtils
          .getBusinessService("collFnComparisonBS", this, mapping
              .getModuleConfig());
      SecurityInfo securityInfo = (SecurityInfo) request.getSession()
          .getAttribute("SecurityInfo");
      String text = null;
      int count=collFnComparisonBS.findOrgpopListCount(orgid, orgname, securityInfo);
      text = "display1('" +orgid+"','"+orgname+"','"+ count+"" + "')";
      response.getWriter().write(text);
      response.getWriter().close();
    } catch (Exception e) {
      e.printStackTrace();
    }

    return null;
  }
}
