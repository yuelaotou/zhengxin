package org.xpup.hafmis.sysloan.accounthandle.carryforward.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.security.common.domain.Userslogincollbank;

public class CarryforwardTbShowAC extends Action {

  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response){
    try {
      SecurityInfo securityInfo = (SecurityInfo) request.getSession()
      .getAttribute("SecurityInfo");
      request.setAttribute("timenow", securityInfo.getUserInfo().getPlbizDate().subSequence(0, 4));
      //插入下拉列表银行
      List temp_banklist = securityInfo.getDkUserBankList();
      List banklist = new ArrayList();
      if(!temp_banklist.isEmpty()){
        Iterator itr = temp_banklist.iterator();    
        while (itr.hasNext()) {
          Userslogincollbank userslogincollbank=(Userslogincollbank)itr.next();
          banklist.add(new org.apache.struts.util.LabelValueBean(
              userslogincollbank.getCollBankName().toString(), userslogincollbank.getCollBankId().toString()));
        }
      }
      request.getSession(true).setAttribute("banklist", banklist);
     } catch (Exception ex) {
      ex.printStackTrace();
    }
    return mapping.findForward("to_carryforwardTb_show");
  }
}