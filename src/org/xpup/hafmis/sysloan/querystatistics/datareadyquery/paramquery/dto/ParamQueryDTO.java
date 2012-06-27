package org.xpup.hafmis.sysloan.querystatistics.datareadyquery.paramquery.dto;

import java.math.BigDecimal;

public class ParamQueryDTO {
  private String loanBankId="";//银行
  private String temp_loanBankId="";
  private String kouAccMode="";//1、扣款方式
  private String decideDateMode="";//2、定日方式
  private String uniteDate="";//2、统一日期
  private String corpus="";//3、正常本金
  private String interest="";//3、正常利息
  private String overdueCorpus="";//3、逾期本金
  private String overdueInterest="";//3、逾期利息
  private String punishInterest="";//3、罚息
  private String punishInterestRateMode="";//4、罚息利率方式
  private BigDecimal punishInterestDateRate=new BigDecimal(0.00);//4、按罚息日利率
  private BigDecimal contractDateRate=new BigDecimal(0.00);//4、按合同日利率
  private BigDecimal moneyDateInterest=new BigDecimal(0.00);//4、按额每日利息
  private String permitDateNum="";//5、宽限天数
  private String isRecord="";//5、是否记罚息
  private String commonMonthNum="";//6、正常
  private String attentionMonthNum="";//6、关注
  private String subMonthNum="";//6、次级
  private String shadinessMonthNum="";//6、可疑
  private String lossMonthNum="";//6、损失
  private String loanVipCheck="";//7、审批贷款
  private String endorseLoan="";//7、签定贷款
  private BigDecimal currentRate=new BigDecimal(0.00);//8、活期利率
  private BigDecimal terminalRate=new BigDecimal(0.00);//8、死期利率
  private String isAdopt="";//9、是否采用新利率
  private String type="";//用来控制页面按钮禁用的
  public String getType() {
    return type;
  }
  public void setType(String type) {
    this.type = type;
  }
  public String getAttentionMonthNum() {
    return attentionMonthNum;
  }
  public void setAttentionMonthNum(String attentionMonthNum) {
    this.attentionMonthNum = attentionMonthNum;
  }
  public String getCommonMonthNum() {
    return commonMonthNum;
  }
  public void setCommonMonthNum(String commonMonthNum) {
    this.commonMonthNum = commonMonthNum;
  }
  public BigDecimal getContractDateRate() {
    return contractDateRate;
  }
  public void setContractDateRate(BigDecimal contractDateRate) {
    this.contractDateRate = contractDateRate;
  }
  public String getCorpus() {
    return corpus;
  }
  public void setCorpus(String corpus) {
    this.corpus = corpus;
  }
  public BigDecimal getCurrentRate() {
    return currentRate;
  }
  public void setCurrentRate(BigDecimal currentRate) {
    this.currentRate = currentRate;
  }
  public String getDecideDateMode() {
    return decideDateMode;
  }
  public void setDecideDateMode(String decideDateMode) {
    this.decideDateMode = decideDateMode;
  }
  public String getEndorseLoan() {
    return endorseLoan;
  }
  public void setEndorseLoan(String endorseLoan) {
    this.endorseLoan = endorseLoan;
  }
  public String getInterest() {
    return interest;
  }
  public void setInterest(String interest) {
    this.interest = interest;
  }
  public String getIsAdopt() {
    return isAdopt;
  }
  public void setIsAdopt(String isAdopt) {
    this.isAdopt = isAdopt;
  }
  public String getIsRecord() {
    return isRecord;
  }
  public void setIsRecord(String isRecord) {
    this.isRecord = isRecord;
  }
  public String getKouAccMode() {
    return kouAccMode;
  }
  public void setKouAccMode(String kouAccMode) {
    this.kouAccMode = kouAccMode;
  }
  public String getLoanBankId() {
    return loanBankId;
  }
  public void setLoanBankId(String loanBankId) {
    this.loanBankId = loanBankId;
  }
  public String getLoanVipCheck() {
    return loanVipCheck;
  }
  public void setLoanVipCheck(String loanVipCheck) {
    this.loanVipCheck = loanVipCheck;
  }
  public String getLossMonthNum() {
    return lossMonthNum;
  }
  public void setLossMonthNum(String lossMonthNum) {
    this.lossMonthNum = lossMonthNum;
  }
  public BigDecimal getMoneyDateInterest() {
    return moneyDateInterest;
  }
  public void setMoneyDateInterest(BigDecimal moneyDateInterest) {
    this.moneyDateInterest = moneyDateInterest;
  }
  public String getOverdueCorpus() {
    return overdueCorpus;
  }
  public void setOverdueCorpus(String overdueCorpus) {
    this.overdueCorpus = overdueCorpus;
  }
  public String getOverdueInterest() {
    return overdueInterest;
  }
  public void setOverdueInterest(String overdueInterest) {
    this.overdueInterest = overdueInterest;
  }
  public String getPermitDateNum() {
    return permitDateNum;
  }
  public void setPermitDateNum(String permitDateNum) {
    this.permitDateNum = permitDateNum;
  }
  public String getPunishInterest() {
    return punishInterest;
  }
  public void setPunishInterest(String punishInterest) {
    this.punishInterest = punishInterest;
  }
  public BigDecimal getPunishInterestDateRate() {
    return punishInterestDateRate;
  }
  public void setPunishInterestDateRate(BigDecimal punishInterestDateRate) {
    this.punishInterestDateRate = punishInterestDateRate;
  }
  public String getPunishInterestRateMode() {
    return punishInterestRateMode;
  }
  public void setPunishInterestRateMode(String punishInterestRateMode) {
    this.punishInterestRateMode = punishInterestRateMode;
  }
  public String getShadinessMonthNum() {
    return shadinessMonthNum;
  }
  public void setShadinessMonthNum(String shadinessMonthNum) {
    this.shadinessMonthNum = shadinessMonthNum;
  }
  public String getSubMonthNum() {
    return subMonthNum;
  }
  public void setSubMonthNum(String subMonthNum) {
    this.subMonthNum = subMonthNum;
  }
  public BigDecimal getTerminalRate() {
    return terminalRate;
  }
  public void setTerminalRate(BigDecimal terminalRate) {
    this.terminalRate = terminalRate;
  }
  public String getUniteDate() {
    return uniteDate;
  }
  public void setUniteDate(String uniteDate) {
    this.uniteDate = uniteDate;
  }
  public String getTemp_loanBankId() {
    return temp_loanBankId;
  }
  public void setTemp_loanBankId(String temp_loanBankId) {
    this.temp_loanBankId = temp_loanBankId;
  }
}
