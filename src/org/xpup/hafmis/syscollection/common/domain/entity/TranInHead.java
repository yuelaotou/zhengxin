package org.xpup.hafmis.syscollection.common.domain.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.xpup.hafmis.syscommon.domain.entity.DomainObject;

/** @author Hibernate CodeGenerator */
public class TranInHead extends DomainObject {


    /** persistent field */
    private TranInOrg tranInOrg = new TranInOrg();

    /** nullable persistent field */
    private TranOutOrg tranOutOrg = new TranOutOrg();

    private String tranOutOrgId;
    /** nullable persistent field */
    private String noteNum;

    /** nullable persistent field */
    private String docNum;

    /** nullable persistent field */
    private String settDate;

    /** nullable persistent field */
    private BigDecimal tranOutHeadId=new BigDecimal(0.00);
    
    /**display on jsp*/
    private String status="";
    /** persistent field */
    private BigDecimal tranStatus=new BigDecimal(0.00);
    /**±¸Ñ¡a*/
    private String reserveaA;
    private String reserveaB;
    private String reserveaC;
    /**count tranInPeople*/
    private String countTranInpeople;
    /**sum tranInAll*/
    private String traninAll;
    
    private String pickUpStatus="";
    
    private BigDecimal tranInSumBalance=new BigDecimal(0.00);
    
    public BigDecimal getTranInSumBalance() {
      return tranInSumBalance;
    }

    public void setTranInSumBalance(BigDecimal tranInSumBalance) {
      this.tranInSumBalance = tranInSumBalance;
    }

    public String getPickUpStatus() {
      return pickUpStatus;
    }

    public void setPickUpStatus(String pickUpStatus) {
      this.pickUpStatus = pickUpStatus;
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

    /** full constructor */
    public TranInHead(TranInOrg tranInOrg, String tranOutOrgId,String traninAll,String countTranInpeople, String noteNum, String docNum, String settDate, BigDecimal tranOutHeadId, BigDecimal tranStatus,String reserveaA, String reserveaB, String reserveaC) {
        this.traninAll=traninAll;
        this.countTranInpeople=countTranInpeople;
        this.tranInOrg = tranInOrg;
        this.tranOutOrgId = tranOutOrgId;
        this.noteNum = noteNum;
        this.docNum = docNum;
        this.settDate = settDate;
        this.tranOutHeadId = tranOutHeadId;
        this.tranStatus = tranStatus;
        this.reserveaA = reserveaA;
        this.reserveaB = reserveaB;
        this.reserveaC = reserveaC;
    }

    /** default constructor */
    public TranInHead() {
    }

    /** minimal constructor */
    public TranInHead(TranInOrg tranInOrg, String tranOutOrgId, BigDecimal tranStatus) {
        this.tranInOrg = tranInOrg;
        this.tranOutOrgId = tranOutOrgId;
        this.tranStatus = tranStatus;
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

    public BigDecimal getTranOutHeadId() {
        return this.tranOutHeadId;
    }

    public void setTranOutHeadId(BigDecimal tranOutHeadId) {
        this.tranOutHeadId = tranOutHeadId;
    }

    public BigDecimal getTranStatus() {
        return this.tranStatus;
    }

    public void setTranStatus(BigDecimal tranStatus) {
        this.tranStatus = tranStatus;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("id", getId())
            .toString();
    }

    public boolean equals(Object other) {
        if ( !(other instanceof TranInHead) ) return false;
        TranInHead castOther = (TranInHead) other;
        return new EqualsBuilder()
            .append(this.getId(), castOther.getId())
            .isEquals();
    }

    public int hashCode() {
        return new HashCodeBuilder()
            .append(getId())
            .toHashCode();
    }

    public TranInOrg getTranInOrg() {
      return tranInOrg;
    }

    public void setTranInOrg(TranInOrg tranInOrg) {
      this.tranInOrg = tranInOrg;
    }

    public TranOutOrg getTranOutOrg() {
      return tranOutOrg;
    }

    public void setTranOutOrg(TranOutOrg tranOutOrg) {
      this.tranOutOrg = tranOutOrg;
    }

    public String getTranOutOrgId() {
      return tranOutOrgId;
    }

    public void setTranOutOrgId(String tranOutOrgId) {
      this.tranOutOrgId = tranOutOrgId;
    }

    public String getCountTranInpeople() {
      return countTranInpeople;
    }

    public void setCountTranInpeople(String countTranInpeople) {
      this.countTranInpeople = countTranInpeople;
    }

    public String getTraninAll() {
      return traninAll;
    }

    public void setTraninAll(String traninAll) {
      this.traninAll = traninAll;
    }

    public String getStatus() {
      return status;
    }

    public void setStatus(String status) {
      this.status = status;
    }


}
