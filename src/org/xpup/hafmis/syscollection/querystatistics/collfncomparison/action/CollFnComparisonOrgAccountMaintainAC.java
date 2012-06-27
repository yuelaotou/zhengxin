package org.xpup.hafmis.syscollection.querystatistics.collfncomparison.action;


import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.xpup.common.util.BSUtils;
import org.xpup.hafmis.syscollection.querystatistics.collfncomparison.bsinterface.ICollFnComparisonBS;

public class CollFnComparisonOrgAccountMaintainAC extends Action {

  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    try{
      List list=(List)request.getSession().getAttribute("countList_gjp");
      ICollFnComparisonBS collFnComparisonBS = (ICollFnComparisonBS) BSUtils
      .getBusinessService("collFnComparisonBS", this, mapping
          .getModuleConfig());
      List printList=collFnComparisonBS.changePrintList(list);
      request.getSession().setAttribute("countList_gjp1", printList);
    }catch(Exception e){
      e.printStackTrace();
    }
    return mapping.findForward("to_orgAccount_print");
  }
}
