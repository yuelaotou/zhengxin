package org.xpup.hafmis.syscollection.paymng.agent.dto;

import java.math.BigDecimal;

/**
 * Copy Right Information : 封装了代扣变更列表内容的DTO Goldsoft Project : AgentImpShowAC
 * 
 * @Version : v1.0
 * @author : 付云峰
 * @Date : 2007.12.17
 */
public class AgentChgListDTO {
  /** 代扣批号 */
  private String agentHeadId = "";

  /** 票据编号 */
  private String noteNum = "";

  /** 汇缴年月 */
  private String agentYearMonth = "";

  /** 单位数 */
  private Integer countOrg = new Integer(0);

  /** 职工数 */
  private Integer countEmp = new Integer(0);

  /** 单位总缴额 */
  private BigDecimal sumAgentOrgPay = new BigDecimal(0.00);

  /** 职工总缴额 */
  private BigDecimal sumAgentEmpPay = new BigDecimal(0.00);

  /** 职工总工资 */
  private BigDecimal sumAgentEmpSalary = new BigDecimal(0.00);

  /** 状态 */
  private String status = "";
  
  /** 状态(中文) */
  private String strStatus = "";
  
  /** 职工代扣清册id */
  private String empAgentId = "";
  
  /** 缴存方式 */
  private String payMode = "";
  
  //职工明细信息
  private String agentEmppopId = "";
  private String agentEmppopname = "";
  private String agentEmppopkouCode = "";
  private String agentEmppopCardID = "";
  private BigDecimal agentEmppoporgpay = new BigDecimal(0.00);
  private BigDecimal agentEmppopemppay = new BigDecimal(0.00);
  private BigDecimal agentEmppopmonthsalary = new BigDecimal(0.00);

  public String getAgentEmppopCardID() {
    return agentEmppopCardID;
  }

  public void setAgentEmppopCardID(String agentEmppopCardID) {
    this.agentEmppopCardID = agentEmppopCardID;
  }

  public BigDecimal getAgentEmppopemppay() {
    return agentEmppopemppay;
  }

  public void setAgentEmppopemppay(BigDecimal agentEmppopemppay) {
    this.agentEmppopemppay = agentEmppopemppay;
  }

  public String getAgentEmppopId() {
    return agentEmppopId;
  }

  public void setAgentEmppopId(String agentEmppopId) {
    this.agentEmppopId = agentEmppopId;
  }

  public String getAgentEmppopkouCode() {
    return agentEmppopkouCode;
  }

  public void setAgentEmppopkouCode(String agentEmppopkouCode) {
    this.agentEmppopkouCode = agentEmppopkouCode;
  }

  public BigDecimal getAgentEmppopmonthsalary() {
    return agentEmppopmonthsalary;
  }

  public void setAgentEmppopmonthsalary(BigDecimal agentEmppopmonthsalary) {
    this.agentEmppopmonthsalary = agentEmppopmonthsalary;
  }

  public String getAgentEmppopname() {
    return agentEmppopname;
  }

  public void setAgentEmppopname(String agentEmppopname) {
    this.agentEmppopname = agentEmppopname;
  }

  public BigDecimal getAgentEmppoporgpay() {
    return agentEmppoporgpay;
  }

  public void setAgentEmppoporgpay(BigDecimal agentEmppoporgpay) {
    this.agentEmppoporgpay = agentEmppoporgpay;
  }

  public String getAgentHeadId() {
    return agentHeadId;
  }

  public void setAgentHeadId(String agentHeadId) {
    this.agentHeadId = agentHeadId;
  }

  public String getAgentYearMonth() {
    return agentYearMonth;
  }

  public void setAgentYearMonth(String agentYearMonth) {
    this.agentYearMonth = agentYearMonth;
  }

  public Integer getCountEmp() {
    return countEmp;
  }

  public void setCountEmp(Integer countEmp) {
    this.countEmp = countEmp;
  }

  public Integer getCountOrg() {
    return countOrg;
  }

  public void setCountOrg(Integer countOrg) {
    this.countOrg = countOrg;
  }

  public String getNoteNum() {
    return noteNum;
  }

  public void setNoteNum(String noteNum) {
    this.noteNum = noteNum;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public BigDecimal getSumAgentEmpPay() {
    return sumAgentEmpPay;
  }

  public void setSumAgentEmpPay(BigDecimal sumAgentEmpPay) {
    this.sumAgentEmpPay = sumAgentEmpPay;
  }

  public BigDecimal getSumAgentEmpSalary() {
    return sumAgentEmpSalary;
  }

  public void setSumAgentEmpSalary(BigDecimal sumAgentEmpSalary) {
    this.sumAgentEmpSalary = sumAgentEmpSalary;
  }

  public BigDecimal getSumAgentOrgPay() {
    return sumAgentOrgPay;
  }

  public void setSumAgentOrgPay(BigDecimal sumAgentOrgPay) {
    this.sumAgentOrgPay = sumAgentOrgPay;
  }

  public String getEmpAgentId() {
    return empAgentId;
  }

  public void setEmpAgentId(String empAgentId) {
    this.empAgentId = empAgentId;
  }

  public String getStrStatus() {
    return strStatus;
  }

  public void setStrStatus(String strStatus) {
    this.strStatus = strStatus;
  }

  public String getPayMode() {
    return payMode;
  }

  public void setPayMode(String payMode) {
    this.payMode = payMode;
  }
}
