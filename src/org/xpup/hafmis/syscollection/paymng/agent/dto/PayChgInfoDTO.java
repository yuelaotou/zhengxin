package org.xpup.hafmis.syscollection.paymng.agent.dto;

import java.math.BigDecimal;

/**
 * Copy Right Information : 封装做过缴额调整职工信息的DTO Goldsoft Project : AgentImpShowAC
 * 
 * @Version : v1.0
 * @author : 付云峰
 * @Date : 2007.12.21
 */
public class PayChgInfoDTO {
  /** 缴额变更的头表id*/
  private String id = "";
  /** 职工编号 */
  private String empId = "";
  /** 工资基数 */
  private BigDecimal oldSalaryBase = new BigDecimal(0.00);
  /** 单位缴额 */
  private BigDecimal oldOrgPay = new BigDecimal(0.00);
  /** 职工基数 */
  private BigDecimal oldEmpPay = new BigDecimal(0.00);
  public String getEmpId() {
    return empId;
  }
  public void setEmpId(String empId) {
    this.empId = empId;
  }
  public BigDecimal getOldEmpPay() {
    return oldEmpPay;
  }
  public void setOldEmpPay(BigDecimal oldEmpPay) {
    this.oldEmpPay = oldEmpPay;
  }
  public BigDecimal getOldOrgPay() {
    return oldOrgPay;
  }
  public void setOldOrgPay(BigDecimal oldOrgPay) {
    this.oldOrgPay = oldOrgPay;
  }
  public BigDecimal getOldSalaryBase() {
    return oldSalaryBase;
  }
  public void setOldSalaryBase(BigDecimal oldSalaryBase) {
    this.oldSalaryBase = oldSalaryBase;
  }
  public String getId() {
    return id;
  }
  public void setId(String id) {
    this.id = id;
  }
  
}
