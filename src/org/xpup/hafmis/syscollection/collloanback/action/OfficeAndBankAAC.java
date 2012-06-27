package org.xpup.hafmis.syscollection.collloanback.action;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.xpup.common.util.BSUtils;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.syscollection.collloanback.bsinterface.ICollLoanbackBS;
import org.xpup.security.common.domain.Userslogincollbank;

/**
 * 办事处，归集银行 连动 Ajax
 * 
 *@author  郭婧平
 *2007-12-19
 */
public class OfficeAndBankAAC extends Action {

  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    SecurityInfo securityInfo=(SecurityInfo)request.getSession().getAttribute("SecurityInfo");
    String officeCode=request.getParameter("officeCode").trim();
    List collBankList1 = new ArrayList();
    
    response.setContentType("text/xml; charset=utf-8");
    PrintWriter out = response.getWriter();
    out.println("<?xml version=\"1.0\" encoding=\"utf-8\"?>");
    out.println("<options>");
    
    if(officeCode.equals("all")){
      List collBankList = securityInfo.getCollBankList();
      Userslogincollbank userslogincollbank = null;
      Iterator itr2 = collBankList.iterator();
      while (itr2.hasNext()) {
        userslogincollbank = (Userslogincollbank) itr2.next();
        collBankList1.add(new org.apache.struts.util.LabelValueBean(userslogincollbank.getCollBankName().toString(), userslogincollbank.getCollBankId().toString()));
      }
      for(int i=0;i<collBankList1.size();i++){
        String collBankId=(String) ((org.apache.struts.util.LabelValueBean)collBankList1.get(i)).getValue();
        String collBankName=(String)((org.apache.struts.util.LabelValueBean)collBankList1.get(i)).getLabel();
        out.println("<value>" + collBankId + "</value>");
        out.println("<text>" + collBankName + "</text>");
      }
    }else{
      ICollLoanbackBS collLoanbackBS = (ICollLoanbackBS) BSUtils
      .getBusinessService("collLoanbackBS", this, mapping.getModuleConfig());
      List collBankList = securityInfo.getCollBankList();
      collBankList1=collLoanbackBS.FindCollectionBankId(officeCode,collBankList);
      for(int i=0;i<collBankList1.size();i++){
        String collBankId=(String) ((org.apache.struts.util.LabelValueBean)collBankList1.get(i)).getLabel();
        String collBankName=(String)((org.apache.struts.util.LabelValueBean)collBankList1.get(i)).getValue();
        out.println("<value>" + collBankId + "</value>");
        out.println("<text>" + collBankName + "</text>");
      }
    }
    
    out.println("</options>");
    out.flush();
    out.close();
    
    return null;
  }
  
}