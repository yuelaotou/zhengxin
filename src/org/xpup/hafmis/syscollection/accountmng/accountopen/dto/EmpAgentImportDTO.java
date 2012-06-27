package org.xpup.hafmis.syscollection.accountmng.accountopen.dto;

import org.xpup.common.util.imp.domn.interfaces.impDto;

/**
 * Copy Right Information : 职工代扣导入列表的DTO Goldsoft Project : EmpAgentImportDTO
 * 
 * @Version : v1.0
 * @author : 付云峰
 * @Date : 2007.12.13
 */
public class EmpAgentImportDTO extends impDto {
  private String empId = "";

  private String empName = "";

  private String cardNum = "";

  private String empAgentNum = "";
  
  private String sex = "";
  private String birthday = "";
  private String cardKind = "";
  private String tel = "";
  private String mobileTle = "";
  private String monthIncome = "";
  private String department = "";

  public String getBirthday() {
    return birthday;
  }

  public void setBirthday(String birthday) {
    this.birthday = birthday;
  }

  public String getCardKind() {
    return cardKind;
  }

  public void setCardKind(String cardKind) {
    this.cardKind = cardKind;
  }

  public String getDepartment() {
    return department;
  }

  public void setDepartment(String department) {
    this.department = department;
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

  public String getSex() {
    return sex;
  }

  public void setSex(String sex) {
    this.sex = sex;
  }

  public String getTel() {
    return tel;
  }

  public void setTel(String tel) {
    this.tel = tel;
  }

  public String getEmpAgentNum() {
    return empAgentNum;
  }

  public void setEmpAgentNum(String empAgentNum) {
    this.empAgentNum = empAgentNum;
  }

  public String getEmpId() {
    return empId;
  }

  public void setEmpId(String empId) {
    this.empId = empId;
  }

  public String getCardNum() {
    return cardNum;
  }

  public void setCardNum(String cardNum) {
    this.cardNum = cardNum;
  }

  public String getEmpName() {
    return empName;
  }

  public void setEmpName(String empName) {
    this.empName = empName;
  }

}
