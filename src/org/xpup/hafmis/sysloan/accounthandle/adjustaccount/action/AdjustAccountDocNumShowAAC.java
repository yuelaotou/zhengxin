package org.xpup.hafmis.sysloan.accounthandle.adjustaccount.action;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.xpup.common.exception.BusinessException;
import org.xpup.common.util.BSUtils;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.sysloan.accounthandle.adjustaccount.bsinterface.IAdjustAccountBS;
import org.xpup.hafmis.sysloan.accounthandle.adjustaccount.dto.AdjustAccountTaInfoDTO;
import org.xpup.security.common.domain.Userslogincollbank;

/**
 * 通过凭证号调整的ajax Action
 * 
 * @author 付云峰
 */
public class AdjustAccountDocNumShowAAC extends Action {

  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    // TODO Auto-generated method stub
    response.setContentType("text/html;charset=UTF-8");
    response.setHeader("Cache-Control", "no-cache");
    String text = "";
    /** 错误信息 */
    String error = "";

    String resFlowHeadId = "";
    String bizType = "";
    String strBizType = "";
    String callbackMoney = "";
    String callbackInterest = "";
    String punishInterest = "";
    String borrowerName = "";
    String makePerson = "";
    String putOutMoney = "";
    String loanKouAcc = "";

    try {

      String flowHeadId = (String) request.getParameter("id");
      IAdjustAccountBS adjustAccountBS = (IAdjustAccountBS) BSUtils
          .getBusinessService("adjustAccountBS", this, mapping
              .getModuleConfig());

      SecurityInfo securityInfo = (SecurityInfo) request.getSession()
          .getAttribute("SecurityInfo");

      List loanbankList1 = null;
      List loanbankList2 = null;
      // 取出用户权限放款银行,显示在下拉菜单中
      List loanbankList = securityInfo.getDkUserBankList();
      loanbankList1 = new ArrayList();
      loanbankList2 = new ArrayList();
      Userslogincollbank bank = null;
      Iterator itr1 = loanbankList.iterator();
      while (itr1.hasNext()) {
        bank = (Userslogincollbank) itr1.next();
        loanbankList1.add(new org.apache.struts.util.LabelValueBean(bank
            .getCollBankName(), bank.getCollBankId().toString()));
        loanbankList2.add(bank.getCollBankId());
      }
      // 得到将要调整业务的信息
      AdjustAccountTaInfoDTO adjustAccountTaInfoDTO = adjustAccountBS
          .judgeLoanFlowHead(flowHeadId, loanbankList2,securityInfo);
      
      resFlowHeadId = adjustAccountTaInfoDTO.getFlowHeadId();
      bizType = adjustAccountTaInfoDTO.getBizType();
      callbackMoney = adjustAccountTaInfoDTO.getCallbackMoney().setScale(2).toString();
      putOutMoney = adjustAccountTaInfoDTO.getPutOutMoney().setScale(2).toString();
      callbackInterest = adjustAccountTaInfoDTO.getCallbackInterest().setScale(2)
          .toString();
      punishInterest = adjustAccountTaInfoDTO.getPunishInterest().setScale(2).toString();
      borrowerName = adjustAccountTaInfoDTO.getBorrowerName();
      makePerson = adjustAccountTaInfoDTO.getMakePerson();
      strBizType=adjustAccountTaInfoDTO.getStrBizType();
      loanKouAcc = adjustAccountTaInfoDTO.getLoanKouAcc();

      
    } catch (BusinessException bex) {
      error = bex.getLocalizedMessage();
    } catch (Exception e) {
      error = "查询失败!";
      e.printStackTrace();
    }

    text = "displays('" + resFlowHeadId + "','" + bizType + "','" + strBizType + "','"
        + callbackMoney + "','" + putOutMoney + "','" + callbackInterest
        + "','" + punishInterest + "','" + borrowerName + "','" + makePerson
        + "','" + error + "','" + loanKouAcc + "')";
    response.getWriter().write(text);
    response.getWriter().close();
    
    return null;
  }

}
