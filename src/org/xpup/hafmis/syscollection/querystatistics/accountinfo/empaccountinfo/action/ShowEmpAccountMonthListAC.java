package org.xpup.hafmis.syscollection.querystatistics.accountinfo.empaccountinfo.action;


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
public class ShowEmpAccountMonthListAC extends Action {

  public static final String PAGINATION_KEY = "org.xpup.hafmis.syscollection.querystatistics.accountinfo.empaccountinfo.action.ShowEmpAccountMonthListAC";

  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response) {
    ActionMessages messages = null;
    try {
      /**
       * 分页
       */
      Pagination pagination = getPagination(PAGINATION_KEY, request);
      PaginationUtils.updatePagination(pagination, request);
      saveToken(request);
      //查询条件时间段从页面上每行记录中取的.
      String startAndlastDateDate=request.getParameter("startAndlastDateDate");
      String startDate="";
      String lastDate="";
      //页面中每行的分段时间.
      if(startAndlastDateDate!=null&&!startAndlastDateDate.equals("")){
      startDate=startAndlastDateDate.substring(0, 8)+"";
      lastDate=startAndlastDateDate.substring(9, 17)+"";
      }
      //获取查询所用的empid和orgid
      String orgIdaa101=request.getParameter("orgIdaa101");
      String empIdaa102=request.getParameter("temp_empid");
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
      if(!pagination.getQueryCriterions().isEmpty()){
      IEmpAccountBS empAccountBS = (IEmpAccountBS) BSUtils.getBusinessService("empAccountBS",
          this, mapping.getModuleConfig());
      SecurityInfo securityInfo=(SecurityInfo)request.getSession().getAttribute("SecurityInfo");
     List list=empAccountBS.findEmpAccountMonthList_sy(pagination,securityInfo);
     EmpAccountAF empAccountAF=(EmpAccountAF)form;
     //显示本期发生金额
     String temp_credit=(String) pagination.getQueryCriterions().get("temp_credit");
     empAccountAF.setTemp_credit(temp_credit);
     //显示本期发生金额
     String temp_debit=(String) pagination.getQueryCriterions().get("temp_debit");
     empAccountAF.setTemp_debit(temp_debit);
     //显示本期利息
     //显示发生利息
     String temp1_interest=(String) pagination.getQueryCriterions().get("temp1_interest");
     empAccountAF.setCurInterest(temp1_interest);
     empAccountAF.setList(list);
     //以下是为了，保持查询条件。
     if(empIdaa102!=null&&!empIdaa102.equals("")){
     empAccountAF.setEmpIdaa102(empIdaa102);
     }else{
       empIdaa102=empAccountAF.getEmpIdaa102();
       empAccountAF.setEmpIdaa102(empIdaa102);
     }
     if(lastDate!=null&&!lastDate.equals("")){
       empAccountAF.setLastDate(lastDate);
       }else{
         lastDate=empAccountAF.getLastDate();
         empAccountAF.setLastDate(lastDate);
       }
      String nameba001= (String) pagination.getQueryCriterions().get("nameba001");
       empAccountAF.setNameba001(nameba001);

      String nameba002=(String) pagination.getQueryCriterions().get("nameba002");
       empAccountAF.setNameba002(nameba002);

     if(orgIdaa101!=null&&!orgIdaa101.equals("")){
       empAccountAF.setOrgIdaa101(orgIdaa101);
       }else{
         orgIdaa101=empAccountAF.getOrgIdaa101();
         empAccountAF.setOrgIdaa101(orgIdaa101);
       }
     if(startDate!=null&&!startDate.equals("")){
       empAccountAF.setStartDate(startDate);
       }else{
         startDate=empAccountAF.getStartDate();
         empAccountAF.setStartDate(startDate);
       }
     //打印准备数据
     List printList=(List) pagination.getQueryCriterions().get("printList");
     request.getSession().setAttribute("printList", printList);
     request.setAttribute("empAccountAF", empAccountAF);
      }
    } catch (Exception ex) {
      ex.printStackTrace();
    }
    return mapping.findForward("to_empAccount_month_list");
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
