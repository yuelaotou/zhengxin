package org.xpup.hafmis.orgstrct.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/** @author Hibernate CodeGenerator */
public class RelaRoleAndOrg implements Serializable {


  private static final long serialVersionUID = 5733376791158589204L;

    /** identifier field */
    private Integer id;

    /** nullable persistent field */
    private String role;

    /** nullable persistent field */
    private String office;

    /** nullable persistent field */
    private String collBankId;

    /** nullable persistent field */
    private String orgId;

    /** full constructor */
    public RelaRoleAndOrg(Integer id, String role, String office, String collBankId, String orgId) {
        this.id = id;
        this.role = role;
        this.office = office;
        this.collBankId = collBankId;
        this.orgId = orgId;
    }

    /** default constructor */
    public RelaRoleAndOrg() {
    }

    /** minimal constructor */
    public RelaRoleAndOrg(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRole() {
        return this.role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getOffice() {
        return this.office;
    }

    public void setOffice(String office) {
        this.office = office;
    }

    public String getCollBankId() {
        return this.collBankId;
    }

    public void setCollBankId(String collBankId) {
        this.collBankId = collBankId;
    }

    public String getOrgId() {
        return this.orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("id", getId())
            .toString();
    }

    public boolean equals(Object other) {
        if ( !(other instanceof RelaRoleAndOrg) ) return false;
        RelaRoleAndOrg castOther = (RelaRoleAndOrg) other;
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
