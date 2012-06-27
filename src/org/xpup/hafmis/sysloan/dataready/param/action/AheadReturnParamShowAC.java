package org.xpup.hafmis.sysloan.dataready.param.action;

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
import org.xpup.hafmis.sysloan.dataready.param.bsinterface.IParamBS;
import org.xpup.hafmis.sysloan.dataready.param.dto.AheadReturnParamDTO;
import org.xpup.hafmis.sysloan.dataready.param.form.AheadReturnParamAF;
import org.xpup.security.common.domain.Userslogincollbank;
public class AheadReturnParamShowAC extends Action{
  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response) {
    try{
      AheadReturnParamAF aheadReturnParamAF=new AheadReturnParamAF();
      SecurityInfo securityInfo = (SecurityInfo) request.getSession().getAttribute(
      "SecurityInfo");
      AheadReturnParamDTO aheadReturnParamDTO=null;
      
      List loanbankList1 = null;
      try {
        // 取出用户权限放款银行,显示在下拉菜单中
        List loanbankList = securityInfo.getDkUserBankList();
        loanbankList1 = new ArrayList();
        Userslogincollbank bank = null;
        loanbankList1.add(new org.apache.struts.util.LabelValueBean("全部", "100"));
        Iterator itr1 = loanbankList.iterator();
        while (itr1.hasNext()) {
          bank = (Userslogincollbank) itr1.next();
          loanbankList1.add(new org.apache.struts.util.LabelValueBean(bank
              .getCollBankName(), bank.getCollBankId().toString()));
        }
      } catch (Exception e) {
        e.printStackTrace();
      }
      String loanBankId="";
      loanBankId=(String)request.getParameter("loanBankId");
      if(loanBankId!=null){
        IParamBS paramBS = (IParamBS) BSUtils
        .getBusinessService("paramBS", this, mapping.getModuleConfig());
        aheadReturnParamDTO=paramBS.findAheadReturnParamInfo(loanBankId);
      }else{
        aheadReturnParamAF=new AheadReturnParamAF();
        IParamBS paramBS = (IParamBS) BSUtils
        .getBusinessService("paramBS", this, mapping.getModuleConfig());
        aheadReturnParamDTO=paramBS.findAheadReturnParamInfo("100");
      }
      if(aheadReturnParamDTO!=null){
        aheadReturnParamAF.setAheadReturnParamDTO(aheadReturnParamDTO);
      }
      aheadReturnParamAF.getAheadReturnParamDTO().setLoanBankId(loanBankId);
      request.setAttribute("aheadReturnParamAF", aheadReturnParamAF);
      request.setAttribute("loanbankList1", loanbankList1);
    }catch(Exception e){
      e.printStackTrace();
    }
    return mapping.findForward("to_aheadreturnparam_show");
  }
}
