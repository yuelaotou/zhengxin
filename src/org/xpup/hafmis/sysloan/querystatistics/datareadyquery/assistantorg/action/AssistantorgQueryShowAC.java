package org.xpup.hafmis.sysloan.querystatistics.datareadyquery.assistantorg.action;

import java.util.HashMap;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.xpup.common.util.BSUtils;
import org.xpup.common.util.Pagination;
import org.xpup.common.util.PaginationUtils;
import org.xpup.hafmis.common.util.BusiConst;
import org.xpup.hafmis.common.util.BusiTools;

import org.xpup.hafmis.sysloan.querystatistics.datareadyquery.assistantorg.bsinterface.IAssistantOrgBS;
import org.xpup.hafmis.sysloan.querystatistics.datareadyquery.assistantorg.dto.AssistantOrgQueryDTO;
import org.xpup.hafmis.sysloan.querystatistics.datareadyquery.assistantorg.form.AssistantorgQueryAF;

public class AssistantorgQueryShowAC extends Action {
  public static final String PAGINATION_KEY = "org.xpup.hafmis.sysloan.querystatistics.datareadyquery.assistantorg.action.AssistantorgQueryShowAC";

  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    // TODO Auto-generated method stub
    try {
      AssistantorgQueryAF assistantorgAF = new AssistantorgQueryAF();
      Pagination pagination = getPagination(
          AssistantorgQueryShowAC.PAGINATION_KEY, request);
      PaginationUtils.updatePagination(pagination, request);
      try {
        Map assistantMap = BusiTools
            .listBusiProperty(BusiConst.PLASSISTANTORG_TYPE);
        assistantorgAF.setAssistantMap(assistantMap);
        Map inAreaMap = BusiTools
        .listBusiProperty(BusiConst.INAREA);
        assistantorgAF.setInAreaMap(inAreaMap);
      } catch (Exception e) {
        e.printStackTrace();
      }
      IAssistantOrgBS assistantOrgBS = (IAssistantOrgBS) BSUtils
          .getBusinessService("assistantOrgBS", this, mapping.getModuleConfig());
      List list = assistantOrgBS.findAssistantOrgList(pagination);
      Iterator iter=list.iterator();
      while (iter.hasNext()) {      
        AssistantOrgQueryDTO element = (AssistantOrgQueryDTO) iter.next();        
        if (!"".equals(element.getArear())) {
          element.setArear(BusiTools.getBusiValue(Integer.parseInt(element.getArear().toString()), BusiConst.INAREA));
        }        
      }
      assistantorgAF.setList(list);
      assistantorgAF.reset(mapping, request);
      request.setAttribute("assistantorgAF", assistantorgAF);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return mapping.findForward("to_assistantorg_show");
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
