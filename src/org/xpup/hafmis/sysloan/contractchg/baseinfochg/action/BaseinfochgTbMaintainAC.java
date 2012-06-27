/*
 * Generated by MyEclipse Struts
 * Template path: templates/java/JavaClass.vtl
 */
package org.xpup.hafmis.sysloan.contractchg.baseinfochg.action;

import java.io.IOException;
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
import org.xpup.hafmis.sysloan.contractchg.baseinfochg.bsinterface.IBaseinfochgBS;
import org.xpup.hafmis.sysloan.loanapply.loanapply.bsinterface.ILoanapplyBS;
import org.xpup.hafmis.sysloan.loanapply.loanapply.form.LoanapplyNewAF;

/**
 * MyEclipse Struts Creation date: 10-08-2007 XDoclet definition:
 * 
 * @struts.action validate="true"
 */
public class BaseinfochgTbMaintainAC extends LookupDispatchAction {
  /*
   * Generated Methods
   */

  /**
   * Method execute
   * 
   * @param mapping
   * @param form
   * @param request
   * @param response
   * @return ActionForward
   */
  protected Map getKeyMethodMap() {
    Map map = new HashMap();

    map.put("button.sure", "sure");
    map.put("button.scan", "scan");

    return map;
  }

  public ActionForward sure(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response) {
    String contranctid = (String) request.getSession().getAttribute("contid");
    LoanapplyNewAF loanapplyNewAF = (LoanapplyNewAF) form;
    IBaseinfochgBS baseinfochgBS = (IBaseinfochgBS) BSUtils.getBusinessService(
        "baseinfochgBS", this, mapping.getModuleConfig());
    SecurityInfo securityInfo = (SecurityInfo) request.getSession()
        .getAttribute("SecurityInfo");
    try {
      baseinfochgBS.updateBorrowerInfo(contranctid, loanapplyNewAF,
          securityInfo);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return mapping.findForward("baseinfochgtbshowAC");
  }

  public ActionForward scan(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    ActionMessages messages = null;
    PictureUpload pu = new PictureUpload();
    String path;
    try {
      SecurityInfo securityInfo = (SecurityInfo) request.getSession()
          .getAttribute("SecurityInfo");
      String serverPath = BusiConst.UPLOAD_SERVER_PATH;
      path = pu.upload(securityInfo.getUserInfo().getUserIp(), "picture",
          serverPath);
      pu.delete(securityInfo.getUserInfo().getUserIp(), "picture");
      ILoanapplyBS loanapplyBS = (ILoanapplyBS) BSUtils.getBusinessService(
          "loanapplyBS", this, mapping.getModuleConfig());
      LoanapplyNewAF loanapplyaf = (LoanapplyNewAF) form;
      loanapplyBS.updateBorrowerInfo(loanapplyaf.getBorrower().getContractId(),
          path);
      messages = new ActionMessages();
      messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("ɨ�����",
          false));
      saveErrors(request, messages);
    } catch (BusinessException e) {
      messages = new ActionMessages();
      messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(e
          .getLocalizedMessage().toString(), false));
      saveErrors(request, messages);
    }

    return mapping.findForward("baseinfochgtbshowAC");
  }
}