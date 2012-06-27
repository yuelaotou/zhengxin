package org.xpup.hafmis.syscollection.querystatistics.chgbiz.chgperson.from;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
/**
 * 
 * @author 王玲
 *2007-7-23
 */
public class ChgpersonEmpListAF extends ActionForm{

  public void reset(ActionMapping arg0, HttpServletRequest arg1) {

    this.orgId = "";//单位编号
    this.orgName = "";// 单位名称
    this.empId="";//职工编号
    this.empName="";//职工名称
    this.chgMonthStart = "";//变更年月开始
    this.chgMonthEnd = "";//变更年月结束
    this.chgDateStart = "";//变更日期开始
    this.chgDateEnd = "";//变更日期结束
    this.list=null;
  }
  private static final long serialVersionUID = 1L;

  private String orgId = "";//单位编号
  private String orgName = "";// 单位名称
  private String empId="";//职工编号
  private String empName="";//职工名称
  private String chgMonthStart = "";//变更年月开始
  private String chgMonthEnd = "";//变更年月结束
  private String chgDateStart = "";//变更日期开始
  private String chgDateEnd = "";//变更日期结束
  private List list=null;
  
  public String getChgDateEnd() {
    return chgDateEnd;
  }
  public void setChgDateEnd(String chgDateEnd) {
    this.chgDateEnd = chgDateEnd;
  }
  public String getChgDateStart() {
    return chgDateStart;
  }
  public void setChgDateStart(String chgDateStart) {
    this.chgDateStart = chgDateStart;
  }
  public String getChgMonthEnd() {
    return chgMonthEnd;
  }
  public void setChgMonthEnd(String chgMonthEnd) {
    this.chgMonthEnd = chgMonthEnd;
  }
  public String getChgMonthStart() {
    return chgMonthStart;
  }
  public void setChgMonthStart(String chgMonthStart) {
    this.chgMonthStart = chgMonthStart;
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

}
