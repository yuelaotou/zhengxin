package org.xpup.hafmis.syscollection.pickupmng.specialpickup.form;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.struts.action.ActionForm;
import org.xpup.hafmis.syscollection.common.domain.entity.SpecialPick;

public class SpePickListAF extends ActionForm {
  private static final long serialVersionUID = 157830469042818336L;

  private SpecialPick specialPick=new SpecialPick();
  
  private Map collectionBankMap = new HashMap();

  private Map officecodeMap = new HashMap();

  private String collectionBankId="";
  
  private String officeCode="";
  
  private String id = "";

  private String name = null;
  
  private String operateTime1=null;
  
  private String operateTime2=null;
  
  private List list;

  public List getList() {
    return list;
  }

  public void setList(List list) {
    this.list = list;
  }

  public String getId() {
    return this.id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Map getCollectionBankMap() {
    return collectionBankMap;
  }

  public void setCollectionBankMap(Map collectionBankMap) {
    this.collectionBankMap = collectionBankMap;
  }

  public Map getOfficecodeMap() {
    return officecodeMap;
  }

  public void setOfficecodeMap(Map officecodeMap) {
    this.officecodeMap = officecodeMap;
  }

  public String getOperateTime1() {
    return operateTime1;
  }

  public void setOperateTime1(String operateTime1) {
    this.operateTime1 = operateTime1;
  }

  public String getOperateTime2() {
    return operateTime2;
  }

  public void setOperateTime2(String operateTime2) {
    this.operateTime2 = operateTime2;
  }

  public SpecialPick getSpecialPick() {
    return specialPick;
  }

  public void setSpecialPick(SpecialPick specialPick) {
    this.specialPick = specialPick;
  }

  public String getOfficeCode() {
    return officeCode;
  }

  public void setOfficeCode(String officeCode) {
    this.officeCode = officeCode;
  }

  public String getCollectionBankId() {
    return collectionBankId;
  }

  public void setCollectionBankId(String collectionBankId) {
    this.collectionBankId = collectionBankId;
  }

  



}
