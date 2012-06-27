package org.xpup.hafmis.syscollection.chgbiz.chgperson.action;

import java.math.BigDecimal;
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
import org.xpup.hafmis.syscollection.chgbiz.chgperson.bsinterface.IChgpersonDoBS;
import org.xpup.hafmis.syscollection.chgbiz.chgperson.form.ChgpersonDoIdAF;
import org.xpup.hafmis.syscollection.chgbiz.chgperson.form.ChgpersonEmpAF;
import org.xpup.hafmis.syscollection.common.domain.entity.ChgPersonTail;
import org.xpup.hafmis.syscollection.common.domain.entity.Emp;

public class SaveChgpersonDoAC extends LookupDispatchAction{
  
  protected Map getKeyMethodMap() {
    Map map = new HashMap();
    map.put("button.add", "add");
    map.put("button.back", "back");
    return map;
  }
  public ActionForward add(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
    
    ActionMessages messages = null;
    ChgpersonDoIdAF chgpersonDoIdAF = (ChgpersonDoIdAF)form;
    try{
      String id = chgpersonDoIdAF.getId().toString();
      ChgpersonEmpAF chgpersonEmpAF = new ChgpersonEmpAF();
      HttpSession session = request.getSession();
      
      chgpersonEmpAF = (ChgpersonEmpAF)session.getAttribute("chgpersonEmpAF_WL");
      String chgMap_1=chgpersonEmpAF.getChgMap_1();//变更类型
      String documentMap_1=chgpersonEmpAF.getDocumentMap_1();//证件类型
      String sexMap_1=chgpersonEmpAF.getSexMap_1();//性别
      String partInMap_1=chgpersonEmpAF.getPartInMap_1();//是否参与汇缴
      String chgreasonMap_1=chgpersonEmpAF.getChgreasonMap_1();//变更原因
      ChgPersonTail chgPersonTail = chgpersonEmpAF.getChgPersonTail();
    
      String orgID=(String)session.getAttribute("orgID");//单位编号
      String chgDate=(String)session.getAttribute("chgDate");//变更年月

      IChgpersonDoBS chgpersonDoBS = (IChgpersonDoBS) BSUtils
          .getBusinessService("chgpersonDoBS", this, mapping.getModuleConfig());   
      
      SecurityInfo securityInfo = (SecurityInfo) request.getSession()
      .getAttribute("SecurityInfo");
      
      Emp emp = chgpersonDoBS.queryEmpByID(id);
      String empID=emp.getEmpId().toString();
      
      chgPersonTail.setName(chgPersonTail.getName().trim());
      chgPersonTail.setCardNum(chgPersonTail.getCardNum().trim());
      chgPersonTail.setBirthday(chgPersonTail.getBirthday().trim());
      chgPersonTail.setDept(chgPersonTail.getDept().trim());
      chgPersonTail.setMobileTel(chgPersonTail.getMobileTel().trim());
      chgPersonTail.setTel(chgPersonTail.getTel().trim());
      chgPersonTail.setMonthIncome(new BigDecimal(chgPersonTail.getMonthIncome().toString().trim()));
      chgPersonTail.setSalaryBase(new BigDecimal(chgPersonTail.getSalaryBase().toString().trim()));
      chgPersonTail.setOrgPay(new BigDecimal( chgPersonTail.getOrgPay().toString().trim()));
      chgPersonTail.setEmpPay(new BigDecimal(chgPersonTail.getEmpPay().toString().trim()));
        
      
     String heaId= chgpersonDoBS.saveChgpersonDO_wsh(orgID,empID,chgDate,chgMap_1,documentMap_1,sexMap_1,partInMap_1,chgreasonMap_1,chgPersonTail,null,securityInfo);
     request.getSession().setAttribute("changeHeadId", heaId);
    } catch (BusinessException bex) {
      messages = new ActionMessages();
      messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(bex
          .getLocalizedMessage().toString(), false));
      saveErrors(request, messages);
    }catch(Exception e){
    }
    return mapping.findForward("to_chgperson_save");
  }
  
  public ActionForward back(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
    try{
      ChgpersonEmpAF chgpersonEmpAF = new ChgpersonEmpAF();
      HttpSession session = request.getSession();
      
      Map chgMap=BusiTools.listBusiProperty(BusiConst.CHGSTATUS);
      chgpersonEmpAF.setChgMap(chgMap);
       
      Map documentMap=BusiTools.listBusiProperty(BusiConst.DOCUMENTSSTATE);
      chgpersonEmpAF.setDocumentMap(documentMap);
      Map sexMap=BusiTools.listBusiProperty(BusiConst.SEX);
      chgpersonEmpAF.setSexMap(sexMap);
      Map partInMap=BusiTools.listBusiProperty(BusiConst.YesNo);
      chgpersonEmpAF.setPartInMap(partInMap);

      chgpersonEmpAF = (ChgpersonEmpAF)session.getAttribute("chgpersonEmpAF_WL");
      request.setAttribute("chgpersonEmpAF", chgpersonEmpAF);
      
    }catch(Exception e){
    }
    return mapping.findForward("to_chgperson_list");
  }
}