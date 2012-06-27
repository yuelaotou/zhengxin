package org.xpup.hafmis.sysfinance.accounthandle.credencefillin.dto;

import java.math.BigDecimal;

/**
 * Copy Right Information : 封装了损益结转列表内容的DTO Goldsoft Project :
 * CredenceFillinTcListDTO
 * 
 * @Version : v1.0
 * @author : 付云峰
 * @Date : 2007.10.25
 */
public class CredenceFillinTcListDTO {
  /** 科目代码 */
  private String subjectcode = "";

  /** 科目名称 */
  private String subjectName = "";

  /** 借方金额 */
  private BigDecimal credit = new BigDecimal(0.00);

  /** 贷方金额 */
  private BigDecimal debit = new BigDecimal(0.00);

  /** 从FN202中得到的同科目代码，同办事处下的借款金额 */
  private BigDecimal sumCredit = new BigDecimal(0.00);

  /** 从FN202中得到的同科目代码，同办事处下的贷款金额 */
  private BigDecimal sumDebit = new BigDecimal(0.00);

  /** 办事处 */
  private String office = "";

  /** 办事处str */
  private String strOffice = "";

  /** PL202待结转方向 */
  private String bySubjectDirection = "";
  
  /** PL110的方向 */
  private String balanceDirection = "";

  public BigDecimal getCredit() {
    return credit;
  }

  public void setCredit(BigDecimal credit) {
    this.credit = credit;
  }

  public BigDecimal getDebit() {
    return debit;
  }

  public void setDebit(BigDecimal debit) {
    this.debit = debit;
  }

  public String getOffice() {
    return office;
  }

  public void setOffice(String office) {
    this.office = office;
  }

  public String getSubjectcode() {
    return subjectcode;
  }

  public void setSubjectcode(String subjectcode) {
    this.subjectcode = subjectcode;
  }

  public String getSubjectName() {
    return subjectName;
  }

  public void setSubjectName(String subjectName) {
    this.subjectName = subjectName;
  }

  public String getBySubjectDirection() {
    return bySubjectDirection;
  }

  public void setBySubjectDirection(String bySubjectDirection) {
    this.bySubjectDirection = bySubjectDirection;
  }

  public String getStrOffice() {
    return strOffice;
  }

  public void setStrOffice(String strOffice) {
    this.strOffice = strOffice;
  }

  public BigDecimal getSumCredit() {
    return sumCredit;
  }

  public void setSumCredit(BigDecimal sumCredit) {
    this.sumCredit = sumCredit;
  }

  public BigDecimal getSumDebit() {
    return sumDebit;
  }

  public void setSumDebit(BigDecimal sumDebit) {
    this.sumDebit = sumDebit;
  }

  public String getBalanceDirection() {
    return balanceDirection;
  }

  public void setBalanceDirection(String balanceDirection) {
    this.balanceDirection = balanceDirection;
  }

}
