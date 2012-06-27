package org.xpup.hafmis.sysfinance.accmng.totleacc.form;

import org.apache.struts.action.ActionForm;

public class TotleaccAF extends ActionForm {
  private static final long serialVersionUID = 1L;
  private String bizyear="";
  private String starperiod="";
  private String endperiod="";
  private String starsubject="";
  private String endsubject="";
  private String office="";
  private String temp_flag="";
  private String subjectLevelEnd="";
  
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

  public String getSubjectLevelEnd() {
    return subjectLevelEnd;
  }

  public void setSubjectLevelEnd(String subjectLevelEnd) {
    this.subjectLevelEnd = subjectLevelEnd;
  }
}
