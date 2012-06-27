package org.xpup.hafmis.sysloan.loancallback.destoryback.action;

import java.math.BigDecimal;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.xpup.common.exception.BusinessException;
import org.xpup.common.util.BSUtils;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.sysloan.loancallback.destoryback.form.DestoryBackTaAF;
import org.xpup.hafmis.sysloan.loancallback.destoryback.bsinterface.IDestoryBackBS;



public class DestoryBackTaFindAAC extends Action{
  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    response.setContentType("text/html;charset=UTF-8");
    response.setHeader("Cache-Control", "no-cache");
    ActionMessages messages = null;
    SecurityInfo securityInfo = (SecurityInfo) request.getSession()
        .getAttribute("SecurityInfo");
    DestoryBackTaAF destoryBackTaAF = new DestoryBackTaAF();
    String text = "";
    String message = "";
    String contractId = "";// 合同编号
    String borrowerName = "";// 借款人姓名
    String cardKindName = "";// 显示证件类型对应的名称
    String cardNum = ""; // 证件号码
    BigDecimal overplusLoanMoney=new BigDecimal(0.00);//剩余本金
    String loanModeName="";//还款方式
    BigDecimal noBackMoney=new BigDecimal(0.00);//核销未收回金额
    try {
      String loanKouAcc = (String) request.getParameter("loanKouAcc");
      IDestoryBackBS destoryBackBS = (IDestoryBackBS) BSUtils.getBusinessService(
          "destoryBackBS", this, mapping.getModuleConfig());
      destoryBackTaAF = destoryBackBS.queryContractInfo(loanKouAcc, 
          securityInfo);   
      contractId = destoryBackTaAF.getContractId();// 合同编号
      borrowerName = destoryBackTaAF.getBorrowerName();// 借款人姓名
      cardKindName = destoryBackTaAF.getCardKindName();// 证件类型
      cardNum  = destoryBackTaAF.getCardNum();// 证件号码
      overplusLoanMoney  = destoryBackTaAF.getOverplusLoanMoney();//剩余本金
      loanModeName = destoryBackTaAF.getLoanModeName();//还款方式
      noBackMoney=destoryBackTaAF.getNoBackMoney();//核销未收回金额  
    } catch (BusinessException bex) {
      message = bex.getMessage();
      messages = new ActionMessages();
      messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(bex
          .getMessage(), false));
      saveErrors(request, messages);
    }
    text = "display('" + contractId + "','" + borrowerName + "','" + cardKindName
        + "','" + cardNum + "'," + "'" + overplusLoanMoney + "','" + loanModeName + "','" + noBackMoney
        + "'";
    text += ",'" + message + "');";
    response.getWriter().write(text);
    response.getWriter().close();
    return null;
  }
}
