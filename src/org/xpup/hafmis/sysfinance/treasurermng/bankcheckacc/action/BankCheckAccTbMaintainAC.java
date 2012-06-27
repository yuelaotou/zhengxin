package org.xpup.hafmis.sysfinance.treasurermng.bankcheckacc.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
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
import org.xpup.hafmis.common.form.IdAF;
import org.xpup.hafmis.orgstrct.dto.OfficeDto;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;

import org.xpup.hafmis.sysfinance.treasurermng.bankcheckacc.bsinterface.IBankCheckAccBS;
import org.xpup.hafmis.sysfinance.treasurermng.bankcheckacc.dto.BankCheckAccTaDTO;
import org.xpup.hafmis.sysfinance.treasurermng.bankcheckacc.form.BankCheckAccTaAF;




public class BankCheckAccTbMaintainAC extends LookupDispatchAction {
  protected Map getKeyMethodMap() {
    Map map = new HashMap();
    map.put("button.example.exports", "exports");
    map.put("button.database.imports", "imports");
    map.put("button.update", "modify");
    map.put("button.delete", "delete");
    map.put("button.deleteall", "deleteAll");
    map.put("button.print", "print");
    return map;
  }
  
  public ActionForward exports(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
    try{
      SecurityInfo securityInfo = (SecurityInfo) request.getSession().getAttribute("SecurityInfo");
      IBankCheckAccBS bankCheckAccBS = (IBankCheckAccBS) BSUtils
      .getBusinessService("bankCheckAccBS", this, mapping.getModuleConfig());
      List expList=new ArrayList();
      List settTypeList=null;
      List summaryList=null;
      Object[] obj=bankCheckAccBS.findParamExplainList(securityInfo, "");
      if(obj.length>0){
        //从数据库中查出结算方式的list，显示在下拉菜单中
        settTypeList=(List)obj[1];
        summaryList=(List)obj[0];
      }
      expList.add(0, settTypeList);
      expList.add(1, summaryList);
      request.getSession().setAttribute("explist",expList);
      response.sendRedirect(request.getContextPath()+"/ExportServlet?ISCANSHU=false&EXP_NAME=bankcheckacc_exp");
    }catch(Exception e){
      e.printStackTrace();
    }
    return null;
  }
  
  public ActionForward imports(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
    SecurityInfo securityInfo = (SecurityInfo) request.getSession().getAttribute("SecurityInfo");
    List officeList1 = null;
    try {
      // 取出用户权限办事处,显示在下拉菜单中
      List officeList = securityInfo.getOfficeList();
      officeList1 = new ArrayList();
      OfficeDto officedto = null;
      Iterator itr1 = officeList.iterator();
      while (itr1.hasNext()) {
        officedto = (OfficeDto) itr1.next();
        officeList1.add(new org.apache.struts.util.LabelValueBean(officedto
            .getOfficeName(), officedto.getOfficeCode()));
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    request.setAttribute("officeList1", officeList1);
    return mapping.findForward("to_bankcheckacctb_imports");
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
      IBankCheckAccBS bankCheckAccBS = (IBankCheckAccBS) BSUtils
      .getBusinessService("bankCheckAccBS", this, mapping.getModuleConfig());
      BankCheckAccTaDTO bankCheckAccTaDTO=bankCheckAccBS.findModifyInfo(idAF.getId().toString(),securityInfo);
      BankCheckAccTaAF bankCheckAccTaAF=new BankCheckAccTaAF();
      bankCheckAccTaAF.setBankCheckAccTaDTO(bankCheckAccTaDTO);
      bankCheckAccTaAF.getBankCheckAccTaDTO().setType("1");
      request.setAttribute("bankCheckAccTaAF", bankCheckAccTaAF);
      }catch (BusinessException bex) {
        messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(bex.getLocalizedMessage(),
            false));
        saveErrors(request, messages);
        return mapping.findForward("bankcheckacctb_show");
      }catch(Exception e){
        e.printStackTrace();
      }
      return mapping.findForward("bankcheckaccta_show");
  }
  
  public ActionForward delete(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    ActionMessages messages = new ActionMessages();
    try{
      IdAF idAF = (IdAF)form;
      SecurityInfo securityInfo = (SecurityInfo) request.getSession()
      .getAttribute("SecurityInfo");
      IBankCheckAccBS bankCheckAccBS = (IBankCheckAccBS) BSUtils
      .getBusinessService("bankCheckAccBS", this, mapping.getModuleConfig());
      bankCheckAccBS.deleteBankCheckAccTbList(idAF.getId().toString(), securityInfo);
      }catch (BusinessException bex) {
        messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(bex.getLocalizedMessage(),
            false));
        saveErrors(request, messages);
      }catch(Exception e){
        e.printStackTrace();
      }
      return mapping.findForward("bankcheckacctb_show");
  }
  
  public ActionForward deleteAll(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    ActionMessages messages = new ActionMessages();
    try{
      List list=(List)request.getSession().getAttribute("countList");
      SecurityInfo securityInfo = (SecurityInfo) request.getSession()
      .getAttribute("SecurityInfo");
      IBankCheckAccBS bankCheckAccBS = (IBankCheckAccBS) BSUtils
      .getBusinessService("bankCheckAccBS", this, mapping.getModuleConfig());
      bankCheckAccBS.deleteBankCheckAccTbListAll(list, securityInfo);
      }catch (BusinessException bex) {
        messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(bex.getLocalizedMessage(),
            false));
        saveErrors(request, messages);
      }catch(Exception e){
        e.printStackTrace();
      }
      return mapping.findForward("bankcheckacctb_show");
  }
  public ActionForward print(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    List list = null;
    try {
      Pagination pagination = (Pagination) request.getSession().getAttribute(
          BankCheckAccTbShowAC.PAGINATION_KEY);
      SecurityInfo securityInfo = (SecurityInfo) request.getSession()
      .getAttribute("SecurityInfo");
      IBankCheckAccBS bankCheckAccBS = (IBankCheckAccBS) BSUtils
      .getBusinessService("bankCheckAccBS", this, mapping.getModuleConfig());
      list = bankCheckAccBS.findBankCheckAccPrintList(pagination, securityInfo);
    } catch (Exception e) {
      e.printStackTrace();
    }
    String url = "bankCheckAccTbShowAC.do";
    request.setAttribute("printList", list);
    request.setAttribute("url", url);
    return mapping.findForward("to_bankcheckacctb_print");
  }
}
