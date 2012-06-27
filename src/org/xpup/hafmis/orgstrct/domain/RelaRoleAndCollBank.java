package org.xpup.hafmis.orgstrct.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/** @author Hibernate CodeGenerator */
public class RelaRoleAndCollBank implements Serializable {

    /** identifier field */
    private Integer id;

    /** persistent field */
    private String roleid;

    /** persistent field */
    private String collbankid;

    /** full constructor */
    public RelaRoleAndCollBank(Integer id, String roleid, String collbankid) {
        this.id = id;
        this.roleid = roleid;
        this.collbankid = collbankid;
    }

    /** default constructor */
    public RelaRoleAndCollBank() {
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

    public String getCollbankid() {
        return this.collbankid;
    }

    public void setCollbankid(String collbankid) {
        this.collbankid = collbankid;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("id", getId())
            .toString();
    }

    public boolean equals(Object other) {
        if ( !(other instanceof RelaRoleAndCollBank) ) return false;
        RelaRoleAndCollBank castOther = (RelaRoleAndCollBank) other;
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
