package org.xpup.hafmis.syscollection.querystatistics.paymentpickstatistics.pastyearscontrast.form;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.struts.action.ActionForm;
import org.xpup.hafmis.syscollection.querystatistics.paymentpickstatistics.pastyearscontrast.dto.PastyearscontrasDTO;
/**
 * 
 * @author 于庆丰
 *
 */
public class PastyearscontrasAF extends ActionForm{
  private List collBankList=new ArrayList();//下拉框--归集银行
  private List officeList=new ArrayList();//下拉框--办事处
  private Map orgCharacterMap = new HashMap();//下拉框--单位性质
  private Map deptMap = new HashMap();//下拉框--主管部门
  private Map ragionMap = new HashMap();//下拉框--所在地区
  
  private String officeCode = "";
  private String collectionBankId = "";
  private String orgChracter = "";//
  private String dept = "";
  private String ragion = "";
  private String startDate = "";
  private String endDate = "";
  
  private String type = "";
  
  private String collectionBank = "";
  
  private  PastyearscontrasDTO pastyearscontrasDTO = new PastyearscontrasDTO();
  
  public PastyearscontrasDTO getPastyearscontrasDTO() {
    return pastyearscontrasDTO;
  }
  public void setPastyearscontrasDTO(PastyearscontrasDTO pastyearscontrasDTO) {
    this.pastyearscontrasDTO = pastyearscontrasDTO;
  }
  public List getCollBankList() {
    return collBankList;
  }
  public void setCollBankList(List collBankList) {
    this.collBankList = collBankList;
  }
  public String getDept() {
    return dept;
  }
  public void setDept(String dept) {
    this.dept = dept;
  }
  public Map getDeptMap() {
    return deptMap;
  }
  public void setDeptMap(Map deptMap) {
    this.deptMap = deptMap;
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
  public List getOfficeList() {
    return officeList;
  }
  public void setOfficeList(List officeList) {
    this.officeList = officeList;
  }
  public Map getOrgCharacterMap() {
    return orgCharacterMap;
  }
  public void setOrgCharacterMap(Map orgCharacterMap) {
    this.orgCharacterMap = orgCharacterMap;
  }
  public String getOrgChracter() {
    return orgChracter;
  }
  public void setOrgChracter(String orgChracter) {
    this.orgChracter = orgChracter;
  }
  public String getRagion() {
    return ragion;
  }
  public void setRagion(String ragion) {
    this.ragion = ragion;
  }
  public Map getRagionMap() {
    return ragionMap;
  }
  public void setRagionMap(Map ragionMap) {
    this.ragionMap = ragionMap;
  }
  public String getStartDate() {
    return startDate;
  }
  public void setStartDate(String startDate) {
    this.startDate = startDate;
  }
  public String getType() {
    return type;
  }
  public void setType(String type) {
    this.type = type;
  }
  public String getCollectionBankId() {
    return collectionBankId;
  }
  public void setCollectionBankId(String collectionBankId) {
    this.collectionBankId = collectionBankId;
  }
  public String getCollectionBank() {
    return collectionBank;
  }
  public void setCollectionBank(String collectionBank) {
    this.collectionBank = collectionBank;
  }
  
 
 
}
