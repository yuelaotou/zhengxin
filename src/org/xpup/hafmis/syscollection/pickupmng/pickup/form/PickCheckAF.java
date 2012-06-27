package org.xpup.hafmis.syscollection.pickupmng.pickup.form;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.apache.struts.action.ActionForm;

public class PickCheckAF extends ActionForm {

  private static final long serialVersionUID = 1L;

  private String orgid = "";

  private String orgname = "";

  private String begdate = "";

  private String enddate = "";

  private String checkbegdate = "";

  private String checkenddate = "";

  private String ischecked = "";

  private int person = 0;

  private BigDecimal corpus = new BigDecimal(0.00);

  private BigDecimal interest = new BigDecimal(0.00);

  private BigDecimal corpusInterest = new BigDecimal(0.00);

  private List list = new ArrayList();

  public String getBegdate() {
    return begdate;
  }

  public void setBegdate(String begdate) {
    this.begdate = begdate;
  }

  public String getEnddate() {
    return enddate;
  }

  public void setEnddate(String enddate) {
    this.enddate = enddate;
  }

  public String getIschecked() {
    return ischecked;
  }

  public void setIschecked(String ischecked) {
    this.ischecked = ischecked;
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

  public List getList() {
    return list;
  }

  public void setList(List list) {
    this.list = list;
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

  public BigDecimal getInterest() {
    return interest;
  }

  public void setInterest(BigDecimal interest) {
    this.interest = interest;
  }

  public int getPerson() {
    return person;
  }

  public void setPerson(int person) {
    this.person = person;
  }

  public String getCheckbegdate() {
    return checkbegdate;
  }

  public void setCheckbegdate(String checkbegdate) {
    this.checkbegdate = checkbegdate;
  }

  public String getCheckenddate() {
    return checkenddate;
  }

  public void setCheckenddate(String checkenddate) {
    this.checkenddate = checkenddate;
  }

}
