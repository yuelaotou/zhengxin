package org.xpup.hafmis.sysloan.loanapply.specialapply.dto;

import java.io.Serializable;
import java.math.BigDecimal;

public class SpecialapplyInfoDTO implements Serializable {

  private static final long serialVersionUID = 1L;

  private String id = "";
  
  // 借款人职工编号
  private String privilegeBorrowerId = "";

  // 借款人姓名
  private String borrowerName = "";

  // 证件号码
  private String cardNum = "";

  // 贷款金额
  private BigDecimal loanMoney = new BigDecimal(0.00);

  // 贷款期限
  private Integer loanTimeLimit = new Integer(0);

  // 办理人员
  private String operator = "";

  // 办理日期
  private String opTime = "";

  // 状态
  private String status = "";

  // 职工编号
  private String empId = "";

  private String remark = "";

  public String getRemark() {
    return remark;
  }

  public void setRemark(String remark) {
    this.remark = remark;
  }

  public String getEmpId() {
    return empId;
  }

  public void setEmpId(String empId) {
    this.empId = empId;
  }

  public String getBorrowerName() {
    return borrowerName;
  }

  public void setBorrowerName(String borrowerName) {
    this.borrowerName = borrowerName;
  }

  public String getCardNum() {
    return cardNum;
  }

  public void setCardNum(String cardNum) {
    this.cardNum = cardNum;
  }

  public String getPrivilegeBorrowerId() {
    return privilegeBorrowerId;
  }

  public void setPrivilegeBorrowerId(String privilegeBorrowerId) {
    this.privilegeBorrowerId = privilegeBorrowerId;
  }

  public BigDecimal getLoanMoney() {
    return loanMoney;
  }

  public void setLoanMoney(BigDecimal loanMoney) {
    this.loanMoney = loanMoney;
  }

  public Integer getLoanTimeLimit() {
    return loanTimeLimit;
  }

  public void setLoanTimeLimit(Integer loanTimeLimit) {
    this.loanTimeLimit = loanTimeLimit;
  }

  public String getOperator() {
    return operator;
  }

  public void setOperator(String operator) {
    this.operator = operator;
  }

  public String getOpTime() {
    return opTime;
  }

  public void setOpTime(String opTime) {
    this.opTime = opTime;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }
}
