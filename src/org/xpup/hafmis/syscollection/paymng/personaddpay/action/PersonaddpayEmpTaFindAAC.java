package org.xpup.hafmis.syscollection.paymng.personaddpay.action;

import java.math.BigDecimal;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.xpup.common.exception.BusinessException;
import org.xpup.common.util.BSUtils;
import org.xpup.common.util.Pagination;
import org.xpup.hafmis.common.util.BusiConst;
import org.xpup.hafmis.common.util.BusiTools;
import org.xpup.hafmis.syscollection.common.domain.entity.Emp;
import org.xpup.hafmis.syscollection.paymng.personaddpay.bsinterface.IPersonAddPayBS;

public class PersonaddpayEmpTaFindAAC extends Action {
  public static final String PAGINATION_KEY = "org.xpup.hafmis.syscollection.paymng.personaddpay.action.PersonAddPayTaShowAC";

  public ActionForward execute(ActionMapping mapping, ActionForm form,
    HttpServletRequest request, HttpServletResponse response)
    throws Exception {
  response.setContentType("text/html;charset=UTF-8");
  response.setHeader("Cache-Control", "no-cache");

  try {
    String empId = request.getParameter("empid");
    String text = null;
    IPersonAddPayBS personAddPayBS = (IPersonAddPayBS) BSUtils
    .getBusinessService("personAddPayBS", this, mapping.getModuleConfig());
    Pagination pagination = (Pagination) request.getSession().getAttribute(
        PAGINATION_KEY);
    String orgId=(String) pagination.getQueryCriterions().get("orgId");  
    Emp emp=personAddPayBS.findEmpInfo(empId, orgId);
    if(emp!=null){
    String sex=BusiTools.getBusiValue(Integer.parseInt(emp.getEmpInfo().getSex().toString()), BusiConst.SEX);
    String cardType=BusiTools.getBusiValue(Integer.parseInt(emp.getEmpInfo().getCardKind().toString()), BusiConst.DOCUMENTSSTATE);
    BigDecimal total=new BigDecimal(0.00);
    total=emp.getOrgPay().add(emp.getEmpPay());
    String totals=total.toString();
   
    text = "displays('"
        + BusiTools.convertSixNumber(emp.getEmpId().toString())
        + "','"
        + emp.getEmpInfo().getName()
        + "','"
        + cardType
        + "','"
        + emp.getEmpInfo().getCardNum()
        + "','"
        + emp.getEmpInfo().getBirthday()
        + "','"
        + sex
        + "','"
        + emp.getSalaryBase()
        + "',"       
        + emp.getOrgPay()
        + ","
        + emp.getEmpPay()
        + ","
        + totals
        +")";
    }else{
      text = "displays('"
        + ""
        + "','"
        + ""
        + "','"
        + ""
        + "','"
        + ""
        + "','"
        + ""
        + "','"
        + ""
        + "',"       
        + 0
        + ","
        + 0
        + ","
        + 0
        +")";
    }
    response.getWriter().write(text);
    response.getWriter().close();
  } catch(BusinessException e){
    String text="backErrors('"+e.getLocalizedMessage()+"')";
    response.getWriter().write(text);
    response.getWriter().close();
  }
  return null;
}

protected String getForword() {
  return "show_org_salarybase_changement_list";
}
protected String getPaginationKey() {
  return PersonAddPayTaShowAC.PAGINATION_KEY;
}
}
