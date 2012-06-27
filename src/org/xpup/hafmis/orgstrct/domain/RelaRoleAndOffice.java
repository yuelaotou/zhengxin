package org.xpup.hafmis.orgstrct.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/** @author Hibernate CodeGenerator */
public class RelaRoleAndOffice implements Serializable {

    /** identifier field */
    private Integer id;

    /** persistent field */
    private String roleid;

    /** persistent field */
    private String office;

    /** full constructor */
    public RelaRoleAndOffice(Integer id, String roleid, String office) {
        this.id = id;
        this.roleid = roleid;
        this.office = office;
    }

    /** default constructor */
    public RelaRoleAndOffice() {
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRoleid() {
        return this.roleid;
    }

    public void setRoleid(String roleid) {
        this.roleid = roleid;
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
        if ( !(other instanceof RelaRoleAndOffice) ) return false;
        RelaRoleAndOffice castOther = (RelaRoleAndOffice) other;
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
