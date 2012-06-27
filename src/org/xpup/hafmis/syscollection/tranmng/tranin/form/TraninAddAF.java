package org.xpup.hafmis.syscollection.tranmng.tranin.form;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.xpup.hafmis.common.form.IdAF;
import org.xpup.hafmis.syscollection.common.domain.entity.TranInTail;

public class TraninAddAF extends IdAF {

  /**
   * 
   */
  private static final long serialVersionUID = 7759331677401101685L;

  private String inOrgId = "";

  private String tranInHeadId = "";

  private BigDecimal orgRate = new BigDecimal(0.00);

  private BigDecimal empRate = new BigDecimal(0.00);

  private Integer payPrecision;

  private TranInTail tranInTail = new TranInTail();

  private Map sexMap = new HashMap();

  private Map documentsstateMap = new HashMap();

  private String type = "";

  private List list;

  private String noteNum = "";
  
  private String traninTailsex="";

  public String getTraninTailsex() {
    return traninTailsex;
  }

  public void setTraninTailsex(String traninTailsex) {
    this.traninTailsex = traninTailsex;
  }

  public String getNoteNum() {
    return noteNum;
  }

  public void setNoteNum(String noteNum) {
    this.noteNum = noteNum;
  }

  public List getList() {
    return list;
  }

  public void setList(List list) {
    this.list = list;
  }

  public Map getDocumentsstateMap() {
    return documentsstateMap;
  }

  public void setDocumentsstateMap(Map documentsstateMap) {
    this.documentsstateMap = documentsstateMap;
  }

  public Map getSexMap() {
    return sexMap;
  }

  public void setSexMap(Map sexMap) {
    this.sexMap = sexMap;
  }

  public TranInTail getTranInTail() {
    return tranInTail;
  }

  public void setTranInTail(TranInTail tranInTail) {
    this.tranInTail = tranInTail;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public BigDecimal getEmpRate() {
    return empRate;
  }

  public void setEmpRate(BigDecimal empRate) {
    this.empRate = empRate;
  }

  public BigDecimal getOrgRate() {
    return orgRate;
  }

  public void setOrgRate(BigDecimal orgRate) {
    this.orgRate = orgRate;
  }

  public String getInOrgId() {
    return inOrgId;
  }

  public void setInOrgId(String inOrgId) {
    this.inOrgId = inOrgId;
  }

  public String getTranInHeadId() {
    return tranInHeadId;
  }

  public void setTranInHeadId(String tranInHeadId) {
    this.tranInHeadId = tranInHeadId;
  }

  public Integer getPayPrecision() {
    return payPrecision;
  }

  public void setPayPrecision(Integer payPrecision) {
    this.payPrecision = payPrecision;
  }

}
