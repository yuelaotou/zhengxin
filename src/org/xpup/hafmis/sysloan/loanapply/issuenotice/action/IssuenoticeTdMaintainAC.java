package org.xpup.hafmis.sysloan.loanapply.issuenotice.action;

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
import org.xpup.common.util.Pagination;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.sysloan.dataready.loanpara.bsinterface.ILoanDocNumDesignBS;
import org.xpup.hafmis.sysloan.loanapply.issuenotice.bsinterface.IIssuenoticeBS;
import org.xpup.hafmis.sysloan.loanapply.issuenotice.dto.IssuenoticePrintDTO;

public class IssuenoticeTdMaintainAC extends LookupDispatchAction {
  public static final String PAGINATION_KEY = "org.xpup.hafmis.sysloan.loanapply.issuenotice.action.IssuenoticeTdShowAC";

  protected Map getKeyMethodMap() {
    Map map = new HashMap();
    map.put("button.revert", "revert");
    map.put("button.print", "print");
    return map;
  }

  public ActionForward revert(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    try {
      String[] settNum = null;
      String rowArray = request.getParameter("rowArrayHid");
      if (rowArray == null) {
        throw new BusinessException("请选择要划款的合同编号！");
      } else {
        settNum = rowArray.split("-");
      }
      SecurityInfo securityInfo = (SecurityInfo) request.getSession()
          .getAttribute("SecurityInfo");
      IIssuenoticeBS issuenoticeBS = (IIssuenoticeBS) BSUtils
          .getBusinessService("issuenoticeBS", this, mapping.getModuleConfig());
      issuenoticeBS.updateIssuenoticeBorrowerAcc_4(settNum, securityInfo);
    } catch (BusinessException e) {
      e.printStackTrace();
      ActionMessages messages = null;
      messages = new ActionMessages();
      messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
          "存在未打印划款凭证过的借款合同！！", false));
      saveErrors(request, messages);
      return mapping.findForward("issuenoticetd_show");
    } catch (Exception e) {
      e.printStackTrace();
    }
    return mapping.findForward("issuenoticetd_show");
  }

  public ActionForward print(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws BusinessException,Exception {
    String[] contractId = null;
    String rowArray = request.getParameter("rowArrayHid");
    if (rowArray == null) {
      throw new BusinessException("请选择要划款的合同编号！");
    } else {
      contractId = rowArray.split("-");
    }
    String realName = "";
    String loanBankId = "";
    SecurityInfo securityInfo = (SecurityInfo) request.getSession()
        .getAttribute("SecurityInfo");
    Pagination pagination = getPagination(IssuenoticeTdShowAC.PAGINATION_KEY,
        request);
    String bizDate = (String) securityInfo.getUserInfo().getPlbizDate();
    IIssuenoticeBS issuenoticeBS = (IIssuenoticeBS) BSUtils.getBusinessService(
        "issuenoticeBS", this, mapping.getModuleConfig());
    try {
      if (pagination.getQueryCriterions().get("loanBankId") != null) {
        loanBankId = (String) pagination.getQueryCriterions().get("loanBankId");
      }
      if (loanBankId == null || "".equals(loanBankId)) {
        throw new BusinessException("请按银行查询!!");
      }
      ILoanDocNumDesignBS loanDocNumDesignBS = (ILoanDocNumDesignBS) BSUtils
          .getBusinessService("sysloanloanDocNumDesignBS", this, mapping
              .getModuleConfig());
      if (loanDocNumDesignBS.getNamePara().equals("1")) {
        realName = securityInfo.getUserName();
      } else {
        realName = securityInfo.getRealName();
      }
      IssuenoticePrintDTO issuenoticePrintDTO = issuenoticeBS
          .getchunaPrint(loanBankId, contractId, bizDate, realName,
              securityInfo.getUserName());
      request.setAttribute("issuenoticePrintDTO", issuenoticePrintDTO);
    } catch (BusinessException e) {
      e.printStackTrace();
      ActionMessages messages = null;
      messages = new ActionMessages();
      messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(e
          .getLocalizedMessage(), false));
      saveErrors(request, messages);
      return mapping.findForward("issuenoticetd_show");
    } catch (Exception e) {
      e.printStackTrace();
      ActionMessages messages = null;
      messages = new ActionMessages();
      messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(e
          .getLocalizedMessage(), false));
      e.printStackTrace();
      saveErrors(request, messages);
      return mapping.findForward("issuenoticetd_show");
    }
    return mapping.findForward("to_issuenoticetd_print");
  }

  private Pagination getPagination(String paginationKey,
      HttpServletRequest request) {
    Pagination pagination = (Pagination) request.getSession().getAttribute(
        paginationKey);
    if (pagination == null) {
      HashMap m = new HashMap();
      pagination = new Pagination(0, 10, 1, "contractid", "DESC", m);

      request.getSession().setAttribute(paginationKey, pagination);
    }
    return pagination;
  }
}
