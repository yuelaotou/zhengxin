package org.xpup.hafmis.syscollection.common.domain.entity;

import java.io.Serializable;
import java.math.BigDecimal;

import org.xpup.hafmis.syscommon.domain.entity.DomainObject;

public class EmpAgentDetail extends DomainObject {
  private Serializable empAgentId;

  /** 职工代扣号 */
  private String empAgentNum = "";

  /** 职工姓名 */
  private String empName = "";

  /** 身份证 */
  private String cardNum = "";

  /** 代扣单位缴额 */
  private BigDecimal agentOrgPay = new BigDecimal(0.00);

  /** 代扣职工缴额 */
  private BigDecimal agentEmpPay = new BigDecimal(0.00);

  /** 职工工资 */
  private BigDecimal agentEmpSalary = new BigDecimal(0.00);

  /** 单位代扣清册id */
  private Integer orgAgentId = new Integer(0);

  public BigDecimal getAgentEmpPay() {
    return agentEmpPay;
  }

  public void setAgentEmpPay(BigDecimal agentEmpPay) {
    this.agentEmpPay = agentEmpPay;
  }

  public BigDecimal getAgentEmpSalary() {
    return agentEmpSalary;
  }

  public void setAgentEmpSalary(BigDecimal agentEmpSalary) {
    this.agentEmpSalary = agentEmpSalary;
  }

  public BigDecimal getAgentOrgPay() {
    return agentOrgPay;
  }

  public void setAgentOrgPay(BigDecimal agentOrgPay) {
    this.agentOrgPay = agentOrgPay;
  }

  public String getCardNum() {
    return cardNum;
  }

  public void setCardNum(String cardNum) {
    this.cardNum = cardNum;
  }

  public String getEmpAgentNum() {
    return empAgentNum;
  }

  public void setEmpAgentNum(String empAgentNum) {
    this.empAgentNum = empAgentNum;
  }

  public String getEmpName() {
    return empName;
  }

  public void setEmpName(String empName) {
    this.empName = empName;
  }

  public Integer getOrgAgentId() {
    return orgAgentId;
  }

  public void setOrgAgentId(Integer orgAgentId) {
    this.orgAgentId = orgAgentId;
  }

  public Serializable getEmpAgentId() {
    return empAgentId;
  }

  public void setEmpAgentId(Serializable empAgentId) {
    this.empAgentId = empAgentId;
  }
}
