package org.xpup.hafmis.syscollection.paymng.personaddpay.action;

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
import org.xpup.hafmis.orgstrct.dto.OfficeDto;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.syscollection.common.domain.entity.Org;
import org.xpup.hafmis.syscollection.paymng.monthpay.bsinterface.IMonthpayBS;
import org.xpup.hafmis.syscollection.paymng.monthpay.form.MonthpayJYAF;
import org.xpup.hafmis.syscollection.paymng.orgaddpay.bsinterface.IOrgaddpayBS;
import org.xpup.hafmis.syscollection.paymng.personaddpay.bsinterface.IPersonAddPayBS;



public class MonthpayModifyAC extends LookupDispatchAction{
  protected Map getKeyMethodMap() {
    Map map = new HashMap();
    map.put("button.update", "update");
    return map;
  }

  public ActionForward update(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
    ActionMessages messages =null;
    try{
      MonthpayJYAF f = (MonthpayJYAF)form;
    SecurityInfo securityInfo=(SecurityInfo)request.getSession().getAttribute("SecurityInfo");
    IPersonAddPayBS personAddPayBS = (IPersonAddPayBS) BSUtils
    .getBusinessService("personAddPayBS", this, mapping.getModuleConfig()); 
    personAddPayBS.updatePaymentInfo(f.getMonthpayHeadId(),f.getPayment_bank_name(),f.getPayment_bank_acc(), securityInfo);
    }
    catch(Exception e){
      e.printStackTrace();
      messages=new ActionMessages();
      messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("É¾³ýÊ§°Ü!",false));
      saveErrors(request, messages);
    }
    return mapping.findForward("show_paymentList");
  }

}
