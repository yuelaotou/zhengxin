package org.xpup.hafmis.syscollection.pickupmng.pickup.dto;

import java.math.BigDecimal;

public class PickCheckDTO {

  private String id = "";

  private String orgid = "";

  private String orgname = "";

  private int personcount = 0;

  private BigDecimal corpus = new BigDecimal(0.00);

  private BigDecimal interest = new BigDecimal(0.00);

  private BigDecimal corpusInterest = new BigDecimal(0.00);

  private String pickdate = "";

  private String checkdate = "";

  private String biztype = "";

  private String hestatus = "";

  private String pistatus = "";

  private String operator = "";

  public String getBiztype() {
    return biztype;
  }

  public void setBiztype(String biztype) {
    this.biztype = biztype;
  }

  public String getCheckdate() {
    return checkdate;
  }

  public void setCheckdate(String checkdate) {
    this.checkdate = checkdate;
  }

  public BigDecimal getCorpus() {
    return corpus;
  }

  public void setCorpus(BigDecimal corpus) {
    this.corpus = corpus;
  }

  public BigDecimal getCorpusInterest() {
    return corpusInterest;
  }

  public void setCorpusInterest(BigDecimal corpusInterest) {
    this.corpusInterest = corpusInterest;
  }

  public String getHestatus() {
    return hestatus;
  }

  public void setHestatus(String hestatus) {
    this.hestatus = hestatus;
  }

  public BigDecimal getInterest() {
    return interest;
  }

  public void setInterest(BigDecimal interest) {
    this.interest = interest;
  }

  public String getOperator() {
    return operator;
  }

  public void setOperator(String operator) {
    this.operator = operator;
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

  public int getPersoncount() {
    return personcount;
  }

  public void setPersoncount(int personcount) {
    this.personcount = personcount;
  }

  public String getPickdate() {
    return pickdate;
  }

  public void setPickdate(String pickdate) {
    this.pickdate = pickdate;
  }

  public String getPistatus() {
    return pistatus;
  }

  public void setPistatus(String pistatus) {
    this.pistatus = pistatus;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

}
