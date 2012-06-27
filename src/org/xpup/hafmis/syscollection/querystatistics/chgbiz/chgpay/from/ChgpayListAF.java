package org.xpup.hafmis.syscollection.querystatistics.chgbiz.chgpay.from;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletRequest;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.validator.ValidatorActionForm;

import org.xpup.hafmis.syscollection.common.domain.entity.Org;

/**
 * @author 吴洪涛 2007-6-27
 */
public class ChgpayListAF extends  ActionForm {

 

  // 办事处：BA001.OFFICECODE
  // 归集银行：BA001.COLLECTION_BANK_ID
  // 单位编号：AA202.ORG_ID
  // 单位名称：BA001.NAME
  // 调整年月：AA202.CHG_MONTH
  // 调整日期：AA202.BIZ_DATE
  // 状态：AA202.CHG_STATUS
  private Org org = new Org();

  private String listCount;

  private List list;

  private List totalorgChgPaymentPayment;

  private List bankList1 = null;// 归集银行list

  private List officeList1 = null;// 制单人list

  private String type = "1";

  private Map map = null;

  /** 办事处： */
  private String officecode;

  /** 归集银行 */
  private String collectionBankId;

  /** 单位编号 */
  private String orgId;

  /** 单位名称 */
  private String orgName = "";

  /** 从调整年月 */
  private String startChgMonth = "";

  /** 至调整年月 */
  private String endChgMonth = "";

  /** 从调整日期 */
  private String startBizDate = "";

  /** 至调整日期 */
  private String endBizDate = "";

  /** 状态： */
  private Integer chgStatus = new Integer(0);

  /** 调整单位数 */
  private int oldPaymentOrg;

  /** 调整前应缴总额 totalOldPayment(各单位相加oldPaySum)： */

  private BigDecimal totalPaySum = new BigDecimal(0.00);

  /** 调整后应缴额总额totalPaySum(各单位相加paySum) */
  private BigDecimal totalOldPayment = new BigDecimal(0.00);

  public void reset(ActionMapping mapping, ServletRequest request) {
    this.startBizDate = "";
    this.endBizDate = "";
    this.startChgMonth = "";
    this.endChgMonth = "";
    this.chgStatus = new Integer(0);
    this.collectionBankId = "";
    this.orgName = "";
    this.officecode = "";
    this.orgId = "";

  }

  public List getList() {
    return list;
  }

  public void setList(List list) {
    this.list = list;
  }

  public Org getOrg() {
    return org;
  }

  public void setOrg(Org org) {
    this.org = org;
  }

  public String getListCount() {
    return listCount;
  }

  public void setListCount(String listCount) {
    this.listCount = listCount;
  }

  public String getEndBizDate() {
    return endBizDate;
  }

  public void setEndBizDate(String endBizDate) {
    this.endBizDate = endBizDate;
  }

  public String getStartBizDate() {
    return startBizDate;
  }

  public void setStartBizDate(String startBizDate) {
    this.startBizDate = startBizDate;
  }

  public String getEndChgMonth() {
    return endChgMonth;
  }

  public void setEndChgMonth(String endChgMonth) {
    this.endChgMonth = endChgMonth;
  }

  public String getStartChgMonth() {
    return startChgMonth;
  }

  public void setStartChgMonth(String startChgMonth) {
    this.startChgMonth = startChgMonth;
  }

  public Integer getChgStatus() {
    return chgStatus;
  }

  public void setChgStatus(Integer chgStatus) {
    this.chgStatus = chgStatus;
  }

  public String getCollectionBankId() {
    return collectionBankId;
  }

  public void setCollectionBankId(String collectionBankId) {
    this.collectionBankId = collectionBankId;
  }

  public String getOrgName() {
    return orgName;
  }

  public void setOrgName(String orgName) {
    this.orgName = orgName;
  }

  public String getOfficecode() {
    return officecode;
  }

  public void setOfficecode(String officecode) {
    this.officecode = officecode;
  }

  public String getOrgId() {
    return orgId;
  }

  public void setOrgId(String orgId) {
    this.orgId = orgId;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public Map getMap() {
    return map;
  }

  public void setMap(Map map) {
    this.map = map;
  }

  public int getOldPaymentOrg() {
    return oldPaymentOrg;
  }

  public void setOldPaymentOrg(int oldPaymentOrg) {
    this.oldPaymentOrg = oldPaymentOrg;
  }

  public BigDecimal getTotalOldPayment() {
    return totalOldPayment;
  }

  public void setTotalOldPayment(BigDecimal totalOldPayment) {
    this.totalOldPayment = totalOldPayment;
  }

  public BigDecimal getTotalPaySum() {
    return totalPaySum;
  }

  public void setTotalPaySum(BigDecimal totalPaySum) {
    this.totalPaySum = totalPaySum;
  }

  public List getTotalorgChgPaymentPayment() {
    return totalorgChgPaymentPayment;
  }

  public void setTotalorgChgPaymentPayment(List totalorgChgPaymentPayment) {
    this.totalorgChgPaymentPayment = totalorgChgPaymentPayment;
  }

  public List getBankList1() {
    return bankList1;
  }

  public void setBankList1(List bankList1) {
    this.bankList1 = bankList1;
  }

  public List getOfficeList1() {
    return officeList1;
  }

  public void setOfficeList1(List officeList1) {
    this.officeList1 = officeList1;
  }

}
