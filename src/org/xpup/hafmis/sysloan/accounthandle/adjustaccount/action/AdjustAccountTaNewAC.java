package org.xpup.hafmis.sysloan.accounthandle.adjustaccount.action;

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
import org.xpup.hafmis.sysloan.accounthandle.adjustaccount.bsinterface.IAdjustAccountBS;
import org.xpup.hafmis.sysloan.accounthandle.adjustaccount.dto.AdjustAccountTaInfoDTO;
import org.xpup.hafmis.sysloan.accounthandle.adjustaccount.form.AdjustAccountAF;
import org.xpup.hafmis.sysloan.loancallback.loancallback.form.LoancallbackTaAF;

/**
 * 进行错帐调整业务的action
 * 
 * @author 付云峰
 */
public class AdjustAccountTaNewAC extends Action {

  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    if (!isTokenValid(request, true)) {
      ActionMessages messages = new ActionMessages();
      messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("请不要重复提交！",
          false));
      saveErrors(request, messages);
      saveToken(request);
    } else {
      try {
        // 得到是否打印的状态
        String isPrint = request.getParameter("isPrint");
        // 如果是批量调整，在打印凭证时则使用此贷款帐号
        String printLoanKouAcc = request.getParameter("loanKouAcc");

        AdjustAccountAF adjustAccountAF = (AdjustAccountAF) form;

        SecurityInfo securityInfo = (SecurityInfo) request.getSession()
            .getAttribute("SecurityInfo");

        String flowHeadId = adjustAccountAF.getAdjustAccountTaInfoDTO()
            .getFlowHeadId();
        String bizType = adjustAccountAF.getAdjustAccountTaInfoDTO()
            .getBizType();
        String autoOverPay = adjustAccountAF.getAdjustAccountTaInfoDTO()
            .getAutoOverPay();
        AdjustAccountTaInfoDTO adjustAccountTaInfoDTO = adjustAccountAF
            .getAdjustAccountTaInfoDTO();

        IAdjustAccountBS adjustAccountBS = (IAdjustAccountBS) BSUtils
            .getBusinessService("adjustAccountBS", this, mapping
                .getModuleConfig());

        String headId = adjustAccountBS.saveAdjustAccountInfo(flowHeadId, "",
            bizType, autoOverPay, securityInfo, adjustAccountTaInfoDTO);

        // 判断是否打印凭证
        if (isPrint.equals("print")) {
          // 由表单得到打印内容
          LoancallbackTaAF loancallbackTaAF = new LoancallbackTaAF();
          loancallbackTaAF = adjustAccountBS.findPrintCallbackInfo(headId);
          String bizDate = "";
          String temp_bizDate = securityInfo.getUserInfo().getPlbizDate();
          bizDate = temp_bizDate.substring(0, 4)+"-"+temp_bizDate.substring(4, 6)+"-"+temp_bizDate.substring(6, 8);
          request.setAttribute("bizDate",bizDate);
          request.setAttribute("LoancallbackTaAF", loancallbackTaAF);
          if(bizType.equals("1")){
            // 进入发放凭证
            return mapping.findForward("to_adjustaccount_putout_print");
          }else{
            return mapping.findForward("to_adjustaccount_print");
          }
        }
      } catch (Exception e) {
        e.printStackTrace();
        ActionMessages messages = new ActionMessages();
        messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
            "业务办理失败！", false));
        saveErrors(request, messages);
      }
    }
    return mapping.findForward("adjustAccountTaShowAC");
  }

}
