package org.xpup.hafmis.syscollection.accountmng.accountchg.dto;

import java.io.Serializable;

public class OrgChgDTO implements Serializable {
  String orgname = "";

  String orgid = "";

  String orgaddr = "";

  String manager = "";

  String orgtel = "";

  String orgtype = "";

  String operatorname = "";

  String operatortel = "";

  String bankname = "";

  String orgrate = "";

  String empnum = "";

  String balance = "";

  String stoptime = "";

  String chgreason = "";

  String optime = "";

  public String getBalance() {
    return balance;
  }

  public void setBalance(String balance) {
    this.balance = balance;
  }

  public String getBankname() {
    return bankname;
  }

  public void setBankname(String bankname) {
    this.bankname = bankname;
  }

  public String getChgreason() {
    return chgreason;
  }

  public void setChgreason(String chgreason) {
    this.chgreason = chgreason;
  }

  public String getEmpnum() {
    return empnum;
  }

  public void setEmpnum(String empnum) {
    this.empnum = empnum;
  }

  public String getManager() {
    return manager;
  }

  public void setManager(String manager) {
    this.manager = manager;
  }

  public String getOperatorname() {
    return operatorname;
  }

  public void setOperatorname(String operatorname) {
    this.operatorname = operatorname;
  }

  public String getOperatortel() {
    return operatortel;
  }

  public void setOperatortel(String operatortel) {
    this.operatortel = operatortel;
  }

  public String getOptime() {
    return optime;
  }

  public void setOptime(String optime) {
    this.optime = optime;
  }

  public String getOrgaddr() {
    return orgaddr;
  }

  public void setOrgaddr(String orgaddr) {
    this.orgaddr = orgaddr;
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

  public String getOrgrate() {
    return orgrate;
  }

  public void setOrgrate(String orgrate) {
    this.orgrate = orgrate;
  }

  public String getOrgtel() {
    return orgtel;
  }

  public void setOrgtel(String orgtel) {
    this.orgtel = orgtel;
  }

  public String getOrgtype() {
    return orgtype;
  }

  public void setOrgtype(String orgtype) {
    this.orgtype = orgtype;
  }

  public String getStoptime() {
    return stoptime;
  }

  public void setStoptime(String stoptime) {
    this.stoptime = stoptime;
  }

}
