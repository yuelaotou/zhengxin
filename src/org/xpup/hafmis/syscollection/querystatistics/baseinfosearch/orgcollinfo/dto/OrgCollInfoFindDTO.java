package org.xpup.hafmis.syscollection.querystatistics.baseinfosearch.orgcollinfo.dto;

public class OrgCollInfoFindDTO {
  /** 办事处 */
  private String officecode="";
  /** 归集银行 */
  private String collectionBankId="";
  /** 单位性质 */
  private String character="";
  /** 主管部门 */
  private String deptInCharge="";
  /** 单位编号 */
  private String orgId="";
  /** 单位名称 */
  private String orgName="";
  /** 单位缴率 */
  private String orgrateStart="";
  private String orgrateEnd="";
  /** 职工缴率 */
  private String emprateStart="";
  private String emprateEnd="";
  /** 单位状态 */
  private String openStatus="";
  /** 缴存方式 */
  private String payMode="";
  /** 所在地区*/
  private String region="";
  /** 原单位编号*/
  private String oldOrgId="";
  /** 稽查员*/
  private String inspector="";
  /** 发薪日*/
  private String payDate="";
  /** 组织机构代码*/
  private String code="";
  /** 开户日期*/
  private String openDateStart="";
  private String openDateTimeEnd="";
  /** 汇缴总额begin*/
  private String paySumStart="";
  private String paySumEnd="";
  /** 公积金余额*/
  private String balanceStart="";
  private String balanceEnd="";
  /** 挂账余额*/
  private String overPayStart="";
  private String overPayEnd="";
  /** 单位缴至年月*/
  private String orgPayMonthStart="";
  private String orgPayMonthEnd;
  /** 个人缴至年月*/
  private String empPayMonthStart="";
  private String empPayMonthEnd="";
  /** 职工总数*/ 
  private String empCountStart="";
  private String empCountEnd="";
  /** 工资总额*/
  private String salarySumStart="";
  private String salarySumEnd="";
  
  public String getBalanceEnd() {
    return balanceEnd;
  }
  public void setBalanceEnd(String balanceEnd) {
    this.balanceEnd = balanceEnd;
  }
  public String getBalanceStart() {
    return balanceStart;
  }
  public void setBalanceStart(String balanceStart) {
    this.balanceStart = balanceStart;
  }
  public String getCharacter() {
    return character;
  }
  public void setCharacter(String character) {
    this.character = character;
  }
  public String getCode() {
    return code;
  }
  public void setCode(String code) {
    this.code = code;
  }
  public String getCollectionBankId() {
    return collectionBankId;
  }
  public void setCollectionBankId(String collectionBankId) {
    this.collectionBankId = collectionBankId;
  }
  public String getDeptInCharge() {
    return deptInCharge;
  }
  public void setDeptInCharge(String deptInCharge) {
    this.deptInCharge = deptInCharge;
  }
  public String getEmpCountEnd() {
    return empCountEnd;
  }
  public void setEmpCountEnd(String empCountEnd) {
    this.empCountEnd = empCountEnd;
  }
  public String getEmpCountStart() {
    return empCountStart;
  }
  public void setEmpCountStart(String empCountStart) {
    this.empCountStart = empCountStart;
  }
  public String getEmpPayMonthEnd() {
    return empPayMonthEnd;
  }
  public void setEmpPayMonthEnd(String empPayMonthEnd) {
    this.empPayMonthEnd = empPayMonthEnd;
  }
  public String getEmpPayMonthStart() {
    return empPayMonthStart;
  }
  public void setEmpPayMonthStart(String empPayMonthStart) {
    this.empPayMonthStart = empPayMonthStart;
  }
  public String getInspector() {
    return inspector;
  }
  public void setInspector(String inspector) {
    this.inspector = inspector;
  }
  public String getOfficecode() {
    return officecode;
  }
  public void setOfficecode(String officecode) {
    this.officecode = officecode;
  }
  public String getOldOrgId() {
    return oldOrgId;
  }
  public void setOldOrgId(String oldOrgId) {
    this.oldOrgId = oldOrgId;
  }
  public String getOpenDateStart() {
    return openDateStart;
  }
  public void setOpenDateStart(String openDateStart) {
    this.openDateStart = openDateStart;
  }
  public String getOpenDateTimeEnd() {
    return openDateTimeEnd;
  }
  public void setOpenDateTimeEnd(String openDateTimeEnd) {
    this.openDateTimeEnd = openDateTimeEnd;
  }
  public String getOpenStatus() {
    return openStatus;
  }
  public void setOpenStatus(String openStatus) {
    this.openStatus = openStatus;
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
  public String getOrgPayMonthEnd() {
    return orgPayMonthEnd;
  }
  public void setOrgPayMonthEnd(String orgPayMonthEnd) {
    this.orgPayMonthEnd = orgPayMonthEnd;
  }
  public String getOrgPayMonthStart() {
    return orgPayMonthStart;
  }
  public void setOrgPayMonthStart(String orgPayMonthStart) {
    this.orgPayMonthStart = orgPayMonthStart;
  }
  public String getOverPayEnd() {
    return overPayEnd;
  }
  public void setOverPayEnd(String overPayEnd) {
    this.overPayEnd = overPayEnd;
  }
  public String getOverPayStart() {
    return overPayStart;
  }
  public void setOverPayStart(String overPayStart) {
    this.overPayStart = overPayStart;
  }
  public String getPayDate() {
    return payDate;
  }
  public void setPayDate(String payDate) {
    this.payDate = payDate;
  }
  public String getPayMode() {
    return payMode;
  }
  public void setPayMode(String payMode) {
    this.payMode = payMode;
  }
  public String getPaySumEnd() {
    return paySumEnd;
  }
  public void setPaySumEnd(String paySumEnd) {
    this.paySumEnd = paySumEnd;
  }
  public String getPaySumStart() {
    return paySumStart;
  }
  public void setPaySumStart(String paySumStart) {
    this.paySumStart = paySumStart;
  }
  public String getRegion() {
    return region;
  }
  public void setRegion(String region) {
    this.region = region;
  }
  public String getSalarySumEnd() {
    return salarySumEnd;
  }
  public void setSalarySumEnd(String salarySumEnd) {
    this.salarySumEnd = salarySumEnd;
  }
  public String getSalarySumStart() {
    return salarySumStart;
  }
  public void setSalarySumStart(String salarySumStart) {
    this.salarySumStart = salarySumStart;
  }
  public String getEmprateEnd() {
    return emprateEnd;
  }
  public void setEmprateEnd(String emprateEnd) {
    this.emprateEnd = emprateEnd;
  }
  public String getEmprateStart() {
    return emprateStart;
  }
  public void setEmprateStart(String emprateStart) {
    this.emprateStart = emprateStart;
  }
  public String getOrgrateEnd() {
    return orgrateEnd;
  }
  public void setOrgrateEnd(String orgrateEnd) {
    this.orgrateEnd = orgrateEnd;
  }
  public String getOrgrateStart() {
    return orgrateStart;
  }
  public void setOrgrateStart(String orgrateStart) {
    this.orgrateStart = orgrateStart;
  }

  
  
  

}
