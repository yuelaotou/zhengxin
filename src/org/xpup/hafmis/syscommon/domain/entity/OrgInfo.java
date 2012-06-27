package org.xpup.hafmis.syscommon.domain.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/** @author Hibernate CodeGenerator */
public class OrgInfo extends DomainObject {

    /** persistent field */
    private String name="";

    /** persistent field */
    private String officecode;
    /** persistent field */
    private String temp_officename;

    /** nullable persistent field */
    private String code;

    /** nullable persistent field */
    private String taxRegNum;

    /** nullable persistent field */
    private String artificialPerson;

    /** nullable persistent field */
    private String character;
    private String temp_character;

    /** nullable persistent field */
    private String industry;
    private String temp_industry;
    /** nullable persistent field */
    private String deptInCharge;
    private String temp_deptInCharge;

    /** nullable persistent field */
    private String address;

    /** nullable persistent field */
    private String postalcode;

    /** nullable persistent field */
    private String tel;

    /** nullable persistent field */
    private String region;
    private String temp_region;

    /**
     * 发薪银行
     */
    private PayBank payBank = new PayBank();
    /**
     * 经办人
     */
    private Transactor transactor = new Transactor();

    /** nullable persistent field */
    private String paydate;

    /** nullable persistent field */
    private String collectionBankId;
    /** nullable persistent field */
    private String temp_collectionBankname;

    /** nullable persistent field */
    private String inspector;

    /** nullable persistent field */
    private String openstatus;
    private String temp_openstatus;

    /** nullable persistent field */
    private String openDate;
    /**备选a*/
    private String reserveaA;
    private String reserveaB;
    private String reserveaC;
    
    private String tijiao="";

    /** full constructor */
    public OrgInfo(String name, String officecode, String code, String taxRegNum, String artificialPerson, String character, String industry, String deptInCharge, String address, String postalcode, String tel, String region, String paybankName, String paybankAccountNum, String paydate, String collectionBankId, String transactorName, String transactorTel, String transactorMobiletel, String transactorEmail, String inspector, String openstatus, String openDate,String reserveaA, String reserveaB, String reserveaC) {

        this.name = name;
        this.officecode = officecode;
        this.code = code;
        this.taxRegNum = taxRegNum;
        this.artificialPerson = artificialPerson;
        this.character = character;
        this.industry = industry;
        this.deptInCharge = deptInCharge;
        this.address = address;
        this.postalcode = postalcode;
        this.tel = tel;
        this.region = region;
        this.payBank.setName(paybankName);
        this.payBank.setAccountNum(paybankAccountNum) ;
        this.paydate = paydate;
        this.collectionBankId = collectionBankId;
        this.transactor.setName(transactorName);
        this.transactor.setTelephone(transactorTel);
        this.transactor.setMobileTelephone(transactorMobiletel);
        this.transactor.setEmail(transactorEmail);
        this.inspector = inspector;
        this.openstatus = openstatus;
        this.openDate = openDate;
        this.reserveaA = reserveaA;
        this.reserveaB = reserveaB;
        this.reserveaC = reserveaC;
    }

    /** default constructor */
    public OrgInfo() {
    }

    /** minimal constructor */
    public OrgInfo(Integer id, String name, String officecode) {
        this.setId(id);
        this.name = name;
        this.officecode = officecode;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOfficecode() {
        return this.officecode;
    }

    public void setOfficecode(String officecode) {
        this.officecode = officecode;
    }

    public String getCode() {
        return this.code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getTaxRegNum() {
        return this.taxRegNum;
    }

    public void setTaxRegNum(String taxRegNum) {
        this.taxRegNum = taxRegNum;
    }

    public String getArtificialPerson() {
        return this.artificialPerson;
    }

    public void setArtificialPerson(String artificialPerson) {
        this.artificialPerson = artificialPerson;
    }

    public String getCharacter() {
        return this.character;
    }

    public void setCharacter(String character) {
        this.character = character;
    }

    public String getIndustry() {
        return this.industry;
    }

    public void setIndustry(String industry) {
        this.industry = industry;
    }

    public String getDeptInCharge() {
        return this.deptInCharge;
    }

    public void setDeptInCharge(String deptInCharge) {
        this.deptInCharge = deptInCharge;
    }

    public String getAddress() {
        return this.address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPostalcode() {
        return this.postalcode;
    }

    public void setPostalcode(String postalcode) {
        this.postalcode = postalcode;
    }

    public String getTel() {
        return this.tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getRegion() {
        return this.region;
    }

    public void setRegion(String region) {
        this.region = region;
    }
    public PayBank getPayBank() {
      return payBank;
    }

    public void setPayBank(PayBank payBank) {
      this.payBank = payBank;
    }
    public String getPaydate() {
        return this.paydate;
    }

    public void setPaydate(String paydate) {
        this.paydate = paydate;
    }

    public String getCollectionBankId() {
        return this.collectionBankId;
    }

    public void setCollectionBankId(String collectionBankId) {
        this.collectionBankId = collectionBankId;
    }
    public Transactor getTransactor() {
      return transactor;
    }

    public void setTransactor(Transactor transactor) {
      this.transactor = transactor;
    }

    public String getInspector() {
        return this.inspector;
    }

    public void setInspector(String inspector) {
        this.inspector = inspector;
    }

    public String getOpenstatus() {
        return this.openstatus;
    }

    public void setOpenstatus(String openstatus) {
        this.openstatus = openstatus;
    }

    public String getOpenDate() {
        return this.openDate;
    }

    public void setOpenDate(String openDate) {
        this.openDate = openDate;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("id", getId())
            .toString();
    }

    public boolean equals(Object other) {
        if ( !(other instanceof OrgInfo) ) return false;
        OrgInfo castOther = (OrgInfo) other;
        return new EqualsBuilder()
            .append(this.getId(), castOther.getId())
            .isEquals();
    }

    public int hashCode() {
        return new HashCodeBuilder()
            .append(getId())
            .toHashCode();
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

    public String getTemp_collectionBankname() {
      return temp_collectionBankname;
    }

    public void setTemp_collectionBankname(String temp_collectionBankname) {
      this.temp_collectionBankname = temp_collectionBankname;
    }

    public String getTemp_officename() {
      return temp_officename;
    }

    public void setTemp_officename(String temp_officename) {
      this.temp_officename = temp_officename;
    }

    public String getTemp_character() {
      return temp_character;
    }

    public void setTemp_character(String temp_character) {
      this.temp_character = temp_character;
    }

    public String getTemp_deptInCharge() {
      return temp_deptInCharge;
    }

    public void setTemp_deptInCharge(String temp_deptInCharge) {
      this.temp_deptInCharge = temp_deptInCharge;
    }

    public String getTemp_region() {
      return temp_region;
    }

    public void setTemp_region(String temp_region) {
      this.temp_region = temp_region;
    }

    public String getTemp_openstatus() {
      return temp_openstatus;
    }

    public void setTemp_openstatus(String temp_openstatus) {
      this.temp_openstatus = temp_openstatus;
    }

    public String getTemp_industry() {
      return temp_industry;
    }

    public void setTemp_industry(String temp_industry) {
      this.temp_industry = temp_industry;
    }

    public String getTijiao() {
      return tijiao;
    }

    public void setTijiao(String tijiao) {
      this.tijiao = tijiao;
    }

}
