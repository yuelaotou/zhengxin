package org.xpup.hafmis.syscollection.chgbiz.chgperson.dto;

import java.math.BigDecimal;

public class ChgPersonHeadFormule {
  private Integer sumChgPerson;
  private Integer insChgperson;
  private Integer mulChgperson;
  private Integer beforeChgperson;
  private BigDecimal insPayment;
  private BigDecimal mulPayment;
  private BigDecimal sumOrgPay;
  private BigDecimal sumEmpPay;
  private BigDecimal sumSumPay;
  
  private BigDecimal oldOldPayment;
  
  private BigDecimal newOldPayment;
  
  private String headId="";


  public BigDecimal getInsPayment() {
    return insPayment;
  }

  public void setInsPayment(BigDecimal insPayment) {
    this.insPayment = insPayment;
  }


  public BigDecimal getMulPayment() {
    return mulPayment;
  }

  public void setMulPayment(BigDecimal mulPayment) {
    this.mulPayment = mulPayment;
  }

  public BigDecimal getOldOldPayment() {
    return oldOldPayment;
  }

  public void setOldOldPayment(BigDecimal oldOldPayment) {
    this.oldOldPayment = oldOldPayment;
  }

  public Integer getInsChgperson() {
    return insChgperson;
  }

  public void setInsChgperson(Integer insChgperson) {
    this.insChgperson = insChgperson;
  }

  public Integer getMulChgperson() {
    return mulChgperson;
  }

  public void setMulChgperson(Integer mulChgperson) {
    this.mulChgperson = mulChgperson;
  }

  public Integer getSumChgPerson() {
    return sumChgPerson;
  }

  public void setSumChgPerson(Integer sumChgPerson) {
    this.sumChgPerson = sumChgPerson;
  }

  public BigDecimal getSumEmpPay() {
    return sumEmpPay;
  }

  public void setSumEmpPay(BigDecimal sumEmpPay) {
    this.sumEmpPay = sumEmpPay;
  }

  public BigDecimal getSumOrgPay() {
    return sumOrgPay;
  }

  public void setSumOrgPay(BigDecimal sumOrgPay) {
    this.sumOrgPay = sumOrgPay;
  }

  public BigDecimal getSumSumPay() {
    return sumSumPay;
  }

  public void setSumSumPay(BigDecimal sumSumPay) {
    this.sumSumPay = sumSumPay;
  }

  public BigDecimal getNewOldPayment() {
    return newOldPayment;
  }

  public void setNewOldPayment(BigDecimal newOldPayment) {
    this.newOldPayment = newOldPayment;
  }

  public Integer getBeforeChgperson() {
    return beforeChgperson;
  }

  public void setBeforeChgperson(Integer beforeChgperson) {
    this.beforeChgperson = beforeChgperson;
  }

  public String getHeadId() {
    return headId;
  }

  public void setHeadId(String headId) {
    this.headId = headId;
  }
}
