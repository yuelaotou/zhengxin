package org.xpup.hafmis.sysloan.loancallback.baddebtdestroy.form;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletRequest;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.xpup.hafmis.sysloan.loancallback.loancallback.dto.BorrowerInfoDTO;

public class BadDebtDestroyTaAF extends ActionForm {
  
  private static final long serialVersionUID = 157830469042818336L;
  
  private BorrowerInfoDTO borrowerInfoDTO=new BorrowerInfoDTO();

  // 还至年月
  private List monthYearList = new ArrayList();

  // 应还信息
  private List shouldBackList = new ArrayList();
  
  //还至年月
  private String monthYear = "";

  // 本次总还款本金
  private BigDecimal sumCorpus = new BigDecimal(0.00);

  // 本次总还款利息
  private BigDecimal sumInterest = new BigDecimal(0.00);

  // 本次总还款金额
  private BigDecimal sumMoney = new BigDecimal(0.00);
  
  // 本次实收金额
  private BigDecimal realMoney = new BigDecimal(0.00);
  
  //核销单位类型
  private String orgType = "";
  
  //核销单位名称
  private String orgName = "";
  
  //核销单位编号
  private String loanassistantorgId ="";
  
  //头表ID
  private String headId = "";
  
  //担保公司名称
  private String assistantOrgId = "";
  
  public String getAssistantOrgId() {
    return assistantOrgId;
  }

  public void setAssistantOrgId(String assistantOrgId) {
    this.assistantOrgId = assistantOrgId;
  }

  public String getHeadId() {
    return headId;
  }

  public void setHeadId(String headId) {
    this.headId = headId;
  }

  public String getLoanassistantorgId() {
    return loanassistantorgId;
  }

  public void setLoanassistantorgId(String loanassistantorgId) {
    this.loanassistantorgId = loanassistantorgId;
  }

  public String getOrgName() {
    return orgName;
  }

  public void setOrgName(String orgName) {
    this.orgName = orgName;
  }

  public String getOrgType() {
    return orgType;
  }

  public void setOrgType(String orgType) {
    this.orgType = orgType;
  }

  public BorrowerInfoDTO getBorrowerInfoDTO() {
    return borrowerInfoDTO;
  }

  public void setBorrowerInfoDTO(BorrowerInfoDTO borrowerInfoDTO) {
    this.borrowerInfoDTO = borrowerInfoDTO;
  }

  public String getMonthYear() {
    return monthYear;
  }

  public void setMonthYear(String monthYear) {
    this.monthYear = monthYear;
  }

  public List getMonthYearList() {
    return monthYearList;
  }

  public void setMonthYearList(List monthYearList) {
    this.monthYearList = monthYearList;
  }

  public BigDecimal getRealMoney() {
    return realMoney;
  }

  public void setRealMoney(BigDecimal realMoney) {
    this.realMoney = realMoney;
  }

  public List getShouldBackList() {
    return shouldBackList;
  }

  public void setShouldBackList(List shouldBackList) {
    this.shouldBackList = shouldBackList;
  }

  public BigDecimal getSumCorpus() {
    return sumCorpus;
  }

  public void setSumCorpus(BigDecimal sumCorpus) {
    this.sumCorpus = sumCorpus;
  }

  public BigDecimal getSumInterest() {
    return sumInterest;
  }

  public void setSumInterest(BigDecimal sumInterest) {
    this.sumInterest = sumInterest;
  }

  public BigDecimal getSumMoney() {
    return sumMoney;
  }

  public void setSumMoney(BigDecimal sumMoney) {
    this.sumMoney = sumMoney;
  }
  public void reset(ActionMapping mapping, ServletRequest request) {

    sumCorpus = new BigDecimal(0.00);
    sumInterest = new BigDecimal(0.00);
    sumMoney = new BigDecimal(0.00);
    realMoney = new BigDecimal(0.00);
    monthYear = "";
  }
}