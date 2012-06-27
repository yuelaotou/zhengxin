package org.xpup.hafmis.sysloan.common.domain.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/** @author Hibernate CodeGenerator */
public class CongealInfo implements Serializable {

    /** identifier field */
    private Integer id;

    /** persistent field */
    private String contractId;

    /** nullable persistent field */
    private BigDecimal orgId;

    /** nullable persistent field */
    private BigDecimal empId;

    /** nullable persistent field */
    private String empName;

    /** nullable persistent field */
    private String cardKind;

    /** nullable persistent field */
    private String cardNum;

    /** nullable persistent field */
    private String status;

    /** nullable persistent field */
    private String type;

    /** nullable persistent field */
    private String reserveaA;

    /** nullable persistent field */
    private String reserveaB;

    /** nullable persistent field */
    private String reserveaC;
    
    private String personId;
    
    /** full constructor */
    public CongealInfo(Integer id, String contractId, BigDecimal orgId, BigDecimal empId, String empName, String cardKind, String cardNum, String status, String type, String reserveaA, String reserveaB, String reserveaC,String personId) {
        this.id = id;
        this.contractId = contractId;
        this.orgId = orgId;
        this.empId = empId;
        this.empName = empName;
        this.cardKind = cardKind;
        this.cardNum = cardNum;
        this.status = status;
        this.type = type;
        this.reserveaA = reserveaA;
        this.reserveaB = reserveaB;
        this.reserveaC = reserveaC;
        this.personId = personId;
    }

    /** default constructor */
    public CongealInfo() {
    }

    /** minimal constructor */
    public CongealInfo(Integer id, String contractId) {
        this.id = id;
        this.contractId = contractId;
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getContractId() {
        return this.contractId;
    }

    public void setContractId(String contractId) {
        this.contractId = contractId;
    }

    public BigDecimal getOrgId() {
        return this.orgId;
    }

    public void setOrgId(BigDecimal orgId) {
        this.orgId = orgId;
    }

    public BigDecimal getEmpId() {
        return this.empId;
    }

    public void setEmpId(BigDecimal empId) {
        this.empId = empId;
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

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
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

    public String getPersonId() {
      return personId;
    }

    public void setPersonId(String personId) {
      this.personId = personId;
    }
    public String toString() {
        return new ToStringBuilder(this)
            .append("id", getId())
            .toString();
    }

    public boolean equals(Object other) {
        if ( !(other instanceof CongealInfo) ) return false;
        CongealInfo castOther = (CongealInfo) other;
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
