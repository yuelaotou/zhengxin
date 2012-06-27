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
 * Copy Right Information : 单位代扣导入Action Goldsoft Project :
 * OrgAgentImportAC
 * 
 * @Version : v1.0
 * @author : 付云峰
 * @Date : 2007.12.12
 */
public class OrgAgentImportAC extends Action {

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
        messages = new ActionMessages();
        messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("文件无后缀！",
            false));
        saveErrors(request, messages);
        return (mapping.findForward("to_orgagent_imports"));
      } else if (!fileType.equals("xls")) {
        messages = new ActionMessages();
        messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("导入文件格式不正确！",
            false));
        saveErrors(request, messages);
        return (mapping.findForward("to_orgagent_imports"));
      }
      ServletContext context = request.getSession().getServletContext();
      String jasperSrc = context.getRealPath("/impXml/" + "orgagent_imp.xml");
      Factory faxtory = new Factory();
      File xmlfile = new File(jasperSrc);
      Map info = null;
      info = faxtory.getInfomation(xmlfile, file.getInputStream(),
          "org.xpup.hafmis.syscollection.accountmng.accountopen.dto.");
      List orgAgentImportList = (List) info.get("OrgAgentImport");
      IOrgOpenAccountBS orgOpenAccountBS = (IOrgOpenAccountBS) BSUtils
          .getBusinessService("orgOpenAccountBS", this, mapping
              .getModuleConfig());
      SecurityInfo securityInfo = (SecurityInfo) request.getSession().getAttribute(
          "SecurityInfo");
      List failList = orgOpenAccountBS.saveOrgAgentList(orgAgentImportList, securityInfo);
      messages = new ActionMessages();
      messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("导入成功！",
          false));
      saveErrors(request, messages);
    }catch (BusinessException be) {
      be.printStackTrace();
      messages = new ActionMessages();
      messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(be
          .getLocalizedMessage(), false));
      saveErrors(request, messages);
    }catch (Exception e) {
      e.printStackTrace();
      messages = new ActionMessages();
      messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("导入失败！",
          false));
      saveErrors(request, messages);
    }
    return mapping.findForward("organization_open_show");
  }

}
