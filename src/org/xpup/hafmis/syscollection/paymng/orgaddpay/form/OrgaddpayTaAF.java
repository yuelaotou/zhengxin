package org.xpup.hafmis.syscollection.paymng.orgaddpay.form;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletRequest;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.xpup.hafmis.syscollection.common.domain.entity.MonthPaymentHead;


public class OrgaddpayTaAF extends ActionForm{
  private static final long serialVersionUID = 0L;
  private MonthPaymentHead monthPaymentHead = new MonthPaymentHead();
  private String orgid="";
  private String name="";
  private List list;
  private String noteNum = "";
  private String listCount = "";
  private String payMonth="";
  private String docNum = "";
  private String type="3";
  private String startPayMonth="";
  
  private String statetype="";
  private String org_head_id="";
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
  /** 挂账余额*/
  private BigDecimal overPay = new BigDecimal(0.00);
  
  private String personCount = "";//人数
  
  public String getPersonCount() {
    return personCount;
  }

  public void setPersonCount(String personCount) {
    this.personCount = personCount;
  }

  public BigDecimal getOverPay() {
    return overPay;
  }

  public void setOverPay(BigDecimal overPay) {
    this.overPay = overPay;
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

  public String getPayment_orgname() {
    return payment_orgname;
  }

  public void setPayment_orgname(String payment_orgname) {
    this.payment_orgname = payment_orgname;
  }

  public String getPayWay() {
    return payWay;
  }

  public void setPayWay(String payWay) {
    this.payWay = payWay;
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

  public String getReceivables_orgname() {
    return receivables_orgname;
  }

  public void setReceivables_orgname(String receivables_orgname) {
    this.receivables_orgname = receivables_orgname;
  }

  public String getStartPayMonth() {
    return startPayMonth;
  }

  public void setStartPayMonth(String startPayMonth) {
    this.startPayMonth = startPayMonth;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public String getDocNum() {
    return docNum;
  }

  public void setDocNum(String docNum) {
    this.docNum = docNum;
  }

  public void setPayMonth(String payMonth) {
    this.payMonth = payMonth;
  }

  public String getPayMonth() {
    return payMonth;
  }

  public  String getListCount() {
    return listCount;
  }

  public void setListCount(String listCount) {
    this.listCount = listCount;
  }

  public String getOrgid() {
    return orgid;
  }

  public void setOrgid(String orgid) {
    this.orgid = orgid;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public void reset(ActionMapping mapping, ServletRequest request) {
    this.orgid="";
    this.name="";
    this.noteNum="";
  }

  public List getList() {
    return list;
  }

  public void setList(List list) {
    this.list = list;
  }

  public MonthPaymentHead getMonthPaymentHead() {
    return monthPaymentHead;
  }

  public void setMonthPaymentHead(MonthPaymentHead monthPaymentHead) {
    this.monthPaymentHead = monthPaymentHead;
  }

  public String getNoteNum() {
    return noteNum;
  }

  public void setNoteNum(String noteNum) {
    this.noteNum = noteNum;
  }

  public String getStatetype() {
    return statetype;
  }

  public void setStatetype(String statetype) {
    this.statetype = statetype;
  }

  public String getOrg_head_id() {
    return org_head_id;
  }

  public void setOrg_head_id(String org_head_id) {
    this.org_head_id = org_head_id;
  }
  
}
