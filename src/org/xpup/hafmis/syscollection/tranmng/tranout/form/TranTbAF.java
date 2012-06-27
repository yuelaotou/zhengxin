package org.xpup.hafmis.syscollection.tranmng.tranout.form;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.xpup.hafmis.common.form.IdAF;
import org.xpup.hafmis.syscollection.common.domain.entity.TranOutTail;

public class TranTbAF extends IdAF {
  
  private List list = null;
  private TranOutTail tranOutTail=new TranOutTail();
  
  private String outOrgId = null;
  private String outOrgname = null;
  private String inOrgId = null;
  private String inOrgName = null;
  private String collBankId = "";
  private String note_num = null;//票据编号
  private String doc_num = null;// 凭证号
  private BigDecimal persons = new BigDecimal(0.00);//人数
  private Map map = null;
  private String money = null;//金额
  private String interest = null;//利息
  private String sum_Money = null;//单位总金额
  private String dates = null;//日期
  private String datesa = null;//日期
  private String payStatus = null;//状态
  /*
   * 转出维护----中间金额
   */
  private BigDecimal  sum_salary = new BigDecimal(0.00);//总金额
  private BigDecimal sum_Interst = new BigDecimal(0.00);//总利息
  private BigDecimal sum_sum = new BigDecimal(0.00);//所有单位总金额
  private Integer sum_counts = new Integer(0);//所有单位总人数
  
  
  
  public Map getMap() {
    return map;
  }
  public void setMap(Map map) {
    this.map = map;
  }

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
  public String getDates() {
    return dates;
  }
  public void setDates(String dates) {
    this.dates = dates;
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
  public String getMoney() {
    return money;
  }
  public void setMoney(String money) {
    this.money = money;
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

  public BigDecimal getPersons() {
    return persons;
  }
  public void setPersons(BigDecimal persons) {
    this.persons = persons;
  }
  public String getSum_Money() {
    return sum_Money;
  }
  public void setSum_Money(String sum_Money) {
    this.sum_Money = sum_Money;
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
  public BigDecimal getSum_sum() {
    return sum_sum;
  }
  public void setSum_sum(BigDecimal sum_sum) {
    this.sum_sum = sum_sum;
  }
  public Integer getSum_counts() {
    return sum_counts;
  }
  public void setSum_counts(Integer sum_counts) {
    this.sum_counts = sum_counts;
  }
  public String getCollBankId() {
    return collBankId;
  }
  public void setCollBankId(String collBankId) {
    this.collBankId = collBankId;
  }
  public String getDatesa() {
    return datesa;
  }
  public void setDatesa(String datesa) {
    this.datesa = datesa;
  }



}
