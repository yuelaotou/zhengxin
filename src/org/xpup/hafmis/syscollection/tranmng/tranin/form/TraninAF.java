package org.xpup.hafmis.syscollection.tranmng.tranin.form;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletRequest;

import org.apache.struts.action.ActionMapping;
import org.xpup.hafmis.common.form.IdAF;

import org.xpup.hafmis.syscollection.common.domain.entity.TranInTail;
import org.xpup.hafmis.syscollection.common.domain.entity.TranOutTail;

/**
 * shiyan
 */
public class TraninAF extends IdAF {

  /**
   * 
   */
  private static final long serialVersionUID = 4314073321220405479L;

  private int traninPeople = 0;

  private BigDecimal sumBalanceAll = new BigDecimal(0.00);

  private BigDecimal sumInterestAll = new BigDecimal(0.00);

  private BigDecimal sumTraninAll = new BigDecimal(0.00);

  private TranInTail tranInTail = new TranInTail();

  private String tranInHeadById = "";

  private String loadsMassage = "";

  private String inOrgId = "";

  private String inOrgName = "";

  private String noteNum = "";
  
  private String docNum ="";
  
  private String outOrgId = "";

  private String outOrgName = "";

  private List list;
  
  private String buttonMagssage = "";
  
  private String isType="";
 
  public String getIsType() {
    return isType;
  }

  public void setIsType(String isType) {
    this.isType = isType;
  }

  public String getButtonMagssage() {
    return buttonMagssage;
  }

  public void setButtonMagssage(String buttonMagssage) {
    this.buttonMagssage = buttonMagssage;
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

  public TranInTail getTranInTail() {
    return tranInTail;
  }

  public void setTranInTail(TranInTail tranInTail) {
    this.tranInTail = tranInTail;
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

  public String getLoadsMassage() {
    return loadsMassage;
  }

  public void setLoadsMassage(String loadsMassage) {
    this.loadsMassage = loadsMassage;
  }

  public String getTranInHeadById() {
    return tranInHeadById;
  }

  public void setTranInHeadById(String tranInHeadById) {
    this.tranInHeadById = tranInHeadById;
  }

  public BigDecimal getSumBalanceAll() {
    return sumBalanceAll;
  }

  public void setSumBalanceAll(BigDecimal sumBalanceAll) {
    this.sumBalanceAll = sumBalanceAll;
  }

  public BigDecimal getSumInterestAll() {
    return sumInterestAll;
  }

  public void setSumInterestAll(BigDecimal sumInterestAll) {
    this.sumInterestAll = sumInterestAll;
  }

  public BigDecimal getSumTraninAll() {
    return sumTraninAll;
  }

  public void setSumTraninAll(BigDecimal sumTraninAll) {
    this.sumTraninAll = sumTraninAll;
  }

  public int getTraninPeople() {
    return traninPeople;
  }

  public void setTraninPeople(int traninPeople) {
    this.traninPeople = traninPeople;
  }
  public void reset(ActionMapping mapping, ServletRequest request) {
    inOrgId="" ;
    inOrgName="";
    noteNum="" ;
    traninPeople = 0;
    sumInterestAll = new BigDecimal(0.00);
    sumBalanceAll = new BigDecimal(0.00);
    sumTraninAll = new BigDecimal(0.00);
    loadsMassage="";
    tranInTail = new TranInTail();
    docNum ="";
    outOrgId = "";
    outOrgName = "";
    list=new ArrayList();
  }

  public String getDocNum() {
    return docNum;
  }

  public void setDocNum(String docNum) {
    this.docNum = docNum;
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
}
