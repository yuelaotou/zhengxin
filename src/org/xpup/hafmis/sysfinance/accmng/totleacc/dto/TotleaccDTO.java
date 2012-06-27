package org.xpup.hafmis.sysfinance.accmng.totleacc.dto;

import java.math.BigDecimal;

public class TotleaccDTO {
  
  private String subjectname="";
  private String credcharnum="";//凭证字号
  private String othersubjectname="";//对方科目
  private String dirtection="";//方向
  private BigDecimal money=new BigDecimal(0.00);//余额
  
  
  /** identifier field */
  private String credenceId;

  /** persistent field */
  private String bookId = "";

  /** nullable persistent field */
  private String credenceNum = "";

  /** nullable persistent field */
  private String subjectCode = "";

  /** nullable persistent field */
  private String summay = "";

  /** nullable persistent field */
  private String freeSummay = "";

  /** nullable persistent field */
  private String oldCredenceNum = "";

  /** nullable persistent field */
  private String settNum = "";

  /** nullable persistent field */
  private BigDecimal debit =new BigDecimal(0.00);

  /** nullable persistent field */
  private BigDecimal credit =new BigDecimal(0.00);

  /** nullable persistent field */
  private String credenceDate = "";

  /** nullable persistent field */
  private String credenceCharacter = "";

  /** nullable persistent field */
  private String makebill = "";

  /** nullable persistent field */
  private String checkpsn = "";

  /** nullable persistent field */
  private String accountpsn = "";

  /** nullable persistent field */
  private String clearpsn = "";

  /** nullable persistent field */
  private String accountCharge = "";

  /** nullable persistent field */
  private String office = "";

  /** nullable persistent field */
  private String credenceSt = "";

  /** nullable persistent field */
  private String incDecSt = "";

  /** nullable persistent field */
  private String cashAccSt = "";

  /** nullable persistent field */
  private String bankAccSt = "";

  /** nullable persistent field */
  private String settType = "";

  /** nullable persistent field */
  private String settDate = "";

  public String getAccountCharge() {
    return accountCharge;
  }

  public void setAccountCharge(String accountCharge) {
    this.accountCharge = accountCharge;
  }

  public String getAccountpsn() {
    return accountpsn;
  }

  public void setAccountpsn(String accountpsn) {
    this.accountpsn = accountpsn;
  }

  public String getBankAccSt() {
    return bankAccSt;
  }

  public void setBankAccSt(String bankAccSt) {
    this.bankAccSt = bankAccSt;
  }

  public String getBookId() {
    return bookId;
  }

  public void setBookId(String bookId) {
    this.bookId = bookId;
  }

  public String getCashAccSt() {
    return cashAccSt;
  }

  public void setCashAccSt(String cashAccSt) {
    this.cashAccSt = cashAccSt;
  }

  public String getCheckpsn() {
    return checkpsn;
  }

  public void setCheckpsn(String checkpsn) {
    this.checkpsn = checkpsn;
  }

  public String getClearpsn() {
    return clearpsn;
  }

  public void setClearpsn(String clearpsn) {
    this.clearpsn = clearpsn;
  }

  public String getCredenceCharacter() {
    return credenceCharacter;
  }

  public void setCredenceCharacter(String credenceCharacter) {
    this.credenceCharacter = credenceCharacter;
  }

  public String getCredenceDate() {
    return credenceDate;
  }

  public void setCredenceDate(String credenceDate) {
    this.credenceDate = credenceDate;
  }

  public String getCredenceId() {
    return credenceId;
  }

  public void setCredenceId(String credenceId) {
    this.credenceId = credenceId;
  }

  public String getCredenceNum() {
    return credenceNum;
  }

  public void setCredenceNum(String credenceNum) {
    this.credenceNum = credenceNum;
  }

  public String getCredenceSt() {
    return credenceSt;
  }

  public void setCredenceSt(String credenceSt) {
    this.credenceSt = credenceSt;
  }

  public BigDecimal getCredit() {
    return credit;
  }

  public void setCredit(BigDecimal credit) {
    this.credit = credit;
  }

  public BigDecimal getDebit() {
    return debit;
  }

  public void setDebit(BigDecimal debit) {
    this.debit = debit;
  }

  public BigDecimal getMoney() {
    return money;
  }

  public void setMoney(BigDecimal money) {
    this.money = money;
  }

  public String getFreeSummay() {
    return freeSummay;
  }

  public void setFreeSummay(String freeSummay) {
    this.freeSummay = freeSummay;
  }

  public String getIncDecSt() {
    return incDecSt;
  }

  public void setIncDecSt(String incDecSt) {
    this.incDecSt = incDecSt;
  }

  public String getMakebill() {
    return makebill;
  }

  public void setMakebill(String makebill) {
    this.makebill = makebill;
  }

  public String getOffice() {
    return office;
  }

  public void setOffice(String office) {
    this.office = office;
  }

  public String getOldCredenceNum() {
    return oldCredenceNum;
  }

  public void setOldCredenceNum(String oldCredenceNum) {
    this.oldCredenceNum = oldCredenceNum;
  }

  public String getSettDate() {
    return settDate;
  }

  public void setSettDate(String settDate) {
    this.settDate = settDate;
  }

  public String getSettNum() {
    return settNum;
  }

  public void setSettNum(String settNum) {
    this.settNum = settNum;
  }

  public String getSettType() {
    return settType;
  }

  public void setSettType(String settType) {
    this.settType = settType;
  }

  public String getSubjectCode() {
    return subjectCode;
  }

  public void setSubjectCode(String subjectCode) {
    this.subjectCode = subjectCode;
  }

  public String getSummay() {
    return summay;
  }

  public void setSummay(String summay) {
    this.summay = summay;
  }

  public String getCredcharnum() {
    return credcharnum;
  }

  public void setCredcharnum(String credcharnum) {
    this.credcharnum = credcharnum;
  }

  public String getDirtection() {
    return dirtection;
  }

  public void setDirtection(String dirtection) {
    this.dirtection = dirtection;
  }

  public String getSubjectname() {
    return subjectname;
  }

  public void setSubjectname(String subjectname) {
    this.subjectname = subjectname;
  }

  public String getOthersubjectname() {
    return othersubjectname;
  }

  public void setOthersubjectname(String othersubjectname) {
    this.othersubjectname = othersubjectname;
  }
}
