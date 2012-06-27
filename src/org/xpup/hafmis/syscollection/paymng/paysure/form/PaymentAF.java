package org.xpup.hafmis.syscollection.paymng.paysure.form;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.xpup.hafmis.syscollection.common.domain.entity.PaymentHead;

/**
 * @author 于庆丰 2007-06-28
 */
public class PaymentAF extends ActionForm {

  private List list = null;

  private Map map = null;

  private Map other_map = null;

  private String id = "";

  private String orgId = "";// 部门ID

  private String orgName = "";// 部门名称

  private String noteNum = "";// 票据编号

  private String docNum = "";// 凭证编号

//  private BigDecimal payMoney = new BigDecimal(0);// 缴存金额
  
  private String payMoney = "";// 缴存金额

  private String settDate = "";// 业务日期
  private String settDate1 = "";// 业务日期

  private Integer payStatus = new Integer(0);// 缴存状态(业务状态)

  private String payType = "";// 缴存类型(业务类型)

  private BigDecimal sumPayMoney = new BigDecimal(0);// 汇缴金额合计
  
  private String reason = "";//挂账原因
  
  private BigDecimal balance = new BigDecimal(0);//挂账余额
  
  private BigDecimal money = new BigDecimal(0);//挂账金额
  
  private BigDecimal orgAddPaySum = new BigDecimal(0);
  
  private BigDecimal empAddPaySum = new BigDecimal(0);
  
  private BigDecimal orgEmpPaySum = new BigDecimal(0);
  
  private Integer count = new Integer(0);
  
  private BigDecimal AddPayAmount = new BigDecimal(0);

  public BigDecimal getAddPayAmount() {
    return AddPayAmount;
  }

  public void setAddPayAmount(BigDecimal addPayAmount) {
    AddPayAmount = addPayAmount;
  }

  public Integer getCount() {
    return count;
  }

  public void setCount(Integer count) {
    this.count = count;
  }

  public BigDecimal getMoney() {
    return money;
  }

  public void setMoney(BigDecimal money) {
    this.money = money;
  }

  public BigDecimal getBalance() {
    return balance;
  }

  public void setBalance(BigDecimal balance) {
    this.balance = balance;
  }

  

  public String getReason() {
    return reason;
  }

  public void setReason(String reason) {
    this.reason = reason;
  }

  public BigDecimal getSumPayMoney() {
    return sumPayMoney;
  }

  public void setSumPayMoney(BigDecimal sumPayMoney) {
    this.sumPayMoney = sumPayMoney;
  }

  public String getDocNum() {
    return docNum;
  }

  public void setDocNum(String docNum) {
    this.docNum = docNum;
  }

  public List getList() {
    return list;
  }

  public void setList(List list) {
    this.list = list;
  }

  public String getNoteNum() {
    return noteNum;
  }

  public void setNoteNum(String noteNum) {
    this.noteNum = noteNum;
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

//  public BigDecimal getPayMoney() {
//    return payMoney;
//  }
//
//  public void setPayMoney(BigDecimal payMoney) {
//    this.payMoney = payMoney;
//  }

  public Integer getPayStatus() {
    return payStatus;
  }

  public void setPayStatus(Integer payStatus) {
    this.payStatus = payStatus;
  }

  public String getPayType() {
    return payType;
  }

  public void setPayType(String payType) {
    this.payType = payType;
  }

  public String getSettDate() {
    return settDate;
  }

  public void setSettDate(String settDate) {
    this.settDate = settDate;
  }

  public Map getMap() {
    return map;
  }

  public void setMap(Map map) {
    this.map = map;
  }

  public Map getOther_map() {
    return other_map;
  }

  public void setOther_map(Map other_map) {
    this.other_map = other_map;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public void reset(ActionMapping mapping,HttpServletRequest request) {
    // TODO Auto-generated method stub
    
    orgId = "";// 部门ID

    orgName = "";// 部门名称

    noteNum = "";// 票据编号

    docNum = "";// 凭证编号

    payMoney = "";// 缴存金额

    settDate = "";// 业务日期

    payStatus = null;// 缴存状态(业务状态)

    payType = null;// 缴存类型(业务类型)

    //sumPayMoney = new BigDecimal(0);
    
   // this.reason = "";//挂账原因
    
   // this.balance = new BigDecimal(0);//挂账余额
    
   // this.money = new BigDecimal(0);//挂账金额
  }

  public BigDecimal getEmpAddPaySum() {
    return empAddPaySum;
  }

  public void setEmpAddPaySum(BigDecimal empAddPaySum) {
    this.empAddPaySum = empAddPaySum;
  }

  public BigDecimal getOrgAddPaySum() {
    return orgAddPaySum;
  }

  public void setOrgAddPaySum(BigDecimal orgAddPaySum) {
    this.orgAddPaySum = orgAddPaySum;
  }

  public BigDecimal getOrgEmpPaySum() {
    return orgEmpPaySum;
  }

  public void setOrgEmpPaySum(BigDecimal orgEmpPaySum) {
    this.orgEmpPaySum = orgEmpPaySum;
  }

  public String getPayMoney() {
    return payMoney;
  }

  public void setPayMoney(String payMoney) {
    this.payMoney = payMoney;
  }

  public String getSettDate1() {
    return settDate1;
  }

  public void setSettDate1(String settDate1) {
    this.settDate1 = settDate1;
  }

}
