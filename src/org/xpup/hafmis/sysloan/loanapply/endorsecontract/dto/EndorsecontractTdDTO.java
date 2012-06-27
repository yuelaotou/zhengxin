package org.xpup.hafmis.sysloan.loanapply.endorsecontract.dto;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EndorsecontractTdDTO {
  private List list = new ArrayList();
  private Map cardMap = new HashMap();//身份证下拉框map
  private Map sexMap = new HashMap();//性别下拉框map
  
  private String id = "";//
  private String contractId = "";//合同ID
  private String debitter = "";//借款人姓名 PL110 
  private String empId = "";//职工编号
  private String empName = "";//职工姓名
  private String cardKind = "";//证件类型
  private String cardNum = "";//证件号码
  private String sex = "";//性别
  private String birthday = "";//出生日期
  private String salary = "";//月工资额
  private String monthPay = "";//月缴存额
  private String balance = "";//账户余额
  private String empSt = "";//账户状态
  private String tel = "";//固定电话
  private String mobile = "";//行动电话
  private String homeTel = "";//家庭电话
  private String homeAddr = "";//家庭住址
  private String homeMai = "";//家庭邮编
  private String orgId = "";//单位编号
  private String orgName = "";//单位名称
  private String orgAddr = "";//单位地址
  private String orgTel = "";//单位电话
  private String orgMail = "";//单位邮政编号
  private String status = "";//合同状态
  
  private String hiddenEmpId = "";//隐藏显示EMPID
  private String isButtonForbid = "";//按钮是否禁用属性　0禁止　1可用
  private String paramValue = "";//参数值AB or BA
  private String isComeFormAdd = "";//是否从添加过来
  private String isReadOnly = "";//是否只读
  public String getBalance() {
    return balance;
  }
  public void setBalance(String balance) {
    this.balance = balance;
  }
  public String getBirthday() {
    return birthday;
  }
  public void setBirthday(String birthday) {
    this.birthday = birthday;
  }
  public String getCardKind() {
    return cardKind;
  }
  public void setCardKind(String cardKind) {
    this.cardKind = cardKind;
  }
  public Map getCardMap() {
    return cardMap;
  }
  public void setCardMap(Map cardMap) {
    this.cardMap = cardMap;
  }
  public String getCardNum() {
    return cardNum;
  }
  public void setCardNum(String cardNum) {
    this.cardNum = cardNum;
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
  public String getEmpSt() {
    return empSt;
  }
  public void setEmpSt(String empSt) {
    this.empSt = empSt;
  }
  public String getHiddenEmpId() {
    return hiddenEmpId;
  }
  public void setHiddenEmpId(String hiddenEmpId) {
    this.hiddenEmpId = hiddenEmpId;
  }
  public String getHomeAddr() {
    return homeAddr;
  }
  public void setHomeAddr(String homeAddr) {
    this.homeAddr = homeAddr;
  }
  public String getHomeMai() {
    return homeMai;
  }
  public void setHomeMai(String homeMai) {
    this.homeMai = homeMai;
  }
  public String getHomeTel() {
    return homeTel;
  }
  public void setHomeTel(String homeTel) {
    this.homeTel = homeTel;
  }
  public String getId() {
    return id;
  }
  public void setId(String id) {
    this.id = id;
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
  public String getMobile() {
    return mobile;
  }
  public void setMobile(String mobile) {
    this.mobile = mobile;
  }
  public String getMonthPay() {
    return monthPay;
  }
  public void setMonthPay(String monthPay) {
    this.monthPay = monthPay;
  }
  public String getOrgAddr() {
    return orgAddr;
  }
  public void setOrgAddr(String orgAddr) {
    this.orgAddr = orgAddr;
  }
  public String getOrgId() {
    return orgId;
  }
  public void setOrgId(String orgId) {
    this.orgId = orgId;
  }
  public String getOrgMail() {
    return orgMail;
  }
  public void setOrgMail(String orgMail) {
    this.orgMail = orgMail;
  }
  public String getOrgName() {
    return orgName;
  }
  public void setOrgName(String orgName) {
    this.orgName = orgName;
  }
  public String getOrgTel() {
    return orgTel;
  }
  public void setOrgTel(String orgTel) {
    this.orgTel = orgTel;
  }
  public String getParamValue() {
    return paramValue;
  }
  public void setParamValue(String paramValue) {
    this.paramValue = paramValue;
  }
  public String getSalary() {
    return salary;
  }
  public void setSalary(String salary) {
    this.salary = salary;
  }
  public String getSex() {
    return sex;
  }
  public void setSex(String sex) {
    this.sex = sex;
  }
  public Map getSexMap() {
    return sexMap;
  }
  public void setSexMap(Map sexMap) {
    this.sexMap = sexMap;
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
