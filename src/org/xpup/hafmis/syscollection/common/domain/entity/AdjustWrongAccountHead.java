package org.xpup.hafmis.syscollection.common.domain.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.xpup.hafmis.syscommon.domain.entity.DomainObject;

/** @author Hibernate CodeGenerator */
public abstract class AdjustWrongAccountHead extends DomainObject {

    /** persistent field */
    private Org org = new Org();
    private String bizType;  
    /** persistent field */
    private String docNum;

    /** nullable persistent field */
    private String bizDate;

    /** persistent field */
    private BigDecimal adjustStatus=new BigDecimal(0.00);
    private String bizDocNum;
    private String bizNoteNum ;
    /**备选a*/
    private String reserveaA;
    private String reserveaB;  
    private String reserveaC;
    private String status;
    private BigDecimal total=new BigDecimal(0.00);;
    private BigDecimal allTotal=new BigDecimal(0.00);
    private BigDecimal allTotalDefaults=new BigDecimal(0.00);
    private Integer personTotal;
    private String orgName;//排序使用
    public BigDecimal getTotal() {
      return total;
    }

    public void setTotal(BigDecimal total) {
      this.total = total;
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
    public AdjustWrongAccountHead(Org org,BigDecimal orgId, String docNum, String bizDate, BigDecimal adjustStatus,String bizDocNum,String bizNoteNum , String reserveaA, String reserveaB, String reserveaC) {
        this.org = org;
        this.docNum = docNum;
        this.bizDate = bizDate;
        this.adjustStatus = adjustStatus;
        this.bizDocNum = bizDocNum;
        this.bizNoteNum = bizNoteNum;
        this.reserveaA = reserveaA;
        this.reserveaB = reserveaB;
        this.reserveaC = reserveaC;
    }

    /** default constructor */
    public AdjustWrongAccountHead() {
    }

    /** minimal constructor */
    public AdjustWrongAccountHead(Org org ,BigDecimal orgId, String docNum, BigDecimal adjustStatus) {
        this.org = org;
        this.docNum = docNum;
        this.adjustStatus = adjustStatus;
    }

    public String getDocNum() {
        return this.docNum;
    }

    public void setDocNum(String docNum) {
        this.docNum = docNum;
    }

    public String getBizDate() {
        return this.bizDate;
    }

    public void setBizDate(String bizDate) {
        this.bizDate = bizDate;
    }

    public BigDecimal getAdjustStatus() {
        return this.adjustStatus;
    }

    public void setAdjustStatus(BigDecimal adjustStatus) {
        this.adjustStatus = adjustStatus;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("id", getId())
            .toString();
    }

    public boolean equals(Object other) {
        if ( !(other instanceof AdjustWrongAccountHead) ) return false;
        AdjustWrongAccountHead castOther = (AdjustWrongAccountHead) other;
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

    public String getBizDocNum() {
      return bizDocNum;
    }

    public void setBizDocNum(String bizDocNum) {
      this.bizDocNum = bizDocNum;
    }

    public String getBizNoteNum() {
      return bizNoteNum;
    }

    public void setBizNoteNum(String bizNoteNum) {
      this.bizNoteNum = bizNoteNum;
    }
    public abstract String getBizType() ;

    public Integer getPersonTotal() {
      return personTotal;
    }

    public void setPersonTotal(Integer personTotal) {
      this.personTotal = personTotal;
    }

    public String getStatus() {
      return status;
    }

    public void setStatus(String status) {
      this.status = status;
    }

    public BigDecimal getAllTotal() {
      return allTotal;
    }

    public void setAllTotal(BigDecimal allTotal) {
      this.allTotal = allTotal;
    }

    public BigDecimal getAllTotalDefaults() {
      return allTotalDefaults;
    }

    public void setAllTotalDefaults(BigDecimal allTotalDefaults) {
      this.allTotalDefaults = allTotalDefaults;
    }

    public String getOrgName() {
      return orgName;
    }

    public void setOrgName(String orgName) {
      this.orgName = orgName;
    }


}
