package org.xpup.hafmis.sysloan.querystatistics.checkqueryplfn.dto;

import java.util.HashMap;
import java.util.Map;

public class CheckQueryPlFnTBFindDTO {
  private String officecode = "";// 办事处
  
  private String loanbank = "";// 归集银行

  private String empid = "";// 职工编号

  private String empname = "";// 职工姓名

  private String plbizst = "";// 个贷业务状态

  private Map plbizstMap = new HashMap();

  private String bizdateSt = "";// 发生日期开始
  
  private String bizdateEnd = "";// 发生日期开始

  private String fnbizst = "";// 财务业务状态
  
  private String contractId="";//合同编号

  private Map fnbizstMap = new HashMap();

  
  public String getBizdateEnd() {
    return bizdateEnd;
  }

  public void setBizdateEnd(String bizdateEnd) {
    this.bizdateEnd = bizdateEnd;
  }

  public String getBizdateSt() {
    return bizdateSt;
  }

  public void setBizdateSt(String bizdateSt) {
    this.bizdateSt = bizdateSt;
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

  public String getFnbizst() {
    return fnbizst;
  }

  public void setFnbizst(String fnbizst) {
    this.fnbizst = fnbizst;
  }

  public Map getFnbizstMap() {
    return fnbizstMap;
  }

  public void setFnbizstMap(Map fnbizstMap) {
    this.fnbizstMap = fnbizstMap;
  }

  public String getLoanbank() {
    return loanbank;
  }

  public void setLoanbank(String loanbank) {
    this.loanbank = loanbank;
  }

  public String getOfficecode() {
    return officecode;
  }

  public void setOfficecode(String officecode) {
    this.officecode = officecode;
  }

  public String getPlbizst() {
    return plbizst;
  }

  public void setPlbizst(String plbizst) {
    this.plbizst = plbizst;
  }

  public Map getPlbizstMap() {
    return plbizstMap;
  }

  public void setPlbizstMap(Map plbizstMap) {
    this.plbizstMap = plbizstMap;
  }

  public String getContractId() {
    return contractId;
  }

  public void setContractId(String contractId) {
    this.contractId = contractId;
  }
}
