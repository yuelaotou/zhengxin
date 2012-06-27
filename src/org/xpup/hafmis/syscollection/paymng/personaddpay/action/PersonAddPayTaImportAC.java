package org.xpup.hafmis.syscollection.paymng.personaddpay.action;

import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
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
import org.xpup.common.util.Pagination;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.syscollection.paymng.personaddpay.form.PersonAddPayAF;
import org.xpup.hafmis.syscollection.paymng.personaddpay.form.PersonAddPayImportAF;
import org.xpup.hafmis.syscollection.paymng.personaddpay.bsinterface.IPersonAddPayBS;
import org.xpup.common.util.imp.Factory;

public class PersonAddPayTaImportAC extends Action {

public static final String PAGINATION_KEY = "org.xpup.hafmis.syscollection.paymng.personaddpay.action.PersonAddPayTaShowAC";
  

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
    Pagination pagination = getPagination(PAGINATION_KEY, request); 
    SecurityInfo securityInfo = (SecurityInfo) request.getSession().getAttribute("SecurityInfo");
    PersonAddPayImportAF forms = (PersonAddPayImportAF) form; 
    ActionMessages messages =null;
    FormFile file = forms.getTheFile(); 
    Map info=null;
    
    String fileType = file.getFileName().substring(file.getFileName().lastIndexOf(".") + 1);
    if(file.toString().length()<1){ 
      return (mapping.findForward("文件无后缀"));
    }else if(!fileType.equals("xls")){
      return (mapping.findForward("文件后缀不是xls格式"));
    }
    InputStream stream=null;
    try{
        stream= file.getInputStream();
    }catch(Exception e){
          e.printStackTrace();
        }
    
    String orgId = request.getParameter("orgId");   
    String orgName = request.getParameter("orgName");  
    String noteNum = request.getParameter("noteNum");
    String payment_orgname = request.getParameter("payment_orgname");
    ServletContext context = request.getSession().getServletContext();
    String jasperSrc = context.getRealPath("/impXml/"+ "empaddpay_imp.xml");
    File files=new File(forms.getUrl());
    Factory faxtory=new Factory();
    File xmlfile=new File(jasperSrc);
    List addpayHeadImportList = new ArrayList();
    List addpayListImportList = new ArrayList();
    try {
      stream=file.getInputStream();
      info = faxtory.getInfomation(xmlfile,stream,"org.xpup.hafmis.syscollection.paymng.personaddpay.dto.");
      addpayHeadImportList=(List)info.get("EmpaddpayHeadImport");
      addpayListImportList = (List)info.get("EmpaddpayListImport");  
    } catch (Exception e) {
    //  e.printStackTrace();
    }
    IPersonAddPayBS personAddPayBS = (IPersonAddPayBS) BSUtils
    .getBusinessService("personAddPayBS", this, mapping.getModuleConfig());
    List failList=null;
    PersonAddPayAF personAddPayAF = (PersonAddPayAF)request.getSession().getAttribute("personAddPayAF");
    personAddPayAF.setPayKind(forms.getPayKind());
    personAddPayAF.setPayWay(forms.getPayWay());
    personAddPayAF.setPayment_orgname(payment_orgname);
    try {
      failList=personAddPayBS.empaddPaylist(addpayHeadImportList,addpayListImportList,orgId,noteNum,securityInfo,personAddPayAF);
      if(failList.size()>0)
      {
        request.getSession().setAttribute("explist",failList);
        response.sendRedirect(request.getContextPath()+"/ExportServlet?ISCANSHU=false&EXP_NAME=empaddpay_exp");
        return null;
      }
    } catch (BusinessException b) {
      b.printStackTrace();
      messages=new ActionMessages();
      messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("导入失败！"+b.getMessage(),
          false));
      saveErrors(request, messages);
    } catch (Exception e) {
      e.printStackTrace();
    }
    request.setAttribute("importEmp", "importEmp");
    return mapping.findForward("personAddPayTaShowAC");
  }
  
  private Pagination getPagination(String paginationKey,
      HttpServletRequest request) {
    Pagination pagination = (Pagination) request.getSession().getAttribute(
        paginationKey);
    if (pagination == null) {
      pagination = new Pagination(0, 10, 1, "addPayTail.empId", "ASC",
          new HashMap(0));
      request.getSession().setAttribute(paginationKey, pagination);
    }   
 
    return pagination;
  } 
}