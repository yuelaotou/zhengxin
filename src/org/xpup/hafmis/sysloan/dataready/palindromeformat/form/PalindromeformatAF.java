package org.xpup.hafmis.sysloan.dataready.palindromeformat.form;

import java.util.ArrayList;
import java.util.List;

import org.apache.struts.action.ActionForm;
/**
 * 
 * @author yuqf
 *2007-12-20
 */
public class PalindromeformatAF extends ActionForm{
  private String bankId = "";
  private List loanBankNameList = new ArrayList();
  
  private String loanId = "";//贷款帐号 1
  private String name = "";//姓名 2
  private String payDate = "";//还款年月 3
  private String realCorpus = "";// 实扣正常本金 4
  private String realOverdueCorpus = "";//实扣逾期本金 5
  private String realInterest = "";// 实扣正常利息 6
  private String realOverdueInterest = "";// 实扣逾期利息 7
  private String realPunishInterest = "";// 实扣罚息 8
  private String nobackCorpus = "";// 未还正常本金 9
  private String nobackOverdueCorpus = "";// 未还逾期本金 10
  private String nobackInterest = "";// 未还正常利息 11 
  private String nobackOverdueInterest = "";// 未还逾期利息  12
  private String nobackPunishInterest = "";// 未还罚息 13
  private String deadLine = "";// 提前还款后剩余期限 14
  
  public String getBankId() {
    return bankId;
  }
  public void setBankId(String bankId) {
    this.bankId = bankId;
  }
  public String getDeadLine() {
    return deadLine;
  }
  public void setDeadLine(String deadLine) {
    this.deadLine = deadLine;
  }
  public List getLoanBankNameList() {
    return loanBankNameList;
  }
  public void setLoanBankNameList(List loanBankNameList) {
    this.loanBankNameList = loanBankNameList;
  }
  public String getLoanId() {
    return loanId;
  }
  public void setLoanId(String loanId) {
    this.loanId = loanId;
  }
  public String getName() {
    return name;
  }
  public void setName(String name) {
    this.name = name;
  }
  public String getNobackCorpus() {
    return nobackCorpus;
  }
  public void setNobackCorpus(String nobackCorpus) {
    this.nobackCorpus = nobackCorpus;
  }
  public String getNobackInterest() {
    return nobackInterest;
  }
  public void setNobackInterest(String nobackInterest) {
    this.nobackInterest = nobackInterest;
  }
  public String getNobackOverdueCorpus() {
    return nobackOverdueCorpus;
  }
  public void setNobackOverdueCorpus(String nobackOverdueCorpus) {
    this.nobackOverdueCorpus = nobackOverdueCorpus;
  }
  public String getNobackOverdueInterest() {
    return nobackOverdueInterest;
  }
  public void setNobackOverdueInterest(String nobackOverdueInterest) {
    this.nobackOverdueInterest = nobackOverdueInterest;
  }
  public String getNobackPunishInterest() {
    return nobackPunishInterest;
  }
  public void setNobackPunishInterest(String nobackPunishInterest) {
    this.nobackPunishInterest = nobackPunishInterest;
  }
  public String getPayDate() {
    return payDate;
  }
  public void setPayDate(String payDate) {
    this.payDate = payDate;
  }
  public String getRealCorpus() {
    return realCorpus;
  }
  public void setRealCorpus(String realCorpus) {
    this.realCorpus = realCorpus;
  }
  public String getRealInterest() {
    return realInterest;
  }
  public void setRealInterest(String realInterest) {
    this.realInterest = realInterest;
  }
  public String getRealOverdueCorpus() {
    return realOverdueCorpus;
  }
  public void setRealOverdueCorpus(String realOverdueCorpus) {
    this.realOverdueCorpus = realOverdueCorpus;
  }
  public String getRealOverdueInterest() {
    return realOverdueInterest;
  }
  public void setRealOverdueInterest(String realOverdueInterest) {
    this.realOverdueInterest = realOverdueInterest;
  }
  public String getRealPunishInterest() {
    return realPunishInterest;
  }
  public void setRealPunishInterest(String realPunishInterest) {
    this.realPunishInterest = realPunishInterest;
  }

 
  
}
