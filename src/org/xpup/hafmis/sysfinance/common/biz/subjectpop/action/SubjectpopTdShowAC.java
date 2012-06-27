package org.xpup.hafmis.sysfinance.common.biz.subjectpop.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.StringTokenizer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.xpup.common.util.BSUtils;
import org.xpup.common.util.Pagination;
import org.xpup.common.util.PaginationUtils;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.sysfinance.bookmng.subject.form.SubjectShowAF;
import org.xpup.hafmis.sysfinance.common.biz.subjectpop.bsinterface.ISubjectpopBS;

public class SubjectpopTdShowAC extends Action{
  public static final String PAGINATION_KEY = "org.xpup.hafmis.sysfinance.common.biz.subjectpop.action.SubjectpopShowAC";
  public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
    SubjectShowAF subjectShowAF = new SubjectShowAF();
    try{
      SecurityInfo securityInfo =(SecurityInfo)request.getSession().getAttribute("SecurityInfo");
      String statuss=request.getParameter("status");
      String indexs=request.getParameter("indexs");
      if(indexs != null){
        request.getSession().setAttribute("indexs", indexs);
      }
      if(statuss==null){
        statuss = request.getSession().getAttribute("status").toString();
      }

      List status = new ArrayList();
      StringTokenizer str = new StringTokenizer(statuss,",");
      while (str.hasMoreTokens()) {
        String temp_str = str.nextToken();
        status.add(temp_str);
      }

      subjectShowAF.setSortcodeflag("3");
      ISubjectpopBS subjectpopBS = (ISubjectpopBS) BSUtils.getBusinessService("subjectpopBS", this, mapping.getModuleConfig());
      Pagination pagination = getPagination(PAGINATION_KEY, request); 
      PaginationUtils.updatePagination(pagination, request);
      request.getSession().setAttribute("status",statuss);
      pagination.getQueryCriterions().put("status", status);  
      List subjectList=subjectpopBS.findSubjectpopTree(subjectShowAF,pagination,securityInfo);
      request.setAttribute("subjectList", subjectList);
    } catch (Exception e) {
      e.printStackTrace();
    }
    request.setAttribute("subjectShowAF", subjectShowAF);
    return mapping.findForward("to_subject_show");
  }

  private Pagination getPagination(String paginationKey,
      HttpServletRequest request) {
    Pagination pagination = (Pagination) request.getSession().getAttribute(
        paginationKey);
    if (pagination == null) {
      pagination = new Pagination(0, 10, 1, "",
          "DESC", new HashMap(0));
      request.getSession().setAttribute(PAGINATION_KEY, pagination);
    }

    return pagination;
  }
}