package org.xpup.hafmis.sysloan.common.domain.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/** @author Hibernate CodeGenerator */
public class PledgeContract implements Serializable {

    /** identifier field */
    private Integer id;

    /** persistent field */
    private String contractId;

    /** nullable persistent field */
    private String pledgeContractId;

    /** persistent field */
    private String pledgeMatterName;

    /** persistent field */
    private BigDecimal pledgeValue;

    /** nullable persistent field */
    private String paperNum;

    /** nullable persistent field */
    private String paperName;

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
    private String pledgeAddr;

    /** nullable persistent field */
    private BigDecimal area;

    /** nullable persistent field */
    private BigDecimal evaluateValue;

    /** nullable persistent field */
    private String reserved;

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
    private String rebate;
    private String reserveaC;
    private String photoUrl;

    public String getPhotoUrl() {
      return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
      this.photoUrl = photoUrl;
    }
    /** full constructor */
    public PledgeContract(Integer id, String contractId, String pledgeContractId, String pledgeMatterName, BigDecimal pledgeValue, String paperNum, String paperName, String name, String cardKind, String cardNum, String tel, String mobile, String pledgeAddr, BigDecimal area, BigDecimal evaluateValue, String reserved, String status, String operator, Date opTime, String reserveaA, String reserveaB, String reserveaC) {
        this.id = id;
        this.contractId = contractId;
        this.pledgeContractId = pledgeContractId;
        this.pledgeMatterName = pledgeMatterName;
        this.pledgeValue = pledgeValue;
        this.paperNum = paperNum;
        this.paperName = paperName;
        this.name = name;
        this.cardKind = cardKind;
        this.cardNum = cardNum;
        this.tel = tel;
        this.mobile = mobile;
        this.pledgeAddr = pledgeAddr;
        this.area = area;
        this.evaluateValue = evaluateValue;
        this.reserved = reserved;
        this.status = status;
        this.operator = operator;
        this.opTime = opTime;
        this.reserveaA = reserveaA;
        this.reserveaB = reserveaB;
        this.reserveaC = reserveaC;
    }

    /** default constructor */
    public PledgeContract() {
    }

    /** minimal constructor */
    public PledgeContract(Integer id, String contractId, String pledgeMatterName, BigDecimal pledgeValue) {
        this.id = id;
        this.contractId = contractId;
        this.pledgeMatterName = pledgeMatterName;
        this.pledgeValue = pledgeValue;
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

    public String getPledgeContractId() {
        return this.pledgeContractId;
    }

    public void setPledgeContractId(String pledgeContractId) {
        this.pledgeContractId = pledgeContractId;
    }

    public String getPledgeMatterName() {
        return this.pledgeMatterName;
    }

    public void setPledgeMatterName(String pledgeMatterName) {
        this.pledgeMatterName = pledgeMatterName;
    }

    public BigDecimal getPledgeValue() {
        return this.pledgeValue;
    }

    public void setPledgeValue(BigDecimal pledgeValue) {
        this.pledgeValue = pledgeValue;
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

    public String getPledgeAddr() {
        return this.pledgeAddr;
    }

    public void setPledgeAddr(String pledgeAddr) {
        this.pledgeAddr = pledgeAddr;
    }

    public BigDecimal getArea() {
        return this.area;
    }

    public void setArea(BigDecimal area) {
        this.area = area;
    }

    public BigDecimal getEvaluateValue() {
        return this.evaluateValue;
    }

    public void setEvaluateValue(BigDecimal evaluateValue) {
        this.evaluateValue = evaluateValue;
    }

    public String getReserved() {
        return this.reserved;
    }

    public void setReserved(String reserved) {
        this.reserved = reserved;
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
        if ( !(other instanceof PledgeContract) ) return false;
        PledgeContract castOther = (PledgeContract) other;
        return new EqualsBuilder()
            .append(this.getId(), castOther.getId())
            .isEquals();
    }

    public int hashCode() {
        return new HashCodeBuilder()
            .append(getId())
            .toHashCode();
    }

    public String getRebate() {
      return rebate;
    }

    public void setRebate(String rebate) {
      this.rebate = rebate;
    }

}
