package org.xpup.hafmis.sysloan.common.domain.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/** @author Hibernate CodeGenerator */
public class OverdueInfo implements Serializable {
  
    /** identifier field */
    private Integer id ;
    
    /** persistent field */
    private String contractId;

    /** nullable persistent field */
    private Integer loanBankId;

    /** persistent field */
    private BigDecimal oweCorpus = new BigDecimal(0.00);

    /** nullable persistent field */
    private BigDecimal  oweInterest = new BigDecimal(0.00);

    /** persistent field */
    private BigDecimal  punishInterest = new BigDecimal(0.00);

    /** persistent field */
    private String oweMonth;
    
    /** persistent field */
    private String oweDate;

    /** nullable persistent field */
    private String reserveaA;

    /** nullable persistent field */
    private String reserveaB;

    /** nullable persistent field */
    private String reserveaC;

    /** full constructor */
    public OverdueInfo(Integer id, String contractId, Integer loanBankId, BigDecimal oweCorpus, BigDecimal oweInterest, BigDecimal punishInterest, String oweMonth, String reserveaA, String reserveaB, String reserveaC,String oweDate) {
        this.id = id;
        this.contractId = contractId;
        this.loanBankId = loanBankId;
        this.oweCorpus = oweCorpus;
        this.oweInterest = oweInterest;
        this.punishInterest = punishInterest;
        this.oweMonth = oweMonth;
        this.oweDate = oweDate;
        this.reserveaA = reserveaA;
        this.reserveaB = reserveaB;
        this.reserveaC = reserveaC;
    }

    /** default constructor */
    public OverdueInfo() {
    }

    /** minimal constructor */
    public OverdueInfo(Integer id,String contractId) {
        this.id = id;
        this.contractId = contractId;
    }



    public String getContractId() {
        return this.contractId;
    }

    public void setContractId(String contractId) {
        this.contractId = contractId;
    }



    public Integer getLoanBankId() {
      return loanBankId;
    }

    public void setLoanBankId(Integer loanBankId) {
      this.loanBankId = loanBankId;
    }


    public BigDecimal getOweCorpus() {
      return oweCorpus;
    }

    public void setOweCorpus(BigDecimal oweCorpus) {
      this.oweCorpus = oweCorpus;
    }



    public BigDecimal getOweInterest() {
      return oweInterest;
    }

    public void setOweInterest(BigDecimal oweInterest) {
      this.oweInterest = oweInterest;
    }

    public BigDecimal getPunishInterest() {
      return punishInterest;
    }

    public void setPunishInterest(BigDecimal punishInterest) {
      this.punishInterest = punishInterest;
    }

    public String getOweMonth() {
      return oweMonth;
    }

    public void setOweMonth(String oweMonth) {
      this.oweMonth = oweMonth;
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
            .append("contractId", getContractId())
            .toString();
    }

    public boolean equals(Object other) {
        if ( !(other instanceof OverdueInfo) ) return false;
        OverdueInfo castOther = (OverdueInfo) other;
        return new EqualsBuilder()
            .append(this.getContractId(), castOther.getContractId())
            .isEquals();
    }

    public int hashCode() {
        return new HashCodeBuilder()
            .append(getContractId())
            .toHashCode();
    }

    public Integer getId() {
      return id;
    }

    public void setId(Integer id) {
      this.id = id;
    }

    public String getOweDate() {
      return oweDate;
    }

    public void setOweDate(String oweDate) {
      this.oweDate = oweDate;
    }

}
