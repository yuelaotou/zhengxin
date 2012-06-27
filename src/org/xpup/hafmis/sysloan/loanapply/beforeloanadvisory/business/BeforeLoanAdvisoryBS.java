package org.xpup.hafmis.sysloan.loanapply.beforeloanadvisory.business;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.xpup.common.exception.BusinessException;
import org.xpup.common.util.Pagination;
import org.xpup.hafmis.common.util.BusiConst;
import org.xpup.hafmis.common.util.BusiTools;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.sysloan.common.dao.BeforeLoanAdvisoryDAO;
import org.xpup.hafmis.sysloan.common.dao.LoanRateDAO;
import org.xpup.hafmis.sysloan.common.loanconditionsset.LoanConditionsParamSetDTO;
import org.xpup.hafmis.sysloan.loanapply.beforeloanadvisory.bsinterface.IBeforeLoanAdvisoryBS;
import org.xpup.hafmis.sysloan.loanapply.beforeloanadvisory.form.BeforeLoanAdvisoryShowAF;

public class BeforeLoanAdvisoryBS implements IBeforeLoanAdvisoryBS {
  BeforeLoanAdvisoryDAO beforeLoanAdvisoryDAO = null;

  LoanRateDAO loanRateDAO = null;

  public void setBeforeLoanAdvisoryDAO(
      BeforeLoanAdvisoryDAO beforeLoanAdvisoryDAO) {
    this.beforeLoanAdvisoryDAO = beforeLoanAdvisoryDAO;
  }

  public BeforeLoanAdvisoryShowAF queryEmpInfo(Pagination pagination,
      SecurityInfo securityInfo,
      BeforeLoanAdvisoryShowAF beforeLoanAdvisoryShowAF) throws Exception,
      BusinessException {
    String empid = (String) pagination.getQueryCriterions().get("empid");
    String empName = (String) pagination.getQueryCriterions().get("empName");
    String empCardnum = (String) pagination.getQueryCriterions().get(
        "empCardnum");
    String orgid = (String) pagination.getQueryCriterions().get("orgid");
    String orgname = (String) pagination.getQueryCriterions().get("orgname");
    List returnList = new ArrayList();
    try {
      if (empid != null && !empid.equals("")) {
        returnList = beforeLoanAdvisoryDAO.getEmployeeInfoList_wxg(empid,
            empName, empCardnum, orgid, orgname, securityInfo);
        if (returnList.size() == 0) {
          throw new BusinessException("该职工信息不存在！");
        }
        Object[] obj = null;
        for (int i = 0; i < returnList.size(); i++) {
          obj = (Object[]) returnList.get(i);
          if (obj[0] != null) {
            beforeLoanAdvisoryShowAF.setEmpname(obj[0].toString());
          }
          if (obj[1] != null) {
            beforeLoanAdvisoryShowAF.setEmpId(obj[1].toString());
          }
          if (obj[2] != null) {
            beforeLoanAdvisoryShowAF.setEmpCardnum(obj[2].toString());
          }
          if (obj[3] != null) {
            beforeLoanAdvisoryShowAF.setEmpOrgname(obj[3].toString());
          }
          if (obj[4] != null) {
            beforeLoanAdvisoryShowAF.setEmpOrgid(obj[4].toString());
          }
          if (obj[5] != null) {
            beforeLoanAdvisoryShowAF.setEmpSalaryBase(obj[5].toString());
          }
          if (obj[6] != null) {
            beforeLoanAdvisoryShowAF.setEmpMonthPay(obj[6].toString());
          }
          if (obj[7] != null) {
            beforeLoanAdvisoryShowAF.setEmpBalance(obj[7].toString());
          }
          if (obj[8] != null) {
            beforeLoanAdvisoryShowAF
                .setEmpStatus(BusiTools.getBusiValue(Integer.parseInt(obj[8]
                    .toString()), BusiConst.OLDPAYMENTSTATE));
          }
          if (obj[9] != null) {
            String temp = obj[9].toString();
            temp = temp.substring(0, 4);
            Calendar cld = Calendar.getInstance();
            int year = cld.get(Calendar.YEAR);
            int age = year - (new Integer(temp)).intValue();
            beforeLoanAdvisoryShowAF.setEmpAge(age + "");
          }
        }
        if (obj[10] != null) {
          beforeLoanAdvisoryShowAF.setOfficecode(obj[10].toString());
        }
        if (obj[11] != null) {
          beforeLoanAdvisoryShowAF.setSex(obj[11].toString());
        }
        beforeLoanAdvisoryShowAF.setEmpContinus(beforeLoanAdvisoryDAO
            .queryMonthCounts_wxg(beforeLoanAdvisoryShowAF.getEmpId(),
                beforeLoanAdvisoryShowAF.getEmpOrgid())
            + "");
      }
    } catch (BusinessException be) {
      throw be;
    } catch (Exception e) {
      e.printStackTrace();
    }

    return beforeLoanAdvisoryShowAF;

  }

  public BeforeLoanAdvisoryShowAF querySpouseInfo(Pagination pagination,
      SecurityInfo securityInfo,
      BeforeLoanAdvisoryShowAF beforeLoanAdvisoryShowAF) throws Exception,
      BusinessException {

    String empid = (String) pagination.getQueryCriterions().get("empid");
    String empName = (String) pagination.getQueryCriterions().get("empName");
    String empCardnum = (String) pagination.getQueryCriterions().get(
        "empCardnum");
    String orgid = (String) pagination.getQueryCriterions().get("orgid");
    String orgname = (String) pagination.getQueryCriterions().get("orgname");
    List returnList = new ArrayList();
    try {
      if (empid != null && !empid.equals("")) {
        returnList = beforeLoanAdvisoryDAO.getEmployeeInfoList_wxg(empid,
            empName, empCardnum, orgid, orgname, securityInfo);
        if (returnList.size() == 0) {
          throw new BusinessException("该职工信息不存在！");
        }
        Object[] obj = null;
        for (int i = 0; i < returnList.size(); i++) {
          obj = (Object[]) returnList.get(i);
          if (obj[0] != null) {
            beforeLoanAdvisoryShowAF.setSpouseName(obj[0].toString());
          }
          if (obj[1] != null) {
            beforeLoanAdvisoryShowAF.setSpouseId(obj[1].toString());
          }
          if (obj[2] != null) {
            beforeLoanAdvisoryShowAF.setSpouseCardnum(obj[2].toString());
          }
          if (obj[3] != null) {
            beforeLoanAdvisoryShowAF.setSpouseOrgname(obj[3].toString());
          }
          if (obj[4] != null) {
            beforeLoanAdvisoryShowAF.setSpouseOrgid(obj[4].toString());
          }
          if (obj[5] != null) {
            beforeLoanAdvisoryShowAF.setSpouseSalaryBase(obj[5].toString());
          }
          if (obj[6] != null) {
            beforeLoanAdvisoryShowAF.setSpouseMonthPay(obj[6].toString());
          }
          if (obj[7] != null) {
            beforeLoanAdvisoryShowAF.setSpouseBalance(obj[7].toString());
          }
          if (obj[8] != null) {
            beforeLoanAdvisoryShowAF
                .setSpouseStatus(BusiTools.getBusiValue(Integer.parseInt(obj[8]
                    .toString()), BusiConst.OLDPAYMENTSTATE));
          }
          if (obj[9] != null) {
            String temp = obj[9].toString();
            temp = temp.substring(0, 4);
            Calendar cld = Calendar.getInstance();
            int year = cld.get(Calendar.YEAR);
            int age = year - (new Integer(temp)).intValue();
            beforeLoanAdvisoryShowAF.setSpouseAge(age + "");
          }

        }
        beforeLoanAdvisoryShowAF.setSpouseContinus(beforeLoanAdvisoryDAO
            .queryMonthCounts_wxg(beforeLoanAdvisoryShowAF.getEmpId(),
                beforeLoanAdvisoryShowAF.getEmpOrgid())
            + "");
      }
    } catch (BusinessException be) {
      throw be;
    } catch (Exception e) {
      e.printStackTrace();
    }

    return beforeLoanAdvisoryShowAF;
  }

  public BeforeLoanAdvisoryShowAF queryLoanInfo(
      BeforeLoanAdvisoryShowAF beforeLoanAdvisoryShowAF, String housetype,
      String totalPrice) throws Exception, BusinessException {

    String office = beforeLoanAdvisoryShowAF.getOfficecode();
    String sex = beforeLoanAdvisoryShowAF.getSex();
    BigDecimal tempMoney1 = new BigDecimal(0.00);
    BigDecimal loanHoseRate = new BigDecimal(0.00);
    BigDecimal tempMoney2 = new BigDecimal(0.00);
    BigDecimal loanHandHoseRate = new BigDecimal(0.00);
    BigDecimal compare = new BigDecimal(0.00);
    Integer age = new Integer(0);
    String empAge = beforeLoanAdvisoryShowAF.getEmpAge();
    Integer empMaxAge = new Integer(0);
    try {
      List tempList1 = beforeLoanAdvisoryDAO.queryEmpLoanAge(office);
      if (tempList1.size() > 0) {
        for (int i = 0; i < tempList1.size(); i++) {
          LoanConditionsParamSetDTO loanConditionsParamSetDTO4 = (LoanConditionsParamSetDTO) tempList1
              .get(i);
          if (sex.equals("1")
              && (loanConditionsParamSetDTO4.getParamValue().equals("1"))) {

            age = new Integer(loanConditionsParamSetDTO4.getParamExplain());
          }
          if (sex.equals("2")
              && (loanConditionsParamSetDTO4.getParamValue().equals("2"))) {
            age = new Integer(loanConditionsParamSetDTO4.getParamExplain());
          }
        }
      }

      int temAge = age.intValue() - new Integer(empAge).intValue() + 1;
      List tempList2 = beforeLoanAdvisoryDAO.queryEmpMaxLoanAge(office);
      if (tempList2.size() > 0) {
        for (int i = 0; i < tempList2.size(); i++) {
          LoanConditionsParamSetDTO loanConditionsParamSetDTO13 = (LoanConditionsParamSetDTO) tempList2
              .get(i);
          if (housetype.equals("01")
              && (loanConditionsParamSetDTO13.getParamValue().equals("1"))) {
            empMaxAge = new Integer(loanConditionsParamSetDTO13
                .getParamExplain());
          }
          if (housetype.equals("02")
              && (loanConditionsParamSetDTO13.getParamValue().equals("2"))) {
            empMaxAge = new Integer(loanConditionsParamSetDTO13
                .getParamExplain());
          }
        }
      }

      if (temAge > empMaxAge.intValue()) {
        beforeLoanAdvisoryShowAF.setMaxLoanYear(empMaxAge.toString());
      } else {
        beforeLoanAdvisoryShowAF.setMaxLoanYear(temAge + "");
      }

      if (housetype.equals("01")) // 商品房
      {
        LoanConditionsParamSetDTO loanConditionsParamSetDTO8 = beforeLoanAdvisoryDAO
            .queryHouseprice(office);
        if (loanConditionsParamSetDTO8.getParamExplain() != null) {
          loanHoseRate = new BigDecimal(loanConditionsParamSetDTO8
              .getParamExplain()).divide(new BigDecimal(100.00), 4,
              BigDecimal.ROUND_HALF_DOWN);
        }
        tempMoney1 = (new BigDecimal(totalPrice)).multiply(loanHoseRate)
            .setScale(2, BigDecimal.ROUND_HALF_DOWN);
        LoanConditionsParamSetDTO loanConditionsParamSetDTO10 = beforeLoanAdvisoryDAO
            .queryMaxHouseprice(office);
        if (loanConditionsParamSetDTO10.getParamExplain() != null) {
          tempMoney2 = new BigDecimal(loanConditionsParamSetDTO10
              .getParamExplain());
        }

      }
      if (housetype.equals("02")) // 二手房
      {
        List tempList3 = beforeLoanAdvisoryDAO.queryHandHouseprice(office);
        for (int i = 0; i < tempList2.size(); i++) {
          LoanConditionsParamSetDTO loanConditionsParamSetDTO9 = (LoanConditionsParamSetDTO) tempList3
              .get(i);
          int j = (compare.compareTo(new BigDecimal(loanConditionsParamSetDTO9
              .getParamExplain())));
          if (j > 0) {
            loanHandHoseRate = compare;
          } else {
            loanHandHoseRate = new BigDecimal(loanConditionsParamSetDTO9
                .getParamExplain());
          }
        }
        tempMoney1 = (new BigDecimal(totalPrice)).multiply(loanHandHoseRate)
            .setScale(2, BigDecimal.ROUND_HALF_DOWN);
        LoanConditionsParamSetDTO loanConditionsParamSetDTO11 = beforeLoanAdvisoryDAO
            .queryMaxHandHouseprice(office);
        if (loanConditionsParamSetDTO11.getParamExplain() != null) {
          tempMoney2 = (new BigDecimal(loanConditionsParamSetDTO11
              .getParamExplain()));
        }

      }

      int i = tempMoney1.compareTo(tempMoney2);
      if (i > 0) {
        beforeLoanAdvisoryShowAF.setMaxLoanMoney(tempMoney2.toString());
      } else {
        beforeLoanAdvisoryShowAF.setMaxLoanMoney(tempMoney1.toString());
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    return beforeLoanAdvisoryShowAF;

  }

  /*
   * 获得月利率
   */
  public String findMonthRate(String office, String loantimeLimit,
      String loanMood) throws BusinessException {
    String monthRate = "";
    try {
      String loantype = "0";
      int i = Integer.parseInt(loantimeLimit);
      // if (!loanMood.equals("")) {
      // if (loanMood.equals("4")) {
      // loantype = "2";
      // } else if (loanMood.equals("5")) {
      // loantype = "3";
      // } else {
      // if (i > 60) {
      // loantype = "1";
      // }
      // }
      // } else {
      // if (i > 60) {
      // loantype = "1";
      // }
      // }
      if (i > 60) {
        loantype = "1";
      }
      if (i <= 60) {
        loantype = "0";
      }
      BigDecimal rate = loanRateDAO.findMontRate(office, loantype);// 求利率
      if (rate == null) {
        monthRate = "";
      } else {
        monthRate = rate.toString();
      }

    } catch (Exception e) {
      e.printStackTrace();
    }
    return monthRate;
  }

  public BeforeLoanAdvisoryShowAF queryIputLoanInfo(
      BeforeLoanAdvisoryShowAF beforeLoanAdvisoryShowAF, String inputLoanMoney,
      String housetype) throws Exception, BusinessException {

    String office = beforeLoanAdvisoryShowAF.getOfficecode();
    String sex = beforeLoanAdvisoryShowAF.getSex();
    Integer age = new Integer(0);
    String empAge = beforeLoanAdvisoryShowAF.getEmpAge();
    Integer empMaxAge = new Integer(0);
    String rate = "0";
    String year = "0";
    BigDecimal moneyTotal = new BigDecimal(0.00);
    try {
      List tempList1 = beforeLoanAdvisoryDAO.queryEmpLoanAge(office);
      if (tempList1.size() > 0) {
        for (int i = 0; i < tempList1.size(); i++) {
          LoanConditionsParamSetDTO loanConditionsParamSetDTO4 = (LoanConditionsParamSetDTO) tempList1
              .get(i);
          if (sex.equals("1")
              && (loanConditionsParamSetDTO4.getParamValue().equals("1"))) {

            age = new Integer(loanConditionsParamSetDTO4.getParamExplain());
          }
          if (sex.equals("2")
              && (loanConditionsParamSetDTO4.getParamValue().equals("2"))) {
            age = new Integer(loanConditionsParamSetDTO4.getParamExplain());
          }
        }
      }

      int temAge = age.intValue() - new Integer(empAge).intValue() + 1;
      List tempList2 = beforeLoanAdvisoryDAO.queryEmpMaxLoanAge(office);
      if (tempList2.size() > 0) {
        for (int i = 0; i < tempList2.size(); i++) {
          LoanConditionsParamSetDTO loanConditionsParamSetDTO13 = (LoanConditionsParamSetDTO) tempList2
              .get(i);
          if (housetype.equals("01")
              && (loanConditionsParamSetDTO13.getParamValue().equals("1"))) {
            empMaxAge = new Integer(loanConditionsParamSetDTO13
                .getParamExplain());
          }
          if (housetype.equals("02")
              && (loanConditionsParamSetDTO13.getParamValue().equals("2"))) {
            empMaxAge = new Integer(loanConditionsParamSetDTO13
                .getParamExplain());
          }
        }
      }

      if (temAge > empMaxAge.intValue()) {
        year = empMaxAge.intValue() * 12 + "";
        beforeLoanAdvisoryShowAF.setOutLoanYear(empMaxAge.toString());
      } else {
        year = temAge * 12 + "";
        beforeLoanAdvisoryShowAF.setOutLoanYear(temAge + "");
      }

      LoanConditionsParamSetDTO loanConditionsParamSetDTO14 = beforeLoanAdvisoryDAO
          .queryRepaymentRate(office);
      if (loanConditionsParamSetDTO14.getParamExplain() != null) {
        rate = loanConditionsParamSetDTO14.getParamExplain();
      }

      moneyTotal = new BigDecimal(rate)
          .multiply(new BigDecimal(inputLoanMoney)).multiply(
              new BigDecimal(year)).setScale(2, BigDecimal.ROUND_HALF_DOWN);
      beforeLoanAdvisoryShowAF.setOutLoanMoney(moneyTotal.toString());
    } catch (Exception e) {
      e.printStackTrace();
    }
    return beforeLoanAdvisoryShowAF;

  }

  public void setLoanRateDAO(LoanRateDAO loanRateDAO) {
    this.loanRateDAO = loanRateDAO;
  }

}
