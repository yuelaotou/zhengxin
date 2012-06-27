package org.xpup.hafmis.sysfinance.common.biz.credencepop.dto;

/**
 * Copy Right Information : 封装了弹出框中办事处，凭证号等信息的DTO Goldsoft Project :
 * CredencePopInfoDTO
 * 
 * @Version : v1.0
 * @author : 付云峰
 * @Date : 2007.11.3
 */
public class CredencePopInfoDTO {

  /** 办事处 */
  private String office = "";

  /** 凭证字 */
  private String credenceCharacter = "";

  /** 凭证号 */
  private String credenceNum = "";

  /** 附单据 */
  private String oldCredenceNum = "";

  /** 日期 */
  private String credenceDate = "";

  /** 结算方式 */
  private String settType = "";

  /** 结算号 */
  private String settNum = "";

  /** 结算日期 */
  private String settDate = "";

  /** 审核 */
  private String checkpsn = "";

  /** 过账 */
  private String clearpsn = "";

  /** 制单人 */
  private String makebill = "";

  /** 出纳 */
  private String accountpsn = "";

  /** 主管会计 */
  private String accountCharge = "";
  
  /** 凭证字说明 */
  private String paramExplain = "";
  
  private String credenceChaNum = "";
  
  private String reserveA = "";
  
  private String reserveB = "";
  
  private String reserveC = "";

  public String getReserveC() {
    return reserveC;
  }

  public void setReserveC(String reserveC) {
    this.reserveC = reserveC;
  }

  public String getCredenceChaNum() {
    return credenceChaNum;
  }

  public void setCredenceChaNum(String credenceChaNum) {
    this.credenceChaNum = credenceChaNum;
  }

  public String getParamExplain() {
    return paramExplain;
  }

  public void setParamExplain(String paramExplain) {
    this.paramExplain = paramExplain;
  }

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

  public String getCredenceNum() {
    return credenceNum;
  }

  public void setCredenceNum(String credenceNum) {
    this.credenceNum = credenceNum;
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
