package org.xpup.hafmis.sysloan.dataready.assistantorg.dto;

public class AssistantOrgDTO {
  private Integer id=null;
  private String assistantOrgName="";
  private String assistantOrgAddr="";
  private String arear="";
  private String paybank="";
  private String contactPrsn="";
  private String contactTel="";
  private String assistantOrgType="";
  private String photoUrl = "";
  public String getPhotoUrl() {
    return photoUrl;
  }
  public void setPhotoUrl(String photoUrl) {
    this.photoUrl = photoUrl;
  }
  public String getAssistantOrgType() {
    return assistantOrgType;
  }
  public void setAssistantOrgType(String assistantOrgType) {
    this.assistantOrgType = assistantOrgType;
  }
  public String getArear() {
    return arear;
  }
  public void setArear(String arear) {
    this.arear = arear;
  }
  public String getAssistantOrgAddr() {
    return assistantOrgAddr;
  }
  public void setAssistantOrgAddr(String assistantOrgAddr) {
    this.assistantOrgAddr = assistantOrgAddr;
  }
  public String getAssistantOrgName() {
    return assistantOrgName;
  }
  public void setAssistantOrgName(String assistantOrgName) {
    this.assistantOrgName = assistantOrgName;
  }
  public String getContactPrsn() {
    return contactPrsn;
  }
  public void setContactPrsn(String contactPrsn) {
    this.contactPrsn = contactPrsn;
  }
  public String getContactTel() {
    return contactTel;
  }
  public void setContactTel(String contactTel) {
    this.contactTel = contactTel;
  }
  public Integer getId() {
    return id;
  }
  public void setId(Integer id) {
    this.id = id;
  }
  public String getPaybank() {
    return paybank;
  }
  public void setPaybank(String paybank) {
    this.paybank = paybank;
  }

}
