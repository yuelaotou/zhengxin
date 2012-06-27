package org.xpup.hafmis.sysloan.loanapply.endorsecontract.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import org.xpup.common.util.Pagination;
import org.xpup.common.util.PaginationUtils;
import org.xpup.hafmis.common.util.BusiConst;
import org.xpup.hafmis.common.util.BusiTools;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.sysloan.loanapply.endorsecontract.bsinterface.IEndorsecontractBS;
import org.xpup.hafmis.sysloan.loanapply.endorsecontract.form.EndorsecontractTdAF;
import org.xpup.hafmis.sysloan.loanapply.endorsecontract.form.EndorsecontractTeAF;

/**
 * @author yuqf
 */
public class EndorsecontractTeShowAC extends Action {
  public static final String PAGINATION_KEY = "org.xpup.hafmis.sysloan.loanapply.endorsecontract.action.EndorsecontractTeShowAC";

  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    // TODO Auto-generated method stub
    // request.getSession().setAttribute("contractId", null);
    // request.getSession().setAttribute("comeFromType",null);
    ActionMessages messages = null;
    Pagination pagination = null;
    SecurityInfo securityInfo = (SecurityInfo) request.getSession()
        .getAttribute("SecurityInfo");
    pagination = getPagination(PAGINATION_KEY, request);
    PaginationUtils.updatePagination(pagination, request);
    IEndorsecontractBS endorsecontractBS = (IEndorsecontractBS) BSUtils
        .getBusinessService("endorsecontractBS", this, mapping
            .getModuleConfig());
    EndorsecontractTeAF endorsecontractTeAF = new EndorsecontractTeAF();
    try {
      endorsecontractTeAF = endorsecontractBS.queryList(pagination,
          securityInfo);
      List bankList = securityInfo.getDkUserBankList();
      endorsecontractTeAF.setBankList(bankList);
      Map contractstMap = BusiTools
          .listBusiProperty(BusiConst.PLCONTRACTSTATUS);
      endorsecontractTeAF.setContractstMap(contractstMap);
      request.setAttribute("theEndorsecontractTeAF",
          endorsecontractTeAF);
      // contractId = endorsecontractTeAF.getContractId();
      // request.getSession().setAttribute("contractId", contractId);
      // request.getSession().setAttribute("comeFromType", "0");
    } catch (BusinessException bex) {
      messages = new ActionMessages();
      messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(bex
          .getMessage(), false));
      saveErrors(request, messages);
    }
    return mapping.findForward("to_endorsecontractTe");
  }

  private Pagination getPagination(String paginationKey,
      HttpServletRequest request) {
    Pagination pagination = (Pagination) request.getSession().getAttribute(
        paginationKey);
    if (pagination == null) {
      pagination = new Pagination(0, 10, 1, "t1.contract_id", "ASC",
          new HashMap(0));
      request.getSession().setAttribute(paginationKey, pagination);
    }

    return pagination;
  }
}
