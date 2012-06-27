package org.xpup.hafmis.sysloan.querystatistics.querystatistics.loandeskaccquery.dto;

public class LoandeskaccqueryTcDTO {
  private String payloanmood;
  private String loanleftmoney="0";
  private String owecorpus="0";
  private String oweinterest="0";
  private String punishinterest="0";
  private String loanBankId = "";
  public String getLoanBankId() {
    return loanBankId;
  }
  public void setLoanBankId(String loanBankId) {
    this.loanBankId = loanBankId;
  }
  public String getLoanleftmoney() {
    return loanleftmoney;
  }
  public void setLoanleftmoney(String loanleftmoney) {
    this.loanleftmoney = loanleftmoney;
  }
  public String getPayloanmood() {
    return payloanmood;
  }
  public void setPayloanmood(String payloanmood) {
    this.payloanmood = payloanmood;
  }
  public String getOwecorpus() {
    return owecorpus;
  }
  public void setOwecorpus(String owecorpus) {
    this.owecorpus = owecorpus;
  }
  public String getOweinterest() {
    return oweinterest;
  }
  public void setOweinterest(String oweinterest) {
    this.oweinterest = oweinterest;
  }
  public String getPunishinterest() {
    return punishinterest;
  }
  public void setPunishinterest(String punishinterest) {
    this.punishinterest = punishinterest;
  }
  

}
