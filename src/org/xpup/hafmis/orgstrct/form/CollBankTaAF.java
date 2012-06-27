package org.xpup.hafmis.orgstrct.form;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionMapping;
import org.xpup.hafmis.common.form.CriterionsAF;

public class CollBankTaAF extends CriterionsAF{
  
  private String officecode;
  private String bankname;
  private String bankid;
  
  //bit add
  private String collectionbankacc="";
  private String contactprsn="";
  private String contacttel="";
  private String centername="";
  
  private List officelist;
  private List collBanklist;
  
  private String type;
  
  public void reset(ActionMapping mapping, HttpServletRequest request) {
    super.reset(mapping, request);
    this.officecode = "";
    this.bankname = "";
    
  }
  public String getBankname() {
    return bankname;
  }
  public String getType() {
    return type;
  }
  public void setType(String type) {
    this.type = type;
  }
  public void setBankname(String bankname) {
    this.bankname = bankname;
  }
  public String getOfficecode() {
    return officecode;
  }
  public void setOfficecode(String officecode) {
    this.officecode = officecode;
  }
  public List getOfficelist() {
    return officelist;
  }
  public void setOfficelist(List officelist) {
    this.officelist = officelist;
  }
  public List getCollBanklist() {
    return collBanklist;
  }
  public void setCollBanklist(List collBanklist) {
    this.collBanklist = collBanklist;
  }
  public String getBankid() {
    return bankid;
  }
  public void setBankid(String bankid) {
    this.bankid = bankid;
  }
  public String getCollectionbankacc() {
    return collectionbankacc;
  }
  public void setCollectionbankacc(String collectionbankacc) {
    this.collectionbankacc = collectionbankacc;
  }
  public String getContactprsn() {
    return contactprsn;
  }
  public void setContactprsn(String contactprsn) {
    this.contactprsn = contactprsn;
  }
  public String getContacttel() {
    return contacttel;
  }
  public void setContacttel(String contacttel) {
    this.contacttel = contacttel;
  }
  public String getCentername() {
    return centername;
  }
  public void setCentername(String centername) {
    this.centername = centername;
  }

}