/*
 * Generated by MyEclipse Struts
 * Template path: templates/java/JavaClass.vtl
 */
package org.xpup.hafmis.sysloan.loanapply.loanapply.action;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jcifs.smb.PictureUpload;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.actions.LookupDispatchAction;
import org.xpup.common.exception.BusinessException;
import org.xpup.common.util.BSUtils;
import org.xpup.hafmis.common.util.BusiConst;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.sysloan.common.loanconditionsset.ILoanConditionsParamSetBS;
import org.xpup.hafmis.sysloan.loanapply.loanapply.bsinterface.ILoanapplyBS;
import org.xpup.hafmis.sysloan.loanapply.loanapply.form.LoanapplyNewAF;

/**
 * MyEclipse Struts Creation date: 09-21-2007 XDoclet definition:
 * 
 * @struts.action validate="true"
 */
public class LoanapplyTaMainTianAC extends LookupDispatchAction {

  protected Map getKeyMethodMap() {
    Map map = new HashMap();

    map.put("button.sure", "sure");
    map.put("button.scan.baseinfo", "scan");

    return map;
  }

  public ActionForward sure(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response) {
    String contractid = (String) request.getSession()
        .getAttribute("contractid");
    ActionMessages messages = null;
    LoanapplyNewAF loanapplyaf = (LoanapplyNewAF) form;
    ILoanapplyBS loanapplyBS = (ILoanapplyBS) BSUtils.getBusinessService(
        "loanapplyBS", this, mapping.getModuleConfig());
    ILoanConditionsParamSetBS loanConditionsParamSetBS = (ILoanConditionsParamSetBS) BSUtils
        .getBusinessService("loanConditionsParamSetBS", this, mapping
            .getModuleConfig());
    SecurityInfo securityInfo = (SecurityInfo) request.getSession()
        .getAttribute("SecurityInfo");
    try {
      boolean isFalg = loanConditionsParamSetBS.isCanSysLoan(loanapplyaf,
          securityInfo);
      String contractId = loanapplyBS.saveBorrowerInfo(loanapplyaf, contractid,
          securityInfo);
      request.getSession().setAttribute("contractid", contractId);
      messages = new ActionMessages();
      messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("信息已保存！",
          false));
      saveErrors(request, messages);
    } catch (BusinessException e) {
      messages = new ActionMessages();
      messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(e
          .getLocalizedMessage().toString(), false));
      saveErrors(request, messages);

    } catch (Exception e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }

    return mapping.findForward("loanapply_new");
  }

  public ActionForward scan(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    ActionMessages messages = null;
    PictureUpload pu = new PictureUpload();
    String path;
    try {
      String contractId = (String)request.getSession().getAttribute("contractid");
      SecurityInfo securityInfo = (SecurityInfo) request.getSession()
          .getAttribute("SecurityInfo");
      String serverPath = BusiConst.UPLOAD_SERVER_PATH;
//      path = pu.upload(securityInfo.getUserInfo().getUserIp(), "picture",
//          serverPath);
      path = pu.upload(securityInfo.getUserInfo().getUserIp(), "picture",
          serverPath,contractId);
      pu.delete(securityInfo.getUserInfo().getUserIp(), "picture");
      ILoanapplyBS loanapplyBS = (ILoanapplyBS) BSUtils.getBusinessService(
          "loanapplyBS", this, mapping.getModuleConfig());
      LoanapplyNewAF loanapplyaf = (LoanapplyNewAF) form;
      loanapplyBS.updateBorrowerInfo(loanapplyaf.getBorrower().getContractId(),
          path);
      messages = new ActionMessages();
      messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("扫描完成", false));
      saveErrors(request, messages);
    } catch (BusinessException e) {
      e.printStackTrace();
      messages = new ActionMessages();
      messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(e
          .getLocalizedMessage().toString(), false));
      saveErrors(request, messages);
    }
    return mapping.findForward("loanapply_new");
  }
}