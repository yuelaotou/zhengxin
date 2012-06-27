package org.xpup.hafmis.syscollection.accounthandle.monthclear.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.xpup.common.util.BSUtils;
import org.xpup.hafmis.common.form.IdAF;
import org.xpup.hafmis.common.util.BusiTools;
import org.xpup.hafmis.common.util.bizlog.BusiLogConst;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.syscollection.accounthandle.monthclear.form.MonthclearAF;
import org.xpup.security.wsf.bizsrvc.IUserControlBS;

public class MonthclearDoAC extends Action{
  public ActionForward execute(ActionMapping arg0, ActionForm arg1, HttpServletRequest arg2, HttpServletResponse arg3) throws Exception {
    ActionMessages messages=null;
    try{
      IdAF lform=(IdAF)arg1;
    SecurityInfo securityInfo = (SecurityInfo)arg2.getSession()
    .getAttribute("SecurityInfo");
    IUserControlBS securityControlBS = (IUserControlBS) BSUtils
    .getBusinessService("securityControlBS", this, arg0.getModuleConfig());
    String[] rowArray = lform.getRowArray();
//    String date=lform.getDate();
//    String bizDate=BusiTools.addMonth(date,1)+"01";
//    securityControlBS.updateBizDate(securityInfo.getUserName(), Integer.toString(BusiLogConst.OP_SYSTEM_TYPE_COLLECTION), bizDate);
    String temp = securityControlBS.checkClear(rowArray,securityInfo);
    String str = arg2.getParameter("temp");
    if(str.equals("1")){
      temp = "1";
    }
    if(temp.equals("3")){
      messages=new ActionMessages();
      messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("3",
          false));
      saveErrors(arg2, messages);
    }else if(temp.equals("0")){
      securityControlBS.updateBizDateMonth_jj(rowArray);
      messages=new ActionMessages();
      messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("0",
          false));
      saveErrors(arg2, messages);      
    }else{
      messages=new ActionMessages();
      messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(temp,
          false));
      saveErrors(arg2, messages);
    }
    }catch(Exception e){    
      e.printStackTrace();
      messages=new ActionMessages();
      messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("1",
          false));
      saveErrors(arg2, messages);
    }
    return arg0.findForward("monthclearShowAC");
  }
}
