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
import org.xpup.hafmis.sysloan.loancallback.destoryback.dto.DestoryBackTaDTO;

public class DestoryBackTaSaveAC extends Action {
  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response) {
    ActionMessages messages = null;
    DestoryBackTaAF destoryBackTaAF = (DestoryBackTaAF) form;
    DestoryBackTaDTO destoryBackTaDTO = new DestoryBackTaDTO();
    try {
      messages = new ActionMessages();
      if (!isTokenValid(request)) {
        messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
            "请不要重复提交！", false));
        saveErrors(request, messages);
      } else {
        resetToken(request);
        String loanKouAcc = destoryBackTaAF.getLoanKouAcc().trim();// 贷款账号
        String contractId = destoryBackTaAF.getContractId().trim();// 合同编号
        String borrowerName = destoryBackTaAF.getBorrowerName().trim();// 借款人姓名
        String cardKindName = destoryBackTaAF.getCardKindName().trim(); // 显示证件类型对应的名称
        String cardNum = destoryBackTaAF.getCardNum().trim(); // 证件号码
        String overplusLoanMoney = destoryBackTaAF.getOverplusLoanMoney()
            .toString().trim();// 剩余本金
        String loanModeName = destoryBackTaAF.getLoanModeName().trim();// 还款方式
        String backUnit = destoryBackTaAF.getBackUnit().trim();
        String noBackMoney = destoryBackTaAF.getNoBackMoney().toString().trim();// 核销未收回金额
        String backMoney = destoryBackTaAF.getBackMoney().toString().trim();// 收回金额
        String backunitName = destoryBackTaAF.getAssistantOrgId().trim();// 收回单位名称
        String loanassistantorgId = destoryBackTaAF.getLoanassistantorgId()
            .trim();// 收回单位ID
        destoryBackTaDTO.setLoanKouAcc(loanKouAcc);
        destoryBackTaDTO.setContractId(contractId);
        destoryBackTaDTO.setBorrowerName(borrowerName);
        destoryBackTaDTO.setCardKindName(cardKindName);
        destoryBackTaDTO.setCardNum(cardNum);
        destoryBackTaDTO
            .setOverplusLoanMoney(new BigDecimal(overplusLoanMoney));
        destoryBackTaDTO.setLoanMode(loanModeName);
        destoryBackTaDTO.setBackUnit(backUnit);
        destoryBackTaDTO.setBackunitName(backunitName);
        destoryBackTaDTO.setNoBackMoney(new BigDecimal(noBackMoney));
        destoryBackTaDTO.setBackMoney(new BigDecimal(backMoney));
        destoryBackTaDTO.setLoanassistantorgId(loanassistantorgId);
        SecurityInfo securityInfo = (SecurityInfo) request.getSession()
            .getAttribute("SecurityInfo");
        IDestoryBackBS destoryBackBS = (IDestoryBackBS) BSUtils
            .getBusinessService("destoryBackBS", this, mapping
                .getModuleConfig());
        // 添加核销收回信息
        destoryBackBS.saveDestoryBack(destoryBackTaDTO, securityInfo);
        destoryBackTaAF.reset(mapping, request);
      }

    } catch (BusinessException bex) {
      messages = new ActionMessages();
      messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(bex
          .getMessage(), false));
      saveErrors(request, messages);
    } catch (Exception e) {
      e.printStackTrace();
    }
    request.setAttribute("printInfo", "print");
    request.getSession()
        .setAttribute("destoryBackTaPrintDTO", destoryBackTaDTO);
    return mapping.findForward("destoryBackTaShowAC");
  }
}
