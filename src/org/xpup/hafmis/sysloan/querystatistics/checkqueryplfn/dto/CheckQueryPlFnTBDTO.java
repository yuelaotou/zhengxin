package org.xpup.hafmis.sysloan.querystatistics.checkqueryplfn.dto;

public class CheckQueryPlFnTBDTO {
  private String contractid = "";// 合同编号

  private String loankouacc = "";// 贷款账号

  private String credencenum = "";// 个贷凭证号
  
  private String fncredencenum = "";// 财务凭证号

  private String notenum = "";// 结算号

  private String empid = "";// 职工编号

  private String empname = "";// 借款人名称

  private String biztype = "";// 摘要

  private String temp_biztype = "";

  private String bizdate = "";// 个贷结算日期

  private String plbizst = "";// 个贷状态
  
  private String temp_plbizst = "";

  private String settledate = "";// 财务结算日期

  private String credenceSt = "";// 财务状态
  
  private String temp_credenceSt = "";

  private String credit = "0";// 贷方金额

  private String debit = "0";// 借方金额

  private String balance = "0";// 余额
  
  private String type="";

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public String getBalance() {
    return balance;
  }

  public void setBalance(String balance) {
    this.balance = balance;
  }

  public String getBizdate() {
    return bizdate;
  }

  public void setBizdate(String bizdate) {
    this.bizdate = bizdate;
  }

  public String getBiztype() {
    return biztype;
  }

  public void setBiztype(String biztype) {
    this.biztype = biztype;
  }

  public String getContractid() {
    return contractid;
  }

  public void setContractid(String contractid) {
    this.contractid = contractid;
  }

  public String getCredencenum() {
    return credencenum;
  }

  public void setCredencenum(String credencenum) {
    this.credencenum = credencenum;
  }

  public String getCredenceSt() {
    return credenceSt;
  }

  public void setCredenceSt(String credenceSt) {
    this.credenceSt = credenceSt;
  }

  public String getCredit() {
    return credit;
  }

  public void setCredit(String credit) {
    this.credit = credit;
  }

  public String getDebit() {
    return debit;
  }

  public void setDebit(String debit) {
    this.debit = debit;
  }

  public String getEmpid() {
    return empid;
  }

  public void setEmpid(String empid) {
    this.empid = empid;
  }

  public String getEmpname() {
    return empname;
  }

  public void setEmpname(String empname) {
    this.empname = empname;
  }

  public String getLoankouacc() {
    return loankouacc;
  }

  public void setLoankouacc(String loankouacc) {
    this.loankouacc = loankouacc;
  }

  public String getNotenum() {
    return notenum;
  }

  public void setNotenum(String notenum) {
    this.notenum = notenum;
  }

  public String getPlbizst() {
    return plbizst;
  }

  public void setPlbizst(String plbizst) {
    this.plbizst = plbizst;
  }

  public String getSettledate() {
    return settledate;
  }

  public void setSettledate(String settledate) {
    this.settledate = settledate;
  }

  public String getTemp_biztype() {
    return temp_biztype;
  }

  public void setTemp_biztype(String temp_biztype) {
    this.temp_biztype = temp_biztype;
  }

  public String getTemp_credenceSt() {
    return temp_credenceSt;
  }

  public void setTemp_credenceSt(String temp_credenceSt) {
    this.temp_credenceSt = temp_credenceSt;
  }

  public String getTemp_plbizst() {
    return temp_plbizst;
  }

  public void setTemp_plbizst(String temp_plbizst) {
    this.temp_plbizst = temp_plbizst;
  }

  public String getFncredencenum() {
    return fncredencenum;
  }

  public void setFncredencenum(String fncredencenum) {
    this.fncredencenum = fncredencenum;
  }
}
