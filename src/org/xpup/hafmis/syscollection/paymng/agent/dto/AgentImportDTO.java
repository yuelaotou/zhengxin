package org.xpup.hafmis.syscollection.paymng.agent.dto;

import java.math.BigDecimal;

import org.xpup.common.util.imp.domn.interfaces.impDto;

/**
 * Copy Right Information : 代扣导入列表DTO Goldsoft Project : AgentImportDTO
 * 
 * @Version : v1.0
 * @author : 付云峰
 * @Date : 2007.12.17
 */
public class AgentImportDTO extends impDto {
  /** 单位代扣号 */
  private String orgAgentNum = "";

  /** 单位名称 */
  private String orgName = "";

  /** 职工代扣号 */
  private String empAgentNum = "";

  /** 职工姓名 */
  private String empName = "";

  /** 身份证 */
  private String cardNum = "";

  /** 单位缴额 */
  private String agentOrgPay = "";

  /** 职工缴额 */
  private String agentEmpPay = "";

  /** 工资 */
  private String agentEmpSalary = "";


  
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

  public String getOrgAgentNum() {
    return orgAgentNum;
  }

  public void setOrgAgentNum(String orgAgentNum) {
    this.orgAgentNum = orgAgentNum;
  }

  public String getOrgName() {
    return orgName;
  }

  public void setOrgName(String orgName) {
    this.orgName = orgName;
  }

  public String getAgentEmpPay() {
    return agentEmpPay;
  }

  public void setAgentEmpPay(String agentEmpPay) {
    this.agentEmpPay = agentEmpPay;
  }

  public String getAgentEmpSalary() {
    return agentEmpSalary;
  }

  public void setAgentEmpSalary(String agentEmpSalary) {
    this.agentEmpSalary = agentEmpSalary;
  }

  public String getAgentOrgPay() {
    return agentOrgPay;
  }

  public void setAgentOrgPay(String agentOrgPay) {
    this.agentOrgPay = agentOrgPay;
  }
}
