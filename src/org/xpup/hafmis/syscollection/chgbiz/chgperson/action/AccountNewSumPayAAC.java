package org.xpup.hafmis.syscollection.chgbiz.chgperson.action;

import java.math.BigDecimal;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.xpup.common.util.BSUtils;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.syscollection.chgbiz.chgperson.bsinterface.IChgpersonDoBS;
import org.xpup.hafmis.syscollection.common.domain.entity.Org;
import org.xpup.hafmis.syscommon.arithmetic.ArithmeticFactory;
import org.xpup.hafmis.syscommon.arithmetic.ArithmeticInterface;

public class AccountNewSumPayAAC extends Action {

  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    response.setContentType("text/html;charset=UTF-8");
    response.setHeader("Cache-Control", "no-cache");
    String text=null;
    try {

      String orgID = request.getParameter("orgID");
      String salarybase=request.getParameter("salarybase");
      BigDecimal newOrgPay= new BigDecimal(0.00);
      BigDecimal newEmpPay= new BigDecimal(0.00);

      IChgpersonDoBS chgpersonDoBS = (IChgpersonDoBS) BSUtils
          .getBusinessService("chgpersonDoBS", this, mapping.getModuleConfig());
      
      SecurityInfo securityInfo = (SecurityInfo) request.getSession()
      .getAttribute("SecurityInfo");
      
      Org org = chgpersonDoBS.queryOrgById(new Integer(orgID),securityInfo);
      
     //缴存精度ID:payPrecision
      int payPrecision=Integer.parseInt(org.getPayPrecision().toString());
      //利用缴存精度
      ArithmeticInterface arithmeticInterface=ArithmeticFactory.getArithmetic().getArithmeticDAO(payPrecision); 
      try{
        if(org.getPayMode().toString().equals("1")){
          newOrgPay=arithmeticInterface.getPay(new BigDecimal(salarybase), org.getOrgRate());
          newEmpPay=arithmeticInterface.getPay(new BigDecimal(salarybase), org.getEmpRate());
        }
      }catch(Exception e){
        e.printStackTrace();
      }
      text="display('"+ newOrgPay+ "','"+ newEmpPay+"')";
      response.getWriter().write(text); 
      response.getWriter().close();
      
    }catch(Exception e){
      e.printStackTrace();
    }

    return null;
  }
}