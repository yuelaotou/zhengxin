package org.xpup.hafmis.sysloan.common.domain.entity;

import java.io.Serializable;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/** @author Hibernate CodeGenerator */
public class CenterRestoreLoanPlan implements Serializable {

    /** identifier field */
    private String id;

    /** full constructor */
    public CenterRestoreLoanPlan(String id) {
        this.id = id;
    }

    /** default constructor */
    public CenterRestoreLoanPlan() {
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("id", getId())
            .toString();
    }

    public boolean equals(Object other) {
        if ( !(other instanceof CenterRestoreLoanPlan) ) return false;
        CenterRestoreLoanPlan castOther = (CenterRestoreLoanPlan) other;
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
