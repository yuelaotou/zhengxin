package org.xpup.hafmis.syscollection.querystatistics.paymentpickstatistics.collectionstatistics.form;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.xpup.hafmis.syscollection.querystatistics.paymentpickstatistics.collectionstatistics.dto.CollectionstatisticsExportDTO;
/**
 * 
 * @author 于庆丰
 *2007-07-30
 */
public class CollectionstatisticsAF extends ActionForm{
  private CollectionstatisticsExportDTO collectionstatisticsExportDTO =  new CollectionstatisticsExportDTO();
  private List collBankList=new ArrayList();
  private List officeList=new ArrayList();
  
  private Map orgCharacterMap = new HashMap();//下拉框--单位性质
  private Map deptMap = new HashMap();//下拉框--主管部门
  private Map ragionMap = new HashMap();//下拉框--所在地区
  
  private Map map = null;
  private Map otherMap = null;
  private List list = null;
  private List alllist = null;
  private String officeCode = "";//办事处
  private String collectionBank = "";//归集银行
  private String orgId = "";//单位编号
  private String orgName = "";// 单位名称
  private String orgCharacter = "";//单位性质
  private String deptInCharge = "";//主管部门
  private String startDate = "";//开始日期
  private String endDate = "";//结束日期
  private String region = "";//所在地区
  private BigDecimal lastMonthCollect = new BigDecimal(0.00);//上月归集
  private BigDecimal monthPay = new BigDecimal(0.00);//本月正常汇缴
  private BigDecimal orgAddPay = new BigDecimal(0.00);//本月单位补缴
  private BigDecimal personAddPay = new BigDecimal(0.00);//本月个人补缴
  private BigDecimal orgOverPay = new BigDecimal(0.00);//本月单位挂账
  private BigDecimal chgPay = new BigDecimal(0.00);//本月调缴存
  private BigDecimal thisMonthCollect = new BigDecimal(0.00);//本月归集
  private String rate = "";//比率
  
  public BigDecimal getChgPay() {
    return chgPay;
  }
  public void setChgPay(BigDecimal chgPay) {
    this.chgPay = chgPay;
  }
  public BigDecimal getLastMonthCollect() {
    return lastMonthCollect;
  }
  public void setLastMonthCollect(BigDecimal lastMonthCollect) {
    this.lastMonthCollect = lastMonthCollect;
  }
  public BigDecimal getMonthPay() {
    return monthPay;
  }
  public void setMonthPay(BigDecimal monthPay) {
    this.monthPay = monthPay;
  }
  public BigDecimal getOrgAddPay() {
    return orgAddPay;
  }
  public void setOrgAddPay(BigDecimal orgAddPay) {
    this.orgAddPay = orgAddPay;
  }
  public BigDecimal getOrgOverPay() {
    return orgOverPay;
  }
  public void setOrgOverPay(BigDecimal orgOverPay) {
    this.orgOverPay = orgOverPay;
  }
  public BigDecimal getPersonAddPay() {
    return personAddPay;
  }
  public void setPersonAddPay(BigDecimal personAddPay) {
    this.personAddPay = personAddPay;
  }

  public String getRate() {
    return rate;
  }
  public void setRate(String rate) {
    this.rate = rate;
  }
  public BigDecimal getThisMonthCollect() {
    return thisMonthCollect;
  }
  public void setThisMonthCollect(BigDecimal thisMonthCollect) {
    this.thisMonthCollect = thisMonthCollect;
  }
  public String getCollectionBank() {
    return collectionBank;
  }
  public void setCollectionBank(String collectionBank) {
    this.collectionBank = collectionBank;
  }
  public String getDeptInCharge() {
    return deptInCharge;
  }
  public void setDeptInCharge(String deptInCharge) {
    this.deptInCharge = deptInCharge;
  }
  public String getEndDate() {
    return endDate;
  }
  public void setEndDate(String endDate) {
    this.endDate = endDate;
  }
  public String getOfficeCode() {
    return officeCode;
  }
  public void setOfficeCode(String officeCode) {
    this.officeCode = officeCode;
  }
  public String getOrgCharacter() {
    return orgCharacter;
  }
  public void setOrgCharacter(String orgCharacter) {
    this.orgCharacter = orgCharacter;
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
  public String getRegion() {
    return region;
  }
  public void setRegion(String region) {
    this.region = region;
  }
  public String getStartDate() {
    return startDate;
  }
  public void setStartDate(String startDate) {
    this.startDate = startDate;
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
  public Map getOtherMap() {
    return otherMap;
  }
  public void setOtherMap(Map otherMap) {
    this.otherMap = otherMap;
  }
  public List getAlllist() {
    return alllist;
  }
  public void setAlllist(List alllist) {
    this.alllist = alllist;
  }
  public void reset(ActionMapping mapping, HttpServletRequest request) {
    // TODO Auto-generated method stub
    super.reset(mapping, request);
   
    this.officeCode = "";//办事处
    this.collectionBank = "";//归集银行
    this.orgId = "";//单位编号
    this.orgName = "";// 单位名称
    this.orgCharacter = "";//单位性质
    this.deptInCharge = "";//主管部门
    this.startDate = "";//开始日期
    this.endDate = "";//结束日期
    this.region = "";//所在地区
   
  }
  public List getCollBankList() {
    return collBankList;
  }
  public void setCollBankList(List collBankList) {
    this.collBankList = collBankList;
  }
  public List getOfficeList() {
    return officeList;
  }
  public void setOfficeList(List officeList) {
    this.officeList = officeList;
  }
  public CollectionstatisticsExportDTO getCollectionstatisticsExportDTO() {
    return collectionstatisticsExportDTO;
  }
  public void setCollectionstatisticsExportDTO(
      CollectionstatisticsExportDTO collectionstatisticsExportDTO) {
    this.collectionstatisticsExportDTO = collectionstatisticsExportDTO;
  }
  public Map getDeptMap() {
    return deptMap;
  }
  public void setDeptMap(Map deptMap) {
    this.deptMap = deptMap;
  }
  public Map getOrgCharacterMap() {
    return orgCharacterMap;
  }
  public void setOrgCharacterMap(Map orgCharacterMap) {
    this.orgCharacterMap = orgCharacterMap;
  }
  public Map getRagionMap() {
    return ragionMap;
  }
  public void setRagionMap(Map ragionMap) {
    this.ragionMap = ragionMap;
  }
  
  
}
