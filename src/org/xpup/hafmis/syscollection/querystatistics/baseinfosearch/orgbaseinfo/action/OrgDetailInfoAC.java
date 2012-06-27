package org.xpup.hafmis.syscollection.querystatistics.baseinfosearch.orgbaseinfo.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.xpup.common.util.BSUtils;
import org.xpup.hafmis.syscollection.common.domain.entity.Org;
import org.xpup.hafmis.syscollection.querystatistics.baseinfosearch.orgbaseinfo.bsinterface.IOrgBaseInfoBS;

public class OrgDetailInfoAC extends Action{
  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response) {
    try{
      IOrgBaseInfoBS orgBaseInfoBS = (IOrgBaseInfoBS)BSUtils.getBusinessService("orgBaseInfoBS",this,mapping.getModuleConfig());
      String orgId = request.getParameter("orgId");
      Org org=orgBaseInfoBS.findOrgInfoById(new Integer(orgId));
      request.setAttribute("orginfo", org);
    }catch(Exception s){
      s.printStackTrace();
    }
    return mapping.findForward("orgdetailinfo");
  }
}
