package org.xpup.hafmis.sysloan.common.domain.entity;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

public class PalindromeFormatTail {
  private Integer id;
  private Integer flowHeadId;
  private String centerSet;
  private String formula;
  /** nullable persistent field */
  private String reserveaA;

  /** nullable persistent field */
  private String reserveaB;

  /** nullable persistent field */
  private String reserveaC;

  public String getCenterSet() {
    return centerSet;
  }

  public void setCenterSet(String centerSet) {
    this.centerSet = centerSet;
  }

  public Integer getFlowHeadId() {
    return flowHeadId;
  }

  public void setFlowHeadId(Integer flowHeadId) {
    this.flowHeadId = flowHeadId;
  }

  public String getFormula() {
    return formula;
  }

  public void setFormula(String formula) {
    this.formula = formula;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

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
  public String toString() {
    return new ToStringBuilder(this)
        .append("id", getId())
        .toString();
}

public boolean equals(Object other) {
    if ( !(other instanceof Bankpalindrome) ) return false;
    PalindromeFormatTail castOther = (PalindromeFormatTail) other;
    return new EqualsBuilder()
        .append(this.getId(), castOther.getId())
        .isEquals();
}

public int hashCode() {
    return new HashCodeBuilder()
        .append(getId())
        .toHashCode();
}
}
