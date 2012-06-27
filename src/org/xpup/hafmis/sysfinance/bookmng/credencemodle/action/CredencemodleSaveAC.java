package org.xpup.hafmis.sysfinance.bookmng.credencemodle.action;



import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.xpup.common.util.BSUtils;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.sysfinance.bookmng.credencemodle.bsinterface.ICredencemodleBS;
import org.xpup.hafmis.sysfinance.bookmng.credencemodle.form.CredencemodleAF;

public class CredencemodleSaveAC extends Action {

  public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
    // TODO Auto-generated method stub
    try {
      CredencemodleAF credencemodleAF=(CredencemodleAF)form;
      SecurityInfo securityInfo = (SecurityInfo) request.getSession()
      .getAttribute("SecurityInfo");
      Integer count=new Integer(0);
      String subjectCode="";
      subjectCode = (String) request.getParameter("subjectCode");     
      if (subjectCode != null && !"".equals(subjectCode.trim())) {
        subjectCode = (String) request.getParameter("subjectCode");       
      }
      String subjectName="";
      subjectName = (String) request.getParameter("subjectName");     
      if (subjectName != null && !"".equals(subjectName.trim())) {
        subjectName = (String) request.getParameter("subjectName");       
      }
      String bizType="";
      bizType = (String) request.getParameter("bizType");     
      if (bizType != null && !"".equals(bizType.trim())) {
        bizType = (String) request.getParameter("bizType");       
      }
      String moneyType="";
      moneyType = (String) request.getParameter("moneyType");     
      if (moneyType != null && !"".equals(moneyType.trim())) {
        moneyType = (String) request.getParameter("moneyType");       
      }
      String subjectDirection="";
      subjectDirection = credencemodleAF.getSubjectDirection3();     
      if (subjectDirection != null && !"".equals(subjectDirection.trim())) {
        subjectDirection = credencemodleAF.getSubjectDirection3();    
      }
      String summary="";
      summary = (String) request.getParameter("summray");     
      if (summary != null && !"".equals(summary.trim())) {
        summary = (String) request.getParameter("summray");       
      }
      ICredencemodleBS credencemodleBS = (ICredencemodleBS) BSUtils
      .getBusinessService("credencemodleBS", this, mapping
          .getModuleConfig());
      if("6".equals(bizType)||"7".equals(bizType)){
        count=credencemodleBS.findCredencemodleInfoExist(subjectCode,"8",null,securityInfo,null);
        if(1==count.intValue()){
          ActionMessages messages = null;
          messages = new ActionMessages();
          messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("不允许对该科目代码进行设置！", false));
          saveErrors(request, messages);
          return mapping.findForward("to_show_credencemodle");
        }
      }
      if("8".equals(bizType)){
        count=credencemodleBS.findCredencemodleInfoExist(subjectCode,"6",null,securityInfo,null);
        if(1==count.intValue()){
          ActionMessages messages = null;
          messages = new ActionMessages();
          messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("不允许对该科目代码进行设置！", false));
          saveErrors(request, messages);
          return mapping.findForward("to_show_credencemodle");
        }
        count=credencemodleBS.findCredencemodleInfoExist(subjectCode,"7",null,securityInfo,null);
        if(1==count.intValue()){
          ActionMessages messages = null;
          messages = new ActionMessages();
          messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("不允许对该科目代码进行设置！", false));
          saveErrors(request, messages);
          return mapping.findForward("to_show_credencemodle");
        }
      }
      count=credencemodleBS.findCredencemodleInfoExist(subjectCode,bizType,moneyType,securityInfo,subjectDirection);
      if("0".equals(count.toString())){
        credencemodleBS.saveCredencemodle(subjectCode,bizType,moneyType,subjectDirection,summary,securityInfo);
      }else{
        credencemodleBS.updateCredencemodle(subjectCode,bizType,moneyType,subjectDirection,summary,securityInfo);
      }    
    } catch (Exception e) {
      // TODO: handle exception
      e.printStackTrace();
    }
    return mapping.findForward("to_show_credencemodle");
  }
  
}
