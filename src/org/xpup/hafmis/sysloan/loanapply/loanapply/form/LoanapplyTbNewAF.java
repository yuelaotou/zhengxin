/*
 * Generated by MyEclipse Struts
 * Template path: templates/java/JavaClass.vtl
 */
package org.xpup.hafmis.sysloan.loanapply.loanapply.form;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

/**
 * MyEclipse Struts Creation date: 09-26-2007 XDoclet definition:
 * 
 * @struts.form name="loanapplytbnewAF"
 */

// 辅助借款人信息
public class LoanapplyTbNewAF extends ActionForm {

  private String isNeedDel;// 用于判断是否走过扫描

  private String office;// 借款人的办事处(用来判断前提条件)

  private String photoUrl;// 用于放置路径的

  private String temp_types;// 当没有辅助借款人信息是，按钮如何禁用

  private String coodm_type;// 判断下面四个按钮禁用的

  private String org_Id;// 隐藏域

  private String coodm;

  private String maxauxiliaryid;

  private String temp_tes;

  private String typenum;

  private String contractId;

  private String borrowerName;

  private String empId;

  private String name;

  private String relation;

  private String relationStatus;

  private String sex;

  private String sexhidden;

  private Map sexMap = new HashMap();

  private Map relationMap = new HashMap();

  private String cardKind;

  private String cardKindhidden;

  private Map cardkingMap = new HashMap();

  private String cardNum;

  private String birthday;

  private String age;

  private String business;

  private String title;

  private String nation;

  private String nativePlace;

  private String marriageSt;

  private String degree;

  private String homeAddr;

  private String homeMail;

  private String houseTel;

  private String homeMobile;

  private String orgId;

  private String orgName;

  private String orgTel;

  private String orgMail = "";

  private String orgAddr;

  private String accBlnce;

  private String monthSalary;

  private String monthPay;

  private String empSt;

  private String bgnDate;

  private String endDate;

  private List list = new ArrayList();

  private String pay_oldyear;

  private String count;

  private String zdjzqje;// 最大可支取金额

  private String orgRate = "";

  private String empRate = "";
  
  private String loanType="";

  public String getCount() {
    return count;
  }

  public void setCount(String count) {
    this.count = count;
  }

  public String getAccBlnce() {
    return accBlnce;
  }

  public void setAccBlnce(String accBlnce) {
    this.accBlnce = accBlnce;
  }

  public String getAge() {
    return age;
  }

  public void setAge(String age) {
    this.age = age;
  }

  public String getBgnDate() {
    return bgnDate;
  }

  public void setBgnDate(String bgnDate) {
    this.bgnDate = bgnDate;
  }

  public String getBirthday() {
    return birthday;
  }

  public void setBirthday(String birthday) {
    this.birthday = birthday;
  }

  public String getBorrowerName() {
    return borrowerName;
  }

  public void setBorrowerName(String borrowerName) {
    this.borrowerName = borrowerName;
  }

  public String getBusiness() {
    return business;
  }

  public void setBusiness(String business) {
    this.business = business;
  }

  public String getCardKind() {
    return cardKind;
  }

  public void setCardKind(String cardKind) {
    this.cardKind = cardKind;
  }

  public Map getCardkingMap() {
    return cardkingMap;
  }

  public void setCardkingMap(Map cardkingMap) {
    this.cardkingMap = cardkingMap;
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

  public String getDegree() {
    return degree;
  }

  public void setDegree(String degree) {
    this.degree = degree;
  }

  public String getEmpId() {
    return empId;
  }

  public void setEmpId(String empId) {
    this.empId = empId;
  }

  public String getEmpSt() {
    return empSt;
  }

  public void setEmpSt(String empSt) {
    this.empSt = empSt;
  }

  public String getEndDate() {
    return endDate;
  }

  public void setEndDate(String endDate) {
    this.endDate = endDate;
  }

  public String getHomeAddr() {
    return homeAddr;
  }

  public void setHomeAddr(String homeAddr) {
    this.homeAddr = homeAddr;
  }

  public String getHomeMail() {
    return homeMail;
  }

  public void setHomeMail(String homeMail) {
    this.homeMail = homeMail;
  }

  public String getHomeMobile() {
    return homeMobile;
  }

  public void setHomeMobile(String homeMobile) {
    this.homeMobile = homeMobile;
  }

  public String getHouseTel() {
    return houseTel;
  }

  public void setHouseTel(String houseTel) {
    this.houseTel = houseTel;
  }

  public List getList() {
    return list;
  }

  public void setList(List list) {
    this.list = list;
  }

  public String getMarriageSt() {
    return marriageSt;
  }

  public void setMarriageSt(String marriageSt) {
    this.marriageSt = marriageSt;
  }

  public String getMonthPay() {
    return monthPay;
  }

  public void setMonthPay(String monthPay) {
    this.monthPay = monthPay;
  }

  public String getMonthSalary() {
    return monthSalary;
  }

  public void setMonthSalary(String monthSalary) {
    this.monthSalary = monthSalary;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getNation() {
    return nation;
  }

  public void setNation(String nation) {
    this.nation = nation;
  }

  public String getNativePlace() {
    return nativePlace;
  }

  public void setNativePlace(String nativePlace) {
    this.nativePlace = nativePlace;
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

  public String getRelation() {
    return relation;
  }

  public void setRelation(String relation) {
    this.relation = relation;
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

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public ActionErrors validate(ActionMapping mapping, HttpServletRequest request) {
    // TODO Auto-generated method stub
    return null;
  }

  /**
   * Method reset
   * 
   * @param mapping
   * @param request
   */
  public void reset(ActionMapping mapping, HttpServletRequest request) {
    // TODO Auto-generated method stub
  }

  public String getTypenum() {
    return typenum;
  }

  public void setTypenum(String typenum) {
    this.typenum = typenum;
  }

  public String getTemp_tes() {
    return temp_tes;
  }

  public void setTemp_tes(String temp_tes) {
    this.temp_tes = temp_tes;
  }

  public String getMaxauxiliaryid() {
    return maxauxiliaryid;
  }

  public void setMaxauxiliaryid(String maxauxiliaryid) {
    this.maxauxiliaryid = maxauxiliaryid;
  }

  public String getCoodm() {
    return coodm;
  }

  public void setCoodm(String coodm) {
    this.coodm = coodm;
  }

  public String getOrg_Id() {
    return org_Id;
  }

  public void setOrg_Id(String org_Id) {
    this.org_Id = org_Id;
  }

  public String getCoodm_type() {
    return coodm_type;
  }

  public void setCoodm_type(String coodm_type) {
    this.coodm_type = coodm_type;
  }

  public String getCardKindhidden() {
    return cardKindhidden;
  }

  public void setCardKindhidden(String cardKindhidden) {
    this.cardKindhidden = cardKindhidden;
  }

  public String getSexhidden() {
    return sexhidden;
  }

  public void setSexhidden(String sexhidden) {
    this.sexhidden = sexhidden;
  }

  public String getTemp_types() {
    return temp_types;
  }

  public void setTemp_types(String temp_types) {
    this.temp_types = temp_types;
  }

  public String getIsNeedDel() {
    return isNeedDel;
  }

  public void setIsNeedDel(String isNeedDel) {
    this.isNeedDel = isNeedDel;
  }

  public String getPhotoUrl() {
    return photoUrl;
  }

  public void setPhotoUrl(String photoUrl) {
    this.photoUrl = photoUrl;
  }

  public String getZdjzqje() {
    return zdjzqje;
  }

  public void setZdjzqje(String zdjzqje) {
    this.zdjzqje = zdjzqje;
  }

  public String getEmpRate() {
    return empRate;
  }

  public void setEmpRate(String empRate) {
    this.empRate = empRate;
  }

  public String getOrgRate() {
    return orgRate;
  }

  public void setOrgRate(String orgRate) {
    this.orgRate = orgRate;
  }

  public String getPay_oldyear() {
    return pay_oldyear;
  }

  public void setPay_oldyear(String pay_oldyear) {
    this.pay_oldyear = pay_oldyear;
  }

  public String getOffice() {
    return office;
  }

  public void setOffice(String office) {
    this.office = office;
  }

  public Map getRelationMap() {
    return relationMap;
  }

  public void setRelationMap(Map relationMap) {
    this.relationMap = relationMap;
  }

  public String getRelationStatus() {
    return relationStatus;
  }

  public void setRelationStatus(String relationStatus) {
    this.relationStatus = relationStatus;
  }

  public String getLoanType() {
    return loanType;
  }

  public void setLoanType(String loanType) {
    this.loanType = loanType;
  }

}