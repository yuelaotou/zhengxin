package org.xpup.hafmis.sysloan.loanapply.loanapply.dto;

public class LoanapplyTeListDTO {
  private String id;

  private String contractid;

  private String empid;

  private String borrowername;

  private String cardnum;

  private String buyhousetype;

  private String loanmoney="0";

  private String loanlimit="0";

  private String contract_st;
  
  private String temp_contract_st;
  
  private String temp_c_st;
  
  private String isPrintApply;
  
  private String redate;//回件时间
  

  public String getRedate() {
    return redate;
  }

  public void setRedate(String redate) {
    this.redate = redate;
  }

  public String getIsPrintApply() {
    return isPrintApply;
  }

  public void setIsPrintApply(String isPrintApply) {
    this.isPrintApply = isPrintApply;
  }

  public String getTemp_c_st() {
    return temp_c_st;
  }

  public void setTemp_c_st(String temp_c_st) {
    this.temp_c_st = temp_c_st;
  }

  public String getBorrowername() {
    return borrowername;
  }

  public void setBorrowername(String borrowername) {
    this.borrowername = borrowername;
  }

  public String getBuyhousetype() {
    return buyhousetype;
  }

  public void setBuyhousetype(String buyhousetype) {
    this.buyhousetype = buyhousetype;
  }

  public String getCardnum() {
    return cardnum;
  }

  public void setCardnum(String cardnum) {
    this.cardnum = cardnum;
  }

  public String getContract_st() {
    return contract_st;
  }

  public void setContract_st(String contract_st) {
    this.contract_st = contract_st;
  }

  public String getContractid() {
    return contractid;
  }

  public void setContractid(String contractid) {
    this.contractid = contractid;
  }

  public String getEmpid() {
    return empid;
  }

  public void setEmpid(String empid) {
    this.empid = empid;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getLoanlimit() {
    return loanlimit;
  }

  public void setLoanlimit(String loanlimit) {
    this.loanlimit = loanlimit;
  }

  public String getLoanmoney() {
    return loanmoney;
  }

  public void setLoanmoney(String loanmoney) {
    this.loanmoney = loanmoney;
  }

  public String getTemp_contract_st() {
    return temp_contract_st;
  }

  public void setTemp_contract_st(String temp_contract_st) {
    this.temp_contract_st = temp_contract_st;
  }

}
