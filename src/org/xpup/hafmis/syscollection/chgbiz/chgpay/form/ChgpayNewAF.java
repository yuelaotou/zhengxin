package org.xpup.hafmis.syscollection.chgbiz.chgpay.form;

import java.util.HashMap;
import java.util.Map;
import org.apache.struts.validator.ValidatorActionForm;
import org.xpup.hafmis.syscollection.common.domain.entity.ChgPaymentTail;

public class ChgpayNewAF extends ValidatorActionForm {

  private static final long serialVersionUID = -7546809440786959772L;

  private ChgPaymentTail chgPaymentTail = new ChgPaymentTail();

  private String type = "";

  private Map sexMap = new HashMap();

  private Map documentsstateMap = new HashMap();

  private String orgRate;

  private String empRate;

  private String orgId;

  public String getOrgId() {
    return orgId;
  }

  public void setOrgId(String orgId) {
    this.orgId = orgId;
  }

  public Map getSexMap() {
    return sexMap;
  }

  public void setSexMap(Map sexMap) {
    this.sexMap = sexMap;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public ChgPaymentTail getChgPaymentTail() {
    return chgPaymentTail;
  }

  public void setChgPaymentTail(ChgPaymentTail chgPaymentTail) {
    this.chgPaymentTail = chgPaymentTail;
  }

  public String getEmpRate() {
    return empRate;
  }

  public void setEmpRate(String empRate) {
    this.empRate = empRate;
  }

  public String getOrgRate() {
    return orgRate;
  }

  public void setOrgRate(String orgRate) {
    this.orgRate = orgRate;
  }

  public Map getDocumentsstateMap() {
    return documentsstateMap;
  }

  public void setDocumentsstateMap(Map documentsstateMap) {
    this.documentsstateMap = documentsstateMap;
  }

}
