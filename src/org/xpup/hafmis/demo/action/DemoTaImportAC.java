/*
 * Generated by MyEclipse Struts
 * Template path: templates/java/JavaClass.vtl
 */
package org.xpup.hafmis.demo.action;
        
import java.io.File;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;
import org.xpup.common.exception.BusinessException;
import org.xpup.common.util.BSUtils;
import org.xpup.common.util.imp.Factory;
import org.xpup.hafmis.demo.bsinterface.IDemoBS;
import org.xpup.hafmis.demo.form.DemoImportAF;

/** 
 * MyEclipse Struts
 * Creation date: 04-11-2007
 * 
 * XDoclet definition:
 * @struts.action validate="true"
 */
public class DemoTaImportAC extends Action {
	/*
	 * Generated Methods
	 */
  

  /** 
	 * Method execute
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 */
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
    DemoImportAF forms = (DemoImportAF) form; 
    FormFile file = forms.getTheFile(); 
    String fileType = file.getFileName().substring(file.getFileName().lastIndexOf(".") + 1);
    if(file.toString().length()<1){ 
      return (mapping.findForward("文件无后缀"));
    }else if(!fileType.equals("xls")){
      return (mapping.findForward("文件后缀不是xls格式"));
    }
    ServletContext context = request.getSession().getServletContext();
    String jasperSrc = context.getRealPath("/impXml/"+ "demo_imp.xml");
    File files=new File(forms.getUrl());
    Factory faxtory=new Factory();
    File xmlfile=new File(jasperSrc);
    Map info=null;
    InputStream stream=null;
    try{
        stream= file.getInputStream();
    }catch(Exception e){
          e.printStackTrace();
        }
    try {
      info = faxtory.getInfomation(xmlfile,stream,"org.xpup.hafmis.demo.dto.");
    } catch (Exception e) {
      e.printStackTrace();
    }
    List demoImportList1=(List)info.get("DemoImport");
    
    IDemoBS demoBS = (IDemoBS) BSUtils
    .getBusinessService("demoBS", this, mapping.getModuleConfig());
    
    List failList=null;
    try {
      failList=demoBS.modifyDemoBatch(demoImportList1);
      if(failList.size()>0)
      {
        request.getSession().setAttribute("explist",failList);
        response.sendRedirect(request.getContextPath()+"/ExportServlet?ISCANSHU=false&EXP_NAME=demo_exp");
        return null;
      }
    } catch (BusinessException e) {
      e.printStackTrace();
    } catch (Exception e) {
      e.printStackTrace();
    }
		return mapping.findForward("showDemoListAC");
	}
  
 
}