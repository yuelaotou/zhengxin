package org.xpup.signjoint.entity;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.xpup.hafmis.demo.domain.entity.Demo;

public class Yctest {

  private Integer id;
  private String name;
  public Yctest(Integer id,String name) {
    this.id=id;
    this.name=name;
  }
  public Yctest()
  {}
  public Integer getId() {
    return id;
  }
  public void setId(Integer id) {
    this.id = id;
  }
  public String getName() {
    return name;
  }
  public void setName(String name) {
    this.name = name;
  }
  
  public String toString() {
    return new ToStringBuilder(this)
        .append("id", getId())
        .append("name", getName())
        .toString();
  }
  public boolean equals(Object other) {
    if ( !(other instanceof Demo) ) return false;
    Demo castOther = (Demo) other;
    return new EqualsBuilder()
        .append(this.getId(), castOther.getId())
        .append(this.getName(), castOther.getName())
        .isEquals();
  }
  public int hashCode() {
    return new HashCodeBuilder()
        .append(getId())
        .append(getName())
        .toHashCode();
  }
  
  
}
