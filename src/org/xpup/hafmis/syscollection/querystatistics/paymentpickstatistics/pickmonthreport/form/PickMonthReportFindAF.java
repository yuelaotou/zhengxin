package org.xpup.hafmis.syscollection.querystatistics.paymentpickstatistics.pickmonthreport.form;

import javax.servlet.http.HttpServletRequest;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

public class PickMonthReportFindAF extends ActionForm{
  
  private String officeCode;
  
  private String collBank;
  
  private String date;
  
  private String startDate = "";
  
  private String endDate = "";
  
  public String getEndDate() {
    return endDate;
  }

  public void setEndDate(String endDate) {
    this.endDate = endDate;
  }

  public String getStartDate() {
    return startDate;
  }

  public void setStartDate(String startDate) {
    this.startDate = startDate;
  }

  public String getCollBank() {
    return collBank;
  }

  public void setCollBank(String collBank) {
    this.collBank = collBank;
  }

  public String getDate() {
    return date;
  }

  public void setDate(String date) {
    this.date = date;
  }

  public String getOfficeCode() {
    return officeCode;
  }

  public void setOfficeCode(String officeCode) {
    this.officeCode = officeCode;
  }

  public void reset(ActionMapping arg0, HttpServletRequest arg1) {
    this.officeCode = "";
    this.collBank = "";
    this.date = "";
  }
  
}
