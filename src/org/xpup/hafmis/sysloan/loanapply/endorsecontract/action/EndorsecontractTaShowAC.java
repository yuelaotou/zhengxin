package org.xpup.hafmis.sysloan.loanapply.endorsecontract.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
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
import org.xpup.common.util.Pagination;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.sysloan.loanapply.endorsecontract.bsinterface.IEndorsecontractBS;
import org.xpup.hafmis.sysloan.loanapply.endorsecontract.dto.EndorsecontractTaDTO;
import org.xpup.hafmis.sysloan.loanapply.endorsecontract.form.EndorsecontractTaAF;
import org.xpup.security.common.domain.Userslogincollbank;

/**
 * @author yuqf
 */
public class EndorsecontractTaShowAC extends Action {
  public static final String PAGINATION_KEY = "org.xpup.hafmis.sysloan.loanapply.endorsecontract.action.EndorsecontractTaShowAC";

  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    // TODO Auto-generated method stub
    ActionMessages messages = null;
    String paramValue = "";
    String contractId = null;
    String comeFromType = null;
    String afterSure = null;
    EndorsecontractTaAF endorsecontractTaAF = new EndorsecontractTaAF();
    SecurityInfo securityInfo = (SecurityInfo) request.getSession()
        .getAttribute("SecurityInfo");
    String insert = (String) request.getSession().getAttribute("insert");
    IEndorsecontractBS endorsecontractBS = (IEndorsecontractBS) BSUtils
        .getBusinessService("endorsecontractBS", this, mapping
            .getModuleConfig());
    Pagination pagination = getPagination(PAGINATION_KEY, request);
    List loanBankNameList = new ArrayList();// 银行
    List bankList = securityInfo.getDkUserBankList();
    Userslogincollbank userslogincollbank = null;
    Iterator bank = bankList.iterator();
    while (bank.hasNext()) {
      userslogincollbank = (Userslogincollbank) bank.next();
      loanBankNameList.add(new org.apache.struts.util.LabelValueBean(
          userslogincollbank.getCollBankName(), userslogincollbank
              .getCollBankId().toString()));
    }
    try {
      comeFromType = (String) request.getAttribute("comeFromType");// 用来判断是否为维护传来的状态
      afterSure = (String) request.getAttribute("afterSure");
      paramValue = endorsecontractBS.queryParamValue(securityInfo);// 参数值('AB'
      String temp_an = (String) request.getAttribute("temp_an");
      if (temp_an == null) {
        if (comeFromType != null) {// 先判断是否是点击维护修改按钮过来
          contractId = (String) request.getSession().getAttribute("contractId");
          loanBankNameList = endorsecontractBS.queryBankList(contractId,
              securityInfo);

          endorsecontractTaAF = endorsecontractBS.queryContractInfo_(
              contractId, pagination, securityInfo, request);
          endorsecontractTaAF.setIsComeFromT5("0");// 是从维护按钮过来，合同编号按钮禁用
        } else if ("afterSure".equals(afterSure)) {// 点击确定后过来的
          contractId = (String) request.getSession().getAttribute("contractId");
          loanBankNameList = endorsecontractBS.queryBankList(contractId,
              securityInfo);

          endorsecontractTaAF = endorsecontractBS.queryContractInfo_(
              contractId, pagination, securityInfo, request);
          String error = (String) request.getAttribute("error");
          if (error == null) {
            endorsecontractTaAF.setIsComeFromT5("0");// 是从维护按钮过来，合同编号按钮禁用
          } else {
            endorsecontractTaAF = new EndorsecontractTaAF();
          }
        } else {
          // 从其他页面过来的
          contractId = (String) request.getSession().getAttribute("contractId");
          // 下面这行代码，点击节点进入时，银行下拉框将为空
          loanBankNameList = endorsecontractBS.queryBankList(contractId,
              securityInfo);
          endorsecontractTaAF = endorsecontractBS.queryContractInfo(contractId,
              pagination, securityInfo, request, insert);
        }
      }
    } catch (BusinessException bex) {
      bex.printStackTrace();
      messages = new ActionMessages();
      messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(bex
          .getMessage(), false));
      saveErrors(request, messages);
    }

    request.setAttribute("loanBankNameList", loanBankNameList);
    endorsecontractTaAF.setParamValue(paramValue);
    String temp_beentruster = endorsecontractTaAF.getBeentruster();
    endorsecontractTaAF.setTemp_beentruster(temp_beentruster);
    String aftermaintain = (String) request.getSession().getAttribute(
        "aftermaintain");
    if (aftermaintain != null && !"".equals(aftermaintain)) {
      request.getSession().setAttribute("aftermaintain", null);
    }
    String taIsNeedDel = (String) request.getAttribute("taIsNeedDel");
    endorsecontractTaAF.setIsNeedDel(taIsNeedDel);
    request.setAttribute("endorsecontractTaAF", endorsecontractTaAF);
    request.getSession().setAttribute("afterSure", null);
    return mapping.findForward("to_endorsecontractTa");
  }

  private Pagination getPagination(String paginationKey,
      HttpServletRequest request) {
    Pagination pagination = (Pagination) request.getSession().getAttribute(
        paginationKey);
    if (pagination == null) {
      pagination = new Pagination(0, 10, 1, "null", "ASC", new HashMap(0));
      request.getSession().setAttribute(paginationKey, pagination);
    }

    return pagination;
  }

}
