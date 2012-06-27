package org.xpup.hafmis.sysloan.common.domain.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/** @author Hibernate CodeGenerator */
public class ImpawnContract implements Serializable {

    /** identifier field */
    private Integer id;

    /** persistent field */
    private String contractId;

    /** nullable persistent field */
    private String impawnContractId;

    /** persistent field */
    private String impawnMatterName;

    /** persistent field */
    private BigDecimal impawnValue;

    /** nullable persistent field */
    private String name;

    /** nullable persistent field */
    private String cardKind;

    /** nullable persistent field */
    private String cardNum;

    /** nullable persistent field */
    private String tel;

    /** nullable persistent field */
    private String mobile;

    /** nullable persistent field */
    private String paperNum;

    /** nullable persistent field */
    private String paperName;

    /** nullable persistent field */
    private String status;

    /** nullable persistent field */
    private String operator;

    /** nullable persistent field */
    private Date opTime;

    /** nullable persistent field */
    private String reserveaA;

    /** nullable persistent field */
    private String reserveaB;

    /** nullable persistent field */
    private String reserveaC;
    private String photoUrl;

    public String getPhotoUrl() {
      return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
      this.photoUrl = photoUrl;
    }
    /** full constructor */
    public ImpawnContract(Integer id, String contractId, String impawnContractId, String impawnMatterName, BigDecimal impawnValue, String name, String cardKind, String cardNum, String tel, String mobile, String paperNum, String paperName, String status, String operator, Date opTime, String reserveaA, String reserveaB, String reserveaC) {
        this.id = id;
        this.contractId = contractId;
        this.impawnContractId = impawnContractId;
        this.impawnMatterName = impawnMatterName;
        this.impawnValue = impawnValue;
        this.name = name;
        this.cardKind = cardKind;
        this.cardNum = cardNum;
        this.tel = tel;
        this.mobile = mobile;
        this.paperNum = paperNum;
        this.paperName = paperName;
        this.status = status;
        this.operator = operator;
        this.opTime = opTime;
        this.reserveaA = reserveaA;
        this.reserveaB = reserveaB;
        this.reserveaC = reserveaC;
    }

    /** default constructor */
    public ImpawnContract() {
    }

    /** minimal constructor */
    public ImpawnContract(Integer id, String contractId, String impawnMatterName, BigDecimal impawnValue) {
        this.id = id;
        this.contractId = contractId;
        this.impawnMatterName = impawnMatterName;
        this.impawnValue = impawnValue;
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getContractId() {
        return this.contractId;
    }

    public void setContractId(String contractId) {
        this.contractId = contractId;
    }

    public String getImpawnContractId() {
        return this.impawnContractId;
    }

    public void setImpawnContractId(String impawnContractId) {
        this.impawnContractId = impawnContractId;
    }

    public String getImpawnMatterName() {
        return this.impawnMatterName;
    }

    public void setImpawnMatterName(String impawnMatterName) {
        this.impawnMatterName = impawnMatterName;
    }

    public BigDecimal getImpawnValue() {
        return this.impawnValue;
    }

    public void setImpawnValue(BigDecimal impawnValue) {
        this.impawnValue = impawnValue;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCardKind() {
        return this.cardKind;
    }

    public void setCardKind(String cardKind) {
        this.cardKind = cardKind;
    }

    public String getCardNum() {
        return this.cardNum;
    }

    public void setCardNum(String cardNum) {
        this.cardNum = cardNum;
    }

    public String getTel() {
        return this.tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getMobile() {
        return this.mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getPaperNum() {
        return this.paperNum;
    }

    public void setPaperNum(String paperNum) {
        this.paperNum = paperNum;
    }

    public String getPaperName() {
        return this.paperName;
    }

    public void setPaperName(String paperName) {
        this.paperName = paperName;
    }

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getOperator() {
        return this.operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public Date getOpTime() {
        return this.opTime;
    }

    public void setOpTime(Date opTime) {
        this.opTime = opTime;
    }

    public String getReserveaA() {
        return this.reserveaA;
    }

    public void setReserveaA(String reserveaA) {
        this.reserveaA = reserveaA;
    }

    public String getReserveaB() {
        return this.reserveaB;
    }

    public void setReserveaB(String reserveaB) {
        this.reserveaB = reserveaB;
    }

    public String getReserveaC() {
        return this.reserveaC;
    }

    public void setReserveaC(String reserveaC) {
        this.reserveaC = reserveaC;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("id", getId())
            .toString();
    }

    public boolean equals(Object other) {
        if ( !(other instanceof ImpawnContract) ) return false;
        ImpawnContract castOther = (ImpawnContract) other;
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
