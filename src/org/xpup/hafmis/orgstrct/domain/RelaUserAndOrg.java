package org.xpup.hafmis.orgstrct.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/** @author Hibernate CodeGenerator */
public class RelaUserAndOrg implements Serializable {

    /** identifier field */
    private Integer id;

    /** persistent field */
    private String username;

    /** persistent field */
    private BigDecimal orgId;

    /** nullable persistent field */
    private String collBankId;

    /** nullable persistent field */
    private String office;

    /** full constructor */
    public RelaUserAndOrg(Integer id, String username, BigDecimal orgId, String collBankId, String office) {
        this.id = id;
        this.username = username;
        this.orgId = orgId;
        this.collBankId = collBankId;
        this.office = office;
    }

    /** default constructor */
    public RelaUserAndOrg() {
    }

    /** minimal constructor */
    public RelaUserAndOrg(Integer id, String username, BigDecimal orgId) {
        this.id = id;
        this.username = username;
        this.orgId = orgId;
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public BigDecimal getOrgId() {
        return this.orgId;
    }

    public void setOrgId(BigDecimal orgId) {
        this.orgId = orgId;
    }

    public String getCollBankId() {
        return this.collBankId;
    }

    public void setCollBankId(String collBankId) {
        this.collBankId = collBankId;
    }

    public String getOffice() {
        return this.office;
    }

    public void setOffice(String office) {
        this.office = office;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("id", getId())
            .toString();
    }

    public boolean equals(Object other) {
        if ( !(other instanceof RelaUserAndOrg) ) return false;
        RelaUserAndOrg castOther = (RelaUserAndOrg) other;
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
