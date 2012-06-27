package org.xpup.hafmis.syscollection.querystatistics.chgbiz.chgperson.action;

import java.util.HashMap;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.xpup.common.exception.BusinessException;
import org.xpup.common.util.BSUtils;
import org.xpup.common.util.Pagination;
import org.xpup.common.util.PaginationUtils;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.syscollection.querystatistics.chgbiz.chgperson.bsinterface.IChgpersonOrgBS;
import org.xpup.hafmis.syscollection.querystatistics.chgbiz.chgperson.dto.ChgpersonEmpHeadDTO;
import org.xpup.hafmis.syscollection.querystatistics.chgbiz.chgperson.from.ChgpersonEmpListAF;

public class ShowChgpersonEmpListAC extends Action {

  public static final String PAGINATION_KEY = "org.xpup.hafmis.syscollection.querystatistics.chgbiz.chgperson.action.ShowChgpersonEmpListAC";

  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response) {

    ActionMessages messages = null;
    ChgpersonEmpListAF af = (ChgpersonEmpListAF) form;
    try {
      /**
       * 分页
       */
      Pagination pagination = getPagination(PAGINATION_KEY, request);
      PaginationUtils.updatePagination(pagination, request);

       SecurityInfo securityInfo = (SecurityInfo) request.getSession()
       .getAttribute("SecurityInfo");

      IChgpersonOrgBS chgpersonOrgBS = (IChgpersonOrgBS) BSUtils
          .getBusinessService("chgpersonOrgBS", this, mapping.getModuleConfig());

      HttpSession session = request.getSession();
      String firstinEmp = (String) session.getAttribute("firstinEmp");

      List list = null;
      ChgpersonEmpHeadDTO chgpersonEmpHeadDTO = new ChgpersonEmpHeadDTO();

      String headID = request.getParameter("chgpersonHeadID");
      if(headID!=null&&!headID.equals("")){
        pagination.getQueryCriterions().put("chgpersonHeadID", headID);
        request.removeAttribute("chgpersonHeadID");
      }
      String chgpersonHeadID=(String)pagination.getQueryCriterions().get("chgpersonHeadID");
      if (chgpersonHeadID != null && !chgpersonHeadID.equals("")) {
        
        list = chgpersonOrgBS.findChgpersonEmpListByChgpersonHeadID(pagination,chgpersonHeadID,securityInfo);
        af.setList(list);
        //查询全部列表信息，打印中用到
        List listAll=chgpersonOrgBS.queryChgpersonEmpListAll(pagination,chgpersonHeadID,securityInfo);
        session.setAttribute("cellList", listAll);

        if(list!=null&&list.size()!=0){
           chgpersonEmpHeadDTO =chgpersonOrgBS.findChgpersonEmpHeadByCriterions(listAll);//计算合计项
        }
        
        session.setAttribute("firstinEmp", "2");

      }else {

        if(!pagination.getQueryCriterions().isEmpty()){
          if (!firstinEmp.equals("1")) {
            list = chgpersonOrgBS.findChgpersonEmpListByCriterions(pagination,securityInfo);
            af.setList(list);
            
            //查询全部列表信息，打印中用到
            List listAll=chgpersonOrgBS.queryChgpersonEmpListAll(pagination,chgpersonHeadID,securityInfo);
            session.setAttribute("cellList", listAll);
  
            if(list!=null&&list.size()!=0){
               chgpersonEmpHeadDTO =chgpersonOrgBS.findChgpersonEmpHeadByCriterions(listAll);//计算合计项
            }
        }
        }else {
          session.setAttribute("firstinEmp", "2");
          af.setList(null);
        }

      }
      

      request.setAttribute("chgpersonEmpListAF", af);
      request.setAttribute("chgpersonEmpHead", chgpersonEmpHeadDTO);

    } catch (BusinessException bex) {
      messages = new ActionMessages();
      messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(bex
          .getLocalizedMessage().toString(), false));
      saveErrors(request, messages);
    } catch (Exception ex) {
      ex.printStackTrace();
    }
    return mapping.findForward("to_chgpersonemp_list");
  }

  private Pagination getPagination(String paginationKey,
      HttpServletRequest request) {
    Pagination pagination = (Pagination) request.getSession().getAttribute(
        paginationKey);
    if (pagination == null) {
      pagination = new Pagination(0, 10, 1, "chgPersonTail.id,empId,id", "DESC",
          new HashMap(0));
      request.getSession().setAttribute(paginationKey, pagination);
    }

    return pagination;
  }

}
