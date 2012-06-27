package org.xpup.hafmis.syscollection.pickupmng.pickup.dto;

import java.math.BigDecimal;

public class OrgSearchConditionDTO {// 单位的查询条件
  private String state;

  private String orgId;

  private String orgName;

  private String noteNumber;

  private String docNumber;

  private String pickDate;

  private String pickType;

  private String pickDate_end;

  private String start;

  private String end;

  private String select;

  private String collectionBank;

  // private BigDecimal reason;
  private String reason;

  public String getDocNumber() {
    return docNumber;
  }

  public void setDocNumber(String docNumber) {
    this.docNumber = docNumber;
  }

  public String getEnd() {
    return end;
  }

  public void setEnd(String end) {
    this.end = end;
  }

  public String getNoteNumber() {
    return noteNumber;
  }

  public void setNoteNumber(String noteNumber) {
    this.noteNumber = noteNumber;
  }

  public String getOrgId() {
    return orgId;
  }

  public void setOrgId(String orgId) {
    this.orgId = orgId;
  }

  public String getOrgName() {
    return orgName;
  }

  public void setOrgName(String orgName) {
    this.orgName = orgName;
  }

  public String getPickDate() {
    return pickDate;
  }

  public void setPickDate(String pickDate) {
    this.pickDate = pickDate;
  }

  public String getStart() {
    return start;
  }

  public void setStart(String start) {
    this.start = start;
  }

  public String getState() {
    return state;
  }

  public void setState(String state) {
    this.state = state;
  }

  public String getSelect() {
    return select;
  }

  public void setSelect(String select) {
    this.select = select;
  }

  // public BigDecimal getReason() {
  // return reason;
  // }
  // public void setReason(BigDecimal reason) {
  // this.reason = reason;
  // }
  public void setReason(String reason) {
    this.reason = reason;
  }

  public String getReason() {
    return reason;
  }

  public String getPickDate_end() {
    return pickDate_end;
  }

  public void setPickDate_end(String pickDate_end) {
    this.pickDate_end = pickDate_end;
  }

  public String getCollectionBank() {
    return collectionBank;
  }

  public void setCollectionBank(String collectionBank) {
    this.collectionBank = collectionBank;
  }

  public String getPickType() {
    return pickType;
  }

  public void setPickType(String pickType) {
    this.pickType = pickType;
  }

}
