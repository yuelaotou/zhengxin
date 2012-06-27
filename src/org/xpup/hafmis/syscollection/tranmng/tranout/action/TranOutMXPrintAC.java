package org.xpup.hafmis.syscollection.tranmng.tranout.action;

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
import org.xpup.hafmis.syscollection.common.domain.entity.TranOutTail;
import org.xpup.hafmis.syscollection.systemmaintain.loanpara.bsinterface.ILoanDocNumDesignBS;
import org.xpup.hafmis.syscollection.tranmng.tranout.bsinterface.ItranoutBS;

public class TranOutMXPrintAC extends Action{
  
  public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        ActionMessages messages =null;
        List list=null;
    try{
      String headid=request.getParameter("headid");
      ItranoutBS tranoutBS = (ItranoutBS) BSUtils.getBusinessService(
          "tranoutBS", this, mapping.getModuleConfig());
      SecurityInfo securityInfo = (SecurityInfo) request.getSession()
      .getAttribute("SecurityInfo");
      list = tranoutBS.findTranOutInfoMXPrint(headid);
      TranOutTail tranOutTail =(TranOutTail)list.get(0);
      String orgId = tranOutTail.getTranOutHead().getTranOutOrg().getId().toString();
      String[] str = tranoutBS.queryOfficeBankNames(orgId, "2", headid, "E", securityInfo);
      String bank = str[1];
      
//    wuht      
      ILoanDocNumDesignBS loanDocNumDesignBS = (ILoanDocNumDesignBS) BSUtils
      .getBusinessService("loanDocNumDesignBS", this, mapping.getModuleConfig());
      String userName="";
      SecurityInfo securityInfo1=(SecurityInfo)request.getSession().getAttribute("SecurityInfo");
        try {
          String name = loanDocNumDesignBS .getNamePara();

          if (name.equals("1")) {
            userName = securityInfo.getUserName();
          } else {
            userName = securityInfo.getRealName();
          }

        } catch (Exception e) {
          // TODO Auto-generated catch block
          e.printStackTrace();
        }
        String collectionBankId="";
        String collectionBankName="";
   
        if(orgId!=null && !orgId.equals("")){  
           collectionBankName=loanDocNumDesignBS.queryCollectionBankNameById(orgId, securityInfo);
        }
        String bizDate = securityInfo.getUserInfo().getBizDate();
        request.setAttribute("userName", userName);
        request.setAttribute("bizDate", bizDate);
        request.setAttribute("collectionBankName", collectionBankName);
//      wuht
      
      request.setAttribute("AA101bank", bank);
      request.setAttribute("tranTailcelllist", list);
      request.setAttribute("URL", "javascript:history.back();");
    }catch(Exception bex){
      messages=new ActionMessages();
      messages.add(ActionMessages.GLOBAL_MESSAGE,new ActionMessage("²Ù×÷Ê§°Ü£¡",
          false));
      saveErrors(request, messages);
    }
    if(list.size()==1){
      return mapping.findForward("to_print_one");
    }else{
      return mapping.findForward("to_print");
    }
  }

}