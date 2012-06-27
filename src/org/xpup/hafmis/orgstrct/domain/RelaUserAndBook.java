package org.xpup.hafmis.orgstrct.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/** @author Hibernate CodeGenerator */
public class RelaUserAndBook implements Serializable {

    /** identifier field */
    private Integer id;

    /** persistent field */
    private String username;

    /** persistent field */
    private String bookId;
    
    private String bizDate;
    
    /** full constructor */
    public RelaUserAndBook(Integer id, String username, String bookId,String fBizDate) {
        this.id = id;
        this.username = username;
        this.bookId = bookId;
    }

    /** default constructor */
    public RelaUserAndBook() {
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

    public String getBookId() {
        return this.bookId;
    }

    public String getBizDate() {
      return bizDate;
    }

    public void setBizDate(String bizDate) {
      this.bizDate = bizDate;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("id", getId())
            .toString();
    }

    public boolean equals(Object other) {
        if ( !(other instanceof RelaUserAndBook) ) return false;
        RelaUserAndBook castOther = (RelaUserAndBook) other;
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
