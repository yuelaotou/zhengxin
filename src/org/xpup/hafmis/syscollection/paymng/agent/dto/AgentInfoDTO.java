package org.xpup.hafmis.syscollection.paymng.agent.dto;

import java.math.BigDecimal;

/**
 * Copy Right Information : 代扣信息DTO Goldsoft Project : AgentInfoDTO
 * 
 * @Version : v1.0
 * @author : 付云峰
 * @Date : 2007.12.17
 */
public class AgentInfoDTO {
  /** 单位id */
  private String orgId = "";

  /** 单位名称 */
  private String orgName = "";

  /** 单位代扣号 */
  private String orgAgentNum = "";

  /** 单位代扣清册id */
  private String orgAgentId = "";

  /** 人数 */
  private Integer countEmpId = new Integer(0);

  /** 单位总缴额 */
  private BigDecimal sumAgentOrgPay = new BigDecimal(0.00);

  /** 职工总缴额 */
  private BigDecimal sumAgentEmpPay = new BigDecimal(0.00);

  /** 职工总工资 */
  private BigDecimal sumAgentEmpSalary = new BigDecimal(0.00);

  /** 职工id */
  private String empId = "";

  /** 职工代扣号 */
  private String empAgentNum = "";

  /** 职工姓名 */
  private String empName = "";

  /** 身份证 */
  private String cardNum = "";

  /** 单位缴额 */
  private BigDecimal agentOrgPay = new BigDecimal(0.00);

  /** 职工缴额 */
  private BigDecimal agentEmpPay = new BigDecimal(0.00);

  /** 职工工资 */
  private BigDecimal agentEmpSalary = new BigDecimal(0.00);
  
  /** 缴存方式 */
  private String payMode = "";

  public BigDecimal getSumAgentEmpSalary() {
    return sumAgentEmpSalary;
  }

  public void setSumAgentEmpSalary(BigDecimal sumAgentEmpSalary) {
    this.sumAgentEmpSalary = sumAgentEmpSalary;
  }

  public String getOrgAgentNum() {
    return orgAgentNum;
  }

  public void setOrgAgentNum(String orgAgentNum) {
    this.orgAgentNum = orgAgentNum;
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

  public BigDecimal getSumAgentEmpPay() {
    return sumAgentEmpPay;
  }

  public void setSumAgentEmpPay(BigDecimal sumAgentEmpPay) {
    this.sumAgentEmpPay = sumAgentEmpPay;
  }

  public BigDecimal getSumAgentOrgPay() {
    return sumAgentOrgPay;
  }

  public void setSumAgentOrgPay(BigDecimal sumAgentOrgPay) {
    this.sumAgentOrgPay = sumAgentOrgPay;
  }

  public Integer getCountEmpId() {
    return countEmpId;
  }

  public void setCountEmpId(Integer countEmpId) {
    this.countEmpId = countEmpId;
  }

  public String getOrgAgentId() {
    return orgAgentId;
  }

  public void setOrgAgentId(String orgAgentId) {
    this.orgAgentId = orgAgentId;
  }

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

  public String getPayMode() {
    return payMode;
  }

  public void setPayMode(String payMode) {
    this.payMode = payMode;
  }
}
