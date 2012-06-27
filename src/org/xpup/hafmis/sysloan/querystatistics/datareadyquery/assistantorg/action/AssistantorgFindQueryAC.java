package org.xpup.hafmis.sysloan.querystatistics.datareadyquery.assistantorg.action;

import java.util.HashMap;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.xpup.common.util.Pagination;
import org.xpup.hafmis.sysloan.querystatistics.datareadyquery.assistantorg.form.AssistantorgQueryAF;

public class AssistantorgFindQueryAC extends Action {
  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    try {
      AssistantorgQueryAF assistantorgAF = (AssistantorgQueryAF) form;
      HashMap criterions = makeCriterionsMap(assistantorgAF);
      Pagination pagination = new Pagination(0, 10, 1, "assitantorgid", "DESC",
          criterions);
      String paginationKey = getPaginationKey();
      request.getSession().setAttribute(paginationKey, pagination);
      assistantorgAF.reset(mapping, request);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return mapping.findForward("assistantorg_show");
  }

  protected String getPaginationKey() {
    return AssistantorgQueryShowAC.PAGINATION_KEY;
  }

  protected HashMap makeCriterionsMap(AssistantorgQueryAF form) {
    HashMap m = new HashMap();
    String assistantOrgName = form.getAssistantOrgName();
    if (!(assistantOrgName == null || assistantOrgName.length() == 0))
      m.put("assistantOrgName", form.getAssistantOrgName().trim());
    String artfclprsn = form.getArtfclprsn();
    if (!(artfclprsn == null || artfclprsn.length() == 0)) {
      m.put("artfclprsn", form.getArtfclprsn().trim());
    }
    String assistantType = form.getAssistantType();
    if (!(assistantType == null || assistantType.length() == 0)) {
      m.put("assistantType", form.getAssistantType().trim());
    }
    String inArea = form.getInArea();
    if (!(inArea == null || inArea.length() == 0)) {
      m.put("inArea", form.getInArea().trim());
    }
    return m;
  }
}
