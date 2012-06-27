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
public class Demo implements Serializable {

    /**
   * 
   */
  private static final long serialVersionUID = 4841184224952164649L;

    /** identifier field */
    private Integer id;
    private String photoUrl;
    /** identifier field */
    private String name;

    /** identifier field */
    private String idcard;

    /** identifier field */
    private String birthday;
    
    private BigDecimal salary=new BigDecimal(0.00);
    
    private BigDecimal  balance=new BigDecimal(0.00);;
    /** full constructor */
    
    private String sex;
    public Demo(Integer id, String name, String idcard, String birthday,BigDecimal salary,String sex,String photoUrl) {
        this.id = id;
        this.name = name;
        this.idcard = idcard;
        this.birthday = birthday;
        this.salary=salary;
        this.sex=sex;
        this.photoUrl=photoUrl;
    }

    /** default constructor */
    public Demo() {
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIdcard() {
        return this.idcard;
    }

    public void setIdcard(String idcard) {
        this.idcard = idcard;
    }

    public String getBirthday() {
        return this.birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public BigDecimal getSalary() {
      return salary;
    }

    public void setSalary(BigDecimal salary) {
      this.salary = salary;
    }

    public BigDecimal getBalance() {
      return balance;
    }

    public void setBalance(BigDecimal balance) {
      if(balance==null){
        balance=new BigDecimal(0.00);
      }
      this.balance = balance;
    }

    public String getSex() {
      return sex;
    }

    public void setSex(String sex) {
      this.sex = sex;
    }
    

    public String getPhotoUrl() {
      return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
      this.photoUrl = photoUrl;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("id", getId())
            .append("name", getName())
            .append("idcard", getIdcard())
            .append("birthday", getBirthday())
            .append("salary",getSalary())
            .append("sex",getSex())
            .append("photoUrl",getPhotoUrl())
            .toString();
    }

    public boolean equals(Object other) {
        if ( !(other instanceof Demo) ) return false;
        Demo castOther = (Demo) other;
        return new EqualsBuilder()
            .append(this.getId(), castOther.getId())
            .append(this.getName(), castOther.getName())
            .append(this.getIdcard(), castOther.getIdcard())
            .append(this.getBirthday(), castOther.getBirthday())
            .isEquals();
    }

    public int hashCode() {
        return new HashCodeBuilder()
            .append(getId())
            .append(getName())
            .append(getIdcard())
            .append(getBirthday())
            .toHashCode();
    }

}
