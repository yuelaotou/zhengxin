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
import org.xpup.hafmis.sysloan.loanapply.endorsecontract.bsinterface.IEndorsecontractBS;
import org.xpup.hafmis.sysloan.loanapply.endorsecontract.form.EndorsecontractTbAF;
import org.xpup.hafmis.sysloan.loanapply.endorsecontract.form.EndorsecontractTcAF;

public class AssurepledgechgTcTopMaintainAC extends LookupDispatchAction{
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

//      request.getSession().setAttribute("contractId", null);
      EndorsecontractTcAF endorsecontractTcAF = (EndorsecontractTcAF)form;
      EndorsecontractTcAF esAF = new EndorsecontractTcAF();
      String key = AssurepledgechgTcShowAC.PAGINATION_KEY;
      Pagination pagination = (Pagination) request.getSession().getAttribute(
          key);
      List list = (List)pagination.getQueryCriterions().get("list");
      String contractId = endorsecontractTcAF.getContractId();
      String debitter = endorsecontractTcAF.getDebitter();
      String impawnPerson = endorsecontractTcAF.getImpawnPerson();//质押人姓名
      String office = endorsecontractTcAF.getOffice();//抵押权人（即××中心）
      String assistantOrgName = endorsecontractTcAF.getAssistantOrgName();//担保公司名称
      esAF.setContractId(contractId);
      esAF.setDebitter(debitter);
      esAF.setImpawnPerson(impawnPerson);
      esAF.setOffice(office);
      esAF.setAssistantOrgName(assistantOrgName);
      esAF.setList(list);
      esAF.setIsReadOnly("0");
      // 证件类型下拉框
      Map map = BusiTools.listBusiProperty(BusiConst.DOCUMENTSSTATE);
      esAF.setMap(map);
      request.getSession().setAttribute("theEndorsecontractTcAF", esAF);
      request.getSession().setAttribute("pl122Id", null);
      return mapping.findForward("to_assurepledgechgTc");
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
//      String pl122Id = "";
//      Integer temppl122Id = (Integer)request.getSession().getAttribute("pl122Id");
//      if(temppl122Id!=null){
//        pl122Id = temppl122Id.toString();
//      }
      String pl122Id =  (String)request.getSession().getAttribute("pl122Id");
      EndorsecontractTcAF endorsecontractTcAF = (EndorsecontractTcAF)form;
      SecurityInfo securityInfo = (SecurityInfo) request.getSession()
      .getAttribute("SecurityInfo");
      IAssurepledgechgBS assurepledgechgBS = (IAssurepledgechgBS) BSUtils
      .getBusinessService("assurepledgechgBS", this, mapping
          .getModuleConfig());
      try{
//        if(pl122Id!= null && !pl122Id.equals("")){
          assurepledgechgBS.addImpawnContract(pl122Id, securityInfo, endorsecontractTcAF, request);
        
        
      messages = new ActionMessages();
      messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("操作成功！",
          false));
      saveErrors(request, messages);
//        }
      }catch (BusinessException bex) {
        messages = new ActionMessages();
        messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(bex
            .getMessage(), false));
        saveErrors(request, messages);
      }
      
      return mapping.findForward("to_assurepledgechgTcShowAC");
    }
}
