package org.xpup.hafmis.sysloan.querystatistics.datareadyquery.assistantorg.action;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.xpup.common.util.BSUtils;
import org.xpup.common.util.Pagination;
import org.xpup.hafmis.common.util.BusiConst;
import org.xpup.hafmis.common.util.BusiTools;
import org.xpup.hafmis.sysloan.querystatistics.datareadyquery.assistantorg.bsinterface.IAssistantOrgBS;
import org.xpup.hafmis.sysloan.querystatistics.datareadyquery.assistantorg.dto.AssistantOrgQueryDTO;

public class AssistantorgPrintAC extends Action {

  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    // TODO Auto-generated method stub
    String assistantOrgName = "";
    String artfclprsn = "";
    String assistantType = "";
    String inArea = "";
    try {
      Pagination pagination = getPagination(
          AssistantorgQueryShowAC.PAGINATION_KEY, request);
      if (pagination.getQueryCriterions().get("assistantOrgName") != null) {
        assistantOrgName = (String) pagination.getQueryCriterions().get(
            "assistantOrgName");
      }
      if (pagination.getQueryCriterions().get("artfclprsn") != null) {
        artfclprsn = (String) pagination.getQueryCriterions().get("artfclprsn");
      }
      if (pagination.getQueryCriterions().get("assistantType") != null) {
        assistantType = (String) pagination.getQueryCriterions().get(
            "assistantType");
      }
      if (pagination.getQueryCriterions().get("inArea") != null) {
        inArea = (String) pagination.getQueryCriterions().get("inArea");
      }
      IAssistantOrgBS assistantOrgBS = (IAssistantOrgBS) BSUtils
          .getBusinessService("assistantOrgBS", this, mapping.getModuleConfig());
      List list = assistantOrgBS.queryAssistantOrgListCount_wsh(
          assistantOrgName, artfclprsn, assistantType, inArea);
      Iterator iter=list.iterator();
      while (iter.hasNext()) {      
        AssistantOrgQueryDTO element = (AssistantOrgQueryDTO) iter.next();        
        if (!"".equals(element.getArear())) {
          element.setArear(BusiTools.getBusiValue(Integer.parseInt(element.getArear().toString()), BusiConst.INAREA));
        }        
      }
      request.setAttribute("empaddpURL", "javascript:history.back();");
      request.setAttribute("cellList", list);
    } catch (Exception e) {
      // TODO: handle exception
      e.printStackTrace();
    }
    return mapping.findForward("to_assistantorglist_print");
  }

  private Pagination getPagination(String paginationKey,
      HttpServletRequest request) {
    Pagination pagination = (Pagination) request.getSession().getAttribute(
        paginationKey);
    if (pagination == null) {
      HashMap m = new HashMap();
      pagination = new Pagination(0, 10, 1, "assitantorgid", "DESC", m);
      request.getSession().setAttribute(paginationKey, pagination);
    }
    return pagination;
  }
}
