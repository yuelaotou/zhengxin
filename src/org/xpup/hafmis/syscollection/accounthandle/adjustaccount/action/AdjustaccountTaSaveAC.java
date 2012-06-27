package org.xpup.hafmis.syscollection.accounthandle.adjustaccount.action;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.actions.LookupDispatchAction;
import org.xpup.common.util.BSUtils;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.syscollection.accounthandle.adjustaccount.bsinterface.IAdjustAccountBS;
import org.xpup.hafmis.syscollection.accounthandle.adjustaccount.form.AdjustaccountNewAF;
import org.xpup.hafmis.syscollection.common.domain.entity.AdjustWrongAccountHead;
import org.xpup.hafmis.syscollection.common.domain.entity.AdjustWrongAccountTail;
import org.xpup.hafmis.syscollection.common.domain.entity.Emp;



public class AdjustaccountTaSaveAC extends LookupDispatchAction{
  protected Map getKeyMethodMap() {
    Map map = new HashMap();
    map.put("button.add", "add");
    map.put("button.back", "back");
    return map;
  }
  public ActionForward add(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
    ActionMessages messages =null;
    try{
      messages=new ActionMessages();
//      if(!isTokenValid(request))
//      {
//        messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("请不要刷新！",
//            false));
//        saveErrors(request, messages);
//      }else{
        SecurityInfo securityInfo = (SecurityInfo) request.getSession()
        .getAttribute("SecurityInfo");
        if (securityInfo == null) { 
        } else {
        resetToken(request);
        String orgId=(String)request.getSession(false).getAttribute("findadjustWrongAccountHead_orgId");

        Serializable orgid=(Serializable)request.getSession().getAttribute("ORGID");
        if(orgId == null && orgid != null){
          orgId=orgid.toString();
        }
        AdjustaccountNewAF adjustaccountNewAF=(AdjustaccountNewAF)form;
        String noteNum = (String)request.getSession().getAttribute("noteNumber");
        AdjustWrongAccountTail adjustWrongAccountTail=adjustaccountNewAF.getAdjustWrongAccountTail();
        IAdjustAccountBS adjustAccountBS = (IAdjustAccountBS) BSUtils
        .getBusinessService("adjustaccountBS", this, mapping.getModuleConfig());
        AdjustWrongAccountHead adjustWrongAccountHead = adjustAccountBS.findAdjustWrongAccountHeadByOrgId(orgId,securityInfo);
        Emp emp1=adjustAccountBS.checkEmp(orgId, adjustWrongAccountTail.getEmpId()+"");//测试职工是否在该单位下
        if(adjustWrongAccountHead==null){//没哟头表
          adjustAccountBS.insertAdjustWrongAccountHeadAndTail(adjustWrongAccountTail,adjustWrongAccountTail.getEmpId(),
              orgId,adjustaccountNewAF.getTypelist(),securityInfo,noteNum);
        }
        else {//有头表
          adjustAccountBS.insertAdjustWrongAccountTail(adjustWrongAccountTail,adjustWrongAccountTail.getEmpId(),adjustWrongAccountHead,securityInfo,orgId);
        }
//          messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("添加成功！",
//            false));
//        saveErrors(request, messages);
      }request.getSession(false).setAttribute("findadjustWrongAccountHead_type", "2");
//      }
    }
    catch(Exception bex){
      request.getSession(false).setAttribute("findadjustWrongAccountHead_type", "3");
      messages=new ActionMessages();
      messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("添加失败！",
          false)); 
      saveErrors(request, messages);
      return mapping.findForward("showAdjustaccountAC");
    } 
    return mapping.findForward("showAdjustaccountAC");
  }
  public ActionForward back(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
    return mapping.findForward("showAdjustaccountAC");
  }
}
