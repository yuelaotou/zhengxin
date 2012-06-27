package org.xpup.hafmis.syscollection.chgbiz.chgpay.form;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.validator.ValidatorActionForm;
import org.xpup.hafmis.syscollection.common.domain.entity.ChgOrgRate;
import org.xpup.hafmis.syscollection.common.domain.entity.Emp;
import org.xpup.hafmis.syscollection.common.domain.entity.Org;

/**
 * @author 吴洪涛 2007-6-27
 */
public class ChgpayListAF extends ActionForm {

  private static final long serialVersionUID = 0L;


  // chgMonth调整年月
  //合计：调整人数oldPaymenSum
  //调整前单位缴额oldOrgPaySum;
  //调整后单位缴额oldOrgPaySum;
  //调整前职工缴额orgPaySum;
  //调整前职工缴额oldEmpPaySum;
  //调整后职工缴额empPaySum;
  //调整前总缴额oldPayment;
  //调整后总缴额paySum
  private Org org = new Org();

  private Serializable id;
  private int oldPaymenSum=0;
  private Double oldOrgPaySum=new Double(0.00);
  
  private Double orgPaySum=new Double(0.00);
  private Double oldEmpPaySum=new Double(0.00);
  private Double empPaySum=new Double(0.00);

  private Double paySum=new Double(0.00);
  private BigDecimal oldPayment = new BigDecimal(0.00);

  private String name = "";
  private String chgMonth = "";
  private String type = "1";
  private String typetb = "1";
  private String statetype;
  private String listCount ;
  private List list;

  private Map chgpayMap=new HashMap();


  public Map getChgpayMap() {
    return chgpayMap;
  }

  public void setChgpayMap(Map chgpayMap) {
    this.chgpayMap = chgpayMap;
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

 
  public Serializable getId() {
    return id;
  }

  public void setId(Serializable id) {
    this.id = id;
  }

  public int getOldPaymenSum() {
    return oldPaymenSum;
  }

  public void setOldPaymenSum(int oldPaymenSum) {
    this.oldPaymenSum = oldPaymenSum;
  }

  public Double getPaySum() {
    return paySum;
  }

  public void setPaySum(Double paySum) {
    this.paySum = paySum;
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

  public Double getOrgPaySum() {
    return orgPaySum;
  }

  public void setOrgPaySum(Double orgPaySum) {
    this.orgPaySum = orgPaySum;
  }

  public void setEmpPaySum(Double empPaySum) {
    this.empPaySum = empPaySum;
  }

  public Double getEmpPaySum() {
    return empPaySum;
  }

  public BigDecimal getOldPayment() {
    return oldPayment;
  }

  public void setOldPayment(BigDecimal oldPayment) {
    this.oldPayment = oldPayment;
  }


  public String getListCount() {
    return listCount;
  }

  public void setListCount(String listCount) {
    this.listCount = listCount;
  }


  /* (non-Javadoc)
   * @see org.apache.struts.action.ActionForm#reset(org.apache.struts.action.ActionMapping, javax.servlet.http.HttpServletRequest)
   */
  public void reset(ActionMapping arg0, HttpServletRequest arg1) {
    // TODO Auto-generated method stub
    this.type = "1";
//    super.reset(arg0, arg1);
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public String getTypetb() {
    return typetb;
  }

  public void setTypetb(String typetb) {
    this.typetb = typetb;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getStatetype() {
    return statetype;
  }

  public void setStatetype(String statetype) {
    this.statetype = statetype;
  }





}
