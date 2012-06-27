package org.xpup.security.common.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/** @author Hibernate CodeGenerator */
public class UsersloginBook implements Serializable {

    /** identifier field */
    private Integer id;

    /** identifier field */
    private String bookId;

    /** identifier field */
    private String bookName;

    /** identifier field */
    private String username;

    /** full constructor */
    public UsersloginBook(Integer id, String bookId, String bookName, String username) {
        this.id = id;
        this.bookId =bookId;
        this.bookName = bookName;
        this.username = username;
    }

    /** default constructor */
    public UsersloginBook() {
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


    public String getBookId() {
      return bookId;
    }

    public void setBookId(String bookId) {
      this.bookId = bookId;
    }

    public String getBookName() {
      return bookName;
    }

    public void setBookName(String bookName) {
      this.bookName = bookName;
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
            .append("collBankId", getBookId())
            .append("collBankName", getBookName())
            .append("username", getUsername())
            .toString();
    }

    public boolean equals(Object other) {
        if ( !(other instanceof UsersloginBook) ) return false;
        UsersloginBook castOther = (UsersloginBook) other;
        return new EqualsBuilder()
            .append(this.getId(), castOther.getId())
            .append(this.getBookId(), castOther.getBookId())
            .append(this.getBookName(), castOther.getBookName())
            .append(this.getUsername(), castOther.getUsername())
            .isEquals();
    }

    public int hashCode() {
        return new HashCodeBuilder()
            .append(getId())
            .append(getBookId())
            .append(getBookName())
            .append(getUsername())
            .toHashCode();
    }

}
