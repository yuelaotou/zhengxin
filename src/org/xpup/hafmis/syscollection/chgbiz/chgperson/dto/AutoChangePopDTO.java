package org.xpup.hafmis.syscollection.chgbiz.chgperson.dto;

/**
 * Copy Right Information : 封装了自动变更弹出框中纪录信息的DTO Goldsoft Project :
 * AutoChangePopDTO
 * 
 * @Version : v1.0
 * @author : 付云峰
 * @Date : 2008.3.18
 */
public class AutoChangePopDTO {
  /**
   * 职工编号
   */
  private String empId = "";
  /**
   * 职工姓名
   */
  private String empName = "";
  /**
   * 证件号码
   */
  private String cardNum = "";
  /**
   * 缴存状态
   */
  private String payStatus = "";
  
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
  public String getPayStatus() {
    return payStatus;
  }
  public void setPayStatus(String payStatus) {
    this.payStatus = payStatus;
  }
}
