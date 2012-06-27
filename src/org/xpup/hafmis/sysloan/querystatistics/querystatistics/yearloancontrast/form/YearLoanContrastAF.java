package org.xpup.hafmis.sysloan.querystatistics.querystatistics.yearloancontrast.form;

import java.util.ArrayList;
import java.util.List;

import org.apache.struts.action.ActionForm;

public class YearLoanContrastAF extends ActionForm{
  private String year = "";
  private String officeCode = "";
  private List list = new ArrayList();
  private String thisyear = "";
  private String lastyear = "";
  private String officeName = "";
  public String getOfficeName() {
    return officeName;
  }
  public void setOfficeName(String officeName) {
    this.officeName = officeName;
  }
  public String getLastyear() {
    return lastyear;
  }
  public void setLastyear(String lastyear) {
    this.lastyear = lastyear;
  }
  public String getThisyear() {
    return thisyear;
  }
  public void setThisyear(String thisyear) {
    this.thisyear = thisyear;
  }
  public List getList() {
    return list;
  }
  public void setList(List list) {
    this.list = list;
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
