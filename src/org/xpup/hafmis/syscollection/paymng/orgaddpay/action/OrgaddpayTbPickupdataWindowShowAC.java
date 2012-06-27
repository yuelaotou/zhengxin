package org.xpup.hafmis.syscollection.paymng.orgaddpay.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.xpup.common.util.BSUtils;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.syscollection.common.domain.entity.Org;
import org.xpup.hafmis.syscollection.paymng.orgaddpay.bsinterface.IOrgaddpayBS;
import org.xpup.hafmis.syscollection.paymng.orgaddpay.form.OrgaddpayTbPickupdataWindowAF;

public class OrgaddpayTbPickupdataWindowShowAC extends Action {

  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    try{
      OrgaddpayTbPickupdataWindowAF af=new OrgaddpayTbPickupdataWindowAF();
      SecurityInfo securityInfo=(SecurityInfo)request.getSession().getAttribute("SecurityInfo");
      String noteNum=request.getParameter("noteNum");
      String orgId=request.getParameter("orgId");
      IOrgaddpayBS orgaddpayBS = (IOrgaddpayBS) BSUtils
      .getBusinessService("orgaddpayBS", this, mapping.getModuleConfig());
      Org org=null;
      String orgName="";
      if(orgId!=null&&!orgId.equals("")){
        org=orgaddpayBS.findOrgInfo(orgId,"2",securityInfo);
      }
      if(org!=null){
        orgName=org.getOrgInfo().getName();
      }
      af.setNoteNum(noteNum);
      af.setOrgId(orgId);
      af.setOrgName(orgName);
      request.getSession().setAttribute("orgaddpayTbPickupdataWindowAF",af );
    }catch(Exception e){
      e.printStackTrace();
    }
    return mapping.findForward("to_orgaddpay_pickupdatawindow_show");
  }
}
