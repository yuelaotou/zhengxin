package org.xpup.hafmis.sysloan.loanaccord.loanaccord.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
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
import org.xpup.common.util.Pagination;
import org.xpup.hafmis.common.form.IdAF;
import org.xpup.hafmis.common.util.BusiConst;
import org.xpup.hafmis.common.util.BusiTools;
import org.xpup.hafmis.orgstrct.dto.OfficeDto;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.sysloan.dataready.rate.bsinterface.IRateBS;
import org.xpup.hafmis.sysloan.dataready.rate.form.RateNewAF;
import org.xpup.hafmis.sysloan.loanaccord.loanaccord.bsinterface.ILoanaccordBS;
import org.xpup.hafmis.sysloan.loanaccord.loanaccord.dto.LoanaccordDTO;
import org.xpup.hafmis.sysloan.loanaccord.loanaccord.form.LoanaccordNewAF;
import org.xpup.hafmis.sysloan.loanaccord.loanaccord.form.LoanaccordShowAF;

public class LoanaccordTbMaintainAC extends LookupDispatchAction {
  protected Map getKeyMethodMap() {
    Map map = new HashMap();
    map.put("button.delete", "deleteLoanaccordInfo");
    map.put("button.print.doc", "printDocList");
    map.put("button.loanaccord.credence.printall", "printAll");
    return map;
  }

  public ActionForward deleteLoanaccordInfo(ActionMapping mapping,
      ActionForm form, HttpServletRequest request, HttpServletResponse response) {
    IdAF idAF = (IdAF) form;
    ActionMessages messages = null;
    try {
      String flowHeadId = (String) idAF.getId();
      SecurityInfo securityInfo = (SecurityInfo) request.getSession()
          .getAttribute("SecurityInfo");
      ILoanaccordBS loanaccordBS = (ILoanaccordBS) BSUtils.getBusinessService(
          "loanaccordBS", this, mapping.getModuleConfig());
      loanaccordBS.removeLoanaccordInfo(flowHeadId, securityInfo);
    } catch (BusinessException bex) {
      messages = new ActionMessages();
      messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(bex
          .getMessage(), false));
      saveErrors(request, messages);
    }
    return mapping.findForward("loanaccordTbShowAC");
  }

  public ActionForward printDocList(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response) {
    IdAF idAF = (IdAF) form;
    String flowHeadId = (String) idAF.getId();
    ActionMessages messages = null;
    SecurityInfo securityInfo = (SecurityInfo) request.getSession()
        .getAttribute("SecurityInfo");
    ILoanaccordBS loanaccordBS = (ILoanaccordBS) BSUtils.getBusinessService(
        "loanaccordBS", this, mapping.getModuleConfig());
    try {
      LoanaccordDTO loanaccordDTO = loanaccordBS.printLoanaccordInfo(
          flowHeadId, securityInfo);
      if (loanaccordDTO != null) {
        request.getSession().setAttribute("loanaccordDTOInfo", loanaccordDTO);
      } else {
        throw new BusinessException("合同编号有问题，打印失败！");
      }
      request.setAttribute("URL", "loanaccordTbForwardUrlAC.do");
    } catch (BusinessException bex) {
      messages = new ActionMessages();
      messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(bex
          .getMessage(), false));
      saveErrors(request, messages);
    }
    return mapping.findForward("to_loanaccord_print");
  }

  public ActionForward printAll(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response) {
    return mapping.findForward("loanaccordall_cell");
  }
}
