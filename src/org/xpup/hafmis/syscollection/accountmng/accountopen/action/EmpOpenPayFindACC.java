package org.xpup.hafmis.syscollection.accountmng.accountopen.action;

import java.math.BigDecimal;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.xpup.hafmis.syscommon.arithmetic.ArithmeticFactory;
import org.xpup.hafmis.syscommon.arithmetic.ArithmeticInterface;
public class EmpOpenPayFindACC extends Action {


    public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    response.setContentType("text/html;charset=UTF-8");
    response.setHeader("Cache-Control", "no-cache");

    try {
      BigDecimal orgRate = new BigDecimal(request.getSession().getAttribute("orgRate").toString());
      BigDecimal empRate = new BigDecimal(request.getSession().getAttribute("empRate").toString());
      BigDecimal salaryBase = new BigDecimal(request.getParameter("salaryBase"));
      BigDecimal orgPay = new BigDecimal(0.00);
      BigDecimal empPay = new BigDecimal(0.00);
      int payPrecision=Integer.parseInt(request.getSession().getAttribute("payPrecision").toString());
      ArithmeticInterface arithmeticInterface=ArithmeticFactory.getArithmetic().getArithmeticDAO(payPrecision); 
      try{
      orgPay=arithmeticInterface.getPay(salaryBase, orgRate);
      empPay=arithmeticInterface.getPay(salaryBase, empRate);

      }catch(Exception e){
        e.printStackTrace();
      }
      String text = null;
      text = "displays('"+ orgPay+ "','"+ empPay+"')";
      response.getWriter().write(text);
      response.getWriter().close();
    } catch(Exception e){
      String text="backErrors('"+e.getLocalizedMessage()+"')";
      response.getWriter().write(text);
      response.getWriter().close();
    }
    return null;
  }

  }


