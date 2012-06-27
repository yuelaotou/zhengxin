package org.xpup.hafmis.sysloan.querystatistics.querystatistics.assuremode.form;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.apache.struts.action.ActionForm;

/**
 * @author 王野 2007-10-11
 */
public class AssureModeAF extends ActionForm {

  private static final long serialVersionUID = -1606767891351787425L;

  private String mode = "";// 单选按钮

  private List list = null;

  private List printList = null;// 打印list

  private String id = null;

  private String office = null; // 办事处

  private String loanBankName = null; // 放款银行

  private String developerName = null; // PL005 开发商

  private String buyhouseorgid = null; // PL114 开发商头表ID headId

  private String floorid = null;// 楼盘ID

  private String floorNum = null; // 楼盘

  private String assistantOrgId = null; // 担保公司ID

  private String assistantOrgName = null; // 担保公司名称(协作单位名称)

  private String pledgeCount = null; // 抵押户数

  private BigDecimal pledgeValue = new BigDecimal(0.00);// PL121 抵押金额

  private String impawnCount = null; // 质押户数

  private BigDecimal impawnValue = new BigDecimal(0.00);// PL122 质押金额

  private String pledgeAssurerCount = null; // 抵押+保证 户数

  private BigDecimal pledgeAssurerValue = new BigDecimal(0.00);// PL121 123

  // 抵押+保证

  private String impawnAssurerCount = null; // 质押+保证 户数

  private BigDecimal impawnAssurerValue = new BigDecimal(0.00);// PL122 123

  // 质押+保证

  private String totalCount = null; // 合计户数

  private BigDecimal totalValue = new BigDecimal(0.00);// 合计金额

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

  public String getBuyhouseorgid() {
    return buyhouseorgid;
  }

  public void setBuyhouseorgid(String buyhouseorgid) {
    this.buyhouseorgid = buyhouseorgid;
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

  public String getFloorNum() {
    return floorNum;
  }

  public void setFloorNum(String floorNum) {
    this.floorNum = floorNum;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getImpawnAssurerCount() {
    return impawnAssurerCount;
  }

  public void setImpawnAssurerCount(String impawnAssurerCount) {
    this.impawnAssurerCount = impawnAssurerCount;
  }

  public BigDecimal getImpawnAssurerValue() {
    return impawnAssurerValue;
  }

  public void setImpawnAssurerValue(BigDecimal impawnAssurerValue) {
    this.impawnAssurerValue = impawnAssurerValue;
  }

  public String getImpawnCount() {
    return impawnCount;
  }

  public void setImpawnCount(String impawnCount) {
    this.impawnCount = impawnCount;
  }

  public BigDecimal getImpawnValue() {
    return impawnValue;
  }

  public void setImpawnValue(BigDecimal impawnValue) {
    this.impawnValue = impawnValue;
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

  public String getPledgeAssurerCount() {
    return pledgeAssurerCount;
  }

  public void setPledgeAssurerCount(String pledgeAssurerCount) {
    this.pledgeAssurerCount = pledgeAssurerCount;
  }

  public BigDecimal getPledgeAssurerValue() {
    return pledgeAssurerValue;
  }

  public void setPledgeAssurerValue(BigDecimal pledgeAssurerValue) {
    this.pledgeAssurerValue = pledgeAssurerValue;
  }

  public String getPledgeCount() {
    return pledgeCount;
  }

  public void setPledgeCount(String pledgeCount) {
    this.pledgeCount = pledgeCount;
  }

  public BigDecimal getPledgeValue() {
    return pledgeValue;
  }

  public void setPledgeValue(BigDecimal pledgeValue) {
    this.pledgeValue = pledgeValue;
  }

  public List getPrintList() {
    return printList;
  }

  public void setPrintList(List printList) {
    this.printList = printList;
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
