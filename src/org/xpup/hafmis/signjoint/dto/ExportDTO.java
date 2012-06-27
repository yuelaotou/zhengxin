package org.xpup.hafmis.signjoint.dto;

public class ExportDTO {
  
  String orgid;
  String orgname;
  String empid;
  String empname;
  String cardnum;
  public ExportDTO(){
    orgid="";
    orgname="";
    empid="";
    empname="";
    cardnum="";
  }
  
  public ExportDTO(String orgid, String orgname, String empid, String empname, String cardnum) {
    this.orgid = orgid;
    this.orgname = orgname;
    this.empid = empid;
    this.empname = empname;
    this.cardnum = cardnum;
  }

  public String getCardnum() {
    return cardnum;
  }
  public void setCardnum(String cardnum) {
    this.cardnum = cardnum;
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
  public String getOrgid() {
    return orgid;
  }
  public void setOrgid(String orgid) {
    this.orgid = orgid;
  }
  public String getOrgname() {
    return orgname;
  }
  public void setOrgname(String orgname) {
    this.orgname = orgname;
  }
  
}
