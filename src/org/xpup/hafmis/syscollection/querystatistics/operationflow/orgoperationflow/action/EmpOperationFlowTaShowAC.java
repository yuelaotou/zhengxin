package org.xpup.hafmis.syscollection.querystatistics.operationflow.orgoperationflow.action;

import java.util.HashMap;
import java.util.List;

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
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.syscollection.common.domain.entity.OrgHAFAccountFlow;
import org.xpup.hafmis.syscollection.querystatistics.operationflow.orgoperationflow.bsinterface.IOrgbusinessflowBS;
import org.xpup.hafmis.syscollection.querystatistics.operationflow.orgoperationflow.dto.EmpOperationFlowTotalDTO;
import org.xpup.hafmis.syscollection.querystatistics.operationflow.orgoperationflow.form.EmpOperationFlowAF;

/**
 * 根据单位编号、单位名称、单位状态查询单位信息
 * 
 *@author 李娟
 *2007-6-27
 */
public class EmpOperationFlowTaShowAC extends Action {

  public static final String PAGINATION_KEY = "org.xpup.hafmis.syscollection.querystatistics.operationflow.orgoperationflow.action.EmpOperationFlowTaShowAC";
  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    ActionMessages messages =null;
    SecurityInfo securityInfo=(SecurityInfo)request.getSession().getAttribute("SecurityInfo");
    try{
      EmpOperationFlowAF empOperationFlowAF=new EmpOperationFlowAF();

      IOrgbusinessflowBS orgbusinessflowBS = (IOrgbusinessflowBS) BSUtils
      .getBusinessService("orgbusinessflowBS", this, mapping.getModuleConfig());
      Pagination pagination = getPagination(EmpOperationFlowTaShowAC.PAGINATION_KEY, request); 
      PaginationUtils.updatePagination(pagination, request);
//      String orgbusinessflowHeadID=request.getParameter("orgbusinessflowHeadID");
//      if(orgbusinessflowHeadID != null){
//        pagination.getQueryCriterions().put("orgbusinessflowHeadID", orgbusinessflowHeadID);
//      }
      String orgbusinessflowHeadID=(String)request.getSession().getAttribute("orgbusinessflowHeadID");
      if(orgbusinessflowHeadID != null){
      pagination.getQueryCriterions().put("orgbusinessflowHeadID", orgbusinessflowHeadID);
      }
      List list = orgbusinessflowBS.findEmpHAFAccountFlowList(pagination, securityInfo);
      List totallist = orgbusinessflowBS.findEmpHAFAccountFlowTotalList(pagination, securityInfo);
      EmpOperationFlowTotalDTO empOperationFlowTotalDTO = new EmpOperationFlowTotalDTO();
      if(totallist.size()>0){
        empOperationFlowTotalDTO = (EmpOperationFlowTotalDTO)totallist.get(0);
      }
      OrgHAFAccountFlow orgHAFAccountFlow = orgbusinessflowBS
      .findOrgHAFAccountFlow(orgbusinessflowHeadID);
      request.setAttribute("empflowlist", list);
      request.setAttribute("totallist", totallist);
      request.setAttribute("totalDTO", empOperationFlowTotalDTO);
      request.setAttribute("empOperationFlowAF", empOperationFlowAF);
      request.setAttribute("BizType", orgHAFAccountFlow.getBiz_Type());
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
    return "empOperationFlowTaShowAC";
  }

  private Pagination getPagination(String paginationKey,
      HttpServletRequest request) {
    Pagination pagination = (Pagination) request.getSession().getAttribute(
        paginationKey);
    if (pagination == null) {
      HashMap m = new HashMap();
      pagination = new Pagination(0, 10, 1, "empHAFAccountFlow.id", "DESC",
          m);
      request.getSession().setAttribute(paginationKey, pagination);
    }   
    return pagination;
  }
  
}


