package org.xpup.hafmis.syscollection.accounthandle.clearaccount.form;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;


/**
 * 
 * @author ¿Ó≈Ù
 *2007-6-27
 */    
public class ClearAccountBalanceShowAF extends ActionForm{
  private String orgName;
  private String orgId;
  private Map bis_type=new HashMap();
  private Map bank=new HashMap();
  private String bis_type1;
  private String bank1;
  private String operator_temp;
  private String startday;
  private String untilday;
  public Map getBank() {
    return bank;
  }
  public void setBank(Map bank) {
    this.bank = bank;
  }
  public String getBank1() {
    return bank1;
  }
  public void setBank1(String bank1) {
    this.bank1 = bank1;
  }
  public Map getBis_type() {
    return bis_type;
  }
  public void setBis_type(Map bis_type) {
    this.bis_type = bis_type;
  }
  public String getBis_type1() {
    return bis_type1;
  }
  public void setBis_type1(String bis_type1) {
    this.bis_type1 = bis_type1;
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
  public String getStartday() {
    return startday;
  }
  public void setStartday(String startday) {
    this.startday = startday;
  }
  public String getUntilday() {
    return untilday;
  }
  public void setUntilday(String untilday) {
    this.untilday = untilday;
  }
  public String getOperator_temp() {
    return operator_temp;
  }
  public void setOperator_temp(String operator_temp) {
    this.operator_temp = operator_temp;
  }
 
  public void reset(ActionMapping mapping, HttpServletRequest request) {
    super.reset(mapping, request);
    this.orgName = "";
    this.orgId = "";
    this.bis_type1 = "";
    this.bank1 = "";
    this.operator_temp = "";
    this.startday = "";
    this.untilday = "" ;
  }
}
