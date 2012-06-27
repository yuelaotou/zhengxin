package org.xpup.hafmis.syscollection.common.domain.entity;

import java.math.BigDecimal;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.xpup.hafmis.syscommon.domain.entity.DomainObject;

/** @author Hibernate CodeGenerator */

public class SettInterestHead extends DomainObject {

  private static final long serialVersionUID = 3953855593268499059L;

    /** persistent field */
    private Org org=new Org();

    /** nullable persistent field */
    private Integer bizId;

    /** persistent field */
    private String  type="";

    /** persistent field */
    private String year="";
    
    /** persistent field */
    private String reserveaA="";
    
    /** persistent field */
    private String reserveaB="";
    
    /** persistent field */
    private String reserveaC="";
    
    /** persistent field */
    private String docNum="";
    
    private String interestType = "";// 单位版_修改记录：wangy 2008-02-28

    public String getInterestType() {
      return interestType;
    }

    public void setInterestType(String interestType) {
      this.interestType = interestType;
    }

    public String getReserveaA() {
      return reserveaA;
    }

    public void setReserveaA(String reserveaA) {
      this.reserveaA = reserveaA;
    }

    public String getReserveaB() {
      return reserveaB;
    }

    public void setReserveaB(String reserveaB) {
      this.reserveaB = reserveaB;
    }

    public String getReserveaC() {
      return reserveaC;
    }

    public void setReserveaC(String reserveaC) {
      this.reserveaC = reserveaC;
    }


    public String getDocNum() {
      return docNum;
    }

    public void setDocNum(String docNum) {
      this.docNum = docNum;
    }

    public SettInterestHead( Org org, Integer bizId, String type, String year,String reserveaA, String reserveaB, String reserveaC,String docNum) {
      
        this.org = org;
        this.bizId = bizId;
        this.type = type;
        this.year = year;
        this.docNum=docNum;
        this.reserveaA=reserveaA;
        this.reserveaB=reserveaB;
        this.reserveaC=reserveaC;
        this.docNum = docNum;

    }

    /** default constructor */
    public SettInterestHead() {
    }

    public SettInterestHead(Org org,  String type, String year) {
      
        this.org = org;
        this.type = type;
        this.year = year;
    }

    public Integer getBizId() {
        return this.bizId;
    }

    public void setBizId(Integer bizId) {
        this.bizId = bizId;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getYear() {
        return this.year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("id", getId())
            .toString();
    }

    public boolean equals(Object other) {
        if ( !(other instanceof SettInterestHead) ) return false;
        SettInterestHead castOther = (SettInterestHead) other;
        return new EqualsBuilder()
            .append(this.getId(), castOther.getId())
            .isEquals();
    }

    public int hashCode() {
        return new HashCodeBuilder()
            .append(getId())
            .toHashCode();
    }

    public Org getOrg() {
      return org;
    }

    public void setOrg(Org org) {
      this.org = org;
    }

}
