package org.xpup.hafmis.syscollection.querystatistics.paymentpickdetail.form;

import java.math.BigDecimal;
import java.util.List;

import org.apache.struts.action.ActionForm;

public class PaymntPickAF extends ActionForm {

  private String office = "";

  private String year = "";

  private String month = "";

  private List list = null;

  // 市本级
  private List listSbj = null;

  // 县区
  private List listXq = null;

  private BigDecimal payMoneySum_sbj = null;

  private BigDecimal payMoneySum_xq = null;

  private BigDecimal pickMoneySum_sbj = null;

  private BigDecimal pickMoneySum_xq = null;

  private BigDecimal payMoneySum = null;

  private BigDecimal pickMoneySum = null;

  public String getOffice() {
    return office;
  }

  public void setOffice(String office) {
    this.office = office;
  }

  public String getMonth() {
    return month;
  }

  public void setMonth(String month) {
    this.month = month;
  }

  public String getYear() {
    return year;
  }

  public void setYear(String year) {
    this.year = year;
  }

  public List getList() {
    return list;
  }

  public void setList(List list) {
    this.list = list;
  }

  public List getListSbj() {
    return listSbj;
  }

  public void setListSbj(List listSbj) {
    this.listSbj = listSbj;
  }

  public List getListXq() {
    return listXq;
  }

  public void setListXq(List listXq) {
    this.listXq = listXq;
  }

  public BigDecimal getPayMoneySum() {
    return payMoneySum;
  }

  public void setPayMoneySum(BigDecimal payMoneySum) {
    this.payMoneySum = payMoneySum;
  }

  public BigDecimal getPayMoneySum_sbj() {
    return payMoneySum_sbj;
  }

  public void setPayMoneySum_sbj(BigDecimal payMoneySum_sbj) {
    this.payMoneySum_sbj = payMoneySum_sbj;
  }

  public BigDecimal getPayMoneySum_xq() {
    return payMoneySum_xq;
  }

  public void setPayMoneySum_xq(BigDecimal payMoneySum_xq) {
    this.payMoneySum_xq = payMoneySum_xq;
  }

  public BigDecimal getPickMoneySum() {
    return pickMoneySum;
  }

  public void setPickMoneySum(BigDecimal pickMoneySum) {
    this.pickMoneySum = pickMoneySum;
  }

  public BigDecimal getPickMoneySum_sbj() {
    return pickMoneySum_sbj;
  }

  public void setPickMoneySum_sbj(BigDecimal pickMoneySum_sbj) {
    this.pickMoneySum_sbj = pickMoneySum_sbj;
  }

  public BigDecimal getPickMoneySum_xq() {
    return pickMoneySum_xq;
  }

  public void setPickMoneySum_xq(BigDecimal pickMoneySum_xq) {
    this.pickMoneySum_xq = pickMoneySum_xq;
  }
}
