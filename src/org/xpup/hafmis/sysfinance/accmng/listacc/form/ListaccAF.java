package org.xpup.hafmis.sysfinance.accmng.listacc.form;

import org.apache.struts.action.ActionForm;

public class ListaccAF extends ActionForm {
  private static final long serialVersionUID = 1L;
  private String bizyear="";
  private String starperiod="";
  private String endperiod="";
  private String starsubject="";
  private String endsubject="";
  private String office="";
  private String temp_flag="";
  private String temp_year="";
  private String temp_month="";
  
  public String getOffice() {
    return office;
  }

  public void setOffice(String office) {
    this.office = office;
  }

  public String getBizyear() {
    return bizyear;
  }

  public void setBizyear(String bizyear) {
    this.bizyear = bizyear;
  }

  public String getEndperiod() {
    return endperiod;
  }

  public void setEndperiod(String endperiod) {
    this.endperiod = endperiod;
  }

  public String getStarperiod() {
    return starperiod;
  }

  public void setStarperiod(String starperiod) {
    this.starperiod = starperiod;
  }

  public String getEndsubject() {
    return endsubject;
  }

  public void setEndsubject(String endsubject) {
    this.endsubject = endsubject;
  }

  public String getStarsubject() {
    return starsubject;
  }

  public void setStarsubject(String starsubject) {
    this.starsubject = starsubject;
  }

  public String getTemp_flag() {
    return temp_flag;
  }

  public void setTemp_flag(String temp_flag) {
    this.temp_flag = temp_flag;
  }
  public String getTemp_month() {
    return temp_month;
  }

  public void setTemp_month(String temp_month) {
    this.temp_month = temp_month;
  }

  public String getTemp_year() {
    return temp_year;
  }

  public void setTemp_year(String temp_year) {
    this.temp_year = temp_year;
  }
}
