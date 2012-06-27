package org.xpup.hafmis.syscollection.paymng.agent.action;

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
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.syscollection.paymng.agent.bsinterface.IAgentBS;
import org.xpup.hafmis.syscollection.paymng.agent.form.AgentImportAF;
import org.xpup.common.exception.BusinessException;
import org.xpup.common.util.BSUtils;
import org.xpup.common.util.imp.Factory;

/**
 * Copy Right Information : 代扣导入Action Goldsoft Project : AgentImportAC
 * 
 * @Version : v1.0
 * @author : 付云峰
 * @Date : 2007.12.17
 */
public class AgentImportAC extends Action {

  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    ActionMessages messages = null;
    try {
      AgentImportAF af = (AgentImportAF) form;
      FormFile file = af.getTheFile();
      String fileType = file.getFileName().substring(
          file.getFileName().lastIndexOf(".") + 1);

      if (file.toString().length() < 1) {
        return (mapping.findForward("文件无后缀"));
      } else if (!fileType.equals("xls")) {
        return (mapping.findForward("文件后缀不是xls格式"));
      }
      ServletContext context = request.getSession().getServletContext();
      String jasperSrc = context.getRealPath("/impXml/" + "agent_imp.xml");
      Factory faxtory = new Factory();
      File xmlfile = new File(jasperSrc);
      Map info = null;
      info = faxtory.getInfomation(xmlfile, file.getInputStream(),
          "org.xpup.hafmis.syscollection.paymng.agent.dto.");
      List agentImportTitle = (List) info.get("AgentImportTitle");
      List agentImport = (List) info.get("AgentImport");

      IAgentBS agentBS = (IAgentBS) BSUtils.getBusinessService("agentBS", this,
          mapping.getModuleConfig());
      SecurityInfo securityInfo = (SecurityInfo) request.getSession()
          .getAttribute("SecurityInfo");
      Object[] obj = agentBS.saveAgent(agentImportTitle, agentImport,
          securityInfo);
      request.getSession().setAttribute("agentDetailId", obj[0].toString());
      request.getSession().setAttribute("noteNum", obj[1].toString());
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
    return mapping.findForward("to_agentImpShowAC");
  }

}
