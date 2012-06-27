package org.xpup.hafmis.syscollection.querystatistics.paymentpickstatistics.pickyearreport.form;

import javax.servlet.http.HttpServletRequest;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

public class PickYearReportFindAF extends ActionForm{
  
  private String officeCode;
  
  private String collBank;
  
  private String date;
  
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
