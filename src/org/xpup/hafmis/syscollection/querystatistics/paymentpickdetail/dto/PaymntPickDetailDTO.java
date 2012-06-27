package org.xpup.hafmis.syscollection.querystatistics.paymentpickdetail.dto;

import java.math.BigDecimal;
import java.util.List;

public class PaymntPickDetailDTO {

  private String office = "";

  private String collBankId = "";
  
  private String collBankName = "";
  
  private String date = "";

  private String settDate = "";
  
  private String bizType = "";
  
  private BigDecimal debit;

  private BigDecimal credit;

  private BigDecimal payMoney;

  private BigDecimal pickMoney;
  
  private List list;

  public List getList() {
    return list;
  }

  public void setList(List list) {
    this.list = list;
  }

  public String getOffice() {
    return office;
  }

  public void setOffice(String office) {
    this.office = office;
  }

  public String getBizType() {
    return bizType;
  }

  public void setBizType(String bizType) {
    this.bizType = bizType;
  }

  public String getCollBankId() {
    return collBankId;
  }

  public void setCollBankId(String collBankId) {
    this.collBankId = collBankId;
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

  public BigDecimal getPayMoney() {
    return payMoney;
  }

  public void setPayMoney(BigDecimal payMoney) {
    this.payMoney = payMoney;
  }

  public BigDecimal getPickMoney() {
    return pickMoney;
  }

  public void setPickMoney(BigDecimal pickMoney) {
    this.pickMoney = pickMoney;
  }

  public String getSettDate() {
    return settDate;
  }

  public void setSettDate(String settDate) {
    this.settDate = settDate;
  }

  public String getDate() {
    return date;
  }

  public void setDate(String date) {
    this.date = date;
  }

  public String getCollBankName() {
    return collBankName;
  }

  public void setCollBankName(String collBankName) {
    this.collBankName = collBankName;
  }

}
