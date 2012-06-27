package org.xpup.hafmis.sysloan.querystatistics.datareadyquery.assistantorg.dto;

import java.math.BigDecimal;

public class AssistantorgQueryTbDTO {
  private String assistantOrgId = "";// 协作单位编号

  private String assistantOrgName = "";// 协作单位名称

  private String artfclprsn = "";// 法人代表

  private String code = "";// 组织机构代码

  private String assistantOrgAddr = "";// 协作单位地址

  private String basedDate = "";// 成立日期

  private String artfclprsnCardKind = "";// 法人证件类型

  private String artfclprsnCardNum = "";// 法人证件号码

  private String allowDept = "";// 批准机关

  private String allowId = "";// 批准文号

  private String agreementStartDate = "";// 协议签订日期

  private String agreementEndDate = "";// 协议到期日期

  private BigDecimal registerFund = new BigDecimal(0.00);// 注册资金

  private String paybank = "";// 开户银行

  private String payacc = "";// 开户行账号

  private String contactPrsn = "";// 联系人

  private String contactTel = "";// 联系电话

  private String business = "";// 职务

  private String remark = "";// 备注

  private String arear = "";// 所属地区

  private String assistantOrgType = "";// 协作单位类型

  public String getAgreementEndDate() {
    return agreementEndDate;
  }

  public void setAgreementEndDate(String agreementEndDate) {
    this.agreementEndDate = agreementEndDate;
  }

  public String getAgreementStartDate() {
    return agreementStartDate;
  }

  public void setAgreementStartDate(String agreementStartDate) {
    this.agreementStartDate = agreementStartDate;
  }

  public String getAllowDept() {
    return allowDept;
  }

  public void setAllowDept(String allowDept) {
    this.allowDept = allowDept;
  }

  public String getAllowId() {
    return allowId;
  }

  public void setAllowId(String allowId) {
    this.allowId = allowId;
  }

  public String getArear() {
    return arear;
  }

  public void setArear(String arear) {
    this.arear = arear;
  }

  public String getArtfclprsn() {
    return artfclprsn;
  }

  public void setArtfclprsn(String artfclprsn) {
    this.artfclprsn = artfclprsn;
  }

  public String getArtfclprsnCardKind() {
    return artfclprsnCardKind;
  }

  public void setArtfclprsnCardKind(String artfclprsnCardKind) {
    this.artfclprsnCardKind = artfclprsnCardKind;
  }

  public String getArtfclprsnCardNum() {
    return artfclprsnCardNum;
  }

  public void setArtfclprsnCardNum(String artfclprsnCardNum) {
    this.artfclprsnCardNum = artfclprsnCardNum;
  }

  public String getAssistantOrgAddr() {
    return assistantOrgAddr;
  }

  public void setAssistantOrgAddr(String assistantOrgAddr) {
    this.assistantOrgAddr = assistantOrgAddr;
  }

  public String getAssistantOrgId() {
    return assistantOrgId;
  }

  public void setAssistantOrgId(String assistantOrgId) {
    this.assistantOrgId = assistantOrgId;
  }

  public String getAssistantOrgName() {
    return assistantOrgName;
  }

  public void setAssistantOrgName(String assistantOrgName) {
    this.assistantOrgName = assistantOrgName;
  }

  public String getAssistantOrgType() {
    return assistantOrgType;
  }

  public void setAssistantOrgType(String assistantOrgType) {
    this.assistantOrgType = assistantOrgType;
  }

  public String getBasedDate() {
    return basedDate;
  }

  public void setBasedDate(String basedDate) {
    this.basedDate = basedDate;
  }

  public String getBusiness() {
    return business;
  }

  public void setBusiness(String business) {
    this.business = business;
  }

  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
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

  public String getPayacc() {
    return payacc;
  }

  public void setPayacc(String payacc) {
    this.payacc = payacc;
  }

  public String getPaybank() {
    return paybank;
  }

  public void setPaybank(String paybank) {
    this.paybank = paybank;
  }

  public BigDecimal getRegisterFund() {
    return registerFund;
  }

  public void setRegisterFund(BigDecimal registerFund) {
    this.registerFund = registerFund;
  }

  public String getRemark() {
    return remark;
  }

  public void setRemark(String remark) {
    this.remark = remark;
  }
}
