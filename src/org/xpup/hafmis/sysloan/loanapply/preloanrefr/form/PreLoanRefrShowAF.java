/**
 * Copy Right Information   : Goldsoft 
 * Project                  : PreLoanRefrShowAF
 * @Version                 : 1.0
 * @author                  : wangy
 * 生成日期                   :2008-05-19
 **/
package org.xpup.hafmis.sysloan.loanapply.preloanrefr.form;

import java.math.BigDecimal;
import java.util.List;

import org.apache.struts.action.ActionForm;

public class PreLoanRefrShowAF extends ActionForm {

  private static final long serialVersionUID = 2769610986924081665L;

  private List list = null;

  private String id = null;

  private String loanMoney = null;
  
  private String printMoney = "";// 打印时用的贷款金额，单位：元
  
  private Integer yearlimit = new Integer(0);// 贷款期限（年限）

  private BigDecimal corpusInterest = new BigDecimal(0.00);// 月还款额(月还本息)
  
  private BigDecimal loanmoneyTotal = new BigDecimal(0.00);// 还款总额
  
  private BigDecimal interestTotal = new BigDecimal(0.00);// 利息总额
  
  private BigDecimal rate = new BigDecimal(0.00);// 年利率

  private Integer count = new Integer(0);

  public BigDecimal getCorpusInterest() {
    return corpusInterest;
  }

  public void setCorpusInterest(BigDecimal corpusInterest) {
    this.corpusInterest = corpusInterest;
  }

  public Integer getCount() {
    return count;
  }

  public void setCount(Integer count) {
    this.count = count;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public BigDecimal getInterestTotal() {
    return interestTotal;
  }

  public void setInterestTotal(BigDecimal interestTotal) {
    this.interestTotal = interestTotal;
  }

  public List getList() {
    return list;
  }

  public void setList(List list) {
    this.list = list;
  }

  public String getLoanMoney() {
    return loanMoney;
  }

  public void setLoanMoney(String loanMoney) {
    this.loanMoney = loanMoney;
  }

  public BigDecimal getLoanmoneyTotal() {
    return loanmoneyTotal;
  }

  public void setLoanmoneyTotal(BigDecimal loanmoneyTotal) {
    this.loanmoneyTotal = loanmoneyTotal;
  }

  public BigDecimal getRate() {
    return rate;
  }

  public void setRate(BigDecimal rate) {
    this.rate = rate;
  }

  public Integer getYearlimit() {
    return yearlimit;
  }

  public void setYearlimit(Integer yearlimit) {
    this.yearlimit = yearlimit;
  }

  public String getPrintMoney() {
    return printMoney;
  }

  public void setPrintMoney(String printMoney) {
    this.printMoney = printMoney;
  }
}
