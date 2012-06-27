package org.xpup.hafmis.syscollection.paymng.monthpay.form;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletRequest;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.xpup.hafmis.syscollection.common.domain.entity.MonthPayment;
import org.xpup.hafmis.syscollection.common.domain.entity.MonthPaymentHead;
import org.xpup.hafmis.syscollection.common.domain.entity.MonthPaymentTail;
import org.xpup.hafmis.syscollection.common.domain.entity.Org;

public class MonthpayJYAF extends ActionForm {

  private Org org = new Org();

  private int payStatus;

  private List list;

  private String noteNum;
  private String name;
  private String orgid;
  private String paymentid;
  private String bankid;
  private String bankname;
  private String office;
  private String collbankid;
  private String collbankname;

  private String lackMonths="";
  private String booker;//制单人
  private String checker;//复核人
  private String accountant;//记账人
  private String orgPayMonth="";
  private String empPayMonth="";
  private String orgLackMoney="";//单位一共欠缴多少钱
  private String orgPayMoney=""; //单位正常缴应该缴多少钱
  private String empPayMoney=""; //职工正常缴应该缴多少钱
 

  // 应缴总额
  private BigDecimal sumPay = new BigDecimal(0.00);

  // 实缴总额
  private BigDecimal realPay = new BigDecimal(0.00);

  // 单位应缴总额
  private BigDecimal orgPay = new BigDecimal(0.00);

  // 职工应缴总额
  private BigDecimal empPay = new BigDecimal(0.00);

  // 超始年月+1
  private String inceptMonth;

  // 缴至年月
  private String payMonth;

  // 本月人数
  private Integer personCounts = new Integer(0);

  // 增加人数
  private Integer personCountsAdd = new Integer(0);

  // 减少人数
  private Integer personCountsLess = new Integer(0);

  // 上月人数
  private Integer ultimoPersonCounts = new Integer(0);

  // 本月金额
  private BigDecimal payMoney = new BigDecimal(0.00);

  // 增加金额
  private BigDecimal payMoneyAdd = new BigDecimal(0.00);

  // 减少金额
  private BigDecimal payMoneyLess = new BigDecimal(0.00);

  // 上月金额
  private BigDecimal ultimoPayMoney = new BigDecimal(0.00);
  
  private String untilMonth="";
  /** 挂账余额*/
  private BigDecimal overPay = new BigDecimal(0.00);
  /** 类型，用这个类型来判断缴存中是否有自动挂账功能*/
  private String type = "";
  
  private String monthpayHeadId="";
  
  //打印日期
  private String printDate="";
  
//  private String checkPerson="";
//  
//  private String clearPerson="";

  private String  payWay="";  // 缴存方式
  
  private String  payKind="";  // 缴存类别
  
  private Map pay_way=new HashMap();
  
  private Map pay_kind=new HashMap();
  
  private  String  receivables_orgname=""; //收款单位名称
 
  private  String receivables_bank_acc="";  //收款单位归集银行账号
  
  private String receivables_bank_name="";  // 收款单位归集银行名称
 
  private String payment_orgname="";   //  付款单位名称
  
  private String payment_bank_acc="";  //  付款单位开户银行账号
  
  private String payment_bank_name=""; // 付款单位开户银行名称
  
  private String docNum="";
  
  private String payStatus_chg = "";//职工状态为3、4
  
  private String payMonthCount="";
  
  public String getPayStatus_chg() {
    return payStatus_chg;
  }

  public void setPayStatus_chg(String payStatus_chg) {
    this.payStatus_chg = payStatus_chg;
  }

  public String getUntilMonth() {
    return untilMonth;
  }

  public void setUntilMonth(String untilMonth) {
    this.untilMonth = untilMonth;
  }

  public BigDecimal getEmpPay() {
    return empPay;
  }

  public void setEmpPay(BigDecimal empPay) {
    this.empPay = empPay;
  }

  public BigDecimal getOrgPay() {
    return orgPay;
  }

  public void setOrgPay(BigDecimal orgPay) {
    this.orgPay = orgPay;
  }

  public BigDecimal getPayMoney() {
    return payMoney;
  }

  public void setPayMoney(BigDecimal payMoney) {
    this.payMoney = payMoney;
  }

  public BigDecimal getPayMoneyAdd() {
    return payMoneyAdd;
  }

  public void setPayMoneyAdd(BigDecimal payMoneyAdd) {
    this.payMoneyAdd = payMoneyAdd;
  }

  public BigDecimal getPayMoneyLess() {
    return payMoneyLess;
  }

  public void setPayMoneyLess(BigDecimal payMoneyLess) {
    this.payMoneyLess = payMoneyLess;
  }

  public String getPayMonth() {
    return payMonth;
  }

  public void setPayMonth(String payMonth) {
    this.payMonth = payMonth;
  }

  public Integer getPersonCounts() {
    return personCounts;
  }

  public void setPersonCounts(Integer personCounts) {
    this.personCounts = personCounts;
  }

  public Integer getPersonCountsAdd() {
    return personCountsAdd;
  }

  public void setPersonCountsAdd(Integer personCountsAdd) {
    this.personCountsAdd = personCountsAdd;
  }

  public Integer getPersonCountsLess() {
    return personCountsLess;
  }

  public void setPersonCountsLess(Integer personCountsLess) {
    this.personCountsLess = personCountsLess;
  }

  public BigDecimal getUltimoPayMoney() {
    return ultimoPayMoney;
  }

  public void setUltimoPayMoney(BigDecimal ultimoPayMoney) {
    this.ultimoPayMoney = ultimoPayMoney;
  }

  public Integer getUltimoPersonCounts() {
    return ultimoPersonCounts;
  }

  public void setUltimoPersonCounts(Integer ultimoPersonCounts) {
    this.ultimoPersonCounts = ultimoPersonCounts;
  }

  public BigDecimal getSumPay() {
    return sumPay;
  }

  public void setSumPay(BigDecimal sumPay) {
    this.sumPay = sumPay;
  }

  public List getList() {
    return list;
  }

  public void setList(List list) {
    this.list = list;
  }

  public int getPayStatus() {
    return payStatus;
  }

  public void setPayStatus(int payStatus) {
    this.payStatus = payStatus;
  }

  public String getInceptMonth() {
    return inceptMonth;
  }

  public void setInceptMonth(String inceptMonth) {
    this.inceptMonth = inceptMonth;
  }

  public Org getOrg() {
    return org;
  }

  public void setOrg(Org org) {
    this.org = org;
  }

  public String getNoteNum() {
    return noteNum;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public void setNoteNum(String noteNum) {
    this.noteNum = noteNum;
  }
  public void reset(ActionMapping mapping, ServletRequest request) {
    this.empPay = null;
    this.inceptMonth ="";
    this.noteNum = "";
    this.orgPay = null;
    this.payMoney = null;
    this.payMoneyAdd = null;
    this.payMoneyLess = null;
    this.payMonth = "";
    this.payStatus =1;
    this.personCounts = null;
    this.personCountsAdd = null;
    this.personCountsLess = null;
    this.realPay = null;
    this.sumPay = null;
    this.ultimoPayMoney = null;
    this.ultimoPersonCounts = null;
    this.name = "";
  }

  public BigDecimal getRealPay() {
    return realPay;
  }

  public void setRealPay(BigDecimal realPay) {
    this.realPay = realPay;
  }

  public String getOrgid() {
    return orgid;
  }

  public void setOrgid(String orgid) {
    this.orgid = orgid;
  }

  public String getPaymentid() {
    return paymentid;
  }

  public void setPaymentid(String paymentid) {
    this.paymentid = paymentid;
  }

  public String getBankid() {
    return bankid;
  }

  public void setBankid(String bankid) {
    this.bankid = bankid;
  }

  public String getBankname() {
    return bankname;
  }

  public void setBankname(String bankname) {
    this.bankname = bankname;
  }

  public String getCollbankid() {
    return collbankid;
  }

  public void setCollbankid(String collbankid) {
    this.collbankid = collbankid;
  }

  public String getCollbankname() {
    return collbankname;
  }

  public void setCollbankname(String collbankname) {
    this.collbankname = collbankname;
  }

  public String getOffice() {
    return office;
  }

  public void setOffice(String office) {
    this.office = office;
  }

  public String getPrintDate() {
    return printDate;
  }

  public void setPrintDate(String printDate) {
    this.printDate = printDate;
  }

  public String getAccountant() {
    return accountant;
  }

  public void setAccountant(String accountant) {
    this.accountant = accountant;
  }

  public String getBooker() {
    return booker;
  }

  public void setBooker(String booker) {
    this.booker = booker;
  }

  public String getChecker() {
    return checker;
  }

  public void setChecker(String checker) {
    this.checker = checker;
  }

  public String getEmpPayMoney() {
    return empPayMoney;
  }

  public void setEmpPayMoney(String empPayMoney) {
    this.empPayMoney = empPayMoney;
  }

  public String getEmpPayMonth() {
    return empPayMonth;
  }

  public void setEmpPayMonth(String empPayMonth) {
    this.empPayMonth = empPayMonth;
  }

  public String getLackMonths() {
    return lackMonths;
  }

  public void setLackMonths(String lackMonths) {
    this.lackMonths = lackMonths;
  }

  public String getOrgLackMoney() {
    return orgLackMoney;
  }

  public void setOrgLackMoney(String orgLackMoney) {
    this.orgLackMoney = orgLackMoney;
  }

  public String getOrgPayMoney() {
    return orgPayMoney;
  }

  public void setOrgPayMoney(String orgPayMoney) {
    this.orgPayMoney = orgPayMoney;
  }

  public String getOrgPayMonth() {
    return orgPayMonth;
  }

  public void setOrgPayMonth(String orgPayMonth) {
    this.orgPayMonth = orgPayMonth;
  }

  public BigDecimal getOverPay() {
    return overPay;
  }

  public void setOverPay(BigDecimal overPay) {
    this.overPay = overPay;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public Map getPay_kind() {
    return pay_kind;
  }

  public void setPay_kind(Map pay_kind) {
    this.pay_kind = pay_kind;
  }

  public Map getPay_way() {
    return pay_way;
  }

  public void setPay_way(Map pay_way) {
    this.pay_way = pay_way;
  }

  public String getPayKind() {
    return payKind;
  }

  public void setPayKind(String payKind) {
    this.payKind = payKind;
  }

  public String getPayWay() {
    return payWay;
  }

  public void setPayWay(String payWay) {
    this.payWay = payWay;
  }

  public String getPayment_bank_acc() {
    return payment_bank_acc;
  }

  public void setPayment_bank_acc(String payment_bank_acc) {
    this.payment_bank_acc = payment_bank_acc;
  }

  public String getPayment_bank_name() {
    return payment_bank_name;
  }

  public void setPayment_bank_name(String payment_bank_name) {
    this.payment_bank_name = payment_bank_name;
  }

  public String getReceivables_bank_acc() {
    return receivables_bank_acc;
  }

  public void setReceivables_bank_acc(String receivables_bank_acc) {
    this.receivables_bank_acc = receivables_bank_acc;
  }

  public String getReceivables_bank_name() {
    return receivables_bank_name;
  }

  public void setReceivables_bank_name(String receivables_bank_name) {
    this.receivables_bank_name = receivables_bank_name;
  }

  public String getPayment_orgname() {
    return payment_orgname;
  }

  public void setPayment_orgname(String payment_orgname) {
    this.payment_orgname = payment_orgname;
  }

  public String getReceivables_orgname() {
    return receivables_orgname;
  }

  public void setReceivables_orgname(String receivables_orgname) {
    this.receivables_orgname = receivables_orgname;
  }

  public String getDocNum() {
    return docNum;
  }

  public void setDocNum(String docNum) {
    this.docNum = docNum;
  }

  public String getPayMonthCount() {
    return payMonthCount;
  }

  public void setPayMonthCount(String payMonthCount) {
    this.payMonthCount = payMonthCount;
  }

  public String getMonthpayHeadId() {
    return monthpayHeadId;
  }

  public void setMonthpayHeadId(String monthpayHeadId) {
    this.monthpayHeadId = monthpayHeadId;
  }



//  public String getCheckPerson() {
//    return checkPerson;
//  }
//
//  public void setCheckPerson(String checkPerson) {
//    this.checkPerson = checkPerson;
//  }
//
//  public String getClearPerson() {
//    return clearPerson;
//  }
//
//  public void setClearPerson(String clearPerson) {
//    this.clearPerson = clearPerson;
//  }

}