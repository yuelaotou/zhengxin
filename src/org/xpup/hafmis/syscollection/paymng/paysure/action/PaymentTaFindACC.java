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
 * @author ÓÚÇì·á
 *2007-06-30
 */
public class PaymentTaFindACC extends Action{

  public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
    // TODO Auto-generated method stub
    response.setContentType("text/html;charset=UTF-8");
    response.setHeader("Cache-Control", "no-cache");
    
    try {
      String orgId =(String)request.getParameter("orgId");
      String orgName =(String)request.getParameter("orgName");
      String noteNum = (String)request.getParameter("noteNum");
      String docNum = (String)request.getParameter("docNum");
      BigDecimal payMoney = new BigDecimal(request.getParameter("payMoney"));
      String settDate=(String)request.getParameter("settDate");
      Integer payStatus=new Integer(request.getParameter("payStatus"));
      String payType= (String)(request.getParameter("payType"));      
  
      IPaymentHeadBS paymentHeadBS = (IPaymentHeadBS) BSUtils
      .getBusinessService("paymentHeadBS", this, mapping.getModuleConfig());
      PaymentHead paymentHead=null;
      if(orgId!=null&&!orgId.equals("")){
        paymentHead=paymentHeadBS.findPaymentListById(new Integer(orgId));
      }
      if(paymentHead!=null){
        orgName=paymentHead.getOrg().getOrgInfo().getName();
      }
      String text=null;
      String paginationKey = getPaginationKey();
      Pagination pagination= (Pagination) request.getSession().getAttribute(paginationKey);
      pagination.getQueryCriterions().put("PaymentHead.orgId", orgId);
      pagination.getQueryCriterions().put("PaymentHead.orgName", orgName);
      pagination.getQueryCriterions().put("PaymentHead.noteNum", noteNum);
      pagination.getQueryCriterions().put("PaymentHead.docNum", docNum);
      pagination.getQueryCriterions().put("PaymentHead.payMoney", payMoney);
      pagination.getQueryCriterions().put("PaymentHead.settDate", settDate);
      pagination.getQueryCriterions().put("PaymentHead.payStatus", payStatus);
      pagination.getQueryCriterions().put("PaymentHead.payType", payType);
      text="displays('"+orgId+"','"+orgName+"')";
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
