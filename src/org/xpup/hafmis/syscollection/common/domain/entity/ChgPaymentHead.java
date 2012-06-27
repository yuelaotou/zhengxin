package org.xpup.hafmis.syscollection.common.domain.entity;

import java.io.Serializable;
import java.math.BigDecimal;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.xpup.hafmis.syscommon.domain.entity.DomainObject;


/** @author Hibernate CodeGenerator */
public class ChgPaymentHead extends DomainObject {


  private static final long serialVersionUID = 7913155381770367906L;
  
  private PaymentHead paymentHead;
    /** persistent field */
    private Org org=new Org();

    /** persistent field */
    private String chgMonth="";

    /** persistent field */
    private String bizDate="";

    /** persistent field */
    private Integer chgStatus=new Integer(0);

    /** persistent field */
    private String reserveaA="";
    
    /** persistent field */
    private String reserveaB="";
    
    /** persistent field */
    private String reserveaC="";
    
    private BigDecimal oldPayment = new BigDecimal(0.00);
    private BigDecimal oldSlarayBase = new BigDecimal(0.00);

    /** full constructor */
    public ChgPaymentHead( Org org, String chgMonth, String bizDate,Integer chgStatus,PaymentHead paymentHead,
        String reserveaA,String reserveaB,String reserveaC,BigDecimal oldPayment,BigDecimal oldSlarayBase ) {
       
        this.org = org;
        this.chgMonth = chgMonth;
        this.bizDate = bizDate;
        this.chgStatus = chgStatus;
        this.paymentHead = paymentHead;
        this.reserveaA=reserveaA;
        this.reserveaB=reserveaB;
        this.reserveaC=reserveaC;
        this.oldPayment = oldPayment;
        this.oldSlarayBase = oldSlarayBase;
    }

    /** default constructor */
    public ChgPaymentHead() {
    }

    /** minimal constructor */
    public ChgPaymentHead( Org org, String chgMonth, String bizDate, Integer chgStatus,BigDecimal oldPayment,BigDecimal oldSlarayBase,PaymentHead paymentHead ) {
        this.paymentHead = paymentHead;
        this.org = org;
        this.chgMonth = chgMonth;
        this.bizDate = bizDate;
        this.chgStatus = chgStatus;
        this.oldPayment = oldPayment;
        this.oldSlarayBase = oldSlarayBase;
    }

    public String getChgMonth() {
        return this.chgMonth;
    }

    public void setChgMonth(String chgMonth) {
        this.chgMonth = chgMonth;
    }

    public String getBizDate() {
        return this.bizDate;
    }

    public void setBizDate(String bizDate) {
        this.bizDate = bizDate;
    }

    public Integer getChgStatus() {
        return this.chgStatus;
    }

    public void setChgStatus(Integer chgStatus) {
        this.chgStatus = chgStatus;
    }

    public Org getOrg() {
      return org;
    }

    public void setOrg(Org org) {
      this.org = org;
    }


    public String toString() {
        return new ToStringBuilder(this)
            .append("id", getId())
            .toString();
    }

    public boolean equals(Object other) {
        if ( !(other instanceof ChgPaymentHead) ) return false;
        ChgPaymentHead castOther = (ChgPaymentHead) other;
        return new EqualsBuilder()
            .append(this.getId(), castOther.getId())
            .isEquals();
    }

    public int hashCode() {
        return new HashCodeBuilder()
            .append(getId())
            .toHashCode();
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

    public BigDecimal getOldPayment() {
      return oldPayment;
    }

    public void setOldPayment(BigDecimal oldPayment) {
      this.oldPayment = oldPayment;
    }

    public BigDecimal getOldSlarayBase() {
      return oldSlarayBase;
    }

    public void setOldSlarayBase(BigDecimal oldSlarayBase) {
      this.oldSlarayBase = oldSlarayBase;
    }

    public static long getSerialVersionUID() {
      return serialVersionUID;
    }

    public PaymentHead getPaymentHead() {
      return paymentHead;
    }

    public void setPaymentHead(PaymentHead paymentHead) {
      this.paymentHead = paymentHead;
    }

}
