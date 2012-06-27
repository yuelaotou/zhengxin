package org.xpup.hafmis.sysloan.common.domain.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/** @author Hibernate CodeGenerator */
public class Assurer implements Serializable {

    /** identifier field */
    private Integer id;

    /** persistent field */
    private String contractId;

    /** nullable persistent field */
    private BigDecimal empId;

    /** persistent field */
    private String empName;

    /** nullable persistent field */
    private String sex;

    /** persistent field */
    private String cardKind;

    /** persistent field */
    private String cardNum;

    /** nullable persistent field */
    private String birthday;

    /** nullable persistent field */
    private BigDecimal salary;

    /** nullable persistent field */
    private BigDecimal monthPay;

    /** nullable persistent field */
    private BigDecimal balance;

    /** nullable persistent field */
    private String empSt;

    /** nullable persistent field */
    private String tel;

    /** nullable persistent field */
    private String mobile;

    /** nullable persistent field */
    private String homeTel;

    /** nullable persistent field */
    private String homeAddr;

    /** nullable persistent field */
    private String homeMail;

    /** nullable persistent field */
    private BigDecimal orgId;

    /** nullable persistent field */
    private String orgName;

    /** nullable persistent field */
    private String orgAddr;

    /** nullable persistent field */
    private String orgMail;

    /** nullable persistent field */
    private String orgTel;

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
    
    private String status;
    private String photoUrl;

    public String getPhotoUrl() {
      return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
      this.photoUrl = photoUrl;
    }
    /** full constructor */
    public Assurer(Integer id, String contractId, BigDecimal empId, String empName, String sex, String cardKind, String cardNum, String birthday, BigDecimal salary, BigDecimal monthPay, BigDecimal balance, String empSt, String tel, String mobile, String homeTel, String homeAddr, String homeMail, BigDecimal orgId, String orgName, String orgAddr, String orgMail, String orgTel, String operator, Date opTime, String reserveaA, String reserveaB, String reserveaC,String status) {
        this.id = id;
        this.contractId = contractId;
        this.empId = empId;
        this.empName = empName;
        this.sex = sex;
        this.cardKind = cardKind;
        this.cardNum = cardNum;
        this.birthday = birthday;
        this.salary = salary;
        this.monthPay = monthPay;
        this.balance = balance;
        this.empSt = empSt;
        this.tel = tel;
        this.mobile = mobile;
        this.homeTel = homeTel;
        this.homeAddr = homeAddr;
        this.homeMail = homeMail;
        this.orgId = orgId;
        this.orgName = orgName;
        this.orgAddr = orgAddr;
        this.orgMail = orgMail;
        this.orgTel = orgTel;
        this.operator = operator;
        this.opTime = opTime;
        this.reserveaA = reserveaA;
        this.reserveaB = reserveaB;
        this.reserveaC = reserveaC;
        this.status = status;
    }

    /** default constructor */
    public Assurer() {
    }

    /** minimal constructor */
    public Assurer(Integer id, String contractId, String empName, String cardKind, String cardNum) {
        this.id = id;
        this.contractId = contractId;
        this.empName = empName;
        this.cardKind = cardKind;
        this.cardNum = cardNum;
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

    public BigDecimal getEmpId() {
        return this.empId;
    }

    public void setEmpId(BigDecimal empId) {
        this.empId = empId;
    }

    public String getEmpName() {
        return this.empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
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

    public BigDecimal getSalary() {
        return this.salary;
    }

    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }

    public BigDecimal getMonthPay() {
        return this.monthPay;
    }

    public void setMonthPay(BigDecimal monthPay) {
        this.monthPay = monthPay;
    }

    public BigDecimal getBalance() {
        return this.balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public String getEmpSt() {
        return this.empSt;
    }

    public void setEmpSt(String empSt) {
        this.empSt = empSt;
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

    public String getHomeTel() {
        return this.homeTel;
    }

    public void setHomeTel(String homeTel) {
        this.homeTel = homeTel;
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

    public String getOrgAddr() {
        return this.orgAddr;
    }

    public void setOrgAddr(String orgAddr) {
        this.orgAddr = orgAddr;
    }

    public String getOrgMail() {
        return this.orgMail;
    }

    public void setOrgMail(String orgMail) {
        this.orgMail = orgMail;
    }

    public String getOrgTel() {
        return this.orgTel;
    }

    public void setOrgTel(String orgTel) {
        this.orgTel = orgTel;
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
        if ( !(other instanceof Assurer) ) return false;
        Assurer castOther = (Assurer) other;
        return new EqualsBuilder()
            .append(this.getId(), castOther.getId())
            .isEquals();
    }

    public int hashCode() {
        return new HashCodeBuilder()
            .append(getId())
            .toHashCode();
    }

    public String getStatus() {
      return status;
    }

    public void setStatus(String status) {
      this.status = status;
    }

}
