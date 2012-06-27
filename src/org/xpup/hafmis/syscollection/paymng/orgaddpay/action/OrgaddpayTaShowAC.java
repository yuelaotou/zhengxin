package org.xpup.hafmis.syscollection.paymng.orgaddpay.action;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

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
import org.xpup.common.util.Pagination;
import org.xpup.common.util.PaginationUtils;
import org.xpup.hafmis.common.util.BusiConst;
import org.xpup.hafmis.common.util.BusiTools;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.syscollection.common.domain.entity.Org;
import org.xpup.hafmis.syscollection.paymng.monthpay.bsinterface.IMonthpayBS;
import org.xpup.hafmis.syscollection.paymng.orgaddpay.bsinterface.IOrgaddpayBS;
import org.xpup.hafmis.syscollection.paymng.orgaddpay.form.OrgaddpayTaAF;

public class OrgaddpayTaShowAC extends Action {

  public static final String PAGINATION_KEY = "org.xpup.hafmis.syscollection.paymng.orgaddpay.action.OrgaddpayTaShowAC";

  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    ActionMessages messages = null;
    try {
      /**
       * 分页
       */
      SecurityInfo securityInfo = (SecurityInfo) request.getSession()
          .getAttribute("SecurityInfo");
      Pagination pagination = getPagination(PAGINATION_KEY, request);
      PaginationUtils.updatePagination(pagination, request);
      saveToken(request);
      IOrgaddpayBS orgaddpayBS = (IOrgaddpayBS) BSUtils.getBusinessService(
          "orgaddpayBS", this, mapping.getModuleConfig());

      IMonthpayBS monthpayBS = (IMonthpayBS) BSUtils.getBusinessService(
          "monthpayBS", this, mapping.getModuleConfig());

      OrgaddpayTaAF orgaddpayTaAF = new OrgaddpayTaAF();
      orgaddpayTaAF = orgaddpayBS.findOrgaddpay(pagination, securityInfo);
      List list = orgaddpayTaAF.getList();
      Org org = null;
      if (list.size() > 0 && list != null) {
        orgaddpayTaAF.setListCount("1");
      }
      String type = (String) request.getAttribute("type");
      if (type == null)
        type = "1";
      List banklist = new ArrayList();
      String receivables_orgname = ""; // 收款单位名称
      String receivables_bank_acc = ""; // 收款单位归集银行账号
      String receivables_bank_name = ""; // 收款单位归集银行名称

      String payment_orgname = ""; // 付款单位名称
      String payment_bank_acc = ""; // 付款单位开户银行账号
      String payment_bank_name = ""; // 付款单位开户银行名称

      BigDecimal overPay = new BigDecimal(0.00);
      if (orgaddpayTaAF.getOrgid() != null
          && !"".equals(orgaddpayTaAF.getOrgid())) {
        org = orgaddpayBS.findOrgInfo(new Integer(orgaddpayTaAF.getOrgid()),
            "2", securityInfo); // 查询挂账余额
        overPay = monthpayBS.queryOverPay(orgaddpayTaAF.getOrgid());
        banklist = monthpayBS.queryCollBankInfo(org.getOrgInfo()
            .getOfficecode(), org.getOrgInfo().getCollectionBankId());
        if (banklist.size() > 0) {
          for (int i = 0; i < banklist.size(); i++) {
            Object obj[] = null;
            obj = (Object[]) banklist.get(0);
            if (obj[0] != null) {
              receivables_orgname = obj[0].toString();
            }
            if (obj[2] != null) {
              receivables_bank_acc = obj[2].toString();
            }
            if (obj[1] != null) {
              receivables_bank_name = obj[1].toString();
            }
          }
        }
        if (org.getOrgInfo().getPayBank().getName() != null) {
          payment_bank_name = org.getOrgInfo().getPayBank().getName(); // 增加付款单位银行名称
        }
        if (org.getOrgInfo().getPayBank().getAccountNum() != null) {
          payment_bank_acc = org.getOrgInfo().getPayBank().getAccountNum();// 增加付款单位银行账号
        }
        if(org.getOrgInfo().getReserveaB()!=null && !org.getOrgInfo().getReserveaB().equals("")){
          payment_orgname = org.getOrgInfo().getReserveaB();
        }else{
          payment_orgname = org.getOrgInfo().getName();
        }
      }
      String payKind = (String) pagination.getQueryCriterions().get("payKind");
      if (orgaddpayTaAF.getPayKind() == null && "".equals(orgaddpayTaAF)) {
        if (payKind == null || "".equals(payKind)) {
          payKind = "2";
        }
      }
      orgaddpayTaAF.setType(type);
      orgaddpayTaAF.setReceivables_bank_acc(receivables_bank_acc);
      orgaddpayTaAF.setReceivables_bank_name(receivables_bank_name);
      orgaddpayTaAF.setReceivables_orgname(receivables_orgname);
      orgaddpayTaAF.setPayment_bank_acc(payment_bank_acc);
      orgaddpayTaAF.setPayment_bank_name(payment_bank_name);
      orgaddpayTaAF.setPayment_orgname(payment_orgname);
      orgaddpayTaAF.setOverPay(overPay);
      orgaddpayTaAF.setPay_kind((BusiTools
          .listBusiProperty(BusiConst.PAY_KIND_INFO)));
      orgaddpayTaAF.setPay_way((BusiTools
          .listBusiProperty(BusiConst.PAY_WAY_INFO)));
      orgaddpayTaAF.setPayKind(payKind);
      request.setAttribute("orgaddpayTaAF", orgaddpayTaAF);
      request.setAttribute("statetype", orgaddpayTaAF.getStatetype());
      pagination.getQueryCriterions().put("pageList", orgaddpayTaAF.getList());
      pagination.getQueryCriterions().put("orgaddpayTaAF", orgaddpayTaAF);
    } catch (BusinessException b) {
      OrgaddpayTaAF orgaddpayTaAF = new OrgaddpayTaAF();
      request.setAttribute("orgaddpayTaAF", orgaddpayTaAF);
      messages = new ActionMessages();
      messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(b
          .getMessage(), false));
      saveErrors(request, messages);
    } catch (Exception ex) {
      ex.printStackTrace();
    }

    return mapping.findForward("show_orgaddpay");
  }

  private Pagination getPagination(String paginationKey,
      HttpServletRequest request) {
    Pagination pagination = (Pagination) request.getSession().getAttribute(
        paginationKey);
    if (pagination == null) {
      pagination = new Pagination(0, 10, 1, " max(monthPaymentTail.id) ", "ASC",
          new HashMap(0));
      request.getSession().setAttribute(paginationKey, pagination);
    }

    return pagination;
  }
}
