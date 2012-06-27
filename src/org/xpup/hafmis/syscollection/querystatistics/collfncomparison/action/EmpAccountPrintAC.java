package org.xpup.hafmis.syscollection.querystatistics.collfncomparison.action;

import java.math.BigDecimal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.xpup.common.util.BSUtils;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.syscollection.querystatistics.collfncomparison.bsinterface.ICollFnComparisonBS;
import org.xpup.hafmis.syscollection.querystatistics.collfncomparison.dto.CollFnComparisonEmpAccountDTO;
/**
 * 职工账打印的Action
 * Copy Right Information :
 * Goldsoft Project : 
 *
 * @Version : v1.0
 * @author : 付云峰
 * @Date : 2008-5-13 上午10:25:27
 */
public class EmpAccountPrintAC extends Action {

  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    try {
      String orgidst = request.getParameter("orgidst");
      String orgidend = request.getParameter("orgidend");

      String empidst = request.getParameter("empidst");
      String empidend = request.getParameter("empidend");

      String timeSt = request.getParameter("timeSt");
      String timeEnd = request.getParameter("timeEnd");

      ICollFnComparisonBS collFnComparisonBS = (ICollFnComparisonBS) BSUtils
          .getBusinessService("collFnComparisonBS", this, mapping
              .getModuleConfig());

      SecurityInfo securityInfo = (SecurityInfo) request.getSession()
          .getAttribute("SecurityInfo");

      List list = collFnComparisonBS.findEmpAccountPrint(orgidst, orgidend,
          empidst, empidend, timeSt, timeEnd, securityInfo);
      
      request.setAttribute("print_list", list);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return mapping.findForward("to_empaccountprint_show");
  }

}
