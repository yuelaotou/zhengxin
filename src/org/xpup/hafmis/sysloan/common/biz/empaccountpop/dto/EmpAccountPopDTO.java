/**
 * Copy Right Information   : Goldsoft 
 * Project                  : EmpAccountPopDTO
 * @Version                 : 1.0
 * @author                  : wangy
 * 生成日期                   :2007-11-02
 **/
package org.xpup.hafmis.sysloan.common.biz.empaccountpop.dto;

import java.math.BigDecimal;

public class EmpAccountPopDTO {

  private String id = null;

  private String docNum = null;// 凭证编号

  private String bizType = null;// 业务类型

  private String settDate = null;// 发生时间

  private String empId = null;// 职工编号

  private String orgId = null;// 单位编号

  private BigDecimal debit = new BigDecimal(0.00);// 借方发生额

  private BigDecimal credit = new BigDecimal(0.00);// 贷方发生额

  private BigDecimal interest = new BigDecimal(0.00);// 利息

  private BigDecimal debitTotal = new BigDecimal(0.00);// 借方发生额

  private BigDecimal creditTotal = new BigDecimal(0.00);// 贷方发生额

  private BigDecimal interestTotal = new BigDecimal(0.00);// 利息

  private Integer count = null;

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public Integer getCount() {
    return count;
  }

  public void setCount(Integer count) {
    this.count = count;
  }

  public BigDecimal getCreditTotal() {
    return creditTotal;
  }

  public void setCreditTotal(BigDecimal creditTotal) {
    this.creditTotal = creditTotal;
  }

  public BigDecimal getDebitTotal() {
    return debitTotal;
  }

  public void setDebitTotal(BigDecimal debitTotal) {
    this.debitTotal = debitTotal;
  }

  public String getEmpId() {
    return empId;
  }

  public void setEmpId(String empId) {
    this.empId = empId;
  }

  public String getOrgId() {
    return orgId;
  }

  public void setOrgId(String orgId) {
    this.orgId = orgId;
  }

  public BigDecimal getInterestTotal() {
    return interestTotal;
  }

  public void setInterestTotal(BigDecimal interestTotal) {
    this.interestTotal = interestTotal;
  }

  public String getBizType() {
    return bizType;
  }

  public void setBizType(String bizType) {
    this.bizType = bizType;
  }

  public BigDecimal getCredit() {
    return credit;
  }

  public void setCredit(BigDecimal credit) {
    this.credit = credit;
  }

  public BigDecimal getDebit() {
    return debit;
  }

  public void setDebit(BigDecimal debit) {
    this.debit = debit;
  }

  public String getDocNum() {
    return docNum;
  }

  public void setDocNum(String docNum) {
    this.docNum = docNum;
  }

  public BigDecimal getInterest() {
    return interest;
  }

  public void setInterest(BigDecimal interest) {
    this.interest = interest;
  }

  public String getSettDate() {
    return settDate;
  }

  public void setSettDate(String settDate) {
    this.settDate = settDate;
  }

}
