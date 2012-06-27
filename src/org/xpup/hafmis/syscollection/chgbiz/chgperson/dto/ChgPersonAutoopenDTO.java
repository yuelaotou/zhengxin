package org.xpup.hafmis.syscollection.chgbiz.chgperson.dto;

/**
 * Copy Right Information :增加自动启封按钮，功能为：点击后，弹出窗口，显示该单位状态为转入封存的职工，可以进行选择（可全选），选择后点击确定后，将选中职工加入到变更清册中，变更类型为启封。
 * AutoChangePopAF
 * 
 * @Version : v1.0
 * @author : 吴洪涛
 * @Date : 2008.6.18
 */
public class ChgPersonAutoopenDTO {
  
  private String id = "";
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
  public String getId() {
    return id;
  }
  public void setId(String id) {
    this.id = id;
  }
}
