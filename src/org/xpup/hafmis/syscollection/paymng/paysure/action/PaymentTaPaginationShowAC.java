package org.xpup.hafmis.syscollection.paymng.paysure.action;

import java.math.BigDecimal;
import java.util.HashMap;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.xpup.common.util.Pagination;
import org.xpup.hafmis.syscollection.paymng.paysure.form.PaymentAF;

/**
 * @author ÓÚÇì·á 2006-07-02
 */
public class PaymentTaPaginationShowAC extends Action {

  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {

    PaymentAF f = (PaymentAF) form;
    HashMap criterions = makeCriterionsMap(f);
    Pagination pagination = new Pagination(0, 10, 1, "paymentHead.id", "DESC",
        criterions);
    String paginationKey = getPaginationKey();
    request.getSession().setAttribute(paginationKey, pagination);
    modifyPagination(pagination);
    f.reset(mapping, request);
    return mapping.findForward(getForword());
  }

  protected String getForword() {
    return "to_paymentTaFindAC";
  }

  protected String getPaginationKey() {
    return PaymentTaFindAC.PAGINATION_KEY;
  }

  protected HashMap makeCriterionsMap(PaymentAF form) {
    HashMap m = new HashMap();
    String orgId = form.getOrgId();
    if (!(orgId == null || "".equals(orgId))) {
      try {
        m.put("orgId", orgId);
      } catch (NumberFormatException e) {
        m.put("orgId", new Integer(-10000));
      }
    }

    String orgName = form.getOrgName();
    if (!(orgName == null || orgName.length() == 0))
      m.put("orgName", form.getOrgName());

    String noteNum = form.getNoteNum();
    if (!(noteNum == null || noteNum.length() == 0))
      m.put("noteNum", form.getNoteNum());

    String docNum = form.getDocNum();
    if (!(docNum == null || docNum.length() == 0))
      m.put("docNum", form.getDocNum());

    String payMoney = form.getPayMoney();


    if (!(payMoney == null || payMoney.length() == 0))
      m.put("payMoney", form.getPayMoney());

    String settDate = form.getSettDate();
    if (!(settDate == null || settDate.length() == 0))
      m.put("settDate", form.getSettDate());
    
    String settDate1 = form.getSettDate1();
    if (!(settDate1 == null || settDate1.length() == 0))
      m.put("settDate1", form.getSettDate1());
    
    Integer payStatus = form.getPayStatus();
    if (!(payStatus == null || payStatus.longValue() == 0))
      m.put("payStatus", form.getPayStatus());

    String payType = form.getPayType();
    if (!(payType == null || payType.length() == 0))
      m.put("payType", form.getPayType());

    return m;
  }

  protected void modifyPagination(Pagination pagination) {
  }
}
