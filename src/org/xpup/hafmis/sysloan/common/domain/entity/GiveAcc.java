package org.xpup.hafmis.sysloan.common.domain.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/** @author Hibernate CodeGenerator */
public class GiveAcc implements Serializable {

    /** identifier field */
    private Integer pokeBankModifyId;

    /** persistent field */
    private String contractId;

    /** persistent field */
    private String oldPokeBank;

    /** persistent field */
    private String oldPokeBankAcc;

    /** persistent field */
    private String newPokeBank;

    /** persistent field */
    private String newPokeBankAcc;

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
    public GiveAcc(Integer pokeBankModifyId, String contractId, String oldPokeBank, String oldPokeBankAcc, String newPokeBank, String newPokeBankAcc, Date opTime, String oprator, String reserveaA, String reserveaB, String reserveaC,String modifyDate) {
        this.pokeBankModifyId = pokeBankModifyId;
        this.contractId = contractId;
        this.oldPokeBank = oldPokeBank;
        this.oldPokeBankAcc = oldPokeBankAcc;
        this.newPokeBank = newPokeBank;
        this.newPokeBankAcc = newPokeBankAcc;
        this.opTime = opTime;
        this.oprator = oprator;
        this.reserveaA = reserveaA;
        this.reserveaB = reserveaB;
        this.reserveaC = reserveaC;
        this.modifyDate = modifyDate;
    }

    /** default constructor */
    public GiveAcc() {
    }

    /** minimal constructor */
    public GiveAcc(Integer pokeBankModifyId, String contractId, String oldPokeBank, String oldPokeBankAcc, String newPokeBank, String newPokeBankAcc, Date opTime, String oprator) {
        this.pokeBankModifyId = pokeBankModifyId;
        this.contractId = contractId;
        this.oldPokeBank = oldPokeBank;
        this.oldPokeBankAcc = oldPokeBankAcc;
        this.newPokeBank = newPokeBank;
        this.newPokeBankAcc = newPokeBankAcc;
        this.opTime = opTime;
        this.oprator = oprator;
    }

    public Integer getPokeBankModifyId() {
        return this.pokeBankModifyId;
    }

    public void setPokeBankModifyId(Integer pokeBankModifyId) {
        this.pokeBankModifyId = pokeBankModifyId;
    }

    public String getContractId() {
        return this.contractId;
    }

    public void setContractId(String contractId) {
        this.contractId = contractId;
    }

    public String getOldPokeBank() {
        return this.oldPokeBank;
    }

    public void setOldPokeBank(String oldPokeBank) {
        this.oldPokeBank = oldPokeBank;
    }

    public String getOldPokeBankAcc() {
        return this.oldPokeBankAcc;
    }

    public void setOldPokeBankAcc(String oldPokeBankAcc) {
        this.oldPokeBankAcc = oldPokeBankAcc;
    }

    public String getNewPokeBank() {
        return this.newPokeBank;
    }

    public void setNewPokeBank(String newPokeBank) {
        this.newPokeBank = newPokeBank;
    }

    public String getNewPokeBankAcc() {
        return this.newPokeBankAcc;
    }

    public void setNewPokeBankAcc(String newPokeBankAcc) {
        this.newPokeBankAcc = newPokeBankAcc;
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
            .append("pokeBankModifyId", getPokeBankModifyId())
            .toString();
    }

    public boolean equals(Object other) {
        if ( !(other instanceof GiveAcc) ) return false;
        GiveAcc castOther = (GiveAcc) other;
        return new EqualsBuilder()
            .append(this.getPokeBankModifyId(), castOther.getPokeBankModifyId())
            .isEquals();
    }

    public int hashCode() {
        return new HashCodeBuilder()
            .append(getPokeBankModifyId())
            .toHashCode();
    }

}
