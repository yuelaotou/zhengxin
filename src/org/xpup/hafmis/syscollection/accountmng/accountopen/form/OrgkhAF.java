package org.xpup.hafmis.syscollection.accountmng.accountopen.form;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionMapping;
import org.apache.struts.validator.ValidatorForm;
import org.xpup.hafmis.syscollection.common.domain.entity.Org;
import org.xpup.hafmis.syscommon.domain.entity.OrgInfo;

public class OrgkhAF extends ValidatorForm {

  private static final long serialVersionUID = 4545439817994321837L;

  private Org org = new Org();

  private String type = "";

  private Map officeMap = new HashMap();

  private BigDecimal payPrecisionMap = new BigDecimal(0.00);

  private Map industyMap = new HashMap();

  private Map orgpaywayMap = new HashMap();

  private Map paymentaccuracyMap = new HashMap();

  private Map inareaMap = new HashMap();

  private Map natureofunitsMap = new HashMap();

  private Map authoritiesMap = new HashMap();

  private String moneyType = "";

  private String isOrgorcenter = "";

  public String getIsOrgorcenter() {
    return isOrgorcenter;
  }

  public void setIsOrgorcenter(String isOrgorcenter) {
    this.isOrgorcenter = isOrgorcenter;
  }

  public Map getIndustyMap() {
    return industyMap;
  }

  public void setIndustyMap(Map industyMap) {
    this.industyMap = industyMap;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public Org getOrg() {
    return org;
  }

  public void setOrg(Org org) {
    this.org = org;
  }

  public Map getOfficeMap() {
    return officeMap;
  }

  public void setOfficeMap(Map officeMap) {
    this.officeMap = officeMap;
  }

  public Map getOrgpaywayMap() {
    return orgpaywayMap;
  }

  public void setOrgpaywayMap(Map orgpaywayMap) {
    this.orgpaywayMap = orgpaywayMap;
  }

  public Map getPaymentaccuracyMap() {
    return paymentaccuracyMap;
  }

  public void setPaymentaccuracyMap(Map paymentaccuracyMap) {
    this.paymentaccuracyMap = paymentaccuracyMap;
  }

  public Map getInareaMap() {
    return inareaMap;
  }

  public void setInareaMap(Map inareaMap) {
    this.inareaMap = inareaMap;
  }

  public Map getNatureofunitsMap() {
    return natureofunitsMap;
  }

  public void setNatureofunitsMap(Map natureofunitsMap) {
    this.natureofunitsMap = natureofunitsMap;
  }

  public Map getAuthoritiesMap() {
    return authoritiesMap;
  }

  public void setAuthoritiesMap(Map authoritiesMap) {
    this.authoritiesMap = authoritiesMap;
  }

  public BigDecimal getPayPrecisionMap() {
    return payPrecisionMap;
  }

  public void setPayPrecisionMap(BigDecimal payPrecisionMap) {
    this.payPrecisionMap = payPrecisionMap;
  }

  public String getMoneyType() {
    return moneyType;
  }

  public void setMoneyType(String moneyType) {
    this.moneyType = moneyType;
  }

  public void reset(ActionMapping mapping, HttpServletRequest request) {
    this.org.setOrgInfo(new OrgInfo());
  }
  
}
