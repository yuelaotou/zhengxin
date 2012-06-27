package org.xpup.hafmis.syscollection.paymng.monthpay.action;

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
import org.xpup.hafmis.syscollection.paymng.monthpay.bsinterface.IMonthpayBS;

public class MonthpayAutoOverPayAAC extends Action {

  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    response.setContentType("text/html;charset=UTF-8");
    response.setHeader("Cache-Control", "no-cache");
    ActionMessages messages =null;
    String text = "";
    boolean flag = true;
    String orgId = request.getParameter("orgId");
    try {
      
      IMonthpayBS monthpayBS = (IMonthpayBS) BSUtils
      .getBusinessService("monthpayBS", this, mapping.getModuleConfig());
      
      flag = monthpayBS.isOverPay(orgId);
      if (!flag) {
        throw new BusinessException("存在未入账的挂账业务，不能进行正常汇缴业务！");
      }
    }catch(BusinessException b){
      messages=new ActionMessages();
      messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(b.getMessage(),
          false));
      saveErrors(request, messages);
      request.getSession().removeAttribute(MonthpayTaShowAC.PAGINATION_KEY);
    } catch (Exception e) {
      e.printStackTrace();
    }
    text = "display1('"+flag+"');";
    response.getWriter().write(text); 
    response.getWriter().close();
    return null;
  }

}
