package org.xpup.hafmis.sysloan.loanapply.endorsecontract.dto;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EndorsecontractTcDTO {
  private List list = new ArrayList();
  private Map map = new HashMap();//身份证下拉框map
  
  private String id = "";//
  private String contractId = "";//合同ID
  private String debitter = "";//借款人姓名 PL110 
  private String impawnContractId = "";//质押合同编号
  private String assistantOrgName = "";//担保公司名称
  private String impawnPerson = "";//质押人
  private String office = "";//质押权人（即××中心）
  private String impawnMatterName = "";//质押物名称
  private String impawnValue = "";//质押物价值
  private String paperPersonName = "";//所有权人姓名
  private String cardKind = "";//所有权人证件类型
  private String carNum = "";//所有权人证件号码
  private String paperNum = "";//所有权证编号
  private String paperName = "";//所有权证名称
  private String tel = "";//所有权人固定电话
  private String mobile = "";//所有权人移动电话
  private String status = "";//合同状态
  
  private String isButtonForbid = "";//按钮是否禁用属性　0禁止　1可用
  private String paramValue = "";//参数值AB or BA
  private String isComeFormAdd = "";//是否从添加过来
  private String isReadOnly = "";//是否只读
  public String getAssistantOrgName() {
    return assistantOrgName;
  }
  public void setAssistantOrgName(String assistantOrgName) {
    this.assistantOrgName = assistantOrgName;
  }
  public String getCardKind() {
    return cardKind;
  }
  public void setCardKind(String cardKind) {
    this.cardKind = cardKind;
  }
  public String getCarNum() {
    return carNum;
  }
  public void setCarNum(String carNum) {
    this.carNum = carNum;
  }
  public String getContractId() {
    return contractId;
  }
  public void setContractId(String contractId) {
    this.contractId = contractId;
  }
  public String getDebitter() {
    return debitter;
  }
  public void setDebitter(String debitter) {
    this.debitter = debitter;
  }
  public String getId() {
    return id;
  }
  public void setId(String id) {
    this.id = id;
  }
  public String getImpawnContractId() {
    return impawnContractId;
  }
  public void setImpawnContractId(String impawnContractId) {
    this.impawnContractId = impawnContractId;
  }
  public String getImpawnMatterName() {
    return impawnMatterName;
  }
  public void setImpawnMatterName(String impawnMatterName) {
    this.impawnMatterName = impawnMatterName;
  }
  public String getImpawnPerson() {
    return impawnPerson;
  }
  public void setImpawnPerson(String impawnPerson) {
    this.impawnPerson = impawnPerson;
  }
  public String getImpawnValue() {
    return impawnValue;
  }
  public void setImpawnValue(String impawnValue) {
    this.impawnValue = impawnValue;
  }
  public String getIsButtonForbid() {
    return isButtonForbid;
  }
  public void setIsButtonForbid(String isButtonForbid) {
    this.isButtonForbid = isButtonForbid;
  }
  public String getIsComeFormAdd() {
    return isComeFormAdd;
  }
  public void setIsComeFormAdd(String isComeFormAdd) {
    this.isComeFormAdd = isComeFormAdd;
  }
  public String getIsReadOnly() {
    return isReadOnly;
  }
  public void setIsReadOnly(String isReadOnly) {
    this.isReadOnly = isReadOnly;
  }
  public List getList() {
    return list;
  }
  public void setList(List list) {
    this.list = list;
  }
  public Map getMap() {
    return map;
  }
  public void setMap(Map map) {
    this.map = map;
  }
  public String getMobile() {
    return mobile;
  }
  public void setMobile(String mobile) {
    this.mobile = mobile;
  }
  public String getOffice() {
    return office;
  }
  public void setOffice(String office) {
    this.office = office;
  }
  public String getPaperName() {
    return paperName;
  }
  public void setPaperName(String paperName) {
    this.paperName = paperName;
  }
  public String getPaperNum() {
    return paperNum;
  }
  public void setPaperNum(String paperNum) {
    this.paperNum = paperNum;
  }
  public String getPaperPersonName() {
    return paperPersonName;
  }
  public void setPaperPersonName(String paperPersonName) {
    this.paperPersonName = paperPersonName;
  }
  public String getParamValue() {
    return paramValue;
  }
  public void setParamValue(String paramValue) {
    this.paramValue = paramValue;
  }
  public String getStatus() {
    return status;
  }
  public void setStatus(String status) {
    this.status = status;
  }
  public String getTel() {
    return tel;
  }
  public void setTel(String tel) {
    this.tel = tel;
  }
  
}
