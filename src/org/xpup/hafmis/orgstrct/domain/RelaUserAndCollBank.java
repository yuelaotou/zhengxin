package org.xpup.hafmis.orgstrct.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/** @author Hibernate CodeGenerator */
public class RelaUserAndCollBank implements Serializable {

    /** identifier field */
    private Integer id;

    /** persistent field */
    private String username;

    /** persistent field */
    private String collBankId;

    /** full constructor */
    public RelaUserAndCollBank(Integer id, String username, String collBankId) {
        this.id = id;
        this.username = username;
        this.collBankId = collBankId;
    }

    /** default constructor */
    public RelaUserAndCollBank() {
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

    public String getCollBankId() {
        return this.collBankId;
    }

    public void setCollBankId(String collBankId) {
        this.collBankId = collBankId;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("id", getId())
            .toString();
    }

    public boolean equals(Object other) {
        if ( !(other instanceof RelaUserAndCollBank) ) return false;
        RelaUserAndCollBank castOther = (RelaUserAndCollBank) other;
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
