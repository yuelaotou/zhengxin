package org.xpup.hafmis.syscollection.chgbiz.chgorgrate.action;

import java.math.BigDecimal;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.xpup.common.util.BSUtils;
import org.xpup.hafmis.syscollection.chgbiz.chgorgrate.bsinterface.IChgorgrateBS;
import org.xpup.hafmis.syscollection.common.domain.entity.Org;
import org.xpup.hafmis.syscommon.arithmetic.ArithmeticFactory;
import org.xpup.hafmis.syscommon.arithmetic.ArithmeticInterface;

public class AccountNewSumPayAC extends Action {

  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    response.setContentType("text/html;charset=UTF-8");
    response.setHeader("Cache-Control", "no-cache");
    String text=null;
    try {

      String orgID = request.getParameter("orgID");
      String orgRate=request.getParameter("new_orgrate");
      String empRate=request.getParameter("new_emprate");
      
      String newSumPay="";
      BigDecimal orgPay=new BigDecimal(0.00);
      BigDecimal empPay=new BigDecimal(0.00);
      
      IChgorgrateBS chgorgrateBS = (IChgorgrateBS) BSUtils
      .getBusinessService("chgorgrateBS", this, mapping.getModuleConfig());
      Org org = chgorgrateBS.queryOrgByorgID(orgID);
      
      //缴存精度ID:payPrecision
      int payPrecision=Integer.parseInt(org.getPayPrecision().toString());
      //利用缴存精度
      ArithmeticInterface arithmeticInterface=ArithmeticFactory.getArithmetic().getArithmeticDAO(payPrecision); 
      System.out.println(arithmeticInterface);
      try{
        
        //计算变更后缴额
        List list = chgorgrateBS.queryEmpSalaryByTIAOJIAN(orgID);
        for(int i=0;i<list.size();i++){
          BigDecimal salarybase =(BigDecimal) list.get(i);
          orgPay=orgPay.add(arithmeticInterface.getPay(salarybase, new BigDecimal(orgRate)));
          empPay=empPay.add(arithmeticInterface.getPay(salarybase, new BigDecimal(empRate)));
        }
        newSumPay=orgPay.add(empPay).toString();
        
      }catch(Exception e){
        e.printStackTrace();
      }
      text="display('"+newSumPay+"')";
      response.getWriter().write(text); 
      response.getWriter().close();
      
    }catch(Exception e){
      e.printStackTrace();
    }

    return null;
  }
}