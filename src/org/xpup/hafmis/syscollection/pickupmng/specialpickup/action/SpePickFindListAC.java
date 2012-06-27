package org.xpup.hafmis.syscollection.pickupmng.specialpickup.action;

import java.util.HashMap;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.xpup.common.util.Pagination;
import org.xpup.hafmis.syscollection.pickupmng.specialpickup.form.SpePickListAF;
public class SpePickFindListAC extends Action {

        public ActionForward execute(ActionMapping mapping, ActionForm form,
          HttpServletRequest request, HttpServletResponse response)
          throws Exception {
          response.setContentType("text/html;charset=UTF-8");
          response.setHeader("Cache-Control", "no-cache");
          SpePickListAF spePickListAF=(SpePickListAF)form;
            HashMap criterions = makeCriterionsMap(spePickListAF);
            String paginationKey = getPaginationKey();
            String id=request.getParameter("id");
            String name=request.getParameter("name");
            String officeCode=request.getParameter("officeCode"); 
            String collectionBankId=request.getParameter("collectionBankId");
            String operateTime1=request.getParameter("operateTime1");
            String operateTime2=request.getParameter("operateTime2");
            
            if (!(id == null || "".equals(id))) {
              criterions.put("id", id);
            }
            if (!(name == null || name.length() == 0)){
              criterions.put("name", name);
            }
            if (!(officeCode == null || officeCode.length() == 0)){
              criterions.put("officeCode", officeCode);
            }
            if (!(collectionBankId == null || "".equals(collectionBankId))) {
              criterions.put("collectionBankId", collectionBankId);
            }
            if (!(operateTime1 == null || operateTime1.length() == 0)){
              criterions.put("operateTime1", operateTime1);
            }
            if (!(operateTime2 == null || operateTime2.length() == 0)){
              criterions.put("operateTime2", operateTime2);
            }
            Pagination pagination = new Pagination(0, 10, 1,
                "id", "DESC", criterions);
            pagination.getQueryCriterions().put("id", spePickListAF.getId());
            pagination.getQueryCriterions().put("name", spePickListAF.getName());
            pagination.getQueryCriterions().put("officeCode", spePickListAF.getOfficeCode());
            pagination.getQueryCriterions().put("collectionBankId", spePickListAF.getCollectionBankId());
            pagination.getQueryCriterions().put("operateTime1", spePickListAF.getOperateTime1());
            pagination.getQueryCriterions().put("operateTime2", spePickListAF.getOperateTime2());
            request.getSession().setAttribute(paginationKey, pagination);
            modifyPagination(pagination);
            spePickListAF.reset(mapping, request);
            return mapping.findForward(getForword());
      }
      protected String getForword() {
        return "show_organizations";
      }
      
      protected HashMap makeCriterionsMap(SpePickListAF form) {
        HashMap m = new HashMap();
        return m;
      }

      protected void modifyPagination(Pagination pagination) {
      }

      protected String getPaginationKey() {
        return SpePickShowListAC.PAGINATION_KEY;
     }
    }

