package org.xpup.security.common.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/** @author Hibernate CodeGenerator */
public class Usersloginuser implements Serializable {

    /** identifier field */
    private Integer id;

    /** identifier field */
    private String subUsername;

    /** identifier field */
    private String username;

    /** full constructor */
    public Usersloginuser(Integer id, String subUsername, String username) {
        this.id = id;
        this.subUsername = subUsername;
        this.username = username;
    }

    /** default constructor */
    public Usersloginuser() {
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }



    public String getSubUsername() {
      return subUsername;
    }

    public void setSubUsername(String subUsername) {
      this.subUsername = subUsername;
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
            .append("orgId", getSubUsername())
            .append("username", getUsername())
            .toString();
    }

    public boolean equals(Object other) {
        if ( !(other instanceof Usersloginorg) ) return false;
        Usersloginuser castOther = (Usersloginuser) other;
        return new EqualsBuilder()
            .append(this.getId(), castOther.getId())
            .append(this.getSubUsername(), castOther.getSubUsername())
            .append(this.getUsername(), castOther.getUsername())
            .isEquals();
    }

    public int hashCode() {
        return new HashCodeBuilder()
            .append(getId())
            .append(getSubUsername())
            .append(getUsername())
            .toHashCode();
    }

}
