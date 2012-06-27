package org.xpup.hafmis.sysfinance.treasurermng.cashdayclear.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.syscollection.chgbiz.chgslarybase.bsinterface.IChgslarybaseBS;
import org.xpup.hafmis.sysfinance.treasurermng.cashdayclear.bsinterface.ICashDayClearBS;
import org.xpup.hafmis.sysfinance.treasurermng.cashdayclear.dto.CashDayClearTaDTO;
import org.xpup.hafmis.sysfinance.treasurermng.cashdayclear.form.CashDayClearTaAF;


public class CashDayClearTcMaintainAC extends LookupDispatchAction {

  public ActionForward delete(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    ActionMessages messages = new ActionMessages();
    try{
      IdAF idAF = (IdAF)form;
      SecurityInfo securityInfo = (SecurityInfo) request.getSession()
      .getAttribute("SecurityInfo");
      ICashDayClearBS cashDayClearBS = (ICashDayClearBS) BSUtils
      .getBusinessService("cashDayClearBS", this, mapping.getModuleConfig());
      //用credenceType来判断是现金日记账还是银行存款日记账
      String credenceType=(String)request.getSession().getAttribute("credenceType_gjp");
      cashDayClearBS.deleteCashDayClearTcList(idAF.getId().toString(),credenceType,securityInfo);
      }catch (BusinessException bex) {
        messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(bex.getLocalizedMessage(),
            false));
        saveErrors(request, messages);
      }catch(Exception e){
        e.printStackTrace();
      }
      return mapping.findForward("cashdaycleartc_show");
  }
  
  public ActionForward modify(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    saveToken(request);
    ActionMessages messages = new ActionMessages();
    try{
      IdAF idAF = (IdAF)form;
      SecurityInfo securityInfo = (SecurityInfo) request.getSession()
      .getAttribute("SecurityInfo");
      ICashDayClearBS cashDayClearBS = (ICashDayClearBS) BSUtils
      .getBusinessService("cashDayClearBS", this, mapping.getModuleConfig());
      CashDayClearTaDTO cashDayClearTaDTO=cashDayClearBS.findModifyInfo(idAF.getId().toString(),securityInfo);
      CashDayClearTaAF cashDayClearTaAF=new CashDayClearTaAF();
      cashDayClearTaAF.setCashDayClearTaDTO(cashDayClearTaDTO);
      cashDayClearTaAF.getCashDayClearTaDTO().setType("1");
      request.getSession().setAttribute("cashDayClearTaAF", cashDayClearTaAF);
      }catch (BusinessException bex) {
        messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(bex.getLocalizedMessage(),
            false));
        saveErrors(request, messages);
        return mapping.findForward("cashdaycleartc_show");
      }catch(Exception e){
        e.printStackTrace();
      }
      return mapping.findForward("cashdayclearta_show");
  }
  
  public ActionForward print(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {

    List list = null;
    try {
      Pagination pagination = (Pagination) request.getSession().getAttribute(
          CashDayClearTcShowAC.PAGINATION_KEY);
      SecurityInfo securityInfo = (SecurityInfo) request.getSession()
      .getAttribute("SecurityInfo");
      ICashDayClearBS cashDayClearBS = (ICashDayClearBS) BSUtils
      .getBusinessService("cashDayClearBS", this, mapping.getModuleConfig());
      //用credenceType来判断是现金日记账还是银行存款日记账
      String credenceType=(String)request.getSession().getAttribute("credenceType_gjp");
      list = cashDayClearBS.findCashDayClearTcPrintList(pagination,credenceType,securityInfo);
      //type是为了到页面上做标识的0为现金日记账；1为银行存款日记账
      if(credenceType.equals("0")){
        request.setAttribute("type", "0");
      }
      if(credenceType.equals("1")){
        request.setAttribute("type", "1");
      }
    } catch (Exception e) {
      // TODO: handle exception
      e.printStackTrace();
    }
    String url = "cashDayClearTcShowAC.do";
    request.setAttribute("printList", list);
    request.setAttribute("url", url);
    return mapping.findForward("to_cashdaycleartc_print");
  }
  
  public ActionForward deleteall(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    ActionMessages messages = new ActionMessages();
    try{
      List list=(List)request.getSession().getAttribute("countList");
      SecurityInfo securityInfo = (SecurityInfo) request.getSession()
      .getAttribute("SecurityInfo");
      ICashDayClearBS cashDayClearBS = (ICashDayClearBS) BSUtils
      .getBusinessService("cashDayClearBS", this, mapping.getModuleConfig());
      //用credenceType来判断是现金日记账还是银行存款日记账
      String credenceType=(String)request.getSession().getAttribute("credenceType_gjp");
      cashDayClearBS.deleteCashDayClearTcListAll(list, securityInfo, credenceType);
      }catch (BusinessException bex) {
        messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(bex.getLocalizedMessage(),
            false));
        saveErrors(request, messages);
      }catch(Exception e){
        e.printStackTrace();
      }
      return mapping.findForward("cashdaycleartc_show");
  }
  public ActionForward exports(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
    ActionMessages messages =null;
    List expList = null;
    try{
      HttpSession session=request.getSession(false);
      Pagination pagination = (Pagination) request.getSession().getAttribute(
          CashDayClearTcShowAC.PAGINATION_KEY);
      SecurityInfo securityInfo = (SecurityInfo) request.getSession()
      .getAttribute("SecurityInfo");
      ICashDayClearBS cashDayClearBS = (ICashDayClearBS) BSUtils
      .getBusinessService("cashDayClearBS", this, mapping.getModuleConfig());
      //用credenceType来判断是现金日记账还是银行存款日记账
      String credenceType=(String)request.getSession().getAttribute("credenceType_gjp");
      expList = cashDayClearBS.findCashDayClearTcPrintList_wsh(pagination,credenceType,securityInfo);
                               
     
      if(expList.size()>0)
      {
          messages=new ActionMessages();
          messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("导出成功！",
            false));
          saveErrors(request, messages);
          request.getSession().setAttribute("explist",expList);
          response.sendRedirect(request.getContextPath()+"/ExportServlet?ISCANSHU=false&EXP_NAME=cashDayClear_exp");
      }else
      {
        messages=new ActionMessages();
        messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("没有数据！",
            false));
        saveErrors(request, messages);
      }
            return null;
    }catch(BusinessException bex){
      messages=new ActionMessages();
      messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(bex
          .getMessage(), false));
      saveErrors(request, messages);
    }
    return mapping.findForward("cashdaycleartc_show");
  }
  protected Map getKeyMethodMap() {
      Map map = new HashMap();
      map.put("button.update", "modify");
      map.put("button.delete", "delete");
      map.put("button.print", "print");
      map.put("button.deleteall", "deleteall");
      map.put("button.export", "exports");
      return map;
  }
}
