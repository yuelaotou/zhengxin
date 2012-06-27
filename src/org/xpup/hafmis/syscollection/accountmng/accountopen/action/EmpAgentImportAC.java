package org.xpup.hafmis.syscollection.accountmng.accountopen.action;

import java.io.File;
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
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.syscollection.accountmng.accountopen.bsinterface.IOrgOpenAccountBS;
import org.xpup.hafmis.syscollection.accountmng.accountopen.form.EmpOpenImportAF;

/**
 * Copy Right Information : 职工代扣导入Action Goldsoft Project : EmpAgentImportAC
 * 
 * @Version : v1.0
 * @author : 付云峰
 * @Date : 2007.12.14
 */
public class EmpAgentImportAC extends Action {

  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    ActionMessages messages = null;
    try {
      EmpOpenImportAF af = (EmpOpenImportAF) form;
      FormFile file = af.getTheFile();
      String fileType = file.getFileName().substring(
          file.getFileName().lastIndexOf(".") + 1);

      if (file.toString().length() < 1) {
        return (mapping.findForward("文件无后缀"));
      } else if (!fileType.equals("xls")) {
        return (mapping.findForward("文件后缀不是xls格式"));
      }
      ServletContext context = request.getSession().getServletContext();
      String jasperSrc = context.getRealPath("/impXml/" + "empagent_imp.xml");
      Factory faxtory = new Factory();
      File xmlfile = new File(jasperSrc);
      Map info = null;
      info = faxtory.getInfomation(xmlfile, file.getInputStream(),
          "org.xpup.hafmis.syscollection.accountmng.accountopen.dto.");
      List empAgentImportTitleList = (List) info.get("EmpAgentImportTitle");
      List empAgentImportList = (List)info.get("EmpAgentImport");
      IOrgOpenAccountBS orgOpenAccountBS = (IOrgOpenAccountBS) BSUtils
          .getBusinessService("orgOpenAccountBS", this, mapping
              .getModuleConfig());
      SecurityInfo securityInfo = (SecurityInfo) request.getSession()
          .getAttribute("SecurityInfo");
      List failList = orgOpenAccountBS.saveEmpAgentList(empAgentImportTitleList, empAgentImportList, securityInfo);
    } catch (BusinessException be) {
      be.printStackTrace();
      messages = new ActionMessages();
      messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(be
          .getLocalizedMessage(), false));
      saveErrors(request, messages);
    } catch (Exception e) {
      e.printStackTrace();
      messages = new ActionMessages();
      messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("导入失败！",
          false));
      saveErrors(request, messages);
    }
    return mapping.findForward("employee_kh_show");
  }

}
