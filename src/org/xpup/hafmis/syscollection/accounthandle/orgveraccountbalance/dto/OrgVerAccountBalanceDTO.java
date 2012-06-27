/**
 * Copy Right Information   : Goldsoft 
 * Project                  : OrgVerAccountBalanceDTO
 * @Version                 : 1.0
 * @author                  : wangy
 * 生成日期                   :2007-12-19
 **/
package org.xpup.hafmis.syscollection.accounthandle.orgveraccountbalance.dto;

import java.math.BigDecimal;

public class OrgVerAccountBalanceDTO {

  private String id = null;

  private String empId = "";// 职工编号
  
  private String empName = "";// 职工姓名
  
  private BigDecimal preBalanceCen = new BigDecimal(0.00);// 中心往年余额
  
  private BigDecimal curBalanceCen = new BigDecimal(0.00);// 中心本年余额
  
  private BigDecimal preBalanceOrg = new BigDecimal(0.00);// 单位往年余额
  
  private BigDecimal curBalanceOrg = new BigDecimal(0.00);// 单位本年余额
  
  private BigDecimal preInterest = new BigDecimal(0.00);// 单位往年利息
  
  private BigDecimal curInterest = new BigDecimal(0.00);// 单位本年利息
  
  private BigDecimal orgPaySum = new BigDecimal(0.00);// SUM单位缴额
  
  private BigDecimal empPaySum = new BigDecimal(0.00);// SUM职工缴额
  
  private String orgId = "";// 单位编号
  
  private String orgName = "";// 单位姓名
  
  private String accYear = "";// 结转年度

  public String getAccYear() {
    return accYear;
  }

  public void setAccYear(String accYear) {
    this.accYear = accYear;
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

  public BigDecimal getCurBalanceCen() {
    return curBalanceCen;
  }

  public void setCurBalanceCen(BigDecimal curBalanceCen) {
    this.curBalanceCen = curBalanceCen;
  }

  public BigDecimal getCurBalanceOrg() {
    return curBalanceOrg;
  }

  public void setCurBalanceOrg(BigDecimal curBalanceOrg) {
    this.curBalanceOrg = curBalanceOrg;
  }

  public String getEmpId() {
    return empId;
  }

  public void setEmpId(String empId) {
    this.empId = empId;
  }

  public String getEmpName() {
    return empName;
  }

  public void setEmpName(String empName) {
    this.empName = empName;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public BigDecimal getPreBalanceCen() {
    return preBalanceCen;
  }

  public void setPreBalanceCen(BigDecimal preBalanceCen) {
    this.preBalanceCen = preBalanceCen;
  }

  public BigDecimal getPreBalanceOrg() {
    return preBalanceOrg;
  }

  public void setPreBalanceOrg(BigDecimal preBalanceOrg) {
    this.preBalanceOrg = preBalanceOrg;
  }

  public BigDecimal getCurInterest() {
    return curInterest;
  }

  public void setCurInterest(BigDecimal curInterest) {
    this.curInterest = curInterest;
  }

  public BigDecimal getPreInterest() {
    return preInterest;
  }

  public void setPreInterest(BigDecimal preInterest) {
    this.preInterest = preInterest;
  }

  public BigDecimal getEmpPaySum() {
    return empPaySum;
  }

  public void setEmpPaySum(BigDecimal empPaySum) {
    this.empPaySum = empPaySum;
  }

  public BigDecimal getOrgPaySum() {
    return orgPaySum;
  }

  public void setOrgPaySum(BigDecimal orgPaySum) {
    this.orgPaySum = orgPaySum;
  }


  
}
