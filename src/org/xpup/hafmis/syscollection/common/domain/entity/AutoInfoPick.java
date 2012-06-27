package org.xpup.hafmis.syscollection.common.domain.entity;

import java.util.Date;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.xpup.hafmis.syscommon.domain.entity.DomainObject;

/** @author Hibernate CodeGenerator */
public class AutoInfoPick extends DomainObject {
    
    /**
   * 
   */
  private static final long serialVersionUID = 1L;

    private Integer orgId=new Integer(0);
    
    private Integer orgHeadId=new Integer(0);
    
    private Integer centerHeadId=new Integer(0);
    
    private String type="";
    
    private String status="";
    
    private Integer payHeadId=new Integer(0); 
    
    private Date orgBizDate;
        
    private Date centerBizDate;
    
    /**±¸Ñ¡a*/
    private String reserveaA;
    private String reserveaB;
    private String reserveaC;
    
    /** full constructor */
    public AutoInfoPick(Integer orgId, Integer orgHeadId, Integer centerHeadId, String type, String status, Integer payHeadId,Date orgBizDate,Date centerBizDate,String reserveaA, String reserveaB, String reserveaC) {
        this.orgId = orgId;
        this.orgHeadId = orgHeadId;
        this.centerHeadId = centerHeadId;
        this.type = type;
        this.status = status;
        this.payHeadId = payHeadId;
        this.orgBizDate = orgBizDate;
        this.centerBizDate = centerBizDate;
        this.reserveaA = reserveaA;
        this.reserveaB = reserveaB;
        this.reserveaC = reserveaC;
    }

    /** default constructor */
    public AutoInfoPick() {
    }

    /** minimal constructor */
    public AutoInfoPick(Integer orgId, Integer orgHeadId, String type, String status) {
      this.orgId = orgId;
      this.orgHeadId = orgHeadId;
      this.type = type;
      this.status = status;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("id", getId())
            .toString();
    }

    public boolean equals(Object other) {
        if ( !(other instanceof AddPayTail) ) return false;
        AddPayTail castOther = (AddPayTail) other;
        return new EqualsBuilder()
            .append(this.getId(), castOther.getId())
            .isEquals();
    }

    public int hashCode() {
        return new HashCodeBuilder()
            .append(getId())
            .toHashCode();
    }
    
    public Date getCenterBizDate() {
      return centerBizDate;
    }

    public void setCenterBizDate(Date centerBizDate) {
      this.centerBizDate = centerBizDate;
    }

    public Integer getCenterHeadId() {
      return centerHeadId;
    }

    public void setCenterHeadId(Integer centerHeadId) {
      this.centerHeadId = centerHeadId;
    }

    public Date getOrgBizDate() {
      return orgBizDate;
    }

    public void setOrgBizDate(Date orgBizDate) {
      this.orgBizDate = orgBizDate;
    }

    public Integer getOrgHeadId() {
      return orgHeadId;
    }

    public void setOrgHeadId(Integer orgHeadId) {
      this.orgHeadId = orgHeadId;
    }

    public Integer getOrgId() {
      return orgId;
    }

    public void setOrgId(Integer orgId) {
      this.orgId = orgId;
    }

    public Integer getPayHeadId() {
      return payHeadId;
    }

    public void setPayHeadId(Integer payHeadId) {
      this.payHeadId = payHeadId;
    }

    public String getStatus() {
      return status;
    }

    public void setStatus(String status) {
      this.status = status;
    }

    public String getType() {
      return type;
    }

    public void setType(String type) {
      this.type = type;
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

    
}