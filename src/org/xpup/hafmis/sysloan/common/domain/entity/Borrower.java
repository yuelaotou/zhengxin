package org.xpup.hafmis.sysloan.common.domain.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/** @author Hibernate CodeGenerator */
public class Borrower implements Serializable {

  /** identifier field */
  private String contractId;

  /** persistent field */
  private String office;

  /** nullable persistent field */
  private BigDecimal empId = new BigDecimal(0);

  /** persistent field */
  private String borrowerName;

  /** persistent field */
  private String sex;

  /** persistent field */
  private String cardKind;

  /** persistent field */
  private String cardNum;

  /** persistent field */
  private String birthday;

  /** persistent field */
  private BigDecimal age = new BigDecimal(0.00);

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
  private BigDecimal orgId = new BigDecimal(0.00);

  /** nullable persistent field */
  private String orgName;

  /** nullable persistent field */
  private String orgTel;

  /** nullable persistent field */
  private String orgMail;

  /** nullable persistent field */
  private String orgAddr;

  /** nullable persistent field */
  private BigDecimal accBlnce = new BigDecimal(0.00);

  /** persistent field */
  private BigDecimal monthSalary = new BigDecimal(0.00);

  /** nullable persistent field */
  private BigDecimal monthPay = new BigDecimal(0.00);

  private String pay_oldyear;

  /** nullable persistent field */
  private String empSt;

  /** 枚举转换合同状态 */
  private String empSt_;

  /** nullable persistent field */
  private String bgnDate;

  /** nullable persistent field */
  private String endDate;

  /** nullable persistent field */
  private String contactAName;

  /** nullable persistent field */
  private String relationA;

  /** nullable persistent field */
  private String contactAOrgName;

  /** nullable persistent field */
  private String contactAOrgTel;

  /** nullable persistent field */
  private String contactAHouseTel;

  /** nullable persistent field */
  private String contactAMobile;

  /** nullable persistent field */
  private String contactBName;

  /** nullable persistent field */
  private String relationB;

  /** nullable persistent field */
  private String contactBOrgName;

  /** nullable persistent field */
  private String contactBOrgTel;

  /** nullable persistent field */
  private String contactBHouseTel;

  /** nullable persistent field */
  private String contactBMobile;

  /** nullable persistent field */
  private BigDecimal privilegeBorrowerId;

  /** nullable persistent field */
  private String operator;

  private String chkPerson;

  private String vipChkPerson;

  /** nullable persistent field */
  private Date opTime;

  /** nullable persistent field */
  private String reserveaA;

  /** nullable persistent field */
  private String reserveaB;

  /** nullable persistent field */
  private String reserveaC;

  private String photoUrl = "";

  /** nullable persistent field */
  private String contactCName;

  /** nullable persistent field */
  private String relationC;

  /** nullable persistent field */
  private String contactCOrgName;

  /** nullable persistent field */
  private String contactCOrgTel;

  /** nullable persistent field */
  private String contactCHouseTel;

  /** nullable persistent field */
  private String contactCMobile;

  private String remark = "";

  // 其他欠款(0:否,1:是)
  private String otherArrearage;

  private String otherArrearage_;

  private String isPrintApply;

  private String isPrintIou;

  private String redate;

  private String loanType = "";

  private String fundCity = "";

  private String loanvipcheckDate = "";

  private String lastcheckDate = "";

  public String getLoanvipcheckDate() {
    return loanvipcheckDate;
  }

  public void setLoanvipcheckDate(String loanvipcheckDate) {
    this.loanvipcheckDate = loanvipcheckDate;
  }

  public String getRedate() {
    return redate;
  }

  public void setRedate(String redate) {
    this.redate = redate;
  }

  public String getIsPrintApply() {
    return isPrintApply;
  }

  public void setIsPrintApply(String isPrintApply) {
    this.isPrintApply = isPrintApply;
  }

  /** full constructor */
  public Borrower(String contractId, String office, BigDecimal empId,
      String borrowerName, String sex, String cardKind, String cardNum,
      String birthday, BigDecimal age, String business, String title,
      String nation, String nativePlace, String marriageSt, String degree,
      String homeAddr, String homeMail, String houseTel, String homeMobile,
      BigDecimal orgId, String orgName, String orgTel, String orgMail,
      String orgAddr, BigDecimal accBlnce, BigDecimal monthSalary,
      BigDecimal monthPay, String empSt, String bgnDate, String endDate,
      String contactAName, String relationA, String contactAOrgName,
      String contactAOrgTel, String contactAHouseTel, String contactAMobile,
      String contactBName, String relationB, String contactBOrgName,
      String contactBOrgTel, String contactBHouseTel, String contactBMobile,
      BigDecimal privilegeBorrowerId, String operator, Date opTime,
      String reserveaA, String reserveaB, String reserveaC) {
    this.contractId = contractId;
    this.office = office;
    this.empId = empId;
    this.borrowerName = borrowerName;
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
    this.contactAName = contactAName;
    this.relationA = relationA;
    this.contactAOrgName = contactAOrgName;
    this.contactAOrgTel = contactAOrgTel;
    this.contactAHouseTel = contactAHouseTel;
    this.contactAMobile = contactAMobile;
    this.contactBName = contactBName;
    this.relationB = relationB;
    this.contactBOrgName = contactBOrgName;
    this.contactBOrgTel = contactBOrgTel;
    this.contactBHouseTel = contactBHouseTel;
    this.contactBMobile = contactBMobile;
    this.privilegeBorrowerId = privilegeBorrowerId;
    this.operator = operator;
    this.opTime = opTime;
    this.reserveaA = reserveaA;
    this.reserveaB = reserveaB;
    this.reserveaC = reserveaC;
    this.isPrintApply = "0";
    this.isPrintIou = "0";
  }

  /** default constructor */
  public Borrower() {
    this.otherArrearage = "0";
    this.isPrintApply = "0";
    this.isPrintIou = "0";
  }

  /** minimal constructor */
  public Borrower(String contractId, String office, String borrowerName,
      String sex, String cardKind, String cardNum, String birthday,
      BigDecimal age, BigDecimal monthSalary) {
    this.contractId = contractId;
    this.office = office;
    this.borrowerName = borrowerName;
    this.sex = sex;
    this.cardKind = cardKind;
    this.cardNum = cardNum;
    this.birthday = birthday;
    this.age = age;
    this.monthSalary = monthSalary;
    this.isPrintApply = "0";
    this.isPrintIou = "0";
  }

  public String getPhotoUrl() {
    return photoUrl;
  }

  public void setPhotoUrl(String photoUrl) {
    this.photoUrl = photoUrl;
  }

  public String getContractId() {
    return this.contractId;
  }

  public void setContractId(String contractId) {
    this.contractId = contractId;
  }

  public String getOffice() {
    return this.office;
  }

  public void setOffice(String office) {
    this.office = office;
  }

  public BigDecimal getEmpId() {
    return this.empId;
  }

  public void setEmpId(BigDecimal empId) {
    this.empId = empId;
  }

  public String getBorrowerName() {
    return this.borrowerName;
  }

  public void setBorrowerName(String borrowerName) {
    this.borrowerName = borrowerName;
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

  public String getContactAName() {
    return this.contactAName;
  }

  public void setContactAName(String contactAName) {
    this.contactAName = contactAName;
  }

  public String getRelationA() {
    return this.relationA;
  }

  public void setRelationA(String relationA) {
    this.relationA = relationA;
  }

  public String getContactAOrgName() {
    return this.contactAOrgName;
  }

  public void setContactAOrgName(String contactAOrgName) {
    this.contactAOrgName = contactAOrgName;
  }

  public String getContactAOrgTel() {
    return this.contactAOrgTel;
  }

  public void setContactAOrgTel(String contactAOrgTel) {
    this.contactAOrgTel = contactAOrgTel;
  }

  public String getContactAHouseTel() {
    return this.contactAHouseTel;
  }

  public void setContactAHouseTel(String contactAHouseTel) {
    this.contactAHouseTel = contactAHouseTel;
  }

  public String getContactAMobile() {
    return this.contactAMobile;
  }

  public void setContactAMobile(String contactAMobile) {
    this.contactAMobile = contactAMobile;
  }

  public String getContactBName() {
    return this.contactBName;
  }

  public void setContactBName(String contactBName) {
    this.contactBName = contactBName;
  }

  public String getRelationB() {
    return this.relationB;
  }

  public void setRelationB(String relationB) {
    this.relationB = relationB;
  }

  public String getContactBOrgName() {
    return this.contactBOrgName;
  }

  public void setContactBOrgName(String contactBOrgName) {
    this.contactBOrgName = contactBOrgName;
  }

  public String getContactBOrgTel() {
    return this.contactBOrgTel;
  }

  public void setContactBOrgTel(String contactBOrgTel) {
    this.contactBOrgTel = contactBOrgTel;
  }

  public String getContactBHouseTel() {
    return this.contactBHouseTel;
  }

  public void setContactBHouseTel(String contactBHouseTel) {
    this.contactBHouseTel = contactBHouseTel;
  }

  public String getContactBMobile() {
    return this.contactBMobile;
  }

  public void setContactBMobile(String contactBMobile) {
    this.contactBMobile = contactBMobile;
  }

  public BigDecimal getPrivilegeBorrowerId() {
    return this.privilegeBorrowerId;
  }

  public void setPrivilegeBorrowerId(BigDecimal privilegeBorrowerId) {
    this.privilegeBorrowerId = privilegeBorrowerId;
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

  public String toString() {
    return new ToStringBuilder(this).append("contractId", getContractId())
        .toString();
  }

  public boolean equals(Object other) {
    if (!(other instanceof Borrower))
      return false;
    Borrower castOther = (Borrower) other;
    return new EqualsBuilder().append(this.getContractId(),
        castOther.getContractId()).isEquals();
  }

  public int hashCode() {
    return new HashCodeBuilder().append(getContractId()).toHashCode();
  }

  public String getEmpSt_() {
    return empSt_;
  }

  public void setEmpSt_(String empSt_) {
    this.empSt_ = empSt_;
  }

  public String getContactCHouseTel() {
    return contactCHouseTel;
  }

  public void setContactCHouseTel(String contactCHouseTel) {
    this.contactCHouseTel = contactCHouseTel;
  }

  public String getContactCMobile() {
    return contactCMobile;
  }

  public void setContactCMobile(String contactCMobile) {
    this.contactCMobile = contactCMobile;
  }

  public String getContactCName() {
    return contactCName;
  }

  public void setContactCName(String contactCName) {
    this.contactCName = contactCName;
  }

  public String getContactCOrgName() {
    return contactCOrgName;
  }

  public void setContactCOrgName(String contactCOrgName) {
    this.contactCOrgName = contactCOrgName;
  }

  public String getContactCOrgTel() {
    return contactCOrgTel;
  }

  public void setContactCOrgTel(String contactCOrgTel) {
    this.contactCOrgTel = contactCOrgTel;
  }

  public String getRelationC() {
    return relationC;
  }

  public void setRelationC(String relationC) {
    this.relationC = relationC;
  }

  public String getRemark() {
    return remark;
  }

  public void setRemark(String remark) {
    this.remark = remark;
  }

  public String getPay_oldyear() {
    return pay_oldyear;
  }

  public void setPay_oldyear(String pay_oldyear) {
    this.pay_oldyear = pay_oldyear;
  }

  public String getIsPrintIou() {
    return isPrintIou;
  }

  public void setIsPrintIou(String isPrintIou) {
    this.isPrintIou = isPrintIou;
  }

  public String getOtherArrearage() {
    return otherArrearage;
  }

  public void setOtherArrearage(String otherArrearage) {
    this.otherArrearage = otherArrearage;
  }

  public String getOtherArrearage_() {
    return otherArrearage_;
  }

  public void setOtherArrearage_(String otherArrearage_) {
    this.otherArrearage_ = otherArrearage_;
  }

  public String getChkPerson() {
    return chkPerson;
  }

  public void setChkPerson(String chkPerson) {
    this.chkPerson = chkPerson;
  }

  public String getVipChkPerson() {
    return vipChkPerson;
  }

  public void setVipChkPerson(String vipChkPerson) {
    this.vipChkPerson = vipChkPerson;
  }

  public String getLoanType() {
    return loanType;
  }

  public void setLoanType(String loanType) {
    this.loanType = loanType;
  }

  public String getFundCity() {
    return fundCity;
  }

  public void setFundCity(String fundCity) {
    this.fundCity = fundCity;
  }

  public String getLastcheckDate() {
    return lastcheckDate;
  }

  public void setLastcheckDate(String lastcheckDate) {
    this.lastcheckDate = lastcheckDate;
  }

}
