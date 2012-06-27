package org.xpup.hafmis.syscollection.querystatistics.accountinfo.empaccountinfo.action;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessages;
import org.xpup.common.util.BSUtils;
import org.xpup.common.util.Pagination;
import org.xpup.common.util.PaginationUtils;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.syscollection.querystatistics.accountinfo.empaccountinfo.bsinterface.IEmpAccountBS;
import org.xpup.hafmis.syscollection.querystatistics.accountinfo.empaccountinfo.form.EmpAccountAF;


/**
 * shiyan
 */
public class ShowEmpAccountDayListAC extends Action {

  public static final String PAGINATION_KEY = "org.xpup.hafmis.syscollection.querystatistics.accountinfo.empaccountinfo.action.ShowEmpAccountDayListAC";

  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response) {
    ActionMessages messages = null;
    try {
      /**
       * 分页
       */
      Pagination pagination = getPagination(PAGINATION_KEY, request);
      Pagination pagination1 = (Pagination)request.getSession().getAttribute(ShowEmpAccountListAC.PAGINATION_KEY);
      PaginationUtils.updatePagination(pagination, request);
      saveToken(request);
      //查询条件的开始时间
      String startDate=pagination1.getQueryCriterions().get("startDate")+"";
      //查询条件的结束时间
      String lastDate=request.getParameter("lastDate");
      String orgIdaa101=request.getParameter("orgIdaa101");
      String empIdaa102=request.getParameter("temp_empid");
      //页面显示的每段截取的时间.
      String temp_time=request.getParameter("temp_time");
      if(startDate!=null&&!startDate.equals("")){
        pagination.getQueryCriterions().put("startDate", startDate);
      }
      if(lastDate!=null&&!lastDate.equals("")){
        pagination.getQueryCriterions().put("lastDate", lastDate);
      }
      if(orgIdaa101!=null&&!orgIdaa101.equals("")){
        pagination.getQueryCriterions().put("orgIdaa101", orgIdaa101);
      }
      if(empIdaa102!=null&&!empIdaa102.equals("")){
        pagination.getQueryCriterions().put("empIdaa102", empIdaa102);
      }
      if(temp_time!=null&&!temp_time.equals("")){
        pagination.getQueryCriterions().put("temp_time", temp_time);
      }
      if(!pagination.getQueryCriterions().isEmpty()){
      IEmpAccountBS empAccountBS = (IEmpAccountBS) BSUtils.getBusinessService("empAccountBS",
          this, mapping.getModuleConfig());
      SecurityInfo securityInfo=(SecurityInfo)request.getSession().getAttribute("SecurityInfo");
     List list=empAccountBS.findEmpAccountDayList_sy(pagination,securityInfo);
     EmpAccountAF empAccountAF=(EmpAccountAF)form;
     //显示本期发生金额
     String temp_credit=(String) pagination.getQueryCriterions().get("temp_credit");
     empAccountAF.setTemp_credit(temp_credit);
     //显示本期发生金额
     String temp_debit=(String) pagination.getQueryCriterions().get("temp_debit");
     empAccountAF.setTemp_debit(temp_debit);
     //显示本期发生利息
     String temp1_interest=(String) pagination.getQueryCriterions().get("temp1_interest");
     empAccountAF.setCurInterest(temp1_interest);
     empAccountAF.setList(list);
     //保存查询条件在页面显示.
     empIdaa102=(String) pagination.getQueryCriterions().get("empIdaa102");
     empAccountAF.setEmpIdaa102(empIdaa102);
     lastDate=(String) pagination.getQueryCriterions().get("lastDate");
     empAccountAF.setLastDate(lastDate);
     orgIdaa101=(String) pagination.getQueryCriterions().get("orgIdaa101");
     empAccountAF.setOrgIdaa101(orgIdaa101);
     startDate=(String) pagination.getQueryCriterions().get("startDate");
     empAccountAF.setStartDate(startDate);
       String nameba001=(String) pagination.getQueryCriterions().get("nameba001");
       empAccountAF.setNameba001(nameba001);
 
       String nameba002=(String) pagination.getQueryCriterions().get("nameba002");
         empAccountAF.setNameba002(nameba002);
     List printList=(List) pagination.getQueryCriterions().get("printList");
     request.getSession().setAttribute("printList", printList);
     request.setAttribute("empAccountAF", empAccountAF);
      }
    } catch (Exception ex) {
      ex.printStackTrace();
    }
    return mapping.findForward("to_empAccount_day_list");
  }

  private Pagination getPagination(String paginationKey,
      HttpServletRequest request) {
    Pagination pagination = (Pagination) request.getSession().getAttribute(
        paginationKey);
    if (pagination == null) {
      pagination = new Pagination(0, 10, 1, "empHAFAccountFlow.id", "ASC",
          new HashMap(0));
      request.getSession().setAttribute(paginationKey, pagination);
    }

    return pagination;
  }
}
