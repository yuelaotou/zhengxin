package org.xpup.hafmis.syscollection.querystatistics.paymentpickstatistics.pickupreason.form;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletRequest;

import org.apache.struts.action.ActionMapping;
import org.xpup.hafmis.common.form.IdAF;
public class PickupreasonAF extends IdAF{

  /**
   * 
   */
  private static final long serialVersionUID = -301137330523833310L;

  private String officeid="";   //办事处
  private String bankid="";    //归集银行
  private String orgid="";  //单位编号
  private String date="";   //发生日期
  private List list;
  private String loadsMassage="";
  private String totalpeople="";
  private String totalmoney="";
 public String getTotalmoney() {
    return totalmoney;
  }
  public void setTotalmoney(String totalmoney) {
    this.totalmoney = totalmoney;
  }
  public String getTotalpeople() {
    return totalpeople;
  }
  public void setTotalpeople(String totalpeople) {
    this.totalpeople = totalpeople;
  }
public void reset(ActionMapping mapping, ServletRequest request) {
    officeid=""; 
    bankid=""; 
    orgid="";  
    list=new ArrayList();
    loadsMassage="";
    totalpeople="";
    totalmoney="";
  }
  public List getList() {
    return list;
  }
  public void setList(List list) {
    this.list = list;
  }
  public String getLoadsMassage() {
    return loadsMassage;
  }
  public void setLoadsMassage(String loadsMassage) {
    this.loadsMassage = loadsMassage;
  }
  public String getBankid() {
    return bankid;
  }
  public void setBankid(String bankid) {
    this.bankid = bankid;
  }
  public String getOfficeid() {
    return officeid;
  }
  public void setOfficeid(String officeid) {
    this.officeid = officeid;
  }
  public String getOrgid() {
    return orgid;
  }
  public void setOrgid(String orgid) {
    this.orgid = orgid;
  }
  public String getDate() {
    return date;
  }
  public void setDate(String date) {
    this.date = date;
  } 
}
