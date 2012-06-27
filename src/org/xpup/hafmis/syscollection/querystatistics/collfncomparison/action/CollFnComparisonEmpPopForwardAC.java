package org.xpup.hafmis.syscollection.querystatistics.collfncomparison.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.xpup.common.util.BSUtils;
import org.xpup.hafmis.common.util.BusiConst;
import org.xpup.hafmis.common.util.BusiTools;
import org.xpup.hafmis.syscollection.querystatistics.collfncomparison.bsinterface.ICollFnComparisonBS;

public class CollFnComparisonEmpPopForwardAC extends Action {

  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {

    try {
      String orgId = request.getParameter("orgId");
      String bizType = request.getParameter("bizType");
      String date = request.getParameter("date");
      String docNum = request.getParameter("docNum");

      ICollFnComparisonBS collFnComparisonBS = (ICollFnComparisonBS) BSUtils
      .getBusinessService("collFnComparisonBS", this, mapping
          .getModuleConfig());
      String orgName = collFnComparisonBS.findOrgName(orgId);
      // 得到单位名称
      // 转换业务类型的方法
      bizType = (BusiTools.getBusiValue_WL(bizType,
          BusiConst.CLEARACCOUNTBUSINESSTYPE));
      request.getSession().setAttribute("ComparisonEmpPop_orgId", orgId);
      request.getSession().setAttribute("ComparisonEmpPop_bizType", bizType);
      request.getSession().setAttribute("ComparisonEmpPop_date", date);
      request.getSession().setAttribute("ComparisonEmpPop_docNum", docNum);
      request.getSession().setAttribute("ComparisonEmpPop_orgName", orgName);

      request.getSession().removeAttribute(
          CollFnComparisonEmpPopShowAC.PAGINATION_KEY);

    } catch (Exception e) {
      e.printStackTrace();
    }

    return mapping.findForward("to_collFnComparisonEmpPopShowAC");
  }

}
