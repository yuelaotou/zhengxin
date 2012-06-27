package org.xpup.hafmis.sysloan.querystatistics.datareadyquery.assistantorg.dto;

public class AssistantOrgQueryDTO {
  private Integer id = null;// 协作单位编号

  private String assistantOrgName = "";// 协作单位名称

  private String assistantOrgAddr = "";// 协作单位地址

  private String arear = "";// 所属地区

  private String paybank = "";// 开户银行

  private String contactPrsn = "";// 联系人

  private String contactTel = "";// 联系电话

  private String assistantOrgType = "";// 协作单位类型
  
  private String photoUrl = "";

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

  public String getPhotoUrl() {
    return photoUrl;
  }

  public void setPhotoUrl(String photoUrl) {
    this.photoUrl = photoUrl;
  }

}
