package org.xpup.hafmis.syscollection.accountmng.accountopen.action;

import java.util.HashMap;
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
import org.xpup.hafmis.common.util.BusiConst;
import org.xpup.hafmis.common.util.BusiTools;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.syscollection.accountmng.accountopen.bsinterface.IOrgOpenAccountBS;
import org.xpup.hafmis.syscollection.accountmng.accountopen.form.EmpChoIdAF;
import org.xpup.hafmis.syscollection.accountmng.accountopen.form.EmpKhCriteronsAF;
import org.xpup.hafmis.syscollection.chgbiz.chgperson.bsinterface.IChgpersonDoBS;
import org.xpup.hafmis.syscollection.chgbiz.chgperson.form.ChgpersonDoIdAF;
import org.xpup.hafmis.syscollection.chgbiz.chgperson.form.ChgpersonEmpAF;
import org.xpup.hafmis.syscollection.common.domain.entity.ChgPersonTail;
import org.xpup.hafmis.syscollection.common.domain.entity.Emp;

public class EmpChoSaveAC extends LookupDispatchAction{

  protected Map getKeyMethodMap() {
    Map map = new HashMap();
    map.put("button.add", "add");
    map.put("button.back", "back");
    return map;
  }
  public ActionForward add(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
    ActionMessages messages = null;
    if(!isTokenValid(request,true))
    {
      messages = new ActionMessages();
      messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("请不要重复提交！",false));         
      saveErrors(request, messages);
      saveToken(request);
    }else{
      saveToken(request);
      try{
        EmpChoIdAF empchoidaf = (EmpChoIdAF)form;
        SecurityInfo securityInfo = (SecurityInfo) request.getSession().getAttribute("SecurityInfo");
        String id = empchoidaf.getId().toString();
        EmpKhCriteronsAF empKhCriteronsAF = new EmpKhCriteronsAF();
        HttpSession session = request.getSession();
        String emppaymonth = (String) request.getSession().getAttribute("org.emppaymonth");
        String orgId = (String) request.getSession().getAttribute("org.id").toString();
        empKhCriteronsAF = (EmpKhCriteronsAF)session.getAttribute("empkhAF");
        IOrgOpenAccountBS orgOpenAccountBS = (IOrgOpenAccountBS) BSUtils
        .getBusinessService("orgOpenAccountBS", this, mapping.getModuleConfig());   
        orgOpenAccountBS.saveEmployeeInfo(orgId,empKhCriteronsAF.getEmp(),emppaymonth,id,securityInfo);
        
    } catch (BusinessException bex) {
      messages = new ActionMessages();
      messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(bex
          .getLocalizedMessage().toString(), false));
      saveErrors(request, messages);
    }catch(Exception e){
     e.printStackTrace();
    }
    }
    EmpKhCriteronsAF aff = new EmpKhCriteronsAF();
    Map sexMap=BusiTools.listBusiProperty(BusiConst.SEX);
    aff.setSexMap(sexMap);
    Map cardKindMap=BusiTools.listBusiProperty(BusiConst.DOCUMENTSSTATE);
    aff.setCardKindMap(cardKindMap);
    aff.setType("1");
    request.setAttribute("empKhCriteronsAF", aff);
    
    return mapping.findForward("emp_open_new");
  }
  
  public ActionForward back(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
    try{
      EmpKhCriteronsAF aff = new EmpKhCriteronsAF();
      Map sexMap=BusiTools.listBusiProperty(BusiConst.SEX);
      aff.setSexMap(sexMap);
      Map cardKindMap=BusiTools.listBusiProperty(BusiConst.DOCUMENTSSTATE);
      aff.setCardKindMap(cardKindMap);
      aff.setType("1");
      request.setAttribute("empKhCriteronsAF", aff);
      
    }catch(Exception e){
      e.printStackTrace();
    }
    return mapping.findForward("emp_open_new");
  }

}
