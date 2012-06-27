package org.xpup.hafmis.sendmail.sendmial.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.xpup.common.exception.BusinessException;
import org.xpup.common.util.BSUtils;
import org.xpup.hafmis.sendmail.sendmial.bsinterface.IMailinfoBS;
import org.xpup.hafmis.sendmail.sendmial.form.MailinfoAF;

public class MailinfoTaShowAC extends Action {
  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response){
    ActionMessages messages = null;
    MailinfoAF mailinfoAF=new MailinfoAF();
    try{
    
    //查看是否有正在使用的邮件人的信息，如果有就显示出来
    IMailinfoBS mailinfoBS = (IMailinfoBS) BSUtils.getBusinessService("mailinfoBS", this,
        mapping.getModuleConfig());
    mailinfoAF=mailinfoBS.queryMailinfo();
  } catch (BusinessException bex) {
    messages = new ActionMessages();
    messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(bex
        .getMessage(), false));
    saveErrors(request, messages);
  } catch (Exception e) {
    e.printStackTrace();
  }
    request.setAttribute("mailinfoAF", mailinfoAF);
    return mapping.findForward("to_mailinfo_show");
  }
}