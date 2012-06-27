/**
 * Copy Right Information   : Goldsoft 
 * Project                  : FiveLevelQueryAF
 * @Version                 : 1.0
 * @author                  : wangy
 * 生成日期                   :2007-10-19
 **/
package org.xpup.hafmis.sysloan.querystatistics.querystatistics.fivelevelquery.form;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.apache.struts.action.ActionForm;

public class FiveLevelQueryAF extends ActionForm {

  private static final long serialVersionUID = -8833963490850405189L;

  private String mode = "";// 单选按钮

  private List list = null;

  private List printList = null;// 打印list

  private String id = null;

  private String office = ""; // 办事处

  private String loanBankName = ""; // 放款银行

  private String developerName = ""; // PL005 开发商

  private String buyhouseorgid = null; // PL114 开发商头表ID headId

  private String floorid = null;// 楼盘ID

  private String floorId = ""; // 楼盘

  private String assistantOrgId = null; // 担保公司ID

  private String assistantOrgName = ""; // 担保公司名称(协作单位名称)

  private String normalCount = null; // 正常户数

  private BigDecimal normalValue = new BigDecimal(0.00);// PL121 正常金额

  private String attentionCount = null; // 关注户数

  private BigDecimal attentionValue = new BigDecimal(0.00);// PL122 关注金额

  private String secondaryCount = null; // 次级户数

  private BigDecimal secondaryValue = new BigDecimal(0.00);// 次级PL121 123

  private String shadinessCount = null; // 可疑户数

  private BigDecimal shadinessValue = new BigDecimal(0.00);// 可疑PL122 123

  private String damnifyCount = null; // 损失户数

  private BigDecimal damnifyValue = new BigDecimal(0.00);// 损失PL122 123

  private String totalCount = null; // 合计户数

  private BigDecimal totalValue = new BigDecimal(0.00);// 合计金额

  private String badRateCount = null; // 不良率户数

  private BigDecimal badRateValue = new BigDecimal(0.00);// 不良率金额

  private List officeList = new ArrayList();

  private List loanBankNameList = new ArrayList();

  private List assistantOrgNameList = new ArrayList();

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

  public List getAssistantOrgNameList() {
    return assistantOrgNameList;
  }

  public void setAssistantOrgNameList(List assistantOrgNameList) {
    this.assistantOrgNameList = assistantOrgNameList;
  }

  public String getAttentionCount() {
    return attentionCount;
  }

  public void setAttentionCount(String attentionCount) {
    this.attentionCount = attentionCount;
  }

  public BigDecimal getAttentionValue() {
    return attentionValue;
  }

  public void setAttentionValue(BigDecimal attentionValue) {
    this.attentionValue = attentionValue;
  }

  public String getBadRateCount() {
    return badRateCount;
  }

  public void setBadRateCount(String badRateCount) {
    this.badRateCount = badRateCount;
  }

  public BigDecimal getBadRateValue() {
    return badRateValue;
  }

  public void setBadRateValue(BigDecimal badRateValue) {
    this.badRateValue = badRateValue;
  }

  public String getBuyhouseorgid() {
    return buyhouseorgid;
  }

  public void setBuyhouseorgid(String buyhouseorgid) {
    this.buyhouseorgid = buyhouseorgid;
  }

  public String getDamnifyCount() {
    return damnifyCount;
  }

  public void setDamnifyCount(String damnifyCount) {
    this.damnifyCount = damnifyCount;
  }

  public BigDecimal getDamnifyValue() {
    return damnifyValue;
  }

  public void setDamnifyValue(BigDecimal damnifyValue) {
    this.damnifyValue = damnifyValue;
  }

  public String getDeveloperName() {
    return developerName;
  }

  public void setDeveloperName(String developerName) {
    this.developerName = developerName;
  }

  public String getFloorid() {
    return floorid;
  }

  public void setFloorid(String floorid) {
    this.floorid = floorid;
  }

  public String getFloorId() {
    return floorId;
  }

  public void setFloorId(String floorId) {
    this.floorId = floorId;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public List getList() {
    return list;
  }

  public void setList(List list) {
    this.list = list;
  }

  public String getLoanBankName() {
    return loanBankName;
  }

  public void setLoanBankName(String loanBankName) {
    this.loanBankName = loanBankName;
  }

  public List getLoanBankNameList() {
    return loanBankNameList;
  }

  public void setLoanBankNameList(List loanBankNameList) {
    this.loanBankNameList = loanBankNameList;
  }

  public String getMode() {
    return mode;
  }

  public void setMode(String mode) {
    this.mode = mode;
  }

  public String getNormalCount() {
    return normalCount;
  }

  public void setNormalCount(String normalCount) {
    this.normalCount = normalCount;
  }

  public BigDecimal getNormalValue() {
    return normalValue;
  }

  public void setNormalValue(BigDecimal normalValue) {
    this.normalValue = normalValue;
  }

  public String getOffice() {
    return office;
  }

  public void setOffice(String office) {
    this.office = office;
  }

  public List getOfficeList() {
    return officeList;
  }

  public void setOfficeList(List officeList) {
    this.officeList = officeList;
  }

  public List getPrintList() {
    return printList;
  }

  public void setPrintList(List printList) {
    this.printList = printList;
  }

  public String getSecondaryCount() {
    return secondaryCount;
  }

  public void setSecondaryCount(String secondaryCount) {
    this.secondaryCount = secondaryCount;
  }

  public BigDecimal getSecondaryValue() {
    return secondaryValue;
  }

  public void setSecondaryValue(BigDecimal secondaryValue) {
    this.secondaryValue = secondaryValue;
  }

  public String getShadinessCount() {
    return shadinessCount;
  }

  public void setShadinessCount(String shadinessCount) {
    this.shadinessCount = shadinessCount;
  }

  public BigDecimal getShadinessValue() {
    return shadinessValue;
  }

  public void setShadinessValue(BigDecimal shadinessValue) {
    this.shadinessValue = shadinessValue;
  }

  public String getTotalCount() {
    return totalCount;
  }

  public void setTotalCount(String totalCount) {
    this.totalCount = totalCount;
  }

  public BigDecimal getTotalValue() {
    return totalValue;
  }

  public void setTotalValue(BigDecimal totalValue) {
    this.totalValue = totalValue;
  }

}
