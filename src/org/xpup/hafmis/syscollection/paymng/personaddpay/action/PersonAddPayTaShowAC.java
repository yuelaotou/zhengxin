package org.xpup.hafmis.syscollection.paymng.personaddpay.action;

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
import org.xpup.hafmis.syscollection.common.domain.entity.AddPayTail;
import org.xpup.hafmis.syscollection.common.domain.entity.EmpAddPay;
import org.xpup.hafmis.syscollection.common.domain.entity.Org;
import org.xpup.hafmis.syscollection.paymng.monthpay.bsinterface.IMonthpayBS;
import org.xpup.hafmis.syscollection.paymng.orgaddpay.form.OrgaddpayTaAF;
import org.xpup.hafmis.syscollection.paymng.personaddpay.bsinterface.IPersonAddPayBS;
import org.xpup.hafmis.syscollection.paymng.personaddpay.dto.PersonAddPayDTO;
import org.xpup.hafmis.syscollection.paymng.personaddpay.form.PersonAddPayAF;

/**
 * @author 卢钢 2007-6-28
 */
public class PersonAddPayTaShowAC extends Action {

  public static final String PAGINATION_KEY = "org.xpup.hafmis.syscollection.paymng.personaddpay.action.PersonAddPayTaShowAC";

  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    ActionMessages messages = null;
    try {
      String importEmp = (String) request.getAttribute("importEmp");
      Pagination pagination = getPagination(PAGINATION_KEY, request);
      PaginationUtils.updatePagination(pagination, request);
      saveToken(request);
      String orgId = (String) pagination.getQueryCriterions().get("orgId");
      if ("".equals(orgId) || orgId == null) {
        orgId = (String) request.getAttribute("orgId");
      }
      if ((orgId != "") && (orgId != null)) {
        pagination.getQueryCriterions().put("orgId", orgId);
      }

      String unitName = "";
      String noteNum = "";

      String receivables_orgname = ""; // 收款单位名称
      String receivables_bank_acc = ""; // 收款单位归集银行账号
      String receivables_bank_name = ""; // 收款单位归集银行名称

      String payment_orgname = ""; // 付款单位名称
      String payment_bank_acc = ""; // 付款单位开户银行账号
      String payment_bank_name = ""; // 付款单位开户银行名称
      String payKind = (String) pagination.getQueryCriterions().get("payKind");
      String payWay = (String) pagination.getQueryCriterions().get("payWay");
      BigDecimal overPay = new BigDecimal(0.00);
      IPersonAddPayBS personAddPayBS = (IPersonAddPayBS) BSUtils
          .getBusinessService("personAddPayBS", this, mapping.getModuleConfig());

      IMonthpayBS monthpayBS = (IMonthpayBS) BSUtils.getBusinessService(
          "monthpayBS", this, mapping.getModuleConfig());

      SecurityInfo securityInfo = (SecurityInfo) request.getSession()
          .getAttribute("SecurityInfo");
      List paymentHead = new ArrayList();
      PersonAddPayAF f = new PersonAddPayAF();
      Org org = null;
      if ((orgId != "") && (orgId != null)) {
        paymentHead = personAddPayBS.queryPersonAddPay(pagination);
        try {
          org = personAddPayBS.findOrgById(new Integer(orgId), securityInfo);
        } catch (BusinessException b) {
          PersonAddPayAF personAddPayAF = new PersonAddPayAF();
          request.getSession().setAttribute("personAddPayAF", personAddPayAF);
          messages = new ActionMessages();
          messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(b
              .getMessage(), false));
          saveErrors(request, messages);
        }

        List banklist = new ArrayList();
        banklist = monthpayBS.queryCollBankInfo(org.getOrgInfo()
            .getOfficecode(), org.getOrgInfo().getCollectionBankId());
        // 查询挂账余额
        overPay = monthpayBS.queryOverPay(orgId);
        if (banklist.size() > 0) {
          for (int i = 0; i < banklist.size(); i++) {
            Object obj[] = null;
            obj = (Object[]) banklist.get(0);

            // receivables_orgname = obj[0].toString();
            // receivables_bank_acc = obj[2].toString();
            // receivables_bank_name = obj[1].toString();
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

      }
      EmpAddPay empAddPay = null;
      if (paymentHead.size() > 0) {
        AddPayTail addPayTail = (AddPayTail) paymentHead.get(0);
        pagination.getQueryCriterions().put("paymentHeadId",
            addPayTail.getPaymentHead().getId().toString());
        String headId = addPayTail.getPaymentHead().getId().toString();
        empAddPay = personAddPayBS.findEmpAddPayInfo(headId);
        noteNum = empAddPay.getNoteNum();
        f.setReceivables_bank_acc(empAddPay.getReceivables_bank_acc());
        f.setReceivables_bank_name(empAddPay.getReceivables_bank_name());
        f.setReceivables_orgname(receivables_orgname);
        f.setPayment_bank_acc(empAddPay.getPayment_bank_acc());
        f.setPayment_bank_name(empAddPay.getPayment_bank_name());
        f.setPayment_orgname(org.getOrgInfo().getName());
        f.setPayWay(empAddPay.getPay_way());
        f.setPayKind(empAddPay.getPay_kind());
        // f.setBizDate(empAddPay.getSettDate());
      }
      List personAddPayList = personAddPayBS
          .findPersonAddPayListByCriterions(pagination);
      List list2 = personAddPayBS.findPersonAddPayPrintList(pagination);
      PersonAddPayAF personAddPayAF = new PersonAddPayAF();

      // 根据提取状态判断单位版按钮
      String statetype = new String();
      if (personAddPayList.size() > 0) {
        PersonAddPayDTO personAddPayDTO = (PersonAddPayDTO) personAddPayList
            .get(0);
        statetype = personAddPayDTO.getTempPickType();

      }
      f.setPersonAddPayList(list2);
      List list = personAddPayBS.findPersonAddPayList(pagination);
      BigDecimal orgpay = new BigDecimal(0.00);
      BigDecimal emppay = new BigDecimal(0.00);
      BigDecimal paysum = new BigDecimal(0.00);
      for (int i = 0; i < list.size(); i++) {
        AddPayTail addPayTail = (AddPayTail) list.get(i);
        orgpay = orgpay.add(addPayTail.getOrgAddMoney());
        emppay = emppay.add(addPayTail.getEmpAddMoney());
        paysum = paysum.add(addPayTail.getOrgAddMoney().add(
            addPayTail.getEmpAddMoney()));
      }
      personAddPayAF.setEmpPaySum(emppay);
      personAddPayAF.setOrgPaySum(orgpay);
      personAddPayAF.setPaySum(paysum);
      personAddPayAF.setOrgId(orgId);
      personAddPayAF.setUnitName(unitName);
      personAddPayAF.setDocNumber(noteNum);
      personAddPayAF.setPersonSum(new Integer(pagination.getNrOfElements()));
      if ((orgId != "") && (orgId != null)) {
        // Org org = personAddPayBS.findOrgById(new Integer(orgId),
        // securityInfo);
        if (org != null) {
          unitName = org.getOrgInfo().getName();
          personAddPayAF.setUnitName(unitName);
          personAddPayAF.setOrgId(BusiTools.convertSixNumber(org.getId()
              .toString()));
          //
          // List banklist = new ArrayList();
          // banklist = monthpayBS.queryCollBankInfo(org.getOrgInfo()
          // .getOfficecode(), org.getOrgInfo().getCollectionBankId());
          // if (banklist.size() > 0) {
          // for (int i = 0; i < banklist.size(); i++) {
          // Object obj[] = null;
          // obj = (Object[]) banklist.get(0);
          // receivables_orgname = obj[0].toString();
          // receivables_bank_acc = obj[2].toString();
          // receivables_bank_name = obj[1].toString();
          // }
          // }
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
          personAddPayAF.setReceivables_bank_acc(receivables_bank_acc);
          personAddPayAF.setReceivables_bank_name(receivables_bank_name);
          personAddPayAF.setReceivables_orgname(receivables_orgname);
          personAddPayAF.setPayment_bank_acc(payment_bank_acc);
          personAddPayAF.setPayment_bank_name(payment_bank_name);
          personAddPayAF.setPayment_orgname(payment_orgname);
          if (paymentHead.size() > 0) {
            personAddPayAF.setListCount("1");
            personAddPayAF.setPersonAddPayList(personAddPayList);
            PersonAddPayDTO personAddPayDTO = null;
            BigDecimal orgPaySum = new BigDecimal(0.00);
            BigDecimal empPaySum = new BigDecimal(0.00);
            BigDecimal paySum = new BigDecimal(0.00);

            if (personAddPayList.size() > 0) {
              for (int i = 0; i < personAddPayList.size(); i++) {
                personAddPayDTO = (PersonAddPayDTO) personAddPayList.get(i);
                orgPaySum = orgPaySum
                    .add(personAddPayDTO.getUnitAddPayAmount());
                empPaySum = empPaySum.add(personAddPayDTO
                    .getPersonAddPayAmount());
                paySum = paySum.add(personAddPayDTO.getAddPayAmount());
              }
              personAddPayAF.setDocNumber(noteNum);
            }
          } else {
            personAddPayAF.setListCount("3");
            pagination.setNrOfElements(0);
          }
        }
      } else {
        personAddPayAF.setListCount("2");
        personAddPayAF.setPayKind("2");

      }

      // System.out.println("SSSSSSSSSSSSSSS="+statetype);

      personAddPayAF.setPay_kind((BusiTools
          .listBusiProperty(BusiConst.PAY_KIND_INFO)));
      personAddPayAF.setPay_way((BusiTools
          .listBusiProperty(BusiConst.PAY_WAY_INFO)));

      PersonAddPayAF af = new PersonAddPayAF();
      af = (PersonAddPayAF) pagination.getQueryCriterions().get(
          "personAddPayAF");
      if (af != null) {
        if (af.getPayWay() != null && !af.getPayWay().equals("")) {
          personAddPayAF.setPayWay(af.getPayWay());
        }
        if (af.getPayKind() != null && af.getPayKind().equals("")) {
          personAddPayAF.setPayKind(af.getPayKind());
        }
      } else {
        personAddPayAF.setPayKind("2");
      }

      personAddPayAF.setOverPay(overPay);
      if (empAddPay == null) {
        if (payKind != null && !"".equals(payKind)) {
          personAddPayAF.setPayKind(payKind);
        }
        if (payWay != null && !"".equals(payWay)) {
          personAddPayAF.setPayWay(payWay);
        }
      } else {
        personAddPayAF.setPayKind(empAddPay.getPay_kind());
        personAddPayAF.setPayWay(empAddPay.getPay_way());
      }

      if (("".equals(personAddPayAF.getDocNumber()) || personAddPayAF
          .getDocNumber() == null)
          && orgId != null && !"".equals(orgId)) {
        personAddPayAF.setDocNumber(personAddPayBS.getNoteNum(securityInfo));
      }
      request.setAttribute("statetype", statetype);
      request.getSession().setAttribute("personAddPayAF", personAddPayAF);
      request.getSession().setAttribute("f", f);
      // 在页面判断是否单位版用到_wangy
      int type = securityInfo.getIsOrgEdition();
      request.setAttribute("type", type + "");
    } catch (Exception ex) {
      ex.printStackTrace();
    }
    return mapping.findForward("to_personaddpay_show.jsp");
  }

  private Pagination getPagination(String paginationKey,
      HttpServletRequest request) {
    Pagination pagination = (Pagination) request.getSession().getAttribute(
        paginationKey);
    if (pagination == null) {
      pagination = new Pagination(0, 10, 1, "max(addPayTail.id)", "ASC",
          new HashMap(0));
      request.getSession().setAttribute(paginationKey, pagination);
    }

    return pagination;
  }
}
