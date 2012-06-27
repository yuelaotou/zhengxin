package org.xpup.hafmis.sysloan.loancallback.loancallback.action; 


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
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.sysloan.common.loanconditionsset.ILoanConditionsParamSetBS;
import org.xpup.hafmis.sysloan.loancallback.loancallback.bsinterface.ILoancallbackBS;
import org.xpup.hafmis.sysloan.loancallback.loancallback.form.LoancallbackTaAF;

public class LoancallbackTaMaintainAC extends LookupDispatchAction {
  protected Map getKeyMethodMap() {
    Map map = new HashMap();
    map.put("button.import", "imports");
    map.put("button.delete", "delete");
    map.put("button.sure", "sure");
    return map;
  }
  public ActionForward imports(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

    return mapping.findForward("loancallback_import");
  }
  public ActionForward delete(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
    ActionMessages messages =null;
    SecurityInfo securityInfo=(SecurityInfo)request.getSession().getAttribute("SecurityInfo");
    Pagination pagination=(Pagination)request.getSession().getAttribute(LoancallbackTaShowAC.PAGINATION_KEY);
    try{
      messages=new ActionMessages();
      ILoancallbackBS loancallbackBS = (ILoancallbackBS) BSUtils
      .getBusinessService("loancallbackBS", this, mapping.getModuleConfig());
      String headId = (String)pagination.getQueryCriterions().get("headId");
      loancallbackBS.deleteCallbackInfoByBank(headId, securityInfo);
    }catch(BusinessException b){
      messages=new ActionMessages();
      messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(b.getMessage(),
          false));
      saveErrors(request, messages);
    }
    pagination.getQueryCriterions().put("loanKouAcc",null);
    pagination.getQueryCriterions().put("headId",null);
    return mapping.findForward("loancallbackTaShowAC");
  }
  public ActionForward sure(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
    ActionMessages messages =null;
    LoancallbackTaAF af = (LoancallbackTaAF)form;
    String matter="";
    SecurityInfo securityInfo=(SecurityInfo)request.getSession().getAttribute("SecurityInfo");//进入页面是要带权限的，从权限中取得是以中心为主还是以银行为主。以此来控制按钮灰显，要在action中设置状态。
    try{
      ILoanConditionsParamSetBS loanConditionsParamSetBS = (ILoanConditionsParamSetBS) BSUtils.getBusinessService(
          "loanConditionsParamSetBS", this, mapping.getModuleConfig());
//      if(!af.getParam().equals("special")){
//        loanConditionsParamSetBS.canLoancallback_ws(af, securityInfo);
//      }
      Pagination pagination=(Pagination)request.getSession().getAttribute(LoancallbackTaShowAC.PAGINATION_KEY);
      List list = (List)pagination.getQueryCriterions().get("shouldBackList");
      List yearMonthList = (List)pagination.getQueryCriterions().get("YearList");
      af.setShouldBackList(list);
      af.setMonthYearList(yearMonthList);
      
      messages=new ActionMessages();;
      String report = request.getParameter("report");  
      String headId= (String)pagination.getQueryCriterions().get("headId");
      //贷款还款类型1.中心为主2.银行为主
      int temp_plLoanReturnType = securityInfo.getPlLoanReturnType();
      //根据权限中的还款类型判断以哪为主
      String plLoanReturnType = "";
      ILoancallbackBS loancallbackBS = (ILoancallbackBS) BSUtils
      .getBusinessService("loancallbackBS", this, mapping.getModuleConfig());
      if(af.getBizType().equals("3")){
        matter=loancallbackBS.partAheadInfo_wsh(af, securityInfo);
      }
      if(temp_plLoanReturnType == BusiConst.PLLOANRETURNTYPE_CENTER){
        plLoanReturnType = "1";//中心为主
        String t="";
        if(matter!=null&&!"".equals(matter)){
          t="0";
        }else{
          t="1";
          headId = loancallbackBS.addCallbackInfo(af, securityInfo,t);
        }
        pagination.getQueryCriterions().put("contractId",null);
      }else if(temp_plLoanReturnType == BusiConst.PLLOANRETURNTYPE_BANK){
        plLoanReturnType = "2";//银行为主
        //更新流水表
        headId = loancallbackBS.addCallbackInfoByLoanBankId(pagination, af.getBorrowerInfoDTO().getContractId(), securityInfo);
        pagination.getQueryCriterions().put("headId",null);
      }
     
      if(matter!=null&&!"".equals(matter)){
        request.setAttribute("matter", matter);
      }
      if(report.equals("print")){  
        LoancallbackTaAF loancallbackTaAF = new LoancallbackTaAF();
        loancallbackTaAF = loancallbackBS.findPrintCallbackInfo(headId);
        request.setAttribute("makePerson", securityInfo.getRealName());
        request.setAttribute("bizDate", securityInfo.getUserInfo().getPlbizDate());
        request.setAttribute("LoancallbackTaAF", loancallbackTaAF);
        request.setAttribute("URL", "loancallbackTaShowAC.do");
        pagination.getQueryCriterions().put("contractId",null);
        return mapping.findForward("loancallback_cell");
      }
      
    }catch(BusinessException b){
      messages=new ActionMessages();
      messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(b.getMessage(),
          false));
      saveErrors(request, messages);
    }
    af.reset(mapping,request);
    return mapping.findForward("loancallbackTaShowAC");
  }
}