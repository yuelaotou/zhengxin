package org.xpup.hafmis.syscollection.tranmng.tranout.dto;



public class TranoutInfoDTO {

  
  private String orgOutid; 
  private String orgOutName;
  private String orgInid; 
  private String orgInName;
  private String noteNum;
  
  private String empId ;
  private String empName ;
  private String card_king;
  private String card_num ;
  private String Issettinrest ;
  private String tranReason;
  
  
  public String getCard_king() {
    return card_king;
  }

  public void setCard_king(String card_king) {
    this.card_king = card_king;
  }

  public String getCard_num() {
    return card_num;
  }

  public void setCard_num(String card_num) {
    this.card_num = card_num;
  }

  public String getEmpId() {
    return empId;
  }

  public void setEmpId(String empId) {
    this.empId = empId;
  }

  public String getEmpName() {
    return empName;
  }


  public void setEmpName(String empName) {
    this.empName = empName;
  }


  public String getIssettinrest() {
    return Issettinrest;
  }


  public void setIssettinrest(String issettinrest) {
    Issettinrest = issettinrest;
  }


  public String getNoteNum() {
    return noteNum;
  }


  public void setNoteNum(String noteNum) {
    this.noteNum = noteNum;
  }


  public String getOrgInid() {
    return orgInid;
  }

  public void setOrgInid(String orgInid) {
    this.orgInid = orgInid;
  }


  public String getOrgInName() {
    return orgInName;
  }


  public void setOrgInName(String orgInName) {
    this.orgInName = orgInName;
  }

  public String getOrgOutid() {
    return orgOutid;
  }


  public void setOrgOutid(String orgOutid) {
    this.orgOutid = orgOutid;
  }


  public String getOrgOutName() {
    return orgOutName;
  }


  public void setOrgOutName(String orgOutName) {
    this.orgOutName = orgOutName;
  }


  public String getInfo(String s) {
    // TODO Auto-generated method stub
    return null;
  }

  public String getTranReason() {
    return tranReason;
  }

  public void setTranReason(String tranReason) {
    this.tranReason = tranReason;
  }

}
