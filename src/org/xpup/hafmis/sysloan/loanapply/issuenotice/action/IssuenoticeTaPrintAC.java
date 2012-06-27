package org.xpup.hafmis.sysloan.loanapply.issuenotice.action;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

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
import org.xpup.hafmis.sysloan.common.domain.entity.Borrower;
import org.xpup.hafmis.sysloan.loanapply.issuenotice.bsinterface.IIssuenoticeBS;
import org.xpup.hafmis.sysloan.loanapply.issuenotice.dto.IssuenoticeTaDTO;
import org.xpup.hafmis.sysloan.loanapply.issuenotice.form.IssuenoticeTaAF;

public class IssuenoticeTaPrintAC extends Action {
  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    IssuenoticeTaAF issuenoticeTaAF = (IssuenoticeTaAF) form;
    String realName = "";
    ActionMessages messages = null;
    try {
      SecurityInfo securityInfo = (SecurityInfo) request.getSession()
          .getAttribute("SecurityInfo");
      String bizdate = (String) securityInfo.getUserInfo().getPlbizDate();
      IssuenoticeTaAF printForm = new IssuenoticeTaAF();
      IIssuenoticeBS issuenoticeBS = (IIssuenoticeBS) BSUtils
          .getBusinessService("issuenoticeBS", this, mapping.getModuleConfig());
      List list = new ArrayList();
      String houseType = "";
      String bizDate = "";
      houseType = issuenoticeBS.findHouseType(issuenoticeTaAF.getContractId());
      IssuenoticeTaDTO issuenoticeTaDTO = new IssuenoticeTaDTO();
      issuenoticeTaDTO = issuenoticeBS.findIssuenoticeTaInfo(issuenoticeTaAF
          .getContractId(), houseType);
      bizDate = issuenoticeBS.findIssuenoticeBizDate(issuenoticeTaAF
          .getContractId());
      issuenoticeTaDTO.setBizDate(bizDate);

     list.add(issuenoticeTaDTO);
      request.setAttribute("bizDate", bizdate);
      issuenoticeBS.saveIssuenotice(issuenoticeTaAF, securityInfo);
      realName = issuenoticeBS.getUserRealName(securityInfo.getUserName());
      if ("1".equals((String) request.getParameter("print"))) {
        Borrower borrower  = issuenoticeBS.findIssuenoticeBorrower(issuenoticeTaAF
            .getContractId());
        request.setAttribute("cellList", list);
        request.setAttribute("save", "save");
        request.getSession().setAttribute("username", realName);
        return mapping.findForward("issuenoticeta_print");
      }
      request.setAttribute("save", "save");
      
    } catch (BusinessException bex) {
      messages = new ActionMessages();
      messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(bex
          .getMessage(), false));
      saveErrors(request, messages);
    } catch (Exception e) {
      e.printStackTrace();
    }
    issuenoticeTaAF.setIsPrint(null);
    return mapping.findForward("issuenotice_show");
  }
}
