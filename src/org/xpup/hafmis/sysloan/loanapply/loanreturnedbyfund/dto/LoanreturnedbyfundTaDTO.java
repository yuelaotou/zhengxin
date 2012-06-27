package org.xpup.hafmis.sysloan.loanapply.loanreturnedbyfund.dto;
//¸¨Öú½è¿îÈËDTO
public class LoanreturnedbyfundTaDTO {
private String id;
private String empid;
private String empname;

private String orgname;

private String orgid;

private String cardnum;
private String seq;

public String getOrgid() {
  return orgid;
}
public void setOrgid(String orgid) {
  this.orgid = orgid;
}

public String getEmpid() {
  return empid;
}
public void setEmpid(String empid) {
  this.empid = empid;
}
public String getEmpname() {
  return empname;
}
public void setEmpname(String empname) {
  this.empname = empname;
}

public String getId() {
  return id;
}
public void setId(String id) {
  this.id = id;
}

public String getOrgname() {
  return orgname;
}
public void setOrgname(String orgname) {
  this.orgname = orgname;
}

public String getCardnum() {
  return cardnum;
}
public void setCardnum(String cardnum) {
  this.cardnum = cardnum;
}
public String getSeq() {
  return seq;
}
public void setSeq(String seq) {
  this.seq = seq;
}


}
