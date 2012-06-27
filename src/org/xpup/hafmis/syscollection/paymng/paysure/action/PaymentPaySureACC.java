package org.xpup.hafmis.syscollection.paymng.paysure.action;

import java.math.BigDecimal;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.xpup.common.util.BSUtils;
import org.xpup.common.util.Pagination;
import org.xpup.hafmis.syscollection.common.domain.entity.PaymentHead;
import org.xpup.hafmis.syscollection.paymng.paysure.bsinterface.IPaymentHeadBS;

/**
 * 
 * @author ั๎นโ
 *20090319
 */
public class PaymentPaySureACC extends Action{

  public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
    response.setContentType("text/html;charset=UTF-8");
    response.setHeader("Cache-Control", "no-cache");
    try {
      String text = "";
      String id =(String)request.getParameter("id");
      IPaymentHeadBS paymentHeadBS = (IPaymentHeadBS) BSUtils
      .getBusinessService("paymentHeadBS", this, mapping.getModuleConfig());
      String returnStr = paymentHeadBS.findPayHeadIdInAA201AA202AA204(id);
      if(returnStr.equals("yes")){
        text="display_yes()";
      }else{
        text="display_no()";
      }
      response.getWriter().write(text); 
      response.getWriter().close();
    } catch (Exception e) {
      e.printStackTrace();
    }
    return null;
  }
  protected String getPaginationKey() {
    return PaymentTaFindAC.PAGINATION_KEY;
 }
}
