package org.xpup.hafmis.sysloan.common.domain.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/** @author Hibernate CodeGenerator */
public class GatheringAcc implements Serializable {

    /** identifier field */
    private Integer receiveBankModifyId;

    /** persistent field */
    private String contractId;

    /** persistent field */
    private String oldBankAcc;

    /** persistent field */
    private String newBankAcc;

    /** persistent field */
    private Date opTime;

    /** persistent field */
    private String oprator;

    /** nullable persistent field */
    private String reserveaA;

    /** nullable persistent field */
    private String reserveaB;

    /** nullable persistent field */
    private String reserveaC;
    
    private String modifyDate;

    /** full constructor */
    public GatheringAcc(Integer receiveBankModifyId, String contractId, String oldBankAcc, String newBankAcc, Date opTime, String oprator, String reserveaA, String reserveaB, String reserveaC,String modifyDate) {
        this.receiveBankModifyId = receiveBankModifyId;
        this.contractId = contractId;
        this.oldBankAcc = oldBankAcc;
        this.newBankAcc = newBankAcc;
        this.opTime = opTime;
        this.oprator = oprator;
        this.reserveaA = reserveaA;
        this.reserveaB = reserveaB;
        this.reserveaC = reserveaC;
        this.modifyDate = modifyDate;
    }

    /** default constructor */
    public GatheringAcc() {
    }

    /** minimal constructor */
    public GatheringAcc(Integer receiveBankModifyId, String contractId, String oldBankAcc, String newBankAcc, Date opTime, String oprator) {
        this.receiveBankModifyId = receiveBankModifyId;
        this.contractId = contractId;
        this.oldBankAcc = oldBankAcc;
        this.newBankAcc = newBankAcc;
        this.opTime = opTime;
        this.oprator = oprator;
    }

    public Integer getReceiveBankModifyId() {
        return this.receiveBankModifyId;
    }

    public void setReceiveBankModifyId(Integer receiveBankModifyId) {
        this.receiveBankModifyId = receiveBankModifyId;
    }

    public String getContractId() {
        return this.contractId;
    }

    public void setContractId(String contractId) {
        this.contractId = contractId;
    }

    public String getOldBankAcc() {
        return this.oldBankAcc;
    }

    public void setOldBankAcc(String oldBankAcc) {
        this.oldBankAcc = oldBankAcc;
    }

    public String getNewBankAcc() {
        return this.newBankAcc;
    }

    public void setNewBankAcc(String newBankAcc) {
        this.newBankAcc = newBankAcc;
    }

    public Date getOpTime() {
        return this.opTime;
    }

    public void setOpTime(Date opTime) {
        this.opTime = opTime;
    }

    public String getOprator() {
        return this.oprator;
    }

    public void setOprator(String oprator) {
        this.oprator = oprator;
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

    public String getModifyDate() {
      return modifyDate;
    }

    public void setModifyDate(String modifyDate) {
      this.modifyDate = modifyDate;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("receiveBankModifyId", getReceiveBankModifyId())
            .toString();
    }

    public boolean equals(Object other) {
        if ( !(other instanceof GatheringAcc) ) return false;
        GatheringAcc castOther = (GatheringAcc) other;
        return new EqualsBuilder()
            .append(this.getReceiveBankModifyId(), castOther.getReceiveBankModifyId())
            .isEquals();
    }

    public int hashCode() {
        return new HashCodeBuilder()
            .append(getReceiveBankModifyId())
            .toHashCode();
    }

}
