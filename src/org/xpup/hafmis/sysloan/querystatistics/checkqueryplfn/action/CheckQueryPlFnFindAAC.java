package org.xpup.hafmis.sysloan.querystatistics.checkqueryplfn.action;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.xpup.common.exception.BusinessException;
import org.xpup.common.util.BSUtils;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.sysfinance.common.biz.queryflow.bsinterface.IQueryFlowBS;
import org.xpup.hafmis.sysloan.accounthandle.adjustaccount.bsinterface.IAdjustAccountBS;
import org.xpup.hafmis.sysloan.accounthandle.adjustaccount.dto.AdjustAccountTaInfoDTO;
import org.xpup.hafmis.sysloan.querystatistics.checkqueryplfn.bsinterface.ICheckQueryPlFnBS;
import org.xpup.security.common.domain.Userslogincollbank;

/**
 * 通过凭证号调整的ajax Action
 * 
 * @author 付云峰
 */
public class CheckQueryPlFnFindAAC extends Action {

  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    // TODO Auto-generated method stub
    response.setContentType("text/html;charset=UTF-8");
    response.setHeader("Cache-Control", "no-cache");
    String text = "";
    /** 错误信息 */
    String error = "";

    String empId = "";
    String empName = "";
    String startTime = "";
    String endTime = "";
    String contractId = "";
    List list=new ArrayList();
    int count=0;
    String flag="1";
    Object obj[]=new Object[7];
    try {

        empId= (String) request.getParameter("empId");
        empName= (String) request.getParameter("empName");
        startTime= (String) request.getParameter("startTime");
        endTime= (String) request.getParameter("endTime");
        contractId= (String) request.getParameter("contractId");
        SecurityInfo securityInfo = (SecurityInfo) request.getSession()
        .getAttribute("SecurityInfo");
        request.getSession().setAttribute("bizdateB", startTime);
        request.getSession().setAttribute("bizdateE", endTime);
        ICheckQueryPlFnBS checkQueryPlFnBS = (ICheckQueryPlFnBS) BSUtils.getBusinessService("checkQueryPlFnBS", this, mapping.getModuleConfig());
        list=checkQueryPlFnBS.showContrctList(empId, empName, startTime, endTime, contractId, securityInfo);
        if(list!=null){
          count=list.size();
          if(list.size()==1){
            obj=(Object[])list.get(0);
            contractId=obj[0].toString();
          }
        }
      
      

      text = "displays('" +count+ "','" + contractId + "')";
  response.getWriter().write(text);
  response.getWriter().close();
     
      
    }  catch (Exception e) {
      error = "查询失败!";
      e.printStackTrace();
    }

   
    
    return null;
  }

}
