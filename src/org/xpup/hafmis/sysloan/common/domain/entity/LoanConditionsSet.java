package org.xpup.hafmis.sysloan.common.domain.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/** @author Hibernate CodeGenerator */
public class LoanConditionsSet implements Serializable {

    /** identifier field */
    private Integer loanParamId;

    /** persistent field */
    private String paramDescrip;

    /** nullable persistent field */
    private String paramValue;

    /** persistent field */
    private String paramNum;

    /** nullable persistent field */
    private String paramExplain;

    /** persistent field */
    private String office;

    /** nullable persistent field */
    private String paramType;
    
    private String isUse;

    /** nullable persistent field */
    private String reserveaA;

    /** nullable persistent field */
    private String reserveaB;

    /** nullable persistent field */
    private String reserveaC;

    /** full constructor */
    public LoanConditionsSet(Integer loanParamId, String paramDescrip, String paramValue, String paramNum, String paramExplain, String office, String paramType, String reserveaA, String reserveaB, String reserveaC,String isUse) {
        this.loanParamId = loanParamId;
        this.paramDescrip = paramDescrip;
        this.paramValue = paramValue;
        this.paramNum = paramNum;
        this.paramExplain = paramExplain;
        this.office = office;
        this.paramType = paramType;
        this.reserveaA = reserveaA;
        this.reserveaB = reserveaB;
        this.reserveaC = reserveaC;
        this.isUse = isUse;
    }

    /** default constructor */
    public LoanConditionsSet() {
    }

    /** minimal constructor */
    public LoanConditionsSet(Integer loanParamId, String paramDescrip, String paramNum, String office,String isUse) {
        this.loanParamId = loanParamId;
        this.paramDescrip = paramDescrip;
        this.paramNum = paramNum;
        this.office = office;
        this.isUse = isUse;
    }

    public String getIsUse() {
      return isUse;
    }

    public void setIsUse(String isUse) {
      this.isUse = isUse;
    }

    public Integer getLoanParamId() {
        return this.loanParamId;
    }

    public void setLoanParamId(Integer loanParamId) {
        this.loanParamId = loanParamId;
    }

    public String getParamDescrip() {
        return this.paramDescrip;
    }

    public void setParamDescrip(String paramDescrip) {
        this.paramDescrip = paramDescrip;
    }

    public String getParamValue() {
        return this.paramValue;
    }

    public void setParamValue(String paramValue) {
        this.paramValue = paramValue;
    }

    public String getParamNum() {
        return this.paramNum;
    }

    public void setParamNum(String paramNum) {
        this.paramNum = paramNum;
    }

    public String getParamExplain() {
        return this.paramExplain;
    }

    public void setParamExplain(String paramExplain) {
        this.paramExplain = paramExplain;
    }

    
    public String getParamType() {
        return this.paramType;
    }

    public void setParamType(String paramType) {
        this.paramType = paramType;
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

    public String toString() {
        return new ToStringBuilder(this)
            .append("loanParamId", getLoanParamId())
            .toString();
    }

    public boolean equals(Object other) {
        if ( !(other instanceof LoanBankPara) ) return false;
        LoanBankPara castOther = (LoanBankPara) other;
        return new EqualsBuilder()
            .append(this.getLoanParamId(), castOther.getLoanParamId())
            .isEquals();
    }

    public int hashCode() {
        return new HashCodeBuilder()
            .append(getLoanParamId())
            .toHashCode();
    }

    public String getOffice() {
      return office;
    }

    public void setOffice(String office) {
      this.office = office;
    }

}
