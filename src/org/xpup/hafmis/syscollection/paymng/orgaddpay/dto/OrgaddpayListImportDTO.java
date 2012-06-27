/*单位编号、单位名称、补缴年月、票据编号、职工编号、职工姓名、单位应缴金额、职工应缴金额、单位补缴金额、职工补缴金额、职工状态（单补的时候另一个置0）*/
package org.xpup.hafmis.syscollection.paymng.orgaddpay.dto;

import org.xpup.common.util.imp.domn.interfaces.impDto;


public class OrgaddpayListImportDTO extends impDto{

  private static final long serialVersionUID = 0L;
  
  private String empId="";
  
  private String  empName="";
   
  private String orgShouldpay="";
  
  private String empShouldpay ;
  
  private String orgAddpayMoney;
  
  private String empAddpayMoney;
  
  private String empPayStatus;
  
  private String endPayMonth;
  
  private String startPayMonth;

  private String salaryBase;
  
  private String orgRate;
  
  private String empRate;
  
  public String getEmpRate() {
    return empRate;
  }

  public void setEmpRate(String empRate) {
    this.empRate = empRate;
  }

  public String getOrgRate() {
    return orgRate;
  }

  public void setOrgRate(String orgRate) {
    this.orgRate = orgRate;
  }

  public String getSalaryBase() {
    return salaryBase;
  }

  public void setSalaryBase(String salaryBase) {
    this.salaryBase = salaryBase;
  }

  public String getEndPayMonth() {
    return endPayMonth;
  }

  public void setEndPayMonth(String endPayMonth) {
    this.endPayMonth = endPayMonth;
  }

  public String getStartPayMonth() {
    return startPayMonth;
  }

  public void setStartPayMonth(String startPayMonth) {
    this.startPayMonth = startPayMonth;
  }

  public String getEmpPayStatus() {
    return empPayStatus;
  }

  public void setEmpPayStatus(String empPayStatus) {
    this.empPayStatus = empPayStatus;
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

  public String getEmpShouldpay() {
    return empShouldpay;
  }

  public void setEmpShouldpay(String empShouldpay) {
    this.empShouldpay = empShouldpay;
  }
  public String getEmpAddpayMoney() {
    return empAddpayMoney;
  }

  public void setEmpAddpayMoney(String empAddpayMoney) {
    this.empAddpayMoney = empAddpayMoney;
  }

  public String getOrgAddpayMoney() {
    return orgAddpayMoney;
  }

  public void setOrgAddpayMoney(String orgAddpayMoney) {
    this.orgAddpayMoney = orgAddpayMoney;
  }

  public String getOrgShouldpay() {
    return orgShouldpay;
  }

  public void setOrgShouldpay(String orgShouldpay) {
    this.orgShouldpay = orgShouldpay;
  }
  
}
