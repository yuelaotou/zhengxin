package org.xpup.hafmis.syscollection.accountmng.accountchg.action;


import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.xpup.common.util.Pagination;
import org.xpup.hafmis.syscollection.accountmng.accountchg.form.OrgChgListAF;
public class OrgChgFindInfoAC extends Action {
 
      public ActionForward execute(ActionMapping mapping, ActionForm form,
          HttpServletRequest request, HttpServletResponse response)
          throws Exception {

        OrgChgListAF f = (OrgChgListAF) form;
        

        HashMap criterions = makeCriterionsMap(f);
        Pagination pagination = new Pagination(0, 10, 1,"orgChgLog.org.id", "DESC", criterions);
        String paginationKey = getPaginationKey();
        request.getSession().setAttribute(paginationKey, pagination);
        modifyPagination(pagination);
        
        f.reset(mapping, request);
        return mapping.findForward(getForword());
      }

      protected String getForword() {
        return "show_orgchg_list";
      }

      protected  String getPaginationKey(){
        return "org.xpup.hafmis.syscollection.accountmng.accountchg.action.OrgChgShowListAC";
      }

      protected HashMap makeCriterionsMap(OrgChgListAF form) {
        HashMap m = new HashMap();
        String id = form.getId();
        if (!(id == null || "".equals(id))) {
          try {
            m.put("id",id);
          } catch (NumberFormatException e) {
            m.put("id", new Integer(-10000));
          }
        }
        
        String name = form.getName();
        if (!(name == null || name.length() == 0))
          m.put("name", form.getName());

        String chgtype = form.getChgType();
        if (chgtype != null )        
            m.put("chgtype", form.getChgType());
        // 使用temp_Type来判断是否为条件查询
        String temp_Type = "b";
        m.put("temp_Type", temp_Type);
        
        return m;
      }

      protected void modifyPagination(Pagination pagination) {
      }
    }

