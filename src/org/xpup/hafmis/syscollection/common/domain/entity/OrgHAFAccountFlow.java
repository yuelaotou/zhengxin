package org.xpup.hafmis.syscollection.common.domain.entity;

import java.math.BigDecimal;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.xpup.hafmis.syscommon.domain.entity.DomainObject;

/** @author Hibernate CodeGenerator */
public abstract class OrgHAFAccountFlow extends DomainObject {

  /** persistent field */
  private Org org = new Org();

  /** nullable persistent field */
  private String docNum = "";

  /** nullable persistent field */
  private String settDate = "";

  /** persistent field */
  private BigDecimal debit = new BigDecimal(0.00);

  /** persistent field */
  private BigDecimal credit = new BigDecimal(0.00);

  /** nullable persistent field */
  private BigDecimal interest = new BigDecimal(0.00);

  /** nullable persistent field */
  private String summary = "";

  /** nullable persistent field */
  private BigDecimal bizId = new BigDecimal(0.00);

  /** 发生金额 */
  private String dcitsum;

  /**
   * 尾表人数
   */
  private Integer personTotal = new Integer(0);

  /** persistent field */
  private BigDecimal bizStatus = new BigDecimal(0.00);

  private String status;// 显示状态

  private String bis_type;// 显示类型

  private String biz_Type; // 显示所有类型在hbm里有配置

  private BigDecimal debit_wl = new BigDecimal(0.00);

  private BigDecimal credit_wl = new BigDecimal(0.00);

  private String setdirection = "";// 发生方向

  private String temp_officename = "";

  private String bizStatus_ = "";

  private String bizType_;

  /** nullable persistent field */
  private String noteNum = "";

  /** persistent field */
  private BigDecimal isFinance = new BigDecimal(0.00);

  /** nullable persistent field */
  private String reserveaA = "";

  /** nullable persistent field */
  private String reserveaB = "";

  /** nullable persistent field */
  private String reserveaC = "";

  /** nullable persistent field */
  private BigDecimal total = new BigDecimal(0.00);

  private BigDecimal interestTotal = new BigDecimal(0.00);

  private BigDecimal moneyTotal = new BigDecimal(0.00);

  private BigDecimal orgBalance = new BigDecimal(0.00);

  private BigDecimal orgBalancea = new BigDecimal(0.00);

  private String MoneyTotal_temp = "";

  private int count = 0;

  private BigDecimal lastMonthCollection = new BigDecimal(0.00);// 上月归集

  private BigDecimal monthPay = new BigDecimal(0.00);// 正常汇缴

  private BigDecimal orgAddPay = new BigDecimal(0.00);// 单位补缴

  private BigDecimal orgoverPay = new BigDecimal(0.00);// 单位挂账

  private BigDecimal peronaddPay = new BigDecimal(0.00);// 个人补缴

  private BigDecimal chgPay = new BigDecimal(0.00);// 调缴存

  private BigDecimal thisMonthCollection = new BigDecimal(0.00);// 本月归集

  private String rate = "";// 比率

  private String Biz_type;

  private String specailType = "";// 1、财政代扣，2、公积金还贷

  private String officeCode = "";// 办事处代码

  private String moneyBank = "";// 存款银行代码

  private BigDecimal isClearAccount = new BigDecimal(0.00);// 扎账标识

  private String registrations = "";// 登记人

  private String checkPerson = "";// 复核人

  private String clearPerson = "";// 记账人

  private String reserveaA_ = "";

  private String checkPersonStr = "";// 复核人

  private String clearPersonStr = "";// 记账人

  public BigDecimal getIsClearAccount() {
    return isClearAccount;
  }

  public void setIsClearAccount(BigDecimal isClearAccount) {
    this.isClearAccount = isClearAccount;
  }

  public String getSpecailType() {
    return specailType;
  }

  public void setSpecailType(String specailType) {
    this.specailType = specailType;
  }

  public int getCount() {
    return count;
  }

  public void setCount(int count) {
    this.count = count;
  }

  public BigDecimal getTotal() {
    return total;
  }

  public void setTotal(BigDecimal total) {
    this.total = total;
  }

  /** full constructor */

  public OrgHAFAccountFlow(Org org, String docNum, String settDate,
      BigDecimal debit, BigDecimal credit, BigDecimal interest, String summary,
      BigDecimal bizId, BigDecimal bizStatus, String noteNum,
      BigDecimal isFinance, String reserveaA, String reserveaB,
      String reserveaC, Integer personTotal, String officeCode,
      String moneyBank, BigDecimal isClearAccount) {

    this.org = org;
    this.docNum = docNum;
    this.settDate = settDate;
    this.debit = debit;
    this.credit = credit;
    this.interest = interest;
    this.summary = summary;
    this.bizId = bizId;
    this.bizStatus = bizStatus;
    this.noteNum = noteNum;
    this.isFinance = isFinance;
    this.reserveaA = reserveaA;
    this.reserveaB = reserveaB;
    this.reserveaC = reserveaC;
    this.personTotal = personTotal;
    this.officeCode = officeCode;
    this.moneyBank = moneyBank;
    this.isClearAccount = isClearAccount;
  }

  /** default constructor */
  public OrgHAFAccountFlow() {
  }

  /** minimal constructor */
  public OrgHAFAccountFlow(Org org, BigDecimal debit, BigDecimal credit,
      BigDecimal bizStatus, BigDecimal isFinance, Integer personTotal) {
    this.org = org;
    this.debit = debit;
    this.credit = credit;
    this.bizStatus = bizStatus;
    this.isFinance = isFinance;
    this.personTotal = personTotal;
  }

  public Org getOrg() {
    return this.org;
  }

  public void setOrg(Org org) {
    this.org = org;
  }

  public String getDocNum() {
    return this.docNum;
  }

  public void setDocNum(String docNum) {
    this.docNum = docNum;
  }

  public String getSettDate() {
    return this.settDate;
  }

  public void setSettDate(String settDate) {
    this.settDate = settDate;
  }

  public BigDecimal getDebit() {
    return this.debit;
  }

  public void setDebit(BigDecimal debit) {
    this.debit = debit;
  }

  public BigDecimal getCredit() {
    return this.credit;
  }

  public void setCredit(BigDecimal credit) {
    this.credit = credit;
  }

  public BigDecimal getInterest() {
    return this.interest;
  }

  public void setInterest(BigDecimal interest) {
    this.interest = interest;
  }

  public String getSummary() {
    return this.summary;
  }

  public void setSummary(String summary) {
    this.summary = summary;
  }

  public BigDecimal getBizId() {
    return this.bizId;
  }

  public void setBizId(BigDecimal bizId) {
    this.bizId = bizId;
  }

  public BigDecimal getBizStatus() {
    return this.bizStatus;
  }

  public void setBizStatus(BigDecimal bizStatus) {
    this.bizStatus = bizStatus;
  }

  public String getNoteNum() {
    return this.noteNum;
  }

  public void setNoteNum(String noteNum) {
    this.noteNum = noteNum;
  }

  public BigDecimal getIsFinance() {
    return this.isFinance;
  }

  public void setIsFinance(BigDecimal isFinance) {
    this.isFinance = isFinance;
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
    return new ToStringBuilder(this).append("id", getId()).toString();
  }

  public boolean equals(Object other) {
    if (!(other instanceof OrgHAFAccountFlow))
      return false;
    OrgHAFAccountFlow castOther = (OrgHAFAccountFlow) other;
    return new EqualsBuilder().append(this.getId(), castOther.getId())
        .isEquals();
  }

  public int hashCode() {
    return new HashCodeBuilder().append(getId()).toHashCode();
  }

  public abstract String getBizType();

  public String getBiz_type() {
    return Biz_type;
  }

  public void setBiz_type(String biz_type) {
    Biz_type = biz_type;
  }

  public BigDecimal getInterestTotal() {
    return interestTotal;
  }

  public void setInterestTotal(BigDecimal interestTotal) {
    this.interestTotal = interestTotal;
  }

  public BigDecimal getMoneyTotal() {
    return moneyTotal;
  }

  public void setMoneyTotal(BigDecimal moneyTotal) {
    this.moneyTotal = moneyTotal;
  }

  public Integer getPersonTotal() {
    return personTotal;
  }

  public void setPersonTotal(Integer personTotal) {
    this.personTotal = personTotal;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public String getBis_type() {
    return bis_type;
  }

  public void setBis_type(String bis_type) {
    this.bis_type = bis_type;
  }

  public String getBiz_Type() {
    return biz_Type;
  }

  public void setBiz_Type(String biz_Type) {
    this.biz_Type = biz_Type;
  }

  public String getBizStatus_() {
    return bizStatus_;
  }

  public void setBizStatus_(String bizStatus_) {
    this.bizStatus_ = bizStatus_;
  }

  public String getBizType_() {
    return bizType_;
  }

  public void setBizType_(String bizType_) {
    this.bizType_ = bizType_;
  }

  public String getDcitsum() {
    return dcitsum;
  }

  public void setDcitsum(String dcitsum) {
    this.dcitsum = dcitsum;
  }

  public BigDecimal getLastMonthCollection() {
    return lastMonthCollection;
  }

  public void setLastMonthCollection(BigDecimal lastMonthCollection) {
    this.lastMonthCollection = lastMonthCollection;
  }

  public BigDecimal getChgPay() {
    return chgPay;
  }

  public void setChgPay(BigDecimal chgPay) {
    this.chgPay = chgPay;
  }

  public BigDecimal getMonthPay() {
    return monthPay;
  }

  public void setMonthPay(BigDecimal monthPay) {
    this.monthPay = monthPay;
  }

  public BigDecimal getOrgAddPay() {
    return orgAddPay;
  }

  public void setOrgAddPay(BigDecimal orgAddPay) {
    this.orgAddPay = orgAddPay;
  }

  public BigDecimal getOrgoverPay() {
    return orgoverPay;
  }

  public void setOrgoverPay(BigDecimal orgoverPay) {
    this.orgoverPay = orgoverPay;
  }

  public BigDecimal getPeronaddPay() {
    return peronaddPay;
  }

  public void setPeronaddPay(BigDecimal peronaddPay) {
    this.peronaddPay = peronaddPay;
  }

  public BigDecimal getThisMonthCollection() {
    return thisMonthCollection;
  }

  public void setThisMonthCollection(BigDecimal thisMonthCollection) {
    this.thisMonthCollection = thisMonthCollection;
  }

  public String getRate() {
    return rate;
  }

  public void setRate(String rate) {
    this.rate = rate;
  }

  public BigDecimal getCredit_wl() {
    return credit_wl;
  }

  public void setCredit_wl(BigDecimal credit_wl) {
    this.credit_wl = credit_wl;
  }

  public BigDecimal getDebit_wl() {
    return debit_wl;
  }

  public void setDebit_wl(BigDecimal debit_wl) {
    this.debit_wl = debit_wl;
  }

  public String getSetdirection() {
    return setdirection;
  }

  public void setSetdirection(String setdirection) {
    this.setdirection = setdirection;
  }

  public String getTemp_officename() {
    return temp_officename;
  }

  public void setTemp_officename(String temp_officename) {
    this.temp_officename = temp_officename;
  }

  public String getMoneyBank() {
    return moneyBank;
  }

  public void setMoneyBank(String moneyBank) {
    this.moneyBank = moneyBank;
  }

  public String getOfficeCode() {
    return officeCode;
  }

  public void setOfficeCode(String officeCode) {
    this.officeCode = officeCode;
  }

  public String getCheckPerson() {
    return checkPerson;
  }

  public void setCheckPerson(String checkPerson) {
    this.checkPerson = checkPerson;
  }

  public String getClearPerson() {
    return clearPerson;
  }

  public void setClearPerson(String clearPerson) {
    this.clearPerson = clearPerson;
  }

  public String getRegistrations() {
    return registrations;
  }

  public void setRegistrations(String registrations) {
    this.registrations = registrations;
  }

  public String getMoneyTotal_temp() {
    return MoneyTotal_temp;
  }

  public void setMoneyTotal_temp(String moneyTotal_temp) {
    MoneyTotal_temp = moneyTotal_temp;
  }

  public String getCheckPersonStr() {
    return checkPersonStr;
  }

  public void setCheckPersonStr(String checkPersonStr) {
    this.checkPersonStr = checkPersonStr;
  }

  public String getClearPersonStr() {
    return clearPersonStr;
  }

  public void setClearPersonStr(String clearPersonStr) {
    this.clearPersonStr = clearPersonStr;
  }

  public String getReserveaA_() {
    return reserveaA_;
  }

  public void setReserveaA_(String reserveaA_) {
    this.reserveaA_ = reserveaA_;
  }

  public BigDecimal getOrgBalance() {
    return orgBalance;
  }

  public void setOrgBalance(BigDecimal orgBalance) {
    this.orgBalance = orgBalance;
  }

  public BigDecimal getOrgBalancea() {
    return orgBalancea;
  }

  public void setOrgBalancea(BigDecimal orgBalancea) {
    this.orgBalancea = orgBalancea;
  }

}
