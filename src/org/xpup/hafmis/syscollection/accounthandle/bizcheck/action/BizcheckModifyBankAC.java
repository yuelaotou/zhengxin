package org.xpup.hafmis.syscollection.accounthandle.bizcheck.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.xpup.common.util.BSUtils;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.syscollection.accounthandle.bizcheck.bsinterface.IBizcheckBS;

public class BizcheckModifyBankAC extends Action {

  public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
    // TODO Auto-generated method stub
    try {
      IBizcheckBS bizcheckBS = (IBizcheckBS) BSUtils
      .getBusinessService("bizcheckBS", this, mapping.getModuleConfig());
      SecurityInfo securityInfo=(SecurityInfo)request.getSession().getAttribute("SecurityInfo");
      String id=(String)request.getSession().getAttribute("id");
      String officeCode=null;
      String moneyBank=null;
      if(request.getParameter("officeCode")!=null){
        officeCode=(String)request.getParameter("officeCode").trim();
      }
      if(request.getParameter("moneyType")!=null){
        moneyBank=(String)request.getParameter("moneyType").trim();
      }
      if(officeCode!=null&&moneyBank!=null&&!"".equals(officeCode)&&!"".equals(moneyBank)){
        bizcheckBS.checkthroughOrgHAFAccountFlow(new Integer (id),securityInfo.getUserIp(),securityInfo.getUserName(),officeCode,moneyBank);
      }else{
        bizcheckBS.checkthroughOrgHAFAccountFlow(new Integer (id),securityInfo.getUserIp(),securityInfo.getUserName(),null,null);
      }
    } catch (Exception e) {
      // TODO: handle exception
      e.printStackTrace();
    }
    return mapping.findForward("to_show_modify");
  }

}
