package org.xpup.hafmis.demo.domain.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
/**
 * 
 * @author ¡ı—Û
 *2007-5-31
 */
/** @author Hibernate CodeGenerator */
public class DemoAccount implements Serializable {


  private static final long serialVersionUID = -6963087639681647522L;

    /** identifier field */
    private String id;

    /** persistent field */
    private BigDecimal addmoney;

    /** persistent field */
    private org.xpup.hafmis.demo.domain.entity.Demo demo;

    /** full constructor */
    public DemoAccount(String id, BigDecimal addmoney, org.xpup.hafmis.demo.domain.entity.Demo demo) {
        this.id = id;
        this.addmoney = addmoney;
        this.demo = demo;
    }

    /** default constructor */
    public DemoAccount() {
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public BigDecimal getAddmoney() {
        return this.addmoney;
    }

    public void setAddmoney(BigDecimal addmoney) {
        this.addmoney = addmoney;
    }

    public org.xpup.hafmis.demo.domain.entity.Demo getDemo() {
        return this.demo;
    }

    public void setDemo(org.xpup.hafmis.demo.domain.entity.Demo demo) {
        this.demo = demo;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("id", getId())
            .toString();
    }

    public boolean equals(Object other) {
        if ( !(other instanceof DemoAccount) ) return false;
        DemoAccount castOther = (DemoAccount) other;
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
