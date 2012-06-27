package org.xpup.hafmis.syscollection.chgbiz.chgpay.action;

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
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.syscollection.chgbiz.chgpay.bsinterface.IChgpayBS;

/**
 * @author 李鹏 2007-12-15
 */
public class ChgPayExportAC extends Action {
  public static final String PAGINATION_KEY = "org.xpup.hafmis.syscollection.chgbiz.chgpay.action.chgPayExportAC";
  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    ActionMessages messages = null;
    try {
      HttpSession session = request.getSession(false);
      Pagination pagination=new Pagination();
      
      String orgId=request.getParameter("orgId");
      String orgName=request.getParameter("orgName");
      pagination.getQueryCriterions().put("org.id", orgId);
      pagination.getQueryCriterions().put("org.orgInfo.name", orgName);
      String empIds=request.getParameter("empIds");
      String empNames=request.getParameter("empNames");
      String orgPays=request.getParameter("orgPays");
      String salaryBases=request.getParameter("salaryBases");
      String tatol=request.getParameter("tatol");
      int finalTatol=(Integer.parseInt(tatol)-1);
      String order[]=new String [finalTatol] ;
      
      if((Integer.parseInt(empIds))!=0){
        order[(Integer.parseInt(empIds)-1)]="emp.empId";
      }
      if((Integer.parseInt(empNames))!=0){
        order[(Integer.parseInt(empNames)-1)]="emp.empInfo.name";
      } 
      if((Integer.parseInt(orgPays))!=0){
        order[(Integer.parseInt(orgPays)-1)]="emp.orgPay";
      }   
      if((Integer.parseInt(salaryBases))!=0){
        order[(Integer.parseInt(salaryBases)-1)]="emp.salaryBase";
      }   
      pagination.getQueryCriterions().put("orderArray", order);

      String chgMonth=request.getParameter("chgMonth");
      pagination.getQueryCriterions().put("chgMonth", chgMonth);
      SecurityInfo securityInfo=(SecurityInfo)request.getSession().getAttribute("SecurityInfo");
      String ip=securityInfo.getUserInfo().getUserIp();
      String name=securityInfo.getUserInfo().getUsername();
      pagination.getQueryCriterions().put("ip", ip);
      pagination.getQueryCriterions().put("name", name);
     
      IChgpayBS chgpayBS = (IChgpayBS) BSUtils.getBusinessService("chgpayBS", this,
          mapping.getModuleConfig());
      List expList = chgpayBS.queryEmpOrgList(pagination);
      if (expList.size() > 0) {
        messages = new ActionMessages();
        messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("导出成功！",
            false));
        saveErrors(request, messages);
        request.getSession().setAttribute("explist", expList);
        response.sendRedirect(request.getContextPath()
            + "/ExportServlet?ISCANSHU=false&EXP_NAME=chgpay_exp");
      } else {
        messages = new ActionMessages();
        messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("没有数据！",
            false));
        saveErrors(request, messages);
      }
      return null;
    } catch (BusinessException bex) {
      messages = new ActionMessages();
      messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(bex
          .getMessage(), false));
      saveErrors(request, messages);
    } 
    return mapping.findForward("chgpayTaSouwAC");
  }
  
  protected String getPaginationKey() {
    return ChgpayTaSouwAC.PAGINATION_KEY;
  }
}
