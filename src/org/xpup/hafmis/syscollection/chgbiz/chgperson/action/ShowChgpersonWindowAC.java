package org.xpup.hafmis.syscollection.chgbiz.chgperson.action;

import java.util.HashMap;
import org.xpup.hafmis.common.util.BusiTools;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.xpup.common.exception.BusinessException;
import org.xpup.common.util.BSUtils;
import org.xpup.common.util.Pagination;
import org.xpup.common.util.PaginationUtils;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.syscollection.chgbiz.chgperson.bsinterface.IChgpersonDoBS;
import org.xpup.hafmis.syscollection.chgbiz.chgperson.dto.ChgPersonHeadFormule;
import org.xpup.hafmis.syscollection.chgbiz.chgperson.form.ChgpersonDoListAF;

public class ShowChgpersonWindowAC extends Action {

  public static final String PAGINATION_KEY = "org.xpup.hafmis.syscollection.chgbiz.chgperson.action.ShowChgpersonWindowAC";
  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {

    try {
      ChgpersonDoListAF chgpersonDoListAF = new ChgpersonDoListAF();
      
      /**
       * 分页
       */   
      Pagination pagination = getPagination(PAGINATION_KEY, request);
      PaginationUtils.updatePagination(pagination, request);
      IChgpersonDoBS chgpersonDoBS = (IChgpersonDoBS) BSUtils
          .getBusinessService("chgpersonDoBS", this, mapping.getModuleConfig());

      SecurityInfo securityInfo = (SecurityInfo) request.getSession()
               .getAttribute("SecurityInfo");
      HttpSession session = request.getSession();
      String show_window=(String)session.getAttribute("showwindow");
      if(show_window.equals("1")){
        String headid =request.getParameter("headid").toString();
        pagination.getQueryCriterions().put("headid", headid);
        String orgId =request.getParameter("orgId").toString();
        pagination.getQueryCriterions().put("id", orgId);
        session.setAttribute("showwindow", "2");
      }
      
      List list = chgpersonDoBS.findChgpersonDoListByHeadID(pagination,securityInfo);
      String changeHeadId = null;
      changeHeadId = (String)session.getAttribute("changeHeadId");
      ChgPersonHeadFormule chgPersonHeadFormule = chgpersonDoBS.findChgpersonHeadByCriterions_wsh(pagination,securityInfo, pagination.getQueryCriterions().get("headid").toString());//查询合计项
     
      session.removeAttribute("changeHeadId");
      if(chgPersonHeadFormule!=null){
        chgPersonHeadFormule.setSumChgPerson(new Integer(chgPersonHeadFormule.getBeforeChgperson().intValue()+chgPersonHeadFormule.getInsChgperson().intValue()-chgPersonHeadFormule.getMulChgperson().intValue()));
      }
      request.setAttribute("chgpersonAF", chgPersonHeadFormule);
      chgpersonDoListAF.setList(list);
      chgpersonDoListAF.setId((String) pagination.getQueryCriterions()
          .get("showwindowid"));
      chgpersonDoListAF.setName((String) pagination.getQueryCriterions().get(
          "showwindowname"));
      String TEMP_date = (String) pagination.getQueryCriterions().get("showwindowdate");
      chgpersonDoListAF.setDate(TEMP_date);
      String tempid = chgpersonDoListAF.getId();
      if(tempid!=null && !"".equals(tempid)){
        String sid = BusiTools.convertTenNumber(tempid);
        chgpersonDoListAF.setId(sid);
      }
      request.setAttribute("chgpersonDoListAF", chgpersonDoListAF);
      
      //chgpersonDoListAF.reset(mapping, request);

    } catch (BusinessException bex) {
      bex.printStackTrace();
    } catch (Exception ex) {
      ex.printStackTrace();
    }
    return mapping.findForward("to_chgperson_window");
  }

  private Pagination getPagination(String paginationKey,
      HttpServletRequest request) {
    Pagination pagination = (Pagination) request.getSession().getAttribute(
        paginationKey);
    if (pagination == null) {
      pagination = new Pagination(0, 10, 1, "chgPersonTail.id", "DESC",
          new HashMap(0));
      request.getSession().setAttribute(paginationKey, pagination);
    }

    return pagination;
  }
}
