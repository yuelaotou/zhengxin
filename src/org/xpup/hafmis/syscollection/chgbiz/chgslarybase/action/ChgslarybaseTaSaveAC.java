package org.xpup.hafmis.syscollection.chgbiz.chgslarybase.action;

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
import org.xpup.hafmis.syscollection.chgbiz.chgslarybase.bsinterface.IChgslarybaseBS;
import org.xpup.hafmis.syscollection.chgbiz.chgslarybase.form.ChgslarybaseNewAF;
import org.xpup.hafmis.syscollection.common.domain.entity.ChgPaymentTail;

public class ChgslarybaseTaSaveAC extends LookupDispatchAction {
  public static final String PAGINATION_KEY = "org.xpup.hafmis.syscollection.chgbiz.chgslarybase.action.ChgslarybaseTaShowAC";

  protected Map getKeyMethodMap() {
    Map map = new HashMap();
    map.put("button.add", "add");
    map.put("button.update", "update");
    map.put("button.back", "back");
    return map;
  }

  public ActionForward add(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    ActionMessages messages = null;
    ChgslarybaseNewAF  chgslarybaseNewAF = (ChgslarybaseNewAF) form;
    ChgPaymentTail chgPaymentTail = chgslarybaseNewAF.getChgPaymentTail();
    Pagination pagination = (Pagination) request.getSession().getAttribute(
        PAGINATION_KEY);
    chgPaymentTail = chgslarybaseNewAF.getChgPaymentTail();
    String empId = request.getParameter("chgPaymentTail.empId");
    pagination.getQueryCriterions().put("chgPaymentTail.empId", empId);
    String orgRate = (String) pagination.getQueryCriterions().get("orgRate");
    String empRate = (String) pagination.getQueryCriterions().get("empRate");
    String orgId = (String) pagination.getQueryCriterions().get("org.id");
    String payPrecision = (String) pagination.getQueryCriterions().get("payPrecision");
    try {
        messages = new ActionMessages();
        if(!isTokenValid(request,true))
        {
          messages = new ActionMessages();
          messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("请不要重复提交！",false));         
          saveErrors(request, messages);
          saveToken(request);
        }else{
          saveToken(request);
        IChgslarybaseBS chgslarybaseBS = (IChgslarybaseBS) BSUtils
            .getBusinessService("chgslarybaseBS", this, mapping
                .getModuleConfig());
        chgslarybaseBS.addChgPaymentTail(chgPaymentTail, pagination);
        }
        Map sexMap=BusiTools.listBusiProperty(BusiConst.SEX);
        Map documentsstateMap=BusiTools.listBusiProperty(BusiConst.DOCUMENTSSTATE);
        chgslarybaseNewAF=new ChgslarybaseNewAF();
        chgslarybaseNewAF.setOrgRate(orgRate);
        chgslarybaseNewAF.setEmpRate(empRate);
        chgslarybaseNewAF.setPayPrecision(payPrecision);
        chgslarybaseNewAF.setDocumentsstateMap(documentsstateMap);
        chgslarybaseNewAF.setOrgId(orgId);
        chgslarybaseNewAF.setSexMap(sexMap);
        chgslarybaseNewAF.setType("1");
        request.setAttribute("chgslarybaseNewAF", chgslarybaseNewAF);  
    } catch (BusinessException bex) {
      messages = new ActionMessages();
      messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("添加失败！",
          false));
      saveErrors(request, messages);
      Map sexMap=BusiTools.listBusiProperty(BusiConst.SEX);
      Map documentsstateMap=BusiTools.listBusiProperty(BusiConst.DOCUMENTSSTATE);
      ChgslarybaseNewAF ch=new ChgslarybaseNewAF();
      ch.setOrgRate(orgRate);
      ch.setEmpRate(empRate);
      ch.setPayPrecision(payPrecision);
      ch.setDocumentsstateMap(documentsstateMap);
      ch.setOrgId(orgId);
      ch.setSexMap(sexMap);
      ch.setType("1");
      request.setAttribute("chgslarybaseNewAF", ch);  
      return mapping.findForward("to_chgslarybase_new.jsp");
    }
    return mapping.findForward("to_chgslarybase_new.jsp");
  }

  public ActionForward update(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    ActionMessages messages = null;
    ChgPaymentTail chgPaymentTail = new ChgPaymentTail();
    ChgslarybaseNewAF chgslarybaseNewAF = (ChgslarybaseNewAF) form;
    try {
      Pagination pagination = (Pagination) request.getSession().getAttribute(
          PAGINATION_KEY);
      String orgRate = (String) pagination.getQueryCriterions().get("orgRate");
      String empRate = (String) pagination.getQueryCriterions().get("empRate");
      String payPrecision = (String) pagination.getQueryCriterions().get("payPrecision");
      chgPaymentTail = chgslarybaseNewAF.getChgPaymentTail();
 
      IChgslarybaseBS chgslarybaseBS = (IChgslarybaseBS) BSUtils
          .getBusinessService("chgslarybaseBS", this, mapping.getModuleConfig());
      chgslarybaseBS.updateChgPaymentTail(chgPaymentTail, pagination);
      Map sexMap=BusiTools.listBusiProperty(BusiConst.SEX);
      Map documentsstateMap=BusiTools.listBusiProperty(BusiConst.DOCUMENTSSTATE);
      
      chgslarybaseNewAF=new ChgslarybaseNewAF();
      chgslarybaseNewAF.setOrgRate(orgRate);
      chgslarybaseNewAF.setEmpRate(empRate);
      chgslarybaseNewAF.setPayPrecision(payPrecision);
      chgslarybaseNewAF.setDocumentsstateMap(documentsstateMap);
      chgslarybaseNewAF.setSexMap(sexMap);
      chgslarybaseNewAF.setType("2");
   
      request.setAttribute("chgslarybaseNewAF", chgslarybaseNewAF);
    } catch (BusinessException bex) {
      messages = new ActionMessages();
      messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("修改失败！",
          false));
      saveErrors(request, messages);
      return mapping.findForward("to_chgslarybase_new.jsp");
    }
    return mapping.findForward("to_chgslarybase_new.jsp");
  }

  public ActionForward back(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    return mapping.findForward("chgslarybaseTaShowAC");
  }

  protected String getPaginationKey() {
    return ChgslarybaseTaShowAC.PAGINATION_KEY;
  }
}
