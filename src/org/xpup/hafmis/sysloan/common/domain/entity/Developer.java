package org.xpup.hafmis.sysloan.common.domain.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.xpup.hafmis.syscommon.domain.entity.DomainObject;

/** @author Hibernate CodeGenerator */
public class Developer extends DomainObject implements Serializable {

    /** identifier field */

    /** persistent field */
    private String developerId;

    /** persistent field */
    private String developerName;
    
    private String developerCode;

    /** nullable persistent field */
    private String landUseId;

    /** nullable persistent field */
    private String salePermis;

    /** persistent field */
    private String office;

    /** nullable persistent field */
    private String code;

    /** nullable persistent field */
    private String artfclprsn;

    /** nullable persistent field */
    private String artfclprsnTel;

    /** nullable persistent field */
    private String developerAddr;

    /** nullable persistent field */
    private String contactPrsnA;

    /** nullable persistent field */
    private String contactTelA;

    /** nullable persistent field */
    private String contactMobileA;

    /** nullable persistent field */
    private String businessA;

    /** nullable persistent field */
    private String contactPrsnB;

    /** nullable persistent field */
    private String contactTelB;

    /** nullable persistent field */
    private String businessB;

    /** nullable persistent field */
    private String contactMobileB;

    /** persistent field */
    private String developerPaybankA;

    /** persistent field */
    private String developerPaybankAAcc;

    /** nullable persistent field */
    private String developerPaybankB;

    /** nullable persistent field */
    private String developerPaybankBAcc;

    /** nullable persistent field */
    private String contactPrsnC;

    /** nullable persistent field */
    private String contactMobileC;

    /** nullable persistent field */
    private String contactTelC;

    /** nullable persistent field */
    private String businessC;

    /** nullable persistent field */
    private String workAddr;

    /** nullable persistent field */
    private String postalCode;

    /** nullable persistent field */
    private String orgTel;

    /** nullable persistent field */
    private String region;

    /** nullable persistent field */
    private String agreementStartDate;

    /** nullable persistent field */
    private String agreementEndDate;

    /** nullable persistent field */
    private String developerSt;

    /** nullable persistent field */
    private String remark;

    /** nullable persistent field */
    private String reserveaA;

    /** nullable persistent field */
    private String reserveaB;

    /** nullable persistent field */
    private String reserveaC;
    
    private String photoUrl;
    
    private String registerFundNumber;
    
    public String getRegisterFundNumber() {
      return registerFundNumber;
    }

    public void setRegisterFundNumber(String registerFundNumber) {
      this.registerFundNumber = registerFundNumber;
    }

    public String getPhotoUrl() {
      return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
      this.photoUrl = photoUrl;
    }

    /** full constructor */
    public Developer( String developerId, String developerName, String landUseId, String salePermis, String office, String code, String artfclprsn, String artfclprsnTel, String developerAddr, String contactPrsnA, String contactTelA, String contactMobileA, String businessA, String contactPrsnB, String contactTelB, String businessB, String contactMobileB, String developerPaybankA, String developerPaybankAAcc, String developerPaybankB, String developerPaybankBAcc, String contactPrsnC, String contactMobileC, String contactTelC, String businessC, String workAddr, String postalCode, String orgTel, String region, String agreementStartDate, String agreementEndDate, String developerSt, String remark, String reserveaA, String reserveaB, String reserveaC) {
       
        this.developerId = developerId;
        this.developerName = developerName;
        this.landUseId = landUseId;
        this.salePermis = salePermis;
        this.office = office;
        this.code = code;
        this.artfclprsn = artfclprsn;
        this.artfclprsnTel = artfclprsnTel;
        this.developerAddr = developerAddr;
        this.contactPrsnA = contactPrsnA;
        this.contactTelA = contactTelA;
        this.contactMobileA = contactMobileA;
        this.businessA = businessA;
        this.contactPrsnB = contactPrsnB;
        this.contactTelB = contactTelB;
        this.businessB = businessB;
        this.contactMobileB = contactMobileB;
        this.developerPaybankA = developerPaybankA;
        this.developerPaybankAAcc = developerPaybankAAcc;
        this.developerPaybankB = developerPaybankB;
        this.developerPaybankBAcc = developerPaybankBAcc;
        this.contactPrsnC = contactPrsnC;
        this.contactMobileC = contactMobileC;
        this.contactTelC = contactTelC;
        this.businessC = businessC;
        this.workAddr = workAddr;
        this.postalCode = postalCode;
        this.orgTel = orgTel;
        this.region = region;
        this.agreementStartDate = agreementStartDate;
        this.agreementEndDate = agreementEndDate;
        this.developerSt = developerSt;
        this.remark = remark;
        this.reserveaA = reserveaA;
        this.reserveaB = reserveaB;
        this.reserveaC = reserveaC;
    }

    /** default constructor */
    public Developer() {
    }

    /** minimal constructor */
    public Developer( String developerId, String developerName, String office, String developerPaybankA, String developerPaybankAAcc) {
        
        this.developerId = developerId;
        this.developerName = developerName;
        this.office = office;
        this.developerPaybankA = developerPaybankA;
        this.developerPaybankAAcc = developerPaybankAAcc;
    }



    public String getDeveloperId() {
        return this.developerId;
    }

    public void setDeveloperId(String developerId) {
        this.developerId = developerId;
    }

    public String getDeveloperName() {
        return this.developerName;
    }

    public void setDeveloperName(String developerName) {
        this.developerName = developerName;
    }

    public String getLandUseId() {
        return this.landUseId;
    }

    public void setLandUseId(String landUseId) {
        this.landUseId = landUseId;
    }

    public String getSalePermis() {
        return this.salePermis;
    }

    public void setSalePermis(String salePermis) {
        this.salePermis = salePermis;
    }

    public String getOffice() {
        return this.office;
    }

    public void setOffice(String office) {
        this.office = office;
    }

    public String getCode() {
        return this.code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getArtfclprsn() {
        return this.artfclprsn;
    }

    public void setArtfclprsn(String artfclprsn) {
        this.artfclprsn = artfclprsn;
    }

    public String getArtfclprsnTel() {
        return this.artfclprsnTel;
    }

    public void setArtfclprsnTel(String artfclprsnTel) {
        this.artfclprsnTel = artfclprsnTel;
    }

    public String getDeveloperAddr() {
        return this.developerAddr;
    }

    public void setDeveloperAddr(String developerAddr) {
        this.developerAddr = developerAddr;
    }

    public String getContactPrsnA() {
        return this.contactPrsnA;
    }

    public void setContactPrsnA(String contactPrsnA) {
        this.contactPrsnA = contactPrsnA;
    }

    public String getContactTelA() {
        return this.contactTelA;
    }

    public void setContactTelA(String contactTelA) {
        this.contactTelA = contactTelA;
    }

    public String getContactMobileA() {
        return this.contactMobileA;
    }

    public void setContactMobileA(String contactMobileA) {
        this.contactMobileA = contactMobileA;
    }

    public String getBusinessA() {
        return this.businessA;
    }

    public void setBusinessA(String businessA) {
        this.businessA = businessA;
    }

    public String getContactPrsnB() {
        return this.contactPrsnB;
    }

    public void setContactPrsnB(String contactPrsnB) {
        this.contactPrsnB = contactPrsnB;
    }

    public String getContactTelB() {
        return this.contactTelB;
    }

    public void setContactTelB(String contactTelB) {
        this.contactTelB = contactTelB;
    }

    public String getBusinessB() {
        return this.businessB;
    }

    public void setBusinessB(String businessB) {
        this.businessB = businessB;
    }

    public String getContactMobileB() {
        return this.contactMobileB;
    }

    public void setContactMobileB(String contactMobileB) {
        this.contactMobileB = contactMobileB;
    }

    public String getDeveloperPaybankA() {
        return this.developerPaybankA;
    }

    public void setDeveloperPaybankA(String developerPaybankA) {
        this.developerPaybankA = developerPaybankA;
    }

    public String getDeveloperPaybankAAcc() {
        return this.developerPaybankAAcc;
    }

    public void setDeveloperPaybankAAcc(String developerPaybankAAcc) {
        this.developerPaybankAAcc = developerPaybankAAcc;
    }

    public String getDeveloperPaybankB() {
        return this.developerPaybankB;
    }

    public void setDeveloperPaybankB(String developerPaybankB) {
        this.developerPaybankB = developerPaybankB;
    }

    public String getDeveloperPaybankBAcc() {
        return this.developerPaybankBAcc;
    }

    public void setDeveloperPaybankBAcc(String developerPaybankBAcc) {
        this.developerPaybankBAcc = developerPaybankBAcc;
    }

    public String getContactPrsnC() {
        return this.contactPrsnC;
    }

    public void setContactPrsnC(String contactPrsnC) {
        this.contactPrsnC = contactPrsnC;
    }

    public String getContactMobileC() {
        return this.contactMobileC;
    }

    public void setContactMobileC(String contactMobileC) {
        this.contactMobileC = contactMobileC;
    }

    public String getContactTelC() {
        return this.contactTelC;
    }

    public void setContactTelC(String contactTelC) {
        this.contactTelC = contactTelC;
    }

    public String getBusinessC() {
        return this.businessC;
    }

    public void setBusinessC(String businessC) {
        this.businessC = businessC;
    }

    public String getWorkAddr() {
        return this.workAddr;
    }

    public void setWorkAddr(String workAddr) {
        this.workAddr = workAddr;
    }

    public String getPostalCode() {
        return this.postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getOrgTel() {
        return this.orgTel;
    }

    public void setOrgTel(String orgTel) {
        this.orgTel = orgTel;
    }

    public String getRegion() {
        return this.region;
    }

    public void setRegion(String region) {
        this.region = region;
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

    public String getDeveloperSt() {
        return this.developerSt;
    }

    public void setDeveloperSt(String developerSt) {
        this.developerSt = developerSt;
    }

    public String getRemark() {
        return this.remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
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
        if ( !(other instanceof Developer) ) return false;
        Developer castOther = (Developer) other;
        return new EqualsBuilder()
            .append(this.getId(), castOther.getId())
            .isEquals();
    }

    public int hashCode() {
        return new HashCodeBuilder()
            .append(getId())
            .toHashCode();
    }

    public String getDeveloperCode() {
      return developerCode;
    }

    public void setDeveloperCode(String developerCode) {
      this.developerCode = developerCode;
    }

}
