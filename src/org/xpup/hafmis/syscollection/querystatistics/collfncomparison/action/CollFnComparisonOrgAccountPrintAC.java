package org.xpup.hafmis.syscollection.querystatistics.collfncomparison.action;

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

public class CollFnComparisonOrgAccountPrintAC extends Action {
  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    try{
      String orgidst=request.getParameter("orgidst");
      String orgidend=request.getParameter("orgidend");
      String timest=request.getParameter("timeSt");
      String timeend=request.getParameter("timeEnd");
      ICollFnComparisonBS collFnComparisonBS = (ICollFnComparisonBS) BSUtils
      .getBusinessService("collFnComparisonBS", this, mapping
          .getModuleConfig());
      SecurityInfo securityInfo = (SecurityInfo) request.getSession()
      .getAttribute("SecurityInfo");
      List list=collFnComparisonBS.findOrgAccountPrintList(orgidst,orgidend,timest,timeend,securityInfo);
      List printList=collFnComparisonBS.changePrintList(list);
      request.getSession().setAttribute("countList_gjp1", printList);
      request.getSession().setAttribute("timest_gjp1", timest);
      request.getSession().setAttribute("timeend_gjp1", timeend);
    }catch(Exception e){
      e.printStackTrace();
    }
    return mapping.findForward("to_orgAccount_print");
  }
}
