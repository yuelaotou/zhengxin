package org.xpup.hafmis.syscollection.common.domain.entity;

import java.math.BigDecimal;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.xpup.hafmis.syscommon.domain.entity.DomainObject;

/** @author Hibernate CodeGenerator */
public class SearchLackInfo  extends DomainObject {

    /** persistent field */
    private Org org;

    /** persistent field */
    private String yearMonth;

    /** persistent field */
    private BigDecimal oegPay;

    /** persistent field */
    private BigDecimal empPay;

    /** nullable persistent field */
    private BigDecimal orgPayReal ;

    /** nullable persistent field */
    private BigDecimal empPayReal ;

    /** full constructor */
    public SearchLackInfo(Org org, String yearMonth, BigDecimal oegPay, BigDecimal empPay, BigDecimal orgPayReal, BigDecimal empPayReal) {
        this.org = org;
        this.yearMonth = yearMonth;
        this.oegPay = oegPay;
        this.empPay = empPay;
        this.orgPayReal = orgPayReal;
        this.empPayReal = empPayReal;
    }

    /** default constructor */
    public SearchLackInfo() {
    }

    /** minimal constructor */
    public SearchLackInfo(Org org, String yearMonth, BigDecimal oegPay, BigDecimal empPay) {
        this.org = org;
        this.yearMonth = yearMonth;
        this.oegPay = oegPay;
        this.empPay = empPay;
    }

    public Org getOrg() {
        return this.org;
    }

    public void setOrg(Org org) {
        this.org = org;
    }

    public String getYearMonth() {
        return this.yearMonth;
    }

    public void setYearMonth(String yearMonth) {
        this.yearMonth = yearMonth;
    }

    public BigDecimal getOegPay() {
        return this.oegPay;
    }

    public void setOegPay(BigDecimal oegPay) {
        this.oegPay = oegPay;
    }

    public BigDecimal getEmpPay() {
        return this.empPay;
    }

    public void setEmpPay(BigDecimal empPay) {
        this.empPay = empPay;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("id", getId())
            .toString();
    }

    public boolean equals(Object other) {
        if ( !(other instanceof SearchLackInfo) ) return false;
        SearchLackInfo castOther = (SearchLackInfo) other;
        return new EqualsBuilder()
            .append(this.getId(), castOther.getId())
            .isEquals();
    }

    public int hashCode() {
        return new HashCodeBuilder()
            .append(getId())
            .toHashCode();
    }

    public BigDecimal getEmpPayReal() {
      return empPayReal;
    }

    public void setEmpPayReal(BigDecimal empPayReal) {
      this.empPayReal = empPayReal;
    }

    public BigDecimal getOrgPayReal() {
      return orgPayReal;
    }

    public void setOrgPayReal(BigDecimal orgPayReal) {
      this.orgPayReal = orgPayReal;
    }

}
