package org.xpup.hafmis.syscollection.querystatistics.cumulativeinfo.dto;

import java.math.BigDecimal;

import org.apache.struts.action.ActionForm;

public class Printdto extends ActionForm{

  
private String bank_name="";
private String operater="";
private String dba="";
private String date="";

public String getBank_name() {
  return bank_name;
}
public void setBank_name(String bank_name) {
  this.bank_name = bank_name;
}
public String getDate() {
  return date;
}
public void setDate(String date) {
  this.date = date;
}
public String getDba() {
  return dba;
}
public void setDba(String dba) {
  this.dba = dba;
}
public String getOperater() {
  return operater;
}
public void setOperater(String operater) {
  this.operater = operater;
}
}
