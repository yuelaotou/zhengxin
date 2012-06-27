package org.xpup.hafmis.sysloan.loancallback.contractchange.action;

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
import org.xpup.hafmis.sysloan.loancallback.contractchange.bsinterface.IContractchangeBS;
import org.xpup.hafmis.sysloan.loancallback.contractchange.dto.ContractchangeDTO;

public class ContractchangeMaintainAC extends LookupDispatchAction {

  protected Map getKeyMethodMap() {
    // TODO Auto-generated method stub
    Map map = new HashMap();
    map.put("button.print", "print");
    map.put("button.auditing.pass", "pass1");
    map.put("button.approval.pass", "pass2");
    return map;
  }

  public ActionForward print(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response) {
    ActionMessages messages = null;
    ContractchangeDTO dto = null;
    try {
      SecurityInfo securityInfo = (SecurityInfo) request.getSession()
          .getAttribute("SecurityInfo");
      IContractchangeBS contractchangeBS = (IContractchangeBS) BSUtils
          .getBusinessService("contractchangeBS", this, mapping
              .getModuleConfig());
      IdAF af = (IdAF) form;
      String id = af.getId().toString();// PKId
      dto = contractchangeBS.printContractchangeDTO(id, securityInfo);
      String time = securityInfo.getUserInfo().getPlbizDate();
      String opertname = securityInfo.getUserInfo().getUsername();
      if (dto != null) {
        request.setAttribute("contractchangeDTO", dto);
        request.setAttribute("time", time);
        request.setAttribute("opertname", opertname);
        String type = dto.getType();
        if ("1".equals(type)) {
          return mapping.findForward("to_contractchange_cell1");
        }
        if ("2".equals(type)) {
          return mapping.findForward("to_contractchange_cell2");
        }
        if ("3".equals(type)) {
          return mapping.findForward("to_contractchange_cell3");
        }
        if ("4".equals(type)) {
          return mapping.findForward("to_contractchange_cell4");
        }
        if ("5".equals(type)) {
          return mapping.findForward("to_contractchange_cell5");
        }
      }
    } catch (BusinessException bex) {
      messages = new ActionMessages();
      messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(bex
          .getMessage(), false));
      saveErrors(request, messages);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return null;
  }
  public ActionForward pass1(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response) {
    ActionMessages messages = null;
    ContractchangeDTO dto = null;
    try {
      SecurityInfo securityInfo = (SecurityInfo) request.getSession()
          .getAttribute("SecurityInfo");
      IContractchangeBS contractchangeBS = (IContractchangeBS) BSUtils
          .getBusinessService("contractchangeBS", this, mapping
              .getModuleConfig());
      IdAF af = (IdAF) form;
      String id = af.getId().toString();// PKId
      contractchangeBS.updatePl101_1(id, securityInfo);
     
      return mapping.findForward("to_contractchangeShowAC");
    } catch (BusinessException bex) {
      messages = new ActionMessages();
      messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(bex
          .getMessage(), false));
      saveErrors(request, messages);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return mapping.findForward("to_contractchangeShowAC");
  }
  public ActionForward pass2(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response) {
    ActionMessages messages = null;
    ContractchangeDTO dto = null;
    try {
      SecurityInfo securityInfo = (SecurityInfo) request.getSession()
          .getAttribute("SecurityInfo");
      IContractchangeBS contractchangeBS = (IContractchangeBS) BSUtils
          .getBusinessService("contractchangeBS", this, mapping
              .getModuleConfig());
      IdAF af = (IdAF) form;
      String id = af.getId().toString();// PKId
      contractchangeBS.updatePl101_2(id, securityInfo);
    } catch (BusinessException bex) {
      messages = new ActionMessages();
      messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(bex
          .getMessage(), false));
      saveErrors(request, messages);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return mapping.findForward("to_contractchangeShowAC");
  }
}
