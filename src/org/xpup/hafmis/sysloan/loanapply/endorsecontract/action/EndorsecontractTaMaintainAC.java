package org.xpup.hafmis.sysloan.loanapply.endorsecontract.action;

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
import org.xpup.hafmis.sysloan.loanapply.endorsecontract.bsinterface.IEndorsecontractBS;
import org.xpup.hafmis.sysloan.loanapply.endorsecontract.form.EndorsecontractTaAF;

/**
 * @author yuqf
 */
public class EndorsecontractTaMaintainAC extends LookupDispatchAction {

  protected Map getKeyMethodMap() {
    // TODO Auto-generated method stub
    Map map = new HashMap();
    map.put("button.sure", "sure");
    map.put("button.scan.houseinfo", "scan");
    map.put("button.disable.contract", "disableContract");
    return map;
  }

  public ActionForward sure(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    // TODO Auto-generated method stub

    ActionMessages messages = null;
    EndorsecontractTaAF endorsecontractTaAF = (EndorsecontractTaAF) form;
    SecurityInfo securityInfo = (SecurityInfo) request.getSession()
        .getAttribute("SecurityInfo");
    String loanassistantorgId = request.getParameter("loanassistantorgId");
    IEndorsecontractBS endorsecontractBS = (IEndorsecontractBS) BSUtils
        .getBusinessService("endorsecontractBS", this, mapping
            .getModuleConfig());
    try {
      endorsecontractBS.endorsecontractTaMaitainSure(loanassistantorgId,
          endorsecontractTaAF, securityInfo);
      request.getSession().setAttribute("insert", "insert");
      messages = new ActionMessages();
      messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("操作成功！",
          false));
      request.getSession().setAttribute("aftermaintain", "aftermaintain");
      saveErrors(request, messages);
    } catch (BusinessException bex) {
      request.setAttribute("error", "error");
      messages = new ActionMessages();
      messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(bex
          .getLocalizedMessage(), false));
      saveErrors(request, messages);
    }
    request.setAttribute("afterSure", "afterSure");
    return mapping.findForward("to_EndorsecontractTaShowAC");
  }

  public ActionForward scan(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    ActionMessages messages = null;
    PictureUpload pu = new PictureUpload();
    String path;
    try {
      EndorsecontractTaAF endorsecontractTaAF = (EndorsecontractTaAF) form;
      SecurityInfo securityInfo = (SecurityInfo) request.getSession()
          .getAttribute("SecurityInfo");
      String serverPath = BusiConst.UPLOAD_SERVER_PATH;
      path = pu.upload(securityInfo.getUserInfo().getUserIp(), "picture",
          serverPath,endorsecontractTaAF.getContractId());
      pu.delete(securityInfo.getUserInfo().getUserIp(), "picture");
      IEndorsecontractBS endorsecontractBS = (IEndorsecontractBS) BSUtils
          .getBusinessService("endorsecontractBS", this, mapping
              .getModuleConfig());
      endorsecontractBS.updateScanTa_Yqf(endorsecontractTaAF.getContractId(),
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
    return mapping.findForward("to_EndorsecontractTaShowAC");
  }

  public ActionForward disableContract(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    IEndorsecontractBS endorsecontractBS = (IEndorsecontractBS) BSUtils
        .getBusinessService("endorsecontractBS", this, mapping
            .getModuleConfig());
    EndorsecontractTaAF endorsecontractTaAF = (EndorsecontractTaAF) form;
    try {
      endorsecontractBS.updateContractSt(endorsecontractTaAF.getContractId());
      request.setAttribute("temp_an", "temp_an");// 如果按了不允许签订按钮，那就页面显示空。
    } catch (Exception e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }

    return mapping.findForward("to_EndorsecontractTaShowAC");
  }
}
