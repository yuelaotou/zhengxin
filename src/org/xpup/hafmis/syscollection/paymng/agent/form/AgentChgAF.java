package org.xpup.hafmis.syscollection.paymng.agent.form;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.apache.struts.action.ActionForm;

/**
 * Copy Right Information : 代扣变更ActionForm Goldsoft Project : AgentChgAF
 * 
 * @Version : v1.0
 * @author : 付云峰
 * @Date : 2007.12.19
 */
public class AgentChgAF extends ActionForm {
  /** 代扣批号 */
  private String agentHeadId = "";

  /** 缴存年月 */
  private String agentYearMonth = "";

  /** 列表内容 */
  private List list;

  /** 判断按钮禁用的状态 */
  private String agentStatus;

  /** 单位明细显示：单位代扣号&单位ID */
  private String agentOrgpopId = "";

  private String agentOrgpopkouCode = "";

  private String agentEmppopId = "";

  private String agentEmppopname = "";

  private String agentEmppopkouCode = "";

  private String agentEmppopCardID = "";

  List emplist = new ArrayList();

  /** 合计的内容 */
  private Integer empCount = new Integer(0);

  private Integer orgCount = new Integer(0);

  private BigDecimal sumAgentOrgPay = new BigDecimal(0.00);

  private BigDecimal sumAgentEmpPay = new BigDecimal(0.00);

  private BigDecimal sumAgentEmpSalary = new BigDecimal(0.00);

  public List getEmplist() {
    return emplist;
  }

  public void setEmplist(List emplist) {
    this.emplist = emplist;
  }

  public String getAgentEmppopCardID() {
    return agentEmppopCardID;
  }

  public void setAgentEmppopCardID(String agentEmppopCardID) {
    this.agentEmppopCardID = agentEmppopCardID;
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

  public String getAgentEmppopname() {
    return agentEmppopname;
  }

  public void setAgentEmppopname(String agentEmppopname) {
    this.agentEmppopname = agentEmppopname;
  }

  public String getAgentOrgpopkouCode() {
    return agentOrgpopkouCode;
  }

  public void setAgentOrgpopkouCode(String agentOrgpopkouCode) {
    this.agentOrgpopkouCode = agentOrgpopkouCode;
  }

  public String getAgentOrgpopId() {
    return agentOrgpopId;
  }

  public void setAgentOrgpopId(String agentOrgpopId) {
    this.agentOrgpopId = agentOrgpopId;
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

  public List getList() {
    return list;
  }

  public void setList(List list) {
    this.list = list;
  }

  public Integer getEmpCount() {
    return empCount;
  }

  public void setEmpCount(Integer empCount) {
    this.empCount = empCount;
  }

  public BigDecimal getSumAgentEmpSalary() {
    return sumAgentEmpSalary;
  }

  public void setSumAgentEmpSalary(BigDecimal sumAgentEmpSalary) {
    this.sumAgentEmpSalary = sumAgentEmpSalary;
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

  public Integer getOrgCount() {
    return orgCount;
  }

  public void setOrgCount(Integer orgCount) {
    this.orgCount = orgCount;
  }

  public String getAgentStatus() {
    return agentStatus;
  }

  public void setAgentStatus(String agentStatus) {
    this.agentStatus = agentStatus;
  }
}
