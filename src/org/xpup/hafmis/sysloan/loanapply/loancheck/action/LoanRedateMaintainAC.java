package org.xpup.hafmis.sysloan.loanapply.loancheck.action;

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
import org.xpup.hafmis.common.form.IdAF;
import org.xpup.hafmis.common.util.BusiConst;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.sysloan.dataready.loanpara.bsinterface.ILoanDocNumDesignBS;
import org.xpup.hafmis.sysloan.loanapply.loanapply.bsinterface.ILoanapplyBS;
import org.xpup.hafmis.sysloan.loanapply.loancheck.bsinterface.ILoanCheckBS;

/**
 * @author wshuang 2008-12-29
 */
public class LoanRedateMaintainAC extends LookupDispatchAction {

  protected Map getKeyMethodMap() {
    Map map = new HashMap();
    map.put("button.redate.sure", "redate");
    map.put("button.redate.sure.del", "delredate");
    map.put("button.print.list", "print");
    map.put("button.scan.others", "scan");
    return map;
  }

  /**
   * 回件确认
   * 
   * @param mapping
   * @param form
   * @param request
   * @param response
   * @return
   */
  public ActionForward redate(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response) {
    ActionMessages messages = null;
    IdAF idAF = (IdAF) form;
    try {
      String contractId = idAF.getId().toString();
      SecurityInfo securityInfo = (SecurityInfo) request.getSession()
          .getAttribute("SecurityInfo");
      ILoanCheckBS loanCheckBS = (ILoanCheckBS) BSUtils.getBusinessService(
          "loanCheckBS", this, mapping.getModuleConfig());
      request.setAttribute("url", "redate");
      loanCheckBS.updateContractStRedateSure(contractId, securityInfo);
    } catch (BusinessException bex) {
      messages = new ActionMessages();
      messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(bex
          .getMessage(), false));
      saveErrors(request, messages);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return mapping.findForward("loancheckShowAC");
  }
  /**
   * 撤销回件确认
   * 
   * @param mapping
   * @param form
   * @param request
   * @param response
   * @return
   */
  public ActionForward delredate(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response) {
    ActionMessages messages = null;
    IdAF idAF = (IdAF) form;
    try {
      String contractId = idAF.getId().toString();
      SecurityInfo securityInfo = (SecurityInfo) request.getSession()
      .getAttribute("SecurityInfo");
      ILoanCheckBS loanCheckBS = (ILoanCheckBS) BSUtils.getBusinessService(
          "loanCheckBS", this, mapping.getModuleConfig());
      request.setAttribute("url", "redate");
      loanCheckBS.updateContractStRedateSureDel(contractId, securityInfo);
    } catch (BusinessException bex) {
      messages = new ActionMessages();
      messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(bex
          .getMessage(), false));
      saveErrors(request, messages);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return mapping.findForward("loancheckShowAC");
  }

  /**
   * 列表打印
   * 
   * @param mapping
   * @param form
   * @param request
   * @param response
   * @return
   */
  public ActionForward print(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response) {
    try {
      SecurityInfo securityInfo = (SecurityInfo) request.getSession()
      .getAttribute("SecurityInfo");
      ILoanDocNumDesignBS loanDocNumDesignBS = (ILoanDocNumDesignBS) BSUtils
      .getBusinessService("sysloanloanDocNumDesignBS", this, mapping
          .getModuleConfig());
      String para = loanDocNumDesignBS.getNamePara();
      String realName = "";
      if (para.equals("1")) {
        realName = securityInfo.getUserName();
      } else {
        realName = securityInfo.getRealName();
      }
      request.setAttribute("username", realName);
      request.setAttribute("bizDate", securityInfo.getUserInfo().getPlbizDate());
    } catch (Exception e) {
      e.printStackTrace();
    }
    return mapping.findForward("to_loanredate_cell");
  }

  /**
   * 扫描
   * 
   * @param mapping
   * @param form
   * @param request
   * @param response
   * @return
   */
  public ActionForward scan(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response) {
    ActionMessages messages = null;
    PictureUpload pu = new PictureUpload();
    String path;
    try {
      IdAF idAF = (IdAF) form;
      String contractId = idAF.getId().toString();
      SecurityInfo securityInfo = (SecurityInfo) request.getSession()
          .getAttribute("SecurityInfo");
      String serverPath = BusiConst.UPLOAD_SERVER_PATH;
      path = pu.upload(securityInfo.getUserInfo().getUserIp(), "picture",
          serverPath,contractId);
      pu.delete(securityInfo.getUserInfo().getUserIp(), "picture");
      ILoanapplyBS loanapplyBS = (ILoanapplyBS) BSUtils.getBusinessService(
          "loanapplyBS", this, mapping.getModuleConfig());
      request.getSession().setAttribute("setIsNeedDel", "1");
      request.setAttribute("url", "redate");
      // 扫描购房信息
      loanapplyBS.updateHouseInfo(contractId, path);
      messages = new ActionMessages();
      messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("扫描完成", false));
      saveErrors(request, messages);
    } catch (BusinessException bex) {
      messages = new ActionMessages();
      messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(bex
          .getLocalizedMessage(), false));
      saveErrors(request, messages);
    } catch (Exception e){
      e.printStackTrace();
    }
    return mapping.findForward("loancheckShowAC");
  }
}
