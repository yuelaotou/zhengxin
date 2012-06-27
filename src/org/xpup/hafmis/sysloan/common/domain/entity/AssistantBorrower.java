package org.xpup.hafmis.sysloan.common.domain.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/** @author Hibernate CodeGenerator */
public class AssistantBorrower implements Serializable {

    /** identifier field */
    private Integer auxiliaryId;

    /** persistent field */
    private String contractId;

    /** nullable persistent field */
    private BigDecimal empId=new BigDecimal(0.00);

    /** persistent field */
    private String name;

    /** nullable persistent field */
    private String relation;

    /** persistent field */
    private String sex;

    /** persistent field */
    private String cardKind;

    /** persistent field */
    private String cardNum;

    /** persistent field */
    private String birthday;

    /** persistent field */
    private BigDecimal age;

    /** nullable persistent field */
    private String business;

    /** nullable persistent field */
    private String title;

    /** nullable persistent field */
    private String nation;

    /** nullable persistent field */
    private String nativePlace;

    /** nullable persistent field */
    private String marriageSt;

    /** nullable persistent field */
    private String degree;

    /** nullable persistent field */
    private String homeAddr;

    /** nullable persistent field */
    private String homeMail;

    /** nullable persistent field */
    private String houseTel;

    /** nullable persistent field */
    private String homeMobile;

    /** nullable persistent field */
    private BigDecimal orgId;

    /** nullable persistent field */
    private String orgName;

    /** nullable persistent field */
    private String orgTel;

    /** nullable persistent field */
    private String orgMail;

    /** nullable persistent field */
    private String orgAddr;

    /** nullable persistent field */
    private BigDecimal accBlnce;

    /** nullable persistent field */
    private BigDecimal monthSalary;

    /** persistent field */
    private BigDecimal monthPay;

    /** nullable persistent field */
    private String empSt;

    /** nullable persistent field */
    private String bgnDate;

    /** nullable persistent field */
    private String endDate;

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
 
    private BigDecimal maxPickBalance;
    
    public BigDecimal getMaxPickBalance() {
      return maxPickBalance;
    }

    public void setMaxPickBalance(BigDecimal maxPickBalance) {
      this.maxPickBalance = maxPickBalance;
    }

    public String getPhotoUrl() {
      return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
      this.photoUrl = photoUrl;
    }

    /** full constructor */
    public AssistantBorrower(Integer auxiliaryId, String contractId, BigDecimal empId, String name, String relation, String sex, String cardKind, String cardNum, String birthday, BigDecimal age, String business, String title, String nation, String nativePlace, String marriageSt, String degree, String homeAddr, String homeMail, String houseTel, String homeMobile, BigDecimal orgId, String orgName, String orgTel, String orgMail, String orgAddr, BigDecimal accBlnce, BigDecimal monthSalary, BigDecimal monthPay, String empSt, String bgnDate, String endDate, String status, String operator, Date opTime, String reserveaA, String reserveaB, String reserveaC,String photoUrl,BigDecimal maxPickBalance) {
        this.auxiliaryId = auxiliaryId;
        this.contractId = contractId;
        this.empId = empId;
        this.name = name;
        this.relation = relation;
        this.sex = sex;
        this.cardKind = cardKind;
        this.cardNum = cardNum;
        this.birthday = birthday;
        this.age = age;
        this.business = business;
        this.title = title;
        this.nation = nation;
        this.nativePlace = nativePlace;
        this.marriageSt = marriageSt;
        this.degree = degree;
        this.homeAddr = homeAddr;
        this.homeMail = homeMail;
        this.houseTel = houseTel;
        this.homeMobile = homeMobile;
        this.orgId = orgId;
        this.orgName = orgName;
        this.orgTel = orgTel;
        this.orgMail = orgMail;
        this.orgAddr = orgAddr;
        this.accBlnce = accBlnce;
        this.monthSalary = monthSalary;
        this.monthPay = monthPay;
        this.empSt = empSt;
        this.bgnDate = bgnDate;
        this.endDate = endDate;
        this.status = status;
        this.operator = operator;
        this.opTime = opTime;
        this.reserveaA = reserveaA;
        this.reserveaB = reserveaB;
        this.reserveaC = reserveaC;
        this.photoUrl = photoUrl;
        this.maxPickBalance=maxPickBalance;
    }

    /** default constructor */
    public AssistantBorrower() {
    }

    /** minimal constructor */
    public AssistantBorrower(Integer auxiliaryId, String contractId, String name, String sex, String cardKind, String cardNum, String birthday, BigDecimal age, String operator, Date opTime) {
        this.auxiliaryId = auxiliaryId;
        this.contractId = contractId;
        this.name = name;
        this.sex = sex;
        this.cardKind = cardKind;
        this.cardNum = cardNum;
        this.birthday = birthday;
        this.age = age;
        this.operator =operator;
        this.opTime = opTime;
    }

    public Integer getAuxiliaryId() {
        return this.auxiliaryId;
    }

    public void setAuxiliaryId(Integer auxiliaryId) {
        this.auxiliaryId = auxiliaryId;
    }

    public String getContractId() {
        return this.contractId;
    }

    public void setContractId(String contractId) {
        this.contractId = contractId;
    }

    public BigDecimal getEmpId() {
        return this.empId;
    }

    public void setEmpId(BigDecimal empId) {
        this.empId = empId;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRelation() {
        return this.relation;
    }

    public void setRelation(String relation) {
        this.relation = relation;
    }

    public String getSex() {
        return this.sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
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

    public String getBirthday() {
        return this.birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public BigDecimal getAge() {
        return this.age;
    }

    public void setAge(BigDecimal age) {
        this.age = age;
    }

    public String getBusiness() {
        return this.business;
    }

    public void setBusiness(String business) {
        this.business = business;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getNation() {
        return this.nation;
    }

    public void setNation(String nation) {
        this.nation = nation;
    }

    public String getNativePlace() {
        return this.nativePlace;
    }

    public void setNativePlace(String nativePlace) {
        this.nativePlace = nativePlace;
    }

    public String getMarriageSt() {
        return this.marriageSt;
    }

    public void setMarriageSt(String marriageSt) {
        this.marriageSt = marriageSt;
    }

    public String getDegree() {
        return this.degree;
    }

    public void setDegree(String degree) {
        this.degree = degree;
    }

    public String getHomeAddr() {
        return this.homeAddr;
    }

    public void setHomeAddr(String homeAddr) {
        this.homeAddr = homeAddr;
    }

    public String getHomeMail() {
        return this.homeMail;
    }

    public void setHomeMail(String homeMail) {
        this.homeMail = homeMail;
    }

    public String getHouseTel() {
        return this.houseTel;
    }

    public void setHouseTel(String houseTel) {
        this.houseTel = houseTel;
    }

    public String getHomeMobile() {
        return this.homeMobile;
    }

    public void setHomeMobile(String homeMobile) {
        this.homeMobile = homeMobile;
    }

    public BigDecimal getOrgId() {
        return this.orgId;
    }

    public void setOrgId(BigDecimal orgId) {
        this.orgId = orgId;
    }

    public String getOrgName() {
        return this.orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public String getOrgTel() {
        return this.orgTel;
    }

    public void setOrgTel(String orgTel) {
        this.orgTel = orgTel;
    }

    public String getOrgMail() {
        return this.orgMail;
    }

    public void setOrgMail(String orgMail) {
        this.orgMail = orgMail;
    }

    public String getOrgAddr() {
        return this.orgAddr;
    }

    public void setOrgAddr(String orgAddr) {
        this.orgAddr = orgAddr;
    }

    public BigDecimal getAccBlnce() {
        return this.accBlnce;
    }

    public void setAccBlnce(BigDecimal accBlnce) {
        this.accBlnce = accBlnce;
    }

    public BigDecimal getMonthSalary() {
        return this.monthSalary;
    }

    public void setMonthSalary(BigDecimal monthSalary) {
        this.monthSalary = monthSalary;
    }

    public BigDecimal getMonthPay() {
        return this.monthPay;
    }

    public void setMonthPay(BigDecimal monthPay) {
        this.monthPay = monthPay;
    }

    public String getEmpSt() {
        return this.empSt;
    }

    public void setEmpSt(String empSt) {
        this.empSt = empSt;
    }

    public String getBgnDate() {
        return this.bgnDate;
    }

    public void setBgnDate(String bgnDate) {
        this.bgnDate = bgnDate;
    }

    public String getEndDate() {
        return this.endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
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
            .append("auxiliaryId", getAuxiliaryId())
            .toString();
    }

    public boolean equals(Object other) {
        if ( !(other instanceof AssistantBorrower) ) return false;
        AssistantBorrower castOther = (AssistantBorrower) other;
        return new EqualsBuilder()
            .append(this.getAuxiliaryId(), castOther.getAuxiliaryId())
            .isEquals();
    }

    public int hashCode() {
        return new HashCodeBuilder()
            .append(getAuxiliaryId())
            .toHashCode();
    }

}
