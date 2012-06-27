package org.xpup.hafmis.sysloan.common.domain.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/** @author Hibernate CodeGenerator */
public class AssistantOrg implements Serializable {

    /** identifier field */
    private Integer assistantOrgId;

    /** nullable persistent field */
    private String assistantOrgName;

    /** nullable persistent field */
    private String artfclprsn;

    /** nullable persistent field */
    private String code;

    /** nullable persistent field */
    private String assistantOrgAddr;

    /** nullable persistent field */
    private String basedDate;

    /** nullable persistent field */
    private String artfclprsnCardKind;

    /** nullable persistent field */
    private String artfclprsnCardNum;

    /** nullable persistent field */
    private String allowDept;

    /** nullable persistent field */
    private String allowId;

    /** nullable persistent field */
    private String agreementStartDate;

    /** nullable persistent field */
    private String agreementEndDate;

    /** nullable persistent field */
    private BigDecimal registerFund;

    /** nullable persistent field */
    private String paybank;

    /** nullable persistent field */
    private String payacc;

    /** nullable persistent field */
    private String contactPrsn;

    /** nullable persistent field */
    private String contactTel;

    /** nullable persistent field */
    private String business;

    /** nullable persistent field */
    private String remark;

    /** nullable persistent field */
    private String arear;

    /** nullable persistent field */
    private String assistantOrgType;

    /** nullable persistent field */
    private String reserveaA;

    /** nullable persistent field */
    private String reserveaB;

    /** nullable persistent field */
    private String reserveaC;
    
    private String photoUrl;

    /** full constructor */
    public AssistantOrg(Integer assistantOrgId, String assistantOrgName, String artfclprsn, String code, String assistantOrgAddr, String basedDate, String artfclprsnCardKind, String artfclprsnCardNum, String allowDept, String allowId, String agreementStartDate, String agreementEndDate, BigDecimal registerFund, String paybank, String payacc, String contactPrsn, String contactTel, String business, String remark, String arear, String assistantOrgType, String reserveaA, String reserveaB, String reserveaC) {
        this.assistantOrgId = assistantOrgId;
        this.assistantOrgName = assistantOrgName;
        this.artfclprsn = artfclprsn;
        this.code = code;
        this.assistantOrgAddr = assistantOrgAddr;
        this.basedDate = basedDate;
        this.artfclprsnCardKind = artfclprsnCardKind;
        this.artfclprsnCardNum = artfclprsnCardNum;
        this.allowDept = allowDept;
        this.allowId = allowId;
        this.agreementStartDate = agreementStartDate;
        this.agreementEndDate = agreementEndDate;
        this.registerFund = registerFund;
        this.paybank = paybank;
        this.payacc = payacc;
        this.contactPrsn = contactPrsn;
        this.contactTel = contactTel;
        this.business = business;
        this.remark = remark;
        this.arear = arear;
        this.assistantOrgType = assistantOrgType;
        this.reserveaA = reserveaA;
        this.reserveaB = reserveaB;
        this.reserveaC = reserveaC;
    }

    /** default constructor */
    public AssistantOrg() {
    }

    /** minimal constructor */
    public AssistantOrg(Integer assistantOrgId) {
        this.assistantOrgId = assistantOrgId;
    }

    public Integer getAssistantOrgId() {
        return this.assistantOrgId;
    }

    public void setAssistantOrgId(Integer assistantOrgId) {
        this.assistantOrgId = assistantOrgId;
    }

    public String getAssistantOrgName() {
        return this.assistantOrgName;
    }

    public void setAssistantOrgName(String assistantOrgName) {
        this.assistantOrgName = assistantOrgName;
    }

    public String getArtfclprsn() {
        return this.artfclprsn;
    }

    public void setArtfclprsn(String artfclprsn) {
        this.artfclprsn = artfclprsn;
    }

    public String getCode() {
        return this.code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getAssistantOrgAddr() {
        return this.assistantOrgAddr;
    }

    public void setAssistantOrgAddr(String assistantOrgAddr) {
        this.assistantOrgAddr = assistantOrgAddr;
    }

    public String getBasedDate() {
        return this.basedDate;
    }

    public void setBasedDate(String basedDate) {
        this.basedDate = basedDate;
    }

    public String getArtfclprsnCardKind() {
        return this.artfclprsnCardKind;
    }

    public void setArtfclprsnCardKind(String artfclprsnCardKind) {
        this.artfclprsnCardKind = artfclprsnCardKind;
    }

    public String getArtfclprsnCardNum() {
        return this.artfclprsnCardNum;
    }

    public void setArtfclprsnCardNum(String artfclprsnCardNum) {
        this.artfclprsnCardNum = artfclprsnCardNum;
    }

    public String getAllowDept() {
        return this.allowDept;
    }

    public void setAllowDept(String allowDept) {
        this.allowDept = allowDept;
    }

    public String getAllowId() {
        return this.allowId;
    }

    public void setAllowId(String allowId) {
        this.allowId = allowId;
    }

    public String getAgreementStartDate() {
        return this.agreementStartDate;
    }

    public void setAgreementStartDate(String agreementStartDate) {
        this.agreementStartDate = agreementStartDate;
    }

    public String getAgreementEndDate() {
        return this.agreementEndDate;
    }

    public void setAgreementEndDate(String agreementEndDate) {
        this.agreementEndDate = agreementEndDate;
    }

    public BigDecimal getRegisterFund() {
        return this.registerFund;
    }

    public void setRegisterFund(BigDecimal registerFund) {
        this.registerFund = registerFund;
    }

    public String getPaybank() {
        return this.paybank;
    }

    public void setPaybank(String paybank) {
        this.paybank = paybank;
    }

    public String getPayacc() {
        return this.payacc;
    }

    public void setPayacc(String payacc) {
        this.payacc = payacc;
    }

    public String getContactPrsn() {
        return this.contactPrsn;
    }

    public void setContactPrsn(String contactPrsn) {
        this.contactPrsn = contactPrsn;
    }

    public String getContactTel() {
        return this.contactTel;
    }

    public void setContactTel(String contactTel) {
        this.contactTel = contactTel;
    }

    public String getBusiness() {
        return this.business;
    }

    public void setBusiness(String business) {
        this.business = business;
    }

    public String getRemark() {
        return this.remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getArear() {
        return this.arear;
    }

    public void setArear(String arear) {
        this.arear = arear;
    }

    public String getAssistantOrgType() {
        return this.assistantOrgType;
    }

    public void setAssistantOrgType(String assistantOrgType) {
        this.assistantOrgType = assistantOrgType;
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
            .append("assistantOrgId", getAssistantOrgId())
            .toString();
    }

    public boolean equals(Object other) {
        if ( !(other instanceof AssistantOrg) ) return false;
        AssistantOrg castOther = (AssistantOrg) other;
        return new EqualsBuilder()
            .append(this.getAssistantOrgId(), castOther.getAssistantOrgId())
            .isEquals();
    }

    public int hashCode() {
        return new HashCodeBuilder()
            .append(getAssistantOrgId())
            .toHashCode();
    }

    public String getPhotoUrl() {
      return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
      this.photoUrl = photoUrl;
    }

}
