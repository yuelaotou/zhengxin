package org.xpup.hafmis.syscollection.accounthandle.ratemng.action;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.actions.LookupDispatchAction;
import org.xpup.common.exception.BusinessException;
import org.xpup.common.util.BSUtils;
import org.xpup.common.util.Pagination;
import org.xpup.hafmis.common.form.IdAF;
import org.xpup.hafmis.demo.bsinterface.IDemoBS;
import org.xpup.hafmis.demo.domain.entity.Demo;
import org.xpup.hafmis.demo.form.DemoAddAF;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.syscollection.accounthandle.ratemng.bsinterface.IRatemngBS;
import org.xpup.hafmis.syscollection.accounthandle.ratemng.form.RatemngAF;
import org.xpup.hafmis.syscollection.common.domain.entity.Emp;
import org.xpup.hafmis.syscollection.common.domain.entity.HafInterestRate;
import org.xpup.hafmis.syscollection.common.domain.entity.TranInHead;
import org.xpup.hafmis.syscollection.common.domain.entity.TranInOrg;
import org.xpup.hafmis.syscollection.common.domain.entity.TranInTail;
import org.xpup.hafmis.syscollection.common.domain.entity.TranOutOrg;
import org.xpup.hafmis.syscollection.tranmng.tranin.bsinterface.ITraninBS;
import org.xpup.hafmis.syscollection.tranmng.tranin.form.TraninAddAF;

public class AddRatemngMaintainAC extends LookupDispatchAction {
  protected Map getKeyMethodMap() {
    Map map = new HashMap();
    map.put("button.add", "add");
    map.put("button.back", "back");
    return map;
  }

  public ActionForward back(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response) {
    return mapping.findForward("showRatemngListAC");
  }

  public ActionForward add(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws BusinessException {
    ActionMessages messages = null;
    try {
      RatemngAF ratemngAF=(RatemngAF)form;
      SecurityInfo securityInfo=(SecurityInfo)request.getSession().getAttribute("SecurityInfo");
      String bizDate=securityInfo.getUserInfo().getBizDate();
      HafInterestRate hafInterestRate=ratemngAF.getHafInterestRate();
      IRatemngBS ratemngBS = (IRatemngBS) BSUtils.getBusinessService("ratemngBS",
          this, mapping.getModuleConfig());
        ratemngBS.addRatemng_sy(hafInterestRate,bizDate,securityInfo);
        messages = new ActionMessages();
        messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("添加成功！",
            false));
        saveErrors(request, messages);
     }catch (BusinessException bex) {
       messages = new ActionMessages();
       messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(bex
           .getLocalizedMessage().toString(), false));
       saveErrors(request, messages);
     }
    catch (Exception e) {
      messages = new ActionMessages();
      messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("添加失败！",
          false));
      saveErrors(request, messages);
    }
    return mapping.findForward("showRatemngListAC");
  }
}