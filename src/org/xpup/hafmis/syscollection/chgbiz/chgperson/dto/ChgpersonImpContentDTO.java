package org.xpup.hafmis.syscollection.chgbiz.chgperson.dto;

import org.xpup.common.util.imp.domn.interfaces.impDto;

public class ChgpersonImpContentDTO extends impDto{
  

  /**
   * 
   */
  private static final long serialVersionUID = 1L;
  private String empcode;
  private String empname;
  private String cardkind;
  private String cardnum;
  private String birthday;
  private String sex;
  private String dept;
  private String salarybase;
  private String orgpay;
  private String emppay;
  private String chgtype;
  private String partin;
  private String chgreason;
  private String payStatus;
  
  public String getBirthday() {
    return birthday;
  }
  public void setBirthday(String birthday) {
    this.birthday = birthday;
  }
  public String getCardkind() {
    return cardkind;
  }
  public void setCardkind(String cardkind) {
    this.cardkind = cardkind;
  }
  public String getCardnum() {
    return cardnum;
  }
  public void setCardnum(String cardnum) {
    this.cardnum = cardnum;
  }
  public String getChgtype() {
    return chgtype;
  }
  public void setChgtype(String chgtype) {
    this.chgtype = chgtype;
  }
  public String getDept() {
    return dept;
  }
  public void setDept(String dept) {
    this.dept = dept;
  }
  public String getEmpcode() {
    return empcode;
  }
  public void setEmpcode(String empcode) {
    this.empcode = empcode;
  }
  public String getEmpname() {
    return empname;
  }
  public void setEmpname(String empname) {
    this.empname = empname;
  }
  public String getEmppay() {
    return emppay;
  }
  public void setEmppay(String emppay) {
    this.emppay = emppay;
  }
  public String getOrgpay() {
    return orgpay;
  }
  public void setOrgpay(String orgpay) {
    this.orgpay = orgpay;
  }
  public String getPartin() {
    return partin;
  }
  public void setPartin(String partin) {
    this.partin = partin;
  }
  public String getSalarybase() {
    return salarybase;
  }
  public void setSalarybase(String salarybase) {
    this.salarybase = salarybase;
  }
  public String getSex() {
    return sex;
  }
  public void setSex(String sex) {
    this.sex = sex;
  }
  public String getChgreason() {
    return chgreason;
  }
  public void setChgreason(String chgreason) {
    this.chgreason = chgreason;
  }
  public String getPayStatus() {
    return payStatus;
  }
  public void setPayStatus(String payStatus) {
    this.payStatus = payStatus;
  }

}
