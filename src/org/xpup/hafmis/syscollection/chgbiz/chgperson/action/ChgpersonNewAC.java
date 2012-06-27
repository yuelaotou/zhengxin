package org.xpup.hafmis.syscollection.chgbiz.chgperson.action;

import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.xpup.hafmis.common.util.BusiConst;
import org.xpup.hafmis.common.util.BusiTools;
import org.xpup.hafmis.syscollection.chgbiz.chgperson.form.ChgpersonEmpAF;

public class ChgpersonNewAC extends Action{
  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    saveToken(request);
    ChgpersonEmpAF chgpersonEmpAF = new ChgpersonEmpAF();
    
    try{
    String chgDate = (String)request.getParameter("chgDate");
    HttpSession session = request.getSession();
    session.setAttribute("chgDate", chgDate);
      
     Map chgMap=BusiTools.listBusiProperty(BusiConst.CHGSTATUS);
     chgpersonEmpAF.setChgMap(chgMap);
     Map documentMap=BusiTools.listBusiProperty(BusiConst.DOCUMENTSSTATE);
     chgpersonEmpAF.setDocumentMap(documentMap);
     Map sexMap=BusiTools.listBusiProperty(BusiConst.SEX);
     chgpersonEmpAF.setSexMap(sexMap);
     Map partInMap=BusiTools.listBusiProperty(BusiConst.YesNo);
     chgpersonEmpAF.setPartInMap(partInMap);
     Map chgreasonMap=BusiTools.listBusiProperty(BusiConst.CHGPERSONREASON);
     chgpersonEmpAF.setChgreasonMap(chgreasonMap);
     

     Map chgreasonMap2=BusiTools.listBusiProperty(BusiConst.CHGPERSONREASON);
     chgpersonEmpAF.setChgreasonMap_2(chgreasonMap2); // 新增开户的变更原因
     String orgID = (String)session.getAttribute("orgID");
     chgpersonEmpAF.setOrgID(orgID);
     
    request.setAttribute("chgpersonEmpAF", chgpersonEmpAF);  
    }catch(Exception e){
    }
    return mapping.findForward("to_chgperson_save");
  }
}
