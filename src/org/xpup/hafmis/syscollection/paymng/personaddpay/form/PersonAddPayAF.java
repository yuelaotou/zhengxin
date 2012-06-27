package org.xpup.hafmis.syscollection.paymng.personaddpay.form;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.validator.ValidatorActionForm;
import org.xpup.hafmis.common.form.IdAF;
import org.xpup.hafmis.syscollection.common.domain.entity.AddPayTail;
/**
 * 
 * @author 卢钢
 *2007-6-28
 */
public class PersonAddPayAF extends ActionForm{
  


  private static final long serialVersionUID = 1L;

  private List personAddPayList=new ArrayList();
  
  private BigDecimal orgPay=new BigDecimal(0.00);
  
  private BigDecimal empPay=new BigDecimal(0.00);
  
  private String beginMonth="";
  
  private String endMonth="";
  
  private String addPayReason="";
  
  private String orgId="";
  
  private String unitName="";
  
  private String docNumber="";
  
  private Integer personSum=new Integer(0);
  
  private BigDecimal empPaySum=new BigDecimal(0.00);
  
  private BigDecimal orgPaySum=new BigDecimal(0.00);

  private BigDecimal paySum=new BigDecimal(0.00);
  
  private String type="";
  
  private String tempPickType="";
  
  private String listCount="";
  
  private String empId="";
  
  private Map sexMap=new HashMap();
  
  private String office="";
  private String collbankname="";
  private String collbankid;
  private String bankid="";
  private String bankname="";
 
  private AddPayTail addPayTail=new AddPayTail();
  
  private String sex="";
  private String orgName="";
  
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
  
  private String bizDate="";
  /** 挂账余额*/
  private BigDecimal overPay = new BigDecimal(0.00);
  
  private String noteNum =  "";
  
  private String transactorName = "";//经办人
  
  private String transactorTel = "";//经办人电话
  
  private Map addPayTypeMap=new HashMap();//补缴类型下拉框
  private String addPayType = "";//补缴类型
  
  public String getAddPayType() {
    return addPayType;
  }

  public void setAddPayType(String addPayType) {
    this.addPayType = addPayType;
  }

  public BigDecimal getOverPay() {
    return overPay;
  }

  public void setOverPay(BigDecimal overPay) {
    this.overPay = overPay;
  }

  public String getSex() {
    return sex;
  }

  public void setSex(String sex) {
    this.sex = sex;
  }

  public Map getSexMap() {
    return sexMap;
  }

  public void setSexMap(Map sexMap) {
    this.sexMap = sexMap;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public List getPersonAddPayList() {
    return personAddPayList;
  }

  public void setPersonAddPayList(List personAddPayList) {
    this.personAddPayList = personAddPayList;
  }

  public String getDocNumber() {
    return docNumber;
  }

  public void setDocNumber(String docNumber) {
    this.docNumber = docNumber;
  }

  public String getOrgId() {
    return orgId;
  }

  public void setOrgId(String orgId) {
    this.orgId = orgId;
  }

  public String getUnitName() {
    return unitName;
  }

  public void setUnitName(String unitName) {
    this.unitName = unitName;
  }

  public BigDecimal getEmpPaySum() {
    return empPaySum;
  }

  public void setEmpPaySum(BigDecimal empPaySum) {
    this.empPaySum = empPaySum;
  }

  public BigDecimal getOrgPaySum() {
    return orgPaySum;
  }

  public void setOrgPaySum(BigDecimal orgPaySum) {
    this.orgPaySum = orgPaySum;
  }

  public BigDecimal getPaySum() {
    return paySum;
  }

  public void setPaySum(BigDecimal paySum) {
    this.paySum = paySum;
  }

  public Integer getPersonSum() {
    return personSum;
  }

  public void setPersonSum(Integer personSum) {
    this.personSum = personSum;
  }

  public String getAddPayReason() {
    return addPayReason;
  }

  public void setAddPayReason(String addPayReason) {
    this.addPayReason = addPayReason;
  }

  public String getBeginMonth() {
    return beginMonth;
  }

  public void setBeginMonth(String beginMonth) {
    this.beginMonth = beginMonth;
  }

  public BigDecimal getEmpPay() {
    return empPay;
  }

  public void setEmpPay(BigDecimal empPay) {
    this.empPay = empPay;
  }

  public String getEndMonth() {
    return endMonth;
  }

  public void setEndMonth(String endMonth) {
    this.endMonth = endMonth;
  }

  public BigDecimal getOrgPay() {
    return orgPay;
  }

  public void setOrgPay(BigDecimal orgPay) {
    this.orgPay = orgPay;
  }

  public AddPayTail getAddPayTail() {
    return addPayTail;
  }

  public void setAddPayTail(AddPayTail addPayTail) {
    this.addPayTail = addPayTail;
  }

  public String getListCount() {
    return listCount;
  }

  public void setListCount(String listCount) {
    this.listCount = listCount;
  }

  public String getEmpId() {
    return empId;
  }

  public void setEmpId(String empId) {
    this.empId = empId;
  }
  public void reset() {
    // TODO Auto-generated method stub
   this.orgPay=new BigDecimal(0.00);
   this.empPay=new BigDecimal(0.00); 
   this.beginMonth="";
   this.endMonth="";
   this.sex="";
   this.empPaySum=new BigDecimal(0.00);
   this.orgPaySum=new BigDecimal(0.00);
   this.paySum=new BigDecimal(0.00);
   this.addPayTail=new AddPayTail();
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

  public String getTempPickType() {
    return tempPickType;
  }

  public void setTempPickType(String tempPickType) {
    this.tempPickType = tempPickType;
  }

  public String getOrgName() {
    return orgName;
  }

  public void setOrgName(String orgName) {
    this.orgName = orgName;
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

  public String getBizDate() {
    return bizDate;
  }

  public void setBizDate(String bizDate) {
    this.bizDate = bizDate;
  }

  public String getNoteNum() {
    return noteNum;
  }

  public void setNoteNum(String noteNum) {
    this.noteNum = noteNum;
  }

  public String getTransactorName() {
    return transactorName;
  }

  public void setTransactorName(String transactorName) {
    this.transactorName = transactorName;
  }

  public String getTransactorTel() {
    return transactorTel;
  }

  public void setTransactorTel(String transactorTel) {
    this.transactorTel = transactorTel;
  }

  public Map getAddPayTypeMap() {
    return addPayTypeMap;
  }

  public void setAddPayTypeMap(Map addPayTypeMap) {
    this.addPayTypeMap = addPayTypeMap;
  }
 
}
