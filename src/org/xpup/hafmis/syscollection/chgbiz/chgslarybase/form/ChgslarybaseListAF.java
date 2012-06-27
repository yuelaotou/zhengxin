package org.xpup.hafmis.syscollection.chgbiz.chgslarybase.form;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import javax.servlet.ServletRequest;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.validator.ValidatorActionForm;
import org.xpup.hafmis.syscollection.common.domain.entity.ChgOrgRate;
import org.xpup.hafmis.syscollection.common.domain.entity.Emp;
import org.xpup.hafmis.syscollection.common.domain.entity.Org;

/**
 * @author 吴洪涛 2007-6-27
 */
public class ChgslarybaseListAF extends ActionForm {

  private static final long serialVersionUID = 0L;

  // chgMonth调整年月
  // 合计：调整人数oldSalaryBaseSum
  // 调整前工资基数olddSalaryBase;
  // 调整前总缴额oldPayment;

  // 调整后工资基数salaryBase;
  // 调整后单位缴额orgPaySum;
  // 调整后职工缴额empPaySum;
  // 调整后缴额合计totalAmount;
  // 调整后总缴额paySum

  // 调整前单位缴额oldOrgPaySum;
  // 调整前职工缴额oldEmpPaySum;
  // 调整前缴额合计oldTotalAmount;
  private Double oldOrgPaySum = new Double(0.00);

  private Double oldEmpPaySum = new Double(0.00);

  private Double oldTotalAmount = new Double(0.00);

  private Org org = new Org();

  private int oldSalaryBaseSum = 0;

  private Double olddSalaryBase = new Double(0.00);

  private Double salaryBase = new Double(0.00);

  private Double orgPaySum = new Double(0.00);

  private Double empPaySum = new Double(0.00);

  private Double totalAmount = new Double(0.00);
  
  private Double data_1 = new Double(0.00);

  private Double data_2 = new Double(0.00);

  private Double data_3 = new Double(0.00);

  private Double data_4 = new Double(0.00);
  
  private String data_5="";
  
  private Double data_6 = new Double(0.00);//减少的工资基数

  private Double data_7 = new Double(0.00);//增加的工资基数

  private Double data_8 = new Double(0.00);//减少的缴额

  private Double data_9 = new Double(0.00);//增加的缴额

  private BigDecimal oldPayment = new BigDecimal(0.00);

  private Double paySum = new Double(0.00);

  private Serializable id;

  private String type = "1";

  private String typetb = "1";

  private String statetype;

  private String chgMonthModify = "";

  private String chgMonth = "";
  
  private String endChgMonth="";

  private String listCount;

  private List list;
  //吴洪涛2008616
  private List chgslarybaseCellList;
  //吴洪涛2008616


  public void reset(ActionMapping mapping, ServletRequest request) {

    this.chgMonth = "";

  }

  public List getList() {
    return list;
  }

  public void setList(List list) {
    this.list = list;
  }

  public String getChgMonth() {
    return chgMonth;
  }

  public void setChgMonth(String chgMonth) {
    this.chgMonth = chgMonth;
  }

  public Org getOrg() {
    return org;
  }

  public void setOrg(Org org) {
    this.org = org;
  }

  public Double getEmpPaySum() {
    return empPaySum;
  }

  public void setEmpPaySum(Double empPaySum) {
    this.empPaySum = empPaySum;
  }

  public BigDecimal getOldPayment() {
    return oldPayment;
  }

  public void setOldPayment(BigDecimal oldPayment) {
    this.oldPayment = oldPayment;
  }

  public int getOldSalaryBaseSum() {
    return oldSalaryBaseSum;
  }

  public void setOldSalaryBaseSum(int oldSalaryBaseSum) {
    this.oldSalaryBaseSum = oldSalaryBaseSum;
  }

  public Double getOrgPaySum() {
    return orgPaySum;
  }

  public void setOrgPaySum(Double orgPaySum) {
    this.orgPaySum = orgPaySum;
  }

  public Double getPaySum() {
    return paySum;
  }

  public void setPaySum(Double paySum) {
    this.paySum = paySum;
  }

  public Double getSalaryBase() {
    return salaryBase;
  }

  public void setSalaryBase(Double salaryBase) {
    this.salaryBase = salaryBase;
  }

  public Double getTotalAmount() {
    return totalAmount;
  }

  public void setTotalAmount(Double totalAmount) {
    this.totalAmount = totalAmount;
  }

  public Serializable getId() {
    return id;
  }

  public void setId(Serializable id) {
    this.id = id;
  }

  public Double getOlddSalaryBase() {
    return olddSalaryBase;
  }

  public void setOlddSalaryBase(Double olddSalaryBase) {
    this.olddSalaryBase = olddSalaryBase;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public String getChgMonthModify() {
    return chgMonthModify;
  }

  public void setChgMonthModify(String chgMonthModify) {
    this.chgMonthModify = chgMonthModify;
  }

  public String getListCount() {
    return listCount;
  }

  public void setListCount(String listCount) {
    this.listCount = listCount;
  }

  public String getTypetb() {
    return typetb;
  }

  public void setTypetb(String typetb) {
    this.typetb = typetb;
  }

  public String getStatetype() {
    return statetype;
  }

  public void setStatetype(String statetype) {
    this.statetype = statetype;
  }

  public Double getOldEmpPaySum() {
    return oldEmpPaySum;
  }

  public void setOldEmpPaySum(Double oldEmpPaySum) {
    this.oldEmpPaySum = oldEmpPaySum;
  }

  public Double getOldOrgPaySum() {
    return oldOrgPaySum;
  }

  public void setOldOrgPaySum(Double oldOrgPaySum) {
    this.oldOrgPaySum = oldOrgPaySum;
  }

  public Double getOldTotalAmount() {
    return oldTotalAmount;
  }

  public void setOldTotalAmount(Double oldTotalAmount) {
    this.oldTotalAmount = oldTotalAmount;
  }

  public List getChgslarybaseCellList() {
    return chgslarybaseCellList;
  }

  public void setChgslarybaseCellList(List chgslarybaseCellList) {
    this.chgslarybaseCellList = chgslarybaseCellList;
  }

  public Double getData_1() {
    return data_1;
  }

  public void setData_1(Double data_1) {
    this.data_1 = data_1;
  }

  public Double getData_2() {
    return data_2;
  }

  public void setData_2(Double data_2) {
    this.data_2 = data_2;
  }

  public Double getData_3() {
    return data_3;
  }

  public void setData_3(Double data_3) {
    this.data_3 = data_3;
  }

  public Double getData_4() {
    return data_4;
  }

  public void setData_4(Double data_4) {
    this.data_4 = data_4;
  }

  public String getData_5() {
    return data_5;
  }

  public void setData_5(String data_5) {
    this.data_5 = data_5;
  }

  public String getEndChgMonth() {
    return endChgMonth;
  }

  public void setEndChgMonth(String endChgMonth) {
    this.endChgMonth = endChgMonth;
  }

  public Double getData_6() {
    return data_6;
  }

  public void setData_6(Double data_6) {
    this.data_6 = data_6;
  }

  public Double getData_7() {
    return data_7;
  }

  public void setData_7(Double data_7) {
    this.data_7 = data_7;
  }

  public Double getData_8() {
    return data_8;
  }

  public void setData_8(Double data_8) {
    this.data_8 = data_8;
  }

  public Double getData_9() {
    return data_9;
  }

  public void setData_9(Double data_9) {
    this.data_9 = data_9;
  }

}
