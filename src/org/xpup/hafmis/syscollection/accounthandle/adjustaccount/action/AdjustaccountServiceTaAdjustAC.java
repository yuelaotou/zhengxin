package org.xpup.hafmis.syscollection.accounthandle.adjustaccount.action;
import java.io.Serializable;
import java.math.BigDecimal;

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
import org.xpup.hafmis.common.util.BusiConst;
import org.xpup.hafmis.common.util.BusiTools;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.syscollection.accounthandle.adjustaccount.bsinterface.IAdjustAccountBS;
import org.xpup.hafmis.syscollection.common.domain.entity.AdjustWrongAccountHead;
import org.xpup.hafmis.syscollection.common.domain.entity.OrgHAFAccountFlow;



public class AdjustaccountServiceTaAdjustAC extends Action{
  public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
    ActionMessages messages =null;
    String id=(String)request.getParameter("id");
    IAdjustAccountBS adjustaccountBS = (IAdjustAccountBS) BSUtils
    .getBusinessService("adjustaccountBS", this, mapping.getModuleConfig());   
    SecurityInfo securityInfo = (SecurityInfo) request.getSession()
    .getAttribute("SecurityInfo");
    try{
      AdjustWrongAccountHead adjustWrongAccountHead1=adjustaccountBS.findOrgHAFAccountFlowById(id);
      Serializable bis_id=(Serializable)adjustWrongAccountHead1.getId();;
      BigDecimal Status=adjustWrongAccountHead1.getAdjustStatus();
     String bizType= adjustWrongAccountHead1.getBizType();
     String temp_bizType=BusiTools.getBusiKey_WL(bizType,BusiConst.CHGBUSINESSTYPE);
     String bizDate= adjustWrongAccountHead1.getBizDate();
      if(adjustWrongAccountHead1.getBizDocNum()==null || adjustWrongAccountHead1.getBizDocNum().equals("")){//该单位的凭证号为空的
        adjustaccountBS.updateAdjustWrongAccountHeadByID(adjustWrongAccountHead1.getId().toString(),securityInfo);
        OrgHAFAccountFlow orgHAFAccountFlow=adjustaccountBS.findOrgHAFAccountFlowByCriterions1(bis_id.toString(), Status,temp_bizType,bizDate);
        adjustaccountBS.deleteOrgHAFAccountFlowAndEmpByHID(orgHAFAccountFlow.getId().toString(),securityInfo,bis_id.toString());
      } 
      else{//-该单位的凭证号  不为空的 删除314记录
        adjustaccountBS.deleteAdjustWrongAccountAllByHID(adjustWrongAccountHead1,securityInfo);
        OrgHAFAccountFlow orgHAFAccountFlow=adjustaccountBS.findOrgHAFAccountFlowByCriterions1(bis_id.toString(), Status,temp_bizType,bizDate);
        adjustaccountBS.deleteOrgHAFAccountFlowAndEmpByHID(orgHAFAccountFlow.getId().toString(),securityInfo,bis_id.toString());
      }
      messages=new ActionMessages();
      messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("操作成功！",
          false));
      saveErrors(request, messages);
    }
    catch(BusinessException bex){
    messages=new ActionMessages();
    messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("操作失败！",
        false));
    saveErrors(request, messages);
    } 
    return mapping.findForward("showAdjustaccountServiceAC");
  }
}
