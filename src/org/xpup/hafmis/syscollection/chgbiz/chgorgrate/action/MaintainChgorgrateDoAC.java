package org.xpup.hafmis.syscollection.chgbiz.chgorgrate.action;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.actions.LookupDispatchAction;
import org.xpup.common.util.BSUtils;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.syscollection.chgbiz.chgorgrate.bsinterface.IChgorgrateBS;
import org.xpup.hafmis.syscollection.chgbiz.chgorgrate.form.ChgOrgRateDoAF;
import org.xpup.hafmis.syscollection.common.domain.entity.ChgOrgRate;

public class MaintainChgorgrateDoAC extends LookupDispatchAction {

  protected Map getKeyMethodMap() {
    Map map = new HashMap();
    map.put("button.sure", "save");
    map.put("button.edit", "update");
    map.put("button.use", "use");
    return map;
  }

  public ActionForward save(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    try{
      ChgOrgRateDoAF chgOrgRateDoAF = (ChgOrgRateDoAF) form;

      ChgOrgRate chgOrgRate=chgOrgRateDoAF.getChgOrgRate();
      
      HttpSession session = request.getSession();
      session.setAttribute("chgOrgRateDoAF_temp", chgOrgRateDoAF);
      
      IChgorgrateBS chgorgrateBS = (IChgorgrateBS) BSUtils
      .getBusinessService("chgorgrateBS", this, mapping.getModuleConfig());
      
      SecurityInfo securityInfo = (SecurityInfo) request.getSession()
          .getAttribute("SecurityInfo");
      
      //根据页面信息，进行数据录入
      BigDecimal newSumPay = chgorgrateBS.saveChgOrgRate(chgOrgRate,securityInfo);

      session.setAttribute("newSumPay", newSumPay);
    
    }catch(Exception e){
      e.printStackTrace();
    }
    return mapping.findForward("to_chgorgrateDo_save");
  }

  public ActionForward update(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    try{
      ChgOrgRateDoAF chgOrgRateDoAF = (ChgOrgRateDoAF) form;
      HttpSession session = request.getSession();
      
      ChgOrgRate chgOrgRate=chgOrgRateDoAF.getChgOrgRate();
      session.setAttribute("chgOrgRateDoAF_temp", chgOrgRateDoAF);
      
      IChgorgrateBS chgorgrateBS = (IChgorgrateBS) BSUtils
      .getBusinessService("chgorgrateBS", this, mapping.getModuleConfig());

      SecurityInfo securityInfo = (SecurityInfo) request.getSession()
          .getAttribute("SecurityInfo");
      
      //根据页面信息，进行数据录入
      BigDecimal newSumPay = chgorgrateBS.updateChgOrgRate(chgOrgRate,securityInfo);

      session.setAttribute("newSumPay", newSumPay);
      
    }catch(Exception e){
      e.printStackTrace();
    }
    return mapping.findForward("to_chgorgrateDo_save");
  }

  public ActionForward use(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    ActionMessages messages =null;
    try{    
      ChgOrgRateDoAF chgOrgRateDoAF = (ChgOrgRateDoAF) form;
      
      ChgOrgRate chgOrgRate=chgOrgRateDoAF.getChgOrgRate();
      
      IChgorgrateBS chgorgrateBS = (IChgorgrateBS) BSUtils
      .getBusinessService("chgorgrateBS", this, mapping.getModuleConfig());

      SecurityInfo securityInfo = (SecurityInfo) request.getSession()
          .getAttribute("SecurityInfo");
      
      //进行数据启用
      chgorgrateBS.useChgOrgRate(chgOrgRate,securityInfo);
      
    }catch(Exception e){
      e.printStackTrace();
      messages=new ActionMessages();
      messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("启用失败！",
          false));
      saveErrors(request, messages);
    }
    return mapping.findForward("to_chgorgrateDo_use");
  }

}
