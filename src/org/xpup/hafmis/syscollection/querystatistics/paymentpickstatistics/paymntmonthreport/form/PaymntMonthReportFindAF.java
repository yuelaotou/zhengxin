package org.xpup.hafmis.syscollection.querystatistics.paymentpickstatistics.paymntmonthreport.form;

import org.apache.struts.action.ActionForm;

public class PaymntMonthReportFindAF extends ActionForm {
  private String officeCode;

  private String collBank;

  private String year;

  private String month;

  public String getCollBank() {
    return collBank;
  }

  public void setCollBank(String collBank) {
    this.collBank = collBank;
  }

  public String getMonth() {
    return month;
  }

  public void setMonth(String month) {
    this.month = month;
  }

  public String getOfficeCode() {
    return officeCode;
  }

  public void setOfficeCode(String officeCode) {
    this.officeCode = officeCode;
  }

  public String getYear() {
    return year;
  }

  public void setYear(String year) {
    this.year = year;
  }
}
