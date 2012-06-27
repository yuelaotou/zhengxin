package org.xpup.hafmis.syscollection.tranmng.tranin.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.xpup.common.util.BSUtils;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.syscollection.systemmaintain.loanpara.bsinterface.ILoanDocNumDesignBS;
import org.xpup.hafmis.syscollection.tranmng.tranin.bsinterface.ITraninBS;
import org.xpup.hafmis.syscollection.tranmng.tranin.form.TraninVidAF;

public class ShowTraninListPrintAC extends Action{
  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
   {
    try{
    String tranInHeadId=request.getParameter("headid");
    String temp_url=request.getParameter("url");
    ITraninBS traninBS = (ITraninBS) BSUtils.getBusinessService("traninBS",
        this, mapping.getModuleConfig());
    SecurityInfo securityInfo=(SecurityInfo)request.getSession().getAttribute("SecurityInfo");
    TraninVidAF traninVidAF = traninBS.print_sy(tranInHeadId,securityInfo);
    
//  wuht
    String orgId = traninVidAF.getInOrgId();         
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
//    wuht
    
    request.setAttribute("URL",temp_url);
    request.setAttribute("traninVidAF", traninVidAF);
    }catch(Exception e){
      e.printStackTrace();
    }
    return mapping.findForward("tranin_tail_cell");
  }

}
