package org.xpup.hafmis.sysloan.common.domain.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/** @author Hibernate CodeGenerator */
public class LoanRate implements Serializable {

    /** identifier field */
    private Integer loanRateId ;

    /** persistent field */
    private String loanRateType;

    /** persistent field */
    private BigDecimal loanMonthRate=new BigDecimal(0.00);

    /** persistent field */
    private String office;

    /** nullable persistent field */
    private String appDate;

    /** persistent field */
    private String ajustDate;

    /** persistent field */
    private String status;

    /** nullable persistent field */
    private String reserveaA;

    /** nullable persistent field */
    private String reserveaB;

    /** nullable persistent field */
    private String reserveaC;
    
    private String appMode;
    
    private String operator;

    private String adjustBasis = "";
    
    public String getAdjustBasis() {
      return adjustBasis;
    }

    public void setAdjustBasis(String adjustBasis) {
      this.adjustBasis = adjustBasis;
    }

    /** full constructor */
    public LoanRate(Integer loanRateId, String loanRateType, BigDecimal loanMonthRate, String office, String appDate, String ajustDate, String status, String reserveaA, String reserveaB, String reserveaC,String appMode,String operator) {
        this.loanRateId = loanRateId;
        this.loanRateType = loanRateType;
        this.loanMonthRate = loanMonthRate;
        this.office = office;
        this.appDate = appDate;
        this.ajustDate = ajustDate;
        this.status = status;
        this.reserveaA = reserveaA;
        this.reserveaB = reserveaB;
        this.reserveaC = reserveaC;
        this.appMode = appMode;
        this.operator = operator;
    }

    /** default constructor */
    public LoanRate() {
    }

    /** minimal constructor */
    public LoanRate(Integer loanRateId, String loanRateType, BigDecimal loanMonthRate, String office, String ajustDate, String status,String operator) {
        this.loanRateId = loanRateId;
        this.loanRateType = loanRateType;
        this.loanMonthRate = loanMonthRate;
        this.office = office;
        this.ajustDate = ajustDate;
        this.status = status;
        this.operator = operator;
    }

    public Integer getLoanRateId() {
        return this.loanRateId;
    }

    public void setLoanRateId(Integer loanRateId) {
        this.loanRateId = loanRateId;
    }

    public String getLoanRateType() {
        return this.loanRateType;
    }

    public void setLoanRateType(String loanRateType) {
        this.loanRateType = loanRateType;
    }

    public BigDecimal getLoanMonthRate() {
        return this.loanMonthRate;
    }

    public void setLoanMonthRate(BigDecimal loanMonthRate) {
        this.loanMonthRate = loanMonthRate;
    }

    public String getOffice() {
        return this.office;
    }

    public void setOffice(String office) {
        this.office = office;
    }

    public String getAppDate() {
        return this.appDate;
    }

    public void setAppDate(String appDate) {
        this.appDate = appDate;
    }

    public String getAjustDate() {
        return this.ajustDate;
    }

    public void setAjustDate(String ajustDate) {
        this.ajustDate = ajustDate;
    }

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getReserveaA() {
        return this.reserveaA;
    }

    public void setReserveaA(String reserveaA) {
        this.reserveaA = reserveaA;
    }

    public String getReserveaB() {
        return this.reserveaB;
    }

    public void setReserveaB(String reserveaB) {
        this.reserveaB = reserveaB;
    }

    public String getReserveaC() {
        return this.reserveaC;
    }

    public void setReserveaC(String reserveaC) {
        this.reserveaC = reserveaC;
    }

    public String getAppMode() {
      return appMode;
    }

    public void setAppMode(String appMode) {
      this.appMode = appMode;
    }

    public String getOperator() {
      return operator;
    }

    public void setOperator(String operator) {
      this.operator = operator;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("loanRateId", getLoanRateId())
            .toString();
    }

    public boolean equals(Object other) {
        if ( !(other instanceof LoanRate) ) return false;
        LoanRate castOther = (LoanRate) other;
        return new EqualsBuilder()
            .append(this.getLoanRateId(), castOther.getLoanRateId())
            .isEquals();
    }

    public int hashCode() {
        return new HashCodeBuilder()
            .append(getLoanRateId())
            .toHashCode();
    }

}
