package org.xpup.hafmis.syscollection.querystatistics.accountinfo.empaccountinfo.dto;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.xpup.hafmis.syscollection.common.domain.entity.EmpHAFAccountFlow;

public class EmpaccountinfoDTO {
//本期贷方发生额
private BigDecimal  temp_credit=new BigDecimal(0.00);
//本期借方发生额
private BigDecimal  temp_debit=new BigDecimal(0.00);
//求利息
private BigDecimal  temp_interest=new BigDecimal(0.00);

private EmpHAFAccountFlow empHAFAccountFlow=new EmpHAFAccountFlow();

private List list=new ArrayList();
public EmpHAFAccountFlow getEmpHAFAccountFlow() {
  return empHAFAccountFlow;
}
public void setEmpHAFAccountFlow(EmpHAFAccountFlow empHAFAccountFlow) {
  this.empHAFAccountFlow = empHAFAccountFlow;
}
public BigDecimal getTemp_credit() {
  return temp_credit;
}
public void setTemp_credit(BigDecimal temp_credit) {
  this.temp_credit = temp_credit;
}
public BigDecimal getTemp_debit() {
  return temp_debit;
}
public void setTemp_debit(BigDecimal temp_debit) {
  this.temp_debit = temp_debit;
}
public BigDecimal getTemp_interest() {
  return temp_interest;
}
public void setTemp_interest(BigDecimal temp_interest) {
  this.temp_interest = temp_interest;
}
public List getList() {
  return list;
}
public void setList(List list) {
  this.list = list;
}
}
