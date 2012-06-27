/*变更类型  职工编号  职工姓名  证件号码 变更后职工状态 工资基数 单位缴额 职工缴额 缴额合计 变更原因 、*//*单位编号、单位名称、调整年月、*/
//吴洪涛2008616
package org.xpup.hafmis.syscollection.chgbiz.chgperson.dto;

import org.xpup.common.util.exp.domn.interfaces.ExpDto;

public class ChgPersonCellListListExportDTO implements ExpDto {

  private static final long serialVersionUID = 0L;

  private String chgType;// 变更类型

  private String empId;// 职工编号

  private String name;// 职工姓名

  private String cardNum;// 证件号码

  private String temp_oldPayStatus;// 变更后职工状态

  private String salaryBase;// 工资基数

  private String orgPay;// 单位缴额

  private String empPay;// 职工缴额

  private String sumPay;// 缴额合计

  private String temp_chgreason;// 变更原因

  private String orgId;

  private String orgName;

  private String chgMonth;

  public String getChgMonth() {
    return chgMonth;
  }

  public void setChgMonth(String chgMonth) {
    this.chgMonth = chgMonth;
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

  public String getEmpPay() {
    return empPay;
  }

  public void setEmpPay(String empPay) {
    this.empPay = empPay;
  }

  public String getOrgPay() {
    return orgPay;
  }

  public void setOrgPay(String orgPay) {
    this.orgPay = orgPay;
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

  public String getSalaryBase() {
    return salaryBase;
  }

  public void setSalaryBase(String salaryBase) {
    this.salaryBase = salaryBase;
  }

  public String getInfo(String s) {
    if (s.equals("empId"))
      return empId;
    if (s.equals("name"))
      return name;
    if (s.equals("cardNum"))
      return cardNum;

    if (s.equals("temp_oldPayStatus"))
      return temp_oldPayStatus;
    if (s.equals("salaryBase"))
      return salaryBase;
    if (s.equals("temp_chgreason"))
      return temp_chgreason;

    if (s.equals("salaryBase"))
      return salaryBase;
    if (s.equals("orgPay"))
      return orgPay;
    if (s.equals("empPay"))
      return empPay;
    if (s.equals("chgType"))
      return chgType;
    if (s.equals("sumPay"))
      return sumPay;
    
    else
      return null;
  }

  public String getChgType() {
    return chgType;
  }

  public void setChgType(String chgType) {
    this.chgType = chgType;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getSumPay() {
    return sumPay;
  }

  public void setSumPay(String sumPay) {
    this.sumPay = sumPay;
  }

  public String getTemp_chgreason() {
    return temp_chgreason;
  }

  public void setTemp_chgreason(String temp_chgreason) {
    this.temp_chgreason = temp_chgreason;
  }

  public String getTemp_oldPayStatus() {
    return temp_oldPayStatus;
  }

  public void setTemp_oldPayStatus(String temp_oldPayStatus) {
    this.temp_oldPayStatus = temp_oldPayStatus;
  }

}
