package org.xpup.hafmis.syscollection.querystatistics.operationflow.orgoperationflow.form;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.struts.action.ActionForm;

public class OrgbusinessflowAF extends ActionForm {

  /**
   * 
   */
  private static final long serialVersionUID = 1L;

  private String officecode = "";

  private String bankcode = "";

  private String orgcode = "";

  private String orgname = "";

  private String notenum = "";

  private String docnum = "";

  private String bsstatus = "";

  private Map bsstatueMap = new HashMap();

  private String bstype = "";

  private Map bstypeMap = new HashMap();

  private String setMonthStart = "";

  private String setMonthEnd = "";

  private String setMoneyStart = "";

  private String setMoneyEnd = "";

  private String setpeopcountStart = "";

  private String setpeopcountEnd = "";

  private String setDirection = "";

  private Map setDirectionMap = new HashMap();

  private BigDecimal debitTotal = new BigDecimal(0.00);

  private BigDecimal creditTotal = new BigDecimal(0.00);

  private List list;

  public String getBankcode() {
    return bankcode;
  }

  public void setBankcode(String bankcode) {
    this.bankcode = bankcode;
  }

  public String getOfficecode() {
    return officecode;
  }

  public void setOfficecode(String officecode) {
    this.officecode = officecode;
  }

  public String getOrgcode() {
    return orgcode;
  }

  public void setOrgcode(String orgcode) {
    this.orgcode = orgcode;
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

  public String getDocnum() {
    return docnum;
  }

  public void setDocnum(String docnum) {
    this.docnum = docnum;
  }

  public String getNotenum() {
    return notenum;
  }

  public void setNotenum(String notenum) {
    this.notenum = notenum;
  }

  public Map getBsstatueMap() {
    return bsstatueMap;
  }

  public void setBsstatueMap(Map bsstatueMap) {
    this.bsstatueMap = bsstatueMap;
  }

  public String getBsstatus() {
    return bsstatus;
  }

  public void setBsstatus(String bsstatus) {
    this.bsstatus = bsstatus;
  }

  public String getBstype() {
    return bstype;
  }

  public void setBstype(String bstype) {
    this.bstype = bstype;
  }

  public Map getBstypeMap() {
    return bstypeMap;
  }

  public void setBstypeMap(Map bstypeMap) {
    this.bstypeMap = bstypeMap;
  }

  public String getSetDirection() {
    return setDirection;
  }

  public void setSetDirection(String setDirection) {
    this.setDirection = setDirection;
  }

  public Map getSetDirectionMap() {
    return setDirectionMap;
  }

  public void setSetDirectionMap(Map setDirectionMap) {
    this.setDirectionMap = setDirectionMap;
  }

  public String getSetMoneyEnd() {
    return setMoneyEnd;
  }

  public void setSetMoneyEnd(String setMoneyEnd) {
    this.setMoneyEnd = setMoneyEnd;
  }

  public String getSetMoneyStart() {
    return setMoneyStart;
  }

  public void setSetMoneyStart(String setMoneyStart) {
    this.setMoneyStart = setMoneyStart;
  }

  public String getSetMonthEnd() {
    return setMonthEnd;
  }

  public void setSetMonthEnd(String setMonthEnd) {
    this.setMonthEnd = setMonthEnd;
  }

  public String getSetMonthStart() {
    return setMonthStart;
  }

  public void setSetMonthStart(String setMonthStart) {
    this.setMonthStart = setMonthStart;
  }

  public String getSetpeopcountEnd() {
    return setpeopcountEnd;
  }

  public void setSetpeopcountEnd(String setpeopcountEnd) {
    this.setpeopcountEnd = setpeopcountEnd;
  }

  public String getSetpeopcountStart() {
    return setpeopcountStart;
  }

  public void setSetpeopcountStart(String setpeopcountStart) {
    this.setpeopcountStart = setpeopcountStart;
  }

  public BigDecimal getCreditTotal() {
    return creditTotal;
  }

  public void setCreditTotal(BigDecimal creditTotal) {
    this.creditTotal = creditTotal;
  }

  public BigDecimal getDebitTotal() {
    return debitTotal;
  }

  public void setDebitTotal(BigDecimal debitTotal) {
    this.debitTotal = debitTotal;
  }
}
