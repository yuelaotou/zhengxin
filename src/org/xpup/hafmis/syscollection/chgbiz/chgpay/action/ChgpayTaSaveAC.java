package org.xpup.hafmis.syscollection.chgbiz.chgpay.action;

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
import org.xpup.hafmis.common.util.BusiConst;
import org.xpup.hafmis.common.util.BusiTools;
import org.xpup.hafmis.syscollection.chgbiz.chgpay.bsinterface.IChgpayBS;
import org.xpup.hafmis.syscollection.chgbiz.chgpay.form.ChgpayNewAF;
import org.xpup.hafmis.syscollection.common.domain.entity.ChgPaymentTail;



public class ChgpayTaSaveAC extends LookupDispatchAction{
  public static final String PAGINATION_KEY = "org.xpup.hafmis.syscollection.chgbiz.chgpay.action.ChgpayTaSouwAC";

  protected Map getKeyMethodMap() {
    Map map = new HashMap();
    map.put("button.add", "add");
    map.put("button.update", "update");
    map.put("button.back", "back");
    return map;
  }

  public ActionForward add(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
    ActionMessages messages =null;
    ChgpayNewAF chgpayNewAF =null;
    chgpayNewAF=(ChgpayNewAF)form;
    String orgId=null;
    Pagination pagination = (Pagination) request.getSession().getAttribute(
        PAGINATION_KEY);
    String empId=request.getParameter("chgPaymentTail.empId");
    String salaryBase=request.getParameter("chgPaymentTail.emp.salaryBase");
    pagination.getQueryCriterions().put("chgPaymentTail.empId", empId);
    pagination.getQueryCriterions().put("salaryBase", salaryBase);
    orgId = (String) pagination.getQueryCriterions().get("org.id");
    try{
      messages=new ActionMessages();
      if(!isTokenValid(request,true))
      {
        messages = new ActionMessages();
        messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("请不要重复提交！",false));         
        saveErrors(request, messages);
        saveToken(request);
      }else{
        saveToken(request);
        ChgPaymentTail chgPaymentTail=chgpayNewAF.getChgPaymentTail();
        
        IChgpayBS chgpayBS = (IChgpayBS) BSUtils.getBusinessService("chgpayBS", this,
            mapping.getModuleConfig());
        chgpayBS.addChgPaymentTail(chgPaymentTail,pagination);
        }
        Map sexMap = BusiTools.listBusiProperty(BusiConst.SEX);
        Map documentsstateMap = BusiTools
            .listBusiProperty(BusiConst.DOCUMENTSSTATE);
        chgpayNewAF = new ChgpayNewAF();
        chgpayNewAF.setDocumentsstateMap(documentsstateMap);
        chgpayNewAF.setSexMap(sexMap);
        chgpayNewAF.setOrgId(orgId);
        chgpayNewAF.setType("1");
        request.setAttribute("chgpayNewAF", chgpayNewAF);
    }catch(BusinessException bex){
      messages=new ActionMessages();
      messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("添加失败！",
          false)); 
      saveErrors(request, messages);
      ChgpayNewAF ch=new ChgpayNewAF();
      Map sexMap = BusiTools.listBusiProperty(BusiConst.SEX);
      Map documentsstateMap = BusiTools
          .listBusiProperty(BusiConst.DOCUMENTSSTATE);
      ch.setDocumentsstateMap(documentsstateMap);
      ch.setSexMap(sexMap);
      ch.setOrgId(orgId);
      ch.setType("1");
      request.setAttribute("chgpayNewAF", ch);
      return mapping.findForward("to_chgpay_new.jsp");
    }
    return mapping.findForward("to_chgpay_new.jsp");
    
  }
  public ActionForward update(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
    ActionMessages messages =null;
    ChgPaymentTail chgPaymentTail=new ChgPaymentTail();
    ChgpayNewAF chgpayNewAF=(ChgpayNewAF)form;

    try{
      Pagination pagination = (Pagination) request.getSession().getAttribute(
          PAGINATION_KEY);
     chgPaymentTail=chgpayNewAF.getChgPaymentTail();
     IChgpayBS chgpayBS = (IChgpayBS) BSUtils.getBusinessService("chgpayBS", this,
         mapping.getModuleConfig());
     chgpayBS.updateChgPaymentTail(chgPaymentTail,pagination);

     ChgpayNewAF chgpayNewAF1=new ChgpayNewAF();
     Map sexMap = BusiTools.listBusiProperty(BusiConst.SEX);
     Map documentsstateMap = BusiTools
         .listBusiProperty(BusiConst.DOCUMENTSSTATE);
     chgpayNewAF1.setDocumentsstateMap(documentsstateMap);
     chgpayNewAF1.setSexMap(sexMap);
     chgpayNewAF1.setType("2");
     request.setAttribute("chgpayNewAF", chgpayNewAF1);
    }catch(BusinessException bex){
      messages=new ActionMessages();
      messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("修改失败！",
          false));
      saveErrors(request, messages);
      return mapping.findForward("to_chgpay_new.jsp");  
    }
    return mapping.findForward("to_chgpay_new.jsp");
  }
  public ActionForward back(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
    return mapping.findForward("chgpayTaSouwAC.do");
  }
  protected String getPaginationKey() {
    return ChgpayTaSouwAC.PAGINATION_KEY;
  }
}
