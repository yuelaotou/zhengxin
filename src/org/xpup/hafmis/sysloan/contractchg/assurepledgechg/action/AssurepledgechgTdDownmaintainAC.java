package org.xpup.hafmis.sysloan.contractchg.assurepledgechg.action;

import java.util.HashMap;
import java.util.List;
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
import org.xpup.common.util.Pagination;
import org.xpup.hafmis.common.form.IdAF;
import org.xpup.hafmis.common.util.BusiConst;
import org.xpup.hafmis.common.util.BusiTools;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.sysloan.contractchg.assurepledgechg.bsinterface.IAssurepledgechgBS;
import org.xpup.hafmis.sysloan.loanapply.endorsecontract.bsinterface.IEndorsecontractBS;
import org.xpup.hafmis.sysloan.loanapply.endorsecontract.form.EndorsecontractTdAF;

public class AssurepledgechgTdDownmaintainAC extends LookupDispatchAction {
  protected Map getKeyMethodMap() {
    // TODO Auto-generated method stub
    Map map = new HashMap();
    map.put("button.update", "update");
    map.put("button.canceled", "canceled");
    map.put("button.scan", "scan");
    return map;
  }

  /**
   * 修改按钮
   * 
   * @param mapping
   * @param form
   * @param request
   * @param response
   * @return
   * @throws Exception
   */
  public ActionForward update(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {

    SecurityInfo securityInfo = (SecurityInfo) request.getSession()
        .getAttribute("SecurityInfo");
    String key = AssurepledgechgTdShowAC.PAGINATION_KEY;
    Pagination pagination = (Pagination) request.getSession().getAttribute(key);
    IEndorsecontractBS endorsecontractBS = (IEndorsecontractBS) BSUtils
        .getBusinessService("endorsecontractBS", this, mapping
            .getModuleConfig());
    IdAF idAF = (IdAF) form;
    EndorsecontractTdAF endorsecontractTdAF = new EndorsecontractTdAF();
    String pl123Id = idAF.getId().toString();
    try {
      endorsecontractTdAF = endorsecontractBS.updateAssurer(pl123Id,
          pagination, securityInfo, request);
    } catch (Exception e) {
      e.printStackTrace();
    }
    List list = (List) pagination.getQueryCriterions().get("list");
    // 证件类型下拉框,性别下拉框
    Map cardMap = BusiTools.listBusiProperty(BusiConst.DOCUMENTSSTATE);
    Map sexMap = BusiTools.listBusiProperty(BusiConst.SEX);
    endorsecontractTdAF.setCardMap(cardMap);
    endorsecontractTdAF.setSexMap(sexMap);
    endorsecontractTdAF.setList(list);
    endorsecontractTdAF.setIsReadOnly("1");
    request.getSession().setAttribute("theEndorsecontractTdAF",
        endorsecontractTdAF);
    request.getSession().setAttribute("pl123Id", pl123Id);

    return mapping.findForward("to_assurepledgechgTd");
  }

  /**
   * 作废按钮
   * 
   * @param mapping
   * @param form
   * @param request
   * @param response
   * @return
   * @throws Exception
   */
  public ActionForward canceled(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    ActionMessages messages = null;
    SecurityInfo securityInfo = (SecurityInfo) request.getSession()
        .getAttribute("SecurityInfo");
    String key = AssurepledgechgTdShowAC.PAGINATION_KEY;
    Pagination pagination = (Pagination) request.getSession().getAttribute(key);
    IAssurepledgechgBS assurepledgechgBS = (IAssurepledgechgBS) BSUtils
        .getBusinessService("assurepledgechgBS", this, mapping
            .getModuleConfig());
    IdAF idAF = (IdAF) form;

    String pl123Id = idAF.getId().toString();
    try {
      assurepledgechgBS.deleteAssurer(pl123Id, pagination, securityInfo,
          request);
      messages = new ActionMessages();
      messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("操作成功！",
          false));
      saveErrors(request, messages);
    } catch (BusinessException bex) {
      messages = new ActionMessages();
      messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(bex
          .getMessage(), false));
      saveErrors(request, messages);
    }
    return mapping.findForward("to_assurepledgechgTdShowAC");
  }

  /**
   * 扫描
   * 
   * @param mapping
   * @param form
   * @param request
   * @param response
   * @return
   * @throws Exception
   */
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
      IEndorsecontractBS endorsecontractBS = (IEndorsecontractBS) BSUtils
      .getBusinessService("endorsecontractBS", this, mapping
          .getModuleConfig());
      IdAF idAF = (IdAF) form;
      String pl123Id = idAF.getId().toString();
      endorsecontractBS.updateScanTd_Yqf(pl123Id, path);
      messages = new ActionMessages();
      messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("扫描完成",
          false));
      saveErrors(request, messages);
    } catch (BusinessException e) {
      messages = new ActionMessages();
      messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(e
          .getLocalizedMessage().toString(), false));
      saveErrors(request, messages);
    }
    return mapping.findForward("to_assurepledgechgTdShowAC");
  }
}
