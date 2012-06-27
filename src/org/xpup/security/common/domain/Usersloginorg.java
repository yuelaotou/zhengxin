package org.xpup.security.common.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/** @author Hibernate CodeGenerator */
public class Usersloginorg implements Serializable {

    /** identifier field */
    private Integer id;

    /** identifier field */
    private Integer orgId;

    /** identifier field */
    private String username;

    /** full constructor */
    public Usersloginorg(Integer id, Integer orgId, String username) {
        this.id = id;
        this.orgId = orgId;
        this.username = username;
    }

    /** default constructor */
    public Usersloginorg() {
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getOrgId() {
        return this.orgId;
    }

    public void setOrgId(Integer orgId) {
        this.orgId = orgId;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("id", getId())
            .append("orgId", getOrgId())
            .append("username", getUsername())
            .toString();
    }

    public boolean equals(Object other) {
        if ( !(other instanceof Usersloginorg) ) return false;
        Usersloginorg castOther = (Usersloginorg) other;
        return new EqualsBuilder()
            .append(this.getId(), castOther.getId())
            .append(this.getOrgId(), castOther.getOrgId())
            .append(this.getUsername(), castOther.getUsername())
            .isEquals();
    }

    public int hashCode() {
        return new HashCodeBuilder()
            .append(getId())
            .append(getOrgId())
            .append(getUsername())
            .toHashCode();
    }

}
