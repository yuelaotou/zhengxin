package org.xpup.security.common.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/** @author Hibernate CodeGenerator */
public class Userslogincollbank implements Serializable {

    /** identifier field */
    private Integer id;

    /** identifier field */
    private Integer collBankId;

    /** identifier field */
    private String collBankName;

    /** identifier field */
    private String username;

    /** full constructor */
    public Userslogincollbank(Integer id, Integer collBankId, String collBankName, String username) {
        this.id = id;
        this.collBankId = collBankId;
        this.collBankName = collBankName;
        this.username = username;
    }

    /** default constructor */
    public Userslogincollbank() {
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCollBankId() {
        return this.collBankId;
    }

    public void setCollBankId(Integer collBankId) {
        this.collBankId = collBankId;
    }

    public String getCollBankName() {
        return this.collBankName;
    }

    public void setCollBankName(String collBankName) {
        this.collBankName = collBankName;
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
            .append("collBankId", getCollBankId())
            .append("collBankName", getCollBankName())
            .append("username", getUsername())
            .toString();
    }

    public boolean equals(Object other) {
        if ( !(other instanceof Userslogincollbank) ) return false;
        Userslogincollbank castOther = (Userslogincollbank) other;
        return new EqualsBuilder()
            .append(this.getId(), castOther.getId())
            .append(this.getCollBankId(), castOther.getCollBankId())
            .append(this.getCollBankName(), castOther.getCollBankName())
            .append(this.getUsername(), castOther.getUsername())
            .isEquals();
    }

    public int hashCode() {
        return new HashCodeBuilder()
            .append(getId())
            .append(getCollBankId())
            .append(getCollBankName())
            .append(getUsername())
            .toHashCode();
    }

}
