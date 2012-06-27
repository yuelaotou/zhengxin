package org.xpup.hafmis.syscollection.querystatistics.operationflow.empoperationflow.action;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
import org.xpup.hafmis.common.util.BusiConst;
import org.xpup.hafmis.common.util.BusiTools;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.syscollection.querystatistics.operationflow.empoperationflow.bsinterface.IEmpOperationFlowBS;
import org.xpup.hafmis.syscollection.querystatistics.operationflow.empoperationflow.dto.EmpOperationFlowTotalDTO;
import org.xpup.hafmis.syscollection.querystatistics.operationflow.empoperationflow.form.EmpOperationFlowAF;

/**
 * 根据单位编号、单位名称、单位状态查询单位信息
 * 
 *@author 李娟
 *2007-6-27
 */
public class EmpOperationFlowTaShowAC extends Action {

  public static final String PAGINATION_KEY = "org.xpup.hafmis.syscollection.querystatistics.operationflow.empoperationflow.action.EmpOperationFlowTaShowAC";
  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    ActionMessages messages =null;
    SecurityInfo securityInfo=(SecurityInfo)request.getSession().getAttribute("SecurityInfo");
    try{
      EmpOperationFlowAF empOperationFlowAF=new EmpOperationFlowAF();
      
      Map opStatusMap=BusiTools.listBusiProperty(BusiConst.CLEARACCOUNTSTATUS);
      Map opTypeMap=BusiTools.listBusiProperty(BusiConst.CLEARACCOUNTBUSINESSTYPE);
      
      empOperationFlowAF.setOpStatusMap(opStatusMap);
      empOperationFlowAF.setOpTypeMap(opTypeMap);
      
      Pagination pagination = getPagination(EmpOperationFlowTaShowAC.PAGINATION_KEY, request); 
      
      PaginationUtils.updatePagination(pagination, request);
      String orgbusinessflowHeadID=request.getParameter("orgbusinessflowHeadID");
      String empid=request.getParameter("empId");
      String orgid=request.getParameter("orgId");
      if(empid != null || orgbusinessflowHeadID != null|| orgid != null){
        pagination.getQueryCriterions().put("type", "1");
      }
      if(orgbusinessflowHeadID != null){
      pagination.getQueryCriterions().put("orgbusinessflowHeadID", orgbusinessflowHeadID);
      }
      if(empid != null){
      pagination.getQueryCriterions().put("empid", empid);
      }
      if(orgid != null){
        pagination.getQueryCriterions().put("orgid", orgid);
        }
      IEmpOperationFlowBS empOperationFlowBS = (IEmpOperationFlowBS) BSUtils
      .getBusinessService("empOperationFlowBS", this, mapping.getModuleConfig());
      
      List list = empOperationFlowBS.findEmpHAFAccountFlowList(pagination, securityInfo);
 //     List printList = empOperationFlowBS.findEmpHAFAccountFlowPrintList(pagination, securityInfo);
      List totallist = empOperationFlowBS.findEmpHAFAccountFlowTotalList(pagination, securityInfo);
      EmpOperationFlowTotalDTO empOperationFlowTotalDTO = new EmpOperationFlowTotalDTO();
      if(totallist.size()>0){
        empOperationFlowTotalDTO = (EmpOperationFlowTotalDTO)totallist.get(0);
      }
 //     pagination.getQueryCriterions().put("pageList", printList);
      request.setAttribute("empflowlist", list);
      request.setAttribute("totallist", totallist);
      request.setAttribute("totalDTO", empOperationFlowTotalDTO);
      request.setAttribute("empOperationFlowAF", empOperationFlowAF);
      }catch(BusinessException bex){
        messages=new ActionMessages();
        messages.add(ActionMessages.GLOBAL_MESSAGE,new ActionMessage(bex.getMessage(),
            false));
        saveErrors(request, messages);
    }catch(Exception e){
      e.printStackTrace();
    }
    return mapping.findForward(getForword());
  }

  protected String getForword() {
    return "show_empOperationFlow";
  }

  private Pagination getPagination(String paginationKey,
      HttpServletRequest request) {
    Pagination pagination = (Pagination) request.getSession().getAttribute(
        paginationKey);
    if (pagination == null) {
      HashMap m = new HashMap();
      m.put("type", "0");
      pagination = new Pagination(0, 10, 1, " empHAFAccountFlow.orgHAFAccountFlow.settDate ", "ASC",
          m);
      request.getSession().setAttribute(paginationKey, pagination);
    }   

    return pagination;
  }
  
}


