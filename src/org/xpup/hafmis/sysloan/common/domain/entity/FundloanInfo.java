package org.xpup.hafmis.sysloan.common.domain.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/** @author Hibernate CodeGenerator */
public class FundloanInfo implements Serializable {

    /** identifier field */
    private Integer id;

    /** nullable persistent field */
    private String loanKouAcc;

    /** nullable persistent field */
    private String contractId;

    /** persistent field */
    private String empId;

    /** nullable persistent field */
    private String orgId;

    /** nullable persistent field */
    private String empName;

    /** persistent field */
    private String cardKind;

    /** persistent field */
    private String cardNum;

    /** nullable persistent field */
    private String seq;

    /** nullable persistent field */
    private String status;

    /** nullable persistent field */
    private String reserveaA;

    /** nullable persistent field */
    private String reserveaB;

    /** nullable persistent field */
    private String reserveaC;

    private String datestop;
    /** full constructor */
    public FundloanInfo(Integer id, String loanKouAcc, String contractId, String empId, String orgId, String empName, String cardKind, String cardNum, String seq, String status, String reserveaA, String reserveaB, String reserveaC,String datestop) {
        this.id = id;
        this.loanKouAcc = loanKouAcc;
        this.contractId = contractId;
        this.empId = empId;
        this.orgId = orgId;
        this.empName = empName;
        this.cardKind = cardKind;
        this.cardNum = cardNum;
        this.seq = seq;
        this.status = status;
        this.reserveaA = reserveaA;
        this.reserveaB = reserveaB;
        this.reserveaC = reserveaC;
        this.datestop = datestop;
    }

    /** default constructor */
    public FundloanInfo() {
    }

    /** minimal constructor */
    public FundloanInfo(Integer id, String empId, String cardKind, String cardNum) {
        this.id = id;
        this.empId = empId;
        this.cardKind = cardKind;
        this.cardNum = cardNum;
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLoanKouAcc() {
        return this.loanKouAcc;
    }

    public void setLoanKouAcc(String loanKouAcc) {
        this.loanKouAcc = loanKouAcc;
    }

    public String getContractId() {
        return this.contractId;
    }

    public void setContractId(String contractId) {
        this.contractId = contractId;
    }

    public String getEmpId() {
        return this.empId;
    }

    public void setEmpId(String empId) {
        this.empId = empId;
    }

    public String getOrgId() {
        return this.orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    public String getEmpName() {
        return this.empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }

    public String getCardKind() {
        return this.cardKind;
    }

    public void setCardKind(String cardKind) {
        this.cardKind = cardKind;
    }

    public String getCardNum() {
        return this.cardNum;
    }

    public void setCardNum(String cardNum) {
        this.cardNum = cardNum;
    }

    public String getSeq() {
        return this.seq;
    }

    public void setSeq(String seq) {
        this.seq = seq;
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

    public String toString() {
        return new ToStringBuilder(this)
            .append("id", getId())
            .toString();
    }

    public boolean equals(Object other) {
        if ( !(other instanceof FundloanInfo) ) return false;
        FundloanInfo castOther = (FundloanInfo) other;
        return new EqualsBuilder()
            .append(this.getId(), castOther.getId())
            .isEquals();
    }

    public int hashCode() {
        return new HashCodeBuilder()
            .append(getId())
            .toHashCode();
    }

    public String getDatestop() {
      return datestop;
    }

    public void setDatestop(String datestop) {
      this.datestop = datestop;
    }

}
