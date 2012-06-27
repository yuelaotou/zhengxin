package org.xpup.hafmis.signjoint.action;
        
import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
import org.xpup.hafmis.demo.bsinterface.IDemoBS;
import org.xpup.hafmis.signjoint.bsinterface.ISignjointBS;
import org.xpup.hafmis.signjoint.dto.SignImportBodyDTO;
import org.xpup.hafmis.signjoint.dto.SignImportHeadDTO;
import org.xpup.hafmis.signjoint.form.*;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;

/**
 * 导入数据
 * @author yinchao
 *
 */
public class SignImportAC extends Action {

  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response) {
    
    SignImportAF forms = (SignImportAF) form; 
    FormFile file = forms.getTheFile(); 
    String fileType = file.getFileName().substring(file.getFileName().lastIndexOf(".") + 1);
    if(file.toString().length()<1){
      ActionMessages messages=new ActionMessages();
      messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("文件无后缀！",
        false));
      saveErrors(request, messages);
      return mapping.findForward("showSignListAC");
    }else if(!fileType.equals("xls")){
      ActionMessages messages=new ActionMessages();
      messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("文件后缀名不是xls！",
        false));
      saveErrors(request, messages);
      return mapping.findForward("showSignListAC");
    }
    ServletContext context = request.getSession().getServletContext();
    String jasperSrc = context.getRealPath("/impXml/"+ "sign_imp.xml");
    File files=new File(forms.getUrl());
    Factory faxtory=new Factory();
    File xmlfile=new File(jasperSrc);
    Map info=null;
    InputStream stream=null;
    //获得操作员信息，和操作时间
    //SecurityInfo securityInfo = (SecurityInfo) request.getSession().getAttribute("SecurityInfo");
    //String bizDate = securityInfo.getUserInfo().getBizDate();// 会计日期
    //String operator = securityInfo.getUserName();//操作员
    //
    try{
        stream= file.getInputStream();
    }catch(Exception e){
          e.printStackTrace();
    }
    try {
      info = faxtory.getInfomation(xmlfile,stream,"org.xpup.hafmis.signjoint.dto.");
    
    } catch (Exception e) {
      e.printStackTrace();
      ActionMessages messages=new ActionMessages();
      messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("导入失败！",
        false));
      saveErrors(request, messages);
      return mapping.findForward("importAC");
    }
    List headlist=(List)info.get("SignImportHead");
    List bodylist=(List)info.get("SignImportBody");
    ISignjointBS bs=(ISignjointBS)BSUtils.getBusinessService("SignjointBS",this, mapping.getModuleConfig());
    try {
      bs.signImpBatch(headlist,bodylist);
    } catch (BusinessException e) {
      e.printStackTrace();
      ActionMessages messages=new ActionMessages();
      messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("导入失败！",
        false));
      saveErrors(request, messages);
      return mapping.findForward("importAC");
    } catch (Exception e) {
      e.printStackTrace();
      ActionMessages messages=new ActionMessages();
      messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("导入失败！",
        false));
      saveErrors(request, messages);
      return mapping.findForward("importAC");
    }
    ActionMessages messages=new ActionMessages();
    messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("导入成功！",
      false));
    saveErrors(request, messages);
    return mapping.findForward("showSignListAC");
  }
  
 
}