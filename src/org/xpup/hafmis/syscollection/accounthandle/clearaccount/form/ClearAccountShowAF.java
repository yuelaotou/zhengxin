package org.xpup.hafmis.syscollection.accounthandle.clearaccount.form;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.struts.validator.ValidatorActionForm;

/**
 * @author 李鹏 2007-6-27
 */
public class ClearAccountShowAF extends ValidatorActionForm {

  private static final long serialVersionUID = 0L;

  private Integer totalPeople = new Integer(0);// 合计人数

  private BigDecimal totalMoney = new BigDecimal(0.00);// 合计金额

  private BigDecimal totalInterest = new BigDecimal(0.00);// 合计利息

  private String flowid = "";

  private String orgName = "";

  private String orgId = "";

  private Map operator = new HashMap();

  private Map bis_Status = new HashMap();

  private Map bis_type = new HashMap();

  private Map bank = new HashMap();

  private Integer bisid = new Integer(0);// 合计金额

  private String operator1 = "";

  private String bis_Status1 = "";

  private String bis_type1 = "";

  private String bank1 = "";

  private String docNum = "";

  private String noteNum = "";// 结算号

  private String type;// 按钮控制

  private BigDecimal credit = new BigDecimal(0.00);

  private BigDecimal debit = new BigDecimal(0.00);

  private List list; // 显示列表

  public String getNoteNum() {
    return noteNum;
  }

  public void setNoteNum(String noteNum) {
    this.noteNum = noteNum;
  }

  public List getList() {
    return list;
  }

  public void setList(List list) {
    this.list = list;
  }

  public BigDecimal getTotalInterest() {
    return totalInterest;
  }

  public void setTotalInterest(BigDecimal totalInterest) {
    this.totalInterest = totalInterest;
  }

  public BigDecimal getTotalMoney() {
    return totalMoney;
  }

  public void setTotalMoney(BigDecimal totalMoney) {
    this.totalMoney = totalMoney;
  }

  public Integer getTotalPeople() {
    return totalPeople;
  }

  public void setTotalPeople(Integer totalPeople) {
    this.totalPeople = totalPeople;
  }

  public Map getBank() {
    return bank;
  }

  public void setBank(Map bank) {
    this.bank = bank;
  }

  public Map getBis_Status() {
    return bis_Status;
  }

  public void setBis_Status(Map bis_Status) {
    this.bis_Status = bis_Status;
  }

  public Map getBis_type() {
    return bis_type;
  }

  public void setBis_type(Map bis_type) {
    this.bis_type = bis_type;
  }

  public String getDocNum() {
    return docNum;
  }

  public void setDocNum(String docNum) {
    this.docNum = docNum;
  }

  public Map getOperator() {
    return operator;
  }

  public void setOperator(Map operator) {
    this.operator = operator;
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

  public String getBank1() {
    return bank1;
  }

  public void setBank1(String bank1) {
    this.bank1 = bank1;
  }

  public String getBis_Status1() {
    return bis_Status1;
  }

  public void setBis_Status1(String bis_Status1) {
    this.bis_Status1 = bis_Status1;
  }

  public String getBis_type1() {
    return bis_type1;
  }

  public void setBis_type1(String bis_type1) {
    this.bis_type1 = bis_type1;
  }

  public String getOperator1() {
    return operator1;
  }

  public void setOperator1(String operator1) {
    this.operator1 = operator1;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public Integer getBisid() {
    return bisid;
  }

  public void setBisid(Integer bisid) {
    this.bisid = bisid;
  }

  public String getFlowid() {
    return flowid;
  }

  public void setFlowid(String flowid) {
    this.flowid = flowid;
  }

  public BigDecimal getCredit() {
    return credit;
  }

  public void setCredit(BigDecimal credit) {
    this.credit = credit;
  }

  public BigDecimal getDebit() {
    return debit;
  }

  public void setDebit(BigDecimal debit) {
    this.debit = debit;
  }

}
