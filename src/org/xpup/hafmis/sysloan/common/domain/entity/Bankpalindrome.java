package org.xpup.hafmis.sysloan.common.domain.entity;

import java.io.Serializable;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

public class Bankpalindrome implements Serializable{
  private Integer bankId;
  private String rowNum;
  /** nullable persistent field */
  private String reserveaA;

  /** nullable persistent field */
  private String reserveaB;

  /** nullable persistent field */
  private String reserveaC;
  
  public String getReserveaA() {
    return reserveaA;
  }

  public void setReserveaA(String reserveaA) {
    this.reserveaA = reserveaA;
  }

  public String getReserveaB() {
    return reserveaB;
  }

  public void setReserveaB(String reserveaB) {
    this.reserveaB = reserveaB;
  }

  public String getReserveaC() {
    return reserveaC;
  }

  public void setReserveaC(String reserveaC) {
    this.reserveaC = reserveaC;
  }

  public Integer getBankId() {
    return bankId;
  }

  public void setBankId(Integer bankId) {
    this.bankId = bankId;
  }

  public String getRowNum() {
    return rowNum;
  }

  public void setRowNum(String rowNum) {
    this.rowNum = rowNum;
  }

  public String toString() {
    return new ToStringBuilder(this)
        .append("bankId", getBankId())
        .toString();
}

public boolean equals(Object other) {
    if ( !(other instanceof Bankpalindrome) ) return false;
    Bankpalindrome castOther = (Bankpalindrome) other;
    return new EqualsBuilder()
        .append(this.getBankId(), castOther.getBankId())
        .isEquals();
}

public int hashCode() {
    return new HashCodeBuilder()
        .append(getBankId())
        .toHashCode();
}
}
