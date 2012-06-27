package org.xpup.hafmis.sysfinance.accounthandle.credencefillin.dto;

/**
 * Copy Right Information : 帐务处理-凭证录入内容 Goldsoft Project :
 * CredenceFillinTaShowDTO
 * 
 * @Version : v1.0
 * @author : 刘冰
 * @Date : 2007.10.20
 */
public class CredenceFillinTaShowDTO {
  /** 账套流水号 */
  private String bookId = "";

  /** 所属办事处 */
  private String office = "";

  /** 修改时使用的，原所属办事处 */
  private String oldOffice = "";

  /** 凭证字 */
  private String credenceCharacter = "";

  /** 凭证号 */
  private String credenceNum = "";

  /** 附单据=原始凭证 */
  private String oldCredenceNum = "";

  /** 修改时使用的原凭证号 */
  private String modify_oldCredenceNum = "";

  /** 日期=FN201中ID号最大的记账日期 */
  private String chargeUpDate = "";

  /** 在修改时，用来记录修改前的日期 */
  private String chargeOldUpDate = "";

  /** 摘要 */
  private String summay = "";

  /** 自由摘要 */
  private String freeSummay = "";

  /** 科目代码 */
  private String subjectCode = "";

  /** 借方金额 */
  private String debit = "0";

  /** 贷方金额 */
  private String credit = "0";

  /** 结算方式 */
  private String settType = "";

  /** 结算号 */
  private String settNum = "";

  /** 结算日期 */
  private String settDate = "";

  /** 审核=复核人 */
  private String checkpsn = "";

  /** 过账 */
  private String clearpsn = "";

  /** 制单 */
  private String makebill = "";

  /** 出纳 */
  private String accountpsn = "";

  /** 主管会计 */
  private String accountCharge = "";

  /** 凭证状态 */
  private String credenceSt = "";

  /** 损益结转状态 */
  private String incDecSt = "";

  /** 现金账结转状态 */
  private String cashAccSt = "";

  /** 银行帐结转状态 */
  private String bankAccSt = "";

  /** 备选A */
  private String reserveA = "";

  /** 备选B */
  private String reserveB = "";

  /** 备选C */
  private String reserveC = "";

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

  public String getChargeUpDate() {
    return chargeUpDate;
  }

  public void setChargeUpDate(String chargeUpDate) {
    this.chargeUpDate = chargeUpDate;
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

  public String getCredenceNum() {
    return credenceNum;
  }

  public void setCredenceNum(String credenceNum) {
    this.credenceNum = credenceNum;
  }

  public String getFreeSummay() {
    return freeSummay;
  }

  public void setFreeSummay(String freeSummay) {
    this.freeSummay = freeSummay;
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

  public String getCredit() {
    return credit;
  }

  public String getDebit() {
    return debit;
  }

  public void setCredit(String credit) {
    this.credit = credit;
  }

  public void setDebit(String debit) {
    this.debit = debit;
  }

  public String getBookId() {
    return bookId;
  }

  public void setBookId(String bookId) {
    this.bookId = bookId;
  }

  public String getBankAccSt() {
    return bankAccSt;
  }

  public void setBankAccSt(String bankAccSt) {
    this.bankAccSt = bankAccSt;
  }

  public String getCashAccSt() {
    return cashAccSt;
  }

  public void setCashAccSt(String cashAccSt) {
    this.cashAccSt = cashAccSt;
  }

  public String getCredenceSt() {
    return credenceSt;
  }

  public void setCredenceSt(String credenceSt) {
    this.credenceSt = credenceSt;
  }

  public String getIncDecSt() {
    return incDecSt;
  }

  public void setIncDecSt(String incDecSt) {
    this.incDecSt = incDecSt;
  }

  public String getChargeOldUpDate() {
    return chargeOldUpDate;
  }

  public void setChargeOldUpDate(String chargeOldUpDate) {
    this.chargeOldUpDate = chargeOldUpDate;
  }

  public String getModify_oldCredenceNum() {
    return modify_oldCredenceNum;
  }

  public void setModify_oldCredenceNum(String modify_oldCredenceNum) {
    this.modify_oldCredenceNum = modify_oldCredenceNum;
  }

  public String getOldOffice() {
    return oldOffice;
  }

  public void setOldOffice(String oldOffice) {
    this.oldOffice = oldOffice;
  }

  public String getReserveC() {
    return reserveC;
  }

  public void setReserveC(String reserveC) {
    this.reserveC = reserveC;
  }

  public String getReserveA() {
    return reserveA;
  }

  public void setReserveA(String reserveA) {
    this.reserveA = reserveA;
  }

  public String getReserveB() {
    return reserveB;
  }

  public void setReserveB(String reserveB) {
    this.reserveB = reserveB;
  }

}
