package org.xpup.hafmis.orgstrct.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/** @author Hibernate CodeGenerator */
public class CollBank implements Serializable {

    /** identifier field */
    private Integer id;

    /** persistent field */
    private String office;

    /** persistent field */
    private Integer collBankId = new Integer(0);

    /** persistent field */
    private String collBankName;
    
    private Integer status = new Integer(0);
    
    //bit add
    private String contactprsn="";//联系人
    
    private String contacttel="";//联系电话
    
    private String collectionbankacc="";//归集银行账号
    
    private String centername="";//中心名称

    public String getCentername() {
      return centername;
    }

    public void setCentername(String centername) {
      this.centername = centername;
    }

    /** full constructor */
    public CollBank(Integer id, String office, Integer collBankId, String collBankName,Integer status,String contactprsn,String contacttel,String collectionbankacc,String centername) {
        this.id = id;
        this.office = office;
        this.collBankId = collBankId;
        this.collBankName = collBankName;
        this.status = status;
        this.contactprsn=contactprsn;
        this.contacttel=contacttel;
        this.collectionbankacc=collectionbankacc;
        this.centername=centername;
    }

    /** default constructor */
    public CollBank() {
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOffice() {
        return this.office;
    }

    public void setOffice(String office) {
        this.office = office;
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

    public Integer getStatus() {
      return status;
    }

    public void setStatus(Integer status) {
      this.status = status;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("id", getId())
            .toString();
    }

    public boolean equals(Object other) {
        if ( !(other instanceof CollBank) ) return false;
        CollBank castOther = (CollBank) other;
        return new EqualsBuilder()
            .append(this.getId(), castOther.getId())
            .isEquals();
    }  

    public int hashCode() {
        return new HashCodeBuilder()
            .append(getId())
            .toHashCode();
    }

    public String getCollectionbankacc() {
      return collectionbankacc;
    }

    public void setCollectionbankacc(String collectionbankacc) {
      this.collectionbankacc = collectionbankacc;
    }

    public String getContactprsn() {
      return contactprsn;
    }

    public void setContactprsn(String contactprsn) {
      this.contactprsn = contactprsn;
    }

    public String getContacttel() {
      return contacttel;
    }

    public void setContacttel(String contacttel) {
      this.contacttel = contacttel;
    }

}
