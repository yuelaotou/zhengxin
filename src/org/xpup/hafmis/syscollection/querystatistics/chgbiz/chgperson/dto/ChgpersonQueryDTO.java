package org.xpup.hafmis.syscollection.querystatistics.chgbiz.chgperson.dto;

import java.math.BigDecimal;

public class ChgpersonQueryDTO {

  private static final long serialVersionUID = 1L;

  private BigDecimal orgid = new BigDecimal(0);

  private String orgname;

  private BigDecimal empid = new BigDecimal(0);

  private String empname;

  private String type;

  private String bizDate;

  private String status;

  public BigDecimal getEmpid() {
    return empid;
  }

  public void setEmpid(BigDecimal empid) {
    this.empid = empid;
  }

  public String getEmpname() {
    return empname;
  }

  public void setEmpname(String empname) {
    this.empname = empname;
  }

  public BigDecimal getOrgid() {
    return orgid;
  }

  public void setOrgid(BigDecimal orgid) {
    this.orgid = orgid;
  }

  public String getOrgname() {
    return orgname;
  }

  public void setOrgname(String orgname) {
    this.orgname = orgname;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public String getBizDate() {
    return bizDate;
  }

  public void setBizDate(String bizDate) {
    this.bizDate = bizDate;
  }

}
