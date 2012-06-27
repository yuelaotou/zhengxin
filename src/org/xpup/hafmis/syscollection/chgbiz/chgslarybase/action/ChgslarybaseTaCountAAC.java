package org.xpup.hafmis.syscollection.chgbiz.chgslarybase.action;

import java.math.BigDecimal;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.xpup.common.util.Pagination;
import org.xpup.common.exception.BusinessException;
import org.xpup.common.util.BSUtils;
import org.xpup.hafmis.syscollection.chgbiz.chgslarybase.bsinterface.IChgslarybaseBS;
import org.xpup.hafmis.syscollection.common.domain.entity.ChgPaymentTail;
import org.xpup.hafmis.syscommon.arithmetic.ArithmeticFactory;
import org.xpup.hafmis.syscommon.arithmetic.ArithmeticInterface;
public class ChgslarybaseTaCountAAC extends Action {
  public static final String PAGINATION_KEY = "org.xpup.hafmis.syscollection.chgbiz.chgslarybase.action.ChgslarybaseTaShowAC";

  public ActionForward execute(ActionMapping mapping, ActionForm form,
    HttpServletRequest request, HttpServletResponse response)
    throws Exception {
  response.setContentType("text/html;charset=UTF-8");
  response.setHeader("Cache-Control", "no-cache");

  try {
    String salaryBase2 = request.getParameter("salaryBase");
    String orgRate2 = request.getParameter("orgRate");
    String empRate2 = request.getParameter("empRate");
    String payPrecision2 =  request.getParameter("payPrecision");
    BigDecimal orgRate = new BigDecimal(request.getParameter("orgRate"));
    BigDecimal empRate = new BigDecimal(request.getParameter("empRate"));
    BigDecimal salaryBase = new BigDecimal(request.getParameter("salaryBase"));
    BigDecimal orgPay = new BigDecimal(0.00);
    BigDecimal empPay = new BigDecimal(0.00);
   //缴存精度ID:payPrecision
    int payPrecision=Integer.parseInt(request.getParameter("payPrecision"));
    //利用缴存精度
    ArithmeticInterface arithmeticInterface=ArithmeticFactory.getArithmetic().getArithmeticDAO(payPrecision); 
    try{
    orgPay=arithmeticInterface.getPay(salaryBase, orgRate);
    empPay=arithmeticInterface.getPay(salaryBase, empRate);

    }catch(Exception e){
      e.printStackTrace();
    }
    String text = null;
    text = "displays2('"+ orgPay+ "','"+ empPay+"')";
    response.getWriter().write(text);
    response.getWriter().close();
  } catch(Exception e){
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
  return ChgslarybaseTaShowAC.PAGINATION_KEY;
}
}
