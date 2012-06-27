package org.xpup.hafmis.syscollection.common.domain.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.xpup.hafmis.syscommon.domain.entity.DomainObject;

/** @author Hibernate CodeGenerator */
public class MonthPaymentHead extends DomainObject {

    private PaymentHead paymentHead=null; 
    
    /** persistent field */

    /** persistent field */
    private String payMonth;
    /**±¸Ñ¡a*/
    private String reserveaA;
    private String reserveaB;
    private String reserveaC;
    private Integer personCount = new Integer(0);
    private BigDecimal orgSumpay = new BigDecimal(0.00);
    private BigDecimal empSumpay = new BigDecimal(0.00);
    private BigDecimal addpayMoney = new BigDecimal(0.00);

    /** full constructor */
    public MonthPaymentHead(PaymentHead paymentHead,String payMonth,String reserveaA, String reserveaB, String reserveaC) {
        this.paymentHead = paymentHead;
        this.payMonth = payMonth;
        this.reserveaA = reserveaA;
        this.reserveaB = reserveaB;
        this.reserveaC = reserveaC;
       
    }

    /** default constructor */
    public MonthPaymentHead() {
    }

    public String getPayMonth() {
        return this.payMonth;
    }

    public void setPayMonth(String payMonth) {
        this.payMonth = payMonth;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("id", getId())
            .toString();
    }

    public boolean equals(Object other) {
        if ( !(other instanceof MonthPaymentHead) ) return false;
        MonthPaymentHead castOther = (MonthPaymentHead) other;
        return new EqualsBuilder()
            .append(this.getId(), castOther.getId())
            .isEquals();
    }

    public int hashCode() {
        return new HashCodeBuilder()
            .append(getId())
            .toHashCode();
    }

    public PaymentHead getPaymentHead() {
      return paymentHead;
    }

    public void setPaymentHead(PaymentHead paymentHead) {
      this.paymentHead = paymentHead;
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

    public BigDecimal getAddpayMoney() {
      return addpayMoney;
    }

    public void setAddpayMoney(BigDecimal addpayMoney) {
      this.addpayMoney = addpayMoney;
    }

    public BigDecimal getEmpSumpay() {
      return empSumpay;
    }

    public void setEmpSumpay(BigDecimal empSumpay) {
      this.empSumpay = empSumpay;
    }

    public BigDecimal getOrgSumpay() {
      return orgSumpay;
    }

    public void setOrgSumpay(BigDecimal orgSumpay) {
      this.orgSumpay = orgSumpay;
    }

    public Integer getPersonCount() {
      return personCount;
    }

    public void setPersonCount(Integer personCount) {
      this.personCount = personCount;
    }

}
