package org.xpup.hafmis.syscollection.querystatistics.paymentpickdetail.business;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.xpup.common.exception.BusinessException;
import org.xpup.common.util.Pagination;
import org.xpup.hafmis.common.util.BusiConst;
import org.xpup.hafmis.common.util.BusiTools;
import org.xpup.hafmis.orgstrct.dao.CollBankDAO;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.syscollection.common.dao.PaymentHeadDAO;
import org.xpup.hafmis.syscollection.querystatistics.paymentpickdetail.bsinterface.IPaymntPickBS;
import org.xpup.hafmis.syscollection.querystatistics.paymentpickdetail.dto.PaymntPickDetailDTO;
import org.xpup.hafmis.syscollection.querystatistics.paymentpickdetail.form.PaymntPickAF;

public class PaymntPickBS implements IPaymntPickBS {

  private PaymentHeadDAO paymentHeadDAO = null;

  private CollBankDAO collBankDAO = null;

  public void setCollBankDAO(CollBankDAO collBankDAO) {
    this.collBankDAO = collBankDAO;
  }

  public void setPaymentHeadDAO(PaymentHeadDAO paymentHeadDAO) {
    this.paymentHeadDAO = paymentHeadDAO;
  }

  public PaymntPickAF queryPaymntPickDetail(Pagination pagination,
      SecurityInfo securityInfo) throws Exception, BusinessException {
    PaymntPickAF paymntPickAF = new PaymntPickAF();
    List list = new ArrayList();
    try {
      String office = (String) pagination.getQueryCriterions().get("office");
      String year = (String) pagination.getQueryCriterions().get("year");
      String month = (String) pagination.getQueryCriterions().get("month");
      month = month.length() == 2 ? month : "0" + month;
      List bankList = paymentHeadDAO.queryPaymntHeadBanks(office, year + month);
      List detailList = paymentHeadDAO.queryPaymntHeadDetail(year + month);
      BigDecimal payMoney = null;
      BigDecimal pickMoney = null;
      List innerList = null;
      if(bankList.isEmpty())
        return paymntPickAF;
      // 统计数
      BigDecimal payMoneySum_sbj = new BigDecimal(0.00);
      BigDecimal pickMoneySum_sbj = new BigDecimal(0.00);
      BigDecimal payMoneySum_xq = new BigDecimal(0.00);
      BigDecimal pickMoneySum_xq = new BigDecimal(0.00);
      int days = BusiTools.daysOfMonth(Integer.parseInt(year), Integer.parseInt(month));
      for (int i = 1; i <= days; i++) {
        PaymntPickDetailDTO dto = new PaymntPickDetailDTO();
        dto.setDate(year + "-" + month + "-" + i);
        innerList = new ArrayList();
        // 控制银行数量
        for (int j = 0; j < bankList.size(); j++) {
          PaymntPickDetailDTO date = new PaymntPickDetailDTO();
          PaymntPickDetailDTO dto1 = (PaymntPickDetailDTO) bankList.get(j);
          payMoney = new BigDecimal(0.00);
          pickMoney = new BigDecimal(0.00);
          for (int k = 0; k < detailList.size(); k++) {
            PaymntPickDetailDTO detailDTO = (PaymntPickDetailDTO) detailList
                .get(k);
            int day = Integer.parseInt(detailDTO.getSettDate().substring(6, 8));
            if (day > i)
              break;
            else if (day == i
                && dto1.getCollBankId().equals(detailDTO.getCollBankId())) {
              if (detailDTO.getBizType().equals("D")) {
                pickMoney = pickMoney.add(detailDTO.getDebit());
              } else if (detailDTO.getBizType().equals("A")
                  || detailDTO.getBizType().equals("B")
                  || detailDTO.getBizType().equals("C")
                  || detailDTO.getBizType().equals("M")
                  || detailDTO.getBizType().equals("K")
                  || detailDTO.getBizType().equals("L")
                  || detailDTO.getBizType().equals("G")) {
                payMoney = payMoney.add(detailDTO.getCredit()).subtract(
                    detailDTO.getDebit());
              }
            }
          }
          if (payMoney.intValue() != 0)
            date.setPayMoney(payMoney);
          if (pickMoney.intValue() != 0)
            date.setPickMoney(pickMoney);

          innerList.add(date);
          if (i == 1)
            date.setCollBankId(collBankDAO.getCollBankByCollBankid_(
                dto1.getCollBankId()).getCollBankName());
          else {
            date.setCollBankId(dto1.getCollBankId());
            date.setOffice(dto1.getOffice());
          }
        }
        // 每行的银行结束
        dto.setList(innerList);
        list.add(dto);
        // 最后一行添加总计
        if (i == days) {
          dto = new PaymntPickDetailDTO();
          dto.setDate("合计");
          List lastList = new ArrayList();
          for (int j = 0; j < innerList.size(); j++) {
            PaymntPickDetailDTO dto_ = (PaymntPickDetailDTO) innerList.get(j);
            PaymntPickDetailDTO lastDTO = new PaymntPickDetailDTO();
            BigDecimal payMoneySum = paymentHeadDAO.queryMonthEndPaySumByBank(
                dto_.getCollBankId(), year + month, "0");
            BigDecimal pickMoneySum = paymentHeadDAO.queryMonthEndPaySumByBank(
                dto_.getCollBankId(), year + month, "1");
            lastDTO.setPayMoney(payMoneySum);
            lastDTO.setPickMoney(pickMoneySum);
            lastList.add(lastDTO);
            if(dto_.getOffice().equals(BusiConst.OFFICECODE_SBJ)) {
              payMoneySum_sbj = payMoneySum_sbj.add(lastDTO.getPayMoney());
              pickMoneySum_sbj = pickMoneySum_sbj.add(lastDTO.getPickMoney());
            } else {
              payMoneySum_xq = payMoneySum_xq.add(lastDTO.getPayMoney());
              pickMoneySum_xq = pickMoneySum_xq.add(lastDTO.getPickMoney());
            }
          }
          dto.setList(lastList);
          list.add(dto);
        }
      }
      if(payMoneySum_sbj.intValue() != 0)
        paymntPickAF.setPayMoneySum_sbj(payMoneySum_sbj);
      if(pickMoneySum_sbj.intValue() != 0)
        paymntPickAF.setPickMoneySum_sbj(pickMoneySum_sbj);
      if(payMoneySum_xq.intValue() != 0)
        paymntPickAF.setPayMoneySum_xq(payMoneySum_xq);
      if(pickMoneySum_xq.intValue() != 0)
        paymntPickAF.setPickMoneySum_xq(pickMoneySum_xq);
      
      paymntPickAF.setPayMoneySum(payMoneySum_sbj.add(payMoneySum_xq));
      paymntPickAF.setPickMoneySum(pickMoneySum_sbj.add(pickMoneySum_xq));
      paymntPickAF.setList(list);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return paymntPickAF;
  }
  public PaymntPickAF queryPaymntPickMonthRept(Pagination pagination,
      SecurityInfo securityInfo) throws Exception, BusinessException {
    PaymntPickAF paymntPickAF = new PaymntPickAF();
    List list = new ArrayList();
    try {
      String office = (String) pagination.getQueryCriterions().get("office");
      String year = (String) pagination.getQueryCriterions().get("year");
      List bankList = paymentHeadDAO.queryPaymntHeadBanks(office, year);
      List detailList = paymentHeadDAO.queryPaymntHeadDetail(year);
      BigDecimal payMoney = null;
      BigDecimal pickMoney = null;
      List innerList = null;
      if(bankList.isEmpty())
        return paymntPickAF;
      // 统计数
      BigDecimal payMoneySum_sbj = new BigDecimal(0.00);
      BigDecimal pickMoneySum_sbj = new BigDecimal(0.00);
      BigDecimal payMoneySum_xq = new BigDecimal(0.00);
      BigDecimal pickMoneySum_xq = new BigDecimal(0.00);
      for (int i = 1; i <= 12; i++) {
        PaymntPickDetailDTO dto = new PaymntPickDetailDTO();
        dto.setDate(year + "-" + ((i + "").length() == 2 ? i + "" : "0" + i));
        innerList = new ArrayList();
        // 控制银行数量
        for (int j = 0; j < bankList.size(); j++) {
          PaymntPickDetailDTO date = new PaymntPickDetailDTO();
          PaymntPickDetailDTO dto1 = (PaymntPickDetailDTO) bankList.get(j);
          payMoney = new BigDecimal(0.00);
          pickMoney = new BigDecimal(0.00);
          for (int k = 0; k < detailList.size(); k++) {
            PaymntPickDetailDTO detailDTO = (PaymntPickDetailDTO) detailList
                .get(k);
            int month = Integer.parseInt(detailDTO.getSettDate().substring(4, 6));
            if (month > i)
              break;
            else if (month == i
                && dto1.getCollBankId().equals(detailDTO.getCollBankId())) {
              if (detailDTO.getBizType().equals("D")) {
                pickMoney = pickMoney.add(detailDTO.getDebit());
              } else if (detailDTO.getBizType().equals("A")
                  || detailDTO.getBizType().equals("B")
                  || detailDTO.getBizType().equals("C")
                  || detailDTO.getBizType().equals("M")
                  || detailDTO.getBizType().equals("K")
                  || detailDTO.getBizType().equals("L")
                  || detailDTO.getBizType().equals("G")) {
                payMoney = payMoney.add(detailDTO.getCredit()).subtract(
                    detailDTO.getDebit());
              }
            }
          }
          if (payMoney.intValue() != 0)
            date.setPayMoney(payMoney);
          if (pickMoney.intValue() != 0)
            date.setPickMoney(pickMoney);

          innerList.add(date);
          if (i == 1)
            date.setCollBankId(collBankDAO.getCollBankByCollBankid_(
                dto1.getCollBankId()).getCollBankName());
          else {
            date.setCollBankId(dto1.getCollBankId());
            date.setOffice(dto1.getOffice());
          }
        }
        // 每行的银行结束
        dto.setList(innerList);
        list.add(dto);
        // 最后一行添加总计
        if (i == 12) {
          dto = new PaymntPickDetailDTO();
          dto.setDate("合计");
          List lastList = new ArrayList();
          for (int j = 0; j < innerList.size(); j++) {
            PaymntPickDetailDTO dto_ = (PaymntPickDetailDTO) innerList.get(j);
            PaymntPickDetailDTO lastDTO = new PaymntPickDetailDTO();
            BigDecimal payMoneySum = paymentHeadDAO.queryMonthEndPaySumByBank(
                dto_.getCollBankId(), year, "0");
            BigDecimal pickMoneySum = paymentHeadDAO.queryMonthEndPaySumByBank(
                dto_.getCollBankId(), year, "1");
            lastDTO.setPayMoney(payMoneySum);
            lastDTO.setPickMoney(pickMoneySum);
            lastList.add(lastDTO);
            if(dto_.getOffice().equals(BusiConst.OFFICECODE_SBJ)) {
              payMoneySum_sbj = payMoneySum_sbj.add(lastDTO.getPayMoney());
              pickMoneySum_sbj = pickMoneySum_sbj.add(lastDTO.getPickMoney());
            } else {
              payMoneySum_xq = payMoneySum_xq.add(lastDTO.getPayMoney());
              pickMoneySum_xq = pickMoneySum_xq.add(lastDTO.getPickMoney());
            }
          }
          dto.setList(lastList);
          list.add(dto);
        }
      }
      if(payMoneySum_sbj.intValue() != 0)
        paymntPickAF.setPayMoneySum_sbj(payMoneySum_sbj);
      if(pickMoneySum_sbj.intValue() != 0)
        paymntPickAF.setPickMoneySum_sbj(pickMoneySum_sbj);
      if(payMoneySum_xq.intValue() != 0)
        paymntPickAF.setPayMoneySum_xq(payMoneySum_xq);
      if(pickMoneySum_xq.intValue() != 0)
        paymntPickAF.setPickMoneySum_xq(pickMoneySum_xq);
      
      paymntPickAF.setPayMoneySum(payMoneySum_sbj.add(payMoneySum_xq));
      paymntPickAF.setPickMoneySum(pickMoneySum_sbj.add(pickMoneySum_xq));
      paymntPickAF.setList(list);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return paymntPickAF;
  }
}
