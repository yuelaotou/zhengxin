package org.xpup.hafmis.sysloan.loanapply.loancheck.action;

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
import org.xpup.hafmis.common.form.IdAF;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.sysloan.loanapply.loancheck.bsinterface.ILoanCheckBS;

/**
 * @author 王野 2007-09-22
 */
public class LoanCheckMaintainAC extends LookupDispatchAction {

  protected Map getKeyMethodMap() {
    Map map = new HashMap();
    map.put("button.auditing.pass", "checkPass");
    map.put("button.auditing.quash", "checkQuash");
    map.put("button.print", "print");
    return map;
  }

  /**
   * Description 审核贷款
   * 
   * @author wangy 2007-09-22
   * @param 审核通过
   * @param mapping
   * @param form
   * @param request
   * @param response
   * @return ActionForward
   * @throws Exception
   */
  public ActionForward checkPass(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response) {
    ActionMessages messages = null;
    IdAF idAF = (IdAF) form;
    try {
      String[] rowArray = idAF.getRowArray();
      SecurityInfo securityInfo = (SecurityInfo) request.getSession()
          .getAttribute("SecurityInfo");
      ILoanCheckBS loanCheckBS = (ILoanCheckBS) BSUtils.getBusinessService(
          "loanCheckBS", this, mapping.getModuleConfig());
     
      loanCheckBS.updateContractSTCheckPassrowArray(rowArray, securityInfo);
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
   * Description 审核贷款
   * 
   * @author wangy 2007-09-22
   * @param 撤销审核
   * @param mapping
   * @param form
   * @param request
   * @param response
   * @return ActionForward
   * @throws Exception
   */
  public ActionForward checkQuash(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response) {
    ActionMessages messages = null;
    IdAF idAF = (IdAF) form;
  
    try {
      String[] rowArray = idAF.getRowArray();
      SecurityInfo securityInfo = (SecurityInfo) request.getSession()
          .getAttribute("SecurityInfo");
      ILoanCheckBS loanCheckBS = (ILoanCheckBS) BSUtils.getBusinessService(
          "loanCheckBS", this, mapping.getModuleConfig());
      loanCheckBS.updateContractSTCheckQuashrowArray(rowArray, securityInfo);
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
   * wuht
   *  1、增加打印：《2008年个人住房，贷款明细表》1、部门显示办事处2、日期显示打印日期：
   * 打印日期为系统日期3、信贷科科长修改为“信贷科科长或办事处主任”
   * 4、当年累计该办事处下，查询年度内的累计贷款发放数 本页小计，累计
   */
  public ActionForward print(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response) {
    SecurityInfo securityInfo = (SecurityInfo) request.getSession()
      .getAttribute("SecurityInfo");
    String bizDate = securityInfo.getUserInfo().getPlbizDate();
    request.setAttribute("bizDate", bizDate);
    request.setAttribute("officeName", securityInfo.getOfficeDto().getOfficeName());
    return mapping.findForward("to_loancheck_show_cell");
  }
}
