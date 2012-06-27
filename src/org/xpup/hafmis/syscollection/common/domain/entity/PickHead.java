package org.xpup.hafmis.syscollection.common.domain.entity;


import java.math.BigDecimal;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.xpup.hafmis.syscommon.domain.entity.DomainObject;

/** @author Hibernate CodeGenerator */
public class PickHead extends DomainObject {

    private BigDecimal pickPersonCount = new BigDecimal(0.00);
    private BigDecimal pickBalance = new BigDecimal(0.00);
    private BigDecimal distroyInterest = new BigDecimal(0.00);
    private BigDecimal pickMoneyCount = new BigDecimal(0.00);
 //   private BigDecimal reason;
    private Org org = new Org();
    private String businessState;
    /** nullable persistent field */
    private String noteNum;
    
    /** nullable persistent field */
    private String docNum;

    /** nullable persistent field */
    private String settDate;

    /** persistent field */
    private BigDecimal pickSatatus;
    /**备选a*/
    private String reserveaA;
    private String reserveaB;
    private String reserveaC;
    private String temp_pick;
    //批次号BATCH_NUM
    private String batchnum;
    
  

    public String getBatchnum() {
      return batchnum;
    }

    public void setBatchnum(String batchnum) {
      this.batchnum = batchnum;
    }

    /** full constructor */
    public PickHead(Org org, BigDecimal orgId, String noteNum, String docNum, String settDate, BigDecimal pickSatatus,String reserveaA, String reserveaB, String reserveaC) {
//      org.getId()
//      org.getOrgInfo().getName()
        this.org = org;
        this.noteNum = noteNum;
        this.docNum = docNum;
        this.settDate = settDate;
        this.pickSatatus = pickSatatus;
        this.reserveaA = reserveaA;
        this.reserveaB = reserveaB;
        this.reserveaC = reserveaC;
    }

    /** default constructor */
    public PickHead() {
    }

    /** minimal constructor */
    public PickHead(Org org, BigDecimal orgId, BigDecimal pickSatatus) {
        this.org = org;
        this.pickSatatus = pickSatatus;
    }

    public String getNoteNum() {
        return this.noteNum;
    }

    public void setNoteNum(String noteNum) {
        this.noteNum = noteNum;
    }

    public String getDocNum() {
        return this.docNum;
    }

    public void setDocNum(String docNum) {
        this.docNum = docNum;
    }

    public String getSettDate() {
        return this.settDate;
    }

    public void setSettDate(String settDate) {
        this.settDate = settDate;
    }

    public BigDecimal getPickSatatus() {
        return this.pickSatatus;
    }

    public void setPickSatatus(BigDecimal pickSatatus) {
        this.pickSatatus = pickSatatus;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("id", getId())
            .toString();
    }

    public boolean equals(Object other) {
        if ( !(other instanceof PickHead) ) return false;
        PickHead castOther = (PickHead) other;
        return new EqualsBuilder()
            .append(this.getId(), castOther.getId())
            .isEquals();
    }

    public int hashCode() {
        return new HashCodeBuilder()
            .append(getId())
            .toHashCode();
    }

    public Org getOrg() {
      return org;
    }

    public void setOrg(Org org) {
      this.org = org;
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

    public BigDecimal getDistroyInterest() {
      return distroyInterest;
    }

    public void setDistroyInterest(BigDecimal distroyInterest) {
      this.distroyInterest = distroyInterest;
    }

    public BigDecimal getPickBalance() {
      return pickBalance;
    }

    public void setPickBalance(BigDecimal pickBalance) {
      this.pickBalance = pickBalance;
    }

    public BigDecimal getPickMoneyCount() {
      return pickMoneyCount;
    }

    public void setPickMoneyCount(BigDecimal pickMoneyCount) {
      this.pickMoneyCount = pickMoneyCount;
    }

    public BigDecimal getPickPersonCount() {
      return pickPersonCount;
    }

    public void setPickPersonCount(BigDecimal pickPersonCount) {
      this.pickPersonCount = pickPersonCount;
    }

    public String getBusinessState() {
      return businessState;
    }

    public void setBusinessState(String businessState) {
      this.businessState = businessState;
    }

    public String getTemp_pick() {
      return temp_pick;
    }

    public void setTemp_pick(String temp_pick) {
      this.temp_pick = temp_pick;
    }

 

//    public BigDecimal getReason() {
//      return reason;
//    }
//
//    public void setReason(BigDecimal reason) {
//      this.reason = reason;
//    }



}
