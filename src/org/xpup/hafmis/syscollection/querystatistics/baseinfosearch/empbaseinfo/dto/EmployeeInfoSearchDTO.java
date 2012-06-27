package org.xpup.hafmis.syscollection.querystatistics.baseinfosearch.empbaseinfo.dto;
import java.util.Map;
public class EmployeeInfoSearchDTO {
  private String orgId;
  private String orgName;
  private String empId;
  private String empName;
  private String salaryStart;
  private String salaryEnd;
  private String orgPayStart;
  private String orgPayEnd;
  private String empPayStart;
  private String empPayEnd;
  private String identityCard;
  private String sex;
  private String payState;
  private String empBalance;
  //ÎâºéÌÎÐÞ¸Ä2008£­3£­11
  private String loanYesNo;
  //ÎâºéÌÎÐÞ¸Ä2008£­3£­11//ÊÇ·ñ´æÔÚ´û¿î  0ÊÇ,1·ñ
  
  public String getEmpId() {
    return empId;
  }
  public void setEmpId(String empId) {
    this.empId = empId;
  }
  public String getEmpName() {
    return empName;
  }
  public void setEmpName(String empName) {
    this.empName = empName;
  }
  public String getEmpPayEnd() {
    return empPayEnd;
  }
  public void setEmpPayEnd(String empPayEnd) {
    this.empPayEnd = empPayEnd;
  }
  public String getEmpPayStart() {
    return empPayStart;
  }
  public void setEmpPayStart(String empPayStart) {
    this.empPayStart = empPayStart;
  }
   
  public String getIdentityCard() {
    return identityCard;
  }
  public void setIdentityCard(String identityCard) {
    this.identityCard = identityCard;
  }
  public String getOrgId() {
    return orgId;
  }
  public void setOrgId(String orgId) {
    this.orgId = orgId;
  }
  public String getOrgName() {
    return orgName;
  }
  public void setOrgName(String orgName) {
    this.orgName = orgName;
  }
  public String getOrgPayEnd() {
    return orgPayEnd;
  }
  public void setOrgPayEnd(String orgPayEnd) {
    this.orgPayEnd = orgPayEnd;
  }
  public String getOrgPayStart() {
    return orgPayStart;
  }
  public void setOrgPayStart(String orgPayStart) {
    this.orgPayStart = orgPayStart;
  }
  public String getSalaryEnd() {
    return salaryEnd;
  }
  public void setSalaryEnd(String salaryEnd) {
    this.salaryEnd = salaryEnd;
  }
  public String getSalaryStart() {
    return salaryStart;
  }
  public void setSalaryStart(String salaryStart) {
    this.salaryStart = salaryStart;
  }
  public String getPayState() {
    return payState;
  }
  public void setPayState(String payState) {
    this.payState = payState;
  }
  public String getSex() {
    return sex;
  }
  public void setSex(String sex) {
    this.sex = sex;
  }
  public String getLoanYesNo() {
    return loanYesNo;
  }
  public void setLoanYesNo(String loanYesNo) {
    this.loanYesNo = loanYesNo;
  }
  public String getEmpBalance() {
    return empBalance;
  }
  public void setEmpBalance(String empBalance) {
    this.empBalance = empBalance;
  }
}
