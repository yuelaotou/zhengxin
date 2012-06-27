package org.xpup.hafmis.sysloan.loancallback.advancepayloan.action;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.actions.LookupDispatchAction;
import org.xpup.common.exception.BusinessException;
import org.xpup.common.util.BSUtils;
import org.xpup.hafmis.common.form.IdAF;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.sysloan.loanaccord.loanaccord.bsinterface.ILoanaccordBS;
import org.xpup.hafmis.sysloan.loancallback.advancepayloan.bsinterface.IAdvancepayloanBS;
import org.xpup.hafmis.sysloan.loancallback.advancepayloan.form.AdvancepayloanTaAF;

public class AdvancepayloanTaMainTainAC extends LookupDispatchAction {

  protected Map getKeyMethodMap() {
    // TODO Auto-generated method stub
    Map map = new HashMap();
    map.put("button.sure", "sure");
    return map;
  }

  public ActionForward sure(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response) {
    ActionMessages messages = null;
    try {
      SecurityInfo securityInfo = (SecurityInfo) request.getSession()
          .getAttribute("SecurityInfo");
      IAdvancepayloanBS advancepayloanBS = (IAdvancepayloanBS) BSUtils
          .getBusinessService("advancepayloanBS", this, mapping
              .getModuleConfig());
      AdvancepayloanTaAF af = (AdvancepayloanTaAF) form;
      String contractId = af.getContractId();// 合同编号
      String new_term = af.getNew_term();// 新借款期限
      String type = af.getType();// 类型
      advancepayloanBS.insert(contractId, new_term, type, securityInfo);
      messages = new ActionMessages();
      messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("操作成功！",
          false));
      saveErrors(request, messages);
    } catch (BusinessException bex) {
      messages = new ActionMessages();
      messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(bex
          .getMessage(), false));
      saveErrors(request, messages);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return mapping.findForward("to_advancepayloanTaShowAC");
  }

}
