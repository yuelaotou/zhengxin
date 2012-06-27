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
import org.xpup.hafmis.sysloan.loanapply.endorsecontract.action.EndorsecontractTbShowAC;
import org.xpup.hafmis.sysloan.loanapply.endorsecontract.bsinterface.IEndorsecontractBS;
import org.xpup.hafmis.sysloan.loanapply.endorsecontract.form.EndorsecontractTcAF;

public class AssurepledgechgTcDownmaintainAC extends LookupDispatchAction {
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
    String key = AssurepledgechgTcShowAC.PAGINATION_KEY;
    Pagination pagination = (Pagination) request.getSession().getAttribute(key);
    IEndorsecontractBS endorsecontractBS = (IEndorsecontractBS) BSUtils
        .getBusinessService("endorsecontractBS", this, mapping
            .getModuleConfig());
    IdAF idAF = (IdAF) form;
    EndorsecontractTcAF endorsecontractTcAF = new EndorsecontractTcAF();
    String pl122Id = idAF.getId().toString();
    try {
      endorsecontractTcAF = endorsecontractBS.updateImpawnContract(pl122Id,
          pagination, securityInfo, request);
    } catch (Exception e) {
      e.printStackTrace();
    }
    List list = (List) pagination.getQueryCriterions().get("list");
    // 证件类型下拉框
    Map map = BusiTools.listBusiProperty(BusiConst.DOCUMENTSSTATE);
    endorsecontractTcAF.setMap(map);
    endorsecontractTcAF.setList(list);
    endorsecontractTcAF.setIsReadOnly("1");// 可以修改的字段：所有权证编号，所有权人电话，移动电话 其余只读
    request.getSession().setAttribute("theEndorsecontractTcAF",
        endorsecontractTcAF);
    request.getSession().setAttribute("pl122Id", pl122Id);

    return mapping.findForward("to_assurepledgechgTc");
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
    String key = EndorsecontractTbShowAC.PAGINATION_KEY;
    Pagination pagination = (Pagination) request.getSession().getAttribute(key);
    IAssurepledgechgBS assurepledgechgBS = (IAssurepledgechgBS) BSUtils
        .getBusinessService("assurepledgechgBS", this, mapping
            .getModuleConfig());
    IdAF idAF = (IdAF) form;

    String pl122Id = idAF.getId().toString();
    try {
      assurepledgechgBS.deleteImpawnContract(pl122Id, pagination, securityInfo,
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
    return mapping.findForward("to_assurepledgechgTcShowAC");
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
      String pl122Id = idAF.getId().toString();
      endorsecontractBS.updateScanTc_Yqf(pl122Id, path);
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
    return mapping.findForward("to_assurepledgechgTcShowAC");
  }
}
