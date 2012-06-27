package org.xpup.hafmis.sysloan.dataready.rate.form;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletRequest;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.xpup.hafmis.sysloan.dataready.rate.dto.RateDTO;

public class RateNewAF extends ActionForm {
  /**
   * 
   */
  private static final long serialVersionUID = -3582513021629698448L;

  private String rateId = "";

  private String type;

  private String loanRateType; // 贷款类型

  private BigDecimal loanYearRate = new BigDecimal(0.00); // 年利率

  private String office; // 办事处

  private Map loanRateTypeMap = new HashMap(); // 贷款枚举

  private String officeName = "";

  private String loanRateTypeUpdate; // 隐藏字段用于存储贷款类型
  
  private String loanRateOfficeUpdate; // 隐藏字段用于存储办事处
  
  private String adjustBasis = "";

  private String adjustDate = "";
  
  public String getAdjustDate() {
    return adjustDate;
  }

  public void setAdjustDate(String adjustDate) {
    this.adjustDate = adjustDate;
  }

  public String getAdjustBasis() {
    return adjustBasis;
  }

  public void setAdjustBasis(String adjustBasis) {
    this.adjustBasis = adjustBasis;
  }

  public String getLoanRateTypeUpdate() {
    return loanRateTypeUpdate;
  }

  public void setLoanRateTypeUpdate(String loanRateTypeUpdate) {
    this.loanRateTypeUpdate = loanRateTypeUpdate;
  }

  public String getOfficeName() {
    return officeName;
  }

  public void setOfficeName(String officeName) {
    this.officeName = officeName;
  }

  public String getLoanRateType() {
    return loanRateType;
  }

  public void setLoanRateType(String loanRateType) {
    this.loanRateType = loanRateType;
  }

  public String getOffice() {
    return office;
  }

  public void setOffice(String office) {
    this.office = office;
  }

  public void reset(ActionMapping mapping, ServletRequest request) {
    loanRateType = "";
    loanYearRate = new BigDecimal(0.00);
    office = "";
    loanRateTypeMap = new HashMap();
    loanRateTypeUpdate="";
  }

  public Map getLoanRateTypeMap() {
    return loanRateTypeMap;
  }

  public void setLoanRateTypeMap(Map loanRateTypeMap) {
    this.loanRateTypeMap = loanRateTypeMap;
  }

  public BigDecimal getLoanYearRate() {
    return loanYearRate;
  }

  public void setLoanYearRate(BigDecimal loanYearRate) {
    this.loanYearRate = loanYearRate;
  }

  public String getRateId() {
    return rateId;
  }

  public void setRateId(String rateId) {
    this.rateId = rateId;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public String getLoanRateOfficeUpdate() {
    return loanRateOfficeUpdate;
  }

  public void setLoanRateOfficeUpdate(String loanRateOfficeUpdate) {
    this.loanRateOfficeUpdate = loanRateOfficeUpdate;
  }
}
