package org.xpup.hafmis.syscollection.accountmng.accountopen.dto;

import java.math.BigDecimal;

import org.xpup.common.util.exp.domn.interfaces.ExpDto;
/**
 * Copy Right Information : 职工代扣导出DTO Goldsoft Project :
 * EmpAgentExportDTO
 * 
 * @Version : v1.0
 * @author : 付云峰
 * @Date : 2007.12.13
 */
public class EmpAgentExportDTO implements ExpDto {

  public String getInfo(String info) {
    if (info.equals("empId")) {
      return this.empId;
    }
    if (info.equals("empName")) {
      return this.empName;
    }
    if (info.equals("cardNum")) {
      return this.cardNum;
    }
    if (info.equals("empAgentNum")) {
      return this.empAgentNum;
    }
    if (info.equals("sex")) {
      return this.sex;
    }
    if (info.equals("birthday")) {
      return this.birthday;
    }
    if (info.equals("cardKind")) {
      return this.cardKind;
    }
    if (info.equals("tel")) {
      return this.tel;
    }
    if (info.equals("mobileTle")) {
      return this.mobileTle;
    }
    if (info.equals("monthIncome")) {
      return this.monthIncome;
    }
    if (info.equals("department")) {
      return this.department;
    }
    return null;
  }

  private String empId = "";

  private String empName = "";

  private String cardNum = "";

  private String empAgentNum = "";
  //-------------------//
  private String sex = "";
  private String birthday = "";
  private String cardKind = "";
  private String tel = "";
  private String mobileTle = "";
  private String monthIncome = "";
  private String department = "";

  public String getDepartment() {
    return department;
  }

  public void setDepartment(String department) {
    this.department = department;
  }

  public String getEmpAgentNum() {
    return empAgentNum;
  }

  public void setEmpAgentNum(String empAgentNum) {
    this.empAgentNum = empAgentNum;
  }

  public String getCardNum() {
    return cardNum;
  }

  public void setCardNum(String cardNum) {
    this.cardNum = cardNum;
  }

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

  public String getMonthIncome() {
    return monthIncome;
  }

  public void setMonthIncome(String monthIncome) {
    this.monthIncome = monthIncome;
  }

  public String getMobileTle() {
    return mobileTle;
  }

  public void setMobileTle(String mobileTle) {
    this.mobileTle = mobileTle;
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

}
