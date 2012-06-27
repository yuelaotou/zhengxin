package org.xpup.hafmis.syscollection.accounthandle.clearaccount.action;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.xpup.common.util.Pagination;
import org.xpup.hafmis.common.util.BusiConst;
import org.xpup.hafmis.common.util.BusiTools;
import org.xpup.hafmis.syscollection.querystatistics.operationflow.empoperationflow.form.EmpOperationFlowAF;

/**
 * 根据单位编号、单位名称查询单位信息
 * 
 *@author 李娟
 *2007-6-27
 */
public class EmpOperationFlowTaFindAC extends Action {
  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {

    EmpOperationFlowAF f = (EmpOperationFlowAF) form;
    EmpOperationFlowAF empOperationFlowAF=new EmpOperationFlowAF();
    
    Map opStatusMap=BusiTools.listBusiProperty(BusiConst.CLEARACCOUNTSTATUS);
    Map opTypeMap=BusiTools.listBusiProperty(BusiConst.CLEARACCOUNTBUSINESSTYPE);
    
    empOperationFlowAF.setOpStatusMap(opStatusMap);
    empOperationFlowAF.setOpTypeMap(opTypeMap);
    
    request.setAttribute("empOperationFlowAF", empOperationFlowAF);
    
    HashMap criterions = makeCriterionsMap(f);
    Pagination pagination = new Pagination(0, f.getPageSize(), 1,
        "empHAFAccountFlow.id", "DESC", criterions);
    String paginationKey = EmpOperationFlowTaShowAC.PAGINATION_KEY;
    request.getSession().setAttribute(paginationKey, pagination);
    
    f.reset(mapping, request);

    return mapping.findForward(getForword());
  }

  protected String getForword() {
    return "show_empOperationFlow";
  }


  protected HashMap makeCriterionsMap(EmpOperationFlowAF form) {
    HashMap m = new HashMap();
    String orgid = form.getOrgid();
    if (!(orgid == null || "".equals(orgid))) {
      try {
        int iid = Integer.parseInt(orgid);
        m.put("orgid", new Integer(iid));
      } catch (NumberFormatException e) {
        m.put("orgid", new Integer(-10000));
      }
    }
    String orgname = form.getOrgname();
    if (!(orgname == null || orgname.length() == 0)){
      m.put("orgname", form.getOrgname());
    }

    String empid = form.getEmpid();
    if (!(empid == null || "".equals(empid))) {
      try {
        int iid = Integer.parseInt(empid);
        m.put("empid", new Integer(iid));
      } catch (NumberFormatException e) {
        m.put("empid", new Integer(-10000));
      }
    }
    String empname = form.getEmpname();
    if (!(empname == null || empname.length() == 0)){
      m.put("empname", form.getEmpname());
    }
    String noteNum = form.getNoteNum();
    if(!(noteNum == null || noteNum.length() == 0)){
      m.put("noteNum", form.getNoteNum());
    }
    String docNum = form.getDocNum();
    if(!(docNum == null || docNum.length() == 0)){
      m.put("docNum", form.getDocNum());
    }
    String opStatus = form.getOpStatus();
    if(!(opStatus == null || opStatus.length() == 0)){
      m.put("opStatus", form.getOpStatus());
    }
    
    String opType = form.getOpType();
    if(!(opType == null || opType.length() == 0)){
      m.put("opType", form.getOpType());
    }
    String inOpDate = form.getInOpDate() ;
    if(!(inOpDate == null || inOpDate.length() == 0)){
      m.put("inOpDate", form.getInOpDate());
    }
    String opDate = form.getOpDate() ;
    if(!(opDate == null || opDate.length() == 0)){
      m.put("opDate", form.getOpDate());
    }
    String inOpMoney = form.getInOpMoney();
    if(!(inOpMoney == null || inOpMoney.length() == 0)){
      m.put("inOpMoney", form.getInOpMoney());
    }
    String opMoney = form.getOpMoney();
    if(!(opMoney == null || opMoney.length() == 0)){
      m.put("opMoney", form.getOpMoney());
    }
    String opDirection = form.getOpDirection() ;
    if(!(opDirection == null || opDirection.length() == 0)){
      m.put("opDirection", form.getOpDirection());
    }
 
    
    return m;
  }

}
