package org.xpup.hafmis.syscollection.accountmng.accountopen.action;

import java.io.File;
import java.math.BigDecimal;
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
import org.xpup.hafmis.demo.bsinterface.IDemoBS;
import org.xpup.hafmis.demo.form.DemoImportAF;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.syscollection.accountmng.accountopen.bsinterface.IOrgOpenAccountBS;
import org.xpup.hafmis.syscollection.accountmng.accountopen.dto.EmpOpenExpContentDTO;
import org.xpup.hafmis.syscollection.accountmng.accountopen.dto.EmpOpenImpContentDTO;
import org.xpup.hafmis.syscollection.accountmng.accountopen.form.EmpOpenImportAF;
import org.xpup.hafmis.syscollection.chgbiz.chgperson.bsinterface.IChgpersonDoBS;
import org.xpup.hafmis.syscollection.chgbiz.chgperson.form.ChgpersonDoListAF;
/**
 * 职工开户导入
 * @author 孙亮
 *2007-7-10
 */
public class EmpOpenImportAC extends Action {
  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response){
    ActionMessages messages = null;
    EmpOpenImportAF af = (EmpOpenImportAF) form; 
    FormFile file = af.getTheFile(); 
    String fileType = file.getFileName().substring(file.getFileName().lastIndexOf(".") + 1);

    if(file.toString().length()<1){
      return (mapping.findForward("文件无后缀"));
    }else if(!fileType.equals("xls")){
      return (mapping.findForward("文件后缀不是xls格式"));
    }
    ServletContext context = request.getSession().getServletContext();
    String jasperSrc = context.getRealPath("/impXml/"+ "empopen_imp.xml");
    File files=new File(af.getUrl());
    Factory faxtory=new Factory();
    File xmlfile=new File(jasperSrc);   
    Map info=null;
    try {                                     
      info = faxtory.getInfomation(xmlfile,file.getInputStream(),"org.xpup.hafmis.syscollection.accountmng.accountopen.dto.");
    } catch (Exception e) {
      e.printStackTrace();
      messages = new ActionMessages();
      messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("导入失败！", false));
      saveErrors(request, messages);
    }
    try {
      List empOpenImpTitle=(List)info.get("EmpOpenImpTitle");   
      List empOpenImpContent=(List)info.get("EmpOpenImpContent");
      IOrgOpenAccountBS orgOpenAccountBS = (IOrgOpenAccountBS) BSUtils
      .getBusinessService("orgOpenAccountBS", this, mapping.getModuleConfig());
      String orgId = String.valueOf(request.getSession().getAttribute("org.id"));
      orgId = orgId.substring(1,orgId.length());
     // System.out.println("orgId==="+orgId);
      List failList=null;
      SecurityInfo sInfo = (SecurityInfo)request.getSession().getAttribute("SecurityInfo");
      failList=orgOpenAccountBS.modifyOrgOpenBatch(empOpenImpTitle,empOpenImpContent,orgId,sInfo);
      
     
        if(failList!=null){
        if (failList.size() > 0) { 
         
          String orgId_1 = String
          .valueOf(request.getSession().getAttribute("org.id"));
          orgId_1 = orgId_1.substring(1,orgId.length());
          
        //  System.out.println("orgId_1==="+orgId_1);
      String orgname = String.valueOf(request.getSession().getAttribute(
          "org.name"));
      BigDecimal paymode = (BigDecimal) request.getSession().getAttribute(
          "org.paymode");
      List expList = new ArrayList();
      expList.add(0, BusiTools.convertSixNumber(orgId_1));
      expList.add(1, orgname);
      expList.add(2, paymode.toString());
     // System.out.println("..empopeninportac");
      for(int i=0;i<failList.size();i++){
        EmpOpenImpContentDTO empOpenImpContentDTO =new EmpOpenImpContentDTO();
        empOpenImpContentDTO=(EmpOpenImpContentDTO)failList.get(i);
        EmpOpenExpContentDTO empOpenExpContentDTO=new EmpOpenExpContentDTO();
        empOpenExpContentDTO.setCardkind(empOpenImpContentDTO.getCardkind());
        empOpenExpContentDTO.setCardnum(empOpenImpContentDTO.getCardnum());
        empOpenExpContentDTO.setDept(empOpenImpContentDTO.getDept());
        empOpenExpContentDTO.setEmpname(empOpenImpContentDTO.getEmpname());
        empOpenExpContentDTO.setEmppay(empOpenImpContentDTO.getEmppay());
        empOpenExpContentDTO.setMobileTle(empOpenImpContentDTO.getMobileTle());
        empOpenExpContentDTO.setMonthIncome(empOpenImpContentDTO.getMonthIncome());
        empOpenExpContentDTO.setOrgpay(empOpenImpContentDTO.getOrgpay());
        empOpenExpContentDTO.setSalarybase(empOpenImpContentDTO.getSalarybase());
        empOpenExpContentDTO.setTel(empOpenImpContentDTO.getTel());
        if(empOpenImpContentDTO.getCardnum().length()==18){
          
          empOpenExpContentDTO.setBirthday( empOpenImpContentDTO.getCardnum().substring(6,14));
         // System.out.println("18--birthday="+empOpenExpContentDTO.getBirthday());
          empOpenExpContentDTO.setSex(""+Integer.parseInt(empOpenImpContentDTO.getCardnum().substring(16,17).toString())%2);
        //  System.out.println("18--sex="+empOpenImpContentDTO.getCardnum().substring(16,17).toString());
          if (empOpenExpContentDTO.getSex().equals("0")){
            empOpenExpContentDTO.setSex("2");
          }
       //   System.out.println("18--sex="+empOpenExpContentDTO.getSex());
        }else{
          empOpenExpContentDTO.setBirthday("19"+empOpenImpContentDTO.getCardnum().substring(6,12));
       //   System.out.println("15--birthday="+empOpenExpContentDTO.getBirthday());
          empOpenExpContentDTO.setSex(""+Integer.parseInt(empOpenImpContentDTO.getCardnum().substring(14,15).toString())%2);
       //   System.out.println("15--sex="+empOpenImpContentDTO.getCardnum().substring(14,15).toString());
          if ( empOpenExpContentDTO.getSex().equals("0")){
            empOpenExpContentDTO.setSex("2");
          }
       //   System.out.println("15--sex="+empOpenExpContentDTO.getSex());
        }
        
        
        expList.add(i+3, empOpenExpContentDTO);
      }
          request.getSession().setAttribute("explist", expList);
          response.sendRedirect(request.getContextPath()
              + "/ExportServlet?ISCANSHU=false&EXP_NAME=empopen_exp");
          return null;
        }
        
        }

    } catch (BusinessException be) {
      messages=new ActionMessages();
      messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("导入失败！"+be.getMessage(),
          false));
      saveErrors(request, messages);
    }catch(Exception s){
      s.printStackTrace();
    }
    return mapping.findForward("employees");
  }
  
 
}