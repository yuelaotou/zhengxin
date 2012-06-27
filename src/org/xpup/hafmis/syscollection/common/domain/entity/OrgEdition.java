package org.xpup.hafmis.syscollection.common.domain.entity;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.xpup.hafmis.syscommon.domain.entity.DomainObject;

/** @author Hibernate CodeGenerator */
public class OrgEdition extends DomainObject {
    
    /**
   * 
   */
  private static final long serialVersionUID = 1L;

    private Integer orgId=new Integer(0);
    
    private Integer isOrg=new Integer(0);
    
    /**±¸Ñ¡a*/
    private String reserveaA;
    private String reserveaB;
    private String reserveaC;
    
    /** full constructor */
    public OrgEdition(Integer orgId, Integer isOrg, String reserveaA, String reserveaB, String reserveaC) {
        this.orgId = orgId;
        this.isOrg = isOrg;
        this.reserveaA = reserveaA;
        this.reserveaB = reserveaB;
        this.reserveaC = reserveaC;
    }

    /** default constructor */
    public OrgEdition() {
    }

    /** minimal constructor */
    public OrgEdition(Integer orgId, Integer isOrg) {
      this.orgId = orgId;
      this.isOrg = isOrg;
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

    public Integer getIsOrg() {
      return isOrg;
    }

    public void setIsOrg(Integer isOrg) {
      this.isOrg = isOrg;
    }

    public Integer getOrgId() {
      return orgId;
    }

    public void setOrgId(Integer orgId) {
      this.orgId = orgId;
    }

    
}