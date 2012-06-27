package org.xpup.hafmis.syscollection.querystatistics.baseinfosearch.orgbaseinfo.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.LookupDispatchAction;
import org.xpup.common.util.BSUtils;
import org.xpup.common.util.Pagination;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.syscollection.common.domain.entity.Org;
import org.xpup.hafmis.syscollection.querystatistics.baseinfosearch.orgbaseinfo.bsinterface.IOrgBaseInfoBS;

public class OrgBaseInfoTaMaintainAC extends LookupDispatchAction {

  protected Map getKeyMethodMap() {
    Map map = new HashMap();
    map.put("button.print", "print");
    map.put("button.printone", "printone");
    return map;
  }

  public ActionForward print(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {

    Pagination pagination = (Pagination) request.getSession().getAttribute(
        OrgBaseInfoShowAC.PAGINATION_KEY);
    List list = (List) pagination.getQueryCriterions().get("printlist");
    request.setAttribute("printlist", list);
    return mapping.findForward("to_orgBaseInfoListprint");
  }

  public ActionForward printone(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {

    String id = (String) request.getParameter("id");
    Integer orgId = new Integer(id);
    IOrgBaseInfoBS orgBaseInfoBS = (IOrgBaseInfoBS) BSUtils.getBusinessService(
        "orgBaseInfoBS", this, mapping.getModuleConfig());
    Org org = orgBaseInfoBS.findOrgInfoById(orgId);
    request.setAttribute("org", org);
    return mapping.findForward("to_orgBaseInfoprint");
  }

}
