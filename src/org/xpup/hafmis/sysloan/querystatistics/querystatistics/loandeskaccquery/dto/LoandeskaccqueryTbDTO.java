package org.xpup.hafmis.sysloan.querystatistics.querystatistics.loandeskaccquery.dto;

import java.math.BigDecimal;

public class LoandeskaccqueryTbDTO {

  private String id = "";// 业务日期

  private String bizdate = "";// 业务日期

  private String yearmonth = "";// 还款年月

  private String docnum = "";// 凭证编号

  private String biztype = "";// 业务类型

  private String accordmoney = "";// 发放金额

  private String overloanmoney = "";// 挂账金额

  private String baddebtmoney = "";// 呆账核销金额

  private String bailmoney = "";// 保证金

  private String shouldcorpus = "";// 应还本金

  private String shouldinterest = "";// 应还利息

  private String shouldpunishinterest = "";// 应还罚息

  private String realcorpus = "";// 实还本金

  private String realinterest = "";// 实还利息

  private String realpunishinterest = "";// 实还罚息

  private String loantype = "";// 还款类型

  private String occurmoney = "";// 发生金额

  private String wrongbiztype = "";// 错账类型

  private String batchNum;

  private BigDecimal sumCorpusInterest = new BigDecimal(0.00);

  public String getWrongbiztype() {
    return wrongbiztype;
  }

  public void setWrongbiztype(String wrongbiztype) {
    this.wrongbiztype = wrongbiztype;
  }

  public String getAccordmoney() {
    return accordmoney;
  }

  public void setAccordmoney(String accordmoney) {
    this.accordmoney = accordmoney;
  }

  public String getBaddebtmoney() {
    return baddebtmoney;
  }

  public void setBaddebtmoney(String baddebtmoney) {
    this.baddebtmoney = baddebtmoney;
  }

  public String getBailmoney() {
    return bailmoney;
  }

  public void setBailmoney(String bailmoney) {
    this.bailmoney = bailmoney;
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

  public String getDocnum() {
    return docnum;
  }

  public void setDocnum(String docnum) {
    this.docnum = docnum;
  }

  public String getLoantype() {
    return loantype;
  }

  public void setLoantype(String loantype) {
    this.loantype = loantype;
  }

  public String getOccurmoney() {
    return occurmoney;
  }

  public void setOccurmoney(String occurmoney) {
    this.occurmoney = occurmoney;
  }

  public String getOverloanmoney() {
    return overloanmoney;
  }

  public void setOverloanmoney(String overloanmoney) {
    this.overloanmoney = overloanmoney;
  }

  public String getRealcorpus() {
    return realcorpus;
  }

  public void setRealcorpus(String realcorpus) {
    this.realcorpus = realcorpus;
  }

  public String getRealinterest() {
    return realinterest;
  }

  public void setRealinterest(String realinterest) {
    this.realinterest = realinterest;
  }

  public String getRealpunishinterest() {
    return realpunishinterest;
  }

  public void setRealpunishinterest(String realpunishinterest) {
    this.realpunishinterest = realpunishinterest;
  }

  public String getShouldcorpus() {
    return shouldcorpus;
  }

  public void setShouldcorpus(String shouldcorpus) {
    this.shouldcorpus = shouldcorpus;
  }

  public String getShouldinterest() {
    return shouldinterest;
  }

  public void setShouldinterest(String shouldinterest) {
    this.shouldinterest = shouldinterest;
  }

  public String getShouldpunishinterest() {
    return shouldpunishinterest;
  }

  public void setShouldpunishinterest(String shouldpunishinterest) {
    this.shouldpunishinterest = shouldpunishinterest;
  }

  public String getYearmonth() {
    return yearmonth;
  }

  public void setYearmonth(String yearmonth) {
    this.yearmonth = yearmonth;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public BigDecimal getSumCorpusInterest() {
    return sumCorpusInterest;
  }

  public void setSumCorpusInterest(BigDecimal sumCorpusInterest) {
    this.sumCorpusInterest = sumCorpusInterest;
  }

  public String getBatchNum() {
    return batchNum;
  }

  public void setBatchNum(String batchNum) {
    this.batchNum = batchNum;
  }

}