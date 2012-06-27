/**
 * Copy Right Information   : Goldsoft 
 * Project                  : OrgVerAccountBalanceAF
 * @Version                 : 1.0
 * @author                  : wangy
 * 生成日期                   :2007-12-19
 **/
package org.xpup.hafmis.syscollection.accounthandle.orgveraccountbalance.form;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.apache.struts.action.ActionForm;

public class OrgVerAccountBalanceAF extends ActionForm {

  private static final long serialVersionUID = -1619275823308491522L;
  
  private List list = null;

  private String id = null;
  
  private String orgId = "";// 单位编号
  
  private String orgName = "";// 单位姓名
  
  private String accYear = "";// 结转年度

  private List accYearList = new ArrayList();// 结转年度下拉框
  
  private String empId = "";// 职工编号
  
  private String empName = "";// 职工姓名
  
  private BigDecimal preBalanceCen = new BigDecimal(0.00);// 中心往年余额
  
  private BigDecimal curBalanceCen = new BigDecimal(0.00);// 中心本年余额
  
  private BigDecimal preBalanceOrg = new BigDecimal(0.00);// 单位往年余额
  
  private BigDecimal curBalanceOrg = new BigDecimal(0.00);// 单位本年余额

  public String getAccYear() {
    return accYear;
  }

  public void setAccYear(String accYear) {
    this.accYear = accYear;
  }

  public List getAccYearList() {
    return accYearList;
  }

  public void setAccYearList(List accYearList) {
    this.accYearList = accYearList;
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

  public List getList() {
    return list;
  }

  public void setList(List list) {
    this.list = list;
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

  
}
