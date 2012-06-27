package org.xpup.hafmis.syscollection.accountmng.accountopen.form;

import java.util.List;

import org.apache.struts.action.ActionForm;

public class EmpkhAF extends ActionForm {

  /**
   * 
   */
  private static final long serialVersionUID = 1L;

  private List list;
  
  private String empid = null;

  private String name = null;

  private String cardNumber = null;

  private int listCount;

  private String orgCount = null;

  private String salaryBaseCount = "0";

  private String orgpaySumCount = "0";

  private String emppaySumCount = "0";

  private String sumCount = "0";

  private String isType = "";

  public String getIsType() {
    return isType;
  }

  public void setIsType(String isType) {
    this.isType = isType;
  }

  public String getEmppaySumCount() {
    return emppaySumCount;
  }

  public void setEmppaySumCount(String emppaySumCount) {
    this.emppaySumCount = emppaySumCount;
  }

  public String getOrgCount() {
    return orgCount;
  }

  public void setOrgCount(String orgCount) {
    this.orgCount = orgCount;
  }

  public String getOrgpaySumCount() {
    return orgpaySumCount;
  }

  public void setOrgpaySumCount(String orgpaySumCount) {
    this.orgpaySumCount = orgpaySumCount;
  }

  public String getSalaryBaseCount() {
    return salaryBaseCount;
  }

  public void setSalaryBaseCount(String salaryBaseCount) {
    this.salaryBaseCount = salaryBaseCount;
  }

  public String getSumCount() {
    return sumCount;
  }

  public void setSumCount(String sumCount) {
    this.sumCount = sumCount;
  }

  public int getListCount() {
    return listCount;
  }

  public void setListCount(int listCount) {
    this.listCount = listCount;
  }

  public String getCardNumber() {
    return cardNumber;
  }

  public void setCardNumber(String cardNumber) {
    this.cardNumber = cardNumber;
  }

  public String getEmpid() {
    return empid;
  }

  public void setEmpid(String empid) {
    this.empid = empid;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public void reset() {
    this.empid = "";
    this.name = "";
    this.cardNumber = "";
  }

  public List getList() {
    return list;
  }

  public void setList(List list) {
    this.list = list;
  }
}
