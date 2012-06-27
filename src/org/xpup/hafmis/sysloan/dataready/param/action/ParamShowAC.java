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
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.xpup.common.util.BSUtils;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.sysloan.dataready.param.bsinterface.IParamBS;
import org.xpup.hafmis.sysloan.dataready.param.dto.ParamDTO;
import org.xpup.hafmis.sysloan.dataready.param.form.ParamAF;
import org.xpup.security.common.domain.Userslogincollbank;

public class ParamShowAC extends Action{
  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response) {
    ActionMessages messages =null;
    try{
      ParamAF paramAF=new ParamAF();
      SecurityInfo securityInfo = (SecurityInfo) request.getSession().getAttribute(
      "SecurityInfo");
      List loanbankList1 = null;
      List loanbankList = securityInfo.getDkUserBankList();
      try {
        // 取出用户权限放款银行,显示在下拉菜单中
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
      if(loanbankList.size()==0){
        messages = new ActionMessages();
        messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("请添加贷款银行！",
            false));
        saveErrors(request, messages);
        saveToken(request);
        request.setAttribute("paramAF", paramAF);
        request.setAttribute("loanbankList1", loanbankList1);
        return mapping.findForward("to_param_show");
      }
      String loanBankId="";
      if(request.getParameter("loanBankId")!=null){
        loanBankId=(String)request.getParameter("loanBankId");
      }else{
      //  Userslogincollbank bank1=(Userslogincollbank)securityInfo.getDkUserBankList().get(0);
        loanBankId="100";
      }
      IParamBS paramBS = (IParamBS) BSUtils
      .getBusinessService("paramBS", this, mapping.getModuleConfig());
      ParamDTO paramDTO=null;
      paramDTO=paramBS.findParamInfo(loanBankId, securityInfo);
      if(paramDTO!=null){
        paramAF.setParamDTO(paramDTO);
      }
      paramAF.getParamDTO().setLoanBankId(loanBankId);
      request.setAttribute("paramAF", paramAF);
      request.setAttribute("loanbankList1", loanbankList1);
    }catch(Exception e){
      e.printStackTrace();
    }
    return mapping.findForward("to_param_show");
  }
}
