package org.xpup.hafmis.sysloan.loanapply.endorsecontract.action;

import java.util.HashMap;
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
import org.xpup.hafmis.common.util.BusiConst;
import org.xpup.hafmis.common.util.BusiTools;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.sysloan.loanapply.endorsecontract.bsinterface.IEndorsecontractBS;
import org.xpup.hafmis.sysloan.loanapply.endorsecontract.form.EndorsecontractTbAF;

/**
 * 
 * @author yuqf
 *
 */
public class EndorsecontractTbTopMaintainAC extends LookupDispatchAction{

  protected Map getKeyMethodMap() {
    // TODO Auto-generated method stub
    Map map = new HashMap();
    map.put("button.add", "add");
    map.put("button.edit", "edit");
    return map;
  }
/**
 * 添加按钮
 * @param mapping
 * @param form
 * @param request
 * @param response
 * @return
 * @throws Exception
 */
  public ActionForward add(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    request.getSession().setAttribute("pl121Id", null);
    EndorsecontractTbAF endorsecontractTbAF = (EndorsecontractTbAF)form;
    EndorsecontractTbAF esAF = new EndorsecontractTbAF();
    String key = EndorsecontractTbShowAC.PAGINATION_KEY;
    Pagination pagination = (Pagination) request.getSession().getAttribute(
        key);
    List list = (List)pagination.getQueryCriterions().get("list");
    String contractId = endorsecontractTbAF.getContractId();
    String debitter = endorsecontractTbAF.getDebitter();
    String pledgePerson = endorsecontractTbAF.getPledgePerson();
    String office = endorsecontractTbAF.getOffice();
    String assistantOrgName = endorsecontractTbAF.getAssistantOrgName();
    esAF.setAssistantOrgName(assistantOrgName);
    esAF.setOffice(office);
    esAF.setPledgePerson(debitter);
    esAF.setContractId(contractId);
    esAF.setDebitter(debitter);
    esAF.setList(list);
    //
    esAF.setSex(endorsecontractTbAF.getSex());
    esAF.setAge(endorsecontractTbAF.getAge());
    esAF.setSfz(endorsecontractTbAF.getSfz());
    esAF.setOrgName(endorsecontractTbAF.getOrgName());
    esAF.setOrgAddress(endorsecontractTbAF.getOrgAddress());
    esAF.setOrgTel(endorsecontractTbAF.getOrgTel());
    esAF.setCardKind(endorsecontractTbAF.getCardKind());
    esAF.setCarNum(endorsecontractTbAF.getCarNum());
    esAF.setDyrgddh(endorsecontractTbAF.getDyrgddh());
    esAF.setDyryddh(endorsecontractTbAF.getDyryddh());
    esAF.setTel(endorsecontractTbAF.getTel());
    esAF.setMobile(endorsecontractTbAF.getMobile());
    esAF.setPledgeAddr(endorsecontractTbAF.getPledgeAddr());
    esAF.setArea(endorsecontractTbAF.getArea());
    esAF.setPledgeValue(endorsecontractTbAF.getPledgeValue());
    esAF.setIsReadOnly("0");
    esAF.setPaperPersonName(debitter);
    // 证件类型下拉框
    Map map = BusiTools.listBusiProperty(BusiConst.DOCUMENTSSTATE);
    esAF.setMap(map);
    request.getSession().setAttribute("theEndorsecontractTbAF", esAF);
    return mapping.findForward("to_endorsecontractTb");
  }
  
  /**
   * 确定按钮
   * @param mapping
   * @param form
   * @param request
   * @param response
   * @return
   * @throws Exception
   */
  public ActionForward edit(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    ActionMessages messages = null;
    String loanassistantorgId = request.getParameter("loanassistantorgId");
    Integer pl121Id = (Integer)request.getSession().getAttribute("pl121Id");
    EndorsecontractTbAF endorsecontractTbAF = (EndorsecontractTbAF)form;
    SecurityInfo securityInfo = (SecurityInfo) request.getSession()
    .getAttribute("SecurityInfo");
    IEndorsecontractBS endorsecontractBS = (IEndorsecontractBS) BSUtils
    .getBusinessService("endorsecontractBS", this, mapping
        .getModuleConfig());
    try{
    endorsecontractBS.sure(pl121Id,loanassistantorgId, securityInfo, endorsecontractTbAF);
    messages = new ActionMessages();
    messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("操作成功！",
        false));
    saveErrors(request, messages);
    }catch (BusinessException bex) {
      messages = new ActionMessages();
      messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(bex
          .getMessage(), false));
      saveErrors(request, messages);
    }
    
    return mapping.findForward("to_endorsecontractTbShowAC");
  }
}
