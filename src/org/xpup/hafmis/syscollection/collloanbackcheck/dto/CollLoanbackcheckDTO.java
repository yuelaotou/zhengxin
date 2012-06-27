package org.xpup.hafmis.syscollection.collloanbackcheck.dto;

import java.io.Serializable;
import java.math.BigDecimal;

public class CollLoanbackcheckDTO implements Serializable {
  
  private static final long serialVersionUID = 1L;
  
  private String batch_num="";
  private String doc_num="";
  private String org_id="";
  private String org_name="";
  private String emp_id="";
  private String emp_name="";
  private BigDecimal money=new BigDecimal(0.00);
  private String sett_date="";
  private String pick_satatus="";
  private String pick_satatus_num="";
  private String borrowerName="";
  private String contractId="";

  private String seqid="";
  
  public String getSeqid() {
    return seqid;
  }
  public void setSeqid(String seqid) {
    this.seqid = seqid;
  }
  public String getBatch_num() {
    return batch_num;
  }
  public void setBatch_num(String batch_num) {
    this.batch_num = batch_num;
  }
  public String getDoc_num() {
    return doc_num;
  }
  public void setDoc_num(String doc_num) {
    this.doc_num = doc_num;
  }
  public String getEmp_id() {
    return emp_id;
  }
  public void setEmp_id(String emp_id) {
    this.emp_id = emp_id;
  }
  public String getEmp_name() {
    return emp_name;
  }
  public void setEmp_name(String emp_name) {
    this.emp_name = emp_name;
  }
  public BigDecimal getMoney() {
    return money;
  }
  public void setMoney(BigDecimal money) {
    this.money = money;
  }
  public String getOrg_id() {
    return org_id;
  }
  public void setOrg_id(String org_id) {
    this.org_id = org_id;
  }
  public String getOrg_name() {
    return org_name;
  }
  public void setOrg_name(String org_name) {
    this.org_name = org_name;
  }
  public String getPick_satatus() {
    return pick_satatus;
  }
  public void setPick_satatus(String pick_satatus) {
    this.pick_satatus = pick_satatus;
  }
  public String getSett_date() {
    return sett_date;
  }
  public void setSett_date(String sett_date) {
    this.sett_date = sett_date;
  }
  public String getPick_satatus_num() {
    return pick_satatus_num;
  }
  public void setPick_satatus_num(String pick_satatus_num) {
    this.pick_satatus_num = pick_satatus_num;
  }
  public String getBorrowerName() {
    return borrowerName;
  }
  public void setBorrowerName(String borrowerName) {
    this.borrowerName = borrowerName;
  }
  public String getContractId() {
    return contractId;
  }
  public void setContractId(String contractId) {
    this.contractId = contractId;
  }
  
}
