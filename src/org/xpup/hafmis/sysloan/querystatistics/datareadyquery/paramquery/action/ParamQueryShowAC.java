package org.xpup.hafmis.sysloan.querystatistics.datareadyquery.paramquery.action;

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
import org.xpup.hafmis.sysloan.querystatistics.datareadyquery.paramquery.bsinterface.IParamQueryBS;
import org.xpup.hafmis.sysloan.querystatistics.datareadyquery.paramquery.dto.ParamQueryDTO;
import org.xpup.hafmis.sysloan.querystatistics.datareadyquery.paramquery.form.ParamQueryAF;
import org.xpup.security.common.domain.Userslogincollbank;

public class ParamQueryShowAC extends Action{
  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response) {
    // TODO Auto-generated method stub
    try{
      ParamQueryAF paramQueryAF=new ParamQueryAF();
      String loanBankId="";
      loanBankId=(String)request.getParameter("loanBankId");
      SecurityInfo securityInfo = (SecurityInfo) request.getSession().getAttribute(
      "SecurityInfo");
      
      List loanbankList1 = null;
      try {
        // 取出用户权限放款银行,显示在下拉菜单中
        List loanbankList = securityInfo.getDkUserBankList();
        loanbankList1 = new ArrayList();
        Userslogincollbank bank = null;
        Iterator itr1 = loanbankList.iterator();
        while (itr1.hasNext()) {
          bank = (Userslogincollbank) itr1.next();
          loanbankList1.add(new org.apache.struts.util.LabelValueBean(bank
              .getCollBankName(), bank.getCollBankId().toString()));
        }
      } catch (Exception e) {
        e.printStackTrace();
      }
      ParamQueryDTO paramQueryDTO=null;
      if(loanBankId!=null){
        IParamQueryBS paramQueryBS = (IParamQueryBS) BSUtils
        .getBusinessService("paramQueryBS", this, mapping.getModuleConfig());
        paramQueryDTO=paramQueryBS.findParamQueryInfo(loanBankId);
      }
      if(paramQueryDTO!=null){
        paramQueryAF.setParamQueryDTO(paramQueryDTO);
      }
      paramQueryAF.getParamQueryDTO().setLoanBankId(loanBankId);
      request.getSession().setAttribute("paramQueryAF", paramQueryAF);
      request.setAttribute("paramQueryAF", paramQueryAF);
      request.setAttribute("loanbankList1", loanbankList1);
    }catch(Exception e){
      e.printStackTrace();
    }
    return mapping.findForward("to_paramquery_show");
  }
}
