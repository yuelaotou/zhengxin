package org.xpup.hafmis.syscollection.querystatistics.paymentpickstatistics.paymntmonthreport.business;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.xpup.common.exception.BusinessException;
import org.xpup.common.util.Pagination;
import org.xpup.hafmis.common.util.BusiTools;
import org.xpup.hafmis.orgstrct.dao.CollBankDAO;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.syscollection.common.dao.PaymentHeadDAO;
import org.xpup.hafmis.syscollection.querystatistics.paymentpickstatistics.paymntmonthreport.bsinterface.IPaymntMonthReportBS;
import org.xpup.hafmis.syscollection.querystatistics.paymentpickstatistics.paymntmonthreport.dto.PaymntMonRepInfoDTO;

public class PaymntMonthReportBS implements IPaymntMonthReportBS {

  private PaymentHeadDAO paymentHeadDAO = null;

  private CollBankDAO collBankDAO = null;

  public void setPaymentHeadDAO(PaymentHeadDAO paymentHeadDAO) {
    this.paymentHeadDAO = paymentHeadDAO;
  }

  public void setCollBankDAO(CollBankDAO collBankDAO) {
    this.collBankDAO = collBankDAO;
  }

  public List queryPaymntMonthRepInfo(Pagination pagination,
      SecurityInfo securityInfo) throws Exception, BusinessException {
    List returnList = new ArrayList();
    try {
      String office = (String) pagination.getQueryCriterions().get("office");
      String bank = (String) pagination.getQueryCriterions().get("bank");
      String year = (String) pagination.getQueryCriterions().get("year");
      String month = (String) pagination.getQueryCriterions().get("month");
      String yearMonth = year + (month.length() < 2 ? "0" + month : month);
      String startDate = year + "0101";
      String endDate = yearMonth + "31";
      List list = paymentHeadDAO.queryPaymntMonReportInfo(office, bank,
          yearMonth, securityInfo);
      HashMap map = paymentHeadDAO.queryOverpayBalance(bank, yearMonth);
      BigDecimal curMonthPay = new BigDecimal(0.00);
      BigDecimal curAddPay = new BigDecimal(0.00);
      BigDecimal curOverPay = new BigDecimal(0.00);
      BigDecimal overPaySumBalance = new BigDecimal(0.00);
      BigDecimal curSumPay = new BigDecimal(0.00);
      BigDecimal prevMonSumBalance = new BigDecimal(0.00);
      BigDecimal curYearSumPay = new BigDecimal(0.00);
      BigDecimal prevMonBalance = null;
      BigDecimal curYearMoney = null;
      BigDecimal overPayBalance = null;

      int orgCount = 0; // 户数
      int personCount = 0; // 人数

      BigDecimal curMonthPay_total = new BigDecimal(0.00);
      BigDecimal curAddPay_total = new BigDecimal(0.00);
      BigDecimal curOverPay_total = new BigDecimal(0.00);
      BigDecimal curSumPay_total = new BigDecimal(0.00);

      int orgCount_total = 0; // 户数
      int personCount_total = 0; // 人数

      String tempBank = "";
      for (int i = 0; i < list.size();) {
        PaymntMonRepInfoDTO dto = (PaymntMonRepInfoDTO) list.get(i);
        dto.setCurMonthPay(curMonthPay);
        if (i == 0)
          tempBank = dto.getCollBankId();
        if (!tempBank.equals(dto.getCollBankId())) {
          PaymntMonRepInfoDTO dto1 = new PaymntMonRepInfoDTO();
          // 上月末余额
          prevMonBalance = paymentHeadDAO.queryPrevMonBalance(tempBank,
              yearMonth);
          dto1.setPrevMonBalance(prevMonBalance);
          // 本年累计
          curYearMoney = paymentHeadDAO.queryCurYearPayMoney(tempBank,
              startDate, endDate);
          dto1.setCurYearSumPay(curYearMoney);
          // 挂账余额
          overPayBalance = new BigDecimal(map.get(tempBank).toString());
          dto1.setOverPayBalance(overPayBalance);
          // 累加合计
          prevMonSumBalance = prevMonSumBalance.add(prevMonBalance);
          curYearSumPay = curYearSumPay.add(curYearMoney);
          overPaySumBalance = overPaySumBalance.add(overPayBalance);
          
          dto1.setCollBank(collBankDAO.getCollBankByCollBankid(tempBank)
              .getCollBankName());
          dto1.setCurMonthPay(curMonthPay);
          dto1.setCurAddPay(curAddPay);
          dto1.setCurOverPay(curOverPay);
          dto1.setCurSumPay(curSumPay);
          dto1.setCurMonthOrgCount(new Integer(orgCount));
          dto1.setCurMonthPsnCount(new Integer(personCount));
          returnList.add(dto1);
          // 银行改变则将数值归0
          curMonthPay = new BigDecimal(0.00);
          curAddPay = new BigDecimal(0.00);
          curOverPay = new BigDecimal(0.00);
          curSumPay = new BigDecimal(0.00);
          orgCount = 0; // 户数
          personCount = 0; // 人数
        } else {
          if (dto.getBizType().equals("A") || dto.getBizType().equals("M")) {
            curMonthPay = curMonthPay.add(dto.getMoney());
            curMonthPay_total = curMonthPay_total.add(dto.getMoney());
          }
          if (dto.getBizType().equals("B")) {
            curAddPay = curAddPay.add(dto.getMoney());
            curAddPay_total = curAddPay_total.add(dto.getMoney());
          }
          if (dto.getBizType().equals("C")) {
            curOverPay = curOverPay.add(dto.getMoney());
            curOverPay_total = curOverPay_total.add(dto.getMoney());
          }
          orgCount += dto.getCurMonthOrgCount().intValue();
          personCount += dto.getCurMonthPsnCount().intValue();
          curSumPay = curSumPay.add(dto.getMoney());
          // 总计数(最后一行统计所有银行的)
          curSumPay_total = curSumPay_total.add(dto.getMoney());
          orgCount_total += dto.getCurMonthOrgCount().intValue();
          personCount_total += dto.getCurMonthPsnCount().intValue();
          i++;
        }
        tempBank = dto.getCollBankId();
      }
      if (!list.isEmpty()) {
        PaymntMonRepInfoDTO endDto = new PaymntMonRepInfoDTO();
        endDto.setCollBank(collBankDAO.getCollBankByCollBankid(tempBank)
            .getCollBankName());
        prevMonBalance = paymentHeadDAO.queryPrevMonBalance(tempBank,
            yearMonth);
        endDto.setPrevMonBalance(prevMonBalance);
        curYearMoney = paymentHeadDAO.queryCurYearPayMoney(tempBank,
            startDate, endDate);
        endDto.setCurYearSumPay(curYearMoney);
        // 挂账余额
        overPayBalance = new BigDecimal(map.get(tempBank).toString());
        endDto.setOverPayBalance(overPayBalance);
        // 累加合计
        prevMonSumBalance = prevMonSumBalance.add(prevMonBalance);
        curYearSumPay = curYearSumPay.add(curYearMoney);
        overPaySumBalance = overPaySumBalance.add(overPayBalance);
        endDto.setCurMonthPay(curMonthPay);
        endDto.setCurAddPay(curAddPay);
        endDto.setCurOverPay(curOverPay);
        endDto.setCurSumPay(curSumPay);
        endDto.setCurMonthOrgCount(new Integer(orgCount));
        endDto.setCurMonthPsnCount(new Integer(personCount));
        returnList.add(endDto);
        PaymntMonRepInfoDTO totalDto = new PaymntMonRepInfoDTO();
        totalDto.setCollBank("合计");
        totalDto.setCurMonthPay(curMonthPay_total);
        totalDto.setCurAddPay(curAddPay_total);
        totalDto.setCurOverPay(curOverPay_total);
        totalDto.setCurSumPay(curSumPay_total);
        totalDto.setPrevMonBalance(prevMonSumBalance);
        totalDto.setCurYearSumPay(curYearSumPay);
        totalDto.setOverPayBalance(overPaySumBalance);
        totalDto.setCurMonthOrgCount(new Integer(orgCount_total));
        totalDto.setCurMonthPsnCount(new Integer(personCount_total));
        returnList.add(totalDto);
      }

    } catch (Exception e) {
      e.printStackTrace();
    }
    return returnList;
  }

}
