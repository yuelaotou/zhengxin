package org.xpup.hafmis.orgstrct.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/** @author Hibernate CodeGenerator */
public class RelaUserAndUser implements Serializable {

    /** identifier field */
    private Integer id;

    /** persistent field */
    private String username;

    /** persistent field */
    private String subusername;

    /** full constructor */
    public RelaUserAndUser(Integer id, String username, String subusername) {
        this.id = id;
        this.username = username;
        this.subusername = subusername;
    }

    /** default constructor */
    public RelaUserAndUser() {
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

    public String getSubusername() {
        return this.subusername;
    }

    public void setSubusername(String subusername) {
        this.subusername = subusername;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("id", getId())
            .toString();
    }

    public boolean equals(Object other) {
        if ( !(other instanceof RelaUserAndUser) ) return false;
        RelaUserAndUser castOther = (RelaUserAndUser) other;
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
