package org.xpup.hafmis.sysloan.querystatistics.querystatistics.assuremode.dto;

/**
 * @author 王野 2007-10-11
 */
public class AssureModeDTO {

  private String office = null; // 办事处

  private String loanBankName = null; // 放款银行

  private String developerName = null; // PL005 开发商

  private String headId = null; // PL114 开发商头表ID

  private String floorNum = null; // 楼盘

  private String assistantOrgId = null; // 担保公司ID

  private String assistantOrgName = null;// 担保公司(协作单位名称)

  private String pledgeCount = null; // 抵押户数

  private String pledgeValue = null;// PL121 抵押金额

  private String impawnCount = null; // 质押户数

  private String impawnValue = null;// PL122 质押金额

  private String pledgeAssurerCount = null; // 抵押+保证 户数

  private String pledgeAssurerValue = null;// 抵押+保证

  private String impawnAssurerCount = null; // 质押+保证 户数

  private String impawnAssurerValue = null;// 质押+保证

  private String totalCount = null; // 合计户数

  private String totalValue = null;// 合计金额

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

  public String getFloorNum() {
    return floorNum;
  }

  public String getDeveloperName() {
    return developerName;
  }

  public void setDeveloperName(String developerName) {
    this.developerName = developerName;
  }

  public void setFloorNum(String floorNum) {
    this.floorNum = floorNum;
  }

  public String getHeadId() {
    return headId;
  }

  public void setHeadId(String headId) {
    this.headId = headId;
  }

  public String getImpawnAssurerCount() {
    return impawnAssurerCount;
  }

  public void setImpawnAssurerCount(String impawnAssurerCount) {
    this.impawnAssurerCount = impawnAssurerCount;
  }

  public String getImpawnAssurerValue() {
    return impawnAssurerValue;
  }

  public void setImpawnAssurerValue(String impawnAssurerValue) {
    this.impawnAssurerValue = impawnAssurerValue;
  }

  public String getImpawnCount() {
    return impawnCount;
  }

  public void setImpawnCount(String impawnCount) {
    this.impawnCount = impawnCount;
  }

  public String getImpawnValue() {
    return impawnValue;
  }

  public void setImpawnValue(String impawnValue) {
    this.impawnValue = impawnValue;
  }

  public String getLoanBankName() {
    return loanBankName;
  }

  public void setLoanBankName(String loanBankName) {
    this.loanBankName = loanBankName;
  }

  public String getOffice() {
    return office;
  }

  public void setOffice(String office) {
    this.office = office;
  }

  public String getPledgeAssurerCount() {
    return pledgeAssurerCount;
  }

  public void setPledgeAssurerCount(String pledgeAssurerCount) {
    this.pledgeAssurerCount = pledgeAssurerCount;
  }

  public String getPledgeAssurerValue() {
    return pledgeAssurerValue;
  }

  public void setPledgeAssurerValue(String pledgeAssurerValue) {
    this.pledgeAssurerValue = pledgeAssurerValue;
  }

  public String getPledgeCount() {
    return pledgeCount;
  }

  public void setPledgeCount(String pledgeCount) {
    this.pledgeCount = pledgeCount;
  }

  public String getPledgeValue() {
    return pledgeValue;
  }

  public void setPledgeValue(String pledgeValue) {
    this.pledgeValue = pledgeValue;
  }

  public String getTotalCount() {
    return totalCount;
  }

  public void setTotalCount(String totalCount) {
    this.totalCount = totalCount;
  }

  public String getTotalValue() {
    return totalValue;
  }

  public void setTotalValue(String totalValue) {
    this.totalValue = totalValue;
  }

}
