package org.xpup.hafmis.sysloan.querystatistics.checkqueryplfn.dto;

public class CheckQueryPlFnDTO {
  // 借款人合同信息
  private String contractid;// 合同编号

  private String loankouacc;// 贷款账号

  private String borrowername;// 借款人姓名

  private String cardnum;// 证件号码

  private String loanbank;// 放款银行

  private String loanbankname = "";// 放款银行名称

  private String loanmoney;// 贷款金额

  private String loanlimit;// 贷款期限

  private String loanmode;// 还款方式
  
  private String temp_loanmode="";

  private String overplusloanmoney = "";// 贷款余额

  private String nobackmoney = "";// 呆账未回收金额

  private String oveaerloanrepay = "";// 挂账余额

  private String ballbalance = "";// 保证金余额

  private String srealcorpus = "";// 总还本金

  private String srealinterest = "";// 总还利息

  private String srealpunishinterest = "";// 总还罚息利息

  private String owercorpus = "0";// 欠还本金

  private String oweinterest = "0";// 欠还利息

  private String owepunishinterest = "0";// 欠还罚息利息
  
  private String payday="";//还款日

  public String getPayday() {
    return payday;
  }

  public void setPayday(String payday) {
    this.payday = payday;
  }

  public String getBallbalance() {
    return ballbalance;
  }

  public void setBallbalance(String ballbalance) {
    this.ballbalance = ballbalance;
  }

  public String getBorrowername() {
    return borrowername;
  }

  public void setBorrowername(String borrowername) {
    this.borrowername = borrowername;
  }

  public String getCardnum() {
    return cardnum;
  }

  public void setCardnum(String cardnum) {
    this.cardnum = cardnum;
  }

  public String getContractid() {
    return contractid;
  }

  public void setContractid(String contractid) {
    this.contractid = contractid;
  }

  public String getLoanbank() {
    return loanbank;
  }

  public void setLoanbank(String loanbank) {
    this.loanbank = loanbank;
  }

  public String getLoankouacc() {
    return loankouacc;
  }

  public void setLoankouacc(String loankouacc) {
    this.loankouacc = loankouacc;
  }

  public String getLoanlimit() {
    return loanlimit;
  }

  public void setLoanlimit(String loanlimit) {
    this.loanlimit = loanlimit;
  }

  public String getLoanmode() {
    return loanmode;
  }

  public void setLoanmode(String loanmode) {
    this.loanmode = loanmode;
  }

  public String getLoanmoney() {
    return loanmoney;
  }

  public void setLoanmoney(String loanmoney) {
    this.loanmoney = loanmoney;
  }

  public String getNobackmoney() {
    return nobackmoney;
  }

  public void setNobackmoney(String nobackmoney) {
    this.nobackmoney = nobackmoney;
  }

  public String getOveaerloanrepay() {
    return oveaerloanrepay;
  }

  public void setOveaerloanrepay(String oveaerloanrepay) {
    this.oveaerloanrepay = oveaerloanrepay;
  }

  public String getOverplusloanmoney() {
    return overplusloanmoney;
  }

  public void setOverplusloanmoney(String overplusloanmoney) {
    this.overplusloanmoney = overplusloanmoney;
  }

  public String getOweinterest() {
    return oweinterest;
  }

  public void setOweinterest(String oweinterest) {
    this.oweinterest = oweinterest;
  }

  public String getOwepunishinterest() {
    return owepunishinterest;
  }

  public void setOwepunishinterest(String owepunishinterest) {
    this.owepunishinterest = owepunishinterest;
  }

  public String getOwercorpus() {
    return owercorpus;
  }

  public void setOwercorpus(String owercorpus) {
    this.owercorpus = owercorpus;
  }

  public String getSrealcorpus() {
    return srealcorpus;
  }

  public void setSrealcorpus(String srealcorpus) {
    this.srealcorpus = srealcorpus;
  }

  public String getSrealinterest() {
    return srealinterest;
  }

  public void setSrealinterest(String srealinterest) {
    this.srealinterest = srealinterest;
  }

  public String getSrealpunishinterest() {
    return srealpunishinterest;
  }

  public void setSrealpunishinterest(String srealpunishinterest) {
    this.srealpunishinterest = srealpunishinterest;
  }

  public String getLoanbankname() {
    return loanbankname;
  }

  public void setLoanbankname(String loanbankname) {
    this.loanbankname = loanbankname;
  }

  public String getTemp_loanmode() {
    return temp_loanmode;
  }

  public void setTemp_loanmode(String temp_loanmode) {
    this.temp_loanmode = temp_loanmode;
  }

}
