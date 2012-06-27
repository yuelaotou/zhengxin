package org.xpup.hafmis.sysloan.accounthandle.clearaccount.dto;

import java.math.BigDecimal;

import org.apache.struts.validator.ValidatorActionForm;


/**
 * 
 * @author jj
 *  2007-10-30
 */
public class ClearAccountBalanceInfoDTO extends ValidatorActionForm{
 
  private static final long serialVersionUID = 0L;
  
  private Integer count = new Integer(0);//笔数
  private BigDecimal corpus = new BigDecimal(0.00);//正常本金
  private BigDecimal overdueCorpus = new BigDecimal(0.00);//逾期本金
  private BigDecimal interest = new BigDecimal(0.00);//利息
  private BigDecimal punishInterest = new BigDecimal(0.00);//罚息
  private BigDecimal occurMoney = new BigDecimal(0.00);//呆账核销金额
  private BigDecimal overMoney = new BigDecimal(0.00);//挂账金额
  private String checkPerson = "";
  private String clearAccPerson = "";
  
  //本期借方
  //发放
  private Integer loanaccord_count=new Integer(0);//笔数
  private BigDecimal loanaccord_occurMoney=new BigDecimal(0.00);//发生额
  //错账调整
  private BigDecimal adjustaccount_loanaccordCount = new BigDecimal(0.00);//笔数
  private BigDecimal adjustaccount_loanaccordOccurMoney = new BigDecimal(0.00);//发生额
  
  //期初本金余额
  private BigDecimal initialStages_corpus = new BigDecimal(0.00);
  //正常本金发生额
  private BigDecimal corpus_occurMoney = new BigDecimal(0.00);
  //逾期本金发生额
  private BigDecimal overdue_occurMoney = new BigDecimal(0.00);
  //期末本金余额
  private BigDecimal final_corpus = new BigDecimal(0.00);
  //期初利息余额
  private BigDecimal initialStages_interest = new BigDecimal(0.00);
  //利息发生额
  private BigDecimal interest_occurMoney = new BigDecimal(0.00);
  //期末利息余额
  private BigDecimal final_interest = new BigDecimal(0.00);
  //期初罚息余额
  private BigDecimal initialStages_punishInterest = new BigDecimal(0.00);
  //罚息发生额
  private BigDecimal punishInterest_occurMoney = new BigDecimal(0.00);
  //期末罚息余额
  private BigDecimal final_punishInterest = new BigDecimal(0.00);
  //期初呆账核金额
  private BigDecimal initialStages_destroyOccurMoney = new BigDecimal(0.00);
  //呆账核销发生额
  private BigDecimal destroyOccurMoney = new BigDecimal(0.00);
  //核销收回发生额
  private BigDecimal destroybackOccurMoney = new BigDecimal(0.00);
  //期末呆账核销金额
  private BigDecimal final_destroyOccurMoney = new BigDecimal(0.00);
  //期初挂账余额
  private BigDecimal initialStages_overpayOccurMoney = new BigDecimal(0.00);
  //挂账发生额
  private BigDecimal overpayOccurMoney = new BigDecimal(0.00);
  //期末挂账余额
  private BigDecimal final_overpayOccurMoney = new BigDecimal(0.00);
  //期初保证金余额
  private BigDecimal initialStages_bailOccurMoney = new BigDecimal(0.00);
  //保证金发生额
  private BigDecimal bailOccurMoney = new BigDecimal(0.00);
  //保证金利息
  private BigDecimal bailInterestOccurMoney = new BigDecimal(0.00);
  //期末保证金余额
  private BigDecimal final_bailOccurMoney = new BigDecimal(0.00);
  
 
  public BigDecimal getAdjustaccount_loanaccordCount() {
    return adjustaccount_loanaccordCount;
  }
  public void setAdjustaccount_loanaccordCount(
      BigDecimal adjustaccount_loanaccordCount) {
    this.adjustaccount_loanaccordCount = adjustaccount_loanaccordCount;
  }
  public BigDecimal getAdjustaccount_loanaccordOccurMoney() {
    return adjustaccount_loanaccordOccurMoney;
  }
  public void setAdjustaccount_loanaccordOccurMoney(
      BigDecimal adjustaccount_loanaccordOccurMoney) {
    this.adjustaccount_loanaccordOccurMoney = adjustaccount_loanaccordOccurMoney;
  }
  
  public BigDecimal getBailInterestOccurMoney() {
    return bailInterestOccurMoney;
  }
  public void setBailInterestOccurMoney(BigDecimal bailInterestOccurMoney) {
    this.bailInterestOccurMoney = bailInterestOccurMoney;
  }
  public BigDecimal getBailOccurMoney() {
    return bailOccurMoney;
  }
  public void setBailOccurMoney(BigDecimal bailOccurMoney) {
    this.bailOccurMoney = bailOccurMoney;
  }
  
  public BigDecimal getCorpus_occurMoney() {
    return corpus_occurMoney;
  }
  public void setCorpus_occurMoney(BigDecimal corpus_occurMoney) {
    this.corpus_occurMoney = corpus_occurMoney;
  }
 
  public BigDecimal getDestroybackOccurMoney() {
    return destroybackOccurMoney;
  }
  public void setDestroybackOccurMoney(BigDecimal destroybackOccurMoney) {
    this.destroybackOccurMoney = destroybackOccurMoney;
  }
  public BigDecimal getDestroyOccurMoney() {
    return destroyOccurMoney;
  }
  public void setDestroyOccurMoney(BigDecimal destroyOccurMoney) {
    this.destroyOccurMoney = destroyOccurMoney;
  }
  public BigDecimal getFinal_bailOccurMoney() {
    return final_bailOccurMoney;
  }
  public void setFinal_bailOccurMoney(BigDecimal final_bailOccurMoney) {
    this.final_bailOccurMoney = final_bailOccurMoney;
  }
  public BigDecimal getFinal_corpus() {
    return final_corpus;
  }
  public void setFinal_corpus(BigDecimal final_corpus) {
    this.final_corpus = final_corpus;
  }
  public BigDecimal getFinal_destroyOccurMoney() {
    return final_destroyOccurMoney;
  }
  public void setFinal_destroyOccurMoney(BigDecimal final_destroyOccurMoney) {
    this.final_destroyOccurMoney = final_destroyOccurMoney;
  }
  public BigDecimal getFinal_interest() {
    return final_interest;
  }
  public void setFinal_interest(BigDecimal final_interest) {
    this.final_interest = final_interest;
  }
  public BigDecimal getFinal_overpayOccurMoney() {
    return final_overpayOccurMoney;
  }
  public void setFinal_overpayOccurMoney(BigDecimal final_overpayOccurMoney) {
    this.final_overpayOccurMoney = final_overpayOccurMoney;
  }
  public BigDecimal getFinal_punishInterest() {
    return final_punishInterest;
  }
  public void setFinal_punishInterest(BigDecimal final_punishInterest) {
    this.final_punishInterest = final_punishInterest;
  }
  public BigDecimal getInitialStages_bailOccurMoney() {
    return initialStages_bailOccurMoney;
  }
  public void setInitialStages_bailOccurMoney(
      BigDecimal initialStages_bailOccurMoney) {
    this.initialStages_bailOccurMoney = initialStages_bailOccurMoney;
  }
  public BigDecimal getInitialStages_corpus() {
    return initialStages_corpus;
  }
  public void setInitialStages_corpus(BigDecimal initialStages_corpus) {
    this.initialStages_corpus = initialStages_corpus;
  }
  public BigDecimal getInitialStages_destroyOccurMoney() {
    return initialStages_destroyOccurMoney;
  }
  public void setInitialStages_destroyOccurMoney(
      BigDecimal initialStages_destroyOccurMoney) {
    this.initialStages_destroyOccurMoney = initialStages_destroyOccurMoney;
  }
  public BigDecimal getInitialStages_interest() {
    return initialStages_interest;
  }
  public void setInitialStages_interest(BigDecimal initialStages_interest) {
    this.initialStages_interest = initialStages_interest;
  }
  public BigDecimal getInitialStages_overpayOccurMoney() {
    return initialStages_overpayOccurMoney;
  }
  public void setInitialStages_overpayOccurMoney(
      BigDecimal initialStages_overpayOccurMoney) {
    this.initialStages_overpayOccurMoney = initialStages_overpayOccurMoney;
  }
  public BigDecimal getInitialStages_punishInterest() {
    return initialStages_punishInterest;
  }
  public void setInitialStages_punishInterest(
      BigDecimal initialStages_punishInterest) {
    this.initialStages_punishInterest = initialStages_punishInterest;
  }
  public BigDecimal getInterest_occurMoney() {
    return interest_occurMoney;
  }
  public void setInterest_occurMoney(BigDecimal interest_occurMoney) {
    this.interest_occurMoney = interest_occurMoney;
  }
  public Integer getLoanaccord_count() {
    return loanaccord_count;
  }
  public void setLoanaccord_count(Integer loanaccord_count) {
    this.loanaccord_count = loanaccord_count;
  }
  public BigDecimal getLoanaccord_occurMoney() {
    return loanaccord_occurMoney;
  }
  public void setLoanaccord_occurMoney(BigDecimal loanaccord_occurMoney) {
    this.loanaccord_occurMoney = loanaccord_occurMoney;
  }
  public BigDecimal getOverdue_occurMoney() {
    return overdue_occurMoney;
  }
  public void setOverdue_occurMoney(BigDecimal overdue_occurMoney) {
    this.overdue_occurMoney = overdue_occurMoney;
  }
 
  public BigDecimal getOverpayOccurMoney() {
    return overpayOccurMoney;
  }
  public void setOverpayOccurMoney(BigDecimal overpayOccurMoney) {
    this.overpayOccurMoney = overpayOccurMoney;
  }
  
  public BigDecimal getPunishInterest_occurMoney() {
    return punishInterest_occurMoney;
  }
  public void setPunishInterest_occurMoney(BigDecimal punishInterest_occurMoney) {
    this.punishInterest_occurMoney = punishInterest_occurMoney;
  }
  public BigDecimal getCorpus() {
    return corpus;
  }
  public void setCorpus(BigDecimal corpus) {
    this.corpus = corpus;
  }
  public Integer getCount() {
    return count;
  }
  public void setCount(Integer count) {
    this.count = count;
  }
  public BigDecimal getInterest() {
    return interest;
  }
  public void setInterest(BigDecimal interest) {
    this.interest = interest;
  }
  public BigDecimal getOccurMoney() {
    return occurMoney;
  }
  public void setOccurMoney(BigDecimal occurMoney) {
    this.occurMoney = occurMoney;
  }
  public BigDecimal getOverdueCorpus() {
    return overdueCorpus;
  }
  public void setOverdueCorpus(BigDecimal overdueCorpus) {
    this.overdueCorpus = overdueCorpus;
  }
  public BigDecimal getPunishInterest() {
    return punishInterest;
  }
  public void setPunishInterest(BigDecimal punishInterest) {
    this.punishInterest = punishInterest;
  }
  public BigDecimal getOverMoney() {
    return overMoney;
  }
  public void setOverMoney(BigDecimal overMoney) {
    this.overMoney = overMoney;
  }
  public String getCheckPerson() {
    return checkPerson;
  }
  public void setCheckPerson(String checkPerson) {
    this.checkPerson = checkPerson;
  }
  public String getClearAccPerson() {
    return clearAccPerson;
  }
  public void setClearAccPerson(String clearAccPerson) {
    this.clearAccPerson = clearAccPerson;
  }


   
}
