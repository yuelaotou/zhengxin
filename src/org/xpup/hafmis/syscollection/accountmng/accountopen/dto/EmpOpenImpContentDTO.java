package org.xpup.hafmis.syscollection.accountmng.accountopen.dto;
import org.xpup.common.util.imp.domn.interfaces.impDto;

public class EmpOpenImpContentDTO extends impDto{
  
  
  private String empname;
  private String cardkind;
  private String cardnum;
  private String dept;
  private String tel="";
  private String mobileTle;
  private String monthIncome;
  private String salarybase;
  private String orgpay;
  private String emppay;
  

  public String getCardnum() {
    return cardnum;
  }
  public void setCardnum(String cardnum) {
    this.cardnum = cardnum;
  }
  public String getCardkind() {
    return cardkind;
  }
  public void setCardkind(String cardkind) {
    this.cardkind = cardkind;
  }
  public String getDept() {
    return dept;
  }
  public void setDept(String dept) {
    this.dept = dept;
  }
  public String getMobileTle() {
    return mobileTle;
  }
  public void setMobileTle(String mobileTle) {
    this.mobileTle = mobileTle;
  }
  public String getMonthIncome() {
    return monthIncome;
  }
  public void setMonthIncome(String monthIncome) {
    this.monthIncome = monthIncome;
  }
  public String getTel() {
    return tel;
  }
  public void setTel(String tel) {
    this.tel = tel;
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
  public String getSalarybase() {
    return salarybase;
  }
  public void setSalarybase(String salarybase) {
    this.salarybase = salarybase;
  }


}
