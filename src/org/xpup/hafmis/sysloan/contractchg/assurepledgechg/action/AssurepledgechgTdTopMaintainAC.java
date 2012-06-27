package org.xpup.hafmis.sysloan.contractchg.assurepledgechg.action;

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
import org.xpup.hafmis.sysloan.contractchg.assurepledgechg.bsinterface.IAssurepledgechgBS;
import org.xpup.hafmis.sysloan.loanapply.endorsecontract.action.EndorsecontractTdShowAC;
import org.xpup.hafmis.sysloan.loanapply.endorsecontract.form.EndorsecontractTbAF;
import org.xpup.hafmis.sysloan.loanapply.endorsecontract.form.EndorsecontractTdAF;

public class AssurepledgechgTdTopMaintainAC extends LookupDispatchAction{
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
    request.getSession().setAttribute("pl123Id", null);
    EndorsecontractTdAF endorsecontractTdAF = (EndorsecontractTdAF)form;
    EndorsecontractTdAF esAF = new EndorsecontractTdAF();
    String key = AssurepledgechgTdShowAC.PAGINATION_KEY;
    Pagination pagination = (Pagination) request.getSession().getAttribute(
        key);
    List list = (List)pagination.getQueryCriterions().get("list");
    String contractId = endorsecontractTdAF.getContractId();
    String debitter = endorsecontractTdAF.getDebitter();
    esAF.setContractId(contractId);
    esAF.setDebitter(debitter);
    esAF.setList(list);
    esAF.setIsReadOnly("0");
    // 证件类型下拉框,性别下拉框
    Map cardMap = BusiTools.listBusiProperty(BusiConst.DOCUMENTSSTATE);
    Map sexMap = BusiTools.listBusiProperty(BusiConst.SEX);
    esAF.setCardMap(cardMap);
    esAF.setSexMap(sexMap);
    request.getSession().setAttribute("theEndorsecontractTdAF", esAF);
    return mapping.findForward("to_assurepledgechgTd");
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
      String pl123Id = (String)request.getSession().getAttribute("pl123Id");
//      String id = null;
//      if(pl123Id != null){
//        id = pl123Id.toString();
//      }
      EndorsecontractTdAF endorsecontractTdAF = (EndorsecontractTdAF)form;
      SecurityInfo securityInfo = (SecurityInfo) request.getSession()
      .getAttribute("SecurityInfo");
      IAssurepledgechgBS assurepledgechgBS = (IAssurepledgechgBS) BSUtils
      .getBusinessService("assurepledgechgBS", this, mapping
          .getModuleConfig());
      try{
        assurepledgechgBS.addAssurer(pl123Id,securityInfo, endorsecontractTdAF,request);
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
      
      return mapping.findForward("to_assurepledgechgTdShowAC");
    }
}
