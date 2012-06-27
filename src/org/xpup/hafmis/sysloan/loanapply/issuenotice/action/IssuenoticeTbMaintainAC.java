package org.xpup.hafmis.sysloan.loanapply.issuenotice.action;

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
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.sysloan.common.domain.entity.Borrower;
import org.xpup.hafmis.sysloan.dataready.loanpara.bsinterface.ILoanDocNumDesignBS;
import org.xpup.hafmis.sysloan.loanapply.issuenotice.bsinterface.IIssuenoticeBS;
import org.xpup.hafmis.sysloan.loanapply.issuenotice.dto.IssuenoticeTaDTO;

import org.xpup.security.common.domain.Userslogincollbank;

public class IssuenoticeTbMaintainAC extends LookupDispatchAction {
  public static final String PAGINATION_KEY = "org.xpup.hafmis.sysloan.loanapply.issuenotice.action.IssuenoticeTbShowAC";

  protected Map getKeyMethodMap() {
    Map map = new HashMap();
    map.put("button.update", "update");
    map.put("button.delete", "delete");
    map.put("button.print", "print");
    return map;
  }

  public ActionForward update(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    try {
      IdAF idAF = (IdAF) form;
      String id = idAF.getId().toString();
      request.setAttribute("contractId", id);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return mapping.findForward("issuenoticeTaShowAC");
  }
  public ActionForward delete(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    try {
      IdAF idAF = (IdAF) form;
      String rowArray = idAF.getId().toString();
      request.getSession().setAttribute("rowArray", rowArray);
      SecurityInfo securityInfo = (SecurityInfo) request.getSession()
          .getAttribute("SecurityInfo");
      IIssuenoticeBS issuenoticeBS = (IIssuenoticeBS) BSUtils
          .getBusinessService("issuenoticeBS", this, mapping.getModuleConfig());
      // for (int i = 0; i < rowArray.length; i++) {
      issuenoticeBS.deleteIssuenotice(rowArray, securityInfo);
      // }
      request.setAttribute("delete", "1");
    } catch (BusinessException e) {
      e.printStackTrace();
      ActionMessages messages = null;
      messages = new ActionMessages();
      messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
          "该笔业务的状态不是下发通知书，不可以删除！", false));
      saveErrors(request, messages);
      return mapping.findForward("issuenoticetb_show");
    } catch (Exception e) {
      e.printStackTrace();
    }
    return mapping.findForward("issuenoticetb_show");
  }

  public ActionForward print(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    IdAF idAF = (IdAF) form;
    String rowArray = idAF.getId().toString();
    List list = new ArrayList();
    String realName = "";
    SecurityInfo securityInfo = (SecurityInfo) request.getSession()
        .getAttribute("SecurityInfo");
    String bizdate = (String) securityInfo.getUserInfo().getPlbizDate();
    request.setAttribute("bizDate", bizdate);

    try {
      IIssuenoticeBS issuenoticeBS = (IIssuenoticeBS) BSUtils
          .getBusinessService("issuenoticeBS", this, mapping.getModuleConfig());
      String houseType = "";
      // realName=issuenoticeBS.getUserRealName(securityInfo.getUserName());
      ILoanDocNumDesignBS loanDocNumDesignBS = (ILoanDocNumDesignBS) BSUtils
          .getBusinessService("sysloanloanDocNumDesignBS", this, mapping
              .getModuleConfig());

      try {
        String name = loanDocNumDesignBS.getNamePara();

        if (name.equals("1")) {
          realName = securityInfo.getUserName();
        } else {
          realName = securityInfo.getRealName();
        }

      } catch (Exception e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }
      // for (int i = 0; i < rowArray.length; i++) {
      String bizDate = "";
      houseType = issuenoticeBS.findHouseType(rowArray);
      IssuenoticeTaDTO issuenoticeTaDTO = new IssuenoticeTaDTO();
      issuenoticeTaDTO = issuenoticeBS.findIssuenoticeTaInfo(rowArray
          .toString(), houseType);
      bizDate = issuenoticeBS.findIssuenoticeBizDate(rowArray.trim());
      issuenoticeTaDTO.setBizDate(bizDate);
      Borrower borrower = new Borrower();
      borrower = issuenoticeBS.findIssuenoticeBorrower(rowArray);
      borrower.setIsPrintIou("1");
      list.add(issuenoticeTaDTO);
      // }
      request.setAttribute("cellList", list);
      request.setAttribute("username", realName);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return mapping.findForward("to_issuenoticetb_print");
  }

}
