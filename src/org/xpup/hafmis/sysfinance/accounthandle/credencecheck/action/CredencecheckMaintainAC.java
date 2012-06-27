package org.xpup.hafmis.sysfinance.accounthandle.credencecheck.action;

import java.util.HashMap;
import java.util.List;
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
import org.xpup.hafmis.sysfinance.accounthandle.credencecheck.bsinterface.ICredencecheckBS;
import org.xpup.hafmis.sysfinance.treasurermng.cashdayclear.bsinterface.ICashDayClearBS;

public class CredencecheckMaintainAC extends LookupDispatchAction {
  protected Map getKeyMethodMap() {
    Map map = new HashMap();
    map.put("button.checkc", "check");
    map.put("button.del.check", "delCheck");
    map.put("button.checkall", "checkall");
    map.put("button.del.checkall", "delCheckall");
    map.put("button.individual.flow", "individualFlow");
    return map;
  }

  public ActionForward check(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    ActionMessages messages = new ActionMessages();
    try {
      IdAF idAF = (IdAF) form;
      String id = idAF.getId().toString();
      SecurityInfo securityInfo = (SecurityInfo) request.getSession()
          .getAttribute("SecurityInfo");
      ICredencecheckBS credencecheckBS = (ICredencecheckBS) BSUtils
          .getBusinessService("credencecheckBS", this, mapping
              .getModuleConfig());
      credencecheckBS.credencecheckCheck(id, securityInfo);
    } catch (BusinessException bex) {
      messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(bex
          .getLocalizedMessage(), false));
      saveErrors(request, messages);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return mapping.findForward("to_credencecheck_show");
  }

  public ActionForward delCheck(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    ActionMessages messages = new ActionMessages();
    try {
      IdAF idAF = (IdAF) form;
      String id = idAF.getId().toString();
      SecurityInfo securityInfo = (SecurityInfo) request.getSession()
          .getAttribute("SecurityInfo");
      ICredencecheckBS credencecheckBS = (ICredencecheckBS) BSUtils
          .getBusinessService("credencecheckBS", this, mapping
              .getModuleConfig());
      credencecheckBS.credencecheckDelCheck(id, securityInfo);
    } catch (BusinessException bex) {
      messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(bex
          .getLocalizedMessage(), false));
      saveErrors(request, messages);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return mapping.findForward("to_credencecheck_show");
  }

  public ActionForward checkall(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    ActionMessages messages = new ActionMessages();
    try {
      List list = (List) request.getSession().getAttribute("countList");
      SecurityInfo securityInfo = (SecurityInfo) request.getSession()
          .getAttribute("SecurityInfo");
      ICredencecheckBS credencecheckBS = (ICredencecheckBS) BSUtils
          .getBusinessService("credencecheckBS", this, mapping
              .getModuleConfig());
      credencecheckBS.credencecheckCheckAll(list, securityInfo);
    } catch (BusinessException bex) {
      messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(bex
          .getLocalizedMessage(), false));
      saveErrors(request, messages);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return mapping.findForward("to_credencecheck_show");
  }

  public ActionForward delCheckall(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    ActionMessages messages = new ActionMessages();
    try {
      SecurityInfo securityInfo = (SecurityInfo) request.getSession()
          .getAttribute("SecurityInfo");
      ICredencecheckBS credencecheckBS = (ICredencecheckBS) BSUtils
          .getBusinessService("credencecheckBS", this, mapping
              .getModuleConfig());
      List list = (List) request.getSession().getAttribute("countList");
      credencecheckBS.credencecheckDelCheckAll(list, securityInfo);
    } catch (BusinessException bex) {
      messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(bex
          .getLocalizedMessage(), false));
      saveErrors(request, messages);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return mapping.findForward("to_credencecheck_show");
  }

  public ActionForward individualFlow(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    ActionMessages messages = new ActionMessages();
    try {
      IdAF idAF = (IdAF) form;
      String[] rowArray = idAF.getRowArray();
      SecurityInfo securityInfo = (SecurityInfo) request.getSession()
          .getAttribute("SecurityInfo");
      ICashDayClearBS cashDayClearBS = (ICashDayClearBS) BSUtils
          .getBusinessService("cashDayClearBS", this, mapping.getModuleConfig());
      // credenceType为0时，就是现金日记账的转账页面；为1时，就是银行存款日记账的转账页面
      String credenceType = (String) request.getSession().getAttribute(
          "credenceType_gjp");
      cashDayClearBS.cashDayClearTaTransfers(rowArray, credenceType,
          securityInfo);
    } catch (BusinessException bex) {
      messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(bex
          .getLocalizedMessage(), false));
      saveErrors(request, messages);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return mapping.findForward("cashdaycleartb_show");
  }
}
