package org.xpup.hafmis.sysloan.common.domain.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/** @author Hibernate CodeGenerator */
public class ContractChg implements Serializable {

    /** identifier field */
    private Integer id;

    /** nullable persistent field */
    private String contractId;

    /** persistent field */
    private String chgColumn;

    /** persistent field */
    private String chgBefInfo;

    /** persistent field */
    private String chgEndInfo;

    /** persistent field */
    private Date opTime;

    /** persistent field */
    private String operator;

    /** nullable persistent field */
    private String contractType;

    /** nullable persistent field */
    private String reserveaA;

    /** nullable persistent field */
    private String reserveaB;

    /** nullable persistent field */
    private String reserveaC;
    
    
    /** full constructor */
    public ContractChg(Integer id, String contractId, String chgColumn, String chgBefInfo, String chgEndInfo, Date opTime, String operator, String contractType, String reserveaA, String reserveaB, String reserveaC) {
        this.id = id;
        this.contractId = contractId;
        this.chgColumn = chgColumn;
        this.chgBefInfo = chgBefInfo;
        this.chgEndInfo = chgEndInfo;
        this.opTime = opTime;
        this.operator = operator;
        this.contractType = contractType;
        this.reserveaA = reserveaA;
        this.reserveaB = reserveaB;
        this.reserveaC = reserveaC;
    }

    /** default constructor */
    public ContractChg() {
    }

    /** minimal constructor */
    public ContractChg(Integer id, String chgColumn,Date opTime, String operator) {
        this.id = id;
        this.chgColumn = chgColumn;
        this.opTime = opTime;
        this.operator = operator;
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

    public String getChgColumn() {
        return this.chgColumn;
    }

    public void setChgColumn(String chgColumn) {
        this.chgColumn = chgColumn;
    }

    public String getChgBefInfo() {
        return this.chgBefInfo;
    }

    public void setChgBefInfo(String chgBefInfo) {
        this.chgBefInfo = chgBefInfo;
    }

    public String getChgEndInfo() {
        return this.chgEndInfo;
    }

    public void setChgEndInfo(String chgEndInfo) {
        this.chgEndInfo = chgEndInfo;
    }

    public Date getOpTime() {
        return this.opTime;
    }

    public void setOpTime(Date opTime) {
        this.opTime = opTime;
    }

    public String getOperator() {
        return this.operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public String getContractType() {
        return this.contractType;
    }

    public void setContractType(String contractType) {
        this.contractType = contractType;
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
        if ( !(other instanceof ContractChg) ) return false;
        ContractChg castOther = (ContractChg) other;
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
