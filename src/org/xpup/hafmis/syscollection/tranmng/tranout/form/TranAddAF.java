package org.xpup.hafmis.syscollection.tranmng.tranout.form;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionMapping;
import org.apache.struts.validator.ValidatorActionForm;
import org.xpup.hafmis.demo.domain.entity.Demo;
import org.xpup.hafmis.syscollection.common.domain.entity.Emp;

public class TranAddAF extends ValidatorActionForm {

  private static final long serialVersionUID = -7546809440786959772L;

  private Emp emp = new Emp();

  private Map sexMap = new HashMap();

  private String type = "";

  private String empid = null;

  private String empname = null;

  private String card_kind = null;

  private String card_num = null;

  private String preBalance = null; // 往年余额

  private String curBalance = null; // 本年余额

  private String preInterest = null; // 往年利息

  private String curInterest = null; // 本年利息

  private String transum = null; // 转出总额

  private String sumInterest = null; // 利息合计

  private String salary = null; // 余额
  
  private String outOrgId = null; // 转出单位
  
  private String inOrgId = null;//转入单位
  private String typetran="";

  private String headId = "";
  
  private String noteNum="";
  
  private Integer tranin_empId ;

  private Map map = null;

  private String yesNo = null;
  
  private String tranReason = "";

  public String getTranReason() {
    return tranReason;
  }

  public void setTranReason(String tranReason) {
    this.tranReason = tranReason;
  }

  public String getYesNo() {
    return yesNo;
  }

  public void setYesNo(String yesNo) {
    this.yesNo = yesNo;
  }

  public Map getMap() {
    return map;
  }

  public void setMap(Map map) {
    this.map = map;
  }

  public String getSalary() {
    return salary;
  }

  public void setSalary(String salary) {
    this.salary = salary;
  }

  public Map getSexMap() {
    return sexMap;
  }

  public static long getSerialVersionUID() {
    return serialVersionUID;
  }

  public String getCard_kind() {
    return card_kind;
  }

  public void setCard_kind(String card_kind) {
    this.card_kind = card_kind;
  }

  public String getCard_num() {
    return card_num;
  }

  public void setCard_num(String card_num) {
    this.card_num = card_num;
  }

  public String getCurBalance() {
    return curBalance;
  }

  public void setCurBalance(String curBalance) {
    this.curBalance = curBalance;
  }

  public String getEmpid() {
    return empid;
  }

  public void setEmpid(String empid) {
    this.empid = empid;
  }

  public String getEmpname() {
    return empname;
  }

  public void setEmpname(String empname) {
    this.empname = empname;
  }

  public String getPreBalance() {
    return preBalance;
  }

  public void setPreBalance(String preBalance) {
    this.preBalance = preBalance;
  }


  public Emp getEmp() {
    return emp;
  }

  public void setEmp(Emp emp) {
    this.emp = emp;
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

  public String getTransum() {
    return transum;
  }

  public void setTransum(String transum) {
    this.transum = transum;
  }

  public void reset(ActionMapping mapping, HttpServletRequest request) {
    super.reset(mapping, request);
    this.type = null;
    this.empid = null;
    this.empname = null;
    this.card_kind = null;
    this.card_num = null;
    this.preBalance = null;
    this.curBalance = null;
    this.preInterest = null;
    this.curInterest = null;
    this.transum = null;
    this.sumInterest = null;
    this.salary = null;
    this.map = null;
    this.yesNo = null;
    this.outOrgId = null;

  }

  public String getInOrgId() {
    return inOrgId;
  }

  public void setInOrgId(String inOrgId) {
    this.inOrgId = inOrgId;
  }

  public String getOutOrgId() {
    return outOrgId;
  }

  public void setOutOrgId(String outOrgId) {
    this.outOrgId = outOrgId;
  }


  public String getCurInterest() {
    return curInterest;
  }

  public void setCurInterest(String curInterest) {
    this.curInterest = curInterest;
  }

  public String getPreInterest() {
    return preInterest;
  }

  public void setPreInterest(String preInterest) {
    this.preInterest = preInterest;
  }

  public String getSumInterest() {
    return sumInterest;
  }

  public void setSumInterest(String sumInterest) {
    this.sumInterest = sumInterest;
  }

  public String getHeadId() {
    return headId;
  }

  public void setHeadId(String headId) {
    this.headId = headId;
  }

  public String getNoteNum() {
    return noteNum;
  }

  public void setNoteNum(String noteNum) {
    this.noteNum = noteNum;
  }

  public Integer getTranin_empId() {
    return tranin_empId;
  }

  public void setTranin_empId(Integer tranin_empId) {
    this.tranin_empId = tranin_empId;
  }

  public String getTypetran() {
    return typetran;
  }

  public void setTypetran(String typetran) {
    this.typetran = typetran;
  }




}
