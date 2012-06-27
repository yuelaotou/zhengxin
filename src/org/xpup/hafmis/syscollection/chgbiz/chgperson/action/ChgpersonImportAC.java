package org.xpup.hafmis.syscollection.chgbiz.chgperson.action;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.upload.FormFile;
import org.xpup.common.exception.BusinessException;
import org.xpup.common.util.BSUtils;
import org.xpup.common.util.imp.Factory;
import org.xpup.hafmis.common.util.BusiTools;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.syscollection.chgbiz.chgperson.bsinterface.IChgpersonDoBS;
import org.xpup.hafmis.syscollection.chgbiz.chgperson.form.ChgpersonDoListAF;
/**
 * 人员变更导入AC
 * @author 王玲
 *2007-7-10
 */
public class ChgpersonImportAC extends Action {
  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response) {

    ActionMessages messages = null;
    ChgpersonDoListAF af = (ChgpersonDoListAF) form; 
    FormFile file = af.getTheFile(); 
    String fileType = file.getFileName().substring(file.getFileName().lastIndexOf(".") + 1);
    if(file.toString().length()<1){
      return (mapping.findForward("文件无后缀"));
    }else if(!fileType.equals("xls")){
      return (mapping.findForward("文件后缀不是xls格式"));
    }
    ServletContext context = request.getSession().getServletContext();
    String jasperSrc = context.getRealPath("/impXml/"+ "chgperson_imp.xml");
    Factory faxtory=new Factory();
    File xmlfile=new File(jasperSrc);
    Map info=null;
    try {
      info = faxtory.getInfomation(xmlfile,file.getInputStream(),"org.xpup.hafmis.syscollection.chgbiz.chgperson.dto.");
    } catch (Exception e) {
    }
    List chgpersonImpTitle=new ArrayList();
    chgpersonImpTitle=(List)info.get("ChgpersonImpTitle");
    List chgpersonImpContent=new ArrayList();   
    chgpersonImpContent=(List)info.get("ChgpersonImpContent");
    
    IChgpersonDoBS chgpersonDoBS = (IChgpersonDoBS) BSUtils
    .getBusinessService("chgpersonDoBS", this, mapping.getModuleConfig());
    
    HttpSession session = request.getSession();
    String orgID = (String)session.getAttribute("orgID");
    orgID = BusiTools.convertSixNumber(orgID);
    List failList=null;
    try {

      SecurityInfo securityInfo = (SecurityInfo) request.getSession()
          .getAttribute("SecurityInfo");
      
      failList=chgpersonDoBS.modifyChgpersonBatch(chgpersonImpTitle,chgpersonImpContent,orgID,securityInfo);
      
    } catch (BusinessException be) {
      messages = new ActionMessages();
      messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
          be.getLocalizedMessage().toString(), false));
      saveErrors(request, messages);
      return mapping.findForward("to_chgperson_importsfail");
      
    } catch (Exception e) {
      e.printStackTrace();
      return mapping.findForward("to_chgperson_importsfail");
    }
    return mapping.findForward("to_chgperson_imports");
  }
  
 
}