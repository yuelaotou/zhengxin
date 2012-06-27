package org.xpup.hafmis.syscollection.querystatistics.collfncomparison.form;

import java.util.List;

import org.apache.struts.action.ActionForm;

public class CollFnComparisonEmpInfoAF extends ActionForm {
  /**
   * 单位编号
   */
  private String orgId = "";

  /**
   * 单位名称
   */
  private String orgName = "";

  /**
   * 职工编号
   */
  private String empId = "";

  /**
   * 职工姓名
   */
  private String empName = "";

  /**
   * 证件号码
   */
  private String cardNum = "";

  /**
   * 列表内容
   */
  private List list;

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

  public List getList() {
    return list;
  }

  public void setList(List list) {
    this.list = list;
  }

  public String getCardNum() {
    return cardNum;
  }

  public void setCardNum(String cardNum) {
    this.cardNum = cardNum;
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
}
