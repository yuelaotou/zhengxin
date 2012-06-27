package org.xpup.hafmis.syscollection.paymng.orgaddpay.action;

import java.math.BigDecimal;
import java.util.ArrayList;
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
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.syscollection.common.domain.entity.Org;
import org.xpup.hafmis.syscollection.paymng.monthpay.action.MonthpayTbWindowMXShowAC;
import org.xpup.hafmis.syscollection.paymng.monthpay.bsinterface.IMonthpayBS;
import org.xpup.hafmis.syscollection.paymng.monthpay.form.MonthpayJYAF;
import org.xpup.hafmis.syscollection.paymng.orgaddpay.bsinterface.IOrgaddpayBS;
import org.xpup.hafmis.syscollection.paymng.orgaddpay.dto.OrgaddpayHeadPrintDto;
import org.xpup.hafmis.syscollection.systemmaintain.loanpara.bsinterface.ILoanDocNumDesignBS;

public class OrgaddpayTbMXPrintAC extends Action {

  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    ActionMessages messages = null;
    String type_month_pay=(String)request.getSession().getAttribute("type_month_pay");
    try {
      SecurityInfo securityInfo=(SecurityInfo)request.getSession().getAttribute("SecurityInfo");
      String paymentid = request.getParameter("paymentid");

      IOrgaddpayBS orgaddpayBS = (IOrgaddpayBS) BSUtils.getBusinessService(
          "orgaddpayBS", this, mapping.getModuleConfig());
      IMonthpayBS monthpayBS = (IMonthpayBS) BSUtils
      .getBusinessService("monthpayBS", this, mapping.getModuleConfig());
      OrgaddpayHeadPrintDto dto = orgaddpayBS.findOrgaddpayPring(paymentid);
      MonthpayJYAF monthpayJYAF = new MonthpayJYAF();
      Org org=orgaddpayBS.findOrgInfo(dto.getOrgId(), "2", securityInfo);
      List banklist = new ArrayList();
      banklist = monthpayBS.queryCollBankInfo(org.getOrgInfo()
          .getOfficecode(), org.getOrgInfo().getCollectionBankId());
      String receivables_orgname = ""; // 收款单位名称
      String receivables_bank_acc = ""; // 收款单位归集银行账号
      String receivables_bank_name = ""; // 收款单位归集银行名称
      if (banklist.size() > 0) {
        for (int i = 0; i < banklist.size(); i++) {
          Object obj[] = null;
          obj = (Object[]) banklist.get(0);
          if(obj[0] != null){
            receivables_orgname=obj[0].toString();
          }
          if(obj[2] != null){
            receivables_bank_acc=obj[2].toString();
          }
          if(obj[1] != null){
            receivables_bank_name=obj[1].toString();
          }
        }
      }  
      monthpayJYAF.setOrgid(org.getId().toString());
      monthpayJYAF.setPrintDate(securityInfo.getUserInfo().getBizDate());
      monthpayJYAF.setNoteNum(dto.getDocNum());
      monthpayJYAF.setReceivables_orgname(receivables_orgname);
      monthpayJYAF.setReceivables_bank_acc(receivables_bank_acc);
      monthpayJYAF.setReceivables_bank_name(receivables_bank_name);
      if(org.getOrgInfo().getReserveaB()!=null && !org.getOrgInfo().getReserveaB().equals("")){
        monthpayJYAF.setPayment_orgname(org.getOrgInfo().getReserveaB());
      }else{
        monthpayJYAF.setPayment_orgname(org.getOrgInfo().getName());
      }
      monthpayJYAF.setPayment_bank_acc(org.getOrgInfo().getPayBank().getAccountNum());
      monthpayJYAF.setPayment_bank_name(org.getOrgInfo().getPayBank().getName());
      monthpayJYAF.setSumPay(new BigDecimal(dto.getPay()));
      monthpayJYAF.setPayKind(dto.getPayKind());
      monthpayJYAF.setPayWay(dto.getPayWay());
      monthpayJYAF.setPersonCounts(new Integer(dto.getPersonCounts()));
      request.setAttribute("monthpayJYAF", monthpayJYAF);
      List list = orgaddpayBS.findOrgaddpayListPring(paymentid);
      request.getSession().setAttribute("cellList", list);
      String orgid = request.getParameter("orgid");
      ILoanDocNumDesignBS loanDocNumDesignBS = (ILoanDocNumDesignBS) BSUtils
          .getBusinessService("loanDocNumDesignBS", this, mapping
              .getModuleConfig());
      String userName = "";
      try {
        String name = loanDocNumDesignBS.getNamePara();

        if (name.equals("1")) {
          userName = securityInfo.getUserName();
        } else {
          userName = securityInfo.getRealName();
        }

      } catch (Exception e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }
      String collectionBankName = "";
      if (orgid != null && !orgid.equals("")) {
        collectionBankName = orgaddpayBS.queryCollectionBankNameById(orgid,
            securityInfo);
      }
      String bizDate = securityInfo.getUserInfo().getBizDate();
      request.getSession().setAttribute("userName", userName);
      request.getSession().setAttribute("bizDate", bizDate);
      request.getSession().setAttribute("collectionBankName",
          collectionBankName);

      // wuht
    } catch (BusinessException bex) {
      messages = new ActionMessages();
      messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(bex
          .getMessage(), false));
      saveErrors(request, messages);
    } catch (Exception ex) {
      ex.printStackTrace();
    }
    request.getSession().setAttribute("type_month_pay", null);
    if("1".equals(type_month_pay)){
      return mapping.findForward("show_print_1");
    }else{
      return mapping.findForward("show_print");
    }
  }

}