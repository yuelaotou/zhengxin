/**
 * Copy Right Information   : Goldsoft 
 * Project                  : BailenRolTaPrintAC
 * @Version                 : 1.0
 * @author                  : wangy
 * 生成日期                   :2007-10-30
 **/
package org.xpup.hafmis.sysloan.specialbiz.bailenrol.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.xpup.common.util.BSUtils;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.syscollection.systemmaintain.loanpara.bsinterface.ILoanDocNumDesignBS;
import org.xpup.hafmis.sysloan.specialbiz.bailenrol.bsinterface.IBailenRolBS;
import org.xpup.hafmis.sysloan.specialbiz.bailenrol.dto.BailenRolTaPrintDTO;

public class BailenRolTaPrintAC extends Action {

  /**
   * 办理保证金登记-打印凭证
   */
  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response) {
    BailenRolTaPrintDTO bailenRolTaPrintDTO = new BailenRolTaPrintDTO();
    bailenRolTaPrintDTO = (BailenRolTaPrintDTO) request.getSession()
        .getAttribute("bailenRolTaPrintDTO");
    try {
      IBailenRolBS bailenRolBS = (IBailenRolBS) BSUtils.getBusinessService(
          "bailenRolBS", this, mapping.getModuleConfig());
      SecurityInfo securityInfo = (SecurityInfo) request.getSession()
      .getAttribute("SecurityInfo");
      String loanBankName = bailenRolTaPrintDTO.getLoanBankName();
      loanBankName = bailenRolBS.changeBank(loanBankName);// 转换银行名称
      bailenRolTaPrintDTO.setLoanBankName(loanBankName);
      //String userName = securityInfo.getRealName();

      ILoanDocNumDesignBS loanDocNumDesignBS = (ILoanDocNumDesignBS) BSUtils
         .getBusinessService("sysloanloanDocNumDesignBS", this, mapping.getModuleConfig());
         String userName="";
         try {
           String name = loanDocNumDesignBS.getNamePara();

           if (name.equals("1")) {
             userName = securityInfo.getUserName();
           } else {
             userName = securityInfo.getRealName();
           }

         } catch (Exception e) {
           // TODO Auto-generated catch block
           e.printStackTrace();
         }
      String plbizDate = securityInfo.getUserInfo().getPlbizDate();
      bailenRolTaPrintDTO.setUserName(userName);//制单人
      bailenRolTaPrintDTO.setUserBizDate(plbizDate.subSequence(0, 4)+"年"+plbizDate.substring(4, 6)+"月"+plbizDate.substring(6, 8)+"日");//业务日期
      request.setAttribute("URL", "bailenRolTaShowAC.do");
      request.setAttribute("bailenRolTaPrintDTO", bailenRolTaPrintDTO);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return mapping.findForward("to_bailenrol_printta");
  }
}