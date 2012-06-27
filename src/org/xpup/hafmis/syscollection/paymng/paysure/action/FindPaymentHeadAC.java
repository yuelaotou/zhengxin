package org.xpup.hafmis.syscollection.paymng.paysure.action;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.xpup.common.util.Pagination;
import org.xpup.common.util.PaginationUtils;
import org.xpup.hafmis.syscollection.paymng.paysure.form.PaymentAF;

/**
 * 
 * @author ÓÚÇì·á
 *
 */
public class FindPaymentHeadAC extends Action{

  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {

    PaymentAF f = (PaymentAF) form;
    HashMap criterions = makeCriterionsMap(f);
    Pagination pagination = new Pagination(0, 10, 1, "paymentHead.id", "DESC",
        criterions);
//    PaginationUtils.updatePagination(pagination, request);
    String paginationKey = getPaginationKey();
    request.getSession().setAttribute(paginationKey, pagination);
    modifyPagination(pagination);
    f.reset(mapping, request);
    return mapping.findForward(getForword());
  }

  protected String getForword() {
    return "to_ShowPaymentHeadAC";
  }

  protected String getPaginationKey() {
    return ShowPaymentHeadAC.PAGINATION_KEY;
  }

  protected HashMap makeCriterionsMap(PaymentAF form) {
    HashMap m = new HashMap();
    String key = "condition";
    m.put("condition",key);
    String orgId = form.getOrgId().trim();
    if (!(orgId == null || "".equals(orgId))) {
      try {
        m.put("orgId", orgId);
      } catch (NumberFormatException e) {
        m.put("orgId", new Integer(-10000));
      }
    }

    String orgName = form.getOrgName().trim();
    if (!(orgName == null || orgName.length() == 0))
      m.put("orgName", form.getOrgName());

    String noteNum = form.getNoteNum().trim();
    if (!(noteNum == null || noteNum.length() == 0))
      m.put("noteNum", form.getNoteNum());

    String docNum = form.getDocNum().trim();
    if (!(docNum == null || docNum.length() == 0))
      m.put("docNum", form.getDocNum());

    String payMoney = form.getPayMoney().trim();
    if (!(payMoney == null || payMoney.length() == 0))
      m.put("payMoney", form.getPayMoney());

    String settDate = form.getSettDate().trim();
    if (!(settDate == null || settDate.length() == 0))
      m.put("settDate", form.getSettDate());

    Integer payStatus = form.getPayStatus();
    if (!(payStatus == null || payStatus.longValue() == 0)){
      m.put("payStatus", form.getPayStatus());
    }else{
      m.put("payStatus", null);
    }

    String payType = form.getPayType();
    if (!(payType == null || payType.length() == 0))
      m.put("payType", form.getPayType());

    return m;
  }

  protected void modifyPagination(Pagination pagination) {
  }
}
