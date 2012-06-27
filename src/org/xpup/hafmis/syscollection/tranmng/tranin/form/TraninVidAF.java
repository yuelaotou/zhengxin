package org.xpup.hafmis.syscollection.tranmng.tranin.form;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletRequest;

import org.apache.struts.action.ActionMapping;
import org.xpup.hafmis.common.form.IdAF;
import org.xpup.hafmis.syscollection.common.domain.entity.ChgPersonTail;
import org.xpup.hafmis.syscollection.common.domain.entity.TranInTail;

/**
 * shiyan
 */
public class TraninVidAF extends IdAF {
  /**
   * 
   */
  private static final long serialVersionUID = 4314073321220405479L;

  private String inOrgId = "";

  private String inOrgName = "";

  private String noteNum = "";

  private String docNum = "";
  private String reason = "";

  private String outOrgId = "";

  private String collBankId = "";
  private String outOrgName = "";

  private String settDate = "";
  private String settDatea = "";

  private String tranStatus = "";

  private String count = "";

  private String sumCount = "";

  private BigDecimal sumTranInAmount = new BigDecimal(0.00);

  private Map stateMap;

  private TranInTail tranInTail = new TranInTail();

  private List list;
  private List lista;
  
  private BigDecimal tranInSumBalance = new BigDecimal(0.00);
 
  public BigDecimal getTranInSumBalance() {
    return tranInSumBalance;
  }

  public void setTranInSumBalance(BigDecimal tranInSumBalance) {
    this.tranInSumBalance = tranInSumBalance;
  }

  public String getDocNum() {
    return docNum;
  }

  public void setDocNum(String docNum) {
    this.docNum = docNum;
  }

  public String getInOrgId() {
    return inOrgId;
  }

  public void setInOrgId(String inOrgId) {
    this.inOrgId = inOrgId;
  }

  public String getInOrgName() {
    return inOrgName;
  }

  public void setInOrgName(String inOrgName) {
    this.inOrgName = inOrgName;
  }

  public List getList() {
    return list;
  }

  public void setList(List list) {
    this.list = list;
  }

  public String getNoteNum() {
    return noteNum;
  }

  public void setNoteNum(String noteNum) {
    this.noteNum = noteNum;
  }

  public String getOutOrgId() {
    return outOrgId;
  }

  public void setOutOrgId(String outOrgId) {
    this.outOrgId = outOrgId;
  }

  public String getOutOrgName() {
    return outOrgName;
  }

  public void setOutOrgName(String outOrgName) {
    this.outOrgName = outOrgName;
  }

  public TranInTail getTranInTail() {
    return tranInTail;
  }

  public void setTranInTail(TranInTail tranInTail) {
    this.tranInTail = tranInTail;
  }

  public String getSettDate() {
    return settDate;
  }

  public void setSettDate(String settDate) {
    this.settDate = settDate;
  }

  public String getTranStatus() {
    return tranStatus;
  }

  public void setTranStatus(String tranStatus) {
    this.tranStatus = tranStatus;
  }

  public Map getStateMap() {
    return stateMap;
  }

  public void setStateMap(Map stateMap) {
    this.stateMap = stateMap;
  }

  public String getCount() {
    return count;
  }

  public void setCount(String count) {
    this.count = count;
  }

  public BigDecimal getSumTranInAmount() {
    return sumTranInAmount;
  }

  public void setSumTranInAmount(BigDecimal sumTranInAmount) {
    this.sumTranInAmount = sumTranInAmount;
  }

  public String getSumCount() {
    return sumCount;
  }

  public void setSumCount(String sumCount) {
    this.sumCount = sumCount;
  }
  public void reset(ActionMapping mapping, ServletRequest request) {
    inOrgId="" ;
    inOrgName="";
    noteNum="" ;
    stateMap=new HashMap();//ÐÔ±ð
    outOrgName ="";
    settDate="";
    tranStatus="" ;
    count = "";
    sumCount="";
    sumTranInAmount = new BigDecimal(0.0);
    tranInTail = new TranInTail();
    list=new ArrayList();
  }

  public String getReason() {
    return reason;
  }

  public void setReason(String reason) {
    this.reason = reason;
  }

  public List getLista() {
    return lista;
  }

  public void setLista(List lista) {
    this.lista = lista;
  }

  public String getCollBankId() {
    return collBankId;
  }

  public void setCollBankId(String collBankId) {
    this.collBankId = collBankId;
  }

  public String getSettDatea() {
    return settDatea;
  }

  public void setSettDatea(String settDatea) {
    this.settDatea = settDatea;
  }
}
