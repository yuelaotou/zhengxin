package org.xpup.hafmis.sysfinance.accounthandle.credencefillin.form;

import java.util.List;
import java.math.BigDecimal;

import org.apache.struts.action.ActionForm;

/**
 * 显示收入，支出AF
 * 
 * @Version : v1.0
 * @author : 杨光
 * @Date : 2008.12.16
 */
public class CredenceFillinIncomeExpenseAF extends ActionForm {
  private static final long serialVersionUID = 1L;

  private BigDecimal sum_debit = new BigDecimal(0.00);

  private BigDecimal sum_credit = new BigDecimal(0.00);

  private String incomeOrExpense = "";

  private List list = null;

  public BigDecimal getSum_credit() {
    return sum_credit;
  }

  public void setSum_credit(BigDecimal sum_credit) {
    this.sum_credit = sum_credit;
  }

  public BigDecimal getSum_debit() {
    return sum_debit;
  }

  public void setSum_debit(BigDecimal sum_debit) {
    this.sum_debit = sum_debit;
  }

  public static long getSerialVersionUID() {
    return serialVersionUID;
  }

  public List getList() {
    return list;
  }

  public void setList(List list) {
    this.list = list;
  }

  public String getIncomeOrExpense() {
    return incomeOrExpense;
  }

  public void setIncomeOrExpense(String incomeOrExpense) {
    this.incomeOrExpense = incomeOrExpense;
  }

}
