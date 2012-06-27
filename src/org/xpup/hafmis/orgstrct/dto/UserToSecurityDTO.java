package org.xpup.hafmis.orgstrct.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;


public class UserToSecurityDTO implements Serializable{

  private static final long serialVersionUID = 5743046093074567834L;
  
  private List officeList=new ArrayList();
  
  private List bankList = new ArrayList();
  
  private List orgList = new ArrayList();
  
  private Vector bankVector=new Vector();
  
  private List sparelist = new ArrayList();

  public Vector getBankVector() {
    return bankVector;
  }

  public void setBankVector(Vector bankVector) {
    this.bankVector = bankVector;
  }

  public List getBankList() {
    return bankList;
  }

  public void setBankList(List bankList) {
    this.bankList = bankList;
  }

  public List getOfficeList() {
    return officeList;
  }

  public void setOfficeList(List officeList) {
    this.officeList = officeList;
  }

  public List getOrgList() {
    return orgList;
  }

  public void setOrgList(List orgList) {
    this.orgList = orgList;
  }

  public List getSparelist() {
    return sparelist;
  }

  public void setSparelist(List sparelist) {
    this.sparelist = sparelist;
  }
}
