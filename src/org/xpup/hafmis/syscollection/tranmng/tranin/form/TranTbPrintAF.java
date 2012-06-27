package org.xpup.hafmis.syscollection.tranmng.tranin.form;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.xpup.hafmis.common.form.IdAF;
import org.xpup.hafmis.syscollection.common.domain.entity.TranOutTail;

public class TranTbPrintAF extends IdAF {
  
  private List list = null;
  private List lista = null;
  private TranOutTail tranOutTail=new TranOutTail();
  
  private String outOrgId = "";
  private String outOrgname = "";
  private String outOrgOpenBank = "";
  private String outOrgCollBank = "";
  private String inPayBankAccNum = "";
  private String inOrgId = "";
  private String inOrgName = "";
  private String inOrgOpenBank = "";
  private String inOrgCollBank = "";
  private String outPayBankAccNum = "";
  
  private String note_num = "";//票据编号
  private String doc_num = "";// 凭证号
  private BigDecimal sumMoney = new BigDecimal(0.00);//总金额
  private String interest = null;//利息
  private String endDate = "";
  private String startDate = "";
  private String payStatus = null;//状态
  private String operator = "";
  /*
   * 转出维护----中间金额
   */
  private BigDecimal  sum_salary = new BigDecimal(0.00);//总金额
  private BigDecimal sum_Interst = new BigDecimal(0.00);//总利息
  private Integer sum_counts = new Integer(0);//所有单位总人数
  
  
  

  public List getList() {
    return list;
  }
  public void setList(List list) {
    this.list = list;
  }

  public String getPayStatus() {
    return payStatus;
  }
  public void setPayStatus(String payStatus) {
    this.payStatus = payStatus;
  }

  public void reset(List list,String id) {
    // TODO Auto-generated method stub
   this.list = null;
  }
  public TranOutTail getTranOutTail() {
    return tranOutTail;
  }
  public void setTranOutTail(TranOutTail tranOutTail) {
    this.tranOutTail = tranOutTail;
  }
  public String getDoc_num() {
    return doc_num;
  }
  public void setDoc_num(String doc_num) {
    this.doc_num = doc_num;
  }
  public String getInOrgId() {
    return inOrgId;
  }
  public void setInOrgId(String inOrgId) {
    this.inOrgId = inOrgId;
  }
  public String getInOrgName() {
    return inOrgName;
  }
  public void setInOrgName(String inOrgName) {
    this.inOrgName = inOrgName;
  }
  public String getInterest() {
    return interest;
  }
  public void setInterest(String interest) {
    this.interest = interest;
  }
  public String getNote_num() {
    return note_num;
  }
  public void setNote_num(String note_num) {
    this.note_num = note_num;
  }
  public String getOutOrgId() {
    return outOrgId;
  }
  public void setOutOrgId(String outOrgId) {
    this.outOrgId = outOrgId;
  }
  public String getOutOrgname() {
    return outOrgname;
  }
  public void setOutOrgname(String outOrgname) {
    this.outOrgname = outOrgname;
  }
  public BigDecimal getSum_Interst() {
    return sum_Interst;
  }
  public void setSum_Interst(BigDecimal sum_Interst) {
    this.sum_Interst = sum_Interst;
  }
  public BigDecimal getSum_salary() {
    return sum_salary;
  }
  public void setSum_salary(BigDecimal sum_salary) {
    this.sum_salary = sum_salary;
  }
  public Integer getSum_counts() {
    return sum_counts;
  }
  public void setSum_counts(Integer sum_counts) {
    this.sum_counts = sum_counts;
  }
  public String getEndDate() {
    return endDate;
  }
  public void setEndDate(String endDate) {
    this.endDate = endDate;
  }
  public String getInOrgOpenBank() {
    return inOrgOpenBank;
  }
  public void setInOrgOpenBank(String inOrgOpenBank) {
    this.inOrgOpenBank = inOrgOpenBank;
  }
  public String getOperator() {
    return operator;
  }
  public void setOperator(String operator) {
    this.operator = operator;
  }
  public String getOutOrgOpenBank() {
    return outOrgOpenBank;
  }
  public void setOutOrgOpenBank(String outOrgOpenBank) {
    this.outOrgOpenBank = outOrgOpenBank;
  }
  public String getStartDate() {
    return startDate;
  }
  public void setStartDate(String startDate) {
    this.startDate = startDate;
  }
  public BigDecimal getSumMoney() {
    return sumMoney;
  }
  public void setSumMoney(BigDecimal sumMoney) {
    this.sumMoney = sumMoney;
  }
  public String getInOrgCollBank() {
    return inOrgCollBank;
  }
  public void setInOrgCollBank(String inOrgCollBank) {
    this.inOrgCollBank = inOrgCollBank;
  }
  public String getOutOrgCollBank() {
    return outOrgCollBank;
  }
  public void setOutOrgCollBank(String outOrgCollBank) {
    this.outOrgCollBank = outOrgCollBank;
  }
  public String getOutPayBankAccNum() {
    return outPayBankAccNum;
  }
  public void setOutPayBankAccNum(String outPayBankAccNum) {
    this.outPayBankAccNum = outPayBankAccNum;
  }
  public String getInPayBankAccNum() {
    return inPayBankAccNum;
  }
  public void setInPayBankAccNum(String inPayBankAccNum) {
    this.inPayBankAccNum = inPayBankAccNum;
  }
  public List getLista() {
    return lista;
  }
  public void setLista(List lista) {
    this.lista = lista;
  }



}
