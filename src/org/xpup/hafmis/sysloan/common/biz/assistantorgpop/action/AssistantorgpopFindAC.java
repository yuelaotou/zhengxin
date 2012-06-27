package org.xpup.hafmis.sysloan.common.biz.assistantorgpop.action;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.xpup.common.util.Pagination;
import org.xpup.hafmis.sysloan.common.biz.assistantorgpop.form.AssistantorgpopAF;

/**
 * @author yuqf
 */
public class AssistantorgpopFindAC extends Action{


    public ActionForward execute(ActionMapping mapping, ActionForm form,
        HttpServletRequest request, HttpServletResponse response)
        throws Exception {
      // TODO Auto-generated method stub
      AssistantorgpopAF assistantorgpopAF = (AssistantorgpopAF) form;
      HashMap criterions = makeCriterionsMap(assistantorgpopAF);
      Pagination pagination = new Pagination(0, assistantorgpopAF.getPageSize(), 1,
          "t.assistant_org_id", "DESC", criterions);
     
      String paginationKey = AssistantorgpopShowAC.PAGINATION_KEY;

      request.getSession().setAttribute(paginationKey, pagination);

      assistantorgpopAF.reset(mapping, request);

      return mapping.findForward("to_assistantorgpopShow");
    }

    protected HashMap makeCriterionsMap(AssistantorgpopAF form) {
      HashMap m = new HashMap();

      String id = form.getId().trim();
      if (id != null && !"".equals(id)) {
        m.put("id", id);
      }

      String name = form.getName().trim();// ½è¿îÈËÐÕÃû
      if (name != null && !"".equals(name)) {
        m.put("name", name);
      }
      
      return m;
    }
}
