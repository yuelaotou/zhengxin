package org.xpup.hafmis.sysloan.accounthandle.clearaccount.form;

import java.math.BigDecimal;


import org.apache.struts.action.ActionForm;
import org.apache.struts.validator.ValidatorActionForm;


/**
 * 
 * @author jj
   *2007-10-30
 */
public class ClearAccountBalanceInfoAF extends ActionForm{
 
  private static final long serialVersionUID = 0L;  //本期贷方
  //单笔回收
  private Integer single_count = new Integer(0);//笔数
  private BigDecimal single_corpus = new BigDecimal(0.00);//正常本金
  private BigDecimal single_overdueCorpus = new BigDecimal(0.00);//逾期本金
  private BigDecimal single_interest = new BigDecimal(0.00);//利息
  private BigDecimal single_punishInterest = new BigDecimal(0.00);//罚息
  private BigDecimal single_occurMoney = new BigDecimal(0.00);//挂账金额
  //部份提前
  private Integer part_count = new Integer(0);//笔数
  private BigDecimal part_corpus = new BigDecimal(0.00);//正常本金
  private BigDecimal part_overdueCorpus = new BigDecimal(0.00);//逾期本金
  private BigDecimal part_interest = new BigDecimal(0.00);//利息
  private BigDecimal part_punishInterest = new BigDecimal(0.00);//罚息
  private BigDecimal part_occurMoney = new BigDecimal(0.00);//挂账金额
  //一次性清还
  private Integer all_count = new Integer(0);//笔数
  private BigDecimal all_corpus = new BigDecimal(0.00);//正常本金
  private BigDecimal all_overdueCorpus = new BigDecimal(0.00);//逾期本金
  private BigDecimal all_interest = new BigDecimal(0.00);//利息
  private BigDecimal all_punishInterest = new BigDecimal(0.00);//罚息
  private BigDecimal all_occurMoney = new BigDecimal(0.00);//挂账金额
  //批量回收
  private Integer batch_count = new Integer(0);//笔数
  private BigDecimal batch_corpus = new BigDecimal(0.00);//正常本金
  private BigDecimal batch_overdueCorpus = new BigDecimal(0.00);//逾期本金
  private BigDecimal batch_interest = new BigDecimal(0.00);//利息
  private BigDecimal batch_punishInterest = new BigDecimal(0.00);//罚息
  private BigDecimal batch_occurMoney = new BigDecimal(0.00);//挂账金额
  //呆账核销
  private Integer destroy_count = new Integer(0);//笔数
  private BigDecimal destroy_corpus = new BigDecimal(0.00);//正常本金
  private BigDecimal destroy_overdueCorpus = new BigDecimal(0.00);//逾期本金
  private BigDecimal destroy_interest = new BigDecimal(0.00);//利息
  private BigDecimal destroy_punishInterest = new BigDecimal(0.00);//罚息
  private BigDecimal destroy_occurMoney = new BigDecimal(0.00);//呆账核销金额
  //核销收回
  private Integer destroyback_count = new Integer(0);//笔数
  private BigDecimal destroyback_corpus = new BigDecimal(0.00);//正常本金
  private BigDecimal destroyback_overdueCorpus = new BigDecimal(0.00);//逾期本金
  private BigDecimal destroyback_interest = new BigDecimal(0.00);//利息
  private BigDecimal destroyback_punishInterest = new BigDecimal(0.00);//罚息
  private BigDecimal destroyback_occurMoney = new BigDecimal(0.00);//呆账核销金额
  //挂账
  private Integer overpay_count = new Integer(0);//笔数
  private BigDecimal overpay_occurMoney = new BigDecimal(0.00);//挂账金额
  //错账调整
  private Integer adjustaccount_count = new Integer(0);//笔数
  private BigDecimal adjustaccount_corpus = new BigDecimal(0.00);//正常本金
  private BigDecimal adjustaccount_overdueCorpus = new BigDecimal(0.00);//逾期本金
  private BigDecimal adjustaccount_interest = new BigDecimal(0.00);//利息
  private BigDecimal adjustaccount_punishInterest = new BigDecimal(0.00);//罚息
  private BigDecimal adjustaccount_destroyOccurMoney = new BigDecimal(0.00);//呆账核销金额
  private BigDecimal adjustaccount_occurMoney = new BigDecimal(0.00);//挂账金额
  
  //保证金
  private Integer credit_bail_count = new Integer(0);//笔数
  private BigDecimal credti_bail_occurMoney = new BigDecimal(0.00);//发生额
  
  //本期借方
  //发放
  private Integer loanaccord_count=new Integer(0);//笔数
  private BigDecimal loanaccord_occurMoney=new BigDecimal(0.00);//发生额
  //错账调整
  private Integer adjustaccount_loanaccordCount = new Integer(0);//笔数
  private BigDecimal adjustaccount_loanaccordOccurMoney = new BigDecimal(0.00);//发生额
  //保证金
  private Integer debit_bail_count = new Integer(0);//笔数
  private BigDecimal debit_bail_occurMoney = new BigDecimal(0.00);//发生额
  //提取利息
  private Integer debit_interest_count = new Integer(0);//笔数
  private BigDecimal debit_interest_occurMoney = new BigDecimal(0.00);//发生额
  //合计
  private Integer credit_count = new Integer(0);//笔数
  private BigDecimal credit_corpus = new BigDecimal(0.00);//正常本金
  private BigDecimal credit_overdueCorpus = new BigDecimal(0.00);//逾期本金
  private BigDecimal credit_interest = new BigDecimal(0.00);//利息
  private BigDecimal credit_punishInterest = new BigDecimal(0.00);//罚息
  private BigDecimal credit_destoryOccurMoney = new BigDecimal(0.00);//呆账核销金额
  private BigDecimal credit_overpayOccurMoney = new BigDecimal(0.00);//挂账金额
  private BigDecimal credit_occurMoney = new BigDecimal(0.00);//发生额
  private Integer debit_count = new Integer(0);//笔数
  private BigDecimal debit_occurMoney = new BigDecimal(0.00);//发生额
  
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
  
  private String loanBankId = "";
  
  private String bizDate = "";
  
  private String checkPerson = "";
  
  private String clearAccountPerson = "";
  
  private String printDate = "";
  
  public String getCheckPerson() {
    return checkPerson;
  }
  public void setCheckPerson(String checkPerson) {
    this.checkPerson = checkPerson;
  }
  public String getClearAccountPerson() {
    return clearAccountPerson;
  }
  public void setClearAccountPerson(String clearAccountPerson) {
    this.clearAccountPerson = clearAccountPerson;
  }
  public String getPrintDate() {
    return printDate;
  }
  public void setPrintDate(String printDate) {
    this.printDate = printDate;
  }
  public BigDecimal getCredit_occurMoney() {
    return credit_occurMoney;
  }
  public void setCredit_occurMoney(BigDecimal credit_occurMoney) {
    this.credit_occurMoney = credit_occurMoney;
  }
  public Integer getCredit_bail_count() {
    return credit_bail_count;
  }
  public void setCredit_bail_count(Integer credit_bail_count) {
    this.credit_bail_count = credit_bail_count;
  }
  public BigDecimal getCredti_bail_occurMoney() {
    return credti_bail_occurMoney;
  }
  public void setCredti_bail_occurMoney(BigDecimal credti_bail_occurMoney) {
    this.credti_bail_occurMoney = credti_bail_occurMoney;
  }
  public Integer getDebit_bail_count() {
    return debit_bail_count;
  }
  public void setDebit_bail_count(Integer debit_bail_count) {
    this.debit_bail_count = debit_bail_count;
  }
  public BigDecimal getDebit_bail_occurMoney() {
    return debit_bail_occurMoney;
  }
  public void setDebit_bail_occurMoney(BigDecimal debit_bail_occurMoney) {
    this.debit_bail_occurMoney = debit_bail_occurMoney;
  }
  public Integer getDebit_interest_count() {
    return debit_interest_count;
  }
  public void setDebit_interest_count(Integer debit_interest_count) {
    this.debit_interest_count = debit_interest_count;
  }
  public BigDecimal getDebit_interest_occurMoney() {
    return debit_interest_occurMoney;
  }
  public void setDebit_interest_occurMoney(BigDecimal debit_interest_occurMoney) {
    this.debit_interest_occurMoney = debit_interest_occurMoney;
  }
  public String getBizDate() {
    return bizDate;
  }
  public void setBizDate(String bizDate) {
    this.bizDate = bizDate;
  }
  public String getLoanBankId() {
    return loanBankId;
  }
  public void setLoanBankId(String loanBankId) {
    this.loanBankId = loanBankId;
  }
  public BigDecimal getCredit_corpus() {
    return credit_corpus;
  }
  public void setCredit_corpus(BigDecimal credit_corpus) {
    this.credit_corpus = credit_corpus;
  }
  public Integer getCredit_count() {
    return credit_count;
  }
  public void setCredit_count(Integer credit_count) {
    this.credit_count = credit_count;
  }
  public BigDecimal getCredit_destoryOccurMoney() {
    return credit_destoryOccurMoney;
  }
  public void setCredit_destoryOccurMoney(BigDecimal credit_destoryOccurMoney) {
    this.credit_destoryOccurMoney = credit_destoryOccurMoney;
  }
  public BigDecimal getCredit_interest() {
    return credit_interest;
  }
  public void setCredit_interest(BigDecimal credit_interest) {
    this.credit_interest = credit_interest;
  }
  public BigDecimal getCredit_overdueCorpus() {
    return credit_overdueCorpus;
  }
  public void setCredit_overdueCorpus(BigDecimal credit_overdueCorpus) {
    this.credit_overdueCorpus = credit_overdueCorpus;
  }
  public BigDecimal getCredit_overpayOccurMoney() {
    return credit_overpayOccurMoney;
  }
  public void setCredit_overpayOccurMoney(BigDecimal credit_overpayOccurMoney) {
    this.credit_overpayOccurMoney = credit_overpayOccurMoney;
  }
  public BigDecimal getCredit_punishInterest() {
    return credit_punishInterest;
  }
  public void setCredit_punishInterest(BigDecimal credit_punishInterest) {
    this.credit_punishInterest = credit_punishInterest;
  }
  public Integer getDebit_count() {
    return debit_count;
  }
  public void setDebit_count(Integer debit_count) {
    this.debit_count = debit_count;
  }
  public BigDecimal getDebit_occurMoney() {
    return debit_occurMoney;
  }
  public void setDebit_occurMoney(BigDecimal debit_occurMoney) {
    this.debit_occurMoney = debit_occurMoney;
  }
  public BigDecimal getAdjustaccount_corpus() {
    return adjustaccount_corpus;
  }
  public void setAdjustaccount_corpus(BigDecimal adjustaccount_corpus) {
    this.adjustaccount_corpus = adjustaccount_corpus;
  }
  public Integer getAdjustaccount_count() {
    return adjustaccount_count;
  }
  public void setAdjustaccount_count(Integer adjustaccount_count) {
    this.adjustaccount_count = adjustaccount_count;
  }
  public BigDecimal getAdjustaccount_destroyOccurMoney() {
    return adjustaccount_destroyOccurMoney;
  }
  public void setAdjustaccount_destroyOccurMoney(
      BigDecimal adjustaccount_destroyOccurMoney) {
    this.adjustaccount_destroyOccurMoney = adjustaccount_destroyOccurMoney;
  }
  public BigDecimal getAdjustaccount_interest() {
    return adjustaccount_interest;
  }
  public void setAdjustaccount_interest(BigDecimal adjustaccount_interest) {
    this.adjustaccount_interest = adjustaccount_interest;
  }
  public Integer getAdjustaccount_loanaccordCount() {
    return adjustaccount_loanaccordCount;
  }
  public void setAdjustaccount_loanaccordCount(
      Integer adjustaccount_loanaccordCount) {
    this.adjustaccount_loanaccordCount = adjustaccount_loanaccordCount;
  }
  public BigDecimal getAdjustaccount_loanaccordOccurMoney() {
    return adjustaccount_loanaccordOccurMoney;
  }
  public void setAdjustaccount_loanaccordOccurMoney(
      BigDecimal adjustaccount_loanaccordOccurMoney) {
    this.adjustaccount_loanaccordOccurMoney = adjustaccount_loanaccordOccurMoney;
  }
  public BigDecimal getAdjustaccount_occurMoney() {
    return adjustaccount_occurMoney;
  }
  public void setAdjustaccount_occurMoney(BigDecimal adjustaccount_occurMoney) {
    this.adjustaccount_occurMoney = adjustaccount_occurMoney;
  }
  public BigDecimal getAdjustaccount_overdueCorpus() {
    return adjustaccount_overdueCorpus;
  }
  public void setAdjustaccount_overdueCorpus(
      BigDecimal adjustaccount_overdueCorpus) {
    this.adjustaccount_overdueCorpus = adjustaccount_overdueCorpus;
  }
  public BigDecimal getAdjustaccount_punishInterest() {
    return adjustaccount_punishInterest;
  }
  public void setAdjustaccount_punishInterest(
      BigDecimal adjustaccount_punishInterest) {
    this.adjustaccount_punishInterest = adjustaccount_punishInterest;
  }
  public BigDecimal getAll_corpus() {
    return all_corpus;
  }
  public void setAll_corpus(BigDecimal all_corpus) {
    this.all_corpus = all_corpus;
  }
  public Integer getAll_count() {
    return all_count;
  }
  public void setAll_count(Integer all_count) {
    this.all_count = all_count;
  }
  public BigDecimal getAll_interest() {
    return all_interest;
  }
  public void setAll_interest(BigDecimal all_interest) {
    this.all_interest = all_interest;
  }
  public BigDecimal getAll_occurMoney() {
    return all_occurMoney;
  }
  public void setAll_occurMoney(BigDecimal all_occurMoney) {
    this.all_occurMoney = all_occurMoney;
  }
  public BigDecimal getAll_overdueCorpus() {
    return all_overdueCorpus;
  }
  public void setAll_overdueCorpus(BigDecimal all_overdueCorpus) {
    this.all_overdueCorpus = all_overdueCorpus;
  }
  public BigDecimal getAll_punishInterest() {
    return all_punishInterest;
  }
  public void setAll_punishInterest(BigDecimal all_punishInterest) {
    this.all_punishInterest = all_punishInterest;
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
  public BigDecimal getBatch_corpus() {
    return batch_corpus;
  }
  public void setBatch_corpus(BigDecimal batch_corpus) {
    this.batch_corpus = batch_corpus;
  }
  public Integer getBatch_count() {
    return batch_count;
  }
  public void setBatch_count(Integer batch_count) {
    this.batch_count = batch_count;
  }
  public BigDecimal getBatch_interest() {
    return batch_interest;
  }
  public void setBatch_interest(BigDecimal batch_interest) {
    this.batch_interest = batch_interest;
  }
  public BigDecimal getBatch_occurMoney() {
    return batch_occurMoney;
  }
  public void setBatch_occurMoney(BigDecimal batch_occurMoney) {
    this.batch_occurMoney = batch_occurMoney;
  }
  public BigDecimal getBatch_overdueCorpus() {
    return batch_overdueCorpus;
  }
  public void setBatch_overdueCorpus(BigDecimal batch_overdueCorpus) {
    this.batch_overdueCorpus = batch_overdueCorpus;
  }
  public BigDecimal getBatch_punishInterest() {
    return batch_punishInterest;
  }
  public void setBatch_punishInterest(BigDecimal batch_punishInterest) {
    this.batch_punishInterest = batch_punishInterest;
  }
  public BigDecimal getCorpus_occurMoney() {
    return corpus_occurMoney;
  }
  public void setCorpus_occurMoney(BigDecimal corpus_occurMoney) {
    this.corpus_occurMoney = corpus_occurMoney;
  }
  public BigDecimal getDestroy_corpus() {
    return destroy_corpus;
  }
  public void setDestroy_corpus(BigDecimal destroy_corpus) {
    this.destroy_corpus = destroy_corpus;
  }
  public Integer getDestroy_count() {
    return destroy_count;
  }
  public void setDestroy_count(Integer destroy_count) {
    this.destroy_count = destroy_count;
  }
  public BigDecimal getDestroy_interest() {
    return destroy_interest;
  }
  public void setDestroy_interest(BigDecimal destroy_interest) {
    this.destroy_interest = destroy_interest;
  }
  public BigDecimal getDestroy_occurMoney() {
    return destroy_occurMoney;
  }
  public void setDestroy_occurMoney(BigDecimal destroy_occurMoney) {
    this.destroy_occurMoney = destroy_occurMoney;
  }
  public BigDecimal getDestroy_overdueCorpus() {
    return destroy_overdueCorpus;
  }
  public void setDestroy_overdueCorpus(BigDecimal destroy_overdueCorpus) {
    this.destroy_overdueCorpus = destroy_overdueCorpus;
  }
  public BigDecimal getDestroy_punishInterest() {
    return destroy_punishInterest;
  }
  public void setDestroy_punishInterest(BigDecimal destroy_punishInterest) {
    this.destroy_punishInterest = destroy_punishInterest;
  }
  public BigDecimal getDestroyback_corpus() {
    return destroyback_corpus;
  }
  public void setDestroyback_corpus(BigDecimal destroyback_corpus) {
    this.destroyback_corpus = destroyback_corpus;
  }
  public Integer getDestroyback_count() {
    return destroyback_count;
  }
  public void setDestroyback_count(Integer destroyback_count) {
    this.destroyback_count = destroyback_count;
  }
  public BigDecimal getDestroyback_interest() {
    return destroyback_interest;
  }
  public void setDestroyback_interest(BigDecimal destroyback_interest) {
    this.destroyback_interest = destroyback_interest;
  }
  public BigDecimal getDestroyback_occurMoney() {
    return destroyback_occurMoney;
  }
  public void setDestroyback_occurMoney(BigDecimal destroyback_occurMoney) {
    this.destroyback_occurMoney = destroyback_occurMoney;
  }
  public BigDecimal getDestroyback_overdueCorpus() {
    return destroyback_overdueCorpus;
  }
  public void setDestroyback_overdueCorpus(BigDecimal destroyback_overdueCorpus) {
    this.destroyback_overdueCorpus = destroyback_overdueCorpus;
  }
  public BigDecimal getDestroyback_punishInterest() {
    return destroyback_punishInterest;
  }
  public void setDestroyback_punishInterest(BigDecimal destroyback_punishInterest) {
    this.destroyback_punishInterest = destroyback_punishInterest;
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
  public Integer getOverpay_count() {
    return overpay_count;
  }
  public void setOverpay_count(Integer overpay_count) {
    this.overpay_count = overpay_count;
  }
  public BigDecimal getOverpay_occurMoney() {
    return overpay_occurMoney;
  }
  public void setOverpay_occurMoney(BigDecimal overpay_occurMoney) {
    this.overpay_occurMoney = overpay_occurMoney;
  }
  public BigDecimal getOverpayOccurMoney() {
    return overpayOccurMoney;
  }
  public void setOverpayOccurMoney(BigDecimal overpayOccurMoney) {
    this.overpayOccurMoney = overpayOccurMoney;
  }
  public BigDecimal getPart_corpus() {
    return part_corpus;
  }
  public void setPart_corpus(BigDecimal part_corpus) {
    this.part_corpus = part_corpus;
  }
  public Integer getPart_count() {
    return part_count;
  }
  public void setPart_count(Integer part_count) {
    this.part_count = part_count;
  }
  public BigDecimal getPart_interest() {
    return part_interest;
  }
  public void setPart_interest(BigDecimal part_interest) {
    this.part_interest = part_interest;
  }
  public BigDecimal getPart_occurMoney() {
    return part_occurMoney;
  }
  public void setPart_occurMoney(BigDecimal part_occurMoney) {
    this.part_occurMoney = part_occurMoney;
  }
  public BigDecimal getPart_overdueCorpus() {
    return part_overdueCorpus;
  }
  public void setPart_overdueCorpus(BigDecimal part_overdueCorpus) {
    this.part_overdueCorpus = part_overdueCorpus;
  }
  public BigDecimal getPart_punishInterest() {
    return part_punishInterest;
  }
  public void setPart_punishInterest(BigDecimal part_punishInterest) {
    this.part_punishInterest = part_punishInterest;
  }
  public BigDecimal getPunishInterest_occurMoney() {
    return punishInterest_occurMoney;
  }
  public void setPunishInterest_occurMoney(BigDecimal punishInterest_occurMoney) {
    this.punishInterest_occurMoney = punishInterest_occurMoney;
  }
  public BigDecimal getSingle_corpus() {
    return single_corpus;
  }
  public void setSingle_corpus(BigDecimal single_corpus) {
    this.single_corpus = single_corpus;
  }
  public Integer getSingle_count() {
    return single_count;
  }
  public void setSingle_count(Integer single_count) {
    this.single_count = single_count;
  }
  public BigDecimal getSingle_interest() {
    return single_interest;
  }
  public void setSingle_interest(BigDecimal single_interest) {
    this.single_interest = single_interest;
  }
  public BigDecimal getSingle_occurMoney() {
    return single_occurMoney;
  }
  public void setSingle_occurMoney(BigDecimal single_occurMoney) {
    this.single_occurMoney = single_occurMoney;
  }
  public BigDecimal getSingle_overdueCorpus() {
    return single_overdueCorpus;
  }
  public void setSingle_overdueCorpus(BigDecimal single_overdueCorpus) {
    this.single_overdueCorpus = single_overdueCorpus;
  }
  public BigDecimal getSingle_punishInterest() {
    return single_punishInterest;
  }
  public void setSingle_punishInterest(BigDecimal single_punishInterest) {
    this.single_punishInterest = single_punishInterest;
  }
   
  
}
