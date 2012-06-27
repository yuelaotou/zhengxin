package org.xpup.hafmis.sysloan.loanapply.endorsecontract.action;

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
import org.xpup.hafmis.common.form.IdAF;
import org.xpup.hafmis.common.util.BusiConst;
import org.xpup.hafmis.common.util.BusiTools;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.sysloan.dataready.loanpara.bsinterface.ILoanDocNumDesignBS;
import org.xpup.hafmis.sysloan.loanapply.endorsecontract.bsinterface.IEndorsecontractBS;
import org.xpup.hafmis.sysloan.loanapply.endorsecontract.form.EndorsecontractTaAF;
import org.xpup.hafmis.sysloan.loanapply.endorsecontract.form.EndorsecontractTbAF;
import org.xpup.hafmis.sysloan.loanapply.endorsecontract.form.EndorsecontractTcAF;
import org.xpup.hafmis.sysloan.loanapply.endorsecontract.form.EndorsecontractTdAF;

public class EndorsecontractTeMaintainAC extends LookupDispatchAction {

  protected Map getKeyMethodMap() {
    // TODO Auto-generated method stub
    Map map = new HashMap();
    map.put("button.update", "update");
    map.put("button.delete", "delete");
    map.put("button.submit", "surecontract");
    map.put("button.delsubmit", "delcontract");
    map.put("button.printborrowcontract", "printborrowcontract");
    map.put("button.printpledgecontract", "printpledgecontract");
    map.put("button.printimpawncontract", "printimpawncontract");
    map.put("button.printassurerinfor", "printassurerinfor");
    map.put("button.entrustassurance.print", "printassurance");
    return map;
  }

  /**
   * 修改
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

    IdAF idAF = (IdAF) form;
    String contractId = idAF.getId().toString();
    request.getSession().setAttribute("contractId", contractId);
    request.setAttribute("comeFromType", "yes");
    request.getSession().setAttribute("pl121Id", null);
    return mapping.findForward("to_endorsecontractTaShowAC");
  }

  /**
   * 删除按钮
   * 
   * @param mapping
   * @param form
   * @param request
   * @param response
   * @return
   * @throws Exception
   */
  public ActionForward delete(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {

    ActionMessages messages = null;
    SecurityInfo securityInfo = (SecurityInfo) request.getSession()
        .getAttribute("SecurityInfo");
    String key = EndorsecontractTeShowAC.PAGINATION_KEY;
    Pagination pagination = (Pagination) request.getSession().getAttribute(key);
    IEndorsecontractBS endorsecontractBS = (IEndorsecontractBS) BSUtils
        .getBusinessService("endorsecontractBS", this, mapping
            .getModuleConfig());
    IdAF idAF = (IdAF) form;

    String contractId = idAF.getId().toString();
    try {
      endorsecontractBS.deleteContract(contractId, pagination, securityInfo,
          request);
      request.getSession().setAttribute("contractId", null);
      // messages = new ActionMessages();
      // messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("操作成功！",
      // false));
      // saveErrors(request, messages);
    } catch (BusinessException bex) {
      messages = new ActionMessages();
      messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(bex
          .getMessage(), false));
      saveErrors(request, messages);
    }
    return mapping.findForward("to_endorsecontractTeShowAC");
  }

  /**
   * 签订合同
   * 
   * @param mapping
   * @param form
   * @param request
   * @param response
   * @return
   * @throws Exception
   */
  public ActionForward surecontract(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    ActionMessages messages = null;
    SecurityInfo securityInfo = (SecurityInfo) request.getSession()
        .getAttribute("SecurityInfo");
    String key = EndorsecontractTeShowAC.PAGINATION_KEY;
    Pagination pagination = (Pagination) request.getSession().getAttribute(key);
    IEndorsecontractBS endorsecontractBS = (IEndorsecontractBS) BSUtils
        .getBusinessService("endorsecontractBS", this, mapping
            .getModuleConfig());
    IdAF idAF = (IdAF) form;

    String contractId = idAF.getId().toString();
    try {
      endorsecontractBS.sureContract(contractId, pagination, securityInfo,
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
    return mapping.findForward("to_endorsecontractTeShowAC");
  }

  /**
   * 撤销签订合同
   * 
   * @param mapping
   * @param form
   * @param request
   * @param response
   * @return
   * @throws Exception
   */
  public ActionForward delcontract(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    ActionMessages messages = null;
    SecurityInfo securityInfo = (SecurityInfo) request.getSession()
        .getAttribute("SecurityInfo");
    String key = EndorsecontractTeShowAC.PAGINATION_KEY;
    Pagination pagination = (Pagination) request.getSession().getAttribute(key);
    IEndorsecontractBS endorsecontractBS = (IEndorsecontractBS) BSUtils
        .getBusinessService("endorsecontractBS", this, mapping
            .getModuleConfig());
    IdAF idAF = (IdAF) form;

    String contractId = idAF.getId().toString();
    try {
      endorsecontractBS.delContract(contractId, pagination, securityInfo,
          request);
      messages = new ActionMessages();
      messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("操作成功！",
          false));
      saveErrors(request, messages);
    } catch (BusinessException bex) {
      bex.printStackTrace();
      messages = new ActionMessages();
      messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(bex
          .getMessage(), false));
      saveErrors(request, messages);
    }
    return mapping.findForward("to_endorsecontractTeShowAC");
  }

  /**
   * 打印借款合同信息
   * 
   * @param mapping
   * @param form
   * @param request
   * @param response
   * @return
   * @throws Exception
   */
  public ActionForward printborrowcontract(ActionMapping mapping,
      ActionForm form, HttpServletRequest request, HttpServletResponse response)
      throws Exception {

    SecurityInfo securityInfo = (SecurityInfo) request.getSession()
        .getAttribute("SecurityInfo");
    String key = EndorsecontractTeShowAC.PAGINATION_KEY;
    Pagination pagination = (Pagination) request.getSession().getAttribute(key);
    IEndorsecontractBS endorsecontractBS = (IEndorsecontractBS) BSUtils
        .getBusinessService("endorsecontractBS", this, mapping
            .getModuleConfig());
    IdAF idAF = (IdAF) form;
    EndorsecontractTaAF endorsecontractTaAF = new EndorsecontractTaAF();
    String contractId = idAF.getId().toString();
    ILoanDocNumDesignBS loanDocNumDesignBS = (ILoanDocNumDesignBS) BSUtils
        .getBusinessService("sysloanloanDocNumDesignBS", this, mapping
            .getModuleConfig());
    String userName = "";
    try {
      String name = loanDocNumDesignBS.getNamePara();

      if (name.equals("1")) {
        userName = securityInfo.getUserName();
      } else {
        userName = securityInfo.getRealName();
      }

    } catch (Exception e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }

    String plbizDate = securityInfo.getUserInfo().getPlbizDate();
    request.setAttribute("userName", userName);
    request.setAttribute("plbizDate", plbizDate);
    endorsecontractTaAF = endorsecontractBS.queryContractInfo_(contractId,
        pagination, securityInfo, request);
    request.getSession().setAttribute("printendorsecontractTaAF",
        endorsecontractTaAF);
    return mapping.findForward("to_endorsecontractTa_cell");
  }

  /**
   * 打印抵押合同信息
   * 
   * @param mapping
   * @param form
   * @param request
   * @param response
   * @return
   * @throws Exception
   */
  public ActionForward printpledgecontract(ActionMapping mapping,
      ActionForm form, HttpServletRequest request, HttpServletResponse response)
      throws Exception {

    SecurityInfo securityInfo = (SecurityInfo) request.getSession()
        .getAttribute("SecurityInfo");
    String key = EndorsecontractTeShowAC.PAGINATION_KEY;
    Pagination pagination = (Pagination) request.getSession().getAttribute(key);
    IEndorsecontractBS endorsecontractBS = (IEndorsecontractBS) BSUtils
        .getBusinessService("endorsecontractBS", this, mapping
            .getModuleConfig());
    IdAF idAF = (IdAF) form;
    EndorsecontractTbAF endorsecontractTbAF = new EndorsecontractTbAF();
    String contractId = idAF.getId().toString();

    ILoanDocNumDesignBS loanDocNumDesignBS = (ILoanDocNumDesignBS) BSUtils
        .getBusinessService("sysloanloanDocNumDesignBS", this, mapping
            .getModuleConfig());
    String userName = "";
    try {
      String name = loanDocNumDesignBS.getNamePara();

      if (name.equals("1")) {
        userName = securityInfo.getUserName();
      } else {
        userName = securityInfo.getRealName();
      }

    } catch (Exception e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }

    String plbizDate = securityInfo.getUserInfo().getPlbizDate();
    request.setAttribute("userName", userName);
    request.setAttribute("plbizDate", plbizDate);
    endorsecontractTbAF = endorsecontractBS.queryPledgeContractList_(
        contractId, pagination, securityInfo, request);
    endorsecontractTbAF.setContractId(contractId);
    if (endorsecontractTbAF.getBorrower().getSex() != null
        && !"".equals(endorsecontractTbAF.getBorrower().getSex())) {
      endorsecontractTbAF
          .setSex(BusiTools.getBusiValue(Integer.parseInt(endorsecontractTbAF
              .getBorrower().getSex()), BusiConst.SEX));
    }
    request.getSession().setAttribute("printendorsecontractTbAF",
        endorsecontractTbAF);
    return mapping.findForward("to_endorsecontractTb_cell");
  }

  /**
   * 打印质押合同信息
   * 
   * @param mapping
   * @param form
   * @param request
   * @param response
   * @return
   * @throws Exception
   */
  public ActionForward printimpawncontract(ActionMapping mapping,
      ActionForm form, HttpServletRequest request, HttpServletResponse response)
      throws Exception {

    SecurityInfo securityInfo = (SecurityInfo) request.getSession()
        .getAttribute("SecurityInfo");
    String key = EndorsecontractTeShowAC.PAGINATION_KEY;
    Pagination pagination = (Pagination) request.getSession().getAttribute(key);
    IEndorsecontractBS endorsecontractBS = (IEndorsecontractBS) BSUtils
        .getBusinessService("endorsecontractBS", this, mapping
            .getModuleConfig());
    IdAF idAF = (IdAF) form;
    EndorsecontractTcAF endorsecontractTcAF = new EndorsecontractTcAF();
    String contractId = idAF.getId().toString();

    ILoanDocNumDesignBS loanDocNumDesignBS = (ILoanDocNumDesignBS) BSUtils
        .getBusinessService("sysloanloanDocNumDesignBS", this, mapping
            .getModuleConfig());
    String userName = "";
    try {
      String name = loanDocNumDesignBS.getNamePara();

      if (name.equals("1")) {
        userName = securityInfo.getUserName();
      } else {
        userName = securityInfo.getRealName();
      }

    } catch (Exception e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    String plbizDate = securityInfo.getUserInfo().getPlbizDate();
    request.setAttribute("userName", userName);
    request.setAttribute("plbizDate", plbizDate);
    endorsecontractTcAF = endorsecontractBS.queryImpawnContractList_(
        contractId, pagination, securityInfo, request);
    endorsecontractTcAF.setContractId(contractId);
    request.getSession().setAttribute("printendorsecontractTcAF",
        endorsecontractTcAF);
    return mapping.findForward("to_endorsecontractTc_cell");
  }

  /**
   * 打印保证人信息
   * 
   * @param mapping
   * @param form
   * @param request
   * @param response
   * @return
   * @throws Exception
   */
  public ActionForward printassurerinfor(ActionMapping mapping,
      ActionForm form, HttpServletRequest request, HttpServletResponse response)
      throws Exception {

    SecurityInfo securityInfo = (SecurityInfo) request.getSession()
        .getAttribute("SecurityInfo");
    String key = EndorsecontractTeShowAC.PAGINATION_KEY;
    Pagination pagination = (Pagination) request.getSession().getAttribute(key);
    IEndorsecontractBS endorsecontractBS = (IEndorsecontractBS) BSUtils
        .getBusinessService("endorsecontractBS", this, mapping
            .getModuleConfig());
    IdAF idAF = (IdAF) form;
    EndorsecontractTdAF endorsecontractTdAF = new EndorsecontractTdAF();
    String contractId = idAF.getId().toString();

    ILoanDocNumDesignBS loanDocNumDesignBS = (ILoanDocNumDesignBS) BSUtils
        .getBusinessService("sysloanloanDocNumDesignBS", this, mapping
            .getModuleConfig());
    String userName = "";
    try {
      String name = loanDocNumDesignBS.getNamePara();

      if (name.equals("1")) {
        userName = securityInfo.getUserName();
      } else {
        userName = securityInfo.getRealName();
      }

    } catch (Exception e) {
      e.printStackTrace();
    }
    String plbizDate = securityInfo.getUserInfo().getPlbizDate();
    request.setAttribute("userName", userName);
    request.setAttribute("plbizDate", plbizDate);
    endorsecontractTdAF = endorsecontractBS.queryAssurerList_(contractId,
        pagination, securityInfo, request);
    endorsecontractTdAF.setContractId(contractId);
    request.getSession().setAttribute("printendorsecontractTdAF",
        endorsecontractTdAF);
    return mapping.findForward("to_endorsecontractTd_cell");
  }


  /**
   * 打印委托扣款保证
   * 
   * @param mapping
   * @param form
   * @param request
   * @param response
   * @return
   * @throws Exception
   */
  public ActionForward printassurance(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {

    SecurityInfo securityInfo = (SecurityInfo) request.getSession()
        .getAttribute("SecurityInfo");
    String key = EndorsecontractTeShowAC.PAGINATION_KEY;
    Pagination pagination = (Pagination) request.getSession().getAttribute(key);
    IEndorsecontractBS endorsecontractBS = (IEndorsecontractBS) BSUtils
        .getBusinessService("endorsecontractBS", this, mapping
            .getModuleConfig());
    IdAF idAF = (IdAF) form;
    EndorsecontractTbAF endorsecontractTbAF = new EndorsecontractTbAF();
    String contractId = idAF.getId().toString();

    ILoanDocNumDesignBS loanDocNumDesignBS = (ILoanDocNumDesignBS) BSUtils
        .getBusinessService("sysloanloanDocNumDesignBS", this, mapping
            .getModuleConfig());
    String userName = "";
    try {
      String name = loanDocNumDesignBS.getNamePara();
      if (name.equals("1")) {
        userName = securityInfo.getUserName();
      } else {
        userName = securityInfo.getRealName();
      }

    } catch (Exception e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    String plbizDate = securityInfo.getUserInfo().getPlbizDate();
    request.setAttribute("userName", userName);
    request.setAttribute("plbizDate", plbizDate);
    endorsecontractTbAF = endorsecontractBS.queryPledgeContractList_(
        contractId, pagination, securityInfo, request);
    endorsecontractTbAF.setContractId(contractId);
    request.getSession().setAttribute("printendorsecontractTbAF",
        endorsecontractTbAF);
    return mapping.findForward("to_endorsecontractTe_cell");
  }
}
